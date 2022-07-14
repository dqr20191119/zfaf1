try{
	String.prototype.endWith=function(str){
	if(str==null||str==""||this.length==0||str.length>this.length)
	  return false;
	if(this.substring(this.length-str.length)==str)
	  return true;
	else
	  return false;
	return true;
	}
	String.prototype.startWith=function(str){
	if(str==null||str==""||this.length==0||str.length>this.length)
	  return false;
	if(this.substr(0,str.length)==str)
	  return true;
	else
	  return false;
	return true;
	}
	String.prototype.trim=function(){
		return $.trim(this);
	}
	String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
	    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
	        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);
	    } else {
	        return this.replace(reallyDo, replaceWith);
	    }
	}
		 /** * 对Date的扩展，将 Date 转化为指定格式的String * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q)
	    可以用 1-2 个占位符 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) * eg: * (new
	    Date()).pattern("yyyy-MM-dd hh:mm:ss.S")==> 2006-07-02 08:09:04.423
	 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04
	 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04
	 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04
	 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
	 */
	Date.prototype.pattern=function(fmt) {
	    var o = {
	    "M+" : this.getMonth()+1, //月份
	    "d+" : this.getDate(), //日
	    "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时
	    "H+" : this.getHours(), //小时
	    "m+" : this.getMinutes(), //分
	    "s+" : this.getSeconds(), //秒
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度
	    "S" : this.getMilliseconds() //毫秒
	    };
	    var week = {
	    "0" : "/u65e5",
	    "1" : "/u4e00",
	    "2" : "/u4e8c",
	    "3" : "/u4e09",
	    "4" : "/u56db",
	    "5" : "/u4e94",
	    "6" : "/u516d"
	    };
	    if(/(y+)/.test(fmt)){
	        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	    }
	    if(/(E+)/.test(fmt)){
	        fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "/u661f/u671f" : "/u5468") : "")+week[this.getDay()+""]);
	    }
	    for(var k in o){
	        if(new RegExp("("+ k +")").test(fmt)){
	            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	        }
	    }
	    return fmt;
	}
}catch(e){
	
}
/**对dialog方法进行简单的封装
 * selector: 选择器
 * options: dialog参数
 * buttons: 将按钮参数单独分离出来*/
function dialog(selector,options,buttons){
	if(typeof(buttons)=="undefined"){
		buttons = options;
		options = {};
	}
	var _options = {
		autoOpen : false,
		height : 'auto',
		width : 600,
		modal : true,
		buttons: buttons
	};
	var url = options["url"]
	if(url){
		if(url.indexOf("_CSRFToken")==-1){
			if(url.indexOf("?")!=-1){
				url += "&_CSRFToken=" + $('meta[name="_CSRFToken"]').attr('content');
			}else{
				url += "?kkk=" + $('meta[name="_CSRFToken"]').attr('content');
			}
		}
		options["url"] = url;
	}
	
	options = $.extend({},_options,options);
	$(selector).dialog(options);
	return $(selector);
}

function refreshCenter(url){
	panel = $("#coralui-layout").layout("panel","center");
	panel.panel("refresh", url);
}


function panelMenuMouseOver(id){
	if($("#"+id).attr("class") == "selected")return;
	$("#"+id+" > .normal").hide();
	$("#"+id+" > .focus").show();
}
function panelMenuMouseOut(id){
	if($("#"+id).attr("class") == "selected")return;
	$("#"+id+" > .normal").show();
	$("#"+id+" > .focus").hide();
}

