<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
	.region_div{
		margin-top:10px;
		height:200px;
	}
	.mp_table{
		margin:5px auto 10px 25px;
	}
	.mp_td{
		padding-left:5px;
	}
	.mp_td input{
		width:100px;
	}
	.mp_btns{
		padding:5px 90px;
	}
</style>
<div style="height:570px;">
	<div style="text-align: center;margin:5px 0px;font-size: 16px;font-weight: bold;background-color: ">添加模型</div>
<cui:form id="regionForm" method="post" action="" heightStyle="fill" style="padding-top:0px;">
	<input type="hidden" id="id" name="id" value="" />
	<cui:layout id="region-layout" fit="true">
		<cui:layoutRegion region='north' split="false" style="width:240px;height:190px;" maxHeight="190" maxWidth="240" url="${ctx}/portal/regionTree" onResize="initTreebox">
		</cui:layoutRegion>
		<cui:layoutRegion region='south' split="false" style="width: 220px;height:350px;"  onLoad="" onResize="">
			<div class="region_div">
				<table class="menu_table">
					<tr>
						<td class="tdLabel"><label>模型类型：</label></td>
						<td class="tdElement"><cui:combobox id="cameraType" data="dvrTypeData" onclick="changeEvent(this)"/></td>
					</tr>
					<tr>
						<td class="tdLabel"><label>模型名称：</label></td> 
						<td class="tdElement"><cui:combobox id="cameraList"  onclick="changeEvent(this)" /></td>
					</tr>
					<tr>
						<td class="tdLabel"><label>可被拾取：</label></td>
						<td class="tdElement"><cui:combobox id="areaProperty" data="propertyData" /></td>
					</tr>
					<tr>
						<td class="tdLabel"><label>最近距离：</label></td>
						<td class="tdElement"><cui:input componentCls="form-control"
							id="abdMapName" name="abdMapName" value="0"/></td>
					</tr>
					<tr>
						<td class="tdLabel"><label>最远距离：</label></td>
						<td class="tdElement"><cui:input componentCls="form-control"
							id="abdMapName" name="abdMapName" value="100"/></td>
					</tr>
					<tr>
						<td class="tdLabel"><label>启用开关：</label></td>
						<td class="tdElement"><cui:button label="启用标注" style="width:188px;" onClick="isEnable" ></cui:button></td>
					</tr>
					<tr>
						<td colspan="2">
							<table class="mp_table">
								<tr>
									<th colspan="2">模型点位坐标</th>
									<th>微调值</th>
								</tr>
								<tr>
									<td>X坐标：</td>
									<td class="mp_td"><cui:input id="" name="" value="0" /></td></td>
									<td class="mp_td"><cui:input id="" name="" value="0" /></td>
								</tr>
								<tr>
									<td>Y坐标：</td>
									<td class="mp_td"><cui:input  id="" name="" value="0" /></td></td>
									<td class="mp_td"><cui:input id="" name="" value="0" /></td>
								</tr>
								<tr>
									<td>Z坐标：</td>
									<td class="mp_td"><cui:input id="" name="" value="0" /></td></td>
									<td class="mp_td"><cui:input id="" name="" value="0" /></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="mp_btns">
							<span>
								<cui:button label="保存" onClick="save" ></cui:button>
								<cui:button label="修改" onClick="update" ></cui:button>
							</span>
						</td>
					</tr>
				</table>
			</div>
		</cui:layoutRegion>
	</cui:layout> 
</cui:form>
</div>		

<script type="text/javascript">
var dvrTypeData = [
	{"text":"请选择","value":"0","selected":"selected"},
    {"text":"摄像机","value":"1"},
    {"text":" 门  ","value":"2"},
    {"text":"报警器","value":"3"}
];
var propertyData = [
	    {"text":"是","value":"1","selected":"selected"},
	    {"text":"否","value":"0"}
];
var cameraListData = [
	    {"text":"是","value":"1","selected":"selected"},
	    {"text":"否","value":"0"}
];

//根据区域信息初始化摄像头下拉列表
function initCameraList(areaId){
	$.ajax({
		type : 'post',
		url : '${ctx}/camera/findByCbdAreaIdAndCbdCusNumber.json',
		data : {'areaId':areaId,'cbdCusNumber':'3261'},
		dataType : 'json',
		success : function(data) {
			console.log(data);
		    $("#cameraList").append("<option value='0' selected='selected'>请选择</option>"); 
			for(var i=0; i<data.length; i++){
			    $("#cameraList").append("<option value='"+date[i].id+"'>"+date[i].cbdName+"</option>"); 
			}
		},error : function(XMLHttpRequest, textStatus, errorThrown) {
			$.alert(textStatus);
		}
	});
}
//修改模型名称事件
function changeEvent(obj){
	if($(obj).attr("id")=="cameraType"){
		//清空模型名称
		var type = $('#cameraType').combobox('getValue');
		if(type == '1'){//摄像机
			
		}else if(type == '2'){//门
			
		}else if(type == '3'){//报警器
			
		}
	}else if($(obj).attr("id")=="cameraList"){
		
	}
}
//保存
function save(){
	
}
//修改
function update(){
	
}
</script>



</body>
</html>