package com.cesgroup.prison.foreign.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constants.socket.IMsgTypeConst;
import com.cesgroup.prison.common.entity.MessageEntity;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.common.facade.MessageSendFacade;
import com.cesgroup.prison.common.service.MessageService;
import com.cesgroup.prison.foreign.dao.ForeignCarDtlsMapper;
import com.cesgroup.prison.foreign.dao.ForeignPeopleDtlsMapper;
import com.cesgroup.prison.foreign.dao.ForeignRegisterMapper;
import com.cesgroup.prison.foreign.entity.ForeignCarDtls;
import com.cesgroup.prison.foreign.entity.ForeignPeopleDtls;
import com.cesgroup.prison.foreign.entity.ForeignRegister;
import com.cesgroup.prison.foreign.service.ForeignRegisterService;
import com.cesgroup.prison.foreign.vo.ForeignRegisterVo;
import com.cesgroup.prison.utils.CommonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service(value = "foreignRegisterService")
@Transactional
public class ForeignRegisterServiceImpl extends BaseDaoService<ForeignRegister, String, ForeignRegisterMapper> implements ForeignRegisterService {

    @Autowired
    private ForeignRegisterMapper mapper;

    @Autowired
    private ForeignCarDtlsMapper carMapper;

    @Autowired
    private ForeignPeopleDtlsMapper peopleMapper;
    
    @Resource
    private MessageService messageService;

    @Override
    public void addRegisterInfo(ForeignRegister entity, JSONArray gridDataCar, JSONArray gridDataPeople, UserBean user, HttpServletRequest request) throws Exception {
        Date date = new Date();
        entity.setFrCrteTime(date);
        entity.setFrUpdtTime(date);
        entity.setFrCusNumber(user.getCusNumber());
        entity.setFrCrteUserId(user.getUserId());
        entity.setFrUpdtUserId(user.getUserId());

        entity.setId(CommonUtil.createUUID());

        mapper.insertInfo(entity);
        String sprRegisterId = entity.getId();

        addPeople(gridDataPeople, sprRegisterId);

        addCar(gridDataCar, sprRegisterId);

        this.sendMsg(IMsgTypeConst.FOREIGN_PEOPLE_COUNT, "2", user.getCusNumber(), "2",
                request.getContextPath() + "/Foreign/openDialog", entity);
    }

    @Override
    public void addPeople(JSONArray Peoples, String sprRegisterId) {
        ForeignPeopleDtls entity = null;
        for (Iterator<Object> iterator = Peoples.iterator(); iterator.hasNext(); ) {
            JSONObject People = (JSONObject) iterator.next();

            entity = new ForeignPeopleDtls();

            entity.setFpdRegisterId(sprRegisterId);
            if (null != People.get("fpdIdCardTypeIndc")) {
                entity.setFpdIdCardTypeIndc((String) People.get("fpdIdCardTypeIndc"));
            }
            if (null != People.get("fpdIdCardCode")) {
                entity.setFpdIdCardCode((String) People.get("fpdIdCardCode"));
            }

            if (null != People.get("fpdPeopleName")) {
                entity.setFpdPeopleName((String) People.get("fpdPeopleName"));
            }

            if (null != People.get("fpdSexIndc")) {
                entity.setFpdSexIndc((String) People.get("fpdSexIndc"));
            }

            if (null != People.get("fpdAge")) {
                entity.setFpdAge((String) People.get("fpdAge"));
            }

            if (null != People.get("fpdPhone")) {
                entity.setFpdPhone((String) People.get("fpdPhone"));
            }

            if (null != People.get("fpdFamilyAddrs")) {
                entity.setFpdFamilyAddrs((String) People.get("fpdFamilyAddrs"));
            }

            entity.setId(CommonUtil.createUUID());
            peopleMapper.insertPeopleInfo(entity);
        }
    }

