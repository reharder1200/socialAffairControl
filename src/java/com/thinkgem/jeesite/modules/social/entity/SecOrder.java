/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单Entity
 * @author hll
 * @version 2020-03-13
 */
public class SecOrder extends DataEntity<SecOrder> {
	
	private static final long serialVersionUID = 1L;
	private String orderId;		// 订单号
	private String orderType;		// 订单类型(0押金 1报名)
	private String payType;		// 支付方式(0微信 1支付宝 3银行卡 4其他)
	private String payStatus;		// 支付状态(0待支付 1已支付 2已退款)
	private String totalAmount;		// 总金额
	
	public SecOrder() {
		super();
	}

	public SecOrder(String id){
		super(id);
	}

	@Length(min=1, max=100, message="订单号长度必须介于 1 和 100 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@Length(min=0, max=1, message="订单类型(0押金 1报名)长度必须介于 0 和 1 之间")
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	@Length(min=0, max=1, message="支付方式(0微信 1支付宝 3银行卡 4其他)长度必须介于 0 和 1 之间")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}
	
	@Length(min=0, max=1, message="支付状态(0待支付 1已支付 2已退款)长度必须介于 0 和 1 之间")
	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}