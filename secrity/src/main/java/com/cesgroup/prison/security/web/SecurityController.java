package com.cesgroup.prison.security.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.commons.SpringContextUtils;
import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.prison.authsystem.service.XarchInvocationSecurityMetadataSource;
@Controller
@RequestMapping("/security")
public class SecurityController extends BaseController {
	// 登录日志对象
	private static final Logger log = LoggerFactory.getLogger(SecurityController.class);

	/**
	 * 系统管理平台统一触发调用的各平台的权限更新方法（非刷新RMI Server资源）
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/flush")
	@ResponseBody
	public AjaxMessage flush(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("============= FlushResources START ===============");
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		
		String message ="server:"+basePath+" ";
		AjaxMessage ajaxMsg = new AjaxMessage();
		try{
			XarchInvocationSecurityMetadataSource  xarchInvocationSecurityMetadataSource = (XarchInvocationSecurityMetadataSource)SpringContextUtils.getBean("XarchInvocationSecurityMetadataSource");
			xarchInvocationSecurityMetadataSource.reflushResourceMapClone();
			message +="reflush  successful";
			ajaxMsg.setSuccess(true);
		}catch(Exception e){
			message +="reflush faild , exception:"+e.getMessage();
			ajaxMsg.setSuccess(false);
		}finally{
			ajaxMsg.setMsg(message);
			log.info(message);
		}
		System.out.println("\n============= FlushResources END ===============");
		
		
		
		return ajaxMsg;
	}

	
	
}