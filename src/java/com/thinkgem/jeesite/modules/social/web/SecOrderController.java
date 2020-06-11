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
import com.thinkgem.jeesite.modules.social.entity.SecOrder;
import com.thinkgem.jeesite.modules.social.service.SecOrderService;

/**
 * 订单Controller
 * @author hll
 * @version 2020-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/social/secOrder")
public class SecOrderController extends BaseController {

	@Autowired
	private SecOrderService secOrderService;
	
	@ModelAttribute
	public SecOrder get(@RequestParam(required=false) String orderId) {
		SecOrder entity = null;
		if (StringUtils.isNotBlank(orderId)){
			entity = secOrderService.get(orderId);
		}
		if (entity == null){
			entity = new SecOrder();
		}
		return entity;
	}
	
	@RequiresPermissions("social:secOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(SecOrder secOrder, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SecOrder> page = secOrderService.findPage(new Page<SecOrder>(request, response), secOrder); 
		model.addAttribute("page", page);
		return "modules/social/secOrderList";
	}

	@RequiresPermissions("social:secOrder:view")
	@RequestMapping(value = "form")
	public String form(SecOrder secOrder, Model model) {
		model.addAttribute("secOrder", secOrder);
		return "modules/social/secOrderForm";
	}

	@RequiresPermissions("social:secOrder:edit")
	@RequestMapping(value = "save")
	public String save(SecOrder secOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, secOrder)){
			return form(secOrder, model);
		}
		secOrderService.save(secOrder);
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:"+Global.getAdminPath()+"/social/secOrder/?repage";
	}
	
	@RequiresPermissions("social:secOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(SecOrder secOrder, RedirectAttributes redirectAttributes) {
		secOrderService.delete(secOrder);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:"+Global.getAdminPath()+"/social/secOrder/?repage";
	}

}