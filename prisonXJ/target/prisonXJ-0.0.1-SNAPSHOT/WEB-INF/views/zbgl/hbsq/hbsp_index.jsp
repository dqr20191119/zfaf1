<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@ page import="com.cesgroup.prison.zbgl.rygl.util.RyCommon"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	UserBean user = AuthSystemFacade.getLoginUserInfo();
	request.setAttribute("cusNumber", user.getCusNumber());
%>
<div style="height: 100%; margin: 0px 10px;">
	<cui:form id="formId_hbsq_sp_query">
		<cui:input type='hidden' id ="spr" name="spr" />
		<cui:input type='hidden' id ="cusNumber" name="cusNumber" />
		<table class="table">
				<%-- <tr><th>单位 ：</th>
                    <td><cui:combobox  id="cusNumber"  name="cusNumber"  data="combox_dw"    componentCls="form-control"  ></cui:combobox></td> --%>
			<td style="display: none"><cui:input  name="zt" value="1" /></td>
			<th>申请人 ：</th>
			<td><cui:input id="sqr" name="sqr" ></cui:input></td>
			<th>申请时间 ：</th>
			<td><cui:datepicker componentCls="form-control"  id="startDate" name="startDate" timeFormat="yyyy-MM-dd"></cui:datepicker></td>
			<th>至</th>
			<td><cui:datepicker componentCls="form-control"  id="endDate" name="endDate"  timeFormat="yyyy-MM-dd"></cui:datepicker></td>
			</tr>
			<tr>
				<td>
					<cui:button label="查询" componentCls="btn-primary" onClick="search"/>
					<cui:button label="重置" onClick="reset"></cui:button>
				</td>
			</tr>
		</table>
	</cui:form>

	<div style="height: 40px;">
		<cui:toolbar id="toolbarId_hbsq_sp" data="toolbar_hbsq_sp"></cui:toolbar>
	</div>

	<cui:grid id="gridId_hbsq_sp" rownumbers="true" multiselect="true" width="auto" fitStyle="fill"
	>
		<cui:gridCols>
			<cui:gridCol name="id" hidden="true" ></cui:gridCol>
			<cui:gridCol name="cusNumber" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combox_dw
				}"  align="center" >单位</cui:gridCol>
			<%-- <cui:gridCol name="deptNumber" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combobox_bm
				}" align="center">部门</cui:gridCol> --%>
			<cui:gridCol name="zbOrder" align="center" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combox_order
				}" >换班人员值班班次</cui:gridCol>
			<cui:gridCol name="dutyDate" align="center">换班人原值班时间</cui:gridCol>
			<cui:gridCol name="hbr" align="center" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combox_ry
				}"  >换班人</cui:gridCol>
			<cui:gridCol name="tdr" align="center" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combox_ry
				}" >替代人</cui:gridCol>
			<cui:gridCol name="tdrzbOrder" align="center" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combox_order
				}" >替代人原班次</cui:gridCol>
			<cui:gridCol name="hbDate" align="center" >换班时间</cui:gridCol>
			<cui:gridCol name="zt" align="center"  formatter="convertCode" revertCode="true" formatoptions="{
				'data': combobox_tjzt
				}" >提交状态</cui:gridCol>
			<cui:gridCol name="spyj" align="center" formatter="convertCode" revertCode="true" formatoptions="{
				'data': combobox_sqyj
				}" >审批意见</cui:gridCol>
		</cui:gridCols>
		<cui:gridPager gridId="gridId_hbsq_sp"/>
	</cui:grid>
	<cui:dialog id="dialogId_hbsq_sp" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true" autoDestroy="true">
	</cui:dialog>
</div>

<script>
    var cusNumber = jsConst.CUS_NUMBER;
    var USER_LEVEL = jsConst.USER_LEVEL;
    var userName = jsConst.USER_NAME;
    var combobox_bm = <%=AuthSystemFacade.getAllChildrenOrgJsonInfoByOrgKey(null)%>;
    $.parseDone(function() {
        $("#gridId_hbsq_sp").grid("reload","${ctx}/zbgl/hbsq/searchData?cusNumber=" + cusNumber+"&type=1&zt=1");
        //$("#spr").textbox("setValue",userName);  // warning 此处改为拥有此模块的用户可以审批所有记录 2021-12-14
        $("#cusNumber").textbox("setValue",cusNumber);
    });

    var toolbar_hbsq_sp = [{
        "type" : "button",
        "id" : "btnId_sp",
        "label" : "审批",
        "onClick" : "openDailog",
        "componentCls" : "btn-primary"
    }];

    var combobox_tjzt = [
        {value:"0",text:"未提交"},
        {value:"1",text:"已提交"}];
    var combox_ry;
    if(USER_LEVEL==1){
        combox_ry =<%= RyCommon.getCommonbox2(request.getAttribute("cusNumber").toString())%>;
    }else{
        combox_ry =<%= RyCommon.getCommonbox5(request.getAttribute("cusNumber").toString())%>;
    }

    var combox_dw =<%= AuthSystemFacade.getHnAllJyJsonInfo()%>;
    var combox_order =<%= RyCommon.getBcglCommonbox(request.getAttribute("cusNumber").toString())%>;



    var combobox_sfty = [
        {value:"1",text:"同意"},
        {value:"2",text:"不同意"}];

    var combobox_sqyj=[
        {value:"0",text:"未审批"},
        {value:"1",text:"同意"},
        {value:"2",text:"不同意"}
    ];





    function openDailog(event, ui) {

        var url;
        if(ui.id == "btnId_sp") {

            var selarrrow = $("#gridId_hbsq_sp").grid("option", "selarrrow");

            if(selarrrow && selarrrow.length == 1) {
                url = "${ctx}/zbgl/hbsq/toHbspEdit?id=" + selarrrow[0];
                var rowData  =$("#gridId_hbsq_sp").grid("getRowData", selarrrow[0]);
                if(rowData.spyj !='0'){//已经审批
                    $.message({message:"已经审批,不能重复审批!", cls:"waring"});
                    return;
                }
            } else {
                $.message({message:"请选择一条记录！", cls:"waring"});
                return;
            }
        }

        $("#dialogId_hbsq_sp").dialog({
            width : 900,
            height : 700,
            title : ui.label,
            url : url
        });
        $("#dialogId_hbsq_sp").dialog("open");
    }
    /**
     * 判断是否是当班指挥长
     *
     * */
    /*function cheakIsDbZhz(rowData) {
        var dutyDate = rowData.dutyDate;
        var res= false;
        $.ajax({
            async:false,
            type : 'post',
            url : '${ctx}/zbgl/hbsq/checkIsZhz',
            data: {
                dutyDate : dutyDate
            },
            dataType : 'json',
            success : function(data) {
               if(data.code=='200') {
                   res = true;
               }
            },
            error : function(XMLHttpRequest, textStatus, errorThrown) {
                $.loading("hide");
                $.message({message:"操作失败！", cls:"error"});
            }
        });

        return res;
    }*/



    function search() {
        var formData = $("#formId_hbsq_sp_query").form("formData");
        $("#gridId_hbsq_sp").grid("option", "postData", formData);
        $("#gridId_hbsq_sp").grid("reload","${ctx}/zbgl/hbsq/searchData");
    }

    function reset() {

        $("#formId_hbsq_sp_query").form("reset");
    }





    //文件删除成功时触发
    /* function f_onClearQueue(e,ui){
        console.log("文件删除成功");
        $("#uploaderId_attachment_affixIds").val("");
    } */


</script>