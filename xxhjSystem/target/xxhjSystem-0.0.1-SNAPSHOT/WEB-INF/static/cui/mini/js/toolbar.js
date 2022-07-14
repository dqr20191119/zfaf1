/*! cui 2017-11-03 */
$.component("coral.toolbar",{version:"4.0.2",castProperties:["data","dataCustom","dropdownOptions"],options:{clickToDisplay:1,id:null,name:null,dataCustom:{},responsive:!0,disabled:!1,cls:null,url:null,title:!1,method:"get",data:null,width:"auto",height:null,isOverflow:!0,dropdownOptions:{button:{text:!1,label:"更多"},panelPosition:{my:"right top",at:"right bottom"},atGroup:0},align:0,autoDisplay:!1,margin:5,onCreate:null,onClick:null,onLoad:null},_jion:function(a,b){var c=[];return $.each(a,function(a,b){c.push($(b))}),$.each(b,function(a,b){c.push($(b))}),c},_hideMenus:function(){$(".coral-tieredmenu").hide()},_create:function(){this.isLoaded=!1,this.groupLength=1,this._initElements(),this._bindEvents()},_bindEvents:function(){var a=this;this._on(this.document,{mousedown:function(b){1==a.options.clickToDisplay&&a.options.autoDisplay&&a.uiBox.toggleClass("coral-toolbar-click-active",!1)}})},_initElements:function(){this.options;this.uiBox=$('<div class="coral-toolbar"></div>'),this.uiBorder=$('<div class="coral-toolbar-border"></div>'),this.uiBox.append(this.uiBorder),this.uiBox.insertAfter(this.element),this.element.appendTo(this.uiBorder),"undefined"!=typeof this.element.attr("id")?this.options.id=this.element.attr("id"):this.options.id&&this.element.attr("id",this.options.id),"undefined"!=typeof this.element.attr("name")?this.options.name=this.element.attr("name"):this.options.name&&this.element.attr("name",this.options.name),this.uiAfter=$("<button type='button' data-frozen='true' class='coral-toolbar-after-element ctrl-toolbar-element'></button>").menubutton({label:this.options.dropdownOptions.button.label,text:this.options.dropdownOptions.button.text,renderType:"button",icons:"cui-icon-arrow-down3 right",data:[],position:this.options.dropdownOptions.panelPosition}),this.uiAfter.menubutton("component").addClass("coral-toolbar-item coral-toolbar-after"),this.uiAfter.menubutton("hide"),this._loadData(),this.options.width&&this.uiBox.css({width:this.options.width}),0==this.options.clickToDisplay&&this.uiBox.addClass("coral-toolbar-click-active")},reload:function(a){this.isLoaded=!1,this.groupLength=1;var b=this.options;"string"!=typeof a?b.data=a:b.url=a,this.element.html(""),this.uiAfter=$("<button type='button' data-frozen='true' class='coral-toolbar-after-element ctrl-toolbar-element'></button>").menubutton({label:this.options.dropdownOptions.button.label,text:this.options.dropdownOptions.button.text,renderType:"button",icons:"cui-icon-arrow-down3 right",data:[],position:this.options.dropdownOptions.panelPosition}),this.uiAfter.menubutton("component").addClass("coral-toolbar-item coral-toolbar-after"),this.uiAfter.menubutton("hide"),this._loadData()},_loadData:function(){var a=this,b=this.options;b.url?$.ajax({type:b.method,url:b.url,data:{},dataType:"json",success:function(b){a._initData(b)},error:function(){$.alert("Json Format Error!")}}):b.data&&this._initData(b.data)},_initData:function(a){"object"==typeof a&&(this._addItems(null,a),this._trigger("onLoad",null,{}),this.isLoaded=!0,this._setFrozenElements(),this._position()),this.options.disabled&&this._setDisabled(this.options.disabled)},_addItems:function(a,b,c){if("object"==typeof b){for(var d=this,e=[],f=b.length,g=0;g<f;g++){var h=b[g];""!=h&&$.isEmptyObject(h)||e.push(d._createItem(h))}if(this._appendItems(a,e,c),this._initItems(e),!this.element.find(".coral-toolbar-after-element").length){var i=this._getGroupNameByIndex(this.options.dropdownOptions.atGroup),j=this.element.find("[group-role='"+i+"']");j.length?j.after(this.uiAfter.menubutton("component")):this.element.append(this.uiAfter.menubutton("component"))}}},_createItem:function(a){var b=a.type||"button",c=null;"-"===a?b="seperator":"->"===a?b="grouper":""===a?b="blank":"more"===a&&(b="more");var d=this._createEl(b);if(c=a,"button"===b&&"undefined"!=typeof a.icon&&""!=a.icon){var e=this._getIcon(a.icon);delete c.icon,null!=e.ico1&&null==e.ico2?c.icons=e.ico1:null==e.ico1&&null!=e.ico2?c.icons=e.ico2+" right":null!=e.ico1&&null!=e.ico2&&(c.icons=e.ico1+" left, "+e.ico2+" right")}return{$el:d,coralType:b,options:c}},_align:function(){if(1==this.groupLength){var a=this.options;if("center"==a.align){var b=this._createEl("grouper").attr("grouper-role","center"),c=this._createEl("grouper").attr("grouper-role","right");this.element.prepend(b),this.element.find("[group-role='left']").removeClass("group-left").addClass("group-center").attr("group-role","center"),this.element.find(".ctrl-toolbar-element").attr("group","center"),this.element.append(c)}else if("right"==a.align){var c=this._createEl("grouper").attr("grouper-role","right");this.element.find("[group-role='left']").removeClass("group-left").addClass("group-right").attr("group-role","right"),this.element.find(".ctrl-toolbar-element").attr("group","right"),this.element.prepend(c)}}},_appendItems:function(a,b,c){var d=this;if(this.isLoaded){var e=this._getGroupNameByIndex(c),f=this.element.find("[group-role='"+e+"']"),g=f.find(".ctrl-toolbar-element:not(.coral-toolbar-separator)").length;if(null==a||a==g)for(var h in b)b[h].$el.attr("group",e),b[h].$el.appendTo(f);else if(0==a)for(var i in b)b[i].$el.attr("group",e),b[i].$el.prependTo(f);else for(var j in b){b[j].$el.attr("group",e);var k=f.find(".ctrl-toolbar-element:eq("+a+")"),l=k.attr("component-role");l?k[l]("component").before(b[j].$el):k.before(b[j].$el)}}else{var m=0,n=d._createGrouper(m,this.groupLength);this.element.append(n),$.each(b,function(a,b){"grouper"==b.coralType?(m+=1,n=d._createGrouper(m,d.groupLength),d.element.append(b.$el.attr("grouper-role",n.attr("group-role"))),d.element.append(n)):(n.append(b.$el.attr("group",n.attr("group-role"))),b.options&&1==b.options.frozen&&b.$el.attr("data-frozen",!0))}),this._align()}},_initItems:function(a){var b=this;for(var c in a)if("seperator"!==a[c].coralType&&"grouper"!==a[c].coralType&&"blank"!==a[c].coralType&&"more"!==a[c].coralType){var d=a[c].$el,e=a[c].coralType.toLowerCase(),f=a[c].options;f.componentCls=" coral-toolbar-item "+f.componentCls||"","html"!==a[c].coralType?(b.options.title&&(f.title=!0),d[e](f),d.off(".toolbaronclick").on(e+"onclick.toolbaronclick",function(a,c){if(c=c||{},c.id=c.id||a.currentTarget.id,"splitbutton"==$(a.currentTarget).attr("component-role"),b.uiAfter.length){var d=$(a.currentTarget).attr("component-role");"splitbutton"!=d&&"button"!=d||b.uiAfter.menubutton("hidePanel")}b._trigger("onClick",a,c)}),d.off(".toolbaronmouseenter").on(e+"onmouseenter.toolbaronmouseenter",function(a,c){var d=$(a.currentTarget).attr("component-role");"splitbutton"!=d&&"menubutton"!=d||0==b.options.clickToDisplay&&b.options.autoDisplay&&($(a.currentTarget)[d]("hideAllMenus"),$(a.currentTarget)[d]("showMenu"))})):d.attr("id",f.id).append(f.content)}},_createEl:function(a){var b;switch(a){case"button":b=$("<button type='button'></button>");break;case"checkbox":b=$("<input type='checkbox' />");break;case"radio":b=$("<input type='radio' />");break;case"textbox":case"combobox":b=$("<input type='text' />");break;case"datepicker":b=$("<input type='text' />");break;case"splitbutton":case"menubutton":b=$("<button type='button'></button>");break;case"seperator":b=$("<div class='coral-toolbar-item coral-toolbar-separator coral-toolbar-separator-horizontal'></div>");break;case"grouper":this.groupLength+=1,b=$("<div class='coral-toolbar-item coral-toolbar-grouper'></div>");break;case"html":b=$("<div class='coral-toolbar-item coral-toolbar-html'></div>");break;case"blank":b=$("<span class='coral-toolbar-item coral-toolbar-blank'></span>");break;case"more":return b=this.uiAfter.menubutton("component");default:b=$("<span class='coral-toolbar-item'></span>")}return b.addClass("ctrl-toolbar-element")},_createGrouper:function(a,b){var c=$();if(1==b||3==b)switch(a){case 0:c=$("<span class='coral-toolbar-group group-left' group-role='left'></span>");break;case 1:c=$("<span class='coral-toolbar-group group-center' group-role='center'></span>");break;case 2:c=$("<span class='coral-toolbar-group group-right' group-role='right'></span>");break;default:c=$("<span class='coral-toolbar-group' group-role='default'></span>")}else if(2==b)switch(a){case 0:c=$("<span class='coral-toolbar-group group-left' group-role='left'></span>");break;case 1:c=$("<span class='coral-toolbar-group group-right' group-role='right'></span>");break;default:c=$("<span class='coral-toolbar-group' group-role='default'></span>")}return c},_getGroupNameByIndex:function(a){if("number"!=typeof a)return"left";if(1==this.groupLength)return"left";if(2==this.groupLength)return 0==a?"left":"right";if(3==this.groupLength)switch(a){case 0:return"left";case 1:return"center";case 2:return"right"}},_getGrouper:function(){return this.element.find(".coral-toolbar-grouper")},_resetGrouper:function(){var a=this._getGrouper();a.length&&a.width(1)},_resetToolbarItems:function(){var a=this,b=this._getElements(2);this._resetGrouper(),$.each(b,function(b,c){var d=$(c),e=a._getComponentByElement(d);if(e.css({left:""}),e.hasClass("coral-menubutton-button-item")){var f=d.attr("group"),g=a.element.find("[group-role='"+f+"']");if(g.find("[data-frozen='true']").length){var h=g.find("[data-frozen='true']:eq(0)"),i=a._getComponentByElement(h);i.before(e.removeClass("coral-menubutton-button-item"))}else g.append(e.removeClass("coral-menubutton-button-item"))}}),this.uiAfter.menubutton("hidePanel"),this.uiAfter.menubutton("hide"),this.uiBorder.css({width:"auto"})},_position:function(){if(!this.element.is(":visible")||!this.options.responsive)return this._resetToolbarItems(),void this.component().addClass("coral-toolbar-initHidden");this.component().removeClass("coral-toolbar-initHidden");var a=this.options;this._resetGrouper(),this.totalWidth=this._totalWidth(),a.isOverflow&&this.uiBox.width()>0?this.uiBorder.width(this.uiBox.width()):this.uiBorder.width(Math.max(this.totalWidth,this.uiBox.width())),this._toolbarWidth()-this.totalWidth<0?this.uiAfter.menubutton("show"):this.uiAfter.menubutton("hide"),this._positionItems(this.element)},_positionItems:function(a){var b=(this.options,this.options.margin),c=!1,d=0,e=$(),f={right:"auto"};this._prePosition(),this.element.find(".coral-toolbar-item:not(.coral-state-hidden)").each(function(a,g){var h=$(g);return!!c||(e.length&&(d=d+e.outerWidth()+b),f.left=d+"px",h.css(f),void(e=h))}),this._handlerDropdownItems()},_handlerDropdownItems:function(){var a=this,b=this._filter(this._getElements(0),".ctrl-init-splitbutton,.ctrl-init-menubutton");a._hideMenus(),$.each(b,function(b,c){var d=$(c),e=a._getComponentByElement(d);try{switch(d.attr("component-role")){case"splitbutton":e.hasClass("coral-menubutton-button-item")?d.splitbutton("menu").tieredmenu("option",{my:"left top",at:"right top",of:d.splitbutton("uiDropdownButton")}):d.splitbutton("menu").tieredmenu("option",{my:"left top",at:"left bottom",of:d});break;case"menubutton":e.hasClass("coral-menubutton-button-item")?d.menubutton("menu").tieredmenu("option",{my:"left top",at:"right top"}):d.menubutton("menu").tieredmenu("option",{my:"left top",at:"left bottom"})}}catch(f){}finally{}})},_getComponentByElement:function(a){var b=$(),c=a.attr("component-role");return b=c?a[c]("component"):a},_filter:function(a,b){var c=[];return $.each(a,function(a,d){var e=$(d);e.is(b)&&c.push(e)}),c},_prePosition:function(){var a=this,b=(this.options,this.options.margin),c=0,d=!1,e=[],f=$(),g=[];g=this.uiAfter.is(":visible")?this._getElements(3):this._getElements(2);var g=this._jion(a._getFrozenElements(),a._filter(g,":not([data-frozen='true'])"));$.each(g,function(g,h){var i=a._getComponentByElement($(h)),j=!i.hasClass("coral-menubutton-button-item");if(d)return!j||(!(!i.hasClass("coral-toolbar-grouper")&&"true"!=$(h).attr("data-frozen"))||(e.push(i),!0));if(f.length&&f.is(":visible")&&(c=c+f.outerWidth()+b),!j){var k=a.element.find("[group-role='"+$(h).attr("group")+"']");if(k.attr("group-role")==$(h).attr("group")&&k.find("[data-frozen='true']").length){var l=k.find("[data-frozen='true']:eq(0)");l.attr("component-role")?l[l.attr("component-role")]("component").before(i.removeClass("coral-menubutton-button-item")):l.before(i.removeClass("coral-menubutton-button-item"))}else i.removeClass("coral-menubutton-button-item").appendTo(k)}return c+i.outerWidth()>a._toolbarWidth()-17?(d=!0,!(!i.hasClass("coral-toolbar-grouper")&&"true"!=$(h).attr("data-frozen"))||(j?e.push(i):a.uiAfter.menubutton("prepend",i),!0)):void(f=i)}),this.isLoaded?a.uiAfter.menubutton("prepend",e):a.uiAfter.menubutton("append",e),this._setGrouperWidth()},_setGrouperWidth:function(){var a=this._getGrouper(),b=this._toolbarWidth()-this._totalWidth(!0,this.uiAfter.is(":visible"))-this.options.margin,c=this._getWidthByGroupRole("left"),d=this._getWidthByGroupRole("right"),e=(b-c+d)/2,f=b-e;return b<0?void a.width(1):void(1==a.length?a.width(b):2==a.length&&(e>0&&f>0&&!this.uiAfter.is(":visible")?($(a[0]).width(e+1),$(a[1]).width(f+1)):($(a[1]).width(b+1),$(a[0]).width(1))))},_getWidthByGroupRole:function(a){var b=this.options,c=0,d=this.element.find("[group-role='"+a+"']"),e=d.find(".coral-toolbar-item");return d.length&&e.length?($.each(e,function(a,d){var f=$(d);c+=f.outerWidth()+b.margin,a==e.length-1&&(c-=b.margin)}),c):0},_toolbarWidth:function(){return this.uiBorder.innerWidth()},_setFrozenElements:function(){var a=this._getElements(3),b=!1;$.each(a,function(a,c){var d=$(c);d.hasClass("coral-toolbar-after-element")&&(b=!0),b&&!d.hasClass("coral-toolbar-grouper")&&d.attr("data-frozen",!0)})},_getFrozenElements:function(){return this.uiAfter.is(":visible")?this.element.find("[data-frozen='true']"):this.element.find("[data-frozen='true']:not(.coral-toolbar-after-element)")},_getElements:function(a){var b=$(),c=this.element.find(".ctrl-toolbar-element"),d=this.uiAfter.menubutton("buttons").find(".ctrl-toolbar-element");switch(b=this._jion(c,d),a){case 0:b=this._filter(b,":not(.coral-toolbar-after-element,.coral-toolbar-grouper,.coral-toolbar-separator)");break;case 1:b=this._filter(b,":not(.coral-toolbar-after-element,.coral-toolbar-grouper)");break;case 2:b=this._filter(b,":not(.coral-toolbar-after-element)");break;case 3:}return b},_getComponents:function(a,b){var c=$(),d=this.element.find(".coral-toolbar-item"),e=this.uiAfter.menubutton("buttonElements");switch(c=this._jion(d,e),a=a||1){case 0:c=this._filter(c,":not(.coral-toolbar-after,.coral-toolbar-grouper,.coral-toolbar-separator)");break;case 1:c=this._filter(c,":not(.coral-toolbar-after,.coral-toolbar-grouper)");break;case 2:c=this._filter(c,":not(.coral-toolbar-after)");break;case 3:}return b||($itemAll=this._filter(c,".coral-state-hidden")),c},getTotalWidth:function(a){return this._totalWidth(a)},_totalWidth:function(a,b){var c=this.options,d=0,e=$();e=b?this.element.find(".coral-toolbar-item:not(.coral-state-hidden)"):this.element.find(".coral-toolbar-item:not(.coral-state-hidden,.coral-toolbar-after)");var f=this.uiAfter.menubutton("buttons").find(".coral-toolbar-item:not(.coral-state-hidden)");return e.each(function(a,b){var g=$(b);d=d+g.outerWidth()+c.margin,a!=e.length-1||f.length||(d-=c.margin)}),a?d-c.margin:(this.uiAfter.menubutton("showPanel"),f.each(function(a,b){var e=$(b);d=d+e.outerWidth()+c.margin,a==f.length-1&&(d-=c.margin)}),this.uiAfter.menubutton("hidePanel"),d)},_getIcon:function(a){var b,c={ico1:null,ico2:null},d=[];return null==a?c:(b=$.trim(a),b.indexOf(",")>=0?(d=b.split(","),c.ico1=""==d[0]?null:d[0],c.ico2=""==d[1]?null:d[1]):c.ico1=b,c)},getLength:function(){return this._getElements(0).length},isExist:function(a){return!!this._getSubCoral(a)},_getGroupElementsLength:function(a){if(!("number"!=typeof a||a>this.groupLength-1)){var b=this._getGroupNameByIndex(a),c=this.element.find("[group-role='"+b+"']");return c.find(".ctrl-toolbar-element:not(.coral-toolbar-separator)").length}},add:function(a,b,c){if("object"==typeof b){if("string"==typeof a)return this._addByParentId(a,b);c=c||0;var d=parseInt(a);null!=a&&isNaN(d)||d<0||d>this._getGroupElementsLength(c)||($.isArray(b)||(b=[b]),0==this._getGroupElementsLength(c)&&(a=null),this._addItems(a,b,c),this._refresh())}},_addByParentId:function(a,b){var c=this._getSubCoral(a);if(c){var d=c.$el;switch(c.type){case"splitbutton":d.splitbutton("menu").tieredmenu("add",null,b);break;case"menubutton":d.menubutton("menu").tieredmenu("add",null,b);break;case"tieredmenu":d.tieredmenu("add",a,b)}}},getSubCoral:function(a){var b=this;return"string"==typeof a?b._getSubCoral(a):b._getSubCoralByIndex(a)},_getSubCoralByIndex:function(a){var b=this,c=parseInt(a);return!(isNaN(c)||c<0||c>this.getLength()-1)&&b._getSubCoral(c)},_findElementsByAttr:function(a,b,c){var d=[];return $.each(a,function(a,e){var f=$(e);c==f.attr(b)&&d.push(f)}),d},_getSubCoral:function(a){var b=this._findElementsByAttr(this._getElements(0),"id",a),c=this._findElementsByAttr(this._getElements(0),"component-role","splitbutton"),d=this._findElementsByAttr(this._getElements(0),"component-role","menubutton"),e=null,f=null,g=null;return"number"==typeof a?(g=$(this._getElements(0)[a]),f={$el:g,type:g.attr("component-role")}):b.length?f={$el:b[0],type:b[0].attr("component-role")}:(e=this._findSubCoralInMenuItems(c,a),c.length&&e?f={$el:e,type:"tieredmenu"}:(e=this._findSubCoralInMenuItems(d,a),d.length&&e?f={$el:e,type:"tieredmenu"}:f))},_findSubCoralInMenuItems:function(a,b){if(a.length){var c=null;return $.each(a,function(a,d){var e=$(d),f=e.attr("component-role"),g=$(d)[f]("menu");g.find("[data-id='"+b+"']").length&&(c=g)}),c}},removeAll:function(){$.each(this._getElements(1),function(a,b){var c=$(b),d=c.attr("component-role");d&&c[d]("destroy"),c.remove()}),this._refresh()},remove:function(a){"string"==typeof a?this._removeById(a):this._removeByIndex(a),this._refresh()},_removeById:function(a){var b=this._getSubCoral(a);if(b){var c=b.$el;if(c.hasClass("coral-toolbar-html"))return void c.remove();switch(b.type){case"tieredmenu":c.tieredmenu("removeItem",a);break;default:var d=c.attr("component-role");c[d]("destroy"),c.remove()}}},_removeByIndex:function(a){var b=parseInt(a);if(isNaN(b)||b<0||b>this.getLength()-1)return!1;var c=$(this._getElements(0)[b]),d=c.attr("component-role");d&&c[d]("component").remove(),c.remove()},update:function(a,b){var c=this;"string"==typeof a?c._updateById(a,b):c._updateByIndex(a,b),this._refresh()},_updateById:function(a,b){var c=this._getSubCoral(a);if(c){var d=c.$el;switch(c.type){case"tieredmenu":d.tieredmenu("updateItem",a,b);break;case"button":d.button("update",b);break;case"splitbutton":d.splitbutton("button").button("update",b);break;case"menubutton":d.menubutton("button").button("update",b)}}},_updateByIndex:function(a,b){var c=parseInt(a);if(isNaN(c)||c<0||c>this.getLength()-1)return!1;var d=this._getSubCoral(a);if(d){var e=d.$el;switch(d.type){case"button":e.button("update",b);break;case"splitbutton":e.splitbutton("button").button("update",b);break;case"menubutton":e.menubutton("button").button("update",b)}}},component:function(){return this.uiBox},_uiBorder:function(){return this.uiBorder},disable:function(){this._setDisabled(!0)},enable:function(){this._setDisabled(!1)},_setDisabled:function(a){var b=this._getElements(0);$.each(b,function(b,c){var d=$(c);return!!d.hasClass("coral-toolbar-html")||void(d.attr("component-role")&&(a?d[d.attr("component-role")]("disable"):d[d.attr("component-role")]("enable")))}),this.options.disabled=!!a},disableItem:function(a){var b=this;"string"==typeof a?b._disableItemById(a):b._disableItemByIndex(a)},_disableItemById:function(a){var b=this._getSubCoral(a);if(b){var c=b.$el;if(c.hasClass("coral-toolbar-html"))return;switch(b.type){case"tieredmenu":c.tieredmenu("disableItem",a);break;default:var d=$(this._findElementsByAttr(this._getElements(0),"id",a)[0]);if(!d.length)return;d[d.attr("component-role")]("disable")}}},_disableItemByIndex:function(a){var b=parseInt(a);if(isNaN(b)||b<0||b>this.getLength()-1)return!1;var c=$(this._getElements(0)[b]);return!c.hasClass("coral-toolbar-html")&&(!!c.length&&(c[c.attr("component-role")]("disable"),!0))},enableItem:function(a){var b=this;"string"==typeof a?b._enableItemById(a):b._enableItemByIndex(a)},_enableItemById:function(a){var b=this._getSubCoral(a);if(b){var c=b.$el;if(c.hasClass("coral-toolbar-html"))return;switch(b.type){case"tieredmenu":c.tieredmenu("enableItem",a);break;default:var d=$(this._findElementsByAttr(this._getElements(0),"id",a)[0]);if(!d.length)return;d[d.attr("component-role")]("enable")}}},_enableItemByIndex:function(a){var b=parseInt(a);if(isNaN(b)||b<0||b>this.getLength()-1)return!1;var c=$(this._getElements(0)[b]);if(!c.hasClass("coral-toolbar-html"))return!!c.length&&(c[c.attr("component-role")]("enable"),!0)},hide:function(a){var b=this;"string"==typeof a?b._hideById(a):b._hideByIndex(a),this._refresh()},hideAll:function(){$.each(this._getElements(1),function(a,b){var c=$(b).attr("component-role");c?$(b)[c]("hide"):$(b).hide()}),this._refresh()},_hideById:function(a){var b=this._getSubCoral(a);if(b){var c=b.$el;if(c.hasClass("coral-toolbar-html"))return void c.hide();switch(b.type){case"tieredmenu":c.tieredmenu("hideItem",a);break;default:var d=$(this._findElementsByAttr(this._getElements(0),"id",a)[0]);if(!d.length)return;d[d.attr("component-role")]("hide")}}},_hideByIndex:function(a){var b=parseInt(a);if(isNaN(b)||b<0||b>this.getLength()-1)return!1;var c=$(this._getElements(0)[b]);return!!c.length&&(c.hasClass("coral-toolbar-html")?(c.hide(),!0):(c[c.attr("component-role")]("hide"),!0))},show:function(a){var b=this;"string"==typeof a?b._showById(a):b._showByIndex(a),this._refresh()},showAll:function(){$.each(this._getElements(1),function(a,b){var c=$(b).attr("component-role");c?$(b)[c]("show"):$(b).show()}),this._refresh()},_showById:function(a){var b=this._getSubCoral(a);if(b){var c=b.$el;if(c.hasClass("coral-toolbar-html"))return void c.show();switch(b.type){case"tieredmenu":c.tieredmenu("showItem",a);break;default:var d=$(this._findElementsByAttr(this._getElements(0),"id",a)[0]);if(!d.length)return;d[d.attr("component-role")]("show")}}},_showByIndex:function(a){var b=parseInt(a);if(isNaN(b)||b<0||b>this.getLength()-1)return!1;var c=$(this._getElements(0)[b]),d=c.attr("component-role");return!!c.length&&(d?c[d]("show"):c.show(),!0)},_destroy:function(){this.uiBox.replaceWith(this.element)},_setOption:function(a,b){"id"!==a&&"name"!==a&&("disabled"===a&&this._setDisabled(b),this._super(a,b))},refresh:function(){this._refresh()},_refresh:function(){this._position(),$.coral.refreshChild(this.element)}});