    @Override
    public void addCar(JSONArray Cars, String sprRegisterId) {
        ForeignCarDtls entity = null;
        for (Iterator<Object> iterator = Cars.iterator(); iterator.hasNext(); ) {
            JSONObject car = (JSONObject) iterator.next();

            entity = new ForeignCarDtls();

            entity.setFcdRegisterId(sprRegisterId);
            if (null != car.get("fcdCarLcnsIdnty")) {
                entity.setFcdCarLcnsIdnty((String) car.get("fcdCarLcnsIdnty"));
            }

            if (null != car.get("fcdCarCmpny")) {
                entity.setFcdCarCmpny((String) car.get("fcdCarCmpny"));
            }

            if (null != car.get("fcdCarTypeIndc")) {
                entity.setFcdCarTypeIndc((String) car.get("fcdCarTypeIndc"));
            }

            if (null != car.get("fcdCarColor")) {
                entity.setFcdCarColor((String) car.get("fcdCarColor"));
            }

            if (null != car.get("fcdPeopleCount")) {
                entity.setFcdPeopleCount(Integer.parseInt((String) car.get("fcdPeopleCount")));
            }

            if (null != car.get("fcdCargo")) {
                entity.setFcdCargo((String) car.get("fcdCargo"));
            }


            entity.setId(CommonUtil.createUUID());
            carMapper.insertCarInfo(entity);
        }
    }

    @Override
    public void updateRegisterInfo(ForeignRegister entity, JSONArray gridDataCar, JSONArray gridDataPeople, UserBean user) {

        if (entity != null && !StringUtils.isEmpty(entity.getId())) {

            entity.setFrUpdtTime(new Date());
            entity.setFrUpdtUserId(user.getUserId());
            mapper.updateInfo(entity);

            String sprRegisterId = entity.getId();
            List<String> list = new LinkedList() {
            };
            list.add(sprRegisterId);

            deletePeopleByIds(list);
            deleteCarByIds(list);

            addPeople(gridDataPeople, sprRegisterId);
            addCar(gridDataCar, sprRegisterId);
        }
    }








    /**
     * @param msgType     消息类型: 6002 故障维修
     * @param sendType    发送类型：1-按监狱订阅、2-按用户、3-按组织部门
     * @param sendTo      发送目标对象: 对应上面的具体值-多个值逗号分隔
     * @param isSendToGzt 是否发送到工作台消息	0-否，1-是(默认传1)
     * @param url         业务模块处理url---带上下文路径列表页面
     * @param entity
     * @throws Exception void
     * @throws
     * @methodName: sendMsg
     * @Description: 发送消息
     */
    private void sendMsg(String msgType, String sendType, String sendTo, String isSendToGzt, String url,
                         ForeignRegister entity) throws Exception {
        Map<String, String> msgMap = new HashMap<String, String>();
        msgMap.put("msgType", msgType);
        msgMap.put("sendType", sendType);
        msgMap.put("sendTo", sendTo);
        msgMap.put("content", JSON.toJSONString(entity));
        msgMap.put("url", url);
        msgMap.put("isSendToGzt", isSendToGzt);
        msgMap.put("ywId", entity.getId());
        msgMap.put("cusNumber", entity.getFrCusNumber());
        msgMap.put("noticeContent", "外来人员进入消息");
        MessageSendFacade.send(msgMap);
    }

    /**
     * 出入人员车辆信息登记
     *
     * @param foreignRegisterVo
     * @throws Exception
     */

