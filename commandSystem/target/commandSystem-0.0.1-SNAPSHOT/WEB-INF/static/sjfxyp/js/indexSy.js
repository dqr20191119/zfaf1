//安全态势开始
var option1 = {
    title: {
        text: ' '
    },
    legend: {
        data: [ ]
    },
    radar: [
        {
            indicator: [
                { text: '危安犯比例' },
                { text: '警囚比' },
                { text: '民警年龄比例' },
                { text: '整体危险评估' },
                { text: '安防设施故障率' },
                { text: '民警门禁进出次数' },
                { text: '罪犯谈话次数' },
                { text: '亲情会见次数' }
            ],
            center: ['50%', '50%'],
            radius: 120,
            startAngle: 90,
            splitNumber: 8,
            shape: 'circle',
            name: {
                formatter:'【{value}】',
                textStyle: {
                    color:'#72ACD1'
                }
            },
            splitArea: {
                areaStyle: {
                    color: ['rgba(114, 172, 209, 0.2)',
                    'rgba(114, 172, 209, 0.4)', 'rgba(114, 172, 209, 0.6)',
                    'rgba(114, 172, 209, 0.8)', 'rgba(114, 172, 209, 1)'],
                    shadowColor: 'rgba(0, 0, 0, 0.3)',
                    shadowBlur: 10
                }
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(255, 255, 255, 0.5)'
                }
            },
            splitLine: {
                lineStyle: {
                    color: 'rgba(255, 255, 255, 0.5)'
                }
            }
        } 
    ],
    series: [
        {
            name: '雷达图',
            type: 'radar',
            itemStyle: {
                emphasis: {
                    // color: 各异,
                    lineStyle: {
                        width: 4
                    }
                }
            },
            data: [
                {
                    value: [15, 30,20, 15, 0, 12, 5, 5],
                    name: '图二',
                    areaStyle: {
                        normal: {
                            color: 'rgba(255, 255, 255, 0.5)'
                        }
                    }
                }
            ]
        } 
    ]
}
var chartOutChar = null;
chartOutChar = echarts.init(document.getElementById('option1'));
chartOutChar.setOption(option1);
 //安全态势结束
 
 
 //刑事犯危安犯比例
var option2 = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'left',
        data:[]
    },
    series: [
        {
            name:'访问来源',
            type:'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '15',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data:[
                {value:35, name:'刑事犯'},
                {value:310, name:'危安犯'} 
            ]
        }
    ],
     color: [ '#7A67EE',
     '#4876FF']
};
var chartOutChar2 = null;
chartOutChar2 = echarts.init(document.getElementById('option2'));
chartOutChar2.setOption(option2);
 
 
 //监狱保有量
 var option3 = {
   title : {
       
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:[]
    },
    toolbox: {
        show : true,
    },
    grid: {
        left: '0%',
        right: '8%',
        bottom: '0%',
        top:'5%',
        containLabel: true
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : ['一月','二月','三月' ],
			   axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#fff',//左边线的颜色
                        width:'2'//坐标线的宽度
                    }
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff',//坐标值得具体的颜色
 
                    }
                }
            
        }
    ],
    yAxis : [
        {
            type : 'value',
			   axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#fff',//左边线的颜色
                        width:'2'//坐标线的宽度
                    }
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff',//坐标值得具体的颜色
 
                    }
                }
        }
    ],
    series : [
        {
            name:'入监',
            type:'line',
            smooth:true,
            itemStyle: {normal: {areaStyle: {type: 'default'}}},
            data:[10, 3,5 ],
           
        },
        {
            name:'出监',
            type:'line',
            smooth:true,
            itemStyle: {normal: {areaStyle: {type: 'default'}}},
            data:[20, 12 ,9 ],
            color:'#6495ED'
        } 
    ]
};
                    
 var chartOutChar3 = null;
chartOutChar3 = echarts.init(document.getElementById('option3'));
chartOutChar3.setOption(option3);
 
 
 
 //外出统计
  

