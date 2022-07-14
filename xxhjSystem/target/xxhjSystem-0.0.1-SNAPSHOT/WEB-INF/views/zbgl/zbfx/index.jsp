<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script src="${ctx}/static/js/scripts/common.js"></script>
<style>
	.fieldset_border {
		border: #B3D0F4 2px solid;
		border-collapse:separate; 
		border-spacing:15px;
		background-color:#F5FAFA;
		width:100%; 
		height:auto;
		margin-left:5px;
	}
</style>

<div style="height: 100%;">
	<table border="0" >
		<tr>
			<td>
				<cui:form id="formId_zbfx">
					<table id="table_two" class="fieldset_border">
						<tr>
							<th>值班类别：</th>
							<td><cui:combobox id="comboboxId_zbfx_categoryName" required= "true" name="categoryId" width = "150" url="${ctx}/zbgl/lbgl/searchAllData?dcaStatus=1" onChange="initDrpmntList"></cui:combobox></td>
							<th>部门名称：</th>
							<td><cui:combobox id="comboboxId_drpmntName" width = "150" required= "true" name="dmdDprtmntId"></cui:combobox></td>
							<th>值班日期：</th>
							<td><cui:datepicker id ="startDate"  name ="startDate" width = "150" dateFormat="yyyy-MM-dd"></cui:datepicker>
							&nbsp;~&nbsp;<cui:datepicker id ="endDate"  name ="endDate" startDateId="startDate" width = "150" dateFormat="yyyy-MM-dd"></cui:datepicker>
							</td>
						</tr>
						<tr>
							<td colspan="5"></td>
							<td align="center"> 
							    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				 				<cui:button label="确定"  componentCls="btn-primary" onClick="ensure()"/>
				 				&nbsp;&nbsp;&nbsp;
								<cui:button label="重置"  onClick="reset"></cui:button>&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</cui:form>
			</td>
		</tr>
		<tr>
			<td>
				<table id="duty" >
					<tr>
						<td>
							<div style="width:970px; height:auto;  overflow: auto; margin: 0px 10px 0px 10px;">
								<div class="notData" align ="center" style="display: none;">暂无数据!</div>
								<div id="dutyGraph" style="height:550px; width:933px;"></div>
								<table id="dutyList" style="height:550px; width:933px;display:none;">
									<div id ="div_gridId_zbfx" style="width:970px;height: auto;display:none">
										<cui:grid id="gridId_zbfx" rownumbers="true"  height="520" width="auto" fitStyle="fill" rowNum="15">
											<cui:gridCols>
												<cui:gridCol name="DBD_STAFF_ID" align="center">警号</cui:gridCol>
												<cui:gridCol name="DBD_STAFF_NAME" align="center">姓名</cui:gridCol>
												<cui:gridCol name="DBD_DUTY_DATE" align="center">值班日期</cui:gridCol>
												<cui:gridCol name="DOR_DUTY_ORDER_NAME" align="center">班次</cui:gridCol>
												<cui:gridCol name="CDJ_JOB_NAME" align="center">岗位</cui:gridCol>
											</cui:gridCols>
											<cui:gridPager gridId="gridId_zbfx" />
										</cui:grid>
									</div>
								</table>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>

