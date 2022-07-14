var lock = false;
$(document).ready(function () {
    common.initLeftMenuClick();
});
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, '');
};
var common = {
    /**页面元素类型：下拉选择*/
    ELEMENT_TYPE_SELECT: "1",
    /**页面元素类型：多选框*/
    ELEMENT_TYPE_CHECKBOX: "2",
    /**页面元素类型：单选框*/
    ELEMENT_TYPE_RADIO: "3",
    /**页面元素触发事件调用方法*/
    callFunc: null,
    /**查询的共通CODE ID*/
    codeId: null,
    /**查询的共通CODEList*/
    commonCodeList: null,
    /** 项目上下文路径 */
    contextPath: '',
    /** 添加项目上下文路径 */
    url: function (url) {
        return this.contextPath + url;
    },
    /** 加载样式文件 */
    loadCss: function (path) {
        if (!path || path.length === 0) return;

        var head = document.getElementsByTagName('head')[0];
        var link = document.createElement('link');
        link.href = this.url(path);
        link.rel = 'stylesheet';
        link.type = 'text/css';
        head.appendChild(link);
    },
    /** 加载js文件 */
    loadJs: function (path) {
        if (!path || path.length === 0) return;
        document.write('<script type="text/javascript" src="' + this.url(path) + '"></script>');
    },
    /**
     * 增加cookie
     * @param objName
     * @param objValue
     * @param objHours
     * @returns
     */
    addCookie: function (objName, objValue, objHours) {
        var str = objName + "=" + escape(objValue) + "; path=/";
        if (objHours > 0) {
            var date = new Date();
            var ms = objHours * 3600 * 1000;
            date.setTime(date.getTime() + ms);
            str += "; expires=" + date.toGMTString();
        }
        document.cookie = str;
    },
    /**
     * 读取cookie
     * @param objName
     * @returns
     */
    getCookie: function (objName) {
        var arrStr = document.cookie.split("; ");
        for (var i = 0; i < arrStr.length; i++) {
            var temp = arrStr[i].split("=");
            if (temp[0] == objName)
                return unescape(temp[1]);
        }
    },
    /**
     * 将null或"null"字符串置为""
     * @param obj
     * @returns
     */
    null2empty: function (obj) {
        if (!obj || "null" == obj)
            return "";
        for (var p in obj) {
            if (obj[p] instanceof Object) {
                obj[p] = null2empty(obj[p]);
            } else if (!obj[p] || "null" == obj[p]) {
                obj[p] = "";
            }
        }
        return obj;
    },
    /**
     * 将null或"null"字符串置为"0"
     * @param obj
     * @returns
     */
    null2zero: function (obj) {
        return isEmpty(obj) ? "0" : obj;
    },
    /**
     * 判断是否为空串（null/"null"/""都作为空串处理）
     * @param obj
     * @returns {Boolean}
     */
    isEmpty: function (obj) {
        if (undefined === obj || null === obj || 0 === obj.length || "null" === obj) {
            return true;
        }
        return false;
    },
    /**
     * 判断是否不为空串（null/"null"/""都作为空串处理）
     * @param obj
     * @returns
     */
    isNotEmpty: function (obj) {
        return !isEmpty(obj);
    },
    /**
     * 根据共通CodeId设置CoralData
     * @param codeId 明细code
     * @param emptyText 空选项的显示名称（没有则不传）
     */
    setCoralData: function (codeId, emptyText) {
        var commonCodeGroupList = $.grep(this.commonCodeList, function (objdata, i) {
            return objdata.codeId == codeId;
        });
        return commonCodeGroupList;
    },
    /**
     * 根据共通CodeId设置text
     * @param codeId 明细code
     * @param codeList 明细code列表
     */
    setCoralFormatData: function (codeDtlId, codeList) {
        var commonCodeGroupList = $.grep(codeList, function (objdata, i) {
            return objdata.value == codeDtlId;
        });
        return commonCodeGroupList[0].text;
    },
    /**
     * 获取上下文路径
     */
    getWholeURL: function (url) {
        //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
        var curWwwPath = window.document.location.href;
        //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        //获取主机地址，如： http://localhost:8083
        var localhostPaht = curWwwPath.substring(0, pos);
        //获取带"/"的项目名，如：/uimcardprj
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        return (localhostPaht + projectName + url);
    },
    /**
     * 初始化点击左侧列表菜单的样式变化
     */
    initLeftMenuClick: function () {
        $("#left_menu li:gt(0)").each(function (i, e) {
            $(e).bind("click", function () {
                $("#left_menu li:gt(0)").removeClass("active");
                $(this).addClass("active");
            });
        });
    },
    /**
     * 根据共通Code初始化页面元素，eleType:元素类型1-下拉框 2-多选框 3-单选框,eleId:页面元素Id
     */
    initCoralElements: function (eleType, eleId) {
        var coralDataGroup = common.setCoralData(common.codeId);
        var $ele = $("#" + eleId);
        if (eleType == common.ELEMENT_TYPE_SELECT) {
            $ele.combobox({
                data: coralDataGroup,
                onSelect: function (event, ui) {
                    common.callFunc(ui.item.value);
                }
            });
        }
    },
    /**
     * url:请求获得数据的路径
     * filed1,filed2需要转换的字段名(顺序要与 text,value一致)
     */
    initSpacialSelectElements: function (eleId, url, filed1, filed2) {
        var DataGroup;
        $.ajax({
            url: common.getWholeURL(url),
            success: function (data) {
                var DataGroup = [];
                for (var x in data) {
                    var obj = data[x];
                    if (x != null) {
                        if (x == "0") {
                            DataGroup.push({"text": obj[filed1], "value": "" + obj[filed2], "selected": true});
                        } else {
                            DataGroup.push({"text": obj[filed1], "value": "" + obj[filed2]});
                        }
                    }
                }
                var $ele = $("#" + eleId);
                $ele.combobox({
                    data: DataGroup,
                    onSelect: function (event, ui) {
                        common.callFunc(ui.item.value);
                    }
                });
            },
            error: function () {
                alert("数据加载异常");
                return;
            }
        });
    },
    // 检索文字
    searchStringConst: function (ctlId, strConst) {
        $("#" + ctlId).val(strConst);
        $("#" + ctlId).focus(function () {
            this.style.color = 'black';
            if (this.value == strConst) {
                this.value = '';
            }
            ;
        });
        $("#" + ctlId).blur(function () {
            if (this.value == '') {
                this.value = strConst;
            }
            ;
            this.style.color = '#ccc';
        });
    },
    /**
     * error处理
     * @param vm
     */
    errMethod: function (errMsgList) {
        if (!common.isEmpty(errMsgList)) {
            $.message({
                message: errMsgList,
                type: "danger"
            });
        } else {
            $.message({
                message: "发生未知错误",
                type: "danger"
            });
        }
        return;
    }
};