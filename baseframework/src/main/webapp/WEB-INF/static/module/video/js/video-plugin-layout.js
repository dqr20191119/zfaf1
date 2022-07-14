/**
 * VideoPlugin类
 * @constructor
 */
function VideoPlugin() {}

/**
 * 实例化VideoPlugin
 */
var videoPlugin = new VideoPlugin();

/**
 * 定义VideoPlugin属性
 */
// 视频窗口顶层容器
VideoPlugin.prototype.container = null;
// 播放器窗口布局数量最大值
VideoPlugin.prototype.maxLayout = 16;
// 当前画面布局(默认0没有)
VideoPlugin.prototype.curLayout = 0;
// 当前环面布局播放器UUID(从videoClient参考过来，暂时不知作用)
VideoPlugin.prototype.playUUID = null;
// 摄像头列表[{"index":"摄像头索引", "cameraId":"摄像头ID"}]
VideoPlugin.prototype.cameraList = [];
// 视频窗口定时轮巡
VideoPlugin.prototype.playInterval = null;
// 视频窗口定时轮巡时间间隔(秒为单位)
VideoPlugin.prototype.playIntervalTime = 10;
// 视频播放器ID命名前缀
VideoPlugin.prototype.videoPlayerIdPrefix = "video_player_";
// 视频播放器
VideoPlugin.prototype.curVideoWindowIndex = -1;

/**
 * json数据转html
 * @param jsonData
 * @param container
 * @returns {*}
 */
VideoPlugin.prototype.jsonToHtml = function (jsonData, container) {
    container = container ? container : document.body;// 窗口容器,没有则默认body
    // console.log("VideoPlugin.prototype.jsonToHtml jsonData = " + JSON.stringify(jsonData));
    for (var attr in jsonData) {
        if (jsonData[attr]["id"]) {
            var num = 1;
        } else {
            var num = jsonData[attr]["num"] || 1;
        }//如果存在id,则循环默认为1,因为id不可重复
        for (var j = 0; j < num; j++) {
            var obj = document.createElement(attr);
            $(container).append(obj);//递归时传入视频窗口容器
            for (var attr2 in jsonData[attr]) {
                var _tempAttr = jsonData[attr][attr2];
                switch (attr2) {
                    case "id":
                        obj.id = _tempAttr;
                        break;
                    case "className": //支持多个class传入~简了点~
                        if (_tempAttr.length && _tempAttr.pop) {
                            for (var k = 0; k < _tempAttr.length; ++k) {
                                // console.log("className1 = " + obj.className);
                                obj.className = obj.className ? obj.className + " " + _tempAttr[k] : _tempAttr[k];
                                // console.log("className2 = " + obj.className);
                            }
                        } else {
                            obj.className = obj.className ? obj.className + " " + _tempAttr : _tempAttr;
                            // console.log("className3 = " + obj.className);
                        }
                        break;
                    case "sub": //如果有子节点则开始递归
                        for (var i = 0; i < _tempAttr.length; i++) {
                            _tempAttr[i].sub ? this.jsonToHtml(_tempAttr[i]) : this.jsonToHtml(_tempAttr[i], obj)
                        }
                        break;
                    case "con"://设置内容,可以生成新的子元素
                        obj.innerHTML = _tempAttr;
                        break;
                    case "num":
                        break;
                    case "fn"://绑定方法
                        for (var fns in _tempAttr) {
                            if (window.addEventListener) {
                                obj.addEventListener(fns, _tempAttr[fns], false);
                            } else {
                                if (window.attachEvent) {
                                    obj.attachEvent("on" + fns, _tempAttr[fns]);
                                }
                            }
                        }
                        break;
                    default: //设置属性
                        obj.setAttribute(attr2, _tempAttr);
                        break;
                }
            }
        }
    }
    return container;
}

/**
 * 执行回调函数
 * @param data
 */
