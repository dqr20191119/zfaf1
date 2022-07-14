// JavaScript Document
function pagerTemplate1(){
    return "<span class='paginator-center'>{prev}{links}{next}{pginput}</span><span class='paginator-right'><em>显示方式：每页</em>{rowlist}<em>条</em></span>";
}
function getHeight(){
    var height=$(window).height();
    var header_height=$(".header").height();
    var footer_height=$(".footer").height();
    $(".content").css("min-height",height-header_height-footer_height-22);
    $(".info").css("min-height",$(".content").height());
}
function currentItem(index){
    $(".sidebar li.level0").eq(index).addClass("current");
}
function currentItem1(index){
    $("li.level1 a").eq(index).addClass("curSelectedNode");
}
function link(event,id,node){
    var id = node.id;
    if(id=="dagl"){
    	//window.location.href="${ctx}/dagl/index";
        window.location.href= common.url("/dagl/index");
        //window.open("http://www.w3school.com.cn")新页面中打开
    }else if(id=="code"){
        window.location.href= common.url("/codebase/index");
        //window.open("http://www.w3school.com.cn")新页面中打开
    }else if(id=="s2"){
        window.location.href= common.url("/variety/index");
    }else if(id=="s3"){
        window.location.href= common.url("/opttype/index");
    }else if(id=="s3_1"){
        window.location.href= common.url("/opt_type/index");
    }else if(id=="s3_2"){
        window.location.href= common.url("/user/index");
    }else if(id=="s3_3"){
        window.location.href= common.url("/role/index");
    }else if(id=="s3_4"){
        window.location.href= common.url("/dictionary/index");
    }
}
$(document).ready(function(){
    $('#cc').tree({
        showIcon : false,
        showLine:false,
        data : [
            {name:"档案管理", open:true, id:"dagl"},   
            {name:"编码管理", open:false, id:"code"}
          
        ],
        onClick:"link"
    });

    getHeight();
});