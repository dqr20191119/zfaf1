package com.cesgroup.prison.login2.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cesgroup.framework.util.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ces.utils.TokenUtils;
import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.framework.util.IpUtil;
import com.cesgroup.framework.util.Tools;
import com.cesgroup.prison.common.bean.ARespBean;
import com.cesgroup.prison.common.bean.login2.LoginReqBean;
import com.cesgroup.prison.common.bean.login2.LoginRespBean;
import com.cesgroup.prison.common.bean.login2.LogoutReqBean;
import com.cesgroup.prison.common.bean.login2.LogoutRespBean;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.UserCodeUtil;
import com.cesgroup.prison.common.bean.user.utils.UserConfigUtil;
import com.cesgroup.prison.common.bean.user.utils.UserConstants;
import com.cesgroup.prison.common.bean.user.utils.UserLoginManager;
import com.cesgroup.prison.db.service.RedisCache;
import com.cesgroup.prison.login2.service.ILoginService;

import cn.org.bjca.security.SecurityEngineDeal;

@Controller
@RequestMapping("/lg/loginCtrl")
public class Login2Controller extends BaseController {
    // 登录日志对象
    private static final Logger log = LoggerFactory.getLogger(Login2Controller.class);

    // 登录服务对象实例
    @Resource
    private ILoginService loginService;

    /**
     * 登录页面跳转
     *
     * @return
     */
    /*
     * @RequestMapping("/ln1") public String login() { return "lg/login"; }
     */

    /*
     * @RequestMapping("/ln") public String logins() {
     *
     * //String userName = "32112319630424004X|南京女子监狱"; //UserEntity userEntity
     * = null; try{ //userEntity =
     * AuthSystemFacade.getUserEntityByLoginName(userName); //return
     * TokenEUtils.getToken(userName + "|" + userEntity.getOrgUnit(), passWord,
     * 1); } catch(Exception ex) { log.error("获取用户临时登录令牌异常：", ex); return null;
     * } return "lg/login"; }
     */

