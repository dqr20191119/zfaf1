<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>

<div style="text-align: center; height: 100%; width: 100%">
	<cui:form id="formId_updateInfo" heightStyle="fill">
		<table class="table" style="width: 98%">
			<tr>
				<th>门禁开启密码：</th>
				<td>
					<cui:input id="openPassword" name="openPassword" componentCls="form-control" required="true" ></cui:input>
				</td>
			</tr>
			
		</table>
	</cui:form>
	<div class="dialog-buttons" style="margin-bottom: 0px; text-align: center;">
		<cui:button label="提交" text="false" onClick="update"></cui:button>
		<cui:button label="重置" text="false" onClick="clear"></cui:button>
	</div>
</div>

<script>
	var info;
	$.parseDone(
			function (){
				findById();
			});

	
	function  findById() {
        $.ajax({
            type : "post",
            url : "${ctx}/dooropen/findById.json?id=${ID}",
            dataType : "json",
            success : function(data) {
                if (data.success) {
                    info = data.obj;
                    $("#formId_updateInfo").form("load", info);
                } else {
                    $.messageQueue({
                        message : data.msg,
                        cls : "warning",
                        iframePanel : true,
                        type : "info"
                    });
                }
            },
            error : function(XMLHttpRequest, textStatus,
                             errorThrown) {
                $.messageQueue({
                    message : textStatus,
                    cls : "warning",
                    iframePanel : true,
                    type : "info"
                });
            }
        });
    }
	
	
	
	//发送修改请求
	function update() {
		if ($("#formId_updateInfo").form("valid")) {
			var formData = $("#formId_updateInfo").form("formData");
			var url = '${ctx}/dooropen/updateDoor.json?id=${ID}';
			$.ajax({
				type : 'post',
				url : url,
				data : formData,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						$.messageQueue({
							message : data.msg,
							cls : "success",
							iframePanel : true,
							type : "info"
						});
						$("#gridId_door_open").grid("reload");
					$("#dialogId_door_mjgl").dialog("close");
					} else {
						$.messageQueue({
							message : data.msg,
							cls : "warning",
							iframePanel : true,
							type : "info"
						});
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({
						message : textStatus,
						cls : "warning",
						iframePanel : true,
						type : "info"
					});
				}
			});
		} else {
			alert("请确认输入是否正确！");
		}

	}

	function clear() {
		$("#formId_updateInfo").form("load", info);
	}

	
</script>
</html>