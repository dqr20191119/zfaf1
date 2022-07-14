<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
	<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js" type="text/javascript"></script>
	<script src="${ctx}/static/bj-cui/cui/cui.js" type="text/javascript"></script>
	<script src="${ctx}/static/system/common.js"></script>
	<script src="${ctx}/static/system/jsConst.js"></script>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>插件安装</title>
<script>
	function MM_swapImgRestore() { //v3.0
		var i, x, a = document.MM_sr;
		for (i = 0; a && i < a.length && (x = a[i]) && x.oSrc; i++)
			x.src = x.oSrc;
	}
	function MM_preloadimage() { //v3.0
		var d = document;
		if (d.image) {
			if (!d.MM_p)
				d.MM_p = new Array();
			var i, j = d.MM_p.length, a = MM_preloadimage.arguments;
			for (i = 0; i < a.length; i++)
				if (a[i].indexOf("#") != 0) {
					d.MM_p[j] = new Image;
					d.MM_p[j++].src = a[i];
				}
		}
	}

	function MM_findObj(n, d) { //v4.01
		var p, i, x;
		if (!d)
			d = document;
		if ((p = n.indexOf("?")) > 0 && parent.frames.length) {
			d = parent.frames[n.substring(p + 1)].document;
			n = n.substring(0, p);
		}
		if (!(x = d[n]) && d.all)
			x = d.all[n];
		for (i = 0; !x && i < d.forms.length; i++)
			x = d.forms[i][n];
		for (i = 0; !x && d.layers && i < d.layers.length; i++)
			x = MM_findObj(n, d.layers[i].document);
		if (!x && d.getElementById)
			x = d.getElementById(n);
		return x;
	}

	function MM_swapImage() { //v3.0
		var i, j = 0, x, a = MM_swapImage.arguments;
		document.MM_sr = new Array;
		for (i = 0; i < (a.length - 2); i += 3)
			if ((x = MM_findObj(a[i])) != null) {
				document.MM_sr[j++] = x;
				if (!x.oSrc)
					x.oSrc = x.src;
				x.src = a[i + 2];
			}
	}
</script>
</head>
<body onload="MM_preloadimage('image/djxz1.png')">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			
			<td style="padding: 5px;">
				<table width="100%" height="160" border="0" cellspacing="0"
					cellpadding="0" style="border: 1px #43e2ff solid;">
					<tr>
						<td
							style="font-size: 16px; font-weight: bolder; padding-left: 10px;"
							height="40">CA登陆认证</td>
					</tr>
					<tr>
						<td
							style="line-height: 25px; font-size: 14px; padding-left: 10px;"
							valign="top"><strong>使用方法：</strong>解压<strong>证书助手</strong>安装
							<br /> <strong>用途：</strong>用于CA认证登陆等。</td>
					</tr>
					<tr>
						<td align="right" style="padding: 0px 10px 10px 0px;"
							valign="bottom"><a
							href="${ctx}/static/upload/BJCA.rar"
							>下载</a></td>
					</tr>
				</table>
			</td>
			<td style="padding: 5px;">
				
			</td>
		</tr>
          
	</table>
</body>
</html>