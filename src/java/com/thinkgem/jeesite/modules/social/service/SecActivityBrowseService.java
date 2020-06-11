/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.social.entity.SecActivityBrowse;
import com.thinkgem.jeesite.modules.social.dao.SecActivityBrowseDao;

/**
 * 活动浏览信息Service
 * @author hll
 * @version 2020-03-13
 */
@Service
@Transactional(readOnly = true)
public class SecActivityBrowseService extends CrudService<SecActivityBrowseDao, SecActivityBrowse> {

	public SecActivityBrowse get(String id) {
		return super.get(id);
	}
	
	public List<SecActivityBrowse> findList(SecActivityBrowse secActivityBrowse) {
		return super.findList(secActivityBrowse);
	}
	
	public Page<SecActivityBrowse> findPage(Page<SecActivityBrowse> page, SecActivityBrowse secActivityBrowse) {
		return super.findPage(page, secActivityBrowse);
	}
	
	@Transactional(readOnly = false)
	public void save(SecActivityBrowse secActivityBrowse) {
		super.save(secActivityBrowse);
	}
	
	@Transactional(readOnly = false)
	public void delete(SecActivityBrowse secActivityBrowse) {
		super.delete(secActivityBrowse);
	}
	
}