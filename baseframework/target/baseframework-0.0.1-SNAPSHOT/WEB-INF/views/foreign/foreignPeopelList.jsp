<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.formCell{
	margin-top: 10px;
	margin-left: 20px;
	display: inline-block;
}
.uploadButtonClass{
  width: 100px;
}
</style>

<div style="background-color: #EDEEEE; width: 100%; height: 100%;">
	<div style="margin-left: 2%; height: 100%;">
        <div>
			   <cui:form id="foreignPeopelSearchData" >
			    <cui:button label="添加" onClick="foreignPeopel.openDialog('add')" cls="greenlight"></cui:button>
			    <cui:button label="编辑" onClick="foreignPeopel.openDialog('update')" cls="greenbtn"></cui:button>
			    <cui:button label="删除" onClick="foreignPeopel.del" cls="deleteBtn" ></cui:button>			  
			    <div style="float: right;" >			
			     <cui:input type="text" id="fpdIdCardCode_search" width="150"  placeholder="请输入证件号" name="fpdIdCardCode"/>
			     <cui:input type="hidden" id="cusNumber_fdp"  name="cusNumber"/><span>进入时间：</span>
			     <cui:datepicker id="startTime" dateFormat="yyyy-MM-dd HH:mm:ss" name="startTime"></cui:datepicker> <span>至</span>
			     <cui:datepicker id="endTime" dateFormat="yyyy-MM-dd HH:mm:ss" name="endTime"></cui:datepicker>
			     <cui:button label="搜索" onClick="foreignPeopel.search" cls="cyanbtn" ></cui:button>	
			     <cui:button label="重置" onClick="foreignPeopel.reset" cls="btn-danger"></cui:button>		  
			    </div>
	            </cui:form>	
			</div>
		<cui:grid id="gridId_foreignPeopel" fitStyle="fill" multiselect="true" colModel="foreignPeopel.gridId_foreignPeopel_colModelDate" url="str" onDblClickRow="foreignPeopel.onDblClickRow">
			<cui:gridPager gridId="gridId_foreignPeopel" />
		</cui:grid>
	</div>

</div>



