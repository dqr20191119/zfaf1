<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style type="text/css"> 
	body{ 
		margin:0 auto;
		text-align:center;
		} 
	.yangshi{ 
		width:98%; 
		border: #B3D0F4 2px solid;
		margin:10px auto;
		align:center;
		background: #F5FAFA;
		margin-bottom:15px;
		margin-top:20px;
		} 
	.foot_content p{
		margin-bottom:10px;
		margin-top:10px;
		text-align:center;
		font-size:15px;
	}
</style>
<div class="foot_content">

	<div id="politicsReward"  class="yangshi">
		<p align="center" style="font-size:17px;font-weight:bold;">行政奖励</p>
		<cui:grid id="gridId_politicsReward" colModel="gridColModel_politicsReward" fitStyle="fill" pager="true" height="300px" rowNum="5"
	     url="${ctx}/xxhj/zfjbxx/listPrisonerRewardPunish?type=1&pprPrsnrIdnty=${pprPrsnrIdnty}&&pprCusNumber=${pprCusNumber}"></cui:grid>
	</div>
	
	<div id="politicsPunish" class="yangshi">
		<p align="center"  style="font-size:17px;font-weight:bold;">行政处罚</p>
		<cui:grid id="gridId_politicsPunish" height="300px" colModel="gridColModel_politicsPunish" fitStyle="fill" pager="true" rowNum="5"
		url="${ctx}/xxhj/zfjbxx/listPrisonerRewardPunish?type=2&pppCusNumber=${pprCusNumber}&&pppPrsnrIdnty=${pprPrsnrIdnty}"></cui:grid>
	</div>
	
	<div id="judicialReward" class="yangshi">
		<p align="center"  style="font-size:17px;font-weight:bold;">司法奖励</p>
		<cui:grid id="gridId_judicialReward" colModel="gridColModel_judicialReward" frozenColumns="true" shrinkToFit="false" fitStyle="fill" pager="true" height="300px" rowNum="5"
		url="${ctx}/xxhj/zfjbxx/listPrisonerRewardPunish?type=3&pjrCusNumber=${pprCusNumber}&&pjrPrsnrIdnty=${pprPrsnrIdnty}">
			<cui:gridPager gridId="gridId_judicialReward" />
		</cui:grid>
	</div>
</div>

