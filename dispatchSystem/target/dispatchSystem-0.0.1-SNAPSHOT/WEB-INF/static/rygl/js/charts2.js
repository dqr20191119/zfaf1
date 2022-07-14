$(function(){
    var ltChart;

   function initLeftChart(){
         ltChart = echarts.init(document.getElementById('ltChart'));

        var option3 = {
            backgroundColor: 'transparent',//背景颜色
        
            radar: [{//每个网格的指数名称，类似于X轴或Y轴上的数据的值大小
                indicator: [{
                    "name": "逃脱倾向",
                    "max":200,
                    "num": 150
                }, {
                    "name": "自杀倾向",
                    "max":200,
                    "num": 160
                   
                }, {
                    "name": "暴力倾向",
                    "max":200,
                    "num": 150
                }
                ],
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
            rich:{
                num: {
                  color: "#ee8c04",
                  fontSize: 18,
                  // padding: [0, 2],
                  align: 'center'
                },
            value: {
                color: "#c9caca",
                fontSize: 18 ,
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
                name: '',
                type: 'radar',//统计图专业名称为雷达图，这里叫做蛛网图
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
                data: [{
                    name: '危险评估',//数据名称
                    value: [105, 85, 115],
                 
                    lineStyle: {
                        normal: {
                            color: '#cc9b06',
                            type: 'solid',
                            width: 1
                        }
                    },
                    symbolSize: 0, // 单个数据标记的大小，可以设置成诸如 10 这样单一的数字，也可以用数组分开表示宽和高，例如 [20, 10] 表示标记宽为20，高为10。
                    label: { // 单个拐点文本的样式设置                            
                       show:true
                    }
                   
                }]
            }]
        }
        ltChart.setOption(option3);
    }

    initLeftChart();

    window.onresize = function () {
        setTimeout(function (){
   
          ltChart.resize();
         
         
         
      },200)
         
        }
    // },200)
})