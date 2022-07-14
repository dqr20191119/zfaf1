<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="minjing-wrapper">
  <div class="left-wrapper item-wrapper">
    <div class="item">
      <div class="basic-info">
        <h2 class="title">基本信息</h2>
        <img src="${ctx}/static/bj-cui/img/gerenminjing/minjing.jpg" alt="民警照片" class="minjing-img">
        <ul class="basic-detail">
          <li>
            <span>姓名：</span>
            <span class="right-name">谭芳</span>
          </li>
          <li>
            <span>性别：</span>
            <span class="right-name">女</span>
          </li>
          <li>
            <span>年龄：</span>
            <span class="right-name">32</span>
          </li>
          <li>
            <span>户籍：</span>
            <span class="right-name">北京</span>
          </li>
        </ul>
        <ul class="more-detail">
          <li>
            <span>身份证号：</span>
            <span class="right-name">4290041985****1858</span>
          </li>
          <li>
            <span>电话号码：</span>
            <span class="right-name">151****5578</span>
          </li>
          <li>
            <span>地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：</span>
            <span class="right-name">北京市朝阳区***</span>
          </li>
        </ul>
      </div>
      <div class="job-info">
        <h2 class="title">岗位信息</h2>
        <ul class="job-list">
          <li class="list-item">
            <span class="iconfont icon-people">
            </span>
            <div class="job-detail">
              <p class="job-des">所内身份</p>
              <p class="job-name">职业律师</p>
            </div>
          </li>
          <li class="list-item">
            <span class="iconfont icon-people">
            </span>
            <div class="job-detail">
              <p class="job-des">职业类别</p>
              <p class="job-name">专职 </p>
            </div>
          </li>
          <li class="list-item">
            <span class="iconfont icon-people">
            </span>
            <div class="job-detail">
              <p class="job-des">执业证号</p>
              <p class="job-name">13106964346221386</p>
            </div>
          </li>
          <li class="list-item">
            <span class="iconfont icon-people">
            </span>
            <div class="job-detail">
              <p class="job-des">执业机构</p>
              <p class="job-name">上海市华城律师事务所</p>
            </div>
          </li>
          <li class="list-item">
            <span class="iconfont icon-people">
            </span>
            <div class="job-detail">
              <p class="job-des">资格证号</p>
              <p class="job-name">169974040889</p>
            </div>
          </li>
          <li class="list-item">
            <span class="iconfont icon-people">
            </span>
            <div class="job-detail">
              <p class="job-des">主管司法局</p>
              <p class="job-name">黄浦区司法局</p>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
  <div class="center-wrapper item-wrapper">
    <div class="duty-info">
      <div class="item">
        <h2 class="title">值班记录</h2>
        <div id="tabs123">
          <ul>
            <li><a href="#fragment-2">本周</a></li>
            <li><a href="#fragment-1">今日</a></li>
          </ul>
          <div id="fragment-2">
            <ul class="table-title">
              <li class="date">时间2</li>
              <li class="content">值班内容2</li>
              <li class="status">完成状态2</li>
            </ul>
            <ul class="table-content">
              <li>
                <span class="date">2019.01.27</span>
                <span class="content">查看监狱每日动态</span>
                <span class="status finish">已完成</span>
              </li>
              <li>
                <span class="date">2019.01.27</span>
                <span class="content">查看监狱每日动态</span>
                <span class="status finish">已完成</span>
              </li>
              <li>
                <span class="date">2019.01.27</span>
                <span class="content">查看监狱每日动态</span>
                <span class="status">未完成</span>
              </li>
            </ul>
          </div>
          <div id="fragment-1">
            <ul class="table-title">
              <li class="date">时间</li>
              <li class="content">值班内容</li>
              <li class="status">完成状态</li>
            </ul>
            <ul class="table-content">
              <li>
                <span class="date">2019.01.27</span>
                <span class="content">查看监狱每日动态</span>
                <span class="status finish">已完成</span>
              </li>
              <li>
                <span class="date">2019.01.27</span>
                <span class="content">查看监狱每日动态</span>
                <span class="status finish">已完成</span>
              </li>
              <li>
                <span class="date">2019.01.27</span>
                <span class="content">查看监狱每日动态</span>
                <span class="status">未完成</span>
              </li>
              <li>
                <span class="date">2019.01.27</span>
                <span class="content">查看监狱每日动态</span>
                <span class="status finish">已完成</span>
              </li>
              <li>
                <span class="date">2019.01.27</span>
                <span class="content">查看监狱每日动态</span>
                <span class="status finish">已完成</span>
              </li>
              <li>
                <span class="date">2019.01.27</span>
                <span class="content">查看监狱每日动态</span>
                <span class="status finish">已完成</span>
              </li>
              <li>
                <span class="date">2019.01.27</span>
                <span class="content">查看监狱每日动态</span>
                <span class="status">未完成</span>
              </li>
              <li>
                <span class="date">2019.01.27</span>
                <span class="content">查看监狱每日动态</span>
                <span class="status finish">已完成</span>
              </li>
              <li>
                <span class="date">2019.01.27</span>
                <span class="content">查看监狱每日动态</span>
                <span class="status finish">已完成</span>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <div class="communication-info">
      <div class="item">
        <h2 class="title">通讯融合</h2>
        <div class="form-wrapper">
          <form id="form1">
            <div class="row">
              <div id="radiolist1"></div>
            </div>
            <div class="row">
              <div class="col-md-3">
                发送信息
              </div>
              <div class="col-md-9">
                <textarea id="textarea1" name="textbox"></textarea>
              </div>
            </div>
          </form>
          <div class="button-wrapper">
            <button id="cancel">取消</button>
            <button id="send">发送</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="right-wrapper item-wrapper">
    <div class="active-info">
      <div class="item">
      <h2 class="title">活动轨迹</h2>
        <!--   <ul class="active-track">
          <li class="track-item">
            <div class="time">
              <p class="time-date">下午</p>
              <p class="date">08:00</p>
            </div>
            <div class="block">一监区</div>
            <div class="block-access">一监区门禁</div>
          </li>
          <li class="track-item">
            <div class="time">
              <p class="time-date">下午</p>
              <p class="date">08:00</p>
            </div>
            <div class="block">一监区</div>
            <div class="block-access">一监区门禁</div>
          </li>
          <li class="track-item">
            <div class="time">
              <p class="time-date">下午</p>
              <p class="date">08:00</p>
            </div>
            <div class="block">一监区</div>
            <div class="block-access">一监区门禁</div>
          </li>
          <li class="track-item">
            <div class="time">
              <p class="time-date">下午</p>
              <p class="date">08:00</p>
            </div>
            <div class="block">一监区</div>
            <div class="block-access">一监区门禁</div>
          </li>
          <li class="track-item">
            <div class="time">
              <p class="time-date">下午</p>
              <p class="date">08:00</p>
            </div>
            <div class="block">一监区</div>
            <div class="block-access">一监区门禁</div>
          </li>
          <li class="track-item">
            <div class="time">
              <p class="time-date">下午</p>
              <p class="date">08:00</p>
            </div>
            <div class="block">一监区</div>
            <div class="block-access">一监区门禁</div>
          </li>
        </ul> -->
      </div>
    </div>
    <div class="patrol-info">
      <div class="item">
        <h2 class="title">巡更记录</h2>
        <!-- <ul class="active-track">
          <li class="track-item">
            <div class="time">
              <p class="time-date">下午</p>
              <p class="date">08:00</p>
            </div>
            <div class="block">一监区</div>
            <div class="block-access">一监区门禁</div>
          </li>
          <li class="track-item">
            <div class="time">
              <p class="time-date">下午</p>
              <p class="date">08:00</p>
            </div>
            <div class="block">一监区</div>
            <div class="block-access">一监区门禁</div>
          </li>
          <li class="track-item">
            <div class="time">
              <p class="time-date">下午</p>
              <p class="date">08:00</p>
            </div>
            <div class="block">一监区</div>
            <div class="block-access">一监区门禁</div>
          </li>
          <li class="track-item">
            <div class="time">
              <p class="time-date">下午</p>
              <p class="date">08:00</p>
            </div>
            <div class="block">一监区</div>
            <div class="block-access">一监区门禁</div>
          </li>
          <li class="track-item">
            <div class="time">
              <p class="time-date">下午</p>
              <p class="date">08:00</p>
            </div>
            <div class="block">一监区</div>
            <div class="block-access">一监区门禁</div>
          </li>
          <li class="track-item">
            <div class="time">
              <p class="time-date">下午</p>
              <p class="date">08:00</p>
            </div>
            <div class="block">一监区</div>
            <div class="block-access">一监区门禁</div>
          </li>
        </ul> -->
      </div>
    </div>
  </div>
  <script>
    //对话框打开后(onOpen)再调用initMinjing。避免宽高计算错误
    initMinjing();
    function initMinjing() {
      $('#tabs123').tabs({
        heightStyle: 'fill',
        active: 1,
        onCreate: function (e) {
          var index = $(e.target).tabs("option", "active");
          var scrollTarget = $(".coral-tabs-panel", $(e.target)).eq(index);
          addScroll($(".table-content", scrollTarget))
        },
        onActivate: function (e, ui) {
          var index = $(e.target).tabs("option", "active");
          var scrollTarget = $(".coral-tabs-panel", $(e.target)).eq(index);
          addScroll($(".table-content", scrollTarget))
        }
      });
      addScroll(".active-track")
      //增加滚动条插件
      function addScroll(target) {
        if ($(target).hasClass("mCustomScrollbar")) {
          $(target).mCustomScrollbar("destroy");
        }
        $(target).mCustomScrollbar({
          axis: "y",
          theme: "minimal-dark"
        });
      }
      $("#form1").form();
      $('#radiolist1').radiolist({
        name: "telephone",
        value: "tel",
        data: [{
            value: "tel",
            text: "固定电话：134567890"
          },
          {
            value: "police",
            text: "警务通：134567890"
          },
          {
            value: "interphone",
            text: "对讲机：134567890"
          }
        ],
        column: 1 //每行放一个
      });
      $('#textarea1').textbox({
        componentCls: "form-control"
      });
      $("#cancel").button({
        onClick: function (e, ui) {
          //点击事件
        },
        componentCls: "custom-button"
      })
      $("#send").button({
        onClick: function (e, ui) {
          //点击事件
        },
        componentCls: "custom-button"
      })
    }
 </script>
  