package com.cesgroup.prison.zbgl.jjb.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.jjb.entity.JjbRzglEntity;

/**
* @author lihong
* @date 创建时间：2020年5月15日 上午10:08:45
* 类说明:
*/
public interface JjbRzglMapper extends BaseDao<JjbRzglEntity, String> {
	Page<JjbRzglEntity> findList(JjbRzglEntity jjbrz, PageRequest pageRequest);

}
