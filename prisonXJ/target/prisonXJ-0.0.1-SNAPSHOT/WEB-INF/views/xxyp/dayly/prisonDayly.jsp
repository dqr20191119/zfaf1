<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style type="text/css">
</style>
<div class="styleContent">
	<input type="hidden" id="cusNumber" value="${cusNumber}"/>
	<%--<div style="float: left;font-size: 14px;width: 100%;margin-bottom: 5px;">
		<div style="float: left;">
			<div class="date_div">
				当前时间：<span class="spanTime"><span id="ymd"></span>&nbsp;&nbsp;00:00:00 ~ <span id="dateTime"></span></span>&nbsp;&nbsp;&nbsp;
			</div>
			<a href="javascript:void(0);" onclick="refurbish();" style="color: rgb(4,181,113);">刷新</a>
		</div>
		<div style="float: right;">
			日期：<cui:datepicker id="date" dateFormat="yyyy-MM-dd" />&nbsp;&nbsp;&nbsp;
			<button class="submit" onclick="queryData();">查询</button>
			<button class="downLoad" onclick="toExportDayly();">导出</button>
		</div>
	</div>--%>
	<div class="showForm" style="margin-top: 20px;width: 100%; height: 570px;overflow-y: auto;">
		<table>
			<tr style="height: 30px;">
				<td class="one" width="170">项目</td>
				<td class="one" width="772">内容</td>
			</tr>
			<tr>
				<td>值班监狱领导</td>
				<td colspan="2" id="ld"></td>
			</tr>
			<tr>
				<td>值班指挥长</td>
				<td colspan="2" id="zhz"></td>
			</tr>
			<tr>
				<td>1、民警值班信息</td>
				<td height="50" id="policeBase">
					今日在编民警 <span id="police_1">0</span> 人，
					<span id="police_2">0</span> 个监区，监区白班在岗民警
					<span id="police_3">0</span> 人( 10:00点 )，晚班收封前在岗民警
					<span id="police_4">0</span> 人( 19:00点 )，晚班收封后在岗民警
					<span id="police_5">0</span> 人( 22:00点 )，监狱夜间集中备勤民警 
					<span id="police_6">0</span> 人(其中特警队员<span id="police_7"></span>人)。
				</td>
			</tr>
			<tr>
				<td>2、押犯基本情况</td>
				<td height="55" id="priosnerBase">
					实押罪犯总数 <span id="prisoner_1">0</span> 人，实押危安犯总数
					<span id="prisoner_2">0</span> 人，加戴戒具罪犯数
					<span id="prisoner_3">0</span> 人，关押禁闭罪犯数 
					<span id="prisoner_4">0</span> 人，隔离审查罪犯数
					<span id="prisoner_5">0</span> 人，立案侦查罪犯数 
					<span id="prisoner_6">0</span> 人，解回重审罪犯数
					<span id="prisoner_7">0</span> 人，暂予监外执行罪犯数
					<span id="prisoner_8">0</span> 人，
					<span>老病残罪犯数
					<span id="prisoner_9">0</span> 人。
				</td>
			</tr>
			<tr>
				<td>3、罪犯违规情况</td>
				<td id="prisonerOrder">
					今日监狱发生罪犯违规 <span id="prisonerOrderCount">0</span> 起，
					其中重大、重要狱情 <span id="prisonerOrderCount_2">0</span> 起。
				</td>
			</tr>
			<tr>
				<td>4、罪犯重点人头情况</td>
				<td height="90" id="keynotePrisoner">
					邪教类罪犯数 <span id="keynotePrisoner_1">0</span> 人(法轮功罪犯 <span id="FLGPrisoner">0</span> 人，					
					全能神罪犯 <span id="QNSPrisoner">0</span>人，&nbsp;其他 <span id="otherPrisoner">0</span> 人)，
					危害国家安全类罪犯 <span id="nationalSecurity">0</span> 人，
					维族罪犯数 <span id="keynotePrisoner_2">0</span> 人，
					台湾地区罪犯数 <span id="TaiWanPrisoner">0</span> 人，
					澳门地区罪犯数 <span id="MacaoPrisoner">0</span> 人，
					香港地区罪犯数 <span id="HongKongPrisoner">0</span> 人，
					外籍罪犯数 <span id="WJPrisoner">0</span> 人，
					限制减刑罪犯数 <span id="keynotePrisoner_3">0</span> 人，
					当日列控罪犯数 <span id="keynotePrisoner_4">0</span> 人，
					高度危险犯数 <span id="keynotePrisoner_5">0</span> 人(J3C)，
					监狱级以上顽固犯数量 <span id="keynotePrisoner_6">0</span> 人，
					监狱级以上危险犯数量 <span id="PrisonerDanger">0</span> 人，
					特岗罪犯 <span id="PrisonerSpecialty">0</span> 人，
					内控对象 <span id="PrisonerInternallyPiloting">0</span> 人，
					老弱病残犯 <span id="lbcPrisoner">0</span> 人,
					刑释安置帮教必接必送 <span id="bjbsPrisoner">0</span> 人。
				</td>
			</tr>
			<tr>
				<td>5、罪犯惩处、高危管控情况</td>
				<td height="50" id="confinePrisoner">
					今日加戴戒具 <span id="confinePrisoner_3">0</span> 人(累计 <span id="confinePrisoner_3_1">0</span> 人)，
					禁闭 <span id="confinePrisoner_1">0</span> 人(累计 <span id="confinePrisoner_1_1">0</span> 人)，
					隔离审查 <span id="confinePrisoner_4">0</span> 人(累计 <span id="confinePrisoner_4_1">0</span> 人)，
					立案侦查 <span id="confinePrisoner_5">0</span> 人(累计 <span id="confinePrisoner_5_1">0</span> 人)，
					高危管控罪犯 <span id="confinePrisoner_2">0</span> 人(累计 <span id="confinePrisoner_2_1">0</span> 人)。
				</td>
			</tr>
			<tr>
				<td>6、罪犯监内就医情况</td> 
				<td id="prisonInsideVis">
					今日监内就诊 <span id="prisonInsideVisCount">0</span> 人，
					监内住院 <span id="prisonInsideHsplCount">0</span> 人。
				</td>
			</tr>
			<!--<tr>
				<td>6、罪犯监内住院情况</td>
				<td id="prisonInsideHspl">
					今日监狱累计监内就诊 <span id="prisonInsideVisCount">0</span> 人，
					涉及 <span id="prisonInsideVis_1">0</span> 个监区，
					就诊人数最多的监区是 <span id="prisonInsideVis_2">无</span> 。
					今日监狱累计监内住院 <span id="prisonInsideHsplCount">0</span> 人，
					涉及 <span id="prisonInsideHspl_1">0</span> 个监区，
					住院人数最多的监区是 <span id="prisonInsideHspl_2">无</span>。
				</td>
			</tr>-->
			<tr>
				<td>7、罪犯外出情况</td>
				<td id="prisonerOut">
					今日监外就诊 <span id="prisonerOut_1">0</span> 人
					(已回监 <span id="prisonerOut_1_1">0</span> 人)，监外住院 
					<span id="prisonerOut_2">0</span> 人
					(已回监 <span id="prisonerOut_2_2">0</span> 人，累计
					<span id="prisonerOut_2_1">0</span> 人)，离监探亲
					<span id="prisonerOut_3">0</span> 人，特许离监探亲 
					<span id="prisonerOut_4">0</span> 人，
					<!--解回再审 
					<span id="prisonerOut_5">0</span> 人(累计
					<span id="prisonerOut_5_1">0</span> 人)，-->
					其他外出 
					<span id="prisonerOut_6">0</span> 人。 
				</td>
			</tr>
			<tr>
				<td>8、罪犯会见情况</td>
				<td id="meetingInfo">
					今日会见 <span id="meetingInfo_1">0</span> 个监区，
					会见人数 <span id="meetingInfo_2">0</span> 人
					（其中零星会见 <span id="meetingInfo_3">0</span> 人、外来提审 <span id="meetingInfo_4">0</span> 人）。
				</td>
			</tr>
			<tr>
				<td>9、罪犯会见复听情况</td>
				<td id="meetingInfoDbl">
					今日监狱累计复听会见录音 <span id="meetingInfoDblCount">0</span> 人次。
				</td>
			</tr>
			<tr>
				<td>10、外来车辆进出情况</td>
				<td id="foreigncar">
					今日进监车辆 <span id="foreigncar_1">0</span> 辆，涉及 
					<span id="foreigncar_2">0</span> 个监区，其中，进出最多的监区是 
					<span id="foreigncar_3">无</span>，共进出 
					<span id="foreigncar_4">0</span> 辆次。
				</td>
			</tr>
			<tr>
				<td>11、外来人员进出情况</td>
				<td id="foreignpeople">
					今日进监人员 <span id="foreignpeople_1">0</span> 人（其中监内施工<span id="worker_in_prison">0</span>人），涉及
					<span id="foreignpeople_2">0</span> 个监区，其中，进出人数最多的监区是 
					<span id="foreignpeople_3">无</span>，共进出 
					<span id="foreignpeople_4">0</span> 人次。
				</td>
			</tr>
			<tr>
				<td>12、网络技防运行情况</td>
				<td id="technicalDevice">
					今日技防设备故障 <span id="technicalDeviceCount">0</span> 个
				</td>
			</tr>
		<tr>
				<td>13、报警情况</td>
				<td id="alarm">
					今日发生报警 <span id="alarmCount">0</span> 起
				</td>
			</tr>
			<tr>
				<td>14、安全生产情况</td>
				<td id="safetyProduction"></td>
			</tr>
			<tr>
				<td>15、舆情情况</td>
				<td id="opinionInfo"></td>
			</tr>
			<tr style="display: none;">
				<td>16、社区信息</td>
				<td id="communityInfo"></td>
			</tr>
			<tr>
				<td>16、上访信息</td>
				<td id="petitionInfo"></td>
			</tr>
			<tr>
				<td>17、监内施工情况</td>
				<td id="constructionInfo"></td>
			</tr>
			<tr>
				<td>18、监狱相关要求</td>
				<td id="require"></td>
			</tr>
			<tr>
				<td>19、省局通报、要求</td>
				<td id="provInspectNotice"></td>
			</tr>
			<tr>
				<td>20、省局通报、要求整改情况</td>
				<td id="correctFeedback">
					<a href="javascript:void(0);" onclick="toCircularChanged();">省局通报整改汇总</a>
				</td>
			</tr>
		</table>
	</div>
	<div class="dialogWindow" id="dialogWindow_one">
		<div class="title">
			<span>监区列表</span>
			<div class="close" onclick="closeWindow_one();"></div>
		</div>
		<div class="list"></div>
	</div>
	<div class="dialogWindow" id="dialogWindow_two">
		<div class="title">
			<span></span>
			<div class="close" onclick="closeWindow_two();"></div>
		</div>
		<div class="table"></div>
	</div>
	<!-- 导出word -->
	<div id="pageContent" style="display: none;">
		<h3>值班监狱领导</h3>	
		<p id="wText_ld"></p>
		<h3>值班指挥长</h3>
		<p id="wText_zhz"></p>
		<h3>1、民警值班信息</h3>
		<p id="wText_1"></p>
		<h3>2、押犯基本情况</h3>
		<p id="wText_2"></p>
		<h3>3、罪犯违规情况</h3>
		<p id="wText_3"></p>
		<h3>4、罪犯重点人头情况</h3>
		<p id="wText_4"></p>
		<h3>5、罪犯惩处、高危管控情况</h3>
		<p id="wText_5"></p>
		<h3>6、罪犯监内就医情况</h3>
		<p id="wText_6"></p>
		<h3>7、罪犯外出情况</h3>
		<p id="wText_7"></p>
		<h3>8、罪犯会见情况</h3>
		<p id="wText_8"></p>
		<h3>9、罪犯会见复听情况</h3>
		<p id="wText_9"></p>
		<h3>10、外来车辆进出情况</h3>
		<p id="wText_10"></p>
		<h3>11、外来人员进出情况</h3>
		<p id="wText_11"></p>
		<h3>12、网络技防运行情况</h3>
		<p id="wText_12"></p>
		<h3>13、报警情况</h3>
		<p id="wText_13"></p>
		<h3>14、安全生产情况</h3>
		<p id="wText_14"></p>
		<h3>15、舆情情况</h3>
		<p id="wText_15"></p>
		<h3>16、上访信息</h3>
		<p id="wText_16"></p>
		<h3>17、监内施工情况</h3>
		<p id="wText_17"></p>
		<h3>18、监狱相关要求</h3>
		<p id="wText_18"></p>
		<h3>19、省局通报、要求</h3>
		<p id="wText_19"></p>
	</div>
