<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="document_id"  value="point_managementList"/> <!-- 页面元素id前缀 -->
<c:set var="fun_name"  value="pointMana"/> <!-- js对象方法的名称 -->
<html>
<body>
<cui:dialog id="${document_id}_dialog" width="60%" height="60%" title="点位信息" iframePanel="true" autoOpen="false"></cui:dialog>
<div class="rightDivStyle right-zb">
		<h4>点位维护</h4>
		<%--<a href="javascript:${fun_name}.allPointManage();" class="modelUrl">点位管理</a>--%>
        <div class="model_buttons_save">
        <cui:button label="点位管理" onClick="${fun_name}.allPointManage" width="80"  ></cui:button>
        </div>
		<div class="model_div">
			<cui:form  id="${document_id}_pointForm" method="post" action="" >
				<input type="hidden" id="${document_id}_alpCusNumber" name="alpCusNumber"  />
				<div class="model_form">
					<div class="model-input" id="${document_id}_regionDiv">
						<label>区域名称：</label>
						<cui:combotree id="${document_id}_region" name="alpGrandId" onChange="${fun_name}.changeGrand"></cui:combotree>
					</div>
					<div class="model-input">
						<label>设备类型：</label>
						<cui:combobox id="${document_id}_deviceType"  name="alpDeviceType" data="${fun_name}.deviceType" onChange="${fun_name}.changeDeviceTypeView"></cui:combobox>
					</div>
					<div id="${document_id}_showDevice" class="model_table first model-input">
						<h4>对应设备</h4>
						<div class="table-con">
							<div class="model_table_content">
								<table class="device_table_first" border="1px" align="center" >
									<thead>
									<tr>
										<th width="35%">设备名</th>
										<th width="25%">设备类型</th>
										<th width="20%">有效状态</th>
										<th width="20%">创建状态</th>
									</tr>
									</thead>
								</table>
							</div>
							<div class="tab-tbody">
								<table>
									<tbody id="${document_id}_deviceRow">
									<tr class="odd">
										<td width="35%">aaa</td>
										<td width="25%">设备类型</td>
										<td width="20%">有效</td>
										<td width="20%">已创建</td>
									</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div id="${document_id}_showDeviceMsg" class="model_table model-input">
						<h3>对应设备</h3>
						<div class="model_table_content">
							<table border="1px" align="center" bgcolor="#87cefa" class="device_table_first">
								<thead>
									<tr>
										<th>设备名</th>
										<th>设备类型</th>
										<th>有效状态</th>
										<th>创建状态</th>
									</tr>
								</thead>
								<tbody >
									<tr><td colspan="4" align="center">无数据。。。</td></tr>
								</tbody>
							</table>
						</div>
					</div>
					<h4>点位坐标</h4>
					<div class="model-input">
						<label>X轴坐标：</label>
						<cui:input id="${document_id}_point_x" name="alpXPointIdnty"  placeholder="暂无"/>
					</div>
					<div class="model-input">
						<label>Y轴坐标：</label>
						<cui:input id="${document_id}_point_y" name="alpYPointIdnty"  placeholder="暂无"/>
					</div>
					<div class="model-input">
						<label>Z轴坐标：</label>
						<cui:input id="${document_id}_point_z" name="alpZPointIdnty"  placeholder="暂无"/>
					</div>
					<div class="model_buttons_save">
						<cui:button label="保 存" onClick="${fun_name}.save" width="55" ></cui:button>
						<cui:button label="重置" onClick="${fun_name}.reSetData" id="${document_id}_reSet" width="55"></cui:button>
						<cui:button label="取消" onClick="${fun_name}.canel" id="${document_id}_canel" width="55"></cui:button>
					</div>
				</div>
			</cui:form>
		</div>
	</div>
