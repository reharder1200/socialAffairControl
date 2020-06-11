/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.social.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.social.entity.SecUserDetail;

/**
 * 用户实名信息DAO接口
 * @author hll
 * @version 2020-03-13
 */
@MyBatisDao
public interface SecUserDetailDao extends CrudDao<SecUserDetail> {
	
}