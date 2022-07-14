package com.cesgroup.prison.common.web;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.dao.MessageMapper;
import com.cesgroup.prison.common.entity.MessageEntity;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.common.service.MessageService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 工作台消息提醒管理
 * 
 */
@Controller
@RequestMapping(value = "/common/message")
public class MessageController extends BaseEntityDaoServiceCRUDController<MessageEntity, String, MessageMapper, MessageService>  {
	
	private final Logger logger = LoggerFactory.getLogger(MessageController.class);  
	
	@Resource
	private MessageService messageService;

	@RequestMapping(value = "/toMessageIndex")
	public ModelAndView toMessageIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("portal/messageIndex");
		return mv;
	}

	@RequestMapping(value = "/queryWithPage")
	@ResponseBody
	public Map<String, Object> queryWithPage(HttpServletRequest request, HttpServletResponse response) throws WebUIException {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		if (user == null) {
			throw new WebUIException("用户未登录");
		}
		String isRead = request.getParameter("isRead");//是否已读

		Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
		paramMap.put("jyId", user.getCusNumber());
		paramMap.put("noticeUserId", user.getUserId());
		if (isRead != null && !"".equals(isRead)) {
			paramMap.put("isRead", isRead);
		}
		try {
			Page<Map<String, Object>> pageInfo = (Page<Map<String, Object>>) this.getService().queryWithPage(paramMap, buildPageRequest());
			return pageToMap(pageInfo);
		} catch (ServiceException e) {
			throw new WebUIException(e.getMessage());
		} catch (Exception e) {
			throw new WebUIException(e.getMessage());
		}
	}

	@RequestMapping(value = "/updateRead")
	@ResponseBody
	public AjaxMessage updateRead(HttpServletRequest request, HttpServletResponse response) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		try {
			// 获取页面传值
			String isRead = request.getParameter("isRead");
			String ids = request.getParameter("ids");

			// 更新读取状态
			this.getService().updateReadById(isRead, new Date(), ids);

			// 设置返回结果
			ajaxMessage.setSuccess(true);
			ajaxMessage.setMsg("更新消息读取状态成功");
		} catch(ServiceException e) {
			ajaxMessage.setSuccess(false);
			ajaxMessage.setMsg("更新消息读取状态失败");
		}
		return ajaxMessage;
	}

	@RequestMapping(value = "/findFirstMessage")
	@ResponseBody
	public MessageEntity findFirstMessage(MessageEntity messageEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		UserBean user = AuthSystemFacade.getLoginUserInfo();		
		messageEntity.setNoticeUserId(user.getUserId());
		messageEntity = messageService.findFirstMessage(messageEntity);
		return messageEntity;				
	}
	
	@RequestMapping(value = "/findByMsgType")
	@ResponseBody
	public List<Map<String, Object>> findByMsgType(MessageEntity messageEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();		
		messageEntity.setNoticeUserId(user.getUserId());
		List<Map<String, Object>> resultList = messageService.findByMsgType(messageEntity);
		return resultList;				
	}
	
	/**
	 * ywId: 业务id
	 * jyId: 监狱id-按业务需求定，如果不传直接更新业务id对应所有的消息为已读，否则更新指定监狱的消息为已读
	 * noticeUserId: 用户id-按业务需求定，如果不传直接更新业务id对应所有的消息为已读，否则更新指定用户的消息为已读
	 * msgType: 消息类型
	 * 
	 * @param messageEntity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateReadByYwId")
	@ResponseBody
	public Map<String, Object> updateReadByYwId(MessageEntity messageEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try {
			
			messageService.updateReadByYwId(messageEntity);
			resultMap.put("code", "1");
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", "0");
		}
		
		return resultMap;
	}



    /**
     * 将分页信息转换为Map
     * @param page
     * @return
     */
    public static Map<String, Object> pageInfoToMap(PageInfo<?> page){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("pageNumber", page.getPageNum());
        resultMap.put("totalPages", page.getPages());
        resultMap.put("total", page.getTotal());
        resultMap.put("data", page.getList());
        return resultMap;
    }

    /**
     * 将分页信息转换为Map
     * @param page
     * @return
     */
    public static Map<String, Object> pageToMap(Page<?> page){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("pageNumber", page.getNumber() + 1);
        resultMap.put("totalPages", page.getTotalPages());
        resultMap.put("total", page.getTotalElements());
        resultMap.put("data", page.getContent());
        return resultMap;
    }
    public static Map<String, Object> success(String message){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", "success");
        map.put("message", message);
        map.put("code", 200);
        return map;
    }
}
