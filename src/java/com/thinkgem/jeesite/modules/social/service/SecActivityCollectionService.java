/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.social.entity.SecActivityCollection;
import com.thinkgem.jeesite.modules.social.dao.SecActivityCollectionDao;

/**
 * 活动收藏信息Service
 * @author hll
 * @version 2020-03-13
 */
@Service
@Transactional(readOnly = true)
public class SecActivityCollectionService extends CrudService<SecActivityCollectionDao, SecActivityCollection> {

	public SecActivityCollection get(String id) {
		return super.get(id);
	}
	
	public List<SecActivityCollection> findList(SecActivityCollection secActivityCollection) {
		return super.findList(secActivityCollection);
	}
	
	public Page<SecActivityCollection> findPage(Page<SecActivityCollection> page, SecActivityCollection secActivityCollection) {
		return super.findPage(page, secActivityCollection);
	}
	
	@Transactional(readOnly = false)
	public void save(SecActivityCollection secActivityCollection) {
		super.save(secActivityCollection);
	}
	
	@Transactional(readOnly = false)
	public void delete(SecActivityCollection secActivityCollection) {
		super.delete(secActivityCollection);
	}
	
}