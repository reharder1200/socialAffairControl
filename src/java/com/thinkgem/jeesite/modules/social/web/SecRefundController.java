/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.social.entity.SecRefund;
import com.thinkgem.jeesite.modules.social.service.SecRefundService;
import com.thinkgem.jeesite.modules.social.util.HttpUtils;

import net.sf.json.JSONObject;

/**
 * 退款Controller
 * 
 * @author hll
 * @version 2020-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/social/secRefund")
public class SecRefundController extends BaseController {

	@Autowired
	private SecRefundService secRefundService;

	@ModelAttribute
	public SecRefund get(@RequestParam(required = false) String id) {
		SecRefund entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = secRefundService.get(id);
		}
		if (entity == null) {
			entity = new SecRefund();
		}
		return entity;
	}

	@RequiresPermissions("social:secRefund:view")
	@RequestMapping(value = { "list", "" })
	public String list(SecRefund secRefund, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<SecRefund> page = secRefundService.findPage(new Page<SecRefund>(
				request, response), secRefund);
		model.addAttribute("page", page);
		return "modules/social/secRefundList";
	}

	@RequiresPermissions("social:secRefund:view")
	@RequestMapping(value = "form")
	public String form(SecRefund secRefund, Model model) {
		model.addAttribute("secRefund", secRefund);
		return "modules/social/secRefundForm";
	}

	@RequiresPermissions("social:secRefund:edit")
	@RequestMapping(value = "save")
	public String save(SecRefund secRefund, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, secRefund)) {
			return form(secRefund, model);
		}
		secRefundService.save(secRefund);
		addMessage(redirectAttributes, "保存退款成功");
		return "redirect:" + Global.getAdminPath()
				+ "/social/secRefund/?repage";
	}

	@RequiresPermissions("social:secRefund:edit")
	@RequestMapping(value = "delete")
	public String delete(SecRefund secRefund,
			RedirectAttributes redirectAttributes) {
		secRefundService.delete(secRefund);
		addMessage(redirectAttributes, "删除退款成功");
		return "redirect:" + Global.getAdminPath()
				+ "/social/secRefund/?repage";
	}

	@RequiresPermissions({ "social:secRefund:edit" })
	@RequestMapping({ "dealRefund" })
	public String dealRefund(SecRefund secRefund,
			RedirectAttributes redirectAttributes) {
		if (secRefund.getRefundStatus().equals(SecRefund.REFUND_STATUS_DONE)) {
			addMessage(redirectAttributes, new String[] { "该退款单已处理过，请勿重复处理" });
		} else {
			String param = "id=" + secRefund.getId();
			String result = HttpUtils.doPost(Global.getConfig("REFUND_URL"),
					param);
			if (result != null) {
				JSONObject jsonObject = JSONObject.fromObject(result);
				if (((Boolean) jsonObject.get("result")).booleanValue()) {
					secRefund = secRefundService.get(secRefund);//接口工程修改了退款订单的订单号，所以需要重新查询一次
					if (jsonObject.get("wxResponse").toString()
							.equals("SUCCESS")) {
						secRefund
								.setRefundStatus(SecRefund.REFUND_STATUS_COMPLETE);
						this.secRefundService.save(secRefund);
						addMessage(redirectAttributes,
								new String[] { "该退款单处理成功，等待微信支付退款至原账户" });
					} else if(jsonObject.get("wxResponse").toString()
							.equals("UNSUCCESS")){
						secRefund.setRefundStatus(SecRefund.REFUND_STATUS_DONE);
						this.secRefundService.save(secRefund);
						addMessage(redirectAttributes,
								new String[] { "该退款单处理成功，退款异常！" });
					}else if(jsonObject.get("wxResponse").toString()
							.equals("FAIL")){
						addMessage(redirectAttributes,
								new String[] { "订单处理异常！异常原因："+jsonObject.get("failReason").toString()});
					}
				} else {
					addMessage(redirectAttributes, new String[] { "处理异常" });
				}
			} else {
				addMessage(redirectAttributes, new String[] { "处理异常" });
			}
		}
		return "redirect:" + Global.getAdminPath()
				+ "/social/secRefund/?repage";
	}

}