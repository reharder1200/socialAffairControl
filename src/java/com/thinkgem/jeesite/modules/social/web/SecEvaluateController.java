/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.web;

import java.util.List;

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
import com.thinkgem.jeesite.modules.social.entity.SecActivity;
import com.thinkgem.jeesite.modules.social.entity.SecEvaluate;
import com.thinkgem.jeesite.modules.social.entity.SecUser;
import com.thinkgem.jeesite.modules.social.service.SecActivityService;
import com.thinkgem.jeesite.modules.social.service.SecEvaluateService;
import com.thinkgem.jeesite.modules.social.service.SecUserService;

/**
 * 评价信息Controller
 * @author hll
 * @version 2020-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/social/secEvaluate")
public class SecEvaluateController extends BaseController {

	@Autowired
	private SecEvaluateService secEvaluateService;
	@Autowired
	private SecActivityService secActivityService;
	@Autowired
	private SecUserService secUserService;
	
	@ModelAttribute
	public SecEvaluate get(@RequestParam(required=false) String id) {
		SecEvaluate entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = secEvaluateService.get(id);
		}
		if (entity == null){
			entity = new SecEvaluate();
		}
		return entity;
	}
	
	@RequiresPermissions("social:secEvaluate:view")
	@RequestMapping(value = {"list", ""})
	public String list(SecEvaluate secEvaluate, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SecEvaluate> page = secEvaluateService.findPage(new Page<SecEvaluate>(request, response), secEvaluate); 
		model.addAttribute("page", page);
		return "modules/social/secEvaluateList";
	}

	@RequiresPermissions("social:secEvaluate:view")
	@RequestMapping(value = "form")
	public String form(SecEvaluate secEvaluate, Model model) {
		model.addAttribute("secEvaluate", secEvaluate);
		
		//查询所有活动列表
		SecActivity secActivity = new SecActivity();
		List<SecActivity> secActivityList = secActivityService.findList(secActivity);
		model.addAttribute("secActivityList", secActivityList);
		
		//查询所有注册用户列表
		SecUser secUser = new SecUser();
		List<SecUser> secUserList = secUserService.findList(secUser);
		model.addAttribute("secUserList",secUserList);
		
		return "modules/social/secEvaluateForm";
	}

	@RequiresPermissions("social:secEvaluate:edit")
	@RequestMapping(value = "save")
	public String save(SecEvaluate secEvaluate, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, secEvaluate)){
			return form(secEvaluate, model);
		}
		secEvaluateService.save(secEvaluate);
		addMessage(redirectAttributes, "保存评价信息成功");
		return "redirect:"+Global.getAdminPath()+"/social/secEvaluate/?repage";
	}
	
	@RequiresPermissions("social:secEvaluate:edit")
	@RequestMapping(value = "delete")
	public String delete(SecEvaluate secEvaluate, RedirectAttributes redirectAttributes) {
		secEvaluateService.delete(secEvaluate);
		addMessage(redirectAttributes, "删除评价信息成功");
		return "redirect:"+Global.getAdminPath()+"/social/secEvaluate/?repage";
	}

}