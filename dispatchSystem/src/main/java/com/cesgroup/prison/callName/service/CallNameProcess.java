package com.cesgroup.prison.callName.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.prison.callNamesAl.bean.CallNameJsonRootBean;
import com.cesgroup.prison.callNamesAl.bean.CallNamesJsDataBean;
import com.cesgroup.prison.callNamesAl.entity.CallNamesMasterEntity;
import com.cesgroup.prison.callNamesAl.service.CallNamesMasterService;
import com.cesgroup.prison.common.cache.CallNamesPrisonerDtls;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.db.service.RedisCache;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.fm.util.MsgTypeConst;

@Service
public class CallNameProcess implements IMessageProcess {

	private final Logger logger = LoggerFactory.getLogger(CallNameProcess.class);

	@Resource
	private CallNamesMasterService service;

	@Override
	@Transactional
	public void processMessage(String cusNumber, JSONObject jsonObject) {

		MsgHeader msgHead = JSON.toJavaObject(jsonObject.getJSONObject("header"), MsgHeader.class);
		String msgType = msgHead.getMsgType();

		switch (msgType) {
		case MsgTypeConst.CALL_NAME_RETURN_02:
			logger.info("收到开始点名指令返回信息：" + jsonObject.toJSONString());
			beginCallNamesReturn(jsonObject);
			break;
		case MsgTypeConst.CALL_NAME_RETURN_03:
			logger.info("收到点名中返回信息：" + jsonObject.toJSONString());
			CallNamesIngReturn(jsonObject);
			break;
		case MsgTypeConst.CALL_NAME_RETURN_05:
			logger.info("收到结束点名返回信息：" + jsonObject.toJSONString());
			EndCallNamesReturn(jsonObject);
			break;
		default:
			break;
		}

	}

	/**
	 * @methodName: beginCallNamesReturn
	 * @Description: 发起点名返回
	 * @param obj
	 */
	private void beginCallNamesReturn(JSONObject obj) {
		CallNameJsonRootBean callNameJsonRootBean = JSONObject.toJavaObject(obj.getJSONObject("body"), CallNameJsonRootBean.class);
		List<CallNamesJsDataBean> data = callNameJsonRootBean.getData();
		int error = 0; // 记录监舍点名发起失败个数
		Map<String, String> jsStts = new HashMap<>();// 存放监舍点名点名状态 key：监舍号 ，value：点名状态 -1发起失败、0未开始点名、1点名进行中、2点名结束
		for (CallNamesJsDataBean bean : data) {
			String key = bean.getJyh() + "-" + bean.getLch() + "-" + bean.getJsh();
			String js = (String) RedisCache.getObject(CommonConstant.CALL_NAMES_REDIS, key);
			JSONObject jsonObject = JSONObject.parseObject(js);
			CallNamesJsDataBean callNamesJsDataBean = JSONObject.toJavaObject(jsonObject, CallNamesJsDataBean.class);
			if (bean.getState().equals("-1")) {
				error = error + 1;// 点名发起失败
			}
			callNamesJsDataBean.setState(bean.getState());
			jsStts.put(bean.getJsh(), bean.getState());
			RedisCache.putHash(CommonConstant.CALL_NAMES_REDIS, key, JSON.toJSONString(callNamesJsDataBean));
			logger.info("收到发起点名返回消息》》》修改 redis 中  key 为" + key + "的监舍信息：" + JSON.toJSONString(callNamesJsDataBean));
		}

		String key = data.get(0).getJyh() + "-" + data.get(0).getLch();
		String callNameRoot = (String) RedisCache.getObject(CommonConstant.CALL_NAMES_REDIS, key);
		if (StringUtils.isNotBlank(callNameRoot)) {
			JSONObject jsonObject = JSONObject.parseObject(callNameRoot);
			CallNameJsonRootBean bean = JSONObject.toJavaObject(jsonObject, CallNameJsonRootBean.class);

			if (data.size() == error) {
				// 未成功发起一间监舍点名，点名发起失败
				if (StringUtils.isNotBlank(bean.getMasterId())) {
					service.delete(bean.getMasterId());// 删除数据库主表数据
				}
				RedisCache.deleteHash(CommonConstant.CALL_NAMES_REDIS, key);// 删除缓存数据
				for (String jsh : jsStts.keySet()) {
					RedisCache.deleteHash(CommonConstant.CALL_NAMES_REDIS, data.get(0).getJyh() + "-" + data.get(0).getLch() + "-" + jsh);// 删除缓存监舍数据
				}
				return;
			}
			bean.setDoneTotal("0");
			bean.setJsTotal((data.size() - error) + "");// 参与点名监舍数量
			bean.setJsStts(jsStts);
			RedisCache.putHash(CommonConstant.CALL_NAMES_REDIS, key, JSON.toJSONString(bean));
		}
	}

