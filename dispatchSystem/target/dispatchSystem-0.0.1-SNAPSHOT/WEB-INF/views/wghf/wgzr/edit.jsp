<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>
<style>
#zbbpPoliceList span{
	display:inline-block;
	border:2px solid #B3D0F4;
	line-height:25px;
	width:100px;
	white-space:pre-wrap;
	text-align: left;
    padding-left: 10px;
}
</style>
<div>	
	<div style="margin: 5px 5px 5px 5px;">
		<table border="0" >
			<tr>
				<td>
					<table>
						<tr>
							<td>
								<div id="policeTable" style="width: 180px; height: 400px; display: block;">
								 <fieldset> 
								 	<div padding="5px">
									 	<cui:combobox id="combobox_drpmntId" width="140" data="combobox_bm" enablePinyin="true" onChange="onSelect" ></cui:combobox>
										<div id="checkBoxButton" style="padding-top:5px;">
											<span style="padding-right:5px;"><cui:button onClick="chkListAll" label="全选" componentCls="btn-primary"></cui:button></span>
											<span style="padding-right:5px;"><cui:button onClick="chkListClear" label="清空" componentCls="btn-primary"></cui:button></span>
											<span><cui:button onClick="chkListEnsure" label="添加" componentCls="btn-primary"></cui:button></span>	
										</div>
									</div>	
									<div id="policeList" style ="width: 150px;height:400px;overflow :auto;">
										<table id="zbbpPoliceList" align="center" style="margin:5px; border-collapse: separate; border-spacing: 8px;" ondrop="drop(event)" ondragover="allowDrop(event)">
										</table>
									</div>	
								 </fieldset>
					             </div>
							</td>
							<td id="dutyTable" style="vertical-align: top; padding-left: 20px;">
								<div id="dutyList" style="position:absolute; width:980px; height:500px; overflow: auto;margin:5px 5px 5px 5px">
									<table border="1"  id="dutyform" style="table-layout:fixed;width:800px;">
									</table>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</div>
	
