var __g;
var __fcMap = {};     //key: guid, value: fc
var __fcGeoMap = {};  //key: guid, value: geoNames[]
var __fds;
var __datasetCRS = null;
var __rootId;

function getMediaRelatePath(relPath){
	var path = "http://34.211.0.208:8080/prison/static/resource/map/media/";
    // var path = "http://127.0.0.1:8080/electronicMap/static/resource/map/media/";
    // "http://192.168.3.250:8082/electronicMap/static/resource/map/media/";
    // var path = "http://33.167.18.28/static/resource/map/media/";
    return path+relPath;//(relPath).replace(/\//g, "\\");
}


/************************************************************************/
/* 初始化三维控件，并设置天空
/************************************************************************/
function initAxControl() {
    __g = getElementByCm("__g");
    console.log(__g);

    try {
        // 初始化RenderControl控件
        var ps = __g.new_PropertySet;
        ps.setProperty("RenderSystem", "OpenGL");
        __g.initialize(true, ps);
    } catch (err) {
        // alert("初始化失败");
    }
    __g.camera.flyTime = 1;

    __rootId = __g.objectManager.getProjectTree().rootID; //也可直接用字符串"11111111-1111-1111-1111-111111111111"

    initControlEvents(__g);

    var skyboxPath = getMediaRelatePath("/skybox");
    var skyboxObj = __g.objectManager.getSkyBox(0);
    skyboxObj.setImagePath(gviSkyboxImageIndex.gviSkyboxImageBack, skyboxPath + "/1_BK.jpg");
    skyboxObj.setImagePath(gviSkyboxImageIndex.gviSkyboxImageBottom, skyboxPath + "/1_DN.jpg");
    skyboxObj.setImagePath(gviSkyboxImageIndex.gviSkyboxImageFront, skyboxPath + "/1_FR.jpg");
    skyboxObj.setImagePath(gviSkyboxImageIndex.gviSkyboxImageLeft, skyboxPath + "/1_LF.jpg");
    skyboxObj.setImagePath(gviSkyboxImageIndex.gviSkyboxImageRight, skyboxPath + "/1_RT.jpg");
    skyboxObj.setImagePath(gviSkyboxImageIndex.gviSkyboxImageTop, skyboxPath + "/1_UP.jpg");

    return true;
}

/************************************************************************/
/* 加载FDB场景
/************************************************************************/
function loadFdb(fileName, textRender, geoRender) {

    // 加载FDB场景
    var c = __g.new_ConnectionInfo;
    c.connectionType = gviConnectionType.gviConnectionFireBird2x;
    c.database = getMediaRelatePath(fileName);
    try {
        var ds = __g.dataSourceFactory.openDataSource(c);
        var fdsNames = ds.getFeatureDatasetNames();
        if (fdsNames.length == 0)
            return false;
        __fds = ds.openFeatureDataset(fdsNames[0]);
        __datasetCRS = __fds.spatialReference;

        var fcNames = __fds.getNamesByType(gviDataSetType.gviDataSetFeatureClassTable);
        if (fcNames.length == 0)
            return false;

        for (var i = 0; i < fcNames.length; i++) {
            var fc = __fds.openFeatureClass(fcNames[i]);

            // 找到FC里面的所有空间列字段
            var geoNames = [];
            var fieldinfos = fc.getFields();
            for (var j = 0; j < fieldinfos.count; j++) {
                var fi = fieldinfos.get(j);
                if (fi && fi.geometryDef)
                    geoNames.push(fi.name);
            }
            __fcMap[fc.guid] = fc;
            __fcGeoMap[fc.guid] = geoNames;
        }
    }
    catch (e) {
        exceptionHandler(e);
    }

    // CreateFeautureLayer
    var hasfly = false;
    for (var fcId in __fcGeoMap) {
        var fc = __fcMap[fcId];
        var geoNames = __fcGeoMap[fcId];
        for (var k = 0; k < geoNames.length; k++) {
            var geoName = geoNames[k];
            var fl = __g.objectManager.createFeatureLayer(fc, geoName, textRender, geoRender, __rootId);

            if (!hasfly) {
                var fieldinfos = fc.getFields();
                var fi = fieldinfos.get(fieldinfos.indexOf(geoName));
                var env = fi.geometryDef.envelope;
                if (env.maxX == 0.0 && env.maxY == 0.0 && env.maxZ == 0.0 &&
                    env.minX == 0.0 && env.minY == 0.0 && env.minZ == 0.0)
                    continue;

                var pos = __g.new_Vector3;
                var ang = __g.new_EulerAngle;
                pos.set((env.maxX + env.minX) / 2, (env.maxY + env.minY) / 2, (env.maxZ + env.minZ) / 2);
                ang.heading = 0;
                ang.tilt = -20;
                __g.camera.lookAt(pos, 600, ang);

                hasfly = true;
            }
        }
    }
}

/************************************************************************/
/* 加载server场景
/************************************************************************/
function loadServer(c, textRender, geoRender) {
    try {
        var ds = __g.dataSourceFactory.openDataSource(c);
        var fdsNames = ds.getFeatureDatasetNames();
        if (fdsNames.length == 0)
            return false;
        __fds = ds.openFeatureDataset(fdsNames[0]);

        var fcNames = __fds.getNamesByType(gviDataSetType.gviDataSetFeatureClassTable);
        if (fcNames.length == 0)
            return false;

        for (var i = 0; i < fcNames.length; i++) {
            var fc = __fds.openFeatureClass(fcNames[i]);

            // 找到FC里面的所有空间列字段
            var geoNames = [];
            var fieldinfos = fc.getFields();
            for (var j = 0; j < fieldinfos.count; j++) {
                var fi = fieldinfos.get(j);
                if (fi && fi.geometryDef)
                    geoNames.push(fi.name);
            }
            __fcMap[fc.guid] = fc;
            __fcGeoMap[fc.guid] = geoNames;
        }
    }
    catch (e) {
        exceptionHandler(e);
    }

    // CreateFeautureLayer
    var hasfly = false;
    for (var fcId in __fcGeoMap) {
        var fc = __fcMap[fcId];
        var geoNames = __fcGeoMap[fcId];
        for (var k = 0; k < geoNames.length; k++) {
            var geoName = geoNames[k];
            var fl = __g.objectManager.createFeatureLayer(fc, geoName, textRender, geoRender, __rootId);

            if (!hasfly) {
                var fieldinfos = fc.getFields();
                var fi = fieldinfos.get(fieldinfos.indexOf(geoName));
                var env = fi.geometryDef.envelope;
                if (env.maxX == 0.0 && env.maxY == 0.0 && env.maxZ == 0.0 &&
                    env.minX == 0.0 && env.minY == 0.0 && env.minZ == 0.0)
                    continue;

                var pos = __g.new_Vector3;
                var ang = __g.new_EulerAngle;
                pos.set((env.maxX + env.minX) / 2, (env.maxY + env.minY) / 2, (env.maxZ + env.minZ) / 2);
                ang.heading = 0;
                ang.tilt = -20;
                __g.camera.lookAt(pos, 600, ang);

                hasfly = true;
            }
        }
    }
}