<script>
	var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
	var cusNumber = jsConst.CUS_NUMBER;
	
	$.parseDone(function() {
		$("#startDate").datepicker("option","maxDate", new Date());
		$("#endDate").datepicker("option","maxDate", new Date());
	})
	
	function ensure() {
		
		var validFlag  = $("#formId_zbfx").form("valid");
		if(!validFlag) {
			return;
		}
		
		var formData = $("#formId_zbfx").form("formData");
		formData["cusNumber"] = cusNumber;
		$("#duty").loading({ text : "正在加载中，请稍后..."});
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/zbfx/searchAllData',
			data: formData,
			dataType : 'json',
			success : function(data) {
				$("#duty").loading("hide");
				if(data != "" && data != null) {
					
					$("#div_gridId_zbfx").hide();
					$(".notData").hide(); 
					$("#dutyGraph").show();
					parseDutyPoliceCount(data);
					
				} else {
					
				 $("#dutyGraph").hide();
				 $("#div_gridId_zbfx").hide();
				 $(".notData").show();
				}
			}
		})
	}
	
	function reset() {
		$("#formId_zbfx").form("clear");
	}
	
	function initDrpmntList() {
		
		var selectedCategory = $("#comboboxId_zbfx_categoryName").combobox("getValue");
		$("#comboboxId_dutyPost").combobox( "reload", "${ctx}/zbgl/gwgl/searchAllData?cdjCategoryId="+selectedCategory);  //岗位
		
		$.ajax({
			type : 'post',
			url : '${ctx}/zbgl/lbbm/searchAllData',
			data: {
				dcdCategoryId:selectedCategory,
				param:'1'
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
				$("#comboboxId_drpmntName").combobox("loadData",data);
			},
		});
	}
	
 	function parseDutyPoliceCount(countList) {
 		
 		var staffIdList = new Array();
		var key = new Array();
		var value = new Array();
		var tickPositions = new Array();
		var maxNum = 0;
		for(var i=  0; i < countList.length; i++) {
			
			staffIdList[i] = countList[i].DBD_STAFF_ID;    /*民警ID*/
			key[i] = countList[i].DBD_STAFF_NAME;          /*民警姓名 */
			value[i] = parseInt(countList[i].DUTYNUM);     /*值班天数 */
			
			if(i > 0) {
				if(maxNum != 0) {
					if(value[i] > value[i-1] && value[i] > maxNum){
						maxNum = value[i];
					}
				}else{
					if(value[i] > value[i-1]){
						maxNum = value[i];
					}
				}
			}else{
				maxNum = value[i];
			}
		}
		maxNum += (maxNum/2);
		maxNum = maxNum < 10 ? 10 : maxNum;
		//根据最大值设置x轴数据
		var j = 0;
		var num = 0;
		var flagNum = maxNum == 10 ? 2 : parseInt(maxNum/10);
		flagNum = flagNum > 2 ? flagNum : 2;
		var yNum = parseInt(flagNum)*10;
		
		initEchart(staffIdList, key, value, yNum);
	
	} 
 	
 	function initEchart(staffIdList, title, data, yNum) {
 		
 		var myChart4 = echarts.init(document.getElementById("dutyGraph"))
		var option1 = {
			title : {
		    },
   		    tooltip: {
   		    	trigger : 'item',
   		    },
   		    grid: {
   		    	left : '2%',
   		        right : '2%',
   		        bottom : '10%',
   		        top :"10%",
   		        containLabel : true
   		    },
   			 xAxis : {
		        type : 'category',
		        axisTick : false,
		        data : title,     /* 横轴数据来源 */
		     	axisLabel : {  
					interval : 0,//横轴信息全部显示  
              		rotate : 30,//-30度角倾斜显示  
              		fontSize : "20px",
         		} 
		    }, 
		    
   		    yAxis: {
   		        type : 'value',
				name : '值班次数',  /*纵轴标题  */
      		    min : 0,
       			max : yNum, 
       			interval : 10,
                splitNumber :10,
                axisLabel: {
                    formatter: '{value}' /*纵轴标题显示格式  */
                }
   		    },
   		    
   		    series: [{
   		    	name : "值班次数",
	            type : 'bar',
	            data : data,
	            itemStyle : { 
					normal : { 
						color : "#4692f0",
                        label : {              /*鼠标放入信息显示方式*/
                            show : true,
                            position : 'top',
                            formatter : '{c}'
                        }
		    		} 
				}
		     },
		     {
                 type:'line',
                 itemStyle : {  /*设置折线颜色*/
                     normal : {
                        color:'#c4cddc'
                     }
                 },
                 data : data,
             }]
		};

		myChart4.setOption(option1);
	    $(window).resize(function () {
	    	myChart4.resize();
	    });
    
	    //点击数据
	    myChart4.on('click',function (params) {
	   
	       	if(params.data!= null && params.data!= '') {
	       		var staffId = staffIdList[parseInt(params.dataIndex)];
	       		loadGrid(staffId);
	       	}
	   });  
	}
 	
 	function loadGrid(staffId) {
 		
 		$("#dutyGraph").hide();
 		$("#dutyGraph").empty();
 		$("#div_gridId_zbfx").show();
 		
 		var formData = $("#formId_zbfx").form("formData");
 		formData["dutyPolice"] = staffId;
		formData["cusNumber"] = cusNumber;
		
		$("#gridId_zbfx").grid("option", "postData", formData);
		$("#gridId_zbfx").grid("reload","${ctx}/zbgl/zbcx/searchData"); 
 	}
	
</script>