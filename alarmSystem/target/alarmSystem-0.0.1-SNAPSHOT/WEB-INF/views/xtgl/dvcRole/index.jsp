<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style type="text/css">
#tabTools {
	box-sizing: border-box;
	padding-top: 2px;
}
#tabTools div.table {
	width: 100%;
	height: 100%;
	display: table;
	padding-right: 5px;
}
#tabTools div.table div.t-cell {
	display: table-cell;
	vertical-align: middle;
}
#tabTools div.table div.t-cell span {
	margin-left: 10px;
}
#sltDvcType {
	height: 23px;
}
#txtSearch {
	width: 160px;
	height: 21px;
}
#dataPanel {
	float: left;
	width: 100%;
	height: 100%;
}
#dpttPanel {
	border-right: 1px solid #999;
	background-color: rgba(245, 245, 245, 0.8);
}

#dvcPanel {
	background-color: rgba(245, 245, 245, 0.8);
}

#dpttPanel, #dvcPanel {
	float: left;
	width: 50%;
	height: 100%;
	box-sizing: border-box;
	padding: 5px 5px;
	overflow: hidden;
}

#dpttPanel .search, #dvcPanel .search {
	box-sizing: border-box;
	width: 100%;
	height: 5%;
	padding: 0px 5px;
}

#dpttScroll, #dvcScroll {
	width: 100%;
	height: 95%;
	overflow: hidden;
	overflow-y: scroll;
}
</style>

<div id="tabTools">
	<div class="table">
		<div class="t-cell">
			<cui:button componentCls="btn-danger" label="删除" onClick="remove()" ></cui:button>
   	 		<cui:button componentCls="btn-success" label="保存"  onClick="save()"></cui:button>
    
			<span style="display:none" class="label">设备类型：</span> <select style="display:none" id="sltDvcType">
				<option value="1">摄像机</option>
				<!--<option value="2">报警器</option> -->
			</select> <span style="display:none" class="label">操作限制：</span> <select style="display:none" id="sltSttsIndc">
				<option value="1">允许操作</option>
				<!--<option value="2">禁止操作</option>-->
			</select>
		</div>
	</div>
</div>

<cui:tabs id="tabs" heightStyle="fill" onActivate="f_onActivate">
	<ul>
		<li><a href="#tabView">查看</a></li>
		<li><a href="#tabEdit">编辑</a></li>
		<li><a href="#tabAdd">新增</a></li>
	</ul>
	<div id="tabView">
		<div id="dataPanel">
			<div id="dpttPanel">
				<input id="txtSearchDptt" class="search" placeholder="输入部门名称搜索定位...">
				<div id="dpttScroll">
					<cui:tree id="dpttTree" asyncEnable="true" keepParent="true"
						asyncType="post"  simpleDataEnable="true" onLoad="" 		
						asyncUrl=""
						checkable="false" chkboxType="zCheck" chkStyle="checkbox"
						onDblClick="" onClick=""
						onRightClick="" formatter=""
						asyncAutoParam="id,name" rootNode="true"
						showRootNode="true">
					</cui:tree>
				</div>
			</div>
			<div id="dvcPanel">
				<input id="txtSearchDvc" class="search" placeholder="输入设备名称搜索定位...">
				<div id="dvcScroll">
					<cui:tree id="dvcTree" asyncEnable="true" keepParent="true"
						asyncType="post"  simpleDataEnable="true" onLoad="" 		
						asyncUrl=""
						checkable="false" chkboxType="zCheck" chkStyle="checkbox"
						onDblClick="" onClick=""
						onRightClick="" formatter=""
						asyncAutoParam="id,name" rootNode="true"
						showRootNode="true">
					</cui:tree>
				</div>
			</div>
		</div>
	</div>
	<div id="tabEdit">
	</div>
	<div id="tabAdd">
	</div>
</cui:tabs>


