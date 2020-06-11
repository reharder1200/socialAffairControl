/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.social.entity.SecRefund;
import com.thinkgem.jeesite.modules.social.dao.SecRefundDao;

/**
 * 退款Service
 * @author hll
 * @version 2020-03-13
 */
@Service
@Transactional(readOnly = true)
public class SecRefundService extends CrudService<SecRefundDao, SecRefund> {

	public SecRefund get(String id) {
		return super.get(id);
	}
	
	public List<SecRefund> findList(SecRefund secRefund) {
		return super.findList(secRefund);
	}
	
	public Page<SecRefund> findPage(Page<SecRefund> page, SecRefund secRefund) {
		return super.findPage(page, secRefund);
	}
	
	@Transactional(readOnly = false)
	public void save(SecRefund secRefund) {
		super.save(secRefund);
	}
	
	@Transactional(readOnly = false)
	public void delete(SecRefund secRefund) {
		super.delete(secRefund);
	}
	
}