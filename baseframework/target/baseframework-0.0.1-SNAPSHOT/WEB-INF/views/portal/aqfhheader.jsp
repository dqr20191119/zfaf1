<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
<!--

-->
#menu a{
	font-size: 20px;
}

#menu>li>ul a{
	font-size: 20px;
}
</style>

<div class="menu-m">
	<ul class="sjdw">
		<sec:authorize url="/zhdd/sy/afzhddpt/afzh/sjdw">
		<li class="sjdw-main">
			<a href="javascript:void(0);">
				<span class="iconfont icon-shijue1"></span>
				<span class="sjdw-name">视角定位</span>
				<span class="iconfont icon-xialadown"></span>
			</a>
			<iframe class="main-iframe" style="visibility: hidden;"></iframe>
			<!-- Begin modified by linhe 2018-1-9 -->
			<ul id="viewPositionMenu">
				<!-- <li> <a href="javascript:alert();centerDisplay('provMap');">1111111ces<b class="leftArrow"></b></a>
						<iframe class="sub-iframe"></iframe>
						<ul>
							<li onclick="centerDisplay('provMap')"><a href="javascript:void(0);">省局</a></li>
							<li onclick="centerDisplay('prisMap')"><a href="javascript:void(0);">监狱</a></li>
						</ul>
					</li> -->
			</ul>
			<!-- End modified by linhe 2018-1-9 -->
		</li>
		</sec:authorize>
	</ul>

	<ul id="menu">
			<li id="nav-home" onclick="openZnafpt()">
				<a href="javascript:void(0)">
					返回
				</a>
			</li>
		
		
		
		<%--<sec:authorize url="/zhdd/sy/zhdd/aqfh/aqfh">
		<li id="zhxt_wldctb-sp_"  onclick="openZhxtDialog_(event, 'aqfh')">
				<a href="javascript:void(0)">
					安全复核
				</a>
		</li>
        </sec:authorize>--%>
        <sec:authorize url="/zhdd/sy/zhdd/aqfh/zfcjfh">
            <li id="zfcjfh_sp"  onclick="openZhxtDialog_(event, 'zfcjfh')">
                <a id ="zhxt_zfcjfh_a" href="javascript:void(0)">罪犯出监复核</a>
            </li>
        </sec:authorize>
		<sec:authorize url="/zhdd/sy/zhdd/aqfh/wlryclfh">
		<li id="wlryclfh_sp" onclick="openZhxtDialog_(event, 'wlryclfh')">
			<a href="javascript:void(0)">外来人员车辆复核</a>
		</li>
		</sec:authorize>
		<%--<sec:authorize url="/zhdd/sy/zhdd/aqfh/wpjcfh">
		<li id="wpjcfh_sp" onclick="openZhxtDialog_(event, 'wpjcfh')">
			<a href="javascript:void(0)">物品进出复核</a>
		</li>
		</sec:authorize>
		<sec:authorize url="/zhdd/sy/zhdd/aqfh/zfldfh">
		<li id="zfldfh_sp"  onclick="openZhxtDialog_(event, 'zfldfh')">
			<a href="javascript:void(0)">罪犯流动复核</a>
		</li>
		</sec:authorize>--%>
				
			
		
	</ul>
 
	<div class="header-content">
        <div class="header-item date">
          <span class="icon iconfont icon-datepiceker"></span>
          <span class="title">${dqrq}</span>
        </div>
        <div class="header-item">
          <span class="icon iconfont icon-police2"></span>
          <span class="title" id="dqyh">当前用户1${map.dprtmntCode}：</span>
        </div>
        <%--
        <div class="header-item dropdow">
          <span class="icon iconfont icon-xialadown">
          	<div class="dropdown-content">
	            <ul class="menu">
	              <li class="menu-item">用户信息</li>
	              <li class="menu-item">用户信息2</li>
	            </ul>
	          </div>
          </span>
        </div>
         --%>
      </div>
</div>

<script type="text/javascript">
function openZnafpt() {
	//var dprtmntCode = jsConst.DEPARTMENT_ID;
	//var url = "${ctx}/portal/bj/shouye";
	// 部门是武警的时候，直接跳到综合首页
	//if(dprtmntCode == '110537') {
		url = "${ctx}/portal/zhshouye";
	//}
	window.location.href = url;
}

function showDqyh() {
	$("#dqyh").append("<span class=\"user\">" + jsConst.REAL_NAME + "</span> （" + jsConst.DEPARTMENT_NAME + "）<br>警号：" + jsConst.POLICE_CODE);
}

function openZhxtDialog_(event, name) {
	var event = window.event || event;
	//event.stopPropagation();
	  if ( event && event.stopPropagation ) {
		  event.stopPropagation();
		} else {
       window.event.cancelBubble = true;
	}
	//event.preventDefault();
	var url = "";
	var w = null;
	var h = null;
  if (name == 'aqfh') {
	  url = jsConst.basePath+'/inspect/checkDialog';
		w = 1000;
		h = 700;
	} else if (name == 'wlryclfh') {
      url = jsConst.basePath+'/aqfh/wlryclfh/toIndex';
      w = 1000;
      h = 700;
	}else if (name == 'zfcjfh') {
		url = jsConst.basePath+'/aqfh/zfcjfh/toIndex';
		w = 1000;
		h = 700;
	}else if (name == 'wpjcfh') {
		$.alert("待开发.....");
		return
	}else if (name == 'zfldfh') {
		$.alert("待开发.....");
		return
	} 


	if (w == null || h == null) {
		w = 900;
		h = 600;
	}

    $('#dialog').html("");
//    $('#dialog').dialog("destroy");
	$('#dialog').dialog({
		width : w,
		height : h,
		title : $("#zhxt_" + name + "_ a").text(),
		url : url
	});
	$("#dialog").dialog("open");

}





</script>