    @Override
    @Transactional
    public void addEntryAndExitInfo(ForeignRegisterVo foreignRegisterVo,HttpServletRequest request) throws Exception {
        List<ForeignCarDtls> foreignCarDtlsList = foreignRegisterVo.getForeignCarDtlsList();
        List<ForeignPeopleDtls> foreignPeopleDtlsList = foreignRegisterVo.getForeignPeopleDtlsList();
        String id = "";
        if(StringUtils.isEmpty(foreignRegisterVo.getId())){
            id = CommonUtil.createUUID();
            UserBean userBean = AuthSystemFacade.getLoginUserInfo();
            foreignRegisterVo.setFrCusNumber(userBean.getCusNumber());
            foreignRegisterVo.setFrCrteUserId(userBean.getUserId());
            foreignRegisterVo.setFrCrteTime(new Date());
            foreignRegisterVo.setFrCheckSttsIndc("0");
            foreignRegisterVo.setId(id);


            if (foreignPeopleDtlsList != null && foreignPeopleDtlsList.size() > 0) {
                String foreignPeoples="";
                for (ForeignPeopleDtls foreignPeopleDtls : foreignPeopleDtlsList) {
                    foreignPeoples+=foreignPeopleDtls.getFpdPeopleName()+",";
                }
                foreignRegisterVo.setFrPeoplesInfo(foreignPeoples.substring(0,foreignPeoples.length()-1));
            }

            if (foreignCarDtlsList != null && foreignCarDtlsList.size() > 0) {
                String foreignCars="";
                for (ForeignCarDtls foreignCarDtls : foreignCarDtlsList) {
                    foreignCars+=foreignCarDtls.getFcdCarLcnsIdnty()+",";
                }
                foreignRegisterVo.setFrCarsInfo(foreignCars.substring(0,foreignCars.length()-1));
            }

            mapper.insertInfo(foreignRegisterVo);
            this.sendMsg(IMsgTypeConst.FOREIGN_PEOPLE_COUNT, "1", userBean.getCusNumber(), "1",
                    request.getContextPath() + "/foreign/list", foreignRegisterVo);

        }else {
            id = foreignRegisterVo.getId();
            UserBean userBean = AuthSystemFacade.getLoginUserInfo();
            foreignRegisterVo.setFrUpdtUserId(userBean.getUserId());
            foreignRegisterVo.setFrUpdtTime(new Date());

            if (foreignPeopleDtlsList != null && foreignPeopleDtlsList.size() > 0) {
                String foreignPeoples="";
                for (ForeignPeopleDtls foreignPeopleDtls : foreignPeopleDtlsList) {
                    foreignPeoples+=foreignPeopleDtls.getFpdPeopleName()+",";
                }
                foreignRegisterVo.setFrPeoplesInfo(foreignPeoples.substring(0,foreignPeoples.length()-1));
            }

            if (foreignCarDtlsList != null && foreignCarDtlsList.size() > 0) {
                String foreignCars="";
                for (ForeignCarDtls foreignCarDtls : foreignCarDtlsList) {
                    foreignCars+=foreignCarDtls.getFcdCarLcnsIdnty()+",";
                }
                foreignRegisterVo.setFrCarsInfo(foreignCars.substring(0,foreignCars.length()-1));
            }

            mapper.updateInfo(foreignRegisterVo);
            this.deleteByFpdRegisterId(id);
        }


        if (foreignPeopleDtlsList != null && foreignPeopleDtlsList.size() > 0) {
            for (ForeignPeopleDtls foreignPeopleDtls : foreignPeopleDtlsList) {
                foreignPeopleDtls.setId(CommonUtil.createUUID());
                foreignPeopleDtls.setFpdRegisterId(id);
                peopleMapper.insertPeopleInfo(foreignPeopleDtls);
            }
        }

        if (foreignCarDtlsList != null && foreignCarDtlsList.size() > 0) {
            for (ForeignCarDtls foreignCarDtls : foreignCarDtlsList) {
                foreignCarDtls.setId(CommonUtil.createUUID());
                foreignCarDtls.setFcdRegisterId(id);
                carMapper.insertCarInfo(foreignCarDtls);
            }
        }
    }

    @Override
    public void updateRegisterCheckInfo(ForeignRegister foreignRegister) throws Exception {
        UserBean userBean = AuthSystemFacade.getLoginUserInfo();
        foreignRegister.setFrCheckPeopleId(userBean.getUserId());
        foreignRegister.setFrCheckPeople(userBean.getUserName());
//        foreignRegister.setFrCheckSttsIndc("1");
        foreignRegister.setFrCheckTime(new Date());
        mapper.checkInfo(foreignRegister);

        MessageEntity messageEntity=new MessageEntity();
        messageEntity.setYwId(foreignRegister.getId());
        messageEntity.setJyId(foreignRegister.getFrCusNumber());
        messageService.updateReadByYwId(messageEntity);
    }

    @Override
    public void updateRegisterOutInfo(String id) throws Exception {
        UserBean userBean = AuthSystemFacade.getLoginUserInfo();
        ForeignRegister foreignRegister = new ForeignRegister();
        foreignRegister.setId(id);
        foreignRegister.setFrOutPeopleId(userBean.getUserId());
        foreignRegister.setFrOutPeople(userBean.getRealName());

        foreignRegister.setFrOutTime(new Date());
        mapper.outInfo(foreignRegister);
    }


