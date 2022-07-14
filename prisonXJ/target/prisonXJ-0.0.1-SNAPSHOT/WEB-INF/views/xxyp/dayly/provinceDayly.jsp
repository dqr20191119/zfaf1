<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<link rel="stylesheet" type="text/css"
	href="${ctx}/static/module/dayly/dayly.css" />

<style type="text/css">
#dialogWindow_one .back_a{
	float: left;
	position: absolute;
	margin-left: 5px;
	margin-top: 3px;
	display: none;
}
#dialogWindow_one .back_a a{
	color: #fff;
	font-size: 14px;
}
#dialogWindow_one .list{
	display: none;
}
.datagrid-view2{
   left: 30px;
}
</style>

<!-- 导出word -->
<div id="pageContent" style="display: none;">
	<p align="left" style="font-size: 21px;font-family: '仿宋';margin-bottom: 100px;">内部资料</p>
	<p align="center" style="color: red;font-size: 68px;font-family: '方正小标宋';font-weight: bold;margin-bottom: 35px;">监狱信息日报</p>
	<p align="center" style="font-size: 20px;font-family: '仿宋';">第   期</p>
	<div align="left" style="width: 100%;font-size: 21px;font-family: '仿宋';">
		<span>省监狱管理局指挥中心</span>
		&nbsp;&nbsp;&nbsp;&nbsp;
		<span class="date"></span>
	</div>
	<hr color="red" style="margin: 0px;padding: 0px;height: 2px;"/>
	<p align="left" style="font-size: 21px;font-family: '仿宋';">&nbsp;&nbsp;今日，全省监狱持续安全稳定。</p>
	<p style="font-size: 21px;font-family: '黑体';">&nbsp;&nbsp;一、基本信息</p>
	<p align="left" style="font-size: 21px;font-family: '仿宋';line-height: 28px;margin-bottom: 0px;">
		&nbsp;&nbsp;全省监狱民警<span style="color: red;" class="police1"></span>人，全省监狱<span style="color: red;" class="police2"></span>个监区，监区白班在岗民警<span style="color: red;" class="police3"></span>人，晚班收封前在岗民警<span style="color: red;" class="police4"></span>人，晚班收封后在岗民警<span style="color: red;" class="police5"></span>人，监狱夜间集中备勤民警<span style="color: red;" class="police6"></span>人。
	</p>
	<p align="left" style="font-size: 21px;font-family: '仿宋';line-height: 28px;margin-top: 5px;">
		&nbsp;&nbsp;全省监狱在册罪犯<span style="color: red;" class="prisoner1"></span>人，在押罪犯<span style="color: red;" class="prisoner2"></span>人，新收押<span style="color: red;" class="prisoner3"></span>人，释放<span style="color: red;" class="prisoner4"></span>人。狱外住院<span style="color: red;" class="prisoner5"></span>人<span class="prisoner5_text"></span>，狱外就诊<span style="color: red;" class="prisoner6"></span>人。戴铐戴镣<span style="color: red;" class="prisoner7"></span>人<span class="prisoner7_text"></span>，禁闭<span style="color: red;" class="prisoner8"></span>人，隔离审查<span style="color: red;" class="prisoner9"></span>人<span class="prisoner9_text"></span>，立案侦查<span style="color: red;" class="prisoner10"></span>人<span class="prisoner10_text"></span>。提回重审<span style="color: red;" class="prisoner11"></span>人，暂予监外执行<span style="color: red;" class="prisoner12"></span>人，老病残犯<span style="color: red;" class="prisoner13"></span>人。
	</p>
	<p style="font-size: 21px;font-family: '黑体';">&nbsp;&nbsp;二、主要狱情</p>
	<p align="left" style="font-size: 21px;font-family: '仿宋';line-height: 28px;">
		&nbsp;&nbsp;&nbsp;&nbsp;
	</p>
	<p style="font-size: 21px;font-family: '黑体';">&nbsp;&nbsp;三、设施设备运行情况</p>
	<p align="left" style="font-size: 21px;font-family: '仿宋';line-height: 28px;">
		&nbsp;&nbsp;&nbsp;&nbsp;
	</p>
	<p style="font-size: 21px;font-family: '黑体';">&nbsp;&nbsp;四、督查情况</p>
	<p align="left" style="font-size: 21px;font-family: '仿宋';line-height: 28px;">
		&nbsp;&nbsp;&nbsp;&nbsp;
	</p>
	<p style="font-size: 21px;font-family: '黑体';">&nbsp;&nbsp;五、进监外来人员、车辆信息</p>
	<p align="left" style="font-size: 21px;font-family: '仿宋';line-height: 28px;">
		&nbsp;&nbsp;今日进监施工人员<span style="color: red;" class="people1"></span>人，进监外协人员<span style="color: red;" class="people2"></span>人，进监车辆<span style="color: red;" class="car1"></span>辆。
	</p>
	<p style="font-size: 21px;font-family: '黑体';">&nbsp;&nbsp;六、其他关注信息</p>
	<p align="left" style="font-size: 21px;font-family: '仿宋';line-height: 28px;">
		&nbsp;&nbsp;&nbsp;&nbsp;
	</p>
	<p align="left" style="font-size: 21px;font-family: '仿宋';line-height: 28px;">
		&nbsp;&nbsp;报：柳玉祥厅长，局领导
	</p>
	<p align="left" style="font-size: 21px;font-family: '仿宋';line-height: 28px;">
		&nbsp;&nbsp;送：局机关各处室，方源集团各部室
	</p>
	<p align="left" style="font-size: 21px;font-family: '仿宋';line-height: 28px;">
		&nbsp;&nbsp;发：各监狱、未成年犯管教所，警校、双龙集团
	</p>
	<hr color="black"/>
	<p align="left" style="font-size: 21px;font-family: '仿宋';">
		&nbsp;&nbsp;<span>编辑：</span>
		&nbsp;&nbsp;&nbsp;
		<span>核稿：</span>
		&nbsp;&nbsp;&nbsp;
		<span>签发：</span>
	</p>
