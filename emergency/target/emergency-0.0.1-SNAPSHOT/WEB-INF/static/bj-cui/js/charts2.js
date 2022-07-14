$(function(){
    var ltChart;
    var dd;
    var zd;
    var gd;
    var jg;
   function initLeftChart(){
         ltChart = echarts.init(document.getElementById('ltChart'));
         $.ajax({
 			type : 'post',
 			url : $("#rootPath").val() + '/aqfxyp/wxpg/searchWxpg',
 			dataType : 'json',
 			async:false,
 			success : function(data) {
 				var a = data.data;
 				dd = a[0].dd;
 				zd = a[0].zd;
 				gd = a[0].gd;
 			},
 			error : function(XMLHttpRequest, textStatus, errorThrown) {
 			}
 		});
         
        var option3 = {
            backgroundColor: 'transparent',//背景颜色
        
            radar: [{
                nameGap: 13,
                center: ['50%', '50%'],//统计图位置，示例是居中
                radius: '70%',//统计图大小
                startAngle: 73,//统计图起始的角度
                splitNumber: 3,//统计图蛛网的网格分段，示例分为三段
                // shape: 'circle',//蛛网是圆角还是尖角
                name: {
                    formatter:function (value, obj) {
                        var num = obj.num
                        return '{num|'+ num +'}\n' + '{value|' + value + '}';
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
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)",
                textStyle:{
                	fontSize:25
                }
            },
            series: [{
                name: '',
                type: 'pie',//统计图专业名称为雷达图，这里叫做蛛网图
                data:[
                    {value:dd, 
                     name:'低度',
                     label:{
                    	 fontSize:25
                     }},
                    {value:zd,
                     name:'中度',
                     label:{
                    	 fontSize:25
                     }},
                    {value:gd, 
                     name:'高度',
                     label:{
                    	 fontSize:25
                     }}
                ],
                itemStyle: {
                    normal: {
                    	color : function(params) {
                    		var colorList = [
                                'green','yellow','red'
                              ];
                    		 return colorList[params.dataIndex]
                    	}
                    }
                }
            }]
        }
        ltChart.on('click', function (params) {
        	var zt;
        	if(params.data.name == '高度'){
        		zt = '1';
        	}else if(params.data.name == '中度'){
        		zt = '2';
        	}else if(params.data.name == '低度'){
        		zt = '3';
        	}
        	$("#dialog").dialog({
				width : 1000, //属性
				height : 800, //属性
				title : '危险评估',
				modal : true, //属性
				autoOpen : false,
				url : $("#rootPath").val() + "/aqfxyp/wxpg/toIndex?zt="+zt
			});
			$("#dialog").dialog("open");
        	//openDialogUserIframe3("dialogId1","iframeId1","违纪情况","tjms4.jsp?zfjbxxId="+zfjbxxId+"&rq="+rq3);
        });
        ltChart.setOption(option3);
    }

    initLeftChart();

    window.onresize = function () {
        setTimeout(function (){
          ltChart.resize();
      },200)
	}
})