    @Override
    public String findPoliceNameById(String policeId) {
        return mapper.findPoliceNameById(policeId);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Map<String, Object>> listAll(ForeignRegisterVo entity, Pageable pageable) {
        Page<Map<String, Object>> page = mapper.listAll(entity, pageable);
        Iterator<Map<String, Object>> iterator = page.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> resultMap = iterator.next();
            String[] policeIdArray = resultMap.get("FR_OPRTN_POLICE").toString().split(",");
            String reportPoliceId = resultMap.get("FR_REPORT_PEOPLE").toString();
            //String recordPoliceId = resultMap.get("FR_RECORD_PEOPLE").toString();
            String reportPoliceName = null;
            try {
                UserBean loginUserInfo = AuthSystemFacade.getLoginUserInfo();
                List<OrgEntity> list = AuthSystemFacade.getAllChildrenOrgInfoByOrgKey(loginUserInfo.getCusNumber());
                Iterator iteratorOrg = list.iterator();
                while(iteratorOrg.hasNext()){
                    OrgEntity orgEntity = (com.ces.authsystem.entity.OrgEntity) iteratorOrg.next();
                    if(orgEntity.getOrgKey().equals(reportPoliceId)){
                        reportPoliceName = orgEntity.getOrgName();
                    }
                }
            } catch (Exception e) {
//                e.printStackTrace();
                throw new RuntimeException("组织部门查询失败");
            }

            List<String> policeNameList = new ArrayList<>();

            for (String policeId : policeIdArray) {
                policeNameList.add(mapper.findPoliceNameById(policeId));
            }

//            String reportPoliceName = mapper.findPoliceNameById(reportPoliceId);
            //String recordPoliceName = mapper.findPoliceNameById(recordPoliceId);
            String policeName = org.apache.commons.lang3.StringUtils.join(policeNameList.toArray(), " , ");

            resultMap.put("reportPoliceName", reportPoliceName);
            //resultMap.put("recordPoliceName", recordPoliceName);
            resultMap.put("policeName", policeName);

        }
        return page;
    }


    @Override
    public Page<Map<String, Object>> listPeopleByRegisterId(Map<String, Object> paramMap, Pageable pageable) {
        return peopleMapper.listPeople(paramMap, pageable);
    }

    @Override
    public Page<Map<String, Object>> listCarByRegisterId(Map<String, Object> paramMap, Pageable pageable) {
        return carMapper.listCar(paramMap, pageable);
    }

    @Override
    public ForeignRegister findById(Map<String, Object> paramMap) {
        return mapper.findById(paramMap);
    }

    @Override
    @Transactional
    public void deleteByFpdRegisterId(String id) {
        peopleMapper.deleteByFpdRegisterId(id);
        carMapper.deleteByFpdRegisterId(id);
    }

    @Override
    public void deletePeopleByIds(List<String> list) {
        peopleMapper.deleteByIds(list);
    }

    @Override
    public void deleteCarByIds(List<String> list) {
        carMapper.deleteByIds(list);
    }

    @Override
    @Transactional
    public void deleteByIds(List<String> list) {
        mapper.deleteByIds(list);
        deletePeopleByIds(list);
        deleteCarByIds(list);
    }

    @Override
    public Map<String, Object> findPeopleByCardCode(Map<String, Object> param) {
        return peopleMapper.findPeopleByCardCode(param);
    }

    @Override
    public Map<String, Object> findCarByCarIdnty(Map<String, Object> param) {
        return carMapper.findCarByCarIdnty(param);
    }

    @Override
    public Page<Map<String, Object>> getCarList(Map<String, Object> param, Pageable pageable) {
        try {
            UserBean userBean = AuthSystemFacade.getLoginUserInfo();
            param.put("cusNumber", userBean.getCusNumber());
            return carMapper.getCarList(param, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<Map<String, Object>> getPeopleList(Map<String, Object> param, Pageable pageable) {
        try {
            UserBean userBean = AuthSystemFacade.getLoginUserInfo();
            param.put("cusNumber", userBean.getCusNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return peopleMapper.getPeopleList(param, pageable);
    }

    @Override
    public Map<String, Object> findLastRow(Map<String, Object> paramMap) {
        return mapper.findLastRow(paramMap);
    }

	@Override
	public Map<String, Object> dkImage(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		ForeignRegister f = mapper.findById(map);
		String zhp1 = f.getFrPhoto1();
		String zhp2 = f.getFrPhoto2();
		Map<String, Object> mapr = new HashMap<String, Object>();
		mapr.put("phono1", zhp1);
		mapr.put("phono2", zhp2);
		return mapr;
	}
}

