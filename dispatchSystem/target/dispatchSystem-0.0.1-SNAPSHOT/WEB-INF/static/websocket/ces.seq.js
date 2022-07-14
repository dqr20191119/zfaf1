/**
 * seq
 * Copyright(c) 2014
 * Dependencies:
 * 	date
 */
(function(){

	var oldTime = "";

	var oldNum = 1;

	function fi0(i){ 
		var b = "0000" + i;
		return b.substring(b.length - 4);
	};

	function getT(){ 
		var d = new Date();
		return ces.date.formatDate(d,"yyyyMMddhhmmss");
	};
	
	function CesSeq() {

	}

	/**
	 * 用途：获取当前yyyyMMddhhmmssXXXXX顺序号<br>
	 * 输入：无<br>
	 * 返回：顺序号
	 */
	CesSeq.get = function(){ 
		var s = getT();
		var cn = 1;
		while (oldTime==s && cn <= oldNum) {
			cn = oldNum + 1;
			if (cn >= 10000) {
				window.setTimeout(function(){ s = getT();cn = 1;},1000);
			}
		}
		oldTime = s;
		s = s + fi0(cn);
		oldNum = cn;
		return s;
	};
	
	function namespace(packages) {
		var d = packages.split(".");
		var o = window["ces"] = window["ces"] || {};
		$.each(d.slice(0), function(j, v2) {
			o = o[v2] = o[v2] || {};
		});
		return o;
	}
	namespace("core");
	
	ces.seq =CesSeq;
	
})();


