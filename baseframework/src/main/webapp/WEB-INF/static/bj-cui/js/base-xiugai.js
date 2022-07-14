$(function(){
    var blChart,brChart,ltChart,pieChart,barChart, pieChartList = [],barChartList=[];
    function initBottomChart(){
        blChart = echarts.init(document.getElementById('blChart'));
        var data = [{
            "name": "十一监区",
            "value": 1000
        }, {
            "name": "医院",
            "value": 1000
        }, {
            "name": "十监区",
            "value": 1000
        }, {
            "name": "九监区",
            "value": 960
        }, {
            "name": "一监区",
            "value": 920
        }, {
            "name": "二监区",
            "value": 860
        }, {
            "name": "七监区",
            "value": 850
        },{
            "name": "六监区",
            "value": 790
        }, {
            "name": "五监区",
            "value": 730
        },{
            "name": "三监区",
            "value": 725
        },{
            "name": "八监区",
            "value": 700
        }, {
            "name": "四监区",
            "value": 650
        }];
        var xData = [],
            yData = [];
   
        data.map(function(a, b) {
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
                containLabel: true,
          
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
                     interval:0
                }
            }],
            yAxis: [
                {
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
                }
            ],
            series: [{
                    name: '合格率',
                    type: 'bar',
                    barWidth: 35,
                    xAxisIndex: 0,
                    barCategoryGap:3,
                    yAxisIndex: 0,
                    itemStyle: {
                        normal: {
                            color: new echarts.graphic.LinearGradient(
                                0, 0, 0, 1, [{
                                        offset: 0,
                                        color: '#01f5f7'
                                    },
    
                                    {
                                        offset: 1,
                                        color: '#125375'
                                    }
                                ]
                            )
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
        
                }
            ]
        };
        blChart.setOption(option);
    }
    function initBottomChart2(){
        brChart = echarts.init(document.getElementById('brChart'));
        option2 = {
            backgroundColor: "transparent",
            tooltip: {
                },
                grid: {
                    left: '0%',
                    right: '3%',
                    bottom: '10%',
                    top: '15%',
                    height: '82%',
                    containLabel: true,
                },
                xAxis: [{
                    type: 'category',
                    interval:0,
                  
                    boundaryGap: false,
                    axisLine: { //坐标轴轴线相关设置。数学上的x轴
                         show: true,
                         lineStyle: {
                             color: '#233e64'
                         },
                     },
                     axisLabel: { //坐标轴刻度标签的相关设置
                         textStyle: {
                             color: '#a6a6a7',
                             margin:15,
                         },
                     },
                     axisTick: { show: false,},
                     data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月','11月', '12月'],
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
                     axisLine: {show: false,},
                     axisLabel: {
                         margin:20,
                         textStyle: {
                             color: '#a6a6a7',
                             
                         },
                     },
                     axisTick: { show: false,},  
                }],
                series: [{
                    name: '异常流量',
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
                           color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                            { offset: 0,  color: 'rgba(61,234,255, 0.9)'}, 
                            { offset: 0.7,  color: 'rgba(61,234,255, 0.1)'}
                           ], false),
        
                        shadowColor: 'rgba(53,142,215, 0.9)', //阴影颜色
                        shadowBlur: 5//shadowBlur设图形阴影的模糊大小。配合shadowColor,shadowOffsetX/Y, 设置图形的阴影效果。
                     }
                 },
                    data: [40, 75, 20, 125, 110, 52, 78,40, 95, 20, 225, 210, 52, 108]
                }]
        };
        brChart.setOption(option2);
    }
   function initLeftChart(){
         ltChart = echarts.init(document.getElementById('ltChart'));
        var list = [
            [60, 80, 60, 65, 6], 100, [{
                "name": "人",
                "num": '80'
            }, {
                "name": "情",
                "num": '89'
               
            }, {
                "name": "物",
                "num": '66'
            }, {
                 "name": "事",
                "num": '45'
          
            }, {
                "name": "地",
                "num": '6'
            }]
        ];
        var seriesData = list[0];
        var radarData = list[2];
    
        var option3 = {
             grid: {
                left: '-5%',
                right: '-5%',
                bottom: '0',
                top: '1%',
            },
            backgroundColor: 'transparent',
             title: {
                    text: '61',
                     x: '48%',
                    y: 'center',
                    textStyle: {
                        color: "#fff",
                        fontSize: 60
                    },
                    zlevel:24
                },
            radar: [{
                 title: {
                        text: 61,
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
                      fontSize: 24,
                      // padding: [0, 2],
                      align: 'center'
                    },
                value: {
                    color: "#c9caca",
                    fontSize: 25 ,
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
        ltChart.setOption(option3);
    }
    function initPies(id,title){
        pieChart = echarts.init(document.getElementById(id));
        var scale = 1;
        var echartData = [{
            value: 54,
            name: 'test1'
        }, {
            value: 54,
            name: 'test2'
        }, {
            value: 115,
            name: 'test2'
        }, {
            value: 15,
            name: 'test3'
        }, {
            value: 54,
            name: 'test4'
        }]
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
        }
       var option = {
              grid: {
                bottom: '2%',
                top: '2%',

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
                    var total = 1000; //各科正确率总和
                    var rate = 1500;
                    var averagePercent; //综合正确率
                    // echartData.forEach(function(value, index, array) {
                    //     total += value.value;
                    // });
                    return '{total|' + total + '}'+'/{rate|'+ rate +'}';
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
                name: '数量',
                type: 'pie',
                radius: ['86%', '71%'],
                hoverAnimation: false,
                color: ['#457ab5', '#457ab5', '#457ab5', '#457ab5', '#457ab5'],
                label:{
                     normal: {
                            show: false,
                        },
                },
                 itemStyle: {normal: {
                    borderWidth: 1,
                    borderColor: '#000',
                } },
                data: echartData
            }]
        };
  
         pieChart.setOption(option);
         pieChartList.push(pieChart)
       
    }
    function initBar(id){
        barChart = echarts.init(document.getElementById(id));
        var data = [70, 34, 20, 40]
        var titlename = ['一级', '二级', '三级', '四级'];
        var valdata = [80, 50, 30, 10]
        var myColor = ['#144065', '#144065', '#144065', '#144065'];
        option = {
            backgroundColor: 'transparent',
             grid: {
                left: '30%',
                right: '17%',
                bottom: '0',
                top: '3%',

                // z: 22
            },
            xAxis: {
                show: false
            },
            yAxis: [{
                show: true,
                data: titlename,
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
                    },
                },


            }, {
                show: true,
                inverse: true,
                data: valdata,
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
                },

            }],
            series: [{
                name: '条',
                type: 'bar',
                yAxisIndex: 1,
                data: data,
                barWidth: 6,
                barCategoryGap:2,
                itemStyle: {
                    normal: {
                        barBorderRadius: 20,
                        color: function(params) {
                            var num = myColor.length;
                            return myColor[params.dataIndex % num]
                        },
                    }
                },
                label: {
                    normal: {
                        show: false
                    }
                },
            }]
        };
          barChart.setOption(option);
          barChartList.push(barChart)
    }


    initPies("left-pie1","人");
    initPies("left-pie2","地");
    initPies("left-pie3","事");
    initPies("left-pie4","物");
    initPies("left-pie5","情");
    initBar("right-pie1");
    initBar("right-pie2");
    initBar("right-pie3");
    initBar("right-pie4");
    initBar("right-pie5");
    initLeftChart();
    initBottomChart();
    initBottomChart2();
    // 
    window.onresize = function () {
        setTimeout(function (){
          blChart.resize();
          brChart.resize();
          ltChart.resize();
          var length = pieChartList.length;
          for(var j=length;j--;) {
            pieChartList[j].resize();
            barChartList[j].resize();
          }
         
      },200)
         
        }
    // },200)
})