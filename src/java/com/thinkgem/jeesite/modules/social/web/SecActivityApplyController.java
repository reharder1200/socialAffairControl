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
import com.thinkgem.jeesite.modules.social.entity.SecActivityApply;
import com.thinkgem.jeesite.modules.social.entity.SecUser;
import com.thinkgem.jeesite.modules.social.service.SecActivityApplyService;
import com.thinkgem.jeesite.modules.social.service.SecActivityService;
import com.thinkgem.jeesite.modules.social.service.SecUserService;

/**
 * 活动报名信息Controller
 * @author hll
 * @version 2020-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/social/secActivityApply")
public class SecActivityApplyController extends BaseController {

	@Autowired
	private SecActivityApplyService secActivityApplyService;
	@Autowired
	private SecActivityService secActivityService;
	@Autowired
	private SecUserService secUserService;
	
	@ModelAttribute
	public SecActivityApply get(@RequestParam(required=false) String id) {
		SecActivityApply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = secActivityApplyService.get(id);
		}
		if (entity == null){
			entity = new SecActivityApply();
		}
		return entity;
	}
	
	@RequiresPermissions("social:secActivityApply:view")
	@RequestMapping(value = {"list", ""})
	public String list(SecActivityApply secActivityApply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SecActivityApply> page = secActivityApplyService.findPage(new Page<SecActivityApply>(request, response), secActivityApply); 
		model.addAttribute("page", page);
		return "modules/social/secActivityApplyList";
	}

	@RequiresPermissions("social:secActivityApply:view")
	@RequestMapping(value = "form")
	public String form(SecActivityApply secActivityApply, Model model) {
		model.addAttribute("secActivityApply", secActivityApply);

		//查询所有活动列表
		SecActivity secActivity = new SecActivity();
		List<SecActivity> secActivityList = secActivityService.findList(secActivity);
		model.addAttribute("secActivityList", secActivityList);
		
		//查询所有注册用户列表
		SecUser secUser = new SecUser();
		List<SecUser> secUserList = secUserService.findList(secUser);
		model.addAttribute("secUserList",secUserList);
		
		return "modules/social/secActivityApplyForm";
	}

	@RequiresPermissions("social:secActivityApply:edit")
	@RequestMapping(value = "save")
	public String save(SecActivityApply secActivityApply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, secActivityApply)){
			return form(secActivityApply, model);
		}
		secActivityApplyService.save(secActivityApply);
		addMessage(redirectAttributes, "保存活动报名信息成功");
		return "redirect:"+Global.getAdminPath()+"/social/secActivityApply/?repage";
	}
	
	@RequiresPermissions("social:secActivityApply:edit")
	@RequestMapping(value = "delete")
	public String delete(SecActivityApply secActivityApply, RedirectAttributes redirectAttributes) {
		secActivityApplyService.delete(secActivityApply);
		addMessage(redirectAttributes, "删除活动报名信息成功");
		return "redirect:"+Global.getAdminPath()+"/social/secActivityApply/?repage";
	}

}