<cui:dialog id="dialogId_foreignPeopel" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true">
<div>
	<cui:form id="formId_foreignPeopel">
	       <!-- 基本信息 begin-->

           <div style="display: inline-block; width: 650px;height:200px;margin-left: 20px">
           <fieldset>
                       <legend>基本信息</legend>
           <div class="formCell">
                           <label style="width: 80px">证件类型：</label>
                           <cui:combobox id="fpdIdCardTypeIndc" name="fpdIdCardTypeIndc"   emptyText="请选择证件类型"  data="foreignPeopel.cardType" required="true" onChange="foreignPeopel.cardTypeSelect"></cui:combobox>
                   </div>
                   
                      <div class="formCell">
                           <label style="width: 80px">证件号：</label>
                           <cui:input id="fpdIdCardCode" name="fpdIdCardCode"   placeholder="请输入证件号"  required="true" onEnter="foreignPeopel.findDataBefore"  validType="idno"></cui:input>
                   </div> 
                 <div class="formCell">
                           <label style="width: 80px">人员姓名：</label>
                           <cui:input id="foreginPeopelId" name="id"  type="hidden"></cui:input>
                           <cui:input id="fpd_username" name="fpdPeopleName"  type="text"  placeholder="请输入姓名" required="true"></cui:input>
                    </div>
                    
                      <div class="formCell">
                           <label style="width: 80px">性别：</label>
                           <cui:combobox id="fpdSexIndc" name="fpdSexIndc"    data="foreignPeopel.sexType" required="true"  value="男"></cui:combobox>
                   </div>
                   
                   <div class="formCell">
                           <label style="width: 80px">年龄：</label>
                           <cui:input id="fpdAge" name="fpdAge"   placeholder="请输入年龄" required="true"  pattern="/^[0-9]*$/"></cui:input>
                   </div>
                   
                    <div class="formCell">
                           <label style="width: 80px">家庭住址：</label>
                           <cui:input id="fpdFamilyAddrs" name="fpdFamilyAddrs"   placeholder="请输入家庭住址" ></cui:input>
                   </div>
                  
                   <div class="formCell">
                           <label style="width: 80px">电话：</label>
                           <cui:input id="fpdPhone" name="fpdPhone"   placeholder="请输入号码"  required="true" pattern="/^[0-9]*$/"></cui:input>
                   </div>  
                   
                     <div class="formCell">
                           <label style="width: 80px">人员类别：</label>
                            <cui:combobox id="fpdPeopleType" name="fpdPeopleType"    data="foreignPeopel.peopleType" required="fasle" ></cui:combobox>
                   </div>       
                      </fieldset>     
                </div>
         <!-- 基本信息 end-->
              <div style="width: 280px ;height:450px ;float: right;margin-right: 20px">
                  <fieldset>
                      <legend>照片</legend>
                      <div style="width: 100%;height:auto">
		               <div style="margin-left: 20px">
							<cui:uploader id="foreignPeopelPic" fileObjName="uploadFile"    uploadLimit="2" 
								buttonText="请选择照片..." uploader="${ctx}/common/affix/upload"
								onUploadStart="common.onUploadStart"
								onUploadSuccess="common.onUploadSuccess" onInit="common.onInit"
								onSWFReady="foreignPeopel.onSWFReady"
								swf="${ctx}/static/resource/swf/uploadify.swf">
							</cui:uploader>
						</div>
						<cui:input id="foreignPeopelPicIds"  name="foreignPeopelPicIds" type="hidden"></cui:input>
					</div>
		     </fieldset>
              </div>
          
               <div style="width: 650px;height:200px;margin-top: 40px;margin-left: 20px">
                      <fieldset>
                       <legend>出入信息</legend>
                    <div class="formCell">
                           <label style="width: 80px">办证民警：</label>
                           <cui:combotree id="fpdOprtnPoliceIdnty"  name="fpdOprtnPoliceIdnty"   required="true" onChange="foreignPeopel.onMJSelect"  allowPushParent="false"></cui:combotree>
                           <cui:input id="fpdOprtnPoliceName" name="fpdOprtnPoliceName"    type="hidden"></cui:input>
                   </div> 
                    <div class="formCell">
                           <label style="width: 80px">陪同民警：</label>
                           <cui:combotree id="fpdAccmpnyPoliceIdnty" name="fpdAccmpnyPoliceIdnty"    required="true"  onChange="foreignPeopel.onMJSelect"  allowPushParent="false" ></cui:combotree>
                           <cui:input id="fpdAccmpnyPoliceName" name="fpdAccmpnyPoliceName"    type="hidden"></cui:input>
                   </div> 
                    <div class="formCell">
                           <label style="width: 80px">驾驶标识：</label>
                           <cui:combobox id="fpdDriverIndc" name="fpdDriverIndc"   emptyText="是否驾车"  data="foreignPeopel.haveCar" required="true"></cui:combobox>
                   </div>
                    <div class="formCell">
                           <label style="width: 80px">去向：</label>
                           <cui:input id="fpdDirection" name="fpdDirection"   placeholder="请输入去向"  required="true"></cui:input>
                   </div>        
                     <div class="formCell">
                           <label style="width: 80px">进出事由：</label>
                           <cui:textarea id="fpdReason" name="fpdReason"    required="true"  height="100"></cui:textarea>
                   </div>   
                   
                   <div class="formCell">
                           <label style="width: 80px">备注：</label>
                           <cui:textarea id="fpdRemark" name="fpdRemark"      height="100"></cui:textarea>
                   </div>      
               </fieldset>                       
           </div>

	</cui:form>
          <div id="optionButtons" style="height:30px;width:120px;margin:0px auto;border:0px solid red;margin-top: 80px">
	            <cui:button label="提交" text="false" onClick="foreignPeopel.addOrUpdate"></cui:button>
	              <cui:button label="重置" text="false" onClick="foreignPeopel.reset"></cui:button>
          </div>
      </div>
