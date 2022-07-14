<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	String jbxxId = request.getParameter("jbxxId");
	 
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>罪犯改造质量考核评价</title>
	<script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/wdgz/style/js/echarts.min.js"></script>
	<link rel="stylesheet" href="${ctx}/static/wdgz/style/css/body.css">
    <link rel="stylesheet" href="${ctx}/static/wdgz/style/css/common.css">
    
	<script>
	var  jbxxId = '<%=jbxxId%>';
	var url = "${ctx}/wdgz/getwdgz?jbxxId="+jbxxId;
	var list = null;
	var jg = null;
	var bfzjs = 3.33;
	$(function(){
     		$.ajax({
				dataType: "json",
				type: "POST",
				async:false,
				url:url,
				success: function(data){
					console.log(data);
				if(data.zfXm){
				var zzgzJcfHz = data.zzgzJcf;
				var zzgzJlfhz = data.zzgzJlf;
				var zzgzHz = data.zzgzZf;
				var jggzJcfHz = data.jggzJcf;
				var jggzJlfhz = data.jggzJlf;
				var jggzHz = data.jggzZf;
				var jygzJcfHz = data.jygzJcf;
				var jygzJlfhz = data.jygzJlf;
				var jygzHz = data.jygzZf;
				var whgzJcfHz = data.whgzJcf;
				var whgzJlfhz = data.whgzJlf;
				var whgzHz = data.whgzZf;
				var ldgzJcfHz = data.ldgzJcf;
				var ldgzJlfhz = data.ldgzJlf;
				var ldgzHz = data.ldgzZf;
				var qbzf = data.qbzf;
				var zfdbColor="#3cff24";
				var zfshdb = "达标";
				var zhfz = " <div style='float: left;color: white;font-size: 30px;'>分值：</div> "+
				 "<div  style='float: left;color: #cdd487;font-size: 30px;' >折前分值："+qbzf+"&nbsp;,折后分值："+Math.round(parseFloat(qbzf)*bfzjs)+"&nbsp;&nbsp;</div> ";
				if(parseFloat(qbzf)<18||parseFloat(zzgzJcfHz)<6||parseFloat(jggzJcfHz)<2||parseFloat(jygzJcfHz)<2||parseFloat(whgzJcfHz)<2||parseFloat(ldgzJcfHz)<3||
				parseFloat(zzgzHz)<6||parseFloat(jggzHz)<3||parseFloat(jygzHz)<3||parseFloat(whgzHz)<3||parseFloat(ldgzHz)<3){
				   zfdbColor = "red";
				   zfshdb = "未达标";
				   zhfz =  "";
				} 
				$("#hfjyjqxm").html("<div style='float: left;color: white;font-size: 30px;'>"+data.jyName+"&nbsp;&nbsp;</div>"+
				 " <div style='float: left;color: white;font-size: 30px;'>"+data.jqName+"&nbsp;&nbsp;</div>"+
				 "<div style='float: left;color: white;font-size: 30px;'> "+data.zfXm+"&nbsp;&nbsp;</div>"+
				 ""+zhfz+""+
				 " <div style='float: left;color: white;font-size: 30px;'>结果：</div> "+
				 " <div  style='float: left;font-size: 30px;color: "+zfdbColor+";'>"+zfshdb+"</div>");
				 
				 	 var jianyiyijian="";
				if(parseFloat(qbzf)<18||parseFloat(zzgzJcfHz)<6||parseFloat(jggzJcfHz)<2||parseFloat(jygzJcfHz)<2||parseFloat(whgzJcfHz)<2||parseFloat(ldgzJcfHz)<3||
				      parseFloat(zzgzHz)<6||parseFloat(jggzHz)<3||parseFloat(jygzHz)<3||parseFloat(whgzHz)<3||parseFloat(ldgzHz)<3){
				   if(parseFloat(zzgzJcfHz)<6||parseFloat(zzgzHz)<6){
				   		jianyiyijian=jianyiyijian+"政治改造、";
				   } if(parseFloat(jggzJcfHz)<2||parseFloat(jggzHz)<3){
				   		jianyiyijian=jianyiyijian+"监管改造、";
				   } if(parseFloat(jygzJcfHz)<2||parseFloat(jygzHz)<3){
				  		 jianyiyijian=jianyiyijian+"教育改造、";
				   } if(parseFloat(whgzJcfHz)<2||parseFloat(whgzHz)<3){
				  		 jianyiyijian=jianyiyijian+"文化改造、";
				   } if(parseFloat(ldgzJcfHz)<3||parseFloat(ldgzHz)<3){
				  		 jianyiyijian=jianyiyijian+"劳动改造、";
				   }
					 
				   jianyiyijian = jianyiyijian.substring(0,jianyiyijian.length-1);
				   jianyiyijian="建议：该罪犯改造质量考核评价，在"+jianyiyijian+"方面未达标，请引起关注！";
				} else{
				    jianyiyijian="建议：该罪犯改造质量考核评价，总体平稳。";
				}
				 $("#jianyiyijian").html(jianyiyijian);
				 
				 
				 
				 
				var zzgzcolor="#3cff24";
				var zzgzshdb = "达标";
				var jggzcolor="#3cff24";
				var jggzshdb = "达标";
				var jygzcolor="#3cff24";
				var jygzshdb = "达标";
				var whgzcolor="#3cff24";
				var whgzshdb = "达标";
				var ldgzcolor="#3cff24";
				var ldgzshdb = "达标";
				var zzgzfzmxzs = "<td style='font-size: 28px;color: white; border-bottom: 2px solid #026dca;'>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>基础分：&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>"+zzgzJcfHz+"&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>，奖励分&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>："+zzgzJlfhz+"&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>，总分：&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>"+zzgzHz+"&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>。</div>"+
									"	<div  style='float: left;color:"+zzgzcolor+";margin-top: 10px;'>"+zzgzshdb+"</div>"+
									"</td>";
				var jggzfzmxzs = "<td style='font-size: 28px;color: white; border-bottom: 2px solid #026dca;'>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>基础分：&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>"+jggzJcfHz+"&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>，奖励分&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>："+jggzJlfhz+"&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>，总分：&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>"+jggzHz+"&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>。</div>"+
									"	<div  style='float: left;color:"+jggzcolor+";margin-top: 10px;'>"+jggzshdb+"</div>"+
									"</td>";
				var jygzfzmxzs = "<td style='font-size: 28px;color: white; border-bottom: 2px solid #026dca;'>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>基础分：&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>"+jygzJcfHz+"&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>，奖励分&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>："+jygzJlfhz+"&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>，总分：&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>"+jygzHz+"&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>。</div>"+
									"	<div  style='float: left;color:"+jygzcolor+";margin-top: 10px;'>"+jygzshdb+"</div>"+
									"</td>";
				var whgzfzmxzs = "<td style='font-size: 28px;color: white; border-bottom: 2px solid #026dca;'>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>基础分：&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>"+whgzJcfHz+"&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>，奖励分&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>："+whgzJlfhz+"&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>，总分：&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>"+whgzHz+"&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>。</div>"+
									"	<div  style='float: left;color:"+whgzcolor+";margin-top: 10px;'>"+whgzshdb+"</div>"+
									"</td>";
				var ldgzfzmxzs = "<td style='font-size: 28px;color: white;  '>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>基础分：&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>"+ldgzJcfHz+"&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>，奖励分&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>："+ldgzJlfhz+"&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>，总分：&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>"+ldgzHz+"&nbsp;&nbsp;</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>。</div>"+
									"	<div  style='float: left;color:"+ldgzcolor+";margin-top: 10px;'>"+ldgzshdb+"</div>"+
									"</td>";
				if(parseFloat(zzgzJcfHz)<6||parseFloat(zzgzHz)<6){
					zzgzcolor = "red";
				    zzgzshdb = "未达标";
				    var naxmdb="<div  style='float: left;'>（</div>";
				    if(parseFloat(zzgzJcfHz)<6){
				    	naxmdb=naxmdb+"<div  style='float: left;'>基础分</div><div  style='float: left;color:"+zzgzcolor+";'>&nbsp;未达标</div>";
				    }
				    if(parseFloat(zzgzHz)<6){
				   	 if(parseFloat(zzgzJcfHz)<6){
				    		naxmdb=naxmdb+"<div  style='float: left;'>、</div>";
				   	 }
				    	naxmdb=naxmdb+"<div  style='float: left;'>总分</div><div  style='float: left;color:"+zzgzcolor+";'>&nbsp;未达标</div>";
				    }
				    naxmdb=naxmdb+"<div  style='float: left;'>）</div>";
				    
				    zzgzfzmxzs = "<td style='font-size: 28px;color: white;   border-bottom: 2px solid #026dca;'>"+
									"	<div  style='float: left;color:"+zzgzcolor+";margin-top: 10px;'>"+zzgzshdb+"</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>"+naxmdb+"</div>"+
									"</td>";
				}
				if(parseFloat(jggzJcfHz)<2||parseFloat(jggzHz)<3){
					jggzcolor = "red";
				    jggzshdb = "未达标";
				    var naxmdb="<div  style='float: left;'>（</div>";
				    if(parseFloat(jggzJcfHz)<2){
				    	naxmdb=naxmdb+"<div  style='float: left;'>基础分</div><div  style='float: left;color:"+jggzcolor+";'>&nbsp;未达标</div>";
				    }
				    if(parseFloat(jggzHz)<3){
				     if(parseFloat(jggzJcfHz)<2){
				    		naxmdb=naxmdb+"<div  style='float: left;'>、</div>";
				   	 }
				    	naxmdb=naxmdb+"<div  style='float: left;'>总分</div><div  style='float: left;color:"+jggzcolor+";'>&nbsp;未达标</div>";
				    }
				    naxmdb=naxmdb+"<div  style='float: left;'>）</div>";
				    
				     jggzfzmxzs = "<td style='font-size: 28px;color: white;  border-bottom: 2px solid #026dca; '>"+
									"	<div  style='float: left;color:"+jggzcolor+";margin-top: 10px;'>"+jggzshdb+"</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>"+naxmdb+"</div>"+
									"</td>";
				}
				if(parseFloat(jygzJcfHz)<2||parseFloat(jygzHz)<3){
					jygzcolor = "red";
				    jygzshdb = "未达标";
				      var naxmdb="<div  style='float: left;'>（</div>";
				    if(parseFloat(jygzJcfHz)<2){
				    	naxmdb=naxmdb+"<div  style='float: left;'>基础分</div><div  style='float: left;color:"+jygzcolor+";'>&nbsp;未达标</div>";
				    }
				    if(parseFloat(jygzHz)<3){
				     if(parseFloat(jygzJcfHz)<2){
				    		naxmdb=naxmdb+"<div  style='float: left;'>、</div>";
				   	 }
				    	naxmdb=naxmdb+"<div  style='float: left;'>总分</div><div  style='float: left;color:"+jygzcolor+";'>&nbsp;未达标</div>";
				    }
				    naxmdb=naxmdb+"<div  style='float: left;'>）</div>";
				    
				     jygzfzmxzs = "<td style='font-size: 28px;color: white;  border-bottom: 2px solid #026dca; '>"+
									"	<div  style='float: left;color:"+jygzcolor+";margin-top: 10px;'>"+jygzshdb+"</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>"+naxmdb+"</div>"+
									"</td>";
				}
				if(parseFloat(whgzJcfHz)<2||parseFloat(whgzHz)<3){
					whgzcolor = "red";
				    whgzshdb = "未达标";
				       var naxmdb="<div  style='float: left;'>（</div>";
				    if(parseFloat(whgzJcfHz)<2){
				    	naxmdb=naxmdb+"<div  style='float: left;'>基础分</div><div  style='float: left;color:"+whgzcolor+";'>&nbsp;未达标</div>";
				    }
				    if(parseFloat(whgzHz)<3){
				     if(parseFloat(whgzJcfHz)<2){
				    		naxmdb=naxmdb+"<div  style='float: left;'>、</div>";
				   	 }
				    	naxmdb=naxmdb+"<div  style='float: left;'>总分</div><div  style='float: left;color:"+whgzcolor+";'>&nbsp;未达标</div>";
				    }
				    naxmdb=naxmdb+"<div  style='float: left;'>）</div>";
				    
				     whgzfzmxzs = "<td style='font-size: 28px;color: white;  border-bottom: 2px solid #026dca; '>"+
									"	<div  style='float: left;color:"+whgzcolor+";margin-top: 10px;'>"+whgzshdb+"</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>"+naxmdb+"</div>"+
									"</td>";
				}
				if(parseFloat(ldgzJcfHz)<3||parseFloat(ldgzHz)<3){
					ldgzcolor = "red";
				    ldgzshdb = "未达标";
				        var naxmdb="<div  style='float: left;'>（</div>";
				    if(parseFloat(ldgzJcfHz)<3){
				    	naxmdb=naxmdb+"<div  style='float: left;'>基础分</div><div  style='float: left;color:"+ldgzcolor+";'>&nbsp;未达标</div>";
				    }
				    if(parseFloat(ldgzHz)<3){
				     if(parseFloat(ldgzJcfHz)<3){
				    		naxmdb=naxmdb+"<div  style='float: left;'>、</div>";
				   	 }
				    	naxmdb=naxmdb+"<div  style='float: left;'>总分</div><div  style='float: left;color:"+ldgzcolor+";'>&nbsp;未达标</div>";
				    }
				    naxmdb=naxmdb+"<div  style='float: left;'>）</div>";
				    
				     ldgzfzmxzs = "<td style='font-size: 28px;color: white;  '>"+
									"	<div  style='float: left;color:"+ldgzcolor+";margin-top: 10px;'>"+ldgzshdb+"</div>"+
									"	<div style='float: left;font-size: 24px;margin-top: 15px;'>"+naxmdb+"</div>"+
									"</td>";
				}
				$("#leftzs").html("<tr  onclick='dakaimx(\""+data.id+"\",\"ZZGZ\")' style='cursor:pointer;'>"+
									"<td style=' font-size: 35px; color: #f9e68b;border-bottom: 2px solid #026dca;text-align: center;'>政治改造</td>"+
									""+zzgzfzmxzs+""+
								"</tr>"+
								"<tr onclick='dakaimx(\""+data.id+"\",\"JGGZ\")' style='cursor:pointer;'>"+
									"<td style=' font-size: 35px; color: #f9e68b;border-bottom: 2px solid #026dca;text-align: center;'>监管改造</td>"+
									""+jggzfzmxzs+""+
								"</tr>"+
								"<tr onclick='dakaimx(\""+data.id+"\",\"JYGZ\")' style='cursor:pointer;'>"+
									"<td style=' font-size:35px; color: #f9e68b;border-bottom: 2px solid #026dca;text-align: center;'>教育改造</td>"+
									""+jygzfzmxzs+""+
								"</tr>"+
								"<tr onclick='dakaimx(\""+data.id+"\",\"WHGZ\")' style='cursor:pointer;'>"+
									"<td style=' font-size: 35px; color: #f9e68b;border-bottom: 2px solid #026dca;text-align: center;'>文化改造</td>"+
									""+whgzfzmxzs+""+
								"</tr>"+
								"<tr onclick='dakaimx(\""+data.id+"\",\"LDGZ\")' style='cursor:pointer;'>"+
									"<td style=' font-size: 35px; color: #f9e68b; text-align: center;'>劳动改造</td>"+
									""+ldgzfzmxzs+""+
								"</tr>");
				
								var datamx = data.listmx;
								var listname = data.listname;
								var listzf = data.listzf;
								var listkf = data.listkf;
								var listTWz = data.listTWz;
								echar2(listzf,listkf,listTWz);
								
								var listPm = data.listPm;
								var listqs = data.listqs;
								if(listPm.length>0){
								 	var pm = listPm[0].pm;
								 	$("#jqpm").html(pm);
								}
								$("#gzqs").html("<div style='float: left;font-weight: bolder;  color: #73ccaf;'>—</div>");
								/* if(listqs.length>0){
								 	var qs = listqs[0].qs;
								 	if(qs=='1'){
								 	$("#gzqs").html("<img style='width: 20px; margin-top: 5px;float: left;' src='style/images/up_red.png'/>");
								 	}else if(qs=='-1'){
								 	$("#gzqs").html("<img style='width: 20px; margin-top: 5px; float: left; ' src='style/images/down_green.png'/>");
								 	}else{
								 	$("#gzqs").html("<div style='float: left;font-weight: bolder;  color: #73ccaf;'>—</div>");
								 	}
								 } */
				
								}else{
									alert("此罪犯未进行改造考评");
								}
				}
				
			});
			 
	});
	function dakaimx(id,lx){
		var url = "${ctx}/wdgz/ymzsmx?zbId="+id+"&lx="+lx;
		window.open(url, "_blank");;
	}
	
	//堆叠图
	function echar2(listzf,listkf,listTWz){
		var myChart =   echarts.init(document.getElementById('mianechar'));
		var	option = {
		 	title : {
			        text: '改造分值比例',
			        x:'left',
			        textStyle:{
			         color:'#ccc',//标题边框颜色,默认'#ccc'
			          fontSize: 24
			        } 
			    },
			  tooltip : {     // 这是弹窗提示组件
			       trigger: 'item',
			       formatter: "{b} <br/>{a} : {c}"
			   },
		    angleAxis: {
		        type: 'category',
		        data: listTWz,
		        z: 10,
		       axisLine:{
		            lineStyle:{
		                color:'#ccc'
		            }
		        },
		         axisLabel:{
		                fontSize: 20
		        }
		    },
		    radiusAxis: {
		    	axisLine:{
		            lineStyle:{
		                color:'#ccc'
		            }
		        }
		    },
		    color:['#CD3333','#C0FF3E' ],
		    polar: {
		    },
		    series: [ {
		        type: 'bar',
		        data: listkf,
		        coordinateSystem: 'polar',
		        name: '扣分',
		        stack: 'a'
		    },{
		        type: 'bar',
		        data: listzf,
		        coordinateSystem: 'polar',
		        name: '加分',
		        stack: 'a'
		    }],
		    legend: {
		        show: true,
		        data: ['加分', '扣分'],
		         textStyle:{
			         color:'#ccc'//标题边框颜色,默认'#ccc'
			        } 
		    }
		};
			 myChart.setOption(option);
	}
	
	
	//饼状图
	 function echar(datamx,listname){
	   var myChart =   echarts.init(document.getElementById('mianechar'));
		var option = {
			    title : {
			        text: '改造分值比例',
			        x:'center',
			        textStyle:{
			         color:'#ccc'//标题边框颜色,默认'#ccc'
			        } 
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    color:['#76EE00', '#CD3700','#BF3EFF','#CD3700','#87CEFA','#CD3700', '#FFFF00','#CD3700','#CD950C','#CD3700'],
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: listname,
			         textStyle:{
			         color:'#ccc'//标题边框颜色,默认'#ccc'
			        } 
			    },
			    series : [
			        {
			            name: '五大改造',
			            type: 'pie',
			            radius : '55%',
			            center: ['60%', '60%'],
			            data: datamx,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
				 		
	 myChart.setOption(option);
	 }
	</script>
	</head>
	<body  >
	<input type="hidden" id="zfjbxxId" value="${ param.zfjbxxId }"/>
			<div style="background:url(style/images/bg.png) repeat center top; text-align:center; font-size:14px; background-size: 100%; color:#000; font-family:'微软雅黑';width:100%; height:100%;min-height: 800px">
				<!-- 放入正题 -->
				<div id="PanelAll">
					<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" align="center">
				      <tr style="height: 16%">
				        <td valign="top" colspan="3" >
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
				            <!--   <tr style="height: 30px">
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
				                <td  id="hfjyjqxm">
				                
				                </td>
				                <td>&nbsp;</td>
				              </tr>
				              
				            </table>
				        </td>
				      </tr>
				    <!--   <div class="BoxTable"> -->
				      <tr style="height: 65%">
				      	 <td valign="top" style="width: 50%">
				      	  <div class="BoxTable">
							<table style="height: 100%;width: 100%;" cellpadding="0" cellspacing="0" id="leftzs">
								
							</table>
						  </div>
						 </td> 
						 <td style="width: 2%">&nbsp;</td>
						 <td valign="top" style="width: 48%">
						  <div class="BoxTable">
							<table style="width: 100%;height: 100%">
								<tr style=" height: 100%;width: 100%;">
									<td style="width: 100%;">
									  <table style="width: 100%;height: 100%">
									  		<tr height="90%">
									  			<td id="mianechar"></td>
									  		</tr>
									  		<!-- 
									  		<tr>
									  			<td id="mianewenzi" style="text-align: center;">
									  				<div style='float: left;color: white;font-size: 20px; margin-left: 33%;'>监区排名：</div>
									  				<div style='float: left;color: white;font-size: 20px' id="jqpm"> </div>
									  				<div style='float: left;color: white;font-size: 20px'>&nbsp;&nbsp;改造趋势：</div>
									  				<div style='float: left;color: white;font-size: 20px' id="gzqs"></div>
									  			</td>
									  		</tr>
									  		 -->
									  </table>
									</td>
								</tr>
								
							</table>
							</div>
						 </td>
				      </tr>
				      <tr>
				       <td valign="top" style="width: 100%;color: #00fff3" colspan="3" align="left" >
				          <div id="jianyiyijian" style="font-size: 30px;"></div>
				       </td>
				      </tr>
				      
				    </table>
					
				</div>
				</body>
			</div>
</body>
</html>