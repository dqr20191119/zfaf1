package com.cesgroup.prison.zbgl.rygl.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.prison.zbgl.rygl.dto.DutyQueryDto;
import com.cesgroup.prison.zbgl.rygl.dto.LastDutyRyDto;
import com.cesgroup.prison.zbgl.rygl.entity.DutyFlagEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.rygl.dto.JyzzxxDto;
import com.cesgroup.prison.zbgl.rygl.entity.RyglEntity;

public interface RyglMapper extends BaseDao<RyglEntity, String> {

     List<RyglEntity> remindMessageByRyzy(RyglEntity ryglEntity);


    RyglEntity getById(String id);

    Page<RyglEntity> findList(RyglEntity ryglEntity, Pageable pageable);


    Integer deleteByIds(List<String> ids);

    List<JyzzxxDto> listSjzzxx();

	List<RyglEntity> selecListtByIds(List<String> ids);
    
	List<RyglEntity> selectListByJob(RyglEntity ryglEntity);
	/**
	 * 值班人员的排班次数增加或改变
	 * @param id 
	 * @param type  1为加排班次数  2为减排班次数
	 */
      void  updatePbCount(@Param("id") String id, @Param("type") String type);

    List<Map<String, Object>> getUserInfoByCusNumber(@Param("cusNumber") String cusNumber);

    /**
     * 查询指挥长数据按指挥长值中班顺序排序
     * @param ryglEntity
     * @return
     */
    List<RyglEntity>  selectListByJobOrderzhzMidOrder(RyglEntity ryglEntity);

    void updateById(RyglEntity ryglEntity);

    RyglEntity getMaxPbOrder();

    /**
     * 获取该岗位类别的最大序列号和最小的序列号
     * @param job
     * @return
     */
    Map<String,Object> getMaxAndMinPbOrderByJob(@Param("cusNumber") String cusNumber,@Param("job")String job);

    RyglEntity getNextPbOrder(@Param("cusNumber") String cusNumber,@Param("job")String job,@Param("pbOrder")String pbOrder);

    /**
     *
     * 查询岗位类别在值班日期状态 培训和病假的 值班人员
     * @param job
     * @param dutyDate
     * @return
     */
    RyglEntity getByJobAndDutyDate(@Param("cusNumber") String cusNumber,@Param("job")String job,@Param("dutyDate")String dutyDate);

    /**
     * 更新没有岗位类别排班的位置  即排到了哪个人
     * @param dutyFlagEntity
     */
     void updateDutyFlag(DutyFlagEntity dutyFlagEntity);

    DutyFlagEntity getDutyFlag(@Param("cusNumber") String cusNumber);


    /**
     *
     * 根据岗位查询 人员 状态 1 .4 5的人
     * @param rygl
     * @return
     */
    List<RyglEntity> selectListByJobAndRyzt(RyglEntity rygl);


    /**
     * 查询排班的信息
     * @param dutyQueryDto
     * @return
     */
    List<LastDutyRyDto > selectLastDutyRyDtoByDutyQueryDto(DutyQueryDto dutyQueryDto);

    /**
     * 查询排班次数
     * @param map   cusNumber,orderName,type 不为空 传入List集合 key 为list
     * @return  DUTY_COUNT
     */
   Integer dutyCount(Map<String,Object> map);

    /**
     * 根据id 更新标记表
     * @param dutyFlagEntity
     */
    void updateDutyFlagById(DutyFlagEntity dutyFlagEntity);

    /**
     * 查询某个人在节假日或工作日的值班班次
     * @param cusNumber
     * @param id
     * @param flag 0 工作日  1 节假日
     * @return
     */
    List<DutyQueryDto> selectOrderNameByIdAndFlag(@Param("cusNumber")String cusNumber,@Param("id")String id,@Param("flag")String flag);


    List<RyglEntity> selectByNodutyByStartDate(@Param("cusNumber")String cusNumber,@Param("startDate")String startDate);

    /**
     * 查询岗位最大的值班顺序pbOrder
     * @param cusNumber
     * @param job
     * @return
     */
    RyglEntity getMaxPbOrderByJob(@Param("cusNumber")String cusNumber,@Param("job")String job);

    /**
     * cusNumber
     * pbOrder 将大于pbOrder的人员顺序后移一位
     * @param    rygl
     */
    void  updateBypbOrder(RyglEntity rygl);

}