</div>
<div class="styleContent">
	<%-- <div style="float: left;font-size: 14px;width: 100%;margin-bottom: 5px;">
		<div style="float: left;">
			<div class="date_div">
				当前时间：<span class="spanTime">
						 	<span id="ymd"></span>
						 	&nbsp;&nbsp;00:00:00 ~ <span id="dateTime"></span>
						 </span>&nbsp;&nbsp;&nbsp;
			</div>
			<a href="javascript:void(0);" onclick="refurbish();" style="color: rgb(4,181,113);">刷新</a>
		</div>
		<div style="float: right;">
			日期：<cui:datepicker id="date" dateFormat="yyyy-MM-dd" />&nbsp;&nbsp;&nbsp;
			<!--时点：<select id="hour" style="width: 60px">
				    <option value="0">全天</option>
				  	<option value="16">16点</option>
				  	<option value="22">22点</option>
				  	<option value="24">24点</option>
				  </select>&nbsp;&nbsp;&nbsp;-->
			监狱列表：<cui:combobox  id="prisonList" url="${ctx}/common/authsystem/findAllJyForCombobox.json" onChange="cusNumberOnChange"></cui:combobox>&nbsp;&nbsp;&nbsp;
			<button class="submit" onclick="queryData();">查询</button>
			<button class="downLoad" onclick="toExportDayly();">导出</button>
		</div>
	</div> --%>
	<div class="showForm" style="margin-top: 20px;width: 100%; height: 570px;overflow-y: auto;">
		<table>
			<tr style="height: 30px;">
				<td class="one" width="170">项目</td>
				<td class="one" width="772">内容</td>
			</tr>
			<tr>
				<td>1、民警值班信息</td>
				<td height="50" id="policeBase">
					全省监狱在编民警 <span id="police_1">0</span> 人，
					<span id="police_2">0</span> 个监区，监区白班在岗民警
					<span id="police_3">0</span> 人( 10:00点 )，晚班收封前在岗民警
					<span id="police_4">0</span> 人( 19:00点 )，晚班收封后在岗民警
					<span id="police_5">0</span> 人( 22:00点 )，监狱夜间集中备勤民警 
					<span id="police_6">0</span> 人。
				</td>
			</tr>
			<tr>
				<td>2、押犯基本情况</td>
				<td height="70" id="priosnerBase">
					今日全省实押罪犯总数 <span id="prisoner_1">0</span> 人，实押危安犯总数
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
					今日全省监狱发生罪犯违规 <span id="prisonerOrderCount">0</span> 起，
					其中重大、重要狱情 <span id="prisonerOrderCount_2">0</span> 起。
				</td>
			</tr>
			<tr>
				<td>4、罪犯重点人头情况</td>
				<td height="90" id="keynotePrisoner">
					邪教类罪犯数 <span id="keynotePrisoner_1">0</span> 人(法轮功罪犯 <span id="FLGPrisoner">0</span> 人，
					全能神罪犯 <span id="QNSPrisoner">0</span> 人，&nbsp;其他 <span id="otherPrisoner">0</span> 人)，
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
					刑释安置帮教必接必送  <span id="bjbsPrisoner">0</span> 人。
				</td>
			</tr>
			<tr>
				<td>5、罪犯惩处、高危管控情况</td>
				<td height="50" id="confinePrisoner">
					今日全省加戴戒具 <span id="confinePrisoner_3">0</span> 人(累计 <span id="confinePrisoner_3_1">0</span> 人)，
					禁闭 <span id="confinePrisoner_1">0</span> 人(累计 <span id="confinePrisoner_1_1">0</span> 人)，
					隔离审查 <span id="confinePrisoner_4">0</span> 人(累计 <span id="confinePrisoner_4_1">0</span> 人)，
					立案侦查 <span id="confinePrisoner_5">0</span> 人(累计 <span id="confinePrisoner_5_1">0</span> 人)，
					高危管控罪犯 <span id="confinePrisoner_2">0</span> 人(累计 <span id="confinePrisoner_2_1">0</span> 人)。
				</td>
			</tr>
			<tr>
				<td>6、罪犯监内就医情况</td>
				<td id="prisonInsideVis">
					今日全省监狱监内就诊 <span id="prisonInsideVisCount">0</span> 人，
					监内住院 <span id="prisonInsideHsplCount">0</span> 人。
				</td>
			</tr>
			<tr>
				<td>7、罪犯外出情况</td>
				<td id="prisonerOut">
					今日全省监外就诊 <span id="prisonerOut_1">0</span> 人
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
					今日全省罪犯会见 <span id="meetingInfoCount">0</span> 人。
				</td>
			</tr>
			<tr>
				<td>9、罪犯会见复听情况</td>
				<td id="meetingInfoDbl">
					今日全省监狱累计复听会见录音 <span id="meetingInfoDblCount">0</span> 人次。
				</td>
			</tr>
			<tr>
				<td>10、外来车辆进出情况</td>
				<td id="foreigncar">
					今日全省进出车辆 <span id="foreigncarCount">0</span> 辆。
				</td>
			</tr>
			<tr>
				<td>11、外来人员进出情况</td>
				<td id="foreignpeople">
					今日全省累计进出监区 <span id="foreignpeopleCount">0</span> 人,其中监内施工<span id="foreign_worker">0</span>人（此数据为已进行监狱大门门禁一体化改造的单位）。
				</td>
			</tr>
			<tr>
				<td>12、网络技防运行情况</td>
				<td id="technicalDevice">
					今日全省技防设备故障 <span id="technicalDeviceCount">0</span> 个
				</td>
			</tr>
			<tr>
				<td>13、报警情况</td>
				<td id="alarm">
					今日全省发生报警 <span id="alarmCount">0</span> 起
					<!-- (其中安防系统自动报警 <span id="alarm_1">0</span> 起，
					人工报警 <span id="alarm_2">0</span> 起，
					已处置 <span id="alarm_3">0</span> 起，
					未处置 <span id="alarm_4">0</span> 起) -->
				</td>
			</tr>
			<tr>
				<td>14、安全生产情况</td>
				<td id="safetyProduction">无安全生产情况。</td>
			</tr>
			<tr>
				<td>15、舆情情况</td>
				<td id="opinionInfo">
					今日全省共发布了 <span id="opinionCount">0</span> 件舆情信息。
				</td>
			</tr>
			<tr style="display: none;">
				<td>16、社区信息</td>
				<td id="communityInfo">
					今日全省共发布了 <span id="communityCount">0</span> 件社区信息。
				</td>
			</tr>
			<tr>
				<td>16、上访信息</td>
				<td id="petitionInfo">
					今日全省共发布了 <span id="petitionCount">0</span> 件上访信息。
				</td>
			</tr>
			<tr>
				<td>17、监内施工</td>
				<td id="constructionInfo">
					今日全省共有监内施工 <span id="constructionCount">0</span> 起，施工人数 <span id="coiNumberCount">0</span> 人。
				</td>
			</tr>
			<tr>
				<td>18、省局通报、要求</td>
				<td id="provInspectNotice"></td>
			</tr>
			<tr>
				<td>19、省局通报、要求整改情况</td>
				<td id="correctFeedback">
					<a href="javascript:void(0);" onclick="loadInspectNoticeList();">省局通报整改汇总</a>
				</td>
			</tr>
		</table>
	</div>
	<div class="dialogWindow" id="dialogWindow_one">
		<div class="title">
			<div class="back_a"><a href="javascript:void(0);">返回</a></div>
			<span>监所列表</span>
			<div class="close" onclick="closeWindow_one();"></div>
		</div>
		<div class="list" id="prov"></div>
		<div class="list" id="prsn"></div>
	</div>
	<div class="dialogWindow" id="dialogWindow_two">
		<div class="title">
			<span></span>
			<div class="close" onclick="closeWindow_two();"></div>
		</div>
		<div class="table"></div>
	</div>
