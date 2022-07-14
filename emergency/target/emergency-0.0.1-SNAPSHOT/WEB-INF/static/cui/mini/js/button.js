/*! cui 2017-11-03 */
!function(){var a,b="coral-button coral-component coral-state-default coral-corner-all",c="coral-button-text-icons coral-button-text-icon-primary coral-button-text-icon-secondary",d=function(){var a=$(this);setTimeout(function(){a.find(":coral-button").button("refresh")},1)},e=function(a){var b=a.name,c=a.form,d=$([]);return b&&(b=b.replace(/'/g,"\\'"),d=c?$(c).find("[name='"+b+"'][type=radio]"):$("[name='"+b+"'][type=radio]",a.ownerDocument).filter(function(){return!this.form})),d};$.component("coral.button",{version:"4.0.1",defaultElement:"<button>",castProperties:["dataCustom","shortCut"],options:{id:null,name:null,cls:null,title:!1,disabled:null,text:!0,label:null,icons:{primary:null,secondary:null},type:"button",width:null,countdown:!1,countdownTime:3e3,showCountdown:!0,onCreate:null,onClick:null,onDblClick:null,onMouseEnter:null,onMouseLeave:null},_create:function(){this.options.icons=this._icons(),this.element.addClass("ctrl-init ctrl-init-button"),this.element.closest("form").unbind("reset"+this.eventNamespace).bind("reset"+this.eventNamespace,d),"boolean"!=typeof this.options.disabled?this.options.disabled=!!this.element.prop("disabled"):this.element.prop("disabled",this.options.disabled),this._determineButtonType(),this.hasTitle=!!this.buttonElement.attr("title")||this.options.title;var c=this,f=this.options,g="checkbox"===this.type||"radio"===this.type,h=g?"":"coral-state-active";f.id&&this.element.attr("id",f.id),f.name&&this.element.attr("name",f.name),null===f.label&&(f.label="input"===this.type?this.buttonElement.val():this.buttonElement.html()),this._hoverable(this.buttonElement),this.buttonElement.addClass(b).attr("role","button").bind("mouseenter"+this.eventNamespace,function(){f.disabled||(this===a&&$(this).addClass("coral-state-active"),c._trigger("onMouseEnter",null,{id:this.id,label:c.options.label}))}).bind("mouseleave"+this.eventNamespace,function(){f.disabled||($(this).removeClass(h),c._trigger("onMouseLeave",null,{id:this.id,label:c.options.label}))}).bind("click"+this.eventNamespace,function(a){function b(){0!==$("body").find(c.element).length&&(e<=0?(c.element.button("enable"),c.element.button("option","label",d)):(setTimeout(function(){0!==$("body").find(c.element).length&&c.element.button("option","label",d+"("+e+")")},0),e--,setTimeout(function(){b()},1e3)))}if(f.disabled)a.preventDefault(),a.stopImmediatePropagation();else{if(f.once&&c.element.button("disable"),f.countdown){var d,e=f.countdownTime/1e3;0!==$("body").find(c.element).length&&(d=c.element.button("option","label")),$(document).find(c.element).length&&(c.element.button("disable"),f.showCountdown?b():c._delay(function(){c.element.button("enable"),c.element.button("option","label",d)},f.countdownTime))}c._trigger("onClick",null,{id:this.id,label:c.options.label})}}).bind("dblclick"+this.eventNamespace,function(a){f.disabled?(a.preventDefault(),a.stopImmediatePropagation()):c._trigger("onDblClick",null,{id:this.id,label:c.options.label})}),this._on({focus:function(){this.buttonElement.addClass("coral-state-focus")},blur:function(){this.buttonElement.removeClass("coral-state-focus")}}),g&&this.element.bind("change"+this.eventNamespace,function(){c.refresh()}),"checkbox"===this.type?this.buttonElement.bind("click"+this.eventNamespace,function(){if(f.disabled)return!1}):"radio"===this.type?this.buttonElement.bind("click"+this.eventNamespace,function(){if(f.disabled)return!1;$(this).addClass("coral-state-active"),c.buttonElement.attr("aria-pressed","true");var a=c.element[0];e(a).not(a).map(function(){return $(this).button("component")[0]}).removeClass("coral-state-active").attr("aria-pressed","false")}):(this.buttonElement.bind("mousedown"+this.eventNamespace,function(){return!f.disabled&&($(this).addClass("coral-state-active"),a=this,void c.document.one("mouseup",function(){a=null}))}).bind("mouseup"+this.eventNamespace,function(){return!f.disabled&&void $(this).removeClass("coral-state-active")}).bind("keydown"+this.eventNamespace,function(a){var b=$.coral.keyCode;return!f.disabled&&(a.keyCode!==b.SPACE&&a.keyCode!==b.ENTER||$(this).addClass("coral-state-active"),f.shortCut&&$.coral.callFunction(f.shortCut,a,this),void c._trigger("onKeyDown"))}).bind("keyup"+this.eventNamespace+" blur"+this.eventNamespace,function(){$(this).removeClass("coral-state-active")}),this.buttonElement.is("a")&&this.buttonElement.keyup(function(a){a.keyCode===$.coral.keyCode.SPACE&&$(this).click()})),f.width&&this.buttonElement.outerWidth(f.width),this._setOption("disabled",f.disabled),this._resetButton()},_icons:function(){function a(a,b,c){var d=b.split(" ");return $.inArray(a,d)>-1?(d=$.grep(d,function(b,c){return b!==a}),d.join(" ")):c?b:null}var b=this.options.icons,c=null,d=null,e=null,f=null;return b&&"string"==typeof b?(c=b.split(","),c.length>0&&(d=$.trim(c[0]),f=a("right",d),null===f&&(e=a("left",d,!0))),c.length>1&&(d=$.trim(c[1]),e=null===e?a("left",d):e,null===f&&(f=a("right",d,!0))),{primary:e,secondary:f}):b},_determineButtonType:function(){var a,b,c;this.element.is("[type=checkbox]")?this.type="checkbox":this.element.is("[type=radio]")?this.type="radio":this.element.is("input")?this.type="input":this.type="button","checkbox"===this.type||"radio"===this.type?(a=this.element.parents().last(),b="label[for='"+this.element.attr("id")+"']",this.buttonElement=a.find(b),this.buttonElement.length||(a=a.length?a.siblings():this.element.siblings(),this.buttonElement=a.filter(b),this.buttonElement.length||(this.buttonElement=a.find(b))),this.element.addClass("coral-helper-hidden-accessible"),c=this.element.is(":checked"),c&&this.buttonElement.addClass("coral-state-active"),this.buttonElement.prop("aria-pressed",c)):this.buttonElement=this.element},component:function(){return this.buttonElement},_destroy:function(){this.element.removeClass("coral-helper-hidden-accessible"),this.buttonElement.removeClass(b+" ui-state-active "+c).removeAttr("role").removeAttr("aria-pressed").html(this.buttonElement.find(".coral-button-text").html()),this.hasTitle||this.buttonElement.removeAttr("title")},_setOption:function(a,b){return this._super(a,b),"disabled"===a?(this.component().toggleClass("coral-state-disabled",!!b),this.element.prop("disabled",!!b),void(b&&("checkbox"===this.type||"radio"===this.type?this.buttonElement.removeClass("coral-state-focus"):this.buttonElement.removeClass("coral-state-focus coral-state-active")))):void this._resetButton()},update:function(a){"string"==typeof a&&this.element.find(".coral-button-text").html(a)},refresh:function(){var a=this.element.is("input, button")?this.element.is(":disabled"):this.element.hasClass("coral-button-disabled");a!==this.options.disabled&&this._setOption("disabled",a),"radio"===this.type?e(this.element[0]).each(function(){$(this).is(":checked")?$(this).button("component").addClass("coral-state-active").attr("aria-pressed","true"):$(this).button("component").removeClass("coral-state-active").attr("aria-pressed","false")}):"checkbox"===this.type&&(this.element.is(":checked")?this.buttonElement.addClass("coral-state-active").attr("aria-pressed","true"):this.buttonElement.removeClass("coral-state-active").attr("aria-pressed","false"))},_resetButton:function(){if("input"===this.type)return void(this.options.label&&this.element.val(this.options.label));var a=this.buttonElement.removeClass(c),b=$("<span></span>",this.document[0]).addClass("coral-button-text").html(this.options.label).appendTo(a.empty()).text(),d=this._icons(),e=d.primary&&d.secondary,f=[];d.primary||d.secondary?(this.options.text&&f.push("coral-button-text-icon"+(e?"s":d.primary?"-primary":"-secondary")),d.primary&&a.prepend("<span class='coral-button-icon-primary icon "+d.primary+"'></span>"),d.secondary&&a.append("<span class='coral-button-icon-secondary icon "+d.secondary+"'></span>"),this.options.text||(f.push(e?"coral-button-icons-only":"coral-button-icon-only"),this.hasTitle||a.attr("title",$.trim(b)))):f.push("coral-button-text-only"),this.hasTitle&&a.attr("title",$.trim(b)),a.addClass(f.join(" "))}}),$.component("coral.buttonset",{version:"4.0.1",options:{items:"button, input[type=button], input[type=submit], input[type=reset], input[type=checkbox], input[type=radio], a, :data(coral-button)"},_create:function(){this.element.addClass("coral-buttonset")},_init:function(){this.refresh()},_setOption:function(a,b){"disabled"===a&&this.buttons.button("option",a,b),"label"===a&&this.update(b),this._super(a,b)},refresh:function(){var a="rtl"===this.element.css("direction"),b=this.element.find(this.options.items),c=b.filter(":coral-button");b.not(":coral-button").button(),c.button("refresh"),this.buttons=b.map(function(){return $(this).button("component")[0]}).removeClass("coral-corner-all coral-corner-left coral-corner-right").filter(":first").addClass(a?"coral-corner-right":"coral-corner-left").end().filter(":last").addClass(a?"coral-corner-left":"coral-corner-right").end().end()},_destroy:function(){this.element.removeClass("coral-buttonset"),this.buttons.map(function(){return $(this).button("component")[0]}).removeClass("coral-corner-left coral-corner-right").end().button("destroy")}})}();