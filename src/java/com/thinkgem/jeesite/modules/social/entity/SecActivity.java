/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 活动基本信息Entity
 * @author hll
 * @version 2020-03-13
 */
public class SecActivity extends DataEntity<SecActivity> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String type;		// 活动类别(0桌游 1棋牌 2唱歌 3其他 )
	private Date beginDate;		// 开始时间
	private Date endDate;		// 结束时间
	private Date closingDate;	//报名截止时间
	private String minPeople;		// 最少人数
	private String maxPeople;		// 最大人数
	private String province;		// 省编码
	private String city;		// 市编码
	private String district;		// 区编码
	private String location;		// 位置
	private String locationDetatil;		// 位置详情
	private String activityLat;		// 经度
	private String activityLon;		// 纬度
	private String chargeType;		// 收费模式(0人均 1请客)
	private String chargeAmount;		// 收费金额
	private String activityDescription;		// 活动描述
	private String picSaveUrl;		// 图片存储位置
	private String contactWx;		// 活动联系人微信号
	private String contactMobile;		// 联系人手机
	private String activityStatus;		// 活动状态(0发起中 1已取消2进行中3纠纷处理 4已结束)
	private String depositStatus;		// 押金状态(0未交 1已交  2已退 3扣除已退)
	private String orderId;		// 押金订单号
	private SecUser activityStarter;		// 活动发起人
	
	private String state;
	private String additOpinion;
	
	public static String STATE_WAIT = "0";//待审核
	public static String STATE_SUCCESS = "2";//审核成功
	public static String STATE_FAIL = "1";//审核失败
	
	public SecActivity() {
		super();
	}

	public SecActivity(String id){
		super(id);
	}

	@Length(min=0, max=50, message="标题长度必须介于 0 和 50 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=1, message="活动类别(0桌游 1棋牌 2唱歌 3其他 )长度必须介于 0 和 1 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@Length(min=0, max=15, message="最少人数长度必须介于 0 和 15 之间")
	public String getMinPeople() {
		return minPeople;
	}

	public void setMinPeople(String minPeople) {
		this.minPeople = minPeople;
	}
	
	@Length(min=0, max=15, message="最大人数长度必须介于 0 和 15 之间")
	public String getMaxPeople() {
		return maxPeople;
	}

	public void setMaxPeople(String maxPeople) {
		this.maxPeople = maxPeople;
	}
	
	@Length(min=0, max=20, message="省编码长度必须介于 0 和 20 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=20, message="市编码长度必须介于 0 和 20 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Length(min=0, max=20, message="区编码长度必须介于 0 和 20 之间")
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
	
	@Length(min=0, max=100, message="位置详情长度必须介于 0 和 100 之间")
	public String getLocationDetatil() {
		return locationDetatil;
	}

	public void setLocationDetatil(String locationDetatil) {
		this.locationDetatil = locationDetatil;
	}
	
	@Length(min=0, max=200, message="经度长度必须介于 0 和 200 之间")
	public String getActivityLat() {
		return activityLat;
	}

	public void setActivityLat(String activityLat) {
		this.activityLat = activityLat;
	}
	
	@Length(min=0, max=200, message="纬度长度必须介于 0 和 200 之间")
	public String getActivityLon() {
		return activityLon;
	}

	public void setActivityLon(String activityLon) {
		this.activityLon = activityLon;
	}
	
	@Length(min=0, max=1, message="收费模式(0人均 1请客)长度必须介于 0 和 1 之间")
	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	
	@Length(min=0, max=20, message="收费金额长度必须介于 0 和 20 之间")
	public String getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(String chargeAmount) {
		this.chargeAmount = chargeAmount;
	}
	
	@Length(min=0, max=500, message="活动描述长度必须介于 0 和 500 之间")
	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}
	
	@Length(min=0, max=1000, message="图片存储位置长度必须介于 0 和 1000 之间")
	public String getPicSaveUrl() {
		return picSaveUrl;
	}

	public void setPicSaveUrl(String picSaveUrl) {
		this.picSaveUrl = picSaveUrl;
	}
	
	@Length(min=0, max=100, message="活动联系人微信号长度必须介于 0 和 100 之间")
	public String getContactWx() {
		return contactWx;
	}

	public void setContactWx(String contactWx) {
		this.contactWx = contactWx;
	}
	
	@Length(min=0, max=20, message="联系人手机长度必须介于 0 和 20 之间")
	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	
	@Length(min=0, max=1, message="活动状态(0发起中 1已取消2进行中3纠纷处理 4已结束)长度必须介于 0 和 1 之间")
	public String getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}
	
	@Length(min=0, max=1, message="押金状态(0未交 1已交  2已退 3扣除已退)长度必须介于 0 和 1 之间")
	public String getDepositStatus() {
		return depositStatus;
	}

	public void setDepositStatus(String depositStatus) {
		this.depositStatus = depositStatus;
	}
	
	@Length(min=0, max=100, message="押金订单号长度必须介于 0 和 100 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public SecUser getActivityStarter() {
		return activityStarter;
	}

	public void setActivityStarter(SecUser activityStarter) {
		this.activityStarter = activityStarter;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAdditOpinion() {
		return additOpinion;
	}

	public void setAdditOpinion(String additOpinion) {
		this.additOpinion = additOpinion;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}
	
}