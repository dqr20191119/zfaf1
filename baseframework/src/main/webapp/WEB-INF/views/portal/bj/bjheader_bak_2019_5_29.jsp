<%@ page import="com.cesgroup.prison.common.bean.user.UserBean"%>
<%@ page import="com.cesgroup.prison.common.bean.login2.LoginRespBean"%>
<%@ page import="com.cesgroup.prison.common.facade.AuthSystemFacade"%>
<%@ page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
	UserBean user = AuthSystemFacade.getLoginUserInfo();
	String orgCodeLogo = user.getOrgCode();
	String departNaeme = user.getDprtmntName();
	System.out.print("*******************************************************************"+departNaeme);
	request.setAttribute("orgcodeLogo", orgCodeLogo);
%>
<img src="${ctx}/static/bj-cui/img/command/logo_zhihui_${orgcodeLogo }.png" alt="指挥中心logo" class="logo" style="margin-left: 10px;">
		<div class="header-content">
			<div class="header-item">
				<span class="icon iconfont icon-police2"></span>
				<span class="title" id="dqyh" style="    line-height: 1.5;">当前用户：</span>
			</div>
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
			<div class="header-item">
				<span class="icon iconfont icon-system-setting" title="退出系统" style="cursor: pointer;" onClick="syLogout();"></span>
			</div>
		</div>
		<ul class="tolist home" style="float: none;">
			<sec:authorize url="/zhdd/zy/zhaf/znafpt/sy">
			<li class="tolist-item status home-page" onClick="toZhsy();">
				首页
			</li>
			</sec:authorize>
			
			<!-- [数据分析_菜单] start -->
			<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx">
			<li class="tolist-item status">
				数据分析
				<ul class="tolist-menu">
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/jyaqfxyp">
					<li class="tolist-menuitem" onclick="openAqfxypSystem()">
						监狱安全风险研判
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/zfwxxpg">
					<li class="tolist-menuitem" onclick="wxxpg()">
						 罪犯危险性评估
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/zfswlpg">	
					<li class="tolist-menuitem" onclick="yswzfxzf()">
						罪犯食物量评估
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/mjlzqkpg">
					<li class="tolist-menuitem">
						民警履职情况评估
						<ul>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/mjlzqkpg/mjzbwh">
							<li class="tolist-menuitem" onclick="openFxpgDialogSy(event,'fxdgl','民警指标维护','ming.jing')">
								民警指标维护
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/mjlzqkpg/zgzbwh">
							<li class="tolist-menuitem" onclick="openFxpgDialogSy(event,'fxdgl','职工指标维护','zhi.gong')">
								职工指标维护
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/mjlzqkpg/bzwryzbwh">
							<li class="tolist-menuitem" onclick="openFxpgDialogSy(event,'fxdgl','编制外人员指标维护','bian.zhi.wai.he.tong.gong')">
								编制外人员指标维护
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/mjlzqkpg/mjlzpkpg">
							<li class="tolist-menuitem" onclick="ryglfxfx()">
								民警履职情况评估
							</li>
							</sec:authorize>
						</ul>
					</li>
					</sec:authorize>
					
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/mjzfsxxpg">
					<li class="tolist-menuitem">
						民警执法实效性评估
						<ul>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/mjzfsxxpg/sjldywcsgzdwh">
							<li class="tolist-menuitem" onclick="openFxpgDialogSy(event,'fxdgl','省局领导、业务处室关注点指标维护','shi.jv.ling.dao')">
								省局领导、业务处室关注点指标维护
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/mjzfsxxpg/jqyrzbgzdwh">
							<li class="tolist-menuitem" onclick="openFxpgDialogSy(event,'fxdgl','监区一日值班关注点指标维护','jian.qu.yi.ri.zhi.ban')">
								监区一日值班关注点指标维护
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/mjzfsxxpg/zhzxgzdwh">
							<li class="tolist-menuitem" onclick="openFxpgDialogSy(event,'fxdgl','指挥中心关注点指标维护','zhi.hui.zhong.xin')">
								指挥中心关注点指标维护
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/mjzfsxxpg/yzglgzdwh">
							<li class="tolist-menuitem" onclick="openFxpgDialogSy(event,'fxdgl','狱政管理关注点指标维护','yu.zheng.guan.li')">
								狱政管理关注点指标维护
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/mjzfsxxpg/xfzxgzdwh">
							<li class="tolist-menuitem" onclick="openFxpgDialogSy(event,'fxdgl','刑罚执行关注点指标维护','xing.fa.zhi.xing')">
								刑罚执行关注点指标维护
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/mjzfsxxpg/jygzgzdwh">
							<li class="tolist-menuitem" onclick="openFxpgDialogSy(event,'fxdgl','教育改造关注点指标维护','jian.yu.gai.zao')">
								教育改造关注点指标维护
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/mjzfsxxpg/ldgzgzdwh">
							<li class="tolist-menuitem" onclick="openFxpgDialogSy(event,'fxdgl','劳动改造关注点指标维护','lao.dong.gai.zao')">
								劳动改造关注点指标维护
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/mjzfsxxpg/hqbzgzdwh">
							<li class="tolist-menuitem" onclick="openFxpgDialogSy(event,'fxdgl','后勤保障关注点指标维护','hou.qin.bao.zhang')">
								后勤保障关注点指标维护
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/mjzfsxxpg/mjzfsxxpg">
							<li class="tolist-menuitem" onclick="ryglyffx()">
								民警执法实效性评估
							</li>
							</sec:authorize>
						</ul>
					</li>
					</sec:authorize>
					
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/sbssyxqkpg">
					<li class="tolist-menuitem">
						设备设施运行情况评估
						<ul>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/sbssyxqkpg/sszbwh">
							<li class="tolist-menuitem" onclick="openFxpgDialogSy(event,'fxdgl','区域指标维护','di.xie.gang')">
								设施指标维护
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/sbssyxqkpg/sbzbwh">
							<li class="tolist-menuitem" onclick="openFxpgDialogSy(event,'fxdgl','设备指标维护','wu.xie.gang')">
								设备指标维护
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/sbssyxqkpg/sbssyxqkpg">
							<li class="tolist-menuitem" onclick="sbyxqk()">
								设备运行情况评估
							</li>
							</sec:authorize>
						</ul>
					</li>
					</sec:authorize>
					
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/mjysqkpg">	
					<li class="tolist-menuitem" onclick="yswzfx()">
						民警饮食情况评估
					</li>
					</sec:authorize>
					
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/yqyjyp">
					<li class="tolist-menuitem">
						狱情警情研判
						<ul>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/yqyjyp/yqyjzbwh">
							<li class="tolist-menuitem" onclick="openFxpgDialogSy(event,'fxdgl','狱情警情指标维护','qing.xie.gang')">
								狱情警情指标维护
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/yqyjyp/yqyjyp">							 
							<li class="tolist-menuitem" onclick="yqyjpg()">
								狱情警情研判
							</li>
							</sec:authorize>
						</ul>
					</li>
					</sec:authorize>
					
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/yatstygl">
					<li class="tolist-menuitem" onclick="openFxpgDialogSy(event,'fxgkgl','预案推送统一管理 ','')">
					 预案推送统一管理
					</li>
					</sec:authorize>
					
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/zfgzzlpg">
					<li class="tolist-menuitem">
					 罪犯改造质量评估
						 <ul>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/zfgzzlpg/zfgzzlpgwh">
							<li class="tolist-menuitem" onclick="openFxpgDialogSy(event,'wdgzwh','罪犯改造质量评估维护','')">
								罪犯改造质量评估维护
							</li>
							 </sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/sjfx/zfgzzlpg/zfgzzlpg">
							<li class="tolist-menuitem" onclick="openZfgzzlkp()">
								罪犯改造质量评估
							</li>
							</sec:authorize>
						</ul>
					</li>
					</sec:authorize>
				</ul>
			</li>
			</sec:authorize>
			<!-- [数据分析_菜单] end -->
			
			<!-- [网格化管理_菜单] start -->
			<li class="tolist-item status">
				网格化管理
				<ul class="tolist-menu">
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj">
					<li class="tolist-menuitem">
						智能统计
						<ul>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/mjxxtg">
							<li class="tolist-menuitem" onclick="toPoliceList2()">
								民警信息统计
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/zfxxtj">
							<li class="tolist-menuitem" onclick="toPrisonerList()">
								罪犯信息统计
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/jqtj">
							<li class="tolist-menuitem" onClick="openMenuDialog(this, event,'alarmRecord')">
								警情统计
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/dwzttj">
							<li class="tolist-menuitem">
								电网状态统计
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/swdbtj">
							<li class="tolist-menuitem" onClick="openMenuDialog(this, event, 'swdbgd')">
								事物督办统计
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/sbtj">
							<li class="tolist-menuitem">
								设备统计
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/wlcltj">
							<li class="tolist-menuitem" onclick="toForeignCarList1()">
								外来车俩统计
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/wlrytj">
							<li class="tolist-menuitem" onClick="openMenuDialog(this, event,'wlry')">
								外来人员统计
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/xwzctj">
							<li class="tolist-menuitem"  onClick="openMenuDialog(this, event,'xwzc1')">
								行为侦测统计
							</li>
							</sec:authorize>
							<%-- <sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/ffsjtj">
							<li class="tolist-menuitem" onclick="openFfsjtj()">
								非法手机统计
							</li>
							</sec:authorize> --%>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/ldgzfftj">
							<li class="tolist-menuitem">
								劳动工具发放统计
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/mgctj">
							<li class="tolist-menuitem">
								敏感词统计
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/mjkgtj">
							<li class="tolist-menuitem"  onclick="openMenuDialog(this,event,'mjkgjl')">
								门禁开关记录
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/rlsbjl">
							<li class="tolist-menuitem"  onclick="openMenuDialog(this,event,'rlsb')">
								人脸识别记录
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/mjxcjl">
							<li class="tolist-menuitem"  onclick="openMenuDialog(this,event,'mjxcjl')">
								民警巡查记录
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/mjkqjl">
							<li class="tolist-menuitem"  onclick="openMenuDialog(this,event,'mjkq')">
								民警考勤记录
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/qjjcjl">
							<li class="tolist-menuitem"  onclick="openMenuDialog(this,event,'qjjcjl')">
								清监检查记录
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/xqdjjl">
							<li class="tolist-menuitem"  onclick="openMenuDialog(this,event,'xqdjlb')">
								心情登记记录
							</li>
							</sec:authorize>
							<%-- <sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/ysjgfx">
							<li class="tolist-menuitem"  onclick="openPoliceYsjgfx()">
								饮食结构分析
							</li>
							</sec:authorize> --%>
							
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/wxxpg">
							<li class="tolist-menuitem"  onclick="openMenuDialog(this,event,'wxxpg1')">
								危险性评估
							</li>
							</sec:authorize>
							
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/zfdm">
							<li class="tolist-menuitem">
							 	罪犯点名
								<ul>
									<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/zfdm/xsdm">
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event,'zfxsdm')">
										巡视点名
									</li>
									</sec:authorize>
									<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/zntj/zfdm/zwdm">
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event,'zfzwdm')">
										早晚点名
									</li>
									</sec:authorize>
								</ul>
							</li>
							</sec:authorize>
						</ul>
					</li>
					</sec:authorize>
					
					<li class="tolist-menuitem">
						一日执勤
						<ul>
							<li class="tolist-menuitem" onclick="openMenuDialog(this,event,'jhrc')">
								计划日程
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this,event,'zqgl')">
								执勤管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this,event,'jndtcx')">
								监内动态查询
							</li>
						</ul>
					</li>
					
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/wghf">
					<li class="tolist-menuitem">
						网格划分
						<ul>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/wghf/qywg">
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'ccode')">
								区域网格
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/wghf/wghgl">
							<li class="tolist-menuitem" onclick="openWg()">
								网格化管理
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/wghf/aqwg">
							<li class="tolist-menuitem">
								安全网格
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/wghf/djwg">
							<li class="tolist-menuitem">
								党建网格
								<ul>
									<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/wghf/djwg/djwgzzwh">
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'djwgzzwh')">
										党建网格组织维护
									</li>
									</sec:authorize>
									<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/wghf/djwg/djwgrywh">
									<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'djwgcywh')">
										党建网格成员维护
									</li>
									</sec:authorize>
									<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/wghf/djwg/djwg">
									<li class="tolist-menuitem" onclick="openDjwg()">
										党建网格
									</li>
									</sec:authorize>
								</ul>
							</li>
							</sec:authorize>
						</ul>
					</li>
					</sec:authorize>
					
					<li class="tolist-menuitem">
						值排班
						<ul>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'lbgl')">
								类别管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'gwgl')">
								岗位管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'bcgl')">
								班次管理
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'mbsz')">
								模板设置
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'zbbp')">
								值班编排
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'zbcx')">
								值班查询
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'zbfx')">
								值班分析
							</li>
							<!-- <li class="tolist-menuitem">
								值班导入
							</li> -->
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'zbfx')">
								值班日志
							</li>
							<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'zbfx')">
								交接班
							</li>
						</ul>
					</li>
					
					
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/sjjb">
					<li class="tolist-menuitem">
						数据接报
						<ul>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/sjjb/rwxf">
							<li class="tolist-menuitem">
								任务下发
								<ul>
									<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/sjjb/rwxf/xfrw">
									<li class="tolist-menuitem" onclick="openMenuDialog(this,event,'xfrw')">
										下发任务
									</li>
									</sec:authorize>
									<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/sjjb/rwxf/jsrw">
									<li class="tolist-menuitem" onclick="openMenuDialog(this,event,'jsrw')">
										接收任务
									</li>
									</sec:authorize>
								</ul>
							</li>
							</sec:authorize>
							
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/sjjb/dbsx">
							<li class="tolist-menuitem">
								待办事项
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/sjjb/bbbs">
							<li class="tolist-menuitem">
								报表报送
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/sjjb/tzgg">
							<li class="tolist-menuitem">
								通知公告
							</li>
							</sec:authorize>
						</ul>
					</li>
					</sec:authorize>
					
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/wghgl/jdjc">
					<li class="tolist-menuitem" onclick="openMenuDialog(this, event, 'jdjc')">
						监督检查
					</li>
					</sec:authorize>
				</ul>
			</li>
			<!-- [网格化管理_菜单] end -->	
				
			<!-- [生物识别_菜单] start -->
			<sec:authorize url="/zhdd/zy/zhaf/znafpt/swsb">	
			<li class="tolist-item status">
				生物识别
				<ul class="tolist-menu">
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/swsb/ydgj">
					<li class="tolist-menuitem">
						运动轨迹
						<ul class="tolist-menuitem">
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/swsb/ydgj/ytydgj">
							<li class="tolist-menuitem">
								依图运动轨迹
								 <ul class="tolist-menuitem">
									<sec:authorize url="/zhdd/zy/zhaf/znafpt/swsb/ydgj/ytydgj/dlyz">
								 		<li class="tolist-menuitem" onclick="openDlyz()">
								 		登录认证
								 		</li>
								 	</sec:authorize>
								 
								 	<sec:authorize url="/zhdd/zy/zhaf/znafpt/swsb/ydgj/ytydgj/rlsb">
								 	<li class="tolist-menuitem" onclick="openRlsb()">
								 		人脸识别
								 	</li>
								 	</sec:authorize>
								 	<sec:authorize url="/zhdd/zy/zhaf/znafpt/swsb/ydgj/ytydgj/rygj">
								 	<li class="tolist-menuitem" onclick="openRygj()">
								 		人员轨迹
								 	</li>
								 	</sec:authorize>
								 </ul>
							</li>
							</sec:authorize>
							<sec:authorize url="/zhdd/zy/zhaf/znafpt/swsb/ydgj/hkydgj">
							<li class="tolist-menuitem" onclick="openHkxt()">
								海康运动轨迹
							</li>
							</sec:authorize>
						</ul>
					</li>
					</sec:authorize>
				</ul>
			</li>
			</sec:authorize>
			<!-- [生物识别_菜单] end -->
			
			<sec:authorize url="/zhdd/zy/zhaf/znafpt/yysb">
			<li class="tolist-item status" onclick="openZnyysb()">
				语音识别
			</li>
			</sec:authorize>
			<li class="tolist-item status" onclick="openOldZnafpt()">
				安防立体防控
			</li>
			<sec:authorize url="/zhdd/zy/zhaf/znafpt/ydjw">
			<li class="tolist-item status">
				移动警务
				<ul class="tolist-menu">
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/ydjw/ydzdsltj">
					<li class="tolist-menuitem">
						移动终端数量统计
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/ydjw/ydzdzttj">
					<li class="tolist-menuitem">
						移动终端状态统计
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/ydjw/gbthqktj">
					<li class="tolist-menuitem">
						个别谈话情况统计
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/ydjw/ydddmtj">
					<li class="tolist-menuitem">
						移动端点名统计
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/ydjw/zjcjqktj">
					<li class="tolist-menuitem">
						证据采集情况统计
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/ydjw/ydoasyqktj">
					<li class="tolist-menuitem">
						移动OA使用情况统计
					</li>
					</sec:authorize>
				</ul>
			</li>
			</sec:authorize>	
			 
		</ul>
		
		
