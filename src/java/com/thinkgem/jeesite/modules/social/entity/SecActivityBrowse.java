/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 活动浏览信息Entity
 * @author hll
 * @version 2020-03-13
 */
public class SecActivityBrowse extends DataEntity<SecActivityBrowse> {
	
	private static final long serialVersionUID = 1L;
	private String activityId;		// 活动id
	private User user;		// 浏览人id
	
	public SecActivityBrowse() {
		super();
	}

	public SecActivityBrowse(String id){
		super(id);
	}

	@Length(min=1, max=100, message="活动id长度必须介于 1 和 100 之间")
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	@NotNull(message="浏览人id不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}