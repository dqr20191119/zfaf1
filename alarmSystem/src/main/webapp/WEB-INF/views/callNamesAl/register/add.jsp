<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.cesgroup.prison.code.utils.CodeFacade"%>
<%@taglib prefix="cui" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<style>
.div_body_rlzc {
	height: 100%;
	width: 100%;
}

.div_content_rlzc {
	margin-left: 3px;
	float: left;
	width: 33%;
	height: 100%;
}

.div_content_rlzc fieldset {
	height: 96.5%;
}

.div_content_rlzc table {
	border-bottom-width: 1px;
	border-bottom-style: solid;
	border-bottom-color: #F5FAFA;
	width: 100%;
	height: 100%;
}

.div_content_rlzc table span {
	padding: 5px 10px;
	font-size: 12px;
	font-family: Verdana;
	font-weight: bold;
}

.div_content_rlzc table tr:nth-child(even) {
	text-align: center;
	background: #FFF
}

.div_content_rlzc table tr:nth-child(odd) {
	text-align: center;
	background: #F5FAFA
}
</style>

<div id="div_body_rlzc" class="div_body_rlzc">
	<div class="div_content_rlzc">
		<fieldset>
			<legend>请选择监舍</legend>
			<div id="tree_div" style="height: 92%;">
				<cui:tree id="js_regionTree" simpleDataEnable="true" asyncAutoParam="id,name" asyncType="get" asyncEnable="true" onDblClick="findPrisonerByJs">
				</cui:tree>
			</div>
		</fieldset>
	</div>
	<div class="div_content_rlzc">
		<fieldset>
			<legend>待注册罪犯列表</legend>
			<cui:button id="btn_rlzc" label="注册" onClick="zfRlzc" disabled="true"></cui:button>
			<div id="prisonInfo_div" style="height: 87%;width: 100%"></div>
		</fieldset>
	</div>
	<div class="div_content_rlzc">
		<fieldset>
			<legend>已注册罪犯列表</legend>
			<div id="prisonInfo_div_1" style="height: 92%;"></div>
		</fieldset>
	</div>
</div>

<script>
	$.parseDone(function() {
		$("#js_regionTree").tree( "reload", "${ctx}/callNames/register/findJsForTree?cusNumber=" + cusNumber);

		$("#tree_div").mCustomScrollbar({
			theme : "minimal-light",
			autoExpandScrollbar : true
		});

	});

	var data_ = null;
	function findPrisonerByJs(event, id, node) {
		if (node.jyh && node.id && node.lch) {
			data_ = { "cusNumber" : node.jyh, "lch" : node.lch, "jsh" : node.id };
			$("#div_body_rlzc").loading({ text : "正在请求数据，请稍后..."});
			getYzczfByjs();
			getWzczfByjs();
		}

	}
	
	//根据监舍获得未注册罪犯 //查询未注册（未存注册表）用户信息
	function getWzczfByjs() {
		$('#btn_rlzc').button("disable");
		$("#prisonInfo_div").empty();
		
		$.ajax({
			type : "post",
			data : data_,
			url : "${ctx}/callNames/register/findPrisonerByJs.json",
			dataType : "json",
			success : function(data) {
				if (data.length > 0) {
					creatTable(data, $("#prisonInfo_div"));
					$('#btn_rlzc').button("enable");
				} 
				$("#div_body_rlzc").loading("hide");
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}
	
	//根据监舍获得已注册罪犯 //查询已注册（已存注册表）罪犯信息
	function getYzczfByjs() {
		$("#prisonInfo_div_1").empty();
		$.ajax({
			type : "post",
			data : data_,
			url : "${ctx}/callNames/register/findRegisterPrisonerByJs.json",
			dataType : "json",
			success : function(data) {
				if (data.length > 0) {
					creatTable(data, $("#prisonInfo_div_1"));
				} 
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
			}
		});
	}

	function creatTable(list, $div) {
		var $div_1 = $("<div style='height: 100%;'></div>")
		var $tableTemp = $("<table class='table'></table>");
		for (var i = 0; i < list.length; i++) {
			var $trTemp = $("<tr></tr>");
			var $tdTemp = $("<td><span>" + list[i].PBD_PRISONER + "</span><span class='zfbh' style='display:none'>" + list[i].PBD_PRISONER_INDC + "</span></td>");
			$trTemp.append($tdTemp);
			$trTemp.appendTo($tableTemp);
		}
		$div_1.append($tableTemp);
		$div.append($div_1);

		$div_1.mCustomScrollbar({
			theme : "minimal-light",
			autoExpandScrollbar : true
		});

	}

	function zfRlzc() {
		$("#div_body_rlzc").loading({ text : "正在批量注册中，请稍后..."});
		var zfbhs = [];
		$("#prisonInfo_div .zfbh").each(function() {
			zfbhs.push(this.innerText);
		});
		if (zfbhs.length) {
			var url = "${ctx}/callNames/register/saveInfo.json?";
			$.ajax({
				type : "post",
				url : url,
				data : JSON.stringify(zfbhs),
				contentType : "application/json",
				success : function(data) {
					$("#div_body_rlzc").loading("hide");
					if (data.success) {
						$.messageQueue({ message : data.msg, cls : "success", iframePanel : true, type : "info" });
						$("#gridId_rlzc").grid("reload");
						getYzczfByjs();
						getWzczfByjs();
					} else {
						$.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$.messageQueue({ message : textStatus, cls : "warning", iframePanel : true, type : "info" });
				}
			});
		}
	}
</script>