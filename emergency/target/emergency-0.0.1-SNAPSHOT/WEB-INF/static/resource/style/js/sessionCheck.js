var isSessionInvalid = false;
var csrf = $('meta[name="_CSRFToken"]').attr('content');
$.ajaxSetup({
	data: {
		_CSRFToken : csrf
	},
	cache : false,
/*	beforeSend : function(xhr, settings) {
		switch (settings.type) {
		case "GET":
			settings.url += "&_CSRFToken=" + csrf;
			break;
		case "POST":
			settings.data += "&_CSRFToken=" + csrf;
			break;
		}
	},*/
	complete : function(data, TS) {
		var status = data.status;
		if (status == 300469) {
			$.confirm("检测到重复登录，点击确认重新登陆！", function(r) {
				if (r) {
					window.location.href = ctx + "/logout.jsp";
					$('meta[name="_CSRFToken"]').attr("content","");
				}
			});
		}
		else if (status == 300468) {
			$.confirm("会话失效或者用户已退出，点击确认重新登陆！", function(r) {
				if (r) {
					window.location.href = ctx + "/logout.jsp";
					$('meta[name="_CSRFToken"]').attr("content","");
				}
			});
		}
	},
	error : function(e) {
		error(e);
	}
});