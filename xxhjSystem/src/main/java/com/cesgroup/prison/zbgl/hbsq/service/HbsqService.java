package com.cesgroup.prison.zbgl.hbsq.service;

import com.cesgroup.framework.dto.AjaxResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zbgl.bcgl.entity.BcglEntity;
import com.cesgroup.prison.zbgl.hbsq.dto.ZbrxxDto;
import com.cesgroup.prison.zbgl.hbsq.entity.HbsqEntity;
/**
* @author lihong
* @date 创建时间：2020年5月15日 上午10:53:51
* 类说明:
*/

public interface HbsqService extends IBaseCRUDService<HbsqEntity, String>  {

	Page<HbsqEntity> findList(HbsqEntity hbsqEntity, PageRequest pageRequest);

	void updateById(HbsqEntity hbsqEntity);
	/**
	 * 进行换班业务
	 * @param id 为换班申请实体类的id
	 */
	void tyHbsq(String id);

	Integer checkZbrIsZbbp(ZbrxxDto zbrxxDto);

    /**
     * 检查当前用户当天排班是否是指挥长岗位
     * @param dutyDate
     * @return
     */
    AjaxResult checkIsZhz(String dutyDate);
}
