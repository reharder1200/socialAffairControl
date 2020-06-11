/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.social.entity.SecEvaluate;
import com.thinkgem.jeesite.modules.social.dao.SecEvaluateDao;

/**
 * 评价信息Service
 * @author hll
 * @version 2020-03-13
 */
@Service
@Transactional(readOnly = true)
public class SecEvaluateService extends CrudService<SecEvaluateDao, SecEvaluate> {

	public SecEvaluate get(String id) {
		return super.get(id);
	}
	
	public List<SecEvaluate> findList(SecEvaluate secEvaluate) {
		return super.findList(secEvaluate);
	}
	
	public Page<SecEvaluate> findPage(Page<SecEvaluate> page, SecEvaluate secEvaluate) {
		return super.findPage(page, secEvaluate);
	}
	
	@Transactional(readOnly = false)
	public void save(SecEvaluate secEvaluate) {
		super.save(secEvaluate);
	}
	
	@Transactional(readOnly = false)
	public void delete(SecEvaluate secEvaluate) {
		super.delete(secEvaluate);
	}
	
}