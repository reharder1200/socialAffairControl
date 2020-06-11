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
import com.thinkgem.jeesite.modules.social.entity.SecUser;
import com.thinkgem.jeesite.modules.social.service.SecUserService;

/**
 * 用户基本信息Controller
 * @author hll
 * @version 2020-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/social/secUser")
public class SecUserController extends BaseController {

	@Autowired
	private SecUserService secUserService;
	
	@ModelAttribute
	public SecUser get(@RequestParam(required=false) String id) {
		SecUser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = secUserService.get(id);
		}
		if (entity == null){
			entity = new SecUser();
		}
		return entity;
	}
	
	@RequiresPermissions("social:secUser:view")
	@RequestMapping(value = {"list", ""})
	public String list(SecUser secUser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SecUser> page = secUserService.findPage(new Page<SecUser>(request, response), secUser); 
		model.addAttribute("page", page);
		return "modules/social/secUserList";
	}

	@RequiresPermissions("social:secUser:view")
	@RequestMapping(value = "form")
	public String form(SecUser secUser, Model model) {
		model.addAttribute("secUser", secUser);
		return "modules/social/secUserForm";
	}

	@RequiresPermissions("social:secUser:edit")
	@RequestMapping(value = "save")
	public String save(SecUser secUser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, secUser)){
			return form(secUser, model);
		}
		secUserService.save(secUser);
		addMessage(redirectAttributes, "保存用户基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/social/secUser/?repage";
	}
	
	@RequiresPermissions("social:secUser:edit")
	@RequestMapping(value = "delete")
	public String delete(SecUser secUser, RedirectAttributes redirectAttributes) {
		secUserService.delete(secUser);
		addMessage(redirectAttributes, "删除用户基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/social/secUser/?repage";
	}

}