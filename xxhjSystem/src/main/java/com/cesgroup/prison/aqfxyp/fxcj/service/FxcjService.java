package com.cesgroup.prison.aqfxyp.fxcj.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.dto.TreeDto;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.aqfxyp.fxcj.entity.Fxcj;
import com.cesgroup.prison.wwjg.sjfwgl.entity.SjfwglEntity;
import com.cesgroup.prison.wwjg.wwjgwh.entity.WwjgwhEntity;

/**
 * Description: 风险采集业务操作类接口
 * @author lincoln.cheng
 *
 * 2019年1月15日
 */
public interface FxcjService extends IBaseCRUDService<Fxcj, String> {
	/**
	 * Description: 初始化风险要素树形结构数据
	 * @return
	 * @throws BusinessLayerException
	 */
	public List<TreeDto> initFxysTree() throws BusinessLayerException;

	/**
	 * Description: 分页查询风险采集信息
	 * @param fxcj
	 * @param pageRequest
	 * @return
	 */
	public Page<Fxcj> findList(Fxcj fxcj, PageRequest pageRequest);

	/**
	 * Description: 新增或修改风险采集信息
	 * @param fxcj
	 * @throws Exception
	 */
	public String saveOrUpdate(Fxcj fxcj) throws BusinessLayerException;
	
	/**
	 * Description: 删除风险采集数据
	 * @param ids
	 */
	public void deleteByIds(String ids) throws BusinessLayerException;
	
	
	public WwjgwhEntity getwwjg(String id);
	
	public SjfwglEntity getsjfw(String id);
	
	
	public List getWg(String leve,String parent);
	
	public Fxcj getById(String id);
	
	public void insterDb(Fxcj fxcj);

	public Page<Fxcj> findList(String jQName, String wwName, String bz, String date, PageRequest pageRequest);
}
