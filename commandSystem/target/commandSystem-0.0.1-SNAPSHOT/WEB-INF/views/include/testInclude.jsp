<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" tagdir="/WEB-INF/tags/security"%>

<li>
	<a href="javascript:void();">
		测试所用
	</a>
	<iframe class="main-iframe"></iframe>
	<ul>
	<!-- 
		<li id="test_tbZfjbxdx" onclick="synchroZfJbxx()">
			<a href="javascript: void(0)">同步罪犯信息</a>
		</li>
		  -->
		<!-- <li id="test_zfPhoto" onclick="openTestDialog(event, 'zfPhoto')">
			<a href="javascript: void(0)">下载罪犯照片</a>
		</li>
		 -->
		 <!-- 
		<li id="test_zfyw" onclick="synchroZfyw('synchroZfyw')">
			<a href="javascript: void(0)">同步罪犯业务数据</a>
		</li>
		<li id="test_zfJbxx" onclick="synchroZfyw('synchroZfjbxx')">
			<a href="javascript: void(0)">同步罪犯基本信息数据</a>
		</li>
		<li id="test_zfJfkhRkh" onclick="synchroZfyw('synchroZfJfkhRkh')">
			<a href="javascript: void(0)">同步日考核数据</a>
		</li>
		<li id="test_zfJfkhYhz" onclick="synchroZfyw('synchroZfJfkhYhz')">
			<a href="javascript: void(0)">同步月考核数据</a>
		</li> -->
		<!-- <li id="test_zfSy" onclick="synchroZfyw('synchroZfsy')">
			<a href="javascript: void(0)">同步罪犯收押数据</a>
		</li>
		<li id="test_zfLj" onclick="synchroZfyw('synchroZfLj')">
			<a href="javascript: void(0)">同步罪犯离监数据</a>
		</li>
		<li id="test_zfShgx" onclick="synchroZfyw('synchroZfShgx')">
			<a href="javascript: void(0)">同步罪犯社会关系数据</a>
		</li>		
		</li>
		<li id="test_zfLbc" onclick="synchroZfyw('synchroZfLbc')">
			<a href="javascript: void(0)">同步罪犯老病残数据</a>
		</li>
		<li id="test_zfRzfp" onclick="synchroZfyw('synchroZfRzfp')">
			<a href="javascript: void(0)">同步罪犯认罪服判数据</a>
		</li>		
		<li id="test_zfTg" onclick="synchroZfyw('synchroZfTg')">
			<a href="javascript: void(0)">同步特管数据</a>
		</li>		
		<li id="test_zfZdzf" onclick="synchroZfyw('synchroZfZdzf')">
			<a href="javascript: void(0)">同步重点罪犯数据</a>
		</li>			
		<li id="test_zfJzzy" onclick="synchroZfyw('synchroZfJzzy')">
			<a href="javascript: void(0)">同步就诊住院数据</a>
		</li>		
		<li id="test_zfJhzs" onclick="synchroZfyw('synchroZfJhzs')">
			<a href="javascript: void(0)">同步解回再审数据</a>
		</li>		
		<li id="test_zfJyzf" onclick="synchroZfyw('synchroZfJyzf')">
			<a href="javascript: void(0)">同步狱外寄押数据</a>
		</li>		
		<li id="test_zfLjtq" onclick="synchroZfyw('synchroZfLjtq')">
			<a href="javascript: void(0)">同步离监探亲数据</a>
		</li>
		<li id="test_zfJzzySj" onclick="synchroZfyw('synchroZfJzzySj')">
			<a href="javascript: void(0)">同步就诊收监数据</a>
		</li>		
		<li id="test_zfZyjwzx" onclick="synchroZfyw('synchroZfZyjwzx')">
			<a href="javascript: void(0)">同步暂予监外执行数据</a>
		</li>
		<li id="test_zfPhoto" onclick="synchroZfyw('synchroZfPhoto')">
			<a href="javascript: void(0)">全量同步罪犯照片数据</a>
		</li>		
		
		<li id="test_zfXfbdJx" onclick="synchroZfyw('synchroZfXfbdJx')">
			<a href="javascript: void(0)">同步刑法变动减刑数据</a>
		</li>		
		<li id="test_zfXfbdJs" onclick="synchroZfyw('synchroZfXfbdJs')">
			<a href="javascript: void(0)">同步刑法变动假释数据</a>
		</li>
		<li id="test_zfYnjcxx" onclick="synchroZfyw('synchroZfYnjcxx')">
			<a href="javascript: void(0)">同步狱内奖惩数据</a>
		</li>
		<li id="test_tsSzbb" onclick="synchroZfyw('tsSzbb')">
			<a href="javascript: void(0)">推送数字冰雹数据</a>
		</li>	-->				
		<!-- 
		<li id="stat_wczf" onclick="synchroZfStat()">
			<a href="javascript: void(0)">华宇：全局罪犯统计数据一键触发</a>
		</li>
		 -->
		<!-- 
		
		 <li id="stat_zyqk" onclick="pushXcjy()">
			<a href="javascript: void(0)">数字冰雹：2.1.战时指挥 - 现场警员</a>
		</li>
		<li id="stat_zyqk" onclick="pushDyjy()">
			<a href="javascript: void(0)">数字冰雹：2.2.战时指挥 - 待援警员</a>
		</li>
		<li id="stat_zyqk" onclick="pushDmcl()">
			<a href="javascript: void(0)">数字冰雹：2.3.战时指挥 - 待命车辆</a>
		</li>
		<li id="stat_zyqk" onclick="pushJwzb()">
			<a href="javascript: void(0)">数字冰雹：2.4.战时指挥 - 警务装备</a>
		</li>
		
		<li id="stat_zyqk" onclick="pushSjxx()">
			<a href="javascript: void(0)">数字冰雹：2.5.战时指挥 - 事件信息</a>
		</li>
		<li id="stat_zyqk" onclick="pushSjlc()">
			<a href="javascript: void(0)">数字冰雹：2.6.战时指挥 - 事件流程</a>
		</li>
		<li id="stat_zyqk" onclick="pushDqlc()">
			<a href="javascript: void(0)">数字冰雹：2.7.战时指挥 - 当前流程</a>
		</li>
		<li id="stat_zyqk" onclick="pushSsbm()">
			<a href="javascript: void(0)">数字冰雹：2.8.战时指挥 - 涉事部位</a>
		</li> -->
		<!--
		<li id="pushJypm" onclick="pushJypm()">
			<a href="javascript: void(0)">数字冰雹：3.1.风险评估-监狱排名</a>
		</li>
		
		<li id="stat_zyqk" onclick="pushFxqd()">
			<a href="javascript: void(0)">数字冰雹：3.2.风险评估-风险清单</a>
		</li>
		<li id="stat_zyqk" onclick="pushPffx()">
			<a href="javascript: void(0)">数字冰雹：3.3.风险评估-频发风险</a>
		</li>
		<li id="stat_zyqk" onclick="pushFxdj()">
			<a href="javascript: void(0)">数字冰雹：3.4.风险评估-风险等级</a>
		</li>
		<li id="stat_zyqk" onclick="pushQbfxd()">
			<a href="javascript: void(0)">数字冰雹：3.5.风险评估-全部风险点</a>
		</li>
		<li id="stat_zyqk" onclick="pushDqfxd()">
			<a href="javascript: void(0)">数字冰雹：3.6.风险评估-当前发生风险点</a>
		</li>
		<li id="stat_zyqk" onclick="pushDqfxxq()">
			<a href="javascript: void(0)">数字冰雹：3.7.风险评估-当前发生风险详情</a>
		</li>
		<li id="stat_zyqk" onclick="pushWgfx()">
			<a href="javascript: void(0)">数字冰雹：3.8.风险评估-网格风险</a>
		</li>
		<li id="stat_zyqk" onclick="pushWgfxpg()">
			<a href="javascript: void(0)">数字冰雹：3.9.风险评估-网格风险评估</a>
		</li>
		<li id="pushWgkf" onclick="pushWgkf()">
			<a href="javascript: void(0)">数字冰雹：3.10.风险评估-网格扣分</a>
		</li>
		
		<li id="pushZfbh" onclick="pushZfbh()">
			<a href="javascript: void(0)">数字冰雹：4.1.领导管理驾驶舱 - 罪犯变化</a>
		</li>
		<li id="stat_zyqk" onclick="pushZfnldb()">
			<a href="javascript: void(0)">数字冰雹：4.2.领导管理驾驶舱 - 罪犯年龄对比</a>
		</li>
		<li id="stat_zyqk" onclick="pushZflx()">
			<a href="javascript: void(0)">数字冰雹：4.3.领导管理驾驶舱 - 罪犯类型</a>
		</li>
		
		<li id="stat_zyqk" onclick="pushDwjs()">
			<a href="javascript: void(0)">数字冰雹：4.4.领导管理驾驶舱 - 队伍建设</a>
		</li>
		<li id="stat_zyqk" onclick="pushGbth()">
			<a href="javascript: void(0)">数字冰雹：4.5.领导管理驾驶舱 - 个别谈话</a>
		</li>
		<li id="stat_zyqk" onclick="pushQqdh()">
			<a href="javascript: void(0)">数字冰雹：4.6.领导管理驾驶舱 - 亲情电话</a>
		</li>
		<li id="stat_zyqk" onclick="pushXfzx()">
			<a href="javascript: void(0)">数字冰雹：4.7.领导管理驾驶舱 - 刑罚执行</a>
		</li>-->
		<!-- 
		<li id="stat_zyqk" onclick="pushLdgj()">
			<a href="javascript: void(0)">数字冰雹：4.8.领导管理驾驶舱 - 劳动工具</a>
		</li> 
		
		<li id="stat_zyqk" onclick="pushDzwp()">
			<a href="javascript: void(0)">数字冰雹：4.9.领导管理驾驶舱 - 大宗物品</a>
		</li>
		<li id="stat_zyqk" onclick="pushJytj()">
			<a href="javascript: void(0)">数字冰雹：4.10.领导管理驾驶舱 - 就医统计</a>
		</li>
		<li id="stat_zyqk" onclick="pushZzjg()">
			<a href="javascript: void(0)">数字冰雹：4.11.领导管理驾驶舱 - 组织结构</a>
		</li>
		<li id="stat_zyqk" onclick="pushJrzb()">
			<a href="javascript: void(0)">数字冰雹：1.1.日常管控-今日值班</a>
		</li>
		<li id="stat_zyqk" onclick="pushRcJrgk()">
			<a href="javascript: void(0)">数字冰雹：1.2.日常管控-今日概况</a>
		</li>-->
		<!--  
		<li id="stat_zyqk" onclick="pushQygk()">
			<a href="javascript: void(0)">数字冰雹：1.3.日常管控-区域管控</a>
		</li>
		<li id="stat_zyqk" onclick="pushZdzf()">
			<a href="javascript: void(0)">数字冰雹：1.4.日常管控-重点罪犯</a>
		</li>
		<li id="stat_zyqk" onclick="pushDmcr()">
			<a href="javascript: void(0)">数字冰雹：1.5.日常管控-大门出入</a>
		</li>-->
		<!-- 
		<li id="stat_zyqk" onclick="pushRlsb()">
			<a href="javascript: void(0)">数字冰雹：1.6.日常管控-人脸识别</a>
		</li>
		<li id="stat_zyqk" onclick="pushZjaf()">
			<a href="javascript: void(0)">数字冰雹：1.7.日常管控-周界安防</a>
		</li>
		<li id="stat_zyqk" onclick="pushSjgk()">
			<a href="javascript: void(0)">数字冰雹：1.8.日常管控-手机管控</a>
		</li>
		<li id="stat_zyqk" onclick="pushYwyjrw()">
			<a href="javascript: void(0)">数字冰雹：1.9.日常管控-押解任务</a>
		</li>
		<li id="stat_zyqk" onclick="pushYwyjclwz()">
			<a href="javascript: void(0)">数字冰雹：1.10.日常管控-押解车辆</a>
		</li>
		<li id="synchroYyjyZzjg" onclick="synchroYyjy('synchroYyjyZzjg')">
			<a href="javascript: void(0)">synchroYyjyZzjg</a>
		</li> 
		<li id="synchroYyjyDrKqjl" onclick="synchroYyjy('synchroYyjyDrKqjl')">
			<a href="javascript: void(0)">synchroYyjyDrKqjl</a>
		</li> -->
	</ul>
