/*! cui 2017-11-03 */
!function(){function a(a,b){this.element=a,this.options=$.extend({},d,b),this._defaults=d,this._name=c,this._loader=null,this.init()}function b(){$[c]||($.loading=function(a){$("body").loading(a)})}var c="loading",d={position:"overlay",text:"",loadingIcon:"coral-icon-loading",loadingProgress:"",tpl:'<span class="loading-wrapper {wrapper}">{text}<span class="{loadingIcon}"></span><div class="{loadingProgress}"></div></span>',disableSource:!1,disableOthers:[],delay:300,dist:[80,90],speed:[7,8]};a.prototype={init:function(){$(this.element).is("body")&&(this.options.position="overlay"),this.isShow=!0,this.show()},show:function(){function a(f,g,h,i){var j=$(".ctrl-init-progressbar"),k=b(f),l=b(h),m=b(g);j.progressbar("value");clearTimeout(d),d=setTimeout(function(){e+m>=k?(clearTimeout(d),e=k,i&&i()):(e+=m,1==c.isShow&&j.progressbar("value",parseInt(e)),a(k,g,h,i))},l)}function b(a){if("object"==typeof a){var b=a[1]-a[0],c=a[0];return Math.random()*b+c}return a}var c=this,d=0,e=0,f=c.options.tpl.replace("{wrapper}"," loading-show ");switch(f=f.replace("{loadingIcon}",c.options.loadingIcon),f=f.replace("{loadingProgress}",c.options.loadingProgress),f=f.replace("{text}",""!==c.options.text?c.options.text+" ":""),c._loader=$(f),$(c.element).is("input, textarea")&&!0===c.options.disableSource?$(c.element).attr("disabled","disabled"):!0===c.options.disableSource&&$(c.element).addClass("disabled"),c.options.position){case"inside":$(c.element).html(c._loader),""!=c.options.loadingProgress&&($.parser.parse($(c.element)),a(c.options.dist,c.options.speed,c.options.delay));break;case"overlay":var g=null;if($(c.element).is("body"))g=$('<div class="coral-loading" style="position:fixed; left:0; top:0; z-index: 10001; width: 100%; height: '+$(c.element).height()+'px;" />'),g.prepend("<div class ='coral-component-overlay'/>"),$("body").prepend(g);else{var h=$(c.element).css("position"),i={},j=$(c.element).outerHeight()+"px",k="100%";i="relative"===h||"absolute"===h||"fixed"===h?{top:0,left:0}:$(c.element).position(),g=$('<div class="coral-loading" style="position:absolute; top: '+i.top+"px; left: "+i.left+"px; z-index: 10000; width: "+k+"; height: "+j+';" />'),g.prepend("<div style='position:absolute;width:100%;height:100%;'><div class ='coral-component-overlay'style='position:relative;'></div></div>"),$(c.element).prepend(g)}g.append(c._loader),c._loader.css({top:g.outerHeight()/2-c._loader.outerHeight()/2+"px",width:""==c.options.text?g.outerWidth()/3:""!=c.options.loadingProgress?g.outerWidth()/3:"auto"}),c.options.loadingProgress&&($.parser.parse($(c.element)),a(c.options.dist,c.options.speed,c.options.delay));break;default:$(c.element).after(c._loader),c.options.loadingProgress&&($.parser.parse($(c.element)),a(c.options.dist,c.options.speed,c.options.delay))}c.disableOthers()},refresh:function(){var a=null,b=this,c=$(b.element).css("position"),d={},e=$(b.element).outerHeight()+"px",f="100%";d="relative"===c||"absolute"===c?{top:0,left:0}:$(b.element).position(),a=$(b.element).find(".coral-loading"),a.css({top:d.top,left:d.left,width:f,height:e}),b._loader.css({top:a.outerHeight()/2-b._loader.outerHeight()/2+"px"})},hide:function(){var a=this;"overlay"===this.options.position?""!=a.options.loadingProgress?($(a.element).find(".coral-loading").find(".ctrl-init-progressbar").progressbar("value",100),setTimeout(function(){$(a.element).find(".coral-loading").first().remove()},150)):$(a.element).find(".coral-loading").first().remove():(""!=self.options.loadingProgress?($(a._loader).find(".ctrl-init-progressbar").progressbar("value",100),setTimeout(function(){$(a._loader).remove()},150)):$(a._loader).remove(),$(a.element).text($(this.element).attr("data-loading-label"))),$(a.element).removeAttr("disabled").removeClass("disabled"),a.enableOthers(),a.isShow=!1},disableOthers:function(){$.each(this.options.disableOthers,function(a,b){var c=$(b);c.is("button, input, textarea")?c.attr("disabled","disabled"):c.addClass("disabled")})},enableOthers:function(){$.each(this.options.disableOthers,function(a,b){var c=$(b);c.is("button, input, textarea")?c.removeAttr("disabled"):c.removeClass("disabled")})}};var e=Array.prototype.slice;$.fn[c]=function(b){var d=this,f=e.call(arguments,1),g="string"==typeof b;return this.each(function(){if(g){if($.data(this,"plugin_"+c)){var e,h=$.data(this,"plugin_"+c);if(!$.isFunction(h[b])||"_"===b.charAt(0))return $.error("no such method '"+b+"' for "+name+" component instance");if(e=h[b].apply(h,f),e!==h&&void 0!==e)return d=e&&e.jquery?d.pushStack(e.get()):e,!1}}else $.data(this,"plugin_"+c,new a(this,b))})},b()}();