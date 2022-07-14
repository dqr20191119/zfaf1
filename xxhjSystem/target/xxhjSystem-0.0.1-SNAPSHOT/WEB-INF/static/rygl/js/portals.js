function bindEvents() {
  var doc = document,
    target = doc.querySelector(".nav"),
    liEle = target.querySelectorAll(".nav-item"),
    chart = doc.querySelector(".chart"),
    img = doc.querySelector(".active-img"),
    length = liEle.length;
  for(var i=0; i<length; i++) {
    liEle[i].addEventListener("mouseenter", setActive, false);
  }
  setPosion();

  function setPosion() {
    var currentTarget = doc.querySelector(".tabs .active");
    if (!currentTarget) {
      return;
    }
    var left = currentTarget.offsetLeft,
      width = currentTarget.offsetWidth,
      index = -1,
      id = currentTarget.getAttribute("data-id"),
      length = liEle.length;
    for (var i = 0; i < length; i++) {
      if (liEle[i].getAttribute("data-id") == id) {
        index = i;
        break;
      }
    }
    if (index > -1) {
      var totalLeft = left + width / 2;
      chart.style.left = totalLeft + "px";
      img.style.left = width * index + "px";
      marginLeft = -(index * 1920).toString() + "px";
      $(".content-slide").css({
        marginLeft: marginLeft
      })
     // $(".content").eq(index).addClass("active")
      //initTabsContent(index);
    }
  }

  function setActive(e) {
    var target = e.target,
      tagName = target.tagName.toLowerCase();
    while (tagName != "li") {
      if (tagName == "ul") {
        break;
      }
      target = target.parentNode;
      tagName = target.tagName.toLowerCase();
    }
    activeLi(target);
  }

  function activeLi(target) {
    if (target.classList.contains("active")) {
      return;
    }
    var actived = doc.querySelector(".tabs .active");
    if (actived) {
      actived.classList.remove("active");
    }
    target.classList.add("active");
    setPosion();
  }
}
var policeJson = [{
  icon: "icon-mobile",
  text: "移动警务系统"
}, {
  icon: "icon-duty",
  text: "民警值班管理系统"
}, {
  icon: "icon-letter",
  text: "罪犯信件管理系统"
}, {
  icon: "icon-lightning",
  text: "刑罚执行全业务系统"
}, {
  icon: "icon-work",
  text: "民警执勤系统"
}, {
  icon: "icon-punish",
  text: "刑罚执行业务系统"
}, {
  icon: "icon-cure",
  text: "罪犯医疗信息管理系统"
}, {
  icon: "icon-watch",
  text: "监管改造管理系统"
}, {
  icon: "icon-test",
  text: "计分考核系统"
}, {
  icon: "icon-shopping",
  text: "大宗物品采购和帐务系统"
}, {
  icon: "icon-consume",
  text: "狱内消费智能管理系统"
}, {
  icon: "icon-call",
  text: "亲情电话管理系统"
}, {
  icon: "icon-meeting",
  text: "会见管理系统"
}, {
  icon: "icon-data",
  text: "罪犯数据管理系统",
  disabled: true
}, {
  icon: "icon-identity",
  text: "罪犯电子档案管理系统",
  disabled: true
}, {
  icon: "icon-public",
  text: "狱务公开系统",
  disabled: true
}, {
  icon: "icon-labour",
  text: "劳动改造综合管理系统",
  disabled: true
}];
var educationJson = [{
  icon: "icon-sudent",
  text: "新生在线"
}, {
  icon: "icon-psycho",
  text: "犯罪心理测评系统"
}, {
  icon: "icon-remote",
  text: "视频帮教及远程会见系统"
}, {
  icon: "icon-teach",
  text: "教育改造管理系统"
}];
var secureJson = [{
  icon: "icon-maintenance",
  text: "运维管理系统"
}, {
  icon: "icon-certification",
  text: "安全认证支撑系统"
}, {
  icon: "icon-backup",
  text: "容灾备份系统"
}, {
  icon: "icon-resource",
  text: "物力资源智能管控系统"
}, {
  icon: "icon-position",
  text: "押解监控定位系统"
}, {
  icon: "icon-electricity",
  text: "监狱围墙电网管理系统"
}, {
  icon: "icon-alarm",
  text: "周界报警管理系统"
}, {
  icon: "icon-phone",
  text: "手机管控系统"
}, {
  icon: "icon-gis",
  text: "GIS引擎"
}, {
  icon: "icon-access",
  text: "门禁管理系统"
}, {
  icon: "icon-evaluation",
  text: "罪犯危险性评估预警系统"
}, {
  icon: "icon-drone",
  text: "无人机防控系统"
}, {
  icon: "icon-patrol",
  text: "智能巡更系统"
}, {
  icon: "icon-control",
  text: "指挥调度一体化系统"
}, {
  icon: "icon-area",
  text: "区域管控系统"
}, {
  icon: "icon-emergency",
  text: "应急指挥调度系统"
}, {
  icon: "icon-voice",
  text: "智能语音识别系统"
}, {
  icon: "icon-monitor",
  text: "视频监控智能分析系统"
}, {
  icon: "icon-name",
  text: "视频点名系统"
}, {
  icon: "icon-video-watch",
  text: "视频监控系统"
}, {
  icon: "icon-risk",
  text: "监狱安全风险动态评估管控系统"
}]
var teamJson = [{
  icon: "icon-training",
  text: "民警教育培训管理系统"
}, {
  icon: "icon-manage",
  text: "队伍管理信息系统"
}, {
  icon: "icon-exam",
  text: "民警晋职晋衔廉政法规考试系统"
}];
var deskJson = [{
  icon: "icon-certificate",
  text: "数字证书认证系统"
}, {
  icon: "icon-net",
  text: "北京市监狱管理局办公外网"
}, {
  icon: "icon-gps",
  text: "警用车辆定位管理系统"
}, {
  icon: "icon-clothes",
  text: "警服管理系统"
}, {
  icon: "icon-equipment",
  text: "警务装备信息管理系统"
}, {
  icon: "icon-comprehensive",
  text: "北京市监狱管理局综合管控平台"
}, {
  icon: "icon-intranet",
  text: "北京市监狱管理局办公内网"
}, {
  icon: "icon-cadre",
  text: "北京市监狱管理局干职信息采集系统"
}];
var jsonData = {
    0: {
        rendered: false,
        data: policeJson
    },
    1: {
      rendered: false,
      data: educationJson
  },
  2: {
    rendered: false,
    data: secureJson
  },
  3: {
    rendered: false,
    data: teamJson
  },
  4: {
    rendered: false,
    data: deskJson
  }
};
function initTabsContent(index) {
  var rendered = jsonData[index].rendered;
  if(!rendered) {
    render(jsonData[index].data, $(".system").eq(index))
  }
}
function render(data, target) {
  var length = data.length
      html = "";
  for(var i=0;i<length;i++) {
    var disabled = data[i].disabled? ' disabled':'';
    html += '<li class="system-item">'+
              '<div class="system-content '+disabled+'">'+
                '<div class="system-text">'+
                  '<img class="system-img" src="./images/icons/'+data[i].icon +'.png" alt="移动警务系统">'+
                  '<span class="title">'+data[i].text+'</span>'+
                '</div>'+
              '</div>'+
            '</li>';
  }
  $(target).html(html);
}
bindEvents();