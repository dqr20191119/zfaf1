$(function(){
	var blChart,brChart,ltChart,pieChartList=[],barChartList=[];
	
	/**
	 * Description: 初始化页面底部-风险分布图表数据
	 */
	function initBottomChart(){
		blChart = echarts.init(document.getElementById('blChart'));

		var urlPath = $("#rootPath").val() + "/aqfxyp/txzs/fxfbData";
	    var params = {};
		// Desc: 调用ajax获取数据库中的风险分布数据
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	var option = initBottomChartOption(data.data);
            		blChart.setOption(option);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * Description: 初始化页面底部-风险趋势图表数据
	 */
	function initBottomChart2(){
		brChart = echarts.init(document.getElementById('brChart'));
		var urlPath = $("#rootPath").val() + "/aqfxyp/txzs/fxqsData";
	    var params = {};
		// Desc: 调用ajax获取数据库中的风险趋势数据
		$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                	var option = initBottomChart2Option(data.data);
            		brChart.setOption(option);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * Description: 初始化首页面左侧风险概况图表数据
	 */
	function initLeftChart() {
		ltChart = echarts.init(document.getElementById('ltChart'));
		//var urlPath = $("#rootPath").val() + "/riskAssess/chart/fxgkData";
	    var params = {};
		// Desc: 调用ajax获取数据库中的风险概况数据
		/*$.ajax({
			type : 'post',
			url : urlPath,
			data : params,
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	var option = initLeftChartOption(data.data);
            		ltChart.setOption(option);
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			
			*/
	  
        $.ajax({
			type : 'post',
			url : $("#rootPath").val() + '/aqfxyp/txzs/searchFxgk',
			dataType : 'json',
			async:false,
			success : function(data) {
				var option = initLeftChartOption(data.data);
				ltChart.setOption(option);
		 
			}, 
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
		
	}
	
	/**
	 * Description: 初始化饼图数据
	 */
	function initPies() {
		//var urlPath = $("#rootPath").val() + "/riskAssess/chart/fxpgData";
		// Desc: 调用ajax获取数据库中的风险分布数据
		/*$.ajax({
			type : 'post',
			url : urlPath,
			data : {
				category:title
			},
			dataType : 'json',
			success : function(data) {
                if(data.code == 200) {
                	console.log(data.data);
                	console.log(title);
                	var option = initPiesOption(data.data, title);
            		var pieChart = echarts.init(document.getElementById(id));
            		pieChart.setOption(option);
            		pieChartList.push(pieChart);
            		
            		
            		
                } else if (data.code == 500){
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});*/
		/*initPies("left-pie1","人");
		initPies("left-pie2","地");
		initPies("left-pie3","事");
		initPies("left-pie4","物");
		initPies("left-pie5","情");*/
		
		
		
		$.ajax({
			type : 'post',
			url : $("#rootPath").val() + '/aqfxyp/txzs/getPies',
			dataType : 'json',
			async:false,
			success : function(data) {
				 var dada = data.data;
				 for(var i = 0;i<dada.length;i++){
				 var wwjgname = dada[i].wwjgname;
				 var wwjgjg = dada[i].wwjgjg;
				 if(wwjgname=="人"){
				    var option = initPiesOption(wwjgjg, "人");
            		var pieChart = echarts.init(document.getElementById("left-pie1"));
            		pieChart.setOption(option);
            		pieChartList.push(pieChart);
				 }
				if(wwjgname=="地"){
					var option = initPiesOption(wwjgjg, "地");
            		var pieChart = echarts.init(document.getElementById("left-pie2"));
            		pieChart.setOption(option);
            		pieChartList.push(pieChart);			 
				}
				if(wwjgname=="事"){
					var option = initPiesOption(wwjgjg, "事");
            		var pieChart = echarts.init(document.getElementById("left-pie3"));
            		pieChart.setOption(option);
            		pieChartList.push(pieChart);
				}
				if(wwjgname=="物"){
					var option = initPiesOption(wwjgjg, "物");
            		var pieChart = echarts.init(document.getElementById("left-pie4"));
            		pieChart.setOption(option);
            		pieChartList.push(pieChart);
				}
				if(wwjgname=="情"){
					var option = initPiesOption(wwjgjg, "情");
            		var pieChart = echarts.init(document.getElementById("left-pie5"));
            		pieChart.setOption(option);
            		pieChartList.push(pieChart);
				}
				
			}
		 
			}, 
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * Description: 初始化条形图数据
	 * initBar("right-pie1","人");
	initBar("right-pie2","地");
	initBar("right-pie3","事");
	initBar("right-pie4","物");
	initBar("right-pie5","情");
	 */
	function initBar() {
		/*var urlPath = $("#rootPath").val() + "/riskAssess/chart/fxzxData";
		// Desc: 调用ajax获取数据库中的风险分布数据
		$.ajax({
			type : 'post',
			url : urlPath,
			data : {
				category:title
			},
			dataType : 'json',
			success : function(data) {
				console.log(data.data);
                if(data.code == 200) {
                	var option = initBarOption(data.data);

            		var barChart = echarts.init(document.getElementById(id));
            		barChart.setOption(option);
            		barChartList.push(barChart);
                } else if (data.code == 500){
                	console.log(title);
                	console.log(data.data);
                }
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});*/
		
		$.ajax({
			type : 'post',
			url : $("#rootPath").val() + '/aqfxyp/txzs/getBars',
			dataType : 'json',
			async:false,
			success : function(data) {
				 var dada = data.data;
				 console.log(dada);
				 for(var i = 0;i<dada.length;i++){
				 var wwjgname = dada[i].wwjgname;
				 var wwjgjg = dada[i].wwjgjg;
				 if(wwjgname=="人"){
				    var option = initBarOption(wwjgjg, "人");
            		var barChart = echarts.init(document.getElementById("right-pie1"));
            		barChart.setOption(option);
            		barChartList.push(barChart);
				 }
				if(wwjgname=="地"){
					 var option = initBarOption(wwjgjg, "地");
	            		var barChart = echarts.init(document.getElementById("right-pie2"));
	            		barChart.setOption(option);
	            		barChartList.push(barChart);		 
				}
				if(wwjgname=="事"){
					 var option = initBarOption(wwjgjg, "事");
	            		var barChart = echarts.init(document.getElementById("right-pie3"));
	            		barChart.setOption(option);
	            		barChartList.push(barChart);
				}
				if(wwjgname=="物"){
					 var option = initBarOption(wwjgjg, "物");
	            		var barChart = echarts.init(document.getElementById("right-pie4"));
	            		barChart.setOption(option);
	            		barChartList.push(barChart);
				}
				if(wwjgname=="情"){
					 var option = initBarOption(wwjgjg, "情");
	            		var barChart = echarts.init(document.getElementById("right-pie5"));
	            		barChart.setOption(option);
	            		barChartList.push(barChart);
				}
				
			}
		 
			}, 
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});
	}
	
	/**
	 * Description: 首页显示数据图表初始化
	 */
	// Description: 风险评估图
	initPies();
 
	
	// Description: 风险指向图
	initBar();
	/*initBar("right-pie1","人");
	initBar("right-pie2","地");
	initBar("right-pie3","事");
	initBar("right-pie4","物");
	initBar("right-pie5","情");*/
	
	// Description: 风险概况图
	initLeftChart();
	
	// Description: 风险分布图
	initBottomChart();
	
	// Description: 风险趋势图
	initBottomChart2();
	
	/**
	 * Description: 页面大小调整时，显示数据图表大小随之调整
	 */
	window.onresize = function () {
		setTimeout(function () {
			blChart.resize();
			brChart.resize();
			ltChart.resize();
			var length = pieChartList.length;
			for(var j=length; j--;) {
				pieChartList[j].resize();
				barChartList[j].resize();
			}
		},200);
	}
});
/**
 * Desc:初始化风险分布图Option
 */
function initBottomChartOption(chartData) {
	var xData = [], yData = [], option;
	
	chartData.map(function(a, b) {
		xData.push(a.name);
		yData.push(a.value);
	});
	option = {
		backgroundColor:"transparent",
		color: ['#3398DB'],
		tooltip: {
			trigger: 'axis',
		},
		legend: {
			show: false
		},
		grid: {
			left: '0%',
			right: '0%',
			bottom: '10%',
			top: '15%',
			height: '81%',
			containLabel: true
		},
		xAxis: [{
			type: 'category',
			data: xData,
			interval:1,
			axisTick: {
				alignWithLabel: true
			},
			axisLine: {
				lineStyle: {
					color: '#0c3b71'
				}
			},
			axisLabel: {
				show: true,
				color: '#aeafb0',
				fontSize:12,
				interval:0,
				rotate:40
			}
		}],
		yAxis: [{
			type: 'value',
			splitLine: {
				show: false
			},
			axisLine: {
				show: false
			},
			axisTick: {
				show: false
			},
			axisLabel: {
				show: false
			},
			splitArea: {
				show: true,
				areaStyle: {
					color: 'transparent'
				}
			}
		}],
		series: [{
			name: '总分',
			type: 'bar',
			barWidth: 35,
			xAxisIndex: 0,
			barCategoryGap:3,
			yAxisIndex: 0,
			itemStyle: {
				normal: {
					color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						offset: 0,
						color: '#01f5f7'
					},
					{
						offset: 1,
						color: '#125375'
					}])
				}
			},
			label: {
				normal: {
					show: true,
					position: 'top',
					color:"#a6a7a9",
					fontSize:12
				}
			},
			data: yData,
			zlevel: 11
		}]
	};
	return option;
}
/**
 * desc: 初始化风险趋势图Option
 */
function initBottomChart2Option(chartData) {
	var xData = [], yData = [], option;
	
	chartData.map(function(a, b) {
		xData.push(a.name);
		yData.push(a.value);
	});
	option = {
		backgroundColor: "transparent",
		tooltip: {
		},
		grid: {
			left: '0%',
			right: '3%',
			bottom: '10%',
			top: '15%',
			height: '82%',
			containLabel: true
		},
		xAxis: [{
			type: 'category',
			interval:0,
			boundaryGap: false,
			axisLine: { //坐标轴轴线相关设置。数学上的x轴
				show: true,
				lineStyle: {
					color: '#233e64'
				}
			},
			axisLabel: { //坐标轴刻度标签的相关设置
				textStyle: {
					color: '#a6a6a7',
					margin:15,
				}
			},
			axisTick: {
				show: false
			},
			data: xData
		}],
		yAxis: [{
			type: 'value',
			splitNumber: 5,
			splitLine: {
				show: true,
				lineStyle: {
					color: '#233e64'
				}
			},
			axisLine: {
				show: false
			},
			axisLabel: {
				margin:20,
				textStyle: {
					color: '#a6a6a7'
				}
			},
			axisTick: {
				show: false
			}
		}],
		series: [{
			name: '扣分',
			type: 'line',
			smooth: false, //是否平滑曲线显示
			symbol:'circle',  // 默认是空心圆（中间是白色的），改成实心圆
			
			label: {
				normal: {
					show: true,
					position: 'top',
					distance:10,
					color:"#3deaff",
					fontSize:12
				}
			},
			lineStyle: {
				normal: {
					//  color: "#3deaff"   // 线条颜色
				}
			},
			itemStyle: {
				normal: {
					color: "#3deaff",  // 会设置点和线的颜色，所以需要下面定制 line
				}
			},
			areaStyle: { //区域填充样式
				normal: {
					//线性渐变，前4个参数分别是x0,y0,x2,y2(范围0~1);相当于图形包围盒中的百分比。如果最后一个参数是‘true’，则该四个值是绝对像素位置。
					color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
						offset: 0,  color: 'rgba(61,234,255, 0.9)'
					}, 
					{
						offset: 0.7,  color: 'rgba(61,234,255, 0.1)'
					}], false),
					shadowColor: 'rgba(53,142,215, 0.9)', //阴影颜色
					shadowBlur: 5//shadowBlur设图形阴影的模糊大小。配合shadowColor,shadowOffsetX/Y, 设置图形的阴影效果。
				}
			},
			data: yData
		}]
	};
	return option;
}
/**
 * desc: 初始化条形图Option
 */
function initBarOption(chartData) {
	var yData = [], seriesData = [], valData = [];
	var myColor = ['#33b7e0', '#33b7e0', '#33b7e0', '#33b7e0'];
	chartData.map(function(a, b) {
		yData.push(a.name);
		seriesData.push(a.value);
		valData.push(a.score);
	});
	var option = {
		backgroundColor: 'transparent',
		grid: {
			left: '30%',
			right: '17%',
			bottom: '0',
			top: '3%'
			// z: 22
		},
		xAxis: {
			show: false
		},
		yAxis: [{
			show: true,
			data: yData,
			inverse: true,
			axisLine: {
				show: false
			},
			splitLine: {
				show: false
			},
			axisTick: {
				show: false
			},
			axisLabel: {
				textStyle: {
					fontSize: 14,
					color: '#fff',
				}
			},
		}, {
			show: true,
			inverse: true,
			data: seriesData,
			axisLabel: {
				textStyle: {
					fontSize: 14,
					color: '#fff',
				},
			},
			axisLine: {
				show: false
			},
			splitLine: {
				show: false
			},
			axisTick: {
				show: false
			}
		}],
		series: [{
			name: '条',
			type: 'bar',
			yAxisIndex: 1,
			data: seriesData,
			barWidth: 6,
			barCategoryGap:2,
			itemStyle: {
				normal: {
					barBorderRadius: 20,
					color: function(params) {
						var num = myColor.length;
						return myColor[params.dataIndex % num]
					}
				}
			},
			label: {
				normal: {
					show: false
				}
			}
		}]
	};
	return option;
}
/**
 * desc: 初始化风险概况图Option
 */
function initLeftChartOption(dada) {
	
	 var seriesData ;
     var radarData;
     var zf;
	/*fxgkData.map(function(a, b) {
		total += a.value;
		seriesData.push(a.value);
		var radar = {
			name:a.name,
			num:a.score
		};
		radarData.push(radar);
	});
	*/
     radarData = dada[0];
		seriesData = dada[2];
		zf = dada[1];
	var option = {
		grid: {
			left: '-5%',
			right: '-5%',
			bottom: '0',
			top: '1%',
			// z: 22
		},
		backgroundColor: 'transparent',
		title: {
			text: zf,
			x: '42%',
			y: 'center',
			textStyle: {
				color: "#fff",
				fontSize: 40
			},
			zlevel:24
		},
		radar: [{
			title: {
				text: zf,
			},
			indicator: radarData,
			center: ['55%', '50%'],
			shape: 'polygon',
			radius: '80%',
			nameGap: 3,
			name: {
				formatter:function (value, obj) {
					var num = obj.num
					return '{value|' + value + '}'+' {num|'+ num +'}';
				},
				rich:{
					num: {
						color: "#ee8c04",
						fontSize: 12,
						// padding: [0, 2],
						align: 'center'
					},
					value: {
						color: "#c9caca",
						fontSize: 12 ,
						align: 'center'
					}
				},
				textStyle: {
					color:'#72ACD1'
				}
			},
			axisLine: {
				lineStyle: {
					color: '#cc9b06'
				},
				itemStyle:{
					color: '#cc9b06'
				}
			},
			splitArea:{
				areaStyle:{
					color:'transparent'
				}
			},
			splitLine:{
				lineStyle:{
					color: '#cc9b06'
				}
			}
		}],
		series: [{
			type: 'radar',
			symbolSize: 0,
			areaStyle: {
				normal: {
					color: new echarts.graphic.LinearGradient(0, 0, 1, 0,
					[{
						offset: 0,
						color: '#96750b'
					}, {
						offset: 0.5,
						color: '#715b0e'
					}, {
						offset: 1,
						color: '#171812'
					}], false)
				}
			},
			lineStyle: {
				normal: {
					color: '#cc9b06',
					type: 'solid',
					width: 1
				}
			},
			data: [{
				value: seriesData,
				label: {
					show: 'true'
				}
			}]
		}]
	};
	return option;
}
/**
 * desc: 初始化饼图Option
 */
function initPiesOption(fxpgData, title) {
	var echartData = [], rate = 0;
	fxpgData.fxpgList.map(function(a, b) {
		rate += a.value;
		var pieData = {
			name:a.name,
			value:a.value
		};
		echartData.push(pieData);
	});
	var scale = 1;
	var rich = {
		rate: {
			color: "#00ffff",
			fontSize: 18 ,
			// padding: [0, 2],
			align: 'center'
		},
		total: {
			color: "#c9caca",
			fontSize: 18 ,
			align: 'center'
		}
	};
	var option = {
		grid: {
			bottom: '2%',
			top: '2%'
		},
		tooltip: {
			trigger: 'item',
			formatter: "{a} <br/>{b}: {c}"
		},
		backgroundColor: 'transparent',
		title: {
			text:title,
			left:'center',
			top:'10%',
			padding:[24,0],
			textStyle:{
				color:'#c9caca',
				fontSize:16,
				align:'center'
			}
		},
		legend: {
			selectedMode:false,
			formatter: function(name) {
				//var total = fxpgData.score; //各科正确率总和
				// var rate = 1000;
				var averagePercent; //综合正确率
				
				return '{rate|' + (parseFloat(rate)).toFixed(2) + '}';
			},
			data: [echartData[0].name],
			// data: ['高等教育学'],
			// itemGap: 50,
			left: 'center',
			top: '45%',
			icon: 'none',
			align:'center',
			textStyle: {
				color: "#c9caca",
				fontSize: 16 * scale,
				rich: rich
			},
		},
		series: [{
			name: '扣分',
			type: 'pie',
			radius: ['86%', '71%'],
			hoverAnimation: false,
			color: ['#457ab5', '#457ab5', '#457ab5', '#457ab5', '#457ab5'],
			label:{
				normal: {
					show: false
				},
			},
			itemStyle: {
				normal: {
					borderWidth: 1,
					borderColor: '#000',
				}
			},
			data: echartData
		}]
	};
	return option;
}