VideoPlugin.prototype.handleCallBack = function(callback, data){
    if (isNotNull(callback)){
        callback.call(this, data);
    }
};

/**
 * setLayout设置布局
 * @param container 视频窗口容器标签
 * @param layout 视频窗口数量
 */
VideoPlugin.prototype.setLayout = function (container, layout, callback) {
    // debugger;
    // 清空视频播放器
    videoPlugin.videoPlayerClear();

    // 清空视频播放器容器
    $(container).empty();

    // 计算视频窗口数
    if (layout < 1) {
        videoPlugin.curLayout = 1;
    } else if(layout >= 1 && layout <= videoPlugin.maxLayout) {
        videoPlugin.curLayout = layout;
    } else {
        videoPlugin.curLayout = videoPlugin.maxLayout;
    }

    // 从视频窗口布局模板json中获取相应的模板
    $.get(jsConst.basePath + '/static/module/video/json/layout-template.json', function (jsonData) {
        // json转化为html
        videoPlugin.container = videoPlugin.jsonToHtml(jsonData["layout_template_1"], container);

        // 遍历videoPlugin.container内的div，为有video-atomic样式的div设置id
        var idIndex = 0;
        $(videoPlugin.container).find("div").each(function(index, subDiv) {
            if($(this).hasClass("video-atomic")) {
                $(this).attr("id", "div_" + videoPlugin.videoPlayerIdPrefix + idIndex);
                idIndex++;
            }
        });

        // 视频播放器初始化
        videoPlugin.videoPlayerInit();

        if (isNotNull(callback)){
            // 执行回调函数
            videoPlugin.handleCallBack(callback, {"success": true, "msg": "设置播放器布局成功"});
        }
    });
};

/**
 * 清空map
 * @param map
 */
VideoPlugin.prototype.clearMap = function (map) {
    if(JSON.stringify(map) != JSON.stringify({})) {
        console.log("before clearMap map = " + JSON.stringify(map));
        $.each(map, function(key, value) {
            delete map[key];
        });
        console.log("after clearMap map = " + JSON.stringify(map));
    }
}

/**
 * 清空list
 * @param list
 */
VideoPlugin.prototype.clearList = function (list) {
    if(list && list.length > 0) {
        console.log("before clearList list = " + JSON.stringify(list));
        list.splice(0, list.length);
        console.log("after clearList list = " + JSON.stringify(list));
    }
}

/**
 * 获取cameraList中最大的index值
 */
VideoPlugin.prototype.getMaxIndexFromCameraList = function () {
    var result = -1;
    if(videoPlugin.cameraList != null && videoPlugin.cameraList.length > 0) {
        $.each(videoPlugin.cameraList, function (i, val) {
            if(parseInt(val.index) > parseInt(result)) {
                result = parseInt(val.index);
            }
        });
    }
    return result;
}

/**
 * 计算下一个视频应在哪个index的播放器播放
 * @returns {number}
 */
VideoPlugin.prototype.getNextIndexOfCameraList = function () {
    var maxIndex = videoPlugin.getMaxIndexFromCameraList();
    // index为-1说明视频窗口关闭,此时设置为0,即第一个index
    if(maxIndex < 0) {
        return 0;
    } else {
        return maxIndex + 1;
    }
}

/**
 * 获取下一个播放器窗口索引
 */
VideoPlugin.prototype.getNextIndexOfVideoWindow = function () {
    if(videoPlugin.curVideoWindowIndex < 0) {
        return 0;
    } else {
        if(videoPlugin.curVideoWindowIndex + 1 < videoPlugin.curLayout) {
            return videoPlugin.curVideoWindowIndex + 1;
        } else {
            return 0;
        }
    }
}

/**
 * 根据index从cameraList中获取camera
 * @param index
 */
