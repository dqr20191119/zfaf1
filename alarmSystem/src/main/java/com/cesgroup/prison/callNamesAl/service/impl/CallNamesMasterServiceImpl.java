package com.cesgroup.prison.callNamesAl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.util.DateUtil;
import com.cesgroup.framework.util.MsgIdUtil;
import com.cesgroup.frm.net.netty.bean.MsgHeader;
import com.cesgroup.prison.callNamesAl.bean.CallNameJsonRootBean;
import com.cesgroup.prison.callNamesAl.bean.CallNamesJsDataBean;
import com.cesgroup.prison.callNamesAl.dao.CallNamesAreaDtlsMapper;
import com.cesgroup.prison.callNamesAl.dao.CallNamesMasterMapper;
import com.cesgroup.prison.callNamesAl.dao.CallNamesResultMapper;
import com.cesgroup.prison.callNamesAl.entity.CallNamesAreaDtlsEntity;
import com.cesgroup.prison.callNamesAl.entity.CallNamesMasterEntity;
import com.cesgroup.prison.callNamesAl.entity.CallNamesResultEntity;
import com.cesgroup.prison.callNamesAl.service.CallNamesMasterService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.cache.CallNamesPrisonerDtls;
import com.cesgroup.prison.common.cache.DvcCameraBaseDtls;
import com.cesgroup.prison.common.cache.DvcVideoDeviceInfo;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.constants.socket.MsgUtils;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.db.service.RedisCache;
import com.cesgroup.prison.netamq.service.MqMessageSender;

@Service
@Transactional
public class CallNamesMasterServiceImpl extends BaseDaoService<CallNamesMasterEntity, String, CallNamesMasterMapper> implements CallNamesMasterService {

	@Resource
	private MqMessageSender mqMessageSender;

	@Autowired
	private CallNamesMasterMapper mapper;

	@Autowired
	private CallNamesAreaDtlsMapper areaDtlsMapper;

	@Autowired
	private CallNamesResultMapper resultMapper;

	@Override
	public void deleteByIds(List<String> ids) {
		mapper.deleteByIds(ids);
	}

