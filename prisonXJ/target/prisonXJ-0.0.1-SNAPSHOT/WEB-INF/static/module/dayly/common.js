	/**
	 * 判断是否为空值
	 * @param _v 待验证的值
	 */
	function isEmpty(_v){
		if (_v == undefined) return true;
		if (_v == null) return true;
		if (_v == "") return true;
		return false;
	}

	/**
	 * 时间转字符串
	 * @param date
	 * @param type
	 * @returns {String}
	 */
	function formatterDate(date,type) {
		if(isEmpty(date)){
			return null;
		}
		var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
		var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
				+ (date.getMonth() + 1);
		var hours = date.getHours() > 9 ? date.getHours() : "0" + date.getHours();
		var seconds = date.getSeconds() > 9 ? date.getSeconds() : "0" + date.getSeconds();
		var minutes = date.getMinutes() > 9 ? date.getMinutes() : "0" + date.getMinutes();
		if(type == "ymd"){
			return date.getFullYear() + '-' + month + '-' + day;
		}else if(type == "hms"){
			return hours + ":" + minutes + ":" + seconds;
		}else if(type == "hm"){
			return hours + ":" + minutes;
		}else if(type == "ymdhms"){
			return date.getFullYear() + '-' + month + '-' + day + " " + hours + ":" + minutes + ":" + seconds;
		}else if(type == "ymdhm"){
			return date.getFullYear() + '-' + month + '-' + day + " " + hours + ":" + minutes;
		}else{
			return date.getFullYear() + '-' + month + '-' + day;
		}
	}
	
	/**
	 * 返回当前时间
	 * type = 0 返回 YYYY-MM-DD 
	 * type = 1 返回YYYY-MM-DD HH:MM:SS
	 */
	function getCurTime(type){
		var nowDate = new Date();
		var day = nowDate.getDate().toString().length == 2?nowDate.getDate():'0'+nowDate.getDate();
		var month = (nowDate.getMonth()+1).toString().length == 2?(nowDate.getMonth()+1):'0'+(nowDate.getMonth()+1);
		if(type == 0){
			return nowDate.getFullYear() +'-'+ month +'-'+ day;
		}else if(type == 1){
			return nowDate.getFullYear() +'-'+ month +'-'+ day +
			' '+nowDate.getHours()+':'+nowDate.getMinutes()+':'+ nowDate.getSeconds();
		}
	}