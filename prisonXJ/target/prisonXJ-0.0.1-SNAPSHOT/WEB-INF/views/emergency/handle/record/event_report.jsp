<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="${ctx}/static/emergency/style/style.css" rel="stylesheet" type="text/css"/>
<style>
	.coral-dialog .coral-dialog-buttonpane .coral-dialog-buttonset {
		text-align: center;
		background: #F0F0F0;
		border-top: 1px solid #ccc;
	}
</style>
<cui:form id="formId_event_report" heightStyle="fill">
  	<table class="table" style="width: 99%;">
		<cui:input id="emerHandleRecordId" name="emerHandleRecordId" type="hidden" value="${emerHandleRecordId}"></cui:input>
<%--  		<tr height="80">--%>
<%--  			<th>证据信息：</th> --%>
<%--  			<td>--%>
<%--  				<cui:button componentCls="btn-primary" label="增加" onClick="chakan"></cui:button>--%>
<%--  			</td> 			 --%>
<%--  		</tr>--%>
  		<tr height="80">
  			<%--<th>人员登记：</th>
  			<td>
  				<cui:uploader id="uploaderId_eventReportRydjb" fileObjName="uploadFile"
					uploader="${ctx}/common/affix/upload" formData="{'fileType':'3'}" 
					onUploadStart="common.onUploadStart" onUploadSuccess="common.onUploadSuccess" onInit="common.onInit"				  
					swf="${ctx}/static/resource/swf/uploadify.swf"></cui:uploader>
  			</td>--%>
			<td colspan="2">
<%--				<p style="font-size: 15px; color: #4692f0;">已选证据</p>--%>
				<cui:grid id="gridId_yxzj" rownumbers="true" multiselect="false" width="auto" height = "500" fitStyle="fill" frozenColumns="true" shrinkToFit="false">
					<cui:gridCols>
						<cui:gridCol name="id" hidden="true" frozen="true">id</cui:gridCol>
						<cui:gridCol name="einFileName" hidden="true" frozen="true">einFileName</cui:gridCol>
						<cui:gridCol name="einFilePath" hidden="true" frozen="true">einFilePath</cui:gridCol>
						<cui:gridCol name="einFtpPath" hidden="true" frozen="true">einFtpPath</cui:gridCol>
						<cui:gridCol name="ceuEvidenceId" hidden="true" frozen="true">id</cui:gridCol>
						<cui:gridCol name="" width="100" align="center" formatter="yxzjCaozuo">操作</cui:gridCol>
						<cui:gridCol name="einTitle" width="300">标题</cui:gridCol>
						<cui:gridCol name="einCrteTime" width="150" align="center">创建时间</cui:gridCol>
						<cui:gridCol name="einAddrs" width="200">发生地点</cui:gridCol>
						<cui:gridCol name="einCameraName" width="200">摄像机名称</cui:gridCol>
						<cui:gridCol name="einFileTypeIndc" width="100" align="center" formatter="convertCode" revertCode="true" formatoptions="{
					'data': combobox_evidenceUse_fileType
					}">文件类型</cui:gridCol>
					</cui:gridCols>
				</cui:grid>
			</td>
  		</tr>
<%--  		<tr height="80">--%>
<%--  			<th>经验总结：</th> --%>
<%--  			<td>--%>
<%--  				<cui:textarea id="experience" name="experience" width="700" required="true"></cui:textarea>--%>
<%--  			</td>	--%>
<%--  		</tr>--%>
	</table>
</cui:form>
<div id="divId_evidenceImg_yxzj" style="display: none; position: absolute; top: 60px; left: 200px; border: 1px solid red;">
	<img src="" width="300" height="300">
</div>
<script>
var combobox_evidenceUse_fileType = <%=CodeFacade.loadCode2Json("4.20.45")%>;
$.parseDone(function() {
	// 页面数据初始化
	initEventReprot();
});

/**
 * 初始化事件记录页面数据
 */