var option4 = {
    color: ['#3398DB'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '0%',
        right: '4%',
        bottom: '3%',
		top:'3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ['就医', '住院', '探亲', 
            '特许', '其他'],
            axisTick: {
                alignWithLabel: true
            },
			   axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#fff',//左边线的颜色
                        width:'2'//坐标线的宽度
                    }
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff',//坐标值得具体的颜色
 
                    }
                }
        }
    ],
    yAxis : [
        {
            type : 'value',
			   axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#fff',//左边线的颜色
                        width:'2'//坐标线的宽度
                    }
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff',//坐标值得具体的颜色
 
                    }
                }
        }
    ],
    series : [
        {
            name:'',
            type:'bar',
            barWidth: '60%',
            data:[10, 5, 7, 5, 2]
        }
    ]
};
 var chartOutChar4 = null;
chartOutChar4 = echarts.init(document.getElementById('option4'));
chartOutChar4.setOption(option4);
 
 //整体评估分析
 
 
 var option5 = {
    title : {
        
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    
    series : [
        {
            name: '访问来源',
            type: 'pie',
            radius : '75%',
            center: ['50%', '50%'],
            label:null,
            data:[
                {value:5, name:'脱逃'},
                {value:10, name:'自杀'},
                {value:34, name:'暴力'} 
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ],
     color: ['#5CACEE','#96CDCD',
     '#CDAD00']
};

 var chartOutChar5 = null;
chartOutChar5 = echarts.init(document.getElementById('option5'));
chartOutChar5.setOption(option5);
 
 
 
 //在押人数趋势变化
 
 var option6 = {
    
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data:[]
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
		top:'3%',
        containLabel: true
    },
    toolbox: {
         
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['2014','2015','2016','2017','2018'],
		axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#fff',//左边线的颜色
                        width:'2'//坐标线的宽度
                    }
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff',//坐标值得具体的颜色
 
                    }
                }
    },
    yAxis: {
        type: 'value',
		axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#fff',//左边线的颜色
                        width:'2'//坐标线的宽度
                    }
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff',//坐标值得具体的颜色
 
                    }
                }
    },
    series: [
        {
            name:'押犯人数',
            type:'line',
            stack: '总量',
            data:[20, 50, 114, 141, 132],
			itemStyle: {
                 color:'#FFFF00'
            }
			
        } 
    ]
};

  var chartOutChar6 = null;
chartOutChar6 = echarts.init(document.getElementById('option6'));
chartOutChar6.setOption(option6);
 
 
 
 
 //警力分别
 
 
 var option7 = {
    title : {
        
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    
    series : [
        {
            name: '访问来源',
            type: 'pie',
            radius : '75%',
            center: ['50%', '50%'],
            label:null,
            data:[
                {value:5, name:'A区'},
                {value:10, name:'B区'},
				 {value:10, name:'C区'},
                {value:34, name:'D区'} 
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ],
     color: ['#5CACEE','#96CDCD',
     '#CDAD00','#68228B']
};

 var chartOutChar7 = null;
chartOutChar7 = echarts.init(document.getElementById('option7'));
chartOutChar7.setOption(option7);
 
 
 
 
//报警分析
var option8 = {
    color: ['#3398DB'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '0%',
        right: '4%',
        bottom: '3%',
		top:'3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ['A区', 'B区', 'C区', 
            'D区', 'E区'],
            axisTick: {
                alignWithLabel: true
            },
			   axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#fff',//左边线的颜色
                        width:'2'//坐标线的宽度
                    }
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff',//坐标值得具体的颜色
 
                    }
                }
        }
    ],
    yAxis : [
        {
            type : 'value',
			   axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#fff',//左边线的颜色
                        width:'2'//坐标线的宽度
                    }
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff',//坐标值得具体的颜色
 
                    }
                }
        }
    ],
    series : [
        {
            name:'',
            type:'bar',
            barWidth: '60%',
            data:[2, 5, 1, 5, 2]
        }
    ]
};
 var chartOutChar8 = null;
