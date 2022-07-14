<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<style type="text/css">
    #vp_tabTools {
        box-sizing: border-box;
        padding-top: 2px;
    }

    #vp_tabTools div.table {
        width: 100%;
        height: 100%;
        display: table;
        padding-right: 5px;
    }

    #vp_tabTools div.table div.t-cell {
        display: table-cell;
        vertical-align: middle;
    }

    #vp_tabTools div.table div.t-cell span {
        margin-left: 10px;
    }

    #txtSearch {
        width: 160px;
        height: 21px;
    }

    #vp_dataPanel {
        float: left;
        width: 100%;
        height: 520px;
        overflow: hidden;
    }

    #vp_regPanel {
        border-right: 1px solid #999;
        background-color: rgba(245, 245, 245, 0.8);
    }

    #vp_dpttPanel {
        background-color: rgba(245, 245, 245, 0.8);
    }

    #vp_regPanel, #vp_dpttPanel {
        float: left;
        width: 50%;
        height: 100%;
        box-sizing: border-box;
        padding: 5px 5px;
        overflow: hidden;
    }

    #vp_regPanel .search, #vp_dpttPanel .search {
        box-sizing: border-box;
        width: 100%;
        height: 5%;
        padding: 0px 5px;
    }

    #vp_dpttScroll, #vp_regionScroll {
        width: 100%;
        height: 95%;
        overflow: hidden;
        overflow-y: scroll;
    }
</style>
<div id="vp_tabTools">
    <div class="table">
        <div class="t-cell">
            <cui:button componentCls="btn-success" label="保存" onClick="save()"></cui:button>
            <cui:button componentCls="btn-warning" label="关闭" onClick="closeDialog()"></cui:button>
        </div>
    </div>
</div>
<cui:tabs id="vp_tabs" heightStyle="fill" onActivate="onActivateChange">
    <ul>
        <li><a href="#vp_tabView">查看</a></li>
        <li><a href="#vp_tabEdit">编辑</a></li>
    </ul>
    <div id="vp_tabView">
        <div id="vp_dataPanel">
            <div id="vp_regPanel">
                <input id="vp_txtSearchReg" class="search" placeholder="输入区域名称搜索...">
                <div id="vp_regionScroll">
                    <cui:tree id="vp_regTree" asyncEnable="true" keepParent="true"
                              asyncType="post" simpleDataEnable="true" onLoad=""
                              asyncUrl=""
                              checkable="false"
                              onDblClick="" onClick="als"
                              onRightClick="" formatter=""
                              asyncAutoParam="id,name,areaId" rootNode="true"
                              showLine="true" showRootNode="true">
                    </cui:tree>
                </div>
            </div>
            <div id="vp_dpttPanel">
                <input id="vp_txtSearchdptt" class="search" placeholder="输入部门人员名称搜索...">
                <div id="vp_dpttScroll">
                    <cui:tree id="vp_peopleTree" asyncEnable="true" keepParent="true"
                              asyncType="post" simpleDataEnable="true" onLoad=""
                              asyncUrl=""
                              checkable="false" chkboxType="zCheck" chkStyle="checkbox"
                              onDblClick="" onClick=""
                              onRightClick="" formatter=""
                              asyncAutoParam="id,name" rootNode="rootNode"
                              showRootNode="true">
                    </cui:tree>
                </div>
            </div>
        </div>
    </div>
    <div id="vp_tabEdit">
    </div>
</cui:tabs>


