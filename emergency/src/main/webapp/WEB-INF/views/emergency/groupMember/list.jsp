<%@page import="com.ces.prison.common.constants.GroupKeyConst" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade" %>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div style="height: 100%; margin: 0px 10px;">
    <!-- 查询条件 -->
    <cui:form id="formId_groupMemberManage_query">
        <table class="table">
            <tr>
                <th>呼叫人员：</th>
                <td>
                    <cui:input id="memberName" name="memberName"></cui:input>
                </td>
                <th>呼叫号码：</th>
                <td>
                    <cui:input id="callNo" name="callNo"></cui:input>
                </td>
                <td>
                    <cui:button label="查询" componentCls="btn-primary" onClick="search"/>
                    <cui:button label="重置" onClick="reset"></cui:button>

                    <!-- 隐藏属性 -->
                    <cui:input id="groupId" name="groupId" type="hidden"></cui:input>
                </td>
            </tr>
        </table>
    </cui:form>
    <!-- 工具栏 -->
    <div style="height: 40px;">
        <cui:toolbar id="toolbarId_groupMemberManage" data="toolbar_groupMemberManage"></cui:toolbar>
    </div>
    <!-- 数据列表 -->
    <cui:grid id="gridId_groupMemberManage" rownumWidth="80" rownumName="序号" rownumbers="true" multiselect="true" width="auto" fitStyle="fill"
              colModel="gridId_groupMemberManage_colModelData">
        <cui:gridPager gridId="gridId_groupMemberManage"/>
    </cui:grid>
    <!-- 新增/修改 对话框 -->
    <cui:dialog id="dialogId_groupMemberManage" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true"
                autoDestroy="true" buttons="buttons_groupMemberManage">
    </cui:dialog>
</div>

