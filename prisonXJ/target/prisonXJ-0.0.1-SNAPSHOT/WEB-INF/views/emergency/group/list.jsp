<%@page import="com.ces.prison.common.constants.GroupKeyConst" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade" %>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div style="height: 100%; margin: 0px 10px;">
    <!-- 查询条件 -->
    <div style="height: 10%">
    <cui:form id="formId_group_query">
        <table class="table">
            <tr>
                <th>工作组名称：</th>
                <td>
                    <cui:input id="groupName" name="groupName"></cui:input>
                </td>
                <td>
                    <cui:button label="查询" componentCls="btn-primary" onClick="search"/>
                    <cui:button label="重置" onClick="reset"></cui:button>

                    <!-- 隐藏属性 -->
                    <cui:input id="preplanId" name="preplanId" type="hidden"></cui:input>
                </td>
            </tr>
        </table>
    </cui:form>
    </div>
    <div style="height: 89%">
    <!-- 工具栏 -->
    <div id="div_toolbarId_group" style="height: 40px; ">
        <cui:toolbar id="toolbarId_group" data="toolbar_group"></cui:toolbar>
    </div>
    <!-- 数据列表 -->
    <cui:grid id="gridId_group" rownumName="序号" rownumWidth="80" rownumbers="true" multiselect="true" width="auto" fitStyle="fill"
              colModel="gridId_group_colModelData">
        <cui:gridPager gridId="gridId_group"/>
    </cui:grid>
    </div>
    <!-- 对话框 -->
    <cui:dialog id="dialogId_group" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true"
                autoDestroy="true" buttons="buttons_group">
    </cui:dialog>
    <!-- 工作组成员列表 -->
    <cui:dialog id="dialogId_groupMember_list" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true"
                autoDestroy="true"   onClose="closeGroupMemberDialog">
    </cui:dialog>
</div>

