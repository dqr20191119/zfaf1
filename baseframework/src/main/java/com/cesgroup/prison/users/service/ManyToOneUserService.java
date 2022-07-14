/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.cesgroup.demo.service.code</p>
 * <p>文件名:CodeKeyService.java</p>
 * <p>类更新历史信息</p>
 * @author xiexiaqin 
 * @date 2016-04-22 10:22
 * @todo 
 */
package com.cesgroup.prison.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.users.dao.ManyToOneUserMapper;
import com.cesgroup.prison.users.entity.ManyToOneUser;

/**
 * 
 * @author xiexiaqin
 * @date 2016-04-22
 *
 */
@Service
public class ManyToOneUserService extends BaseService<ManyToOneUser,Long> {

	@Autowired
	private ManyToOneUserMapper mapper;
	
	public Page<ManyToOneUser> findAll(String userName, String departmentName, Pageable pageable){
		return mapper.findByUserNameLikeAndDepartment$DepartmentNameLike(userName, departmentName, pageable);
	}


}
