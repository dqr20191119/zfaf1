/**
 * date
 * Copyright(c) 2014
 * Dependencies:
 * 	无
 */
(function(){
	function CesDate() {

	}
	
	/**
	 * 用途：按日期时间格式截取字符串<br>
	 * 输入：date:yyyy-MM-dd HH24:mm:ss格式的字符串;fmt格式<br>
	 * 返回：符合格式要求的字符串
	 */
	CesDate.dateTruncate = function(date,fmt){
		var dateStr = "";
		if(date == undefined || date == null || date == ""){
			dateStr = "-";
		}else{
			date = date.split(" ");
			if(fmt == "yyyy-MM-dd"){
				dateStr = date[0];
			}else if(fmt == "HH24:mm:ss"){
				dateStr = date[1];
			}else if(fmt == "MM-dd HH24:mm"){
				dateStr = date[0].substring(5,10)+" "+date[1].substring(0,5);
			}
		}
		return dateStr;
	};

	/**
	 * 用途：将 Date 转化为指定格式的String<br>
	 * 输入：date 日期对象new Date(); fmt 格式<br>
	 * 说明：月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，<br>
	 * 		年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)<br>
	 * 例子：Util.formatDate(new Date(),"yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423<br>
	 * 		Util.formatDate(new Date(),"yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18<br>
	 * 返回：指定格式的String
	 */
	CesDate.formatDate = function(date,fmt) {
		var o = {
			"M+" : date.getMonth() + 1, // 月份
			"d+" : date.getDate(), // 日
			"h+" : date.getHours(), // 小时
			"m+" : date.getMinutes(), // 分
			"s+" : date.getSeconds(), // 秒
			"q+" : Math.floor((date.getMonth() + 3) / 3), // 季度
			"S" : date.getMilliseconds() // 毫秒
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	};
	
	/**
	 * 用途：格式化日期20140401-->2014-04-01<br>
	 * 输入：str:yyyyMMdd<br>
	 * 返回：yyyy-MM-dd
	 */
	CesDate.formatDateString = function(str){
	    str = trim(str);
	    var year,month,day;
	    
	    var reg = /^(\d{4})[-/]?(\d{2})[-/]?(\d{2})$/;
	    if(!reg.test(str))
	       return str;
	    
	    year  = RegExp.$1;
	    month = RegExp.$2;
	    day   = RegExp.$3;
	    
	    return year+"-"+month+"-"+day;
	};

	/**
	 * 用途：去掉日期格式化20140401<--2014-04-01<br>
	 * 输入：str:yyyy-MM-dd<br>
	 * 返回：yyyyMMdd
	 */
	CesDate.unformateDateString = function(str){
		var year,month,day;
		
		reg = /^(\d{4})[-/]?(\d{2})[-/]?(\d{2})$/;
	    if(!reg.test(str))
	       return str;
	    
	    year  = RegExp.$1;
	    month = RegExp.$2;
	    day   = RegExp.$3;
	    
	    return year+month+day;
	};

	/**
	 * 用途：返回合法日期值date1与date2之间大小<br>
	 * 输入：date1 yyyyMMdd;date2 yyyyMMdd<br>
	 * 返回：0相等，-1表示date1<date2，1表示date1.date2
	 */
	CesDate.dateCompare = function(date1,date2){
		var s1=CesDate.formatDateString(date1.toString());
		var s2=CesDate.formatDateString(date2.toString());
		var y1=parseInt(s1.substring(0,4),10);
		var y2=parseInt(s2.substring(0,4),10);
		var m1=parseInt(s1.substring(5,7),10);
		var m2=parseInt(s2.substring(5,7),10);
		var d1=parseInt(s1.substr(8,2),10);
		var d2=parseInt(s2.substr(8,2),10);
		if(y1<y2)
			return -1 ;
		else
		if(y1>y2)
			return 1 ;
		else
			if(m1<m2)
				return -1 ;
			else
			if(m1>m2)
				return 1 ;
			else
				if(d1<d2)
					return -1 ;
				else
				if(d1>d2)
					return 1 ;
				else
					return 0 ;
	};
	
	/**
	 * 用途：返回合法日期值date1与date2之间大小<br>
	 * 输入：date1 yyyy-MM-dd hh24:mi:ss;date2 yyyy-MM-dd hh24:mi:ss<br>
	 * 返回：0相等，-1表示date1<date2，1表示date1.date2
	 */
	CesDate.timeCompare = function(date1,date2){
		var s1=date1.toString();
		var s2=date2.toString();
		var y1=parseInt(s1.substring(0,4),10);
		var y2=parseInt(s2.substring(0,4),10);
		var m1=parseInt(s1.substring(5,7),10);
		var m2=parseInt(s2.substring(5,7),10);
		var d1=parseInt(s1.substr(8,2),10);
		var d2=parseInt(s2.substr(8,2),10);
		var h1=parseInt(s1.substr(11,2),10);
		var h2=parseInt(s2.substr(11,2),10);
		var mi1=parseInt(s1.substr(14,2),10);
		var mi2=parseInt(s2.substr(14,2),10);
		var ss1=parseInt(s1.substr(17,2),10);
		var ss2=parseInt(s2.substr(17,2),10);
		if(y1<y2){
			return -1 ;
		}else{
			if(y1>y2){
				return 1 ;
			}else{
				if(m1<m2){
					return -1 ;
				}else{
					if(m1>m2){
						return 1 ;
					}else{
						if(d1<d2){
							return -1 ;
						}else{
							if(d1>d2){
								return 1 ;
							}else{
								if(h1<h2){
									return -1 ;
								}else{
									if(h1>h2){
										return 1 ;
									}else{
										if(mi1<mi2){
											return -1 ;
										}else{
											if(mi1>mi2){
												return 1 ;
											}else{
												if(ss1<ss2){
													return -1 ;
												}else{
													if(ss1>ss2){
														return 1 ;
													}else{
														return 0 ;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	};
	
	/**
	 * 用途：获取某年某月的最大天数<br>
	 * 输入：year：年；month：月<br>
	 * 返回： 某年某月的最大天数
	 */
	CesDate.getMaxDay = function(year, month) {
		if (month == 4 || month == 6 || month == 9 || month == 11)
			return "30";
		if (month == 2)
			if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
				return "29";
			else
				return "28";
		return "31";
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
	
	ces.date = CesDate;
})();