/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户实名信息Entity
 * @author hll
 * @version 2020-03-13
 */
public class SecUserDetail extends DataEntity<SecUserDetail> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户id
	private String name;		// 真实姓名
	private String sex;		// 性别(0男 1女)
	private String age;		// 年龄
	private String phone;		// 办公电话
	private String email;		// 邮箱
	private String certificateType;		// 证件类型(0身份证 1护照 3港澳通行证)
	private String certificateNum;		// 证件号码
	private String address;		// 常住地址
	private String reserverName;		// 备用联系人姓名
	private String reserverMobile;		// 备用联系人手机
	
	public SecUserDetail() {
		super();
	}

	public SecUserDetail(String id){
		super(id);
	}

	@NotNull(message="用户id不能为空")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=100, message="真实姓名长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="性别(0男 1女)长度必须介于 0 和 1 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=5, message="年龄长度必须介于 0 和 5 之间")
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	@Length(min=0, max=15, message="办公电话长度必须介于 0 和 15 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=100, message="邮箱长度必须介于 0 和 100 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=1, message="证件类型(0身份证 1护照 3港澳通行证)长度必须介于 0 和 1 之间")
	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}
	
	@Length(min=0, max=100, message="证件号码长度必须介于 0 和 100 之间")
	public String getCertificateNum() {
		return certificateNum;
	}

	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}
	
	@Length(min=0, max=300, message="常住地址长度必须介于 0 和 300 之间")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Length(min=0, max=100, message="备用联系人姓名长度必须介于 0 和 100 之间")
	public String getReserverName() {
		return reserverName;
	}

	public void setReserverName(String reserverName) {
		this.reserverName = reserverName;
	}
	
	@Length(min=0, max=15, message="备用联系人手机长度必须介于 0 和 15 之间")
	public String getReserverMobile() {
		return reserverMobile;
	}

	public void setReserverMobile(String reserverMobile) {
		this.reserverMobile = reserverMobile;
	}
	
}