	/**
	 * @methodName: EndCallNamesReturn
	 * @Description: 结束点名返回，成功收到结束点名消息后，保存点名结果
	 * @param obj
	 *            void
	 */
	private void EndCallNamesReturn(JSONObject obj) {
		CallNameJsonRootBean callNameJsonRootBean = JSONObject.toJavaObject(obj.getJSONObject("body"), CallNameJsonRootBean.class);
		List<CallNamesJsDataBean> data = callNameJsonRootBean.getData();
		for (CallNamesJsDataBean bean : data) {
			if (bean.getState().equals("2")) {

				String key = bean.getJyh() + "-" + bean.getLch();
				String callNameRoot = (String) RedisCache.getObject(CommonConstant.CALL_NAMES_REDIS, key);
				JSONObject jsonObject_ = JSONObject.parseObject(callNameRoot);
				CallNameJsonRootBean callNameRootbean = JSONObject.toJavaObject(jsonObject_, CallNameJsonRootBean.class);
				Map<String, String> jsStts = callNameRootbean.getJsStts();
				String doneTotal = (Integer.parseInt(callNameRootbean.getDoneTotal()) + 1) + "";// 已结束点名监舍数量

				String key_js = key + "-" + bean.getJsh();
				String js = (String) RedisCache.getObject(CommonConstant.CALL_NAMES_REDIS, key_js);
				JSONObject jsonObject = JSONObject.parseObject(js);
				CallNamesJsDataBean callNamesJsDataBean = JSONObject.toJavaObject(jsonObject, CallNamesJsDataBean.class);
				callNamesJsDataBean.setState("2");
				RedisCache.putHash(CommonConstant.CALL_NAMES_REDIS, key_js, JSON.toJSONString(callNamesJsDataBean));

				if (StringUtils.isNotBlank(jsStts.get(bean.getJsh()))) {
					jsStts.put(bean.getJsh(), bean.getState());
					callNameRootbean.setDoneTotal(doneTotal);
					RedisCache.putHash(CommonConstant.CALL_NAMES_REDIS, key, JSON.toJSONString(callNameRootbean));
					logger.info("修改 redis 中  key 为" + key + "的点名主表信息：" + JSON.toJSONString(callNameRootbean));
				}

				if (doneTotal.equals(callNameRootbean.getJsTotal())) {// 参与点名监舍 == 完成点名监舍，修改主表信息
					CallNamesMasterEntity callNamesMasterEntity = service.findById(callNameRootbean.getMasterId());
					if (callNamesMasterEntity != null) {
						callNamesMasterEntity.setCnmIsEnd("1");
						service.update(callNamesMasterEntity);// 点名结束
					}
				}

				try {
					service.saveResult(bean.getJyh(), bean.getLch(), bean.getJsh(), callNameRootbean.getUserID(), callNamesJsDataBean);// 保存点名结果
					logger.info("保存点名结果：" + JSON.toJSONString(callNamesJsDataBean));
				} catch (Exception e) {
					e.printStackTrace();
				}

				logger.info("修改 redis 中  key 为" + key + "的监舍信息：" + JSON.toJSONString(callNamesJsDataBean));

			}
		}

	}

	/**
	 * @methodName: CallNamesIngReturn
	 * @Description: 点名过程中收到点名消息，修改缓存中罪犯点名状态，如果点名完成，主动发起结束点名指令操作
	 * @param obj
	 *            void
	 */
	public void CallNamesIngReturn(JSONObject obj) {
		String zfbh = obj.getJSONObject("body").getString("zfbh");
		if (StringUtils.isNotBlank(zfbh)) {
			String[] zfbhs_ = zfbh.split(",");
			for (int i = 0; i < zfbhs_.length; i++) {
				String cachePrisonerJson = (String) RedisCache.getObject(CallNamesPrisonerDtls.tableName, zfbhs_[i]);
				if (StringUtils.isNotBlank(cachePrisonerJson)) {
					@SuppressWarnings("unchecked")
					Map<String, String> prisonerMap = (Map<String, String>) JSONObject.parse(cachePrisonerJson); // 获得点到罪犯的信息
					String key = prisonerMap.get("JYH") + "-" + prisonerMap.get("LCH") + "-" + prisonerMap.get("JSH"); // 拼接出罪犯所在监舍缓存信息key
					String js = (String) RedisCache.getObject(CommonConstant.CALL_NAMES_REDIS, key);
					JSONObject jsonObject = JSONObject.parseObject(js);
					CallNamesJsDataBean callNamesJsDataBean = JSONObject.toJavaObject(jsonObject, CallNamesJsDataBean.class);

					Map<String, String> zfbhs = callNamesJsDataBean.getZfbhs();
					if (zfbhs.containsKey(zfbhs_[i]) && zfbhs.get(zfbhs_[i]).equals("0")) {
						zfbhs.put(zfbhs_[i], "1"); // 点到罪犯 修改状态
						String arrivedTotal = (Integer.parseInt(callNamesJsDataBean.getArrivedTotal()) + 1) + "";
						callNamesJsDataBean.setArrivedTotal(arrivedTotal);
						RedisCache.putHash(CommonConstant.CALL_NAMES_REDIS, key, JSON.toJSONString(callNamesJsDataBean));

						if (arrivedTotal.equals(callNamesJsDataBean.getPeopleTotal())) {// 如果点到人数和实际人数一致，发起结束点名
							List<String> jshs = new ArrayList<>();
							jshs.add(callNamesJsDataBean.getJsh());
							try {
								service.EndCallNames(callNamesJsDataBean.getJyh(), callNamesJsDataBean.getLch(), jshs);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						logger.info("修改 redis 缓存表 " + CallNamesPrisonerDtls.tableName + "中  罪犯编号 为" + zfbhs_[i] + "的点名状态：" + JSON.toJSONString(callNamesJsDataBean));
					}
				}
			}

		}
	}

}
