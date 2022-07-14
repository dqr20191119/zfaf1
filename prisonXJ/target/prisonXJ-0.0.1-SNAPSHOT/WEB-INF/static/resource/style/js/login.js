function login(){
			$("#mesBox").hide().empty();
			if(!$("#loginName").val()){
				$("#mesBox").append("<span><img src='"+ctx+"/res/resource/style/images/login/fail.png'/></span><span>用户名不能为空！</span>").show();
				return;
			}

			if(!$("#password").val()){
				$("#mesBox").append("<span><img src='"+ctx+"/res/resource/style/images/login/fail.png'/></span><span>密码不能为空！</span>").show();
				return;
			}
			
			$('#loginForm').submit();

		}