function showDiv(obj,id){
	var display = $("#"+id).css("display");
	if(display == "block"){
		$("#"+id).hide("normal");
	}else{
		$("#"+id).show("normal");
	}
	var imgs = $(obj).find("img");
	if(imgs.length>0){
		if(display == "block"){
			$(imgs[0]).attr("src",ctx+"/res/resource/style/images/Arrowhead-Down-01-32.png")
		}else{
			$(imgs[0]).attr("src",ctx+"/res/resource/style/images/Arrowhead-01-32.png")
		}
	}
}
function openWindow(url){
	openWindow(url,modal,width,height);
}
function openWindow(url,modal,width,height){
	//debugger;
	var winWidth = window.screen.availWidth-10+"px";
	var winHeight = window.screen.availHeight-70+"px";
	//if(typeof(width) == 'undefined'){
		width = winWidth;
	//}
	//if(typeof(height) == 'undefined'){
		height = winHeight;
	//}
	if(modal){
		window.showModalDialog(url,'','dialogWidth='+width+';dialogHeight='+height+';resizable:yes;scroll:yes;status:no; center=yes; help=no');
	}else{
		window.open(url,'newwindow');
		//window.open(url,'_blank','width='+width+',height='+height+',fullscreen=yes,type=fullWindow,toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=yes, status=yes');
	}
}


function dialogLoad() {
	$.parser.parse();
}

function message(msg){
	$.message(msg);
}
function warning(msg){
	$.message({message:"警告！"+msg, cls:"warning"});
}
function error(eMsg){
	
	var msg;
	if(eMsg&&eMsg['statusText']&&(eMsg['statusText'].indexOf('NetworkError')!=-1 || eMsg['statusText'].indexOf('error')!=-1)){
		$.message({message:"错误！网络异常，有可能断网或后台服务停止服务", cls:"danger"});
		return;
	}
	if(eMsg&&eMsg.responseText&&eMsg.responseText.indexOf("权限")!=-1){
		return;
	}
	if(eMsg&&eMsg['statusText']&&(eMsg['statusText'].indexOf('abort')!=-1)){
		return;
	}
	if((typeof eMsg)=='string'){
		$.message({message:"错误！"+eMsg, cls:"danger"});
		return;
	}
	
	try{
		//msg = jQuery.parseJSON($.trim(eMsg.responseText)).message;
		msg = eMsg.responseText;
		if(msg&&msg.indexOf('message\":')!=-1&&msg.indexOf(',\"code')!=-1){
			msg = msg.substring(msg.indexOf('message\":')+('message\":'.length), msg.indexOf(',\"code'));
			if(msg.lastIndexOf("Exception: ")!=-1){
				msg = msg.substring(msg.lastIndexOf('Exception: ')+('Exception: '.length));
			}
		} else {
			return;
		}
	}catch(e){
		if((typeof eMsg)=='string'){
			msg = eMsg;
		}else if(eMsg.statusText=='abort'){
			return;
		}else{
			msg = e.message;
		}
		//console.log(e);
	}
	if(msg&&msg.indexOf("Unable")!=-1){
		return;
	}
	if(!msg){
		msg="系统异常！";
	}
	$.message({message:"错误！"+msg, cls:"danger"});
}


function layoutCenterResize(){
	var panel = $("#coralui-layout").layout("panel","center");
	var centerHeight = panel.panel("option","height");
	var navHeight = $(".PanelNav:visible").outerHeight(true);
	var panelConOuterHeight = $(".PanelCon").outerHeight(true);
	var panelConInnerHeight = $(".PanelCon").height();
	//alert(centerHeight+";"+navHeight+";"+(panelConOuterHeight-panelConInnerHeight));
	var panelCon = centerHeight- navHeight-(panelConOuterHeight-panelConInnerHeight)-2 - $('.Gcrumbs:visible').outerHeight(true);
	var searchHeight = $(".PanelSelect").outerHeight(true);
	//alert(searchHeight);
	var listHeight = panelCon-searchHeight -2;
	var panelgrid = listHeight - parseInt($('.panelTop').outerHeight(true));
	
	var ctreeheight = $('.F-left').height() - $('.leftHgroup').height()
	
	$('.F-left .coral-tree').height(ctreeheight);
	
	//alert(listHeight);
	$(".PanelCon").height(panelCon);
	$(".PanelList").height(listHeight);
	$('.panelgrid').height(panelgrid);
	
	$('.panelhalf').height(listHeight/2);
	

	var panelhastabheight = listHeight - $('.nobordertabul').outerHeight(true);
	$('.panelhastabheight').height(panelhastabheight);

	var panellist2 = panelhastabheight - 20;
	$('.PanelList2').height(panellist2);

	var hastreeheight = panellist2 - $('.floatLeft2').outerHeight(true) - 10;
	$('.hastreeheight').height(hastreeheight);

	$('#firstscanimg').height($('#firstscanshow').height() - $('#firstscanbtn:visible').outerHeight(true));
	$('.systemManagementTro').css({
			'width': $('.trosize').width(),
			'height': $('.trosize').height()
	});
	$('.systemManagementTro img').css({
		'max-width': $('.trosize').width(),
		'max-height': $('.trosize').height()
	});
	
	$('#listRequired,#listTaken,#listMaking').height(listHeight- $('.panelul').height());

	$('.panelgrid .coral-grid,.PanelList .coral-grid').grid("refresh");
}