    /**
     *
     * 大平台单点登录入口 登陆系统
     *
     * @param ={
     *            "mode": "登录模式：1.用户密码登录（默认）、2.授权（令牌）登录", "token":
     *            "登录令牌，mode=2时有效", "userName": "登录用户名，mode=1时有效", "password":
     *            "登录密码，mode=1时有效", "verifyCode":
     *            "验证码，该值在检测到用户已在其它地方登录时返回，如果验证有效则强制登录" }
     */
    @RequestMapping("/ln3")
    @ResponseBody
    public LoginRespBean loginx(HttpServletRequest request, HttpServletResponse response) {

        String args = null; // 请求数据
        JSONObject reqJSON = null; // 请求数据
        LoginReqBean reqBean = null; // 登录请求对象
        LoginRespBean respBean = null; // 登录响应对象

        try {

            // 获取请求参数
            args = URLDecoder.decode(request.getParameter("args"), "UTF-8");
            String redirect = request.getParameter("redirect");
            // log.info("用户登录系统请求 ==> " + args);

            // 如果ip已登录
            // String loginIp = request.getRemoteAddr();
            String loginIp = IpUtil.getIpAddress();
            Object loginObj = RedisCache.getObject(UserLoginManager.LOGIN_CACHE_IP_USERINFO, loginIp);
            if (loginObj != null && !"".equals(String.valueOf(loginObj))) {

                // 上一用户登出：执行客户端机器上次登录的残留用户登出系统操作
                UserBean userBean = JSON.toJavaObject(JSON.parseObject(String.valueOf(loginObj)), UserBean.class);
                LogoutReqBean reqOutBean = new LogoutReqBean();
                reqOutBean.setUserId(userBean.getUserId());
                reqOutBean.setLogoutIp(loginIp);
                loginService.logout(reqOutBean);
                if (redirect == null || redirect.toString().equals("")) {
                    // 重新登录：使用新用户重新进行一次登录，保证单点登录的用户为最新用户
                    response.sendRedirect(request.getContextPath() + "/lg/loginCtrl/ln3?args="
                            + URLEncoder.encode(args, "UTF-8") + "&redirect=1");
                    return null;
                }

            }

            // String token = TokenUtils.getToken("小王|南京监狱", "", 1);
            String token = TokenUtils.getToken(args, "", 1);
            args = "{\"mode\":2,\"token\":\"" + token + "\"}";

            // 实例化对象、数据转换
            respBean = new LoginRespBean();
            reqJSON = JSON.parseObject(args);
            reqBean = JSON.toJavaObject(reqJSON, LoginReqBean.class);

            // 检查设置登录模式并设置登录IP
            reqBean.setMode(Tools.toStr(reqJSON.get("mode"), UserConfigUtil.get(UserConfigUtil.DEF_LOGIN_MODE)));
            // reqBean.setLoginIp(request.getRemoteAddr());
            reqBean.setLoginIp(IpUtil.getIpAddress());
            // reqBean.setToken("32112319630424004X|新疆女子监狱");
            // 选择登录模式
            switch (reqBean.getMode()) {
                case UserConstants.LOGIN_MODE_LOCAL:
                    respBean = loginService.localLogin(reqBean);
                    break; // 用户密码登录
                case UserConstants.LOGIN_MODE_AUTH:
                    respBean = loginService.authLogin(reqBean);
                    break; // 授权（令牌）登录
                default:
                    respBean.setRespCode(UserCodeUtil.CODE_0001);
                    break; // 未知登录模式
            }

            // 返回登录结果
            // log.info("用户登录系统响应 <== " + JSON.toJSONString(respBean));

            // String tokenStr
            // =com.ces.utils.TokenUtils.getToken(user.getLoginName(),user.getPassword(),480);//临时创建令牌，令牌有效事件20分钟
            // setCookie(rememberMeCookieName,tokenStr,-1);//设置remember cookie
            // 为会话级令牌，浏览器级
            // setCookie(LOGINUSERNAME_COOKIEKEY,URLEncoder.encode((user.getName()),"UTF-8"),-1);
            // setCookie(LOGINUSERNAME_COOKIEKEY,URLEncoder.encode((user.getName()),"UTF-8"),-1);

            // 记录用户于session会话中
            // setCookie(request , response ,
            // LOGINUSERNAME_COOKIEKEY,URLEncoder.encode(respBean.getUserBean().getUserName(),"UTF-8"),-1);
            // request.getSession().setAttribute("userbean",
            // JSON.toJSONString(respBean.getUserBean()));

            // 重定向到首页
            response.sendRedirect(request.getContextPath() + "/portal/zhshouye");
        } catch (Exception ex) {
            respBean.setRespCode(UserCodeUtil.CODE_0002);
            respBean.setRespDesc(respBean.getRespDesc() + ":" + ex.getMessage());
            log.error("登录错误：", ex);
        }

        return null;
    }

    private static final String LOGINUSERNAME_COOKIEKEY = "_xarch_loginuser_name_";
    private Boolean useSecureCookie = null;

    /**
     * <p>
     * 创建Cookie
     * </p>
     *
     * @param name
     * @param value
     * @param maxAge
     * @author <a href="mailto:510276046@qq.com">Erxn(滕树宝)</a>
     * @date 2013-11-12 上午9:32:14
     */
    private void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value,
                           int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");

