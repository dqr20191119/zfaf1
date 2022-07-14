<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_rygl_query">
		<table class="table">
			<tr><th>单位 ：</th>
				<td><cui:combobox  id="cusNumber"  name="cusNumber"  data="combox_dw"    componentCls="form-control" onSelect="changeDw" showClose="true" ></cui:combobox></td>
                <%--<th>部门 ：</th>
                <td><cui:combobox id="deptNumber" name="deptNumber" componentCls="form-control" data="dw_data" showClose="true"></cui:combobox></td>--%>
                <th>人员状态 ：</th>
                <td><cui:combobox  name="ryzt" componentCls="form-control" data="combobox_ryzt" showClose="true"></cui:combobox></td>
                <th>岗位类别 ：</th>
                <td><cui:combobox  name="job" componentCls="form-control" data="combobox_zbJob" showClose="true"></cui:combobox></td>
			</tr>
            <tr>
                <th>年度 ：</th>
                <td><cui:datepicker id="year"  name="year"   dateFormat="yyyy"></cui:datepicker>
                <th>警号 ：</th>
                <td><cui:input name="policeBh"></cui:input></td>
                <th>姓名 ：</th>
                <td><cui:input name="name"></cui:input></td>
                <td>
                    <cui:button label="查询" componentCls="btn-primary" onClick="search"/>
                    <cui:button label="重置" onClick="reset"></cui:button>
                </td>
                <td>
                    <cui:button label="模板下载"  componentCls="btn-primary" onClick="downloadexcel"></cui:button>
                     <cui:button label="导入" componentCls="btn-primary" onClick="importExcel"></cui:button>
                     <cui:button label="导出" componentCls="btn-primary" onClick="exportExcel"></cui:button>
                </td>
            </tr>
		</table>
	</cui:form>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_rygl" data="toolbar_rygl"></cui:toolbar>
	</div>	
			
	<cui:grid id="gridId_rygl" rownumbers="true" multiselect="true" width="auto" fitStyle="fill"
		 >
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true" >id</cui:gridCol>
			<cui:gridCol name="cusNumber" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combox_dw
				}"  align="center" >单位</cui:gridCol>
			<%--<cui:gridCol name="deptNumber" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combobox_bm
				}" align="center">部门</cui:gridCol>--%>
            <cui:gridCol name="name" align="center">姓名</cui:gridCol>
          <%--  <cui:gridCol name="policeBh" align="center">警号</cui:gridCol>--%>
           <%-- <cui:gridCol name="csrq" align="center">出生日期</cui:gridCol>--%>
			<cui:gridCol name="sex" align="center" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combobox_sex
				}">性别</cui:gridCol>
			<%--<cui:gridCol name="jobLevel" align="center">职级</cui:gridCol>
			<cui:gridCol name="nl" align="center">年龄</cui:gridCol>
			<cui:gridCol name="position" align="center">职务</cui:gridCol>--%>
			<cui:gridCol name="ryzt" align="center" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combobox_ryzt
				}">人员状态</cui:gridCol>
            <cui:gridCol name="remark" align="center">备注</cui:gridCol>
            <cui:gridCol name="startDate" align="center">不参与值班起始日期</cui:gridCol>
            <cui:gridCol name="endDate" align="center">不参与值班结束时间</cui:gridCol>
            <cui:gridCol name="jdStartDate" align="center">借调起始日期</cui:gridCol>
            <cui:gridCol name="jdEndDate" align="center">借调结束日期</cui:gridCol>
            <cui:gridCol name="job" align="center">岗位类别</cui:gridCol>
			<cui:gridCol name="year" align="center">年度</cui:gridCol>
            <cui:gridCol name="yearCount" align="center">全年排班数</cui:gridCol>
            <cui:gridCol name="middleCount" align="center">中班值班数</cui:gridCol>
            <cui:gridCol name="nightCount" align="center">晚班值班数</cui:gridCol>
            <cui:gridCol name="workDayCount" align="center">工作日值班数</cui:gridCol>
            <cui:gridCol name="holidaysCount" align="center">休息日值班数</cui:gridCol>
            <cui:gridCol name="fddayCount" align="center">法定节假日值班数</cui:gridCol>
            <cui:gridCol name="noDuty" align="center">不值班情况</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_rygl"/>
	</cui:grid>
	
	<cui:dialog id="dialogId_rygl" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true" buttons="buttons_rygl" maximizable="true">
	</cui:dialog>
	<cui:dialog id="dialogImport" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true">
	</cui:dialog>
