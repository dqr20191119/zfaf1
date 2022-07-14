//左边导航效果显示
$(document).ready(function() {
    $(".subNavBox>li").hover(function() {
        $(this).addClass("hover")
    },
    function() {
        $(this).removeClass("hover")
    });
   
});


//标签切换
function xzschange(name, cursel, n) {
	    for (i = 1; i <= n; i++) {
		var menu = document.getElementById(name + i);
		var con = document.getElementById("con_" + name + "_" + i);
		menu.className = i == cursel ? "tab-on2" : "tab-normal2";
		con.style.display = i == cursel ? "block" : "none";
  }
}
function xzschange1(name, cursel, n) {
	    for (i = 1; i <= n; i++) {
		var menu = document.getElementById(name + i);
		var con = document.getElementById("con_" + name + "_" + i);
		menu.className = i == cursel ? "tabactive01" : "tabnormal01";
		con.style.display = i == cursel ? "block" : "none";
  }
}
function xzschange3(name, cursel, n) {
	    for (i = 1; i <= n; i++) {
		var menu = document.getElementById(name + i);
		var con = document.getElementById("con_" + name + "_" + i);
		menu.className = i == cursel ? "ob-on3" : "ob-normal3";
		con.style.display = i == cursel ? "block" : "none";
  }
}
function xzschange4(name, cursel, n) {
	    for (i = 1; i <= n; i++) {
		var menu = document.getElementById(name + i);
		var con = document.getElementById("con_" + name + "_" + i);
		menu.className = i == cursel ? "tabactive02" : "tabnormal02";
		con.style.display = i == cursel ? "block" : "none";
  }
}