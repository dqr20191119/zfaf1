<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String zbId = request.getParameter("zbId");
	String lx = request.getParameter("lx");
	 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>罪犯改造质量考核评价明细</title>
		<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/wdgz/style/js/echarts.min.js"></script>
	<link rel="stylesheet" href="${ctx}/static/wdgz/style/css/body.css">
    <link rel="stylesheet" href="${ctx}/static/wdgz/style/css/common.css">
	<script>
	 	var zbid = '<%=zbId%>';
	 	var lx = '<%=lx%>';
	 	 
	 	var list = null;
		var jg = null;
		var bfzjs = 3.33;
	 	var url = "${ctx}/wdgz/getwdgzMx?zbId="+zbid+"&lx="+lx;
	 	$(function(){
     			$.ajax({
				dataType: "json",
				type: "POST",
				async:false,
				url:url,
				success: function(data){
					console.log(data);
				       if((data.listjg).length>0){
						list=data;
						jg = data.listjg;
						var zzgzJcfHz = list.zzgzJcf;
						var zzgzJlfhz = list.zzgzJlf;
						var zzgzHz = list.zzgzZf;
						var jggzJcfHz = list.jggzJcf;
						var jggzJlfhz = list.jggzJlf;
						var jggzHz = list.jggzZf;
						var jygzJcfHz = list.jygzJcf;
						var jygzJlfhz = list.jygzJlf;
						var jygzHz = list.jygzZf;
						var whgzJcfHz = list.whgzJcf;
						var whgzJlfhz = list.whgzJlf;
						var whgzHz = list.whgzZf;
						var ldgzJcfHz = list.ldgzJcf;
						var ldgzJlfhz = list.ldgzJlf;
						var ldgzHz = list.ldgzZf;
						var qbzf = list.qbzf;
						var zfdbColor="#3cff24";
						var zfshdb = "达标";
						var zhfz = " <div style='float: left;color:white ;font-size: 30px;'>分值：</div> "+
				 "<div  style='float: left;color: #cdd487;font-size: 30px;' >折前分值："+qbzf+"&nbsp;,折后分值："+Math.round(parseFloat(qbzf)*bfzjs)+"&nbsp;&nbsp;</div> ";
						if(parseFloat(qbzf)<18||parseFloat(zzgzJcfHz)<6||parseFloat(jggzJcfHz)<2||parseFloat(jygzJcfHz)<2||parseFloat(whgzJcfHz)<2||parseFloat(ldgzJcfHz)<2||
						parseFloat(zzgzHz)<6||parseFloat(jggzHz)<3||parseFloat(jygzHz)<3||parseFloat(whgzHz)<3||parseFloat(ldgzHz)<3){
						   zfdbColor = "red";
						   zfshdb = "未达标";
						   zhfz = "";
						}
						$("#hfjyjqxm2").html("<div style='float: left;color: white;font-size: 30px;'>"+list.jyName+"&nbsp;&nbsp;</div>"+
						 " <div style='float: left;color: white;font-size: 30px;'>"+list.jqName+"&nbsp;&nbsp;</div>"+
						 "<div style='float: left;color: white;font-size: 30px;'> "+list.zfXm+"&nbsp;&nbsp;</div>"+
							""+zhfz+""+
					 " <div style='float: left;color: white;font-size: 30px;'>结果：</div> "+
					 " <div  style='float: left;font-size: 30px;color: "+zfdbColor+";'>"+zfshdb+"</div>");
						 }
						 var kplxflg=0;
						 var mxjgg = "";
						 var fzlxjcf=0;
						  var fzlxjlf=0;
						 for(var i = 0 ;i<jg.length;i++){
						    var jgmx = jg[i];
						    var bt = "";
						    var kplx = jgmx.WDGZ_TYPE_CODE;
						    var hzf = "";
						    var jcf = "";
						    var jlf = "";
						    if(kplx==lx){
							    if(kplx=='ZZGZ'){
							    	hzf=zzgzHz;
							    	bt="政治改造";
							    	jcf = zzgzJcfHz;
							    	jlf = zzgzJlfhz;
							    }else  if(kplx=='JGGZ'){
							  	 	 hzf=jggzHz;
							   	 	bt="监管改造";
							   	 	jcf =jggzJcfHz;
							    	jlf = jggzJlfhz;
							    }else  if(kplx=='JYGZ'){
							    hzf=jygzHz;
							  	  bt="教育改造";
							  	  	jcf = jygzJcfHz;
							    	jlf = jygzJlfhz;
							    }else if(kplx=='WHGZ'){
							    hzf=whgzHz;
							   	 bt="文化改造";
							   	 jcf = whgzJcfHz;
							    	jlf = whgzJlfhz;
							    }else  if(kplx=='LDGZ'){
							    hzf=ldgzHz;
							  	  bt="劳动改造";
							  	  jcf = ldgzJcfHz;
							    	jlf = ldgzJlfhz;
							    }
							    if(kplxflg==0){
							        mxjgg=mxjgg+"<tr><td style=' text-align: left;'><div  style=' font-size: 30px;color: #f9e68b;float: left'>"+bt+"&nbsp;&nbsp;&nbsp;&nbsp;</div><div  style=' font-size: 20px;color: #EEAD0E;float: left;    margin-top: 10px;'>基础分（"+jcf+"）+&nbsp;&nbsp;奖励分（"+jlf+"）=&nbsp;&nbsp;该项得分:（"+hzf+"）</div></td></tr>";
							    }
							    kplxflg++;
							    var kpnr = jgmx.WDGZ_MX;
							    var kpfz = jgmx.WDGZ_MX_FZ;
							    var fzlx = jgmx.WDGZ_MX_TYPE;
							    var fzlxmc = "";
							    if(fzlx=="JCF"){
							   		 fzlxmc="基础分条款";
							   		 fzlxjcf++;
							   		 fzlxjlf=0;
							    }else if (fzlx=="JLF"){
							   		 fzlxmc="奖励分条款";
							   		 fzlxjlf++;
							   		 fzlxjcf=0;
							    }
							    if(fzlxjcf=="1"||fzlxjlf=="1"){
							    mxjgg=mxjgg+" <tr><td style='font-size: 25px;color: #C1FFC1;'>"+fzlxmc+"</td></tr>";
							    }
							    
							   mxjgg=mxjgg+"<tr><td style='font-size: 20px;color: white;'><div style='float: left;width:85%;'>"+kpnr+"</div>&nbsp;&nbsp;&nbsp;&nbsp;<div style='float: left;color:#8beff9;'>（分值："+kpfz+"）</div></td></tr>" 
						     }
						 }
						 $("#khmx").html(mxjgg);
					}
				});
     		
     		
	 });
	</script>
	</head>
	<body  >
			<div style="background:url(style/images/bg.png) repeat center top; text-align:center; font-size:14px; background-size: 100%; color:#000; font-family:'微软雅黑';width:100%; height:100%;min-height: 800px">
				<!-- 放入正题 -->
				<div id="PanelAll">
					<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				      <tr style="height: 15%">
				        <td valign="top"   >
				        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
				              <tr >
				                <td > &nbsp;</td>
				                <td align="center" style=" font-size: 38px;color: white;">
				                 <div class="PanelHead" style="font-family: '黑体'">
				               		 <img alt="" style="padding-bottom: 10px;  padding-right: 20px;" src="${ctx}/static/wdgz/style/images/logoIcon.png">
				               		 罪&nbsp;犯&nbsp;改&nbsp;造&nbsp;质&nbsp;量&nbsp;考&nbsp;核&nbsp;评&nbsp;价
				                </div>
				                </td>
				                <td></td>
				              </tr>
				             <!--  <tr style="height: 30px">
				                 <td>&nbsp;</td>
				                 <td>&nbsp;</td>
				                 <td>&nbsp;</td>
				              </tr>
				               <tr style="height: 30px">
				                 <td>&nbsp;</td>
				                 <td>&nbsp;</td>
				                 <td>&nbsp;</td>
				              </tr> -->
				              <tr>
				                <td>&nbsp;</td>
				                <td  id="hfjyjqxm2">
				                
				                </td>
				                <td>&nbsp;</td>
				              </tr>
				              
				            </table>
				        </td>
				      </tr>
				    <!--   <div class="BoxTable"> -->
				      <tr style="height: 75%">
				      	 <td valign="top" style="width: 100%">
				      	  <div class="BoxTable" style="height: 630px">
							<table style="height: 100%;width: 100%;" cellpadding="0" cellspacing="0" id="khmx" >
							    
							</table>
						  </div>
						 </td> 
					 
				      </tr>
				      
				      
				    </table>
					
				</div>
				</body>
			</div>
</body>
</html>