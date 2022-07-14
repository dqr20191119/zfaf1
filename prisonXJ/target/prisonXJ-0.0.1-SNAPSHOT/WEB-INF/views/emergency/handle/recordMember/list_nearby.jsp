<%@page import="com.ces.prison.common.constants.GroupKeyConst" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade" %>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link href="${ctx}/static/emergency/style/style.css" rel="stylesheet" type="text/css"/>

<div id="divId_nearby_police">
    <div class="left_side">
        <div class="title"></div>
        <div class="content">
            <ul class="leftnav"></ul>
        </div>
        <div class="bottom">
            <cui:button label="确定" style="width:40%;" componentCls="btn-primary" onClick="saveOrUpdate()"></cui:button>
            <cui:button label="清空" style="width:40%;" onClick="clearCandidate()"></cui:button>
        </div>
    </div>
    <div class="right_side">
        <div class="content">
            <!-- 查询条件 -->
            <cui:form id="formId_nearby_query">
                <table class="table">
                    <tr>
                        <th>姓名：</th>
                        <td>
                            <cui:input id="memberName" name="memberName"></cui:input>
                        </td>
                        <td>呼叫号码：</td>
                        <td>
                            <cui:input id="callNo" name="callNo"></cui:input>
                        </td>
                        <td>
                            <cui:button label="查询" componentCls="btn-primary" onClick="search"/>
                            <cui:button label="重置" onClick="reset"></cui:button>
                        </td>
                    </tr>
                </table>
            </cui:form>
            <!-- 工具栏 -->
            <div style="height: 40px;">
                <cui:toolbar id="toolbarId_nearby" data="toolbar_nearby"></cui:toolbar>
            </div>

            <!-- 数据列表 -->
            <cui:grid id="gridId_nearby" rownumbers="true" multiselect="true" width="auto" fitStyle="fill"
                      colModel="gridId_nearby_colModelData">
                <cui:gridPager gridId="gridId_nearby"/>
            </cui:grid>
        </div>
    </div>
</div>

<!-- 隐藏域 -->
<div id="divId_nearby_police_hidden" style="display: none;">
    <!-- 应急处置记录的工作组编号 -->
    <cui:input id="recordGroupId" name="recordGroupId" type="hidden" value="${recordGroupId}"></cui:input>
    <!-- 应急处置记录编号 -->
    <cui:input id="recordId" name="recordId" type="hidden" value="${emerHandleRecord.id}"></cui:input>
    <!-- 应急处置记录的报警区域 -->
    <cui:input id="alarmAreaName" name="alarmAreaName" type="hidden" value="${emerHandleRecord.alarmAreaName}"></cui:input>
    <!-- 应急处置记录的报警单位编号 -->
    <cui:input id="alarmCusNumber" name="alarmCusNumber" type="hidden" value="${emerHandleRecord.cusNumber}"></cui:input>
</div>

