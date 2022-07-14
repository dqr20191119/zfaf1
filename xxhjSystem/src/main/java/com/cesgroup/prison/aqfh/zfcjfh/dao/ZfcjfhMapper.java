package com.cesgroup.prison.aqfh.zfcjfh.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.aqfh.zfcjfh.entity.ZfcjfhEntity;

/**
* @author lihong
* @date 创建时间：2020年6月4日 上午10:46:38
* 类说明:
*/
public interface ZfcjfhMapper  extends BaseDao<ZfcjfhEntity, String> {
	Page<ZfcjfhEntity> findList(ZfcjfhEntity zfcjfhEntity, PageRequest pageRequest);

	void updateById(ZfcjfhEntity zfcjfhEntity);
	
	
	List<ZfcjfhEntity> getTodaySfzfData(ZfcjfhEntity zfcjfhEntity);

	List<ZfcjfhEntity> selectByZfCj(ZfcjfhEntity zfcj);
}