VideoPlugin.prototype.getByIndexFromCameraList = function (index) {
    var result = null;
    console.log("VideoPlugin getByIndexFromCameraList index = " + index);
    console.log("VideoPlugin getByIndexFromCameraList videoPlugin.cameraList = " + JSON.stringify(videoPlugin.cameraList));
    if(videoPlugin.cameraList != null && videoPlugin.cameraList.length > 0) {
        try {
            $.each(videoPlugin.cameraList, function (i, val) {
                if(parseInt(val.index) == parseInt(index)) {
                    result = val;
                    throw new CustomException("break");
                }
            });
        } catch (e) {
            if (e.message != "break") {
                console.error(e);
            }
        }
    }
    console.log("VideoPlugin getByIndexFromCameraList return " + JSON.stringify(result));
    return result;
}

/**
 * 停止视频播放器轮巡
 */
VideoPlugin.prototype.stopVideoPlayInterval = function () {
    if(videoPlugin.playInterval) {
        window.clearInterval(videoPlugin.playInterval);
        videoPlugin.playInterval = null;
    }
}

/**
 * 停止当前播放的视频
 */
VideoPlugin.prototype.stopAllVideoPlayer = function () {
    if(videoPlugin.container) {
        $(videoPlugin.container).find("object").each(function (index, videoObject) {
            videoPlugin.stopVideoPlayer($(this).attr("id"));
        });
    }
}

/**
 * 生成视频播放器对象
 * @param params
 * @returns {player}
 */
VideoPlugin.prototype.createVideoPlayer = function (params) {
    var player = document.createElement("object");

    if(params._id) {
        $(player).attr("id", params._id);
    }
    if(params._clsid) {
        $(player).attr("classid", params._clsid);
    }
    if(params._codebase) {
        $(player).attr("codebase", params._codebase);
    }
    $(player).addClass("video-player");
    return player;
}

/**
 * 摄像机列表数据初始化
 * @param data
 */
VideoPlugin.prototype.cameraListInit = function (options) {
    // 摄像机信息
    var data = options.data;
    // 操作子类型：1手动、2轮循(自动)、3群组
    var subType = options.subType;

    console.log("VideoPlugin cameraListInit data = " + JSON.stringify(data));

    console.log("VideoPlugin cameraListInit cameraList1 = " + JSON.stringify(videoPlugin.cameraList));
    // tempCameraList与videoPlugin.cameraList的处理
    if(subType == 1) {
        // cameraList中的存储的摄像机ID依次上浮，第一个摄像机ID被抛弃，将当前摄像机添加到cameraList最后
        for(var i = 0; i < data.length; i++) {
            var tempIndex = -1;
            var tempCameraId = "";
            var tempStartTime = "";
            var tempEndTime = "";
            if(typeof data[i] == 'object') {
                if(isNull(data[i].index) || data[i].index.length == 0) {
                    data[i]['index'] = i;
                }
                tempIndex = data[i].index;
                tempCameraId = data[i].cameraId;
                if(data[i].startTime) {
                    tempStartTime = data[i].startTime;
                }
                if(data[i].endTime) {
                    tempEndTime = data[i].endTime;
                }
            } else {
                tempIndex = i;
                tempCameraId = data[i]
            }
            if(videoPlugin.cameraList.length < videoPlugin.curLayout) {
                // 附加
                var tempCamera = {
                    "index": tempIndex,
                    "cameraId": tempCameraId,
                    "startTime": tempStartTime,
                    "endTime": tempEndTime
                };
                videoPlugin.cameraList.push(tempCamera);
            } else {
                // 替换
                var tempCamera = {
                    "index": tempIndex,
                    "cameraId": tempCameraId,
                    "startTime": tempStartTime,
                    "endTime": tempEndTime
                };
                videoPlugin.cameraList[tempIndex] = tempCamera;
            }
        }
    } else if(subType == 2 || subType == 3) {
        // cameraList清空并添加最新的摄像机列表
        videoPlugin.clearList(videoPlugin.cameraList);

        // cameraList后面附加最新的摄像机列表
        for(var i = 0; i < data.length; i++) {
            var tempIndex = -1;
            var tempCameraId = "";
            var tempStartTime = "";
            var tempEndTime = "";
            if(typeof data[i] == 'object') {
                if(isNull(data[i].index) || data[i].index.length == 0) {
                    data[i]['index'] = i;
                }
                tempIndex = data[i].index;
                tempCameraId = data[i].cameraId;
                if(data[i].startTime) {
                    tempStartTime = data[i].startTime;
                }
                if(data[i].endTime) {
                    tempEndTime = data[i].endTime;
                }
            } else {
                tempIndex = i;
                tempCameraId = data[i]
            }

            // 附加
            var tempCamera = {
                "index": tempIndex,
                "cameraId": tempCameraId,
                "startTime": tempStartTime,
                "endTime": tempEndTime
            };
            videoPlugin.cameraList.push(tempCamera);
        }
    }
    console.log("VideoPlugin cameraListInit cameraList2 = " + JSON.stringify(videoPlugin.cameraList));
}

