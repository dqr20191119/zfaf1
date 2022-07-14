<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div class="F-left" style="width: 100%;background-color: #EDEEEE;overflow: scroll ">
	<!-- 公共群组信息 -->
	<cui:tree id="groupTree" asyncEnable="true" keepParent="true"
		 asyncType="post" asyncUrl="" simpleDataEnable="true"
		asyncAutoParam="id,name" onClick="onClick" rootNode="true" showRootNode="true" >
	</cui:tree>
	<!-- 自定义群组信息 -->
	<cui:tree id="DIYGroupTree" asyncEnable="true" keepParent="true"
		 asyncType="post" asyncUrl="" simpleDataEnable="true"
		asyncAutoParam="id,name" onClick="onClick" rootNode="true" showRootNode="true" >
	</cui:tree>

</div>

<script>
	var jsConst=window.top.jsConst;
	var cusNumber=jsConst.ORG_CODE;						//监狱编号
	var userId=jsConst.USER_ID;			//登录人

	$.parseDone(function() {
		$("#groupTree").tree("option","asyncUrl","${ctx}/groupManage/simpleGroupTree.json?gmaUseRange=0&gmaCusNumber="+cusNumber);
		$("#groupTree").tree("reload");
		
		$("#DIYGroupTree").tree("option","asyncUrl","${ctx}/groupManage/simpleGroupTree.json?gmaUseRange=1&gmaCusNumber="+cusNumber+"&gmaCrteUserId="+userId);
		$("#DIYGroupTree").tree("reload");
		
		 var panel = $('#group-layout').layout('panel', 'center');
	});

	//显示单击的群组信息
	function onClick(event, id, node) {

		//重置表单
		resetForm();

        if(node.id == '0' || node.id == '1'){
            //适用范围0、公共预案，1、自定义预案
            $('#gmaUseRange').radiolist("setValue",node.id);
            $('#gmaParentId').combotree("clear");
            $('#gmaParentId').combotree('reload','${ctx}/groupManage/simpleGroupTree.json?gmaCusNumber='+cusNumber+'&gmaUseRange='+node.id);
        }

		$.ajax({
			type : 'post',
			url : '${ctx}/groupManage/findById.json',
			data : {'id':node.id},
			dataType : 'json',
			success : function(data) {
				if(!data.exception){
					var model=data.group;
					$('#groupId').textbox("setValue",model.id);
					$('#gmaIsLeaf').textbox("setValue",model.gmaIsLeaf);
					$('#gmaCusNumber').textbox("setValue",model.gmaCusNumber);
					//适用范围0、公共预案，1、自定义预案
					$('#gmaUseRange').radiolist("setValue",model.gmaUseRange);
					$('#gmaParentId').combotree('reload','${ctx}/groupManage/simpleGroupTree.json?gmaCusNumber='+cusNumber+'&gmaUseRange='+model.gmaUseRange);
					/* //设置父节点为readonly，即不可修改父节点
					$('#gmaParentId').combotree("option","readonly",true); */
					$('#gmaGrpName').textbox("setValue",model.gmaGrpName);
					$('#gmaShortName').textbox("setValue",model.gmaShortName);
					$("#gmaParentId").combotree("setValue",model.gmaParentId);
					$("#gmaTypeIndc").combobox("setValue",model.gmaTypeIndc);
					$("#gmaSubTypeIndc").combobox("setValue",model.gmaSubTypeIndc);
					//要害时段，显示开始，结束时间
					if("2"==model.gmaSubTypeIndc){
						$(".yhsd").show();
					}else{
						$(".yhsd").hide();
					}
					$("#gmaPurpose").radiolist("setValue",model.gmaPurpose);
					//因为执行$("#gmaStartTime").datepicker("setDate",null)会出问题
					if(model.gmaStartTime!=null){
						$("#gmaStartTime").datepicker("setValue",model.gmaStartTime);
					}
					if(model.gmaEndTime!=null){
						$("#gmaEndTime").datepicker("setValue",model.gmaEndTime);	
					}
					//隐藏添加按钮
					//$('.isAdd').hide();
					$('#gmaRemark').textbox("setValue",model.gmaRemark);
					$('#gmaShowSeq').textbox("setValue",model.gmaShowSeq);
					//$("#gmaCrteTime").datepicker("setValue",model.gmaCrteTime);
					//$("#gmaUpdtTime").datepicker("setValue",model.gmaUpdtTime);
				}else{
					$.message({
						iframePanel:true,
				        message:data.exception.cause.message,
				        type:"danger"
				    });
				}
			},error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.alert({
					message:textStatus,
					title:"信息提示",
					iframePanel:true
				});
			}
		});
		
		//加载右侧成员信息
		$("#checkedGrid").grid('reload','${ctx}/groupMember/queryTree.json?grdTypeIndc=1&grdCusNumber='+cusNumber+'&grdGrpId='+node.id);
	
	}
	
	function resetForm(){
		$("#formId_group").form("reset");
	} 
</script>