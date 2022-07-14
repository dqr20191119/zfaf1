/*-------------------add by zk start-------------------*/
function Messenger(){
	
}
var messenger=new Messenger();
/**
 * 已使用的证据编号集合
 */
Messenger.prototype.evidenceSqnoList = new Array();
/**
 * 证据类型集合
 */
Messenger.prototype.fileTypeList = new Array(); 
/**
 * 是否详情展示
 */
Messenger.prototype.isDetailShow = false;

//通过id获取rowData
Messenger.prototype.zjxx_getRowData=function(rowId){
	return $("#gridId_zjxx").grid("getRowData",rowId);
}

Messenger.prototype.getzjxxData = function (id) {
    var resData = null;
    $.ajax({
        type : "post",
        url : jsConst.basePath+"/evidence/getEvidenceById?id="+id,
        dataType : 'json',
        async:false,
        success : function(data) {
            if(data.code==200){
                var obj = data.data;
                resData =obj;
            }
        }
    });
    return resData;
};

//证据信息toolbar
var toolbarOnClick_zjxx = function(event, ui) {
	if ("create_jdd" == ui.id) {
		f_createJdd();
	}else if("batchDelete_zjxx" == ui.id){
		batchDeleteZjxx();
	}else if("edit_zjxx" == ui.id){
		editZjxx();
	}
}
//编辑图片证据
function editZjxx(){
	var selarrrow = $("#gridId_zjxx").grid("option", "selarrrow");
	if (selarrrow.length == 1) {
		var rowData = $("#gridId_zjxx").grid("getRowData",selarrrow[0]);
		//1:图片， 2：录像
		if(rowData.EIN_FILE_TYPE_INDC == 1){
			window.top.openEditImgDialog(rowData.ID);
		}else {
			$.message({
				iframePanel:true,
				message : "请选择一条图片证据记录！",
				cls : "warning"
			});
			return;
		}
	} else {
		$.message({
			iframePanel:true,
			message : "请选择一条图片证据记录！",
			cls : "warning"
		});
	}
}
//查询证据信息
function searchZjxx(){
	debugger;
	var postData = {};
	//var einSttsIndc = $('#zjxxzt').combobox("getValue");
	var einFileTypeIndc = $('#wjlx').combobox("getValue");
	var startTime= $('#startingDate').datepicker("getDateValue");
	var endTime= $('#endingDate').datepicker("getDateValue");

	/*if (einSttsIndc != "") {
		postData['einSttsIndc'] = einSttsIndc;
	}*/
	if (einFileTypeIndc != "") {
		postData['einFileTypeIndc'] = einFileTypeIndc;
	}
	
	if (startTime != "") {
		postData['startTime'] = startTime;
	}
	if (endTime != "") {
		postData['endTime'] = endTime;
	}

	$("#gridId_zjxx").grid("option", "postData", postData);
	$("#gridId_zjxx").grid("reload",jsConst.basePath+"/evidence/searchEvidence.json?einSttsIndc=0&einCusNumber="+jsConst.ORG_CODE);
}
//批量删除证据信息
function batchDeleteZjxx(){
	var selarrrow = $("#gridId_zjxx").grid("option", "selarrrow");
	console.log(selarrrow);
	if (selarrrow != null && selarrrow.length>0) {
		$.confirm( {
			message:"确认删除？",
			iframePanel:true,
			callback: function(sure) {
			if (sure == true) {
				$.ajax({
					type : "post",
					url : jsConst.basePath+"/evidence/batchDelete.json",
					contentType: "application/json; charset=utf-8",
					data:JSON.stringify(selarrrow),
					success : function(data) {
						if(data.success){
							$.message({
								iframePanel:true,
								message : "操作成功！",
								cls : "success"
							});
							$("#gridId_zjxx").grid("reload",jsConst.basePath+"/evidence/searchEvidence.json?einSttsIndc=0&einCusNumber="+jsConst.ORG_CODE);
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
			
			}
		});
	} else {
		$.message({
			iframePanel:true,
			message : "请先勾选需要处理记录！",
			cls : "warning"
		});
	}
}
//创建监督单
function f_createJdd(snapType){
	//清空fileTypeList
	messenger.fileTypeList = new Array();
	var ur = "";
	if(snapType =="1"){//表示视频截图
        //查询最新的一条事件记录
        ur = jsConst.basePath+"/monitor/newjdc?lx=1";
        $.ajax({
            type : "post",
            url : jsConst.basePath+"/evidence/getNowEvidence",
            dataType : 'json',
            async:false,
            success : function(data) {
                if(data.code==200){
                    var obj = data.data;
                    messenger.evidenceSqnoList.push(obj.id);
                    messenger.fileTypeList.push(obj.einFileTypeIndc)
                }
            }
        });
    }else{
        messenger.evidenceSqnoList = $("#gridId_zjxx").grid("option", "selarrrow");
    }
	if (messenger.evidenceSqnoList != null && messenger.evidenceSqnoList.length>0) {
	    if(snapType!="1"){
            for(var i=0;i<messenger.evidenceSqnoList.length;i++){
                messenger.fileTypeList.push($("#gridId_zjxx").grid("getCell",messenger.evidenceSqnoList[i], "EIN_FILE_TYPE_INDC"));
            }
            ur = jsConst.basePath+"/monitor/newjdc";
        }
		$("#dialogId_jdjc").dialog({
			width : 900,
			height : 700,
			subTitle : '创建监督单',
			url : ur,
		});
		$("#dialogId_jdjc").dialog("open");


	} else {
		$.message({
			iframePanel:true,
			message : "请先勾选需要处理记录！",
			cls : "warning"
		});
	}
}


/*-------------------add by zk end-------------------*/