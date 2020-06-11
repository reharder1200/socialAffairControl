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
import com.thinkgem.jeesite.modules.social.entity.SecActivityAlarm;
import com.thinkgem.jeesite.modules.social.entity.SecUser;
import com.thinkgem.jeesite.modules.social.service.SecActivityAlarmService;
import com.thinkgem.jeesite.modules.social.service.SecActivityService;
import com.thinkgem.jeesite.modules.social.service.SecUserService;

/**
 * 活动提醒Controller
 * @author hll
 * @version 2020-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/social/secActivityAlarm")
public class SecActivityAlarmController extends BaseController {

	@Autowired
	private SecActivityAlarmService secActivityAlarmService;
	@Autowired
	private SecActivityService secActivityService;
	@Autowired
	private SecUserService secUserService;
	
	@ModelAttribute
	public SecActivityAlarm get(@RequestParam(required=false) String id) {
		SecActivityAlarm entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = secActivityAlarmService.get(id);
		}
		if (entity == null){
			entity = new SecActivityAlarm();
		}
		return entity;
	}
	
	@RequiresPermissions("social:secActivityAlarm:view")
	@RequestMapping(value = {"list", ""})
	public String list(SecActivityAlarm secActivityAlarm, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SecActivityAlarm> page = secActivityAlarmService.findPage(new Page<SecActivityAlarm>(request, response), secActivityAlarm); 
		model.addAttribute("page", page);
		
		response.setHeader("Access-Control-Expose-Headers","Cache-Control,Content-Type,Expires,Pragma,Content-Language,Last-Modified,reqId");
		response.addHeader("reqId", "1234gtddeert");
		System.out.println(request.getHeader("cookie"));
		return "modules/social/secActivityAlarmList";
	}

	@RequiresPermissions("social:secActivityAlarm:view")
	@RequestMapping(value = "form")
	public String form(SecActivityAlarm secActivityAlarm, HttpServletRequest request,Model model) {
		model.addAttribute("secActivityAlarm", secActivityAlarm);

		//查询所有活动列表
		SecActivity secActivity = new SecActivity();
		List<SecActivity> secActivityList = secActivityService.findList(secActivity);
		model.addAttribute("secActivityList", secActivityList);
		
		//查询所有注册用户列表
		SecUser secUser = new SecUser();
		List<SecUser> secUserList = secUserService.findList(secUser);
		model.addAttribute("secUserList",secUserList);
		System.out.println(request.getHeader("cookie"));
		System.out.println(request.getHeader("reqId"));
		return "modules/social/secActivityAlarmForm";
	}

	@RequiresPermissions("social:secActivityAlarm:edit")
	@RequestMapping(value = "save")
	public String save(SecActivityAlarm secActivityAlarm, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, secActivityAlarm)){
			//return form(secActivityAlarm, model);
		}
		secActivityAlarmService.save(secActivityAlarm);
		addMessage(redirectAttributes, "保存活动提醒成功");
		return "redirect:"+Global.getAdminPath()+"/social/secActivityAlarm/?repage";
	}
	
	@RequiresPermissions("social:secActivityAlarm:edit")
	@RequestMapping(value = "delete")
	public String delete(SecActivityAlarm secActivityAlarm, RedirectAttributes redirectAttributes) {
		secActivityAlarmService.delete(secActivityAlarm);
		addMessage(redirectAttributes, "删除活动提醒成功");
		return "redirect:"+Global.getAdminPath()+"/social/secActivityAlarm/?repage";
	}

}