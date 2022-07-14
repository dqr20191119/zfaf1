
//日历
function Rili(dom) {
	$.ajax({
		type : 'post',
		url: jsConst.basePath +"/xxhj/jhrc/searchRcByDay",
		data : {
			"day":formateDateYMD(new Date())
		},
		dataType : 'json',
		success: function(result) {
			$(dom).eCalendar({
				events: result
			});
		}
	});
	
};

function formateDateYMD(date_param){
	if(date_param){
		return date_param.getFullYear() + '-' + (date_param.getMonth() + 1) + '-' + date_param.getDate();
	}
}

function getPath() {
    var lxId = $(this).attr('name');
    console.log(lxId);
    if (lxId != null && '' != lxId){
        $.ajax({
            type: "post",
            url: jsConst.basePath+"/prisonPath/listAllForPrisonPathCamera?pcrPathId=" + lxId,
            dataType: "json",
            success: function (data) {
                info = data.data;
                var deviceIds = [];
                for (var i = 0; i < info.length; i++) {
                    deviceIds.push(info[i].PCR_CAMERA_ID);
                }
                if (deviceIds.length > 0) {
                    videoClient.playVideoHandle({
                        'subType': 2,
                        'layout': deviceIds.length,
                        'data': deviceIds,
                        'callback': function (data) {
                        }
                    });
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                $.messageQueue({message: textStatus, cls: "warning", iframePanel: true, type: "info"});
            }
        });

    }
}

(function($) {

	var eCalendar = function(options, object) {
		// Initializing global variables
		var adDay = new Date().getDate();
		var adMonth = new Date().getMonth();
		var adYear = new Date().getFullYear();
		var dDay = adDay;
		var dMonth = adMonth;
		var dYear = adYear;
		var instance = object;

		var settings = $.extend({}, $.fn.eCalendar.defaults, options);

		function lpad(value, length, pad) {
			if(typeof pad == 'undefined') {
				pad = '0';
			}
			var p;
			for(var i = 0; i < length; i++) {
				p += pad;
			}
			return(p + value).slice(-length);
		}

		var mouseOver = function() {
			$(this).addClass('c-nav-btn-over');
		};
		var mouseLeave = function() {
			$(this).removeClass('c-nav-btn-over');
		};

		var mouseOverEvent = function() {
			$(".c-event-list").scrollTop(0);
			$(this).addClass('c-event-over');
			var d = $(this).attr('data-event-day');
			$('div.c-event-item[data-event-day="' + d + '"]').addClass('c-event-over1').host;
			//$(".c-event-list").scrollTop($('div.c-event-item[data-event-day="' + d + '"]').position().top - $('div.c-event-item[data-event-day="' + d + '"]').height())
		};

		var mouseLeaveEvent = function() {
			$(this).removeClass('c-event-over')
			var d = $(this).attr('data-event-day');
			$('div.c-event-item[data-event-day="' + d + '"]').removeClass('c-event-over1');

		};
		var mouseOverItem = function() {
			$(this).addClass('c-event-over1');
			var d = $(this).attr('data-event-day');
			$('div.c-event[data-event-day="' + d + '"]').addClass('c-event-over');
		};
		var mouseLeaveItem = function() {
			$(this).removeClass('c-event-over1')
			var d = $(this).attr('data-event-day');
			$('div.c-event[data-event-day="' + d + '"]').removeClass('c-event-over');
		};

		var clickitem = function() {
			//清除其它点击样式
			$('.c-grid > div').removeClass('c-event');
			//新增点击样式
			$(this).addClass('c-event');
			
			var d = $(this).attr('data-event-day');
			
			//加载数据
			loadEvents(new Date(dYear, dMonth, d));

			$('div.c-event-item[data-event-day="' + d + '"]').siblings().removeAttr("style")
			$('div.c-event-item[data-event-day="' + d + '"]').css({
				"font-weight": "700",
				"color": "#fff",
				"background": "-webkit-linear-gradient(left, #01c2e6 , #1160ff)",
				"background": " -o-linear-gradient(right, #01c2e6 , #1160ff)",
				"background": "-moz-linear-gradient(right, #01c2e6 , #1160ff)",
				"background": "linear-gradient(to right, #01c2e6 , #1160ff)"
			}).host;
			$('div.c-event-item[data-event-day="' + d + '"]').siblings().children().removeAttr("style")
			$('div.c-event-item[data-event-day="' + d + '"]').children().css("color", "white")
			$('div.c-event[data-event-day="' + d + '"]').siblings().removeAttr("style")
			$('div.c-event[data-event-day="' + d + '"]').css({
				"box-shadow": " 0 0 8px #cccccc",
				"font-weight": "700",
				"color": "#fff",
				"background": "-webkit-linear-gradient(left, #01c2e6 , #1160ff)",
				"background": " -o-linear-gradient(right, #01c2e6 , #1160ff)",
				"background": "-moz-linear-gradient(right, #01c2e6 , #1160ff)",
				"background": "linear-gradient(to right, #01c2e6 , #1160ff)"
			}).host;

		}

		var nextMonth = function() {
			if(dMonth < 11) {
				dMonth++;
			} else {
				dMonth = 0;
				dYear++;
			}
			print();
			if($(".c-day").is(".c-today")) {
				$(".c-month-top").html(dYear + "-" + settings.months[dMonth]);
				$(".c-month-center").html($(".c-today").text());
				//	            $(".c-month-bottom").html("有课");
			} else {
				$(".c-month-top").html(dYear);
				$(".c-month-center").html(settings.months[dMonth]);
				$(".c-month-bottom").html("");
			}

		};
		var previousMonth = function() {
			if(dMonth > 0) {
				dMonth--;
			} else {
				dMonth = 11;
				dYear--;
			}
			print();
			if($(".c-day").is(".c-today")) {
				$(".c-month-top").html(dYear + "-" + settings.months[dMonth]);
				$(".c-month-center").html($(".c-today").text());
				//	            $(".c-month-bottom").html("有课");
			} else {
				$(".c-month-top").html(dYear);
				$(".c-month-center").html(settings.months[dMonth]);
				$(".c-month-bottom").html("");
			}
		};

		function loadEvents(date_param) {
			var cEventsBody = $('.c-event-body');
			cEventsBody.empty();
			
			var eventList = $('<div/>').addClass('c-event-list');
			
			cEventsBody.append(eventList);
			$.ajax({
					type : 'post',
					url: jsConst.basePath +"/xxhj/jhrc/searchRcByDay",
					data : {
						"day":formateDateYMD(date_param)
					},
					dataType : 'json',
					success: function(result) {
						if(result && result.length>0){
							for(i = 0,len=result.length; i < len; i++) {

								var date = result[i].CPS_SCHEDULE_TIME;
								var item = $('<div/>').addClass('c-event-item');
       /*                         if(result[i].LX != null){
                                    item.attr("name",result[i].LX);
                                }*/
								var a = $('<a/>').addClass('c-event-item-a').attr('href', "javascript:void(0);");
								var title = $('<div/>').addClass('title').html(date + '  ' + result[i].CPS_DRPMNT_NAME+ ':');
								var item_content = $('<div/>').addClass('item_content').html(result[i].CPS_PLAN_DETAIL);
								item.attr('data-event-day', date_param.getDate());
								item.on('mouseover', mouseOverItem).on('mouseleave', mouseLeaveItem).on('click', clickitem);
                                // item.on('mouseover', mouseOverItem).on('mouseleave', mouseLeaveItem).on('click', getPath);//.on('click', clickitem);
                                item.append(a);
								a.append(title).append(item_content);
								eventList.append(item);
							}
						}	
					}
				});
		}
		function mGetDate(year, month){
		    var d = new Date(year, month, 0);
		    return d.getDate();
		}

		function print() {
			var dWeekDayOfMonthStart = new Date(dYear, dMonth, 1).getDay();
			var dLastDayOfMonth = new Date(dYear, dMonth + 1, 0).getDate();
			var dLastDayOfPreviousMonth = new Date(dYear, dMonth + 1, 0).getDate() - dWeekDayOfMonthStart + 1;

			var cBody = $('<div/>').addClass('c-grid');
			var cEvents = $('<div/>').addClass('c-event-grid');
			var cEventsBody = $('<div/>').addClass('c-event-body');
			var cEventsTop = $('<div/>').addClass('c-event-top clearfix');
			cEvents.append($('<div/>').addClass('c-event-title c-pad-top').html(settings.eventTitle));
			cEvents.append(cEventsBody);
			var cNext = $('<div/>').addClass('c-next c-grid-title c-pad-top');
			var cMonth = $('<div/>').addClass('c-month c-grid-title c-pad-top');
			var cPrevious = $('<div/>').addClass('c-previous c-grid-title c-pad-top');
			cPrevious.html(settings.textArrows.previous);

			cNext.html(settings.textArrows.next);

			cPrevious.on('mouseover', mouseOver).on('mouseleave', mouseLeave).on('click', previousMonth);
			cNext.on('mouseover', mouseOver).on('mouseleave', mouseLeave).on('click', nextMonth);

			cEventsTop.append(cPrevious);
			cEventsTop.append(cMonth);
			cEventsTop.append(cNext);
			for(var i = 0; i < settings.weekDays.length; i++) {
				var cWeekDay = $('<div/>').addClass('c-week-day c-pad-top');
				cWeekDay.html(settings.weekDays[i]);
				cBody.append(cWeekDay);
			}
			var day = 1;
			var dayOfNextMonth = 1;
			
			for(var i = 0; i < 42; i++) {
				var cDay = $('<div/>');
				if(i < dWeekDayOfMonthStart) {
					cDay.addClass('c-day-previous-month c-pad-top');
					//                  cDay.html(dLastDayOfPreviousMonth++);
				} else if(day <= dLastDayOfMonth) {
					cDay.addClass('c-day c-pad-top');
					if(day == dDay && adMonth == dMonth && adYear == dYear) {
						cDay.addClass('c-today');
					}
					cDay.attr('data-event-day', day);
					cDay.on('mouseover', mouseOverEvent).on('mouseleave', mouseLeaveEvent).on('click', clickitem);
					cDay.html(day++);
				} else {
					cDay.addClass('c-day-next-month c-pad-top');
					//                  cDay.html(dayOfNextMonth++);
				}
				cBody.append(cDay);
			}
			var eventList = $('<div/>').addClass('c-event-list');
			for(var i = 0; i < settings.events.length; i++) {
				var date = settings.events[i].CPS_SCHEDULE_TIME;
                var item = $('<div/>').addClass('c-event-item');
/*                console.log(settings.events[i].LX);
				if(settings.events[i].LX != null){
					item.attr("name",settings.events[i].LX);
				}*/
				var a = $('<a/>').addClass('c-event-item-a').attr('href', "javascript:void(0);");
				var title = $('<div/>').addClass('title').html(date + '  ' + settings.events[i].CPS_DRPMNT_NAME+ ':');
				var item_content = $('<div/>').addClass('item_content').html(settings.events[i].CPS_PLAN_DETAIL);
				item.attr('data-event-day', new Date().getDate());
				item.on('mouseover', mouseOverItem).on('mouseleave', mouseLeaveItem).on('click', clickitem);
                // item.on('mouseover', mouseOverItem).on('mouseleave', mouseLeaveItem).on('click', getPath);

				item.append(a);
				a.append(title).append(item_content);
				eventList.append(item);
			}
			
			$(instance).addClass('calendar');
			cEventsBody.append(eventList);
			$(instance).html(cBody).append(cEvents);
			$(instance).prepend(cEventsTop);
			$(".c-event-item").addClass("clearfix");

			var cMontop = $("<div/>").addClass("c-month-top");
			cMonth.append(cMontop);
			cMontop.html(dYear + "-" + settings.months[dMonth]);
			var cMoncenter = $("<div/>").addClass("c-month-center");
			cMonth.append(cMoncenter);
			cMoncenter.html($(".c-today").text());
			var cMonbottom = $("<div/>").addClass("c-month-bottom");
			cMonth.append(cMonbottom);
			if($(".c-today").is(".c-event")) {
				//cMonbottom.html("有课");
			} else {
				cMonbottom.html(" ");
			}
			
		}
		//默认查询当天的日程
		return print();
	}

	$.fn.eCalendar = function(oInit) {
		return this.each(function() {
			return eCalendar(oInit, $(this));
		});
	};

	// plugin defaults
	/*$.fn.eCalendar.defaults = {
		weekDays: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
		months: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
		textArrows: {
			previous: '',
			next: ''
		},
		eventTitle: '',
		url: '',
		events: [{
				title: 'Brasil x Croácia',
				item_content: 'Abertura da copa do mundo 2014',
				datetime: new Date(2014, 6, 12, 17)
			},
			{
				title: 'Brasil x México',
				item_content: 'Segundo jogo da seleção brasileira',
				datetime: new Date(2014, 6, 17, 16)
			},
			{
				title: 'Brasil x Camarões',
				item_content: 'Terceiro jogo da seleção brasileira',
				datetime: new Date(2014, 6, 23, 16)
			}
		]
	};*/
	
	$.fn.eCalendar.defaults = {
			weekDays: ['星期日','星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
			months: ['01', '02', '03', '04', '05', '06',
				'07', '08', '09', '10', '11', '12'
			],
			textArrows: {
				previous: '',
				next: ''
			},
			eventTitle: '',
			url: '',
			events: [/*{
					title: '活动标题 1',
					item_content: '活动描述 1',
					datetime: new Date(2018, 3, 16, 12),
					href: 'http://www.baidu.com'
				},
				{
					title: '活动标题 2',
					item_content: '活动描述 2',
					datetime: new Date(2018, 3, 17, 12)
				}*/
			]
		};
	
}(jQuery));