	@Override
	public void updateInfo(CallNamesMasterEntity entity) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setCnmUpdtTime(date);
			entity.setCnmUpdtUserId(userId);
			map.put("callNamesMasterEntity", entity);
		}
		mapper.updateInfo(map);
	}

	@Override
	public Page<Map<String, Object>> listAll(CallNamesMasterEntity entity, Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("callNamesMasterEntity", entity);
		}
		return mapper.listAll(map, pageable);
	}

	@Override
	public CallNamesMasterEntity findById(String id) {
		return mapper.selectOne(id);
	}

	@Override
	public void addInfo(CallNamesMasterEntity entity) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		String userId = userBean.getUserId();
		Date date = new Date();
		entity.setCnmCusNumber(userBean.getCusNumber());
		entity.setCnmUpdtTime(date);
		entity.setCnmUpdtUserId(userId);
		entity.setCnmCrteTime(date);
		entity.setCnmCrteUserId(userId);
		mapper.insert(entity);
	}

	@Override
	public List<Map<String, Object>> findForTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userLev = request.getParameter("userLev");
		String cusNumber = request.getParameter("cusNumber");
		String id = request.getParameter("id");

		List<Map<String, Object>> list = null;
		if (StringUtils.isBlank(id)) {
			if ("1".equals(userLev)) {
				list = new ArrayList<>();
				List<OrgEntity> jyList = AuthSystemFacade.getAllJyInfo();
				for (OrgEntity orgEntity : jyList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("pId", null);
					map.put("id", orgEntity.getOrgKey());
					map.put("name", orgEntity.getOrgName());
					map.put("jyh", cusNumber);
					map.put("node_lev", "1");
					map.put("isParent", true);
					list.add(map);
					List<OrgEntity> jqList = AuthSystemFacade.getAllJqInfoByJyKey(orgEntity.getOrgKey());
					for (OrgEntity orgEntity2 : jqList) {
						Map<String, Object> map2 = new HashMap<String, Object>();
						map2.put("pId", orgEntity.getOrgKey());
						map2.put("id", orgEntity2.getOrgKey());
						map2.put("name", orgEntity2.getOrgName());
						map2.put("jyh", cusNumber);
						map2.put("node_lev", "2");
						map2.put("isParent", true);
						list.add(map2);
					}
				}
			} else if ("2".equals(userLev)) {
				list = new ArrayList<>();
				List<OrgEntity> jyList = AuthSystemFacade.getAllJyInfo();
				for (OrgEntity orgEntity : jyList) {
					if (cusNumber.equals(orgEntity.getOrgKey())) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("pId", null);
						map.put("id", orgEntity.getOrgKey());
						map.put("name", orgEntity.getOrgName());
						map.put("jyh", cusNumber);
						map.put("isParent", true);
						map.put("node_lev", "1");
						map.put("open", true);
						list.add(map);
						List<OrgEntity> jqList = AuthSystemFacade.getAllJqInfoByJyKey(cusNumber);
						for (OrgEntity orgEntity2 : jqList) {
							Map<String, Object> map2 = new HashMap<String, Object>();
							map2.put("pId", orgEntity.getOrgKey());
							map2.put("id", orgEntity2.getOrgKey());
							map2.put("name", orgEntity2.getOrgName());
							map2.put("jyh", cusNumber);
							map2.put("node_lev", "2");
							map2.put("isParent", true);
							list.add(map2);
						}
					}
				}

			}
		} else if (StringUtils.isNotBlank(id) || "3".equals(userLev)) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			// paramMap.put("cusNumber", cusNumber);
			paramMap.put("dprtmntId", id);
			list = mapper.findAreaDepartment(paramMap);
			if (!list.isEmpty()) {
				for (Map<String, Object> areaMap : list) {
					areaMap.put("isParent", false);
					areaMap.put("open", false);
					areaMap.put("jyh", cusNumber);
					areaMap.put("node_lev", "3");
				}
			}

		}
		return list;
	}

	@Override
	public List<Map<String, Object>> findJsAndZfsByLc(HttpServletRequest request, HttpServletResponse response) {
		String lch = request.getParameter("lch");
		String cusNumber = request.getParameter("cusNumber");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("lch", lch);
		map.put("cusNumber", cusNumber);
		return mapper.findJsAndZfsByLc(map);
	}

	@Override
	public String beginCallNames(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String msg = "";
		List<Map<String, Object>> jsData = this.findJsAndZfsByLc(request, response);

		if (!jsData.isEmpty()) {
			List<CallNamesJsDataBean> jsInfos = new LinkedList<>();// ??????????????????
			List<String> jsList = new ArrayList<>(); // ??????????????????????????????????????????
			for (Map<String, Object> map : jsData) {
				String camera = (String) map.get("ZSXT");
				String lch = (String) map.get("LCH");
				String jyh = (String) map.get("JYH");
				String jsh = (String) map.get("JSH");
				String zfs = (String) map.get("ZFS");// ????????????
				if (StringUtils.isNotBlank(camera) && !zfs.equals("0")) {
					CallNamesJsDataBean callNamesJsDataBean = new CallNamesJsDataBean();
					callNamesJsDataBean.setCamera(getCamera(camera));
					callNamesJsDataBean.setArrivedTotal("0");
					callNamesJsDataBean.setPeopleTotal(zfs);
					callNamesJsDataBean.setState("0");
					callNamesJsDataBean.setJsh(jsh);
					callNamesJsDataBean.setJyh(jyh);
					callNamesJsDataBean.setLch(lch);
					jsInfos.add(callNamesJsDataBean);
				} else {
					jsList.add(jsh);
				}
			}
			if (!jsInfos.isEmpty()) {
				String cusNumber = request.getParameter("cusNumber");
				String lch = request.getParameter("lch");
				UserBean userBean = AuthSystemFacade.getLoginUserInfo();

				CallNamesMasterEntity entity = new CallNamesMasterEntity(); // ?????????????????????
				entity.setCnmDprtmntId(userBean.getDprtmntCode());
				entity.setCnmIsEnd("0");
				entity.setCnmStartTime(new Date());
				entity.setCnmTimeLag(request.getParameter("duration"));
				entity.setCnmLch(lch);
				this.addInfo(entity);

				CallNameJsonRootBean callNameJsonRootBean = new CallNameJsonRootBean();
				callNameJsonRootBean.setAction("1");// ????????????
				callNameJsonRootBean.setDepartment(userBean.getDprtmntCode());// ???????????????????????????
				callNameJsonRootBean.setDuration(request.getParameter("duration"));// ????????????
				callNameJsonRootBean.setUserID(userBean.getUserId()); // ???????????????
				callNameJsonRootBean.setMasterId(entity.getId());
				RedisCache.putHash(CommonConstant.CALL_NAMES_REDIS, cusNumber + "-" + lch, JSON.toJSONString(callNameJsonRootBean));// ???????????????????????????

				callNameJsonRootBean.setMasterId(null);
				callNameJsonRootBean.setData(jsInfos); // ?????????????????????
				String openStr = createMessage(cusNumber, callNameJsonRootBean, MsgUtils.MSG_TYPE_CALL_NAME_001);

				mqMessageSender.sendCallNamesMessage(openStr, cusNumber, MsgUtils.MSG_TYPE_CALL_NAME_001);

				saveJsInfoForCache(jsInfos, entity);// ???????????????????????????
				msg = "??????????????????????????????";
				if (!jsList.isEmpty()) {
					msg = msg + ",??????????????????????????????????????????????????????" + String.join(",", jsList);
				}
			}
		} else {
			msg = "????????????????????????????????????????????????????????????";
		}

		return msg;
	}

	/**
	 * @methodName: saveJsInfoForCache
	 * @Description: ???????????????????????????
	 * @param jsInfos
	 * @param entity void
	 * @throws
	 */
	private void saveJsInfoForCache(List<CallNamesJsDataBean> jsInfos, CallNamesMasterEntity entity) {
		for (int i = 0; i < jsInfos.size(); i++) {
			CallNamesJsDataBean bean = jsInfos.get(i);
			String lch = bean.getLch();
			String jyh = bean.getJyh();
			String jsh = bean.getJsh();

			List<String> zfbhs = getPrisonerIndcByJs(jyh, lch, jsh);// ???????????????????????????
			Map<String, String> zfMap = new HashMap<>();
			for (String zfbh : zfbhs) {
				zfMap.put(zfbh, "0"); // key ???????????? value ???????????? ??? 0????????? 1 ??????
			}
			bean.setZfbhs(zfMap);
			bean.setMatserId(entity.getId());
			RedisCache.putHash(CommonConstant.CALL_NAMES_REDIS, jyh + "-" + lch + "-" + jsh, JSON.toJSONString(bean));
		}
	}

	private String createMessage(String cusNumber, CallNameJsonRootBean callNameJsonRootBean, String msgType) {
		MsgHeader msgHeader = new MsgHeader();
		msgHeader.setCusNumber(cusNumber);// ??????????????????
		msgHeader.setMsgID(MsgIdUtil.getMsgSeq(""));
		msgHeader.setMsgType(msgType);
		msgHeader.setLength(0);
		msgHeader.setSender("SERVER");
		msgHeader.setRecevier("CALLNAMES");
		msgHeader.setSendTime(DateUtil.getDateString(new Date(), DateUtil.sdf));
		JSONObject out = new JSONObject();
		out.put("header", msgHeader);
		out.put("body", callNameJsonRootBean);
		return out.toJSONString();
	}

	/**
	 * @methodName: getCamera
	 * @Description: ???????????????????????? ??? ????????? nvr??????
	 * @param cameraId
	 * @return Map<String,Object>
	 * @throws
	 */
	private Map<String, Object> getCamera(String cameraId) {
		Map<String, Object> cameraBase = null;
		Map<String, Object> deviceBase = null;

		String[] fileds = null;

		fileds = new String[] { DvcCameraBaseDtls.CBD_NAME, DvcCameraBaseDtls.CBD_DVR_IDNTY, DvcCameraBaseDtls.CBD_DVR_CHNNL_IDNTY, DvcCameraBaseDtls.CBD_VIDEO_PLAY_INDC, DvcCameraBaseDtls.CBD_IP_ADDRS, DvcCameraBaseDtls.CBD_PORT, DvcCameraBaseDtls.CBD_USER_NAME, DvcCameraBaseDtls.CBD_USER_PASSWORD, DvcCameraBaseDtls.CBD_CHNNL_IDNTY, DvcCameraBaseDtls.CBD_BRAND_NAME, DvcCameraBaseDtls.ID };
		// ???????????????????????????
		cameraBase = RedisCache.getHashMap(DvcCameraBaseDtls.tableName, cameraId, fileds);

		// ?????????????????????????????????
		deviceBase = RedisCache.getHashMap(DvcVideoDeviceInfo.tableName, cameraBase.get(DvcCameraBaseDtls.CBD_DVR_IDNTY));
		cameraBase.put("deviceBase", deviceBase);
		return cameraBase;
	}

	@Override
	public List<String> getPrisonerIndcByJs(String jyh, String lch, String jsh) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jsh", jsh);
		map.put("lch", lch);
		map.put("jyh", jyh);
		return mapper.getPrisonerIndcByJs(map);
	}

	@Override
	public int isCallNamesIng(CallNamesMasterEntity entity) {
		List<CallNamesMasterEntity> list = mapper.selectByEntity(entity);// ?????????????????????????????????????????????
		if (list.size() == 1) {
			String key = entity.getCnmCusNumber() + "-" + entity.getCnmLch();
			String callNameRoot = (String) RedisCache.getObject(CommonConstant.CALL_NAMES_REDIS, key);
			if (StringUtils.isNotBlank(callNameRoot)) {
				JSONObject jsonObject = JSONObject.parseObject(callNameRoot);
				CallNameJsonRootBean bean = JSONObject.toJavaObject(jsonObject, CallNameJsonRootBean.class);
				if (bean.getJsTotal() != null && !"0".equals(bean.getJsTotal())) {
					return 1; // ???????????????
				}
				if (bean.getJsTotal() != null && bean.getDoneTotal() != null && bean.getJsTotal().equals(bean.getDoneTotal())) {
					CallNamesMasterEntity callNamesMasterEntity = list.get(0);
					callNamesMasterEntity.setCnmIsEnd("1");
					mapper.update(callNamesMasterEntity);
					RedisCache.deleteHash(CommonConstant.CALL_NAMES_REDIS, key);
					Map<String, String> jsStts = bean.getJsStts();
					for (String jsh : jsStts.keySet()) {
						RedisCache.deleteHash(CommonConstant.CALL_NAMES_REDIS, entity.getCnmCusNumber() + "-" + entity.getCnmLch() + "-" + jsh);
					}
				}
			} else {
				// ?????????????????????????????????????????????????????????,???????????????
				mapper.deleteByEntity(list.get(0));
			}
		}
		return 0;
	}

	@Override
	public String EndCallNames(String jyh, String lch, List<String> jshs) throws Exception {
		String msg = "??????????????????????????????";
		if (StringUtils.isNoneBlank(jyh, lch) && !jshs.isEmpty()) {

			List<CallNamesJsDataBean> dataBeans = new ArrayList<>();
			for (String jsh : jshs) {// ??????????????????
				String key = jyh + "-" + lch + "-" + jsh;
				String js = (String) RedisCache.getObject(CommonConstant.CALL_NAMES_REDIS, key);
				if (StringUtils.isNotBlank(js)) {
					JSONObject jsonObject = JSONObject.parseObject(js);
					CallNamesJsDataBean callNamesJsDataBean = JSONObject.toJavaObject(jsonObject, CallNamesJsDataBean.class);
					String cameraId = (String) callNamesJsDataBean.getCamera().get("ID");
					callNamesJsDataBean.setCamera(getCamera(cameraId));
					callNamesJsDataBean.setZfbhs(null);// ?????????????????????????????????null
					dataBeans.add(callNamesJsDataBean);
				}

			}

			if (!dataBeans.isEmpty()) {// ?????????????????????
				String key = jyh + "-" + lch;
				String callNameRoot = (String) RedisCache.getObject(CommonConstant.CALL_NAMES_REDIS, key);
				if (StringUtils.isNotBlank(callNameRoot)) {
					JSONObject jsonObject = JSONObject.parseObject(callNameRoot);
					CallNameJsonRootBean bean = JSONObject.toJavaObject(jsonObject, CallNameJsonRootBean.class);
					bean.setAction("2");// ????????????
					bean.setData(dataBeans);
					bean.setJsTotal(null);// ?????????????????????????????????null
					bean.setJsStts(null);// ?????????????????????????????????null
					bean.setDoneTotal(null);// ?????????????????????????????????null
					String openStr = createMessage(jyh, bean, MsgUtils.MSG_TYPE_CALL_NAME_004);
					System.err.println("???????????????????????????????????????" + openStr);
					mqMessageSender.sendCallNamesMessage(openStr, jyh, MsgUtils.MSG_TYPE_CALL_NAME_004);
					msg = "??????????????????????????????";
				}
			}
		}
		return msg;
	}

	/*
	 * ?????? Javadoc???
	 * 
	 * @see com.cesgroup.prison.callNamesAl.service.CallNamesMasterService#
	 * getCallNamesDtlsByJs(java.lang.String, java.lang.String, java.util.List)
	 * ??????3s?????????????????????
	 */
	@Override
	public List<CallNamesJsDataBean> getCallNamesDtlsByJs(String jyh, String lch, List<String> jshs) throws Exception {

		List<CallNamesJsDataBean> beans = new ArrayList<>();

		if (StringUtils.isNoneBlank(jyh, lch)) {
			if (!jshs.isEmpty()) {
				for (String jsh : jshs) {
					String key = jyh + "-" + lch + "-" + jsh;
					String js = (String) RedisCache.getObject(CommonConstant.CALL_NAMES_REDIS, key);
					if (StringUtils.isNotBlank(js)) {
						JSONObject jsonObject = JSONObject.parseObject(js);
						CallNamesJsDataBean callNamesJsDataBean = JSONObject.toJavaObject(jsonObject, CallNamesJsDataBean.class);
						String cameraId = (String) callNamesJsDataBean.getCamera().get("ID");
						callNamesJsDataBean.setCamera(getCamera(cameraId));
						beans.add(callNamesJsDataBean);

						/*
						 * if (callNamesJsDataBean.getState().equals("-1")) {
						 * RedisCache.deleteHash(CommonConstant.CALL_NAMES_REDIS, key); // ???????????????????????????????????????
						 * }
						 */
					}
				}
			} /*
				 * else {
				 * String key = jyh + "-" + lch;
				 * String callNameRoot = (String)
				 * RedisCache.getObject(CommonConstant.CALL_NAMES_REDIS, key);
				 * if (StringUtils.isNotBlank(callNameRoot)) {
				 * JSONObject jsonObject = JSONObject.parseObject(callNameRoot);
				 * CallNameJsonRootBean bean = JSONObject.toJavaObject(jsonObject,
				 * CallNameJsonRootBean.class);
				 * CallNamesMasterEntity callNamesMasterEntity =
				 * mapper.selectOne(bean.getMasterId());
				 * callNamesMasterEntity.setCnmIsEnd("1");
				 * this.updateInfo(callNamesMasterEntity); // ??????????????????
				 * RedisCache.deleteHash(CommonConstant.CALL_NAMES_REDIS, key); // ????????????
				 * 
				 * }
				 * 
				 * }
				 */

		}
		return beans;
	}

	@Override
	public CallNamesJsDataBean getCallNamesPrisonerDtlsByJs(String jyh, String lch, String jsh) {
		CallNamesJsDataBean callNamesJsDataBean = null;
		String key = jyh + "-" + lch + "-" + jsh;
		String js = (String) RedisCache.getObject(CommonConstant.CALL_NAMES_REDIS, key);
		if (StringUtils.isNotBlank(js)) {
			JSONObject jsonObject = JSONObject.parseObject(js);
			callNamesJsDataBean = JSONObject.toJavaObject(jsonObject, CallNamesJsDataBean.class);
			String cameraId = (String) callNamesJsDataBean.getCamera().get("ID");
			callNamesJsDataBean.setCamera(getCamera(cameraId));
		}
		return callNamesJsDataBean;
	}

	@Override
	public List<Map<String, Object>> getPrisonerInfoByJsFromCache(String jyh, String lch, String jsh) {
		List<Map<String, Object>> list = new ArrayList<>();
		String key = jyh + "-" + lch + "-" + jsh;
		String js = (String) RedisCache.getObject(CommonConstant.CALL_NAMES_REDIS, key);
		if (StringUtils.isNotBlank(js)) {
			JSONObject jsonObject = JSONObject.parseObject(js);
			CallNamesJsDataBean callNamesJsDataBean = JSONObject.toJavaObject(jsonObject, CallNamesJsDataBean.class);
			Map<String, String> zfbhs = callNamesJsDataBean.getZfbhs();
			for (String zfbh : zfbhs.keySet()) {
				String cachePrisonerJson = (String) RedisCache.getObject(CallNamesPrisonerDtls.tableName, zfbh);
				@SuppressWarnings("unchecked")
				Map<String, Object> map = (Map<String, Object>) JSONObject.parse(cachePrisonerJson);
				list.add(map);
			}
		}
		return list;
	}

	@Override
	public CallNameJsonRootBean getCallNamesDtlsByLc(String jyh, String lch) {
		CallNameJsonRootBean bean = null;
		String key = jyh + "-" + lch;
		String callNameRoot = (String) RedisCache.getObject(CommonConstant.CALL_NAMES_REDIS, key);
		if (StringUtils.isNotBlank(callNameRoot)) {
			JSONObject jsonObject = JSONObject.parseObject(callNameRoot);
			bean = JSONObject.toJavaObject(jsonObject, CallNameJsonRootBean.class);
		}
		return bean;
	}

	@Override
	public Page<Map<String, Object>> listAllForJsInfo(CallNamesAreaDtlsEntity entity, Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("callNamesAreaDtlsEntity", entity);
		}
		return areaDtlsMapper.listAll(map, pageable);
	}

	@Override
	public Page<Map<String, Object>> listAllForZfInfo(CallNamesResultEntity entity, Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("callNamesResultEntity", entity);
		}
		return resultMapper.listAll(map, pageable);
	}

	@Override
	public void saveResult(String jyh, String lch, String jsh, String userId, CallNamesJsDataBean callNamesJsDataBean) throws Exception {
		Date date = new Date();
		// ????????????
		CallNamesAreaDtlsEntity areaDtlsEntity = new CallNamesAreaDtlsEntity();
		areaDtlsEntity.setNadCrteTime(date);
		areaDtlsEntity.setNadCrteUserId(userId);
		areaDtlsEntity.setNadUpdtTime(date);
		areaDtlsEntity.setNadUpdtUserId(userId);
		areaDtlsEntity.setNadCusNumber(jyh);
		areaDtlsEntity.setNadLch(lch);
		areaDtlsEntity.setNadJsh(jsh);
		areaDtlsEntity.setNadMasterId(callNamesJsDataBean.getMatserId());
		areaDtlsEntity.setNadPrisonerCalledTotal(callNamesJsDataBean.getArrivedTotal());
		areaDtlsEntity.setNadPrisonerTotal(callNamesJsDataBean.getPeopleTotal());
		areaDtlsMapper.insert(areaDtlsEntity);

		List<CallNamesResultEntity> resultEntities = new ArrayList<>();
		Map<String, String> zfbhs = callNamesJsDataBean.getZfbhs();
		for (String zfbh : zfbhs.keySet()) {
			CallNamesResultEntity callNamesResultEntity = new CallNamesResultEntity();
			callNamesResultEntity.setCnrCrteTime(date);
			callNamesResultEntity.setCnrCrteUserId(userId);
			callNamesResultEntity.setCnrCusNumber(jyh);
			callNamesResultEntity.setCnrIsCalled(zfbhs.get(zfbh));
			callNamesResultEntity.setCnrMasterId(callNamesJsDataBean.getMatserId());
			callNamesResultEntity.setCnrPrisonerIndc(zfbh);
			callNamesResultEntity.setCnrUpdtTime(date);
			callNamesResultEntity.setCnrUpdtUserId(userId);
			callNamesResultEntity.setCnrNadId(areaDtlsEntity.getId());
			resultEntities.add(callNamesResultEntity);
		}
		resultMapper.insert(resultEntities);
	}

}
