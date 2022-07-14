/**
 * @Description: ajax请求封装工具方法
 * @author mxz
 * @date 2017-12-21
 */
var _DOCUMENT_EVENT = {
    /**
     * ajax获取数据返回
     * @param url 请求地址
     * @param params 参数集
     */
    request_data:function(url,params,async){
        var data = null;
        if(async == null || async == undefined)
            async = true;
        if(!params){
            params = {};
        }
        $.ajax({
            url : url,
            type : 'POST',
            data : params,
            dataType : 'json',
            async : false,
            success : function(result){
                data = result;
            }
        });
        return data;
    },
    /**
     * ajax获取数据返回
     * @param url 请求地址
     * @param reqParams 请求参数集
     * @param resParams 响应参数集
     */
    request_data_callback:function(url,fun,reqParams,resParams){
        if(!reqParams){
            reqParams = {};
        }
        $.ajax({
            url : url,
            type : 'POST',
            data : reqParams,
            dataType : 'json',
            success : function(result){
                var respons = [];
                respons.push(result);
                if(resParams){
                    $.each(resParams,function(index,item){
                        respons.push(item);
                    });
                }
                doCallback(fun,respons);
            }
        });
    },
    /**
     * 设置alert输出
     * @param msg 输出内容
     */
    alert_msg:function(msg){
        $.alert({
            message:msg,
            title:"提示信息",
            iframePanel:true
        });
    },
    /**
     * 设置messge输出
     * @param msg 输出内容
     */
    messge:function(msg){
        $.message({
            message: msg,
            iframePanel: true
        });
    }
}
//这个方法做了一些操作、然后调用回调函数
function doCallback(fn,args) {
    fn.apply(this, args);
}