function initTreebox(){
	//二级树区域高度
	$('.treebox').height($('.menuLeftSecond').height() - $('.menuLeftSecondHead').height() );
}

//此方法用于左侧菜单功能
try{
	$(function(){
		$('body').on('click','.menuLeftFirst li',function(){
	 		$(this).addClass('hit').siblings().removeClass('hit');
	 	})
	
		$('body').on('click','.menuLeftFirst li.hasTree',function(){
	 		$('.menuLeftSecond').animate({'left':'0'});
	 		var titleHtml = $(this).html();
	 		$('.firstName').html(titleHtml);
	 		var no = parseInt($(this).attr('no'));
	 		$(".treebox .leftTree>div").hide();
	 		$(".treebox .leftTree>div[no='"+no+"']").show();
	
	 	})
	
	 	$('body').on('click','.BackFirst',function(){
	 		$('.menuLeftSecond').animate({'left':'100%'});
	 	})
	
	 	//menuLeftFirst2
	 	$('body').on('click','.menuLeftFirst2>ul>li',function(){
	 		$(this).addClass('hit').siblings().removeClass('hit');
	 	})
	
	})
}catch(e){
	
}

//异步树加载后，自动打开第一层
var treeAsyncTimes={};
 function treeAsyncExpandnode(e, treeId, treeNode) {
	if(treeAsyncTimes[treeId]){
		treeAsyncTimes[treeId]+= 1;
	}else{
		treeAsyncTimes[treeId] = 1;
	}
	//if(treeAsyncTimes[treeId]%2!=0){
	 	var tree = $("#"+treeId);
	    var nodes = tree.tree("getNodes");
	    tree.tree("expandNode",nodes[0], true);
	//}
 }

 //点击树上节点时, 勾选上节点的checkbox
 function checkClickNode(e, treeId, treeNode){
/*	 if(treeNode.level == 0){
		 if(treeNode.checked){
			 $("#"+treeId).tree("checkAllNodes",false);
		 } else {
			 $("#"+treeId).tree("checkAllNodes",true);
		 }
	 } else {
		 if(treeNode.checked){
			 $("#"+treeId).tree("checkNode",treeNode,false);
		 } else {
			 $("#"+treeId).tree("checkNode",treeNode,true);
		 }
	 }*/
	 if(treeNode.checked){
		 $("#"+treeId).tree("checkNode",treeNode,false);
	 } else {
		 $("#"+treeId).tree("checkNode",treeNode,true);
	 }
 }


function loading(msg){
	$.loading({
		position: "overlay",
		text: msg
	});
}
function hide(){
	$.loading("hide");
}

function tooltipinput(placeholder){
	var tooltipinput = '';
	tooltipinput += '<span class="tooltipbarbox" style="display:block;">';
		tooltipinput += '<span class="tooltiparrow"></span>';
		tooltipinput += '<span class="tooltiptext">+placeholder+</span>';
	tooltipinput += '</span>';
	$('.se-searchall').append(tooltipinput);
}

//为左侧root结点下树增加或修改节点 only for 系统管理
function addNodeUnderRootNode(tree,treeNode,addFlag,isExpand){
	if(addFlag){
		var selNode = tree.tree("getNodeByParam","id","-1");
	}else{
		var selNode = tree.tree("getSelectedNodes");
	}
	if(!selNode){
		return;
	}
	if(addFlag){
		tree.tree("addNodes",selNode,treeNode,true);
		//是否展开节点
		if(isExpand){
			tree.tree("expandNode",selNode[0], true, true);
		}
	} else {
		var childrenNodes = selNode[0].children;
		for(var i in childrenNodes){
			if(childrenNodes[i].id == treeNode.id){
				childrenNodes[i].name = treeNode.name;
				tree.tree("updateNode",childrenNodes[i]);
			}
		}
	}
}

