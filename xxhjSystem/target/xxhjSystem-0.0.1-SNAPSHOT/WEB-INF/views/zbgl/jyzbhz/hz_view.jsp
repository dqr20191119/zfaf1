<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.zbgl.rygl.util.RyCommon"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div style="height:100%;">
	<cui:form id="formId_hz_query">
		<table class="table">
			<tr>	
				<th>按值班日期汇总：</th>
				<td><cui:datepicker id ="combobox_dutyDate" name = "dutyDate" dateFormat="yyyy-MM-dd" ></cui:datepicker>
				</td>
				<td>
	 				<cui:button label="按值班日期汇总" componentCls="btn-primary" onClick="search('1')"/>
				</td>
				<th>按值班月份汇总：</th>
				<td><cui:datepicker id ="combobox_zbMonth" name = "zbYf" dateFormat="yyyy-MM" ></cui:datepicker>
				</td>
				<td>
	 				<cui:button label="按值班月份汇总" componentCls="btn-primary" onClick="search('2')"/>
				</td>
			</tr>
            <tr>
                <th>按值班月份打印：</th>
                <td>
                    <cui:datepicker id ="datepicker_zbMonth" name = "zbYf" dateFormat="yyyy-MM"></cui:datepicker>
                </td>
                <td>
                    <cui:button label="按值班月份打印" componentCls="btn-primary" onClick="openPrint()"/>
                </td>
            </tr>
		</table>
	</cui:form>

    <cui:dialog id="dialogId_hz_print" autoDestroy="true"  autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" buttons="buttons_hz_print" maximizable="true">
    </cui:dialog>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_hz_query" data="toolbar_hz_query"></cui:toolbar>	
	</div>	
	
	<cui:grid id="gridId_hz_query" rownumbers="true"  width="auto" fitStyle="fill" rowNum="30">
		<cui:gridCols>
				<cui:gridCol name="id" hidden="true">id</cui:gridCol>
				<cui:gridCol name="cusNumber" align="center" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combox_dw
				}">监狱</cui:gridCol>
				<cui:gridCol name="dutyDate" align="center">值班日期</cui:gridCol>
				<cui:gridCol name="zhz" align="center">指挥长</cui:gridCol>
				<cui:gridCol name="zbz" align="center">值班长</cui:gridCol>
				<cui:gridCol name="zby" align="center">值班员</cui:gridCol>
				<cui:gridCol name="zbDh" align="center">值班电话</cui:gridCol>
			</cui:gridCols>
			<cui:gridPager gridId="gridId_hz_query"/>
	</cui:grid>
</div>

<script>
 var combox_dw =<%= AuthSystemFacade.getHnAllJyJsonInfo()%>;

	$.parseDone(function() {
		$("#combobox_dutyDate").datepicker("setDate",new Date());
		$("#combobox_zbMonth").datepicker("setDate",new Date());
		$("#datepicker_zbMonth").datepicker("setDate",new Date());
		search("1");
	});
	
	
	var toolbar_hz_query = [{
		"type" : "button",
		"id" : "btnId_export_zbp",
		"label" : "按天导出",
		"onClick" : "exportZbp('1')",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_export_zbpyf",
		"label" : "按月导出",
		"onClick" : "exportZbp('2')",
		"componentCls" : "btn-primary"
	}]
	
	var grid_hz_ColModel = [{
		  label : "id",
	        name : "id",
	        hidden : true
	       // formatter : "text"//设置formatter后不能再使用editable为true
	    },{
        label : "监狱",
        name : "cusNumber",
        align : "center",
        formatter:"convertCode",
        revertCode:true,
        formatoptions:{
    			'data': combox_dw
        }
    },{
        label : "指挥长",
        name : "zhz",
        align : "center"
    },{

        label : "值班长",
        name : "zbz",
        align : "center"
    },{

        label : "值班人员",
        name : "zby",
        align : "center"
    },{

        label : "值班电话",
        name : "zbDh",
        align : "center"
    }];

 var buttons_hz_print=[ {
     text: "打印",
     id: "btnId_print",
     click:function () {

         print();
     }
 },{
     text: "打印预览",
     id: "btnId_printY",
     click: function () {

         printYl();
     }
 },{
     text: "关闭",
     id: "btnId_qx",
     click: function () {

         $("#dialogId_hz_print").dialog("close");
     }
 }];

	
	function search(type) {
		
		var formData = {};
		if(type ==1){
			var dutyDate = $("#combobox_dutyDate").datepicker("getValue");
			if(dutyDate==null || dutyDate=="" || dutyDate==undefined){
				$.message({message:"请选择汇总的值班日期！", cls:"waring"});
				return;
			}
			formData.dutyDate = dutyDate;
		}
		
		if(type ==2){
			var zbYf = $("#combobox_zbMonth").datepicker("getValue");
			if(zbYf==null || zbYf=="" || zbYf==undefined){
				$.message({message:"请选择汇总的值班月份！", cls:"waring"});
				return;
			}
			formData.zbYf = zbYf;
		}
		
		
		$("#gridId_hz_query").grid("option", "postData", formData);
		$("#gridId_hz_query").grid("reload","${ctx}/zbgl/jyzbhz/getZbDetailByDay"); 
		$("#gridId_zbbp_query").grid("reload");
	}
	
	function clear() {
			$("#formId_hz_query").form("reset");
	}
	
	
	 function exportZbp(type) {
		var allRowDate = $("#gridId_hz_query").grid("getRowData");
		console.log("allRowDate="+JSON.stringify(allRowDate));
		
		if(allRowDate.length <= 0){
			$.message({message:"该值班日期还没有数据！", cls:"waring"});
			return;
		}
		
		if(type==1){
			var dutyDate = $("#combobox_dutyDate").datepicker("getValue");
			var url ="${ctx}/zbgl/jyzbhz/export?dutyDate="+dutyDate;
			window.location.href = url;
		}
		
		if(type==2){
			var zbYf = $("#combobox_zbMonth").datepicker("getValue");
			var url ="${ctx}/zbgl/jyzbhz/export?zbYf="+zbYf;
			window.location.href = url;
		}
		
		
	} 

	function openPrint() {
	   var zbprint_Month = $("#datepicker_zbMonth").datepicker("getValue");
	   if(zbprint_Month==null ||zbprint_Month=='' || zbprint_Month==undefined){
	       $.alert("请选择打印月份");
       }else{
          var url = "${ctx}/zbgl/jyzbhz/toPrint?zbyf="+zbprint_Month;
           $("#dialogId_hz_print").dialog({
               width :1200,
               height : 1000,
               title : "打印",
               url : url
           });
           $("#dialogId_hz_print").dialog("open");
       }

    }


</script>