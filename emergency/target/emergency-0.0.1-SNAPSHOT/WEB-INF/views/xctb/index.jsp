<!DOCTYPE html>
<%@ page language="java" import="java.util.*,java.lang.String,java.lang.Math" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="cache-control" content="no-store, no-cache, must-revalidate">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="0">
<title>监控巡查通报编辑</title>

<script src="${ctx}/static/system/jsConst.js"></script>
<script>
	jsConst.basePath = "${ctx}/";
</script>
<script src="${ctx}/static/jquery-cui/js/jquery-1.11.3.min.js"></script>
<script src="${ctx}/static/system/common.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/static/utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/static/utf8-jsp/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${ctx}/static/utf8-jsp/lang/zh-cn/zh-cn.js"></script>
<style>
.dialog-buttons{
	text-align:right;
	line-height:35px;
	background:#F0F0F0;
	border-top:1px solid #ccc;
}
</style>
</head>
<body id="warp-main"  style="width:800px;margin:auto;">
	<%  List list=null; 
		Map map=null;
		List imageList=null; 
		Map imageMap=null;
        if(request.getAttribute("monitorList")!=null){  
            list = (List)request.getAttribute("monitorList");
        }
        String inoInspectPhase = (String)request.getAttribute("inoInspectPhase");
        String inoInspectBj = (String)request.getAttribute("inoInspectBj");
        String inspectTime = (String)request.getAttribute("inspectTime");
    %>  
	<textarea id="logContent" style="width:98%;height:800px;" name="content">
	<p align="center" class="MsoNormal" style="text-align: center;"><span style="color: red; font-family: 华文行楷; font-size: 69pt;">监控巡查通报<span lang="EN-US"><o:p></o:p></span></span></p>
	<p align="center" class="MsoNormal" style="text-align: center;"><span style="font-family: 宋体; font-size: 16pt; mso-ascii-font-family: Calibri; mso-hansi-font-family: Calibri;">（第</span><span lang="EN-US" style="font-size: 16pt;"><%=inoInspectPhase%></span><span style="font-family: 宋体; font-size: 16pt; mso-ascii-font-family: Calibri; mso-hansi-font-family: Calibri;">期）</span><span lang="EN-US" style="font-size: 16pt;"><o:p></o:p></span></p>

	<p class="MsoNormal" style="margin-bottom:0px;"><span style="font-family: 宋体; font-size: 16pt; mso-ascii-font-family: Calibri; mso-hansi-font-family: Calibri;">${cusName}指挥中心</span><span lang="EN-US" style="font-size: 16pt;"><span style="mso-spacerun: yes;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</span><%=inspectTime%></span><span lang="EN-US" style="font-size: 16pt;"><o:p></o:p></span></p>


	<p style="width: 100%; margin-top:0px; color: rgb(255, 0, 0); border-bottom-color: red; border-bottom-width: 2px; border-bottom-style: solid;">
    &nbsp;
	</p>

    <p align="center" class="MsoNormal" style="text-align: center;"><span style="font-family: 仿宋; font-size: 22pt;">${cusName}指挥中心每日监控巡查通报<span lang="EN-US"><o:p></o:p></span></span></p>
	<p class="MsoNormal" style="text-indent: 32pt; mso-char-indent-count: 2.0;"><span style="font-family: 仿宋; font-size: 16pt;">现将<%=inspectTime%>指挥中心监控巡查发现问题通报如下,请以下单位认真整改。</span></p>


	 <% 
	 	if(list!=null){
	 		for(int i=0;i<list.size();i++){
	 			map = (Map)list.get(i);
	 			String mdoAddr = (String)map.get("mdoAddr");
	 			String mdoProblem = (String)map.get("mdoProblem");
	 			imageList = (List)map.get("imageList");
	 			%>
				<p style="text-indent: 32pt; mso-char-indent-count: 2.0;">
					<span style="font-family: 仿宋; font-size: 16pt;">
						<%=i+1%>、<%=mdoAddr%>：<%=mdoProblem%>
					</span>
					<div align="center" sytle="text-align:center;width:100%;">
					<%
						if(imageList!=null){
					 		for(int j=0;j<imageList.size();j++){
					 			imageMap = (Map)imageList.get(j);
					 			String einFileName = (String)imageMap.get("EIN_FILE_NAME");
					 			String einFtpPath = (String)imageMap.get("EIN_FTP_PATH");
					 			String einFtpPrefix = (String)imageMap.get("EIN_FTP_PREFIX");
					 			String imageUrl = "/resource/"+einFtpPrefix+"/"+einFtpPath+"/"+einFileName+"?temp=" + Math.random();
					 			%>
								<img sytle="margin:0 auto;" height="400px" width="550px" src="<%=imageUrl%>" onclick="" />
								<br/>
								<%
					 		}
					 	}
					%>
					</div>	
				</p>
				<%
	 		}
	 	}
	 %>


	<p class="MsoNormal"><span style="font-family: 仿宋; font-size: 16pt;">抄送：监狱各领导<span lang="EN-US"><o:p></o:p></span></span></p>
	<p class="MsoNormal"><span style="font-family: 仿宋; font-size: 16pt;">发送：狱政科、狱侦科、警务督察室、各监区<span lang="EN-US"><o:p></o:p></span></span></p>

	<p style="width: 100%; color: rgb(255, 0, 0); border-bottom-color: red; border-bottom-width: 2px; border-bottom-style: solid;">
    	&nbsp;
	</p>



  	<p class="MsoNormal"><span style="font-family: 仿宋; font-size: 16pt;">编校<span lang="EN-US">:</span><%=inoInspectBj%><span lang="EN-US"><span style="mso-spacerun: yes;">&nbsp;
 	</span><span style="mso-spacerun: yes;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span style="mso-spacerun: yes;">&nbsp;</span><span style="mso-spacerun: yes;">&nbsp;&nbsp;</span><span style="mso-spacerun: yes;">&nbsp;</span><span style="mso-spacerun: yes;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><%=inspectTime%>编发</span><span lang="EN-US"><o:p></o:p></span></span></p>
	</textarea>
	<div class="dialog-buttons" style="margin-top:10px;">
		<button type="button" onclick="confirm()">保存</button>
		<button type="button" onclick="xctbClose()">关闭</button>
	</div>
</body>
<script>
	
	window.UEDITOR_HOME_URL="${ctx}/utf8-jsp/";
	var editor = new baidu.editor.ui.Editor();
	editor.render("logContent");
	function xctbClose(){
		window.close();
	}
	function confirm(){
		var inspectId = '${inspectId}';
		var cusNumber = '${cusNumber}';
		var inspectHTML = editor.getContent();
		console.log(inspectId);
		console.log(inspectHTML);
		$.ajax({
			type : 'post',
			url : '${ctx}/inspect/createInspectFile.json',
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {
				"cusNumber":cusNumber,
				"inspectId":inspectId,
				"inspectHTML":inspectHTML
			},
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					alert("保存成功");
				} else {
					alert("保存失败："+data.msg);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("保存失败");
			}
		}); 
	}
	
</script>
</html>
