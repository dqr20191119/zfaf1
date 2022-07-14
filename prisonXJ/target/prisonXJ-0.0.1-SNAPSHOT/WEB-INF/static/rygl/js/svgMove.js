var containerBox=document.getElementById("containerBox")
var container = document.getElementById("svg");
var center=document.getElementById("center");
var lightDot=document.getElementById("lightDot");
var maskDiv=document.getElementById("maskDiv");
var outDiv=document.getElementById("outDiv");
var scale = 1,timer = null;
var scaleFan=1;
center.style.transform="  scale(" + scale + ")";
var establish = {};
//拖拽和右键移动
establish.leftKey = function(e, name) {
	var event = e || window.event;
	//获取鼠标相对于div的坐标
	var dis_left = event.clientX - containerBox.offsetLeft;
	var dis_top = event.clientY - containerBox.offsetTop;
	//判断  svg 是否有放大
	var isSacle=Number(containerBox.style.transform.split("(")[1].split(")")[0]) ;
	// if(isSacle==1){
	// 	console.log("没有放大")
	// 	containerBox.style.left = "0px";
	// 	containerBox.style.top = "0px";
	// }else{
		//只有触发了nomousedown事件，才会给他添加onmousemove事件
		var x = [];
		document.onmousemove = function(e) {
			var event = e || window.event;
			var disx = event.clientX - dis_left;
			var disy = event.clientY - dis_top;
			if(name == "left") {
				//将鼠标的坐标作为div的坐标
//				center.style.left = disx + "px";
//				center.style.top = disy + "px";
//				outDiv.style.left = disx + "px";
//				outDiv.style.top = disy + "px";
				containerBox.style.left = disx + "px";
				containerBox.style.top = disy + "px";

			} else {
				x.push(disx);
				var n = 27;
				if(x[0] > x[1]) {
					n = n + n;
				} else {
					n = n - n;
				}
			}
		//}
	}
	
}
var splitScal=2.15;
var flag=true;
//缩放
establish.roller = function(e) {
	
	if(scale.toFixed(1) == 4 || scale.toFixed(1) == 1) {
		return scale.toFixed(1) == 4 ? scale -= 0.05 : scale += 0.05;
	} else {
		if(e == 120 || e == 3) {
			if(scale.toFixed(1) != 4) {
				scale += 0.05;
				scaleFan -=0.03;
				if(scaleFan<=0.4){
					scaleFan=0.4;
					if(flag){
						splitScal=scale;
						flag=false;
					}

				}
			}
		} else {
			scale -= 0.05;
			if(scale<splitScal){
				scaleFan +=0.03;
			}
			
			if(scaleFan>=1){
					scaleFan=1;
				}
		}
	}
//	container.style.transform = "  scale(" + scale + ")";
//	center.style.transform = "  scale(" + scale + ")";
//	lightDot.style.transform = "  scale(" + scale + ")";
//	maskDiv.style.transform = "  scale(" + scale + ")";
//outDiv.style.transform = "translate(-50%,-39%)  scale(" + scale + ")";
 containerBox.style.transform = "  scale(" + scale + ")";
 $(".tuBiao").css("transform","scale("+scaleFan+") translate("+((scale-1) * 4.5)+"px,"+((scale-1) * 4.5)+"px)");
 $("#lightDot").css("transform","scale("+scaleFan+") ");
}
//鼠标滚轮缩放
	document.body.onmousewheel = function(event) {
		event = event || window.event;
		establish.roller(event.wheelDelta);
	};

	document.body.addEventListener("DOMMouseScroll", function(event) {
		establish.roller(event.detail);
	});
//鼠标按下事件
	containerBox.onmousedown = function(e) {
		//container.style.transform = "scale(1)";
		if(e.button == 2) {
			establish.leftKey(e, "right");
		} else if(e.button == 0) {
			establish.leftKey(e, "left");
		} else if(e.button == 1) {
			container.style.animation = "";
		}
		document.oncontextmenu(e);
	}

	//松开鼠标后，要移除mousemove事件
	document.onmouseup = function() {
		document.onmousemove = null;
	}

	document.oncontextmenu = function(e) {
		e.preventDefault();
	};
