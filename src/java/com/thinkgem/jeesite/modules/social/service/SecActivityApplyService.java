/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.social.entity.SecActivityApply;
import com.thinkgem.jeesite.modules.social.dao.SecActivityApplyDao;

/**
 * 活动报名信息Service
 * @author hll
 * @version 2020-03-13
 */
@Service
@Transactional(readOnly = true)
public class SecActivityApplyService extends CrudService<SecActivityApplyDao, SecActivityApply> {

	public SecActivityApply get(String id) {
		return super.get(id);
	}
	
	public List<SecActivityApply> findList(SecActivityApply secActivityApply) {
		return super.findList(secActivityApply);
	}
	
	public Page<SecActivityApply> findPage(Page<SecActivityApply> page, SecActivityApply secActivityApply) {
		return super.findPage(page, secActivityApply);
	}
	
	@Transactional(readOnly = false)
	public void save(SecActivityApply secActivityApply) {
		super.save(secActivityApply);
	}
	
	@Transactional(readOnly = false)
	public void delete(SecActivityApply secActivityApply) {
		super.delete(secActivityApply);
	}
	
}