<script type="text/javascript">
/**
 * 首页退出
 */
function syLogout() {
	$.confirm("确定要退出系统吗？", function(r) {
		if (r) {
			var ur = jsConst.basePath+'lg/loginCtrl/logout';
			$.ajax({
				type : 'post',
				url : ur,
				data : {'userId': jsConst.USER_ID  },
				dataType : 'json',
				success : function(data) {
					var user = null;
					if (data.result) {
						/* user = data.userBean; */
						window.location.href=ssoPage();
					}else{
						alert("退出失败："+data.respDesc);
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert(textStatus);
				}
			});
		}
	});
}

/**
 * 跳转至综合首页
 */
function toZhsy() {
	var url = urlfhs;
	window.open(url, "_self");
}
/**
 * 安全风险研判跳转
 */
function openAqfxypSystem() {
	
	var url = "${ctx}/portal/aqfxyp/shouye";
	window.open(url, "_self");
}

//危险性评估
  function wxxpg (){
			   var url = "http://192.168.8.187/jy-wxpg/form/5be0f9483d0a86f8d24fca432843b784/insert";
				window.open(url, "_blank");
		   }
//饮食物资分析罪犯
function yswzfxzf(){
	   var url = "http://192.168.8.188:8080/PMS/charts/criminal.html";
		window.open(url, "_blank");
}
function openFxpgDialogSy(event, name,zhi,code) {
	var event = window.event || event;
	if (event && event.stopPropagation) {
		event.stopPropagation();
	} else {
		window.event.cancelBubble = true;
	}
	//event.preventDefault();
	var url = "";
	var w = null;
	var h = null;
	if (name == 'fxdgl') {
		url = jsConst.basePath + '/wwjg/fxdgl/toIndex?sjfwName='+code;
		w = 1000;
		h = 800;
	}else if (name == 'fxgkgl') {
		url = jsConst.basePath + '/wwjg/fxgkgl/toIndex';
		w = 1000;
		h = 800;
	} else if (name == 'wdgzwh') {
		url = jsConst.basePath + '/wwjg/fxgkgl/toIndexWdgz';
		w = 1000;
		h = 800;
	} 
	
	$('#dialog').html("");
	$('#dialog').dialog({
		width : w,
		height : h,
		title : zhi,
		url : url
	});
	$("#dialog").dialog("open");
}

//民警管理风险分析
function ryglfxfx(){
		var url = "${ctx}/portal/sjfxyp/ryglfxfx";
		window.open(url, "_blank");
	}
	
//民警执法实效性
function ryglyffx(){
	var url = "${ctx}/portal/sjfxyp/ryglyffx";
	window.open(url, "_blank");
}

//设备运行情况评估
function sbyxqk(){
		var url = "${ctx}/portal/sjfxyp/sbyxqk";
		window.open(url, "_blank");
	}
//饮食物资分析民警
function  yswzfx (){
	   var url = "http://192.168.8.188:8080/PMS/charts/police.html";
		window.open(url, "_blank");
}
//狱情预警评估
function yqyjpg(){
	   var url = "${ctx}/portal/sjfxyp/yqyjpg";
		window.open(url, "_blank");
}
//改造质量考评
function openZfgzzlkp() {
		
		var url = "${ctx}/portal/bj/zfzlkp";
		window.open(url, "_blank");
	}
/**
 * 查询在岗民警
 */
function toPoliceList2() {
	// 民警编号或姓名
	var policeIdntyOrName = $("#mjzf").textbox("getValue");
	
	// 民警编号或姓名转Base64编码
	var policeIdntyOrNameBase64 = new Base64().multiEncode(policeIdntyOrName, 2);
	// alert("policeIdntyOrName = " + policeIdntyOrName);
	$.ajax({
		type : 'post',
		url : "${ctx}/xxhj/jnmj/queryDutyConfig",
		data : {
			cusNumber:jsConst.CUS_NUMBER
		},
		dataType : 'json',
		success : function(data) {
			var cusNumberFlag = "";
			if(data) {
				if(data.FLAG) {
					cusNumberFlag = data.FLAG;
				}
			}
			$("#dialog").dialog({
				width : 1000, 
				height : 800, 
				title : '民警信息 ',
				modal : true, 
				autoOpen : false,
				url : "${ctx}/xxhj/jnmj/jnmjPoliceInfo?config=" +cusNumberFlag+ "&drptmntId=" + jsConst.DEPARTMENT_ID + "&cusNumber=" + jsConst.CUS_NUMBER + "&policeIdntyOrName=" + policeIdntyOrNameBase64
			});
			
			$("#dialog").dialog("open");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// jsConst.CUS_NUMBER_FLAG = "";
		}
	});
}
/**
 * 弹出罪犯查询页面
 */
function toPrisonerList() {
	var cusNumber = "";
	var prisonArea = "";
	// 罪犯编号或姓名
	var prsnrIdntyOrName = $("#mjzf").textbox("getValue");
	var USER_LEVEL= jsConst.USER_LEVEL;
	var prisonAreaName = "";
	if(USER_LEVEL==3){
		prisonAreaName = "<%=departNaeme%>";
	}
	/* jsConst.DEPARTMENT_ID */
	// 罪犯编号或姓名转Base64编码
	var prsnrIdntyOrNameBase64 = new Base64().multiEncode(prsnrIdntyOrName, 2);
	if(jsConst.USER_LEVEL != 1){
		cusNumber = jsConst.CUS_NUMBER;
		if(jsConst.USER_LEVEL == 3) {
			prisonArea= jsConst.DEPARTMENT_ID; 
		}
	}
	// alert("prsnrName = " + prsnrName);
	$("#dialog").dialog({
		width : 1200,
		height : 800, 
		title : '罪犯信息',
		url : "${ctx}/xxhj/zfjbxx/prisonerInfo?query=2&type=1&drpmntId=" + prisonArea + "&cusNumber=" + cusNumber + "&prsnrIdntyOrName=" + prsnrIdntyOrNameBase64+ "&drpmntName=" +encodeURI(encodeURI(prisonAreaName))
	});
	$("#dialog").dialog("open");
}
function openMenuDialog(obj, event, name) {

	var event = window.event || event;
	// event.stopPropagation();
	if (event && event.stopPropagation) {
		event.stopPropagation();
	} else {
		window.event.cancelBubble = true;
	}
	// event.preventDefault();
	var url = "";
	var w = null;
	var h = null;
	var title = null;
	if (name == 'camera') {
		w = 1100;
		h = 600;
		url = jsConst.basePath + '/jfsb/camera/list'
	} else if (name == 'videoDevice') {
		url = jsConst.basePath + '/sppz/videoDevice/list'
	} else if (name == 'streamServer') {
		url = jsConst.basePath + '/sppz/streamServer/list'
	} else if (name == 'videoClient') {
		url = jsConst.basePath + '/sppz/videoClient/list'
	} else if (name == 'powerNetwork') {
		url = jsConst.basePath + '/jfsb/powerNetwork/list'
	} else if (name == 'physicalDevice') {
		url = jsConst.basePath + '/wfsb/physicalDevice/list'
	} else if (name == 'physicalDeviceName') {
		url = jsConst.basePath + '/wfsb/physicalDeviceName/list'
	} else if (name == 'dvcRole') {
		url = jsConst.basePath + '/xtgl/dvcRole/index'
	} else if (name == 'policeDevice') {
		url = jsConst.basePath + '/wfsb/policeDevice/list'
	} else if (name == 'broadcast') {
		url = jsConst.basePath + '/broadcast/openDialog'
	} else if (name == 'talkBackServer') {
		w = 1200;
		h = 600;
		url = jsConst.basePath + '/talkBackServer/openDialog'
	} else if (name == 'talkBackBase') {
		w = 1200;
		h = 600;
		url = jsConst.basePath + '/talkBackBase/openDialog?type=0'
	} else if (name == 'alertor') {
		w = 1200;
		h = 600;
		url = jsConst.basePath + '/alertor/openDialog'
	} else if (name == 'door') {
		w = 1200;
		h = 600;
		url = jsConst.basePath + '/door/openDialog'
	} else if (name == 'doorCtrl') {
		url = jsConst.basePath + '/doorControl/openDialog'
	} else if (name == 'screenPlan') {
		url = jsConst.basePath + '/screenPlan/openDialog'
	} else if (name == 'ewtcwh') {
		url = jsConst.basePath + '/xtgl/planeLayer/index'
	} else if (name == 'ewdwwh') {// //二维图层点位维护
		url = jsConst.basePath + '/xtgl/planeLayerPoint/index'
		w = 1400;
		h = 620;
	} else if (name == 'jswh') {
		url = jsConst.basePath + '/xxhj/jswh/toIndex'
		w = 1000;
		h = 800;
	} else if (name == 'regionDepart') {
		url = jsConst.basePath + '/regionDepart/index';
	} else if (name == "xxdy") {
		url = jsConst.basePath + '/common/msgsubscribe/index';
	} else if (name == "prisonPath") {
		url = jsConst.basePath + '/prisonPath/openDialog';
	} else if (name == "doorPlan") {
		url = jsConst.basePath + '/door/plan/openDialog';
	} else if (name == "crontab") {
		url = jsConst.basePath + '/crontab/index';
	} else if (name == "viewPeople") {
		url = jsConst.basePath + '/viewPeople/index';
	} else if (name == 'jdjc') {
		url = jsConst.basePath + '/monitor/jdjc';
		w = 1000;
		h = 700;
	} else if (name == 'jddlb') {
		url = jsConst.basePath + '/monitor/jddlb';
		w = 1000;
		h = 700;
	} else if (name == 'realTimeTalk') {
		url = jsConst.basePath + '/realTimeTalk/openDialog';
		w = 1000;
		h = 600;
	} else if (name == 'group') {
		w = 1200;
		h = 600;
		url = jsConst.basePath + '/groupManage/index';
	} else if (name == 'screenSwitch') {
		url = jsConst.basePath + '/screenSwitch/openDialog';
	} else if (name == 'wldctb-bj') {
		url = jsConst.basePath + '/inspect/editDialog';
	} else if (name == 'wldctb-lb') {
		url = jsConst.basePath + '/inspect/inspectListDialog';
		w = 1000;
		h = 700;
	} else if (name == 'wldctb-sp') {
		url = jsConst.basePath + '/inspect/checkDialog';
		w = 1000;
		h = 700;
	} else if (name == 'wldctb-hz') {
		url = jsConst.basePath + '/inspect/recordDialog';
		w = 1000;
		h = 700;
	} else if (name == 'bddctb-bj') {
		url = jsConst.basePath + '/inspectlocal/editDialog';
	} else if (name == 'bddctb-sp') {
		url = jsConst.basePath + '/inspectlocal/checkDialog';
	} else if (name == 'bddctb-hz') {
		url = jsConst.basePath + '/inspectlocal/recordDialog';
	} else if (name == 'tbzg-fq') {
		url = jsConst.basePath + '/xxyp/change/launchDialog';
	} else if (name == 'tbzg-zg') {
		url = jsConst.basePath + '/xxyp/change/changeDialog';
	} else if (name == 'tbzg-sp') {
		url = jsConst.basePath + '/xxyp/change/checkDialog';
	} else if (name == 'tbzg-hz') {
		url = jsConst.basePath + '/xxyp/change/recordDialog';
	} else if (name == 'gwgl') {
		url = jsConst.basePath + '/zbgl/gwgl/toIndex';
	} else if (name == 'bcgl') {
		url = jsConst.basePath + '/zbgl/bcgl/toIndex';
	} else if (name == 'lbgl') {
		url = jsConst.basePath + '/zbgl/lbgl/toIndex';
	} else if (name == 'mbsz') {
		url = jsConst.basePath + '/zbgl/mbsz/toIndex';
	} else if (name == 'zbbp') {
		url = jsConst.basePath + '/zbgl/zbbp/toIndex';
	} else if (name == 'zbcx') {
		url = jsConst.basePath + '/zbgl/zbcx/toIndex';
	} else if (name == 'zbfx') {
		url = jsConst.basePath + '/zbgl/zbfx/toIndex';
	} else if (name == 'excel') {// excel
		url = jsConst.basePath + '/zbgl/kspb/toIndex';
	} else if (name == 'pjcjl') {
		url = jsConst.basePath + '/xxhj/jnmj/jnmjPoliceInoutRecord';
	} else if (name == 'jcjl') {
		url = jsConst.basePath + '/xxhj/jnmj/jnmjPoliceInoutRecord?cusNumber='
				+ jsConst.CUS_NUMBER;
	} else if (name == 'jqjcjl') {
		url = jsConst.basePath + '/xxhj/jnmj/jnmjPoliceInoutRecord?cusNumber='
				+ jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
	} else if (name == 'jndt') {
		url = jsConst.basePath + '/xxhj/jndt/toIndex';
	} else if (name == 'gwgl') {
		url = jsConst.basePath + '/zbgl/gwgl/toIndex';
	} else if (name == 'bcgl') {
		url = jsConst.basePath + '/zbgl/bcgl/toIndex';
	} else if (name == 'lbgl') {
		url = jsConst.basePath + '/zbgl/lbgl/toIndex';
	} else if (name == 'mbsz') {
		url = jsConst.basePath + '/zbgl/mbsz/toIndex';
	} else if (name == 'zbbp') {
		url = jsConst.basePath + '/zbgl/zbbp/toIndex';
	} else if (name == 'zbcx') {
		url = jsConst.basePath + '/zbgl/zbcx/toIndex';
	} else if (name == 'zbfx') {
		url = jsConst.basePath + '/zbgl/zbfx/toIndex';
	} else if (name == 'mjczdj') {
		url = jsConst.basePath + '/xxhj/mjczdj/openDialog';
	} else if (name == 'jhrc') {
		url = jsConst.basePath + '/xxhj/jhrc/toIndex';
	} else if (name == 'cgsgxx') {
		url = jsConst.basePath + '/xxhj/cgsgxx/toIndex';
	} else if (name == 'sblxsz') {
		url = jsConst.basePath + '/xxhj/zfsjsb/sblxsz/toIndex?cusNumber='
				+ jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
	} else if (name == 'sjsb') {
		url = jsConst.basePath + '/xxhj/zfsjsb/sjsb/toIndex?cusNumber='
				+ jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
	} else if (name == 'sjhz') {
		url = jsConst.basePath + '/xxhj/zfsjsb/sjsb/toSjhz?cusNumber='
				+ jsConst.CUS_NUMBER + '&drpmntId=' + jsConst.DEPARTMENT_ID;
	} else if (name == 'jdjc') {
		url = jsConst.basePath + '/monitor/jdjc';
		w = 1000;
		h = 700;
	} else if (name == 'jddlb') {
		url = jsConst.basePath + '/monitor/jddlb';
		w = 1000;
		h = 700;
	} else if (name == 'realTimeTalk') {
		url = jsConst.basePath + '/realTimeTalk/openDialog';
		w = 1000;
		h = 600;
	} else if (name == 'group') {
		w = 1200;
		h = 600;
		url = jsConst.basePath + '/groupManage/index';
	} else if (name == 'screenSwitch') {
		url = jsConst.basePath + '/screenSwitch/openDialog';
	} else if (name == 'wldctb-bj') {
		url = jsConst.basePath + '/inspect/editDialog';
	} else if (name == 'wldctb-lb') {
		url = jsConst.basePath + '/inspect/inspectListDialog';
		w = 1000;
		h = 700;
	} else if (name == 'wldctb-sp') {
		url = jsConst.basePath + '/inspect/checkDialog';
		w = 1000;
		h = 700;
	} else if (name == 'wldctb-hz') {
		url = jsConst.basePath + '/inspect/recordDialog';
		w = 1000;
		h = 700;
	} else if (name == 'bddctb-bj') {
		url = jsConst.basePath + '/inspectlocal/editDialog';
	} else if (name == 'bddctb-sp') {
		url = jsConst.basePath + '/inspectlocal/checkDialog';
	} else if (name == 'bddctb-hz') {
		url = jsConst.basePath + '/inspectlocal/recordDialog';
	} else if (name == 'tbzg-fq') {
		url = jsConst.basePath + '/xxyp/change/launchDialog';
	} else if (name == 'tbzg-zg') {
		url = jsConst.basePath + '/xxyp/change/changeDialog';
	} else if (name == 'tbzg-sp') {
		url = jsConst.basePath + '/xxyp/change/checkDialog';
	} else if (name == 'tbzg-hz') {
		url = jsConst.basePath + '/xxyp/change/recordDialog';
	} else if (name == 'rcs') {
		url = jsConst.basePath + '/rcs/openDialog';
		w = 1000;
		h = 600;
	} else if (name == 'alarmType') {
		url = jsConst.basePath + '/alarmTypeAndLev/openDialog';
	} else if (name == 'alarmPlan') {
		url = jsConst.basePath + '/plan/openDialog'
	} else if (name == 'affairsRecord') {
		w = 700;
		h = 500;
		url = jsConst.basePath + '/deviceMaintain/openDialog/record';
	} else if (name == 'affairsHandle') {
		w = 1100;
		h = 600;
		url = jsConst.basePath + '/deviceMaintain/openDialog/handle';
	} else if (name == 'affairsfeedBack') {
		w = 1100;
		h = 600;
		url = jsConst.basePath + '/deviceMaintain/openDialog/feedback';
	} else if (name == 'affairsOversee') {
		w = 1100;
		h = 600;
		url = jsConst.basePath + '/deviceMaintain/openDialog/oversee';
	} else if (name == 'affairsGather') {
		w = 1100;
		h = 600;
		url = jsConst.basePath + '/deviceMaintain/openDialog/gather';
	} else if (name == 'flows') {
		url = jsConst.basePath + '/flow/list';
	} else if (name == 'alarmRecord') {
		w = 1200;
		h = 800;
		url = jsConst.basePath + "/alarm/openDialog/record?DpName=''";
	} else if (name == 'alarmRecord1') {
		w = 1200;
		h = 800;
		title = "一级警情";
		url = jsConst.basePath + '/alarm/openDialog/record?type=1';
	} else if (name == 'alarmRecord2') {
		w = 1200;
		h = 800;
		title = "二级警情";
		url = jsConst.basePath + '/alarm/openDialog/record?type=2';
	} else if (name == 'alarmRecord3') {
		w = 1200;
		h = 800;
		title = "三级警情";
		url = jsConst.basePath + '/alarm/openDialog/record?type=3';
	} else if (name == 'sporadicFlow') {
		w = 1100;
		h = 580;
		url = jsConst.basePath + '/sporadicFlow/openDialog';
	} else if (name == 'deviceRecord') {
		w = 1100;
		h = 600;
		url = jsConst.basePath + '/deviceMaintain/record/openDialog';
	} else if (name == 'faultType') {
		url = jsConst.basePath + '/deviceFaultType/openDialog';
	} else if (name == 'wlry') {
		w = 1200;
		h = 800;
		title = "外来人员";
		url = jsConst.basePath + '/xxhj/foreignerPeos/toIndex?onlyToday=0';
	} else if (name == 'wlry1') {
		w = 1200;
		h = 800;
		title = "外来人员";
		url = jsConst.basePath + '/xxhj/foreignerPeos/toIndex?onlyToday=1';
	} else if (name == 'wlrc') {
		w = 1000;
		h = 680;
		url = jsConst.basePath + '/foreign/list';
	} else if (name == 'wlcl') {
		w = 1200;
		h = 680;
		url = jsConst.basePath + '/foreignCar/list';
	} else if (name == 'dmls') {
		w = 1200;
		h = 680;
		url = jsConst.basePath + '/callNames/openDialog/dmls';
	} else if (name == 'fqdm') {
		w = 1200;
		h = 680;
		url = jsConst.basePath + '/callNames/openDialog/fqdm';
	} else if (name == 'rlzc') {
		w = 1200;
		h = 680;
		url = jsConst.basePath + '/callNames/register/openDialog';
	} else if (name == 'dmfq') {
		w = 1200;
		h = 680;
		url = jsConst.basePath + '/callNames/master/openDialog';
	} else if (name == 'dmjl') {
		url = jsConst.basePath + '/callNames/master/openDialog/record';
	} else if (name == 'zfjcj') {
		url = jsConst.basePath + '/zfjcj/list';
	} else if (name == "gzzgl") {
		url = jsConst.basePath + '/yjct/gzzgl/index';
	} else if (name == "wzgl") {
		url = jsConst.basePath + '/yjct/wzgl/index';
	} else if (name == "zjgl") {
		url = jsConst.basePath + '/yjct/zjgl/index';
	} else if (name == "fggl") {
		url = jsConst.basePath + '/yjct/fggl/index';
	} else if (name == "czffgl") {
		url = jsConst.basePath + '/yjct/czffgl/index';
	} else if (name == "yabz") {
		url = jsConst.basePath + '/yjct/yabz/index';
	} else if (name == "yasp") {
		url = jsConst.basePath + '/yjct/yasp/index';
	} else if (name == "yafb") {
		url = jsConst.basePath + '/yjct/yafb/index';
	} else if (name == "yljh") {
		url = jsConst.basePath + '/yjct/yljh/index';
	} else if (name == "ylsp") {
		url = jsConst.basePath + '/yjct/ylsp/index';
	} else if (name == "ylfb") {
		url = jsConst.basePath + '/yjct/ylfb/index';
	} else if (name == "zxyl") {

	} else if (name == "yljl") {
		url = jsConst.basePath + '/yjct/yjjl/toIndex?type=2';
	} else if (name == "yltj") {
		url = jsConst.basePath + '/yjct/yjjl/toTj?type=2';
	} else if (name == "czjl") {
		url = jsConst.basePath + '/yjct/yjjl/toIndex?type=1';
	} else if (name == "cztj") {
		url = jsConst.basePath + '/yjct/yjjl/toTj?type=1';
	} else if (name == "xxdy") {
		url = jsConst.basePath + '/yjct/msgsubscribe/index';
	} else if (name == "yjctSszk") {
		url = jsConst.basePath + '/yjct/sszk/toIndex';
	} else if (name == 'yrzq') {
		url = jsConst.basePath + '/wghgl/yrzq/toIndex?type=yzx';
	} else if (name == 'zqgl') {
		url = jsConst.basePath + '/wghgl/yrzq/toIndex?type=zqgl';
	} else if (name == 'swdbgd') {
		w = 1200;
		h = 680;
		url = jsConst.basePath + '/rwgl/rwjs/openDialog/index?type=swdb';
	} else if (name == 'dayly') {
		url = '${ctx}/xxyp/dayly/daylyLayout';
		w = 1000;
		h = 680;
	} else if (name == 'xfrw') {
		w = 1200;
		h = 680;
		url = jsConst.basePath + '/rwgl/rwxf/index';
	} else if (name == 'jsrw') {
		w = 1200;
		h = 680;
		url = jsConst.basePath + '/rwgl/rwjs/index';
	} else if (name == 'jndtcx') {
		url = jsConst.basePath + '/wghgl/yrzq/toList';
	} else if (name == 'ccode') {// // 网格划分 网格管理分配格长
		w = 1200;
		h = 680;
		url = jsConst.basePath + '/wghf/wgzrfp/toIndex';
	} else if (name == 'xwzc') {
        w = 1200;
        h = 680;
		title = "行为侦测";
		url = jsConst.basePath + '/xwzc/toIndex?type=1';
	} else if (name == 'xwzc1') {
		url = jsConst.basePath + "/xwzc/toIndex?type=''";
	} else if (name == 'mjkgjl') {
		url = jsConst.basePath + '/xxhj/mjkgjl/toIndex';
	} else if (name == 'swsb') {
		// 生物识别
		url = jsConst.basePath + '/policeLocation/openSwsbCountDialog';
	} else if (name == "znys") {
		// 智能钥匙
		title = "智能钥匙";
		url = jsConst.basePath + '/xxhj/znys/toIndex';
	} else if (name == "mjxcjl") {
		// 民警巡查记录
		url = jsConst.basePath + '/xxhj/patrol/mjxcjl/toIndex';
	} else if (name == "qjjcjl") {
		// 清监检查
		url = jsConst.basePath + '/wghgl/yrzq/qjjc/toIndex';
	} else if (name == "xqdjlb") {
		// 心情登记
		url = jsConst.basePath + '/wghgl/yrzq/xqdjjl/toIndex';
	} else if (name == "rlsb") {
		url = jsConst.basePath + '/rlsb/toIndex';
	} else if (name == "zfxsdm") {
		// 罪犯巡视点名
		url = jsConst.basePath + '/zfxx/zfXsdm/toIndex';
	} else if (name == "zfzwdm") {
		// 罪犯早晚点名
		url = jsConst.basePath + '/zfxx/zfZwdm/toIndex';
	} else if (name == 'mjkq') {
		url = jsConst.basePath + '/xxhj/mjkq/toIndex';
		// 民警考勤查询
	} else if (name == 'wxxpg1') {
		url = jsConst.basePath + '/aqfxyp/wxpg/toIndex?zt=0';
		// 危险性评估
	}else if (name == 'djwgzzwh') {
		//党建网格组织维护
		url = jsConst.basePath + '/djwg/zzwh/toIndex';
	}else if (name == 'djwgcywh') {
		//党建网格成员维护
		url = jsConst.basePath + '/djwg/rywh/toIndex';
	} else if (name == 'jctj') {
		// 进出统计
		url = jsConst.basePath + '/xxhj/jndt/jctj';
	} 
	if (w == null || h == null) {
		w = 1000;
		h = 600;
	}
	if (title == null) {
		title = $(obj).text();
	}
	$('#dialog').html("");
	// $('#dialog').dialog("destroy");
	$('#dialog').dialog({
		width : w,
		height : h,
		title : title,
		url : url
	});
	$("#dialog").dialog("open");
	return;

}

//外来车辆统计
	function toForeignCarList1() {
			$("#dialogId_rightHomeMenu").dialog({
				width : 1000, //属性
				height : 800, //属性
				title : '外来车辆',
				modal : true, //属性
				autoOpen : false,
				url : "${ctx}/foreign/openCarInfo?frType=1&date=0"
			});
			$("#dialogId_rightHomeMenu").dialog("open");
		}
//外来人员统计
function toForeignPeopleList() {
	$("#dialogId_rightHomeMenu").dialog({
		width : 1000, //属性
		height : 800, //属性
		title : '外来人员',
		modal : true, //属性
		autoOpen : false,
		url : "${ctx}/foreign/openPeopleInfo?flag=1"
	});
	$("#dialogId_rightHomeMenu").dialog("open");
}
//非法手机
function openFfsjtj() {

	var url = "http://192.168.9.221/inmm/nzlogin.do?username=u0013&password=Sw123456&type=1";
	window.open(url, "_blank");
}
/**
 * 民警饮食结构分析
 */
function openPoliceYsjgfx() {
	var url = "http://192.168.8.188:8080/PMS/charts/police.html";
	window.open(url, "_blank");
}
//打开网格化管理页面
function openWg(){
	console.log(1);
	var url = "${ctx}/portal/wghgl";
	window.open(url, "_blank");
	
}
//党建网格
function openDjwg(){
	console.log(2);
	var url = "${ctx}/portal/djwg";
	window.open(url, "_blank");
	
}



/**
 * 依图登录认证
 */
function openDlyz() {
	var url = "http://192.168.9.33:11180/#/login?username=qd&password=qd";
	// var url = "http://192.168.9.33:9000/#/monitor?rtsp=rtsp://admin:Admin12345@206.0.32.7";
	window.open(url, "_blank");
}
/**
 * 依图人员轨迹
 */
function openRygj() {
    //var url = "http://192.168.9.33:11180/#/trace/index";
    var cusNumber = ${map.cusNumber};
    var userName = "";
    var password="";
    if(cusNumber == '1103') {
        userName = "ZHONGXIN";// 延庆
        password="Zhongxin@123";
    } else if(cusNumber == '1105') {
        userName = "ZHONGXIN";// 女子
        password="Zhongxin@123";
    } else if(cusNumber == '1142') {
        userName = "";// 柳林 赵川
    } else if(cusNumber == '1145') {
        userName = "";// 前进 蔡长海
    } else if(cusNumber == '1146') {
        userName = "";// 潮白 王晓泉
    } else if(cusNumber == '1149') {
        userName = "ZHONGXIN";// 垦华
        password="Zhongxin@123";
    }
    if(userName) {
        var url = "http://192.168.9.74:9000/#/entry?username=" + userName + "&password=" + password+"&page=data";
        window.open(url, "_blank");
    } else {
        alert("请联系管理员配置链接地址");
    }

}
/**
 * 依图人脸识别
 */
function openRlsb() {
	// var url = "http://192.168.9.33:9000/#/monitor?rtsp=rtsp://admin:Admin12345@206.0.32.7";
	// var url = "http://192.168.9.33:11180/#/drawer?camera_id=6@DEFAULT";
	var url = "http://192.168.9.33:11180/#/drawer?camera_id=6@DEFAULT";
	window.open(url, "_blank");
}




//海康运动轨迹
function openHkxt() {
	var url = "https://206.0.43.5/cas/login";
	window.open(url, "_blank");
}
/**
 * 语音识别系统
 */
 function openZnyysb() {
	var url = "http://192.168.8.82:8080/jy-yysb/cas";
	window.open(url, "_blank");
}

//安全立体防控
 function openOldZnafpt() {
		
		var url = "${ctx}/portal/shouye";
		window.open(url, "_self");
	}
 /**
  * 加载今日压犯情况
  */
 function loadJryfqk(){
 	$.ajax({
 		type : "post",
         url : "${ctx}/jyshouye/jryfqk/query",
 		dataType : "json",
 		success : function(data) {
 			var zars = 0;
 			var zcrs = 0;

 			for(var i = 0;i<data.length;i++){
 				var map = data[i];
 				var key = map.key;
 				var value = map.value;
 				var time = map.time;
 				if(key=="ZFXX_ZCRS"){//在册人数
 					$("#zfZcrs").text(value);
 					zcrs = value;
 				}else
 				if(key=="ZFXX_JZZY"){//就诊住院
 					$("#zfWcjy").append("<p class=\"custodyNum alertNum\" style=\"cursor: pointer;\" onclick=\"searchListJryf('zfWcjy','"+time+"')\">"+value+"</p>");
 					//$("#zfWcjy").text(value);
 				}else
 				if(key=="ZFXX_JYZF"){//寄押人数

 					zars = parseInt(zars)+ parseInt(value);
 					//$("#zfYwjy").text(value);
 					$("#zfYwjy").append("<p class=\"custodyNum alertNum\" style=\"cursor: pointer;\" onclick=\"searchListJryf('zfYwjy','"+time+"')\">"+value+"</p>");
 				}else
 				if(key=="ZFXX_TTBH"){//脱逃人数
 					//$("#zfTtrs").text(value);
 					$("#zfTtrs").append("<p class=\"custodyNum alertNum\" style=\"cursor: pointer;\" >"+value+"</p>");
 					zars = parseInt(zars) + parseInt(value);
 				}else
 				if(key=="ZFXX_LJTQ"){//离监探亲人数
 					$("#zfTxlj").append("<p class=\"custodyNum alertNum\" style=\"cursor: pointer;\" onclick=\"searchListJryf('zfTxlj','"+time+"')\">"+value+"</p>");
 					//$("#zfTxlj").text(value);
 				}else
 				if(key=="ZFXX_JWZX"){//监外执行人数
 					$("#zfJwzx").append("<p class=\"custodyNum alertNum\" style=\"cursor: pointer;\" onclick=\"searchListJryf('zfJwzx','"+time+"')\">"+value+"</p>");
 					//$("#zfJwzx").text(value);
 					zars = parseInt(zars) + parseInt(value);
 				}else
 				if(key=="ZFXX_THDJ"){//解回人数
 					//$("#zfJhzs").text(value);
 					$("#zfJhzs").append("<p class=\"custodyNum alertNum\" style=\"cursor: pointer;\" onclick=\"searchListJryf('zfJhzs','"+time+"')\">"+value+"</p>");

 					zars = parseInt(zars) + parseInt(value);
 				}
 				else if(key=="ZFDD_SFZF"){//释放
 					$("#zfSfrs").append("<p class=\"custodyNum alertNum\" style=\"cursor: pointer;\" onclick=\"searchListJryf('zfSfrs','"+time+"')\">"+value+"</p>");
 						//$("#zfSfrs").text(value);
 					}
 				else if(key=="ZFDD_SYZF"){//收押人数
 					$("#zfSyrs").append("<p class=\"custodyNum alertNum\" style=\"cursor: pointer;\" onclick=\"searchListJryf('zfSyrs','"+time+"')\">"+value+"</p>");
 					//$("#zfSyrs").text(value);
 				}
 				else if(key=="ZFDD_DCZF"){//调出人数
 					//$("#zfDcrs").text(value);
 					$("#zfDcrs").append("<p class=\"custodyNum alertNum\" style=\"cursor: pointer;\" onclick=\"searchListJryf('zfDcrs','"+time+"')\">"+value+"</p>");
 				}
 				else if(key=="ZFDD_SWZF"){//死亡人数
 					$("#zfSwrs").append("<p class=\"custodyNum alertNum\" style=\"cursor: pointer;\" onclick=\"searchListJryf('zfSwrs','"+time+"')\">"+value+"</p>");
 					//$("#zfSwrs").text(value);
 				}
 				else if(key=="ZFDD_QSZF"){//遣送人数
 					$("#ZfQsrs").append("<p class=\"custodyNum alertNum\" style=\"cursor: pointer;\" >"+value+"</p>");
 					//$("#ZfQsrs").text(value);
 				}
 				else if(key=="ZFDD_DRZF"){//调入人数
 					$("#zfDrrs").append("<p class=\"custodyNum alertNum\" style=\"cursor: pointer;\" onclick=\"searchListJryf('zfDrrs','"+time+"')\">"+value+"</p>");
 					//$("#zfDrrs").text(value);
 				}
 			}
 			//在押为在册减去暂予监外、脱逃、解回
 				$("#zfZyrs").text(parseInt(zcrs) - parseInt(zars));
 		},
 		error : function(XMLHttpRequest, textStatus, errorThrown) {
 			$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
 		}
 	});
 }


 function searchListJryf( type, time){
 	$("#dialog").dialog({
 		width : 1000, //属性
 		height : 800, //属性
 		title : "罪犯信息",
 		modal : true, //属性
 		autoOpen : false,
 		url : "${ctx}/jyshouye/jryfqk/toIndex?type=" + type + "&time=" + time
 	});
 	$("#dialog").dialog("open");

 }

</script>		
		
