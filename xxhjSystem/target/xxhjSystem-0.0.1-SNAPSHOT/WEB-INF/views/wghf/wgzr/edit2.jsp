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
#dialog-buttons{
	position:relative;
	top:280px;
}
#policeTable {
  position: relative;
  top: 200px;
}
#dutyTable {
  position: relative;
  top: 200px;
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
									<table border="1" id="dutyform" style="table-layout:fixed;width:600px;">
									      <tr style="background-color:#337ab7;color:white;font-size:13px;font-weight: bold">
											   <td align="center" style="border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:130px; height:80px;word-break: keep-all;white-space:nowrap;">管理格格长</td>
											   <td td="" align="center" style="border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:130px; height:80px; word-break: keep-all;white-space:nowrap;" id="title_0">区域格格长<br></td>
											   <td td="" align="center" style="border: 2px solid #B3D0F4; padding:10px 20px 15px 20px; width:130px; height:80px; word-break: keep-all;white-space:nowrap;" id="title_1">基础格格长<br></td>
										   </tr>	
											<tr>
												<td id="p1_0o0_0" align="center" style="width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;" ondrop="drop(this)" ondragover="allowDrop(event)" onclick="checkList(this)"><ul id="100_0" class="dutyTd"></ul></td>
												<td id="p1_0o0_1" align="center" style="width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;" ondrop="drop(this)" ondragover="allowDrop(event)" onclick="checkList(this)"><ul id="100_1" class="dutyTd"></ul></td>
												<td id="p1_0o1_2" align="center" style="width:80px; height:80px; border: 2px solid #B3D0F4; word-break: keep-all; white-space:nowrap;" ondrop="drop(this)" ondragover="allowDrop(event)" onclick="checkList(this)"><ul id="101_2" class="dutyTd"></ul></td>
											</tr>
									</table>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<div id="dialog-buttons" style="margin-bottom: 10px; text-align: center;">
		<cui:button label="保存" text="false" onClick="buttons_fxcj"></cui:button>
		<cui:button label="取消" text="false" onClick="toClose"></cui:button>
	</div>
</div>
<script>

		var cusNumber = jsConst.CUS_NUMBER;//监狱ID
		var USER_LEVEL = jsConst.USER_LEVEL;//用户等级
		var drpmntId = jsConst.DEPARTMENT_ID;//部门ID
		
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

		
		/* 全选 */
		
		function chkListAll() {
			$("input:checkBox").prop("checked","checked");	
		}
		/* 清空  */
		function chkListClear() {
			 $("input:checkBox").removeAttr("checked");
		}
		/* 添加  */
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
		
		
		function toClose(){
			$("#dialogId_yrzq_edit").dialog("close");
		}
		
</script>