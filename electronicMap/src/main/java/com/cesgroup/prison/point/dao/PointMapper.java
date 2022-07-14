package com.cesgroup.prison.point.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.point.entity.Point;
import com.cesgroup.prison.point.vo.PointVo;
import com.cesgroup.prison.region.entity.Region;
import com.cesgroup.prison.view.entity.View;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

/**
 * 点位管理mapper
 * @author mxz 2017-12-20
 *
 */
public interface PointMapper extends BaseDao<Point,String> {


    int getCountById(PointVo pointVo);

    void insertAll(PointVo pointVo);

    void updateAll(PointVo pointVo);

    Page<PointVo> findByPage(PageRequest pageRequest, PointVo pointVo);

//    @Delete("delete from CDS_ARCGIS_LAYER_POINTS where id=#{id}")
//    void destoryPoint(String id);
    int destoryPoint(String id);

    List<PointVo> getAllCamera(PointVo pointVo);

    List<PointVo> getDevicePointById(PointVo pointVo);

    Page<PointVo> getAllTypeDevice(PageRequest pageRequest, PointVo pointVo);

    List<PointVo> getAllAlarm(PointVo pointVo);

    List<PointVo> getAllDoor(PointVo pointVo);

    List<PointVo> getAllBroadcast(PointVo pointVo);

    List<PointVo> getAllDoorControll(PointVo pointVo);

    List<PointVo> getAllDeviceOther(PointVo pointVo);

    List<PointVo> getAllDeviceMain(PointVo pointVo);

    List<View> getViewByGrandId(PointVo pointVo);

    @Select("select ABD_AREA_ID as abdAreaId from CDS_AREA_BASE_DTLS where ABD_CUS_NUMBER=#{cusNumber} and (ABD_PARENT_AREA_ID like concat(#{grandId},'%') or ABD_AREA_ID=#{grandId})")
    List<String> getAllGrandByThis(@Param("cusNumber") String cusNumber,@Param("grandId") String grandId);

    List<PointVo> getAllPointByGrandAndType(PointVo pointVo);

    List<PointVo> getAllTabs(PointVo pointVo);

/*    @Select("select abd_area_id, abd_area_name from cds_area_base_dtls where abd_area_name like '%监舍' and abd_cus_number = #{alpCusNumber}")
    Map<String, Object> getAllDormByCusnumber(String alpCusNumber);*/

    Page<PointVo> findByDormPage(PageRequest pageRequest, PointVo pointVo);
}