<script type="text/javascript">
    var rootNode = {name: jsConst.CUS_NAME + "部门人员", id: jsConst.CUS_NUMBER, isParent: true, open: true};

    var zCheck = {
        enable: true,
        chkboxType: {
            "Y": "s",
            "N": "s"
        }
    };

    $.parseDone(function () {
        loadCheck();
    });


    // 初始化搜索
    $('#vp_txtSearchReg').bind('keyup', function () {
        $("#vp_regTree").tree("filterNodesByParam", {
            name: this.value
        });
    });

    $('#vp_txtSearchdptt').bind('keyup', function () {
        $("#vp_peopleTree").tree("filterNodesByParam", {
            name: this.value
        });
    });

    function onActivateChange(event, ui) {
        var index = $("#vp_tabs").tabs("option", "active");
        switch (index) {
            case 0:
                console.log("查看");
                loadCheck();
                $('#vp_dataPanel').appendTo($('#vp_tabView'));
                break;
            case 1:
                console.log("编辑");
                loadEdit();
                $('#vp_dataPanel').appendTo($('#vp_tabEdit'));
                break;
        }
    }

    function loadCheck() {
        $("#vp_regTree").tree("option", "checkable", false);
        $("#vp_regTree").tree("reload",
            "${ctx}/viewPeople/findHavedAreatree?cusNumber=" + jsConst.CUS_NUMBER);

        $("#vp_peopleTree").tree("option", "checkable", true);
        $("#vp_peopleTree").tree("reload",
            "${ctx}/common/authsystem/findSyncDeptPoliceForCombotree?cusNumber=" + jsConst.CUS_NUMBER);

    }

    function loadEdit() {
        $("#vp_regTree").tree("option", "checkable", false);
        $("#vp_regTree").tree("reload",
            "${ctx}/common/areadevice/findForCombotree?cusNumber=" + jsConst.CUS_NUMBER + "&deviceType=0");

        $("#vp_peopleTree").tree("option", "checkable", true);
        $("#vp_peopleTree").tree("reload",
            "${ctx}/common/authsystem/findSyncDeptPoliceForCombotree?cusNumber=" + jsConst.CUS_NUMBER);

    }

    //单击事件，显示单击的区域父区域名称
    function als(event, id, node) {
        var nodes = $("#vp_peopleTree").tree("getChangeCheckedNodes");
        console.log(nodes);
        for(var i=0 ; i< nodes.length; i++) {
            $('#vp_peopleTree').tree("checkNode", nodes[i], false);
        }

        $.ajax({
            type: 'post',
            url: '${ctx}/viewPeople/getPeopleInfo.json',
            data: {'cusNumber': jsConst.CUS_NUMBER, 'areaId': node.id},
            dataType: 'json',
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    if (data != null) {
                        var checkNode = $('#vp_peopleTree').tree("getNodeByParam", 'id', data[i].vprPoliceId);
                        // console.log(checkNode);
                        $('#vp_peopleTree').tree("checkNode", checkNode, true);
                        // checkNode.chkDisabled = true;
                    }
                }
            }, error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(textStatus);
            }
        });
    }

    /*
     * 保存
     */
    function save() {
        //选中区域
        var regionNode = $('#vp_regTree').tree("getSelectedNodes");
        if (regionNode.length == 0) {
            $.alert({
                message: "请选择需要操作的区域！",
                title: "提示信息",
                iframePanel: true
            });
            return;
        }
        console.log(regionNode[0].isParent);
        if (regionNode[0].isParent == true) {
            $.alert({
                message: "不能关联楼房区域视角，请选择需要关联的楼层！",
                title: "提示信息",
                iframePanel: true
            });
            return;
        }

        //选择部门
        var departNodes = $('#vp_peopleTree').tree("getCheckedNodes");
        var objArry = new Array();
        if (departNodes.length > 0) {
            for (var i = 0; i < departNodes.length; i++) {
                var obj = {};
                if (departNodes[i].isParent == false) {
                    obj.vprPoliceId = departNodes[i].id;
                    obj.vprPoliceName = departNodes[i].name;
                    obj.vprCusNumber = jsConst.CUS_NUMBER;
                    obj.vprAreaId = regionNode[0].id;
                    obj.vprAreaName = regionNode[0].name;
                    objArry.push(obj);
                }
            }
            // console.log(objArry);
        } else {
            var obj = {};
            obj.vprCusNumber = jsConst.CUS_NUMBER;
            obj.vprAreaId = regionNode[0].id;
            obj.vprAreaName = regionNode[0].name;
            objArry.push(obj);
        }
        $.ajax({
            type: 'post',
            url: '${ctx}/viewPeople/save',
            data: {'objs': JSON.stringify(objArry)},
            dataType: 'json',
            success: function (data) {
                $.message({
                    message: "操作成功",
                    iframePanel: true
                });
            }, error: function (XMLHttpRequest) {
                alert(textStatus);
            }
        });
    }

    /*
     * 关闭
     */
    function closeDialog() {
        $("#dialog").dialog('close');
    }

</script>