<script type="text/javascript">

	var jsConst=window.top.jsConst;
	var cusNumber=jsConst.ORG_CODE;							//监狱编号
	var userId=jsConst.USER_ID;						//登录人
	var dprtmntIdnty=jsConst.DEPARTMENT_ID;					//部门ID
	var dpttZTree = null;
	var dvcZTree = null;

	var zCheck = {
		enable : true,
		chkboxType : {
			"Y" : "s",
			"N" : "s"
		}
	};
	var zView = {
		showIcon : true
	};
	$.parseDone(function(){
		$("#sltSttsIndc").val(1);
		$("#sltDvcType").val(1);
		
		loadCheck ();
	});

	
	function f_onActivate(event, ui){//回调事件: tabs切换事件结束后触发。
    	var index=$("#tabs").tabs("option","active");
    	switch (index) {
    	//查看
		case 0:
			console.log("查看");	
			loadCheck();
			$('#dataPanel').appendTo($('#tabView'));
			break;
		//编辑
		case 1:
			console.log("编辑");
			loadEdit();
			$('#dataPanel').appendTo($('#tabEdit'));
			break;
		//新增
		case 2:
			console.log("新增");
			loadAdd();
			$('#dataPanel').appendTo($('#tabAdd'));
			break;
		}
	}
	
	// 初始化搜索
	$('#txtSearchDptt').bind('keyup', function () {
		$("#dpttTree").tree("filterNodesByParam", {
			name : this.value
		});
	});

	$('#txtSearchDvc').bind('keyup', function () {
		$("#dvcTree").tree("filterNodesByParam", {
			name : this.value
		});
	});
	
	function show_dpttOnDblClick(event, id, node){
		$("#dvcTree").tree("reload",
				"${ctx}/xtgl/dvcRole/simpleCameraTreeByXML.json?wid=-1&cusNumber="+cusNumber+"&dprtmntIdnty="+node.id);
	}
	function edit_dpttOncheck(event, id, node){
		$("#dpttTree").tree("checkNode",node,true);
		$.ajax({
			type : 'post',
			url : "${ctx}/xtgl/dvcRole/simpleCameraTreeByXML.json?wid=-1&cusNumber="+cusNumber+"&dprtmntIdnty="+node.id,
			dataType : 'json',
			success : function(data) {
				if(data.exception==undefined){
					console.log(data);
					var exitCamera=data;
					for(var i=0;i<exitCamera.length;i++){
						var node=$('#dvcTree').tree('getNodeByParam','id',exitCamera[i].id)
						$("#dvcTree").tree("checkNode",node,true);
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
	}
	
	/*
	 * 查看
	 */
	function loadCheck () {
		$(".del").hide();
		$(".save").hide();
		//初始化树
		$("#dpttTree").tree("option","checkable",false);
		$("#dpttTree").tree("reload","${ctx}/xtgl/dvcRole/searchExistDprtmnt.json?ddrDvcTypeIndc=1&ddrCusNumber="+cusNumber);
		$("#dvcTree").tree("option","checkable",false);
		$("#dpttTree").tree("option","onDblClick",show_dpttOnDblClick);
	}

	/*
	 * 编辑
	 */
	function loadEdit () {
		$(".del").show();
		$(".save").show();
		
		$("#dpttTree").tree("option","onDblClick",edit_dpttOncheck);
		
		$("#dpttTree").tree("option","checkable",true);
		$("#dpttTree").tree("reload",
				"${ctx}/xtgl/dvcRole/searchExistDprtmnt.json?ddrDvcTypeIndc=1&ddrCusNumber="+cusNumber);
			
		
		$("#dvcTree").tree("option","checkable",true);
		$("#dvcTree").tree("reload",
				"${ctx}/xtgl/dvcRole/simpleAreaCameraTreeByXML.json?wid=1&cusNumber="+cusNumber+"&dprtmntIdnty="+dprtmntIdnty);
		
	}

	/*
	 * 加载新增
	 */
	function loadAdd () {
		$(".del").show();
		$(".save").show();
		
		$("#dpttTree").tree("option","onDblClick","");
		
		$("#dpttTree").tree("option","checkable",true);
		$("#dpttTree").tree("reload",
				"${ctx}/xtgl/dvcRole/simpleDepartmentTree.json?cusNumber="+cusNumber);
			
		$("#dvcTree").tree("option","checkable",true);
		$("#dvcTree").tree("reload",
				"${ctx}/xtgl/dvcRole/simpleAreaCameraTreeByXML.json?wid=1&cusNumber="+cusNumber+"&dprtmntIdnty="+dprtmntIdnty);
		
		
	}
	
	/*
	 * 保存
	 */
	function save () {
		var dpttNodes = $("#dpttTree").tree("getCheckedNodes");
		var dvcNodes = $("#dvcTree").tree("getCheckedNodes");
		var dvcType = getDvcType();
		var sttsIndc = getSttsIndc();
		var tipStr = '是否确认添加[ ';
		
		if (dpttNodes.length == 0) {
			$.alert({
				message:"请选择部门!",
				title:"信息提示",
				iframePanel:true
			});
			return;
		};
		if (dvcNodes.length == 0) {$.alert({
			message:"请选择设备!",
			title:"信息提示",
			iframePanel:true
		}); return;};
		
		//批量删除参数
		var dpttIds=new Array();
		//批量新增参数
		var dvcDptt=new Array();
		for (var i = 0, I = dpttNodes.length; i < I; i++) {
			
			dpttIds.push(dpttNodes[i].id);
			
			tipStr += dpttNodes[i].name + ', ';
			
			for (var j = 0, J = dvcNodes.length; j < J; j++) {
				if (dvcNodes[j].node_type=="camera") {
					dvcDptt.push({
						'ddrCusNumber':cusNumber,
						'ddrDvcTypeIndc':dvcType,
						'ddrDvcIdnty':dvcNodes[j].id,
						'ddrDprtmntIdnty':dpttNodes[i].id,
						'ddrDprtmntName':dpttNodes[i].name,
						'ddrSttsIndc':sttsIndc,
						'ddrCrteUserId':userId,
						'ddrUpdtUserId':userId
					});
				}
			}
			
		}
		tipStr = tipStr.substring(0, tipStr.length-2) + ' ]等部门的控制权限?';
		$.confirm( {
			message:tipStr,
			iframePanel:true,
			callback: function(sure) {
				if (sure == true) {
					$.ajax({
						type : "post",
						url : jsConst.basePath+"/xtgl/dvcRole/batchDelete.json",
						contentType: "application/json; charset=utf-8",
						data:JSON.stringify(dpttIds),
						success : function(data) {
							if(data.success){
								$.ajax({
									type : 'post',
									url : '${ctx}/xtgl/dvcRole/batchInsert.json',
									contentType: "application/json; charset=utf-8",
									data : JSON.stringify(dvcDptt),
									dataType : 'json',
									success : function(data) {
										if(data.success){
											$.message({
												message : "操作成功",
												cls : "success",
												iframePanel:true
											});
											
										}else{
											$.message( {
												iframePanel:true,
										        message:data.msg,
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
							}else{
								$.message( {
									iframePanel:true,
							        message:data.msg,
							        type:"danger"
							    });
							}
							
						}
					}); 
				}
				if (sure == false) {
					console.log('cancel');
				}
			}
		});

	}
	/*
	 * 删除
	 */
	function remove () {
		
		var dpttNodes = $("#dpttTree").tree("getCheckedNodes");
		var tipStr = '是否确认删除[ ';
		
		if (dpttNodes.length == 0) {$.alert({
			message:"请选择部门!",
			title:"信息提示",
			iframePanel:true
		}); return;};
		
		//批量删除参数
		var dpttIds=new Array();
		for (var i = 0, I = dpttNodes.length; i < I; i++) {
			
			dpttIds.push(dpttNodes[i].id);
			
			tipStr += dpttNodes[i].name + ', ';
		
		}
		tipStr = tipStr.substring(0, tipStr.length-2) + ' ]等部门的控制权限?';
		$.confirm( {
			message:tipStr,
			iframePanel:true,
			callback: function(sure) {
				if (sure == true) {
					$.ajax({
						type : "post",
						url : jsConst.basePath+"/xtgl/dvcRole/batchDelete.json",
						contentType: "application/json; charset=utf-8",
						data:JSON.stringify(dpttIds),
						success : function(data) {
							if(data.success){
								$.message({
									message : "操作成功",
									cls : "success",
									iframePanel:true
								});
								
							}else{
								$.message( {
									iframePanel:true,
							        message:data.msg,
							        type:"danger"
							    });
							}
							
						}
					}); 
				}
				if (sure == false) {
					console.log('cancel');
				}
			}
		});
		
	}
	
	function getDvcType () {
		return $('#sltDvcType').val();
	}

	function getSttsIndc () {
		return $('#sltSttsIndc').val();
	}
</script>