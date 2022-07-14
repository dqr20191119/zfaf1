function initZfxx() {


  var barChart, ltChart, Chart3, brChart, brChart2, brChart3;

  function initBar(id) {
    barChart = echarts.init(document.getElementById(id));
    var data = ["80", "70", "60"];
    var titlename = ['服刊进度', '教育进度', '评估进度'];
    var valdata = ["20%", "40%", "30%"];
    var myColor = ['#00ffff', '#00ffff', '#00ffff'];
    option = {
      backgroundColor: 'transparent',
      grid: {
        left: '10%',
        right: '10%',
        bottom: '0',
        top: '0',

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
            fontSize: 18,
            color: '#fff',
          },
        },


      }, {
        show: true,
        inverse: true,
        data: valdata,
        axisLabel: {
          textStyle: {
            fontSize: 18,
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
        barWidth: 23,
        barCategoryGap: 2,
        itemStyle: {
          normal: {
            barBorderRadius: 20,
            color: function (params) {
              var num = myColor.length;
              return myColor[params.dataIndex % num]
            },
          }
        },
        label: {
          normal: {
            show: true,
            fontSize: 14,
            color: "#000"
          }
        },
      }]
    };
    barChart.setOption(option);
  }

  function initLeftChart() {
    ltChart = echarts.init(document.getElementById('centerLChart'));

    var option3 = {
      backgroundColor: 'transparent', //背景颜色

      radar: [{ //每个网格的指数名称，类似于X轴或Y轴上的数据的值大小
        indicator: [{
          "name": "逃脱倾向",
          "max": 200,
          "num": 150
        }, {
          "name": "自杀倾向",
          "max": 200,
          "num": 160

        }, {
          "name": "暴力倾向",
          "max": 200,
          "num": 150
        }],
        nameGap: 13,
        center: ['50%', '50%'], //统计图位置，示例是居中
        radius: '70%', //统计图大小
        startAngle: 75, //统计图起始的角度
        splitNumber: 3, //统计图蛛网的网格分段，示例分为三段
        // shape: 'circle',//蛛网是圆角还是尖角
        name: {
          formatter: function (value, obj) {
            var num = obj.num
            return '{num|' + num + '}\n' + '{value|' + value + '}';
          },
          rich: {
            num: {
              color: "#ee8c04",
              fontSize: 18,
              // padding: [0, 2],
              align: 'center'
            },
            value: {
              color: "#c9caca",
              fontSize: 18,
              align: 'center'
            }
          },
          textStyle: {
            color: '#72ACD1'
          }
        },
        axisLine: {
          lineStyle: {
            color: '#cc9b06'
          },
          itemStyle: {
            color: '#cc9b06'
          }
        },
        splitArea: {
          areaStyle: {
            color: 'transparent'
          }
        },
        splitLine: {
          lineStyle: {
            color: '#cc9b06'
          }
        }
      }],
      series: [{
        name: '',
        type: 'radar', //统计图专业名称为雷达图，这里叫做蛛网图
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
          name: '危险评估', //数据名称
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
            show: true
          }

        }]
      }]
    }
    ltChart.setOption(option3);
  }

  function initRightChart() {
    Chart3 = echarts.init(document.getElementById('centerRChart')); //初始化的为dom元素 
    var option3 = {
      grid: {
        top: "10%",
        bottom: "20%"
      },
      tooltip: {
        trigger: 'axis'
      },
      xAxis: [{
        type: 'category',
        interval: 0,

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
            margin: 15,
          },
        },
        axisTick: {
          show: false,
        },
        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
      }],
      yAxis: [{
        type: 'value',

        axisLabel: {
          textStyle: {
            color: '#ffff',

          },
          formatter: function (value) {
            var texts = [];
            if (value == 100) {
              texts.push('宽管');
            } else if (value == 200) {
              texts.push('严管');
            } else if (value == 300) {
              texts.push('普管');
            }
            return '{value|' + texts + '}'

          },
          rich: {

            value: {
              color: "#a6a6a7",
              fontSize: 14,
              align: 'center'
            }
          },
        },
        splitLine: {
          lineStyle: {
            show: false
          }
        },
        splitLine: {
          lineStyle: {
            show: false,
            color: "#1d3052"
          }
        },
        axisLine: {
          lineStyle: {
            color: "#233e64"
          }
        },
      }],
      series: [

        {
          name: '一级',
          showSymbol: false,
          itemStyle: {
            normal: {
              label: {
                show: false,

              }
            }
          },
          lineStyle: {
            color: "#00ffff"
          },
          type: 'line',
          data: [40, 75, 20, 125, 110, 352, 78, 40, 95, 20, 225, 210, 52, 108]
        },
        {
          name: '二级',
          showSymbol: false,
          itemStyle: {
            normal: {
              label: {
                show: false,

              }
            }
          },
          lineStyle: {
            color: "#b68c07"
          },
          type: 'line',
          data: [60, 25, 80, 225, 130, 152, 48, 140, 105, 120, 325, 110, 82, 128]
        },
        {
          name: '三级',
          showSymbol: false,
          itemStyle: {
            normal: {
              label: {
                show: false,

              }
            }
          },
          lineStyle: {
            color: "#ee1313"
          },
          type: 'line',
          data: [10, 125, 30, 215, 50, 92, 148, 40, 75, 20, 125, 90, 182, 158]
        }
      ]
    };

    // 使用刚指定的配置项和数据显示图表。
    Chart3.setOption(option3);
  }

  function initBottomChart2() {
    brChart = echarts.init(document.getElementById('bottomCChart'));
    option2 = {
      backgroundColor: "transparent",
      tooltip: {
        trigger: 'axis'
      },
      grid: {
        left: '0%',
        right: '3%',
        bottom: '5%',
        top: '10%',
        height: '78%',
        containLabel: true,
      },
      xAxis: [{
        type: 'category',
        interval: 0,

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
            margin: 15,
          },
        },
        axisTick: {
          show: false,
        },
        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
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
          show: true,
          lineStyle: {
            color: '#233e64'
          },
        },
        axisLabel: {
          margin: 20,
          textStyle: {
            color: '#a6a6a7',

          },
        },
        axisTick: {
          show: false,
        },
      }],
      series: [{
        name: '异常流量',
        type: 'line',
        smooth: false, //是否平滑曲线显示
        symbol: 'circle', // 默认是空心圆（中间是白色的），改成实心圆

        label: {
          normal: {
            show: true,
            position: 'top',
            distance: 10,
            color: "#3deaff",
            fontSize: 12
          }
        },
        lineStyle: {
          normal: {
            //  color: "#3deaff"   // 线条颜色
          }
        },
        itemStyle: {
          normal: {
            color: "#3deaff", // 会设置点和线的颜色，所以需要下面定制 line

          }
        },
        areaStyle: { //区域填充样式
          normal: {
            //线性渐变，前4个参数分别是x0,y0,x2,y2(范围0~1);相当于图形包围盒中的百分比。如果最后一个参数是‘true’，则该四个值是绝对像素位置。
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(61,234,255, 0.9)'
              },
              {
                offset: 0.7,
                color: 'rgba(61,234,255, 0.1)'
              }
            ], false),

            shadowColor: 'rgba(53,142,215, 0.9)', //阴影颜色
            shadowBlur: 5 //shadowBlur设图形阴影的模糊大小。配合shadowColor,shadowOffsetX/Y, 设置图形的阴影效果。
          }
        },
        data: [40, 75, 20, 125, 110, 52, 78]
      }]
    };
    brChart.setOption(option2);
  }

  function initBottomChart3() {
    brChart2 = echarts.init(document.getElementById('bottomRChart'));
    var colorList = {
      type: 'linear',
      x: 0,
      y: 0,
      x2: 0,
      y2: 1,
      colorStops: [{
        offset: 0,
        color: '#01f5f7'
      }, {
        offset: 1,
        color: '#125375' // 100% 处的颜色
      }],
      globalCoord: false // 缺省为 false
    };
    var colorList2 = {
      type: 'linear',
      x: 0,
      y: 0,
      x2: 0,
      y2: 1,
      colorStops: [{
        offset: 0,
        color: '#a57f09' // 0% 处的颜色
      }, {
        offset: 1,
        color: '#24200d' // 100% 处的颜色
      }],
      globalCoord: false // 缺省为 false
    }
    var option = {
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'cross',
          crossStyle: {
            color: '#999'
          }
        },
        formatter: '{b} 比例 : {c2}',
        extraCssText: 'width:auto;height:auto;'
      },
      grid: {
        left: '15%',
        right: '0',
        bottom: '10%',
        top: '10%',
        height: '80%',
        containLabel: true,
      },
      xAxis: [{
        type: 'category',
        data: ['1月', '2月', '3月'],
        axisPointer: {
          type: 'shadow'
        },

        splitLine: {
          show: false
        },
        axisTick: {
          show: false
        },
        axisLine: { //坐标轴轴线相关设置。数学上的x轴
          show: true,
          lineStyle: {
            color: '#233e64'
          },
        },
        axisLabel: { //坐标轴刻度标签的相关设置
          textStyle: {
            color: '#a6a6a7',
            margin: 15,
          },
        },
      }],
      yAxis: [{
          type: 'value',
          min: 0,
          max: 7,
          interval: 1,
          splitLine: {
            show: true,
            lineStyle: {
              color: '#233e64'
            }
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: '#233e64'
            },
          },
          axisLabel: {
            margin: 20,
            textStyle: {
              color: '#a6a6a7',

            },
          },
        },
        {
          type: 'value',
          min: 0,
          max: 7,
          interval: 1,
          splitLine: {
            show: false,
            lineStyle: {
              color: '#233e64'
            }
          },
          axisLine: {
            show: true,
            lineStyle: {
              color: '#233e64'
            },
          },
          axisLabel: {
            margin: 20,
            textStyle: {
              color: '#a6a6a7',

            },
          },


        }
      ],
      series: [{
          name: '男生',
          type: 'bar',
          data: [3, 4, 1],
          itemStyle: {
            color: function (params) {
              return colorList;
            },
          },
        },
        {
          name: '女生',
          type: 'bar',
          data: [2, 5, 3],
          itemStyle: {
            color: function (params) {
              return colorList2;
            },
          },
        },

      ]
    };
    brChart2.setOption(option);

  }

  function initBottomChart4() {
    brChart3 = echarts.init(document.getElementById('bottomLChart')); //初始化的为dom元素 
    var option3 = {
      grid: {
        top: "10%",
        bottom: "20%"
      },
      tooltip: {
        trigger: 'axis'
      },
      xAxis: [{
        type: 'category',
        interval: 0,

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
            margin: 15,
          },
        },
        axisTick: {
          show: false,
        },
        data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
      }],
      yAxis: [{
        type: 'value',
        splitLine: {
          lineStyle: {
            show: false
          }
        },
        splitLine: {
          lineStyle: {
            show: false,
            color: "#1d3052"
          }
        },
        axisLine: {
          lineStyle: {
            color: "#233e64"
          }
        },
        axisLabel: { //坐标轴刻度标签的相关设置
          textStyle: {
            color: '#a6a6a7',
            margin: 15,
          },
        },
      }],
      series: [

        {
          name: '一级',
          showSymbol: false,
          itemStyle: {
            normal: {
              label: {
                show: false,

              }
            }
          },
          lineStyle: {
            color: "#00ffff"
          },
          type: 'line',
          data: [40, 75, 20, 125, 110, 52, 78, 40, 95, 20, 225, 10, 52, 78]
        }
      ]
    };

    // 使用刚指定的配置项和数据显示图表。
    brChart3.setOption(option3);
  }
  initBar("topChart");
  initLeftChart();
  initRightChart();
  initBottomChart2();
  initBottomChart3();
  initBottomChart4();
  $("#zfxxScrollR").mCustomScrollbar({
    axis: "y",
    theme: "minimal-dark",
    scrollbarPosition: "outside",
  });
  $("#dialog_mjxx").mCustomScrollbar({
    axis: "y",
    theme: "minimal-dark",
    scrollbarPosition: "outside",
  });
}