<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.zbgl.rygl.util.RyCommon"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div style="height:100%;">
	<cui:form id="formId_zbbp_query">
		<table class="table">
			<tr>	
				<th>值班类别：</th>
				<td><cui:combobox id="combobox_zbbpQueryCategoryName" name="categoryId" readonlyInput= "false" url="${ctx}/zbgl/lbgl/searchAllData?dcaStatus=1" onChange="queryInitDrpList()"></cui:combobox></td>
				<th>部门名称：</th>
				<td><cui:combobox id="combobox_DrpmntName"  name="dprtmntId"></cui:combobox>
				</td>
				<td>
	 				<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="clear"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>
	
	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_zbbp_query" data="toolbar_zbbp_query"></cui:toolbar>	
	</div>	
	
	<cui:grid id="gridId_zbbp_query" rownumbers="true" multiselect="true" width="auto" fitStyle="fill" rowNum="20">
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true">id</cui:gridCol>
			<cui:gridCol name="dmdModeId" hidden="true">模板ID</cui:gridCol>
			<cui:gridCol name="dmdName" align="center">排班表名称</cui:gridCol>
			<cui:gridCol name="cdmModeName" align="center">模板名称</cui:gridCol>
			<cui:gridCol name="dmdDprtmntId" align="center" formatter="convertCode" revertCode="true" formatoptions="{
			'data': combobox_bm
			}">部门名称</cui:gridCol>
			<cui:gridCol name="cdmCategoryId" hidden="true">模板名称</cui:gridCol>
			<cui:gridCol name="dmdStartDate" align="center">生效时间</cui:gridCol>
			<cui:gridCol name="dmdEndDate" align="center">截止时间</cui:gridCol>
			<cui:gridCol name="dmdZt" align="center" formatter="convertCode" revertCode="true" formatoptions="{
			'data': combobox_zt
			}">状态</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_zbbp_query" />
	</cui:grid>
	 <cui:dialog id="dialogId_zbbp" autoDestroy="true"  autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" buttons="buttons_zbbp" maximizable="true">
	</cui:dialog>
	<cui:dialog id="dialogId_zbbp_view" autoDestroy="true" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" buttons="buttons_zbbp_view" maximizable="true">
	</cui:dialog>
    <cui:dialog id="dialogId_zbbp_print" autoDestroy="true"  autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" buttons="buttons_zbbp_print" maximizable="true">
    </cui:dialog>
    <cui:dialog id="dialogImport" autoDestroy="true"  autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true"  buttons="buttons_zbbp_import" maximizable="true">
    </cui:dialog>
</div>