//为左侧树增加或修改节点
function addOrUpdateLeftTreeNode(tree,treeNode,addFlag,isExpand){
	var selNode = tree.tree("getSelectedNodes");
	if(!selNode){
		return;
	}
	if(addFlag){
		tree.tree("addNodes",selNode[0],treeNode,true);
		//是否展开节点
		if(isExpand){
			tree.tree("expandNode",selNode[0], true, true);
		}
	} else {
		var childrenNodes = selNode[0].children;
		for(var i in childrenNodes){
			if(childrenNodes[i].id == treeNode.id){
				childrenNodes[i].name = treeNode.name;
				tree.tree("updateNode",childrenNodes[i]);
			}
		}
	}
}

//为左侧树删除节点
function deleteLeftTreeNode(tree,id){
	var idsArray = id.toString().split(",");
	var selNode = tree.tree("getSelectedNodes");
	if(!selNode){
		return;
	}
	var childrenNodes = selNode[0].children;
	for (var i = idsArray.length-1 ; i >= 0 ; i--) {
		for (var j = childrenNodes.length-1; j >= 0; j--) {
			if(childrenNodes[j].id == idsArray[i]){
				tree.tree("removeNode",childrenNodes[j]);
				break;
			}
		}
	}
}

//拖动排序
function sortGrid(e, ui){
	var grid = $("#"+e.target.id);
	var url = grid.grid("option","url");
	/* var datatype = grid.grid("option","datatype");
	if(datatype&&datatype=="local"){
		return;
	} */
	if(!url)return;
	url = url.substring(0,url.lastIndexOf("/"));
	url = url+"/sort";
	var sortAfterIDs = ui.permutation.join();
	var sortBeforeIDs = ui.originalPermutation.join();
	//alert(ui.permutation.length);
	 $.ajax({
		type: 'post',
		url	: url,
		data: {
				"sortAfterIDs": sortAfterIDs,
				"sortBeforeIDs":sortBeforeIDs
		},
		dataType: 'json',
		success	: function(data){
			 message("排序成功");
		},
		error: function(e){
			error(e);
		}
	});
}

//检查唯一性
function checkUnique(){
	var value = this.value;
	var url = this.form.action;
	if(!url)return;
	
	url = url + "/checkUnique";
	var name = this.name;
	var id = this.form.id.value;

	var dataJson = {};
	//colName_LIKE_value
	dataJson["filter"] = name + "_EQ_"+ value;
	if(url.indexOf("org/") > -1 && "organizeCode"!=name){
		dataJson["filter"] += ",pId_EQ_"+this.form.pId.value;
	}
	if(url.indexOf("organizeType/") > -1 || url.indexOf("roleclassification/") > -1){
		dataJson["filter"] += ",pId_EQ_"+this.form.pId.value;
	}
	if(url.indexOf("user-copy/") > -1){
		dataJson["filter"] += ",status_EQ_0";
	}
	dataJson["id"] = id;
	var errorMessage = value+"已经存在！";
	var result ={};
	$.ajax({
		type: 'post',
		url	: url,
		data: dataJson,
		dataType: 'json',
		async: false,
		success	: function(data){
			result = { isValid: data, errMsg: errorMessage };
		},
		error: function(e){
			error(e);
		}
	});
	return result;
}

function isSuperSysManager(){
	if(currentUser.loginName == "supersysmanager"){
		return true;
	}else{
		return false;
	}
}

function isCardNo(card)  
{  
   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
   var reg = /^(\d{6})(18|19|20)?(\d{2})([01]\d)([0123]\d)(\d{3})(\d|X|x)?$/;  
   if(reg.test(card.trim()) === false)  
   {  
       return  false;  
   } else {
	   return true;
   }
}  

/**
 * 汉字转拼音(首字母)
 */
