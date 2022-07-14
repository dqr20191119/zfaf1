<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<style type="text/css">
    #rd_tabTools {
        box-sizing: border-box;
        padding-top: 2px;
    }

    #rd_tabTools div.table {
        width: 100%;
        height: 100%;
        display: table;
        padding-right: 5px;
    }

    #rd_tabTools div.table div.t-cell {
        display: table-cell;
        vertical-align: middle;
    }

    #rd_tabTools div.table div.t-cell span {
        margin-left: 10px;
    }

    #txtSearch {
        width: 160px;
        height: 21px;
    }

    #rd_dataPanel {
        float: left;
        width: 100%;
        height: 520px;
    }

    #rd_regPanel {
        border-right: 1px solid #999;
        background-color: rgba(245, 245, 245, 0.8);
    }

    #rd_dpttPanel {
        background-color: rgba(245, 245, 245, 0.8);
    }

    #rd_regPanel, #rd_dpttPanel {
        float: left;
        width: 50%;
        height: 100%;
        box-sizing: border-box;
        padding: 5px 5px;
        overflow: hidden;
    }

    #rd_regPanel .search, #rd_dpttPanel .search {
        box-sizing: border-box;
        width: 100%;
        height: 5%;
        padding: 0px 5px;
    }

    #rd_dpttScroll, #rd_regionScroll {
        width: 100%;
        height: 95%;
        overflow: hidden;
        overflow-y: scroll;
    }
</style>
<div id="rd_tabTools">
    <div class="table">
        <div class="t-cell">
            <cui:button componentCls="btn-success" label="保存" onClick="save()"></cui:button>
            <cui:button componentCls="btn-warning" label="关闭" onClick="closeDialog()"></cui:button>
        </div>
    </div>
</div>
<cui:tabs id="tabs" heightStyle="fill" onActivate="onActivateChange">
    <ul>
        <li><a href="#rd_tabView">查看</a></li>
        <li><a href="#rd_tabEdit">编辑</a></li>
    </ul>
    <div id="rd_tabView">
        <div id="rd_dataPanel">
            <div id="rd_regPanel">
                <input id="rd_txtSearchReg" class="search" placeholder="输入区域名称搜索定位...">
                <div id="rd_regionScroll">
                    <cui:tree id="rd_regTree" asyncEnable="true" keepParent="true"
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
            <div id="rd_dpttPanel">
                <input id="rd_txtSearchdptt" class="search" placeholder="输入部门名称搜索定位...">
                <div id="rd_dpttScroll">
                    <cui:tree id="rd_dpttTree" asyncEnable="true" keepParent="true"
                              asyncType="post" simpleDataEnable="true" onLoad=""
                              asyncUrl=""
                              checkable="false" chkboxType="zCheck" chkStyle="radio"
                              onDblClick="" onClick=""
                              onRightClick="" formatter=""
                              asyncAutoParam="id,name" rootNode="rootNode"
                              showRootNode="true">
                    </cui:tree>
                </div>
            </div>
        </div>
    </div>
    <div id="rd_tabEdit">
    </div>
</cui:tabs>


<script type="text/javascript">
    var rootNode = {name: jsConst.CUS_NAME + "组织部门", id: "", isParent: true, open: true};

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
    $('#rd_txtSearchReg').bind('keyup', function () {
        $("#rd_regTree").tree("filterNodesByParam", {
            name: this.value
        });
    });

    $('#rd_txtSearchdptt').bind('keyup', function () {
        $("#rd_dpttTree").tree("filterNodesByParam", {
            name: this.value
        });
    });

    function onActivateChange(event, ui){
        var index=$("#tabs").tabs("option","active");
        switch (index) {
            case 0:
                console.log("查看");
                loadCheck();
                $('#rd_dataPanel').appendTo($('#rd_tabView'));
                break;
            case 1:
                console.log("编辑");
                loadEdit();
                $('#rd_dataPanel').appendTo($('#rd_tabEdit'));
                break;
        }
    }


    function loadCheck() {

        $("#rd_dpttTree").tree("option", "checkable", true);
        $("#rd_dpttTree").tree("reload",
                "${ctx}/common/authsystem/findAllDeptForCombotree?cusNumber=" + jsConst.CUS_NUMBER);

        $("#rd_regTree").tree("option", "checkable", false);
        $("#rd_regTree").tree("reload",
                "${ctx}/common/areadevice/findForDepCombotree?cusNumber=" + jsConst.CUS_NUMBER);
    }

    function loadEdit() {

        $("#rd_dpttTree").tree("option", "checkable", true);
        $("#rd_dpttTree").tree("reload",
            "${ctx}/common/authsystem/findAllDeptForCombotree?cusNumber=" + jsConst.CUS_NUMBER);

        $("#rd_regTree").tree("option", "checkable", false);
        $("#rd_regTree").tree("reload",
            "${ctx}/common/areadevice/findForCombotree?cusNumber=" + jsConst.CUS_NUMBER + "&deviceType=0");
    }

    //单击事件，显示单击的区域父区域名称
    function als(event, id, node) {
        var nodes = $('#rd_dpttTree').tree("getNodeByParam", '监狱领导').children;
        //取消选中
        for (var i = 0; i < nodes.length; i++) {
            $('#rd_dpttTree').tree("checkNode", nodes[i], false);
            // nodes[i].chkDisabled = false;
        }
        $.ajax({
            type: 'post',
            url: '${ctx}/regionDepart/getDepartInfo.json',
            data: {'cusNumber': jsConst.CUS_NUMBER, 'areaId': node.id},
            dataType: 'json',
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    if(data!=null){
                        for (var j = 0; j < nodes.length; j++) {
                            if (data[i].adrDprtmntId == nodes[j].id) {
                                $('#rd_dpttTree').tree("checkNode", nodes[j], true);
                            }/*else{
                                nodes[j].chkDisabled = true;
                            }*/
                        }
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
        var regionNode = $('#rd_regTree').tree("getSelectedNodes");
        if (regionNode.length == 0) {
            $.alert({
                message: "请选择需要操作的区域！",
                title: "提示信息",
                iframePanel: true
            });
            return;
        }

        if (regionNode[0].isParent == true) {
            $.alert({
                message: "不能关联父级楼房区域，请选择需要关联的楼层！",
                title: "提示信息",
                iframePanel: true
            });
            return;
        }
        //选择部门
        var departNodes = $('#rd_dpttTree').tree("getCheckedNodes");
        var objArry = new Array();
        if (departNodes.length > 0) {
            for (var i = 0; i < departNodes.length; i++) {
                var obj = {};
                obj.adrDprtmntId = departNodes[i].id;
                obj.adrDprtmntName = departNodes[i].name;
                obj.adrCusNumber = jsConst.CUS_NUMBER;
                obj.adrAreaId = regionNode[0].id;
                obj.adrAreaName = regionNode[0].name;
                objArry.push(obj);
            }
        } else {
            var obj = {};
            obj.adrCusNumber = jsConst.CUS_NUMBER;
            obj.adrAreaId = regionNode[0].id;
            obj.adrAreaName = regionNode[0].name;
            objArry.push(obj);
        }
        $.ajax({
            type: 'post',
            url: '${ctx}/regionDepart/save',
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