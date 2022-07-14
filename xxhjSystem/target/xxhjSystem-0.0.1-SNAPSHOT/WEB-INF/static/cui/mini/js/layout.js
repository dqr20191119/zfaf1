/*! cui 2017-11-03 */
$.component("coral.layout",{version:"4.1.4",options:{onCreate:null,fit:!1},layoutPanelDefault:{region:null,split:!1,showSplitIcon:!1,collapsedSize:30,minWidth:10,minHeight:10,maxWidth:1e4,maxHeight:1e4},resizing:!1,_resizeLayout:function(){function a(a){var b=a.panel("getOptions");return Math.min(Math.max(b.height,b.minHeight),b.maxHeight)}function b(a){var b=a.panel("getOptions");return Math.min(Math.max(b.width,b.minWidth),b.maxWidth)}function c(b,c){if(b.length&&e._isPanelVisible(b)){var d=b.panel("getOptions"),f=a(b),g="n"==c?"north":"south",h=i.find(">.coral-layout-panel-"+g).children(".ctrl-init-panel"),l=h.panel("getOptions");if(n.height-=f,"n"==c){var m=i.find(">.coral-layout-split-north");n.top+=f+j,!l.split&&d.border&&n.top--,m.css({top:f,left:0})}else{var o=i.find(">.coral-layout-split-south");o.css({top:i.height()-f-k,left:0})}b.panel("resize",{width:i.width(),height:f,left:0,top:"n"==c?0:i.height()-f}),!l.split&&d.border&&n.height++,d.border&&!$.support.boxSizing&&n.height-2}}function d(a,c){if(a.length&&e._isPanelVisible(a)){var d=a.panel("getOptions"),f="e"==c?"east":"west",g=i.find(">.coral-layout-panel-"+f).children(".ctrl-init-panel"),h=g.panel("getOptions"),j=b(a);if(a.panel("resize",{width:j,height:n.height,left:"e"==c?i.width()-j:0,top:n.top}),n.width-=j,"w"==c){var k=i.find(">.coral-layout-split-west");n.left+=j+l,k.css({height:n.height,top:n.top,left:j}),!h.split&&d.border&&n.left--}else{var o=i.find(">.coral-layout-split-east");o.css({height:n.height,top:n.top,left:i.width()-j-m})}!h.split&&d.border&&n.width++,$.support.boxSizing||n.width-2}}var e=this,f=this.element,g=e.options,h=e.panels,i=$(f);"body"==f[0].tagName.toLowerCase()?this._fit():g.fit?i.css(this._fit()):this._fit(!1);var j=i.find(">.coral-layout-split-north").height()||0,k=i.find(">.coral-layout-split-south").height()||0,l=i.find(">.coral-layout-split-west").width()||0,m=i.find(">.coral-layout-split-east").width()||0,n={top:0,left:0,width:i.width()-l-m,height:i.height()-k-j};c(this._isPanelVisible(h.expandNorth)?h.expandNorth:h.north,"n"),c(this._isPanelVisible(h.expandSouth)?h.expandSouth:h.south,"s"),d(this._isPanelVisible(h.expandEast)?h.expandEast:h.east,"e"),d(this._isPanelVisible(h.expandWest)?h.expandWest:h.west,"w"),h.center.panel("resize",n)},_splitLayout:function(a,b){var c=$(this.element).layout("panel",a);c.panel("options").split=b;var d="layout-split-"+a,e=c.panel("panel").removeClass(d);b&&e.addClass(d),e.resizable({disabled:!b}),this._resizeLayout()},_create:function(){function a(a){a.children("div").each(function(){var d=$.parser.parseOptions(this,[]);if("north,south,east,west,center".indexOf(d.region)>=0){b._addPanel(c,d,this);var e=a.layout("panel",d.region);e.panel("option").split&&b._addSplitIcon(c,d,this)}})}this.panels={center:$(),north:$(),south:$(),east:$(),west:$()};var b=this,c=this.element,d=$(c);d.addClass("coral-layout"),this.component().attr("component-id",this.options.id),a(d.children("form").length?d.children("form"):d),d.append('<div class="coral-layout-split-proxy-h"></div><div class="coral-layout-split-proxy-v"></div>'),this._resizeLayout(),this._initCollapse(),this._on({"click.splitIcon":function(a){var c=$(a.target).attr("data-dir");return b.panels[c].is(":visible")?this._collapse(c):this._expand(c),this._trigger("onSplitIconClick",a),!1}})},_addSplitIcon:function(a,b,c){var d=$(a),e=b.region,f=d.layout("panel",e),g=$(c),h=$(c).parent(),i=(g.panel("component"),"coral-layout-split-"+e),j="<div id='layout-split-"+e+"Id' class='layout-split "+i+"'>",k={north:"up5",south:"down5",east:"right5",west:"left5"};if(k[e]){if(g.length||(h=d.find(">.coral-layout-panel-"+e)),f.panel("option").showSplitIcon){var l="splitIcon cui-icon-arrow-"+k[e],m="<div class='"+l+"' data-dir='"+e+"'></div>";j+=m+"</div>"}else j+="</div>";var n=$(j);h.after(n)}},_addPanel:function(a,b,c){var d=this;b.region=b.region||"center";var e=this.panels,f=$(a),g=b.region;if(!e[g].length){var h=$(c);h.length||(h=$("<div></div>").appendTo(f));var i={north:"up3",south:"down3",east:"right3",west:"left3"},j="cui-icon-arrow-"+i[g],k=["title","->"],i={north:"up3",south:"down3",east:"right3",west:"left3"},j="cui-icon-arrow-"+i[g];i[g]&&k.push({icons:j,label:g,text:!1,onClick:function(a){d._collapse(g)}});var l=$.extend({},this.layoutPanelDefault,{width:h.length?parseInt(h[0].style.width)||h.outerWidth():"auto",height:h.length?parseInt(h[0].style.height)||h.outerHeight():"auto",doSize:!1,componentCls:"coral-layout-panel coral-layout-panel-"+g,bodyCls:"coral-layout-body",toolbarOptions:{data:k},onOpen:function(){}},b);"center"===l.region&&(l.split=!1),h.panel(l),e[g]=h;var m=h.panel("component"),n={north:"s",south:"n",east:"w",west:"e"};h.panel("option").split&&m.resizable($.extend({handles:n[g]||"",disabled:!h.panel("option").split,start:function(b){if(d.resizing=!0,"north"==g||"south"==g)var c=$(">div.coral-layout-split-proxy-v",a);else var c=$(">div.coral-layout-split-proxy-h",a);var e={display:"block"};"north"==g?(e.top=parseInt(m.css("top"))+m.outerHeight()-c.height(),e.left=parseInt(m.css("left")),e.width=m.outerWidth(),e.height=c.height()):"south"==g?(e.top=parseInt(m.css("top")),e.left=parseInt(m.css("left")),e.width=m.outerWidth(),e.height=c.height()):"east"==g?(e.top=parseInt(m.css("top"))||0,e.left=parseInt(m.css("left"))||0,e.width=c.width(),e.height=m.outerHeight()):"west"==g&&(e.top=parseInt(m.css("top"))||0,e.left=m.outerWidth()-c.width(),e.width=c.width(),e.height=m.outerHeight()),c.css(e),$('<div class="coral-layout-mask"></div>').css({left:0,top:0,width:f.width(),height:f.height()}).appendTo(f)},resize:function(b){if("north"==g||"south"==g){var c=$(">div.coral-layout-split-proxy-v",a);c.css("top",b.pageY-$(a).offset().top-c.height()/2)}else{var c=$(">div.coral-layout-split-proxy-h",a);c.css("left",b.pageX-$(a).offset().left-c.width()/2)}return!1},helper:"coral-resizable-helper",stop:function(a,b){f.children("div.coral-layout-split-proxy-v,div.coral-layout-split-proxy-h").hide(),h.panel("resize",b.size),d._resizeLayout(),d.resizing=!1,f.find(">div.coral-layout-mask").remove()}},b))}},_remove:function(a){var b=this.panels,c=this.element,d=$(c).find(".coral-layout-split-"+a);d.find(".splitIcon");if(b[a].length){b[a].panel("destroy"),$(b[a]).remove(),b[a]=$();var e="expand"+a.substring(0,1).toUpperCase()+a.substring(1);b[e]&&(b[e].panel("destroy"),$(b[a]).remove(),b[e]=void 0),d.length&&($(d).remove(),d=$())}},_collapseSplitIcon:function(a,b){var c,d=this.element,e=this.panels[a],f=e.panel("getOptions"),g=(f.collapsedSize,$(d).find(".coral-layout-split-"+a)),h=g.find(".splitIcon");if("east"==a)c="cui-icon-arrow-left5",h.removeClass("cui-icon-arrow-right5").addClass(c),g.css({left:b.expandP.left-g.width()});else if("west"==a)c="cui-icon-arrow-right5",h.removeClass("cui-icon-arrow-left5").addClass(c),g.css({left:b.expandP.width});else if("north"==a)c="cui-icon-arrow-down5",h.removeClass("cui-icon-arrow-up5").addClass(c),g.css({top:b.expandP.height}),$(d).find(".coral-layout-split-west").css({top:b.expandP.height+g.height(),height:b.resizeC.height}),$(d).find(".coral-layout-split-east").css({top:b.expandP.height+g.height(),height:b.resizeC.height});else if("south"==a){c="cui-icon-arrow-up5",h.removeClass("cui-icon-arrow-down5").addClass(c);var i=b.expandP.top-g.height();g.css({top:i}),$(d).find(".coral-layout-split-west").css({height:b.resizeC.height}),$(d).find(".coral-layout-split-east").css({height:b.resizeC.height})}},_collapse:function(a,b){function c(b){var c="";"east"==b?c+="cui-icon-arrow-left3":"west"==b?c+="cui-icon-arrow-right3":"north"==b?c+="cui-icon-arrow-down3":"south"==b&&(c+="cui-icon-arrow-up3");var d=$("<div></div>").appendTo(f),g=["->","->",{icons:c,componentCls:"collapseIcon",label:a,text:!1,onClick:function(b){e._expand(a)}}];return"east"!=b&&"west"!=b||(g=[{icons:c,componentCls:"collapseIcon",label:a,text:!1,onClick:function(b){e._expand(a)}}]),d.panel($.extend({},e.layoutPanelDefault,{componentCls:"coral-layout-expand coral-layout-expand-"+b,title:"&nbsp;",showTitle:!1,closed:!0,minWidth:0,minHeight:0,doSize:!1,toolbarOptions:{isOverflow:!1,data:g}})),d.panel("component").hover(function(){$(this).addClass("coral-layout-expand-over")},function(){$(this).removeClass("coral-layout-expand-over")}),d}function d(){var b=$(f),c=g.center.panel("getOptions"),d=i.collapsedSize;if("east"==a){var h=c.width+i.width-d,j=0;return i.split||(j=1),{resizeC:{width:h},expand:{left:b.width()-i.width},expandP:{top:c.top,left:b.width()-d,width:d,height:c.height},collapse:{left:b.width(),top:c.top,height:c.height}}}if("west"==a){var h=c.width+i.width-d,k=b.find(">.coral-layout-split-west"),j=0;return i.split||(j=1),{resizeC:{width:h,left:d+k.width()-j},expand:{left:0},expandP:{left:0,top:c.top,width:d,height:c.height},collapse:{left:-i.width,top:c.top,height:c.height}}}if("north"==a){var l=c.height,m=b.find(">.coral-layout-split-north");return e._isPanelVisible(g.expandNorth)||(l+=i.height-d),g.east.add(g.west).add(g.expandEast).add(g.expandWest).panel("resize",{top:d+m.height(),height:l}),{resizeC:{top:d+m.height(),height:l},expand:{top:0},expandP:{top:0,left:0,width:b.width(),height:d},collapse:{top:-i.height,width:b.width()}}}if("south"==a){var l=c.height;return e._isPanelVisible(g.expandSouth)||(l+=i.height-d),g.east.add(g.west).add(g.expandEast).add(g.expandWest).panel("resize",{height:l}),{resizeC:{height:l},expand:{top:b.height()-i.height},expandP:{top:b.height()-d,left:0,width:b.width(),height:d},collapse:{top:b.height(),width:b.width()}}}}var e=this,f=this.element;void 0==b&&(b="normal");var g=this.panels,h=g[a],i=h.panel("getOptions");if(0!=i.beforeCollapse.call(h)){var j="expand"+a.substring(0,1).toUpperCase()+a.substring(1);g[j]||(g[j]=c(a),g[j].panel("component").bind("click",function(b){if($(b.target).closest(".collapseIcon").length)return!1;var c=d();return h.panel("expand",!1).panel("open").panel("resize",c.collapse),h.panel("component").animate(c.expand,function(){$(this).unbind(".layout").bind("mouseleave.layout",{region:a},function(a){1!=e.resizing&&e._collapse(a.data.region)})}),!1}));var k=d();this._isPanelVisible(g[j])||g.center.panel("resize",k.resizeC),h.panel("component").animate(k.collapse,b,function(){h.panel("collapse",!1).panel("close"),g[j].panel("open").panel("resize",k.expandP),$(this).unbind(".layout")}),this._collapseSplitIcon(a,k)}},_expandSplitIcon:function(a){var b,c=this.element,d=$(c).find(".coral-layout-split-"+a),e=d.find(".splitIcon");"east"==a?(b="cui-icon-arrow-right5",e.removeClass("cui-icon-arrow-left5").addClass(b)):"west"==a?(b="cui-icon-arrow-left5",e.removeClass("cui-icon-arrow-right5").addClass(b)):"north"==a?(b="cui-icon-arrow-up5",e.removeClass("cui-icon-arrow-down5").addClass(b)):"south"==a&&(b="cui-icon-arrow-down5",e.removeClass("cui-icon-arrow-up5").addClass(b))},_expand:function(a){function b(){var b=$(d),c=e.center.panel("getOptions");return"east"==a&&e.expandEast?{collapse:{left:b.width(),top:c.top,height:c.height},expand:{left:b.width()-e.east.panel("getOptions").width}}:"west"==a&&e.expandWest?{collapse:{left:-e.west.panel("getOptions").width,top:c.top,height:c.height},expand:{left:0}}:"north"==a&&e.expandNorth?{collapse:{top:-e.north.panel("getOptions").height,width:b.width()},expand:{top:0}}:"south"==a&&e.expandSouth?{collapse:{top:b.height(),width:b.width()},expand:{top:b.height()-e.south.panel("getOptions").height,height:e.south.panel("getOptions").height}}:void 0}var c=this,d=this.element,e=this.panels,f=e[a],g=f.panel("getOptions");if(0!=g.beforeExpand.call(f)){var h=b(),i="expand"+a.substring(0,1).toUpperCase()+a.substring(1);e[i]&&(e[i].panel("close"),f.panel("component").stop(!0,!0),f.panel("expand",!1).panel("open").panel("resize",h.collapse),f.panel("component").animate(h.expand,function(){c._resizeLayout()}),this._expandSplitIcon(a))}},_isPanelVisible:function(a){return!!a&&(!!a.length&&a.panel("component").is(":visible"))},_initCollapse:function(){var a=this.panels;a.east.length&&a.east.panel("getOptions").collapsed&&this._collapse("east",0),a.west.length&&a.west.panel("getOptions").collapsed&&this._collapse("west",0),a.north.length&&a.north.panel("getOptions").collapsed&&this._collapse("north",0),a.south.length&&a.south.panel("getOptions").collapsed&&this._collapse("south",0)},_fit:function(a){return $.coral.panel.fit(this.element,a)},_destroy:function(){this.element.children().remove(),this.element.detach()},refresh:function(){this._resizeLayout()},panel:function(a){return this.panels[a]},collapse:function(a){this._collapse(a)},expand:function(a){this._expand(a)},add:function(a){this._addPanel(this.element,a),a.split&&this._addSplitIcon(this.element,a),this._resizeLayout(),this.panels[a.region].panel("getOptions").collapsed&&this._collapse(a.region,0)},remove:function(a){this._remove(a),this._resizeLayout()},split:function(a){this._splitLayout(a,!0)},unsplit:function(a){this._splitLayout(a,!1)},resize:function(a,b){var c=a,d=this.element;if(void 0!==b){var e=this.panels[b],f=e.panel("component").outerHeight(),g=e.panel("component").outerWidth();e.panel("resize",a);var h=e.panel("component").outerHeight(),i=e.panel("component").outerWidth();c.height=$(d).height()+h-f,c.width=$(d).width()+i-g}$(d).css(c),this._resizeLayout()}});