</div>

<script>
    var cusNumber = jsConst.CUS_NUMBER;
    var USER_LEVEL = jsConst.USER_LEVEL;
    var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
	$.parseDone(function() {
	    if(USER_LEVEL=="2"){
	        $("#cusNumber").combobox("option","readonly","true");
            $("#cusNumber").combobox("setValue", cusNumber);
        }
        $("#gridId_rygl").grid("reload","${ctx}/zbgl/rygl/searchData?cusNumber=" + cusNumber);
        $.ajax({
            type : 'post',
            url : '${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox',
            data: {
                "cusNumber" : cusNumber
            },
            dataType : 'json',
            success : function(data) {
                $("#deptNumber").combobox( "loadData", data);
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $.loading("hide");
                $.message({message:"操作失败！", cls:"error"});
            }
        });

        if(USER_LEVEL=="1"){
            $.ajax({
                type : 'post',
                url : '${ctx}/zbgl/rygl/remindMessageByRyzy',
                dataType : 'json',
                success : function(data) {
                    if(data.code==200){
                        if(data.message !=""){
                            $.alert("消息提醒:"+data.message);
                        }
                    }
                }
            });
        }


	});
	
	var toolbar_rygl = [{
		"type" : "button",
		"id" : "btnId_add",
		"label" : "新增",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_edit",
		"label" : "修改",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	}, {
		"type" : "button",
		"id" : "btnId_remove",
		"label" : "删除",
		"onClick" : "deleteByIds",
		"componentCls" : "btn-primary"
	},{
        "type" : "button",
        "id" : "btnId_view",
        "label" : "查看",
        "onClick" : "openDailog",
        "componentCls" : "btn-primary"
    },{
        "type" : "button",
        "id" : "btnId_dutyFlag",
        "label" : "初始化值班标签",
        "onClick" : "openDailog",
        "componentCls" : "btn-primary"
    }];

	var combox_dw =<%= AuthSystemFacade.getHnAllJyJsonInfo()%>

	var combobox_sex = [
 		{value:"0",text:"男","selected":true},
		{value:"1",text:"女"}
	];

	var combobox_zbJob=[
		{value:"指挥长",text:"指挥长"},
		{value:"男值班长",text:"男值班长"},
		{value:"女值班长",text:"女值班长"},
		{value:"男值班员",text:"男值班员"},
		{value:"女值班员",text:"女值班员"}]
	
	

	var combobox_ryzt=[
	    {value:"1",text:"在编","selected":true},
		{value:"2",text:"借调"},
		{value:"3",text:"退休"},
		{value:"4",text:"培训"},
		{value:"5",text:"病假"},
        {value:"6",text:"其他"}]
	var buttons_rygl = [{
		text: "保存",
		id: "btnId_save",
		click: function () {
			saveOrUpdate();
		}        
	}, {
	    text: "关闭",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_rygl").dialog("close");
	    }            
	}];
	
	
    //改变单位下拉框的值 获取部门的下拉框值
	function changeDw() {
      var jyId=  $("#cusNumber").combobox( "getValue");
      if(jyId=='4300'){
          $.ajax({
              type : 'post',
              url : '${ctx}/zbgl/rygl/listSjzzxx',
              dataType : 'json',
              success : function(data) {
                  $("#deptNumber").combobox( "loadData", data);
              },
              error : function(XMLHttpRequest, textStatus, errorThrown) {
                  $.loading("hide");
                  $.message({message:"操作失败！", cls:"error"});
              }
          });
      }else {
          $.ajax({
              type : 'post',
              url : '${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox',
              data: {
                  "cusNumber" : jyId
              },
              dataType : 'json',
              success : function(data) {
                  $("#deptNumber").combobox( "loadData", data);
              },
              error : function(XMLHttpRequest, textStatus, errorThrown) {
                  $.loading("hide");
                  $.message({message:"操作失败！", cls:"error"});
              }
          });
      }


    }

	function openDailog(event, ui) {
		 
		var url;
		if(ui.id == "btnId_add") {
			
			url = "${ctx}/zbgl/rygl/toEdit";
            $("#btnId_save").show();
		}else if(ui.id == "btnId_edit") {	
			
			var selarrrow = $("#gridId_rygl").grid("option", "selarrrow");
			if(selarrrow && selarrrow.length == 1) {
				url = "${ctx}/zbgl/rygl/toEdit?id=" + selarrrow[0];
                $("#btnId_save").show();
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}else if(ui.id == "btnId_view") {

            var selarrrow = $("#gridId_rygl").grid("option", "selarrrow");
            if(selarrrow && selarrrow.length == 1) {
                url = "${ctx}/zbgl/rygl/view?id=" + selarrrow[0];
                $("#btnId_save").hide();
            } else {
                $.message({message:"请选择一条记录！", cls:"waring"});
                return;
            }
        }else if(ui.id == "btnId_dutyFlag") {
                url = "${ctx}/zbgl/rygl/todutyFlagIndex";
                $("#btnId_save").hide();
        }

		$("#dialogId_rygl").dialog({
			width : 1500,
			height : 400,
			title : ui.label,
			url : url				 
		});
		$("#dialogId_rygl").dialog("open");
	}
	
	function search() {
		var formData = $("#formId_rygl_query").form("formData");
		$("#gridId_rygl").grid("option", "postData", formData);
		$("#gridId_rygl").grid("reload","${ctx}/zbgl/rygl/searchData");
	}
	
	function reset() {
		
		$("#formId_rygl_query").form("reset");
	}
	
	function saveOrUpdate() {
		
		var validFlag = $("#formId_rygl_edit").form("valid");
		if(!validFlag) {
			return;
		}
		var formData = $("#formId_rygl_edit").form("formData");

		$.loading({text:"正在处理中，请稍后..."});
		//console.log("rygl数据="+JSON.stringify(formData))
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/rygl/saveOrUpdate',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$.loading("hide");
				
				if(data.code == "1") {
					$.message({message:"操作成功！", cls:"success"}); 
					$("#dialogId_rygl").dialog("close");
					$("#gridId_rygl").grid("reload");
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
		
		var selarrrow = $("#gridId_rygl").grid("option", "selarrrow");
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
						url : '${ctx}/zbgl/rygl/deleteByIds',
						data: {
							ids : ids
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							if(data.code == "1") {
								$.message({message:"操作成功！", cls:"success"});
								$("#gridId_rygl").grid("reload");
							}  else {
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
	
	//下载Excel模板文件
	function downloadexcel(){
		var url = "${ctx}/zbgl/rygl/downloadexcel";
		window.location.href = url;
	}
	//文件导入
	function importExcel(){
		$("#dialogImport" ).dialog("option",{
			title:"导入窗口",
			subTitle:"信息",
			width:520,
			height:450,
			url:"${ctx}/zbgl/rygl/toImport"
		});
		$( "#dialogImport" ).dialog("open");
	}
	//文件导出
	function exportExcel() {
		var selarrrow = $("#gridId_rygl").grid("option", "selarrrow");
		if(selarrrow && selarrrow.length > 0) {
			var ids = "";
			for(var i = 0; i < selarrrow.length; i++) {
				ids += selarrrow[i] + ",";
			}
			var url ="${ctx}/zbgl/rygl/exportExcel?ids="+ids;
			window.location.href = url;
		} else {
			$.message({message:"请至少选择一条记录！", cls:"waring"});
			return;
		}		
	}
	
</script>