package com.cesgroup.prison.foreign.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.foreign.entity.ForeignRegister;
import com.cesgroup.prison.foreign.vo.ForeignRegisterVo;

public interface ForeignRegisterMapper extends BaseDao<ForeignRegister, String> {

    void deleteByIds(List<String> ids);

    void insertInfo(ForeignRegister record);

    Page<Map<String, Object>> listAll(ForeignRegisterVo foreignRegisterVo, Pageable pageable);

    int updateInfo(ForeignRegister record);

    ForeignRegister findById(Map<String, Object> map);

    String findPoliceNameById(String policeId);

    void checkInfo(ForeignRegister foreignRegister);
    void outInfo(ForeignRegister foreignRegister);
    Map<String,Object> findLastRow(Map<String, Object> map);
    
    /**
	 * Description: 查询外来车辆历史数据，避免插入重复数据
	 * @param 监狱编号
	 * @param 车牌号
	 * @param 车辆进出时间
	 */
    List<ForeignRegister> findHisRecord(@Param("cusNumber") String cusNumber,@Param("cph") String cph,@Param("cljcsj") String cljcsj);
}