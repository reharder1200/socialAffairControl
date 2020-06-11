/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.sys.entity.User;

import javax.validation.constraints.NotNull;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 活动提醒Entity
 * @author hll
 * @version 2020-03-13
 */
public class SecActivityAlarm extends DataEntity<SecActivityAlarm> {
	
	private static final long serialVersionUID = 1L;
	private String activityId;		// 活动id
	private SecUser user;		// 提醒人id
	private Date alarmDate;		// 提醒时间
	
	//关联活动
	private String title;		// 活动标题
	
	public SecActivityAlarm() {
		super();
	}

	public SecActivityAlarm(String id){
		super(id);
	}

	@Length(min=1, max=100, message="活动id长度必须介于 1 和 100 之间")
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	@NotNull(message="提醒人id不能为空")
	public SecUser getUser() {
		return user;
	}

	public void setUser(SecUser user) {
		this.user = user;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAlarmDate() {
		return alarmDate;
	}

	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}