package com.cesgroup.prison.sporadicFlow.service.impl;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constants.socket.IMsgTypeConst;
import com.cesgroup.prison.common.facade.MessageSendFacade;
import com.cesgroup.prison.sporadicFlow.dao.SporadicFlowMapper;
import com.cesgroup.prison.sporadicFlow.dao.SporadicFlowPeopleRegisterMapper;
import com.cesgroup.prison.sporadicFlow.entity.SporadicFlowPeopleRegisterEntity;
import com.cesgroup.prison.sporadicFlow.entity.SporadicFlowRegisterEntity;
import com.cesgroup.prison.sporadicFlow.service.SporadicFlowService;
import com.cesgroup.prison.utils.CommonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Transactional
public class SporadicFlowServiceImpl extends BaseDaoService<SporadicFlowRegisterEntity, String, SporadicFlowMapper>
        implements SporadicFlowService {

    @Autowired
    private SporadicFlowMapper mapper;

    @Autowired
    private SporadicFlowPeopleRegisterMapper peopleMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<Map<String, Object>> listAll(SporadicFlowRegisterEntity entity, Pageable pageable,UserBean user) {
        Map<String, Object> map = new HashMap<>();
        if (entity != null) {
            map.put("sporadicFlowRegisterEntity", entity);
        }
        return mapper.listAll(map, pageable);
    }

    @Override
    public void addRegisterInfo(SporadicFlowRegisterEntity entity, JSONArray gridDataPolice, JSONArray gridDataOffender, UserBean user,HttpServletRequest request) throws Exception{
        entity.setSflCrteTime(new Date());
        entity.setSflUpdtTime(new Date());
        entity.setSflCusNumber(Integer.parseInt(user.getCusNumber()));
        entity.setSflCrteDprtmntId(user.getDprtmntCode());
        entity.setSflCrteUserId(user.getUserId());
        entity.setSflCrteUser(user.getRealName());
        entity.setSflUpdtUserId(user.getUserId());
        entity.setSflUpdtUser(user.getRealName());

        entity.setSflFlowStts("1");// 流动状态 待出发
        entity.setId(CommonUtil.createUUID());
        mapper.insertSporadicFlowRegister(entity);
        String sprRegisterId = entity.getId();
        addPolice(gridDataPolice, sprRegisterId, user);
        addOffender(gridDataOffender, sprRegisterId, user);

        this.sendMsg(IMsgTypeConst.CURRENT_FLOW_PEOPLE, "1", user.getCusNumber(), "1",
                request.getContextPath() + "/sporadicFlow/openDialog", entity);
    }

    @Override
    public void addPolice(JSONArray Peoples, String sprRegisterId, UserBean user) {
        SporadicFlowPeopleRegisterEntity entity = null;
        for (Iterator<Object> iterator = Peoples.iterator(); iterator.hasNext(); ) {
            JSONObject People = (JSONObject) iterator.next();
            Date date = new Date();
            entity = new SporadicFlowPeopleRegisterEntity();
            entity.setSprUpdtUserId(user.getUserId());
            entity.setSprUpdtTime(date);
            entity.setSprRegisterId(sprRegisterId);
            entity.setSprCusnumber(user.getCusNumber());
            entity.setSprPeopleId((String) People.get("id"));
            entity.setSprPeople((String) People.get("name"));
            entity.setSprPeopleType("1");
            entity.setSprCrteUserId(user.getUserId());
            entity.setSprCrteTime(date);
            entity.setId(CommonUtil.createUUID());
            peopleMapper.insertPeople(entity);
        }
    }

    @Override
    public void addOffender(JSONArray Peoples, String sprRegisterId, UserBean user) {
        SporadicFlowPeopleRegisterEntity entity = null;
        for (Iterator<Object> iterator = Peoples.iterator(); iterator.hasNext(); ) {
            JSONObject People = (JSONObject) iterator.next();
            Date date = new Date();
            entity = new SporadicFlowPeopleRegisterEntity();
            entity.setSprUpdtUserId(user.getUserId());
            entity.setSprUpdtTime(date);
            entity.setSprRegisterId(sprRegisterId);
            entity.setSprCusnumber(user.getCusNumber());
            entity.setSprPeopleId((String) People.get("PRSNR_IDNTY"));
            entity.setSprPeople((String) People.get("NAME"));
            entity.setSprJSH((String) People.get("JSH"));
            entity.setSprPeopleType("2");
            entity.setSprCrteUserId(user.getUserId());
            entity.setSprCrteTime(date);
            entity.setId(CommonUtil.createUUID());
            peopleMapper.insertPeople(entity);
        }
    }

    @Override
    public void updateRegisterInfo(SporadicFlowRegisterEntity entity,UserBean user) {
        Map<String, Object> map = new HashMap<>();
        if (entity != null && !StringUtils.isEmpty(entity.getId())) {
            entity.setSflUpdtTime(new Date());
            entity.setSflUpdtUserId(user.getUserId());
            if (entity.getSflFlowStts() != null && "3".equals(entity.getSflFlowStts())) {
                entity.setSflEndTime(new Date());
            }
            if (entity.getSflFlowStts() != null && "4".equals(entity.getSflFlowStts())) {
                entity.setSflBackTime(new Date());
            }
            if (entity.getSflFlowCheckStts()!=null && !StringUtils.isEmpty(entity.getSflFlowCheckStts())) {
                entity.setSflCheckUser(user.getRealName());
                entity.setSflCheckUserId(user.getUserId());
                entity.setSflCheckTime(new Date());
            }
            map.put("sporadicFlowRegisterEntity", entity);
        }
        mapper.updateSoradicFlowInfo(map);
    }

    @Override
    public void updateRegisterInfoAll(SporadicFlowRegisterEntity entity, JSONArray gridDataPolice, JSONArray gridDataOffender, UserBean user,HttpServletRequest request) throws Exception{
        entity.setSflUpdtTime(new Date());

        Map<String, Object> map = new HashMap<>();
        if (entity != null && !StringUtils.isEmpty(entity.getId())) {
            entity.setSflUpdtUserId(user.getUserId());
            entity.setSflUpdtUser(user.getRealName());
            entity.setSflUpdtTime(new Date());
            map.put("sporadicFlowRegisterEntity", entity);

            mapper.updateSoradicFlowInfo(map);

            String sprRegisterId = entity.getId();

            List<String> list = new LinkedList() {};
            list.add(sprRegisterId);

            deletePeopleByIds(list);

            addPolice(gridDataPolice, sprRegisterId, user);
            addOffender(gridDataOffender, sprRegisterId, user);
        }
    }

    @Override
    public void deleteByIds(List<String> list) {
        mapper.deleteByIds(list);
        peopleMapper.deleteByIds(list);
    }

    @Override
    public void deletePeopleByIds(List<String> list) {
        peopleMapper.deleteByIds(list);
    }

    @Override
    public Map<String, Object> findById(Map<String, Object> paramMap) {
        return mapper.findById(paramMap);
    }

    @Override
    public List<Map<String, Object>> findDeptPoliceForGridByItem(Map<String, Object> paramMap) {
        return peopleMapper.listPolice(paramMap);
    }

    @Override
    public List<Map<String, Object>> findDeptOffenderForGridByItem(Map<String, Object> paramMap) {
        return peopleMapper.listOffender(paramMap);
    }

    /**
     * @methodName: sendMsg
     * @Description: 发送消息
     * @param msgType 消息类型: 6002 故障维修
     * @param sendType 发送类型：1-按监狱订阅、2-按用户、3-按组织部门
     * @param sendTo 发送目标对象: 对应上面的具体值-多个值逗号分隔
     * @param isSendToGzt 是否发送到工作台消息	0-否，1-是(默认传1)
     * @param url 业务模块处理url---带上下文路径列表页面
     * @param entity
     * @throws Exception void
     * @throws
     */
    private void sendMsg(String msgType, String sendType, String sendTo, String isSendToGzt, String url,
                         SporadicFlowRegisterEntity entity) throws Exception {
        Map<String, String> msgMap = new HashMap<String, String>();
        msgMap.put("msgType", msgType);
        msgMap.put("sendType", sendType);
        msgMap.put("sendTo", sendTo);
        msgMap.put("content", JSON.toJSONString(entity));
        msgMap.put("url", url);
        msgMap.put("isSendToGzt", isSendToGzt);
        msgMap.put("ywId", entity.getId());
        msgMap.put("cusNumber", Integer.toString(entity.getSflCusNumber()));
        msgMap.put("noticeContent", "零星流动人员消息");
        MessageSendFacade.send(msgMap);
    }
}
