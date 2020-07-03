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
	private String openid;		// 用户标识
	private String out_trade_no;		// 商户订单号
	private String appid;		// 微信分配的小程序ID
	private String body;		// 商品描述
	private String detail;		// 商品详情
	private String mch_id;		// 微信支付分配的商户号
	private String device_info;		// 微信支付分配的终端设备号，
	private String nonce_str;		// 随机字符串
	private String sign;		// 签名
	private String sign_type;		// 签名类型，目前支持HMAC-SHA256和MD5，默认为MD5
	private String is_subscribe;		// 用户是否关注公众账号，Y-关注，N-未关注
	private String trade_type;		// JSAPI、NATIVE、APP
	private String bank_type;		// 银行类型，采用字符串类型的银行标识
	private Integer total_fee;		// 订单总金额，单位为分
	private Integer settlement_total_fee;		// 应结订单金额=订单金额-非充值代金券金额，应结订单金额									&lt;/td&gt;									&lt;td nowrap&gt;										&lt;input type=
	private String fee_type;		// 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	private Integer cash_fee;		// 现金支付金额订单现金支付金额，
	private String cash_fee_type;		// 货币类型，符合ISO4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	private Integer coupon_fee;		// 代金券金额									&lt;/td&gt;									&lt;td nowrap&gt;										&lt;input type=
	private String coupon_count;		// 代金券使用数量
	private String transaction_id;		// 微信支付订单号
	private String attach;		// 商家数据包，原样返回
	private String time_end;		// 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	private String payStatus;	//支付状态
	
	public static final String PAY_STATUS_WAIT = "0";	//已创建待支付
	public static final String PAY_STATUS_CONFIRM = "1";	//已确认支付
	
	public SecPay() {
		super();
	}

	public SecPay(String id){
		super(id);
	}

	@Length(min=1, max=128, message="用户标识长度必须介于 1 和 128 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getDevice_info() {
		return device_info;
	}

	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getIs_subscribe() {
		return is_subscribe;
	}

	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getBank_type() {
		return bank_type;
	}

	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = Integer.parseInt(total_fee);
	}

	public Integer getSettlement_total_fee() {
		return settlement_total_fee;
	}

	public void setSettlement_total_fee(String settlement_total_fee) {
		this.settlement_total_fee = Integer.parseInt(settlement_total_fee);
	}

	public String getFee_type() {
		return fee_type;
	}

	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}

	public Integer getCash_fee() {
		return cash_fee;
	}

	public void setCash_fee(String cash_fee) {
		this.cash_fee = Integer.parseInt(cash_fee);
	}

	public String getCash_fee_type() {
		return cash_fee_type;
	}

	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}

	public Integer getCoupon_fee() {
		return coupon_fee;
	}

	public void setCoupon_fee(String coupon_fee) {
		this.coupon_fee = Integer.parseInt(coupon_fee);
	}

	public String getCoupon_count() {
		return coupon_count;
	}

	public void setCoupon_count(String coupon_count) {
		this.coupon_count = coupon_count;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getTime_end() {
		return time_end;
	}

	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
}