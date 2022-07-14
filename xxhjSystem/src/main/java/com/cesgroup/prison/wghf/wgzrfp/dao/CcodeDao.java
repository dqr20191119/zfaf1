package com.cesgroup.prison.wghf.wgzrfp.dao;

import java.util.List;


import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.wghf.wgzrfp.entiy.Ccode;



public interface CcodeDao extends BaseDao<Ccode, String>{

	List<Ccode> findByCodeKey();

}
