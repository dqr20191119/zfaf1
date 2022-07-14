// JavaScript Document
function getHeight(){
    var height=$(window).height();
    var header_height=$(".header").height();
    var footer_height=$(".footer").height();
    $(".content").css("min-height",height-header_height-footer_height);
}