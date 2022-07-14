<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="cut" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<body>
<div id="carInfoShow" class="dialog">
    <cui:grid id="gridId_peopleInfo" fitStyle="width" height="635"
              singleselect="false" colModel="gridId_peopleInfo_colModelDate">
        <%--<cui:gridPager gridId="gridId_carInfo" />--%>
    </cui:grid>

</div>
</body>

<script type="text/javascript">
    var gridId_peopleInfo_colModelDate = [{
        name: "FR_TIME",
        label: "进出时间",
        align: "center",
        width: 120
    },{
        name: "FPD_ID_CARD_CODE",
        label: "证件号",
        align: "center",
        width: 120
    }, {
        name: "FPD_PEOPLE_NAME",
        label: "姓名",
        align: "center",
        width: 80
    }, {
        name: "FPD_SEX_INDC",
        label: "性别",
        align: "center",
        width: 60
    }, {
        name: "FPD_AGE",
        label: "年龄",
        align: "center",
        width: 60

    }, {
        name: "FPD_PHONE",
        label: "电话",
        align: "center",
        width: 120
    },{
        name: "FR_TYPE",
        label: "进出状态",
        align: "center",
        width: 50,
        formatter: function (cellvalue, options, rawObject) {
            if (cellvalue == '0') {
                return "<font color='#ff9900'>待进入</font>";
            } else if (cellvalue == '1') {
                return "<font color='green'>已进入</font>";
            } else if (cellvalue == '2') {
                return "<font color='red'>已离开</font>";
            } else {
                return "";
            }
        }
    }];

    $.parseDone(function () {
        console.log("${requestScope.flag}");
        $("#gridId_peopleInfo").grid("reload", "${ctx}/foreign/getPeopleList?flag=${requestScope.flag}");

    });
</script>
</html>