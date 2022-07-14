/**
 * Add by linhe for 3dmap public function
 * 2018-01-03
 */
/*(function (global) {
 global.prisonmap =
 }(this));*/
var map = {
    cusNumber: jsConst.CUS_NUMBER,
    //模型显示隐藏
    hideAreaId: null,
    hideParentAreaId: null,
    //视频巡视
    cameraTour: null,
    pointData: null,
    nowIndex: null,
    pointCount: null,
    videoPlayTime: null,
    tableLabels: {},
    hideTableLabels: {},
    dromtableLabels: [],
    areaIdArray: [],
    arrowArray: [],
    //省局登录初始化视角定位
    showPrisonMenu: function () {
        $.ajax({
            type: 'post',
            url: jsConst.basePath + 'common/authsystem/findAllJyForCombobox',
            data: [],
            dataType: 'json',
            success: function (data) {
                debugger;
                var menuHtml = '';
                for (var i = 0; i < data.length; i++) {
                    var li = '<li><a href="javascript:void(0);" onclick="map.loadMapInfo(' + data[i].value + ',\'' + data[i].text + '\')">'
                        + data[i].text;
                    li = li + '</a></li>';
                    menuHtml = menuHtml + li;
                }
                $('#viewPositionMenu').html(menuHtml);

                //初始化滚动条
                $("#viewPositionMenu").mCustomScrollbar({
                    theme: "minimal-dark",
                    autoExpandScrollbar: true,
                    scrollInertia: 0,
                    mouseWheelPixels: 100//鼠标滚动一下滑动多少像素
                });


                //调用显示隐藏子节点样式
                //map.showOrHideMenu();
            },
            error: function (e) {
                console.log(e);
            }
        })
    },
    loadMapInfo: function (cusNumber, prisonName) {//省局加载监狱三维地图
        jsConst.ROOT_ORGA_CODE = cusNumber;//当前选中监狱编号
        jsConst.ROOT_ORGA_NAME = prisonName;//当前选中监狱名称
        //加载三维地图
        centerDisplay('prisMap', jsConst.MAP_TYPE);
    },
    //监狱登录初始化左上角视角定位下拉菜单
    showViewMenu: function () {
        /*mCustomScrollbar滚动条应该在放入树的内容之后执行初始化函数，
         * 这个时候mCustomScrollbar初始化的时候才可以给里面的内容执行加监听事件等一系列初始化操作.
         * 而不能先执行mCustomScrollbar初始化，再往里面加内容。
         * 因为二维里面执行了一次初始化，所以此处直接往里面加内容，然后在执行mCustomScrollbar会有问题，
         * 所以此处不管三七二十一先执行destroy
         * */
        $("#viewPositionMenu").mCustomScrollbar("destroy");

        $('#viewPositionMenu').html('');//清空视角定位信息
        var cusNumber = jsConst.USER_LEVEL == '1' ? jsConst.ROOT_ORGA_CODE : jsConst.CUS_NUMBER;
        var parentAreaId = '';
       // debugger;
        $.ajax({
            type: 'post',
            url: jsConst.basePath + 'view/findRegionView.json',
            data: {'cusNumber': cusNumber, 'parentAreaId': parentAreaId, 'confine': 0},
            dataType: 'json',
            success: function (data) {
              // debugger;
                var menuHtml = '';
                for (var i = 0; i < data.length; i++) {
                    //父节点
                    var li = '<li><a href="javascript:void(0);" onclick="map.viewPosition('
                        + data[i].VFU_X_CRDNT + ',' + data[i].VFU_Y_CRDNT + ',' + data[i].VFU_Z_CRDNT + ',' + data[i].VFU_HEADING_CRDNT
                        + ',' + data[i].VFU_TILT_CRDNT + ',\'' + data[i].ABD_PARENT_AREA_ID + '\',\'' + data[i].VFU_AREA_ID
                        + '\',' + data[i].VFU_STTS + ',\'' + data[i].ABD_AREA_NAME + '\')">' + data[i].ABD_AREA_NAME;
                    if (data[i].childens && data[i].childens.length > 0) {
                        li = li + '<b class="leftArrow"></b>';
                    }
                    li = li + '</a>';
                    //子节点
                    var childenLi = '';
                    //子节点
                    if (data[i].childens && data[i].childens.length > 0) {
                        childens = data[i].childens;
                        childenLi = '<iframe class="sub-iframe"></iframe><ul>';
                        for (var j = 0; j < childens.length; j++) {
                            childenLi = childenLi + '<li><a href="javascript:void(0);" onclick="map.viewPosition('
                                + childens[j].VFU_X_CRDNT + ',' + childens[j].VFU_Y_CRDNT + ',' + childens[j].VFU_Z_CRDNT
                                + ',' + childens[j].VFU_HEADING_CRDNT + ',' + childens[j].VFU_TILT_CRDNT
                                + ',\'' + childens[j].ABD_PARENT_AREA_ID + '\',\'' + childens[j].VFU_AREA_ID + '\',' + childens[j].VFU_STTS
                                + ',\'' + childens[j].ABD_AREA_NAME + '\')">' + childens[j].ABD_AREA_NAME + '</a></li>';
                        }
                        childenLi = childenLi + '</ul>';
                        li = li + childenLi;
                    }
                    if(i==(data.length-1) && data.length <= 8) {
                        li = li + '<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>';
                        console.log(data.length);
                    }
                    li = li + '</li>';
                    menuHtml = menuHtml + li;
                }
                $('#viewPositionMenu').html(menuHtml);

                //$("#viewPositionMenu").mCustomScrollbar("destroy");
                //初始化滚动条
                $("#viewPositionMenu").mCustomScrollbar({
                    theme: "minimal-dark",
                    autoExpandScrollbar: true,
                    scrollInertia: 0,
                    mouseWheelPixels: 100//鼠标滚动一下滑动多少像素
                });
                //body手动聚焦，否则不会响应鼠标滚轮
                $("body").focus();


                //调用显示隐藏子节点
                map.showOrHideMenu();
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                /*$.alert({
                    message: textStatus,
                    title: "提示信息",
                    iframePanel: true
                });*/
                console.log("请求view/findRegionView.json报错....错误信息为:"+textStatus)
            }
        });
    },
    showOrHideMenu: function (parentMenuId) {
        $('.sjdw-main li').has('ul').mouseenter(function (e) {
            //console.log("456");
            $(this).children('ul').css('visibility', 'visible');
            var width = $(this).children('ul').find("li").eq(0).outerWidth(true) - 2;
            var height = $(this).children('ul').height() - 2;
            var childMenu = $(this).children("ul");
            var iframePanel = $(this).children("iframe");
            var childMenuHeight = childMenu.outerHeight(true);
            //var compHeight = parseInt($(this).parent("div").height() - $(this).offset().top- 40);
            var offsetTop = $(e.target).parent("li")[0].offsetTop;
            var oldTop = 0;
            try {
                oldTop = parseInt($("#viewPositionMenu").find(".mCSB_container")[0].offsetTop);
            } catch (e) {
                console.log(e);
            }
            var top1 = Math.abs(oldTop);
            var bodyHeight = $(".mCustomScrollBox").outerHeight(true) - parseInt(offsetTop - top1);
            if (childMenuHeight > bodyHeight) {
                childMenu.css({
                    "top": "auto",
                    "bottom": 0
                })
                iframePanel.css({
                    "top": "auto",
                    "bottom": 0
                })
                /*if(childMenuHeight > compHeight) {
                    childMenu.css({
                        "top":"auto",
                        "bottom":0
                    })*/
                /*iframePanel.css({
                    "top":"auto",
                    "bottom":0
                })*/
            }
            //}
            $(this).children('iframe').width(width);
            $(this).children('iframe').height(height);
            $(this).children('iframe').css('visibility', 'visible');
            return false;
        }).mouseleave(function () {
            $(this).children('ul').css('visibility', 'hidden');
            $(this).children('iframe').css('visibility', 'hidden');
        });
    },
    //视角定位
    viewPosition: function (x, y, z, heading, tilt, parentAreaId, areaId, isHide, regionName) {
        // 清空signx_show内的视频播放器
        videoPlugin.videoPlayerClear();
        // 隐藏视频播放器signx_show
        $("div[id='signx_show']").hide();
        // 显示三维地图
        $("div[id='map3d_container']").show();

        //二维加三维 add by zk
        if (jsConst.MAP_TYPE == '2') {
            planeIn3DViewPosition(areaId);
            return;
        }

        // 加载监控点、报警点数量
		var areaInfoCountData = common.getAreaInfoCount(jsConst.ROOT_ORGA_CODE, areaId);
		$('#monitorPoint').text(areaInfoCountData.sxjsl + "个");
		$('#alarmPoint').text(areaInfoCountData.bjqsl + "个");
		$("#mapAreaId").textbox("setValue", areaId);
		
        var cusNumber = jsConst.USER_LEVEL == '1' ? jsConst.ROOT_ORGA_CODE : jsConst.CUS_NUMBER;
        map.setCameraInfo(x, y, z, heading, tilt);//摄像头定位
        //修改位置
        // initToolsText(regionName);
        $('#locationName').text(regionName).unbind('click').bind('click', function() {
            // planeViewPosition(jsConst.CUS_NUMBER, jsConst.CUS_NAME);
            map.viewPosition(x, y, z, heading, tilt, parentAreaId, areaId, isHide, regionName);
        });

        /** 隐藏显示设备  */
        /**
         参数 ：1、区域id（string），2、设备类型（string 为空为全部类型 指定类型：eg:('1,2,3')） 3、监狱编号（string）4、是否显示子节点设备（int 0、不显示1、显示）
         设备类：1-摄像头，2-对讲分机，3-报警器，4-门禁，5-广播，6-对讲主机，0-只查询区域
         */
        /** 隐藏上个区域的设备 */
        //if(map.hideAreaId!=null && map.hideAreaId!=''){
        // map.delImOrMod();
        //}
        /** 显示当前区域设备 */
        if (areaId != null && areaId != '' && areaId != cusNumber) {//监狱视角作为还原三维地图，不作为视角定位处理
            map.getAllPointByGrandAndType(cusNumber, areaId, '', 1);//显示当前区域以及其子节点的设备
            //map.getAllPointByGrandAndType(jsConst.CUS_NUMBER,areaId,'',0);//当前区域的设备
            //map.getAllPointByGrandAndType(map.cusNumber,areaId,'1,4',1);//摄像机 门禁
        }
        
        map.showHideByAreaId(parentAreaId, areaId, isHide);//模型显示隐藏
        
        //add wq 2018-4-18
        if (!$.isEmptyObject(map.tableLabels)) {
            //隐藏和显示单独区域标签
            /*            if (areaId != null && areaId != '' && parentAreaId != 'undefined' && typeof(parentAreaId) != 'undefined' && parentAreaId != null && parentAreaId != cusNumber) {
                            map.tableLabels[parentAreaId].visibleMask = gviViewportMask.gviViewNone;
                            map.hideTableLabels[parentAreaId] = map.tableLabels[parentAreaId];
                        } else if (parentAreaId == cusNumber || areaId == cusNumber) {
                            $.each(map.hideTableLabels, function (key, value) {
                                value.visibleMask = gviViewportMask.gviViewAllNormalView;
                            });
                            map.hideTableLabels = {};
                        }*/

            //隐藏和显示全部区域标签
            if (areaId != null && areaId != '' && parentAreaId != 'undefined' && typeof(parentAreaId) != 'undefined' && parentAreaId != null && parentAreaId != cusNumber) {
                $.each(map.tableLabels, function (key, value) {
                    map.tableLabels[key].visibleMask = gviViewportMask.gviViewNone;
                });
            } else if (parentAreaId == cusNumber || areaId == cusNumber) {
                $.each(map.tableLabels, function (key, value) {
                    map.tableLabels[key].visibleMask = gviViewportMask.gviViewAllNormalView;
                });
            }

        }


    },
    setCameraInfo: function (x, y, z, heading, tilt) {
        //视角定位
        var position = {};	//坐标
        var angle = {};	//角度
        position.x = x;
        position.y = y;
        position.z = z;
        angle.heading = heading;
        angle.tilt = tilt;
        map_3d.setCameraInfo(position, angle);
    },
    //撤销视角定位
    setCameraUndo: function () {
        map_3d.setCameraUndo();
    },
    //根据区域编号显示隐藏模型
    showHideByAreaId: function (parentAreaId, areaId, isHide) {
        if (map.hideParentAreaId != null && map.hideParentAreaId != parentAreaId) {
            //显示已隐藏模型
            map.showOrHideModel(parentAreaId, map.hideAreaId, 0);
        }
        map.showOrHideModel(parentAreaId, areaId, isHide);
    },
    //显示或隐藏模型方法
    showOrHideModel: function (parentAreaId, areaId, isHide) {
        var cusNumber = jsConst.USER_LEVEL == '1' ? jsConst.ROOT_ORGA_CODE : jsConst.CUS_NUMBER;
        $.ajax({
            type: 'post',
            url: jsConst.basePath + 'common/all/findAreaEqualLevelModel.json',
            data: {"cusNumber": cusNumber, "areaId": areaId},
            dataType: 'json',
            success: function (data) {
                //隐藏模型集合
                var hideModels = data.hideModel;
                //显示模型集合
                var showModels = data.showModel;
                if (isHide != null && areaId != null) {
                    /**    隐藏显示模型 */
                        //隐藏
                    var hideModels = data.hideModel;
                    if (hideModels != null) {
                        for (var i = 0; i < hideModels.length; i++) {
                            var model = hideModels[i];
                            if (model.MIN_MODEL_FCNAME != null && model.MIN_MODEL_FCNAME != null
                                && model.MIN_MODEL_FDSNAME != null && model.MIN_MODEL_FDSNAME != ""
                                && model.MIN_MODEL_NO != null && model.MIN_MODEL_NO != "") {
                                //隐藏属性
                                if (isHide == 1 || isHide == "1") {//隐藏
                                    map.hideAreaId = areaId;
                                    map.hideParentAreaId = parentAreaId;
                                    map_3d.hideByNameAndId(model.MIN_MODEL_FCNAME, model.MIN_MODEL_FDSNAME, model.MIN_MODEL_NO);
                                } else {//显示
                                    map.hideAreaId = null;
                                    map.hideParentAreaId = null;
                                    map_3d.showByNameAndId(model.MIN_MODEL_FCNAME, model.MIN_MODEL_FDSNAME, model.MIN_MODEL_NO);
                                }
                            }
                        }
                    }
                    //显示
                    if (showModels != null) {
                        for (var i = 0; i < showModels.length; i++) {
                            var model = showModels[i];
                            if (model.MIN_MODEL_FCNAME != null && model.MIN_MODEL_FCNAME != null
                                && model.MIN_MODEL_FDSNAME != null && model.MIN_MODEL_FDSNAME != ""
                                && model.MIN_MODEL_NO != null && model.MIN_MODEL_NO != "") {
                                map_3d.showByNameAndId(model.MIN_MODEL_FCNAME, model.MIN_MODEL_FDSNAME, model.MIN_MODEL_NO);
                            }
                        }
                    }
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.alert({
                    message: textStatus,
                    title: "提示信息",
                    iframePanel: true
                });
            }
        });
    },
    removeAlarm: function () {
        map_3d.deleteGeometry();
    },
    /** 1、根据区域id，设备类型（空位全部类型，指定：eg:'1,2'),监狱编号 获取此区域下的所有点位
     *  2、是否显示子区域下的所有点位 0、不显示 1、显示
     *  3、返回所有点位模型对象
     */
    getAllPointByGrandAndType: function (curName, grandId, deviceType, ifShowChild) {
        /*            if(map.hideAreaId==null|| map.hideAreaId==''){
         for(var i=0;i<map_3d.allModelObj.length;i++){
         map_3d.__g.objectManager.delayDelete(map_3d.allModelObj[i].guid, 0);
         }
         }*/
        if (map.areaIdArray != null && map.areaIdArray.length > 0) {
            for (var i = 0; i < map.areaIdArray.length; i++) {
                if (map.areaIdArray[i] != grandId) {
                    var dataPara = {
                        'alpCusNumber': curName,
                        'alpGrandId': grandId,
                        'alpDeviceType': deviceType,
                        'ifShowChild': ifShowChild
                    };
                    var data = _DOCUMENT_EVENT.request_data(jsConst.basePath + "point/getAllPointByObj", dataPara, false);
                    // map_3d.allModelObj=[];
                    map.areaIdArray = [];
                    map.delImOrMod();
                    var cameraNumber = 0;
                    var alarmNumber = 0;
                    if (data.length > 0) {
                        for (var i = 0; i < data.length; i++) {
                            var nowObj = new Object();

                            if (data[i].alpDeviceType == '1') {
                                cameraNumber++;
                                nowObj = map.createImgImm(mapDevice["dt_" + data[i].alpDeviceType]["img"]["d_" + data[i].alpDeviceModel], data[i].alpXPointIdnty, data[i].alpYPointIdnty, data[i].alpZPointIdnty);
                            } else if (data[i].alpDeviceType == '8') {
                                nowObj = map.createModelImm(mapDevice["dt_" + data[i].alpDeviceType]["model"]["d_8"], data[i].alpXPointIdnty, data[i].alpYPointIdnty, data[i].alpZPointIdnty);
                            } else {
                                nowObj = map.createModelImm(mapDevice["dt_" + data[i].alpDeviceType]["model"]["d_1"], data[i].alpXPointIdnty, data[i].alpYPointIdnty, data[i].alpZPointIdnty);
                            }

                            //add wq 2018-4-13
                            nowObj.name = data[i].alpDeviceIdnty + "|" + data[i].alpDeviceType;
                            // nowObj.gviDepthTestEnable = gviDepthTestMode.gviDepthTestEnable;//设置遮挡不显示
                            map_3d.allModelObj.push(nowObj);
                            map.areaIdArray.push(grandId);
                        }
                        //$("#monitorPoint").text(cameraNumber + "个");
                    }

                    //创建楼层标签
                    if (grandId.length > 8) {
                        map.cleanDormLabels();
                        map.showLabels(curName, grandId, null);
                    } else {
                        map.cleanDormLabels();
                    }
                }
            }
        } else {
            var dataPara = {
                'alpCusNumber': curName,
                'alpGrandId': grandId,
                'alpDeviceType': deviceType,
                'ifShowChild': ifShowChild
            };
            var data = _DOCUMENT_EVENT.request_data(jsConst.basePath + "point/getAllPointByObj", dataPara, false);
            // map_3d.allModelObj=[];
            map.areaIdArray = [];
            map.delImOrMod();
            var cameraNumber = 0;
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    var nowObj = new Object();

                    if (data[i].alpDeviceType == '1') {
                        cameraNumber++;
                        nowObj = map.createImgImm(mapDevice["dt_" + data[i].alpDeviceType]["img"]["d_" + data[i].alpDeviceModel], data[i].alpXPointIdnty, data[i].alpYPointIdnty, data[i].alpZPointIdnty);
                    } else if (data[i].alpDeviceType == '8') {
                        nowObj = map.createModelImm(mapDevice["dt_" + data[i].alpDeviceType]["model"]["d_8"], data[i].alpXPointIdnty, data[i].alpYPointIdnty, data[i].alpZPointIdnty);
                    } else {
                        nowObj = map.createModelImm(mapDevice["dt_" + data[i].alpDeviceType]["model"]["d_1"], data[i].alpXPointIdnty, data[i].alpYPointIdnty, data[i].alpZPointIdnty);
                    }

                    //add wq 2018-4-13
                    nowObj.name = data[i].alpDeviceIdnty + "|" + data[i].alpDeviceType;
                    // nowObj.gviDepthTestEnable = gviDepthTestMode.gviDepthTestEnable;//设置遮挡不显示
                    map_3d.allModelObj.push(nowObj);
                    map.areaIdArray.push(grandId);
                }
                //$("#monitorPoint").text(cameraNumber + "个");
            }
            //创建楼层标签
            if (grandId.length > 8) {
                map.cleanDormLabels();
                map.showLabels(curName, grandId, null);
            } else {
                map.cleanDormLabels();
            }
        }


        /*            var dataPara={'alpCusNumber':curName,'alpGrandId':grandId,'alpDeviceType':deviceType,'ifShowChild':ifShowChild};
         var data=_DOCUMENT_EVENT.request_data(jsConst.basePath+"point/getAllPointByObj",dataPara,false);
         map_3d.allModelObj=[];
         if(data.length>0){
         for(var i=0;i<data.length;i++){
         var nowObj=new Object();
         //摄像头创建时采用图片，待图片修改修改完毕，统一加载图片
         /!*					if(jsConst.LOAD_DEVICE_TYPE==2){
         }
         else{
         nowObj = map.createModelImm(mapDevice["dt_" + data[i].alpDeviceType]["model"]["d_" + data[i].alpDeviceModel],data[i].alpXPointIdnty,data[i].alpYPointIdnty,data[i].alpZPointIdnty);
         }*!/

         if(data[i].alpDeviceType == '1'){
         nowObj = map.createImgImm(mapDevice["dt_" + data[i].alpDeviceType]["img"]["d_" + data[i].alpDeviceModel], data[i].alpXPointIdnty, data[i].alpYPointIdnty, data[i].alpZPointIdnty);
         }else if(data[i].alpDeviceType == '8'){
         nowObj = map.createModelImm(mapDevice["dt_" + data[i].alpDeviceType]["model"]["d_8"], data[i].alpXPointIdnty, data[i].alpYPointIdnty, data[i].alpZPointIdnty);
         }else{
         nowObj = map.createModelImm(mapDevice["dt_" + data[i].alpDeviceType]["model"]["d_" + data[i].alpDeviceModel],data[i].alpXPointIdnty,data[i].alpYPointIdnty,data[i].alpZPointIdnty);
         }

         //add wq 2018-4-13
         nowObj.name = data[i].alpDeviceIdnty+"|"+data[i].alpDeviceType;
         nowObj.gviDepthTestEnable = gviDepthTestMode.gviDepthTestEnable;//设置遮挡不显示
         map_3d.allModelObj.push(nowObj);
         }
         }*/
    },
    /*** 以下通过x,y,z创建模型对象 currentCreat*/
    createModelImm: function (path, x, y, z) {
        var modelName = getMediaRelatePath(path);
        var geoFactory = map_3d.__g.geometryFactory;
        var modePoint = geoFactory.createGeometry(gviGeometryType.gviGeometryModelPoint, gviVertexAttribute.gviVertexAttributeZ);
        modePoint.modelName = modelName;
        modePoint.setCoords(x, y, z, 0, 0);
        var currentCreat = map_3d.__g.objectManager.createRenderModelPoint(modePoint, null, __rootId);
        currentCreat.maxVisibleDistance = 1000;//最大可视距离
        return currentCreat;
    },
    /*** 以下通过x,y,z创建图片对象 currentCreat*/
    createImgImm: function (path, x, y, z) {
        var pointName = getMediaRelatePath(path);
        var gfactory = map_3d.__g.geometryFactory;
        var poi = gfactory.createGeometry(gviGeometryType.gviGeometryPoint, gviVertexAttribute.gviVertexAttributeZ);
        poi.x = x;
        poi.y = y;
        poi.z = z;
        var geoSymbol = map_3d.__g.new_ImagePointSymbol;   //将点以图片的形式显示出来
        geoSymbol.imageName = pointName;  //使用ImageClass里存在的图片
        geoSymbol.size = 18;
        geoSymbol.occlusionTransparent = false;
        geoSymbol.shrinkDistanceRatio = 0.5;
        var currentCreat = map_3d.__g.objectManager.createRenderPoint(poi, geoSymbol, __rootId);
        currentCreat.maxVisibleDistance = 80;
        return currentCreat;
    },
    //根据区域ID跳转到管理员视角
    setAdminCameraByAreaId: function (areaId, cusNumber) {
        $.ajax({
            type: 'post',
            url: jsConst.basePath + "view/findViewByAreaId",
            data: {"cusNumber": cusNumber, "areaId": areaId, "confine": '1'},
            dataType: 'json',
            success: function (data) {
                if (data != null) {
                    map.setCameraInfo(data.vfuXCrdnt, data.vfuYCrdnt, data.vfuZCrdnt,
                        data.vfuHeadingCrdnt, data.vfuTiltCrdnt);
                }
            },
            error: function (e) {
                console.log(e);
            }
        });
    },
    //根据cusNumber,deviceType,deviceId,isGlow图层闪烁:仅当true时闪烁,画出图层
    createRenderPolygonByPointsByDevice: function (deviceId, isGlow, cusNumber) {
        //垂直视角
        $.ajax({
            type: 'post',
            url: jsConst.basePath + "layer/findByLayer",
            data: {'linDeviceId': deviceId, 'linCusNumber': cusNumber},
            dataType: 'json',
            success: function (data) {
                var points = data.layerPoints;
                if (data != null) {
                    var newPoints = [];
                    for (var i = 0; i < points.length; i++) {
                        var point = points[i];
                        var newPoint = {};
                        newPoint.x = parseFloat(point.lpoPointX);
                        newPoint.y = parseFloat(point.lpoPointY);
                        newPoint.z = parseFloat(point.lpoPointZ);
                        newPoints.push(newPoint);
                    }
                    //保存到geometryGuids，key为custom_开头
                    var guidKey = "custom_" + deviceId;
                    map_3d.createRenderPolygonByPoints(newPoints, isGlow, guidKey);
                }
            }
        });
    },
    //显示图层，定位到垂直视角，deviceType设备类型 ,deviceId设备ID,isGlow图层闪烁:仅当true时闪烁,areaId,跳转镜头区域id，cusNumber监狱编号
    createRenderPolygonAndSetAdminCamera: function (deviceId, isGlow, areaId, cusNumber) {
        map.removeAlarm();
        //显示图层
        map.createRenderPolygonByPointsByDevice(deviceId, isGlow, cusNumber);
        //定位垂直视角
        map.setAdminCameraByAreaId(areaId, cusNumber);
    },
    // ------------预警图层管理模块调用结束------------
    //-------------巡视模块开始--------------
    /* 根据路线id巡视路线 */
    showTour: function (routeId, time) {//路线id，节点视频播放时间
        map.videoPlayTime = time;
        map.nowIndex = 0;
        /** 
         * Add by lincoln.cheng 2019-03-22 Begin 
         * 清除路线上的箭头
         */
    	map.deleteArrowOnCameraTour();
        /** Add by lincoln.cheng 2019-03-22 End */
        // 清除cameraTour
        if(map.cameraTour) {
            map.cameraTour.stop();
            map_3d.__g.objectManager.deleteObject(map.cameraTour.guid);
            map.cameraTour = null;
        }
        map.cameraTour = map_3d.__g.objectManager.createCameraTour(__rootId);
        $.ajax({
            type: "post",
            data: {"rprRoamIdnty": routeId},
            url: jsConst.basePath + "routePoint/selectPointsByRouteID.json",
            success: function (data) {
                map.pointData = data;
                map.pointCount = data != null ? data.length : 0;
                
                var arrowPointData = [];
                if(data != null && data.length > 0) {
                	for (var i = 0; i < data.length; i++) {
                        var position = map_3d.__g.new_Vector3;
                        var angle = map_3d.__g.new_EulerAngle;
                        position.set(data[i].rprPositionX, data[i].rprPositionY, data[i].rprPositionZ);
                        angle.heading = data[i].rprAngleHead == null ? 0 : data[i].rprAngleHead;
                        angle.tilt = data[i].rprAngleTilt == null ? 0 : data[i].rprAngleTilt;
                        angle.roll = data[i].roll == null ? 0 : data[i].roll;
                        map.cameraTour.addWaypoint(position, angle, data[i].rprRouteSpeed, gviCameraTourMode.gviCameraTourSmooth);
                    }
                	
	                /** 
	                 * Add by lincoln.cheng 2019-03-22 Begin 
	                 * 自定义路线上的箭头
	                 */
                	map.createArrowOnCameraTour(routeId);
                    /** Add by lincoln.cheng 2019-03-22 End */
            	}
                map.cameraTour.play();
                //$("#showRouteVideoDialog").dialog("open");
            }
        })
    },
    //暂停巡视
    stopPlay: function () {
        map.cameraTour.pause();
        /*if(map.pointData[map.nowIndex].rprEquipmentId==null||map.pointData[map.nowIndex].rprEquipmentId==""){
         $("#videoPlay_map").text("暂无摄像头")
         }else{
         $("#videoPlay_map").text(map.pointData[map.nowIndex].rprEquipmentId)
         }	*/
        setTimeout(function () {
            //$("#showRouteVideoDialog").dialog("close");
            //videoClient.closeAllMult();
        }, map.videoPlayTime);
    },
    //继续巡视
    contiunePlay: function () {
        if (map.nowIndex < map.cameraTour.waypointsNumber - 1) {
            map.cameraTour.play();
        } else {
            stop();
            $.message({
                message: "演示完毕！",
                cls: "success",
                iframePanel: true
            });
        }
    },
    //巡视节点改变事件
    onWaypointchanged: function (index) {
        //保存当前index
        map.nowIndex = index;
        var x = map.pointData[index];
        
        /*debugger;
        console.log("prisonmap onWaypointchanged index = " + index);
        console.log("prisonmap onWaypointchanged x is " + JSON.stringify(x));*/
        
        // 获取当前点的前四个点
        /* var arrowPointIndex = (map.nowIndex + 4) > map.pointCount ? map.pointCount : (map.nowIndex + 4);
        var arrowPoints = [];
        for(var i=0; i < 3; i++) {
        	arrowPoints.push(map.pointData[arrowPointIndex - i]);
        }
        if(arrowPoints != null && arrowPoints.length >= 3) {
        	var height = Math.min(arrowPoints[0].rprPositionZ, arrowPoints[1].rprPositionZ, arrowPoints[2].rprPositionZ);
    		height = height > 1 ? (height - 1) : height;
    		// console.log("height = " + height);
        	var points = CreateCurveArrow2D(arrowPoints[0].rprPositionX, arrowPoints[0].rprPositionY, arrowPoints[1].rprPositionX, arrowPoints[1].rprPositionY, arrowPoints[2].rprPositionX, arrowPoints[2].rprPositionY, height);
        	var rp = map.createArrow(points);

			// 将箭头放入箭头数组
			map.arrowArray.push(rp);
        } */
        //$("#showRouteVideoDialog").dialog("open");
        var cameraId = map.pointData[map.nowIndex].rprEquipmentId;
        /*console.log("prisonmap onWaypointchanged pointData = " + JSON.stringify(map.pointData[map.nowIndex]));
        console.log("prisonmap onWaypointchanged cameraId = " + cameraId);*/

        if (cameraId == null || cameraId == "" || cameraId == undefined || cameraId == 'undefined') {
            //$("#videoPlay_map").text("暂无摄像头")
            console.log("暂无摄像头");
        } else {
            var screenId = $("#dpyaList").combobox("getValue");
            if (screenId != null && screenId != "" && screenId != undefined && screenId != 'undefined' ) {
                var cameraIds = [];
                cameraIds.push(cameraId);
                var data = {};
                data["screenId"] = screenId;
                data["cameraIds"] = JSON.stringify(cameraIds);
                data["type"] = "4";

                $.ajax({
					type: 'post',
					url: jsConst.basePath + 'screenSwitch/startScreenSwitch.json',
					data: data,
					dataType: 'json',
					success: function (data) {
						/* if(data.success) {
							_DOCUMENT_EVENT.messge(data.msg);
						} */
					}
                });
            }

            //$("#videoPlay_map").text(map.pointData[map.nowIndex].rprEquipmentId)
            if(jsConst.VIDEO_PLAYER_TYPE=='1'){
                $("#dialogId_videoPluginDemo").dialog("close");
                $("#dialogId_videoPluginDemo").dialog({
                    width: 800,
                    height: 600,
                    title: '视频调阅',
                    onOpen: function() {
                        // 播放选中的摄像头实时视频
                        videoPlugin.multiVideoPlayHandle({
                            'container': $("#dialogId_videoPluginDemo"),
                            'subType': 1,
                            'layout': 1,
                            'data': [{
                                'index': 0,
                                'cameraId': cameraId
                            }],
                            'callback': function (data) {
                                // __toggleVideo(treeNode, true);
                            }
                        });
                    },
                    onClose: function () {
                        videoPlugin.videoPlayerClear();
                    }
                });
                $("#dialogId_videoPluginDemo").dialog("open");
            }else if(jsConst.VIDEO_PLAYER_TYPE=='0' || !jsConst.VIDEO_PLAYER_TYPE){
                // 巡视路线窗口布局设为1
                videoClient.setLayout(1);
                // 打开视频
                videoClient.playVideoHandle({
                    'subType': 1, //1 手动 2 自动
                    'layout': 1,
                    'data': [{
                        'index': 0,
                        'cameraId': cameraId
                    }],
                    'callback': function (data) {
                        // __toggleVideo(treeNode, true);
                    }
                });
            }
        }
    },
    //停止巡视
    stopCameraTour: function () {
        if (map.cameraTour) {
            map.cameraTour.stop();
        }
    },
    //切换为飞行模式
    setMouseFlyMode: function () {
        if ((jsConst.USER_LEVEL == '1' && (jsConst.ROOT_ORGA_CODE == null || jsConst.ROOT_ORGA_CODE == '')) ||
            ((jsConst.USER_LEVEL == '2' || jsConst.USER_LEVEL == '3') && (jsConst.CUS_NUMBER == null || jsConst.CUS_NUMBER == ''))) {
            return false;
        }
        map_3d.setMouseFlyMode();
    },
    //切换为框选模式
    setMouseDragModel: function () {
        if ((jsConst.USER_LEVEL == '1' && (jsConst.ROOT_ORGA_CODE == null || jsConst.ROOT_ORGA_CODE == '')) ||
            ((jsConst.USER_LEVEL == '2' || jsConst.USER_LEVEL == '3') && (jsConst.CUS_NUMBER == null || jsConst.CUS_NUMBER == ''))) {
            return false;
        }
        map_3d.setMouseDragModel();
    },
    //add by wq 2018-4-13
    setMouseDormPointSelect: function () {
        /*if((jsConst.USER_LEVEL=='1' && (jsConst.ROOT_ORGA_CODE==null || jsConst.ROOT_ORGA_CODE==''))||
         ((jsConst.USER_LEVEL=='2'||jsConst.USER_LEVEL=='3') && (jsConst.CUS_NUMBER==null || jsConst.CUS_NUMBER==''))){
         return false;
         }*/
        map_3d.setMouseDormPointSelect();
    },
    //创建标签 ,content为标签内容,采用系统换行符换行
    showLabels: function (cusNumber, areaId, content) {
        $.ajax({
            type: "post",
            data: {"mliCusNumber": cusNumber, "mliAreaId": areaId},
            url: jsConst.basePath + "labels/labelList.json",
            success: function (data) {
                //alert(data.length);
                if (data.length > 0) {
                    for (var i = 0; i < data.length; i++) {
                        var point = map_3d.__g.geometryFactory.createPoint(gviVertexAttribute.gviVertexAttributeZ);
                        point.x = data[i].mliX;
                        point.y = data[i].mliY;
                        point.z = data[i].mliZ;
                        if (content != null && "" != content) {
                            map.createLabels(areaId, point, data[i].mliWidth, content, data[i].mliHeight, data[i].mliLabelType);
                        } else {
                            map.createLabels(areaId, point, data[i].mliWidth, data[i].mliLabelCode, data[i].mliHeight, data[i].mliLabelType);
                        }

                    }
                }
            }
        })
    },
    createLabels: function (areaId, point, width, content, height, type) {
        var tableLabel = map_3d.__g.objectManager.createLabel(__rootId);
        //update wq 2018-4-18
        if (type == 5) {
            map.tableLabels[areaId] = tableLabel;
            tableLabel.text = content;
        }
        else if (type == 2) {
            //接口改变，更换取值方式
            /* var jsh = content.split("|")[0];
			var jsId = content.split("|")[1];
			tableLabel.name = jsh + "|" + jsId; */

            var jsh = content.split("_")[2];
            tableLabel.name = content;
            map.dromtableLabels.push(tableLabel);
            tableLabel.text = jsh;
        }
        tableLabel.position = point;
        var textSymbol = map_3d.__g.new_TextSymbol;
        textSymbol.drawLine = true;
        textSymbol.verticalOffset = height;
        textSymbol.marginWidth = width;
        textSymbol.maxVisualDistance = 800;
        var textAttribute = map_3d.__g.new_TextAttribute;
        textAttribute.textColor = 0xffffff00;
        textAttribute.textSize = 10;
        textAttribute.underline = false;
        textAttribute.font = "黑体";
        textSymbol.textAttribute = textAttribute;
        tableLabel.textSymbol = textSymbol;
    },
    //清除楼层标签
    cleanDormLabels: function () {
        for (var i = 0; i < map.dromtableLabels.length; i++) {
            map_3d.__g.objectManager.deleteObject(map.dromtableLabels[i].guid);
        }
        map.dromtableLabels = [];

    },
    //清除全局标签
    cleanLabels: function () {
        for (var i = 0; i < map.tableLabels.length; i++) {
            //update wq 2018-4-18
            $.each(map.tableLabels, function (key, value) {
                map_3d.__g.objectManager.deleteObject(value.guid);
            })
        }
        map.tableLabels = {};
    },
    //获取标签信息
    findShowLabels: function () {
        $.ajax({
            type: "post",
            data: {"cusNumber": jsConst.CUS_NUMBER},
            url: jsConst.basePath + "common/all/findShowLabelData.json",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var labelText = "楼内民警：" + data[i].mjsl + "人\n楼内罪犯：" + data[i].zfsl + "人\n设备：" + data[i].sbsl + "台\n区域名称：" + data[i].areaName;
                    // var labelText = "楼内民警：" + data[i].mjsl + "人\n楼内罪犯：" + data[i].zfsl + "人\n设备：" + data[i].sbsl + "台";
                    map.showLabels(jsConst.CUS_NUMBER, data[i].areaId, labelText);
                }
            },
            error: function (e) {
                //alert(e);
            }
        })
    },
    //删除已创建模型或图片
    delImOrMod: function () {
        if (map_3d.allModelObj != null && map_3d.allModelObj.length > 0) {
            for (var i = 0; i < map_3d.allModelObj.length; i++) {
                map_3d.__g.objectManager.deleteObject(map_3d.allModelObj[i].guid);
            }
            map_3d.allModelObj = [];
        }
    },
    // 划箭头
    createArrow: function (points) {
		var polygon = map_3d.__g.geometryFactory.createGeometry(gviGeometryType.gviGeometryPolygon, gviVertexAttribute.gviVertexAttributeZ);
		polygon.spatialCRS = map_3d.__fds.spatialReference;
		var point = map_3d.__g.geometryFactory.createGeometry(gviGeometryType.gviGeometryPoint, gviVertexAttribute.gviVertexAttributeZ);
		point.spatialCRS = map_3d.__fds.spatialReference;
		for(var i=0; i<points.length; ){				
			point.setCoords(points[i], points[i+1], points[i+2], 0, 0);
			polygon.exteriorRing.appendPoint(point);
			i += 3;
		}
		// debugger;
		var surfaceSymbol = map_3d.__g.new_SurfaceSymbol;
		surfaceSymbol.color = colorFromARGB(255, 33, 147, 222);
		var curveSymbol = map_3d.__g.new_CurveSymbol;
		curveSymbol.color = colorFromARGB(255, 33, 147, 222);
		surfaceSymbol.boundarySymbol = curveSymbol;
		var rp = map_3d.__g.objectManager.createRenderPolygon(polygon, surfaceSymbol, __rootId);
		
		return rp;
    },
    // 在巡视路线上划箭头
    createArrowOnCameraTour: function (routeId) {
        /** 
         * Add by lincoln.cheng 2019-03-22 Begin 
         * 自定义路线上的箭头
         */
    	if(map.pointData != null && map.pointData.length > 0) {
			var basePointArray = map.pointData;
	    	// 一维数组转二维数组
			var pointArray = arrayConvert(basePointArray, 4);
			
			if(pointArray != null && pointArray.length > 0) {
				$.ajax({
		            type: "post",
		            data: {"id": routeId},
		            url: jsConst.basePath + "route/findByRouteId.json",
		            success: function (data) {
		            	/*debugger;*/
		            	var showArrow = data.rpiShowArrow;// 是否显示箭头
		            	if(showArrow != null && showArrow == '1') {
			            	var rpiHorizonHeight = data.rpiHorizonHeight;
			            	for(var i=0; i<pointArray.length; i++) {
								var tempPointArray = pointArray[i];
								// 首先将当前数组反转
								tempPointArray.reverse();
								
								if(tempPointArray != null && tempPointArray.length >= 3) {
									if(tempPointArray[2] != null) {
										var height = null;
										if(rpiHorizonHeight) {
											height = rpiHorizonHeight;
										}
										if(height == null) {
											height = Math.min(tempPointArray[0].rprPositionZ, tempPointArray[1].rprPositionZ, tempPointArray[2].rprPositionZ);
										}
				    	            	var points = CreateCurveArrow2D(tempPointArray[0].rprPositionX, tempPointArray[0].rprPositionY, tempPointArray[1].rprPositionX, tempPointArray[1].rprPositionY, tempPointArray[2].rprPositionX, tempPointArray[2].rprPositionY, height);
				    	            	
				    	    			var rp = map.createArrow(points);
				    	    			
				    	    			// 将箭头放入箭头数组
				    	    			map.arrowArray.push(rp);
									}
								}
							}
		            	}
		            }
		        })
			}
    	}
        /** Add by lincoln.cheng 2019-03-22 End */
    },
    // 删除在巡视路线上的箭头
    deleteArrowOnCameraTour: function () {
    	// debugger;
        /** 
         * Add by lincoln.cheng 2019-03-22 Begin 
         * 清除巡视路线上的箭头
         */
        if(map.arrowArray != null && map.arrowArray.length > 0) {
        	for(var i=0; i<map.arrowArray.length; i++) {
        		var arrow = map.arrowArray[i];
        		map_3d.__g.objectManager.deleteObject(arrow.guid);
        	}
        	map.arrowArray.splice(0, map.arrowArray.length);
        }
        /** Add by lincoln.cheng 2019-03-22 End */
    },
    // 根据区域ID，到数据库中查询区域的三维视角信息定位
    viewPositionByAreaId: function (areaId, isHide, regionName) {
        var url = jsConst.basePath + "/view/findViewByAreaId.json";
        var params = {};
        params["cusNumber"] = jsConst.CUS_NUMBER;
        params["areaId"] = areaId;
        params["confine"] = "0";

        // console.log("prisonmap viewPositionByAreaId areaId = " + areaId);
        // console.log("prisonmap viewPositionByAreaId isHide = " + isHide);
        // console.log("prisonmap viewPositionByAreaId regionName = " + regionName);
        var callBack = function (data) {
            // console.log("prisonmap viewPositionByAreaId a");
            // console.log("prisonmap viewPositionByAreaId data = " + JSON.stringify(data));
            if (data) {
                // console.log("prisonmap viewPositionByAreaId b");
                map.viewPosition(data.vfuXCrdnt, data.vfuYCrdnt, data.vfuZCrdnt, data.vfuHeadingCrdnt, data.vfuTiltCrdnt, data.vfuParentViewId, areaId, isHide, regionName);
            } else {
                // console.log("prisonmap viewPositionByAreaId c");
                console.error("areaId = [" + areaId + "]的区域未设置三维视角");
            }
        };
        // console.log("prisonmap viewPositionByAreaId d");
        ajaxTodo(url, params, callBack);
    }
}