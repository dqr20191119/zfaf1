<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.coral-tabs>.coral-tabs-nav>li>.coral-tabs-anchor {
	font-size: 18px;
}
#formId_zfda th{
    padding: 10px 10px 10px 30px;
    vertical-align: top;
    font-size: 13px;
    text-align:right;
    
}
#formId_zfda {
    padding-top: 0px;
    padding-bottom: 0px;
}
#formId_zfda table tr:nth-child(even) {
	background: #FFF
}

#formId_zfda table tr:nth-child(odd) {
	background: #F5FAFA
}

#zfdaTabId li{
	text-align:center;
	font-size: 15px;
    padding-right: 25px;
    padding-left: 25px;
	} 
	
.zfdaPicture td{
	padding: 10px 20px 20px 100px
	}
</style>

<cui:tabs id="zfdaTabId" heightStyle="fill">
	<ul style="font-size: 25px">
		<li><a href="#fragment_zfda">罪犯档案</a></li>
		<li style="font-size: 25px"><a href="${ctx}/xxhj/zfjbxx/rewardsPunishment?pprPrsnrIdnty=${pbdPrsnrIdnty}&&pprCusNumber=${pbdCusNumber}">考核奖惩</a></li>
		<li style="font-size: 25px"><a href="${ctx}/xxhj/zfjbxx/finanicalManagement?pbdPrsnrIdnty=${pbdPrsnrIdnty}&&pbdCusNumber=${pbdCusNumber}">收支管理</a></li>
		<li style="font-size: 25px"><a href="${ctx}/xxhj/zfjbxx/healthRecord?pbdPrsnrIdnty=${pbdPrsnrIdnty}&&pbdCusNumber=${pbdCusNumber}">健康档案</a></li>
		<%-- <li><a href="${ctx}/xxhj/zfjbxx/behavioralControl">行为掌控</a></li> --%>
		<li><a href="${ctx}/xxhj/zfjbxx/prisonerMeeting?pbdPrsnrIdnty=${pbdPrsnrIdnty}&&pbdCusNumber=${pbdCusNumber}">罪犯会见</a></li>
		<li><a href="${ctx}/xxhj/zfjbxx/prisonerPhone?pbdPrsnrIdnty=${pbdPrsnrIdnty}&&pbdCusNumber=${pbdCusNumber}">亲情电话</a></li>
	</ul>
	<div id="fragment_zfda" >
       	<cui:form id="formId_zfda" >
			<table class="table_zfda" style="align:center; margin:10px 10px 10px 10px;height: 690px; width: 980px; border: #B3D0F4 2px solid; border-collapse:separate; border-spacing:8px;" >
				<tr>   
					<th>编号：</th>
				    <td><cui:input id="prisonerId1" name="PBD_PRSNR_IDNTY" ></cui:input></td>
				    <th>证件类型：</th>
				    <td><cui:combobox id="crtfctsType"  name="PBD_CRTFCTS_TYPE" data="combobox_zjlx" ></cui:combobox></td>
			    </tr>
			    <tr>
				    <th>单位：</th>
				    <td><cui:input id="prisonzone_number"  name="PBD_PRSN_AREA" readonly="true"></cui:input></td>
				    <th>证件号码：</th>
				    <td><cui:input id="Id_number" name="PBD_CRTFCTS_IDNTY" readonly="true"></cui:input></td>
				</tr>	
				<tr>   
					<th>姓名：</th>
				    <td><cui:input id="prisonername"   name="PBD_PRSNR_NAME" ></cui:input></td>
				    <th>婚姻情况：</th>
				    <td><cui:combobox id="mrrge_stts"  data="combobox_hyqk" name="PBD_MRRGE_STTS"></cui:combobox></td>
				</tr>
				<tr>   
					<th>性别：</th>
				    <td><cui:combobox id="prisonSex"  name="PBD_SEX_INDC" data="combobox_xb"></cui:combobox></td>
					<th>捕前文化：</th>
				    <td><cui:combotree id="educational_background" data="combobox_bqwh" name="PBD_CLTRE_LEVEL"></cui:combotree></td>
				    
				</tr>
				<tr>   
					<th>出生日期：</th>
				    <td><cui:input id="birthdate"  name="PBD_BIRTH_DATE"></cui:input></td>
				    <th>入监起期日：</th>
				    <td><cui:input id="prison_startdate"  name="PBD_ENTRY_PRISONER_DATE" ></cui:input></td>
				
				</tr>
				<tr>   
					<th>民族：</th>
				    <td><cui:combobox id="nationality"  data="combobox_mz" name="PBD_NATION" ></cui:combobox></td>
				    <th>判处刑期： </th>
				    <td><cui:input id="prison_term"  name="PBD_SNTN_TERM" ></cui:input></td>
				</tr>
			    <tr>   
					<th>剥夺政治权利：</th>
				    <td><cui:input id="chargedate"  name="PBD_SNTN_DPRVTN_TERM"  readonly="true"></cui:input></td>
				    <th>刑期起日：</th>
				    <td><cui:input id="prison_startdate"  name="PBD_SNTN_START_DATE" ></cui:input></td>
				</tr>
			    <tr>   
					<th>分管等级：</th>
				    <td><cui:combobox id="level"  data="combobox_fgdj" name="PBD_SPRT_MNGE" ></cui:combobox></td>
				    <th>刑期止日：</th>
				    <td><cui:input id="prison_enddate"  name="PBD_SNTN_END_DATE"></cui:input></td>
				</tr>
				<tr> 
					<th>分押类型：</th>
				    <td><cui:combobox id="chargeType"  data="combobox_fylb" name="PBD_CHARGE_TYPE"></cui:combobox></td>
				    <th>罪名：</th>
				    <td><cui:combotree id="chargeName"  multiple="true"  data="combobox_zm" name="PBD_ACCSTN" ></cui:combotree></td>
			    </tr>
			    <tr>
				    <th>籍贯：</th>
				    <td><cui:combobox id="native_address"  data="combobox_jg" name="PBD_NATIVE_ADDRS" ></cui:combobox></td>
				    <th>家庭住址：</th>
				    <td>
				    <cui:combobox id="shengShiQu"  data="combobox_jg"  name="PBD_SHENGSHIQU" ></cui:combobox>
				    <cui:input id="homeAddress"  name="PBD_HOME_ADDRS" ></cui:input>
				    </td>
			    </tr>
			    <tr>
			    	<td colspan="4">
				    	 <table class="zfdaPicture" style="padding:5px 5px 5px 5px;width:900px;background-color:white">
						    <tr>
							    <td><img src="" id="picLeft" width="120" height="150"></td>
								<td><img src="" id="picMiddle" width="120" height="150"></td>
								<td><img src="" id="picRight" width="120" height="150"></td>
						    </tr>
						</table>
			    	</td>
			    </tr>
	    	</table>
		</cui:form>
	</div>