function makePy(chinese) {
	var fullPinYin = pinyinEngine.toPinyin(chinese, true, ",");
	var result = "";
	if(fullPinYin){
		var fullPinYinArray = fullPinYin.split(",");
		for(var i=0; i<fullPinYinArray.length; i++){
			result += fullPinYinArray[i].substring(0,1);
		}
	}
	return result;
}

/**
 * 验证两次密码是否正确
 */
function checkPasswordCorrectness(e, ui) {
	var errorMessage = "输入密码不一致";
	var password = this.form.password.value;
	var ensurePassword = $(this).textbox("getText");
	if (password == ensurePassword) {
		isValid = true;
	} else {
		isValid = false;
	}
	return {
		isValid : isValid,
		errMsg : errorMessage
	};
}

/**
 * 审批状态格式化
 */
function approveStatusFormatter(cellValue,options,rowObject){
    if (cellValue == '0') {
        return '审批通过';
    } else if (cellValue == '1') {
        return '新增待审批';
    } else if (cellValue == '2') {
        return '新增退回';
    } else if (cellValue == '3') {
        return '修改待审批';
    } else if (cellValue == '4') {
        return '修改退回';
    }else if (cellValue == '5') {
        return '删除待审批';
    } else if (cellValue == '6') {
        return '删除退回';
    } else if (cellValue == '7') {
        return '关联待审批';
    } else if (cellValue == '8') {
        return '关联退回';
    } else if (cellValue == '14') {
        return '兼职待审批';
    } else if (cellValue == '15') {
        return '兼职审批退回';
    } else if (cellValue == '16') {
        return '撤销兼职待审批';
    } else if (cellValue == '17') {
        return '改变组织待审批';
    } else if (cellValue == '18') {
        return '审批退回';
    }
}



/**
 * 审批时间格式化
 */
function approveTimeFormatter(cellValue,options,rowObject){
	return new Date(cellValue).pattern("yyyy-MM-dd HH:mm:ss");
}

/**
 * 数组比较，提取选择前后新增和删除的roleId
 */
function arrayCompared(oldDatas,newDatas) {
	var a = "";
	var b = "";
	if(oldDatas.length >0) {
		a = oldDatas.toString().split(",");
	}
	if(newDatas.length >0) {
		b = newDatas.toString().split(",");
	}
	  var temp = []; //临时数组1  
	    var temparray = [];//临时数组2  
	    for (var i = 0; i < b.length; i++) {  
	        temp[b[i]] = true;  
	    };  
	    for (var i = 0; i < a.length; i++) {  
	        if (!temp[a[i]]) {  
	            temparray.push(a[i]);  
	        } ;  
	    };  
    return temparray.toString();
}

/**
 * 处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
 */
function banBackSpace(e) {
	var ev = e || window.event;// 获取event对象
	var obj = ev.target || ev.srcElement;// 获取事件源

	var t = obj.type || obj.getAttribute('type');// 获取事件源类型

	// 获取作为判断条件的事件类型
	var vReadOnly = obj.getAttribute('readonly');
	var vEnabled = obj.getAttribute('enabled');
	
	// 处理null值情况
	vReadOnly = (vReadOnly == null) ? false : vReadOnly;
	vEnabled = (vEnabled == null) ? true : vEnabled;

	// 当敲Backspace键时，事件源类型为密码或单行、多行文本的，
	// 并且readonly属性为true或enabled属性为false的，则退格键失效
	var flag1 = (ev.keyCode == 8
			&& (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vEnabled != true)) ? true
			: false;

	// 当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
	var flag2 = (ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea") ? true
			: false;

	// 判断
	if (flag2) {
		return false;
	}
	if (flag1) {
		return false;
	}
} 

//禁止后退键 作用于Firefox、Opera 
document.onkeypress=banBackSpace; 
//禁止后退键 作用于IE、Chrome 
document.onkeydown=banBackSpace; 



/**
 * 处理名称字符过长的方法
 */
function cutString(beforeStr,size,replaceStr){
	if(beforeStr.length > size){
		beforeStr = beforeStr.substring(0,size) + replaceStr;
	}
	return beforeStr;
}