package com.cesgroup.prison.dagl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.dagl.entity.Atth;
/**
 * 全文dao
 * 
 * @author xiexiaqin
 * @date 2016-06-22
 *
 */
public interface AtthMapper extends BaseDao<Atth, String> {

	/**
	 * 根据条目找全文
	 * @param ownerId
	 * @return
	 */
	public List<Atth> getAtth(@Param("ownerId") String ownerId);

	/**
	 * 获取对应条目的全文名称
	 * 
	 * @param ownerId
	 * @return
	 */
	@Select("select file_Name from T_FW_Atth where owner_Id=#{ownerId}")
	public List<String> getFileNames(String ownerId);

	/**
	 * 上传全文，保存全文信息
	 * 
	 * @param atth
	 */
	public void saveAtthByXml(Atth atth);

}
