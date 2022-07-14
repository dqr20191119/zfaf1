<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<div style="padding:0px 5px 5px 5px;">
	<cui:form id="formId_jnmjPoliceInfo">
		<table style="padding-left:15px; width: 100%; border: #B3D0F4 2px solid; border-collapse:separate; border-spacing:10px; background-color:#F5FAFA;">
			<tr>
				<th style="text-align:right">警号：</th>
				<td><cui:input id="policeNumber" name='PBD_POLICE_IDNTY'></cui:input></td>
				<th style="text-align:right">警务通号：</th>
				<td><cui:input id="policeCmmnct" name='PBD_POLICE_CMMNCT'></cui:input></td>
				<td rowspan="6" width="100px"></td>
				<td rowspan="6"><img src="" padding-left="50px" id="policePic" width="180" height="210"></td>
			</tr>
			<tr>
				<th style="text-align:right">姓名：</th>
				<td><cui:input id="policeName" name='PBD_POLICE_NAME'></cui:input></td>
				<th style="text-align:right">对讲呼号：</th>
				<td><cui:input id="talkNum" name='PBD_TALK_NUM'></cui:input></td>
			</tr>
			<tr>
				<th style="text-align:right">性别：</th>
				<td><cui:input id="policeSex" name='PBD_SEX'></cui:input></td>
				<th style="text-align:right">监狱短号：</th>
				<td><cui:input id="prisonShortNumber" name='PBD_SHORT_PHONE'></cui:input></td>
			</tr>
			<tr>
				<th style="text-align:right">出生日期：</th>
				<td><cui:input id="brithDate" name='PBD_BIRTH_DATE'></cui:input></td>
				<th style="text-align:right">固定电话：</th>
				<td><cui:input id="fixedPhone" name='PBD_FIXED_PHONE'></cui:input></td>
			</tr>
			<tr>
				<th style="text-align:right">部门：</th>
				<td><cui:input id="departmentName" name='PBD_DRPTMNT'></cui:input></td>
				<th style="text-align:right">手机号码：</th>
				<td><cui:input id="phone" name='PBD_PHONE'></cui:input></td>
			</tr>
			<tr>
				<th style="text-align:right">职务：</th>
				<td><cui:input id="position" name='PBD_PSTN_NAME'></cui:input></td>
				<th style="text-align:right">住宅电话：</th>
				<td><cui:input id="homePhoneNumber" name='PBD_FAMILY_PHONE'></cui:input></td>
			</tr>
		</table>
	</cui:form>
	
	<div style="border:#B3D0F4 2px solid;">
		<cui:form id="formId_policeInfoCheck">
			<table style="border-collapse:separate;border-spacing:8px;width:100%">
			 	<tr>
					<td>监所名称：&nbsp;&nbsp;<cui:combobox id="criteria_jnmjPbdPrsn" data="combobox_jy"  required="true" width="140" onSelect="onFaultTypeSelect" ></cui:combobox></td>
				    <td>部门：&nbsp;&nbsp;<cui:combobox id="criteria_jnmjPbdDrptmnt"  width="140"  required="false" name="orgName"></cui:combobox></td>
			        <%-- <td>警号：&nbsp;&nbsp;<cui:input id="criteria_jnmjPbdPoliceIdnty" width="140"  ></cui:input></td>
					<td>民警姓名：&nbsp;&nbsp;<cui:input id="criteria_jnmjPbdPoliceName"  width="140" ></cui:input></td> --%>
					<td>民警警号/姓名：&nbsp;&nbsp;<cui:input id="criteria_jnmjPbdPoliceIdntyOrName"  width="140" ></cui:input></td>
				    <td><cui:button label="查询" onClick="setQueryCriteria" componentCls="btn-primary"></cui:button></td>
					<td><cui:button id="resetBtn" label="重置" onClick="resetHandler"></cui:button></td>
					<cui:input id="criteria_policeList" width="140" type="hidden" ></cui:input>
				</tr>
			</table>
	    </cui:form>
		
		<cui:grid id="gridId_policeInfoList" singleselect="true" fitStyle="fill"  pager="true" 
		          onDblClickRow="getRowId"  onComplete="loadFirstData" rowNum="10" height="380">	          
	 		<cui:gridCols>
				<cui:gridCol name="PBD_CUS_NUMBER" width="150" align="center" hidden="true">监所编号</cui:gridCol>
				<cui:gridCol name="PBD_DRPTMNT_ID" width="150" align="center" hidden="true">部门编号</cui:gridCol>
				<cui:gridCol name="OBD_ORGA_NAME" width="150" align="center">监所名称</cui:gridCol>
				<cui:gridCol name="PBD_POLICE_IDNTY" width="150" align="center">警号</cui:gridCol>
				<!-- 
				<cui:gridCol name="PBD_POLICE_NAME" width="150" align="center" formatter="formatEvidenceUseOprt">姓名</cui:gridCol>
				 -->
				<cui:gridCol name="PBD_POLICE_NAME" width="150" align="center">姓名</cui:gridCol>
				<cui:gridCol name="PBD_DRPTMNT" width="150" align="center">部门</cui:gridCol>
				<cui:gridCol name="PBD_BIRTH_DATE" width="150" align="center">出生日期</cui:gridCol>
				<cui:gridCol name="PBD_PSTN_NAME" width="150" align="center">职务</cui:gridCol>
				<!-- 
				<cui:gridCol name="PBD_SZQY" width="150" align="center" formatter = "FormatterSzqy">所在区域</cui:gridCol>
				
				<cui:gridCol name="PBD_TRACK" width="150" align="center" formatter = "trackFormatter">人员轨迹</cui:gridCol>
				 
				<cui:gridCol name="inOut_record" width="150" align="center" formatter="Formatter">进出记录</cui:gridCol>-->
				<cui:gridCol name="PBD_SEX" width="150" align="center" formatter="formatter" hidden ="true">性别</cui:gridCol>
				<cui:gridCol name="PBD_POLICE_CMMNCT" width="150" align="center" hidden ="true">警务通号</cui:gridCol>
				<cui:gridCol name="PBD_SHORT_PHONE" width="150" align="center" hidden ="true">监狱短号</cui:gridCol>
				<cui:gridCol name="PBD_TALK_NUM" width="150" align="center" hidden="true">对讲呼号</cui:gridCol>
				<cui:gridCol name="PBD_FIXED_PHONE" width="150" align="center" hidden="true">固定电话</cui:gridCol>
				<cui:gridCol name="PBD_FAMILY_PHONE" width="150" align="center" hidden="true">住址电话</cui:gridCol>
				<cui:gridCol name="PBD_PHONE" width="150" align="center" hidden="true">电话</cui:gridCol>
				<cui:gridCol name="PBD_LOGIN_NAME" width="150" align="center" hidden="true">登陆姓名</cui:gridCol>
			</cui:gridCols> 		 
			<cui:gridPager gridId="gridId_policeInfoList" />      	          
		</cui:grid>
	</div>	
	
	<cui:dialog id="dialogId_policeInfo" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true"></cui:dialog>
	<input type="hidden" id="levels" />
	<div id="dialog_mjxx"></div>
