package com.cesgroup.prison.jfsb.service.impl;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jfsb.dao.TalkBackServerMapper;
import com.cesgroup.prison.jfsb.entity.TalkBackServerEntity;
import com.cesgroup.prison.jfsb.service.TalkBackServerService;
import com.cesgroup.prison.utils.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**      
* @projectName：prison   
* @ClassName：TalkBackServerServiceImpl   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月20日 下午7:12:57   
* @version        
*/
@Service
@Transactional
public class TalkBackServerServiceImpl extends BaseDaoService<TalkBackServerEntity, String, TalkBackServerMapper> implements TalkBackServerService {

	@Override
	public AjaxMessage addInfo(TalkBackServerEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();

			//电脑IP校验 wuzilong 2018-07-06
            if(StringUtils.isNotBlank(entity.getTsePcIp())){
                //判断传入的IP是否有重复
                //电脑IP转数组
                String[] tsePcIps = entity.getTsePcIp().split(",");
                if(!CommonUtil.checkArrayIsRepeat(tsePcIps)){
                    tsePcIps = CommonUtil.replaceArrayNull(tsePcIps);
                    List<String> listAll = Arrays.asList(tsePcIps);
                    String repeatIps = CommonUtil.getRepeatString(listAll);
                    if(StringUtils.isNotBlank(repeatIps)){
                        flag = false;
                        obj = "电脑IP【" + repeatIps + "】重复，保存失败";
                        ajaxMessage.setMsg((String) obj);
                        ajaxMessage.setSuccess(flag);
                        return ajaxMessage;
                    }
                }
                //判断传入的IP在数据库中是否有重复
                List<String> repeatIpList = this.getDao().findRepeatIpsWhenAdd(entity.getTsePcIp());
                repeatIpList = CommonUtil.replaceListNull(repeatIpList);
                if(repeatIpList !=null && repeatIpList.size() > 0){
                    flag = false;
                    obj = "电脑IP【" + repeatIpList.toString() + "】重复，保存失败";
                    ajaxMessage.setMsg((String) obj);
                    ajaxMessage.setSuccess(flag);
                    return ajaxMessage;
                }
            }

			TalkBackServerEntity backServerEntity = new TalkBackServerEntity();
			backServerEntity.setTseCusNumber(userBean.getCusNumber());
			backServerEntity.setTseIdnty(entity.getTseIdnty());
			Map<String, Object> map = this.findTalkbackServerSum(backServerEntity);
			if (Integer.parseInt((String) map.get("SUM")) > 0) {
				flag = false;
				obj = "编号【" + entity.getTseIdnty() + "】重复，保存失败";
			} else {
				entity.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				entity.setTseCusNumber(userBean.getCusNumber());
				entity.setTseCrteTime(date);
				entity.setTseCrteUserId(userId);
				entity.setTseUpdtTime(date);
				entity.setTseUpdtUserId(userId);
				entity.setTseActionIndc("1");
				this.getDao().insert(entity);
				flag = true;
				obj = "保存成功";
			}

		} catch (Exception e) {
			flag = false;
			obj = "保存主机发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;

	}

	@Override
	public AjaxMessage updateInfo(TalkBackServerEntity entity) throws Exception {

		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			//电脑IP校验 wuzilong 2018-07-08
			if(StringUtils.isNotBlank(entity.getTsePcIp())){
				//判断传入的IP是否有重复
				//电脑IP转数组
				String[] tsePcIps = entity.getTsePcIp().split(",");
				if(!CommonUtil.checkArrayIsRepeat(tsePcIps)){
					tsePcIps = CommonUtil.replaceArrayNull(tsePcIps);
					List<String> listAll = Arrays.asList(tsePcIps);
					String repeatIps = CommonUtil.getRepeatString(listAll);
					if(StringUtils.isNotBlank(repeatIps)){
						flag = false;
						obj = "电脑IP【" + repeatIps + "】重复，保存失败";
						ajaxMessage.setMsg((String) obj);
						ajaxMessage.setSuccess(flag);
						return ajaxMessage;
					}
				}
				//判断传入的IP在数据库中是否有重复
                Map<String,Object> m = new HashMap<>();
				m.put("id", entity.getId());
				m.put("tsePcIp",entity.getTsePcIp());
				List<String> repeatIpList = this.getDao().findRepeatIpsWhenUpdate(m);
				repeatIpList = CommonUtil.replaceListNull(repeatIpList);
				if(repeatIpList !=null && repeatIpList.size() > 0){
					flag = false;
					obj = "电脑IP【" + repeatIpList.toString() + "】重复，保存失败";
					ajaxMessage.setMsg((String) obj);
					ajaxMessage.setSuccess(flag);
					return ajaxMessage;
				}
			}

			Map<String, Object> map = new HashMap<>();
			if (entity != null) {
				UserBean userBean = AuthSystemFacade.getLoginUserInfo();
				String userId = userBean.getUserId();
				Date date = new Date();
				entity.setTseActionIndc("2");
				entity.setTseUpdtTime(date);
				entity.setTseUpdtUserId(userId);
				map.put("talkBackServerEntity", entity);
			}
			this.getDao().updateInfo(map);
			flag = true;
			obj = "保存成功";
		} catch (Exception e) {
			flag = false;
			obj = "保存主机发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;

	}

	@Override
	public Page<Map<String, Object>> listAll(TalkBackServerEntity entity, Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("talkBackServerEntity", entity);
		}
		return this.getDao().listAll(map, pageable);
	}

	@Override
	public TalkBackServerEntity findById(String id) {
		return this.getDao().selectOne(id);
	}

	@Override
	public Map<String, Object> searchTreeData(String cusNumber) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber.trim());
		}
		List<Map<String, Object>> maps = this.getDao().searchTreeData(map);

		Map<String, Object> m = new HashMap<>();
		m.put("id", "1");
		m.put("name", "对讲主机");
		m.put("children", maps);
		m.put("open", true);

		return m;
	}

	@Override
	public List<Map<String, Object>> findByCusNumber(String cusNumber) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber.trim());
		}
		return this.getDao().findByCusNumber(map);
	}

	@Override
	public List<Map<String, Object>> findInfoByCusNumberAndPcIpAndBaseIdnty(String cusNumber, String ip, String idnty) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber.trim());
		}
		if (StringUtils.isNotBlank(ip)) {
			map.put("ip", ip.trim());
		}
		if (StringUtils.isNotBlank(idnty)) {
			map.put("idnty", idnty.trim());
		}
		return this.getDao().findInfoByCusNumberAndPcIpAndBaseIdnty(map);
	}

	@Override
	public void deleteById(String id) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(id)) {
			TalkBackServerEntity entity = new TalkBackServerEntity();
			entity.setId(id);
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setTseActionIndc("3");
			entity.setTseUpdtTime(date);
			entity.setTseUpdtUserId(userId);
			map.put("talkBackServerEntity", entity);
		}
		this.getDao().deleteById(map);// 逻辑删除主机信息
		this.getDao().deleteByIdForBase(map);// 逻辑删除分机信息
	}

	@Override
	public Map<String, Object> findTalkbackServerSum(TalkBackServerEntity entity) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("talkBackServerEntity", entity);
		}
		return this.getDao().findTalkbackServerSum(map);
	}

}