<script>
    // 预案数据来源
    var groupMemberSourceData = [
        {value:"", text:"请选择","selected":true},
        {value:"0", text:"用户定义"},
        {value:"1", text:"系统同步"}
    ];
    /**
     * 页面加载完成后执行
     */
    $.parseDone(function () {
        // 页面数据加载
        loadPage();
    });

    /**
     * 工具栏
     * @type {*[]}
     */
    var toolbar_groupMemberManage = [{
        "type": "button",
        "id": "btnId_add",
        "label": "增加",
        "onClick": "openGroupMemberDailog",
        "componentCls": "btn-primary"
    }, {
        "type": "button",
        "id": "btnId_edit",
        "label": "修改",
        "onClick": "openGroupMemberDailog",
        "componentCls": "btn-primary"
    }, {
        "type": "button",
        "id": "btnId_remove",
        "label": "删除",
        "onClick": "deleteByIds",
        "componentCls": "btn-primary"
    }];

    /**
     * 数据列表
     */
    var gridId_groupMemberManage_colModelData = [{
        label: "id",
        name: "id",
        width: "70",
        hidden: true
    }, {
        label: "梯队编号",
        name: "groupId",
        width: "70",
        hidden: true
    }, {
        label: "呼叫人员编号",
        name: "memberId",
        width: "70",
        hidden: true
    }, {
        label: "梯队名称",
        name: "groupName",
        width: "70",
        align: "center"
    }, {
        label: "呼叫人员",
        name: "memberName",
        width: "70",
        align: "center"
    }, {
        label: "呼叫号码",
        name: "callNo",
        width: "70",
        align: "center"
    }, {
        label: "数据来源",
        name: "source",
        width: "85",
        align: "center",
        formatter : "convertCode",
        revertCode : true,
        formatoptions : {'data': groupMemberSourceData}
    }, {
        label: "更新时间",
        align: "center",
        name: "updateTime",
        width: "85"
    }/*, {
        label: "操作",
        name: "operate",
        align: "center",
        width: "70",
        formatter: "operateFormatter"
    }*/];

    /**
     * 新增、更新对话框按钮
     */
    var buttons_groupMemberManage = [{
        text: "保存",
        id: "btnId_save",
        click: function () {
            saveOrUpdate();
        }
    }, {
        text: "重置",
        id: "btnId_cancel",
        click: function () {
            initEmerGroupMember();
        }
    }];

    /**
     * 打开对话框
     * @param event
     * @param ui
     */
    function openGroupMemberDailog(event, ui) {
        var groupId = $("#formId_groupMemberManage_query").find("#groupId").textbox("getValue");// 工作组编号
        var url = "";
        if (ui.id == "btnId_add") {
            url = "${ctx}/emergency/groupMemberManage/toEdit?groupId=" + groupId;
        } else if (ui.id == "btnId_edit") {
            var selarrrow = $("#gridId_groupMemberManage").grid("option", "selarrrow");
            if (selarrrow && selarrrow.length == 1) {
                var rowData = $("#gridId_groupMemberManage").grid("getRowData", selarrrow[0]);
                url = "${ctx}/emergency/groupMemberManage/toEdit?id=" + rowData.id + "&groupId=" + rowData.groupId + "&memberId=" + rowData.memberId;
            } else {
                $.message({message: "请选择一条记录！", cls: "waring"});
                return;
            }
        }
        $("#dialogId_groupMemberManage").dialog({
            width: 400,
            height: 600,
            title: ui.label+"工作组成员",
            url: url
        });
        $("#dialogId_groupMemberManage").dialog("open");
    }

    /**
     * 页面数据加载
     */
    function loadPage() {
        var url = "${ctx}/emergency/groupMemberManage/queryWithPage";
        var params = {};
        var groupId = "${groupId}";
        if(groupId) {
            params["groupId"] = groupId;
            //将工作组ID赋予查询条件中的隐藏属性
            $("#formId_groupMemberManage_query").find("#groupId").textbox("setValue", groupId);
        }
        $('#gridId_groupMemberManage').grid('option', 'postData', params);
        $("#gridId_groupMemberManage").grid("reload", url);
    }

    /**
     * 条件查询
     */
    function search() {
        var formObj = $("#formId_groupMemberManage_query");

        var groupId = formObj.find("#groupId").textbox("getValue");// 工作组编号
        var callNo = formObj.find("#callNo").textbox("getValue");// 呼叫号码
        var memberName = formObj.find("#memberName").textbox("getValue");// 呼叫成员

        var params = {};
        if (groupId) {
            params["groupId"] = groupId;
        }
        if (memberName) {
            params["memberName"] = memberName;
        }
        if (callNo) {
            params["callNo"] = callNo;
        }
        $("#gridId_groupMemberManage").grid("option", "postData", params);
        $("#gridId_groupMemberManage").grid("reload");
    }

    /**
     * 重置查询条件
     */
    function reset() {
        var formObj = $("#formId_groupMemberManage_query");
        formObj.find("#callNo").textbox("setValue", "");
        formObj.find("#memberName").textbox("setValue", "");
    }

    /**
     * 操作栏初始化
     */
    function operateFormatter(cellValue, options, rowObject) {
        var result = "<button class=\"ctrl-init ctrl-init-button coral-button coral-component coral-state-default coral-corner-all coral-button-text-only\" onClick= \"showDetail('"+rowObject.id+"')\">查看</button>" ;

        return result;
    }

    /**
     * 保存或更新指挥调度应急预案
     */
    function saveOrUpdate() {
        var validFlag = $("#formId_groupMember_edit").form("valid");
        if (!validFlag) {
            return;
        }

        var url = "${ctx}/emergency/groupMemberManage/saveOrUpdate.json";
        var formData = $("#formId_groupMember_edit").form("formData");

        $.loading({text: "正在处理中，请稍后..."});

        var callBack = function (data) {
            console.log("list data = " + JSON.stringify(data));
            if (data.success) {
                $.loading("hide");
                $("#dialogId_groupMemberManage").dialog("close");
                $.message({message: "操作成功！", cls: "success"});

                // 刷新列表
                search();
            } else {
                $.loading("hide");
                $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
            }
        };
        ajaxTodo(url, formData, callBack);
    }

    /**
     * 删除指挥调度应急预案
     */
    function deleteByIds() {
        var selectedIds = "";
        var selectedIdArray = $("#gridId_groupMemberManage").grid("option", "selarrrow");
        if (selectedIdArray != null && selectedIdArray.length > 0) {
            for(var i=0; i < selectedIdArray.length; i++) {
                selectedIds += selectedIdArray[i] + ",";
            }
            if(selectedIds != null && selectedIds != '') {
                selectedIds = selectedIds.substr(0, selectedIds.lastIndexOf(","));
            }
            $.confirm("确认删除？", function(r) {
                if(r) {
                    var url = "${ctx}/emergency/groupMemberManage/deleteByIds.json";
                    var params = {};
                    if(selectedIds) {
                        params["ids"] = selectedIds;
                    }

                    $.loading({text: "正在处理中，请稍后..."});

                    var callBack = function (data) {
                        if (data.success) {
                            $.loading("hide");
                            $.message({message: "删除成功！", cls: "success"});

                            // 刷新页面
                            search();
                        } else {
                            $.loading("hide");
                            $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
                        }
                    };
                    ajaxTodo(url, params, callBack);
                }
            });
        } else {
            $.message({message: "请选择待删除的应急小组成员！", cls: "waring"});
        }
    }

    /**
     * 显示详情
     * @param id
     */
    function showDetail(id) {

    }
</script>