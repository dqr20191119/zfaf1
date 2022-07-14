/*! cui 2017-11-03 */
$.component("coral.radio",$.coral.formelement,{version:"4.0.1",castProperties:["triggers","showRequiredMark","hideRequiredMark","shortCut"],options:{showStar:!0,id:null,name:null,width:"auto",height:24,label:"",starBefore:!1,labelField:null,disabled:!1,readonly:!1,isLabel:!1,allowCancel:!1,checked:!1,required:!1,isCheck:!1,value:"",errMsg:null,errMsgPosition:"leftBottom",onValidError:null,onValidSuccess:null,onClick:null,onKeyDown:null,onChange:$.noop,triggers:null,excluded:!1},_create:function(){var a=this,b=this.options;a.element.jquery||(a.element=$(a.element)),a.element.addClass("coral-form-element-radio tabbable"),a.element.addClass("coral-validation-radio"),"undefined"==typeof a.element.attr("id")&&!!a.options.id&&a.element.attr("id",a.options.id),a.options.id=a.element.uniqueId().attr("id");var c=a.element.attr("name");"undefined"!=typeof c?a.options.name=c:a.element.attr("name",a.options.name),""!=$.trim(a.element.val())&&"on"!=$.trim(a.element.val())?a.options.value=a.element.val():a.options.value&&a.element.val(a.options.value),a.nameMark="coral-radio-element-"+a._hashCode(a.options.name),a.element.addClass(a.nameMark),a.uiRadio=$('<span class="coral-radio"></span>'),a.uiLabel=$('<label class="coral-radio-label" for='+a.options.id+"></label>"),a.uiIcon=$('<span class="coral-radio-icon"></span>'),a.uiLabel.append(a.uiIcon),a.options.label&&a.uiLabel.append(a.options.label),a.options.checked?(a._getRadios().not(a.element).radio("uncheck"),a.uiIcon.addClass("cui-icon-radio-checked coral-radio-hightlight"),a.element.prop("checked",!0)):(a.uiIcon.addClass("cui-icon-radio-unchecked"),a.element.prop("checked",!1)),a.element.after(a.uiRadio),a.uiRadio.append(a.element).append(a.uiLabel),b.labelField&&(this.uiLabelField=$('<label class="coral-label">'+b.labelField+"</label>"),this.uiRadio.prepend(this.uiLabelField),this.uiRadio.addClass("coral-hasLabel")),a.element[0].checked===!0&&(a.originalValue=this.getValue()),a._bindEvent()},reset:function(){"off"===this.originalValue||""===this.originalValue?this.uncheck():this.check()},_getRadios:function(){var a=this,b=a.element.closest("form");return b.length>0?b.find($("."+a.nameMark)):$("."+a.nameMark)},_setDisabled:function(a){a?(this.element.prop("disabled",!0),this.element.removeClass("tabbable"),this.uiRadio.addClass("coral-state-disabled")):(this.element.prop("disabled",!1),this.element.addClass("tabbable"),this.uiRadio.removeClass("coral-state-disabled")),this.options.disabled=!!a},_setIsLabel:function(a){a?(this.element.prop("isLabel",!0),this.uiRadio.addClass("coral-isLabel"),this.element.removeClass("tabbable")):(this.element.prop("isLabel",!1),this.uiRadio.removeClass("coral-isLabel"),this.element.addClass("tabbable")),this.options.isLabel=!!a},_setReadonly:function(a){a?(this.element.prop("reaonly",!0),this.uiRadio.addClass("coral-readonly"),this.element.removeClass("tabbable")):(this.element.prop("readonly",!1),this.uiRadio.removeClass("coral-readonly"),this.element.addClass("tabbable")),this.options.readonly=!!a},focus:function(){return!(this.options.disabled||this.options.readonly||this.options.isLabel)&&(this.element.focus(),!0)},_bindEvent:function(){var a=this,b=a.options;this.options.disabled&&this._setDisabled(this.options.disabled),this.options.readonly&&this._setReadonly(this.options.readonly),this.options.isLabel&&this._setIsLabel(this.options.isLabel);var c;this.element.bind("focus",function(){a.uiRadio.addClass("coral-radio-highlight")}).bind("blur",function(){a.uiRadio.removeClass("coral-radio-highlight")}).bind("click",function(b){if(!(a.options.disabled||a.options.readonly||a.options.isLabel)){if(c)return void(c=!1);a._clearCheckedState(),a.uiIcon.removeClass("cui-icon-radio-unchecked"),a.uiIcon.addClass("cui-icon-radio-checked coral-radio-hightlight"),a.element.prop("checked",!0),a._trigger("onChange",null,[{checked:!!a.element.prop("checked")}]),a._trigger("onClick",null,{checked:!!a.element.prop("checked")})}}).bind("keydown"+this.eventNamespace,function(c){if(a.options.allowCancel){var d=$.coral.keyCode;switch(c.keyCode){case d.SPACE:c.preventDefault(),a._selectItem(c)}}b.shortCut&&$.coral.callFunction(b.shortCut,event,this),a._trigger("onKeyDown",c,{})}),this.uiRadio.bind("mouseenter"+this.eventNamespace,function(){a.options.disabled||$(this).addClass("coral-radio-hover")}).bind("mouseleave"+this.eventNamespace,function(){a.options.disabled||$(this).removeClass("coral-radio-hover")}),this.uiLabel.bind("click",function(b){a._selectItem(b)})},_selectItem:function(a){var b=this;if(b.options.readonly||b.options.disabled||b.options.isLabel)return!1;if(suppressClick=!0,b.options.isCheck=$(b.element).prop("checked"),b.options.isCheck){if(!b.options.allowCancel)return;b._clearCheckedState(),b.uiIcon.addClass("cui-icon-radio-unchecked"),b.element.prop("checked",!1),b._trigger("onChange",null,[{checked:!!b.element.prop("checked")}])}else b._clearCheckedState(),b.uiIcon.removeClass("cui-icon-radio-unchecked"),b.uiIcon.addClass("cui-icon-radio-checked"),b.element.prop("checked",!0),b._trigger("onChange",null,[{checked:!!b.element.prop("checked")}])},clear:function(){},_clearCheckedState:function(){this._getRadios().each(function(){$(this).radio("component").find(".coral-radio-icon").removeClass("cui-icon-radio-checked coral-radio-hightlight").addClass(" cui-icon-radio-unchecked"),$(this).prop("checked",!1)})},_hashCode:function(a){if(!a)return 0;a=""+a;for(var b=0,c=0,d=a.length,e=0;e<d;e++)b=31*b+a.charCodeAt(c++),(b>2147483647||b<2147483648)&&(b=4294967295&b);return b},_setOption:function(a,b){"id"!==a&&"name"!==a&&("readonly"===a&&this._setReadonly(b),"isLabel"===a&&this._setIsLabel(b),"disabled"===a&&this._setDisabled(b),this._super(a,b))},_destroy:function(){this.uiRadio.replaceWith(this.element),this.uncheck(),this.element.removeClass("coral-form-element-radio"),this.element.removeClass("coral-validation-radio"),this.element.removeClass(this.nameMark)},component:function(){return this.uiRadio},disable:function(){this._setDisabled(!0)},enable:function(){this._setDisabled(!1)},show:function(){this.component().show()},hide:function(){this.component().hide()},check:function(){var a=this;this.uiIcon.hasClass("cui-icon-radio-unchecked")&&(a._getRadios().each(function(){$(this).radio("component").find(".coral-radio-icon").removeClass("cui-icon-radio-checked coral-radio-hightlight").addClass(" cui-icon-radio-unchecked")}),this.uiIcon.removeClass("cui-icon-radio-unchecked").addClass("cui-icon-radio-checked coral-radio-hightlight"),this.element.prop("checked",!0))},uncheck:function(){this.uiIcon.hasClass("coral-radio-hightlight")&&(this.uiIcon.removeClass("cui-icon-radio-checked coral-radio-hightlight").addClass("cui-icon-radio-unchecked"),this.element.prop("checked",!1))},isChecked:function(){return this.element.prop("checked")},setValue:function(a){"on"===a?this.check():this.uncheck()},getValue:function(){return this.getValues().join(",")},getValues:function(){var a=this,b=[];return a._getRadios().each(function(){var a=$(this);a.radio("isChecked")&&b.push(a.val())}),b},refresh:function(){this._destroy(),this._create()}});