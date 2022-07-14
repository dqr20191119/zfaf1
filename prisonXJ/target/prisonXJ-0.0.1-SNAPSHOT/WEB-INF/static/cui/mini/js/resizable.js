/*! cui 2017-11-03 */
!function(){function a(a){return parseInt(a,10)||0}function b(a){return!isNaN(parseInt(a,10))}function c(a,b){if("hidden"===$(a).css("overflow"))return!1;var c=b&&"left"===b?"scrollLeft":"scrollTop",d=!1;return a[c]>0||(a[c]=1,d=a[c]>0,a[c]=0,d)}$.component("coral.resizable",$.coral.mouse,{version:"4.0.1",componentEventPrefix:"resize",options:{alsoResize:!1,animate:!1,animateDuration:"slow",animateEasing:"swing",aspectRatio:!1,autoHide:!1,containment:!1,ghost:!1,grid:!1,layout:!1,helper:!1,maxHeight:null,maxWidth:null,minHeight:10,minWidth:10,zIndex:90,resize:null,start:null,stop:null},_create:function(){var a,b,c,d,e,f=this,g=this.options;if(this.element.addClass("coral-resizable"),$.extend(this,{_aspectRatio:!!g.aspectRatio,aspectRatio:g.aspectRatio,originalElement:this.element,_proportionallyResizeElements:[],_helper:g.helper||g.ghost||g.animate?g.helper||"coral-resizable-helper":null}),this.element[0].nodeName.match(/canvas|textarea|input|select|button|img/i)&&(this.element.wrap($("<div class='coral-wrapper' style='overflow: hidden;'></div>").css({position:this.element.css("position"),width:this.element.outerWidth(),height:this.element.outerHeight(),top:this.element.css("top"),left:this.element.css("left")})),this.element=this.element.parent().data("coral-resizable",this.element.data("coral-resizable")),this.elementIsWrapper=!0,this.element.css({marginLeft:this.originalElement.css("marginLeft"),marginTop:this.originalElement.css("marginTop"),marginRight:this.originalElement.css("marginRight"),marginBottom:this.originalElement.css("marginBottom")}),this.originalElement.css({marginLeft:0,marginTop:0,marginRight:0,marginBottom:0}),this.originalResizeStyle=this.originalElement.css("resize"),this.originalElement.css("resize","none"),this._proportionallyResizeElements.push(this.originalElement.css({position:"static",zoom:1,display:"block"})),this.originalElement.css({margin:this.originalElement.css("margin")}),this._proportionallyResize()),this.handles=g.handles||($(".coral-resizable-handle",this.element).length?{n:".coral-resizable-n",e:".coral-resizable-e",s:".coral-resizable-s",w:".coral-resizable-w",se:".coral-resizable-se",sw:".coral-resizable-sw",ne:".coral-resizable-ne",nw:".coral-resizable-nw"}:"e,s,se"),this.handles.constructor===String)for("all"===this.handles&&(this.handles="n,e,s,w,se,sw,ne,nw"),a=this.handles.split(","),this.handles={},b=0;b<a.length;b++)c=$.trim(a[b]),e="coral-resizable-"+c,d=$("<div class='coral-resizable-handle "+e+"'></div>"),d.css({zIndex:g.zIndex}),"se"===c&&d.addClass("coral-icon coral-icon-gripsmall-diagonal-se"),this.handles[c]=".coral-resizable-"+c,this.element.append(d);this._renderAxis=function(a){var b,c,d,e;a=a||this.element;for(b in this.handles)this.handles[b].constructor===String&&(this.handles[b]=this.element.children(this.handles[b]).first().show()),this.elementIsWrapper&&this.originalElement[0].nodeName.match(/textarea|input|select|button/i)&&(c=$(this.handles[b],this.element),e=/sw|ne|nw|se|n|s/.test(b)?c.outerHeight():c.outerWidth(),d=["padding",/ne|nw|n/.test(b)?"Top":/se|sw|s/.test(b)?"Bottom":/^e$/.test(b)?"Right":"Left"].join(""),a.css(d,e),this._proportionallyResize()),$(this.handles[b]).length},this._renderAxis(this.element),this._handles=$(".coral-resizable-handle",this.element).disableSelection(),this._handles.mouseover(function(){f.resizing||(this.className&&(d=this.className.match(/coral-resizable-(se|sw|ne|nw|n|e|s|w)/i)),f.axis=d&&d[1]?d[1]:"se")}),g.autoHide&&(this._handles.hide(),$(this.element).addClass("coral-resizable-autohide").mouseenter(function(){g.disabled||($(this).removeClass("coral-resizable-autohide"),f._handles.show())}).mouseleave(function(){g.disabled||f.resizing||($(this).addClass("coral-resizable-autohide"),f._handles.hide())})),this._mouseInit()},_destroy:function(){this._mouseDestroy();var a,b=function(a){$(a).removeClass("coral-resizable coral-resizable-disabled coral-resizable-resizing").removeData("resizable").removeData("coral-resizable").unbind(".resizable").find(".coral-resizable-handle").remove()};return this.elementIsWrapper&&(b(this.element),a=this.element,this.originalElement.css({position:a.css("position"),width:a.outerWidth(),height:a.outerHeight(),top:a.css("top"),left:a.css("left")}).insertAfter(a),a.remove()),this.originalElement.css("resize",this.originalResizeStyle),b(this.originalElement),this},_mouseCapture:function(a){var b,c,d=!1;for(b in this.handles)c=$(this.handles[b])[0],(c===a.target||$.contains(c,a.target))&&(d=!0);return!this.options.disabled&&d},_mouseStart:function(b){var c,d,e,f=this.options,g=this.element;return this.resizing=!0,this._renderProxy(),c=a(this.helper.css("left")),d=a(this.helper.css("top")),f.containment&&(c+=$(f.containment).scrollLeft()||0,d+=$(f.containment).scrollTop()||0),this.offset=this.helper.offset(),this.position={left:c,top:d},this.size=this._helper?{width:this.helper.width(),height:this.helper.height()}:{width:g.width(),height:g.height()},this.originalSize=this._helper?{width:g.outerWidth(),height:g.outerHeight()}:{width:g.width(),height:g.height()},this.originalPosition={left:c,top:d},this.sizeDiff={width:g.outerWidth()-g.width(),height:g.outerHeight()-g.height()},this.originalMousePosition={left:b.pageX,top:b.pageY},this.aspectRatio="number"==typeof f.aspectRatio?f.aspectRatio:this.originalSize.width/this.originalSize.height||1,e=$(".coral-resizable-"+this.axis).css("cursor"),$("body").css("cursor","auto"===e?this.axis+"-resize":e),g.addClass("coral-resizable-resizing"),this._propagate("start",b),!0},_mouseDrag:function(a){var b,c,d=this.originalMousePosition,e=this.axis,f=a.pageX-d.left||0,g=a.pageY-d.top||0,h=this._change[e];return this._updatePrevProperties(),!!h&&(b=h.apply(this,[a,f,g]),this._updateVirtualBoundaries(a.shiftKey),(this._aspectRatio||a.shiftKey)&&(b=this._updateRatio(b,a)),b=this._respectSize(b,a),this._updateCache(b),this._propagate("resize",a),c=this._applyChanges(),!this._helper&&this._proportionallyResizeElements.length&&this._proportionallyResize(),$.isEmptyObject(c)||(this._updatePrevProperties(),this._trigger("resize",a,this.ui()),this._applyChanges()),!1)},_mouseStop:function(a){this.resizing=!1;var b,d,e,f,g,h,i,j=this.options,k=this;return this._helper&&(b=this._proportionallyResizeElements,d=b.length&&/textarea/i.test(b[0].nodeName),e=d&&c(b[0],"left")?0:k.sizeDiff.height,f=d?0:k.sizeDiff.width,g={width:k.helper.width()-f,height:k.helper.height()-e},h=parseInt(k.element.css("left"),10)+(k.position.left-k.originalPosition.left)||null,i=parseInt(k.element.css("top"),10)+(k.position.top-k.originalPosition.top)||null,j.animate||this.element.css($.extend(g,{top:i,left:h})),k.helper.height(k.size.height),k.helper.width(k.size.width),this._helper&&!j.animate&&this._proportionallyResize()),$("body").css("cursor","auto"),this.element.removeClass("coral-resizable-resizing"),this._propagate("stop",a,this.ui()),this._helper&&this.helper.remove(),!1},_updatePrevProperties:function(){this.prevPosition={top:this.position.top,left:this.position.left},this.prevSize={width:this.size.width,height:this.size.height}},_applyChanges:function(){var a={};return this.position.top!==this.prevPosition.top&&(a.top=this.position.top+"px"),this.position.left!==this.prevPosition.left&&(a.left=this.position.left+"px"),this.size.width!==this.prevSize.width&&(a.width=this.size.width+"px"),this.size.height!==this.prevSize.height&&(a.height=this.size.height+"px"),this.helper.css(a),a},_updateVirtualBoundaries:function(a){var c,d,e,f,g,h=this.options;g={minWidth:b(h.minWidth)?h.minWidth:0,maxWidth:b(h.maxWidth)?h.maxWidth:1/0,minHeight:b(h.minHeight)?h.minHeight:0,maxHeight:b(h.maxHeight)?h.maxHeight:1/0},(this._aspectRatio||a)&&(c=g.minHeight*this.aspectRatio,e=g.minWidth/this.aspectRatio,d=g.maxHeight*this.aspectRatio,f=g.maxWidth/this.aspectRatio,c>g.minWidth&&(g.minWidth=c),e>g.minHeight&&(g.minHeight=e),d<g.maxWidth&&(g.maxWidth=d),f<g.maxHeight&&(g.maxHeight=f)),this._vBoundaries=g},_updateCache:function(a){this.offset=this.helper.offset(),b(a.left)&&(this.position.left=a.left),b(a.top)&&(this.position.top=a.top),b(a.height)&&(this.size.height=a.height),b(a.width)&&(this.size.width=a.width)},_updateRatio:function(a){var c=this.position,d=this.size,e=this.axis;return b(a.height)?a.width=a.height*this.aspectRatio:b(a.width)&&(a.height=a.width/this.aspectRatio),"sw"===e&&(a.left=c.left+(d.width-a.width),a.top=null),"nw"===e&&(a.top=c.top+(d.height-a.height),a.left=c.left+(d.width-a.width)),a},_respectSize:function(a){var c=this._vBoundaries,d=this.axis,e=b(a.width)&&c.maxWidth&&c.maxWidth<a.width,f=b(a.height)&&c.maxHeight&&c.maxHeight<a.height,g=b(a.width)&&c.minWidth&&c.minWidth>a.width,h=b(a.height)&&c.minHeight&&c.minHeight>a.height,i=this.originalPosition.left+this.originalSize.width,j=this.position.top+this.size.height,k=/sw|nw|w/.test(d),l=/nw|ne|n/.test(d);return g&&(a.width=c.minWidth),h&&(a.height=c.minHeight),e&&(a.width=c.maxWidth),f&&(a.height=c.maxHeight),g&&k&&(a.left=i-c.minWidth),e&&k&&(a.left=i-c.maxWidth),h&&l&&(a.top=j-c.minHeight),f&&l&&(a.top=j-c.maxHeight),a.width||a.height||a.left||!a.top?a.width||a.height||a.top||!a.left||(a.left=null):a.top=null,a},_getPaddingPlusBorderDimensions:function(a){for(var b=0,c=[],d=[a.css("borderTopWidth"),a.css("borderRightWidth"),a.css("borderBottomWidth"),a.css("borderLeftWidth")],e=[a.css("paddingTop"),a.css("paddingRight"),a.css("paddingBottom"),a.css("paddingLeft")];b<4;b++)c[b]=parseInt(d[b],10)||0,c[b]+=parseInt(e[b],10)||0;return{height:c[0]+c[2],width:c[1]+c[3]}},_proportionallyResize:function(){if(this._proportionallyResizeElements.length)for(var a,b=0,c=this.helper||this.element;b<this._proportionallyResizeElements.length;b++)a=this._proportionallyResizeElements[b],this.outerDimensions||(this.outerDimensions=this._getPaddingPlusBorderDimensions(a)),a.css({height:c.height()-this.outerDimensions.height||0,width:c.width()-this.outerDimensions.width||0})},_renderProxy:function(){var a=this.element,b=this.options;this.elementOffset=a.offset(),this._helper?(this.helper=this.helper||$("<div style='overflow:hidden;'></div>"),this.helper.addClass(this._helper).css({width:this.element.outerWidth()-1,height:this.element.outerHeight()-1,position:"absolute",left:this.elementOffset.left+"px",top:this.elementOffset.top+"px",zIndex:++b.zIndex}),this.helper.appendTo("body").disableSelection()):this.helper=this.element},_change:{e:function(a,b){return{width:this.originalSize.width+b}},w:function(a,b){var c=this.originalSize,d=this.originalPosition;return{left:d.left+b,width:c.width-b}},n:function(a,b,c){var d=this.originalSize,e=this.originalPosition;return{top:e.top+c,height:d.height-c}},s:function(a,b,c){return{height:this.originalSize.height+c}},se:function(a,b,c){return $.extend(this._change.s.apply(this,arguments),this._change.e.apply(this,[a,b,c]))},sw:function(a,b,c){return $.extend(this._change.s.apply(this,arguments),this._change.w.apply(this,[a,b,c]))},ne:function(a,b,c){return $.extend(this._change.n.apply(this,arguments),this._change.e.apply(this,[a,b,c]))},nw:function(a,b,c){return $.extend(this._change.n.apply(this,arguments),this._change.w.apply(this,[a,b,c]))}},_propagate:function(a,b){$.coral.plugin.call(this,a,[b,this.ui()]),"resize"!==a&&this._trigger(a,b,this.ui())},plugins:{},ui:function(){return{originalElement:this.originalElement,element:this.element,helper:this.helper,position:this.position,size:this.size,originalSize:this.originalSize,originalPosition:this.originalPosition}}}),$.coral.plugin.add("resizable","animate",{stop:function(a){var b=$(this).data("coral-resizable"),d=b.options,e=b._proportionallyResizeElements,f=e.length&&/textarea/i.test(e[0].nodeName),g=f&&c(e[0],"left")?0:b.sizeDiff.height,h=f?0:b.sizeDiff.width,i={width:b.size.width-h,height:b.size.height-g},j=parseInt(b.element.css("left"),10)+(b.position.left-b.originalPosition.left)||null,k=parseInt(b.element.css("top"),10)+(b.position.top-b.originalPosition.top)||null;b.element.animate($.extend(i,k&&j?{top:k,left:j}:{}),{duration:d.animateDuration,easing:d.animateEasing,step:function(){var c={width:parseInt(b.element.css("width"),10),height:parseInt(b.element.css("height"),10),top:parseInt(b.element.css("top"),10),left:parseInt(b.element.css("left"),10)};e&&e.length&&$(e[0]).css({width:c.width,height:c.height}),b._updateCache(c),b._propagate("resize",a)}})}}),$.coral.plugin.add("resizable","containment",{start:function(){var b,d,e,f,g,h,i,j=$(this).data("coral-resizable"),k=j.options,l=j.element,m=k.containment,n=m instanceof $?m.get(0):/parent/.test(m)?l.parent().get(0):m;n&&(j.containerElement=$(n),/document/.test(m)||m===document?(j.containerOffset={left:0,top:0},j.containerPosition={left:0,top:0},j.parentData={element:$(document),left:0,top:0,width:$(document).width(),height:$(document).height()||document.body.parentNode.scrollHeight}):(b=$(n),d=[],$(["Top","Right","Left","Bottom"]).each(function(c,e){d[c]=a(b.css("padding"+e))}),j.containerOffset=b.offset(),j.containerPosition=b.position(),j.containerSize={height:b.innerHeight()-d[3],width:b.innerWidth()-d[1]},e=j.containerOffset,f=j.containerSize.height,g=j.containerSize.width,h=c(n,"left")?n.scrollWidth:g,i=c(n)?n.scrollHeight:f,j.parentData={element:n,left:e.left,top:e.top,width:h,height:i}))},resize:function(a){var b,c,d,e,f=$(this).resizable("instance"),g=f.options,h=f.containerOffset,i=f.position,j=f._aspectRatio||a.shiftKey,k={top:0,left:0},l=f.containerElement,m=!0;l[0]!==document&&/static/.test(l.css("position"))&&(k=h),i.left<(f._helper?h.left:0)&&(f.size.width=f.size.width+(f._helper?f.position.left-h.left:f.position.left-k.left),j&&(f.size.height=f.size.width/f.aspectRatio,m=!1),f.position.left=g.helper?h.left:0),i.top<(f._helper?h.top:0)&&(f.size.height=f.size.height+(f._helper?f.position.top-h.top:f.position.top),j&&(f.size.width=f.size.height*f.aspectRatio,m=!1),f.position.top=f._helper?h.top:0),d=f.containerElement.get(0)===f.element.parent().get(0),e=/relative|absolute/.test(f.containerElement.css("position")),d&&e?(f.offset.left=f.parentData.left+f.position.left,f.offset.top=f.parentData.top+f.position.top):(f.offset.left=f.element.offset().left,f.offset.top=f.element.offset().top),b=Math.abs(f.sizeDiff.width+(f._helper?f.offset.left-k.left:f.offset.left-h.left)),c=Math.abs(f.sizeDiff.height+(f._helper?f.offset.top-k.top:f.offset.top-h.top)),b+f.size.width>=f.parentData.width&&(f.size.width=f.parentData.width-b,j&&(f.size.height=f.size.width/f.aspectRatio,m=!1)),c+f.size.height>=f.parentData.height&&(f.size.height=f.parentData.height-c,j&&(f.size.width=f.size.height*f.aspectRatio,m=!1)),m||(f.position.left=f.prevPosition.left,f.position.top=f.prevPosition.top,f.size.width=f.prevSize.width,f.size.height=f.prevSize.height)},stop:function(){var a=$(this).resizable("instance"),b=a.options,c=a.containerOffset,d=a.containerPosition,e=a.containerElement,f=$(a.helper),g=f.offset(),h=f.outerWidth()-a.sizeDiff.width,i=f.outerHeight()-a.sizeDiff.height;a._helper&&!b.animate&&/relative/.test(e.css("position"))&&$(this).css({left:g.left-d.left-c.left,width:h,height:i}),a._helper&&!b.animate&&/static/.test(e.css("position"))&&$(this).css({left:g.left-d.left-c.left,width:h,height:i})}}),$.coral.plugin.add("resizable","alsoResize",{start:function(){var a=$(this).data("coral-resizable"),b=a.options,c=function(a){$(a).each(function(){var a=$(this);a.data("coral-resizable-alsoresize",{width:parseInt(a.width(),10),height:parseInt(a.height(),10),left:parseInt(a.css("left"),10),top:parseInt(a.css("top"),10)})})};"object"!=typeof b.alsoResize||b.alsoResize.parentNode?c(b.alsoResize):b.alsoResize.length?(b.alsoResize=b.alsoResize[0],c(b.alsoResize)):$.each(b.alsoResize,function(a){c(a)})},resize:function(a,b){var c=$(this).data("coral-resizable"),d=c.options,e=c.originalSize,f=c.originalPosition,g={height:c.size.height-e.height||0,width:c.size.width-e.width||0,top:c.position.top-f.top||0,left:c.position.left-f.left||0},h=function(a,c){$(a).each(function(){var a=$(this),d=$(this).data("coral-resizable-alsoresize"),e={},f=c&&c.length?c:a.parents(b.originalElement[0]).length?["width","height"]:["width","height","top","left"],h=$.coral.getStyles(a[0]),i=jQuery.support.boxSizing&&"border-box"===jQuery.css(a,"boxSizing",!1,h);$.each(f,function(b,c){var f=(d[c]||0)+(g[c]||0);f&&f>=0&&(i?$.inArray(c,["width","height"])>=0?a[c](f):a.css(c,f):e[c]=f||null)}),a.css(e)})};"object"!=typeof d.alsoResize||d.alsoResize.nodeType?h(d.alsoResize):$.each(d.alsoResize,function(a,b){h(a,b)})},stop:function(){$(this).removeData("resizable-alsoresize")}}),$.coral.plugin.add("resizable","ghost",{start:function(){var a=$(this).data("coral-resizable"),b=a.options,c=a.size;a.ghost=a.originalElement.clone(),a.ghost.css({opacity:.25,display:"block",position:"relative",height:c.height,width:c.width,margin:0,left:0,top:0}).addClass("coral-resizable-ghost").addClass("string"==typeof b.ghost?b.ghost:""),a.ghost.appendTo(a.helper)},resize:function(){var a=$(this).data("coral-resizable");a.ghost&&a.ghost.css({position:"relative",height:a.size.height,width:a.size.width})},stop:function(){var a=$(this).data("coral-resizable");a.ghost&&a.helper&&a.helper.get(0).removeChild(a.ghost.get(0))}}),$.coral.plugin.add("resizable","grid",{resize:function(){var a,b=$(this).resizable("instance"),c=b.options,d=b.size,e=b.originalSize,f=b.originalPosition,g=b.axis,h="number"==typeof c.grid?[c.grid,c.grid]:c.grid,i=h[0]||1,j=h[1]||1,k=Math.round((d.width-e.width)/i)*i,l=Math.round((d.height-e.height)/j)*j,m=e.width+k,n=e.height+l,o=c.maxWidth&&c.maxWidth<m,p=c.maxHeight&&c.maxHeight<n,q=c.minWidth&&c.minWidth>m,r=c.minHeight&&c.minHeight>n;c.grid=h,q&&(m+=i),r&&(n+=j),o&&(m-=i),p&&(n-=j),/^(se|s|e)$/.test(g)?(b.size.width=m,b.size.height=n):/^(ne)$/.test(g)?(b.size.width=m,b.size.height=n,b.position.top=f.top-l):/^(sw)$/.test(g)?(b.size.width=m,b.size.height=n,b.position.left=f.left-k):((n-j<=0||m-i<=0)&&(a=b._getPaddingPlusBorderDimensions(this)),n-j>0?(b.size.height=n,b.position.top=f.top-l):(n=j-a.height,b.size.height=n,b.position.top=f.top+e.height-n),m-i>0?(b.size.width=m,b.position.left=f.left-k):(m=j-a.height,b.size.width=m,b.position.left=f.left+e.width-m))}})}();