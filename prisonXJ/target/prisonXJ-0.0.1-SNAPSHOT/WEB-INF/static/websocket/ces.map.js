/** 
 * map
 * Copyright(c) 2014
 * Dependencies: 无
 * 
 * 说明：模拟java Map对象
 * var myMap = new ces.Map();
 * myMap.put("key","value");
 * var key = myMap.get("key");
 * myMap.remove("key");
 */
(function(){
	
	function namespace(packages) {
		var d = packages.split(".");
		var o = window["ces"] = window["ces"] || {};
		$.each(d.slice(0), function(j, v2) {
			o = o[v2] = o[v2] || {};
		});
		return o;
	}
	namespace("core");
	
	ces.Map = function() {

		this.elements = new Array();
		
		/**
		 * 用途：获取map大小<br>
		 * 输入：无<br>
		 * 返回：map大小
		 */
		this.size = function(){
			return this.elements.length;
		};
		
		/**
		 * 用途：判断map是否为空<br>
		 * 输入：无<br>
		 * 返回：为空返回true，否则返回false
		 */
		this.isEmpty = function(){
			return (this.elements.length < 1);
		};
		
		/**
		 * 用途：清空map键值对<br>
		 * 输入：无<br>
		 * 返回：无
		 */
		this.clear = function(){
			this.elements = new Array();
		};
		
		/**
		 * 用途：往map中添加键值对<br>
		 * 输入：_key键;_value值<br>
		 * 返回：无
		 */
		this.put = function(_key, _value){
			this.remove(_key);
			this.elements.push({key: _key, value: _value});
		};
		
		/**
		 * 用途：删除map中的键值对<br>
		 * 输入：_key键<br>
		 * 返回：删除成功返回true,否则返回false
		 */
		this.remove = function(_key){
			try {
				for (var i = 0; i < this.elements.length; i++) {
					if (this.elements[i].key == _key) {
						this.elements.splice(i, 1);
						return true;
					}
				}
			} catch (e) {
				return false;
			}
			return false;
		};
		
		/**
		 * 用途：获取map中的键对应的值<br>
		 * 输入：_key键<br>
		 * 返回：值
		 */
		this.get = function(_key){
			try {
				for (var i = 0; i < this.elements.length; i++) {
					if (this.elements[i].key == _key) { return this.elements[i].value; }
				}
			} catch (e) {
				return null;
			}
		};
		
		/**
		 * 用途：通过索引获取键值对<br>
		 * 输入：索引<br>
		 * 返回：键值对
		 */
		this.element = function(_index){
			if (_index < 0 || _index >= this.elements.length) { return null; }
			return this.elements[_index];
		};
		
		/**
		 * 用途：判断map中是否包含键<br>
		 * 输入：_key键<br>
		 * 返回：包含返回true,否则返回false
		 */
		this.containsKey = function(_key){
			try {
				for (var i = 0; i < this.elements.length; i++) {
					if (this.elements[i].key == _key) {
						return true;
					}
				}
			} catch (e) {
				return false;
			}
			return false;
		};
		
		/**
		 * 用途：获取map中所有的值<br>
		 * 输入：无<br>
		 * 返回：所有值的数组
		 */
		this.values = function(){
			var arr = new Array();
			for (var i = 0; i < this.elements.length; i++) {
				arr.push(this.elements[i].value);
			}
			return arr;
		};
		
		/**
		 * 用途：获取map中所有的键<br>
		 * 输入：无<br>
		 * 返回：所有键的数组
		 */
		this.keys = function(){
			var arr = new Array();
			for (var i = 0; i < this.elements.length; i++) {
				arr.push(this.elements[i].key);
			}
			return arr;
		};
	};
	
})();
