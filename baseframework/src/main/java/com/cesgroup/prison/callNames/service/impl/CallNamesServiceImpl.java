package com.cesgroup.prison.callNames.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.callNames.dao.CallNamesDoneMapper;
import com.cesgroup.prison.callNames.dao.CallNamesMapper;
import com.cesgroup.prison.callNames.dao.CallNamesUndoneMapper;
import com.cesgroup.prison.callNames.entity.CallNamesDoneEntity;
import com.cesgroup.prison.callNames.entity.CallNamesRecordEntity;
import com.cesgroup.prison.callNames.entity.CallNamesUndoneEntity;
import com.cesgroup.prison.callNames.service.CallNamesService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jfsb.dao.CameraMapper;
import com.cesgroup.prison.jfsb.entity.Camera;

@Service
@Transactional
public class CallNamesServiceImpl extends BaseDaoService<CallNamesRecordEntity, String, CallNamesMapper>
		implements CallNamesService {

	@Autowired
	private CallNamesMapper callNamesMapper;

	@Autowired
	private CallNamesDoneMapper callNamesDoneMapper;

	@Autowired
	private CallNamesUndoneMapper callNamesUndoneMapper;

	@Autowired
	private CameraMapper cameraMapper;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<Map<String, Object>> findPrisonerNumForCallNames(Map<String, Object> map) {
		if (map.get("para") != null) {
			if (map.get("para").equals("1")) {
				List<Map<String, Object>> list = null;
				try {
					List<OrgEntity> orgList = AuthSystemFacade
							.getAllChildrenOrgInfoByOrgKey(String.valueOf(map.get("cusNumber")));
					list = callNamesMapper.findPrisonerNumForCallNamesByCusNumber(map);
					for (int i = 0; i < list.size(); i++) {
						for (OrgEntity orgEntity : orgList) {
							if (orgEntity.getOrgKey().equals(list.get(i).get("DEMPTID"))) {
								list.get(i).put("DEMPT", orgEntity.getOrgName());
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				return list;
			}
			if (map.get("para").equals("2")) {
				return callNamesMapper.findPrisonerNumForCallNamesByDempt(map);

			}
			if (map.get("para").equals("3")) {
				return callNamesMapper.findPrisonerNumForCallNamesByArea(map);
			}
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> findForTree(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
					map.put("node_lev", "1");
					map.put("isParent", true);
					list.add(map);
					List<OrgEntity> jqList = AuthSystemFacade.getAllJqInfoByJyKey(orgEntity.getOrgKey());
					for (OrgEntity orgEntity2 : jqList) {
						Map<String, Object> map2 = new HashMap<String, Object>();
						map2.put("pId", orgEntity.getOrgKey());
						map2.put("id", orgEntity2.getOrgKey());
						map2.put("name", orgEntity2.getOrgName());
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
			list = callNamesMapper.findAreaDepartment(paramMap);
			if (!list.isEmpty()) {
				for (Map<String, Object> areaMap : list) {
					areaMap.put("isParent", false);
					areaMap.put("open", false);
					areaMap.put("node_lev", "3");
				}
			}

		}
		System.out.println(list);
		return list;
	}

	@Override
	public Page<Map<String, Object>> listAll(CallNamesRecordEntity callNamesRecordEntity, Pageable pageable) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (callNamesRecordEntity != null) {
			map.put("callNamesRecordEntity", callNamesRecordEntity);
		}
		return callNamesMapper.listAll(map, pageable);
	}

	@Override
	public void updateInfo(CallNamesRecordEntity callNamesRecordEntity) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (callNamesRecordEntity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			callNamesRecordEntity.setCnrUpdtTime(date);
			callNamesRecordEntity.setCnrUpdtUserId(userId);
			map.put("callNamesRecordEntity", callNamesRecordEntity);
		}
		callNamesMapper.updateInfo(map);
	}

	@Override
	public void addInfo(CallNamesRecordEntity callNamesRecordEntity) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		String userId = userBean.getUserId();
		Date date = new Date();
		callNamesRecordEntity.setCnrCusNumber(userBean.getCusNumber());
		callNamesRecordEntity.setCnrCrteTime(date);
		callNamesRecordEntity.setCnrCrteUserId(userId);
		callNamesRecordEntity.setCnrUpdtTime(date);
		callNamesRecordEntity.setCnrUpdtUserId(userId);
		callNamesMapper.insert(callNamesRecordEntity);
	}

	/**
	* @methodName: send
	* @param giveMoneyUrl
	* @param params
	* @param type 1、 post 请求 2、get请求
	* @return Map<String,Object>
	* @throws  
	*/
	public Map<String, Object> send(String giveMoneyUrl, String type) {
		giveMoneyUrl = "http://33.168.120.23:8090/" + giveMoneyUrl;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
		String jsonString = null;
		if (type.equals("1")) {
			jsonString = restTemplate.postForObject(giveMoneyUrl, requestEntity, String.class);
		}
		if (type.equals("2")) {
			jsonString = restTemplate.getForObject(giveMoneyUrl, String.class);
		}
		return JSON.parseObject(jsonString);
	}

	/*
	 * 1.开始点名： 名称:cfAdmin/prison/beginRollcall.do
	 */
	@Override
	public Map<String, Object> beginRollcall(CallNamesRecordEntity callNamesRecordEntity) throws Exception {
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		callNamesRecordEntity.setId(id);
		Map<String, Object> map = this.send("cfAdmin/prison/beginRollcall.do?rollcallId=" + id + "&duration="
				+ callNamesRecordEntity.getCnrTimeLag(), "1");
		if ((int) map.get("status") == 0) {
			callNamesRecordEntity.setCnrStartTime(new Date());
			callNamesRecordEntity.setCnrIsDone("0");
			this.addInfo(callNamesRecordEntity);
		}
		return map;
	}

	/*
	 * 点名中查询： 名称:cfAdmin/prison/getNumber.do Method:get
	 */
	@Override
	public Map<String, Object> getNumber(String rollcallId, String demptId) {
		String url = "cfAdmin/prison/getNumber.do?rollcallId=" + rollcallId + "&demptId=" + demptId;
		return this.send(url, "2");
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getEndRollcallList(String rollcallId, String demptId, String floorId, String cellId) {
		String url = "cfAdmin/prison/getEndRollcallList.do?rollcallId=" + rollcallId + "&demptId=" + demptId
				+ "&floorId=" + floorId;
		if (StringUtils.isNotBlank(cellId)) {
			url = url + "&cellId=" + cellId;
		}
		Map<String, Object> map = this.send(url, "2");

		if ((int) map.get("status") == 0) {
			Map<String, Object> result = (Map<String, Object>) map.get("result");
			Camera entity = new Camera();
			entity.setCbdAreaId(floorId);
			entity.setCbdAddrs(cellId);
			List<Camera> cameras = cameraMapper.selectByEntity(entity);
			if(!cameras.isEmpty()) {
				List<String> cameraIds = new ArrayList<>();
				for (Camera camera : cameras) {
					cameraIds.add(camera.getId());
				}
				result.put("cameraIds", cameraIds);
			}
		

		}

		return map;
	}

	@Override
	public List<Map<String, Object>> getJSPrisonerInfo(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String jsh = request.getParameter("jsh");
		if (StringUtils.isNotBlank(jsh)) {
			map.put("jsh", jsh);
		}
		String lch = request.getParameter("lch");
		if (StringUtils.isNotBlank(lch)) {
			map.put("lch", lch);
		}
		String cusNumber = request.getParameter("cusNumber");
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber);
		}
		return callNamesMapper.getJSPrisonerInfo(map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public AjaxMessage saveEndRollcallList(String rollcallId) {

		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			CallNamesRecordEntity callNamesRecordEntity = callNamesMapper.selectOne(rollcallId);
			if (StringUtils.isBlank(callNamesRecordEntity.getCnrCallSum())) {// 已点名数如果存在，不进行下列操作
				Map<String, Object> map = this.getPersonDetail(rollcallId);
				if ((int) map.get("status") == 0) {
					Map<String, Object> result = (Map<String, Object>) map.get("result");
					UserBean userBean = AuthSystemFacade.getLoginUserInfo();
					String userId = userBean.getUserId();
					Date date = new Date();
					List<Map<String, Object>> arrivedList = (List<Map<String, Object>>) result.get("arrivedList");
					if (arrivedList.size() > 0) {
						for (Map<String, Object> arrived : arrivedList) {
							CallNamesDoneEntity callNamesDoneEntity = new CallNamesDoneEntity();
							callNamesDoneEntity.setCndCusNumber(userBean.getCusNumber());
							callNamesDoneEntity.setCndCrteTime(date);
							callNamesDoneEntity.setCndCrteUserId(userId);
							callNamesDoneEntity.setCndDprtmnt(String.valueOf(arrived.get("demptName")));
							callNamesDoneEntity.setCndDprtmntId(String.valueOf(arrived.get("demptId")));
							callNamesDoneEntity.setCndPrisonerIndc(String.valueOf(arrived.get("criminalNumber")));
							callNamesDoneEntity.setCndPrisonerName(String.valueOf(arrived.get("name")));
							callNamesDoneEntity.setCndRecordId(rollcallId);
							callNamesDoneEntity.setCndRollMark(String.valueOf(arrived.get("rollMark")));
							callNamesDoneEntity.setCndRollTime(String.valueOf(arrived.get("rollTime")));
							callNamesDoneEntity.setCndJsh(String.valueOf(arrived.get("cellId")));
							callNamesDoneEntity.setCndJs(String.valueOf(arrived.get("cellName")));
							callNamesDoneEntity.setCndLch(String.valueOf(arrived.get("floorId")));
							callNamesDoneEntity.setCndLc(String.valueOf(arrived.get("floorName")));
							callNamesDoneMapper.insert(callNamesDoneEntity);
						}
					}

					List<Map<String, Object>> notArrivedList = (List<Map<String, Object>>) result.get("notArrivedList");// 未点到罪犯集合
					if (notArrivedList.size() > 0) {
						for (Map<String, Object> notArrived : notArrivedList) {
							CallNamesUndoneEntity callNamesUndoneEntity = new CallNamesUndoneEntity();
							callNamesUndoneEntity.setCnuCrteTime(date);
							callNamesUndoneEntity.setCnuCrteUserId(userId);
							callNamesUndoneEntity.setCnuCusNumber(userBean.getCusNumber());
							callNamesUndoneEntity.setCnuPrisonerIndc(String.valueOf(notArrived.get("criminalNumber")));
							callNamesUndoneEntity.setCnuPrisonerName(String.valueOf(notArrived.get("name")));
							callNamesUndoneEntity.setCnuRecordId(rollcallId);
							callNamesUndoneEntity.setCnuDprtmntId(String.valueOf(notArrived.get("demptId")));
							callNamesUndoneEntity.setCnuDprtmnt(String.valueOf(notArrived.get("demptName")));
							callNamesUndoneEntity.setCnuLch(String.valueOf(notArrived.get("floorId")));
							callNamesUndoneEntity.setCnuLc(String.valueOf(notArrived.get("floorName")));
							callNamesUndoneEntity.setCnuJsh(String.valueOf(notArrived.get("cellId")));
							callNamesUndoneEntity.setCnuJs(String.valueOf(notArrived.get("cellName")));
							callNamesUndoneMapper.insert(callNamesUndoneEntity);
						}
					}

					callNamesRecordEntity.setCnrCallSum(arrivedList.size() + "");
					callNamesRecordEntity.setCnrPrisonerSum((arrivedList.size() + notArrivedList.size()) + "");
					callNamesRecordEntity.setCnrEndTime(new Date());// 更新结束时间
					callNamesRecordEntity.setCnrIsDone("1");
					this.updateInfo(callNamesRecordEntity);
					flag = true;
					obj = "点名记录保存成功";
				} else {
					flag = false;
					obj = "点名记录保存:" + map.get("message");
				}
			}
		} catch (Exception e) {
			flag = false;
			obj = "点名记录保存发生异常";
		}
		ajaxMessage.setMsg(String.valueOf(obj));
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;

	}

	@Override
	public Page<Map<String, Object>> listAllForDone(CallNamesDoneEntity callNamesDoneEntity, Pageable pageable) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (callNamesDoneEntity != null) {
			map.put("callNamesDoneEntity", callNamesDoneEntity);
		}
		return callNamesDoneMapper.listAll(map, pageable);
	}

	@Override
	public Page<Map<String, Object>> listAllForUndone(CallNamesUndoneEntity callNamesUndoneEntity, Pageable pageable) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (callNamesUndoneEntity != null) {
			map.put("callNamesUndoneEntity", callNamesUndoneEntity);
		}
		return callNamesUndoneMapper.listAll(map, pageable);
	}

	@Override
	public Map<String, Object> endIngRollcall(CallNamesRecordEntity callNamesRecordEntity) {
		String url = "cfAdmin/prison/endIngRollcall.do?rollcallId=" + callNamesRecordEntity.getId();
		return this.send(url, "1");
	}

	@Override
	public Map<String, Object> getPersonDetail(String rollcallId) {
		String url = "cfAdmin/prison/getPersonDetail.do?rollcallId=" + rollcallId;
		return this.send(url, "2");
	}

}
