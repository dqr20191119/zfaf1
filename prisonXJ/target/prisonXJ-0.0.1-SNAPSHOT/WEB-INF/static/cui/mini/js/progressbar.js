/*! cui 2017-11-03 */
$.component("coral.progressbar",{version:"4.0.1",options:{id:null,name:null,max:100,value:0,text:"{value}%",onChange:null,onComplete:null},min:0,_create:function(){this.oldValue=this.options.value=this._constrainedValue(),this.element.addClass("coral-progressbar coral-component coral-corner-all"),this.valueDiv=$('<div class="coral-progressbar-value coral-corner-left"></div>').appendTo(this.element),this.textDiv=$('<div class="coral-progressbar-text"></div>').appendTo(this.valueDiv),this.valueWidth=this.element.width()-(this.valueDiv.outerWidth(!0)-this.valueDiv.outerWidth()),this._refreshValue()},_destroy:function(){this.element.removeClass("coral-progressbar coral-component coral-component-content coral-corner-all"),this.valueDiv.remove()},value:function(a){return void 0===a?this.options.value:(this.options.value=this._constrainedValue(a),void this._refreshValue())},_constrainedValue:function(a){return void 0===a&&(a=this.options.value),this.indeterminate=a===!1,"number"!=typeof a&&(a=0),!this.indeterminate&&Math.min(this.options.max,Math.max(this.min,a))},_setOptions:function(a){var b=a.value;delete a.value,this._super(a),this.options.value=this._constrainedValue(b),this._refreshValue()},_setOption:function(a,b){"max"===a&&(b=Math.max(this.min,b)),"disabled"===a&&this.element.toggleClass("coral-state-disabled",!!b).attr("aria-disabled",b),this._super(a,b)},_percentage:function(){return this.indeterminate?100:100*(this.options.value-this.min)/(this.options.max-this.min)},_refreshValue:function(){var a=this.options.value,b=this._percentage().toFixed(0),c=this.options.text.replace(/{value}/,b),d=0,e=0;this.valueDiv.toggle(this.indeterminate||a>this.min).toggleClass("coral-corner-right",a===this.options.max).width(this.valueWidth*b/100),100==b&&this.valueDiv.width("auto"),this.textDiv.html(c),d=this.element.position().left+(this.element.outerWidth()-this.textDiv.outerWidth())/2,e=this.element.position().top+(this.element.outerHeight()-this.textDiv.outerHeight())/2,this.textDiv.position({of:this.element,my:d+" "+e,at:d+" "+e}),this.element.toggleClass("coral-progressbar-indeterminate",this.indeterminate),this.indeterminate?this.overlayDiv||(this.overlayDiv=$("<div class='coral-progressbar-overlay'></div>").appendTo(this.valueDiv)):this.overlayDiv&&(this.overlayDiv.remove(),this.overlayDiv=null),this.oldValue!==a&&(this.oldValue=a,this._trigger("onChange",null,{value:a,oldValue:this.oldValue})),a===this.options.max&&this._trigger("onComplete")}});