
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@page import="cn.org.bjca.security.SecurityEngineDeal"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%
	SecurityEngineDeal.setRootPath("C:\\BJCAROOT");
	SecurityEngineDeal sed = null;
	sed = SecurityEngineDeal.getInstance("SecXV3Default");
	String strServerCert = sed.getServerCertificate();
	String strRandom = sed.genRandom(24);
	String strSignedData = sed.signData(strRandom.getBytes());

	session.setAttribute("Random", strRandom);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta charset="utf-8">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<title></title>
	<style type="text/css">
	.font1{
		font-size: 22px;
	    color: white;
	   
	}
	.Caclass{
	    padding: 10px;
	    background: rgba(1, 76, 96, 0.5);
	    border-radius: 4px;
	    border: 1px solid #71c7d5;
	    color: #fff;
	    padding-left: 50px;
	    font-size: 20px;
	    width: 235px;
	    height: 50px;
	}
	
	</style>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/login.css" />
	
	<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
	<script src="${ctx}/static/system/common.js"></script>
	<script src="${ctx}/static/system/jsConst.js"></script>
	<script>jsConst.basePath="${ctx}/";</script>
</head>

<SCRIPT language=JavaScript src="${ctx}/static/system/date.js"></SCRIPT>

<SCRIPT ID=clientEventHandlersJS LANGUAGE=javascript>
			var strServerSignedData = "<%=strSignedData%>";
			var strServerRan = "<%=strRandom%>";
			var strServerCert = "<%=strServerCert%>";
			function LoginForm_onsubmit() 
			{
				var strContainerName =  LoginForm.UserList.value;
				var strPin = LoginForm.UserPwd.value;
				LoginForm.strRandom.value = "<%=strRandom%>";
				var ret = Login("LoginForm", strContainerName, strPin,"${ctx}/lg/loginCtrl/logincacaca");
				if (ret) {
					LoginForm.UserPwd.value = "";
					return true;
				} else {
					return false;
				}
				
			}
			
			
			 
			
</SCRIPT>
<body>
	<div class="mainCon">
		<div class="container">
			<div class="titleLogo">
				<img src="${ctx}/static/bj-cui/img/logo.png" class="logoPic" alt="" />
				<img src="${ctx}/static/bj-cui/img/title.png" class="titlePic" alt="" />
			</div>
			<div class="loginCon clearfix">
				<form method="post" ID="LoginForm" name="LoginForm"
								action="${ctx}/lg/loginCtrl/logincacaca" onsubmit="return LoginForm_onsubmit()">

								<table id="bs" width="90%" border="0" align="center"
									cellpadding="0" cellspacing="0">
									<tr>
										<td height="15" colspan="2"></td>
									</tr>
									<tr>
										<td width="120" class="font1">选择证书</td>

										<td><div class="loginIpt"><img src="${ctx}/static/bj-cui/img/username.png" alt="" /><select id="UserList" name="UserList" class="Caclass">
										</select></div></td>

									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td class="font1">CA口令</td>
										
										<td><div class="loginIpt">
										<img class="passWordLogo" src="${ctx}/static/bj-cui/img/password.png" alt="" />
										<input type="password" name="pwd1" id="UserPwd"
											class="Caclass"></div></td>
									</tr>
									<tr>
										<td>&nbsp;</td>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td colspan="2" width="65"><div class="login-button login-input">
											<button >登<i></i>录</button>
										</div>
									</td>
										
									</tr>
								</table>
								<input type="hidden" ID="UserSignedData" name="UserSignedData">
								<input type="hidden" ID="UserCert" name="UserCert"> <input
									type="hidden" ID="ContainerName" name="ContainerName">
								<input type="hidden" ID="strRandom" name="strRandom">
							</form>
							
							
				<div class="loginLogo clearfix">
					<div class="pull-left CA caPluIcon">
						<div class="caPluPic caPluPic1" onclick="zhuanYh()"></div>
						<p>账号登陆</p>
					</div>

					<div class="pull-right caPluIcon">
						<div class="caPluPic caPluPic2" onclick="fujian()"></div>
						<p>安装插件</p>
					</div>
				</div>
			</div>
			<div class="bottomPic"></div>
		</div>
	</div>
	<script>
		 
		function zhuanYh(){
			
			window.location.href=jsConst.basePath+"lg/loginCtrl/ln";
		}
function fujian(){
			
			//window.location.href=jsConst.basePath+"/lg/loginCtrl/fujian";
			 window.open(jsConst.basePath+"/lg/loginCtrl/fujian");
		}
	</script>
	
</body>
<SCRIPT type="text/javascript" src="${ctx}/static/system/ZWXTXSAB.js"></SCRIPT>
	<SCRIPT LANGUAGE=JAVASCRIPT>
		SetUserCertList("LoginForm.UserList");
	</SCRIPT>
</html>