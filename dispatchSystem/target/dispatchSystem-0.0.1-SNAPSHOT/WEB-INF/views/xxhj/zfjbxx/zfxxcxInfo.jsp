<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div class="personal-wrapper clearfix">
  <div class="left-wrapper">
    <div class="left-content">
      <div class="basic-info">
        <h3 class="title">基本信息</h3>
        <div class="zfxx-basic clearfix">
          <div class="zfxx-pic zfxx-box">
            <img src="${ctx}/static/bj-cui/img/dialog/fanren.png" />
          </div>
          <div class="zfxx-tip zfxx-box">
            <div>
              <span>编号:</span>
              <span></span>
            </div>
            <div>
              <span>姓名:</span>
              <span>张三</span>
            </div>
            <div>
              <span>监狱:</span>
              <span>第一监狱</span>
            </div>
            <div>
              <span>监区:</span>
              <span>三监区</span>
            </div>
          </div>
        </div>
        <div class="zfxx-detail">
          <ul>
            <li>
              <span>身份证号:</span>
              <span>4290041985***1858</span>
            </li>
            <li>
              <span>出生日期:</span>
              <span></span>
            </li>
            <li>
              <span>民族:</span>
              <span>维吾尔族</span>
            </li>
            <li>
              <span>入监日期:</span>
              <span>2018-05-14</span>
            </li>
            <li>
              <span>籍贯:</span>
              <span>浙江省杭州市下城区黄河路102号</span>
            </li>
            <li>
                <span>户籍:</span>
                <span>浙江省杭州市下城区黄河路102号</span>
            </li>
          </ul>
        </div>
      </div>
      <div class="basic-info">
        <h3 class="title">活动轨迹</h3>
        <div class="right-box">
            <div>
              <ul id="zfxxScrollR">
                <li>
                  <span>
                    <p> 上午</p>
                    <p> 08:00</p>
                  </span>
                  <span>一监区</span>
                  <span>门禁1</span>
                </li>
                <li>
                  <span>
                    <p> 上午</p>
                    <p> 08:00</p>
                  </span>
                  <span>一监区</span>
                  <span>门禁1</span>
                </li>
                <li>
                  <span>
                    <p> 上午</p>
                    <p> 08:00</p>
                  </span>
                  <span>一监区</span>
                  <span>门禁1</span>
                </li>
                <li>
                  <span>
                    <p> 上午</p>
                    <p> 08:00</p>
                  </span>
                  <span>一监区</span>
                  <span>门禁1</span>
                </li>
                <li>
                  <span>
                    <p> 上午</p>
                    <p> 08:00</p>
                  </span>
                  <span>一监区</span>
                  <span>门禁1</span>
                </li>
                <li>
                  <span>
                    <p> 上午</p>
                    <p> 08:00</p>
                  </span>
                  <span>一监区</span>
                  <span>门禁1</span>
                </li>
              </ul>
            </div>
          </div>
      </div>
    </div>
  </div>

  <div class="right-wrapper">
    <div class="basic-info">
      <div id="topChart" class="topChart"></div>
    </div>
    <div class="center-left">
      <h3>危险性评估</h3>
        <div id="centerLChart" class="centerLChart"></div>
    </div>
    <div class="center-right">
        <h3>分级处遇</h3>
          <div id="centerRChart" class="centerRChart"></div>
      </div>
    <div class="bottom-box clearfix">
        <div class="bottom-left">
          <h3>计分考核</h3>
          <div id="bottomLChart" class="bottomLChart chart-box"></div>
      </div>
      <div class="bottom-left">
          <h3>大帐消费</h3>
          <div id="bottomCChart" class="bottomCChart chart-box"></div>
      </div>
      <div class="bottom-left">
          <h3>亲情会见</h3>
          <div id="bottomRChart" class="bottomRChart chart-box"></div>
      </div>
    </div>
  </div>
</div>

<script>
$(function () {
	
	initZfxx();
});
</script>
