/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 退款Entity
 * @author hll
 * @version 2020-03-13
 */
public class SecRefund extends DataEntity<SecRefund> {
	private static final long serialVersionUID = 1L;
	private String outRefundNo;
	private String orderId;
	private String activityId;
	private String openid;
	private String totalFee;
	private String refundReason;
	private String refundStatus;
	public static String REFUND_STATUS_WAIT = "0";
	public static String REFUND_STATUS_DONE = "1";
	public static String REFUND_STATUS_COMPLETE = "2";
	
	public SecRefund() {}
	
	public SecRefund(String id)
	{
	  super(id);
	}
	
	@Length(min=0, max=60, message="order_id长度必须介于 0 和 60 之间")
	public String getOrderId()
	{
	  return this.orderId;
	}
	
	public void setOrderId(String orderId)
	{
	  this.orderId = orderId;
	}
	
	@Length(min=0, max=500, message="refund_reason长度必须介于 0 和 500 之间")
	public String getRefundReason()
	{
	  return this.refundReason;
	}
	
	public void setRefundReason(String refundReason)
	{
	  this.refundReason = refundReason;
	}
	
	@Length(min=0, max=2, message="refund_status长度必须介于 0 和 2 之间")
	public String getRefundStatus()
	{
	  return this.refundStatus;
	}
	
	public void setRefundStatus(String refundStatus)
	{
	  this.refundStatus = refundStatus;
	}
	
	public String getOpenid()
	{
	  return this.openid;
	}
	
	public void setOpenid(String openid)
	{
	  this.openid = openid;
	}
	
	public String getTotalFee()
	{
	  return this.totalFee;
	}
	
	public void setTotalFee(String totalFee)
	{
	  this.totalFee = totalFee;
	}
	
	public String getActivityId()
	{
	  return this.activityId;
	}
	
	public void setActivityId(String activityId)
	{
	  this.activityId = activityId;
	}
	
	public String getOutRefundNo()
	{
	  return this.outRefundNo;
	}
	
	public void setOutRefundNo(String outRefundNo)
	{
	  this.outRefundNo = outRefundNo;
	}
		
}