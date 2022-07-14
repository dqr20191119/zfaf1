<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security" %>
<sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl">
<li id="nav-xtgl">
  <a href="javascript:void(0)" data-toggle="dropdown" class="dropdown-toggle">
     系统管理
  </a>
  <iframe class="main-iframe"></iframe>
  <ul>
	  <sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/sbgl">
	  <li id="xtgl_jfsb">
         <a href="javascript: void(0)">
                    设备管理
         <b class="leftArrow"></b>
         </a>
         <iframe class="sub-iframe"></iframe>
	   	  <ul>
			 <sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/sbgl/sxjgl">
             <li id="xxgl_camera" onclick="openXxglDialog(event,'camera')">
                 <a href="#">摄像机管理</a>
             </li>
             </sec:authorize>
			 <sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/sbgl/yplxjgl">
             <li id="xxgl_videoDevice" onclick="openXxglDialog(event,'videoDevice')">
                 <a href="#">硬盘录像机管理</a>
             </li>
             </sec:authorize>
			 <sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/sbgl/lmtfwgl">
             <li id="xxgl_streamServer" onclick="openXxglDialog(event,'streamServer')">
                 <a href="#">流媒体服务管理</a>
             </li>
          	 </sec:authorize>
			 <sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/sbgl/dwgl">
             <li id="xxgl_powerNetwork" onclick="openXxglDialog(event,'powerNetwork')">
                 <a href="#">电网管理</a>
             </li>
             </sec:authorize>
			 <sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/sbgl/bjqgl">
             <li id="xxgl_alertor" onclick="openXxglDialog(event,'alertor')">
                 <a href="javascript:void(0)">报警器管理</a>
             </li>
             </sec:authorize>
			 <sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/sbgl/mjkzqgl">
           <li id="xxgl_doorCtrl" onclick="openXxglDialog(event,'doorCtrl')">
                 <a href="javascript:void(0)">门禁控制器管理</a>
             </li>
             </sec:authorize>
			 <sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/sbgl/mjgl">
             <li id="xxgl_door" onclick="openXxglDialog(event,'door')">
                 <a href="javascript:void(0)">门禁管理</a>
             </li>
             </sec:authorize>
              <sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/sbgl/mjkqmmgl">
             <li id="xxgl_dooropen" onclick="openXxglDialog(event,'dooropen')">
                 <a href="javascript:void(0)">门禁开启密码管理</a>
             </li>
             </sec:authorize>
			 <sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/sbgl/djgl">
	          <li>
		          <a href="javascript:void(0);">对讲管理</a>
		          <b class="leftArrow"></b>
		          <iframe class="sub-iframe"></iframe>
	     	    <ul>
				  <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/sbgl/djgl/zjgl">
	              <li id="xxgl_talkBackServer" onclick="openXxglDialog(event,'talkBackServer')">
	                  <a href="#">主机管理</a>
	              </li>
	              </sec:authorize>
				  <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/sbgl/djgl/fjgl">
	              <li id="xxgl_talkBackBase" onclick="openXxglDialog(event,'talkBackBase')">
	                  <a href="#">分机管理</a>
	              </li>
	              </sec:authorize>
	        	</ul>
	    	</li>
	    	</sec:authorize>
	    	
			<sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/sbgl/gbgl">
			<li>
				<a href="javascript:void(0);">广播管理</a>
				<b class="leftArrow"></b>
				<iframe class="sub-iframe"></iframe>
				<ul>
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/sbgl/gbgl/gbsbwh">
					<li id="xxgl_broadcast" onclick="openXxglDialog(event,'broadcast')">
						<a href="#">广播设备维护</a>
					</li>
					</sec:authorize>
					<sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/sbgl/gbgl/gbqmwh">
					<li id="xxgl_broadcastFile" onclick="openXxglDialog(event,'broadcastFile')">
						<a href="#">广播曲目维护</a>
					</li>
					</sec:authorize>
				</ul>
			</li>
			</sec:authorize>
	    </ul>
	</li>
	</sec:authorize>
	
	<sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/qygl">
    <li id="xtgl_qywh" onclick="">
       <a href="javascript:void(0)">
                区域管理
           <b class="leftArrow"></b>
       </a>
       <iframe class="sub-iframe"></iframe>
        <ul>
		   <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/qygl/qywh">
           <li id="xxgl_qywh" onclick="toXxglDisplay('qywh')">
               <a href="javascript:void(0);">区域维护</a>
           </li>
           </sec:authorize>
		   <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/qygl/jswh">
			<li id="xxgl_jswh" onclick="openXxglDialog(event,'jswh')">
				<a href="javascript:void(0);">监舍维护</a>
			</li>
			</sec:authorize>
		   <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/qygl/qybmwh">
            <li id="xxgl_regionDepart" onclick="openXxglDialog(event,'regionDepart')">
                <a href="javascript:void(0)">区域部门维护</a>
            </li>
            </sec:authorize>
		   <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/qygl/sjrywh">
            <li id="xxgl_viewPeople" onclick="openXxglDialog(event,'viewPeople')">
                <a href="javascript:void(0)">视角人员维护</a>
            </li>
            </sec:authorize>
		   <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/qygl/lxpz">
            <li id="xxgl_prisonPath" onclick="openXxglDialog(event,'prisonPath')">
                <a href="javascript:void(0)">路线配置</a>
            </li>
            </sec:authorize>
        </ul>
    </li>
	</sec:authorize>
	
	<sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/swdtwh">
    <li id="xtgl_swdtwh" onclick="">
	    <a href="javascript:void(0)">
	              三维地图维护
	         <b class="leftArrow"></b>
	     </a>
	     <iframe class="sub-iframe"></iframe>
        <ul>
		   <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/swdtwh/mxwh">
           <li id="xxgl_mxwh" onclick="toXxglDisplay('mxwh')">
               <a href="javascript:void(0);">模型维护</a>
           </li>
		   </sec:authorize>
		   <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/swdtwh/sjwh">
           <li id="xxgl_sjwh" onclick="toXxglDisplay('sjwh')">
               <a href="javascript:void(0);">视角维护</a>
           </li>
           </sec:authorize>
		   <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/swdtwh/dwwh">
           <li id="xxgl_dwwh" onclick="toXxglDisplay('dwwh')">
               <a href="javascript:void(0);">点位维护</a>
           </li>
           </sec:authorize>
		   <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/swdtwh/tcwh">
           <li id="xxgl_bjtcwh" onclick="toXxglDisplay('bjtcwh')">
               <a href="javascript:void(0);">图层维护</a>
           </li>
           </sec:authorize>
		   <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/swdtwh/xswh">
           <li id="xxgl_xswh" onclick="toXxglDisplay('xswh')">
               <a href="javascript:void(0);">巡视维护</a>
           </li>
           </sec:authorize>
		   <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/swdtwh/bqwh">
           <li id="xxgl_bqwh" onclick="toXxglDisplay('bqwh')">
               <a href="javascript:void(0);">标签维护</a>
           </li>
           </sec:authorize>
		   <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/swdtwh/jsxxwh">
           <li id="xxgl_jsxxdwwh" onclick="toXxglDisplay('jsxxdwwh')">
               <a href="javascript:void(0);">监舍信息维护</a>
           </li>
           </sec:authorize>
        </ul>
    </li>
	</sec:authorize>
	
	<sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/ewdtwh">
    <li id="xtgl_ewdtwh" onclick="">
		<a href="javascript:void(0)">
                二维地图维护
           <b class="leftArrow"></b>
       </a>
       <iframe class="sub-iframe"></iframe>
        <ul>
		   <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/ewdtwh/tcwh">
	        <li id="xxgl_ewtcwh" onclick="openXxglDialog(event,'ewtcwh')">
	            <a href="javascript:void(0);">图层维护</a>
	        </li>
	        </sec:authorize>
		   <sec:authorize url="/zhdd/zy/zhaf/znafpt/aqltfk/xtgl/ewdtwh/dwwh">
	        <li id="xxgl_ewdwwh" onclick="openXxglDialog(event,'ewdwwh')">
	            <a href="javascript:void(0);">点位维护</a>
	        </li>
	        </sec:authorize>
        </ul>
    </li>
    </sec:authorize>
    
    
	<sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/xxdy">
    <li id="xxdy" onclick="openXxglDialog(event, 'xxdy')">
        <a href="javascript:void(0)">消息订阅</a>
    </li>
 	</sec:authorize>
	<sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/rzcx">
     <li id="crontab" onclick="openXxglDialog(event,'rzcx')">
         <a href="javascript:void(0)">日志查询</a>
     </li>
      </sec:authorize>
      <sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/rztbfx">
     <li id="crontab" onclick="openXxglDialog(event,'rztb')">
         <a href="javascript:void(0)">日志图表分析</a>
     </li>
     </sec:authorize>
        <sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/rztj">
      <li id="crontab" onclick="openXxglDialog(event,'rztj')">
         <a href="javascript:void(0)">日志统计</a>
     </li>
 	</sec:authorize>
 	 <!-- TODO 由于角色全部用的是工程师角色，定时任务菜单暂时注释，防止用户误操作 -->
	 <sec:authorize url="/zhdd/sy/afzhddpt/afzh/xtgl/dsrw">
     <li id="crontab" onclick="openXxglDialog(event,'crontab')">
         <a href="javascript:void(0)">定时任务</a>
     </li>
     </sec:authorize>
    </ul>
