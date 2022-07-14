<%@page import="com.ces.prison.common.constants.GroupKeyConst" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade" %>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div style="height: 100%; margin: 0px 10px;">
    <!-- 查询条件 -->
    <cui:form id="formId_preplanManage_query">
        <table class="table">
            <tr>
                <th>预案来源：</th>
                <td>
                    <cui:combobox id="preplanSource" name="preplanSource" data="preplanSourceData"></cui:combobox>
                </td>
                <th>预案名称：</th>
                <td>
                    <cui:input id="preplanName" name="preplanName"></cui:input>
                </td>
                <td>
                    <cui:button label="查询" componentCls="btn-primary" onClick="search"/>
                    <cui:button label="重置" onClick="reset"></cui:button>
                </td>
            </tr>
        </table>
    </cui:form>
    <!-- 工具栏 -->
    <div style="margin-bottom: 4px">
        <cui:toolbar id="toolbarId_preplanManage" data="toolbar_preplanManage"></cui:toolbar>
    </div>
    <!-- 数据列表 -->
    <cui:grid id="gridId_preplanManage" rownumName="序号" rownumWidth="80" rownumbers="true" multiselect="true" width="auto" fitStyle="fill"
              colModel="gridId_preplanManage_colModelData">
        <cui:gridPager gridId="gridId_preplanManage"/>
    </cui:grid>
    <!-- 新增/修改 对话框 -->
    <cui:dialog id="dialogId_preplanManage" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true"
                autoDestroy="true">
    </cui:dialog>
</div>

<script>
    var c = {
        value : '',
        text : '请选择',
        selected : 'true'
    };
    // 预案数据来源
    var preplanSourceData = [
        {value:"0", text:"用户定义"},
        {value:"1", text:"系统同步"}
    ];
    preplanSourceData.unshift(c);
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
    var toolbar_preplanManage = [{
        "type": "button",
        "id": "btnId_add",
        "label": "增加",
        "onClick": "openDailog",
        "componentCls": "btn-primary"
    }, {
        "type": "button",
        "id": "btnId_edit",
        "label": "修改",
        "onClick": "openDailog",
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
    var gridId_preplanManage_colModelData = [{
        label: "id",
        name: "id",
        width: "70",
        hidden: true
    }, {
        label: "预案名称",
        name: "name",
        width: "70",
        align: "center"
    }, {
        label: "预案来源",
        name: "source",
        width: "85",
        align: "center",
        formatter : "convertCode",
        revertCode : true,
        formatoptions : {'data': preplanSourceData}
    }, {
        label: "创建时间",
        align: "center",
        name: "createTime",
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
    var buttons_preplanManage = [{
        text: "保存",
        id: "btnId_save",
        click: function () {
            saveOrUpdate();
        }
    }, {
        text: "重置",
        id: "btnId_cancel",
        click: function () {
            $("#formId_preplan_edit").form("reset");
            //$("#dialogId_preplanManage").dialog("close");
        }
    }];

    /**
     * 打开对话框
     * @param event
     * @param ui
     */
    function openDailog(event, ui) {
        var url = "";
        if (ui.id == "btnId_add") {
            url = "${ctx}/emergency/preplanManage/toEdit";
        } else if (ui.id == "btnId_edit") {
            var selarrrow = $("#gridId_preplanManage").grid("option", "selarrrow");
            if (selarrrow && selarrrow.length == 1) {
                url = "${ctx}/emergency/preplanManage/toEdit?id=" + selarrrow[0];
            } else {
                $.message({message: "请选择一条记录！", cls: "waring"});
                return;
            }
        }
        $("#dialogId_preplanManage").dialog({
            width: 400,
            height: 400,
            title: ui.label+"预案",
            url: url
        });
        $("#dialogId_preplanManage").dialog("open");
    }

    /**
     * 页面数据加载
     */
    function loadPage() {
        var url = "${ctx}/emergency/preplanManage/queryWithPage";
        var params = {};

        $('#gridId_preplanManage').grid('option', 'postData', params);
        $("#gridId_preplanManage").grid("reload", url);
    }

    /**
     * 条件查询
     */
    function search() {
        var formObj = $("#formId_preplanManage_query");

        var preplanSource = formObj.find("#preplanSource").combobox("getValue");// 预案来源
        var preplanName = formObj.find("#preplanName").textbox("getValue");// 预案名称

        var params = {};
        if (preplanSource) {
            params["preplanSource"] = preplanSource;
        }
        if (preplanName) {
            params["preplanName"] = preplanName;
        }
        $("#gridId_preplanManage").grid("option", "postData", params);
        $("#gridId_preplanManage").grid("reload");
    }

    /**
     * 重置查询条件
     */
    function reset() {
        var formObj = $("#formId_preplanManage_query");
        formObj.find("#preplanSource").combobox("setValue", "");
        formObj.find("#preplanName").textbox("setValue", "");
    }

    /**
     * 操作栏初始化
     */
    function operateFormatter(cellValue, options, rowObject) {
        var result = "<button class=\"ctrl-init ctrl-init-button coral-button coral-component coral-state-default coral-corner-all coral-button-text-only\" onClick= \"showCommandList('"+rowObject.id+"')\">指令列表</button>" ;

        return result;
    }

    /**
     * 保存或更新指挥调度应急预案
     */
    function saveOrUpdate() {
        var validFlag = $("#formId_preplan_edit").form("valid");
        if (!validFlag) {
            return;
        }

        var url = "${ctx}/emergency/preplanManage/saveOrUpdate.json";
        var formData = $("#formId_preplan_edit").form("formData");

        $.loading({text: "正在处理中，请稍后..."});

        var callBack = function (data) {
            if (data.success) {
                $.loading("hide");
                $("#dialogId_preplanManage").dialog("close");
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
        var selectedIdArray = $("#gridId_preplanManage").grid("option", "selarrrow");
        if (selectedIdArray != null && selectedIdArray.length > 0) {
            for(var i=0; i < selectedIdArray.length; i++) {
                selectedIds += selectedIdArray[i] + ",";
            }
            if(selectedIds != null && selectedIds != '') {
                selectedIds = selectedIds.substr(0, selectedIds.lastIndexOf(","));
            }
            $.confirm("确认删除？", function(r) {
                if(r) {
                    var url = "${ctx}/emergency/preplanManage/deleteByIds.json";
                    var params = {};
                    if(selectedIds) {
                        params["ids"] = selectedIds;
                    }

                    $.loading({text: "正在处理中，请稍后..."});

                    var callBack = function (data) {
                        if (data.success) {
                            $.loading("hide");
                            $.message({message: "删除成功！", cls: "success"});

                            // 刷新指令树
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
            $.message({message: "请选择待删除的指挥调度应急预案！", cls: "waring"});
        }
    }
</script>