</div>
<cui:dialog id="dialog_provDayly" autoOpen="false" iframePanel="true" reLoadOnOpen="true" modal="true" resizable="false"></cui:dialog>
<script type="text/javascript">
	var jsConst = window.top.jsConst;
	var cusNumber = jsConst.ORG_CODE;
	var cusName = jsConst.CUS_NAME;
	
	$(function(){
		loadData(formatterDate(new Date()));
	});
	
	/**
	 * 加载数据
	 */
	function loadData(date){
		//重置数据，待改善
		$(".showForm").find("span").html("0");
		
		var paramData={
				"pidDate":date
		}
		$.ajax({
			type : 'post',
			url : '${ctx}/xxyp/provDayly/initProvDaylyData',
			dataType : 'json',
			success : function(data) {
				if (data.success) {
					$.ajax({
						type : 'post',
						url : '${ctx}/xxyp/provDayly/getProvDaylyData.json',
						data : paramData,
						dataType : 'json',
						success : function(data) {
							if(data.exception==undefined){
								$.message({
									message : "刷新成功",
									cls : "success",
									iframePanel:true
								});
								console.log(data);
								for(var i=0;i<data.length;i++){
									$("#"+data[i].PID_RECORD_ITEM).html(data[i].PID_RECORD_VALUE);
								}
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
	function loadInspectNoticeList(){
		$('#dialog_provDayly').dialog({
			width : 800,
			height : 600,
			title : "网络督查通报-汇总",
			url : jsConst.basePath+'/inspect/recordDialog'
		});
		$("#dialog_provDayly").dialog("open");
	}
</script>