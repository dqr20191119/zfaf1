package com.cesgroup.prison.emergency.handle.recordMember.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.util.DateUtil;
import com.cesgroup.framework.util.MsgIdUtil;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.frm.net.netty.bean.MsgHeader;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.emergency.handle.record.dao.EmerHandleRecordDao;
import com.cesgroup.prison.emergency.handle.record.entity.EmerHandleRecord;
import com.cesgroup.prison.emergency.handle.recordGroup.dao.EmerHandleRecordGroupDao;
import com.cesgroup.prison.emergency.handle.recordGroup.entity.EmerHandleRecordGroup;
import com.cesgroup.prison.emergency.handle.recordMember.dao.EmerHandleRecordMemberDao;
import com.cesgroup.prison.emergency.handle.recordMember.dto.NearbyLocationDto;
import com.cesgroup.prison.emergency.handle.recordMember.dto.NearbyPoliceDto;
import com.cesgroup.prison.emergency.handle.recordMember.entity.EmerHandleRecordMember;
import com.cesgroup.prison.emergency.handle.recordMember.service.EmerHandleRecordMemberService;
import com.cesgroup.prison.jfsb.service.TalkBackServerService;
import com.cesgroup.prison.netamq.service.MqMessageSender;
import com.cesgroup.prison.rcs.dto.RcsMessageBodyDto;
import com.cesgroup.prison.rcs.dto.RcsMessageDto;
import com.cesgroup.prison.rcs.dto.YjyaMessageDto;
import com.cesgroup.prison.utils.CommonUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 应急预案业务操作类
 */
@Service
@Transactional
public class EmerHandleRecordMemberServiceImpl extends BaseDaoService<EmerHandleRecordMember, String, EmerHandleRecordMemberDao> implements EmerHandleRecordMemberService {
    /**
     * gson工具类
     */
    private static final Gson gson = new GsonBuilder().create();

    /**
     * 应急处置记录DAO
     */
    @Autowired
    private EmerHandleRecordDao emerHandleRecordDao;
    /**
     * 应急处置记录的工作组DAO
     */
    @Autowired
    private EmerHandleRecordGroupDao emerHandleRecordGroupDao;
    /**
     * 消息发送
     */
    @Autowired
    private MqMessageSender mqMessageSender;
    /**
     * 对讲主机Service
     */
    @Resource
    private TalkBackServerService talkBackServerService;

    @Override
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException {
        try {
            Page<Map<String, Object>> page = this.getDao().findWithPage(paramMap, pageable);
            return page;
        } catch (Exception e) {
            throw new ServiceException("分页查询应急处置记录的工作组成员发生异常，原因：" + e);
        }
    }

    @Override
    public List<EmerHandleRecordMember> queryByRecordGroupId(String recordGroupId) throws ServiceException {
        try {
            return this.getDao().findByRecordGroupIdAndStatus(recordGroupId, "0");
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录的工作组ID、数据状态，查询应急处置记录的工作组成员列表发生异常");
        }
    }

    @Override
    public Page<NearbyPoliceDto> queryNearbyWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException {
        try {
            Page<NearbyPoliceDto> page = this.getDao().findNearbyWithPage(paramMap, pageable);
            if(page != null && page.getContent() != null && page.getContent().size() > 0) {
                for(NearbyPoliceDto nearbyPoliceDto : page.getContent()) {
                    // 根据民警警号、民警姓名，查询民警位置信息、数据来源、数据类型，取最新的一条数据
                    Map<String, Object> tempParamMap = new LinkedHashMap<String, Object>();
                    tempParamMap.put("cusNumber", paramMap.get("cusNumber"));
                    tempParamMap.put("areaName", paramMap.get("areaName"));
                    tempParamMap.put("timeBeforeThirtyMins", paramMap.get("timeBeforeThirtyMins"));
                    tempParamMap.put("policeNo", nearbyPoliceDto.getPoliceNo());
                    tempParamMap.put("policeName", nearbyPoliceDto.getPoliceName());

                    List<NearbyLocationDto> nearbyLocationDtoList = null;
                    try {
                        nearbyLocationDtoList = this.getDao().findNearbyLocationByContition(tempParamMap);
                    } catch (Exception e) {
                        throw new ServiceException("分页查询附近的民警信息发生异常，原因：" + e);
                    }
                    if(nearbyLocationDtoList != null && nearbyLocationDtoList.size() > 0) {
                        nearbyPoliceDto.setCaptureTime(nearbyLocationDtoList.get(0).getCaptureTime());
                        nearbyPoliceDto.setLocationName(nearbyLocationDtoList.get(0).getLocationName());
                        nearbyPoliceDto.setDataSource(nearbyLocationDtoList.get(0).getDataSource());
                        nearbyPoliceDto.setDataCategory(nearbyLocationDtoList.get(0).getDataCategory());
                    }
                }
            }
            return page;
        } catch (Exception e) {
            throw new ServiceException("分页查询附近的民警信息发生异常，原因：" + e);
        }
    }

