<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<link href="${ctx}/static/css/callNames_new.css" type="text/css" rel="stylesheet" />
<body>
	<div class="layout-wrap-dianming">
		<div class="layout-container clearfix">
			<div class="layout-main">
				<div id="tree_div" class="layout-left">
					<cui:tree id="callNames_regionTree" simpleDataEnable="true" asyncAutoParam="id,name" asyncType="get" asyncEnable="true" onDblClick="findCellByRegion">
					</cui:tree>
				</div>
				<div class="callNames-right">
					<div id="callNames-content_1" class="callNames-content" style="display: none;">
						<h3 id="left_title_1" class="left_title">&nbsp;</h3>
						<ul id="box_1" class="js_box specialbox1">
						</ul>
					</div>
					<div id="callNames-content_2" class="callNames-content" style="display: none;">
						<h3 id="left_title_2" class="left_title">&nbsp;</h3>
						<ul id="box_2" class="js_box">
						</ul>
					</div>
					<div id="callNames-content_3" class="callNames-content" style="display: none;">
						<h3 id="left_title_3" class="left_title">&nbsp;</h3>
						<ul id="box_3" class="js_box">
						</ul>
					</div>
					<div id="callNames-content_notDate" class="callNames-content" style="display: none;">
						<h3 id="left_title_notDate" class="left_title">&nbsp;</h3>
						<span class='notData'> 未初始化监舍信息...</span>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>

<cui:dialog id="dialogId_rollcall" iframePanel="true" autoOpen="false" resizable="false" reLoadOnOpen="true" modal="true" onClose="dialogId_rollcall_close"></cui:dialog>

