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
import com.thinkgem.jeesite.modules.social.entity.SecUserDetail;
import com.thinkgem.jeesite.modules.social.service.SecUserDetailService;

/**
 * 用户实名信息Controller
 * @author hll
 * @version 2020-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/social/secUserDetail")
public class SecUserDetailController extends BaseController {

	@Autowired
	private SecUserDetailService secUserDetailService;
	
	@ModelAttribute
	public SecUserDetail get(@RequestParam(required=false) String userId) {
		SecUserDetail entity = null;
		if (StringUtils.isNotBlank(userId)){
			entity = secUserDetailService.get(userId);
		}
		if (entity == null){
			entity = new SecUserDetail();
		}
		return entity;
	}
	
	@RequiresPermissions("social:secUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(SecUserDetail secUserDetail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SecUserDetail> page = secUserDetailService.findPage(new Page<SecUserDetail>(request, response), secUserDetail); 
		model.addAttribute("page", page);
		return "modules/social/secUserDetailList";
	}

	@RequiresPermissions("social:secUser:view")
	@RequestMapping(value = "form")
	public String form(SecUserDetail secUserDetail, Model model) {
		model.addAttribute("secUserDetail", secUserDetail);
		return "modules/social/secUserDetailForm";
	}

	@RequiresPermissions("social:secUser:edit")
	@RequestMapping(value = "save")
	public String save(SecUserDetail secUserDetail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, secUserDetail)){
			return form(secUserDetail, model);
		}
		secUserDetailService.save(secUserDetail);
		addMessage(redirectAttributes, "保存用户实名信息成功");
		return "redirect:"+Global.getAdminPath()+"/social/secUserDetail/?repage";
	}
	
	@RequiresPermissions("social:secUser:edit")
	@RequestMapping(value = "delete")
	public String delete(SecUserDetail secUserDetail, RedirectAttributes redirectAttributes) {
		secUserDetailService.delete(secUserDetail);
		addMessage(redirectAttributes, "删除用户实名信息成功");
		return "redirect:"+Global.getAdminPath()+"/social/secUserDetail/?repage";
	}

}