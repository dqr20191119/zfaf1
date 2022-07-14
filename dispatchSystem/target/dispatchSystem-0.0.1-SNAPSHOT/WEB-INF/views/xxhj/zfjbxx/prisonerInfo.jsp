<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%
	UserBean user = AuthSystemFacade.getLoginUserInfo();
	String orgName = user.getOrgName();
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>
<style>
	
#formId_prisonerInfo th {
	vertical-align: middle !important;
	font-size: 12px;
	font-weight: bold;
	text-align:right;
}
.fieldset_border {
	border: #B3D0F4 2px solid;
	border-collapse:separate; 
	border-spacing:8px;
	background-color:#F5FAFA;
}
</style>

<div>
	<div style="padding:5px 5px 5px 5px;">
		<cui:form id="formId_prisonerInfo">
			<center>
				<table class="fieldset_border" style="width: 100%">
					<tr>
						<th>罪犯编号：</th>
						<td><cui:input id="prisonerId" name="PBD_PRSNR_IDNTY" componentCls="form-control"></cui:input></td>
						<th>所在监区：</th>
						<td><cui:input id="prisonZone" name="PBD_PRSN_AREA"  componentCls="form-control"></cui:input></td>
						<td rowspan="5" width="10%"><img src="" id="leftPic" width="100" height="130"></td>
						<td rowspan="5" width="10%"><img src="" id="middlePic" width="100" height="130"></td>
						<td rowspan="5" width="10%"><img src="" id="rightPic" width="100" height="130"></td>
					</tr>
					<tr>
						<th>罪犯姓名：</th>
						<td><cui:input id="prisonerName" name="PBD_PRSNR_NAME" componentCls="form-control"></cui:input></td>
						<th>罪名：</th>
						<td><cui:input id="chargeName" name="PBD_ACCSTN" componentCls="form-control"></cui:input></td>
					</tr>
					<tr>
						<th>性别：</th>
						<td><cui:input id="prisonSex" name="PBD_SEX_INDC" componentCls="form-control"></cui:input></td>
						<th>刑种：</th>
						<td><cui:input id="prisonTerm" name="PBD_SNTN_TYPE" componentCls="form-control"></cui:input></td>
					</tr>
					<tr>
						<th>民族：</th>
						<td><cui:input id="nationality" name="PBD_NATION" componentCls="form-control"></cui:input></td>
						<th>刑期起日：</th>
						<td><cui:input id="prisonStartDate" name="PBD_SNTN_START_DATE" componentCls="form-control"></cui:input></td>
					</tr>
					<tr>
						<th>出生日期：</th>
						<td><cui:input id="birthDate" name="PBD_BIRTH_DATE" componentCls="form-control"></cui:input></td>
						<th>刑期止日：</th>
						<td><cui:input id="prisonEndDate" name="PBD_SNTN_END_DATE" componentCls="form-control"></cui:input></td>
					</tr>
				</table>
			</center>
		</cui:form>
	</div>
	
	<div style="border:#B3D0F4 2px solid; margin:0px 5px 0px 5px">
		<cui:form id="formId_prisonerInfoCheck">
			<table style="border-collapse:separate;border-spacing:8px;width:100%">
				<tr>
					<td>监所名称:&nbsp;&nbsp;<cui:combobox id="criteria_pbdPrsn" data="combobox_jy" required="true" width="130" onSelect="onFaultTypeSelect" ></cui:combobox></td>
					<td>所在监区:&nbsp;&nbsp;<cui:combobox id="criteria_pbdPrsnAreae" width="130" name="orgName"></cui:combobox></td>
					<td>罪犯编号/姓名:&nbsp;&nbsp;<cui:input id="criteria_pbdPrsnrIdntyOrName"  width="130"></cui:input></td>
					<td><cui:button label="查询" onClick="setQueryCriteria" componentCls="btn-primary"></cui:button></td>
					<td><cui:button id="resetBtn" label="重置" componentCls="redlight" onClick="resetHandler"></cui:button></td>
				</tr>
			</table>
		</cui:form>
			
		<div>
			<cui:grid id="gridId_prisonerInfo" height="420" colModel="gridColModel_prisonerInfo" frozenColumns="true" shrinkToFit="false" fitStyle="fill" rowNum="10" pager="true" onComplete="loadDefaultData" onDblClickRow="getRowId">
				<cui:gridPager gridId="gridId_prisonerInfo" />
			</cui:grid>
		</div>
	</div>
	<cui:dialog id="dialogId_prisonerInfo" reLoadOnOpen="true" modal="true" iframePanel="true" resizable="false" autoOpen="false"  maximizable="false"></cui:dialog>
	<input type="hidden" id="query" />
	<input type="hidden" id="type" />
	<input type="hidden" id="levels" />
	<div id="dialog_mjxx"></div>
