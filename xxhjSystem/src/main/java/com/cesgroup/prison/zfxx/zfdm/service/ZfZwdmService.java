package com.cesgroup.prison.zfxx.zfdm.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.zfxx.zfdm.entity.ZfZwdm;

/**
 * Description: 罪犯早晚点名业务访问接口
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
public interface ZfZwdmService extends IBaseCRUDService<ZfZwdm, String> {
	/**
	 * 分页查询罪犯早晚点名
	 * @param param
	 * @param pageable
	 * @return
	 */
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> param, Pageable pageable);
    
    /**
     * 查询监区最近的早晚点名记录
     * @return
     * @throws BusinessLayerException
     */
    public List<ZfZwdm> queryLatestZwdmRecordOfJianqu() throws BusinessLayerException;
}
