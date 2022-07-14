<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>

<head>
	<meta charset="utf-8">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<title></title>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/login.css" />
	
	<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
	<script src="${ctx}/static/system/common.js"></script>
	<script src="${ctx}/static/system/jsConst.js"></script>
	<script>jsConst.basePath="${ctx}/";</script>
</head>
<body>
	<div class="mainCon">
		<div class="container">
			<div class="titleLogo">
				<img src="${ctx}/static/bj-cui/img/logo_1100.png" class="logoPic" alt="" />
				<img src="${ctx}/static/bj-cui/img/title.png" class="titlePic" alt="" />
			</div>
			<div class="loginCon clearfix">
				<div class="loginForm pull-left">
					<img src="${ctx}/static/bj-cui/img/userPic.png" id="useTxt" alt="" />
					<div id="tipMsg">用户名不能为空！</div>
					<div class="loginIpt">
						<img src="${ctx}/static/bj-cui/img/username.png" alt="" />
						<input type="text" id="loginUser" value="用户名" class="login-text" />
					</div>
					<div class="loginIpt">
						<img class="passWordLogo" src="${ctx}/static/bj-cui/img/password.png" alt="" />
						<input type="password" id="loginPassword" value="000000" class="login-text" />
					</div>
					<div class="login-remember">
						<span id="clickCheckbox" class="login-checkboxs">
							<span class="login-checkbox">
								<input type="checkbox" id="loginCheckbox">
								<span></span>
							</span>
							<span>记住密码</span>
						</span>

					</div>
					<div class="login-button login-input">
						<button onclick="login()">登<i></i>录</button>
					</div>
				</div>
				<div class="loginLogo clearfix">
					<div class="pull-left CA caPluIcon">
						<!--<img src="img/caCertific.png" alt="" />-->
						<div class="caPluPic caPluPic1" onclick="zhuanCa()"></div>
						<p>CA认证</p>
					</div>

					<div class="pull-right caPluIcon">
						<!--<img src="img/Plugin.png" alt="" />-->
						<div class="caPluPic caPluPic2" onclick="fujian()"></div>
						<p>安装插件</p>
					</div>
				</div>
			</div>
			<div class="bottomPic"></div>
		</div>
	</div>
	<script>
		$(function () {
			$("#clickCheckbox").addClass("login-checkboxs");
			$("#clickCheckbox").children(".login-checkbox").children("input").prop("checked", true);
		});
		//记住密码选择范围js
		$("#clickCheckbox").click(function () {
			if ($(this).hasClass("login-checkboxs")) {
				$(this).removeClass("login-checkboxs");
				$(this).children(".login-checkbox").children("input").prop("checked", false);
			} else {
				$(this).addClass("login-checkboxs");
				$(this).children(".login-checkbox").children("input").prop("checked", true);
			}
		});
		var $tipMsg = $("#tipMsg");
		$("#loginUser").focus(function () {
			var $this = $(this);
			var thisValue = $this.val();
			$tipMsg.hide();
			if (thisValue == "用户名") {
				$this.val("");
			}
		});
		$("#loginUser").blur(function () {
			var $this = $(this);
			var userVal = $this.val();
			if (userVal == "") {
				$this.val("用户名");
			}
		})
		$("#loginPassword").focus(function () {
			var $this = $(this);
			var thisValue = $this.val();
			$tipMsg.hide();
			if (thisValue == "初始密码000000") {
				$this.val("");
			}
		});
		$("#loginPassword").blur(function () {
			var $this = $(this);
			var passWordVal = $this.val();
			if (passWordVal == "") {
				$this.val("初始密码000000");
			}
		});
		function login() {
			
			var userVal = $("#loginUser").val();
			var passwordVal = $("#loginPassword").val();
			if (!userVal || userVal == "用户名") {
				$tipMsg.show().html("请输入用户名!").css("top", "123px");
				return;
			}

			if (!passwordVal || passwordVal == "初始密码000000") {
				$tipMsg.show().html("请输入密码!").css("top", "184px");
				return;
			}
			
			var ur = '${ctx}/lg/loginCtrl/login';
			$.ajax({
				type : 'post',
				url : ur,			
				data : {'args': userVal,'verifyCode':"","passwordVal":passwordVal},	// encodeURIComponent(encodeURIComponent(userName)
				dataType : 'json',
				success : function(data) {
					
					var user = null;
					if (data.result) {
						
						user = data.userBean;
						console.log(user);
						// 判断用户是否使用一体分控
						if (user.userAio != 0) {
							location.href = "page/AIO/main.jsp?" + loginParmas;
							return;
						}
						initJSConstUserBean(user);
						//alert(jsConst.USER_NAME+":单点成功");
						window.location.href= "${ctx}/portal/zhshouye";
						
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
		function zhuanCa(){
			
			window.location.href=jsConst.basePath+"lg/loginCtrl/lnCa";
		}
		function fujian(){
			
			//window.location.href=jsConst.basePath+"/lg/loginCtrl/fujian";
			 window.open(jsConst.basePath+"/lg/loginCtrl/fujian");
		}
	</script>
</body>

</html>