</div>

<script type="text/javascript">
	var USER_LEVEL = jsConst.USER_LEVEL;
	var thisCusNumber = jsConst.CUS_NUMBER; 
	//监狱
	var combobox_jy = <%=AuthSystemFacade.getAllJyJsonInfo()%>;
	//名族
	var combobox_mz = <%=CodeFacade.loadCode2Json("4.3.6")%>;
	//性别
    var combobox_xb = <%=CodeFacade.loadCode2Json("4.3.3")%>;
    //籍贯
    var combobox_jg = <%=CodeFacade.loadCode2Json("4.3.2")%>;  
    //罪名
    var combobox_zm = <%=CodeFacade.loadCode2SimpleTreeJson("4.4.1", null, 5)%>;
    //刑种
    var combobox_xz = <%=CodeFacade.loadCode2Json("4.5.17")%>;
  	//@zyzf 0所有罪犯 1重要罪犯
	var zyzf = "${zyzf}" ? "${zyzf}" : 0;
	$.parseDone(function() {
		var query = "${query}";                       
		var type = "${type}";//罪犯种类 全局一致
		var prisonArea ="${drpmntId}";//罪犯监区编号
		var prisonAreaName ="${drpmntName}";//罪犯监区编号
		/* var prsnrName = "${prsnrName}";  */
		var prsnrIdntyOrName = "${prsnrIdntyOrName}";
		// alert("parseDone type1 is " + type);
		
		$("#type").val(type);
		$("#query").val(query);
		type = (type == "null" || type == "" ? "" : parseInt(type));
		prisonArea = (prisonArea == "0" || prisonArea == "null" ? "" : prisonArea);
		// alert("parseDone type2 is " + type);
		
		var data = {
			query : query,
			type : type,
			prisonArea : prisonArea,
			prsnrIdntyOrName: prsnrIdntyOrName,
			prisonAreaName:prisonAreaName
		};
		// alert("parseDone USER_LEVEL = " + USER_LEVEL);
		if(USER_LEVEL == 1) {
			//省局用户
			loadProvMethods(data);
		} else if(USER_LEVEL == 2) {
			//监狱用户
			loadPrisMethods(data);
		} else {
			//监区用户
			loadDrpmntMethods(data);
		}
	});
	
	/**
	 * 加载省局用户所展示的模块
	 */
	function loadProvMethods(data){
		
		var cusNumber = "${cusNumber}";               //   只有省局权限需要接收监狱编号 
		var prisonArea = "${drpmntId}";                //   省局接收监区编号
		cusNumber = (cusNumber == null ? "" : cusNumber);
		data.cusNumber = cusNumber;
	 	$('#criteria_pbdPrsn').combobox("setValue",cusNumber+"");
	 	$('#criteria_pbdPrsnAreae').combobox("setValue",data.prisonArea+"");
		$("#criteria_pbdPrsnAreae").combobox("reload", "${ctx}/common/authsystem/findAllJqByJyKeyForCombobox?cusNumber=" + cusNumber);	//加载监狱监区
		$("#criteria_pbdPrsnrIdntyOrName").textbox("setValue", data.prsnrIdntyOrName);
		
		setLoadCriteria(cusNumber,data);
	}  
	/**
	 * 加载监狱用户所展示的模块
	 */
	function loadPrisMethods(data){
		// alert("loadPrisMethods data = " + JSON.stringify(data));
		
		var prisonArea = "${drpmntId}";  	           //  监狱权限接收罪犯监区编号
		$('#criteria_pbdPrsn').combobox("option","readonly",true);
		$('#criteria_pbdPrsn').combobox("setValue",jsConst.CUS_NUMBER+"");     //  监狱权限自己的监狱编号
		$("#criteria_pbdPrsnAreae").combobox("reload",  "${ctx}/common/authsystem/findAllJqByJyKeyForCombobox?cusNumber=" + jsConst.CUS_NUMBER); 		//加载监狱监区
		$('#criteria_pbdPrsnAreae').combobox("setValue", data.prisonArea+"");
		/* $("#criteria_pbdPrsnrName").textbox("setValue", data.prsnrName); */
		$("#criteria_pbdPrsnrIdntyOrName").textbox("setValue", data.prsnrIdntyOrName);
		
		setLoadCriteria(jsConst.CUS_NUMBER,data);
	}
	/**
	 * 加载监区用户所展示的模块
	 */
	function loadDrpmntMethods(data){
		
		$('#criteria_pbdPrsn').combobox("option","readonly",true);
		$('#criteria_pbdPrsnAreae').combobox("option","readonly",true);
		$('#criteria_pbdPrsn').combobox("setValue",jsConst.CUS_NUMBER+"");       //  监区权限自己的监狱编号
		$("#criteria_pbdPrsnAreae").combobox("reload",  "${ctx}/common/authsystem/findAllJqByJyKeyForCombobox?cusNumber=" + jsConst.CUS_NUMBER); 		//加载监狱监区
		$('#criteria_pbdPrsnAreae').combobox("setValue",jsConst.DEPARTMENT_ID+"");     //  监区权限自己的监区编号
		$("#criteria_pbdPrsnrIdntyOrName").textbox("setValue", data.prsnrIdntyOrName);// 罪犯编号或姓名
		
		setLoadCriteria(jsConst.CUS_NUMBER,data);
	}
    //省局视角 监狱选中 根据 监狱加载监区
	function onFaultTypeSelect(event, ui) {
	
		$("#criteria_pbdPrsnAreae").combobox("reload",  "${ctx}/common/authsystem/findAllJqByJyKeyForCombobox?cusNumber=" + ui.item.value);
	}
	
	function setLoadCriteria(cusNumber,data){
		var list = {};
		list["cusNumber"] = cusNumber;
		list["prsnAreaIdnty"] = data.prisonArea == "other" ? cusNumber : data.prisonArea; // other 就是cusNumber，不是other 就是自己的监区编号
		/* list["prsnrIdnty"] = $("#criteria_pbdPrsnrIdnty").val();
		list["prsnrName"] = $("#criteria_pbdPrsnrName").val(); */
		list["prsnrIdntyOrName"] = $("#criteria_pbdPrsnrIdntyOrName").val();
		list["prisonAreaName"] = data.prisonAreaName;
		
		data.list = list;

		setQueryArgs(data);
	}
	/**
	 * 设置罪犯查询条件
	 */
	function setQueryCriteria(){
		//查询时清除重要罪犯条件查询所有
		zyzf = 0;
		// alert("setQueryCriteria" + "USER_LEVEL = " + USER_LEVEL);
		var query = $("#query").val();
		var type = $("#type").val();
		var prisonArea; 
		var cusNumber;
		
		var list = {};
		
		if(USER_LEVEL != 1){
			
			cusNumber = jsConst.CUS_NUMBER;
			if( USER_LEVEL == 3) {
				prisonArea= jsConst.DEPARTMENT_ID; 
				prisonArea = $("#criteria_pbdPrsnAreae").combobox('getText');
 			} else {
 				prisonArea = $("#criteria_pbdPrsnAreae").combobox('getText');
 			}
		}else {			
			cusNumber = $('#criteria_pbdPrsn').combobox("getValue");
			prisonArea = $("#criteria_pbdPrsnAreae").combobox("getText");
	
			if(cusNumber == "") {
				$.alert("请选择查询监狱");
				return;
			}
		}
		list["cusNumber"] = cusNumber;
		list["prsnAreaIdnty"] = prisonArea == "other" ? cusNumber : prisonArea;
		list["prisonAreaName"] = prisonArea == "other" ? cusNumber : prisonArea;
		/* list["prsnrIdnty"] = $("#criteria_pbdPrsnrIdnty").val();
		list["prsnrName"] = $("#criteria_pbdPrsnrName").val(); */
		list["prsnrIdntyOrName"] = $("#criteria_pbdPrsnrIdntyOrName").val();
		// list.push(cusNumber);                                                    // list[0]
		// list.push(prisonArea == "other" ? cusNumber : prisonArea);               // list[1]
		// list.push($("#criteria_pbdPrsnrIdnty").val());                            // list[2]
		// list.push($("#criteria_pbdPrsnrName").val());                            // list[3]
		
		var data = {
			query : query,
			type : type,
			list : list
		}
		setQueryArgs(data);
	}
	/**
	 * 选择对应类型的sql条件
	 */
	function setQueryArgs(data) {
		console.log("setQueryArgs data = " + JSON.stringify(data));
		var list = data.list;
		var url = null ;
		var type = data.type == "" ? "" : parseInt(data.type);
		var whereId = type;      
		
		if (data.query == 1) {                 //从老的今日押犯 p_prisonerToToday 传来的query == 1
			
			url="${ctx}/xxhj/zfjbxx/queryPrisonerBriefInfo";
			
			if(type == 0) {                   //在押罪犯
				
			} else if(type == 1) {             //当时释放
			
			} else if(type == 2) {             //当日收押
			
			} else if (type == 3) {             //累计解回再审   list[4]
			
			    list["prsnrSttsIndc"] = 22; 
			} else if (type == 4) {            //特许离监   list[4]
				
			    whereId = 3;
			    list["prsnrSttsIndc"] = 15; 
			} else if(type == 5) {
				
				whereId = 0;
			} else if(type == 7) {
				
				whereId = 1;
			} else if(type == 8) {            //监外就医  list[4]
			
				whereId = 2;
			
			//从首页进来的没有 其他选项
			} else if(type == 21) {          //邪教罪犯
				
				whereId = 0;
			} else if(type == 22) {          //维族罪犯
				
				whereId = 0;
			}else if (type == 31){          //外籍罪犯
	        	
	            
	        } else if (type == 23){         //限制减刑
	        	
	           
	        } else if (type == 24){         //当日列控
	        	
	           
	        } else if (type == 32 ){       //一级预警
	        	
	           
	        } else if (type == 25){        //顽危罪犯
	          
	        }    
			
			if(data.prisonArea == "other") {
				
				if(type == 0) {
					
					whereId = 5;
				}else if(type == 1) {
					
					whereId = 6;
				}else if(type == 2) {
					
					whereId = 7;
				}else if(type == 3 || type == 4 || type == 5) {
					
					whereId = 8;
				}else if(type == 6) {      //type为6 暂未出现
					whereId = 9;
				}
			}
		} else if (data.query == 2 ) {                 // 从罪犯基本信息传来  
			
			url = "${ctx}/xxhj/zfjbxx/queryPrisonerInfo";
		}
	    // alert("prisonerInfo url = " + url);
		var args = {
				
			url : url,
			para : whereId,
			queryType : type,
			list : list
		};
		console.log("setQueryArgs args = " + JSON.stringify(args));
		loadPrisonerInfoList(args);
	}	
	/**
	 * 列表加载罪犯信息
	 */
	function loadPrisonerInfoList(args) {
		
		var list=args.list;
		var url=args.url;
		var queryType=args.queryType;
		var para=args.para;
		//list["prisonAreaName"] = data.prisonAreaName;
		console.log(list);
		var data = {
			type: queryType,
			para: para,
		    cusNumber: list.cusNumber,
		    prsnAreaIdnty: list.prisonAreaName,
		    /* prsnrIdnty: list.prsnrIdnty,
		    prsnrName: list.prsnrName, */
		    prsnrIdntyOrName: list.prsnrIdntyOrName,
		    prsnrSttsIndc: list.prsnrSttsIndc,
		    zyzf:zyzf
		};
		$("#gridId_prisonerInfo").grid("option", "postData", data);
		$("#gridId_prisonerInfo").grid("reload", url);

	}
     //自动加载第一条记录
	 function loadDefaultData() {
    	 
		var rowData = $("#gridId_prisonerInfo").grid("getRowData", 1);
	
		loadPrisonerPic(rowData);
		loadPrisonerInfo(rowData);
	}
	//查询照片
	function loadPrisonerPic(rowData) {
	
		$("#leftPic").attr("src","${ctx}/common/all/findZfPicInfo?zfBh=" +rowData.PBD_PRSNR_IDNTY+ "&mtNrFl=4");
		$("#middlePic").attr("src","${ctx}/common/all/findZfPicInfo?zfBh=" +rowData.PBD_PRSNR_IDNTY+ "&mtNrFl=3");
		$("#rightPic").attr("src","${ctx}/common/all/findZfPicInfo?zfBh=" +rowData.PBD_PRSNR_IDNTY+ "&mtNrFl=5");
		
		$("#leftPic").one("error",function(e){
			$("#leftPic").attr("src","${ctx}/static/rygl/img/icon-prisoner.png");
		});
		$("#middlePic").one("error",function(e){
			$("#middlePic").attr("src","${ctx}/static/rygl/img/icon-prisoner.png");
		});
		$("#rightPic").one("error",function(e){
			$("#rightPic").attr("src","${ctx}/static/rygl/img/icon-prisoner.png");
		});
		
		
		
		
	}
	
	function Formatter(cellValue, options, rowObject) {

		/* var param1 = rowObject.PBD_PRSNR_IDNTY;
		var param2 = rowObject.PBD_CUS_NUMBER;
		var result = "<a href='' style='color: #4692f0;'onclick='checkrecord( "+ param1.toString()
				+ "," + param2.toString() + ");return false;'>查看</a>";
		
		return result; */
		var param1 = rowObject.PBD_PRSNR_PRIMARY_KEY;
		// alert(param1);
		// var result = "<a href='' style='color: #4692f0;' onclick='openHyZfxx(" + param1.toString() + ");return false;'>查看</a>";
		var result = "<a href='' style='color: #4692f0;' onClick='openHyZfxx(\"" + param1.toString() + "\");return false;'>查看 </a>";
		return result;
	}
	function formatEvidenceUseOprt(cellvalue, options, rawObject) {
		var cZfbh= rawObject.PBD_PRSNR_IDNTY;
		var html =  "<a href='javascript: void(0);' onclick='openMjxx(\""+cZfbh+"\")' style='color: #4692f0; margin-left: 5px;'>"+cellvalue+"</a>";
		return html;	
	}
	function openMjxx(cZfbh) {
		var url;
        url = "${ctx}/xxhj/zfxxcx/zfxxcxInfo?cZfbh="+cZfbh;

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
	
	var gridColModel_prisonerInfo = [{
		label : "监狱编号",
		name : "PBD_CUS_NUMBER",
		hidden : true,
		frozen: true
	},{
		label : "罪犯标识",
		name : "PBD_PRSNR_PRIMARY_KEY",
		hidden : true,
		frozen: true
	},{
		label : "监所名称",
		name : "PBD_CUS_NAME",
		align:"center",
		width:"100",
		frozen: true,
		formatter : function(cellvalue, options, rawObject) {
	    	return '<%=orgName %>';
		}
	},{
		label : "所在监区",
		name : "PBD_PRSN_AREA",
		align:"center",
		width:"100",
		frozen: true
	},{
		label : "罪犯编号",
		name : "PBD_PRSNR_IDNTY",
		align:"center",
		width:"100",
		frozen: true
	},{
		label : "罪犯姓名",
		name : "PBD_PRSNR_NAME",
		align:"center",
		width:"100",
		frozen: true 
		//,formatter:"formatEvidenceUseOprt"
	}/*,{
		label : "人员轨迹",
		name : "PBD_TRACK",
		formatter : "trackFormatter",
		align:"center",
		width:"80",
		frozen: true
	},{
		label : "详情信息",
		name : "PBD_REMARK",
		formatter : "Formatter",
		align:"center",
		width:"80",
		frozen: true
	},{
		label : "进出记录",
		name : "PBD_JCJL",
		formatter : "FormatterJcjl",
		align:"center",
		width:"80",
		frozen: true
	},{
		label : "所在区域",
		name : "PBD_SZQY",
		formatter : "FormatterSzqy",
		align:"center",
		width:"80",
		frozen: true
	},{
		label : "重控犯",
		name : "PBD_ZKF",
		formatter : "FormatterZkf",
		align:"center",
		width:"80",
		frozen: true
	}*/,{
		label : "性别",
		name : "PBD_SEX_INDC",
		align:"center",
		width:"100",
		formatter : function(cellvalue, options, rawObject) {
			if(cellvalue) {
		    	return convertColVal(combobox_xb, cellvalue);
	    	} else {
	    		return '';
		    } 
		}
	},{
		label : "民族",
		name : "PBD_NATION",
		align:"center",
		width:"100",
		formatter : function(cellvalue, options, rawObject) {
			if(cellvalue) {
		    	return convertColVal(combobox_mz, cellvalue);
	    	} else{
	    		return '';
	    	} 
		}
	},{
		label : "出生日期",
		name : "PBD_BIRTH_DATE",
		align:"center",
		width:"100"
	},{
		label : "罪名",
		name : "PBD_ACCSTN",
		align:"center",
		width:"200",
		formatter : function(cellvalue, options, rawObject) {
			if(cellvalue) {
				var list = cellvalue.split(",");
				var value = '';
				if (list.length > 1) {
					for(var i = 0; i < list.length; i++) {
						if(i< list.length-1) {
							value = convertColValTree(combobox_zm, list[i])+","+value;
						}else {
							value = value+convertColValTree(combobox_zm, list[i]);
						}
					}
					return value;
				} else {
					return convertColValTree(combobox_zm, cellvalue)
				}	
			} else {
				return '';
			}
		}
	},{
		label : "刑期",
		name : "PBD_SNTN_TERM",
		align:"center",
		width:"100"
		
	},{
		label : "刑种",
		name : "PBD_SNTN_TYPE",
		align:"center",
		width:"100",
		formatter: function(cellvalue, options, rawObject) {
			if(cellvalue) {
		    	return convertColVal(combobox_xz, cellvalue);
	    	} else {
	    		return '';
		    } 
		}
	},{
		label : "籍贯",
		name : "PBD_NATIVE_ADDRS",
		align:"center",
		width:"200",
	    formatter : function(cellvalue, options, rawObject) {
	    	if(cellvalue) {
		    	return convertColVal(combobox_jg, cellvalue);
	    	} else {
	    		return '';
		    } 
		}
	},{
		label : "邢起日期",
		name : "PBD_SNTN_START_DATE",
		hidden : true
	},{
		label : "邢止日期",
		name : "PBD_SNTN_END_DATE",
		hidden : true
	}/*,{
		label : "监舍",
		name : "PBD_SZJS",
		align:"center",
		hidden : false
	},{
		label : "床位",
		name : "PBD_SZCW",
		align:"center",
		hidden : false
	}*/
	]
	
	 function getRowId(e, ui) {
		
		var rowData = $("#gridId_prisonerInfo").grid("getRowData", ui.rowId);
 		loadPrisonerPic(rowData);             //加载罪犯照片
   	    loadPrisonerInfo(rowData);            //加载罪犯信息
	}
	 
	function loadPrisonerInfo(rowData) {
		
		$("#formId_prisonerInfo").form("setReadOnly",true);
		$("#formId_prisonerInfo").form("loadData",rowData);
	
	} 
	
	function resetHandler() {
		
		if(USER_LEVEL == 1) {
			
			$("#formId_prisonerInfoCheck").form("clear");
			
		} else if(USER_LEVEL == 2) {
			
			$("#criteria_pbdPrsnAreae").combobox("clear");
			$("#criteria_pbdPrsnrIdntyOrName").textbox("setValue","");
		
		} else {
			$("#criteria_pbdPrsnrIdntyOrName").textbox("setValue","");
		}
	}
	
	//链接罪犯信息一卡通
	function checkrecord(PBD_PRSNR_IDNTY, PBD_CUS_NUMBER) {
	
		$("#dialogId_prisonerInfo").dialog("option",
			{
				width : 1000,
				height : 800,
				title : "罪犯一卡通",
 				url : "${ctx}/xxhj/zfjbxx/criminalArchivesFile?prisonerId="+ PBD_PRSNR_IDNTY+ "&cusNumber="+ PBD_CUS_NUMBER
 		});
		$("#dialogId_prisonerInfo").dialog("open");
	}
	
	/**
	 * 人员轨迹
	 */
	function trackFormatter(cellValue, options, rowObject) {
		var result = "<a href='' style='color: #4692f0;'onclick='openRygj(\""+rowObject.PBD_PRSNR_IDNTY+"\");return false;'>查看</a>";
		return result;
	}
	/**
	*进出记录
	*/
	function FormatterJcjl(cellvalue, options, rawObject) {
		var html =  "<a href='javascript: void(0);'  onclick='openJcjl(\""+rawObject.PBD_PRSNR_IDNTY+"\")' style='color: #4692f0; margin-left: 5px;'>"+cellvalue+"</a>";
		return html;	
	}
	/**
	*所在区域
	*/
	function FormatterSzqy(cellvalue, options, rawObject) {
		var html = cellvalue;
		if(cellvalue!="公共区域"&&cellvalue!="监区"){
			 html =  "<a href='javascript: void(0);' onclick='openSzqy(\""+rawObject.PBD_PRSNR_IDNTY+"\",\""+cellvalue+"\")' style='color: #4692f0; margin-left: 5px;'>"+cellvalue+"</a>";
		}
		
		return html;	
	}
	
	function openJcjl(zfbh){
		$("#dialogId_prisonerInfo").dialog("option",
				{
					width : 1000,
					height : 800,
					title : "进出记录",
	 				url : "${ctx}/xxhj/zfjbxx/prisonJcjl?zfbh="+zfbh
	 		});
			$("#dialogId_prisonerInfo").dialog("open");
	}
	
	function openSzqy(zfbh,szqu){
		$.ajax({
			type : 'post',
			url : "${ctx}/xxhj/zfjbxx/queryCaramer?zfbh="+zfbh+"&szqu="+encodeURI(encodeURI(szqu)),
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
	
	/**
	 * 依图
	 * 登录
	 */
	function openQd() {
		/* var url = "http://192.168.9.33:11180/#/login?username=qd&password=qd";
		window.open(url, "_blank"); */
	}
	/**
	 * 依图
	 * 人员轨迹
	 */
	function openRygj(zfbh) {
		/* $("#dialogId_prisonerInfo").dialog("option",
			{
				width : 1000,
				height : 800,
				title : "人员轨迹",
 				url : "http://192.168.9.33:11180/#/login?username=qd&password=qd"
 		});
		$("#dialogId_prisonerInfo").dialog("open"); */
		
		/* var url = "http://192.168.9.33:11180/#/trace/index";
		window.open(url, "_blank"); */
		var url = "${ctx}/xxhj/zfjbxx/rygl?zfbh="+zfbh;
		window.open(url, "_blank");
	}

	/**
	 * 华宇
	 * 罪犯信息页
	 */
	function openHyZfxx(cId) {
		// alert("cId = " + cId);
		var url = "http://192.168.8.242/jy-search/form/5839f63344d35cac2de67149156c63d4/display?zfid=" + cId;
		window.open(url, "_blank");
	}
</script>

