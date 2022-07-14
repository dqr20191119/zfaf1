package com.cesgroup.prison.user.web;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.framework.util.IpUtil;
import com.cesgroup.prison.common.bean.login2.LoginRespBean;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.UserCodeUtil;
import com.cesgroup.prison.common.bean.user.utils.UserLoginManager;

/**
 * 系统全局登录用户处理控制类
 * @author ziyang
 *
 */


@Controller
@RequestMapping("/common/user")
public class UserInfoController extends BaseController {
	/**
	 * 获取用户登录信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/userinfo")
	@ResponseBody
	public LoginRespBean userinfo (HttpServletRequest request) {
		LoginRespBean respBean = null;	// 登录响应对象
		respBean = new LoginRespBean();
		
		// String loginIp = request.getRemoteAddr();
		String loginIp = IpUtil.getIpAddress();
		UserBean userBean = UserLoginManager.getUserByLoginIp(loginIp);
		if(userBean != null) {
			respBean.setUserBean(userBean);
			respBean.setResult(true);
		}else
			respBean.setRespCode(UserCodeUtil.CODE_0002);
		return respBean;
	}
}