        if (useSecureCookie == null) {
            cookie.setSecure(request.isSecure());
        } else {
            cookie.setSecure(useSecureCookie);
        }
        response.addCookie(cookie);
    }

    /**
     * 获取用户登录信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/userinfo")
    @ResponseBody
    public LoginRespBean userinfo(HttpServletRequest request) {
        LoginRespBean respBean = null; // 登录响应对象
        respBean = new LoginRespBean();

        // String loginIp = request.getRemoteAddr();
        String loginIp = IpUtil.getIpAddress();
        UserBean userBean = UserLoginManager.getUserByLoginIp(loginIp);
        if (userBean != null) {
            respBean.setUserBean(userBean);
            respBean.setResult(true);
        } else
            respBean.setRespCode(UserCodeUtil.CODE_0002);
        return respBean;
    }

    /**
     * 加密数据
     *
     * @param data
     * @return
     */
    /*
     * @ResponseBody
     *
     * @RequestMapping("encode") public String encode (@WebParam String data) {
     * try{ return Tools.notEmpty(data) ? TokenEUtils.encodeToken(new
     * String[]{data}) : null; } catch(Exception ex) { log.error("加密数据异常，参数[" +
     * data + "]", ex); return null; } }
     */

    /**
     * 解密数据
     *
     * @param data
     * @return
     */
    /*
     * @ResponseBody
     *
     * @RequestMapping("decode") public String decode (@WebParam String data) {
     * try{ return Tools.notEmpty(data) ? TokenEUtils.decodeToken(data)[0] :
     * null; } catch(Exception ex) { log.error("解密数据异常，参数[" + data + "]", ex);
     * return null; } }
     */

    // =================================临时测试代码，用于获取登录令牌=======================================
    /*
     * @ResponseBody
     *
     * @RequestMapping("getToken") public String getToKen(HttpServletRequest
     * request){ String userName = null; String passWord = null; UserEntity
     * userEntity = null;
     *
     * try{ log.debug("获取用户临时登录令牌..."); userName =
     * request.getParameter("userName"); passWord =
     * request.getParameter("password"); userEntity =
     * AuthSystemFacade.getUserEntityByLoginName(userName); return
     * TokenEUtils.getToken(userName + "|" + userEntity.getOrgUnit(), passWord,
     * 1); } catch(Exception ex) { log.error("获取用户临时登录令牌异常：", ex); return null;
     * } }
     */

    /**
     * 跳转到登录页
     *
     * @param request
     * @param response
     * @return
     */
    //正式环境用
    /*@RequestMapping("/ln")
    public String logins(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.getLongin(request, response);
    }*/

    @RequestMapping("/ln")
    public String logins(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String loginIp = IpUtil.getIpAddress();
        Object loginObj = RedisCache.getObject(UserLoginManager.LOGIN_CACHE_IP_USERINFO, loginIp);

        if (loginObj != null && !"".equals(String.valueOf(loginObj))) {
            // 如果当前IP已登录重定向到首页
            response.sendRedirect(request.getContextPath() + "/portal/zhshouye");
            return null;
        }
        return "lg/login";
    }


    /**
     * 跳转到登录页
     *
     * @param request
     * @param response
     * @return
     */
    /*//正式环境用
    @RequestMapping("/lnCa")
    public String loginCa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return this.getLongin(request, response);
    }*/

    @RequestMapping("/lnCa")
    public String loginCa(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String loginIp = IpUtil.getIpAddress();
        Object loginObj = RedisCache.getObject(UserLoginManager.LOGIN_CACHE_IP_USERINFO, loginIp);

        if (loginObj != null && !"".equals(String.valueOf(loginObj))) {
            // 如果当前IP已登录重定向到首页
            response.sendRedirect(request.getContextPath() + "/portal/zhshouye");
            return null;
        }
        return "lg/loginCa";
    }

    private String getLongin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String loginIp = IpUtil.getIpAddress();
        Object loginObj = RedisCache.getObject(UserLoginManager.LOGIN_CACHE_IP_USERINFO, loginIp);

        if (loginObj != null && !"".equals(String.valueOf(loginObj))) {
            // 如果当前IP已登录重定向到首页
            response.sendRedirect(request.getContextPath() + "/portal/zhshouye");
            return null;
        }
        String zhywptLogin = ConfigUtil.get("ZHYWPT_LOGIN");
        response.sendRedirect(zhywptLogin);
        return null;
    }




    /**
     * 跳转到登录页
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/fujian")
    public String fujian(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return "lg/pluginDown";
    }

    /**
     * 跳转到用户退出错误页
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/errorLogout")
    public String errorLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return "/error/errorLogout";
    }

    /**
     * 退出系统
     *
     * @param
     */
    @RequestMapping("logout")
    @ResponseBody
    public LogoutRespBean logout(HttpServletRequest request) {

        LogoutRespBean respBean = null; // 登录响应对象
        LogoutReqBean reqBean = null; // 登录请求对象
        String args = null; // 请求数据

        try {
            // 获取请求参数
            args = request.getParameter("userId");
            log.info("用户退出系统请求 ==> " + args);

            // 实例化对象、数据转换
            respBean = new LogoutRespBean();
            reqBean = new LogoutReqBean();
            reqBean.setUserId(args);
            reqBean.setLogoutIp(IpUtil.getIpAddress());

            // 登出系统
            respBean = loginService.logout(reqBean);
        } catch (Exception ex) {

            respBean.setRespCode(UserCodeUtil.CODE_0005);
            respBean.setRespDesc(respBean.getRespDesc() + ":" + ex.getMessage());
            log.error("用户退出系统异常：", ex);
        }

        log.info("用户退出系统响应 <== " + JSON.toJSONString(respBean));
        return respBean;
    }

    /**
     * 安防系统自身登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public LoginRespBean login(HttpServletRequest request, HttpServletResponse response) {

        String args = null; // 请求数据
        String verifyCode = ""; // 请求登录强制码
        JSONObject reqJSON = null; // 请求数据
        LoginReqBean reqBean = null; // 登录请求对象
        LoginRespBean respBean = null; // 登录响应对象
        String Pwd = null;
        String UserName = request.getParameter("args");
        try {

            // 获取请求参数
            args = request.getParameter("args");
            verifyCode = request.getParameter("verifyCode");
            Pwd = request.getParameter("passwordVal");
            log.info("用户登录系统请求 ==> " + args);

            String token = TokenUtils.getToken(args, Pwd, 1);
            if (args.contains("|")) {
                args = "{\"mode\":2,\"token\":\"" + token + "\"}";
            } else {
                args = "{\"mode\":1,\"token\":\"" + token + "\"}";
            }
            // 实例化对象、数据转换
            respBean = new LoginRespBean();
            reqJSON = JSON.parseObject(args);
            if (verifyCode != null && !verifyCode.equals(""))
                reqJSON.put("verifyCode", verifyCode);
            reqBean = JSON.toJavaObject(reqJSON, LoginReqBean.class);
            reqBean.setUserName(UserName);
            reqBean.setPassword(Pwd);
            // 检查设置登录模式并设置登录IP
            reqBean.setMode(Tools.toStr(reqJSON.get("mode"), UserConfigUtil.get(UserConfigUtil.DEF_LOGIN_MODE)));
            reqBean.setLoginIp(IpUtil.getIpAddress());

            // 选择登录模式
            switch (reqBean.getMode()) {
                case UserConstants.LOGIN_MODE_LOCAL:
                    respBean = loginService.localLogin(reqBean);
                    break; // 用户密码登录
                case UserConstants.LOGIN_MODE_AUTH:
                    respBean = loginService.authLogin(reqBean);
                    break; // 授权（令牌）登录
                default:
                    respBean.setRespCode(UserCodeUtil.CODE_0001);
                    break; // 未知登录模式
            }

            // 返回登录结果
            log.info("用户登录系统响应 <== " + JSON.toJSONString(respBean));

            // 记录用户于session会话中
            // setCookie(request , response ,
            // LOGINUSERNAME_COOKIEKEY,URLEncoder.encode(respBean.getUserBean().getUserName(),"UTF-8"),-1);
            // request.getSession().setAttribute("userbean",
            // JSON.toJSONString(respBean.getUserBean()));
        } catch (Exception ex) {

            respBean.setRespCode(UserCodeUtil.CODE_0002);
            respBean.setRespDesc(respBean.getRespDesc() + ":" + ex.getMessage());
            log.error("登录错误：", ex);
        }

        return respBean;
    }

    /**
     * 安防系统CA登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logincac")
    @ResponseBody
    public LoginRespBean logincac(HttpServletRequest request, HttpServletResponse response) {

        String args = null; // 请求数据
        String verifyCode = ""; // 请求登录强制码
        JSONObject reqJSON = null; // 请求数据
        LoginReqBean reqBean = null; // 登录请求对象
        LoginRespBean respBean = null; // 登录响应对象

        try {
            String jgz = request.getParameter("jgz");
            if ("1".equals(jgz)) {

                String uniqueId = request.getParameter("uniqueId");
                // 系统登陆
                String CAId = uniqueId.replace("SF", "");
                args = loginService.loginName(CAId);
                String token = TokenUtils.getToken(args, "", 1);
                args = "{\"mode\":2,\"token\":\"" + token + "\"}";
                // 实例化对象、数据转换
                respBean = new LoginRespBean();
                reqJSON = JSON.parseObject(args);
                if (verifyCode != null && !verifyCode.equals(""))
                    reqJSON.put("verifyCode", verifyCode);
                reqBean = JSON.toJavaObject(reqJSON, LoginReqBean.class);

                // 检查设置登录模式并设置登录IP
                reqBean.setMode(Tools.toStr(reqJSON.get("mode"), UserConfigUtil.get(UserConfigUtil.DEF_LOGIN_MODE)));
                reqBean.setLoginIp(IpUtil.getIpAddress());

                // 选择登录模式
                switch (reqBean.getMode()) {
                    // case UserConstants.LOGIN_MODE_LOCAL: respBean =
                    // loginService.localLogin(reqBean); break; // 用户密码登录
                    case UserConstants.LOGIN_MODE_AUTH:
                        respBean = loginService.authLogin(reqBean);
                        break; // 授权（令牌）登录
                    default:
                        respBean.setRespCode(UserCodeUtil.CODE_0001);
                        break; // 未知登录模式
                }

                // 返回登录结果
                log.info("用户登录系统响应 <== " + JSON.toJSONString(respBean));

                // 记录用户于session会话中
                // setCookie(request , response ,
                // LOGINUSERNAME_COOKIEKEY,URLEncoder.encode(respBean.getUserBean().getUserName(),"UTF-8"),-1);
                // request.getSession().setAttribute("userbean",
                // JSON.toJSONString(respBean.getUserBean()));
            } else {
                respBean.setRespCode(UserCodeUtil.CODE_0002);
            }
        } catch (Exception ex) {

            respBean.setRespCode(UserCodeUtil.CODE_0002);
            respBean.setRespDesc(respBean.getRespDesc() + ":" + ex.getMessage());
            log.error("登录错误：", ex);
        }

        return respBean;

    }

    /**
     * 安防系统CA登录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/logincacaca")
    @ResponseBody
    public void logincacaca(HttpServletRequest request, HttpServletResponse response) {

        String args = null; // 请求数据
        String verifyCode = ""; // 请求登录强制码
        JSONObject reqJSON = null; // 请求数据
        LoginReqBean reqBean = null; // 登录请求对象
        LoginRespBean respBean = null; // 登录响应对象

        // ca认证
        try {
            SecurityEngineDeal.setRootPath("C:\\BJCAROOT");
            HttpSession session = request.getSession();
            String ranStr = (String) session.getAttribute("Random");

            SecurityEngineDeal sed = null;
            sed = SecurityEngineDeal.getInstance("SecXV3Default");

            // 获得登陆用户cert
            String clientCert =
                    // "MIIFQjCCBCqgAwIBAgIKGzAAAAAAAEpq5jANBgkqhkiG9w0BAQUFADBSMQswCQYDVQQGEwJDTjENMAsGA1UECgwEQkpDQTEYMBYGA1UECwwPUHVibGljIFRydXN0IENBMRowGAYDVQQDDBFQdWJsaWMgVHJ1c3QgQ0EtMTAeFw0xODA5MDIxNjAwMDBaFw0xOTA5MDMxNTU5NTlaMIGUMQswCQYDVQQGEwJDTjEtMCsGA1UECgwk5YyX5Lqs5pWw5a2X6K6k6K+B6IKh5Lu95pyJ6ZmQ5YWs5Y+4MRIwEAYDVQQDDAnotbXkuIDmnYMxJTAjBgkqhkiG9w0BCQEWFnpoYW95aXF1YW5AYmpjYS5vcmcuY24xGzAZBgoJkiaJk/IsZAEpDAsxODgwMDE0NzgxMjCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA0OP1eOdc9AIxSC/dY/jKyue3ruL2TxHoH4z0m/LXnHlPdBh/vmkf386l3TEzM5/330kAiCHy/bRppK1A6NkslwiOB82yPXDlkQE4Avv9Vyhxxv1PnbS94zdLcuFRQgQi4ciCR3nUjc2PwMJPRsAsAX8OwLbjs/PEIpTow3pohrUCAwEAAaOCAlkwggJVMB8GA1UdIwQYMBaAFKw77K8Mo1AO76+vtE9sO9vRV9KJMB0GA1UdDgQWBBRe/s4OV/VtVI0GjnUahSQ1AWIVPzALBgNVHQ8EBAMCBsAwga8GA1UdHwSBpzCBpDBtoGugaaRnMGUxCzAJBgNVBAYTAkNOMQ0wCwYDVQQKDARCSkNBMRgwFgYDVQQLDA9QdWJsaWMgVHJ1c3QgQ0ExGjAYBgNVBAMMEVB1YmxpYyBUcnVzdCBDQS0xMREwDwYDVQQDEwhjYTNjcmwyNTAzoDGgL4YtaHR0cDovL2xkYXAuYmpjYS5vcmcuY24vY3JsL3B0Y2EvY2EzY3JsMjUuY3JsMAkGA1UdEwQCMAAwEQYJYIZIAYb4QgEBBAQDAgD/MB0GBSpWCwcBBBRTRjM3MDY4NjE5OTQwMTAyNzAxODAdBgUqVgsHCAQUU0YzNzA2ODYxOTk0MDEwMjcwMTgwIAYIYIZIAYb4RAIEFFNGMzcwNjg2MTk5NDAxMDI3MDE4MBsGCCpWhkgBgTABBA8xMDIwMDAwMTE3ODg5NjYwJQYKKoEchu8yAgEEAQQXMkNAU0YzNzA2ODYxOTk0MDEwMjcwMTgwKgYLYIZIAWUDAgEwCQoEG2h0dHA6Ly9iamNhLm9yZy5jbi9iamNhLmNydDAPBgUqVhUBAQQGMTAwMDgwMEAGA1UdIAQ5MDcwNQYJKoEchu8yAgIBMCgwJgYIKwYBBQUHAgEWGmh0dHA6Ly93d3cuYmpjYS5vcmcuY24vY3BzMBMGCiqBHIbvMgIBAR4EBQwDNTIwMA0GCSqGSIb3DQEBBQUAA4IBAQBdH1P2feqvOnKBQrETyEbm8oVlcHNpPlytBfi05co6cooArgbE3VXMQEzbMXES702G6QvMPBdwW0IABb+05RK44tkGltEYZUbXumHGriXM3U3e238Gy3YOUEEE/0I/DX7zAHBrFgjVtVMWS7y92cqfwZrhN9c59Vk2WWY4lmx35Xt0G2NGdY7XRjFfquGTdr79jPwbu+gvfIGSQ3hYpXRUvwPdoC7Jbm9dVBhbFhHAwyAUyHuTPL3ozS0Ez0xCdV6IAzWl4CNb50aR/o1+o+/aPMzGOAOd0aru9sqwZlSq8fhr9mi+UDljOSOGnKJkElinyMV18NWQTW/yEMpMmByB";
                    request.getParameter("UserCert");
            String UserSignedData = request.getParameter("UserSignedData");
            String ContainerName = request.getParameter("ContainerName");
            String certPub = sed.getCertInfo(clientCert, 8);
            // System.out.println(clientCert);
            // 验证服务器证书有效期
            java.text.SimpleDateFormat date = new java.text.SimpleDateFormat("yyyy/MM/dd");
            String dateNow = date.format(new Date());
            Date datenow = date.parse(dateNow);
            String servercert = sed.getServerCertificate();
            String servercertdate = sed.getCertInfo(servercert, 12);

            Date scertdate = date.parse(servercertdate);
            // int dd = scertdate.compareTo(datenow);
            // if (dd == -1) {
            // System.out.println("服务器证书已过期:请联系系统管理员更新服务器证书。");
            // return;
            // }
            // 验证客户端证书
            try {

                int retValue = sed.validateCert(clientCert);
                // 验证客户端签名
                try {
                    if (sed.verifySignedData(clientCert, ranStr, UserSignedData)) {

                    } else {
                        String ctx = request.getContextPath();
                        try {
                            response.sendRedirect(ctx + "/lg/loginCtrl/lnCa");
                            return;
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        System.out.println("验证客户端签名错误");

                    }
                } catch (Exception e) {
                    String ctx = request.getContextPath();
                    try {
                        response.sendRedirect(ctx + "/lg/loginCtrl/lnCa");
                        return;
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    System.out.println("验证客户端签名错误:" + e.getMessage());

                }
                if (retValue == 1) {

                    session.setAttribute("ContainerName", ContainerName);

                    String uniqueIdStr = "";
                    try {
                        uniqueIdStr = sed.getCertInfo(clientCert, 17);
                    } catch (Exception e) {
                        String ctx = request.getContextPath();
                        try {
                            response.sendRedirect(ctx + "/lg/loginCtrl/lnCa");
                            return;
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        System.out.println("客户端证书验证失败:" + e.getMessage());
                    }
                    session.setAttribute("UniqueID", uniqueIdStr);

                    String uniqueId = "";
                    try {
                        String type = sed.getCertInfo(clientCert, 31);
                        if (type == "ECC") {
                            uniqueId = sed.getCertInfoByOid(clientCert, "1.2.156.112562.2.1.1.1");
                        } else {
                            // 获得登陆用户ID
                            uniqueId = sed.getCertInfoByOid(clientCert, "2.16.840.1.113732.2");
                        }

                    } catch (Exception e) {
                        String ctx = request.getContextPath();
                        try {
                            response.sendRedirect(ctx + "/lg/loginCtrl/lnCa");
                            return;
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        System.out.println("客户端证书验证失败:" + e.getMessage());
                    }

                    System.out.println("欢迎您使用本系统!");
                    System.out.println("主题通用名：");
                    System.out.println(uniqueIdStr);
                    System.out.println("证书颁发者(颁发者通用名): ");
                    System.out.println(certPub);
                    System.out.println("证书唯一标识(备用主题通用名)：");
                    System.out.println(uniqueId);
                    System.out.println("(实际集成时,会将唯一标识与数据库比对,判断是否为合法用户)");

                    // 系统登陆
                    String CAId = uniqueId.replace("SF", "");
                    CAId = CAId.replace("X", "");
                    CAId = CAId.replace("x", "");
                    args = loginService.loginName(CAId);
                    System.out.println("*****************************************" + args);
                    String token = TokenUtils.getToken(args, "", 1);
                    args = "{\"mode\":2,\"token\":\"" + token + "\"}";
                    // 实例化对象、数据转换
                    respBean = new LoginRespBean();
                    reqJSON = JSON.parseObject(args);
                    if (verifyCode != null && !verifyCode.equals(""))
                        reqJSON.put("verifyCode", verifyCode);
                    reqBean = JSON.toJavaObject(reqJSON, LoginReqBean.class);

                    // 检查设置登录模式并设置登录IP
                    reqBean.setMode(
                            Tools.toStr(reqJSON.get("mode"), UserConfigUtil.get(UserConfigUtil.DEF_LOGIN_MODE)));
                    reqBean.setLoginIp(IpUtil.getIpAddress());

                    // 选择登录模式
                    switch (reqBean.getMode()) {
                        // case UserConstants.LOGIN_MODE_LOCAL: respBean =
                        // loginService.localLogin(reqBean); break; // 用户密码登录
                        case UserConstants.LOGIN_MODE_AUTH:
                            respBean = loginService.authLogin(reqBean);
                            break; // 授权（令牌）登录
                        default:
                            respBean.setRespCode(UserCodeUtil.CODE_0001);
                            break; // 未知登录模式
                    }
                    String ctx = request.getContextPath();
                    try {
                        request.setAttribute("respBean", respBean);
                        response.sendRedirect(ctx + "/portal/zhshouye");
                        return;
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    String ctx = request.getContextPath();
                    try {
                        response.sendRedirect(ctx + "/lg/loginCtrl/lnCa");
                        return;
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("客户端证书验证失败！");
                    if (retValue == -1) {
                        System.out.println("登录证书的根不被信任");
                    } else if (retValue == -2) {
                        System.out.println("登录证书超过有效期");
                    } else if (retValue == -3) {
                        System.out.println("登录证书为作废证书");
                    } else if (retValue == -4) {
                        System.out.println("登录证书被临时冻结");
                    }

                }
            } catch (Exception ex) {
                String ctx = request.getContextPath();
                try {
                    response.sendRedirect(ctx + "/lg/loginCtrl/lnCa");
                    return;
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("客户端证书验证失败:" + ex.getMessage());

            }

        } catch (Exception ex) {

            respBean.setRespCode(UserCodeUtil.CODE_0002);
            respBean.setRespDesc(respBean.getRespDesc() + ":" + ex.getMessage());
            log.error("登录错误：", ex);
        }

    }


    /**
     * 获取海康单点登录的token
     *
     * @param
     */
    @RequestMapping("getToken")
    @ResponseBody
    public String getToken(HttpServletRequest request) {
        String str = "";
        String url = request.getParameter("url");
        try {
            URL netUrl = new URL(url);
            URLConnection conn = netUrl.openConnection();//或HttpURLConnection connect = (HttpURLConnection) connection.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine = null;
            while ((inputLine = br.readLine()) != null) {
                str = inputLine;
                return str;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void main(String[] args) {
        String url = "http://34.205.80.15/home/ssoTokenKey.action";
        try {
            URL oracle = new URL(url);
            URLConnection conn = oracle.openConnection();//或HttpURLConnection connect = (HttpURLConnection) connection.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine = null;
            while ((inputLine = br.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}