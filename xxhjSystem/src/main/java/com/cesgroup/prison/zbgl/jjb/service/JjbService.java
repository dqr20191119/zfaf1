package com.cesgroup.prison.zbgl.jjb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.zbgl.jjb.entity.JjbEntity;
import com.cesgroup.prison.zbgl.jjb.entity.JjbRzglEntity;

/**
* @author lihong
* @date 创建时间：2020年5月15日 上午9:57:53
* 类说明:
*/
public interface JjbService extends IBaseCRUDService<JjbEntity, String> {

	Page<JjbEntity> findList(JjbEntity jjbEntity, PageRequest pageRequest);

	void updateById(JjbEntity jjbEntity);

	AjaxResult inItDutyData();

	AjaxResult checkIsZbry(String id);

	Page<JjbRzglEntity> findJjbRzList(String id, PageRequest pageRequest);
	/**
	 * 交接班提交
	 * @param jjbEntity
	 */
	AjaxResult jjbTj(JjbEntity jjbEntity);

	AjaxResult checkIsZbz(JjbEntity jjbEntity);

}
