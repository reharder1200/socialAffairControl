/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.modules.sys.entity.User;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 评价信息Entity
 * @author hll
 * @version 2020-03-13
 */
public class SecEvaluate extends DataEntity<SecEvaluate> {
	
	private static final long serialVersionUID = 1L;
	private String activityId;		// 活动id
	private SecUser user;		// 评论人id
	private String content;		// 评论内容
	private String activityScore;		// 评论活动分数
	private String organizerScore;		// 评论发起人分数
	private String isShow;		// 是否显示(0是 1否)
	private String showOrder;		// 评论显示顺序
	
	//关联活动
	private String title;		// 活动标题
	
	public SecEvaluate() {
		super();
	}

	public SecEvaluate(String id){
		super(id);
	}

	@Length(min=1, max=100, message="活动id长度必须介于 1 和 100 之间")
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	@NotNull(message="评论人id不能为空")
	public SecUser getUser() {
		return user;
	}

	public void setUser(SecUser user) {
		this.user = user;
	}
	
	@Length(min=0, max=500, message="评论内容长度必须介于 0 和 500 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=10, message="评论活动分数长度必须介于 0 和 10 之间")
	public String getActivityScore() {
		return activityScore;
	}

	public void setActivityScore(String activityScore) {
		this.activityScore = activityScore;
	}
	
	@Length(min=0, max=10, message="评论发起人分数长度必须介于 0 和 10 之间")
	public String getOrganizerScore() {
		return organizerScore;
	}

	public void setOrganizerScore(String organizerScore) {
		this.organizerScore = organizerScore;
	}
	
	@Length(min=0, max=1, message="是否显示(0是 1否)长度必须介于 0 和 1 之间")
	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
	@Length(min=0, max=10, message="评论显示顺序长度必须介于 0 和 10 之间")
	public String getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(String showOrder) {
		this.showOrder = showOrder;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}