/**
 * 视频插件播放器初始化
 */
VideoPlugin.prototype.videoPlayerInit = function() {
    // debugger;
    // 停止视频播放器的轮巡
    videoPlugin.stopVideoPlayInterval();

    // 遍历视频播放器Object，将视频播放器停止
    // videoPlugin.stopAllVideoPlayer();

    // 遍历videoPlugin.container中id前缀为"div_" + videoPlugin.videoPlayerIdPrefix的div，为每个视频窗口添加播放器
    $(videoPlugin.container).find("div[id*='div_" + videoPlugin.videoPlayerIdPrefix + "']").each(function(index, videoDiv) {
        var playerId = $(this).attr("id").substring($(this).attr("id").indexOf(videoPlugin.videoPlayerIdPrefix), $(this).attr("id").length);

        // 播放器参数
        var params = {};
        params["_id"] = playerId;
        params["_clsid"] = "clsid:67E756E2-7133-49C5-86D9-0C5B38096ECC";
        params["_codebase"] = "lib/setup.exe";

        // 视频窗口添加播放器
        $(this).append(videoPlugin.createVideoPlayer(params));
    });

    // 视频播放器设置画面布局
    setTimeout(videoPlugin.videoPlayerOpenWindows, 1000);
}

/**
 * 视频播放器设置画面布局
 */
VideoPlugin.prototype.videoPlayerOpenWindows = function () {
    var videoPlayers = $(videoPlugin.container).find("object");

    if(videoPlayers.length <= 0) {
        setTimeout(videoPlugin.videoPlayerOpenWindows, 500);
    } else {
        $.each(videoPlayers, function(index, videoPlayer) {
            document.getElementById($(this).attr("id")).openWindows(videoPlugin.curLayout);
        });
    }
}

/**
 * 播放选中多个摄像头的实时视频
 * @param options 参数对象
 * options = {
 *      "container":"视频窗口容器",
 * 		"subType":"操作子类型：1手动、2轮循(自动)、3群组",
 * 		"layout":"布局",
 * 		"data":"数据",
 * 		"callback":"回调"
 * }
 */
