<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>门户</title>
  <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/normalize.css">
  <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/iconfont/iconfont.css">
  <link rel="stylesheet" type="text/css" href="${ctx}/static/bj-cui/css/portals.css">
</head>

<body>
  <div class="container">
    <div class="header">
      <div class="logo">
        <img src="${ctx}/static/bj-cui/img/header_logo.png" alt="北京市监狱（戒毒）管理局智能安防平台">
      </div>
      <div class="header-content">
        <div class="header-item date">
          <span class="icon iconfont icon-datepiceker"></span>
          <span class="title">${dqrq}</span>
        </div>
        <div class="header-item">
          <span class="icon iconfont "></span>
          <span class="title" id="dqyh1">当前用户：</span>
        </div>
        <div class="header-item dropdow">
          <span class="icon iconfont icon-xialadown"></span>
        </div>
      </div>
    </div>
    <div class="center">
      <div class="tabs">
        <div class="chart"></div>
        <img src="${ctx}/static/bj-cui/img/active.png" alt="active" class="active-img">
        <div class="nav-wrapper">
          <ul class="nav">
            <li class="active nav-item" data-id="police">
              <span class="icon iconfont icon-police1"></span>
              <span class="title">民警执法平台</span>
            </li>
            <li class="nav-item" data-id="education">
              <span class="icon iconfont icon-education"></span>
              <span class="title">教育改造平台</span>
            </li>
            <li class="nav-item" data-id="secure" onclick="openZnafpt()">
              <span class="icon iconfont icon-police"></span>
              <span class="title">智能安防平台</span>
            </li>
            <li class="nav-item" data-id="team">
              <span class="icon iconfont icon-team"></span>
              <span class="title">队伍建设平台</span>
            </li>
            <li class="nav-item" data-id="desk">
              <span class="icon iconfont icon-ziyuan"></span>
              <span class="title">综合办公平台</span>
            </li>
          </ul>
        </div>
      </div>
      <div class="content-wrapper">
        <div class="content-slide">
          <div class="content">
            <ul class="system">
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-mobile.png" alt="移动警务系统"><span
                      class="title">移动警务系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-duty.png" alt="移动警务系统"><span
                      class="title">民警值班管理系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-letter.png" alt="移动警务系统"><span
                      class="title">罪犯信件管理系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-lightning.png" alt="移动警务系统"><span
                      class="title">刑罚执行全业务系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-work.png" alt="移动警务系统"><span
                      class="title">民警执勤系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-punish.png" alt="移动警务系统"><span
                      class="title">刑罚执行业务系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-cure.png" alt="移动警务系统"><span
                      class="title">罪犯医疗信息管理系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-watch.png" alt="移动警务系统"><span
                      class="title">监管改造管理系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-test.png" alt="移动警务系统"><span
                      class="title">计分考核系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-shopping.png" alt="移动警务系统"><span
                      class="title">大宗物品采购和帐务系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-consume.png" alt="移动警务系统"><span
                      class="title">狱内消费智能管理系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-call.png" alt="移动警务系统"><span
                      class="title">亲情电话管理系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-meeting.png" alt="移动警务系统"><span
                      class="title">会见管理系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content  disabled">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-data.png" alt="移动警务系统"><span
                      class="title">罪犯数据管理系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content  disabled">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-identity.png" alt="移动警务系统"><span
                      class="title">罪犯电子档案管理系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content  disabled">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-public.png" alt="移动警务系统"><span
                      class="title">狱务公开系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content  disabled">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-labour.png" alt="移动警务系统"><span
                      class="title">劳动改造综合管理系统</span></div>
                </div>
              </li>
            </ul>
          </div>
          <div class="content">
            <ul class="system">
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-sudent.png" alt="移动警务系统"><span
                      class="title">新生在线</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-psycho.png" alt="移动警务系统"><span
                      class="title">犯罪心理测评系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-remote.png" alt="移动警务系统"><span
                      class="title">视频帮教及远程会见系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-teach.png" alt="移动警务系统"><span
                      class="title">教育改造管理系统</span></div>
                </div>
              </li>
            </ul>
          </div>
          <div class="content">
            <ul class="system">
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-maintenance.png" alt="移动警务系统"><span
                      class="title">运维管理系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-certification.png" alt="移动警务系统"><span
                      class="title">安全认证支撑系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-backup.png" alt="移动警务系统"><span
                      class="title">容灾备份系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-resource.png" alt="移动警务系统"><span
                      class="title">物力资源智能管控系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-position.png" alt="移动警务系统"><span
                      class="title">押解监控定位系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-electricity.png" alt="移动警务系统"><span
                      class="title">监狱围墙电网管理系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-alarm.png" alt="移动警务系统"><span
                      class="title">周界报警管理系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-phone.png" alt="移动警务系统"><span
                      class="title">手机管控系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-gis.png" alt="移动警务系统"><span
                      class="title">GIS引擎</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-access.png" alt="移动警务系统"><span
                      class="title">门禁管理系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-evaluation.png" alt="移动警务系统"><span
                      class="title">罪犯危险性评估预警系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-drone.png" alt="移动警务系统"><span
                      class="title">无人机防控系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-patrol.png" alt="移动警务系统"><span
                      class="title">智能巡更系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-control.png" alt="移动警务系统"><span
                      class="title">指挥调度一体化系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-area.png" alt="移动警务系统"><span
                      class="title">区域管控系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-emergency.png" alt="移动警务系统"><span
                      class="title">应急指挥调度系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-voice.png" alt="移动警务系统"><span
                      class="title">智能语音识别系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-monitor.png" alt="移动警务系统"><span
                      class="title">视频监控智能分析系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-name.png" alt="移动警务系统"><span
                      class="title">视频点名系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-video-watch.png" alt="移动警务系统"><span
                      class="title">视频监控系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-risk.png" alt="移动警务系统"><span
                      class="title">监狱安全风险动态评估管控系统</span></div>
                </div>
              </li>
            </ul>
          </div>
          <div class="content">
            <ul class="system">
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-training.png" alt="移动警务系统"><span
                      class="title">民警教育培训管理系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-manage.png" alt="移动警务系统"><span
                      class="title">队伍管理信息系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-exam.png" alt="移动警务系统"><span
                      class="title">民警晋职晋衔廉政法规考试系统</span></div>
                </div>
              </li>
            </ul>
          </div>
          <div class="content">
            <ul class="system">
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-certificate.png" alt="移动警务系统"><span
                      class="title">数字证书认证系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-net.png" alt="移动警务系统"><span
                      class="title">北京市监狱管理局办公外网</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-gps.png" alt="移动警务系统"><span
                      class="title">警用车辆定位管理系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-clothes.png" alt="移动警务系统"><span
                      class="title">警服管理系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-equipment.png" alt="移动警务系统"><span
                      class="title">警务装备信息管理系统</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-comprehensive.png" alt="移动警务系统"><span
                      class="title">北京市监狱管理局综合管控平台</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-intranet.png" alt="移动警务系统"><span
                      class="title">北京市监狱管理局办公内网</span></div>
                </div>
              </li>
              <li class="system-item">
                <div class="system-content ">
                  <div class="system-text"><img class="system-img" src="${ctx}/static/bj-cui/img/icons/icon-cadre.png" alt="移动警务系统"><span
                      class="title">北京市监狱管理局干职信息采集系统</span></div>
                </div>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div>
  <script src="${ctx}/static/bj-cui/js/jquery-1.12.2.min.js"></script>
  <script src="${ctx}/static/bj-cui/js/portals.js"></script>
  <script src="${ctx}/static/system/common.js"></script>
  <script src="${ctx}/static/system/jsConst.js"></script>
  
  <script type="text/javascript">
  		$(function () {
			jsConst.basePath = "${ctx}/";

			initUserInfo1();
		});
		
		function showDqyh1() {
			$("#dqyh1").append("<span class=\"user\">" + jsConst.USER_NAME + "</span> （" + jsConst.DEPARTMENT_NAME + "）<br>警号：" + jsConst.POLICE_CODE);
		}
		
		function openZnafpt() {
			
			var url = "${ctx}/portal/bj/shouye";
			window.open(url, "_blank");
		}
  </script>
</body>

</html>