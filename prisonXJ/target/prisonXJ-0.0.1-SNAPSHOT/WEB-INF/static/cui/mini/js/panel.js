/*! cui 2017-11-03 */
var _timer=!1,_resizable=!0;$(window).unbind(".coral-panel").bind("resize.coral-panel",function(){_resizable&&(_timer!==!1&&clearTimeout(_timer),_timer=setTimeout(function(){_resizable=!1,$.coral.refreshAllComponent("body"),_timer=!1,_resizable=!0},200))}),$.component("coral.panel",{version:"4.0.2",options:{id:null,title:null,iconCls:null,width:"auto",height:"auto",left:null,top:null,cls:null,headerCls:null,bodyCls:null,style:{},href:null,cache:!0,fit:!1,border:!0,doSize:!0,showHeader:!0,showTitle:!0,content:null,collapsible:!1,showNavButton:!1,minimizable:!1,maximizable:!1,closable:!1,collapsed:!1,minimized:!1,maximized:!1,closed:!1,tools:null,url:null,loadtext:"加载中，请耐心等候...",loadingMessage:"Loading...",extractor:function(a){var b=/<body[^>]*>((.|[\n\r])*)<\/body>/im,c=b.exec(a);return c?c[1]:a},beforeOpen:$.noop,beforeClose:$.noop,beforeDestroy:$.noop,beforeCollapse:$.noop,beforeExpand:$.noop,onLoad:$.noop,onOpen:$.noop,onClose:$.noop,onDestroy:$.noop,onResize:$.noop,onMove:$.noop,onMaximize:$.noop,onRestore:$.noop,onMinimize:$.noop,onCollapse:$.noop,onExpand:$.noop},_create:function(){this.originalTitle=this.element.attr("title"),this.element.removeAttr("title").addClass("ctrl-init ctrl-init-panel"),this._createWrapper(),this.isLoaded=!1,this.element.appendTo(this.uiPanel),this._addHeader(),this._setBorder(),1==this.options.doSize&&(this.uiPanel.css("display","block"),this.resizePanel()),1==this.options.closed||1==this.options.minimized?this.close():this.open()},_createWrapper:function(){this.element.addClass("coral-panel-body"),this.uiPanel=$('<div class="coral-panel"></div>').insertBefore(this.element)},_fit:function(a){return $.coral.panel.fit(this.uiPanel,a)},resizePanel:function(a){var b=this.options,c=this.uiPanel,d=c.children("div.coral-panel-header"),e=c.children("div.coral-panel-body");a&&$.extend(b,{width:a.width,height:a.height,left:a.left,top:a.top}),b.fit?$.extend(b,this._fit()):this._fit(!1),c.css({left:b.left,top:b.top}),isNaN(b.width)?c.width("auto"):c.outerWidth(b.width),d.add(e).outerWidth(c.width()),isNaN(b.height)?(e.height("auto"),c.css("height","")):(c.outerHeight(b.height),e.outerHeight(c.height()-d.outerHeight())),this._doResize(),this.toolbarEl&&this.toolbarEl.toolbar("refresh"),this._trigger("onResize",null,{width:b.width,height:b.height})},_setBorder:function(){var a=this.options,b=this.uiPanel,c=this.header(),d=this.body();$.isEmptyObject(a.style)||b.css(a.style),a.border?(c.removeClass("coral-panel-header-noborder"),d.removeClass("coral-panel-body-noborder")):(c.addClass("coral-panel-header-noborder"),d.addClass("coral-panel-body-noborder")),c.addClass(a.headerCls),d.addClass(a.bodyCls)},_addHeader:function(){var a=this.options,b=this.uiPanel,c=this;if(a.tools&&"string"==typeof a.tools&&b.find(">div.coral-panel-header>div.coral-panel-tool .coral-panel-tool-a").appendTo(a.tools),b.children("div.coral-panel-header").remove(),a.title&&a.showHeader){var d=$('<div class="coral-panel-header"></div>').prependTo(b);this.toolbarEl=$("<div></div>"),this.toolbarEl.appendTo(d);var e=[],f={type:"html",content:a.title},g={type:"button",icons:"cui-icon-arrow-up3 coral-panel-tool-collapse",label:"collapse",text:!1,onClick:function(){a.collapsed===!0?c.expand(!0):c.collapse(!0)}},h={type:"button",icons:"cui-icon-minus3 coral-panel-tool-min",label:"minimize",text:!1,onClick:function(){c.minimize()}},i={type:"button",icons:"cui-icon-enlarge7 coral-panel-tool-max",label:"maximize",text:!1,onClick:function(){a.maximized===!0?c.restore():c.maximize()}},j={type:"button",icons:"cui-icon-cross2 coral-panel-tool-close",label:"close",text:!1,onClick:function(){c.close()}},k={data:e};a.toolbarOptions?($.each(a.toolbarOptions.data,function(b,c){"title"===c&&(a.toolbarOptions.data[b]=f),"collapse"===c&&(a.toolbarOptions.data[b]=g),"minimize"===c&&(a.toolbarOptions.data[b]=h),"maximize"===c&&(a.toolbarOptions.data[b]=i),"close"===c&&(a.toolbarOptions.data[b]=j)}),$.extend(!0,k,a.toolbarOptions)):(a.showTitle&&e.push(f),e.push("->"),a.collapsible&&e.push(g),a.minimizable&&e.push(h),a.maximizable&&e.push(i),a.closable&&e.push(j)),this.toolbarEl.toolbar(k),b.children("div.coral-panel-body").removeClass("coral-panel-body-noheader")}else b.children("div.coral-panel-body").addClass("coral-panel-body-noheader")},toolbar:function(){return this.toolbarEl},_loadData:function(){var a=this.options,b=this;a.url?b.isLoaded&&a.cache||(b.isLoaded=!1,b.element.html(""),$(this.element).loading({position:"inside",text:this.options.loadtext}),this.xhr&&this.xhr.abort(),this.xhr=$.ajax(this._ajaxSettings()),this.xhr.success(function(c){b.setContent(a.extractor.call(b.element,c)),b._trigger("onLoad",null,arguments),b.isLoaded=!0}).complete(function(a,c){a===b.xhr&&(b.xhr=null)})):a.content&&(b.isLoaded||(b.setContent(a.content),b.isLoaded=!0))},_ajaxSettings:function(){var a=this.options;return{url:a.url,cache:!1,dataType:"html"}},_doResize:function(){$.coral.refreshChild(this.element)},_destroy:function(a){1!=a&&0==this._trigger("beforeDestroy")||(this.originalTitle&&this.element.attr("title",this.originalTitle),this.element.removeClass("coral-panel-body").detach(),this.element.insertAfter(this.component()),this.component().remove(),this._trigger("onDestroy"))},component:function(){return this.uiPanel},panel:function(){return this.uiPanel},getOptions:function(){return this.options},header:function(){return this.uiPanel.find(">div.coral-panel-header")},body:function(){return this.uiPanel.find(">div.coral-panel-body")},setTitle:function(a){this.options.title=a,this.header().find("div.coral-panel-title").html(a)},setContent:function(a){this.element.children().remove(),this.element.html(a),$.parser&&$.parser.parse(this.element)},open:function(a){var b=this.options,c=null;1!=a&&this._trigger("beforeOpen",null)===!1||(this.uiPanel.show(),b.closed=!1,b.minimized=!1,c=this.uiPanel.children(".coral-panel-header").find("a.coral-panel-tool-restore"),c.length&&(b.maximized=!0),this._trigger("onOpen"),b.maximized===!0&&(b.maximized=!1,this.maximize()),b.collapsed===!0&&(b.collapsed=!1,this.collapse()),b.collapsed||(this._loadData(),this._doResize()))},close:function(a){var b=this.options,c=this.uiPanel;1!=a&&0==this._trigger("beforeClose",null)||(this._fit(!1),c.hide(),b.closed=!0,this._trigger("onClose"))},refresh:function(a){this.isLoaded=!1,a&&(this.options.url=a),this._loadData()},reload:function(a){this.refresh(a)},resize:function(a){this.resizePanel(a)},move:function(a){var b=this.options;a&&(null!=a.left&&(b.left=a.left),null!=a.top&&(b.top=a.top)),this.uiPanel.css({left:b.left,top:b.top}),this._trigger("onMove",null,{left:b.left,top:b.top})},maximize:function(){var a=this.options;a.maximized!==!0&&(this.uiPanel.children("div.coral-panel-header").find("a.coral-panel-tool-max").addClass("coral-panel-tool-restore").addClass("cui-icon-shrink7").removeClass("cui-icon-enlarge7"),this.original||(this.original={width:a.width,height:a.height,left:a.left,top:a.top,fit:a.fit}),a.left=0,a.top=0,a.fit=!0,this.resizePanel(),a.minimized=!1,a.maximized=!0,this._trigger("onMaximize"))},minimize:function(){this._fit(!1),this.uiPanel.hide(),this.options.minimized=!0,this.options.maximized=!1,this._trigger("onMinimize")},restore:function(){this.options.maximized!==!1&&(this.uiPanel.show(),this.uiPanel.children("div.coral-panel-header").find("a.coral-panel-tool-max").removeClass("coral-panel-tool-restore").addClass("cui-icon-enlarge7").removeClass("cui-icon-shrink7"),$.extend(this.options,this.original),this.resizePanel(),this.options.minimized=!1,this.options.maximized=!1,this.original=null,this._trigger("onRestore"))},collapse:function(a){var b=this.options,c=this.body(),d=this;b.collapsed!==!0&&(c.stop(!0,!0),d._trigger("beforeCollapse")!==!1&&(this.header().find("a.coral-panel-tool-collapse").addClass("coral-panel-tool-expand"),a===!0?c.slideUp("normal",function(){b.collapsed=!0,d._trigger("onCollapse")}):(c.hide(),b.collapsed=!0,d._trigger("onCollapse"))))},expand:function(a){var b=this.options,c=this.body(),d=this;b.collapsed!==!1&&(c.stop(!0,!0),d._trigger("beforeExpand")!==!1&&(this.header().find("a.coral-panel-tool-collapse").removeClass("coral-panel-tool-expand"),a===!0?c.slideDown("normal",function(){b.collapsed=!1,d._trigger("onExpand"),d._loadData(),d._doResize()}):(c.show(),b.collapsed=!1,d._trigger("onExpand"),d._loadData(),d._doResize())))}}),$.coral.panel.fit=function(a,b){b=void 0==b||b;var c=a.parent()[0];return c=$(c),b?c.hasClass("coral-noscroll")||(c.addClass("coral-noscroll"),"BODY"==c.attr("tagName")&&$("html").addClass("coral-panel-fit")):c.hasClass("coral-noscroll")&&(c.removeClass("coral-noscroll"),"BODY"==c.attr("tagName")&&$("html").removeClass("coral-panel-fit")),{width:c.width(),height:c.height()}};