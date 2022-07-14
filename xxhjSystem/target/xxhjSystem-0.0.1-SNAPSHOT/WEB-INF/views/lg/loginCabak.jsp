<%@page contentType="text/html; charset=GBK"%>
<%@page import="java.util.*,java.io.FileInputStream"%>
<%@page import="cn.org.bjca.security.SecurityEngineDeal"%>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);

	String ranStr = (String) session.getAttribute("Random");

	SecurityEngineDeal sed = null;
	sed = SecurityEngineDeal.getInstance("SM2");
%>
<%
		
					//获得登陆用户cert
					String clientCert = "MIIFQjCCBCqgAwIBAgIKGzAAAAAAAEpq5jANBgkqhkiG9w0BAQUFADBSMQswCQYDVQQGEwJDTjENMAsGA1UECgwEQkpDQTEYMBYGA1UECwwPUHVibGljIFRydXN0IENBMRowGAYDVQQDDBFQdWJsaWMgVHJ1c3QgQ0EtMTAeFw0xODA5MDIxNjAwMDBaFw0xOTA5MDMxNTU5NTlaMIGUMQswCQYDVQQGEwJDTjEtMCsGA1UECgwk5YyX5Lqs5pWw5a2X6K6k6K+B6IKh5Lu95pyJ6ZmQ5YWs5Y+4MRIwEAYDVQQDDAnotbXkuIDmnYMxJTAjBgkqhkiG9w0BCQEWFnpoYW95aXF1YW5AYmpjYS5vcmcuY24xGzAZBgoJkiaJk/IsZAEpDAsxODgwMDE0NzgxMjCBnzANBgkqhkiG9w0BAQEFAAOBjQAwgYkCgYEA0OP1eOdc9AIxSC/dY/jKyue3ruL2TxHoH4z0m/LXnHlPdBh/vmkf386l3TEzM5/330kAiCHy/bRppK1A6NkslwiOB82yPXDlkQE4Avv9Vyhxxv1PnbS94zdLcuFRQgQi4ciCR3nUjc2PwMJPRsAsAX8OwLbjs/PEIpTow3pohrUCAwEAAaOCAlkwggJVMB8GA1UdIwQYMBaAFKw77K8Mo1AO76+vtE9sO9vRV9KJMB0GA1UdDgQWBBRe/s4OV/VtVI0GjnUahSQ1AWIVPzALBgNVHQ8EBAMCBsAwga8GA1UdHwSBpzCBpDBtoGugaaRnMGUxCzAJBgNVBAYTAkNOMQ0wCwYDVQQKDARCSkNBMRgwFgYDVQQLDA9QdWJsaWMgVHJ1c3QgQ0ExGjAYBgNVBAMMEVB1YmxpYyBUcnVzdCBDQS0xMREwDwYDVQQDEwhjYTNjcmwyNTAzoDGgL4YtaHR0cDovL2xkYXAuYmpjYS5vcmcuY24vY3JsL3B0Y2EvY2EzY3JsMjUuY3JsMAkGA1UdEwQCMAAwEQYJYIZIAYb4QgEBBAQDAgD/MB0GBSpWCwcBBBRTRjM3MDY4NjE5OTQwMTAyNzAxODAdBgUqVgsHCAQUU0YzNzA2ODYxOTk0MDEwMjcwMTgwIAYIYIZIAYb4RAIEFFNGMzcwNjg2MTk5NDAxMDI3MDE4MBsGCCpWhkgBgTABBA8xMDIwMDAwMTE3ODg5NjYwJQYKKoEchu8yAgEEAQQXMkNAU0YzNzA2ODYxOTk0MDEwMjcwMTgwKgYLYIZIAWUDAgEwCQoEG2h0dHA6Ly9iamNhLm9yZy5jbi9iamNhLmNydDAPBgUqVhUBAQQGMTAwMDgwMEAGA1UdIAQ5MDcwNQYJKoEchu8yAgIBMCgwJgYIKwYBBQUHAgEWGmh0dHA6Ly93d3cuYmpjYS5vcmcuY24vY3BzMBMGCiqBHIbvMgIBAR4EBQwDNTIwMA0GCSqGSIb3DQEBBQUAA4IBAQBdH1P2feqvOnKBQrETyEbm8oVlcHNpPlytBfi05co6cooArgbE3VXMQEzbMXES702G6QvMPBdwW0IABb+05RK44tkGltEYZUbXumHGriXM3U3e238Gy3YOUEEE/0I/DX7zAHBrFgjVtVMWS7y92cqfwZrhN9c59Vk2WWY4lmx35Xt0G2NGdY7XRjFfquGTdr79jPwbu+gvfIGSQ3hYpXRUvwPdoC7Jbm9dVBhbFhHAwyAUyHuTPL3ozS0Ez0xCdV6IAzWl4CNb50aR/o1+o+/aPMzGOAOd0aru9sqwZlSq8fhr9mi+UDljOSOGnKJkElinyMV18NWQTW/yEMpMmByB";
					//request.getParameter("UserCert");
					String UserSignedData = request.getParameter("UserSignedData");
					String ContainerName = request.getParameter("ContainerName");
					String certPub = sed.getCertInfo(clientCert, 8);
					//System.out.println(clientCert);
					//验证服务器证书有效期
					java.text.SimpleDateFormat date = new java.text.SimpleDateFormat(
							"yyyy/MM/dd");
					String dateNow = date.format(new Date());
					Date datenow = date.parse(dateNow);
					String servercert = sed.getServerCertificate();
					String servercertdate = sed.getCertInfo(servercert, 12);

					Date scertdate = date.parse(servercertdate);
					
					try {

						int retValue = sed.validateCert(clientCert);

						if (retValue == 1) {

							session.setAttribute("ContainerName", ContainerName);

							String uniqueIdStr = "";
							try {
								uniqueIdStr = sed.getCertInfo(clientCert, 17);
							} catch (Exception e) {
								e.printStackTrace();
							}
							session.setAttribute("UniqueID", uniqueIdStr);

							String uniqueId = "";
							try {
								String type=sed.getCertInfo(clientCert, 31);
								if(type=="ECC"){
									uniqueId = sed.getCertInfoByOid(clientCert,
											"1.2.156.112562.2.1.1.1");
								}else{
									//获得登陆用户ID
								uniqueId = sed.getCertInfoByOid(clientCert,
											"2.16.840.1.113732.2");
								}

							} catch (Exception e) {
								e.printStackTrace();
							}
							request.setAttribute("jgz", 1);
							request.setAttribute("uniqueId", uniqueId);
						} else {
							request.setAttribute("jgz", 0);
						}
					} catch (Exception ex) {
						request.setAttribute("jgz", 0);
					}

					//验证客户端签名
					/* try {
						if (sed.verifySignedData(clientCert, ranStr, UserSignedData)) {

						} else {
							out.println("<h3>验证客户端签名错误！</h3>");
							return;
						}
					} catch (Exception e) {
						out.println("<p><h3>验证客户端签名错误:" + e.getMessage() + "</h3><p>");
						return;
					} */
				%>
<html>

<head>
<title></title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/login.css" />
	
	<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
	<script src="${ctx}/static/system/common.js"></script>
	<script src="${ctx}/static/system/jsConst.js"></script>
	<script>jsConst.basePath="${ctx}/";</script>
</head>
<body>
<input id="jgz" name="jgz" type="text" value="${jgz }">
<input id="uniqueId" name="uniqueId" type="text" value="${uniqueId }">
</body>
<script type="text/javascript">
$(function () {
	var uniqueId = "<%uniqueId%>";
	var jgz = "<%jgz%>";
	var ur = '${ctx}/lg/loginCtrl/logincac';
	$.ajax({
		type : 'post',
		url : ur,			
		data : {'jgz': jgz,'uniqueId': uniqueId},	// encodeURIComponent(encodeURIComponent(userName)
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
	
	
});

</script>
</html>