</div>
<cui:dialog id="dialog_prisonDayly" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
<script type="text/javascript">
	var jsConst = window.top.jsConst;
	var cusNumber;
	$(function(){
		cusNumber=$("#cusNumber").val();
		if(!cusNumber){
			cusNumber = jsConst.ORG_CODE;
		}
		loadData(formatterDate(new Date()));
	});

	/**
	 * 导出
	 */
	function toExportDayly(){
		
	}
	/**
	 * 加载数据
	 */
	function loadData(date){
		//重置数据，待改善
		$(".showForm").find("span").html("0");
		
		var paramData={
				"pidDate":date,
				"pidCusNumber":cusNumber
		}
		$.ajax({
			type : 'post',
			url : '${ctx}/xxyp/dayly/initDaylyData?cusNumber='+cusNumber,
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					$.ajax({
						type : 'post',
						url : '${ctx}/xxyp/dayly/getDaylyData.json',
						data : paramData,
						dataType : 'json',
						success : function(data) {
							if(data.exception==undefined){
								console.log(data);
								for(var i=0;i<data.length;i++){
									$("#"+data[i].PID_RECORD_ITEM).html(data[i].PID_RECORD_VALUE);
								}
								$.message({
									message : "刷新成功",
									cls : "success",
									iframePanel:true
								});
							}else{
								$.message( {
									iframePanel:true,
							        message:data.exception.cause.message,
							        type:"danger"
							    });
							}
							
						},
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							$.alert({
								message:textStatus,
								title:"信息提示",
								iframePanel:true
							});
						}
					}); 
				} else {
					$.message({
						iframePanel:true,
						message : data.msg,
						type : "danger"
					});
				}

			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert({
					message:textStatus,
					title:"信息提示",
					iframePanel:true
				});
			}
		});
		
	}
	/**
	 * 加载数据
	 */
	function loadData1(date){
		loadTeqin(date);
		getZbPolice();
		queryPoliceBase_new(date);
		queryPrisonerBase_new(1,date);
		queryPrisonerBase_new(2,date);
		queryPrisonerBase_new(3,date);
		queryPrisonerBase_new(4,date);
		queryPrisonerBase_new(5,date);
		loadPrisonerOrder(date);
		queryKeynotePrisoner_new(1,date);
	 	queryKeynotePrisoner_new(2,date);
	 	queryKeynotePrisoner_new(3,date);
	 	queryKeynotePrisoner_new(4,date);
	 	queryKeynotePrisoner_new(5,date);
	 	queryKeynotePrisoner_new(6,date);
	 	queryKeynotePrisoner_new(7,date);
	 	queryConfinePrisoner_new(1,date);
		queryConfinePrisoner_new(2,date);
		loadForeigncarCount(date);
		loadForeigncarAreaCount(date);
		loadForeignpeopleCount(date);
		loadForeignpeopleAreaCount(date);
		loadPrisonInsideVis(date);
		loadPrisonInsideHspl(date);
		loadPrisonerOutCount(date);
		loadAlarmCount(date);
		loadTechnicalCount(date);
		loadDutyInfo(date,11,0);
		loadDutyInfo(date,1201,1);
		loadMessageReleaseInfo(date,1);
		loadMessageReleaseInfo(date,2);
		loadPetitionInfo(date);
		loadConstructionInfo(date);
		loadProvInspectNotice(date);
		loadRequire(date);
		loadSafetyProduction(date);
	}
	
	/*
	 * 加载历史日报时点数据
	 */
	function loadPrisonInformationDayly(date,hour){
		
	}
	
	//特勤队员
	function loadTeqin(date){

	}
	/**
	 * 在编民警
	 */
	function getZbPolice(){
		
	}
	/**
	 * 查询民警值班信息
	 */
	function queryPoliceBase_new(date){
		
	}
	/**
	 * 查询当日押犯基本情况
	 */
	function queryPrisonerBase_new(num,date){
		
	}
	/**
	 * 查询今日违规、重大、重要狱情罪犯总数
	 */
	function loadPrisonerOrder(date){
	    
	}
	/**
	 * 查询当日重点人头情况
	 */
	function queryKeynotePrisoner_new(num,date){
	    
	}
	/**
	 * 查询罪犯惩处、高危管控情况
	 */
	function queryConfinePrisoner_new(type,date){
	    
	}
	/**
	 * 查询外来车辆统计数量
	 */
	function loadForeigncarCount(date){
	    
	}
	/**
	 * 查询外来车辆进出最多的监区和次数
	 */
	function loadForeigncarAreaCount(date){
		
	}
	/**
	 * 查询外来人员统计数量
	 */
	function loadForeignpeopleCount(date){
		
	}
	/**
	 * 查询外来人员进出最多的监区和次数
	 */
	function loadForeignpeopleAreaCount(date){
		
	}
	/**
	 * 查询监内就诊罪犯总数
	 */
	function loadPrisonInsideVis(date){
		
	}
	/**
	 * 查询监内住院罪犯总数
	 */
	function loadPrisonInsideHspl(date){
		
	}
	/**
	 * 查询罪犯外出情况（外出押解、外出就诊、外出住院）
	 */
	function loadPrisonerOutCount(date){
		
	}
	/**
	 * 查询报警统计
	 */
	function loadAlarmCount(date){
		
	}
	/**
	 * 查询技防设备统计
	 */
	function loadTechnicalCount(date){
		
	}
	/**
	 * 查询值班信息
	 */
	function loadDutyInfo(date,id,type){
		
	}
	/**
	 * 查询信息发布信息
	 */
	function loadMessageReleaseInfo(date,msgType){
		
	}
	/**
	 * 查询上访信息
	 */
	function loadPetitionInfo(date){
		
	}
	/**
	 * 查询施工情况信息
	 */
	function loadConstructionInfo(date){
		
	}
	/**
	 * 查询省局通报、要求
	 */
	function loadProvInspectNotice(date){
		
	}
	/**
	 * 加载相关要求
	 */
	function loadRequire(date){
		
	}
	/*
	 * 安全生产
	 */
	function loadSafetyProduction(date){
		
	}
	function toCircularChanged(){
		$('#dialog_prisonDayly').dialog({
			width : 800,
			height : 600,
			title : "通报整改-汇总",
			url : jsConst.basePath+'/xxyp/change/recordDialog?cusNumber='+cusNumber
		});
		$("#dialog_prisonDayly").dialog("open");
	}
</script>