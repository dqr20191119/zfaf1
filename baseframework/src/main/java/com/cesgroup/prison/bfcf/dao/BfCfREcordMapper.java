package com.cesgroup.prison.bfcf.dao;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.bfcf.entity.BfCfREcordEntity;
import com.cesgroup.prison.broadcastPlay.entity.BroadcastPlay;
import com.cesgroup.prison.jfsb.entity.AlertorEntity;

public interface BfCfREcordMapper extends BaseDao<BfCfREcordEntity, String> {
	
	/**
	 *更新报警器的布防撤防状态  0.已撤防  1. 已布防
	 * @param alertorEntity
	 * @return
	 */
	public int updateAbdTypeAlertorById(AlertorEntity alertorEntity);
/**
 * 更新所有的防区的布防撤防状态
 * @param alertorEntity
 * @return
 */
	public int updateAbdTypeAlertor(AlertorEntity alertorEntity); 
	
	/**
	 * 根据分区Id编号  更新布防撤防状态
	 * @param alertorEntity
	 * @param fenquId 
	 * @return
	 */
	public int updateAbdTypeAlertorByFenquId(AlertorEntity alertorEntity);
	
	/**
	 * 通过msgId 更新状态
	 * @param bfCfREcordEntity
	 * @return
	 */
	public int  updateStatusByMsgId(BfCfREcordEntity bfCfREcordEntity);
	public AlertorEntity selectAbdTypeByFenquId(@Param("cusNumber")String cusNumber, @Param("fenquId")String fenquId);
	
	

}
