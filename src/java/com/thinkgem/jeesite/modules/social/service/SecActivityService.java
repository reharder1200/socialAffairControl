/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.social.entity.SecActivity;
import com.thinkgem.jeesite.modules.social.dao.SecActivityDao;

/**
 * 活动基本信息Service
 * @author hll
 * @version 2020-03-13
 */
@Service
@Transactional(readOnly = true)
public class SecActivityService extends CrudService<SecActivityDao, SecActivity> {

	public SecActivity get(String id) {
		return super.get(id);
	}
	
	public List<SecActivity> findList(SecActivity secActivity) {
		return super.findList(secActivity);
	}
	
	public Page<SecActivity> findPage(Page<SecActivity> page, SecActivity secActivity) {
		return super.findPage(page, secActivity);
	}
	
	@Transactional(readOnly = false)
	public void save(SecActivity secActivity) {
		super.save(secActivity);
	}
	
	@Transactional(readOnly = false)
	public void delete(SecActivity secActivity) {
		super.delete(secActivity);
	}
	
}