package com.cesgroup.prison.foreign.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.cesgroup.prison.foreign.entity.ForeignPeopleDtls;

public interface ForeignPeopleDtlsService extends IBaseCRUDService<ForeignPeopleDtls, String> {

    int deleteByPrimaryKey(Map<String, Object> map);

    void insertSelectiveB(ForeignPeopleDtls record, String foreignPeopelPicIds);

    ForeignPeopleDtls selectByPrimaryKey(Map<String, Object> map);

    int updateByPrimaryKeySelective(ForeignPeopleDtls record,String foreignPeopelPicIds);
   
    Page<Map<String, Object>> pageFind(Map<String, Object> map,Pageable pageable);
    
    Map<String, Object> selectBaseInfoByCardId(Map<String, Object> map);
    List<AffixEntity> findPic(String id);
}
