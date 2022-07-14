package com.cesgroup.prison.bfcfprocess.service;

import javax.annotation.Resource;

import com.cesgroup.prison.fm.util.MsgTypeConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.bfcf.dao.BfCfREcordMapper;
import com.cesgroup.prison.bfcf.entity.BfCfREcordEntity;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.jfsb.entity.AlertorEntity;
import com.google.gson.JsonObject;

/**
 * 布防撤防返回信息处理
 *
 * @author
 */
@Service
@Transactional
public class BfCfProcess implements IMessageProcess {
    /**
     * 日志处理类
     */
    private final Logger logger = LoggerFactory.getLogger(BfCfProcess.class);

     
    @Resource
    private BfCfREcordMapper bfCfREcordMapper;
    
    /**
     * 布防撤防消息处理
     */
    @Transactional
    @Override
    public void processMessage(String cusNumber, JSONObject jsonObject) {
       System.out.println("BfCfProcess processMessage 收到布防撤防消息" + jsonObject.toJSONString());
        // 将普通的JSONObject对象转化为JsonObject
        JsonObject gsonObject = Util.fromJson(JSONObject.toJSONString(jsonObject), JsonObject.class);

        // 从gsonObject中取出header，并转化为MsgHeader兑现
        MsgHeader msgHead = gsonObject != null ? Util.fromJson(gsonObject.get("header"), MsgHeader.class) : null;

        // 从msgHead中获取msgType消息类型
        String msgType = msgHead != null ? msgHead.getMsgType() : "";
      //更新周界报警器的 状态 主要为了实时更新报警器的状态
        if(msgType.equals(MsgTypeConst.BFCF_STATUS)) {
        	 logger.info("收到布防撤防状态更新信息：" + jsonObject.toJSONString());
        	 // 从gsonObject中获取body
            JsonObject body = gsonObject.getAsJsonObject("body");
            //获取防区编号FenquId和状态 Status    0 布防   1 撤防  2周界布防  3.没有定义 
            
            String  fenquId = body.get("FenquId") != null?body.get("FenquId").toString():"";
            String  status = body.get("Status") != null?body.get("Status").toString():"";
            //状态和数据库里的含义不一样  转换下
            String abdType ="";
            if(status.equals("0")) {
            	abdType= "1";//布防
            }else if(status.equals("1")) {
            	abdType ="0";//撤防
            }else if(status.equals("2")) {
            	abdType="2";//周界布防
            }else if(status.equals("0")) {
            	abdType="3";//没有定义
            }
            //这里查询一下该分区的布防撤防状态 若跟消息的状态一样  就不更改  否则更改
            AlertorEntity alertor = bfCfREcordMapper.selectAbdTypeByFenquId(cusNumber,fenquId);
            if(!abdType.equals(alertor.getAbdType())) {
            	 //更新防区报警器的布防撤防状态
                AlertorEntity alertorEntity = new AlertorEntity();
                alertorEntity.setAbdIdnty(fenquId);
                alertorEntity.setAbdType(abdType);
                alertorEntity.setAbdCusNumber(cusNumber);
                bfCfREcordMapper.updateAbdTypeAlertorByFenquId(alertorEntity);
            }
        }
      //布防撤防操作命令后的返回消息
        if(msgType.equals(MsgTypeConst.BFCF_TYPE)) {
        	try {
        		 logger.info("收到布防撤防指令返信息：" + jsonObject.toJSONString());
            	 // 从gsonObject中获取body
                JsonObject body = gsonObject.getAsJsonObject("body");
                //"action":3,"status":1,"fenquID":"00000003" "msgID"
                //action 1 布防  2.撤防   3.全部布防  4.全部撤防
                //status 0 失败 1 成功
                String  action = body.get("action") != null?body.get("action").toString():"";
                String  status = body.get("status") != null?body.get("status").toString():"";
                String  fenquId = body.get("fenquID") != null?body.get("fenquID").toString():"";
                String  msgId = body.get("msgID") != null?body.get("msgID").toString():"";
              //更新布防撤防记录
            	BfCfREcordEntity bfCfREcordEntity   = new BfCfREcordEntity();
            	bfCfREcordEntity.setMsgId(msgId);
            	bfCfREcordEntity.setStatus(status);
            	bfCfREcordMapper.updateStatusByMsgId(bfCfREcordEntity);
                if(status.equals("1")) {//成功
                	//更新报警器的布防撤防状态
                	AlertorEntity alertorEntity = new AlertorEntity();
                	alertorEntity.setAbdCusNumber(cusNumber);
                	if(action.equals("3")) {//一键布防
                		alertorEntity.setAbdType("1");
                		bfCfREcordMapper.updateAbdTypeAlertor(alertorEntity);
                	}else if(action.equals("4")) {//一键撤防
                		alertorEntity.setAbdType("0");
                		bfCfREcordMapper.updateAbdTypeAlertor(alertorEntity);
                	}if(action.equals("1")) {
                		alertorEntity.setAbdIdnty(fenquId);
                        alertorEntity.setAbdType("1");//布防
                		bfCfREcordMapper.updateAbdTypeAlertorByFenquId(alertorEntity);
                	}if(action.equals("2")) {
                		alertorEntity.setAbdIdnty(fenquId);
                        alertorEntity.setAbdType("0");//撤防
                		bfCfREcordMapper.updateAbdTypeAlertorByFenquId(alertorEntity);
                	}
                }else {//失败
                	logger.info("操作布防撤防失败");
                }
			} catch (Exception e) {
				logger.error("操作布防撤防发生异常,信息为:"+e.getMessage());
			}
        	
        }
    }

   
}
