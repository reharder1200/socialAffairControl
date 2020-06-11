/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 用户基本信息Entity
 * @author hll
 * @version 2020-03-13
 */
public class SecUser extends DataEntity<SecUser> {
	
	public static final String IS_REAL_NAME_NO = "0";
	public static final String IS_REAL_NAME_YES = "1";
	
	private static final long serialVersionUID = 1L;
	private String mobile;		// 手机号码
	private String nickname;		// 昵称
	private String wxSign;		// 个性签名
	private String wxOpenid;		// 微信id
	private String avatar;		// 头像路径
	private String userType;		// 用户类型(0普通用户 1授权用户)
	private String isRealName;		// 是否实名(0 否 1是)
	private String status;		// 用户状态(0正常 1删除 2停用 3冻结)
	private Date registerDate;		// 注册时间
	
	public SecUser() {
		super();
	}

	public SecUser(String id){
		super(id);
	}

	@Length(min=0, max=15, message="手机号码长度必须介于 0 和 15 之间")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Length(min=0, max=100, message="昵称长度必须介于 0 和 100 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Length(min=0, max=200, message="个性签名长度必须介于 0 和 200 之间")
	public String getWxSign() {
		return wxSign;
	}

	public void setWxSign(String wxSign) {
		this.wxSign = wxSign;
	}
	
	@Length(min=0, max=100, message="微信id长度必须介于 0 和 100 之间")
	public String getWxOpenid() {
		return wxOpenid;
	}

	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}
	
	@Length(min=0, max=1000, message="头像路径长度必须介于 0 和 1000 之间")
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	@Length(min=0, max=1, message="用户类型(0普通用户 1授权用户)长度必须介于 0 和 1 之间")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Length(min=0, max=1, message="是否实名(0 否 1是)长度必须介于 0 和 1 之间")
	public String getIsRealName() {
		return isRealName;
	}

	public void setIsRealName(String isRealName) {
		this.isRealName = isRealName;
	}
	
	@Length(min=0, max=1, message="用户状态(0正常 1删除 2停用 3冻结)长度必须介于 0 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
}