function initEventReprot() {
	// 应急处置记录ID
    $("#experience").textbox("setValue", '${experience}');
	var emerHandleRecordId = $("#formId_event_report").find("#emerHandleRecordId").textbox("getValue");

	var url = "${ctx}/emergency/handle/recordManage/queryDetailById.json";
	var params = {};
	if(emerHandleRecordId) {
		params["id"] = emerHandleRecordId;
	}

	var callBack = function(data) {
		if (data.success) {
			// 填充经验总结
			$("#formId_event_report").find("#experience").textbox("setValue", data.obj.experience);

			// 人员登记附件展示
			common.loadAffix("uploaderId_eventReportRydjb", data.obj.rydjbAffixList, false);
		} else {
			$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info"});
		}
	};
	ajaxTodo(url, params, callBack);


    $.ajax({
        type : 'post',
        url : '${ctx}/common/evidenceUse/searchAllData',
        data : {
            ceuYwId : emerHandleRecordId,
            ceuYwType : 3
        },
        dataType : 'json',
        success : function(data) {
            $("#gridId_yxzj").grid("clearGridData");
            for(var i = 0; i < data.length; i++) {

                $("#gridId_yxzj").grid("addRowData", data[i].id, data[i]);
            }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) {
            $.message({message:"操作失败！", cls:"error"});
        }
    });
}
function yxzjCaozuo(cellvalue, options, rawObject) {

    var html = "<a href='javascript: void(0);' onclick='showYxzj(\"" + rawObject.ceuEvidenceId + "\", \"" + rawObject.einFileTypeIndc + "\", \"" + rawObject.einFileName + "\", \"" + rawObject.einFtpPath + "\", \"" + rawObject.einFilePath + "\")' onmouseleave='hideYxzj()' style='color: #4692f0;'>预览</a>"
        + "<a href='javascript: void(0);' onclick='deleteYxzj(\"" + rawObject.id + "\", \"" + options.rowId + "\")' style='color: #4692f0; margin-left: 5px;'>删除</a>";
    return html;
}
function showYxzj(id, fileType, fileName, ftpPath, filePath) {
	// 首先判定文件在FTP中是否存在
	// 请求URL地址
	var url = "${ctx}/evidence/queryById";
	// 请求参数
	var params = {};
	params["id"] = id;

	// 回调函数
	var callBack = function (data) {
		if (data.success) {
			// ftp文件信息
			var einCusNumber = data.obj.einCusNumber;
			var einFtpPath = data.obj.einFtpPath;
			var einFilePath = data.obj.einFilePath;
			var einFileName = data.obj.einFileName;

			if(fileType == "1") {
				// 截图图片URL
				var imgUrl = '${ctx}/evidence/getFtpImage?' +
						'&ftpCusNumber=' + new Base64().multiEncode(einCusNumber, 2) +
						'&ftpPath=' + new Base64().multiEncode(einFtpPath, 2) +
						'&ftpFileName=' + new Base64().multiEncode(einFileName, 2) +
						'&temp=' + Math.random();

				$("#divId_evidenceImg_yxzj img").attr("src", imgUrl);
				$("#divId_evidenceImg_yxzj").show();
			} else {
				// 录像
				var vc = window.top.videoClient;
				var file = einFilePath + '/' + einFileName;
				vc.setLayout(1, function (result) {
					if (result && result.success) {
						vc.playVideoFile(0, file, einCusNumber, einFtpPath, null, null);
					}
				});
			}
		} else {
			$.loading("hide");
			$.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
		}
	};
	ajaxTodo(url, params, callBack);
}

function hideYxzj() {

    $("#divId_evidenceImg_yxzj img").attr("src", "");
    $("#divId_evidenceImg_yxzj").hide();
}
function deleteYxzj(id, rowId) {

    $.confirm("确认删除？", function(r) {
        if(r) {

            if(id != "") {
                $.ajax({
                    type: "post",
                    url: "${ctx}/common/evidenceUse/deleteByIds",
                    data: {
                        ids: id
                    },
                    dataType: "json",
                    success: function(data) {

                        if(data.code == "1") {
                            $.message({message:"操作成功！", cls:"success"});
                            $("#gridId_yxzj").grid("delRowData", rowId);
                         } else {
                            $.message({message:"操作失败！", cls:"error"});
                        }
                    },
                    error: function(XMLHttpRequest, textStatus, errorThrown) {
                        $.message({message:"操作失败！", cls:"error"});
                    }
                });
            } else {

                // 删除列表
                /* for(var i = 0; i < length; i++) {
                   // selarrrow.length 每次删除的时候此值都在动态改变,selarrrow也在动态改变, 每次删除第一个OK
                   $("#zfddSnplddEditGrid").grid("delRowData", selarrrow[0]);
               } */
                $("#gridId_yxzj").grid("delRowData", rowId);
            }
        }
    });
}

/**
 * 证据信息
 */
function chakan() {
	// 应急处置记录ID

	var emerHandleRecordId = $("#formId_event_report").find("#emerHandleRecordId").textbox("getValue");
	common.relEvidence(emerHandleRecordId, "3");
}
</script>