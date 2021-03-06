package com.cesgroup.prison.callNamesAl.service.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.callNamesAl.bean.CallNamesAlBean;
import com.cesgroup.prison.callNamesAl.bean.CallNamesPrisonerAlBean;
import com.cesgroup.prison.callNamesAl.dao.CallNamesRegisterMapper;
import com.cesgroup.prison.callNamesAl.entity.CallNamesRegisterEntity;
import com.cesgroup.prison.callNamesAl.service.CallNamesRegisterService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.dao.AffixMapper;
import com.cesgroup.prison.common.dao.AreadeviceMapper;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.cesgroup.prison.common.facade.AuthSystemFacade;

@Service
@Transactional
public class CallNamesRegisterServiceImpl extends
		BaseDaoService<CallNamesRegisterEntity, String, CallNamesRegisterMapper> implements CallNamesRegisterService {

	@Autowired
	private CallNamesRegisterMapper mapper;

	@Autowired
	private RestTemplate restTemplate;

	@Resource
	private AffixMapper affixMapper;

	@Resource
	private AreadeviceMapper areadeviceMapper;

	private static final String FACE_HOST = "http://33.168.120.245/face/api";
	private static final String APPKEY = "app-xjjy";

	/**
	* @methodName: send
	* @Description: ????????????
	* @param giveMoneyUrl ??????url
	* @param request 
	* @param type 1???post 2???get
	* @return CallNamesBean
	* @throws  
	*/
	private CallNamesAlBean send(String giveMoneyUrl, MultiValueMap<String, String> params, String type) {

		CallNamesAlBean bean = null;
		if (type.equals("1")) {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/x-www-form-urlencoded");
			HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(
					params, headers);
			bean = restTemplate.postForObject(giveMoneyUrl, requestEntity, CallNamesAlBean.class);

		}
		if (type.equals("2")) {
			bean = restTemplate.getForObject(giveMoneyUrl, CallNamesAlBean.class);
		}
		return bean;
	}

	/**
	 * @throws IOException 
	 * @throws FileNotFoundException 
	* @methodName: ImageToBase64ByLocal
	* @Description: ???????????????????????????????????????????????????????????????Base64????????????
	* @param imgFile ????????????url
	* @return String ??????Base64?????????????????????????????????
	* @throws  
	*/
	private String ImageToBase64ByLocal(String imgFile) throws IOException {
		InputStream in = null;
		byte[] data = null;
		// ????????????????????????
		in = new FileInputStream(imgFile);
		data = new byte[in.available()];
		in.read(data);
		in.close();
		return Base64.getEncoder().encodeToString(data);
	}

	public CallNamesAlBean registerFace(String groupName, List<JSONObject> picInfos) {
		MultiValueMap<String, String> param = new LinkedMultiValueMap<String, String>();
		param.add("appKey", APPKEY);
		param.add("requestId", UUID.randomUUID().toString());
		param.add("method", "REGISTER");
		param.add("groupName", groupName);
		param.add("isQualityLimit", "false");
		param.add("picInfo", JSON.toJSONString(picInfos));
		return this.send(FACE_HOST, param, "1");
	}

	@Override
	public void deleteByIds(List<String> ids) {
		mapper.deleteByIds(ids);
	}

	/**
	* @methodName: splitList
	* @Description: ?????????list??????n???list?????????????????????10?????????
	* @param source
	* @return List<List<String>>
	* @throws  
	*/
	private List<List<String>> splitList(List<String> source) {
		List<List<String>> result = new ArrayList<List<String>>();
		if (source.size() > 10) {
			int remainder = source.size() % 10;// ??????
			int count = source.size() / 10;
			int offset = 0;// ?????????
			if (remainder != 0) {
				count = count + 1;
			}
			for (int i = 0; i < count; i++) {
				List<String> value = null;
				int n = i == count - 1 && remainder != 0 ? remainder + 10 * i : 10 * (i + 1);
				value = source.subList(offset, n);
				offset += 10;
				result.add(value);
			}
		} else {
			result.add(source);
		}
		return result;

	}

	@Override
	public String addInfo(List<String> zfbhs) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		String cusNumber = userBean.getCusNumber();
		String userId = userBean.getUserId();
		Date date = new Date();
		List<List<String>> zfbhsList = splitList(zfbhs);
		for (List<String> zfbhList : zfbhsList) {
			List<Map<String, String>> list = mapper.findZfPicInfo(zfbhList);// ?????????????????????????????????
			List<JSONObject> picInfos = new LinkedList<>();// ??????????????????????????????????????????????????????????????????
			Map<String, CallNamesRegisterEntity> entityMap = new HashMap<>();
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, String> map = list.get(i);
					String zfbh = map.get("ZFBH");// ????????????
					String imgUrl = map.get("IMG_URL");// ??????url
					String realPath = null;// ????????????????????????
					if (StringUtils.isNotBlank(imgUrl)) {// ?????????????????????
						if (imgUrl.indexOf(CommonConstant.uploadZhddGlobalPath) == -1) {
							realPath = "Z:" + imgUrl.replaceAll("/", "\\\\");
						} else {
							realPath = CommonConstant.jggzUploadsRootPath + imgUrl.replaceAll("/", "\\\\");
						}
						JSONObject picInfo = new JSONObject();
						picInfo.put("imageId", UUID.randomUUID().toString());
						try {
							picInfo.put("picture", ImageToBase64ByLocal(realPath));// ?????????????????????????????????????????????????????????????????????????????????
							picInfo.put("userId", zfbh);
							picInfos.add(picInfo);
						} catch (Exception e) {
							e.printStackTrace();
							imgUrl = null;// ???????????????????????????????????????url??????null???????????????????????????
						}
					}
					CallNamesRegisterEntity entity = new CallNamesRegisterEntity();// ?????????????????????????????????
					entity.setCnrCusNumber(cusNumber);
					entity.setCnrPrisonerIndc(zfbh);
					entity.setCnrCrteTime(date);
					entity.setCnrCrteUserId(userId);
					entity.setCnrUpdtTime(date);
					entity.setCnrUpdtUserId(userId);
					entity.setCnrImgName(map.get("IMG_NAME"));// ??????name
					entity.setCnrImgSize(map.get("IMG_SIZE"));// ????????????
					entity.setCnrImgUrl(imgUrl);
					entityMap.put(zfbh, entity);// ??????map
				}
			}
			CallNamesAlBean bean = registerFace(cusNumber, picInfos);
			if (bean.isSuccess()) {
				Map<String, List<CallNamesPrisonerAlBean>> faces = bean.getFaces();
				Collection<List<CallNamesPrisonerAlBean>> collection = faces.values();
				for (List<CallNamesPrisonerAlBean> list_ : collection) {
					CallNamesPrisonerAlBean alBean = list_.get(0);
					CallNamesRegisterEntity callNamesRegisterEntity = entityMap.get(alBean.getUserId());
					if (callNamesRegisterEntity != null) {
						callNamesRegisterEntity.setCnrImgId(alBean.getImageId());
					}
				}
			}
			List<CallNamesRegisterEntity> entityList = new ArrayList<>(entityMap.values());
			mapper.insert(entityList);
		}
		return "????????????";
	}

	@Override
	public void updateInfo(CallNamesRegisterEntity entity) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setCnrUpdtTime(date);
			entity.setCnrUpdtUserId(userId);
			map.put("callNamesRegisterEntity", entity);
		}
		mapper.updateInfo(map);
	}

	@Override
	public Page<Map<String, Object>> listAll(CallNamesRegisterEntity entity, Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("callNamesRegisterEntity", entity);
		}
		return mapper.listAll(map, pageable);
	}

	@Override
	public CallNamesRegisterEntity findById(String id) {
		return mapper.selectOne(id);
	}

	/*
	 * ?????? Javadoc???
	 * 
	 * @see com.cesgroup.prison.callNamesAl.service.CallNamesRegisterService#
	 * addPrisonerPicFile(java.lang.String, java.lang.String)
	 */
	@Override
	public String addPrisonerPicFile(String id, String file) throws Exception {
		String msg = "";
		// ????????????
		List<String> idList = new ArrayList<String>();
		if (StringUtils.isNotBlank(file)) {
			idList.addAll(Arrays.asList(file.split(",")));
		}
		affixMapper.updateYwId(id, idList);
		msg = "??????????????????";
		CallNamesRegisterEntity entity = mapper.selectOne(id);
		if (entity != null) {
			AffixEntity affixEntity = affixMapper.getById(file);
			String imgUrl = CommonConstant.jggzUploadsRootPath + affixEntity.getLinkUrl().replaceAll("/", "\\\\");
			String imgId = UUID.randomUUID().toString();

			List<JSONObject> picInfos = new LinkedList<>();
			JSONObject picInfo = new JSONObject();
			picInfo.put("imageId", imgId);
			picInfo.put("picture", ImageToBase64ByLocal(imgUrl));// ?????????????????????????????????????????????????????????????????????????????????
			picInfo.put("userId", entity.getCnrPrisonerIndc());
			picInfos.add(picInfo);

			CallNamesAlBean bean = registerFace(entity.getCnrCusNumber(), picInfos);
			if (!bean.isSuccess()) {
				msg = msg + ",???????????????" + bean.getMessage();
			}
			if (bean.isSuccess() && bean.getFaces().isEmpty()) {
				Map<String, CallNamesAlBean> map = bean.getFailRegisterFaces();
				for (String key : map.keySet()) {
					msg = msg + ",???????????????" + map.get(key).getMessage();// ????????????????????????????????????????????????
				}

			}

			if (bean.isSuccess() && bean.getFailRegisterFaces().isEmpty()) {
				UserBean userBean = AuthSystemFacade.getLoginUserInfo();
				String userId = userBean.getUserId();
				CallNamesRegisterEntity callNamesRegisterEntity = new CallNamesRegisterEntity();
				callNamesRegisterEntity.setId(id);
				callNamesRegisterEntity.setCnrImgId(imgId);
				callNamesRegisterEntity.setCnrImgUrl(affixEntity.getLinkUrl());
				callNamesRegisterEntity.setCnrImgSize(affixEntity.getFileSize());
				callNamesRegisterEntity.setCnrImgName(affixEntity.getFileName());
				callNamesRegisterEntity.setCnrUpdtUserId(userId);
				callNamesRegisterEntity.setCnrUpdtTime(new Date());
				this.updateInfo(callNamesRegisterEntity);
				msg = msg + ",????????????";
			}

		}

		return msg;

	}

	@Override
	public List<AffixEntity> findPrisonerPicFile(String id) {
		AffixEntity affixEntity = new AffixEntity();
		affixEntity.setYwId(id);
		List<AffixEntity> affixList = affixMapper.findAllList(affixEntity);
		return affixList;
	}

	@Override
	public List<Map<String, Object>> findForTree(Map<String, Object> paramMap) {
		List<Map<String, Object>> areaList = areadeviceMapper.findAllArea(paramMap);
		if (paramMap.get("id") != null && (areaList == null || areaList.isEmpty())) {
			List<Map<String, Object>> jsList = mapper.findJsForTree(paramMap);
			return jsList;
		}
		for (Map<String, Object> areaMap : areaList) {
			areaMap.put("isParent", true);
			areaMap.put("open", false);
		}
		return areaList;
	}

	@Override
	public List<Map<String, Object>> findPrisonerByJs(Map<String, Object> paramMap) {
		return mapper.findPrisonerByJs(paramMap);
	}

	@Override
	public List<Map<String, Object>> findRegisterPrisonerByJs(Map<String, Object> paramMap) {
		return mapper.findRegisterPrisonerByJs(paramMap);
	}

	/*
	 * ?????? Javadoc???
	 * 
	 * @see
	 * com.cesgroup.prison.callNamesAl.service.CallNamesRegisterService#logout(java.
	 * util.List)
	 */
	@Override
	public String logout(List<String> ids) throws Exception {
		String msg = "";
		for (String id : ids) {
			CallNamesRegisterEntity entity = this.findById(id);
			if (entity != null) {
				String zfbh = entity.getCnrPrisonerIndc();
				String groupName = entity.getCnrCusNumber();
				CallNamesAlBean bean = this
						.send(FACE_HOST + "?appKey=" + APPKEY + "&requestId=" + UUID.randomUUID().toString()
								+ "&method=DELETE_USER&groupName=" + groupName + "&userId=" + zfbh, null, "2");
				if (bean.isSuccess() && bean.getRequest().getUserId().equals(zfbh)) {
					UserBean userBean = AuthSystemFacade.getLoginUserInfo();
					String userId = userBean.getUserId();
					entity.setCnrImgId(null);
					entity.setCnrImgUrl(null);
					entity.setCnrImgSize(null);
					entity.setCnrImgName(null);
					entity.setCnrUpdtUserId(userId);
					entity.setCnrUpdtTime(new Date());
					this.updateInfo(entity);
				} else {
					msg = msg + " ???????????????" + zfbh + "???????????????  ";
				}
			}
		}
		if (msg.equals("")) {
			msg = "????????????";
		}
		return msg;
	}
}
