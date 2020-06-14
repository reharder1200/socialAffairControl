/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.social.entity.SecActivity;
import com.thinkgem.jeesite.modules.social.entity.SecUser;
import com.thinkgem.jeesite.modules.social.service.SecActivityService;
import com.thinkgem.jeesite.modules.social.service.SecUserService;
import com.thinkgem.jeesite.modules.social.util.HttpUtils;

/**
 * 活动基本信息Controller
 * @author hll
 * @version 2020-03-13
 */
@Controller
@RequestMapping(value = "${adminPath}/social/secActivity")
public class SecActivityController extends BaseController {

	@Autowired
	private SecActivityService secActivityService;
	@Autowired
	private SecUserService secUserService;
	
	@ModelAttribute
	public SecActivity get(@RequestParam(required=false) String id) {
		SecActivity entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = secActivityService.get(id);
		}
		if (entity == null){
			entity = new SecActivity();
		}
		return entity;
	}
	
	@RequiresPermissions("social:secActivity:view")
	@RequestMapping(value = {"list", ""})
	public String list(SecActivity secActivity, HttpServletRequest request, HttpServletResponse response, Model model) {
		secActivity.setState("2");//仅显示审核通过的活动列表
		Page<SecActivity> page = secActivityService.findPage(new Page<SecActivity>(request, response), secActivity); 
		model.addAttribute("page", page);
		return "modules/social/secActivityList";
	}

	@RequiresPermissions("social:secActivity:view")
	@RequestMapping(value = "form")
	public String form(SecActivity secActivity, Model model) {
		model.addAttribute("secActivity", secActivity);
		
		//查询所有注册用户列表
		SecUser secUser = new SecUser();
		List<SecUser> secUserList = secUserService.findList(secUser);
		model.addAttribute("secUserList",secUserList);
		
		return "modules/social/secActivityForm";
	}

	@RequiresPermissions("social:secActivity:edit")
	@RequestMapping(value = "save")
	public String save(SecActivity secActivity, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, secActivity)){
			return form(secActivity, model);
		}
		secActivityService.save(secActivity);
		addMessage(redirectAttributes, "保存活动基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/social/secActivity/?repage";
	}
	
	@RequiresPermissions("social:secActivity:edit")
	@RequestMapping(value = "delete")
	public String delete(SecActivity secActivity, RedirectAttributes redirectAttributes) {
		secActivityService.delete(secActivity);
		addMessage(redirectAttributes, "删除活动基本信息成功");
		return "redirect:"+Global.getAdminPath()+"/social/secActivity/?repage";
	}
	
	@RequiresPermissions("social:secActivity:view")
	@RequestMapping(value = "mapShow")
	public String mapShow(SecActivity secActivity, Model model, RedirectAttributes redirectAttributes) {
		List<SecActivity> secActivities = secActivityService.findList(secActivity);
		
		//JSONArray secActivityList = JSONArray.fromObject(secActivities);
		
		Gson gson = new Gson();
		String secActivityList = gson.toJson(secActivities);
		
		/*使用Jackson将java对象转为json字符串*/
        /*ObjectMapper mapper=new ObjectMapper();	//1.创建objectmappter对象
        String secActivityList = null;	//2.调用mapper的writeValueAsString()方法把一个对象或集合转为json字符串
		try {
			secActivityList = mapper.writeValueAsString(secActivities);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		model.addAttribute("secActivities", secActivityList);
		return "modules/social/secActivityMapShow";
	}

	/*活动审核列表*/
	@RequiresPermissions("social:secActivity:approve")
	@RequestMapping(value = "approveList")
	public String approveList(SecActivity secActivity, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		if(StringUtils.isBlank(secActivity.getState())){
			secActivity.setState("0");
		}
		secActivity.setNoCancle("1");  //未取消的活动
		Page<SecActivity> pageParam = new Page<SecActivity>(request, response);
		pageParam.setOrderBy("state asc");
		Page<SecActivity> page = secActivityService.findPage(pageParam, secActivity); 
		model.addAttribute("page", page);
		return "modules/social/secActivityApproveList";
	}
	
	/*活动审核页面*/
	@RequiresPermissions("social:secActivity:approve")
	@RequestMapping(value = "approveForm")
	public String approveForm(SecActivity secActivity, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		model.addAttribute("secActivity", secActivity);
		
		//查询所有注册用户列表
		SecUser secUser = new SecUser();
		List<SecUser> secUserList = secUserService.findList(secUser);
		model.addAttribute("secUserList",secUserList);
		return "modules/social/secActivityApproveForm";
	}
	
	/*活动审核查看页面*/
	@RequiresPermissions("social:secActivity:approve")
	@RequestMapping(value = "approveShow")
	public String approveShow(SecActivity secActivity, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		model.addAttribute("secActivity", secActivity);
		
		//查询所有注册用户列表
		SecUser secUser = new SecUser();
		List<SecUser> secUserList = secUserService.findList(secUser);
		model.addAttribute("secUserList",secUserList);
		return "modules/social/secActivityApproveShow";
	}

	/*活动审核*/
	@RequiresPermissions("social:secActivity:approve")
	@RequestMapping(value = "approve")
	public String approve(SecActivity secActivity, HttpServletRequest request,
			HttpServletResponse response, Model model, RedirectAttributes redirectAttributes) {
		if(StringUtils.isBlank(secActivity.getState()) || secActivity.getState().equals(SecActivity.STATE_WAIT)){
			addMessage(redirectAttributes, "审核出现异常！");
		}else if(secActivity.getState().equals(SecActivity.STATE_FAIL)){
			addMessage(redirectAttributes, "审核结果：不通过！");
			secActivityService.save(secActivity);
			
			//消息提醒
			String param = "activityId=" + secActivity.getId()+"&content=\""+DateUtils.formatDateTime(new Date())+"\",\"" + secActivity.getTitle()+"活动审核失败，失败原因："+secActivity.getAdditOpinion()+"\"&userId="+secActivity.getActivityStarter().getId()+"&cmsId=630703&remarks=活动审核不通过";
			System.out.println("----"+param);
			String result = HttpUtils.doPost(Global.getConfig("MESSAGE_URL"),param);
			
		}else if(secActivity.getState().equals(SecActivity.STATE_SUCCESS)){
			addMessage(redirectAttributes, "审核结果：成功！");
			secActivityService.save(secActivity);
			
			//消息提醒
			String param = "activityId=" + secActivity.getId()+"&content=\""+DateUtils.formatDateTime(new Date())+"\",\"" + secActivity.getTitle()+"活动审核成功，请密切关注活动报名情况\"&userId="+secActivity.getActivityStarter().getId()+"&cmsId=630703&remarks=活动审核成功";
			System.out.println("----"+param);
			
			String result = HttpUtils.doPost(Global.getConfig("MESSAGE_URL"),param);
		}
		return "redirect:"+Global.getAdminPath()+"/social/secActivity/approveList?repage";
	}
	
}