    @Override
    public Page<NearbyPoliceDto> queryAllPoliceWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException {
        try {
            return this.getDao().findAllPoliceWithPage(paramMap, pageable);
        } catch (Exception e) {
            throw new ServiceException("分页查询本单位全部的民警信息发生异常，原因：" + e);
        }
    }

    @Override
    public void saveOrUpdate(String recordGroupId, String memberDataJson) throws ServiceException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new ServiceException("用户未登录");
        }
        //1 根据应急处置记录的工作组主键ID，查询应急处置记录的工作组信息
        EmerHandleRecordGroup emerHandleRecordGroup = null;
        try {
            emerHandleRecordGroup = this.emerHandleRecordGroupDao.findById(recordGroupId);
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录的工作组ID，查询应急处置记录的工作组发生异常，原因：" + e);
        }
        if(emerHandleRecordGroup == null) {
            throw new ServiceException("根据应急处置记录的工作组ID，查询到的应急处置记录的工作组为空");
        }

        //2 将工作组成员Json字符串转化为Json数组
        JsonArray memberDataJsonArray = gson.fromJson(memberDataJson, JsonArray.class);
        if(memberDataJsonArray == null || memberDataJsonArray.size() <= 0) {
            return;
        }

        //3 根据应急处置记录的工作组ID，查询该工作组当前关联的工作组成员列表
        List<EmerHandleRecordMember> emerHandleRecordMemberList = null;
        try {
            emerHandleRecordMemberList = this.getDao().findByRecordGroupIdAndStatus(emerHandleRecordGroup.getId(), "0");
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录的工作组ID，查询应急处置记录的工作组成员发生异常，原因：" + e);
        }

        //4 将工作组成员Json数组，与工作组当前已关联的工作组成员比较，按照待添加、待更新、待删除归类
        List<EmerHandleRecordMember> listToAdd = null, listToUpd = null, listToDel = null;

        //4.1 遍历emerHandleRecordMemberList，提取成员姓名_呼叫号码，存入HashMap用于标识一个成员
        Map<String, EmerHandleRecordMember> memberNameCallNoMap = new LinkedHashMap<String, EmerHandleRecordMember>();
        if(emerHandleRecordMemberList != null && emerHandleRecordMemberList.size() > 0) {
            for(EmerHandleRecordMember member : emerHandleRecordMemberList) {
                memberNameCallNoMap.put(member.getMemberName() + "|" + member.getCallNo(), member);
            }
        }

        //4.2 遍历工作组成员Json数组，与工作组当前已关联的工作组成员比较，得到待添加、待更新列表
        for(int i=0; i<memberDataJsonArray.size(); i++) {
            JsonObject memberDataJsonObject = memberDataJsonArray.get(i).getAsJsonObject();

            if(memberDataJsonObject == null) {
                continue;
            }

            String unitName = Util.notNull(memberDataJsonObject.get("unitName")) ? memberDataJsonObject.get("unitName").getAsString() : null;
            String deptName = Util.notNull(memberDataJsonObject.get("deptName")) ? memberDataJsonObject.get("deptName").getAsString() : null;
            String policeNo = Util.notNull(memberDataJsonObject.get("policeNo")) ? memberDataJsonObject.get("policeNo").getAsString() : null;
            String policeName = Util.notNull(memberDataJsonObject.get("policeName")) ? memberDataJsonObject.get("policeName").getAsString() : null;
            String callNo = Util.notNull(memberDataJsonObject.get("callNo")) ? memberDataJsonObject.get("callNo").getAsString() : null;

            if(memberNameCallNoMap.containsKey(policeName + "|" + callNo)) {
                EmerHandleRecordMember tempMember = memberNameCallNoMap.get(policeName + "|" + callNo);
                tempMember.setUnitName(unitName);
                tempMember.setDeptName(deptName);
                tempMember.setMemberJobNo(policeNo);
                tempMember.setMemberName(policeName);
                tempMember.setCallNo(callNo);
                if(listToUpd == null) {
                    listToUpd = new ArrayList<EmerHandleRecordMember>();
                }
                listToUpd.add(tempMember);
            } else {
                EmerHandleRecordMember tempMember = new EmerHandleRecordMember();
                tempMember.setUnitName(unitName);
                tempMember.setDeptName(deptName);
                tempMember.setMemberJobNo(policeNo);
                tempMember.setMemberName(policeName);
                tempMember.setCallNo(callNo);
                if(listToAdd == null) {
                    listToAdd = new ArrayList<EmerHandleRecordMember>();
                }
                listToAdd.add(tempMember);
            }
        }

        //4.3 根据已有的工作组成员列表、待更新的工作组成员列表，获取差集，即：待删除的工作组成员列表
        listToDel = this.memberListRemove(emerHandleRecordMemberList, listToUpd);

        //5 分别对待新增、待更新、待删除的工作组成员列表进行数据库操作
        if(listToAdd != null && listToAdd.size() > 0) {
            for(EmerHandleRecordMember memberToAdd : listToAdd) {
                memberToAdd.setId(CommonUtil.createUUID());
                memberToAdd.setCusNumber(emerHandleRecordGroup.getCusNumber());
                memberToAdd.setRecordId(emerHandleRecordGroup.getRecordId());
                memberToAdd.setRecordGroupId(emerHandleRecordGroup.getId());
                memberToAdd.setCallStatus("0");
                memberToAdd.setSource("1");
                memberToAdd.setStatus("0");
                Integer showOrder = this.getDao().findMaxShowOrderByCusNumberAndRecordIdAndRecordGroupId(memberToAdd.getCusNumber(), memberToAdd.getRecordId(), memberToAdd.getRecordGroupId());
                memberToAdd.setShowOrder(Long.valueOf(showOrder == null ? 1 : showOrder + 1));
                memberToAdd.setUpdateUserId(user.getUserId());
                memberToAdd.setUpdateTime(new Date());
                try {
                    this.getDao().insertSelective(memberToAdd);
                } catch (Exception e) {
                    throw new ServiceException("新增应急处置记录的工作组成员发生异常，原因：" + e);
                }
            }
        }
        if(listToUpd != null && listToUpd.size() > 0) {
            for(EmerHandleRecordMember memberToUpd : listToUpd) {
                memberToUpd.setCallStatus("0");
                memberToUpd.setSource("1");
                memberToUpd.setStatus("0");
                memberToUpd.setUpdateUserId(user.getUserId());
                memberToUpd.setUpdateTime(new Date());
                try {
                    this.getDao().updateSelective(memberToUpd);
                } catch (Exception e) {
                    throw new ServiceException("更新应急处置记录的工作组成员发生异常，原因：" + e);
                }
            }
        }
        if(listToDel != null && listToDel.size() > 0) {
            for(EmerHandleRecordMember memberToDel : listToDel) {
                try {
                    this.getDao().deleteByEntity(memberToDel);
                } catch (Exception e) {
                    throw new ServiceException("删除多余的应急处置记录的工作组成员发生异常，原因：" + e);
                }
            }
        }
    }

    @Override
    public void startCallByRecordGroupId(String clientIp, String recordGroupId, String noticeContent, boolean restartCall) throws ServiceException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new ServiceException("用户未登录");
        }
        if(recordGroupId == null || "".equals(recordGroupId)) {
           throw new ServiceException("应急处置记录的工作组ID为空，呼叫工作组成员中断");
        }

        //1 根据应急处置记录的工作组主键ID，查询应急处置记录的工作组信息
        EmerHandleRecordGroup emerHandleRecordGroup = null;
        try {
            emerHandleRecordGroup = this.emerHandleRecordGroupDao.findById(recordGroupId);
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录的工作组ID，查询应急处置记录的工作组发生异常，原因：" + e);
        }
        if(emerHandleRecordGroup == null) {
            throw new ServiceException("根据应急处置记录的工作组ID，查询到的应急处置记录的工作组为空");
        }
        //1.1 判断应急处置记录的工作组通知状态
        if(!restartCall && emerHandleRecordGroup.getGroupNoticeStatus() != null && "1".equals(emerHandleRecordGroup.getGroupNoticeStatus())) {
            logger.info("工作组[" + emerHandleRecordGroup.getGroupName() + "]的呼叫未执行，原因：工作组通知状态为已通知，且并非重新呼叫");
            return;
        }

        //2 根据当前用户的IP，查找当前IP所绑定的对讲主机
        String talkServerId = null, talkServerBrand = null;
        try {
            List<Map<String, Object>> talkBackServerList = talkBackServerService.findInfoByCusNumberAndPcIpAndBaseIdnty(emerHandleRecordGroup.getCusNumber(), clientIp, null);

            if(talkBackServerList != null && talkBackServerList.size() > 0) {
                talkServerId = (String) talkBackServerList.get(0).get("TSE_IDNTY");
                talkServerBrand = (String) talkBackServerList.get(0).get("TSE_BRAND");
            }
        } catch (Exception e) {
            throw new ServiceException("根据单位编号[" + emerHandleRecordGroup.getCusNumber() + "]，IP[" + clientIp + "]，查询绑定的对讲主机发生异常，原因：" + e);
        }
        if(Util.isNull(talkServerId)) {
            throw new ServiceException("请求的IP[" + clientIp + "]，在单位编号[" + emerHandleRecordGroup.getCusNumber() + "]未绑定对讲主机，无法呼叫通讯号码！");
        }

        //3 根据应急处置记录ID，查询应急处置记录信息
        EmerHandleRecord emerHandleRecord = null;
        try {
            emerHandleRecord = this.emerHandleRecordDao.findById(emerHandleRecordGroup.getRecordId());
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录ID，查询应急处置记录发生异常，原因：" + e);
        }
        if(emerHandleRecord == null) {
            throw new ServiceException("根据应急处置记录ID，查询到的应急处置记录为空");
        }

        //4 根据应急处置记录的工作组ID，查询该工作组当前关联的工作组成员列表
        List<EmerHandleRecordMember> emerHandleRecordMemberList = null;
        try {
            emerHandleRecordMemberList = this.getDao().findByRecordGroupIdAndStatus(emerHandleRecordGroup.getId(), "0");
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录的工作组ID，查询应急处置记录的工作组成员发生异常，原因：" + e);
        }
        if(emerHandleRecordMemberList == null || emerHandleRecordMemberList.size() <= 0) {
            throw new ServiceException("工作组成员为空");
        }

        //5 调用指挥调度系统的启动预案或者语音通知模式通知工作组成员
        if(noticeContent == null || "".equals(noticeContent)) {
            throw new ServiceException("通知内容为空，呼叫工作组成员中断");
        }

        //5.1 将用户列表中的号码，拼接成多个号码字符串，以英文逗号分隔
        String callNos = "";
        for(EmerHandleRecordMember recordMember : emerHandleRecordMemberList) {
            callNos += recordMember.getCallNo() + ",";
        }
        if(callNos != null && !"".equals(callNos)) {
            callNos = callNos.substring(0, callNos.lastIndexOf(","));
        }

        //5.2 发送呼叫消息
        //5.2.1 消息体
        JSONObject msgBody = new JSONObject();
        msgBody.put("talkID", talkServerId);
        msgBody.put("toID", callNos);
        msgBody.put("action", "1");
        msgBody.put("brand", talkServerBrand);
        msgBody.put("userID", user.getUserId());

        //5.2.2 消息封装
        String sendMsg = this.createMessage(emerHandleRecordGroup.getCusNumber(), msgBody);

        //5.2.3 消息发送
        try {
            mqMessageSender.sendTalkMessage(sendMsg, emerHandleRecordGroup.getCusNumber(), "");
        } catch (Exception e) {
            throw new ServiceException("调用对讲主机，呼叫[" + emerHandleRecordGroup.getGroupName() + ": " + callNos + "]发生异常");
        }

        //5 更新应急处置记录的工作组、工作组成员的通知状态
        //5.1 更新应急处置记录的工作组通知状态
        if(noticeContent != null && !"".equals(noticeContent)) {
            emerHandleRecordGroup.setGroupNotice(noticeContent);
        }
        emerHandleRecordGroup.setGroupNoticeStatus("1");
        emerHandleRecordGroup.setGroupNoticeTime(new Date());
        emerHandleRecordGroup.setUpdateUserId(user.getUserId());
        emerHandleRecordGroup.setUpdateTime(new Date());
        try {
            this.emerHandleRecordGroupDao.updateSelective(emerHandleRecordGroup);
        } catch (Exception e) {
            throw new ServiceException("更新应急处置记录的工作组的通知状态发生异常");
        }

        //5.2 更新应急处置记录的工作组成员呼叫状态
        for(EmerHandleRecordMember recordMember : emerHandleRecordMemberList) {
            recordMember.setCallStatus("1");
            recordMember.setCallResult("呼叫已发送");
            recordMember.setCallTime(new Date());
            recordMember.setUpdateUserId(user.getUserId());
            recordMember.setUpdateTime(new Date());

            try {
                this.getDao().updateSelective(recordMember);
            } catch (Exception e) {
                throw new ServiceException("更新应急处置记录的工作组成员的呼叫状态发生异常");
            }
        }
    }

    @Override
    public List<EmerHandleRecordMember> queryListByRecordIdAndRecordGroupId(String recordId, String recordGroupId) throws ServiceException {
        if(recordId == null || "".equals(recordId) || recordGroupId == null || "".equals(recordGroupId)) {
            return null;
        }
        //1 应急工作组查询
        List<EmerHandleRecordMember> handleMemberList = null;
        try {
            handleMemberList = this.getDao().findByRecordIdAndRecordGroupIdAndStatus(recordId, recordGroupId, "0");
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录ID[" + recordId + "]、关联的工作组ID[" + recordGroupId + "]查询关联的应急工作组成员发生异常");
        }
        return handleMemberList;
    }

    /**
     * 获取两个工作组成员列表的差集
     * @param list1
     * @param list2
     * @return
     */
    private List<EmerHandleRecordMember> memberListRemove(List<EmerHandleRecordMember> list1, List<EmerHandleRecordMember> list2) {
        if(list1 == null || list1.size() <= 0) {
            return null;
        }
        if(list2 == null || list2.size() <= 0) {
            return list1;
        }
        // 将list2总的工作组成员ID，存入HashSet
        Set<String> set2 = new HashSet<String>();
        for(EmerHandleRecordMember member2 : list2) {
            set2.add(member2.getId());
        }

        List<EmerHandleRecordMember> resultList = new ArrayList<EmerHandleRecordMember>();
        for(EmerHandleRecordMember member1 : list1) {
            if(!set2.contains(member1.getId())) {
                resultList.add(member1);
            }
        }
        return resultList;
    }

    /**
     * 遍历应急处置记录的工作组成员，找到增补的用户号码
     *
     * @param list
     * @return
     */
    private String getSupplementCallNo(List<EmerHandleRecordMember> list) {
        if(list == null || list.size() <= 0) {
            return null;
        }
        String supplementCallNo = "";
        for(EmerHandleRecordMember member: list) {
            if("0".equals(member.getSource())) {
                supplementCallNo += member.getCallNo() + ",";
            }
        }
        if(!"".equals(supplementCallNo)) {
            supplementCallNo = supplementCallNo.substring(0, supplementCallNo.lastIndexOf(","));
        }
        return supplementCallNo;
    }

    /**
     * 封装消息头与消息内容--对讲
     *
     * @param cusNumber
     * @param msgBody
     * @return
     */
    private String createMessage(String cusNumber, JSONObject msgBody) {
        // 消息头
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setCusNumber(cusNumber);// 新增的消息头
        msgHeader.setMsgID(MsgIdUtil.getMsgSeq(""));
        msgHeader.setMsgType("TALK001");
        msgHeader.setLength(0);
        msgHeader.setSender("Server");
        msgHeader.setRecevier("Server");
        msgHeader.setSendTime(DateUtil.getDateString(new Date(), DateUtil.sdf));
        JSONObject out = new JSONObject();
        out.put("header", msgHeader);
        out.put("body", msgBody);
        return out.toJSONString();
    }
}
