package com.cesgroup.prison.zfxx.zfdm.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.zfxx.zfdm.entity.ZfXsdm;
import com.cesgroup.prison.zfxx.zfdm.entity.ZfZwdm;

/**
 * Description: 罪犯巡视点名业务访问接口
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
public interface ZfXsdmService extends IBaseCRUDService<ZfXsdm, String> {
	/**
	 * 分页查询罪犯巡视点名
	 * @param param
	 * @param pageable
	 * @return
	 */
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> param, Pageable pageable);
    
    /**
     * 查询监区最近的巡视点名记录
     * @return
     * @throws BusinessLayerException
     */
    public List<ZfXsdm> queryLatestXsdmRecordOfJianqu() throws BusinessLayerException;
}
