<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
.form-control {
	width: 100%;
}
</style>

<div style="width: 100%; height: 100%">
	<cui:form id="formId_policeInfo_" heightStyle="fill">
		<table class="table" style="width: 100%;">
			<tr>
				<td>警号：</td>
				<td>
					<cui:input componentCls="form-control" id="policeNumber" name='PBD_POLICE_IDNTY'></cui:input>
				</td>

				<td rowspan="4" colspan="2" align="center">
					<img src="${ctx}/static/images/noImg.jpg" id="pbdImgDir" width="200" height="200"  />
				</td>
			</tr>
			<tr>
				<td>姓名：</td>
				<td>
					<cui:input componentCls="form-control" id="policeName" name='PBD_POLICE_NAME'></cui:input>
				</td>
			</tr>
			<tr>
				<td>性别：</td>
				<td>
					<cui:input componentCls="form-control" id="policeSex" name='PBD_SEX'></cui:input>
				</td>
			</tr>

			<tr>
				<td>出生日期：</td>
				<td>
					<cui:input componentCls="form-control" id="brithDate" name='PBD_BIRTH_DATE'></cui:input>
				</td>
			</tr>

			<tr>

				<td>警务通号：</td>
				<td>
					<cui:input componentCls="form-control" id="policeCmmnct" name='PBD_POLICE_CMMNCT'></cui:input>
				</td>
				<td>对讲呼号：</td>
				<td>
					<cui:input componentCls="form-control" id="talkNum" name='PBD_TALK_NUM'></cui:input>
				</td>
			</tr>
			<tr>

				<td>监狱短号：</td>
				<td>
					<cui:input componentCls="form-control" id="prisonShortNumber" name='PBD_SHORT_PHONE'></cui:input>
				</td>


				<td>固定电话：</td>
				<td>
					<cui:input componentCls="form-control" id="fixedPhone" name='PBD_FIXED_PHONE'></cui:input>
				</td>
			</tr>
			<tr>
				<td>部门：</td>
				<td>
					<cui:input componentCls="form-control" id="departmentName" name='PBD_DRPTMNT'></cui:input>
				</td>
				<td>手机号码：</td>
				<td>
					<cui:input componentCls="form-control" id="phone" name='PBD_PHONE'></cui:input>
				</td>
			</tr>
			<tr>
				<td>职务：</td>
				<td>
					<cui:input componentCls="form-control" id="position" name='PBD_PSTN_NAME'></cui:input>
				</td>
				<td>住宅电话：</td>
				<td>
					<cui:input componentCls="form-control" id="homePhoneNumber" name='PBD_FAMILY_PHONE'></cui:input>
				</td>
			</tr>
		</table>
	</cui:form>
</div>

<script type="text/javascript">
	$.parseDone(function() {
		var url = '${ctx}/xxhj/jnmj/findPoliceByUserId?pbdcusNumber=${pbdcusNumber}&pbdUserId=${pbdUserId}';
		$.ajax({
			type : "post",
			url : url,
			dataType : "json",
			success : function(data) {
				$("#formId_policeInfo_").form("setReadOnly", true);
				$("#formId_policeInfo_").form("load", data);
				$("#pbdImgDir").attr("src","${ctx}/common/authsystem/findMjPicInfo?loginName="+data.PBD_LOGIN_NAME+"&demptId="+data.PBD_DRPTMNT_ID);
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
	});
</script>


