package com.cesgroup.prison.foreign.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.foreign.entity.ForeignPeopleDtls;

public interface ForeignPeopleDtlsMapper extends BaseDao<ForeignPeopleDtls, String> {

    /**
     * 添加进出人员信息
     * @param record
     */
    void insertPeopleInfo(ForeignPeopleDtls record);

    /**
     * 根据进出基础信息id删除人员信息
     * @param id
     * @return
     */
    int deleteByFpdRegisterId(String id);

    /**
     * 人员信息Grid分页
     * @param map
     * @param pageable
     * @return
     */
    Page<Map<String, Object>> listPeople(Map<String, Object> map, Pageable pageable);


    void deleteByIds(List<String> ids);

    Map<String, Object> findPeopleByCardCode(Map<String, Object> param);

    Page<Map<String, Object>> getPeopleList(Map<String, Object> param, Pageable pageable);

    // 原来的
    int deleteByPrimaryKey(Map<String, Object> map);

    ForeignPeopleDtls selectByPrimaryKey(Map<String, Object> map);

    int updateByPrimaryKeySelective(Map<String, Object> map);
   
    Page<Map<String, Object>> pageFind(Map<String, Object> map,Pageable pageable);
    
    List<Map<String, Object>> selectBaseInfoByCardId(Map<String, Object> map);
}