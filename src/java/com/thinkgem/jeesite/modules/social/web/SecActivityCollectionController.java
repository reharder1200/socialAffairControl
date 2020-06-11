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
import com.thinkgem.jeesite.modules.social.entity.SecActivityCollection;
import com.thinkgem.jeesite.modules.social.service.SecActivityCollectionService;

/**
 * 活动收藏信息Controller
 * @author hll
 * @version 2020-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/social/secActivityCollection")
public class SecActivityCollectionController extends BaseController {

	@Autowired
	private SecActivityCollectionService secActivityCollectionService;
	
	@ModelAttribute
	public SecActivityCollection get(@RequestParam(required=false) String id) {
		SecActivityCollection entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = secActivityCollectionService.get(id);
		}
		if (entity == null){
			entity = new SecActivityCollection();
		}
		return entity;
	}
	
	@RequiresPermissions("social:secActivityCollection:view")
	@RequestMapping(value = {"list", ""})
	public String list(SecActivityCollection secActivityCollection, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SecActivityCollection> page = secActivityCollectionService.findPage(new Page<SecActivityCollection>(request, response), secActivityCollection); 
		model.addAttribute("page", page);
		return "modules/social/secActivityCollectionList";
	}

	@RequiresPermissions("social:secActivityCollection:view")
	@RequestMapping(value = "form")
	public String form(SecActivityCollection secActivityCollection, Model model) {
		model.addAttribute("secActivityCollection", secActivityCollection);
		return "modules/social/secActivityCollectionForm";
	}

	@RequiresPermissions("social:secActivityCollection:edit")
	@RequestMapping(value = "save")
	public String save(SecActivityCollection secActivityCollection, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, secActivityCollection)){
			return form(secActivityCollection, model);
		}
		secActivityCollectionService.save(secActivityCollection);
		addMessage(redirectAttributes, "保存活动收藏信息成功");
		return "redirect:"+Global.getAdminPath()+"/social/secActivityCollection/?repage";
	}
	
	@RequiresPermissions("social:secActivityCollection:edit")
	@RequestMapping(value = "delete")
	public String delete(SecActivityCollection secActivityCollection, RedirectAttributes redirectAttributes) {
		secActivityCollectionService.delete(secActivityCollection);
		addMessage(redirectAttributes, "删除活动收藏信息成功");
		return "redirect:"+Global.getAdminPath()+"/social/secActivityCollection/?repage";
	}

}