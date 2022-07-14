<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
.form-control {
	width: 100%;
}
</style>
<div style="text-align: center; height: 100%; width: 100%">
	<cui:form id="formId_duty_flag_edit" heightStyle="fill">
		<input id="duty_flag_id"  type='hidden' name="id" />
		<table class="table" style="width: 98%">
			<tr>
				<th>指挥长最后值班人员：</th>
				<td>
                    <cui:autocomplete source="ry_autocomplete" id="zhzFlag" name="zhzFlag" postMode="value" width="300"  multiLineLabel="true" ></cui:autocomplete>
				</td>
                <th>指挥长值中班值班长最后时间：</th>
                <td>
                    <cui:datepicker componentCls="form-control"  id="zhzZbFlagDutyDate" name="zhzZbFlagDutyDate"   required="true" timeFormat="yyyy-MM-dd"></cui:datepicker>
                </td>

			</tr>
			<tr>
                <th>女值班长工作日最后值班人员：</th>
                <td>
                    <cui:autocomplete source="ry_autocomplete" id="wzbzWFlag" name="wzbzWFlag" postMode="value" width="300"  multiLineLabel="true"  ></cui:autocomplete>
                </td>
                <th>女值班长节假日最后值班人员：</th>
                <td>
                    <cui:autocomplete source="ry_autocomplete" id="wzbzHFlag" name="wzbzHFlag" postMode="value" width="300"  multiLineLabel="true" ></cui:autocomplete>
                </td>
			</tr>
			<tr>
                <th>女值班员工作日最后值班人员：</th>
                <td>
                    <cui:autocomplete source="ry_autocomplete" id="wzbyWFlag" name="wzbyWFlag" postMode="value" width="300"  multiLineLabel="true"  ></cui:autocomplete>
                </td>
                <th>女值班员节假日最后值班人员：</th>
                <td>
                    <cui:autocomplete source="ry_autocomplete" id="wzbyHFlag" name="wzbyHFlag" postMode="value" width="300"  multiLineLabel="true" ></cui:autocomplete>
                </td>
			</tr>
			<tr>
                <th>男值班长工作日最后值班人员：</th>
                <td>
                    <cui:autocomplete source="ry_autocomplete" id="mzbzWFlag" name="mzbzWFlag" postMode="value" width="300"  multiLineLabel="true"  ></cui:autocomplete>
                </td>
                <th>男值班长节假日最后值班人员：</th>
                <td>
                    <cui:autocomplete source="ry_autocomplete" id="mzbzHFlag" name="mzbzHFlag" postMode="value" width="300"  multiLineLabel="true"  ></cui:autocomplete>
                </td>
			</tr>
            <tr>
                <th>男值班长工作日晚班最后值班人员：</th>
                <td>
                    <cui:autocomplete source="ry_autocomplete" id="mzbzWNFlag" name="mzbzWNFlag" postMode="value" width="300"  multiLineLabel="true"  ></cui:autocomplete>
                </td>
            </tr>
            <tr>
                <th>男值班员工作日最后值班人员：</th>
                <td>
                    <cui:autocomplete source="ry_autocomplete" id="mzbyWFlag" name="mzbyWFlag" postMode="value" width="300"  multiLineLabel="true"  ></cui:autocomplete>
                </td>
                <th>男值班员节假日最后值班人员：</th>
                <td>
                    <cui:autocomplete source="ry_autocomplete" id="mzbyHFlag" name="mzbyHFlag" postMode="value" width="300"  multiLineLabel="true"  ></cui:autocomplete>
                </td>
            </tr>
		</table>
	</cui:form>
</div>

<script>

	$.parseDone(function() {
        $.ajax({
            type : 'post',
            url : '${ctx}/zbgl/rygl/getdutyFlagByCusNumber',
            data: {"cusNumber":cusNumber},
            dataType : 'json',
            success : function(data) {
                $("#duty_flag_id").val(data.id);
                $("#zhzFlag").autocomplete("setValue",data.zhzFlag);
                $("#zhzZbFlagDutyDate").datepicker("setValue",data.zhzZbFlagDutyDate);
                $("#wzbzWFlag").autocomplete("setValue",data.wzbzWFlag);
                $("#wzbzHFlag").autocomplete("setValue",data.wzbzHFlag);
                $("#wzbyWFlag").autocomplete("setValue",data.wzbyWFlag);
                $("#wzbyHFlag").autocomplete("setValue",data.wzbyHFlag);
                $("#mzbzWFlag").autocomplete("setValue",data.mzbzWFlag);
                $("#mzbzWNFlag").autocomplete("setValue",data.mzbzWNFlag);
                $("#mzbzHFlag").autocomplete("setValue",data.mzbzHFlag);
                $("#mzbyWFlag").autocomplete("setValue",data.mzbyWFlag);
                $("#mzbyHFlag").autocomplete("setValue",data.mzbyHFlag);
            }
        });
	});
	

</script>