
 //功能模块使用热点
var genzhong_mkrd = {
     
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        top:'3%',
        containLabel: true
    },
    xAxis: {
        type: 'value',
        boundaryGap: [0, 0.01],
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
        type: 'category',
        data: ['20-25岁','25-30岁','30-35岁','35-40岁','40-45岁','45岁以上'],
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
            name: '',
            type: 'bar',
            data: [21, 45, 51, 106, 74, 94],
            itemStyle:{color:'#1E90FF'}
        } 
    ]
};

  var chartOutChar_genzhong_mkrd= null;
chartOutChar_genzhong_mkrd= echarts.init(document.getElementById('genzhong_mkrd'));
chartOutChar_genzhong_mkrd.setOption(genzhong_mkrd);
 
 
 
 
 //功能模块使用热点(di)
var genzhong_mkrdd= {
     
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'shadow'
        }
    },
    
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        top:'3%',
        containLabel: true
    },
    xAxis: {
        type: 'value',
        boundaryGap: [0, 0.01],
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
        type: 'category',
        data: ['专科以下','高职专科','大学本科','研究生及以上'],
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
            name: '',
            type: 'bar',
            data: [30, 75, 241, 45],
            itemStyle:{color:'#7FFFAA'}
        } 
    ]
};

  var chartOutChar_genzhong_mkrdd= null;
chartOutChar_genzhong_mkrdd= echarts.init(document.getElementById('genzhong_mkrdd'));
chartOutChar_genzhong_mkrdd.setOption(genzhong_mkrdd);
 
 
 
 //系统使用情况
 var genzhong_xtsy = {
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
            data : ['2013年','2014年','2015年','2016年','2017年','2018年','2019年' ],
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
            name:'人数',
            type:'line',
            smooth:true,
            itemStyle: {normal: {areaStyle: {type: 'default'}}},
            data:[230,290,330,353,385,372,391 ],
            color:'#6495ED'
           
        } 
    ]
};
                    
 var chartOutChar_genzhong_xtsy = null;
chartOutChar_genzhong_xtsy = echarts.init(document.getElementById('genzhong_xtsy'));
chartOutChar_genzhong_xtsy.setOption(genzhong_xtsy);
 
 //一周业务使用情况
 var genzhong_yzywsyqk = {
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b}: {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        x: 'center',
        data:[]
    },
    series: [
        {
            name:'人数',
            type:'pie',
            radius: ['50%', '80%'],
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
                {value:23, name:'一监区'},
                {value:43, name:'二监区'},
                {value:21, name:'三监区'},
                {value:18, name:'四监区'},
                {value:19, name:'五监区'} ,
                {value:3, name:'六监区'} ,
                {value:22, name:'七监区'} ,
                {value:24, name:'八监区'} ,
                {value:20, name:'九监区'} ,
                {value:19, name:'十监区'} ,
                {value:17, name:'十一监区'} 

            ]
        }
    ],
     color: [ '#ADD8E6','#00BFFF',
     '#CDAD00','#4876FF','#87CEFA','#32CD32']
};
var chartOutChar_genzhong_yzywsyqk = null;
chartOutChar_genzhong_yzywsyqk = echarts.init(document.getElementById('genzhong_yzywsyqk'));
chartOutChar_genzhong_yzywsyqk.setOption(genzhong_yzywsyqk);
 
 
 //流程办结情况
 var genzhong_lcbjqk = {
    
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
        data: ['劳动改造科','狱政管理科','教育改造科','刑罚执行科','指挥中心','创建办公室','基建办公室'],
		axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#fff',//左边线的颜色
                        width:'2'//坐标线的宽度
                    }
                },
                axisLabel: {
                	interval:0,
                	rotate:0,
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
            name:'人数',
            type:'line',
            stack: '人数',
            data:[6, 7, 8, 5, 6,3,5],
			itemStyle: {
                 color:'#FFFF00'
            }
			
        } 
    ]
};

  var chartOutChar_genzhong_lcbjqk = null;
chartOutChar_genzhong_lcbjqk = echarts.init(document.getElementById('genzhong_lcbjqk'));
chartOutChar_genzhong_lcbjqk.setOption(genzhong_lcbjqk);
 
 
var genzhong_sjjhsy = {
    
    tooltip: {
        trigger: 'axis'
    },
    
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        top:'3%',
        containLabel: true
    },
     
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['一监区','二监区','三监区','四监区','五监区','六监区','七监区','八监区','九监区','十监区','十一监区'],
		axisLine: {
                    lineStyle: {
                        type: 'solid',
                        color: '#fff',//左边线的颜色
                        width:'2'//坐标线的宽度
                    }
                },
                axisLabel: {
                	interval:0,
                	rotate:0,
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
                 name:'民警',
                 type:'line',
                 stack: '人数',
                 data:[22, 23, 19, 24, 25, 3, 21, 17, 16, 21, 19],
     			color:'#00FF00',
             } ,
        {
            name:'罪犯',
            type:'line',
            stack: '人数',
            data:[79, 91, 96, 100, 66, 0, 100, 108, 32, 108, 108],
			color:'#FF00FF',
        }
       
    ]
};
  var chartOutChar_genzhong_sjjhsy = null;
chartOutChar_genzhong_sjjhsy = echarts.init(document.getElementById('genzhong_sjjhsy'));
chartOutChar_genzhong_sjjhsy.setOption(genzhong_sjjhsy);
 
 