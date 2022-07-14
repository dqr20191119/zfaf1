<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@page import="com.ces.prison.common.constants.GroupKeyConst"%>
<%@page import="com.ces.prison.common.constants.SystemConst"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/><!DOCTYPE html>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="cache-control" content="no-store, no-cache, must-revalidate">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
    <title>登录页</title>
    <script src="${ctx}/static/jquery-cui/js/jquery-1.11.3.min.js"></script>
      <link rel="stylesheet" href="${ctx}/static/cui/cui.min.css"/>
     <script src="${ctx}/static/cui/cui.min.js"></script>
    <link rel="stylesheet" href="${ctx}/static/css/reset.css">
    <link rel="stylesheet" href="${ctx}/static/css/login.css">
    <script src="${ctx}/static/js/login/login.js"></script>
    <script src="${ctx}/static/system/jsConst.js"></script>
    <script src="${ctx}/static/system/common.js"></script>
    <script>jsConst.basePath="${ctx}/";</script>
    
    
    
    
</head>

<body>

<script>
<%-- var combobox_jy = <%=CodeFacade.loadCode4ComboJson(GroupKeyConst.GROUP_CODE_KEY_JY, 2, SystemConst.UNIT_CODE_KEY_PRE,SystemConst.UNIT_CODE_KEY_CENTER)%>;//从编码表中取监狱 --%>

/* console.log(combobox_jy); */

</script>

<div class="wrapper clearfix">
    <div class="content">
        <div class="box clearfix">
           <%--  <img src="${ctx}/static/images/show.png" class="show"> --%>

            
            
            <div class="login-panel">
	<div style="margin: 5% 10%;">
		<h1>单点跳转测试入口</h1>
		<table class="login-tab" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="td-bb td-br">登录用户：</td>
				<td class="td-bb" style="width: 70%; position: relative;">
					<span style="position: relative;">
						<input id="txtUserName" class="txt" type="text" value="" placeholder="请输入用户名...">
						<!-- <input id="txtUserName" class="txt" type="text" value="zxh|新收犯监狱" placeholder="请输入用户名..."> -->
						<!-- <input id="txtUserName" class="txt" type="text" value="32112319630424004X" placeholder="请输入用户名..."> -->
						<!-- <input id="txtUserName" class="txt" type="text" value="szjy01|苏州监狱" placeholder="请输入用户名..."> -->
						<span class="select-btn"></span>
					</span>
					<div class="users-panel">
						<div class="users"></div>
					</div>
				</td>
				<td class="td-bb">
					<span data-id="rememberAccount" class="on-off" data-status="1" data-on-msg="开" data-off-msg="关" title="记住账号"></span>
				</td>
			</tr>
			<tr>
				<td class="td-bb td-br">登录密码：</td>
				<td class="td-bb">
					<input id="txtPassword" class="txt" type="password" value="000000" placeholder="请输入登录密码...">
				</td>
				<td class="td-bb"></td>
			</tr>
		</table>
		
		<div align="center">
			<input type="button" class="login-btn box" style="margin-left: 300px;" onclick="login(1)" value="登 录">
			<a href="javascript:logout()">登出</a>
		</div>
	</div>
</div>
            
            
            
            
            
            
            
        </div>
    </div>
    <div class="foot">
     
    </div>
</div>
<script>
/*
 * 登录
 */

var verifyCode_ ="";
function login (flag) {
	var userName = $('#txtUserName').val();
	if(userName.indexOf("|") == -1){
		userName = userName + '|南京女子监狱' ;//测试环境20171016
	}
	var password = $('#txtPassword').val();
	
	var accounts = null;

	if (!userName) {
		_alert('请输入用户名!');
		return;
	}
	if (!password) {
		_alert('请输入登录密码!');
		return;
	}
	var loginMode  = flag;


	// 1.用户密码登陆
	if (loginMode == 1) {
		/* ajaxTodo('loginCtrl/encode', {'data': userName + '&' + password + '&' + mapMode + "&" + playFlash},
			function (data) {
				if (data) {
					_href('mode=1&' + data,flag);
				} else {
					_alert("登录失败：数据加密错误!");
				}
			}
		);
		 */
		var ur = '${ctx}/lg/loginCtrl/login';
		$.ajax({
			type : 'post',
			url : ur,			
			data : {'args': userName,'verifyCode': verifyCode_},	// encodeURIComponent(encodeURIComponent(userName)
			dataType : 'json',
			success : function(data) {
				
				var user = null;
				if (data.result) {
					
					user = data.userBean;

					// 判断用户是否使用一体分控
					if (user.userAio != 0) {
						location.href = "page/AIO/main.jsp?" + loginParmas;
						return;
					}
					initJSConstUserBean(user);
					//alert(jsConst.USER_NAME+":单点成功");
					window.location.href= "${ctx}/portal/shouye";
					
				}else{
					
					if (data.respCode == '1005') {
						if(loginMode == 1){
							if (confirm(data.respDesc + '，是否强制登录?')) {
								//args.verifyCode = data.verifyCode;
								//_login(args, mapMode, playFlash);
								verifyCode_ = data.verifyCode;
								login(1);
								
							} else {
								verifyCode_ ="";//重置强制验证码
								//loginFault();
							}
						}else{
							//args.verifyCode = data.verifyCode;
							//_login(args, mapMode, playFlash);
							verifyCode_ = data.verifyCode;
							login(1);
						}
					} else {
						alert(data.respDesc);
						//loginFault('数据库连接失败，请检查网络是否正常?');
						//loginFault(data.respDesc + '（错误码:' + data.respCode + '）');
					}
					
					
					
					
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(textStatus);
			}
		});
	}

	// 2.用户令牌登录
	if (loginMode == 2) {
		ajaxTodo('loginCtrl/getToken', {'userName': userName, 'password': password},
			function (token) {
				if (token) {
					_href(token + "&" + mapMode + "&" + playFlash,flag);
				} else {
					_alert('获取登录令牌失败!');
				} 
			}
		);
	}
}
//initUserInfo();
</script>
</body>
</html>