<script>
	var cusNumber = jsConst.CUS_NUMBER;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var drpmntId = jsConst.DEPARTMENT_ID;
	
	var id = '${id}';                      //模板部门表的主键
	var dmdDprtmntId = '${dmdDprtmntId}';  
	var categoryId = '${categoryId}';
	var dmdModeId = '${dmdModeId}'    //模板的主键
	var dmdStartDate = '${dmdStartDate}';
	var dmdEndDate = '${dmdEndDate}';
	var flag = false; 
	var flags = true;          /* 监区的USER_LEVEL是3,权限被控制 */
	
	$.parseDone(function() {
		
		if(USER_LEVEL != null && USER_LEVEL == '3') {
			$("#comboxId_zbbp_edit_CategoryName").combobox("reload","${ctx}/zbgl/lbbm/searchAllData?dcdDprtmntId="+drpmntId+"&param=2");
			$("#comboxId_zbbp_edit_DrpmntName").combobox("option","readonly",true);
		} else {
			$("#comboxId_zbbp_edit_CategoryName").combobox("reload","${ctx}/zbgl/lbgl/searchAllData?dcaStatus=1");
		}
	});
	/* 查询模板数据 */
	var modeData = null;
	function initModeData(param) {
	
		$("#dutyform").empty();
		var selectedModeId = $("#comboxId_zbbp_edit_ModeName").combobox("getValue");   //模板的主键
			
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/mbsz/getById',
			data: {
				mojCusNumber : cusNumber,
				modelId : selectedModeId,
				id : id,              //模板部门表主键
			},
			dataType : 'json',
			success : function(data) {
				
				createDutyForm(data);
				modeData = data;
			}
		});
	}
	/* 解析表格数据 */
	var order = null;
	var orderCount = null;


	function createDutyForm(data) {
		
		$("#param").textbox("setValue",param);       //后台判断参数 
		order = (data.cdmOrderData ).split(";");
		orderCount = order.length; 
		$("#dateDiff").textbox("setValue",dateDiff+1);
	    var title = "<tr style='background-color:#337ab7;color:white;font-size:13px;font-weight: bold'><td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:60px; word-break: keep-all;white-space:nowrap;'>管理格格长</td><td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:80px;word-break: keep-all;white-space:nowrap;'>区域格格长</td><td align='center' style='border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:150px; height:80px;word-break: keep-all;white-space:nowrap;'>基础格格长</td>"
	    title = title+ "</tr>";
	    $("#dutyform").append(title); 
	    var jobCount =  0;
	  	   	for(var i = 0;i < order.length; i++) {
	 	    	var orderID = (order[i].split("-")[0]).split("_")[0];
		    	var orderName = (order[i].split("-")[0]).split("_")[1];
		    	var job = (order[i].split("-")[1]).split(",");
		    	jobCount = parseInt(jobCount + job.length);      //岗位数统计
		    	for(var j = 0;j < job.length; j++) {
		    		var jobID = (job[j]).split("_")[0];  //模板详情表中的主键
		    		var jobName = (job[j]).split("_")[1];
		    		var item = "<tr>"; 
					var tdPeople = "";
					for(var k = 0; k < 3; k++) {
						tdPeople = tdPeople + "<td id='p"+i+"_"+j+"o"+k+"_"+k+"' align='center' style='width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;' ondrop='drop(this)' ondragover='allowDrop(event)' onClick='checkList(this)'><ul id='"+i+""+j+""+k+"_"+k+"' class='dutyTd'></ul></td>"	
					}
					item = item + tdPeople;
					item = item + "</tr>";
					$("#dutyform").append(item);
				}
	    	}
	    $("#jobCount").textbox("setValue",jobCount);
	    
	    $("#dutyList").mCustomScrollbar({
			theme : "minimal-light",
			autoExpandScrollbar : true,
			axis: "xy"
		});
	}	
	
	var tdColorId = null;  //选中的td的id
	function checkList(data) {
	
		if(tdColorId) {
			$("#" +tdColorId).css({"backgroundColor":""});
		} 
		tdColorId = (data.id).trim();
		$("#" +tdColorId).css({"backgroundColor":"#E0FFFF"});
	}
	 
	function chkListClear() {
		 $("input:checkBox").removeAttr("checked");
	}
	
	function chkListAll() {
		$("input:checkBox").prop("checked","checked");	
	}
	
	function chkListEnsure(e, ui) {
	
		var list = new Array();
		$("#zbbpPoliceList input[type='checkbox']:checked").each(function (i) {
			
			var id = $(this).attr("id");
			var name =  $(this).attr("name");
			list.push({"policeId" : id , "policeName" : name});			     
		});

		if(list.length > 0 && tdColorId != null ) {
			addCheckList(list,tdColorId);
		} else if (list.length == 0) {
			$.message({message:"请选择值班人员！", cls:"waring"});
		} else {
			$.message({message:"请选中添加区域！", cls:"waring"});
		}
	 }
	
	function addCheckList(list,pId) {
		for(var i = 0; i < list.length; i++) {
			
			var policeId = list[i].policeId;
			var policeName = list[i].policeName;

			var sameId = $("#id_" +pId+ "-" +policeId);
			
			if(sameId.length > 0 && sameId != '') {
				continue;
			}else {
				var li = "<li id='id_" +pId+ "-" +policeId+ "' style='font-size:13px;margin:8px'>" +policeName+ "<input class='hTd' type='hidden' value='" +policeName+ "'/><a href='javascript:void(0);' id='" +pId+ "-" +policeId+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li>"
				$("#" +pId+ " ul").append(li);
			}
		}
	}
	
	function onSelect(event, ui){
	
		initPoliceList(ui.value);
	}
	
	/* 加载选中部门民警 */
	function initPoliceList(DrpmntId) {
		
		if(DrpmntId) {
			selectedDrpmntId = DrpmntId;
		}
		$("#zbbpPoliceList").empty();
		$("#combobox_drpmntId").combobox("setValue",selectedDrpmntId);
		var tab = $("#zbbpPoliceList");
		tab.css({"border-collapse":"separate","border-spacing": "8px"});
		
		$.ajax({
			type : 'post',
			url : '${ctx}/common/authsystem/findDeptPoliceForCombotree',
			data: {
				cusNumber : cusNumber,
				id : selectedDrpmntId,
			},
			dataType : 'json',
			success : function(data) {
				
				var policeTr = null;
				for(var i = 0; i < data.length; i++) {
					policeTr = "<tr><td style='font-size:13px; width:140px; height:30px; word-break: keep-all; white-space:nowrap;'>"
							 	+ "<ul ondragstart='dragStart(this)'   ondrag='dragging(this)' draggable='true' ondrop='drop(event)' ondragover='allowDrop(event)'  id='" +data[i].id+ "' ><span>" 
							 	+ "<input class= 'checkBox' id='" +data[i].id+ "'name='" +data[i].name+ "' type='checkbox'/> &nbsp;&nbsp;&nbsp;" +data[i].name+ "</span></ul></td></tr>";
							 	
					tab.append(policeTr);
				}
				$.parser.parse(tab);
			}				
		});
		$("#policeList").mCustomScrollbar({
			theme : "minimal-light",
			autoExpandScrollbar : true
		});
	}
	/**
	 * 拖动事件
	 */
	 var policeName = null;
	 var policeId = null;
	 function dragStart(data) {
		 
		 policeName = (data.innerText).trim();
		 policeId = data.id;
	}
	 
	function dragging(event) {
	  
	}
	
	function allowDrop(event) {
	    event.preventDefault();
	}
	
 	/* 判断是否重复选择警员 */
	function drop(data) {
	 
		var pId = data.id;
		var sameId = $("#id_" +pId+ "-" +policeId);
		
		if(sameId.length > 0 && sameId != '') {
			$.message({message:"重复选择！请拖拽正确的值班人员到值班表！", cls:"waring"});
		}else {
			var li = "<li id='id_" +pId+ "-" +policeId+ "' style='font-size:13px;margin:8px'>" +policeName+ "<input class='hTd' type='hidden' value='" +policeName+ "'/><a href='javascript:void(0);' id='" +pId+ "-" +policeId+ "' style='color:red;font-weight: bold' onClick='deleteSelPolice(this)'>&nbsp &nbsp X</a></li>"
			$("#" +pId+ " ul").append(li);
		}
	} 
	
	function deleteSelPolice(data) {
		var id = data.id;
		$("#id_" +id).remove();
	}
	
	/**
	 * 获取td数量
	 */
	function getDutyTdPeopleLen() {
		
		var cloneNum = 0;
		var tdCount = 0;
		var seq = 0;
		var fSeq = 0;
		
		$("#dutyform").find("tr").each(function(i) {
			
			var seqN = 0;
			if(i == 1) {
				$(this).find(".dutyTd").each(function() {
					if($(this).find("li").html()) {
						cloneNum++;
						var liId = $(this).find("li").attr("id");
						fSeq =  parseInt(liId.split("o")[1].split("_")[0]) + 1; //第一行有效td个数
					}
				});
			}
			
			$(this).find(".dutyTd").each(function(j) {
				 if($(this).find("li").html()) {
					tdCount++;                          //算出已排班的td个数
					var liId = $(this).find("li").attr("id");
					seqN =  parseInt(liId.split("o")[1].split("_")[0]) + 1;  //每个<tr>的<td>序号
				}
			});
			seq =  seq + seqN ;
		});
		
		var jobCount = parseInt($("#jobCount").textbox("getValue"));
		var tableCount = jobCount*(dateDiff + 1);
		
		if (tdCount == tableCount) {
			
			$.message({message:"请勿重复复制！", cls:"waring"});
			
		} else if(seq == tdCount && (seq/fSeq) == jobCount) {
			
			if(cloneNum*jobCount == tdCount) {
				return cloneNum;          //已排班天数
			} else {
				$.message({message:"请将排班填写完整！", cls:"waring"}); 
			}
		} else {
			$.message({message:"请将排班填写完整！", cls:"waring"}); 
		}
	}

</script>