VideoPlugin.prototype.multiVideoPlayHandle = function (options) {
    this.playUUID = (new Date()).getTime();
    var container = options.container;  // 视频窗口容器
    var data = options.data;            // 摄像机信息
    var layout = options.layout;        // 视频窗口布局
    var callback = options.callback;    // 回调函数
    // debugger;
    if(isNull(data) || data.length == 0){
        console.error("videoPlugin.multiVideoPlayHandle --> 无效的摄像机ID集合");
        return false;
    }

    // 校验布局参数，第一次初始化时为空则根据视频数量来设置窗口布局
    if (isNull(layout) || layout == 0 || videoPlugin.curLayout == 0) {
        layout = data.length;
    }
    console.log("videoPlugin.multiVideoPlayHandle layout = " + layout);
    console.log("videoPlugin.multiVideoPlayHandle curLayout = " + videoPlugin.curLayout);
    // 判断是否需要设置布局
    if(layout != videoPlugin.curLayout && videoPlugin.curLayout != videoPlugin.maxLayout) {
        // 布局，同时检测视频客户端是否非正常关闭画面
        videoPlugin.setLayout(container, layout, function (data) {
            console.log("videoPlugin.multiVideoPlayHandle setLayout callback data = " + JSON.stringify(data));
            if (data.success) {
                // 递归调用
                videoPlugin.multiVideoPlayHandle(options);
            } else {
                videoPlugin.handleCallBack(callback, data);
            }
        });
    } else {
        // 生成播放列表
        videoPlugin.cameraListInit(options);

        // 遍历视频播放器的播放列表，开始播放
        videoPlugin.startPlayCameraList();

        // 执行回调函数
        videoPlugin.handleCallBack(callback, {"success": true, "msg": "播放成功"});
    }
    return this.playUUID;
}

/**
 * 群组视频播放
 * @param options
 * options = {
 *      "container":"视频窗口容器",
 * 		"cusNumber":"单位编号",
 * 		"groupId":"摄像头群组编号",
 * 		"groupName":"摄像头群组名称",
 * 		"callback":"回调"
 * }
 * @returns {*}
 */
VideoPlugin.prototype.groupVideoPlayHandle = function (options) {
    var container = options.container;  // 视频窗口容器
    var cusNumber = options.cusNumber;  // 单位编号
    var groupId = options.groupId;      // 摄像头群组编号
    var groupName = options.groupName;  // 摄像头群组名称
    var callback = options.callback;    // 回调函数

    var url = jsConst.basePath + "groupMember/queryByGrdGrpIdAndGrdCusNumberAndGrdTypeIndc.json";
    var params = {
        "grdCusNumber": cusNumber,
        "grdGrpId": groupId,
        "grdTypeIndc": 1
    };

    var callMultiVideoPlay = function(data) {
        if (data.success) {
            if(data.obj && data.obj.length > 0) {
                // 根据查询到的摄像头列表，将数据组合成调用多个摄像头播放所需的数据
                var cameraList = [];
                for(var i=0; i<data.obj.length; i++) {
                    var camera = {
                        'index': i,
                        'cameraId': data.obj[i].grdMmbrIdnty
                    };
                    cameraList.push(camera);
                }

                // 调用多摄像头播放
                videoPlugin.multiVideoPlayHandle({
                    'container': container,
                    'subType': 2,
                    'layout': cameraList.length,
                    'data': cameraList,
                    'callback': callback
                });
            } else {
                $.messageQueue({ message : "【" + groupName + "】未关联摄像头", cls : "warning", iframePanel : true, type : "info" });
            }
        } else {
            $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
        }
    };
    ajaxTodo(url, params, callMultiVideoPlay);
}

/**
 * 清空视频播放器
 */
VideoPlugin.prototype.videoPlayerClear = function () {
    // 停止视频播放器的轮巡
    videoPlugin.stopVideoPlayInterval();

    // 停止视频播放器播放
    videoPlugin.stopAllVideoPlayer();

    // 清空视频播放器的播放列表
    videoPlugin.clearList(videoPlugin.cameraList);

    // 窗口布局数量
    videoPlugin.curLayout = 0;

    // 当前视频窗口索引
    videoPlugin.curVideoWindowIndex = -1;

    // 清空播放器的容器子元素
    if(videoPlugin.container) {
        $(videoPlugin.container).find("object").each(function(index, videoObject) {
            $(this).remove();
        });
        $(videoPlugin.container).empty();
        videoPlugin.container = null;
    }
}

/**
 * 根据视频播放器ID，停止播放
 * @param playerId
 * @param windowId
 */
