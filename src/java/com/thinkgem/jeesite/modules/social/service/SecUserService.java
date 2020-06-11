/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.social.entity.SecUser;
import com.thinkgem.jeesite.modules.social.dao.SecUserDao;

/**
 * 用户基本信息Service
 * @author hll
 * @version 2020-03-13
 */
@Service
@Transactional(readOnly = true)
public class SecUserService extends CrudService<SecUserDao, SecUser> {

	public SecUser get(String id) {
		return super.get(id);
	}
	
	public List<SecUser> findList(SecUser secUser) {
		return super.findList(secUser);
	}
	
	public Page<SecUser> findPage(Page<SecUser> page, SecUser secUser) {
		return super.findPage(page, secUser);
	}
	
	@Transactional(readOnly = false)
	public void save(SecUser secUser) {
		super.save(secUser);
	}
	
	@Transactional(readOnly = false)
	public void delete(SecUser secUser) {
		super.delete(secUser);
	}
	
}