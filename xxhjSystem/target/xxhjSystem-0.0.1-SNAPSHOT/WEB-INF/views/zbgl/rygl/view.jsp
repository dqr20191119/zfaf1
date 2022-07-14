<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
	<cui:form id="formId_rygl_view">
		<cui:input type='hidden' name="id" />

 		<table class="table">
			<tr>
				<th>单位：</th>
				<td><cui:combobox id="dw1" data="combox_dw" name="cusNumber" readonly="true"   ></cui:combobox></td>
                <th>部门：</th>
                <td><cui:combobox id="dept1" name="deptNumber"  readonly="true" ></cui:combobox></td>
            </tr>
			<tr>			
				<th>姓名：</th>
				<td><cui:input  name="name"   readonly="true" ></cui:input></td>
				<th>年龄：</th>
                <td><cui:input id="age" name="nl"  readonly="true" ></cui:input></td>
                <th>警号：</th>
                <td><cui:input id="policeBh" name="policeBh"   readonly="true" ></cui:input></td>
			</tr>
			<tr>
                <th>出生日期：</th>
                <td><cui:input id="csrq" name="csrq"   readonly="true"></cui:input></td>
				<th>性别：</th>
				<td><cui:combobox id="sex" name="sex" data="combobox_sex" readonly="true" ></cui:combobox></td>
			</tr>
            <tr>
                <th>职级：</th>
                <td><cui:input id="jobLevel" name="jobLevel" readonly="true" ></cui:input></td>
                <th>职务：</th>
                <td><cui:input id="position" name="position" readonly="true" ></cui:input></td>
            </tr>
			<tr>
				<th>人员状态：</th>
                <td><cui:combobox id="ryzt" name="ryzt" data="combobox_ryzt" readonly="true" ></cui:combobox></td>
			</tr>
		</table>
	</cui:form>
</div>

<script>
	
	$.parseDone(function() {
	
		var id = '${id}';
		if(id) {
			loadForm("formId_rygl_view", "${ctx}/zbgl/rygl/getById?id="+id, function(data) {
	                var dw=  $("#dw1").combobox( "getValue");
	                if(dw=='4300'){
	                	 $.ajax({
	                         type : 'post',
	                         url : '${ctx}/zbgl/rygl/listSjzzxx',
	                         dataType : 'json',
	                         success : function(data) {
	                             $("#dept1").combobox( "loadData", data);
	                         },
	                         error : function(XMLHttpRequest, textStatus, errorThrown) {
	                             $.loading("hide");
	                             $.message({message:"操作失败！", cls:"error"});
	                         }
	                     });
	                }else{
	                	   $.ajax({
	                           type : 'post',
	                           url : '${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox',
	                           data: {
	                               "cusNumber" : dw
	                           },
	                           dataType : 'json',
	                           success : function(data) {
	                               $("#dept1").combobox( "loadData", data);
	                           },
	                           error : function(XMLHttpRequest, textStatus, errorThrown) {
	                               $.loading("hide");
	                               $.message({message:"操作失败！", cls:"error"});
	                           }
	                       });
	                }
				});
		}	
	});
	
</script>
