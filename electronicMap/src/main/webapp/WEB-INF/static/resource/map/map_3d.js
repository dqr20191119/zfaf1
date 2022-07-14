var map_3d;
$(function () {
    map_3d = {
        cepServer: "",
        __g: document.getElementById("__g"),
        initMap: false,
        tree: null,
        route: null,
        selectFeatureIds: [],	//当前选中模型id
        featureLayer: null,		//当前选中模型对象
        mousePosition: null,		//鼠标点击点位坐标
        geometryGuids: [],		//保存的线面guid数据
        allModelObj: [],         //保存当前视角的所有模型对象
        //存储{}对象，key，guid
        rpolyline: null,			//临时保存的线，用于保存面
        angle: null,				//视角角度信息
        cameraInfo: null,		//camera信息
        hideFeatureIds: [],		//隐藏模型集合
        dragSelectModels: [],
        wqzjLine: null,
        initMapStat: false,
        ctrDeviceIds: [],
        __fds: null,
        __datasetCRS: null,
        init: function () {
            //map_3d.cameraInfo = map_3d.__g.camera.getCamera();
            //初始化三维控件
            try {
                map_3d.initMap = initAxControl();
                if (jsConst.USER_LEVEL == '1' && jsConst.ROOT_ORGA_CODE != null && jsConst.ROOT_ORGA_CODE != '') {
                    map_3d.initMapStat = map_3d.openCep(jsConst.ROOT_ORGA_CODE);
                } else if (jsConst.CUS_NUMBER != null && jsConst.CUS_NUMBER != '' && (jsConst.USER_LEVEL == '2' || jsConst.USER_LEVEL == '3')) {
                    map_3d.initMapStat = map_3d.openCep(jsConst.CUS_NUMBER);
                }

                //全局变量 ALARM_INFO包含参数areaId,alarmDeviceId,cusNumber
                if (jsConst.ALARM_INFO != null && '' != jsConst.ALARM_INFO) {
                    map_3d.alarmAction();
                } else {
                    //设置视角最低高度
                    map_3d.onViewChange();
                }
            } catch (e) {
                alert("初始化三维地图控件失败！");
            }
        },
        openCep: function (cusNumber) {
            var serverKey = 'p_' + cusNumber
            var cep = mapSever[serverKey];
            if (cep == null || cep == '' || typeof(cep) == 'undefined') {
                return false;
            }
            if (map_3d.cepServer != null && map_3d.cepServer != '') {
                //判断该监狱三维地图是否已经加载，若一加载则不再加载
                if (cep.cepServerPath == map_3d.cepServer.cepServerPath && cep.fdbServerIp == map_3d.cepServer.fdbServerIp
                    && cep.fdbServerName == map_3d.cepServer.fdbServerName) {
                    return false;
                }
            }
            //var initMap = initAxControl();
            if (map_3d.initMap) {
                map_3d.cepServer = cep;
                var cepPath = getMediaRelatePath(map_3d.cepServer.cepServerPath);
                map_3d.__g.project.open(cepPath, false, "");
                try {
                	//debugger;
                	var projectTree1 = map_3d.__g.projectTree;
                	var path ="ynanan\\YNJY\\雁南";
                	var path = mapLayerPath[serverKey];//"bjnzjy\\1234\\女子监狱";
                	console.log("map_3d.js openCep layerPath = " + path);
                	var pathID = map_3d.__g.projectTree.findItem(path);
            		var layerobj= map_3d.__g.objectManager.getFeatureLayer(pathID);

            		var fcname = layerobj.featureClassInfo.featureClassName;
            	 	var fdsname = layerobj.featureClassInfo.dataSetName;
            	 	var cr = layerobj.featureClassInfo.dataSourceConnectionString;
            	 	var df = map_3d.__g.dataSourceFactory;
            	 	var ds = df.openDataSourceByString(cr);
            	 	var fds = ds.openFeatureDataset(fdsname);
            	 	var fc = fds.openFeatureClass(fcname);
            	 	map_3d.__fds = fds;
            	 	map_3d.__datasetCRS = fds.spatialReference;
                } catch(e) {
                	alert("project获取tree失败" + e);
                }
                //飞行时间
                map_3d.__g.camera.flyTime = 2;
                //tree = map_3d.projectTree;
                map_3d.tree = map_3d.__g.projectTree;

                map_3d.setGlowLine(__rootId);
                return true;
            }
        },
        //鼠标拾取(功能未启用)
        setMouseClickSelect: function () {
            map_3d.cameraInfo = map_3d.__g.camera.getCamera();
            //设置当前模式为拾取模式
            map_3d.__g.interactMode = gviInteractMode.gviInteractSelect;
            //设置当前拾取模式为场景模式
            map_3d.__g.mouseSelectObjectMask = gviMouseSelectObjectMask.gviSelectAll;
            //设置鼠标触发方式为点选
            map_3d.__g.mouseSelectMode = gviMouseSelectMode.gviMouseSelectClick;
            //绑定鼠标点击事件
            if (typeof (map_3d.onMouseClickSelectGet) == "function") ____events["onMouseClickSelect"] = map_3d.onMouseClickSelectGet;
        },
        //鼠标点击拾取事件(功能未启用)
        onMouseClickSelectGet: function (pickResult, intersectPoint, mask, eventSender) {
        	//cep拾取
            if (pickResult == null)
                return;
            var featureId = pickResult.featureId;
            var fl = pickResult.featureLayer;
            var fc = map_3d.fnGetFcByLayer(fl);
            map_3d.featureLayer = fl;

            //待测  2018-6-1
            var ctrl = document.getElementById("CtrlSel").checked;
            if (!ctrl || (ctrl == true && mask != 12)) {  //ctrl键if (mask != 12) {
                map_3d.__g.featureManager.unhighlightAll();
                map_3d.selectFeatureIds = [];
                map_3d.selectFeatureIds.push(featureId);
                map_3d.__g.featureManager.highlightFeature(fc, featureId, 0xffffff00);
            } else {
                //若已选中则取消选中
                var hasId = false;
                for (index in map_3d.selectFeatureIds) {
                    if (map_3d.selectFeatureIds[index] == featureId) {
                        map_3d.selectFeatureIds.splice(index, 1);//.del(id);
                        map_3d.__g.featureManager.unhighlightFeature(fc, featureId);//取消高亮
                        hasId = true;
                    }
                }
                if (!hasId) {
                    map_3d.selectFeatureIds.push(featureId);
                    map_3d.__g.featureManager.highlightFeature(fc, featureId, 0xffffff00);
                }
            }
            //高亮
            //隐藏
            //__g.featureManager.setFeatureVisibleMask(fc,featureId,gviViewportMask.gviViewNone);
            //给全局变量重新赋值
            /*map_3d.selectFeatureIds = [];
             map_3d.selectFeatureIds.push(featureId);*/
            map_3d.featureLayer = pickResult.featureLayer;
            map_3d.mousePosition = intersectPoint;
        },

        //add wq 2018-4-13
        //点位信息鼠标拾取
        setMouseDormPointSelect: function () {
            map_3d.__g.interactMode = gviInteractMode.gviInteractSelect;
            map_3d.__g.mouseSelectObjectMask = gviMouseSelectObjectMask.gviSelectAll;
            map_3d.__g.mouseSelectMode = gviMouseSelectMode.gviMouseSelectClick;
            if (typeof (map_3d.fnMouseDormClickSelect) == "function") ____events["onMouseClickSelect"] = map_3d.fnMouseDormClickSelect;
        },

        //add wq 2018-4-13
        //点位信息鼠标点击拾取事件
        fnMouseDormClickSelect: function (pickResult, intersectPoint, mask, eventSender) {
        	if (pickResult == null) {
                return;
            }

            if (pickResult.type == gviObjectType.gviObjectRenderPoint) {
                var pointId = pickResult.renderPoint.name.split("|")[0];
                var adp = pickResult.renderPoint.name.split("|")[1];
                var pointIdArray = [];
                pointIdArray.push(pointId);
                switch (adp) {
                    case "2":
                        var data = {};
                        data["cusNumber"] = jsConst.CUS_NUMBER;
                        data["talkIdntys"] = JSON.stringify(pointIdArray);
                        $.confirm({
                            message: "是否打开该分机对讲？",
                            iframePanel: true,
                            callback: function (stat) {
                                if (stat == true) {
                                    $.ajax({
                                        type: 'post',
                                        url: jsConst.basePath + 'realTimeTalk/startTalk.json',
                                        data: data,
                                        dataType: 'json',
                                        success: function (data) {
                                            _DOCUMENT_EVENT.messge("呼叫成功！");
                                        },
                                    });
                                }
                                if (stat == false) {
                                    console.log('cancel');
                                }
                            }
                        });
                        break;
                    case "4":
                        var data = {};
                        data["action"] = '1';
                        data["doorIds"] = JSON.stringify(pointIdArray);
                        $.confirm({
                            message: "是否打开该门禁？",
                            iframePanel: true,
                            callback: function (stat) {
                                if (stat == true) {
                                    $.ajax({
                                        type: 'post',
                                        url: jsConst.basePath + 'doorlinkage/controlDoor.json',
                                        data: data,
                                        dataType: 'json',
                                        success: function (data) {
                                            console.log('门禁1', data);
                                            //打开摄像头
                                            if (data.obj) {
                                                videoClient.playVideoHandle({
                                                    'subType': 2,
                                                    'layout': data.obj.length,
                                                    'data': data.obj
                                                });
                                            }
                                            _DOCUMENT_EVENT.messge("操作成功！");
                                        },
                                    });
                                }
                                if (stat == false) {
                                    console.log('cancel');
                                }
                            }
                        });
                        break;
                    case 8:
                        $("#dialogId_DormInfo").dialog({
                            subTitle: '监舍点位信息',
                            width: 1000,
                            height: 520,
                            reLoadOnOpen: "true",
                            // modal:false,
                            position: {my: "left", at: "left+400px top+500px ", of: window},
                            autoOpen: true,
                            url: jsConst.basePath + 'xxhj/zfjbxx/prisonerBedInfo?jsId=' + pointId  //a12792e260c640560160c988cdc50012
                        });
                        break;
                }

                if (mask == 12) {
                    map_3d.ctrDeviceIds.push(pointId);
                }

            } else if (pickResult.type == gviObjectType.gviObjectRenderModelPoint) {
                var pointId = pickResult.renderModelPoint.name.split("|")[0];
                var adp = pickResult.renderModelPoint.name.split("|")[1];
                var pointIdArray = [];
                pointIdArray.push(pointId);
                switch (adp) {
                    case "2":
                        var data = {};
                        data["cusNumber"] = jsConst.CUS_NUMBER;
                        data["talkIdntys"] = JSON.stringify(pointIdArray);

                        $.confirm({
                            message: "是否打开该分机对讲？",
                            iframePanel: true,
                            callback: function (stat) {
                                if (stat == true) {
                                    $.ajax({
                                        type: 'post',
                                        url: jsConst.basePath + 'realTimeTalk/startTalk.json',
                                        data: data,
                                        dataType: 'json',
                                        success: function (data) {
                                            _DOCUMENT_EVENT.messge("呼叫成功！");
                                        },
                                    });
                                }
                                if (stat == false) {
                                    console.log('cancel');
                                }
                            }
                        });
                        break;
                    case "4":
                        var data = {};
                        data["action"] = '1';
                        data["doorIds"] = JSON.stringify(pointIdArray);

                        $.confirm({
                            message: "是否打开该门禁？",
                            iframePanel: true,
                            callback: function (stat) {
                                if (stat == true) {
                                    $.ajax({
                                        type: 'post',
                                        url: jsConst.basePath + 'doorlinkage/controlDoor.json',
                                        data: data,
                                        dataType: 'json',
                                        success: function (data) {
                                            debugger;
                                            console.log('门禁2', data.obj);
                                            //打开摄像头
                                            if (data.obj) {
                                                videoClient.playVideoHandle({
                                                    'subType': 2,
                                                    'layout': data.obj.length,
                                                    'data': data.obj
                                                });
                                            }
                                            _DOCUMENT_EVENT.messge("操作成功！");
                                        },
                                    });
                                }
                                if (stat == false) {
                                    console.log('cancel');
                                }
                            }
                        });
                        break;

                    case "8":
                        $("#dialogId_DormInfo").dialog({
                            subTitle: '监舍点位信息',
                            width: 1000,
                            height: 520,
                            reLoadOnOpen: "true",
                            // modal:false,
                            position: {my: "left", at: "left+400px top+500px ", of: window},
                            autoOpen: true,
                            url: jsConst.basePath + 'xxhj/zfjbxx/prisonerBedInfo?jsId=' + pointId  //a12792e260c640560160c988cdc50012
                        });
                        break;
                }

                if (mask == 12) {
                    map_3d.ctrDeviceIds.push(pointId);
                }

            } else if (pickResult.type == gviObjectType.gviObjectLabel) {
                // var jsId = pickResult.label.name.split("|")[1];
                var jsId = pickResult.label.name.split("_");
                var cusNumber = jsId[0];
                var lch = jsId[1];
                var jsh = jsId[2];
                console.log(jsId);
                if (typeof(jsId) != "undefined") {
                    $("#dialogId_DormInfo").dialog({
                        subTitle: '监舍点位信息',
                        width: 1000,
                        height: 520,
                        reLoadOnOpen: "true",
                        // modal:false,
                        position: {my: "left", at: "left+400px top+500px ", of: window},
                        autoOpen: true,
                        url: jsConst.basePath + "xxhj/zfjbxx/jsPrisonerInfo?cusNumber=" + cusNumber + "&lch=" + lch + "&jsh=" + jsh
                    });
                }
            }
        },

        //隐藏模型
        hideModel: function (ids) {
        	//alert("map_3d.js hideModel");
            if (ids == null || typeof(ids) == "undefined")
                ids = map_3d.selectFeatureIds;
            map_3d.__g.featureManager.setFeaturesVisibleMask(map_3d.fnGetFcByLayer(), ids, gviViewportMask.gviViewNone);
            for (var i = 0; i < ids.length; i++) {
                map_3d.hideFeatureIds.push(ids[i]);
            }
        },
        //显示模型
        showModel: function (ids) {
            if (ids == null || typeof(ids) == "undefined")
                ids = map_3d.hideFeatureIds;
            //显示
            map_3d.__g.featureManager.setFeaturesVisibleMask(map_3d.fnGetFcByLayer(), ids, gviViewportMask.gviViewAllNormalView);
            //取消高亮
            map_3d.__g.featureManager.unhighlightAll();

        },
        //根据模型名称，数据源名称和模型id集合（数组）隐藏模型
        hideByNameAndId: function (fcname, fdsname, id) {
            map_3d.__g.featureManager.setFeatureVisibleMask(map_3d.fnGetFc(fcname, fdsname), id, gviViewportMask.gviViewNone);
        },
        //根据模型名称，数据源名称和模型id集合（数组）隐藏模型
        showByNameAndId: function (fcname, fdsname, id) {
            map_3d.__g.featureManager.setFeatureVisibleMask(map_3d.fnGetFc(fcname, fdsname), id, gviViewportMask.gviViewAllNormalView);
        },
        //摄像头视角改变事件
        onCameraChange: function () {
            if (typeof (map_3d.setViewPosition) == "function") ____events["onCameraUndoRedoStatusChanged"] = map_3d.setViewPosition;
            if (typeof (map_3d.setViewPosition) == "function") ____events["onCameraFlyFinished"] = map_3d.setViewPosition;

        },
        setViewPosition: function () {
            map_3d.cameraInfo = map_3d.__g.camera.getCamera();
            if (map_3d.cameraInfo.position.z < 0.999) {//设置最低高度
                map_3d.cameraInfo.position.z = 1;
            }
            if (map_3d.cameraInfo.position.z > 600.1) {//设置最大高度
                map_3d.cameraInfo.position.z = 600;
            }
            view3d.setViewPosition(map_3d.cameraInfo.position.x, map_3d.cameraInfo.position.y, map_3d.cameraInfo.position.z,
                map_3d.cameraInfo.angle.heading, map_3d.cameraInfo.angle.tilt);
        },
        //视角定位
        setCameraInfo: function (p, a) {
            var position = map_3d.__g.new_Vector3;
            var angle = map_3d.__g.new_EulerAngle;
            position.set(p.x, p.y, p.z);
            angle.heading = a.heading == null ? 0 : a.heading;
            angle.tilt = a.tilt == null ? 0 : a.tilt;
            angle.roll = a.roll == null ? 0 : a.roll;
            map_3d.__g.camera.setCamera(position, angle, gviSetCameraFlags.gviSetCameraNoFlags);
        },
        //撤销相机操作
        setCameraUndo: function () {
            map_3d.__g.camera.undo();
        },
        //设置为飞行模式（鼠标只能移动地图不可拾取框选）
        setMouseFlyMode: function () {
            map_3d.__g.interactMode = gviInteractMode.gviInteractNormal;
        },
        //框选模型
        setMouseDragModel: function () {
            map_3d.__g.interactMode = gviInteractMode.gviInteractSelect;
            map_3d.__g.mouseSelectObjectMask = gviMouseSelectObjectMask.gviSelectAll;//OverlayLabel//gviSelectAll
            map_3d.__g.mouseSelectMode = gviMouseSelectMode.gviMouseSelectDrag;//.gviMouseSelectClick; //
            if (typeof (map_3d.fnMouseDragSelect) == "function") ____events["onMouseDragSelect"] = map_3d.fnMouseDragSelect;

            //map_3d.__g.onmouseclickselect = getObjs;
        },
        fnMouseDragSelect: function (pickResult, mask) {
            //取消模型高亮
            for (var i = 0; i < map_3d.dragSelectModels.length; i++) {
                var selectModel = map_3d.dragSelectModels[i];
                if (selectModel.renderModelPoint.type == gviObjectType.gviObjectRenderModelPoint) {
                    map_3d.dragSelectModels[i].renderModelPoint.unhighlight();
                }
            }
            map_3d.dragSelectModels = [];//已框选模型数组
            if (pickResult == null)
                return;
            if (pickResult != null) {
                var deviceIds = [];
                for (i = 0; i < pickResult.count; i++) {
                    var pr = pickResult.get(i);
                    if (pr.type == gviObjectType.gviObjectRenderPoint) {//框选点
                        if (pr.renderPoint.name.split("|")[1] == "1") {
                            deviceIds.push(pr.renderPoint.name.split("|")[0]);
                        }
                    } else if (pr.type == gviObjectType.gviObjectRenderModelPoint) {//框选模型
                        if (pr.renderModelPoint.name.split("|")[1] == "1") {
                            map_3d.dragSelectModels.push(pr);
                            pr.renderModelPoint.highlight(0xffffff00);
                            //update wq 2018-4-13
                            deviceIds.push(pr.renderModelPoint.name.split("|")[0]);
                            // alert("拾取到" + pr.type + "类型，模型guid为" + pr.renderModelPoint.guid);
                        }
                    }
                }
                //调用视频设备
                if (deviceIds.length > 0) {
                    videoClient.playVideoHandle({
                        'subType': 2,
                        'layout': deviceIds.length,
                        'data': deviceIds,
                        'callback': function (data) {
                            //__toggleVideo(treeNode, true);
                        }
                    });
                }
            }
        },
        //周界线闪烁
        setGlowLine: function (guid) {
            var firstChildGuid = map_3d.tree.getNextItem(guid, 11);
            if (firstChildGuid == "00000000-0000-0000-0000-000000000000") {
                return;
            }
            map_3d.loopNode(firstChildGuid);
            var brotherGuid = map_3d.tree.getNextItem(firstChildGuid, 13);
            while (brotherGuid != "00000000-0000-0000-0000-000000000000") {//遍历子节点
                if (map_3d.loopNode(brotherGuid))
                    return;
                brotherGuid = map_3d.tree.getNextItem(brotherGuid, 13);
            }
        },
        loopNode: function (curId) {
            var nodeName = map_3d.tree.getItemName(curId);
            if (nodeName == 'wqzjx_line') {//围墙周界线
                map_3d.wqzjLine = map_3d.__g.objectManager.getObjectById(curId);
                map_3d.wqzjLine.glow(-1);
                // map_3d.showOrHideZjx();//调用周界线计时显示隐藏方法
                return true;
            }
            if (map_3d.tree.isGroup(curId)) {
                map_3d.setGlowLine(curId);
                return false;
            }
        },
        //周界线计时显示(cm8实现闪烁，但由于闪烁效果不佳，暂时停用不做删除)
        showOrHideZjx: function () {
            if (map_3d.wqzjLine == null)
                return false;
            setTimeout("map_3d.showOrHideZjx()", 500);
            if (map_3d.wqzjLine.visibleMask > 0) {
                map_3d.wqzjLine.visibleMask = 0;//隐藏
            } else {
                map_3d.wqzjLine.visibleMask = 15;//显示
            }
        },
        //获取数据源
        fnGetFc: function (fcname, fdsname) {
            if (map_3d.cepServer == null || typeof(map_3d.cepServer) == "")
                return;
            if (fcname == null || typeof(fcname) == "undefined" || fdsname == null || typeof(fdsname) == "undefined")
                return;
            var cr = "ConnType=CMS7HTTP; Server=" + map_3d.cepServer.fdbServerIp + "; Port=" + map_3d.cepServer.fdbServerPort + "; DataBase=" + map_3d.cepServer.fdbServerName;//"ConnType=CMS7HTTP; Server=127.0.0.1; Port=8040; DataBase=view_new";//layerobj.featureClassInfo.dataSourceConnectionString;
            var ds = map_3d.__g.dataSourceFactory.openDataSourceByString(cr);//map_3d.fdbObj
            var fds = ds.openFeatureDataset(fdsname);
            var fc = fds.openFeatureClass(fcname);
            return fc;
        },
        //根据选中模型返回模型对象 for cep file
        fnGetFcByLayer: function (layerobj) {
            if (layerobj == null) {
                if (map_3d.featureLayer == null) {
                    return;
                }
                layerobj = map_3d.featureLayer;
            }
            var fcname = layerobj.featureClassInfo.featureClassName;
            var fdsname = layerobj.featureClassInfo.dataSetName;
            var cr = layerobj.featureClassInfo.dataSourceConnectionString;
            var ds = map_3d.__g.dataSourceFactory.openDataSourceByString(cr);
            var fds = ds.openFeatureDataset(fdsname);
            var fc = fds.openFeatureClass(fcname);
            return fc;
        },
        // ------------预警图层管理模块调用开始------------
        // 删除全部三维中的模型
        deleteGeometry: function () {
            //遍历删除显示模型
            if (map_3d.geometryGuids.length != 0) {
                var obj = map_3d.geometryGuids.pop();
                while (obj != undefined) {
                    var guid = obj.guid;
                    map_3d.__g.objectManager.deleteObject(guid);
                    obj = map_3d.geometryGuids.pop();
                }
            }
        },
        // 删除一个图层，list——>deviceId组成的字符串数组
        deleteGeometryList: function (list) {
            //删除自定义添加的图层
            for (var j in list) {
                var deviceId = list[j];
                var guidKey = "custom_" + deviceId;
                for (var i = 0; i < map_3d.geometryGuids.length; i++) {
                    var obj = map_3d.geometryGuids[i];
                    if (obj.key == guidKey) {
                        var guid = obj.guid;
                        map_3d.__g.objectManager.deleteObject(guid);
                        //删除数组中的对象
                        map_3d.geometryGuids.splice(i, 1);
                        break;
                    }
                }
            }
        },
        //选择路线
        createRenderPolyline: function () {
            //清空之前保留的线
            map_3d.deleteGeometry();
            map_3d.__g.interactMode = gviInteractMode.gviInteractEdit; //几何数据编辑模式
            map_3d.__g.mouseSelectMode = gviMouseSelectMode.gviMouseSelectClick; //鼠标左键点选模式
            map_3d.__g.objectEditor.finishEdit(); //结束几何物体编辑状态。

            var __geo = map_3d.__g.geometryFactory.createGeometry(gviGeometryType.gviGeometryPolyline, gviVertexAttribute.gviVertexAttributeZ);
            var rpolyline = map_3d.__g.objectManager.createRenderPolyline(__geo, null, __rootId);
            var resultCode = map_3d.__g.objectEditor.startEditRenderGeometry(rpolyline, gviGeoEditType.gviGeoEditCreator);
            map_3d.rpolyline = rpolyline;
            //保存到geometryGuids，key为polyline_开头
            var obj = {'key': "polyline_" + rpolyline.guid, 'guid': rpolyline.guid};
            map_3d.geometryGuids.push(obj);
        },
        //路线转图形
        createRenderPolygon: function () {
            var gfactory = map_3d.__g.geometryFactory;
            var fde_polygon = gfactory.createGeometry(gviGeometryType.gviGeometryPolygon,
                gviVertexAttribute.gviVertexAttributeZ);
            var fde_point = gfactory.createGeometry(gviGeometryType.gviGeometryPoint,
                gviVertexAttribute.gviVertexAttributeZ);
            var linPoints = "";
            var line = map_3d.rpolyline.getFdeGeometry();
            for (var i = 0; i < line.pointCount; i++) {
                var point = line.getPoint(i);
                fde_point.setCoords(point.x, point.y, point.z, 0, 0);
                fde_polygon.exteriorRing.appendPoint(fde_point);
                linPoints += point.x + "," + point.y + "," + point.z + ";";
            }
            $('#linPoints').val(linPoints);
            var surfaceSymbol = map_3d.__g.new_SurfaceSymbol;
            surfaceSymbol.color = 0x80FF0000;  // 红色ARGB
            fde_polygon.close();//封闭组成Polygon的所有Ring,如果已经封闭,则不会再次进行封闭。
            var rpolygon = map_3d.__g.objectManager.createRenderPolygon(fde_polygon, surfaceSymbol, __rootId);
            //保存到geometryGuids，key为polygon_开头
            var obj = {'key': "polygon_" + rpolygon.guid, 'guid': rpolygon.guid};
            map_3d.geometryGuids.push(obj);
        },
        //根据points,point.x,point.y,point.z画图层
        createRenderPolygonByPoints: function (points, isGlow, guidKey) {
            var gfactory = map_3d.__g.geometryFactory;
            var fde_polygon = gfactory.createGeometry(gviGeometryType.gviGeometryPolygon,
                gviVertexAttribute.gviVertexAttributeZ);
            var fde_point = gfactory.createGeometry(gviGeometryType.gviGeometryPoint,
                gviVertexAttribute.gviVertexAttributeZ);
            for (var i = 0; i < points.length; i++) {
                var point = points[i];
                fde_point.setCoords(point.x, point.y, point.z, 0, 0);
                fde_polygon.exteriorRing.appendPoint(fde_point);
            }
            var surfaceSymbol = map_3d.__g.new_SurfaceSymbol;
            surfaceSymbol.color = 0x80FF0000;  // 红色ARGB
            fde_polygon.close();//封闭组成Polygon的所有Ring,如果已经封闭,则不会再次进行封闭。
            var rpolygon = map_3d.__g.objectManager.createRenderPolygon(fde_polygon, surfaceSymbol, __rootId);
            if (isGlow == true)
                rpolygon.glow(-1);
            //保存到geometryGuids，key为custom_开头
            var obj = {'key': guidKey, 'guid': rpolygon.guid};
            map_3d.geometryGuids.push(obj);
        },
        //视角改变事件
        onViewChange: function () {
            //视角改变
            if (typeof (map_3d.setViewMinZ) == "function") ____events["onCameraUndoRedoStatusChanged"] = map_3d.setViewMinZ;
        },
        //设置当前视角最低高度为1,最高高度为600
        setViewMinZ: function () {
            var cameraInfo = map_3d.__g.camera.getCamera();
            //console.log(cameraInfo);
            var position = cameraInfo.position;
            var angle = cameraInfo.angle;
            //最低高低
            if (position.z < -20) {
                position.z = 1;
                map_3d.__g.camera.setCamera(position, angle, gviSetCameraFlags.gviSetCameraNoFlags);
            }
            if (position.z > 600.1) {
                position.z = 600;
                map_3d.__g.camera.setCamera(position, angle, gviSetCameraFlags.gviSetCameraNoFlags);
            }
        },
        btn_addImagePoint: function () {
            map_3d.__g.mouseSelectMode = gviMouseSelectMode.gviMouseSelectClick;
            if (typeof (map_3d.addImagePoint) == "function") ____events["onMouseClickSelect"] = map_3d.addImagePoint;
            map_3d.__g.interactMode = gviInteractMode.gviInteractEdit;
        },
        addImagePoint: function (pickResult, intersectPoint, mask, eventSender) {
        	var gfactory = map_3d.__g.geometryFactory;
            var poi = gfactory.createGeometry(gviGeometryType.gviGeometryPoint, gviVertexAttribute.gviVertexAttributeZ);
            poi.x = intersectPoint.x;
            poi.y = intersectPoint.y;
            poi.z = intersectPoint.z;
            var geoSymbol = map_3d.__g.new_ImagePointSymbol;   //将点以图片的形式显示出来
            var imageName = getMediaRelatePath("/png/camera.png");
            console.log(imageName);
            geoSymbol.imageName = imageName;  //使用ImageClass里存在的图片
            geoSymbol.size = 25;
            var rpoi = map_3d.__g.objectManager.createRenderPoint(poi, geoSymbol, __rootId);
        },
        alarmAction: function () {
        	map.showHideByAreaId('', jsConst.ALARM_INFO.areaId, 1);
            map.getAllPointByGrandAndType(jsConst.ALARM_INFO.cusNumber, jsConst.ALARM_INFO.areaId, '', 1);
            map.createRenderPolygonAndSetAdminCamera(jsConst.ALARM_INFO.alarmDeviceId, true, jsConst.ALARM_INFO.areaId, jsConst.ALARM_INFO.cusNumber);
            jsConst.ALARM_INFO = null;
        },

        onKeyUp: function (flags, char) {
            if (flags == 49181 && map_3d.ctrDeviceIds.length > 0) {
                videoClient.playVideoHandle({
                    'subType': 2,
                    'layout': map_3d.ctrDeviceIds.length,
                    'data': map_3d.ctrDeviceIds,
                    'callback': function (data) {
                        //__toggleVideo(treeNode, true);
                        map_3d.ctrDeviceIds = [];
                    }
                });
            }
        },

        //以下为cm事件初始化所需为空方法，勿删  add wq 2018-5-4
        onObjectEditing: function () {
        },
        onCameraUndoRedoStatusChanged: function () {
        },
        onCameraFlyFinished: function () {
        },
        onMouseClickSelect: function () {
        },
        onMouseDragSelect: function () {
        },
        onCameraTourWaypointChanged: function () {
        }
    }
    map_3d.init();
});