</div>

<script type="text/javascript">

	var USER_LEVEL = jsConst.USER_LEVEL; 
	var thisCusNumber = jsConst.CUS_NUMBER;
	var combobox_jy = <%=AuthSystemFacade.getAllJyJsonInfo()%>;
	var combobox_xb = <%=CodeFacade.loadCode2Json("4.3.3")%>;
	var config = "${config}";
	
    $.parseDone(function(){
    	
		var drptmnt = "${drptmntId}";
		var cusNumber = "${cusNumber}";
		var config = "${config}";
		var policeNoList = "${policeNoList}";
		/* var policeId ='';
		var policeName = "${policeName}"; */
		var policeIdntyOrName = "${policeIdntyOrName}";
		// alert("jnmjPoliceInfo policeName is " + policeName);
		drptmnt = (drptmnt == null ? "" : drptmnt);
	
		var data = {				
			 cusNumber:cusNumber,
			 drptmnt : drptmnt,
			 policeNoList:policeNoList,
			 /* policeId : policeId,
			 policeName : policeName, */
			 policeIdntyOrName: policeIdntyOrName
		};	
		
		if(USER_LEVEL==1) {
			// 监狱局权限
			loadProvMethods(data);
		} else if(USER_LEVEL==2) {
			// 监狱权限
			loadPrisMethods(data);
		} else {
			// 监区权限
			loadDrpmntMethods(data);
		}
    });
	function formatEvidenceUseOprt(cellvalue, options, rawObject) {
		var html =  "<a href='javascript: void(0);' onclick='openMjxx(1)' style='color: #4692f0; margin-left: 5px;'>"+cellvalue+"</a>";
		return html;	
	}
	function loadProvMethods(data) {
		
		//省局权限
		$('#criteria_jnmjPbdPrsn').combobox("setValue",cusNumber+"");
		$("#criteria_jnmjPbdDrptmnt").combobox("reload",  "${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox?cusNumber=" + data.cusNumber);
		$('#criteria_jnmjPbdDrptmnt').combobox("setValue",data.drptmnt+"");
		/* $("#criteria_jnmjPbdPoliceName").textbox("setValue", data.policeName); */
		$("#criteria_jnmjPbdPoliceIdntyOrName").textbox("setValue", data.policeIdntyOrName);
		$("#criteria_policeList").textbox("setValue", data.policeNoList);
		setLoadCriteria(data.cusNumber,data);
	} 
	
	function loadPrisMethods(data) {
		
		// 监狱权限
		$('#criteria_jnmjPbdPrsn').combobox("option","readonly",true);
		$('#criteria_jnmjPbdPrsn').combobox("setValue",data.cusNumber+"");
		$("#criteria_jnmjPbdDrptmnt").combobox("reload",  "${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox?cusNumber=" + data.cusNumber);
		$('#criteria_jnmjPbdDrptmnt').combobox("setValue",data.drptmnt+"");
		/* $("#criteria_jnmjPbdPoliceName").textbox("setValue", data.policeName); */
		$("#criteria_jnmjPbdPoliceIdntyOrName").textbox("setValue", data.policeIdntyOrName);
		$("#criteria_policeList").textbox("setValue", data.policeNoList);
		setLoadCriteria(data.cusNumber,data);
	}
	
	function loadDrpmntMethods(data) {
	
		// 监区权限
		$('#criteria_jnmjPbdPrsn').combobox("option","readonly",true);
		$('#criteria_jnmjPbdDrptmnt').combobox("option","readonly",true);
		$('#criteria_jnmjPbdPrsn').combobox("setValue",data.cusNumber+"");
		$("#criteria_jnmjPbdDrptmnt").combobox("reload",  "${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox?cusNumber=" + data.cusNumber);
		$('#criteria_jnmjPbdDrptmnt').combobox("setValue",data.drptmnt+"");
		/* $("#criteria_jnmjPbdPoliceName").textbox("setValue", data.policeName); */
		$("#criteria_jnmjPbdPoliceIdntyOrName").textbox("setValue", data.policeIdntyOrName);
		$("#criteria_policeList").textbox("setValue", data.policeNoList);
		setLoadCriteria(data.cusNumber,data);
	}
	
	function setLoadCriteria(cusNumber,data){
		var args = {				
			 sqlId :'${ctx}/xxhj/jnmj/queryPrisonDrptmntPoliceInfo',
			 whereId :(data.drptmnt == "other" ? "3" : "2"),                        //其他   是3    
			 queryType : true,
			 paraList : {}                               
		};
		
		var list = {};
		/* list.push(cusNumber);//list[0]
		data.drptmnt = data.drptmnt == "other" ? cusNumber : data.drptmnt;
		list.push(data.drptmnt);     
		list.push(data.policeId);   
		list.push(data.policeName); */
		list["cusNumber"] = cusNumber;
		list["drptmnt"] = data.drptmnt == "other" ? cusNumber : data.drptmnt;
		/* list["policeId"] = data.policeId;
		list["policeName"] = data.policeName; */
		list["policeIdntyOrName"] = data.policeIdntyOrName;
		list["config"] = config;
		list["policeNoList"] = data.policeNoList;
		args.paraList = list;
		console.log("jnmjPoliceInfo.jsp setLoadCriteria args = " + JSON.stringify(args));
		loadPoliceInfoList(args);
	}

	function setQueryCriteria() {
		//从生物识别链接进入后再点击查询按钮时查询当前部门下所有民警
		config = (config == "1") ? "" :  config;
		var validFlag  = $("#formId_policeInfoCheck").form("valid");
		
		if(!validFlag) {
			return;
		}
		
		var cusNumber;
		var drptmnt;
		var list = {};
		
		if(USER_LEVEL != 1) { 
 			cusNumber = jsConst.CUS_NUMBER;
 			
 			if(USER_LEVEL == 3) {
 				
 				drptmnt= jsConst.DEPARTMENT_ID; 
 			}else {
 				
 				drptmnt = $("#criteria_jnmjPbdDrptmnt").combobox('getValue');
 			}
		} else {			
			cusNumber = $('#criteria_jnmjPbdPrsn').combobox("getValue");
			drptmnt = $("#criteria_jnmjPbdDrptmnt").combobox('getValue');
			
			if(cusNumber == "") {
				$.message({message:"请选择查询监狱!", cls:"waring"});
				return;
			}
		}
		
		/* var policeId = $("#criteria_jnmjPbdPoliceIdnty").val();
		var policeName = $("#criteria_jnmjPbdPoliceName").val(); */
		var policeIdntyOrName = $("#criteria_jnmjPbdPoliceIdntyOrName").val();
		var sqlId = '${ctx}/xxhj/jnmj/queryPrisonDrptmntPoliceInfo';

		/* list.push(cusNumber);                                                       	
		list.push(drptmnt == "other" ? cusNumber : drptmnt);
		list.push(policeId);
		list.push(policeName);
		list.push(config); */
		
		list["cusNumber"] = cusNumber;
		list["drptmnt"] = drptmnt == "other" ? cusNumber : drptmnt;
		/* list["policeId"] = policeId;
		list["policeName"] = policeName; */
		list["policeIdntyOrName"] = policeIdntyOrName;
		list["config"] = config;
		list["policeNoList"] = $("#criteria_policeList").val();
		var data = {				
			 sqlId: sqlId,
			 whereId :"2",
			 paraList : list,
		}
		
		loadPoliceInfoList(data);
	}
	function openMjxx(type) {
		
		var url;
		if(type == 1) {
			url = "${ctx}/portal/bj/mjxx";
		} else {
			url = "${ctx}/portal/bj/zfxx";
		}
		
		$("#dialog_mjxx").dialog({
	      width: "80%",
	      height: "90%",
	      resizable: false,
	      autoDestroy: true,
	      componentCls: "custom dark", //对话框风格，引入dialog.css后需要加上此属性
	      asyncType: "get",            //ajax请求的dataType
	      autoOpen: false,
	      modal: true,
	      url: url,
	      onOpen: function() {         //对话框打开时自动触发的回调事件
	      }
	     
	    });
		$("#dialog_mjxx").dialog("reload");
        $("#dialog_mjxx").dialog("open")
	}
	
 	//查询并显示民警表格信息
	function loadPoliceInfoList(args) {
		var list = args.paraList;
 		$.ajax({
			type : "post",    
			url : args.sqlId,
			dataType : "json",
			data: {
			    para: args.whereId,
				cusNumber: list.cusNumber,
				drptmntId: list.drptmnt,
				policeIdntyOrName: list.policeIdntyOrName,
				config: list.config,
				policeNoList: list.policeNoList
			},
			success : function(data) {
				if(data){	
					$("#gridId_policeInfoList").grid("reload",data);
				}
			},
		}); 
 		
 		

 		
 		
	}
 	
	//监狱选中后  加载选中监狱下的所有监区
    function onFaultTypeSelect(event, ui) {
	
		$("#criteria_jnmjPbdDrptmnt").combobox("reload",  "${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox?cusNumber="+ ui.item.value);
	}
	
	//自动在form中显示第一条数据
	function loadFirstData() {
    	 
		var rowData = $("#gridId_policeInfoList").grid("getRowData", 1);
		loadPoliceInfo(rowData);
	}
	//加载照片
	function loadPolicePic(rowData) {
		//${ctx}/static/rygl/img/ico-police.png
		$("#policePic").attr("src","${ctx}/common/authsystem/findMjPicInfo?loginName="+rowData.PBD_LOGIN_NAME+"&demptId="+rowData.PBD_DRPTMNT_ID);
		$("#policePic").one("error",function(e){
			$("#policePic").attr("src","${ctx}/static/rygl/img/icon-police.png");
		});
	 }
	
	function getRowId(e, ui) {
		var rowData = $("#gridId_policeInfoList").grid("getRowData", ui.rowId);
		// alert("getRowId rowData is " + JSON.stringify(rowData));
	    loadPoliceInfo(rowData);
	}
	//性别转码
	function formatter(cellValue, options, rowObject) {
		if(cellValue){
			var sex = cellValue.replace(/\s+/g,"");
			var val = "";
			if (sex == '1') {
				val = "男性";
			} else if (sex == '0') {
				val = "女性";
			}
			return val;
		} else {
			return '';
		}
	} 
	// “查看”列
  	function Formatter(cellValue, options, rowObject) {
  
		var para1 = rowObject.PBD_CUS_NUMBER;
		var para2  =rowObject.PBD_POLICE_IDNTY;
		var result = "<a href='' style='color: #4692f0;' onclick='checkrecord("+ para1.toString()+ ",\"" + para2.toString() + "\");return false;'>查 看</a>";
		return result;
	} 
  	//查看 进出记录
	function checkrecord(cusNumber,policeIdnty) {
		$("#dialogId_policeInfo").dialog("option", {
			width : 1000,
			height : 800,
//			modal : true,
			title : "民警进出记录",                
			url : "${ctx}/xxhj/jnmj/jnmjPoliceInoutRecord?policeId=" + policeIdnty + "&cusNumber=" + cusNumber,
		});
		$("#dialogId_policeInfo").dialog("open");
	}
	
	//重置
	function resetHandler() {
			
		if(USER_LEVEL == "1") {
			
			$("#formId_policeInfoCheck").form("clear");			
		} else if (USER_LEVEL == "2") {
			
			$("#criteria_jnmjPbdDrptmnt").combobox("clear");
			/* $("#criteria_jnmjPbdPoliceIdnty").textbox("setValue","");
			$("#criteria_jnmjPbdPoliceName").textbox("setValue",""); */
			$("#criteria_jnmjPbdPoliceIdntyOrName").textbox("setValue","");
		} else {
			/* $("#criteria_jnmjPbdPoliceIdnty").textbox("setValue","");
			$("#criteria_jnmjPbdPoliceName").textbox("setValue",""); */
			$("#criteria_jnmjPbdPoliceIdntyOrName").textbox("setValue","");
		}
	}
	
	function loadPoliceInfo(rowData) {
		$("#formId_jnmjPoliceInfo").form("setReadOnly",true);
		$("#formId_jnmjPoliceInfo").form("loadData",rowData);
		loadPolicePic(rowData);

	}

	 /**
	  * 列表单元格值转码
	  * @param data	转码集合
	  * @param val	转码值
	  * @returns		转码后值
	  */
	 function convertColVal(data, val) {
	 	for(var i in data) {
	 		if(val == data[i].value) {
	 			return data[i].text;
	 		}
	 	}
	 	return val;
	 }
	 
	 
	 /**
		 * 人员轨迹
		 */
	function trackFormatter(cellValue, options, rowObject) {
		var result = "<a href='' style='color: #4692f0;'onclick='openRygj(\""+rowObject.PBD_POLICE_IDNTY+"\");return false;'>查看</a>";
		return result;
	}
	/**
	 * 人员轨迹
	 */
	function openRygj(mjbh) {
		var url = "${ctx}/xxhj/jnmj/rygl?mjbh="+mjbh;
		window.open(url, "_blank");
	}
	/**
	*所在区域
	*/
	function FormatterSzqy(cellvalue, options, rawObject) {
		var html = cellvalue;
		if(cellvalue!="公共区域"&&cellvalue!="监区"){
			 html =  "<a href='javascript: void(0);' onclick='openSzqy(\""+rawObject.PBD_POLICE_IDNTY+"\",\""+cellvalue+"\")' style='color: #4692f0; margin-left: 5px;'>"+cellvalue+"</a>";
		}
		
		return html;	
	}
	function openSzqy(mjbh,szqu){
		$.ajax({
			type : 'post',
			url : "${ctx}/xxhj/jnmj/queryCaramer?mjbh="+mjbh+"&szqu="+encodeURI(encodeURI(szqu)),
			dataType : 'json',
			success : function(data) {
				var lagLegth = data.length;
				 
				 var deviceIds = [];
				for(var i=0;i<data.length;i++){
					var caramerId = data[i].ID;
					var caramerName = data[i].CBD_NAME;
					deviceIds.push(caramerId);
				}
				 if (deviceIds.length > 0) {
					videoClient.playVideoHandle({
	                    'subType': 2,
	                    'layout': deviceIds.length,
	                    'data': deviceIds,
	                    'callback': function (data) {
	                    }
	                });
				 }
			}
			});
	}
</script>