//安全态势开始
var zfxx_fxpg = {
    title: {
        text: ' '
    },
    legend: {
        data: [ ]
    },
    radar: [
        {
            indicator: [
                { text: '调监影响' },
                { text: '会见影响' },
                { text: '与罪犯关系' },
                { text: '与民警关系' },
                { text: '劳动教育' }
            ],
            center: ['50%', '50%'],
            radius: 90,
            startAngle: 90,
            splitNumber: 5,
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
                    value: [15, 30,20, 15, 0, 12],
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
var chartOutChar_zfxx_fxpg = null;
chartOutChar_zfxx_fxpg = echarts.init(document.getElementById('zfxx_fxpg'));
chartOutChar_zfxx_fxpg.setOption(zfxx_fxpg);





 var zfxx_jfkh = {
    
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
        data: ['一月','月二','三月','四月','五月','六月'],
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
            name:'奖励分',
            type:'line',
            stack: '奖励分',
            data:[12, 10, 12, 9, 8,12],
			itemStyle: {
                 color:'#FFFF00'
            }
			
        } 
    ]
};

  var chartOutChar_zfxx_jfkh = null;
chartOutChar_zfxx_jfkh = echarts.init(document.getElementById('zfxx_jfkh'));
chartOutChar_zfxx_jfkh.setOption(zfxx_jfkh);
 
 
 
 
//大帐消费
 var zfxx_dzxf = {
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
            data : ['周一','周二','周三','周四','周五','周六','周日' ],
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
            name:'消费',
            type:'line',
            smooth:true,
            itemStyle: {normal: {areaStyle: {type: 'default'}}},
            data:[10,4,5,7,7,1,7 ],
            color:'#ADFF2F'
           
        } 
    ]
};
                    
 var chartOutChar_zfxx_dzxf = null;
chartOutChar_zfxx_dzxf = echarts.init(document.getElementById('zfxx_dzxf'));
chartOutChar_zfxx_dzxf.setOption(zfxx_dzxf);
 
 //亲情会见
 
var colors = ['#5793f3', '#d14a61', '#675bba'];

var zfxx_qqhj = {
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
            name: '亲属',
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
            name: '非亲属',
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
            name:'亲属',
            type:'bar',
            data:[2.0, 4.0, 3.0]
        },
        {
            name:'非亲属',
            type:'bar',
            yAxisIndex: 1,
            data:[2.0, 5.0, 1.0]
        }
    ]
};
  var chartOutChar_zfxx_qqhj = null;
chartOutChar_zfxx_qqhj = echarts.init(document.getElementById('zfxx_qqhj'));
chartOutChar_zfxx_qqhj.setOption(zfxx_qqhj);
 



 
 
 
 
 
 
 
 
 
 
 
 