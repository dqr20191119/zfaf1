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
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.cesgroup.authen4.core.service.CoreBiz;
//import com.cesgroup.authen4.user.entity.User;
import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.users.dao.UsersMapper;
import com.cesgroup.prison.users.entity.UserDept;
import com.cesgroup.prison.users.entity.Users;

/**
 * 
 * @author xiexiaqin
 * @date 2016-04-22
 *
 */
@Service
public class UsersService extends BaseService<Users,Long> {

	@Autowired
	private UsersMapper mapper;
	//@Autowired
	//private CoreBiz coreBiz;

	/**
	 * 查询用户信息及所在部门 by sql 函数方式递归
	 * 
	 * @param user
	 * @param pageRequest 
	 * @return
	 */
	public Page<Map<String, String>> listAll(Users user,String ageBeg,String ageEnd,String departmentIds, PageRequest pageRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		if (departmentIds != null&&!"".equals(departmentIds)) {
			String[] ids = departmentIds.split(",");
			map.put("departments", ids);
		}
		if(!"".equals(ageBeg)){
			map.put("ageBeg", ageBeg);
		}
		if(!"".equals(ageEnd)){
			map.put("ageEnd", ageEnd);
		}
		return mapper.listAll(map,pageRequest);
	}
	@Transactional
	public void insertUser(Users user){
		try {
			this.getDao().insert(user);
		/*	User u=new User();
			u.setName(user.getUserName());
			coreBiz.createUser(u, "", "", "");*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
