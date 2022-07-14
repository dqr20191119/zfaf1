<%@page import="com.ces.prison.common.constants.GroupKeyConst" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade" %>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div style="height: 100%; margin: 0px 10px;">
    <!-- 查询条件 -->
    <cui:form id="formId_step_query">
        <table class="table">
            <tr>
                <th>处置步骤名称：</th>
                <td>
                    <cui:input id="stepName" name="stepName"></cui:input>
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
    <!-- 工具栏 -->
    <div id="div_toolbarId_step" style="height: 40px; ">
        <cui:toolbar id="toolbarId_step" data="toolbar_step"></cui:toolbar>
    </div>
    <!-- 数据列表 -->
    <cui:grid id="gridId_step" rownumName="序号" rownumWidth="80" rownumbers="true" multiselect="true" width="auto" fitStyle="fill"
              colModel="gridId_step_colModelData">
        <cui:gridPager gridId="gridId_step"/>
    </cui:grid>
    <!-- 新增/修改对话框 -->
    <cui:dialog id="dialogId_step" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true"
                autoDestroy="true" buttons="buttons_step">
    </cui:dialog>
    <!-- 配置关联工作组对话框 -->
    <cui:dialog id="dialogId_stepGroup_maintain" autoOpen="false" resizable="false" iframePanel="true" reLoadOnOpen="true" modal="true"
                autoDestroy="true" buttons="buttons_stepGroup_maintain">
    </cui:dialog>
</div>