</li>
<script>

	function synchroYyjy(type) {
		var urlPath = jsConst.basePath + "/xxhj/mjkq/" + type;
		alert(urlPath);
		var params = {}; 
		// Desc: 同步岳阳监狱考勤
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
				alert('success');
	             if(data.code == 200) { 
	            	console.log(data.data);
	             } else if (data.code == 500){
	            	console.log(data.data);
	            } 
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	};

/**
 * 同步罪犯业务数据
 */
	function synchroZfyw(type) {
		var urlPath = jsConst.basePath + "/zfxx/zfShgx/" + type;
		alert(urlPath);
		var params = {}; 
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
				data : params,
			dataType : 'json',
			success : function(data) {
				alert('success');
	             if(data.code == 200) { 
	            	console.log(data.data);
	             } else if (data.code == 500){
	            	console.log(data.data);
	            } 
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	};
	
	function openTestDialog(event, name) {
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
		if (name == 'zfPhoto') {
			url = jsConst.basePath+'/zfxx/zfJbxx/openDialog';
			w = 1000;
			h = 600;
		}
	
	
		if (w == null || h == null) {
			w = 900;
			h = 600;
		}
	
	    $('#dialog').html("");
		$('#dialog').dialog({
			width : w,
			height : h,
			title : $("#test_" + name + " a").text(),
			url : url
		});
		$("#dialog").dialog("open");
		return;
	}

	/**
	 * 测试同步【外出罪犯】信息
	 */
	function synchroZfStat() {
		var urlPath = jsConst.basePath + "/zfxx/zfStat/synchroZfStat";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 2.测试同步【暂押罪犯】信息
	 */
	function synchroZfStatZy() {
		var urlPath = jsConst.basePath + "/zfxx/zfStatZy/synchroZfStatZy";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 3.测试同步【罪犯调动】信息
	 */
	function synchroZfStatZfdd() {
		var urlPath = jsConst.basePath + "/zfxx/zfStatZy/synchroZfStatZfdd";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	
	/**
	 * 4.测试同步【暂押罪犯】信息
	 */
	function synchroZfStatZyqk() {
		var urlPath = jsConst.basePath + "/zfxx/zfStatZy/synchroZfStatZyqk";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 5.测试同步【性别罪犯】信息
	 */
	function synchroZfStatZyXb() {
		var urlPath = jsConst.basePath + "/zfxx/zfStatZy/synchroZfStatZyXb";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 6.测试同步【罪犯年龄】信息
	 */
	function synchroZfStatZyNl() {
		var urlPath = jsConst.basePath + "/zfxx/zfStatZy/synchroZfStatZyNl";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 7.测试同步【罪名】信息
	 */
	function synchroZfStatZyZm() {
		var urlPath = jsConst.basePath + "/zfxx/zfStatZy/synchroZfStatZyZm";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 8.测试同步【原判刑期】信息
	 */
	function synchroZfStatZyYpxq() {
		var urlPath = jsConst.basePath + "/zfxx/zfStatZy/synchroZfStatZyYpxq";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 9.测试同步【剩余刑期】信息
	 */
	function synchroZfStatZySyxq() {
		var urlPath = jsConst.basePath + "/zfxx/zfStatZy/synchroZfStatZySyxq";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 10.测试同步【监狱关押能力】信息
	 */
	function synchroZfStatZyJyGynl() {
		var urlPath = jsConst.basePath + "/zfxx/zfStatZy/synchroZfStatZyJyGynl";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 11.测试同步【各监狱在押人数】信息
	 */
	function synchroZfStatZyJygyqk() {
		var urlPath = jsConst.basePath + "/zfxx/zfStatZy/synchroZfStatZyJygyqk";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 12.测试同步【各监狱改造情况】信息
	 */
	function synchroZfStatZyJygzqk() {
		var urlPath = jsConst.basePath + "/zfxx/zfStatZy/synchroZfStatZyJygzqk";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	
	/**
	 * 数字冰雹：8.日常管控-手机管控
	 */
	function pushSjgk() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/pushSjgk";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	


	/**
	 * 测试同步罪犯基本信息用到
	 */
	function synchroZfJbxx() {
		var urlPath = jsConst.basePath + "/zfxx/zfJbxx/synchroZfJbxx";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：1.今日值班信息接口
	 */
	function pushJrzb() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/rcgk/pushJrzb";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
				alert('success');
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：2.日常管控-今日概况
	 */
	function pushRcJrgk() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/rcgk/pushJrgk";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
				alert('success');
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	
	
	/**
	 * 数字冰雹：3.日常管控-今日概况
	 */
	function pushQygk() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/rcgk/pushQygk";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：4.日常管控-今日概况
	 */
	function pushZdzf() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/rcgk/pushZdzf";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：5.日常管控-今日概况
	 */
	function pushDmcr() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/rcgk/pushDmcr";
		alert(urlPath);
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：6.日常管控-今日概况
	 */
	function pushRlsb() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/rcgk/pushRlsb";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：7.日常管控-今日概况
	 */
	function pushZjaf() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/rcgk/pushZjaf";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：8.日常管控-今日概况
	 */
	function pushSjgk() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/rcgk/pushSjgk";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：9.日常管控-今日概况
	 */
	function pushYwyjrw() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/rcgk/pushYwyjrw";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：10.日常管控-今日概况
	 */
	function pushYwyjclwz() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/rcgk/pushYwyjclwz";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：2.1.战时指挥 - 现场警员
	 */
	function pushXcjy() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/zszh/pushXcjy";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：2.2.战时指挥 - 待援警员
	 */
	function pushDyjy() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/zszh/pushDyjy";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：2.3.战时指挥 - 待命车辆
	 */
	function pushDmcl() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/zszh/pushDmcl";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：2.4.战时指挥 - 警务装备
	 */
	function pushJwzb() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/zszh/pushJwzb";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：2.5.战时指挥 - 事件信息
	 */
	function pushSjxx() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/zszh/pushSjxx";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：2.6.战时指挥 - 事件流程
	 */
	function pushSjlc() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/zszh/pushSjlc";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：2.7.战时指挥 - 当前流程
	 */
	function pushDqlc() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/zszh/pushDqlc";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：2.8.战时指挥 - 涉事部位
	 */
	function pushSsbm() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/zszh/pushSsbm";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：3.1.风险评估-监狱排名
	 */
	function pushJypm() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/fxpg/pushJypm";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：3.2.风险评估-风险清单
	 */
	function pushFxqd() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/fxpg/pushFxqd";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：3.3.风险评估-频发风险
	 */
	function pushPffx() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/fxpg/pushPffx";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：3.4.风险评估-风险等级
	 */
	function pushFxdj() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/fxpg/pushFxdj";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：3.5.风险评估-全部风险点
	 */
	function pushQbfxd() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/fxpg/pushQbfxd";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：3.6.风险评估-当前发生风险点
	 */
	function pushDqfxd() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/fxpg/pushDqfxd";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：3.7.风险评估-当前发生风险详情
	 */
	function pushDqfxxq() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/fxpg/pushDqfxxq";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：3.8.风险评估-网格风险
	 */
	function pushWgfx() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/fxpg/pushWgfx";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：3.9.风险评估-网格风险评估
	 */
	function pushWgfxpg() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/fxpg/pushWgfxpg";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：3.10.风险评估-网格扣分
	 */
	function pushWgkf() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/fxpg/pushWgkf";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：4.1.领导管理驾驶舱 - 罪犯变化
	 */
	function pushZfbh() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/ldgl/pushZfbh";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：4.2.领导管理驾驶舱 - 罪犯年龄对比
	 */
	function pushZfnldb() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/ldgl/pushZfnldb";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：4.3.领导管理驾驶舱 - 罪犯类型
	 */
	function pushZflx() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/ldgl/pushZflx";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：4.4.领导管理驾驶舱 - 队伍建设
	 */
	function pushDwjs() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/ldgl/pushDwjs";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：4.5.领导管理驾驶舱 - 个别谈话
	 */
	function pushGbth() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/ldgl/pushGbth";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：4.6.领导管理驾驶舱 - 亲情电话
	 */
	function pushQqdh() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/ldgl/pushQqdh";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：4.7.领导管理驾驶舱 - 刑罚执行
	 */
	function pushXfzx() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/ldgl/pushXfzx";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：4.8.领导管理驾驶舱 - 劳动工具
	 */
	function pushLdgj() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/ldgl/pushLdgj";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：4.9.领导管理驾驶舱 - 大宗物品
	 */
	function pushDzwp() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/ldgl/pushDzwp";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：4.10.领导管理驾驶舱 - 就医统计
	 */
	function pushJytj() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/ldgl/pushJytj";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * 数字冰雹：4.11.领导管理驾驶舱 - 组织结构
	 */
	function pushZzjg() {
		var urlPath = jsConst.basePath + "/zfxx/szbb/ldgl/pushZzjg";
	    var params = {};
		// Desc: 测试同步罪犯基本信息用到
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	
</script>