</li>
</sec:authorize>


<script>
    function openXxglDialog(event, name) {
        var event = window.event || event;
        //event.stopPropagation();
        if (event && event.stopPropagation) {
            event.stopPropagation();
        } else {
            window.event.cancelBubble = true;
        }
        //event.preventDefault();
        var url = "";
        var w = null;
        var h = null;
        if (name == 'camera') {
            w = 1500;
            h = 700;
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
        } else if (name == 'broadcastFile') {
            url = jsConst.basePath + '/broadcastFile/openDialog'
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
        }else if (name == 'doorCtrl') {
            url = jsConst.basePath + '/doorControl/openDialog'
        }
        else if(name=='dooropen'){
        	url = jsConst.basePath + '/dooropen/openDialog'
        }else if (name == 'screenPlan') {
            url = jsConst.basePath + '/screenPlan/openDialog'
        } else if (name == 'ewtcwh') {
            url = jsConst.basePath + '/xtgl/planeLayer/index'
        } else if (name == 'ewdwwh') {////二维图层点位维护
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
         // url = jsConst.basePath + '/jobs/index';
        } else if (name == "viewPeople") {
            url = jsConst.basePath + '/viewPeople/index';
        }else if (name == "rzcx") {
            url = jsConst.basePath + '/xxhj/rzcx/toIndex';
        }else if (name == "rztb") {//日志图表
            url = jsConst.basePath + '/xxhj/rzcx/toIndextb';
        }else if (name == "rztj") {//日志统计
            url = jsConst.basePath + '/xxhj/rzcx/toIndextj';
        }

        $('#dialog').html("");
//        $('#dialog').dialog("destroy");
        if (w == null || h == null) {
            w = 1000;
            h = 600;
        }

        $('#dialog').dialog({
            width: w,
            height: h,
            title: $("#xxgl_" + name + " a").text(),
            url: url
        });
        $("#dialog").dialog("open");
        return;
    }

    function toXxglDisplay(name) {
        var panel = $("#layout1").layout("panel", "east");
        if (name == "111") {
            //	panel.panel("refresh", "${ctx}/menubar/index");
            alert("111");
        }
        //Begin add by linhe for 3d map manage 2018-1-11
        if (jsConst.MAP_TYPE == 0 && name != 'qywh') {
            $.messageQueue({message: "请先切换为三维地图", cls: "warning", iframePanel: true, type: "info"});
            return;
        }

        //模型维护
        if (name == 'mxwh') {
            panel.panel("refresh", jsConst.basePath + "/model/index");
        } else if (name == 'qywh') {//区域维护
            panel.panel("refresh", jsConst.basePath + "/region/index");
        } else if (name == 'sjwh') {//视角维护
            panel.panel("refresh", jsConst.basePath + "/view/index");
        } else if (name == 'dwwh') {//点位维护
            panel.panel("refresh", jsConst.basePath + "/point/index");
        } else if (name == 'bjtcwh') {//报警图层维护
            panel.panel("refresh", jsConst.basePath + "/layer/index");
        } else if (name == 'xswh') {//巡视维护
            panel.panel("refresh", jsConst.basePath + "/route/walkManager");
        } else if (name == 'bqwh') {//标签维护
            panel.panel("refresh", jsConst.basePath + "/labels/labelManager");
        } else if (name == 'jsxxdwwh') {//监舍信息点位维护
            panel.panel("refresh", jsConst.basePath + "/point/index2");
        }
    }
</script>