chartOutChar8 = echarts.init(document.getElementById('option8'));
chartOutChar8.setOption(option8);
 
 
 
 var option9 = {
    
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross'
        }
    },
   grid: {
        left: '0%',
        right: '4%',
        bottom: '3%',
		top:'3%',
        containLabel: true
    },
    xAxis:  {
        type: 'category',
        boundaryGap: false,
        data: ['00:00', '01:15', '02:30', '03:45', 
        '05:00', '06:15', '07:30', '08:45', '10:00', 
        '11:15', '12:30', '13:45', '15:00', '16:15', 
        '17:30', '18:45', '20:00', '21:15', '22:30',
        '23:45'],
		 axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#B2DFEE',//左边线的颜色
                        width:'2'//坐标线的宽度
                    }
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff',//坐标值得具体的颜色
 
                    }
                }
    },
    yAxis: {
        type: 'value',
        axisLabel: {
            formatter: '{value} '
        },
        axisPointer: {
            snap: true
        },
		 axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#B2DFEE',//左边线的颜色
                        width:'2'//坐标线的宽度
                    }
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff',//坐标值得具体的颜色
 
                    }
                }
    },
    visualMap: {
        show: false,
        dimension: 0,
        pieces: [{
            lte: 6,
            color: '#B8860B'
        }, {
            gt: 6,
            lte: 8,
            color: '#B8860B'
        }, {
            gt: 8,
            lte: 14,
            color: '#B8860B'
        }, {
            gt: 14,
            lte: 17,
            color: '#B8860B'
        }, {
            gt: 17,
            color: '#B8860B'
        }]
    },
    series: [
        {
            name:'',
            type:'line',
            smooth: true,
            data: [3, 10, 2, 6, 7, 0, 5, 0, 4, 9, 8, 9, 4, 5,
            6, 7, 8, 7, 6, 4],
            
        }
    ]
};

  var chartOutChar9 = null;
chartOutChar9 = echarts.init(document.getElementById('option9'));
chartOutChar9.setOption(option9);



var colors = ['#5793f3', '#d14a61', '#675bba'];

var option10 = {
    color: colors,

    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross'
        }
    },
   grid: {
        left: '0%',
        right: '4%',
        bottom: '3%',
		top:'3%',
        containLabel: true
    },
    legend: {
        data:[]
    },
    xAxis: [
        {
            type: 'category',
            axisTick: {
                alignWithLabel: true
            },
            data: ['1月','2月','3月'],
			 axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#B2DFEE',//左边线的颜色
                        width:'2'//坐标线的宽度
                    }
                },
                axisLabel: {
                    textStyle: {
                        color: '#fff',//坐标值得具体的颜色
 
                    }
                }
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: 'A情况',
            min: 0,
            max: 15,
            position: 'left',
            axisLine: {
                lineStyle: {
                    color: colors[0]
                }
            },
            axisLabel: {
                formatter: '{value} '
            }
        },
        {
            type: 'value',
            name: 'B情况',
            min: 0,
            max: 15,
            position: 'right',
            axisLine: {
                lineStyle: {
                    color: colors[1]
                }
            },
            axisLabel: {
                formatter: '{value} '
            }
        }
    ],
    series: [
        {
            name:'A情况',
            type:'bar',
            data:[2.0, 4.9, 9.0]
        },
        {
            name:'B情况',
            type:'bar',
            yAxisIndex: 1,
            data:[2.6, 5.9, 6.0]
        }
    ]
};
  var chartOutChar10 = null;
chartOutChar10 = echarts.init(document.getElementById('option10'));
chartOutChar10.setOption(option10);

//地图


