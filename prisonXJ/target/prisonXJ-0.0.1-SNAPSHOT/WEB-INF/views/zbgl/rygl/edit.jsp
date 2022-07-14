<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div>
	<cui:form id="formId_rygl_edit">
		<cui:input type='hidden' name="id" />

 		<table class="table">
			<tr>
				<th>单位：</th>
				<td><cui:combobox id="dw" data="combox_dw" name="cusNumber" required="true" onChange="change" ></cui:combobox></td>
                <th>部门：</th>
                <td><cui:combobox id="dept" name="deptNumber" showClose="ture" ></cui:combobox></td>
                <th>人员状态：</th>
                <td><cui:combobox id="ryzt" name="ryzt" data="combobox_ryzt" required="true" width="250" onSelect="showStartAndEndDate"></cui:combobox></td>
                <th>备注：</th>
                <td><cui:input id="remark" name="remark" ></cui:input></td>
            </tr>
			<tr>			
				<th>姓名：</th>
				<td><cui:input  name="name"  required="true" ></cui:input></td>
                <th>警号：</th>
                <td><cui:input id="policeBh" name="policeBh"   ></cui:input></td>
                <th class="th_1">不参与值班起始日期：</th>
                <td class="th_1"><cui:datepicker  id="rygl_startDate" name="startDate"  dateFormat="yyyy-MM-dd"></cui:datepicker ></td>
                <th class="th_2">不参与值班截止日期：</th>
                <td class="th_2"><cui:datepicker  id="rygl_endDate" name="endDate" startDateId="rygl_startDate"  dateFormat="yyyy-MM-dd" ></cui:datepicker ></td>

                <th class="th_jd">借调起始日期：</th>
                <td class="th_jd"><cui:datepicker  id="rygl_jdStartDate" name="jdStartDate"  dateFormat="yyyy-MM-dd"></cui:datepicker ></td>
                <th class="th_jd">借调结束日期：</th>
                <td class="th_jd"><cui:datepicker  id="rygl_jdEndDate" name="jdEndDate" startDateId="rygl_jdStartDate"  dateFormat="yyyy-MM-dd" ></cui:datepicker ></td>

			</tr>
			<tr>
                <th>出生日期：</th>
                <td><cui:datepicker  id="csrq" name="csrq"  dateFormat="yyyy-MM-dd" onChange="getAgeByCsrq" ></cui:datepicker ></td>
                <th>年龄：</th>
                <td><cui:input id="age" name="nl"  ></cui:input></td>
				<th>性别：</th>
				<td><cui:combobox id="sex" name="sex" data="combobox_sex" required="true" ></cui:combobox></td>
			</tr>
            <tr>
                <th>职级：</th>
                <td><cui:input id="jobLevel" name="jobLevel"  ></cui:input></td>
                <th>职务：</th>
                <td><cui:input id="position" name="position"  ></cui:input></td>
                <th>值班岗位：</th>
				<td><cui:combobox id="job" name="job" data="combobox_zbJob" required="true"  onSelect="setAfterInsertId_source"></cui:combobox></td>
            </tr>
			<%-- <tr>
				<th>人员状态：</th>
                <td><cui:combobox id="ryzt" name="ryzt" data="combobox_ryzt" required="true" width="250"></cui:combobox></td>
			</tr> --%>
            <tr>
                <th>是否中间插入：</th>
                <td><cui:combobox id="insertType" name="insertType" data="combobox_insertType"   onSelect="setAfterInsertId" ></cui:combobox></td>
               <%-- <th>插入该</th>--%>
                <%--<td><cui:input id="afterInsertId" name="afterInsertId"></cui:input></td>--%>
                <th></th>
                <td id="InsetId_td"><label>插入该</label><cui:autocomplete id="afterInsertId" name="afterInsertId"   postMode="value" multiLineLabel="true" placeholder ="请输入要搜索的姓名"  ></cui:autocomplete><label>之后</label><td>
                <%--<th>之后</th>--%>
            </tr>
		</table>
	</cui:form>
