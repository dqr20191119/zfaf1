<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div style="text-align: center; height: 100%; width: 100%">
	<cui:form id="formId_yrzq_edit" heightStyle="fill">
		<cui:input type='hidden' name="id" id="id" value="${id}"/>
		<cui:input type='hidden' name="prisonId" />
		<cui:input type='hidden' name="departId" />
		<cui:input type='hidden' name="departName" />
		<cui:input type='hidden' name="startTime" />
		<cui:input type='hidden' name="endTime" />
		<cui:input type='hidden' name="createTime" />
		<cui:input type='hidden' name="createUserId" />
		<cui:input type='hidden' name="dataType" />
		<cui:input type='hidden' name="title" />
		<cui:input type='hidden' name="finishUserName" />
		<cui:input type='hidden' name="finishUserId" />
		<cui:input type='hidden' name="fxcjId" id="fxcjId" value="${fxcjId}"/>
		<cui:input type='hidden' name="sxsj" id="sxsj" value="${sxsj}"/>
		
		
 		<table class="table">
 				<%-- <tr>
					<th style="text-align:right">状态：</th>
					<td><cui:combobox id="state" name="state" required="true" width="250" data="data_state"></cui:combobox></td>	
				</tr>
				<tr>
					<th style="text-align:right">是否超时：</th>
					<td><cui:combobox id="isTimeout" name="isTimeout" required="true" width="250" data="data_sfcs"></cui:combobox></td>	
				</tr>
				<tr>			
					<th style="text-align:right">完成时间：</th>
					<td><cui:datepicker  id="finishDate" name="finishDate" model="timepicker" required="true" width="250"></cui:datepicker ></td>
				</tr> --%>
				<tr>
					<td colspan="2" id="cjcs">
					
					</td>
				</tr>
				
				<tr>
					<th id = "zbrTh" style="display:none">接班人：</th>
					<td id = "zbrTd" style="display:none"><cui:combobox id = "zbr" name="zbr" width="300" required="true" url="${ctx}/wghgl/yrzq/getMj" multiple = "true"></cui:combobox></td> 
				</tr>
				<tr>			
					<th id = "zbrzTh" style="display:none">值班日志：</th>
					<td id = "zbrzTd" style="display:none;width:75%;"><cui:textarea id="zbrz" name="zbrz" componentCls="form-control"></cui:textarea></td>
				</tr>
				<tr>			
					<th style="text-align:right">完成情况：</th>
					<td><cui:textarea id="mark" name="mark" componentCls="form-control"></cui:textarea></td>
				</tr>
				
				<tr id = "sample" >			
					<th style="text-align:right;display:none" id = "jjTh" >解决方案：</th>
					<td id = "jjTd" style="display:none"><cui:textarea id="jjfa" name="jjfa" readonly="true" componentCls="form-control"></cui:textarea></td>
				</tr>
			</table>
	</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 10px; text-align: center;">
		<cui:button label="保存" text="false" onClick="saveOrUpdate"></cui:button>
		<cui:button label="取消" text="false" onClick="toClose"></cui:button>
		<cui:button label="转班" text="false" onClick="zhuanban" id="zhuanban" style="display:none;"></cui:button>
	</div>
</div>


<script>
	var data_state=[{value:'0',text:'待执行'},{value:'1',text:'未完成'},{value:'2',text:'已完成'}];
	var data_sfcs=[{value:'0',text:'未超时'},{value:'1',text:'已超时'}];//是否超时
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.ORG_CODE; //监狱编号
	var userId = jsConst.USER_ID; //登录人
	var id = '${id}';
	var fxcjId = '${fxcjId}';
	var title = '${title}';
	var sxsj = '${sxsj}';
	$.parseDone(function() {
		//cjcs
		if(fxcjId){
			$.ajax({
				type : 'post',
				url : "${ctx}/wwjg/fxgkgl/getByfxcjId?id=" + fxcjId,
				dataType : 'json',
				success : function(data) { 
					$("#cjcs").textbox('setValue',data.kfz);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					 
				}
			});
			
			
		}
		if(title == '接班'){
			$("#zhuanban").show();
			$("#zbrzTh").show();
			$("#zbrzTd").show();
			$.ajax({
				type : 'post',
				url : "${ctx}/wghgl/yrzq/getZbrz",
				dataType : 'json',
				success : function(data) {
					var obj = data.obj;
					if(obj != null && obj.length > 4000) {
						obj = obj.substring(1,4000);
					}
					$("#zbrz").textbox('setValue',obj);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					 
				}
			});
		}
		 
		if(title == '交班'){
			$("#zbrTh").show();
			$("#zbrTd").show();
			$("#zbrzTh").show();
			$("#zbrzTd").show();
			$.ajax({
				type : 'post',
				url : "${ctx}/wghgl/yrzq/searchZbrz?sxsj="+sxsj,
				dataType : 'json',
				success : function(data) {
					var obj = data.obj;
					if(obj != null && obj.length > 4000) {
						obj = obj.substring(1,4000);
					}
					$("#zbrz").textbox('setValue',obj);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					 
				}
			});
			/*loadForm("formId_yrzq_edit", "${ctx}/xxhj/jhrc/getZbr", function(data) {
				if(data){
					$('#zbr').combobox('setValue',data.ZBRID);
				}
			}); */
		}
		
	})
	
	
function saveOrUpdate() {
		var zbrText = $('#zbr').combobox('getText');
		var validFlag = $("#formId_yrzq_edit").form("valid");
		if(!validFlag) {
			return;
		}
		var formData = $("#formId_yrzq_edit").form("formData");
		formData["zbrText"] = zbrText;
		var url = '${ctx}/wghgl/yrzq/saveOrUpdate';
		if(title == '交班'){
			url = '${ctx}/xxhj/jhrc/saveOrUpdateJb';
		}
		$.loading({text:"正在处理中，请稍后..."});
		$.ajax({
			type : 'post',
			contentType: "application/json",
			url : url,
			dataType : 'json',
			data:JSON.stringify(formData),
			success : function(data) {
				$.loading("hide");
				if(data.success) {
					$.messageQueue({ message : data.obj, cls : "success", iframePanel : true, type : "info" });
					showYrzqContent();
					if(title == '风险处理'){
						initLeftChart();
					}
					toClose();
					refreshDiv();
				} else {
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"操作失败！", cls:"error"}); 
			}
		});
	}
	
	function toClose(){
		$("#dialogId_yrzq_edit").dialog("close");
	}
	function zhuanban(){
		$("#dialogId_yrzq_edit").dialog("close");
		$("#dialogId_yrzq_edit").dialog({
			width : 400, //属性
			height : 200, //属性
			title : '转班',
			modal : true, //属性
			autoOpen : false,
			url : "${ctx}/wghgl/yrzq/toNew?id="+id
		});
		$("#dialogId_yrzq_edit").dialog("open");
	}
</script>