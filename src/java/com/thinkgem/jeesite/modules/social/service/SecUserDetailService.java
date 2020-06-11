/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.social.entity.SecUser;
import com.thinkgem.jeesite.modules.social.entity.SecUserDetail;
import com.thinkgem.jeesite.modules.social.dao.SecUserDetailDao;

/**
 * 用户实名信息Service
 * @author hll
 * @version 2020-03-13
 */
@Service
@Transactional(readOnly = true)
public class SecUserDetailService extends CrudService<SecUserDetailDao, SecUserDetail> {

	@Autowired
	private SecUserService secUserService;
	
	public SecUserDetail get(String id) {
		return super.get(id);
	}
	
	public List<SecUserDetail> findList(SecUserDetail secUserDetail) {
		return super.findList(secUserDetail);
	}
	
	public Page<SecUserDetail> findPage(Page<SecUserDetail> page, SecUserDetail secUserDetail) {
		return super.findPage(page, secUserDetail);
	}
	
	@Transactional(readOnly = false)
	public void save(SecUserDetail secUserDetail) {
		super.save(secUserDetail);
		
		//更新用户基本信息表实名字段
		SecUser secUser = secUserService.get(secUserDetail.getUserId());
		if(null!=secUser){
			secUser.setIsRealName(SecUser.IS_REAL_NAME_YES);
			secUserService.save(secUser);
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(SecUserDetail secUserDetail) {
		super.delete(secUserDetail);
	}
	
}