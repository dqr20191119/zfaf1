<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/module/video/css/rightVideoPlan.css" />

<style>
.form-control {
	width: 100%;
}
.yhsd{
	display:none;
}
.transfer_splitDiv {
    float:left;
    margin-top:40%;
    margin-left:10px;
}
.transfer_btn {
    padding-bottom:25px
}
</style>

<div style="height: 99%;">
	<cui:layout id="ccode-layout" fit="true">
		<!-- 北京市监狱局树形结构 -->
		<cui:layoutRegion region="west" split="false" style="width: 300px;" maxWidth="300" url="${ctx}/wghf/wgzrfp/ccodeTreePage" onResize="initTreebox">
		</cui:layoutRegion>
		<cui:layoutRegion region="center" split="false" onLoad="layoutCenterResize" onResize="layoutCenterResize">
		</cui:layoutRegion>
	</cui:layout>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
</div>
<script type="text/javascript">
	var buttons_zbbp = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			
			saveOrUpdate();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_zbbp").dialog("close");
	    }            
	}];
	function saveOrUpdate() {
		
		var formData = $("#formId_zbbp_edit").form("formData");
		
		if(!formData.id) {   //新增
			var flag = saveDutyData();
		
			if(flag) {
				var data = validateDutydate(formData);
				if(data && data.length >0) {
					$.message({message:"生效日期或截止日期已存在编排记录!", cls:"waring"});
					return;
				}
				creatSavaData(formData);
			}else {
				 $.message({message:"操作有误，请重新选择！", cls:"waring"}); 
			}
		} else{
			creatSavaData(formData);
		}
    }
	
	function creatSavaData(formData){
		
   		var allPoliceList = '';
		var dutyId= '';  
		$("#dutyform").find("tr").each(function(i) {
			$(this).find(".jobTd").each(function(j) {
				dutyId = $(this).attr("id")
			})
			
			if(i>0) {
				var police = '';  
				$(this).find(".dutyTd").each(function(j) {
					
					if(j == 0) {
						var tdId = dutyId+ "&";
					}else{
						var tdId = "," +dutyId+ "&";
					}
					
					var tdName = '';
					$(this).find("li").each(function(k) {
						var liId = $(this).find("a").attr("id");
						var policeName = $(this).find("input").val();
					   
						if(k == 0) {
							var policeIds = liId.split("-")[1];
							var policeNames = policeName;
						}else {
							
							var policeIds = "@" + liId.split("-")[1];
							var policeNames = "@" + policeName;
						}
						 tdId += policeIds;
						 tdName += policeNames;
					})
					
					if(tdName && tdId) {
						police += tdId+ "%" +tdName;
					}else{
						police += tdId;
					}
				})
				
				if(i == 1) {
					allPoliceList += police;
				}else if(i > 1){
					allPoliceList += ";"+police;
				}
			}
		})
		
		var tdCount = allPoliceList.split("%");
	  	var count = Number(formData.jobCount) * Number(formData.dateDiff) + 1;
	  
		if (tdCount.length == count) {
			
 		 	$.loading({text:"正在处理中，请稍后..."});
			formData.allPoliceList = allPoliceList;
		   	$.ajax({
				type : 'post',
				url : '${ctx}/zbgl/zbbp/saveDutyData',
				data: formData,
				dataType : 'json',
				success : function(data) {
					$.loading("hide");
					if(data.code == "1") {
						
						$.message({message:"操作成功！", cls:"success"}); 
						$("#dialogId_zbbp").dialog("close");
						$("#gridId_zbbp_query").grid("reload");
						
					} else {
						$.message({message:"操作失败！", cls:"error"}); 
					}				
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					
					$.message({message:"操作失败！", cls:"error"}); 
				}
			});
	  } else {
		  $.message({message:"注意！每个值班岗位至少有一个有值班人员！", cls:"waring"}); 
	  } 
	}
</script>