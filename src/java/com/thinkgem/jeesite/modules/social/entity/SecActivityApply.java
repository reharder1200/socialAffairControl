/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.sys.entity.User;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 活动报名信息Entity
 * @author hll
 * @version 2020-03-13
 */
public class SecActivityApply extends DataEntity<SecActivityApply> {
	
	private static final long serialVersionUID = 1L;
	private String activityId;		// 活动id
	private SecUser user;		// 报名人id
	private Date signDate;		// 报名时间
	private String signType;		// 报名状态(0已报名 1已取消)
	private String isEvaluate;		// 是否评价(0否 1是)
	private String orderId;			//报名订单号
	
	//关联活动
	private String title;		// 活动标题
	
	public SecActivityApply() {
		super();
	}

	public SecActivityApply(String id){
		super(id);
	}

	@Length(min=0, max=100, message="活动id长度必须介于 0 和 100 之间")
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	public SecUser getUser() {
		return user;
	}

	public void setUser(SecUser user) {
		this.user = user;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	
	@Length(min=0, max=1, message="报名状态(0已报名 1已取消)长度必须介于 0 和 1 之间")
	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}
	
	@Length(min=0, max=1, message="是否评价(0否 1是)长度必须介于 0 和 1 之间")
	public String getIsEvaluate() {
		return isEvaluate;
	}

	public void setIsEvaluate(String isEvaluate) {
		this.isEvaluate = isEvaluate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}