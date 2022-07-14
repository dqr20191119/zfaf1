<%@page import="com.ces.prison.common.constants.GroupKeyConst"%>
<%@page import="com.cesgroup.prison.aqfxyp.fxcj.common.FxcjConstant.FxysTypePrefix"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>



<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_fxcj_query">
		<table class="table">
			<tr><th>明细：</th>
				<td><cui:input name="bz"></cui:input></td>				
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_fxcj" data="toolbar_fxcj"></cui:toolbar>	
	</div>	
		
	<cui:grid id="gridId_fxcj" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" 
		url="${ctx}/aqfxyp/fxcj/searchDataNew?jQName=${jQName}&wwName=${wwName}&date=${date}" rowNum="15">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="wwjgName" align="center">五维架构</cui:gridCol>
			<cui:gridCol name="sjfwName" align="center">数据范围</cui:gridCol>
				<cui:gridCol name="wgOne" align="center">一级网格</cui:gridCol>
			<cui:gridCol name="wgTwo" align="center">二级网格</cui:gridCol>
			<cui:gridCol name="fxdName" align="center">风险点</cui:gridCol>
			<cui:gridCol name="pgtjName" align="center">评估条件</cui:gridCol>
			<cui:gridCol name="kczf" align="center">扣除总分</cui:gridCol>
			<cui:gridCol name="fxDate" align="center">风险时间</cui:gridCol>
			<cui:gridCol name="bz" align="center">明细</cui:gridCol>
			<cui:gridCol name="status" align="center" sortable="true" formatter="convertCode" revertCode="true"  formatoptions="{
			'data':commbobox_sfyx
			}">状态</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_fxcj" />
	</cui:grid>
	
	<cui:dialog id="dialogId_fxcj" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_fxcj">
	</cui:dialog>
	<cui:dialog id="dialogId_rwxf2" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true">
	</cui:dialog>
</div>

<script>
var combobox_wgOne;
var combobox_wgTwo;
var combobox_wgThree;
var commbobox_sfyx=[{'value':'1','text':'有效'},{'value':'0','text':'无效'}];
	$.parseDone(function() {
		huquwangeg();
	});
	function huquwangeg(){
		if(!combobox_wgOne){
			$.ajax({
				type: 'post',
				url: '${ctx}/aqfxyp/fxcj/searchWG?leve=1',
				dataType: 'json',
				success: function(data) {
					combobox_wgOne=data;
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					$.message("获取失败！");
				}
			});
		}
		if(!combobox_wgTwo){
			$.ajax({
				type: 'post',
				url: '${ctx}/aqfxyp/fxcj/searchWG?leve=2',
				dataType: 'json',
				success: function(data) {
					combobox_wgTwo=data;
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					$.message("获取失败！");
				}
			});
		}
		if(!combobox_wgThree){
			$.ajax({
				type: 'post',
				url: '${ctx}/aqfxyp/fxcj/searchWG?leve=3',
				dataType: 'json',
				success: function(data) {
					combobox_wgThree=data;
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					$.message("获取失败！");
				}
			});
		}
		
	}
	                     
	
	var buttons_fxcj = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			saveOrUpdate2();
		}        
	}, {
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	$("#dialogId_fxcj").dialog("close");
	    }            
	}];
	
	function search() {
		var formData = $("#formId_fxcj_query").form("formData");
		$("#gridId_fxcj").grid("option", "postData", formData);
		$("#gridId_fxcj").grid("reload");
	}
	
	function reset() {
		$("#formId_fxcj_query").form("reset");
	}

	function saveOrUpdate() {
			var validFlag = $("#formId_rwxf_edit").form("valid");
			if(!validFlag) {
				return;
			}
			var formData = $("#formId_rwxf_edit").form("formData");
			formData.rwStatus = '0';
			$.loading({text:"正在处理中，请稍后..."});
			$.ajax({
				type : 'post',
				url : '${ctx}/rwgl/rwxf/saveOrUpdate',
				data: formData,
				dataType : 'json',
				success : function(data) {
					$.loading("hide");
					if(data.code == "1") {
						$.message({message:"操作成功！", cls:"success"});
						$("#dialogId_rwxf2").dialog("close");
					} else {
						$.message({message:"操作失败！", cls:"error"});
					}				
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.loading("hide");
					$.message({message:"操作失败！", cls:"error"});
				}
			});
		}
		
		
	function updateStatus() {
		var validFlag = $("#formId_rwxf_edit").form("valid");
		if(!validFlag) {
			return;
		}
		var formData = $("#formId_rwxf_edit").form("formData");
		formData.rwStatus = '1';
		$.loading({text:"正在处理中，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/rwgl/rwxf/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"});
					$("#dialogId_rwxf2").dialog("close");
				} else {
					$.message({message:"操作失败！", cls:"error"});
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"操作失败！", cls:"error"});
			}
		});
	}

	function closeDialog() {
		
		$("#dialogId_rwxf2").dialog("close");
	}
	function saveOrUpdate2() {
		var validFlag = $("#formId_fxcj_edit").form("valid");
		if(!validFlag) {
			return;
		}
		var formData = $("#formId_fxcj_edit").form("formData");
       console.log(formData);
		$.loading({text:"正在处理中，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/aqfxyp/fxcj/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"}); 
					$("#dialogId_fxcj").dialog("close");
					$("#gridId_fxcj").grid("reload");
					var fxcjId = data.fxcjId;
					$.confirm("是否督办？", function(r) {
						if(r) {
							//新增督办信息
						$.ajax({
							type : 'post',
							url : '${ctx}/aqfxyp/fxcj/insertDb?fxcjId='+fxcjId,
							data: formData,
							dataType : 'json',
							success : function(data) {
								$.message({message:"督办新增成功！", cls:"success"}); 
							}
						});
							
							
						}
						// 将title转为base64
						var titleBase64 = new Base64().multiEncode(formData.pgtjName, 2);
						var url = "${ctx}/rwgl/rwxf/toEdit?type=fxcj&title=" + titleBase64 + "&jhJjsj="+formData.jhJjsj+"&fxcjId="+fxcjId;
						// alert("url = " + url);
						$("#dialogId_rwxf2").dialog({
							width : 1000,
							height : 600,
							title : "新增下发任务",
							url : url				 
						});
						$("#dialogId_rwxf2").dialog("open");
						
					});
					
					
					fhjzcyz();
					
					
				} else if(data.code == "2") {
					$.message({message:data.msg, cls:"error"}); 
					
				} else {
					$.message({message:"操作失败！", cls:"error"}); 
				}				
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.loading("hide");
				$.message({message:"操作失败！", cls:"error"}); 
			}
		});
	}
	
	function deleteByIds() {
		var selarrrow = $("#gridId_fxcj").grid("option", "selarrrow");	
		if(selarrrow && selarrrow.length > 0) {
			$.confirm("确认删除？", function(r) {
				if(r) {
					var ids = "";
					for(var i = 0; i < selarrrow.length; i++) {
						ids += selarrrow[i] + ",";
					}
					$.loading({text:"正在处理中，请稍后..."});
					$.ajax({
						type : 'post',
						url : '${ctx}/aqfxyp/fxcj/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_fxcj").grid("reload");
							}   else {
								$.message({message:"操作失败！", cls:"error"}); 
							}				
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							$.loading("hide");
							$.message({message:"操作失败！", cls:"error"}); 
						}
					});
				}
			}); 
		} else {
			$.message({message:"请选择记录！", cls:"waring"});
			return;
		}
	}	
</script>