</div>

<script>
	var cusNumber = jsConst.CUS_NUMBER;
	var USER_LEVEL = jsConst.USER_LEVEL;
	var combobox_insertType=[{"value":"0","text":"否"},{"value":"1","text":"是"}];
	$.parseDone(function() {
        $("#InsetId_td").hide();
		if(USER_LEVEL!=1){
			$("#job").combobox("option","required","false");
		}
      //  $("#rygl_startDate").datepicker("option","required","true")

		var id = '${id}';
		if(id) {//修改
			loadForm("formId_rygl_edit", "${ctx}/zbgl/rygl/getById?id="+id, function(data) {
				 $("#dw").combobox("option","readonly","true");
                var dw=  $("#dw").combobox( "getValue");
                if(dw=='4300'){
                	 $.ajax({
                         type : 'post',
                         url : '${ctx}/zbgl/rygl/listSjzzxx',
                         dataType : 'json',
                         success : function(data) {
                             $("#dept").combobox( "loadData", data);
                         },
                         error : function(XMLHttpRequest, textStatus, errorThrown) {
                             $.loading("hide");
                             $.message({message:"操作失败！", cls:"error"});
                         }
                     });
                    setAfterInsertId_source();
                }else{
                	   $.ajax({
                           type : 'post',
                           url : '${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox',
                           data: {
                               "cusNumber" : dw
                           },
                           dataType : 'json',
                           success : function(data) {
                               $("#dept").combobox( "loadData", data);
                           },
                           error : function(XMLHttpRequest, textStatus, errorThrown) {
                               $.loading("hide");
                               $.message({message:"操作失败！", cls:"error"});
                           }
                       });
                }
                showStartAndEndDate();
			});
		}else{//新增页面
			  $("#dw").combobox("option","readonly","true");
	            $("#dw").combobox("setValue", cusNumber);
	            $.ajax({
                    type : 'post',
                    url : '${ctx}/common/authsystem/findAllChildrenOrgByJyKeyForCombobox',
                    data: {
                        "cusNumber" : cusNumber
                    },
                    dataType : 'json',
                    success : function(data) {
                        $("#dept").combobox( "loadData", data);
                    },
                    error : function(XMLHttpRequest, textStatus, errorThrown) {
                        $.loading("hide");
                        $.message({message:"操作失败！", cls:"error"});
                    }
                });
            showStartAndEndDate();
		}

	});


	function setAfterInsertId(e,u) {
	    var insertType =$("#insertType").combobox("getValue");
	   // alert(insertType);
	    if(insertType=="1"){
	        $("#InsetId_td").show();
            $("#afterInsertId").autocomplete({"required":true});
        }else{
            $("#InsetId_td").hide();
            $("#afterInsertId").autocomplete({"required":false});
        }
    }


    function setAfterInsertId_source(e,u) {
        $("#afterInsertId").autocomplete("setValue","");
        var job = $("#job").combobox("getValue");
        $.ajax({
            type : 'post',
            url : "${ctx}/zbgl/rygl/getCommonbox",
            data: {
                "cusNumber":cusNumber,"job":job
            },
            dataType : 'json',
            async: false,
            success : function(data) {
                $("#afterInsertId").autocomplete({"source":data});
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $.message({message:"操作失败！", cls:"error"});
            }
        });
       // $("#afterInsertId").autocomplete({"source": s_url});
    }

	function showStartAndEndDate(event,ui) {
       // var rgzt =ui.item.value;
        var rgzt = $("#ryzt").combobox( "getValue");
       // alert("rgzt="+rgzt);
        if(rgzt=="1"){
            $(".th_1").hide();
            $(".th_2").hide();
            $(".th_jd").hide();
        }else if(rgzt=="3"){
            $(".th_1").show();
            $(".th_2").hide();
            $(".th_jd").hide();
        }else if(rgzt=="2"){
            $(".th_1").hide();
            $(".th_2").hide();
            $(".th_jd").show();
        }
        else{
            $(".th_1").show();
            $(".th_2").show();
            $(".th_jd").hide();
        }



        if(rgzt=='4'||rgzt=='5' ||rgzt=='6'){
            $("#rygl_startDate").datepicker({"required":true});
            $("#rygl_endDate").datepicker("option","required",false);
            $("#rygl_jdStartDate").datepicker({"required":false});
        }
        else if(rgzt=='2'){
            $("#rygl_startDate").datepicker({"required":false});
            $("#rygl_endDate").datepicker("option","required",false);
            $("#rygl_startDate").datepicker("setValue","");
            $("#rygl_endDate").datepicker("setValue","");
            $("#rygl_jdStartDate").datepicker({"required":true});
        }
        else if(rgzt=='3'){
            $("#rygl_startDate").datepicker({"required":true});
            $("#rygl_endDate").datepicker("option","required",false);
            $("#rygl_endDate").datepicker("setValue","");
            $("#rygl_jdStartDate").datepicker({"required":false});
        }else{
            $("#rygl_startDate").datepicker({"required":false});
            $("#rygl_endDate").datepicker("option","required",false);
            $("#rygl_startDate").datepicker("setValue","");
            $("#rygl_endDate").datepicker("setValue","");
            $("#rygl_jdStartDate").datepicker({"required":false});
        }

    }


	function getAgeByCsrq() {
		var csrq = $("#csrq").datepicker( "getDateValue" );
	    var age = getAge(csrq);
		$("#age").val(age);
	}
	
	
    //改变单位下拉框的值 获取部门的下拉框值
    function change() {
        var jyId=  $("#dw").combobox( "getValue");
        if(jyId==4300){
        	 $.ajax({
                 type : 'post',
                 url : '${ctx}/zbgl/rygl/listSjzzxx',
                 dataType : 'json',
                 success : function(data) {
                     $("#dept").combobox( "loadData", data);
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
                     "cusNumber" : jyId
                 },
                 dataType : 'json',
                 success : function(data) {
                     $("#dept").combobox( "loadData", data);
                 },
                 error : function(XMLHttpRequest, textStatus, errorThrown) {
                     $.loading("hide");
                     $.message({message:"操作失败！", cls:"error"});
                 }
             });
        }
    }

	//根据出生日期获取年龄格式YYYY-MM-dd
    function getAge(strAge) {
        var birArr = strAge.split("-");
        var birYear = birArr[0];
        var birMonth = birArr[1];
        var birDay = birArr[2];

        d = new Date();
        var nowYear = d.getFullYear();
        var nowMonth = d.getMonth() + 1; //记得加1
        var nowDay = d.getDate();
        var returnAge;

        if (birArr == null) {
            return false
        };
        var d = new Date(birYear, birMonth - 1, birDay);
        if (d.getFullYear() == birYear && (d.getMonth() + 1) == birMonth && d.getDate() == birDay) {
            if (nowYear == birYear) {
                returnAge = 0; // 
            } else {
                var ageDiff = nowYear - birYear; // 
                if (ageDiff > 0) {
                    if (nowMonth == birMonth) {
                        var dayDiff = nowDay - birDay; // 
                        if (dayDiff < 0) {
                            returnAge = ageDiff - 1;
                        } else {
                            returnAge = ageDiff;
                        }
                    } else {
                        var monthDiff = nowMonth - birMonth; // 
                        if (monthDiff < 0) {
                            returnAge = ageDiff - 1;
                        } else {
                            returnAge = ageDiff;
                        }
                    }
                } else {
                    return -1; //返回-1 表示出生日期输入错误 晚于今天
                }
            }
            return returnAge;
        } else {
        	//格式错误输出 -2
            return -2;
        }
    }
    
    
</script>