</cui:tabs>

<script>

    //证件类型
	var combobox_zjlx = <%=CodeFacade.loadCode2Json("4.5.5")%>;
	//籍贯
	var combobox_jg = <%=CodeFacade.loadCode2Json("4.3.2")%>;
	//分押类别
	var combobox_fylb = <%=CodeFacade.loadCode2Json("4.6.5")%>;
	//性别
	var combobox_xb = <%=CodeFacade.loadCode2Json("4.3.3")%>;
	//民族
	var combobox_mz = <%=CodeFacade.loadCode2Json("4.3.6")%>;
	//婚姻情况
	var combobox_hyqk = <%=CodeFacade.loadCode2Json("4.3.4")%>;
	//捕前文化
	var combobox_bqwh = <%=CodeFacade.loadCode2SimpleTreeJson("4.3.7",null,5)%>;
	//分管等级
	var combobox_fgdj = <%=CodeFacade.loadCode2Json("4.6.6")%>;
	//籍贯
    var combobox_jg = <%=CodeFacade.loadCode2Json("4.3.2")%>;  
    //罪名
    var combobox_zm = <%=CodeFacade.loadCode2SimpleTreeJson("4.4.1", null, 5)%>;
    
	$.parseDone(function() {

 		var url = "${ctx}/xxhj/zfjbxx/queryInfoPrisonerArchives?prisonerId=${pbdPrsnrIdnty}&cusNumber=${pbdCusNumber}";
		
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				if (data) {
					loadData(data);
					loadPrisonerPic();
				} else {
					
					$.alert("获取数据失败！");
				}
			},
			});
	});
	
	function loadData(data) {
		
		$("#formId_zfda").form("setReadOnly",true);
        $("#formId_zfda").form("loadData",data[0]);
	}
	//查询照片
	 function loadPrisonerPic() {
	
		 $("#picLeft").attr("src","${ctx}/common/all/findZfPicInfo?zfBh=${pbdPrsnrIdnty}&mtNrFl=4");
		 $("#picMiddle").attr("src","${ctx}/common/all/findZfPicInfo?zfBh=${pbdPrsnrIdnty}&mtNrFl=3");
		 $("#picRight").attr("src","${ctx}/common/all/findZfPicInfo?zfBh=${pbdPrsnrIdnty}&mtNrFl=5");
	 }
</script>