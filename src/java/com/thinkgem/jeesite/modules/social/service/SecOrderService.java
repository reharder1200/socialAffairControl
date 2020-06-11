/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.social.entity.SecOrder;
import com.thinkgem.jeesite.modules.social.dao.SecOrderDao;

/**
 * 订单Service
 * @author hll
 * @version 2020-03-13
 */
@Service
@Transactional(readOnly = true)
public class SecOrderService extends CrudService<SecOrderDao, SecOrder> {

	public SecOrder get(String id) {
		return super.get(id);
	}
	
	public List<SecOrder> findList(SecOrder secOrder) {
		return super.findList(secOrder);
	}
	
	public Page<SecOrder> findPage(Page<SecOrder> page, SecOrder secOrder) {
		return super.findPage(page, secOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(SecOrder secOrder) {
		super.save(secOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(SecOrder secOrder) {
		super.delete(secOrder);
	}
	
}