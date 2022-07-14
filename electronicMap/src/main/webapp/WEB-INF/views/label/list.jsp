<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="PanelCon">
	<div class="PanelList">
		<div class="clearfix panelTop" >
			<!-- 操作按钮开始 -->
			 <div>
			   <cui:form id="labelSearchData" >
			    <cui:button label="编辑" onClick="labelList.openUpdate" cls="greenbtn" 	></cui:button>
			    <cui:button label="删除" onClick="labelList.delRoute" cls="deleteBtn" ></cui:button>	
			    <cui:button label="展示标签" onClick="labelList.showSelectLabels" cls="btn-info"></cui:button>	  
			     <div style="float: right;" >
			     <%-- <cui:combobox  name="mliLabelType" id="labelType1"  required="true" data="label.labelType" emptyText="请选择标签种类" width="120"/> --%>			
			     <cui:combotree  id="areaTree" width="180" name="mliAreaId" url="str" />
			     <cui:input type="text" id="search" width="120"  placeholder="请输入标签名称" name="mliLabelCode"/>
			     <cui:button label="搜索" onClick="labelList.search" cls="cyanbtn" ></cui:button>	
			     <cui:button label="重置" onClick="labelList.reset" cls="btn-danger" ></cui:button>		  
			    </div>
	            </cui:form>	
			</div>
			<!-- 操作按钮结束 -->
			<!-- 列表展示开始 -->
		</div>
		<cui:grid id="labelGrid" rownumbers="false" multiselect="true"
		      url="${ctx}/labels/labelPage" fitStyle="fill" width="auto" height="400" rowNum="10">
			<cui:gridCols>
				<cui:gridCol name="id" hidden="true" align="center">id</cui:gridCol>
				<cui:gridCol name="mliLabelCode" width="40" align="center">标签名称</cui:gridCol>
				<cui:gridCol name="mliAreaId" width="40" hidden="true">关联区域id</cui:gridCol>
				<cui:gridCol name="mliAreaName" width="40" align="center">关联区域</cui:gridCol>
				<cui:gridCol name="mliWidth" width="40" align="center" >标签宽度</cui:gridCol>
			    <cui:gridCol name="mliHeight" width="40" align="center">标签高度</cui:gridCol>	
			    <cui:gridCol name="mliX" width="40" align="center">标签x坐标</cui:gridCol>	
			    <cui:gridCol name="mliY" width="40" align="center">标签y坐标</cui:gridCol>	
			    <cui:gridCol name="mliZ" width="40" align="center">标签z坐标</cui:gridCol>	
			    <cui:gridCol name="mliLabelType" hidden="true"></cui:gridCol>			   
			</cui:gridCols>
			<cui:gridPager gridId="labelGrid" />
		</cui:grid>
	<!-- 列表展示结束 -->
</div>
</div>
<script type="text/javascript">
      $(function(){    	  
    	  labelList ={ 
    			  url:"${ctx}/common/areadevice/findForCombotree.json?cusNumber="+jsConst.CUS_NUMBER+"&deviceType=0",	           
    	           openUpdate:function(){
    	        	   var selrow = $("#labelGrid").grid("option", "selarrrow");    	        	 
                        if(selrow.length==1){
                        	label.cleanLabels();
                            var rowData = $('#labelGrid').grid("getRowData", selrow[0]);
                         	var point = map_3d.__g.geometryFactory.createPoint(gviVertexAttribute.gviVertexAttributeZ);
						    point.x = rowData.mliX;
						    point.y = rowData.mliY;
						    point.z = rowData.mliZ;	
                			setData(selrow[0]);
                			createLabel(point,rowData.mliWidth,"楼内民警：20人\n楼内罪犯：100人\n待维修设备：05台",rowData.mliHeight)
                			var angle = map_3d.__g.new_EulerAngle;
                			    angle.heading = 0;
                		        angle.titl = -30;
                			 map_3d.__g.camera.lookAt(point.position, 40, angle);
                			$("#labelDialog").dialog("close"); 
                        }else{
                        	$.message({
            					message : "请先勾选一条需要修改的记录！",
            					cls : "warning"
            				});
                        }
                        	
    	           },
    	           delRoute:function(){
    	            var selrow = $("#labelGrid").grid("option", "selarrrow");
   	        		if (selrow.length>0) {
   	        			$.confirm("确认是否删除？", function(r) {
   	     				if (r) {
   	     					var j =0;//成功条数
   	     					var k =0;//失败条数
   	     					for(var i=0;i<selrow.length;i++){
   	     					$.ajax({
   	     					    async :false,
   	     						type : "post",
   	     						data : {"id":selrow[i]},
   	     						url : "${ctx}/labels/delLabel.json",
   	     						success : function(data) {
   	     						   if(data.message=="操作成功"){
   	     							   j++;
   	     						   }else{
   	     							   k++;
   	     						   }
   	     						}
   	     					});
   	     				  }
   	     				$("#labelGrid").grid("reload");
   	     				   $.message({
								message : "删除成功"+j+"条,删除失败"+k+"条.",
								cls : "success"
							});						
   	     				}
   	     			});
   	        		} else {
   	        			$.message({
   	     				message : "请先勾选需要删除记录！",
   	     				cls : "warning"
   	     			  });
   	        		}
   	           },
   	           search:function(){
   	        	   var data = $("#labelSearchData").form("formData");
   	        	   $("#labelGrid").grid("option",{
   	        		   postData:data,
   	        		   url:'${ctx}/labels/labelPage',
   	        	   });
   	        	   $("#labelGrid").grid("reload");
   	           },
			reset:function(){
				$("#labelSearchData").form("reset");
			},
			showSelectLabels:function(){
				label.cleanLabels();
				 var selrow = $("#labelGrid").grid("option", "selarrrow");    	        	 
                 if(selrow.length>0){
                	 for(var i=0;i<selrow.length;i++){
						 var rowData = $('#labelGrid').grid("getRowData", selrow[i]);
                		 var point = map_3d.__g.geometryFactory.createPoint(gviVertexAttribute.gviVertexAttributeZ);
						    point.x = rowData.mliX;
						    point.y = rowData.mliY;
						    point.z = rowData.mliZ;	
             			createLabel(point,rowData.mliWidth,"楼内民警：20人\n楼内罪犯：100人\n待维修设备：05台",rowData.mliHeight); 
             			$("#labelDialog").dialog("close");
                	 }
                 }else{
                	 $.message({
     					message : "请先至少勾选一条需要展示的记录！",
     					cls : "warning"
     				});
                 }
			},
			
     }  
    	  $( "#areaTree" ).combotree({url:labelList.url});  
	});
	  
      function setData(selrow){
    	   var rowData = $('#labelGrid').grid("getRowData", selrow);
			$("#labelId").textbox("setValue",rowData.id);
			$("#labelBox").combotree("setValue",rowData.mliAreaId);
			$("#labelCode").textbox("setValue",rowData.mliLabelCode);
			$("#mliWidth").textbox("setValue",rowData.mliWidth); 
			$("#mliHeight").textbox("setValue",rowData.mliHeight); 
			$("#view_x").textbox("setValue",rowData.mliX); 
			$("#view_y").textbox("setValue",rowData.mliY);
			$("#view_z").textbox("setValue",rowData.mliZ);  
			$("#labelType").combobox("setValue",rowData.mliLabelType); 
      }
</script>