<script>
    // 附近民警数据来源
    var nearbyDataCategoryData = [
        {value:"1", text:"人脸抓拍"},
        {value:"2", text:"狱内干警"}
    ];

    // 候选民警列表
    var candidatePoliceArray = [];

    /**
     * 页面加载完成后执行
     */
    $.parseDone(function () {
        // 左侧待选列表初始化
        initLeft();

        // 页面数据加载
        loadPage();
    });

    /**
     * 工具栏
     * @type {*[]}
     */
    var toolbar_nearby = [{
        "type": "button",
        "id": "btnId_add",
        "label": "添加至侯选",
        "onClick": "pushInCandidate",
        "componentCls": "btn-primary"
    }];

    /**
     * 数据列表
     */
    var gridId_nearby_colModelData = [{
        label: "id",
        name: "id",
        width: "70",
        hidden: true
    }, {
        label: "单位名称",
        name: "unitName",
        width: "70",
        hidden: false
    }, {
        label: "部门名称",
        name: "deptName",
        width: "70",
        hidden: false
    }, {
        label: "警号",
        name: "policeNo",
        width: "65",
        align: "center"
    }, {
        label: "姓名",
        name: "policeName",
        width: "70",
        align: "center"
    }, {
        label: "号码",
        name: "policeAffair",
        width: "70",
        align: "center"
    }];

    /**
     * 民警信息Bean
     */
    function PoliceInfoBean(unitName, deptName, policeNo, policeName, callNo) {
        this.unitName = unitName;
        this.deptName = deptName;
        this.policeNo = policeNo;
        this.policeName = policeName;
        this.callNo = callNo;
    }

    /**
     * 初始化左侧待选列表
     */
    function initLeft() {
        var recordGroupId = $("div[id='divId_nearby_police_hidden']").find("#recordGroupId").textbox("getValue");// 应急处置记录的工作组编号

        var url = "${ctx}/emergency/handle/recordMemberManage/queryByRecordGroupId.json";
        var params = {};
        if(preplanId) {
            params["recordGroupId"] = recordGroupId;
        }

        var callBack = function(data) {
            if (data.success) {
                // 将工作组成员存入 candidatePoliceArray
                if(data.obj != null && data.obj.length > 0) {
                    try {
                        $.each(data.obj, function(index, val) {
                            var policeInfoBean = new PoliceInfoBean(val.unitName, val.deptName, val.memberJobNo, val.memberName, val.callNo);

                            if(!isPoliceExists(policeInfoBean)) {
                                candidatePoliceArray.push(policeInfoBean);
                            }
                        });

                        // 刷新候选列表
                        refreshCandidate();
                    } catch (e) {
                        if (e.message != "break") {
                            console.error(e);
                        }
                    }
                }
            } else {
                $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * 页面数据加载
     */
    function loadPage() {
        var cusNumber = $("#alarmCusNumber").textbox("getValue");// 报警发生的单位编号
        var areaName = $("#alarmAreaName").textbox("getValue");// 报警发生的区域

        var params = {};
        if(cusNumber) {
            params["cusNumber"] = cusNumber;
        }
        if(areaName) {
            params["areaName"] = areaName;
        }

        var url = "${ctx}/emergency/handle/recordMemberManage/queryAllPoliceWithPage";
        $('#gridId_nearby').grid('option', 'postData', params);
        $("#gridId_nearby").grid("reload", url);
    }

    /**
     * 条件查询
     */
    function search() {
        var cusNumber = $("#alarmCusNumber").textbox("getValue");// 报警发生的单位编号
        var areaName = $("#alarmAreaName").textbox("getValue");// 报警发生的区域

        var formObj = $("#formId_nearby_query");
        var memberName = formObj.find("#memberName").textbox("getValue");// 民警姓名
        var callNo = formObj.find("#callNo").textbox("getValue");// 呼叫号码

        var params = {};
        if(cusNumber) {
            params["cusNumber"] = cusNumber;
        }
        if(areaName) {
            params["areaName"] = areaName;
        }
        if (memberName) {
            params["memberName"] = memberName;
        }
        if (callNo) {
            params["callNo"] = callNo;
        }
        $("#gridId_nearby").grid("option", "postData", params);
        $("#gridId_nearby").grid("reload");
    }

    /**
     * 重置查询条件
     */
    function reset() {
        var formObj = $("#formId_nearby_query");
        formObj.find("#memberName").textbox("setValue", "");//民警姓名
        formObj.find("#callNo").textbox("setValue", "");//呼叫号码
    }

    /**
     * 保存或更新指挥调度应急预案
     */
    function saveOrUpdate() {
        var recordGroupId = $("div[id='divId_nearby_police_hidden']").find("#recordGroupId").textbox("getValue");// 应急处置记录的工作组编号

        if (candidatePoliceArray == null || candidatePoliceArray.length <= 0) {
            $.messageQueue({message: "请选择待添加至工作组的民警", cls: "warning", iframePanel: true, type: "info"});
            return;
        }

        var url = "${ctx}/emergency/handle/recordMemberManage/saveOrUpdate.json";
        var params = {};

        if(recordGroupId) {
            params["recordGroupId"] = recordGroupId;
        }
        if(candidatePoliceArray) {
            params["memberDataJson"] = JSON.stringify(candidatePoliceArray);
        }

        // $.loading({text: "正在处理中，请稍后..."});

        var callBack = function (data) {
            if (data.success) {
                // $.loading("hide");
                $("#dialogId_groupMember_nearby").dialog("close");
                $.message({message: "操作成功！", cls: "success"});

                // 刷新列表
                // search();
            } else {
                // $.loading("hide");
                $.messageQueue({message: data.msg, cls: "warning", iframePanel: true, type: "info"});
            }
        };
        ajaxTodo(url, params, callBack);
    }

    /**
     * 添加至候选列表
     */
    function pushInCandidate() {
        var selectedIdArray = $("#gridId_nearby").grid("option", "selarrrow");

        if (selectedIdArray != null && selectedIdArray.length > 0) {
            $.confirm("确认加入候选？", function(r) {
                if(r) {
                    try {
                        $.each(selectedIdArray, function(index, selectedId){
                            var rowData = $("#gridId_nearby").grid("getRowData", selectedId);
                            var policeInfoBean = new PoliceInfoBean(rowData.unitName, rowData.deptName, rowData.policeNo, rowData.policeName, rowData.policeAffair);

                            if(!rowData.policeName) {
                                alert("选中的数据中民警姓名不能为空");
                                throw new CustomException("break");
                            }
                            if(!rowData.policeAffair) {
                                alert("选中的数据中呼叫号码不能为空");
                                throw new CustomException("break");
                            }
                            if(!isPoliceExists(policeInfoBean)) {
                                candidatePoliceArray.push(policeInfoBean);
                            }
                        });

                        // 刷新候选列表
                        refreshCandidate();
                    } catch (e) {
                        if (e.message != "break") {
                            console.error(e);
                        }
                    }
                }
            });
        } else {
            $.message({message: "请选择待加入候选的民警！", cls: "waring"});
        }
    }

    /**
     * 判断候选民警数组中，是否已有改民警信息
     */
    function isPoliceExists(policeInfo) {
        var flag = false;
        if(candidatePoliceArray != null && candidatePoliceArray.length > 0) {
            try {
                $.each(candidatePoliceArray, function(index, val) {
                    if(val.policeName == policeInfo.policeName && val.callNo == policeInfo.callNo) {
                        flag = true;
                        throw new CustomException("break");
                    }
                });
            } catch (e) {
                if (e.message != "break") {
                    console.error(e);
                }
            }
        }
        return flag;
    }

    /**
     * 刷新候选列表
     */
    function refreshCandidate() {
        // 候选民警元素容器
        var policeContainer = $("div[id='divId_nearby_police']").find("div[class='left_side']").find("div[class='content']").find("ul:eq(0)");
        // 民警信息模板
        var policeTemplate = '<li><div class="content-item"><span class="item-text"></span></div></li>';

        // 清空显示内容
        policeContainer.empty();

        // 填充候选民警
        if(candidatePoliceArray != null && candidatePoliceArray.length > 0) {
            var timers = [];
            $.each(candidatePoliceArray, function (index, obj) {
                // 工作组填充
                var policeTemplateClone = $(policeTemplate).clone();
                policeTemplateClone.attr({
                    id : obj.policeNo,
                    name : obj.policeName,
                    value : obj.callNo
                }).unbind('dblclick').bind('dblclick', function() {
                    event.stopPropagation();
                    if(timers[index]) {
                        clearTimeout(timers[index]);
                    }
                    // 候选民警双击事件
                    removePolice(this);
                }).unbind('click').bind('click', function() {
                    var liObj = this;
                    if(timers[index]) {
                        clearTimeout(timers[index]);
                    }

                    // 应急预案名称单击事件
                    timers[index] = setTimeout(function () {
                        console.log("候选民警单击");
                    }, 500);
                });

                // 显示名称赋值
                policeTemplateClone.find("div[class='content-item']").find("span[class='item-text']").html(obj.policeName);

                policeContainer.append(policeTemplateClone);
            });
        }
    }

    /**
     * 将民警从候选列表中删除
     */
    function removePolice(obj) {
        if(candidatePoliceArray != null && candidatePoliceArray.length > 0) {
            try {
                $.each(candidatePoliceArray, function(index, val) {
                    if (val.policeName == $(obj).attr("name") && val.callNo == $(obj).attr("value")) {
                        candidatePoliceArray.splice(index, 1);
                        throw new CustomException("break");
                    }
                });
            } catch (e) {
                if (e.message != "break") {
                    console.error(e);
                }
            } finally {
                $(obj).remove();
            }
        }
    }

    /**
     * 清空候选民警列表
     */
    function clearCandidate() {
        // 候选民警元素容器
        var policeContainer = $("div[id='divId_nearby_police']").find("div[class='left_side']").find("div[class='content']").find("ul:eq(0)");

        // 清空候选民警列表
        if(candidatePoliceArray != null && candidatePoliceArray.length > 0) {
            candidatePoliceArray.splice(0, candidatePoliceArray.length);
            // 清空显示内容
            policeContainer.empty();
        }
    }
</script>