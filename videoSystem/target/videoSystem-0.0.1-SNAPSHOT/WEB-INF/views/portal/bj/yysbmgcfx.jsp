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
      <label>监区</label>
      <input id="blockName" />
    </div>
    <div class="col-md-6">
      <label>日期</label>
      <input type="text" id="startDate" />
      <span>至</span>
      <input type="text" id="endDate" />
      <button id="queryBtn">查询</button>
    </div>
  </div>
</div>
<div class="grid-wrapper">
  <img src="${ctx}/static/bj-cui/img/lishijilu/grid_bg.png" alt="列表的背景图" class="grid-bg">
  <div id="gridList2" class="grid-content"></div>
</div>
<script>
  //监区下拉框创建
  $("#blockName").combobox({
    data: [{
      value: "all", //隐藏值
      text: "全部监狱" //显示值
    }, {
      value: "one", //隐藏值
      text: "一监区" //显示值
    }],
    value: "all", //默认值
    onChange: function(e, ui) {
      //值改变时触发
    }
  })
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
      var start = $("#blockName").combobox("getValue"); //获取监区下拉框的值
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
  $("#gridList2").grid({
    colModel: [{
        label: "id",
        name: "id",
        align: "center",
        hidden: true
      }, //id列隐藏，该列的值必须唯一，不能重复
      {
        label: "日期",
        align: "center",
        name: "prisonDate"
      },
      {
        label: "监区",
        align: "center",
        name: "block"
      },
      {
        label: "服刑人员姓名",
        align: "center",
        name: "prisoner"
      },  
      {
        label: "敏感词分析",
        align: "center",
        name: "crime"
      },
      {
        label: "操作",
        align: "center",
        name: "toolbar",
        width: 300,
        formatter: function (e, ui) {
          return "<span class='grid-oper iconfont icon-signal'><span class='text'>语音下载</span></span>"+
          "<span class='grid-oper iconfont icon-check'><span class='text'>文本下载</span></span>"+
          "<span class='grid-oper iconfont icon-txt'><span class='text'>查看</span></span>";
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
      //绑定列表上的操作列点击事件
      $(".icon-signal", $(e.target)).off("click").on("click", function (e) {
        alert("语音下载")
      })
      $(".icon-check", $(e.target)).off("click").on("click", function (e) {
        alert("文本下载")
      })
      $(".icon-txt", $(e.target)).off("click").on("click", function (e) {
        //alert("查看")
        //中间区域变更为realTimeMonitoring
        refreshCenter("realtime")
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
      crime: "越狱、枪支、逃跑"
    },{ 
      id: "2",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "越狱、枪支、逃跑"
    },{ 
      id: "3",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "越狱、枪支、逃跑"
    },{ 
      id: "4",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "越狱、枪支、逃跑"
    },{ 
      id: "5",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "越狱、枪支、逃跑"
    },{ 
      id: "6",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "越狱、枪支、逃跑"
    },{ 
      id: "7",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "越狱、枪支、逃跑"
    },{ 
      id: "8",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "越狱、枪支、逃跑"
    },{ 
      id: "9",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "越狱、枪支、逃跑"
    },{ 
      id: "10",
      prisoner: "张三",
      block: "第一监区",
      prisonDate: "2019.1.16",
      crime: "越狱、枪支、逃跑"
    }]
  })
</script>
</body>

</html>
