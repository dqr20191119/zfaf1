( function( factory ) {
    if ( typeof define === "function" && define.amd ) {
        define( [
            "jquery"
        ], factory );
    } else {
        factory( jQuery, window, document );
    }
}( function( $, window, document, undefined ) {
//noDefinePart
/**
* Loading plugin for jQuery
* version: v1.0.6
* 
* Small helper to give the user a visual feedback that something is happening 
* when fetching/posting data
* 
* USAGE:
* - global overlay:                     $.loading();
* - use javascript:                     $( selector ).loading();
* - On non-form elements:               $("div").loading({ text: "Loading", position:'inside'});
* - remove the loading element:         $( selector ).loading( "hide" );
*
* @author Laurent Blanes <laurent.blanes@gmail.com>
* ---
* Copyright 2013, Laurent Blanes ( https://github.com/hekigan/is-loading )
* 
* The MIT License (MIT)
* 
* Copyright (c) 2013 Laurent Blanes
* 
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
* 
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
* 
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/
(function () {

    // Create the defaults once
    var pluginName = "loading",
        defaults = {
            'position': "overlay",        // right | inside | overlay
            'text': "",                 // Text to display next to the loader
            'loadingIcon': "coral-icon-loading",    // loader CSS loadingIcon
            'loadingProgress':'',
            'tpl': '<span class="loading-wrapper {wrapper}">{text}<span class="{loadingIcon}"></span><div class="{loadingProgress}"></div></span>',    // loader base Tag
            'disableSource': false,      // true | false
            'disableOthers': [],
            'delay':300,
            'dist':[80,90],
            'speed':[7,8]
        };

    // The actual plugin constructor
    function Plugin(element, options) {
        this.element = element;
        
        // Merge user options with default ones
        this.options = $.extend({}, defaults, options);

        this._defaults     = defaults;
        this._name         = pluginName;
        this._loader       = null;                // Contain the loading tag element

        this.init();
    }

    // Contructor function for the plugin (only once on page load)
    function contruct() {

        if ( !$[pluginName] ) {
            $.loading = function( opts ) {
                $( "body" ).loading( opts );
            };
        }
    }
    
    Plugin.prototype = {
        init: function() {
            if($(this.element).is("body")) {
                this.options.position = "overlay";
            }
            this.isShow = true;
            this.show();
        },

        show: function() {
            var self = this,timer = 0,prg = 0,
                tpl = self.options.tpl.replace( '{wrapper}', ' loading-show ');
	            tpl = tpl.replace('{loadingIcon}', self.options['loadingIcon']);
	            tpl = tpl.replace('{loadingProgress}', self.options['loadingProgress'])
	            tpl = tpl.replace('{text}', (self.options.text !== "") ? self.options.text + ' ' : '' );
            self._loader = $( tpl );
            // Disable the element
            if( $(self.element).is("input, textarea") && true === self.options.disableSource ) {
                $(self.element).attr("disabled", "disabled");
            }
            else if(true === self.options.disableSource) {
                $(self.element).addClass("disabled");
            }
           function start(dist, speed, delay, callback){
            	var bar = $(".ctrl-init-progressbar");
            	var _dist = random(dist);
	          	var _delay = random(delay);
	          	var _speed = random(speed);
	          	var value = bar.progressbar("value");
	          	clearTimeout(timer);
	          	timer = setTimeout(function(){
            	    if (prg + _speed >= _dist) {
            	      clearTimeout(timer);
            	      prg = _dist;
            	      callback && callback();
            	    } else {
            	      prg += _speed;
            	      if( self.isShow == true){
 	            		 bar.progressbar("value", parseInt(prg));// 留意，由于已经不是自增1，所以这里要取整
 	            	 }
            	      start (_dist, speed, delay, callback);
            	    }
            	  }, _delay)
    		};
    		function random (n) {
    			if (typeof n === 'object') {
				    var times = n[1] - n[0];
				    var offset = n[0];
				    return Math.random() * times + offset;
    			} else {
    				return n
    			}
    		};
            // Set position
            switch (self.options.position) {
                case "inside":
                   $( self.element ).html( self._loader );
                   if(self.options['loadingProgress']!=""){
                    	$.parser.parse(  $( self.element ));
                    	start(self.options.dist,self.options.speed,self.options.delay);
                    }
                    break;
                case "overlay":
                    var $wrapperTpl = null;
                    if ($(self.element).is("body")) {
                        $wrapperTpl = $('<div class="coral-loading" style="position:fixed; left:0; top:0; z-index: 10001; width: 100%; height: ' + $(self.element).height() + 'px;" />');
                        $wrapperTpl.prepend("<div class ='coral-component-overlay'/>");
                        $("body").prepend($wrapperTpl);
                        /*$( window ).on('resize', function() {
                            $wrapperTpl.height( $(window).height() + 'px' );
                            self._loader.css({top: ($(window).height()/2 - self._loader.outerHeight()/2) + 'px' });
                        });*/
                    } else {
                        var cssPosition = $(self.element).css('position'),
                            pos = {},
                            height = $(self.element).outerHeight() + 'px',
                            width = '100%'; // $( self.element ).outerWidth() + 'px;

                        if('relative' === cssPosition || 'absolute' === cssPosition || 'fixed' === cssPosition ) {
                            pos = {'top': 0 , 'left': 0};
                        } else {
                            pos = $(self.element).position();
                        }
                        $wrapperTpl = $('<div class="coral-loading" style="position:absolute; top: ' + pos.top + 'px; left: ' + pos.left + 'px; z-index: 10000; width: ' + width + '; height: ' + height + ';" />');
                        $wrapperTpl.prepend("<div style='position:absolute;width:100%;height:100%;'><div class ='coral-component-overlay'style='position:relative;'></div></div>");
                        $(self.element).prepend($wrapperTpl);
                        /*$( window ).on('resize', function() {
                            $wrapperTpl.height( $( self.element ).outerHeight() + 'px' );
                            self._loader.css({top: ($wrapperTpl.outerHeight()/2 - self._loader.outerHeight()/2) + 'px' });
                        });*/
                    }
                    $wrapperTpl.append(self._loader);
                    self._loader.css({
                    	top: ($wrapperTpl.outerHeight()/2 - self._loader.outerHeight()/2) + 'px',
                    	width: self.options.text == "" ? $wrapperTpl.outerWidth()/3 : (self.options['loadingProgress'] != ""? $wrapperTpl.outerWidth()/3 : "auto")
                    });
                    if(self.options['loadingProgress']){   
                    	$.parser.parse(  $( self.element ));
                    	start(self.options.dist,self.options.speed,self.options.delay);
                    }
                    break;
                default:
                    $(self.element).after(self._loader);
	                if(self.options['loadingProgress']){
	                	$.parser.parse(  $( self.element ));
	                	start(self.options.dist,self.options.speed,self.options.delay);
	                }
                    break;
            }
            self.disableOthers();
        },
        refresh: function(){
        	 var $wrapperTpl = null,
        	 	 self = this,
        	 	 cssPosition = $( self.element ).css('position'),
                 pos = {},
                 height = $( self.element ).outerHeight() + 'px',
                 width = '100%'; // $( self.element ).outerWidth() + 'px;
             if( 'relative' === cssPosition || 'absolute' === cssPosition ) {
                 pos = { 'top': 0,  'left': 0 };
             } else {
                 pos = $( self.element ).position();
             }
             $wrapperTpl = $( self.element ).find(".coral-loading");
             $wrapperTpl.css({
            	 top: pos.top,
            	 left: pos.left,
            	 width: width,
            	 height: height
             });
             self._loader.css({
            	 top: ($wrapperTpl.outerHeight()/2 - self._loader.outerHeight()/2) + 'px' 
            });
        },
        hide: function() {
        	var that = this;
            if("overlay" === this.options.position) {
            	if(that.options['loadingProgress'] != ""){
            		$(that.element).find(".coral-loading").find(".ctrl-init-progressbar").progressbar("value",100);
            		setTimeout(function(){
            			$(that.element).find(".coral-loading").first().remove();
            		},150)
            	}else{
            		$(that.element).find(".coral-loading").first().remove();
            	}
            } else {
            	if(self.options['loadingProgress'] != ""){
            		$(that._loader).find(".ctrl-init-progressbar").progressbar("value",100);
                	setTimeout(function(){
                		$(that._loader).remove();
                	},150)
            	}else{
            		$(that._loader).remove();
            	}
                $(that.element).text($(this.element).attr("data-loading-label"));
            }
            $(that.element).removeAttr("disabled").removeClass("disabled");
            that.enableOthers();
            that.isShow = false;
        },
        
        /**
         * TODO: do with coral
         */
        disableOthers: function() {
            $.each(this.options.disableOthers, function(i, e) {
                var elt = $(e);
                if(elt.is( "button, input, textarea")) {
                    elt.attr("disabled", "disabled");
                }
                else {
                    elt.addClass("disabled");
                }
            });
        },
        /**
         * TODO: do with coral
         */
        enableOthers: function() {
            $.each(this.options.disableOthers, function(i, e) {
                var elt = $(e);
                if(elt.is("button, input, textarea")) {
                    elt.removeAttr("disabled");
                }
                else {
                    elt.removeClass("disabled");
                }
            });
        }
    };
    var slice = Array.prototype.slice;
    // Constructor
    $.fn[pluginName] = function (options) {
    	var returnValue = this,
    		args = slice.call(arguments, 1),
    		isMethodCall = typeof options === "string";
        return this.each (function () {
        	if (isMethodCall) {
        		if ($.data(this, "plugin_" + pluginName)) {
        			var methodValue, 
        				instance = $.data( this, "plugin_" + pluginName );
        			if (!$.isFunction( instance[options]) || options.charAt( 0 ) === "_") {
    					return $.error( "no such method '" + options + "' for " + name + " component instance" );
    				}
    				methodValue = instance[options].apply(instance, args);
    				if ( methodValue !== instance && methodValue !== undefined ) {
    					returnValue = methodValue && methodValue.jquery ?
    						returnValue.pushStack(methodValue.get()) :
    						methodValue;
    					return false;
    				}
        		}
        	} else {
        		$.data( this, "plugin_" + pluginName, new Plugin( this, options ) );
        	}
        });
    };
    
    contruct();

})();
// noDefinePart
} ) );