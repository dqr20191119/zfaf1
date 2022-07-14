var http = {};
http.quest = function (option, callback) {
    var url = option.url;
    var method = option.method;
    var data = option.data;
    var timeout = option.timeout || 0;
    var xhr = new XMLHttpRequest();
    (timeout > 0) && (xhr.timeout = timeout);
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status >= 200 && xhr.status < 400) {
                var result = xhr.responseText;
                try { result = JSON.parse(xhr.responseText); } catch (e) { }
                callback && callback(null, result);
            } else {
                callback && callback('status: ' + xhr.status);
            }
        }
    }.bind(this);
    xhr.open(method, url, true);
    if (typeof data === 'object') {
        try {
            data = JSON.stringify(data);
        } catch (e) { }
    }
    xhr.send(data);
    xhr.ontimeout = function () {
        callback && callback('timeout');
        console.log('%c连%c接%c超%c时', 'color:red', 'color:orange', 'color:purple', 'color:green');
    };
};
http.get = function (url, callback) {
    var option = url.url ? url : { url: url };
    option.method = 'get';
    this.quest(option, callback);
};
http.post = function (option, callback) {
    option.method = 'post';
    this.quest(option, callback);
};

// //普通get请求
// http.get('http://www.baidu.com', function (err, result) {
//     // 这里对结果进行处理
// });
//
// //定义超时时间(单位毫秒)
// http.get({ url: 'http://www.baidu.com', timeout: 1000 }, function (err, result) {
//     // 这里对结果进行处理
// });
//
// //post请求
// http.post({ url: 'http://www.baidu.com', data: '123', timeout: 1000 }, function (err, result) {
//     // 这里对结果进行处理
// });




