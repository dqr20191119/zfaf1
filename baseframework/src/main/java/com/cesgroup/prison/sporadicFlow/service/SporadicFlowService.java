package com.cesgroup.prison.sporadicFlow.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.sporadicFlow.entity.SporadicFlowPeopleRegisterEntity;
import net.sf.json.JSONArray;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.sporadicFlow.entity.SporadicFlowRegisterEntity;

import javax.servlet.http.HttpServletRequest;

/**      
* @projectName：prison   
* @ClassName：SporadicFlowRegisterService   
* @Description：   零星活动登记
* @author：Tao.xu   
* @date：2017年12月7日 下午7:27:54
* @version        
*/
public interface SporadicFlowService extends IBaseCRUDService<SporadicFlowRegisterEntity, String> {

	/**
	* @methodName: listAll
	* @Description: 分页查询所有
	* @param entity
	* @param pageable
	* @return Page<Map<String,Object>>    
	* @throws
	*/
	public Page<Map<String, Object>> listAll(SporadicFlowRegisterEntity entity, Pageable pageable,UserBean user);

	/**
	* @methodName: addRegisterInfo
	* @Description: 添加登记信息
	* @param entity
	* @throws
	*/
	public void addRegisterInfo(SporadicFlowRegisterEntity entity, JSONArray gridDataPolice,JSONArray gridDataOffender,UserBean user,HttpServletRequest request) throws Exception;

	/**
	 * @methodName: addPeople
	 * @Description: 添加民警信息
	 * @param List<Map<String, Object>>
	 * @throws
	 */
	public void addPolice(JSONArray Peoples,String sprRegisterId,UserBean user);

	/**
	 * 添加罪犯信息
	 * @param Peoples
	 * @param sprRegisterId
	 * @param user
     */
	public void addOffender(JSONArray Peoples,String sprRegisterId,UserBean user);

	/**
	* @methodName: updateRegisterInfo
	* @Description: 更新登记信息
	* @param entity void    
	* @throws
	*/
	public void updateRegisterInfoAll(SporadicFlowRegisterEntity entity, JSONArray gridDataPolice,JSONArray gridDataOffender,UserBean user,HttpServletRequest request) throws Exception;

	public void updateRegisterInfo(SporadicFlowRegisterEntity entity,UserBean user);
	/**
	* @methodName: deleteById
	* @Description: 根据id删记录
	* @param list void    
	* @throws
	*/
	public void deleteByIds(List<String> list);

	/**
	 * @methodName: deletePeopleByIds
	 * @Description: 根据id删罪犯人员
	 * @param list void
	 * @throws
	 */
	public void deletePeopleByIds(List<String> list);

	/**
	 * @return 
	* @methodName: findById
	* @Description: 通过id查纪录
	* @throws
	*/
	public Map<String, Object> findById(Map<String, Object> paramMap);

	/**
	 * @methodName: listAllSporadicFlowPeople
	 * @Description: 查询所有罪犯人员列表
	 * @throws
	 */

	public List<Map<String, Object>> findDeptPoliceForGridByItem(Map<String, Object> paramMap);

	public List<Map<String, Object>> findDeptOffenderForGridByItem(Map<String, Object> paramMap);


}