<script type="text/javascript">
    /**
	 * 1、编辑状态时 隐藏所有已有的点位  始终保持显示在编辑的点位
	 * 2、有一个按钮能够显示所有已有的点位
	 * */
    var pointMana;
    var deviceId="";
    var deviceIdForEdit="";
    var deviceName="";
    var modelCurrent=null;//当前添加模型的对象
	var flagIfCheck=false;//是否已经勾选
    var flagIfHave=false;//模型一次只能添加一个
	var deviceModelType="";
	var creatDeviceType="";//创建时的设备类型
    $(function(){
        pointMana = {
            cusNumber:jsConst.CUS_NUMBER,//监狱编号
            url:null,
            mapName:null,
            deviceType:  <%=CodeFacade.loadCode2Json("4.20.57")%>,

            init:function(){
                $('#${document_id}_region').combotree({url:"${ctx}/common/areadevice/findForCombotree.json?cusNumber="+pointMana.cusNumber+"&deviceType=0"});
				$('#${document_id}_canel').hide();
            },
            changeGrand:function(){
                pointMana.changeGrandView($('#${document_id}_region').combotree('getValue'));
			},
			//区域改变时切换当前视角 和对应设备
            changeGrandView:function(grandId){
                if($('#${document_id}_deviceType').combobox('getValue')!=''&&deviceIdForEdit=="")pointMana.changeDeviceTypeView();
                var dataPara={'alpGrandId':grandId,'alpCusNumber':pointMana.cusNumber};
                var data=_DOCUMENT_EVENT.request_data("${ctx}/point/getViewByGrandId",dataPara,false);
                if(data!=null&&data.length>0)
                map.viewPosition(data[0].vfuXCrdnt,data[0].vfuYCrdnt,data[0].vfuZCrdnt,data[0].vfuHeadingCrdnt,data[0].vfuTiltCrdnt,data[0].vfuParentViewId,grandId,1);
			},
            //设备类型改变时 获取对应设备
            changeDeviceTypeView:function(){
                $('#${document_id}_deviceRow').html("");
                $('#${document_id}_showDevice').show();
                $('#${document_id}_showDeviceMsg').hide();
                creatDeviceType=$('#${document_id}_deviceType').combobox('getValue');
                var grandId=$('#${document_id}_region').combotree('getValue');
                var dataPara={'alpDeviceType':creatDeviceType,'alpGrandId':grandId,'alpCusNumber':pointMana.cusNumber,'alpDeviceIdnty':deviceIdForEdit};
                 //刷新设备的列表
                _DOCUMENT_EVENT.request_data_callback('${ctx}/point/getAllDevice',pointMana.callBackList,dataPara,null);
            },
            callBackList:function(result){
                debugger;
                   var str="";
                   if(result.length>0&&result!=null){
					   for(var i=0;i<result.length;i++){
						   if(deviceIdForEdit!=''){//进入编辑状态
							   str=str+"<tr id='"+result[i].deviceId+"_"+result[i].deviceName+"_"+result[i].deviceType+"' style='background-color: red'>";
							   str=str+"<td align='center' width='35%'>"+result[i].deviceName+"</td>";
							   str=str+"<td align='center' width='25%'>"+result[i].deviceTypeName+"</td>";
							   str=str+"<td align='center' width='20%'><input type='checkbox' class='ifStatus' name='checkboxStatus' id='${document_id}_status_"+i+"'></input></td>";
							   str=str+"<td align='center' width='20%'><input type='checkbox' class='ifCreate'  name='checkboxCreate' id='${document_id}_create_"+i+"'></input></td></tr>";
						   }else{
							   if(result[i].alpXPointIdnty==null){//未创建
								   str=str+"<tr id='"+result[i].deviceId+"_"+result[i].deviceName+"_"+result[i].deviceType+"' style='background-color: red'>";
								   str=str+"<td align='center' width='35%'>"+result[i].deviceName+"</td>";
								   str=str+"<td align='center' width='25%'>"+result[i].deviceTypeName+"</td>";
								   str=str+"<td align='center' width='20%'><input type='checkbox' class='ifStatus' name='checkboxStatus' id='${document_id}_status_"+i+"'></input></td>";
								   str=str+"<td align='center' width='20%'><input type='checkbox' class='ifCreate'  name='checkboxCreate' id='${document_id}_create_"+i+"'></input></td></tr>";
							   }else{
								   str=str+"<tr id='"+result[i].deviceId+"_"+result[i].deviceName+"_"+result[i].deviceType+"'>";
								   str=str+"<td align='center' width='35%'>"+result[i].deviceName+"</td>";
								   str=str+"<td align='center' width='25%'>"+result[i].deviceTypeName+"</td>";
								   if(result[i].alpStatuIdnc==0)
									   str=str+"<td align='center' width='20%'>有效</td>";
								   else
									   str=str+"<td align='center' width='20%'>无效</td>";
								   str=str+"<td align='center' width='20%'>已创建</td></tr>";
							   }
						   }
					   }
				   }else{
                       str=str+"<tr><td colspan='4' align='center'>无数据。。。</td></tr>";
				   }
                $('#${document_id}_deviceRow').append(str);
				//列表记录点击事件
                $(".tab-tbody tr").click(function(){
                    var dataId=$(this).attr("id").split("_");
                    deviceId=dataId[0];
                    deviceName=dataId[1];
                    deviceModelType=dataId[2];
                    //获取后台坐标数据
                    var data=_DOCUMENT_EVENT.request_data("${ctx}/point/getDevicePointById",{'alpDeviceIdnty':deviceId,'alpCusNumber':jsConst.CUS_NUMBER},false);
                   if(data!=null&&data!=undefined&&data.length!=0)pointMana.showPlaceValue(data[0]);
                });
                pointMana.checkBoxEvent();//复选框点击事件
                pointMana.checkIfHave(result);//判断是否为选中
			},
			//判断是否为选中
			checkIfHave:function(result){
                for(var i=0;i<result.length;i++){
                    if(deviceIdForEdit!='') {//进入编辑状态
                        if(result[i].alpStatuIdnc==0)
                            document.getElementById("${document_id}_status_"+i).checked = true;
                        else
                            document.getElementById("${document_id}_status_"+i).checked = false;
                        if(result[i].alpXPointIdnty!=null)
                            document.getElementById("${document_id}_create_"+i).checked = true;
                        else
                            document.getElementById("${document_id}_create_"+i).checked = false;
                    }else{
                        if(result[i].alpXPointIdnty==null){
                            if(result[i].alpStatuIdnc==0)
                                document.getElementById("${document_id}_status_"+i).checked = true;
                            else
                                document.getElementById("${document_id}_status_"+i).checked = false;
						}
					}

				}
			},
            //复选框点击事件
			checkBoxEvent:function(){
			    $(".ifCreate").click(function(){
                    if($("input[name='checkboxCreate']:checked").length>1){
                        _DOCUMENT_EVENT.messge("请先保存当前编辑的点位");
                        return false;
					}
			        var id=$(this).attr("id");
			        if($("#"+id).is(':checked')){//创建模型
                        flagIfCheck=true;
                        pointMana.attachMouse();//点击创建模型
					}else{ //销毁模型
						map_3d.__g.objectManager.delayDelete(modelCurrent.guid, 0);
                        pointMana.reSetCommon();
                        $('#${document_id}_point_x').textbox('setValue',"");
                        $('#${document_id}_point_y').textbox('setValue',"");
                        $('#${document_id}_point_z').textbox('setValue',"");
                    }
                });
                $(".ifStatus").click(function(){
                    if($("input[name='checkboxStatus']:checked").length>1){
                        _DOCUMENT_EVENT.messge("请先保存当前编辑的点位");
                        return false;
                    }
                });
		    },
            //点击添加模型按钮 进入添加状态
            attachMouse:function(){
                map_3d.__g.mouseSelectMode =  gviMouseSelectMode.gviMouseSelectClick ;//鼠标左键点选模式（up的时候触发）
                if (typeof (pointMana.fnMouseClickSelect) == "function") ____events["onMouseClickSelect"] = pointMana.fnMouseClickSelect;
                map_3d.__g.interactMode = gviInteractMode.gviInteractEdit;//转为编辑模式
                if (typeof (pointMana.fnonobjectediting) == "function") ____events["onObjectEditing"] = pointMana.fnonobjectediting;
            },
            fnMouseClickSelect:function(pickResult, intersectPoint, mask, eventSender){
                if(flagIfCheck==true&&flagIfHave==false){//判断已经勾选过复选框
					//创建模型或者图片
                    pointMana.creatModelOrImg(intersectPoint.x,intersectPoint.y,intersectPoint.z);
                    modelCurrent.glow(-1);//闪烁
                    //以下编辑状态下动态获取坐标值
                    if (typeof (pointMana.fnonobjectediting) == "function") ____events["onObjectEditing"] = pointMana.fnonobjectediting;
                    map_3d.__g.objectEditor.startEditRenderGeometry(modelCurrent, gviGeoEditType.gviGeoEdit3DMove);
                    //以下为坐标赋值 modelCurrent
                    var posiotion={alpXPointIdnty:intersectPoint.x,alpYPointIdnty:intersectPoint.y,alpZPointIdnty:intersectPoint.z};
                    pointMana.showPlaceValue(posiotion);//为坐标赋值
                    flagIfHave=true;
				}
            },
            fnonobjectediting:function(){
                var position={'alpXPointIdnty':modelCurrent.getFdeGeometry().position.x,
					          'alpYPointIdnty':modelCurrent.getFdeGeometry().position.y,
                              'alpZPointIdnty':modelCurrent.getFdeGeometry().position.z};
                pointMana.showPlaceValue(position);
			},
			//为坐标赋值
            showPlaceValue:function(data){
                $('#${document_id}_point_x').textbox('setValue',data.alpXPointIdnty);
                $('#${document_id}_point_y').textbox('setValue',data.alpYPointIdnty);
                $('#${document_id}_point_z').textbox('setValue',data.alpZPointIdnty);
			},
            //创建模型或者图片
            creatModelOrImg:function(x,y,z){
                //if(creatDeviceType==1){//摄像头用创建图片或者模型
                   if(jsConst.LOAD_DEVICE_TYPE==2)//创建图片
                        modelCurrent=map.createImgImm(mapDevice["dt_"+creatDeviceType]["img"]["d_"+deviceModelType],x,y,z);
                    else
                        modelCurrent = map.createModelImm(mapDevice["dt_" + creatDeviceType]["model"]["d_" + deviceModelType],x,y,z);
                //}else
                    //modelCurrent=map.createModelImm(mapDevice["dt_"+creatDeviceType]["model"]["d_"+deviceModelType],x,y,z);
			},
            //添加的保存
            save:function(){
               if(modelCurrent!=""&&modelCurrent!=null){
                   if($("#${document_id}_pointForm").form("valid")){
                	   map_3d.__g.objectEditor.finishEdit();
                       $('#${document_id}_alpCusNumber').val(jsConst.CUS_NUMBER);
                       var formData = $("#${document_id}_pointForm").form("formData");
                       formData.alpStatuIdnc = $('.ifStatus').is(':checked')?0:1;
                       formData.alpDeviceName=deviceName;
                       formData.alpDeviceModel=deviceModelType;
                       if(deviceIdForEdit!=''&&deviceIdForEdit!=null)
                           formData.alpDeviceIdnty=deviceIdForEdit;
					   else
                           formData.alpDeviceIdnty=deviceId;
                       _DOCUMENT_EVENT.request_data_callback('${ctx}/point/savePoint',pointMana.callBackSave,formData,null	);
				   }else{
					   _DOCUMENT_EVENT.alert_msg("表单验证失败！！！");
				   }
               }else{
                   _DOCUMENT_EVENT.alert_msg("请先在地图上点击添加的模型！！！");
			   }
			},
			//保存回调函数
            callBackSave:function(result) {
                if(result.success){
                    _DOCUMENT_EVENT.messge("操作成功！");
                    map_3d.__g.interactMode = gviInteractMode.gviInteractSelect; //设置当前模式为拾取模式
                    pointMana.saveReSet(); //调用清空方法
                }else
                    _DOCUMENT_EVENT.messge("操作失败，请检查！");
            },
			//重置
			reSetData:function(){
                if(modelCurrent!=null){
                    map_3d.__g.objectManager.delayDelete(modelCurrent.guid, 0);
                }
                map_3d.setMouseFlyMode();

                $("#${document_id}_pointForm").form("clear");
                $('#${document_id}_deviceRow').html("");
                $('#${document_id}_showDevice').hide();
                $('#${document_id}_showDeviceMsg').show();
                pointMana.reSetCommon();
			},
			//保存后的数据重置
			saveReSet:function(){
                $('#${document_id}_point_x').textbox('setValue',"");
                $('#${document_id}_point_y').textbox('setValue',"");
                $('#${document_id}_point_z').textbox('setValue',"");
                $('#${document_id}_canel').hide();
                $('#${document_id}_reSet').show();
                pointMana.reSetCommon();
                pointMana.changeDeviceTypeView();
            },
			reSetCommon:function(){
                modelCurrent.glow(0);//停止闪烁
                deviceIdForEdit="";
                modelCurrent=null;
                flagIfCheck=false;
                flagIfHave=false;
			},
			//所有点位信息dialog
            allPointManage:function(){
                $("#${document_id}_dialog").dialog({
                    width:1000,
                    height:520,
                    subTitle :'点位信息列表',
                    modal:false,                 //弹出窗口底层可操作
                    position: { my: "left",at:"left+30px top+300px ",of: window} , //弹出窗口 位置
                    autoOpen:true,
                    url:'${ctx}/point/list'
                });
			},
            canel:function(){
                if(modelCurrent!=null){
                    map_3d.__g.objectManager.delayDelete(modelCurrent.guid, 0);
                }
                map_3d.setMouseFlyMode();
                pointInfo.canelSet();
                $('#${document_id}_canel').hide();
			}
        };
        pointMana.init();
    });

</script>
</body>
</html>