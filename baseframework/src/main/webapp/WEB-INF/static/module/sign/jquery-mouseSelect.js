(function() { 

  	document.getElementById("signx_show").onmousedown = function(event) {
  		var rectItems = [];
  		if($("#rect").length > 0){
  			$("#rect").remove();
  		}
  		var rectDom = $('<div id="rect" style="position: absolute; background-color: rgb(195, 213, 237); opacity: 0.6;display: none;"></div>');
  	 	$("body").append(rectDom);
  		$("#rect").attr("style", "position: absolute; background-color: rgb(195, 213, 237);opacity:0.6;border: 1px dashed rgb(0, 153, 255);");
        $("#rect").empty();
        //清空框选中的项
        if (rectItems)
            rectItems.length = 0;

        // 鼠标按下时才允许处理鼠标的移动事件
        isSelect = true;
        // 取得鼠标按下时的坐标位置
        startX = event.clientX;
        startY = event.clientY;

        //设置你要画的矩形框的起点位置
        $("#rect").offset().left = startX;
        $("#rect").offset().top = startY;

        document.getElementById("signx_show").onmousemove = function(event) { 
			if (isSelect) {
			        // 取得鼠标移动时的坐标位置
		        endX = event.clientX;
		        endY = event.clientY;

		        //endX = event.offsetX;
		        //endY = event.offsetY;

		        var r = $("#rect");

		        // 设置拉框的大小    
		        $("#rect").width(Math.abs(endX - startX));
		        $("#rect").height(Math.abs(endY - startY));

		        $("#rect").css("display", "block");
		        $("#rect").css("z-index", "1201");
		        // A part
		        if (endX < startX && endY < startY) {
		            $("#rect").css("left", endX);
		            $("#rect").css("top", endY);
		        }

		        // B part
		        if (endX > startX && endY < startY) {
		            $("#rect").css("left", startX);
		            $("#rect").css("top", endY);
		        }

		        // C part
		        if (endX < startX && endY > startY) {
		            $("#rect").css("left",endX);
		            $("#rect").css("top", startY);
		        }

		        // D part
		        if (endX > startX && endY > startY) {
		            $("#rect").css("left", startX);
		            $("#rect").css("top", startY);
		        }

		        /*
		        这两句代码是最重要的,没有这两句代码,你的拉框,就只能拉框,在鼠标松开的时候,
		        拉框停止,但是不能相应鼠标的mouseup事件.那么你想做的处理就不能进行.
		        这两句的作用是使当前的鼠标事件不在冒泡,也就是说,不向其父窗口传递,所以才可以相应鼠标抬起事件,
		        这两行代码是拉框最核心的部分.
		        */
		        window.event.cancelBubble = true;
		        window.event.returnValue = false;
			 }

      		 clearEventBubble(event); 

    } 

    document.onmouseup = function(event) {
 		var childs = [];
        if (isSelect) {
        var w = $("#rect").width();
        var h = $("#rect").height();
        if (w > 10 && h > 10) {
            $("#rect").css("display", "block");
           
            var fileNodes = document.getElementById("signx_show").getElementsByTagName("div"); 
            
		    for ( var i = 0; i < fileNodes.length; i++) { 

		      if (fileNodes[i].className.indexOf("signIndex") != -1) { 

		        fileNodes[i].className = "signIndex"; 
               
		        childs.push(fileNodes[i]); 

		      } 

		    } 
       
            for (var i = 0; i < childs.length; i++) {
                if (childs[i].id == "rect") {   
                    continue;
                } else {
                    var x = 0, y = 0;
                    var child = $("#signx_show #" + childs[i].id);
                    var childleft = child.offset().left;
                    var childtop = child.offset().top;
                    //var childleft = parseInt(child.css("left").replace("px", ""));
                    //var childtop = parseInt(child.css("top").replace("px", ""));
                    x = childleft;
                    y = childtop;
                   
                    var rectleft = parseInt($("#rect").css("left").replace("px", ""));
                    var recttop = parseInt($("#rect").css("top").replace("px", ""));
                    if (x > rectleft && y > recttop && (x + child.width()) < (rectleft + $("#rect").width()) &&
                        (y + child.height()) < (recttop + $("#rect").height())) {
                        rectItems.push(childs[i]);
			            if (childs[i].className.indexOf("seled") == -1) { 
			              childs[i].className = childs[i].className + " seled"; 

			            } 
                    }
                }
            }
            //查找被框选中的元素  ------end
            //重新设置框选容器的大小、位置，并将被框选中的元素移入框选容器   -------start
            if (rectItems.length > 0) {
                var left = 0, top = 0, maxW = 0, minW = 0, maxH = 0, minH = 0;
                for (var i = 0; i < rectItems.length; i++) {
                    var item = $("#" + rectItems[i].id);
                    var itemleft = item.offset().left;
                    var itemtop = item.offset().top;
                    //var itemleft = parseInt(item.css("left").replace("px", ""));
                    //var itemtop = parseInt(item.css("top").replace("px", ""));

                    if (i != 0) {
                        if (itemleft <= left)
                            left = itemleft;
                        if (itemtop <= top)
                            top = itemtop;

                        if ((itemleft + item.width()) >= maxW)
                            maxW = itemleft + item.width();
                        if (itemleft <= minW)
                            minW = itemleft;

                        if ((itemtop + item.height()) >= maxH)
                            maxH = itemtop + item.height();
                        if (itemtop <= minH)
                            minH = itemtop;

                    }
                    else {
                        left = itemleft;
                        top = itemtop;

                        maxW = itemleft + item.width();
                        minW = left;
                        maxH = itemtop + item.height();
                        minH = top;
                    }
                }
                $("#rect").css("left", left);
                $("#rect").css("top", top);
                $("#rect").width(Math.abs(maxW - minW)+5);
                $("#rect").height(Math.abs(maxH - minH)+5);
            } else {
                $("#rect").css("display", "none");
            }
            //重新设置框选容器的大小、位置，并将被框选中的元素移入框选容器   -------end
        } else {
            $("#rect").css("display", "none");
        }
    }
    //鼠标抬起,就不允许在处理鼠标移动事件
    isSelect = false;
    showSelDiv(rectItems)

    } 
 }
})(); 
function clearEventBubble(evt) { 
	if (evt.stopPropagation){ 
		evt.stopPropagation(); 
	}else{
		evt.cancelBubble = true; 
	}if (evt.preventDefault){ 
		evt.preventDefault(); 
	}else{
		evt.returnValue = false; 
	}
} 
function showSelDiv(arr) { 
	if(arr){
		var deviceIds = []; 
		//摄像头类型值
		var cameraTypeValue="1";
		for ( var i = 0; i < arr.length; i++) { 
			if (arr[i].className.indexOf("seled") != -1) {
				if($("#"+arr[i].id).attr("data-plpDeviceType")==cameraTypeValue){
					deviceIds.push($("#"+arr[i].id).attr("data-plpDeviceIdnty"));
				}
			} 
		} 
		// console.log("摄像头ids="+deviceIds);
		if(deviceIds.length>0){
            if (jsConst.VIDEO_PLAYER_TYPE == '1') {
                $("#dialogId_videoPluginDemo").dialog("close");
                $("#dialogId_videoPluginDemo").dialog({
                    width: 800,
                    height: 600,
                    title: '视频调阅',
                    onOpen: function() {
                        // 将框选的图标解除框选
                        $("#rect").css("display", "none");
                        document.onmouseup = function (event) {
                            // console.log("++++++++");
                            // 鼠标抬起,就不允许在处理鼠标移动事件
                            isSelect = false;
                            $("#rect").css("display", "none");
                        }

                        // 播放选中的摄像头实时视频
                        videoPlugin.multiVideoPlayHandle({
                            'container': $("div[id='dialogId_videoPluginDemo']"),
                            'subType': 2,
                            'layout': deviceIds.length,
                            'data': deviceIds,
                            'callback': function (data) {
                                console.log("jquery-mouseSelect.js showSelDiv data = " + JSON.stringify(data));
                            }
                        });
                    },
                    onClose: function () {
                        videoPlugin.videoPlayerClear();
                    }
                });
                $("#dialogId_videoPluginDemo").dialog("open");
            } else if (jsConst.VIDEO_PLAYER_TYPE == '0' || !jsConst.VIDEO_PLAYER_TYPE) {
                videoClient.playVideoHandle({
                    'subType': 2,
                    'layout': deviceIds.length,
                    'data': deviceIds,
                    'callback': function (data) {
                        $("#rect").css("display", "none");
                        /*
                         * 清除document.onmouseup事件，否则后续每次鼠标弹起都会触发 add by zk 20180806
                         * 这一块需要注意:因为请求是异步的，所以会出现一种情况：
                         * 当请求已经发送还没有收到反馈的时候，再次进行框选（这时候虽然恢复了鼠标onmouseup事件），
                         * 但是在鼠标弹起之前这时候反馈过来了，就会用当前onmouseup事件处理方法替换掉鼠标按下时设置的onmouseup事件处理方法。
                         * 从而造成框选的黑框无法取消，所以此处进行了处理，
                         * 当出现这种特殊情况的时候，隐藏黑框并使isSelect= false从而不去去处理鼠标移动事件
                         *  */
                        document.onmouseup = function (event) {
                            // console.log("++++++++");
                            // 鼠标抬起,就不允许在处理鼠标移动事件
                            isSelect = false;
                            $("#rect").css("display", "none");
                        }
                    }
                });
            }
		}	 
	}
} 