<script>
    // 预案工作组数据来源
    var groupSourceData = [
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
    var toolbar_group = [{
        "type": "button",
        "id": "btnId_add",
        "label": "新增",
        "onClick": "openGroupDailog",
        "componentCls": "btn-primary"
    }, {
        "type": "button",
        "id": "btnId_edit",
        "label": "修改",
        "onClick": "openGroupDailog",
        "componentCls": "btn-primary"
    }, {
        "type": "button",
        "id": "btnId_remove",
        "label": "删除",
        "onClick": "deleteByIds",
        "componentCls": "btn-primary"
    }];

    
    function closeGroupMemberDialog(event, ui) {
        refreshTree();
    }


    /**
     * 数据列表
     */
    var gridId_group_colModelData = [{
        label: "id",
        name: "id",
        width: "70",
        hidden: true
    }, {
        label: "preplanId",
        name: "preplanId",
        width: "70",
        hidden: true
    }, {
        label: "预案名称",
        name: "preplanName",
        width: "85",
        align: "center"
    }, {
        label: "工作组名称",
        name: "name",
        width: "85",
        align: "center"
    }, {
        label : "数据来源",
        name : "source",
        width : "75",
        align : "center",
        formatter : "convertCode",
        revertCode : true,
        formatoptions : {'data': groupSourceData}
    }, {
        label: "通知内容",
        name: "notice",
        width: "100",
        align: "left"
    }, {
        label: "创建时间",
        align: "center",
        name: "createTime",
        width: "85"
    }, {
        label: "操作",
        name: "operate",
        align: "center",
        width: "70",
        formatter: "operateFormatter"
    }];

    /**
     * 新增、更新对话框按钮
     */
    var buttons_group = [{
        text: "保存",
        id: "btnId_save",
        click: function () {
            saveOrUpdate();
        }
    }, {
        text: "取消",
        id: "btnId_cancel",
        click: function () {
            $("#dialogId_group").dialog("close");

        }
    }];

    /**
     * 打开对话框
     * @param event
     * @param ui
     */
    function openGroupDailog(event, ui) {
        console.log("emergency group list openGroupDailog ui = " + JSON.stringify(ui));
        var preplanId = $("#formId_group_query").find("#preplanId").textbox("getValue");//应急预案编号

        console.log("emergency group list openGroupDailog preplanId = " + preplanId);
        var url = "";
        if (ui.id == "btnId_add") {
            url = "${ctx}/emergency/groupManage/toEdit?preplanId=" + preplanId;
        } else if (ui.id == "btnId_edit") {
            var selarrrow = $("#gridId_group").grid("option", "selarrrow");
            if (selarrrow && selarrrow.length == 1) {
                url = "${ctx}/emergency/groupManage/toEdit?preplanId=" + preplanId + "&id=" + selarrrow[0];
            } else {
                $.message({message: "请选择一条记录！", cls: "waring"});
                return;
            }
        }

        console.log("emergency group list openGroupDailog url = " + url);
        $("#dialogId_group").dialog({
            width: 660,
            height: 330,
            title: ui.label,
            url: url
        });
        $("#dialogId_group").dialog("open");
    }

    /**
     * 页面数据加载
     */
    function loadPage() {
        // 应急预案编号
        var preplanId = "${preplanId}";

        // 工具栏隐藏与显示
        var preplanId = "${preplanId}";
        if(preplanId == '' || preplanId == 'root') {
            $("div[id='div_toolbarId_group']").hide();
        } else {
            $("div[id='div_toolbarId_group']").show();
        }

        // 分页查询
        var url = "${ctx}/emergency/groupManage/queryWithPage";
        var params = {};

        // 初始化查询条件
        if (preplanId) {
            params["preplanId"] = preplanId;
            $("form[id='formId_group_query']").find("#preplanId").textbox("setValue", preplanId);
        }

        $('#gridId_group').grid('option', 'postData', params);
        $("#gridId_group").grid("reload", url);
    }

    /**
     * 条件查询
     */
    function search() {
        var formObj = $("#formId_group_query");

        var preplanId = formObj.find("#preplanId").textbox("getValue");// 应急预案编号
        var groupName = formObj.find("#groupName").textbox("getValue");// 应急工作组名称

        var params = {};
        if (preplanId) {
            params["preplanId"] = preplanId;
        }
        if (groupName) {
            params["groupName"] = groupName;
        }
        $("#gridId_group").grid("option", "postData", params);
        $("#gridId_group").grid("reload");
    }

    /**
     * 重置查询条件
     */
    function reset() {
        var formObj = $("#formId_group_query");
        formObj.find("#groupName").textbox("setValue", "");
    }

    /**
     * 操作栏初始化
     */
    function operateFormatter(cellValue, options, rowObject) {
        var result = "<button class=\"ctrl-init ctrl-init-button coral-button coral-component coral-state-default coral-corner-all coral-button-text-only\" onClick= \"groupMemberMaintain('"+rowObject.id+"','" + rowObject.name + "')\">成员维护</button>" ;

        return result;
    }

    /**
     * 保存或更新指令
     */
    function saveOrUpdate() {
        var validFlag = $("#formId_group_edit").form("valid");
        if (!validFlag) {
            return;
        }

        var url = "${ctx}/emergency/groupManage/saveOrUpdate.json";
        var formData = $("#formId_group_edit").form("formData");

        $.loading({text: "正在处理中，请稍后..."});

        var callBack = function (data) {
            if (data.success) {
                $.loading("hide");
                $("#dialogId_group").dialog("close");
                $.message({message: "操作成功！", cls: "success"});

                // 刷新指令树
                refreshTree();
            } else {
                $.loading("hide");
                $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
            }
        };
        ajaxTodo(url, formData, callBack);
    }

    /**
     * 删除工作组
     */
    function deleteByIds() {
        var selectedIds = "";
        var selectedIdArray = $("#gridId_group").grid("option", "selarrrow");
        console.log("selectedIdArray = " + JSON.stringify(selectedIdArray));
        if (selectedIdArray != null && selectedIdArray.length > 0) {
            for(var i=0; i < selectedIdArray.length; i++) {
                selectedIds += selectedIdArray[i] + ",";
            }
            if(selectedIds != null && selectedIds != '') {
                selectedIds = selectedIds.substr(0, selectedIds.lastIndexOf(","));
            }
            $.confirm("确认删除？", function(r) {
                if(r) {
                    var url = "${ctx}/emergency/groupManage/deleteByIds.json";
                    var params = {};
                    if(selectedIds) {
                        params["ids"] = selectedIds;
                    }

                    $.loading({text: "正在处理中，请稍后..."});

                    var callBack = function (data) {
                        if (data.success) {
                            $.loading("hide");
                            $.message({message: "删除成功！", cls: "success"});

                            // 刷新应急预案树
                            refreshTree();
                        } else {
                            $.loading("hide");
                            $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
                        }
                    };
                    ajaxTodo(url, params, callBack);
                }
            });
        } else {
            $.message({message: "请选择待删除的工作组！", cls: "waring"});
        }
    }

    /**
     * 工作组成员维护
     *
     * @param groupId
     * @param groupName
     */
    function groupMemberMaintain(groupId, groupName) {
        console.log("==================== emergency/group/list.jsp groupMemberMaintain start ====================");
        var url = "${ctx}/emergency/groupMemberManage/openDialog?groupId=" + groupId;
        $("#dialogId_groupMember_list").dialog({
            width: 800,
            height: 600,
            title: groupName + "成员列表",
            url: url
        });
        $("#dialogId_groupMember_list").dialog("open");
        console.log("==================== emergency/group/list.jsp groupMemberMaintain end ====================");
    }
</script>