// 生成地图配置文件
function MapEchart() {
    var geoCoordMap = {
        '长沙监狱': [113.45, 28.4],
        '长康监狱': [113.9, 28.19],
        '坪塘监狱': [113.64, 27.99],
        '未管所': [113.1, 28.06],
        '女子监狱': [112.45, 28.19],
        '星城监狱': [112.15, 28.03],
        '邵阳监狱': [110.9, 26.92],
        '娄底监狱': [111.8, 27.63],
        '网岭监狱': [113.4, 27.66],
        '茶陵监狱': [113.63, 26.9],
        '雁北监狱': [112.6, 27.2],
        '湘南监狱': [113.0, 26.9],
        '雁南监狱': [112.3, 26.66],
        '衡州监狱': [112.76, 26.46],
        '岳阳监狱': [113.53, 28.89],
        '德山监狱': [111.69, 29.54],
        '武陵监狱': [111.45, 28.84],
        '津市监狱': [111.95, 28.88],
        '张家界监狱': [110.57, 29.22],
        '赤山监狱': [111.88, 28.37],
        '郴州监狱': [113.03, 25.59],
        '桂阳监狱': [113.53, 25.75],
        '永州监狱': [111.8, 25.73],
        '东安监狱': [111.7, 25.43],
        '怀化监狱': [110.15, 27.5],
        '吉首监狱': [109.73, 28.32]
    };

    var data = []
    for (var i in geoCoordMap) {
        data.push({
            name: i,
            value: parseInt(Math.random() * 3 + 1 )
        })
    }

    var convertData = function(data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordMap[data[i].name];
            if (geoCoord) {
                res.push({
                    name: data[i].name,
                    value: geoCoord.concat(data[i].value)
                });
            }
        }
        return res;
    };

    // mapChart的配置
    var optionMap = {
        geo: {
            map: "湖南",
            zoom: 1.25,
            aspectScale: 0.9, //长宽比
            label: {
                normal: {
                    show: false
                },
                emphasis: {
                    show: false
                }
            },
            roam: false,
            itemStyle: {
                normal: {
                    areaColor: "#031525",
                    borderColor: "#00C7FB",
                    borderWidth: 2
                },
                emphasis: {
                    areaColor: "#2B91B7"
                }
            },
            regions: [
                //对不同的区块进行着色
                {
                    name: "长沙市",
                    itemStyle: {
                        normal: {
                            areaColor: "#1D80E5"
                        }
                    }
                },
                {
                    name: "邵阳市",
                    itemStyle: {
                        normal: {
                            areaColor: "#065FB9"
                        }
                    }
                },
                {
                    name: "娄底市",
                    itemStyle: {
                        normal: {
                            areaColor: "#1D80E5"
                        }
                    }
                },
                {
                    name: "株洲市",
                    itemStyle: {
                        normal: {
                            areaColor: "#065FB9"
                        }
                    }
                },
                {
                    name: "湘潭市",
                    itemStyle: {
                        normal: {
                            areaColor: "#005c9b"
                        }
                    }
                },
                {
                    name: "衡阳市",
                    itemStyle: {
                        normal: {
                            areaColor: "#12B3FE"
                        }
                    }
                },
                {
                    name: "岳阳市",
                    itemStyle: {
                        normal: {
                            areaColor: "#065FB9"
                        }
                    }
                },
                {
                    name: "常德市",
                    itemStyle: {
                        normal: {
                            areaColor: "#12B3FE"
                        }
                    }
                },
                {
                    name: "张家界市",
                    itemStyle: {
                        normal: {
                            areaColor: "#065FB9"
                        }
                    }
                },
                {
                    name: "益阳市",
                    itemStyle: {
                        normal: {
                            areaColor: "#065FB9"
                        }
                    }
                },
                {
                    name: "郴州市",
                    itemStyle: {
                        normal: {
                            areaColor: "#065FB9"
                        }
                    }
                },
                {
                    name: "永州市",
                    itemStyle: {
                        normal: {
                            areaColor: "#1D80E5"
                        }
                    }
                },
                {
                    name: "怀化市",
                    itemStyle: {
                        normal: {
                            areaColor: "#065FB9"
                        }
                    }
                },
                {
                    name: "湘西土家族苗族自治州",
                    itemStyle: {
                        normal: {
                            areaColor: "#1D80E5"
                        }
                    }
                }
            ]
        },
       tooltip:{
    	   show:true,
    	   trigger:'item'
    	  
       },
        series: [
            {
                name: "城市",
                type: "scatter",
                coordinateSystem: "geo",
                data: [
                    { name: "长沙市", value: [113.05, 28.4, 0] },
                    { name: "邵阳市", value: [111.15, 27.3, 0] },
                    { name: "娄底市", value: [111.5, 27.88, 0] },
                    { name: "株洲市", value: [113.39, 27.43, 0] },
                    { name: "湘潭市", value: [112.55, 27.82, 0] },
                    { name: "衡阳市", value: [112.52, 26.95, 0] },
                    { name: "岳阳市", value: [113.23, 29.19, 0] },
                    { name: "常德市", value: [111.69, 29.28, 0] },
                    { name: "张家界市", value: [110.47, 29.52, 0] },
                    { name: "益阳市", value: [112.2, 28.57, 0] },
                    { name: "郴州市", value: [113.03, 25.99, 0] },
                    { name: "永州市", value: [111.6, 26.13, 0] },
                    { name: "怀化市", value: [110.21, 27.78, 0] },
                    { name: "湘西土家族苗族自治州", value: [109.73, 28.72, 0] }
                ],
                symbolSize: 8,
                label: {
                    normal: {
                        formatter: function(v) {
                            if (v.name === "湘西土家族苗族自治州") return "吉首市";
                            else return v.name;
                        },
                        position: "bottom",
                        show: true,
                        textStyle: {
                            fontSize: 18,
                            color: "#ffffff",
                            fontWeight: "bold"
                        }
                    },
                    emphasis: {
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: "rgba(255,255,255,0)"
                    }
                }
            },
            {
                name: "城市",
                type: "scatter",
                coordinateSystem: "geo",
                data: convertData(data),
                symbolSize: 8,
                label: {
                    normal: {
                        formatter: function(v) {
                            if (v.value[2] === 0) return "";
                            else return v.name;
                        },
                        position: "bottom",
                        show: true,
                        textStyle: {
                            fontSize: 12,
                            color: "#ffffff"
                        }
                    },
                    emphasis: {
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        //color: '#ffffff'
                        color: function(params) {
                            //自定义颜色
                            var colorList = ["#FF5F6F", "#FF9B05", "#6B3DCE"];
                            var idx = params.value[2]; //params.dataIndex
                            if (idx === 0) return "rgba(255,255,255,0)";
                            else return colorList[idx - 1];
                        }
                    }
                }
            }
        ]
    };

    return optionMap;
}


// 渲染地图
function getMap(url, id) {
    var map = echarts.init(document.getElementById(id));
    http.get(url, function (err, result) {
        if (result) {
            echarts.registerMap("湖南", result);

            var option = MapEchart();
            map.setOption(option);
        }
    });
  //  console.log(111)
    return map
}