VideoPlugin.prototype.stopVideoPlayer = function (playerId, windowId) {
    if(windowId) {
        try {
            if(document.getElementById(playerId)) {
                var stopFlag = document.getElementById(playerId).stop(windowId);
                console.log("视频播放器[" + playerId + "]的播放窗口[" + windowId + "]停止，返回结果值 = " + stopFlag);
            }
        } catch (e) {
            console.log("视频播放器[" + playerId + "]的播放窗口[" + windowId + "]停止，发生异常，原因：" + e.message);
        }
    } else {
        try {
            if(document.getElementById(playerId)) {
                var stopFlag = document.getElementById(playerId).stopAll;
                console.log("视频播放器[" + playerId + "]停止，返回结果值 = " + stopFlag);
            }
        } catch (e) {
            console.log("视频播放器[" + playerId + "]停止，发生异常，原因：" + e.message);
        }
    }
}

/**
 * 开始播放视频
 * @param playerId
 * @param windowIndex
 * @param cameraId
 */
VideoPlugin.prototype.startVideoPlay = function (playerId, windowIndex, cameraId) {
    var windowId = "player" + windowIndex;
    var url = jsConst.basePath + "jfsb/camera/getPlayInfoByCameraId.json";
    var params = {};
    params['cameraId'] = cameraId;

    console.log("player = " + document.getElementById(playerId));
    if(!document.getElementById(playerId)) {
        console.log("startVideoPlay player [" + playerId +"] not exists!");
        return false;
    }

    // 播放之前先停止
    try {
        var stopResult = document.getElementById(playerId).stop(windowId);
        console.log("video-plugin-layout.js startVideoPlay 播放之前先停止 playerId = " + playerId + " windowId = " + windowId + " stopResult = " + stopResult);
    } catch (e) {
        console.error("video-plugin-layout.js startVideoPlay 播放之前先停止 playerId = " + playerId + " windowId = " + windowId + " 发生异常，原因：" + e.message);
    }

    var callBack = function(data) {
        if (data.success) {
            var playInfo = data.obj;
            console.log("startVideoPlay playInfo = " + JSON.stringify(playInfo));
            // 播放新的摄像头
            try {
                var playResult = document.getElementById(playerId).play(
                    windowId,
                    playInfo.brand,
                    playInfo.cameraName,
                    playInfo.ipAddress,
                    playInfo.port,
                    playInfo.userName,
                    playInfo.password,
                    playInfo.channel,
                    playInfo.platformDeviceId
                );
                // 为当前播放器窗口索引赋值
                videoPlugin.curVideoWindowIndex = windowIndex;
                console.log("video-plugin-layout.js startVideoPlay playerId = " + playerId + " windowId = " + windowId + " playResult = " + playResult);
            } catch (e) {
                console.error("video-plugin-layout.js startVideoPlay playerId = " + playerId + " windowId = " + windowId + " 发生异常，原因：" + e.message);
            }
        } else {
            $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
        }
    };
    ajaxTodo(url, params, callBack);
}

/**
 * 视频播放器播放摄像机列表
 */