var option11 = {
    
    tooltip : {
        trigger: 'item'
    },
     
    dataRange: {
        min: 0,
        max: 200,
        x: 'left',
        y: 'bottom',
        text:['高','低'],           // 文本，默认为数值文本
        calculable : true,
        inRange: {
                color: ['#00cccc','#3366ff']
            }
    },
    
    series : [
        {
            name: '来源',
            type: 'map',
            mapType: 'china',
            roam: true,
            itemStyle:{
                normal:{label:{show:true}},
                emphasis:{label:{show:true}}
            },
            data:[
                {name: '北京',value: 70},
                {name: '天津',value: 12},
                {name: '上海',value: 14},
                {name: '重庆',value: 56},
                {name: '河北',value: 87},
                {name: '河南',value: 122},
                {name: '云南',value: 43},
                {name: '辽宁',value: 45},
                {name: '黑龙江',value:56},
                {name: '湖南',value: 7},
                {name: '安徽',value: 34},
                {name: '山东',value: 43},
                {name: '新疆',value: 165},
                {name: '江苏',value: 98},
                {name: '浙江',value: 89},
                {name: '江西',value:111},
                {name: '湖北',value: 122},
                {name: '广西',value:34},
                {name: '甘肃',value:56},
                {name: '山西',value: 34},
                {name: '内蒙古',value: 0},
                {name: '陕西',value: 56},
                {name: '吉林',value:78},
                {name: '福建',value: 12},
                {name: '贵州',value: 56},
                {name: '广东',value: 65},
                {name: '青海',value: 87},
                {name: '西藏',value:134},
                {name: '四川',value: 567},
                {name: '宁夏',value: 12},
                {name: '海南',value: 1},
                {name: '台湾',value: 4},
                {name: '香港',value: 10},
                {name: '澳门',value: 8}
            ]
        }
    ]
};

var chartOutChar11 = null;
chartOutChar11= echarts.init(document.getElementById('option11'));
chartOutChar11.setOption(option11);              
chartOutChar11.on('click', function (params) {//点击事件
        if (params.componentType === 'series') {
             var provinceName =params.name;
             alert(provinceName);
            }
});






var option12 = {
    
    tooltip : {
        trigger: 'item'
    },
     
    dataRange: {
        min: 0,
        max: 100000,
        x: 'left',
        y: 'bottom',
        text:['高','低'],           // 文本，默认为数值文本
        calculable : true,
        inRange: {
                color: ['#00cccc','#3366ff']
            }
    },
    
    series : [
        {
            name: '来源',
            type: 'map',
            mapType: '新疆',
            roam: true,
            itemStyle:{
                normal:{label:{show:true}},
                emphasis:{label:{show:true}}
            },
            data:[
					/*{name: '乌鲁木齐市',value: 2004},
	                {name: '克拉玛依市',value: 323},
	                {name: '吐鲁番地区',value: 247},
	                {name: '哈密地区',value: 97},
	                {name: '阿克苏地区',value: 23600},
	                {name: '喀什地区',value: 85744},
	                {name: '和田地区',value: 52769},
	                {name: '昌吉回族自治州',value: 1218},
	                {name: '博尔塔拉蒙古自治州',value: 423},
	                {name: '巴音郭楞蒙古自治州',value: 1880},
	                {name: '克孜勒苏柯尔克孜自治州',value: 1164},
	                {name: '伊犁哈萨克自治州',value: 4014},
	                {name: '阿勒泰地区',value: 609},
	                {name: '塔城地区',value: 934},
	                {name: '五家渠市',value: 934},
	                {name: '石河子市',value: 934},
	                {name: '阿拉尔市',value: 934},
	                {name: '图木舒克市',value: 934},
	                {name: '铁门关市',value: 934},
	                {name: '双河市',value: 934},
	                {name: '北屯市',value: 934}*/
            ]
        }
    ]
};

var chartOutChar12 = null;
chartOutChar12= echarts.init(document.getElementById('option12'));
chartOutChar12.setOption(option12);              
chartOutChar12.on('click', function (params) {//点击事件
        if (params.componentType === 'series') {
             var provinceName =params.name;
             alert(provinceName);
            }
});

function denglushow(){
  $("#djzz_landing").show();
  $("#djzz_landingbar").show();
  
} 
function dengluhide(){
  $("#djzz_landing").hide();
  $("#djzz_landingbar").hide();
  
} 


