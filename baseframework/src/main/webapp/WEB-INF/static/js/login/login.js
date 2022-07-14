var boxWidth;//先定义一个变量用来记录宽度，比面计算的时候出错
var boxHeight;
function getHeight(){
    $(".content").css("height",$(window).height()-$(".foot").height());
    var heightDiatance=$(".content").height()-boxHeight;
    $(".box").css("margin-top",heightDiatance/2);
    var widthDiatance=$(".content").width()-boxWidth;
    $(".box").css("margin-left",widthDiatance/2);
}
$(document).ready(function(){
    $('#form1').form({
        ajaxSubmit: true
    });
    //$('#username,#password,#verification').textbox({
    //    readonly:false
    //});
    $('#username,#password').textbox({
        readonly:false
    });
    $('#password').focus(function(){
        $(".label_input").css("display","none");
    });
    $('#username').focus(function(){
        $("#username_label").css("display","none");
    });
    $('#username').blur(function(){
        var value=$('#username').val();
        if(value==""){
            $("#username_label").css("display","block");
        }

    });
    $('#password').blur(function(){
        var value=$('#password').val();
        if(value==""){
            $(".label_input").css("display","block");
        }

    });
    //$('#verification').focus(function(){
    //    $(this).val("");
    //});
    //$('#verification').blur(function(){
    //    var value=$('#verification').val();
    //    if(value==""){
    //        $(this).val("请输入正确的验证码！");
    //    }
    //});

    boxWidth=$(".box").width();
    boxHeight=$(".box").height();
    $(".content").css("min-width",boxWidth);
    $(".content").css("min-height",boxHeight);

    getHeight();
});
$(window).resize(function(){
    getHeight();
});
