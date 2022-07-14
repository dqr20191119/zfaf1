package com.cesgroup.prison.foreign.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.foreign.entity.ForeignRegister;
import com.cesgroup.prison.foreign.vo.ForeignRegisterVo;

import net.sf.json.JSONArray;

/**
 * @projectName：prison
 * @ClassName：ForeignService
 * @Description：   外来人车登记
 * @author：Tao.xu
 * @date：2018年6月12日
 * @version
 */
public interface ForeignRegisterService extends IBaseCRUDService<ForeignRegister, String> {


    /**
     * 登记外来人车基础信息
     * @param entity
     * @param gridDataCar
     * @param gridDataPeople
     * @param user
     * @param request
     * @throws Exception
     */
    public void addRegisterInfo(ForeignRegister entity, JSONArray gridDataCar, JSONArray gridDataPeople, UserBean user, HttpServletRequest request) throws Exception;

    /**
     * 登记外来车信息
     * @param cars
     * @param sprRegisterId
     */
    public void addCar(JSONArray cars, String sprRegisterId);

    /**
     * 登记外来人员信息
     * @param peoples
     * @param sprRegisterId
     */
    public void addPeople(JSONArray peoples, String sprRegisterId);

    /**
     * 修改外来人车基础信息
     * @param entity
     * @param gridDataCar
     * @param gridDataPeople
     * @param user
     */
    public void updateRegisterInfo(ForeignRegister entity, JSONArray gridDataCar, JSONArray gridDataPeople, UserBean user);


    /**
     * 根据id删车记录
     * @param list
     */
    public void deleteCarByIds(List<String> list);

    /**
     * 根据id删人员记录
     * @param list
     */
    public void deletePeopleByIds(List<String> list);


    /**
     * 外来人车信息录入
     * @param foreignRegisterVo
     * @throws Exception
     */
    public void addEntryAndExitInfo(ForeignRegisterVo foreignRegisterVo,HttpServletRequest request) throws Exception;

    /**
     * 分页查询所有
     * @param entity
     * @param pageable
     * @return
     */
    public Page<Map<String, Object>> listAll(ForeignRegisterVo entity, Pageable pageable);

    /**
     * 根据警号查询名称
     * @param policeId
     * @return
     */
    public String findPoliceNameById(String policeId);

    /**
     * 根据登记人车ID查询人员信息
     * @param paramMap
     * @return
     */
    public Page<Map<String, Object>> listPeopleByRegisterId(Map<String, Object> paramMap, Pageable pageable);

    /**
     * 根据登记人车ID查询车辆信息
     * @param paramMap
     * @return
     */
    public Page<Map<String, Object>> listCarByRegisterId(Map<String, Object> paramMap, Pageable pageable);

    /**
     * 通过id查单条登记记录
     * @param paramMap
     * @return
     */
    public ForeignRegister findById(Map<String, Object> paramMap);


    /**
     * 根据登记信息id删除人车记录
     * @param id
     */
    public void deleteByFpdRegisterId(String id);

    /**
     * 根据id删记录
     * @param list
     */
    public void deleteByIds(List<String> list);


    public Map<String, Object> findLastRow(Map<String, Object> paramMap);


    public void updateRegisterCheckInfo(ForeignRegister foreignRegister) throws Exception;

    public void updateRegisterOutInfo(String id) throws Exception;

    public Map<String, Object> findPeopleByCardCode(Map<String, Object> param);

    public Map<String, Object> findCarByCarIdnty(Map<String, Object> param);

    Page<Map<String, Object>> getCarList(Map<String, Object> param, Pageable pageable);

    Page<Map<String, Object>> getPeopleList(Map<String, Object> param, Pageable pageable);
    
    
    public Map<String, Object> dkImage(String id);


}
