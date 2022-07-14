<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="form-wrapper">
  <div class="row">
    <div class="col-md-3">
      <label>服刑人员姓名</label>
      <input type="text" id="prisonName" />
    </div>
    <div class="col-md-6">
      <label>日期</label>
      <input type="text" id="startDate" />
      <span>至</span>
      <input type="text" id="endDate" />
      <button id="queryBtn">查询</button>
    </div>
    <div class="col-md-3 right-button">
      <button id="historyList">生成回放记录表</button>
    </div>
  </div>
</div>
<div class="grid-wrapper">
  <img src="${ctx}/static/bj-cui/img/lishijilu/grid_bg.png" alt="列表的背景图" class="grid-bg">
  <div id="gridList" class="grid-content"></div>
</div>
<script>
  //服刑人员姓名输入框创建
  $("#prisonName").textbox({})
  //开始日期框创建
  $("#startDate").datepicker({

  })
  //结束日期框创建
  $("#endDate").datepicker({
    startDateId: "startDate" //限制日期起始时间
  })
  //查询按钮创建
  $("#queryBtn").button({
    onClick: function (e, ui) {
      //点击按钮功能
      var start = $("#startDate").datepicker("getValue"); //获取开始日期的值
      var end = $("#endDate").datepicker("getValue"); //获取结束日期的值
      var name = $("#prisonName").textbox("getValue");//服刑人员输入框的值
    },
    componentCls: "query-button",
    icons: "iconfont icon-query"
  })
  //生成回放记录表
  $("#historyList").button({
    onClick: function (e, ui) {
      //点击按钮功能
    },
    componentCls: 'history-button',
    icons: "iconfont icon-list"
  })
  //列表创建
  $("#gridList").grid({
    colModel: [{
        label: "id",
        name: "id",
        align: "center",
        hidden: true
      }, //id列隐藏，该列的值必须唯一，不能重复
      {
        label: "服刑人员",
        align: "center",
        name: "prisoner"
      },
      {
        label: "监区",
        align: "center",
        name: "block"
      },
      {
        label: "入监日期",
        align: "center",
        name: "prisonDate"
      },
      {
        label: "罪名",
        align: "center",
        name: "crime"
      },
      {
        label: "处遇级别",
        align: "center",
        name: "level"
      },
      {
        label: "最近通话时间",
        align: "center",
        name: "talkTime"
      },
      {
        label: "责任干警",
        align: "center",
        name: "police"
      },
      {
        label: "合计通话次数",
        align: "center",
        name: "time",
        formatter: function (e, ui) {
          return "<span class='total-time'>" + e + "</span>";
        }
      }
    ],
    componentCls: "grid-list",
    height: 600,
    fitStyle: "width",
    pager: true, //设置分页
    pagerTemplate: function () { //分页条自定义模板
      return "<span class='paginator-left'>{viewrecords}</span>" +
        "<span class='paginator-center'>{first}{prev}{links}{next}{last}</span>" +
        "<span class='paginator-right'>{pginput}</span>";
    },
    onComplete: function (e, ui) {
      //滚动条美化
      if ($(".coral-grid-rows-view").hasClass("mCustomScrollbar")) {
        $(".coral-grid-rows-view").mCustomScrollbar("destroy");
      }
      $(".coral-grid-rows-view").mCustomScrollbar({
        axis: "y",
        theme: "minimal-dark"
      })
       //绑定列表上的合计通话次数点击事件
      $(".total-time", $(e.target)).off("click").on("click", function (e) {
        alert("合计通话次数")
      })
    },
    rownumName: "序号",
    rownumbers: true, //自动排序
    rownumWidth: 150,
    altRows: true,
    datatype: "local", //设置为本地数据，如果要后台请求，"local"改成"json"
    data: [{ //本地数据，后台请求用url
      id: "1",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "2",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "3",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "4",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "5",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "6",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "7",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "8",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "11",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "12",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "13",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "14",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "15",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "16",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "17",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "18",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "19",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "20",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "21",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "22",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "23",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "24",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "25",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }, {
      id: "26",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "",
      level: "",
      talkTime: "2019.1.26",
      police: "张三",
      time: "10"
    }]
  })
</script>
</body>

</html>
