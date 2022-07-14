
 //罪犯分布
 var yffx_zffb = {
    title : {
        
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    
    series : [
        {
            name: '情报搜集研判',
            type: 'pie',
            radius : '90%',
            center: ['50%', '50%'],
            label:null,
            data:[
                {value:21, name:'正常'},
                {value:1, name:'搜集不及时/不全面'},
                {value:1, name:'研判不准'},
                {value:2, name:'工作记录不清'} 
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
     '#CDAD00','#4876FF']
};

 var chartOutChar_yffx_zffb = null;
chartOutChar_yffx_zffb = echarts.init(document.getElementById('yffx_zffb'));
chartOutChar_yffx_zffb.setOption(yffx_zffb);
 
 //罪名分布
 var yffx_zmfb = {
    title : {
        
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    
    series : [
        {
            name: '安全排查',
            type: 'pie',
            radius : '90%',
            center: ['50%', '50%'],
            label:null,
            data:[
                {value:2, name:'进出监区搜身'},
                {value:2, name:'出收工搜身'},
                {value:1, name:'进出生活区或重点区域安检'},
				 {value:1, name:'三大现场清监'},
				 {value:1, name:'监舍清监'} ,
				 {value:3, name:'违禁品、违规品、危险品检查'} 

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
     color: ['#ADD8E6','#00BFFF',
     '#CDAD00','#4876FF','#87CEFA','#32CD32']
};

 var chartOutChar_yffx_zmfb= null;
chartOutChar_yffx_zmfb = echarts.init(document.getElementById('yffx_zmfb'));
chartOutChar_yffx_zmfb.setOption(yffx_zmfb);
 
 
 
 
 /*
 //刑期分布
 var yffx_xqfb = {
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
            radius : '90%',
            center: ['50%', '50%'],
            label:null,
            data:[
                {value:5, name:'1年以下'},
                {value:10, name:'1-3年'},
                {value:7, name:'3-5年'},
				{value:1, name:'5-10年'},
				{value:4, name:'10年以上'},
				{value:37, name:'无期'} ,
				{value:4, name:'死刑缓期2年执行'} 

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
     color: ['#ADD8E6','#00BFFF',
     '#CDAD00','#4876FF','#87CEFA','#32CD32','#1E90FF']
};

 var chartOutChar_yffx_xqfb= null;
chartOutChar_yffx_xqfb = echarts.init(document.getElementById('yffx_xqfb'));
chartOutChar_yffx_xqfb.setOption(yffx_xqfb);
 */
 
 
 
 
 //民族分布
 var yffx_mzfb = {
    title : {
        
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    
    series : [
        {
            name: '生产工具',
            type: 'pie',
            radius : '90%',
            center: ['50%', '50%'],
            label:null,
            data:[
                {value:1, name:'一级风险'},
                {value:6, name:'二级风险'},
                {value:7, name:'三级风险'},
				{value:10, name:'四级风险'} 

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
     color: ['#ADD8E6','#00BFFF',
     '#CDAD00','#4876FF']
};

 var chartOutChar_yffx_mzfb= null;
chartOutChar_yffx_mzfb = echarts.init(document.getElementById('yffx_mzfb'));
chartOutChar_yffx_mzfb.setOption(yffx_mzfb);
 
 
 //分级初遇
var yffx_fjcy = {
	    title : {
	        
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    
	    series : [
	        {
	            name: '心理矫治',
	            type: 'pie',
	            radius : '90%',
	            center: ['50%', '50%'],
	            label:null,
	            data:[
	                {value:22, name:'正常'},
	                {value:0, name:'安全防护措施不周全'},
	                {value:1, name:'心理咨询员配备不齐'},
					 {value:27, name:'活动开展次数'},
					 {value:1, name:'材料不全'} ,
					 {value:3, name:'重控罪犯开展咨询与干预'} 

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
	     color: ['#ADD8E6','#00BFFF',
	     '#CDAD00','#4876FF','#87CEFA','#32CD32']
	};

	 var chartOutChar_yffx_fjcy= null;
	chartOutChar_yffx_fjcy = echarts.init(document.getElementById('yffx_fjcy'));
	chartOutChar_yffx_fjcy.setOption(yffx_fjcy);
	 
	  
 
 
 /*
 //去极端化
 var yffx_qjdh = {
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
            radius : '90%',
            center: ['50%', '50%'],
            label:null,
            data:[
                {value:5, name:'已转化'},
                {value:1, name:'未转化'},
                {value:7, name:'基本转化'}

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
     color: ['#ADD8E6','#00BFFF',
     '#CDAD00']
};
*/
/* var chartOutChar_yffx_qjdh= null;
chartOutChar_yffx_qjdh = echarts.init(document.getElementById('yffx_qjdh'));
chartOutChar_yffx_qjdh.setOption(yffx_qjdh);
 */
 /*
 //老病残分布
 var yffx_lbcfb = {
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
            radius : '90%',
            center: ['50%', '50%'],
            label:null,
            data:[
                {value:5, name:'一监'},
                {value:1, name:'二监'},
                {value:7, name:'三监'},
                {value:7, name:'四监'},
                {value:7, name:'五监'},
                {value:7, name:'六监'},
                {value:7, name:'三监'}

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
     color: ['#ADD8E6','#00BFFF',
     '#CDAD00','#4876FF','#87CEFA','#32CD32']
};*/

/* var chartOutChar_yffx_lbcfb= null;
chartOutChar_yffx_lbcfb = echarts.init(document.getElementById('yffx_lbcfb'));
chartOutChar_yffx_lbcfb.setOption(yffx_lbcfb);
 */
 //文化程度 
/*var yffx_whcd = {
     
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
        data: ['已组建','未组建','组建不力','弄虚作假'],
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
            name: '班组建设',
            type: 'bar',
            data: [10, 3, 1, 0,],
            itemStyle:{color:'#1E90FF'}
        } 
    ]
};

  var chartOutChar_yffx_whcd= null;
chartOutChar_yffx_whcd= echarts.init(document.getElementById('yffx_whcd'));
chartOutChar_yffx_whcd.setOption(yffx_whcd);
 */


//分级初遇
var yffx_whcd = {
	    title : {
	        
	        x:'center'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    
	    series : [
	        {
	            name: '班组建设',
	            type: 'pie',
	            radius : '90%',
	            center: ['50%', '50%'],
	            label:null,
	            data:[
	                {value:22, name:'已组建'},
	                {value:0, name:'未组建'},
	                {value:1, name:'组建不力'},
					 {value:1, name:'弄虚作假'} 

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
	     color: ['#ADD8E6','#00BFFF',
	     '#CDAD00','#4876FF']
	};

	 var chartOutChar_yffx_whcd= null;
	chartOutChar_yffx_whcd = echarts.init(document.getElementById('yffx_whcd'));
	chartOutChar_yffx_whcd.setOption(yffx_whcd);
	 
	  