</cui:dialog>
<script>
	var jsConst = window.top.jsConst;

	var cusNumber = jsConst.CUS_NUMBER;//监狱号

	var userId = jsConst.USER_ID;//当前登陆者
	
	var foreignPeopel = null;
    var isUploaderDisable =false;
    
	$(function(){
		foreignPeopel={
				sexType:[{text:"男",value:"男"},{text:"女",value:"女"}],
				cardType:[{text:"身份证",value:"1"},{text:"驾驶证",value:"2"}],
				haveCar:[{text:"驾车",value:"1"},{text:"不驾车",value:"0"}],
			 gridId_foreignPeopel_colModelDate :[ {
						label : "ID",
						name : "ID",
						hidden : true,
						key : true
					}, {
						name : "FPD_PEOPLE_NAME",
					    label : "姓名",
						align : "center",
						width : 80
					},{
						name : "FPD_ID_CARD_CODE",
					    label : "证件号",
						align : "center",
						width : 120
					},{
						name : "FPD_SEX_INDC",
						label : "性别",
						align : "center",
						width : 80
					}, {
						name : "FPD_AGE",
						label : "年龄",
						align : "center",
						width : 80
					}, {
						name : "FPD_DIRECTION",
						label : "去向",
						align : "center",
						width : 120
					}, {
						name : "FPD_ACCMPNY_POLICE_NAME",
					    label : "陪同民警",
						align : "center",
						width : 80
					},{
						name : "FPD_ENTER_TIME",
						label : "进入时间",
						align : "center",
						width : 120,		
					},{
						name : "FPD_LEAVE_TIME",
						label : "离开时间",
						align : "center",
						width : 120,
					},{
						name : "FPD_REASON",
						label : "进入原因",
						align : "center",
						width : 180
					},{
						name : "FPD_REMARK",
						label : "备注",
						align : "center",
						width : 120
					},{
					    name : "FPD_FAMILY_ADDRS",//住址家庭
						hidden : true
					},{
						name : "FPD_PHONE",//电话
						hidden : true
					},{
						
						name : "FPD_ID_CARD_TYPE_INDC",//证件类型
						hidden : true
					},{
						name : "FPD_ACCMPNY_POLICE_IDNTY",//陪同民警 id
						hidden : true
					},{
						name : "FPD_DRIVER_INDC",//是否驾车
						hidden : true
					},{
						name : "FPD_OPRTN_POLICE_NAME",//办证民警姓名
						hidden : true
					},{
						name : "FPD_OPRTN_POLICE_IDNTY",//办证民警id
						hidden : true
					},{
						name : "FPD_PEOPLE_TYPE",//外来人员类型
						hidden : true
					}],
					//打开新增或修改dialog
				   openDialog:function(str){
					   debugger
				     $("#formId_foreignPeopel").form("setIsLabel",false);	
					  $("#formId_foreignPeopel").form("clear");
					  $("#optionButtons").show();			
					  if( isUploaderDisable){
						  $("#foreignPeopelPic").uploader("disable", false);
						   isUploaderDisable =false;
					   }
					  var  title ="";
					 if(str=="add"){						 
                         title="新增";
                         foreignPeopel.clearPics();
                         $("#fpdSexIndc").combobox("setValue","男");
                         $("#fpdDriverIndc").combobox("setValue","0");
                         $("#fpdIdCardTypeIndc").combobox("setValue","1");
					 }else if(str=="update"){
						 var  selarrrow =$("#gridId_foreignPeopel").grid("option","selarrrow");
						 if(selarrrow.length==1){
							 var rowData = $('#gridId_foreignPeopel').grid("getRowData", selarrrow[0]);
							 foreignPeopel.setDateToForm(rowData);
							 foreignPeopel.doPicJob(selarrrow[0],"");
							 title ="修改";
						 }else{
								$.message({
	            					message : "请先勾选一条需要修改的记录！",
	            					cls : "warning"
	            				});
								return;
						 }
					 }				   
					 $("#dialogId_foreignPeopel").dialog({
						    width : 1000,
							height : 680,
							title : title
					 })
					 $("#dialogId_foreignPeopel").dialog("open");
				   },
				   //填充表格数据
				   setDateToForm:function(data){
					   if(data.ID){
						   $("#foreginPeopelId").textbox("setValue",data.ID);
					   }		
					   $("#fpd_username").textbox("setValue",data.FPD_PEOPLE_NAME);
					   $("#fpdAge").textbox("setValue",data.FPD_AGE);
					   $("#fpdIdCardCode").textbox("setValue",data.FPD_ID_CARD_CODE);
					   $("#fpdSexIndc").combobox("setValue",data.FPD_SEX_INDC);
					   $("#fpdFamilyAddrs").textbox("setValue",data.FPD_FAMILY_ADDRS);
					   $("#fpdIdCardTypeIndc").combobox("setValue",data.FPD_ID_CARD_TYPE_INDC);
					   $("#fpdPhone").textbox("setValue",data.FPD_PHONE);
					   $("#fpdDirection").textbox("setValue",data.FPD_DIRECTION);
					   if(data.FPD_OPRTN_POLICE_IDNTY){
						   $("#fpdOprtnPoliceIdnty").combotree("setValue",data.FPD_OPRTN_POLICE_IDNTY);
					   }
					   if(data.FPD_OPRTN_POLICE_NAME){
						   $("#fpdOprtnPoliceName").textbox("setValue",data.FPD_OPRTN_POLICE_NAME);
					   }
					  if(data.FPD_ACCMPNY_POLICE_IDNTY){
						  $("#fpdAccmpnyPoliceIdnty").combotree("setValue",data.FPD_ACCMPNY_POLICE_IDNTY);
					  }
					 if(data.FPD_ACCMPNY_POLICE_NAME){
						   $("#fpdAccmpnyPoliceName").textbox("setValue",data.FPD_ACCMPNY_POLICE_NAME);
					 }
					 if(data.FPD_ACCMPNY_POLICE_NAME){
						   $("#fpdAccmpnyPoliceName").textbox("setValue",data.FPD_ACCMPNY_POLICE_NAME);
					 }					
					 if(data.FPD_DRIVER_INDC){
						   $("#fpdDriverIndc").combobox("setValue",data.FPD_DRIVER_INDC);
					 }
				     if(data.FPD_REASON){
				  	   $("#fpdReason").textbox("setValue",data.FPD_REASON);
				     }			
					   $("#fpdRemark").textbox("setValue",data.FPD_REMARK);
				   },
				   addOrUpdate:function(){
					 var data=null;
					 var url="";
				    var picIds = $("#foreignPeopelPic_affixIds").val()==null?"":$("#foreignPeopelPic_affixIds").val();
				    $("#foreignPeopelPicIds").textbox("setValue",picIds);
					 if($("#formId_foreignPeopel").form("valid")){
						  data =$("#formId_foreignPeopel").form("formData");
						   if(data.id==null||data.id==""){
							   url="${ctx}/foreignPeopel/insertForeignPeopel";
						   }else{
							   url="${ctx}/foreignPeopel/updateForeignPeopel";
						   }
						   $.ajax({
								type : "post",
								url : url,
								data : data,
								dataType : "json",
								success : function(data) {
									if (data.success) {
										 $.message({
												message : "操作成功",
												cls : "success"
											});				
										$("#gridId_foreignPeopel").grid("reload");
										$("#dialogId_foreignPeopel").dialog("close");		
										 foreignPeopel.clearPics();
									} else {
										$.message({ message : data.message, cls : "warning" });
									}
								},
								error : function(XMLHttpRequest, textStatus,
										errorThrown) {
									alert(textStatus);
								}
							});
					 }else{
						 $.message({ message : "请检查表单内容", cls : "warning" });
					 }				   
				   },
				   //选择民警id的同时，对民警姓名进行保存
				   onMJSelect:function(event, ui){
					   if(event.target.id=="fpdOprtnPoliceIdnty"){
						   $("#fpdOprtnPoliceName").textbox("setValue",ui.newText);
					   }else if(event.target.id=="fpdAccmpnyPoliceIdnty"){
						   $("#fpdAccmpnyPoliceName").textbox("setValue",ui.newText);
					   }
				   },
				   del:function(){
					      var selrow = $("#gridId_foreignPeopel").grid("option", "selarrrow");
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
		   	     						url : "${ctx}/foreignPeopel/delForeignPeopel.json",
		   	     						success : function(data) {
		   	     						   if(data.success){
		   	     							   j++;
		   	     						   }else{
		   	     							   k++;
		   	     						   }
		   	     						}
		   	     					});
		   	     				  }
		   	     				$("#gridId_foreignPeopel").grid("reload");
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
				   findDataBefore : function(event,ui){
					   foreignPeopel.clearPics();
					   $.ajax({
							type : "post",
							url : "${ctx}/foreignPeopel/findRecord.json",
							data : {fpdIdCardCode : ui.value,cusNumber:cusNumber},
							dataType : "json",
							success : function(data) {
								debugger
								if (data.success) {		
									foreignPeopel.setDateToForm(data.obj);
									 common.loadAffix("foreignPeopelPic", data.obj.pics, false);
								} else {
									$.message({ message : data.msg, cls : "warning" });
								}
							},
					   })
				   },
				   reset :function(){
					   var id = $("#foreginPeopelId").textbox("getValue");
					   if(id==null||id==""){
						   $("#formId_foreignPeopel").form("clear");
					   }else{
						   var  selarrrow =$("#gridId_foreignPeopel").grid("option","selarrrow");
						   var rowData = $('#gridId_foreignPeopel').grid("getRowData", selarrrow[0]);
							 foreignPeopel.setDateToForm(rowData);
					   }
				   },
				   doPicJob:function(id,funName){
					   if( isUploaderDisable){
						   $("#foreignPeopelPic").uploader("disable", false);
						   isUploaderDisable =false;
					   }
					   foreignPeopel.clearPics();
					   $.ajax({
							type : "post",
							url : "${ctx}/foreignPeopel/findPic.json",
							data : {id:id},
							dataType : "json",
							success : function(data) {
								if (data.success) {		
									 common.loadAffix("foreignPeopelPic", data.obj, false);
									 if(funName=="onDblClickRow"){									
										 $("#foreignPeopelPic").uploader("disable", true);
										 isUploaderDisable=true;
									 }
								} else {
									$.message({
				   	     				message : "加载图片失败！",
				   	     				cls : "warning"
				   	     			  });
								}
							},
					   })						
				   },
				   clearPics:function(){
					   $("#foreignPeopelPic").prev(".wrapper").html("");
				        $("#foreignPeopelPic_affixIds").val("");
					    $("#foreignPeopelPicIds").textbox("setValue","");
				   },
				   search:function(){
					   $("#cusNumber_fdp").textbox("setValue",cusNumber);
					  var data= $("#foreignPeopelSearchData").form("formData");
					   $("#gridId_foreignPeopel").grid("option",{
						   postData:data,
						   url:"${ctx}/foreignPeopel/pageList.json"
					   });
					   $("#gridId_foreignPeopel").grid("reload");
				   },
				   reset:function(){
					  $("#foreignPeopelSearchData").form("clear");
				   },
				   //双击显示详情
				   onDblClickRow:function(e, ui){
					   $("#formId_foreignPeopel").form("setIsLabel",true);
					   var rowData = $('#gridId_foreignPeopel').grid("getRowData", ui.rowId);
						 foreignPeopel.setDateToForm(rowData);
						 foreignPeopel.doPicJob(ui.rowId,"onDblClickRow");				
					   $("#dialogId_foreignPeopel").dialog({
						    width : 1000,
							height : 680,
							title : "外来人员信息"
					 })
					 $("#optionButtons").hide();
					   $("#dialogId_foreignPeopel").dialog("open");
				   },
				   //改变证件类型时更换验证规则
				   cardTypeSelect:function(event, ui){
					   if(ui.newValue=="1"){
						   $("#fpdIdCardCode").textbox("option",{validType:'idno'});
					   }else{
						   $("#fpdIdCardCode").textbox("option",{validType:''});
					   }
				   },
				   onSWFReady:function(){
					   $("#foreignPeopelPic").uploader("disable", false);
				   }
		}
		$("#fpdAccmpnyPoliceIdnty").combotree({url:"${ctx}/common/authsystem/findDeptPoliceForCombotree?cusNumber="+cusNumber});
		$("#fpdOprtnPoliceIdnty").combotree({url:"${ctx}/common/authsystem/findDeptPoliceForCombotree?cusNumber="+cusNumber});
		$("#gridId_foreignPeopel").grid({url:"${ctx}/foreignPeopel/pageList.json?cusNumber="+cusNumber});
	});
</script>