<script>
	var jsConst = window.top.jsConst;
	var userLev = jsConst.USER_LEVEL;
	var cusNumber = jsConst.CUS_NUMBER;//监狱号
	var isCallNaming = false;//是否正在点名
	var isBeging = false;//是否正在请求点名
	
	var si_getEndRollcallList = null;//罪犯界面轮询
	var endSi = null;//监舍详情轮询
	var si_start = null;//开始点名轮询
	
	$.parseDone(function() {
		$("#callNames_regionTree").tree( "reload", "${ctx}/callNames/master/findForTree?cusNumber=" + cusNumber + "&userLev=" + userLev);
		$(".layout-main #tree_div").mCustomScrollbar({
			theme : "minimal-light",
			autoExpandScrollbar : true
		});
		
	});
	$("#dialog").dialog({
		 onClose : function() {            //回调事件
			 if(endSi != null){
			 	clearInterval(endSi);  
			 } //监舍详情轮询
			 if(si_start = null){
				clearInterval(si_start);	 
			 }//开始点名轮询
		 }

	});
	
	
	
	function dialogId_rollcall_close() {
		if(si_getEndRollcallList != null){
			clearInterval(si_getEndRollcallList); 
		}
	}
	
	function isEmpty(_v) {
		if (_v == undefined)
			return true;
		if (_v == null)
			return true;
		if (_v == "undefined")
			return true;
		if (_v == "")
			return true;
		return false;
	}

	function findCellByRegion(event, id, node) {
		if(isBeging){
			alert("正在点名，请等待点名请求结束后继续操作！！");
			return;
		}
		var dempt = null;
		var area = null;
		var cusNumber_ = node.jyh;
		var para = node.node_lev;
		if (!isEmpty(para)) {
			switch (para) {
			case "1":
				//cusNumber_ = node.jyh;
				break;
			case "2":
				//cusNumber_ = node.jyh;
				dempt = node.id;
				break;
			case "3":
				dempt = node.pId;
				area = node.id;
				break;
			default:
				return;
			}

			var data = {};
			if (!isEmpty(area)) {
				data['lch'] = area;
			}
			if (!isEmpty(dempt)) {
				data['jqh'] = dempt;
			}
			if (!isEmpty(cusNumber_)) {
				data['cusNumber'] = cusNumber_;
			}
			if (!isEmpty(para)) {
				data['para'] = para;
			}
			
			if(para =='1' || para == '2'){
				return;
			}
 
			$(".callNames-content").hide();

			$(".callNames-right").loading({
				text : "加载中。。。。",
				loadingIcon : "coral-icon-loading"
			});
			$.ajax({
				type : "post",
				url : "${ctx}/callNames/master/findJsAndZfsByLc.json",
				data : data,
				dataType : "json",
				success : function(data) {
					if(endSi != null){
						clearInterval(endSi); //点名过程中如果点击左侧区域树，重新创建界面时结束轮询
					}
					
					if(si_start != null){
						clearInterval(si_start);//点名过程中如果点击左侧区域树，重新创建界面时结束轮询					
					}
					
					creatHtmlForShow(para, node, data);
					$(".callNames-right").loading("hide");
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});

		}

	}

	function creatHtmlForShow(para, node, data) {
		if (para == "3") {
			$("#left_title_" + para).text(node.name);
			$("#box_3").empty()
			
			if (data != undefined && data != "undefined" && data.length > 0) {
				var $btnDiv = $("<div class='btn_div'></div>")
				if(isCallNamesIng(node.jyh,node.id)){
					//var $span = '<span style="color: red;"><strong>正在点名中...</strong></span>';
				    var $spanAndBtn = '<button id="button_end" >停止点名</button>&nbsp;&nbsp;&nbsp;<span style="color: #fff;"><strong>正在点名中...</strong></span>';
				    $(".btn_div").append($spanAndBtn);
					$btnDiv.append($spanAndBtn);
				}else{
					var $inputAndBtn = '<input id="textbox" class="textbox">&nbsp;&nbsp;&nbsp;<button id="button" class="button">开始点名</button>';
					$btnDiv.append($inputAndBtn);
				}
					$("#box_3").append($btnDiv);
				
				
				for (var i = 0; i < data.length; i++) {
					var cell = data[i];
					if(cell.ZFS != 0 || cell.ZFS != '0'){
						var div = " <li class=\""+ cell.LCH +"\"> <div class=\"zt_title\"> <p>"
						+ cell.JSH
						+ "监舍</p>  </div> <div id=\"" + cell.LCH + "-" + cell.JSH  
						+"\" class=\"zt_h\"> <a href=\"#\" onclick=\"openRollcallDailog('" + cell.JYH + "','" + cell.LCH + "','" + cell.JSH + "')\">"
						+ "<div> <p> <span class =\"peopleTotal\">0</span> /" + cell.ZFS
						+ "</p> </div> </a> <p class=\"rollcallSatus\">状态 : 未完成</p> </div>"
						+ "<input type=\"text\" value=\""+cell.JSH+"\" class=\""+ cell.LCH +"-jshInput\" style=\"display:none\"></input>" 
						+ "<input type=\"text\" value=\"0\" id=\"" + cell.LCH + "-" + cell.JSH + "-state\" style=\"display:none\"></input>"
						+"</li>";
						$("#box_3").append(div);	
					}else{
						var div = " <li class=\""+ cell.LCH +"\"> <div class=\"zt_title\"> <p>"
						+ cell.JSH
						+ "监舍</p>  </div> <div id=\"" + cell.LCH + "-" + cell.JSH  
						+"\" class=\"zt_none\"> "
						+ "<div> <p>罪犯信息未注册，请注册</p> </div> </div> </li>";
						$("#box_3").append(div);	
					}
					
				}
				$("#callNames-content_" + para).mCustomScrollbar({
						theme : "minimal-light",
						autoExpandScrollbar : true
				}); 
				
				$('#textbox').textbox({ validType: "naturalnumber" });
				
				$('#button').button({
					    onClick: function(e, ui) {  
					    	var jshs = []; //监舍号集合
					    	var time = $('#textbox').textbox("getText");
					    	
					    	if (!(/(^[1-9]\d*$)/.test(time))) { 
						    	$.messageQueue({ message : "点名时长: 请输入大于等于1的正整数", cls : "warning", iframePanel : true, type : "info" });
					    	　　   return; 
					    	}
					    	
					    	$("."+node.id +"-jshInput").each(function() {
					    		if($("#"+node.id+"-"+this.value).hasClass('zt_h')){
						    		jshs.push(this.value);
								}
							});
							
							if(jshs.length == 0){
								$.messageQueue({ message : "该楼层监舍均未注册罪犯信息，请注册后操作", cls : "warning", iframePanel : true, type : "info" });
								return;
							}
					    	beginCallNames(node.jyh,node.id + "",time);
					    }
					 });

				 $('#button_end').button({
					    onClick: function(e, ui) {  
					    	endCallNames(node.jyh,node.id + "");
					    },
					 });
				
				$("#callNames-content_" + para).show();
				
				if(isCallNaming){
					getCallNamesDtlsByJs(node.jyh,node.id + "");//正在点名就直接后台获取监舍点名信息
				}
			} else {
				$("#left_title_notDate").text(node.name);
				$("#callNames-content_notDate").show();
			}
		}
	}

	function beginCallNames(cusNumber_,lch_,duration_) {
		$.confirm("是否开始点名", "信息确认", function(confirm) {
			if (confirm) {
				var data = {};
				data['lch'] = lch_;
				data['cusNumber'] = cusNumber_;
				data['duration'] = duration_;
				$.ajax({
					type : "post",
					url : "${ctx}/callNames/master/beginCallNames.json",
					data : data,
					dataType : "json",
					success : function(data) {
						if (data.success) {
							isBeging = true;
							
							$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
							$(".btn_div").empty();
							var $span = '<span style="color: #fff;"><strong>正在启动点名，请稍后...</strong></span>';
							$(".btn_div").append($span);
						 
							getCallNamesDtlsByLc(cusNumber_,lch_);//轮询查询点名是否成功
							 
						} else {
							$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
						} 
					},
					error : function(XMLHttpRequest, textStatus, errorThrown) {
						$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
					}
				});
			}
		});
	}
	
	//判断楼层是否正在点名
	function isCallNamesIng(jyh_,lch_) {
		var isEnd = 0;
		var data = {};
		data['cnmCusNumber'] = jyh_;
		data['cnmLch'] = lch_;
		data['cnmIsEnd'] = "0";
		$.ajax({
			type : "post",
			url : "${ctx}/callNames/master/isCallNamesIng.json",
			data : data,
			dataType : "json",
			async : false,
			success : function(data) {
				if (data.success) {
					isEnd = data.obj;
				}  
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			
			}
		});
		isCallNaming = isEnd == 1 ? true : false;
		return  isCallNaming;
	}
	
	 

	function getCallNamesDtlsByLc(jyh_,lch_){
		si_start = setInterval(function(){
			var data = {};
			data['jyh'] = jyh_;
			data['lch'] = lch_;
			$.ajax({
				type : "post",
				url : "${ctx}/callNames/master/getCallNamesDtlsByLc.json",
				data : data,
				dataType : "json",
				success : function(data) {
					if (data.success) {
						/* debugger; */
						 var JsonRootBean = data.obj;
						 if(JsonRootBean == null || JsonRootBean.jsTotal == '0'){
							 isBeging = false;
							 $(".btn_div").empty();
							 var $span = '<span style="color: #fff;"><strong>点名发起失败。</strong></span>';
							 $(".btn_div").append($span);
							 clearInterval(si_start);
							 si_start = null; 
						 }
						 if(JsonRootBean.jsTotal != '0' && JsonRootBean.jsTotal != null){
							 $(".btn_div").empty();
							 var $spanAndBtn = '<button id="button_end" >停止点名</button>&nbsp;&nbsp;&nbsp;<span style="color: #fff;"><strong>正在点名中...</strong></span>';
							 $(".btn_div").append($spanAndBtn);
							 $('#button_end').button({
								    onClick: function(e, ui) {  
								    	endCallNames(jyh_,lch_);
								    },
								 });
							 getCallNamesDtlsByJs(jyh_,lch_); 
							 isBeging = true;
							 clearInterval(si_start);
							 si_start = null; 
						 } 
						 
					}else{
						isBeging = false;
						$(".btn_div").empty();
						var $span = '<span style="color: red;"><strong>' + data.msg + '</strong></span>';
						$(".btn_div").append($span);
						clearInterval(si_start); 
						si_start = null;
					} 
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					isBeging = false;
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
					clearInterval(si);
					si_start = null;
				}
			});
		},1 * 1000);
	}
	
	
	function endCallNames(jyh_,lch_) {
		var data = {};
		data['jyh'] = jyh_;
		data['lch'] = lch_;
		var jshs = []; //监舍号集合
		 
		$("."+lch_ +"-jshInput").each(function() {
    		if($("#"+lch_+"-"+this.value).hasClass('zt_h')){
    			var state = $("#" + lch_ + "-" + this.value + "-state").val();
    			if(state == "1"){
	    			jshs.push(this.value);
    			}
			}
		});
		
		if(jshs.length == 0){
			//return;
		}
		/* debugger; */
		data['jshs'] = jshs ;
		
		$.ajax({
			type : "post",
			url : "${ctx}/callNames/master/EndCallNames.json",
			data : data,
			dataType : "json",
			data : JSON.stringify(data),
			contentType : "application/json",
			success : function(data) {
				if (data.success) {
					$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" }); 
					$(".btn_div").empty();
					var $span = '<span style="color: #fff;"><strong>正在结束点名，请稍后...</strong></span>';
					$(".btn_div").append($span);
				}else{
					$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" }); 
				} 
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}
	
	function getCallNamesDtlsByJs(jyh_,lch_) {
		var lastGET = false;//是否是最后获取点名详情的标志，当整个界面监舍显示点名完成的时候，最后向后台请求一次数据，结束此次点名
		endSi = setInterval(function(){
			var data = {};
			data['jyh'] = jyh_;
			data['lch'] = lch_;
			var jshs = []; //监舍号集合
			 
			$("."+lch_ +"-jshInput").each(function() {
	    		if($("#"+lch_+"-"+this.value).hasClass('zt_h')){
		    		jshs.push(this.value);
				}
			});
			data['jshs'] = jshs ;
			
			if(jshs.length == 0 ){
				if(lastGET){
					$(".btn_div").empty();
					var $span = '<span style="color: #fff;"><strong>点名已结束。</strong></span>';
					$(".btn_div").append($span);
					clearInterval(endSi);
					endSi = null;
					isBeging = false;
					return;
				}
				lastGET = true;
			}
			
			$.ajax({
				url : "${ctx}/callNames/master/getCallNamesDtlsByJs.json",
				dataType : "json",
				type : "post",
				data : JSON.stringify(data),
				contentType : "application/json",
				success : function(data) {
					
					if (data.success) {
						var list = data.obj;
						for (var i = 0; i < list.length; i++) {
							var jsInfo = list[i];
						
							$("#"+jsInfo.lch + "-" + jsInfo.jsh + " .peopleTotal").text(jsInfo.arrivedTotal);
							$("#"+jsInfo.lch + "-" + jsInfo.jsh + "-state").val(jsInfo.state);
							if(jsInfo.state == "1"){
								$("#"+jsInfo.lch + "-" + jsInfo.jsh + " .rollcallSatus").text("状态 : 点名中");	
							}
							if(jsInfo.state == "2"){
								if($("#"+jsInfo.lch+"-"+ jsInfo.jsh).hasClass('zt_h')){
									$("#"+jsInfo.lch + "-" + jsInfo.jsh + " .rollcallSatus").text("状态 : 已结束");	
									$("#"+jsInfo.lch + "-" + jsInfo.jsh ).attr('class', 'zt_done' ); 
								}
							}
							if(jsInfo.state == "-1"){
								if($("#"+jsInfo.lch+"-"+ jsInfo.jsh).hasClass('zt_h')){
									$("#"+jsInfo.lch + "-" + jsInfo.jsh + " .rollcallSatus").text("状态 : 失败");	
									$("#"+jsInfo.lch + "-" + jsInfo.jsh ).attr('class', 'zt_none' ); 
								}
							}
							 
						}
						 
					}else{
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" }); 
						clearInterval(endSi);
						endSi = null;
						isBeging = false;
					} 
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
					clearInterval(endSi);
					endSi = null;
					isBeging = false;
				}
			});
		},3 * 1000);
	}
	
	function openRollcallDailog(jyh_,lch_,jsh_) {
		var state = $("#" + lch_ + "-" + jsh_ + "-state").val();
	    if(state!="1"){
	    	$.messageQueue({ message : "点名已结束或未开始，无法查看点名详情", cls : "warning", iframePanel : true, type : "info" });
	    	return;
	    }
		
		$("#dialogId_rollcall").dialog({
			width : 1200,
			height : 800,
			url : "${ctx}/callNames/master/openDialog/JsZf?jyh="+jyh_+"&lch="+lch_+"&jsh="+jsh_,
			title : '人员列表',
		});
		$("#dialogId_rollcall").dialog("open");
	}
	
</script>