<script>
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
    var toolbar_step = [{
        "type": "button",
        "id": "btnId_add",
        "label": "新增",
        "onClick": "openStepDailog",
        "componentCls": "btn-primary"
    }, {
        "type": "button",
        "id": "btnId_edit",
        "label": "修改",
        "onClick": "openStepDailog",
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
    var gridId_step_colModelData = [{
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
        label: "处置步骤名称",
        name: "name",
        width: "85",
        align: "center"
    }, {
        label: "关联梯队",
        name: "groupName",
        width: "100",
        align: "center"
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
    var buttons_step = [{
        text: "保存",
        id: "btnId_save",
        componentCls:"btn-primary",
        click: function () {
            saveOrUpdate();
        }
    }/*, {
        text: "取消",
        id: "btnId_cancel",
        click: function () {
            $("#dialogId_step").dialog("close");
        }
    }*/];
    /**
     * 配置关联工作组对话框按钮
     */
    var buttons_stepGroup_maintain = [{
        text: "保存",
        id: "btnId_save",
        componentCls:"btn-primary",
        click: function () {
            saveOrUpdateStepGroup();
        }
    }/*, {
        text: "取消",
        id: "btnId_cancel",
        click: function () {
            $("#dialogId_stepGroup_maintain").dialog("close");
        }
    }*/];
    /**
     * 打开对话框
     * @param event
     * @param ui
     */
    function openStepDailog(event, ui) {
        var preplanId = $("#formId_step_query").find("#preplanId").textbox("getValue");//应急预案编号

        var url = "";
        if (ui.id == "btnId_add") {
            url = "${ctx}/emergency/stepManage/toEdit?preplanId=" + preplanId;
        } else if (ui.id == "btnId_edit") {
            var selarrrow = $("#gridId_step").grid("option", "selarrrow");
            if (selarrrow && selarrrow.length == 1) {
                url = "${ctx}/emergency/stepManage/toEdit?preplanId=" + preplanId + "&id=" + selarrrow[0];
            } else {
                $.message({message: "请选择一条记录！", cls: "waring"});
                return;
            }
        }
        $("#dialogId_step").dialog({
            width: 660,
            height: 330,
            title: ui.label,
            url: url
        });
        $("#dialogId_step").dialog("open");
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
            $("div[id='div_toolbarId_step']").hide();
        } else {
            $("div[id='div_toolbarId_step']").show();
        }

        // 分页查询
        var url = "${ctx}/emergency/stepManage/queryWithPage";
        var params = {};

        // 初始化查询条件
        if (preplanId) {
            params["preplanId"] = preplanId;
            $("form[id='formId_step_query']").find("#preplanId").textbox("setValue", preplanId);
        }

        $('#gridId_step').grid('option', 'postData', params);
        $("#gridId_step").grid("reload", url);
    }

    /**
     * 条件查询
     */
    function search() {
        var formObj = $("#formId_step_query");

        var preplanId = formObj.find("#preplanId").textbox("getValue");// 应急预案编号
        var stepName = formObj.find("#stepName").textbox("getValue");// 应急处置步骤名称

        var params = {};
        if (preplanId) {
            params["preplanId"] = preplanId;
        }
        if (stepName) {
            params["stepName"] = stepName;
        }
        $("#gridId_step").grid("option", "postData", params);
        $("#gridId_step").grid("reload");
    }

    /**
     * 重置查询条件
     */
    function reset() {
        var formObj = $("#formId_step_query");
        formObj.find("#stepName").textbox("setValue", "");
    }

    /**
     * 操作栏初始化
     */
    function operateFormatter(cellValue, options, rowObject) {
        var result = "<button class=\"ctrl-init ctrl-init-button coral-button coral-component coral-state-default coral-corner-all coral-button-text-only\" onClick= \"stepGroupMaintain('"+rowObject.preplanId+"','" + rowObject.id + "')\">关联梯队</button>" ;

        return result;
    }

    /**
     * 保存或更新指令
     */
    function saveOrUpdate() {
        var validFlag = $("#formId_step_edit").form("valid");
        if (!validFlag) {
            return;
        }

        var url = "${ctx}/emergency/stepManage/saveOrUpdate.json";
        var formData = $("#formId_step_edit").form("formData");

        $.loading({text: "正在处理中，请稍后..."});

        var callBack = function (data) {
            if (data.success) {
                $.loading("hide");
                $("#dialogId_step").dialog("close");
                $.message({message: "操作成功！", cls: "success"});

                // 刷新
                search();
            } else {
                $.loading("hide");
                $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
            }
        };
        ajaxTodo(url, formData, callBack);
    }

    /**
     * 删除处置步骤
     */
    function deleteByIds() {
        var selectedIds = "";
        var selectedIdArray = $("#gridId_step").grid("option", "selarrrow");
        if (selectedIdArray != null && selectedIdArray.length > 0) {
            for(var i=0; i < selectedIdArray.length; i++) {
                selectedIds += selectedIdArray[i] + ",";
            }
            if(selectedIds != null && selectedIds != '') {
                selectedIds = selectedIds.substr(0, selectedIds.lastIndexOf(","));
            }
            $.confirm("确认删除？", function(r) {
                if(r) {
                    var url = "${ctx}/emergency/stepManage/deleteByIds.json";
                    var params = {};
                    if(selectedIds) {
                        params["ids"] = selectedIds;
                    }

                    $.loading({text: "正在处理中，请稍后..."});

                    var callBack = function (data) {
                        if (data.success) {
                            $.loading("hide");
                            $.message({message: "删除成功！", cls: "success"});

                            // 刷新
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
            $.message({message: "请选择待删除的处置步骤！", cls: "waring"});
        }
    }

    /**
     * 处置步骤关联梯队维护
     *
     * @param stepId
     * @param stepName
     */
    function stepGroupMaintain(preplanId, stepId) {
        console.log("==================== emergency/step/list.jsp stepGroupMaintain start ====================");
        var url = "${ctx}/emergency/stepGroupManage/toEdit?preplanId=" + preplanId + "&stepId=" + stepId;
        $("#dialogId_stepGroup_maintain").dialog({
            width: 800,
            height: 600,
            title: "配置关联工作组",
            url: url
        });
        $("#dialogId_stepGroup_maintain").dialog("open");
        console.log("==================== emergency/step/list.jsp stepGroupMaintain end ====================");
    }

    /**
     * 保存或更新处置步骤与工作组关联关系
     */
    function saveOrUpdateStepGroup() {
        var url = "${ctx}/emergency/stepGroupManage/saveOrUpdate.json";
        var stepId = $("#formId_stepGroup_maintain").find("#stepId").textbox("getValue");

        var checkedGroupUl = $("div[id='divId_stepGroup_middle']").find("div[class='content']").find("ul:eq(0)");
        var checkedGroupLiArray = checkedGroupUl.children("li");
        var groupIds = "";
        $.each(checkedGroupLiArray, function(index, checkedGroupLi) {
            groupIds += $(checkedGroupLi).attr("value") + ",";
        });

        var params = {};
        if(stepId) {
            params["stepId"] = stepId;
        }
        if(groupIds != null && groupIds != '') {
            groupIds = groupIds.substr(0, groupIds.lastIndexOf(","));
        }
        params["groupIds"] = groupIds;
        $.loading({text: "正在处理中，请稍后..."});

        var callBack = function (data) {
            if (data.success) {
                $.loading("hide");
                $("#dialogId_stepGroup_maintain").dialog("close");
                $.message({message: "操作成功！", cls: "success"});

                // 刷新
                search();
            } else {
                $.loading("hide");
                $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
            }
        };
        ajaxTodo(url, params, callBack);
    }
</script>