VideoPlugin.prototype.startPlayCameraList = function() {
    // 如果是正在轮巡
    if(videoPlugin.playInterval) {
        // 将videoPlugin.cameraList重新排序
        var firstCameraId = "";
        console.log("VideoPlugin startPlayCameraList reset cameraList1 = " + JSON.stringify(videoPlugin.cameraList));
        for(var i=0; i<videoPlugin.cameraList.length; i++) {
            if(i == 0) {
                firstCameraId = cameraList[i].cameraId;
            }
            if(i < videoPlugin.cameraList.length - 1) {
                videoPlugin.cameraList[i].cameraId = videoPlugin.cameraList[i + 1].cameraId;
            } else {
                videoPlugin.cameraList[i].cameraId = firstCameraId;
            }
        }
        console.log("VideoPlugin startPlayCameraList reset cameraList2 = " + JSON.stringify(videoPlugin.cameraList));
    }

    // 遍历视频播放器，开始播放
    $(videoPlugin.container).find("object").each(function(index, videoObject) {
        var playerId = $(this).attr("id");
        var camera = null;
        if(videoPlugin.playInterval || videoPlugin.curVideoWindowIndex < 0) {
            // 如果当前播放的视频窗口索引小于0，则播放列表依次播放
            for(var i=0; i<videoPlugin.curLayout; i++) {
                camera = videoPlugin.getByIndexFromCameraList(i);
                console.log("startPlayCameraList for cameraIndex = " + i);
                console.log("startPlayCameraList for camera = " + JSON.stringify(camera));
                if(camera) {
                    setTimeout(videoPlugin.startVideoPlay(playerId, i, camera.cameraId), 500);
                }
            }
        } else {
            // 如果当前播放的视频窗口索引不小于0，则播放当前索引的视频窗口
            var cameraIndex = videoPlugin.getNextIndexOfVideoWindow();
            camera = videoPlugin.getByIndexFromCameraList(cameraIndex);
            console.log("startPlayCameraList cameraIndex = " + cameraIndex);
            console.log("startPlayCameraList camera = " + JSON.stringify(camera));
            if(camera) {
                setTimeout(videoPlugin.startVideoPlay(playerId, cameraIndex, camera.cameraId), 500);
            }
        }
    });

    // 摄像头数量超过播放器数量时设置轮询
    if(videoPlugin.cameraList.length > videoPlugin.curLayout) {
        videoPlugin.setVideoPlayInterval(videoPlugin.playIntervalTime);
    }
}

/**
 * 设置定时轮巡
 * @param s 间隔时间(秒)
 */
VideoPlugin.prototype.setVideoPlayInterval = function (s) {
    videoPlugin.playInterval = window.setInterval(videoPlugin.startPlayCameraList(), parseInt(s) * 1000);
}

/**
 * 视频回放
 * @param options
 * options = {
 *      "container":"视频窗口容器",
 * 		"layout":"布局",
 * 		"data":"数据",
 * 		"callback":"回调"
 * }
 */
VideoPlugin.prototype.multiVideoPlaybackHandle = function(options) {
    this.playUUID = (new Date()).getTime();
    var container = options.container;  // 视频窗口容器
    var data = options.data;            // 摄像机信息
    var layout = options.layout.layout; // 视频窗口布局
    var callback = options.callback;    // 回调函数
    // debugger;
    if(isNull(data) || data.length == 0){
        console.error("videoPlugin.multiVideoPlaybackHandle --> 无效的摄像机ID集合");
        return false;
    }

    // 校验布局参数，第一次初始化时为空则根据视频数量来设置窗口布局
    if (isNull(layout) || layout == 0 || videoPlugin.curLayout == 0) {
        layout = data.length;
    }
    console.log("videoPlugin.multiVideoPlaybackHandle layout = " + layout);
    console.log("videoPlugin.multiVideoPlaybackHandle curLayout = " + videoPlugin.curLayout);
    // 判断是否需要设置布局
    if(layout != videoPlugin.curLayout && videoPlugin.curLayout != videoPlugin.maxLayout) {
        // 布局，同时检测视频客户端是否非正常关闭画面
        videoPlugin.setLayout(container, layout, function (data) {
            console.log("videoPlugin.multiVideoPlaybackHandle setLayout callback data = " + JSON.stringify(data));
            if (data.success) {
                // 递归调用
                videoPlugin.multiVideoPlaybackHandle(options);
            } else {
                videoPlugin.handleCallBack(callback, data);
            }
        });
    } else {
        // 生成播放列表
        videoPlugin.cameraListInit(options);

        // 遍历视频播放器的播放列表，开始回放
        videoPlugin.startPlaybackCameraList();

        // 执行回调函数
        videoPlugin.handleCallBack(callback, {"success": true, "msg": "回放成功"});
    }
    return this.playUUID;
}

