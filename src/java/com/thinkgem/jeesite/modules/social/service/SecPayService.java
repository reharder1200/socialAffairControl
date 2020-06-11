/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.social.entity.SecPay;
import com.thinkgem.jeesite.modules.social.dao.SecPayDao;

/**
 * 用户支付操作记录Service
 * @author hll
 * @version 2020-03-13
 */
@Service
@Transactional(readOnly = true)
public class SecPayService extends CrudService<SecPayDao, SecPay> {

	public SecPay get(String id) {
		return super.get(id);
	}
	
	public List<SecPay> findList(SecPay secPay) {
		return super.findList(secPay);
	}
	
	public Page<SecPay> findPage(Page<SecPay> page, SecPay secPay) {
		return super.findPage(page, secPay);
	}
	
	@Transactional(readOnly = false)
	public void save(SecPay secPay) {
		super.save(secPay);
	}
	
	@Transactional(readOnly = false)
	public void delete(SecPay secPay) {
		super.delete(secPay);
	}
	
}