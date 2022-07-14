$(function(){
    var ltChart;
    function initChart(){
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
               left: '2%',
               right: '3%',
               bottom: '0',
               top: '0',
               height:"80%",
               width:"90%"
               // z: 22
           },
           backgroundColor: 'transparent',
            title: {
                   text: '725',
                    x: '38%',
                   y: 'center',
                   textStyle: {
                       color: "#fff",
                       fontSize: 45
                   },
                   zlevel:24
               },
           radar: [{
                title: {
                       text: 61,
                   },
               indicator: radarData,
               center: ['50%', '50%'],
               shape: 'polygon',
               radius: '60%',
               nameGap: 3,
               name: {
                       formatter:function (value, obj) {
                           var num = obj.num
                           return '{value|' + value + '}'+' {num|'+ num +'}';
               },
               rich:{
                   num: {
                     color: "#ee8c04",
                     fontSize: 19,
                     // padding: [0, 2],
                     align: 'center'
                   },
               value: {
                   color: "#c9caca",
                   fontSize: 19 ,
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

   initChart();
   
    // 
    window.onresize = function () {
        setTimeout(function (){
            initChart();
         
      },200)
         
        }
    // },200)
})