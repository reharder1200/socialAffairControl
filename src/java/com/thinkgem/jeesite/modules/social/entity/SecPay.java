/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户支付操作记录Entity
 * @author hll
 * @version 2020-03-13
 */
public class SecPay extends DataEntity<SecPay> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 浏览人id
	private String orderId;		// 订单号
	
	public SecPay() {
		super();
	}

	public SecPay(String id){
		super(id);
	}

	@NotNull(message="浏览人id不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Length(min=1, max=20, message="订单号长度必须介于 1 和 20 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}