/**
 * 视频播放器回放摄像机视频列表
 */
VideoPlugin.prototype.startPlaybackCameraList = function() {
    // 遍历视频播放器，开始播放
    $(videoPlugin.container).find("object").each(function(index, videoObject) {
        var playerId = $(this).attr("id");
        var camera = null;
        if(videoPlugin.playInterval || videoPlugin.curVideoWindowIndex < 0) {
            // 如果当前播放的视频窗口索引小于0，则播放列表依次播放
            for(var i=0; i<videoPlugin.curLayout; i++) {
                camera = videoPlugin.getByIndexFromCameraList(i);
                console.log("startPlaybackCameraList for cameraIndex = " + i);
                console.log("startPlaybackCameraList for camera = " + JSON.stringify(camera));
                if(camera) {
                    setTimeout(videoPlugin.startVideoPlayback(playerId, i, camera.cameraId, camera.startTime, camera.endTime), 500);
                }
            }
        } else {
            // 如果当前播放的视频窗口索引不小于0，则播放当前索引的视频窗口
            var cameraIndex = videoPlugin.getNextIndexOfVideoWindow();
            camera = videoPlugin.getByIndexFromCameraList(cameraIndex);
            console.log("startPlaybackCameraList cameraIndex = " + cameraIndex);
            console.log("startPlaybackCameraList for camera = " + JSON.stringify(camera));
            if(camera) {
                setTimeout(videoPlugin.startVideoPlayback(playerId, cameraIndex, camera.cameraId, camera.startTime, camera.endTime), 500);
            }
        }
    });
}

/**
 * 开始回放视频
 * @param playerId
 * @param windowIndex
 * @param cameraId
 * @param startTime
 * @param endTime
 */
VideoPlugin.prototype.startVideoPlayback = function (playerId, windowIndex, cameraId, startTime, endTime) {
    var windowId = "player" + windowIndex;
    var url = jsConst.basePath + "jfsb/camera/getPlayInfoByCameraId.json";
    var params = {};
    params['cameraId'] = cameraId;

    if(!document.getElementById(playerId)) {
        console.log("startVideoPlayback player [" + playerId + "] not exists;");
        return false;
    }
    console.log("player = " + document.getElementById(playerId));
    // 回放之前先停止
    try {
        var stopResult = document.getElementById(playerId).stop(windowId);
        console.log("video-plugin-layout.js startVideoPlayback 回放之前先停止 playerId = " + playerId + " windowId = " + windowId + " stopResult = " + stopResult);
    } catch (e) {
        console.error("video-plugin-layout.js startVideoPlayback 回放之前先停止 playerId = " + playerId + " windowId = " + windowId + " 发生异常，原因：" + e.message);
    }

    var callBack = function(data) {
        if (data.success) {
            var playInfo = data.obj;
            try {
                // 回放新的摄像头
                var playResult = document.getElementById(playerId).playBack(
                    windowId,
                    playInfo.brand,
                    playInfo.cameraName,
                    playInfo.ipAddress,
                    playInfo.port,
                    playInfo.userName,
                    playInfo.password,
                    playInfo.channel,
                    playInfo.platformDeviceId,
                    startTime,
                    endTime
                );
                // 为当前播放器窗口索引赋值
                videoPlugin.curVideoWindowIndex = windowIndex;
                console.log("video-plugin-layout.js startVideoPlayback playerId = " + playerId + " windowId = " + windowId + " playResult = " + playResult);
            } catch (e) {
                console.error("video-plugin-layout.js startVideoPlayback playerId = " + playerId + " windowId = " + windowId + " 发生异常，原因：" + e.message);
            }
        } else {
            $.messageQueue({ message : data.msg, cls : "warning", iframePanel : true, type : "info" });
        }
    };
    ajaxTodo(url, params, callBack);
}
