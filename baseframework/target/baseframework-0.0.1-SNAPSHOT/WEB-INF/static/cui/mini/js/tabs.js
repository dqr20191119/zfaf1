/*! cui 2017-11-03 */
$.component("coral.tabs",{version:"4.0.1",delay:300,options:{active:0,collapsible:!1,event:"click",loadonce:!0,renderPanelOnActivate:!1,destroyAllonActive:!1,heightStyle:"content",hide:null,show:null,loadtext:"加载中，请耐心等候...",overflowContent:!1,method:"GET",newbtn:!1,name:null,onTabNew:null,onActivate:null,onLoad:null,beforeActivate:null,beforeLoad:null,beforeTabClose:null,onTabClose:null},_create:function(){var a=this,b=this.options;null!=b.method&&""!=b.method?b.method=(b.method+"").toUpperCase():b.method="GET",this.running=!1,this.element.addClass("coral-tabs ctrl-init ctrl-init-tabs coral-component coral-component-content coral-corner-all").toggleClass("coral-tabs-collapsible",b.collapsible),this._processTabs(),"boolean"==typeof b.newbtn&&b.newbtn&&this.addnewbtn(),b.active=this._initialActive(),$.isArray(b.disabled)&&(b.disabled=$.unique(b.disabled.concat($.map(this.tabs.filter(".coral-state-disabled"),function(b){return a.tabs.index(b)}))).sort()),this.options.active!==!1&&this.anchors.length?this.active=this._findActive(b.active):this.active=$(),this._refresh(),this.active.length&&this.load(b.active),this.element.hasClass("coral-tabs-bottom")?$(".coral-tabs-bottom .coral-tabs-nav, .coral-tabs-bottom .coral-tabs-nav > *").removeClass("coral-corner-all coral-corner-top").addClass("coral-corner-bottom"):this.element.hasClass("coral-tabs-right")?this.element.addClass("coral-tabs-right coral-helper-clearfix").removeClass("coral-corner-top").addClass("coral-corner-left"):this.element.hasClass("coral-tabs-left")&&this.element.removeClass("coral-corner-top").addClass("coral-tabs-left coral-helper-clearfix coral-corner-left")},_isLocal:function(){return function(a){return a=a.cloneNode(!1),a.hash.length>1&&a.href.indexOf("#")!=-1}}(),_initialActive:function(){var a=this.options.active,b=this.options.collapsible,c=location.hash.substring(1);return null===a&&(c&&this.tabs.each(function(b,d){if($(d).attr("aria-controls")===c)return a=b,!1}),null===a&&(a=this.tabs.index(this.tabs.filter(".coral-state-active"))),null!==a&&a!==-1||(a=!!this.tabs.length&&0)),a!==!1&&(a=this.tabs.index(this.tabs.eq(a)),a===-1&&(a=!b&&0)),!b&&a===!1&&this.anchors.length&&(a=0),a},_getCreateEventData:function(){return{tab:this.active,panel:this.active.length?this._getPanelForTab(this.active):$()}},_tabKeydown:function(a){var b=$(this.document[0].activeElement).closest("li"),c=this.tabs.index(b),d=!0;if(!this._handlePageNav(a)){switch(a.keyCode){case $.coral.keyCode.RIGHT:case $.coral.keyCode.DOWN:c++;break;case $.coral.keyCode.UP:case $.coral.keyCode.LEFT:d=!1,c--;break;case $.coral.keyCode.END:c=this.anchors.length-1;break;case $.coral.keyCode.HOME:c=0;break;case $.coral.keyCode.SPACE:return a.preventDefault(),clearTimeout(this.activating),void this._activate(c);case $.coral.keyCode.ENTER:return a.preventDefault(),clearTimeout(this.activating),void this._activate(c!==this.options.active&&c);default:return}a.preventDefault(),clearTimeout(this.activating),c=this._focusNextTab(c,d),a.ctrlKey||(b.attr("aria-selected","false"),this.tabs.eq(c).attr("aria-selected","true"),this.activating=this._delay(function(){this.option("active",c)},this.delay))}},_panelKeydown:function(a){this._handlePageNav(a)||a.ctrlKey&&a.keyCode===$.coral.keyCode.UP&&(a.preventDefault(),this.active.focus())},_handlePageNav:function(a){return a.altKey&&a.keyCode===$.coral.keyCode.PAGE_UP?(this._activate(this._focusNextTab(this.options.active-1,!1)),!0):a.altKey&&a.keyCode===$.coral.keyCode.PAGE_DOWN?(this._activate(this._focusNextTab(this.options.active+1,!0)),!0):void 0},_findNextTab:function(a,b){function c(){return a>d&&(a=0),a<0&&(a=d),a}for(var d=this.tabs.length-1;$.inArray(c(),this.options.disabled)!==-1;)a=b?a+1:a-1;return a},_focusNextTab:function(a,b){return a=this._findNextTab(a,b),this.tabs.eq(a).focus(),a},_setOption:function(a,b){return"active"===a?void("string"==typeof b?this._activate(this.getIndexById(b)):this._activate(b)):"disabled"===a?void this._setupDisabled(b):(this._super(a,b),"collapsible"===a&&(this.element.toggleClass("coral-tabs-collapsible",b),b||this.options.active!==!1||this._activate(0)),"event"===a&&this._setupEvents(b),void("heightStyle"===a&&this._setupHeightStyle(b)))},_tabId:function(a){return a.attr("aria-controls")||"coral-tabs-"+getNextTabId()},_sanitizeSelector:function(a){return a?a.replace(/[!"$%&'()*+,.\/:;<=>?@\[\]\^`{|}~]/g,"\\$&"):""},refresh:function(){var a=this.options,b=this.tablist.children(":has(a[href])");a.disabled=$.map(b.filter(".coral-state-disabled"),function(a){return b.index(a)}),this._processTabs(),a.active!==!1&&this.anchors.length?this.active.length&&!$.contains(this.tablist[0],this.active[0])?this.tabs.length===a.disabled.length?(a.active=!1,this.active=$()):this._activate(this._findNextTab(Math.max(0,a.active-1),!1)):a.active=this.tabs.index(this.active):(a.active=!1,this.active=$()),this._refresh()},_refresh:function(){this._setupDisabled(this.options.disabled),this._setupEvents(this.options.event),this.tabs.not(this.active).attr({"aria-selected":"false","aria-expanded":"false",tabIndex:-1}),this.panels.not(this._getPanelForTab(this.active)).hide().attr({"aria-hidden":"true"}),this.active.length?(this.active.addClass("coral-tabs-active coral-state-active").attr({"aria-selected":"true","aria-expanded":"true",tabIndex:0}),this._getPanelForTab(this.active).show().attr({"aria-hidden":"false"}).attr({"data-render":""})):this.tabs.eq(0).attr("tabIndex",0),this._setupHeightStyle(this.options.heightStyle),this.options.overflowContent&&this._showhideArrow()},add:function(a){a=a||{};var b=this.options.disabled,c=null,d=a.tabId,e=null,f=a.label,g=a.ariaControls,h=!1,i=a.href,j=a.hide,k=void 0!=a.closeable&&a.closeable,l=k===!0?"<span class='coral-closable cui-icon-cross2'></span>":"",m=a.content;if(b!==!0){"boolean"==typeof this.options.newbtn&&this.options.newbtn&&this.tablist.find("> li:last").remove(),g?c=g:"undefined"==typeof i?c=$({}).uniqueId()[0].id:(c=i,0==c.indexOf("#")?c=c.substr(1):h=!0),d=d?"id="+d:"";var n="<li "+d+"><a href='#{href}'>"+f+"</a>{closespan}</li>";n=n.replace(/\{closespan\}/g,l),h?(e=$(n.replace(/#\{href\}/g,c)),this.options.overflowContent?this.component().children(".coral-tabs-content").children(".coral-tabs-nav").append(e):this.component().children(".coral-tabs-nav").append(e)):(e=$(n.replace(/#\{href\}/g,"#"+c)),g&&e.attr("aria-controls",g),this.options.overflowContent?this.component().children(".coral-tabs-content").children(".coral-tabs-nav").append(e):this.component().children(".coral-tabs-nav").append(e),this.element.hasClass("coral-tabs-bottom")?this.options.overflowContent?this.component().children(".coral-tabs-content").before("<div id='"+c+"'>"+m+"</div>"):this.component().children(".coral-tabs-nav").before("<div id='"+c+"'>"+m+"</div>"):this.component().append("<div id='"+c+"'>"+m+"</div>")),1==j&&e.hide(),"boolean"==typeof this.options.newbtn&&this.options.newbtn&&this.addnewbtn(),this.refresh()}},remove:function(a){if(!this.options.disabled){if(a instanceof Array)for(var b in a)this._remove(a[b]);else this._remove(a);this.refresh()}},_remove:function(a){var b;if(b="undefined"==typeof a?this.options.active:"string"==typeof a?this.getIndexById(a):a,b!=-1&&b!==!1){var c=this._getList().find("> li:has(a[href])").eq(b).attr("aria-controls");this.component().find("li[aria-controls='"+c+"']").remove(),this.component().find("div#"+c).remove()}},showTabs:function(a,b){if(a instanceof Array)for(var c in a)this._showTabs(a[c],b);else this._showTabs(a,b)},_showTabs:function(a,b){var c;if(c="string"==typeof a?this.getIndexById(a):a,c!=-1){var d=this._getList().find("> li:has(a[href])").eq(c).attr("aria-controls");0==b?(this.component().find("li[aria-controls='"+d+"']").hide(),this.component().find("div#"+d).hide()):this.component().find("li[aria-controls='"+d+"']").show()}},addnewbtn:function(){var a=this,b=$("<li class='coral-tabs-newbtn coral-corner-top'><a class='coral-tabs-anchor'><span class='cui-icon-plus-circle2'></span></a></li>");b.unbind("click").bind("click",function(b){!0!==a.options.disabled&&a._trigger("onTabNew",null,[])}),this.tablist.append(b),a._hoverable(b)},_processTabs:function(){var a=this;if(this.tablist=this._getList().addClass("coral-tabs-nav coral-helper-reset coral-helper-clearfix coral-component-header coral-corner-all").attr("role","tablist").delegate("> li","mousedown"+this.eventNamespace,function(a){$(this).is(".coral-state-disabled")&&a.preventDefault()}).delegate(".coral-tabs-anchor","focus"+this.eventNamespace,function(){$(this).closest("li").is(".coral-state-disabled")&&this.blur()}),this.options.overflowContent&&!this.tablist.parent().hasClass("coral-tabs-content")){this.tablist.wrap("<div class='coral-tabs-content coral-tabs'></div>"),this.element.prepend("<div class='icon-arrow-left3 coral-scroller-left coral-tabs-arrow'></div><div class='icon-arrow-right3 coral-scroller-right coral-tabs-arrow'></div>");var b=this._getClass(this.options.cls);"coral-tabs-right"!=b&&"coral-tabs-left"!=b||(this.element.children(".coral-scroller-left").removeClass("icon-arrow-left3").addClass("icon-arrow-up3"),this.element.children(".coral-scroller-right").removeClass("icon-arrow-right3").addClass("icon-arrow-down3")),this._bindArrowEvent()}this.tabs=this.tablist.find("> li:has(a[href])").addClass("coral-state-default coral-corner-top").attr({role:"tab",tabIndex:-1}),this.tabs=this.tabs.filter(function(){if(0==$(this).data("authority")){$(this).remove();var a=$(this).find(">a").attr("href");return"#"==a.slice(0,1)&&$("body").find(a).remove(),!1}return!0}),this.anchors=this.tabs.map(function(){return $("a",this)[0]}).addClass("coral-tabs-anchor").attr({role:"presentation",tabIndex:-1}),this.panels=$(),this.anchors.each(function(b,c){var d,e,f,g=$(c).uniqueId().attr("id"),h=$(c).closest("li"),i=h.attr("aria-controls");if(h.find(".coral-closable").addClass("cui-icon-cross2"),a._isLocal(c))d=c.hash,f=d.substring(1),e=a.element.find(a._sanitizeSelector(d)),a.options.renderPanelOnActivate&&e.attr("data-render","false");else{if(f=h.attr("aria-controls")||$({}).uniqueId()[0].id,d="#"+f,e=a.element.find(d),!e.length){e=a._createPanel(f);var j=a._getClass(a.options.cls),k=a.tablist.parent(".coral-tabs-content").length?a.tablist.parent(".coral-tabs-content"):void 0;"coral-tabs-bottom"==j?e.insertBefore(a.panels[b-1]||k||a.tablist):e.insertAfter(a.panels[b-1]||k||a.tablist)}e.attr("aria-live","polite")}e.length&&(a.panels=a.panels.add(e)),i&&h.data("coral-tabs-aria-controls",i),h.attr({"aria-controls":f,"aria-labelledby":g}),e.attr("aria-labelledby",g)}),this.panels.addClass("coral-tabs-panel coral-component-content coral-corner-bottom").attr("role","tabpanel")},_getList:function(){return this.tablist||this.element.find(">ol,>ul").eq(0)},_createPanel:function(a){return $("<div>").attr("id",a).addClass("coral-tabs-panel coral-component-content coral-corner-bottom").data("coral-tabs-destroy",!0)},_setupDisabled:function(a){$.isArray(a)&&(a.length?a.length===this.anchors.length&&(a=!0):a=!1);for(var b,c=0;b=this.tabs[c];c++)a===!0||$.inArray(c,a)!==-1?$(b).addClass("coral-state-disabled").attr("aria-disabled","true"):$(b).removeClass("coral-state-disabled").removeAttr("aria-disabled");this.options.disabled=a,this.options.overflowContent&&(a===!0?this.element.children(".coral-tabs-arrow").addClass("coral-state-disabled"):this.element.children(".coral-tabs-arrow").removeClass("coral-state-disabled"))},_setupEvents:function(a){var b={};a&&$.each(a.split(" "),function(a,c){b[c]="_eventHandler"}),this._off(this.anchors.add(this.tabs).add(this.panels).add(this.tabs.children("span.cui-icon-cross2"))),this._on(!0,this.anchors,{click:function(a){a.preventDefault()}}),this._on(this.anchors,b),this._on(this.tabs.children("span.coral-closable"),{click:"_tabClose"}),this._on(this.panels,{keydown:"_panelKeydown"}),this._on(this.tabs,{keydown:"_tabKeydown"}),this._focusable(this.tabs),this._hoverable(this.tabs)},_tabClose:function(a){var b=this;if(b.options.disabled!==!0){var c=$(a.target),d=c.closest("li"),e=d.attr("aria-controls"),f=b._getPanelForTab(d),g={currentTab:d,currentPanel:f,panelId:e};b._trigger("beforeTabClose",a,g)!==!1&&(d.remove(),b.element.find("#"+e).remove(),b._trigger("onTabClose",a,g),b.refresh())}},_setupHeightStyle:function(a){var b,c,d=!1,e=this.element.parent(),f=this.options.overflowContent;if("fill"===a){$.coral.fitParent(this.component(),!0),b=e.height(),c=e.width(),e.addClass("coral-noscroll"),b-=this.element.outerHeight()-this.element.height(),this.element.siblings(":visible").not("script").each(function(){var a=$(this),c=a.css("position");"absolute"!==c&&"fixed"!==c&&(b-=a.outerHeight())}),this.element.children().not(this.panels).each(function(){if($(this).parent().hasClass("coral-tabs-right")||$(this).parent().hasClass("coral-tabs-left")){var a=$(this).css("position");if(d=!0,"absolute"===a||"fixed"===a)return;return void(c-=$(this).outerWidth(!0))}(!f||f&&!$(this).hasClass("coral-tabs-arrow"))&&(b-=$(this).outerHeight(!0))});var g,h=this.panels.filter(":visible");if(g=h.outerHeight()-h.innerHeight(),this.panels.each(function(){$(this).height(Math.max(0,b-g-$(this).innerHeight()+$(this).height()))}),d){var i=this.element.children("ul");i.height(Math.max(0,b-i.innerHeight()+i.height()))}}else"auto"===a&&(b=0,this.panels.each(function(){b=Math.max(b,$(this).height("").height()),$(this).width("")}).height(b));this.panels.each(function(){$(this).is(":visible")&&$.coral.refreshAllComponent($(this))})},_eventHandler:function(a){var b=this.options,c=this.active,d=$(a.currentTarget),e=d.closest("li"),f=e[0]===c[0],g=f&&b.collapsible,h=g?$():this._getPanelForTab(e),i=c.length?this._getPanelForTab(c):$(),j={oldTab:c,oldPanel:i,newTab:g?$():e,newPanel:h};a.preventDefault(),e.hasClass("coral-state-disabled")||e.hasClass("coral-tabs-loading")||this.running||f&&!b.collapsible||this._trigger("beforeActivate",a,j)===!1||(b.active=!g&&this.tabs.index(e),this.active=f?$():e,this.xhr&&this.xhr.abort(),i.length||h.length||$.error("jQuery UI Tabs: Mismatching fragment identifier."),h.length&&this.load(this.tabs.index(e),a),this._toggle(a,j))},_toggle:function(a,b){function c(){e.running=!1,e._trigger("onActivate",a,b),"false"===f.attr("data-render")?coral.render(f):$.coral.refreshAllComponent(f)}function d(){b.newTab.closest("li").addClass("coral-tabs-active coral-state-active"),f.length&&e.options.show?e._show(f,e.options.show,c):(f.show(),c())}var e=this,f=b.newPanel,g=b.oldPanel;this.running=!0,g.length&&this.options.hide?this._hide(g,this.options.hide,function(){b.oldTab.closest("li").removeClass("coral-tabs-active coral-state-active"),d()}):(b.oldTab.closest("li").removeClass("coral-tabs-active coral-state-active"),g.hide(),d()),g.attr("aria-hidden","true"),b.oldTab.attr({"aria-selected":"false","aria-expanded":"false"}),f.length&&g.length?b.oldTab.attr("tabIndex",-1):f.length&&this.tabs.filter(function(){return 0===$(this).attr("tabIndex")}).attr("tabIndex",-1),f.attr("aria-hidden","false"),b.newTab.attr({"aria-selected":"true","aria-expanded":"true",tabIndex:0})},_activate:function(a){if(a!=-1){var b,c=this._findActive(a);c[0]!==this.active[0]&&(c.length||(c=this.active),b=c.find(".coral-tabs-anchor")[0],this._eventHandler({target:b,currentTarget:b,preventDefault:$.noop}),this.options.overflowContent&&this._showhideArrow())}},_findActive:function(a){return a===!1?$():this.tabs.eq(a)},_getIndex:function(a){return"string"==typeof a&&(a=this.anchors.index(this.anchors.filter("[href$='"+a+"']"))),a},getIdByIndex:function(a){var b=this.tablist.children("li").filter(function(){return $(this).hasClass("coral-state-default")});return b.eq(a).attr("aria-controls")},getIndexById:function(a){0==a.indexOf("#")&&(a=a.substring(1));var b=this.tablist.children("li").filter(function(){return $(this).hasClass("coral-state-default")}),c=b.index(b.filter("[aria-controls$='"+a+"']"));return c==-1&&(c=b.index(b.filter("[id$='"+a+"']"))),c},getIndexByTabId:function(a){var b=this.tablist.children("li").filter(function(){return $(this).hasClass("coral-state-default")});return b.index(b.filter("#"+a))},getAllTabId:function(){return this.getPanelIds()},getPanelIds:function(){var a=[];return this.tabs.filter("[aria-controls]").each(function(){var b=$(this).attr("aria-controls");""!=b&&a.push(b)}),a},getTabIds:function(){var a=[];this.tablist.children("li").each(function(){var b=$(this).attr("id");""!=b&&a.push(b)});return a},getTabEl:function(){var a=this.tablist.children("li.coral-state-default");return a},getPanelEl:function(){return{}},getLength:function(){return this.tabs.length},_destroy:function(){this.xhr&&this.xhr.abort(),this.element.removeClass("coral-tabs coral-component coral-component-content coral-corner-all coral-tabs-collapsible"),this.tablist.removeClass("coral-tabs-nav coral-helper-reset coral-helper-clearfix coral-component-header coral-corner-all").removeAttr("role"),this.anchors.removeClass("coral-tabs-anchor").removeAttr("role").removeAttr("tabIndex").removeUniqueId(),this.tablist.unbind(this.eventNamespace),this.tabs.add(this.panels).each(function(){$.data(this,"coral-tabs-destroy")?$(this).remove():$(this).removeClass("coral-state-default coral-state-active coral-state-disabled coral-corner-top coral-corner-bottom coral-component-content coral-state-active coral-tabs-panel").removeAttr("tabIndex").removeAttr("aria-live").removeAttr("aria-busy").removeAttr("aria-selected").removeAttr("aria-labelledby").removeAttr("aria-hidden").removeAttr("aria-expanded").removeAttr("role")}),this.tabs.each(function(){var a=$(this),b=a.data("coral-tabs-aria-controls");b?a.attr("aria-controls",b).removeData("coral-tabs-aria-controls"):a.removeAttr("aria-controls")}),this.panels.show(),"content"!==this.options.heightStyle&&this.panels.css("height","")},enable:function(a){var b=this.options.disabled;b!==!1&&(void 0===a?b=!1:(a=this._getIndex(a),b=$.isArray(b)?$.map(b,function(b){return b!==a?b:null}):$.map(this.tabs,function(b,c){return c!==a?c:null})),this._setupDisabled(b))},disable:function(a){var b=this.options.disabled;if(b!==!0){if(void 0===a)b=!0;else{if(a=this._getIndex(a),$.inArray(a,b)!==-1)return;b=$.isArray(b)?$.merge([a],b).sort():[a]}this._setupDisabled(b)}},_destroyOtherTabs:function(a){var b=this._getList().find("> li:has(a[href])").eq(a).attr("aria-controls");this.component().children("div:not(#"+b+")").empty()},load:function(a,b){a=this._getIndex(a);var c=this,d=this.tabs.eq(a),e=d.find(".coral-tabs-anchor"),f=this._getPanelForTab(d),g={tab:d,panel:f};this.options.destroyAllonActive&&c._destroyOtherTabs(a),(c._isLocal(e[0])||$.data(e[0],"cache"))&&"boolean"!=typeof b||(this.options.loadonce&&$.data(e[0],"cache",!0),this.xhr=$.ajax(this._ajaxSettings(e,b,g)),this.xhr&&"canceled"!==this.xhr.statusText&&(d.addClass("coral-tabs-loading"),f.attr("aria-busy","true"),f.loading({position:"inside",text:"加载中，请耐心等候..."}),this.xhr.success(function(a){setTimeout(function(){f.html(a),$.coral.openTag===!0&&$.parser.parse(f),c._trigger("onLoad",b,g)},1)}).complete(function(a,b){setTimeout(function(){"abort"===b&&c.panels.stop(!1,!0),d.removeClass("coral-tabs-loading"),f.removeAttr("aria-busy"),a===c.xhr&&delete c.xhr},1)})))},_ajaxSettings:function(a,b,c){var d=this;return{url:a.attr("href"),beforeSend:function(a,e){return d._trigger("beforeLoad",b,$.extend({jqXHR:a,ajaxSettings:e},c))},type:d.options.method}},_getPanelForTab:function(a){var b=$(a).attr("aria-controls");return this.element.find(this._sanitizeSelector("#"+b))},getPanelForTab:function(a){return this._getPanelForTab(a)},_getLiWidth:function(){var a={},b=this.element.children(".coral-tabs-content").children(".coral-tabs-nav");return a.totalWidth=0,a.width=[],$(".coral-corner-top",b).each(function(){a.totalWidth+=$(this).outerWidth(!0),a.width.push(a.totalWidth)}),a},_getLiHeight:function(){var a={},b=this.element.children(".coral-tabs-content").children(".coral-tabs-nav");return a.totalHeight=0,a.height=[],$(".coral-corner-top",b).each(function(){a.totalHeight+=$(this).outerHeight(!0),a.height.push(a.totalHeight)}),a},_showhideArrow:function(){var a=this._getClass(this.options.cls),b=this.element.children(".coral-tabs-content");if("coral-tabs-left"==a||"coral-tabs-right"==a){var c=this.element.height(),d=this._getLiHeight().totalHeight;d>c?(b.addClass("coral-content-row").css("height",c-40),this.element.children(".coral-tabs-arrow").show(),this._setArrowPosition(a)):(this.element.children(".coral-tabs-arrow").hide(),b.removeClass("coral-content-row").css("height",c),b.children(".coral-tabs-nav").css({marginTop:0}))}else{var e=b.width(),f=this._getLiWidth().totalWidth;f>e?(b.addClass("coral-content-row"),this.element.children(".coral-tabs-arrow").show(),this._setArrowPosition(a)):(b.removeClass("coral-content-row"),this.element.children(".coral-tabs-arrow").hide(),b.children(".coral-tabs-nav").css({marginLeft:0}))}},_getClass:function(a){if(a){for(var b,c=a.split(" ").reverse(),d=["coral-tabs-left","coral-tabs-right","coral-tabs-bottom"],e=0;e<c.length;e++)if($.inArray(c[e],d)>-1){b=c[e];break}return b}},_bindArrowEvent:function(){var a=this,b=this._getClass(this.options.cls),c=this.element,d=0,e=0;this.element.children(".coral-tabs-arrow").off("click").on("click",function(f){if(a.options.disabled!==!0){var g,h=f.target,i="marginLeft",j=a._getLiWidth(),k=a._getLiHeight(),l=j.width,m=j.totalWidth,n=k.height,o=k.totalHeight,p=c.children(".coral-tabs-content").width(),q=c.children(".coral-tabs-content").height();i="coral-tabs-left"==b||"coral-tabs-right"==b?"marginTop":"marginLeft";var r=$(c).children(".coral-content-row").children(".coral-tabs-nav");g=parseInt(r.css(i));var s=Math.abs(g);if($(h).hasClass("icon-arrow-left3"))d-=1,l[d-1]?r.css({marginLeft:"-"+l[d-1]+"px"}):(r.css({marginLeft:0}),d=0);else if($(h).hasClass("icon-arrow-right3")){var t=p+s;m>t&&(r.css({marginLeft:"-"+l[d]+"px"}),d++)}else if($(h).hasClass("icon-arrow-up3"))e-=1,n[e-1]?r.css({marginTop:"-"+n[e-1]+"px"}):(r.css({marginTop:0}),e=0);else if($(h).hasClass("icon-arrow-down3")){var u=q+s;o>u&&(r.css({marginTop:"-"+n[e]+"px"}),e++)}}})},_setArrowPosition:function(a){var b=this.element,c=b.children(".coral-tabs-content").children(".coral-tabs-nav");if("coral-tabs-left"==a||"coral-tabs-right"==a)var d=c.width()/2,e=b.children(".coral-tabs-arrow.icon-arrow-up3"),f=b.children(".coral-tabs-arrow.icon-arrow-down3"),g=e.width()/2,h=f.width()/2;else var i=c.height()/2,j=b.children(".coral-tabs-arrow.icon-arrow-left3"),k=b.children(".coral-tabs-arrow.icon-arrow-right3"),l=j.height()/2,m=k.height()/2;switch(a){case"coral-tabs-left":e.css({left:d-g}),f.css({left:d-h});break;case"coral-tabs-right":e.css({right:d-g}),f.css({right:d-h});break;case"coral-tabs-bottom":j.css({bottom:i-l}),k.css({bottom:i-m});break;default:j.css({top:i-l}),k.css({top:i-m})}}});