<script type="text/javascript">
	//奖励类型(政治)
	var combobox_jllx = <%=CodeFacade.loadCode2Json("4.6.10")%>;
    //处罚依据
    var combobox_cfyj = <%=CodeFacade.loadCode2Json("4.6.40")%>;
    //处罚类型
    var combobox_cflx = <%=CodeFacade.loadCode2Json("4.6.12")%>;
    //违规事实(处罚原因)
    var combobox_wgss = <%=CodeFacade.loadCode2Json("4.6.39")%>;
   //罪名
    var combobox_zm = <%=CodeFacade.loadCode2SimpleTreeJson("4.4.1", null, 5)%>;

	var gridColModel_politicsReward = [ {
		label : "罪犯姓名",
		name : "PPR_PRSNR_NAME",
		align:"center"
	}, {
		label : "奖励类型",
		name : "PPR_RWRD_TYPE",
		align:"center",
		formatter : function(cellvalue, options, rawObject) {
			return convertColVal(combobox_jllx, cellvalue);
		}
	}, {
		label : "奖励依据",
		name : "PPR_RWRD_BASIS",
		align:"center"
	}, {
		label : "奖励开始时间",
		name : "PPR_RWRD_START_DATE",
		align:"center"
	} ,{
		label : "奖励结束时间",
		name : "PPR_RWRD_END_DATE",
		align:"center"
	} ]

	//裁判机关类型
	var combobox_cpjglx = <%=CodeFacade.loadCode2Json("4.4.4")%>;
	//裁判机关城市
	var combobox_cpjgcs = <%=CodeFacade.loadCode2Json("4.3.2")%>;
	//变动类别
	var combobox_bdlb = <%=CodeFacade.loadCode2Json("4.4.2")%>;
	
	var gridColModel_politicsPunish = [ {
		label : "罪犯姓名",
		name : "PPR_PRSNR_NAME",
		align:"center"
	}, {
		label : "处罚类型",
		name : "PPP_PNSH_TYPE",
		align:"center",
		formatter : function(cellvalue, options, rawObject) {
			return convertColVal(combobox_cflx, cellvalue);
		}
	},{
		label : "违规事实",
		name : "PPP_ILLEGAL_FACT",
		align:"center",
		formatter : function(cellvalue, options, rawObject) {
			return convertColVal(combobox_wgss, cellvalue);
		}
	},{
		label : "处罚依据",
		name : "PPP_PNSH_BASIS",
		align:"center",
		formatter : function(cellvalue, options, rawObject) {
			return convertColVal(combobox_cfyj, cellvalue);
		}
	}, {
		label : "处罚日期",
		name : "PPR_PNSH_DATE",
		align:"center"
	}, {
		label : "处罚期限",
		name : "PPR_PNSH_DATE",
		align:"center"
	}]

	//刑种
    var combobox_xz = <%=CodeFacade.loadCode2Json("4.5.17")%>;
    
	var gridColModel_judicialReward = [ {
		label : "罪犯姓名",
		name : "PJR_PRSNR_NAME",
		frozen: true
	}, {
		label : "变动幅度",
		name : "PJR_CHNG_RANGE",
		align:"center",
		frozen: true
	}, {
		label : "变动类别",
		name : "PJR_CHNG_TYPE",
		align:"center",
		frozen: true,
		formatter : function(cellvalue, options, rawObject) {
			return convertColVal(combobox_bdlb, cellvalue);
		}
	
	}, {
		label : "减刑后的刑期",
		name : "PJR_OVERPLUS_TERM",
		frozen: true
	}, {
		label : "考验期止日",
		name : "PJR_TEST_START_DATE",
		align:"center",
		frozen: true
	}, {
		label : "裁判机关城市",
		name : "PJR_TRAIL_OFFICE_CITY",
		align:"center",
		frozen: true,
		formatter : function(cellvalue, options, rawObject) {
			return convertColVal(combobox_cpjgcs, cellvalue);
		}
	}, {
		label : "裁判机关类型",
		name : "PJR_TRAIL_OFFICE_TYPE",
		align:"center",
		formatter : function(cellvalue, options, rawObject) {
			return convertColVal(combobox_cpjglx, cellvalue);
		}
	}, {
		label : "裁判日期",
		name : "PJR_TRAIL_DATE",
		align:"center"
	}, {
		label : "裁判字号",
		name : "PJR_TRAIL_IDNTY",
		align:"center"
	}, {
		label : "罪名",
		name : "PJR_ACCSTN",
		align:"center",
		formatter : function(cellvalue, options, rawObject) {
			
			var list = new Array();
			var value = '';
			list = cellvalue.split(",");
			
			if (list.length > 1) {
				
				for(var i=0;i<list.length;i++) {
					
					if(i< list.length-1) {
						
						value = convertColVal(combobox_zm, list[i])+","+value;
					}else {
						value = value + convertColVal(combobox_zm, list[i]);
					}
				}
				return value;
			}else {
				return convertColVal(combobox_zm, cellvalue)
			} 	
		}
	}, {
		label : "刑种",
		name : "PJR_SNTN_TYPE",
		align:"center",
		formatter : function(cellvalue, options, rawObject) {
			return convertColVal(combobox_xz, cellvalue);
		}
	}, {
		label : "刑期起日",
		name : "PJR_PRSN_START_DATE",
		align:"center"
	}, {
		label : "刑期止日",
		name : "PJR_PRSN_END_DATE",
		align:"center"
	}, {
		label : "剥夺政治权利",
		name : "PJR_DPRVTN_POWER",
		align:"center"
	}]
	
</script>