<script>
	var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
	var commonbox_ry =<%=RyCommon.getCommonbox("4300")%>
	var cusNumber = jsConst.CUS_NUMBER;
	var drpmntId = jsConst.DEPARTMENT_ID;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var combobox_zt = [{"value":"0","text":"未发布"},
					   {"value":"1","text":"已发布"}
						];
	
	
	$.parseDone(function() {
		
		if( USER_LEVEL =='3' && USER_LEVEL != null) {
			$("#combobox_zbbpQueryCategoryName").combobox("reload","${ctx}/zbgl/lbbm/searchAllData?dcdDprtmntId="+drpmntId+"&param=2");
			$("#combobox_DrpmntName").combobox("option","readonly",true);
		} else {
			$("#combobox_zbbpQueryCategoryName").combobox("reload","${ctx}/zbgl/lbgl/searchAllData?dcaStatus=1");
		}
		//initCategory(drpmntId);
        if(USER_LEVEL=='1'){
            $("#btnId_print").show();
        }else{
            $("#btnId_print").hide();
        }

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

		search();
		
	});
	
	//只有初次加载调用
	function initCategory(drpmntId) {
		
		var flag = true;
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/lbbm/searchAllData',
			data: {
				dcdDprtmntId:drpmntId
			},
			dataType : 'json',
			success : function(data) {
				if(data.length > 0) {
					$("#combobox_zbbpQueryCategoryName").combobox("setValue",data[0].DCD_CATEGORY_ID);
					queryInitDrpList(flag);
				} else {
					search();
				}
			},
		});
	}
	
	var toolbar_zbbp_query = [{
		"type" : "button",
		"id" : "btnId_add",
		"label" : "新增",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_remove",
		"label" : "删除",
		"onClick" : "deleteById",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_edit",
		"label" : "修改",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_view",
		"label" : "查看",
		"onClick" : "openDailog",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_fb",
		"label" : "发布",
		"onClick" : "updateZt('1')",
		"componentCls" : "btn-primary"
	},{
		"type" : "button",
		"id" : "btnId_cx",
		"label" : "撤销",
		"onClick" : "updateZt('0')",
		"componentCls" : "btn-primary"
	},{
        "type" : "button",
        "id" : "btnId_export",
        "label" : "导出值班表",
        "onClick" : "openDailog",
        "componentCls" : "btn-primary"
    },{
        "type" : "button",
        "id" : "btnId_print",
        "label" : "打印值班表",
        "onClick" : "openDailog",
        "componentCls" : "btn-primary"
    }]


    var buttons_zbbp_print=[ {
        text: "打印",
        id: "btnId_print1",
        click:function () {
            zj_print();
        }
    },{
        text: "打印预览",
        id: "btnId_printYl",
        click: function () {

            printYl();
        }
    },{
        text: "关闭",
        id: "btnId_qx",
        click: function () {

            $("#dialogId_zbbp_print").dialog("close");
        }
    }];

    var buttons_zbbp_import = [{
        text: "确定上传文件",
        id: "uploadBut",
        click: function () {

            uploadFun();
        }
    }, {
        text: "取消",
        id: "uploadBut_cancel",
        click: function () {

            closeFun();
        }
    }];



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
	
	var buttons_zbbp_view = [{
	    text: "取消",
	    id: "btnId_cancel",
	    click: function () {
	    	
	    	$("#dialogId_zbbp_view").dialog("close");
	    }            
	}];
	
	function search() {
		
		var formData = {};
		formData.dmdCusNumber = cusNumber;
		formData.cdmCategoryId = $("#combobox_zbbpQueryCategoryName").combobox("getValue");;
		formData.dmdDprtmntId = $("#combobox_DrpmntName").combobox("getValue");
		
		$("#gridId_zbbp_query").grid("option", "postData", formData);
		$("#gridId_zbbp_query").grid("reload","${ctx}/zbgl/mbbm/searchData"); 
	}
	
	function clear() {
		if( USER_LEVEL =='3' && USER_LEVEL != null) {
			$("#combobox_zbbpQueryCategoryName").combobox("clear");
		} else {
			$("#formId_zbbp_query").form("reset");
		}
	}
	
	function openDailog(event, ui) {
		
		var url;
		var	selectedCategory = $("#combobox_zbbpQueryCategoryName").combobox("getValue");

		if(ui.id == "btnId_add") {
			
			url = "${ctx}/zbgl/zbbp/toEdit";
			$("#dialogId_zbbp").dialog({
				width :1400,
				height : 900,
				title : ui.label,
				url : url				 
			});
			$("#dialogId_zbbp").dialog("open");
			
		}else {	
			var selarrrow = $("#gridId_zbbp_query").grid("option","selarrrow");
			var rowData = $("#gridId_zbbp_query").grid("getRowData", selarrrow[0]);
			
			if(selarrrow && selarrrow.length == 1 ) {
				
				if(ui.id == "btnId_edit"){
					var dmdZt = rowData.dmdZt;
					if(dmdZt=='1'){
						$.message({message:"该值班表为发布状态,不能修改,如要修改,请先撤销！", cls:"waring"});
						return;
					}else{
						url = "${ctx}/zbgl/zbbp/toEdit?id=" +rowData.id+ "&categoryId=" +rowData.cdmCategoryId+ "&dmdDprtmntId=" +rowData.dmdDprtmntId+ "&dmdModeId="
						+rowData.dmdModeId+ "&dmdStartDate=" +rowData.dmdStartDate+ "&dmdEndDate=" +rowData.dmdEndDate+"&dmdName="+rowData.dmdName;
				        url =encodeURI(url);
						$("#dialogId_zbbp").dialog({
							width : 1400,
							height : 900,
							title : ui.label,
							url : url				 
						});
						$("#dialogId_zbbp").dialog("open");
					}
					
				}else if(ui.id == "btnId_view") {
					
					url = "${ctx}/zbgl/zbbp/toView?id=" +rowData.id+ "&categoryId=" +rowData.cdmCategoryId+ "&dmdDprtmntId=" +rowData.dmdDprtmntId+ "&dmdModeId="
					+rowData.dmdModeId+ "&dmdStartDate=" +rowData.dmdStartDate+ "&dmdEndDate=" +rowData.dmdEndDate;
					
					$("#dialogId_zbbp_view").dialog({
						width : 1200,
						height : 800,
						title : ui.label,
						url : url				 
					});
					$("#dialogId_zbbp_view").dialog("open");
				}else if(ui.id == "btnId_print") {

                    url = "${ctx}/zbgl/zbbp/toPrint?id=" +rowData.id+ "&categoryId=" +rowData.cdmCategoryId+ "&dmdDprtmntId=" +rowData.dmdDprtmntId+ "&dmdModeId="
                        +rowData.dmdModeId+ "&dmdStartDate=" +rowData.dmdStartDate+ "&dmdEndDate=" +rowData.dmdEndDate+"&print=1";

                    $("#dialogId_zbbp_print").dialog({
                        width : 1100,
                        height : 800,
                        title : ui.label,
                        url : url
                    });
                    $("#dialogId_zbbp_print").dialog("open");
                }
				else if(ui.id == "btnId_export") {
					url = "${ctx}/zbgl/zbbp/exportExcel?id=" +rowData.id+ "&categoryId=" +rowData.cdmCategoryId+ "&dmdDprtmntId=" +rowData.dmdDprtmntId+ "&dmdModeId="
                        +rowData.dmdModeId+ "&dmdStartDate=" +rowData.dmdStartDate+ "&dmdEndDate=" +rowData.dmdEndDate;
                    window.location.href = url;
				}
				
			} else {
				$.message({message:"请选择一条记录！", cls:"waring"});
				return;
			}			
		}
	}
	/**
     *
     *打开打印预览页面
     * */
	function openPrint() {
       var  url = "${ctx}/zbgl/zbbp/toPrint";
        $("#dialogId_zbbp_print").dialog({
            width : 1100,
            height : 800,
            title :"打印值班表",
            url : url
        });
        $("#dialogId_zbbp_print").dialog("open");
    }


	function queryInitDrpList(flag) {
		
		var	selectedCategory = $("#combobox_zbbpQueryCategoryName").combobox("getValue");
		
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/lbbm/searchAllData',
			data: {
				dcdCategoryId : selectedCategory,
				param : '1'
			},
			dataType : 'json',
			success : function(data) {
				
				for (var i = 0; i < data.length; i++) {
					
					 	for(var j in combobox_bm) {
					 		if(data[i].value == combobox_bm[j].value) {
					 			data[i].text = combobox_bm[j].text;
					 			break;
					 		}
					 	}
					 }
				$("#combobox_DrpmntName").combobox("loadData",data);
				
				if(flag) {
					$('#combobox_DrpmntName').combobox("setValue",drpmntId);
					search();
				}
			},
		});
	}
	
	/**
	 * 查询判断此类别或部门是否在生效日期或截止日期时间段内已有排班
	 */
	function validateDutydate(formData) {
		
		var returnData = '';
		
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/mbbm/searchAllData',
			data: formData,
			dataType : 'json',
			async: false,
			success : function(data) {
				
				returnData = data;
			},
		});
		return returnData;
	}
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




    function createPrint() {
	    var result ;
        var formData = $("#formId_zbbp_edit").form("formData");
        var zhzjobId =formData.zhzjobId;//指挥长
        var jobCount = formData.jobCount;
        var allPoliceList = '';
        //var dutyId= '';
        var dutyId =new Array();
        $("#dutyform").find("tr").each(function(i) {
            $(this).find(".jobTd").each(function(j) {
                dutyId[j] = $(this).attr("id");

            })
        })
        $("#dutyform").find("tr").each(function(i) {
            // debugger;
            if(i>1) {
                var police = '';
                $(this).find(".dutyTd").each(function(g) {
                    if(g == 0) {
                        var tdId = dutyId[g]+ "&";
                    }else{
                        var tdId = "," +dutyId[g]+ "&";
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

                if(i == 2) {
                    allPoliceList += police;
                }else if(i > 2){
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
                url : '${ctx}/zbgl/zbbp/getPrint',
                data: formData,
                dataType : 'json',
                async:false,
                success : function(data) {
                    $.loading("hide");
                    result = data;
                },
                error : function(XMLHttpRequest, textStatus, errorThrown) {

                    $.message({message:"操作失败！", cls:"error"});
                }
            });
        } else {
            $.message({message:"注意！每个值班岗位至少有一个有值班人员！", cls:"waring"});
            return ;
        }

        return result;
    }




	function creatSavaData(formData){
		var zhzjobId =formData.zhzjobId;//指挥长
		var jobCount = formData.jobCount;
   		var allPoliceList = '';
		//var dutyId= '';  
		var dutyId =new Array();
		$("#dutyform").find("tr").each(function(i) {
		 $(this).find(".jobTd").each(function(j) {
				dutyId[j] = $(this).attr("id");
				 
			})
		})
		$("#dutyform").find("tr").each(function(i) {
			 // debugger;
			if(i>1) {
						var police = '';  
						$(this).find(".dutyTd").each(function(g) {
							if(g == 0) {
								var tdId = dutyId[g]+ "&";
							}else{
								var tdId = "," +dutyId[g]+ "&";
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
						
						if(i == 2) {
							allPoliceList += police;
						}else if(i > 2){
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
	
	function updateZt(type){
		var msg = "是否发布该值班排班表!"
		if(type=='0'){
			msg ="是否撤销该值班排班表!"
		}
		var selarrrow = $("#gridId_zbbp_query").grid("option","selarrrow");
		var rowData = $("#gridId_zbbp_query").grid("getRowData", selarrrow[0]);
		if(selarrrow && selarrrow.length == 1){
			var dmdZt =rowData.dmdZt; 
			if(type =="0"){
				if(dmdZt=='0'){
					$.message({message:"已经为撤销状态！", cls:"waring"});
					return;
				}
			}else{
				if(dmdZt=='1'){
					$.message({message:"已经为发布状态！", cls:"waring"});
					return;
				}
			}
			$.confirm(msg, function(r) {
				if(r) {
					var id = rowData.id;        //模板部门表主键
					$.ajax({
						type : 'post',
						url : '${ctx}/zbgl/mbbm/updateDmdZtById',
						data: {
							id : id,
							dmdZt:type
						},
						dataType : 'json',
						success : function(data) {
							$.loading("hide");
							if(data.code == "200") {
								$.message({message:data.message, cls:"success"});
								$("#gridId_zbbp_query").grid("reload");
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
			});
		}else{
			$.message({message:"请选择一条记录！", cls:"waring"});
			return;
		}
	}
	
	
	function deleteById() {
		
		var selarrrow = $("#gridId_zbbp_query").grid("option", "selarrrow");	
		var rowData =$("#gridId_zbbp_query").grid("getRowData", selarrrow[0]);
		var flag = true;
		if(selarrrow && selarrrow.length == 1) {
			if( USER_LEVEL =='3' && USER_LEVEL != null) {
				flag = (rowData.dmdDprtmntId == drpmntId ? true:false);
			} 
			if(flag == true) {
				$.confirm("是否确认删除 [\""+ rowData.dmdStartDate + " ~ " + rowData.dmdEndDate + "] ? 注意！此时间段下的编排的值班民警将会一并删除'", function(r) {
					if(r) {
						var id = rowData.id;        //模板部门表主键
						$.loading({text:"正在处理中，请稍后..."});
						$.ajax({
							type : 'post',
							url : '${ctx}/zbgl/zbbp/deleteByConditions',
							data: {
								dbdDutyModeDepartmentId : id,
							},
							dataType : 'json',
							success : function(data) {
								
								$.loading("hide");
								if(data.code == "1") {
									$.message({message:"操作成功！", cls:"success"});
									$("#gridId_zbbp_query").grid("reload");
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
				}); 		
			} else {
				$.message({message:"注意！只能删除本监区记录！", cls:"waring"});
			}
		} else {
			$.message({message:"请选择一条记录！", cls:"waring"});
			return;
		}		
	}



	
</script>