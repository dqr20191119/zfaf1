/**
 * array
 * Copyright(c) 2014
 * Dependencies:
 * 	无
 */
(function(){
	function CesArray() {

	}
	
	/**
	 * 用途：数组中是否包含目标对象<br>
	 * 输入：array：数组对象; obj:目标对象<br>
	 * 返回：包含返回true，否则返回false
	 */
	CesArray.contains = function(array, obj) {
		var flag = false;
		for ( var i in array) {
			if (array[i] == obj) {
				flag = true;
				break;
			}
		}
		return flag;
	};
	
	
	/** 
	 * 用途：删除数组指定下标或指定对象 <br>
	 * 输入：array：数组对象; obj:目标对象<br>
	 * 用法：arr.remove(2);//删除下标为2的对象（从0开始计算） <br>
	 * 		arr.remove(str);//删除指定对象 <br>
	 * 返回：无
	 */  
	CesArray.remove=function(array, obj){  
	    for(var i =0;i <array.length;i++){  
	        var temp = array[i];  
	        if(!isNaN(obj)){  
	            temp=i;  
	        }  
	        if(temp == obj){  
	            for(var j = i;j <array.length;j++){  
	            	array[j]=array[j+1];  
	            }  
	            array.length = array.length-1;  
	        }     
	    }  
	};  
	
	/**
	 * 用途：数字数组由大到小排序 <br>
	 * 输入：array：数组对象<br>
	 * 返回：由大到小排序的数组
	 */
	CesArray.max2Min = function(array) {  
	    var oValue;  
	    for (var i = 0; i < array.length; i++) {  
	        for (var j = 0; j <= i; j++) {  
	            if (array[i] > array[j]) {  
	                oValue = array[i];  
	                array[i] = array[j];  
	                array[j] = oValue;  
	            }  
	        }  
	    }  
	    return array;  
	};  
	
	/**
	 * 用途：数字数组由小到大排序 <br>
	 * 输入：array：数组对象<br>
	 * 返回：由小到大排序的数组
	 */
	CesArray.min2Max = function(array) {  
	    var oValue;  
	    for (var i = 0; i < array.length; i++) {  
	        for (var j = 0; j <= i; j++) {  
	            if (array[i] < array[j]) {  
	                oValue = array[i];  
	                array[i] = array[j];  
	                array[j] = oValue;  
	            }  
	        }  
	    }  
	    return array;  
	};
	  
	/**
	 * 用途：获得数字数组中最大项 <br>
	 * 输入：array：数组对象<br>
	 * 返回：数组中最大项
	 */
	CesArray.getMax = function(array) {  
	    var oValue = 0;  
	    for (var i = 0; i < array.length; i++) {  
	        if (array[i] > oValue) {  
	            oValue = array[i];  
	        }  
	    }  
	    return oValue;  
	};  
	  
	/**
	 * 用途：获得数字数组中最小项   <br>
	 * 输入：array：数组对象<br>
	 * 返回：最小项
	 */
	CesArray.getMin = function(array) {  
	    var oValue = 0;  
	    for (var i = 0; i < array.length; i++) {  
	        if (array[i] < oValue) {  
	            oValue = array[i];  
	        }  
	    }  
	    return oValue;  
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
	
	ces.array = CesArray;
})();