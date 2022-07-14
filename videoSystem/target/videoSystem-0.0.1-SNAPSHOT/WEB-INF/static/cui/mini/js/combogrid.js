/*! cui 2017-11-03 */
$.component("coral.combogrid",$.coral.combo,{version:"4.0.3",castProperties:["colNames","colModel","data","buttonOptions","gridOptions","buttons","shortCut","searchColumns"],options:{colNames:[],colModel:[],valueField:"id",textField:"name",panelRenderOnShow:!1,multiple:!1,method:"post",selarrrow:[],searchColumns:[],mode:null,buttons:[],url:null,panelWidth:500,panelHeight:220,sortable:!1,data:[],onSelectAll:null,onSortableColums:null,onLoad:$.noop,onComplete:null,pager:!1,buttonOptions:null,loader:function(a,b,c){var d=$(this),e=d.combogrid("option","url");return!!e&&(a.q&&(a={q:a.q.join(",")}),$.ajax({type:d.combogrid("option","method"),url:e,data:a,dataType:"json",success:function(a){b(a)},error:function(){c.apply(this,arguments)}}),!1)},gridOptions:{loadonce:"false"}},grid:function(){return $("#combo_grid_"+$(this.element).attr("id"))},button:function(){if(null!==this.options.buttonOptions)return this.$button},_destroy:function(){this.element.removeClass("coral-validation-combogrid"),this.element.removeClass("coral-form-element-combogrid"),this.grid().grid("destroy"),this._super()},_showItems:function(){var a=(this.options.textField,this.options.valueField,{}),b=this.grid();a.filters="{}",b.grid("option","localonce",!0),$.extend(b.grid("option","postData"),a),b.grid("reload",{page:1})},_doQuery:function(a){var b=this.options,c=this.grid(),d=b.textField,e=c.grid("option","colModel"),f=[],g=[];if("remote"==b.mode)this.reload(null,{q:a});else{for(var h in e){var i=e[h];"cb"!=i.name&&"rn"!=i.name}for(var j=0;j<a.length;j++)if(b.searchColumns.length>0)for(var h=0;h<b.searchColumns.length;h++)g.push('{"field":"'+b.searchColumns[h]+'","op":"cn","data":"'+a[j]+'"}');else g.push('{"field":"'+d+'","op":"cn","data":"'+a[j]+'"}');f.filters='{"groupOp":"OR","rules":['+g.join(",")+"]}",$.extend(c.grid("option","postData"),f)}c.grid("option","localonce",!0),c.grid("reload",{page:1})},_checkMathch:function(a,b){var c,d,e=[],f=[],g=this.options.textField,h=this.options.valueField;this.grid();b&&this._showItems();var i=this.grid().grid("option","data"),j=!1,k={};if(this.options.multiple){for(c=0;c<a.length;c++){for(d=0;d<i.length;d++)if(i[d][g]!=a[c]&&i[d][h]!=a[c]&&(k[c.toString()]=!0),i[d][g]==a[c]){e.push(i[d][h]),f.push(i[d][g]),j=!0;break}j||this.options.forceSelection||(!k[c.toString()]&&!b||b)&&(e.push(a[c]),f.push(a[c])),j=!1}var l=this._getOnlyValues(),m=this._getOnlyTexts();for(c=0;c<a.length;c++)for(d=0;d<m.length;d++)m[d]==a[c]&&$.inArray(m[d],f)===-1&&(e.push(l[d]),f.push(m[d]))}else{var n=-1;for(c=0;c<i.length;c++)i[c][g]==a[0]&&(n=n===-1?c:n,j=!0);j&&(e.push(i[n][h]),f.push(i[n][g])),j||this.options.forceSelection||(!k[0]&&!b||b)&&(e.push(a[0]),f.push(a[0]))}return{valarr:e,textarr:f}},reload:function(a,b){this.cache.isReload=!0;var c=this.grid(),d={},e=(this.options.loader,!1),f=[];a||this.options.url?!a&&this.options.url&&(a=this.options.url):a=[],"string"!=typeof a?(d=a,d.data?f=d.data:d.url?(a=d.url,e=!0):a instanceof Array?f=a:d.url||d.data||this.options.url?d.url||d.data||!this.options.url||(a=this.options.url,e=!0):f=[]):(this.options.url=a,e=!0),$.isFunction(d.onLoad)&&(this.cache.onLoad=d.onLoad),1==e?(b=b||{},$.isEmptyObject(b)?c.grid("reload",a):(a=a.indexOf("?")!=-1?a+"&q="+b.q:a+"?q="+b.q,c.grid("reload",a))):c.grid("reload",a)},_removeGridHighlights:function(){this._removeHighlight(this.grid().find(".coral-grid-btable tr td > span.coral-keyword-highlight"))},_renderItems:function(a){},_addGridHighlights:function(){this._addHighlight(this.grid().find(".coral-grid-btable .jqgrow").children("td"),this.uiCombo.textbox.val())},_updateGridData:function(a){var b=this.grid().grid("getRowData",a.rowId),c=this._getTextFromHTML(b[this.options.valueField]),d=this._getTextFromHTML(b[this.options.textField]),e=$.inArray(c,this.gridValueArr);return this.options.multiple?void(a.status?e==-1&&(this.gridValueArr.push(c),this.gridTextArr.push(d),this.gridRowIdArr.push(a.rowId)):e!=-1&&(this.gridValueArr.splice(e,1),this.gridTextArr.splice(e,1),this.gridRowIdArr.splice(e,1))):(this.gridValueArr=[c],this.gridTextArr=[d],void(this.gridRowIdArr=[a.rowId]))},_create:function(){var a=this;this.element.addClass("coral-form-element-combogrid coral-validation-combogrid"),this._super(),this.panelRendered=!0,null!==this.options.buttonOptions&&(this.$button=this._getButtonEl(),this.component().append(this.$button).addClass("coral-combogrid-hasButton"),this.$button.button(this.options.buttonOptions)),this.uiCombo.panel.unbind().bind("mousedown",function(b){a.cancelBlur=!0,a._delay(function(){delete a.cancelBlur});var c=$(b.target).closest(".coral-grid-pager").length;return 1==c})},_initCombo:function(){this._super();$();this.options.pager?grid=$('<div id="combo_grid_'+$(this.element).attr("id")+'"><div class="combo_grid_'+$(this.element).attr("id")+'"></div></div>').appendTo(this.uiCombo.pContent):grid=$('<div id="combo_grid_'+$(this.element).attr("id")+'"></div>').appendTo(this.uiCombo.pContent),this.gridValueArr=[],this.gridTextArr=[],this.gridRowIdArr=[],goptions={fitStyle:"fill",keyName:this.options.valueField,sortable:this.options.sortable,colModel:this.options.colModel,colNames:this.options.colNames,multiselect:this.options.multiple,sortname:this.options.sortname,width:"auto"},goptions=$.extend({},goptions,this.options.gridOptions),null!=this.options.url?(goptions.url=this.options.url,goptions.datatype="json"):(goptions.data=this.options.data,goptions.datatype="local"),this._on(grid,{gridonselectrow:function(a,b){var c=this._getOnlyValues();this.options.multiple?$.inArray(b.rowId,c)==-1?c.push(b.rowId):c.splice($.inArray(b.rowId,c),1):c=[b.rowId],this.setValues(c,!0,!1),!this.options.multiple&&a.originalEvent&&"click"==a.originalEvent.type&&this.hidePanel(),this._trigger("onSelectRow",a,[{rowId:b.rowId,status:b.status}])},gridonselectall:function(a,b){var c=b.status?b.aRowIds.concat():[];this.setValues(c,!0,!1)},gridonload:function(a,b){var c=this,d=!1,e=b.data;c.options.clearOnLoad&&(d=c._clearValues(e)),this._addGridHighlights(),this.dataLoaded=!0;for(var f=this.grid().grid("option","selarrrow").concat(),g=0;g<f.length;g++)this.grid().grid("setSelection",f[g],!1);var h=this._getOnlyValues();$.each(h,function(a,b){c.grid().grid("setSelection",b,!1,null)}),1==d&&(this.currentValues=[]),this.search||(this.setValues(this.currentValues),this.search=!1),this.cache.isReload&&(this.cache.isReload=!1,this._trigger(this.cache.onLoad||"onLoad",null,[b]),delete this.cache.onLoad)}}),grid.grid(goptions),grid.grid("refresh")},_getButtonEl:function(){return $("<button type='button'></button>").addClass("coral-combogrid-button")},_getRowDataByColName:function(a){var b=(this.options,this.grid()),c=b.grid("option","data"),d=[];return"string"==typeof a&&(a=[a]),$.each(c,function(b,c){var e={};for(var f in a){var g=a[f];e[g]=c[g]}d.push(e)}),d},_getTextArrByValueArr:function(a){var b=(this.options,this.options.valueField),c=this.options.textField,d=this._getRowDataByColName([b,c]),e=[];for(var f in a){var g=a[f],h=!1;$.each(d,function(a,d){g==d[b]&&(e.push(d[c]),h=!0)}),h||e.push(a[f])}return e},setValues:function(a,b,c){for(var d=this.grid(),e=d.grid("option","selarrrow").concat(),f=0;f<e.length;f++)d.grid("setSelection",e[f],!1);if(!this.dataLoaded){var g={setValues:{values:a,text:c,triggerOnChange:b}};this._addCacheItem(g)}var h=this.options,i=[];i=this._getTextArrByValueArr(a),c="boolean"==typeof c&&c,c||this._setText(i.join(h.separator)),this.currentValues=a,this.currentTexts=i;var j=a.concat();j.sort();for(var f=0;f<j.length;f++)(j[f]!==j[f+1]&&f!=j.length||f==j.length)&&d.grid("setSelection",j[f],!1);if("item"==h.width){var k=$("<div style = 'visibility:hidden;'><span>"+this.getText()+"</span></div>").appendTo("body"),l=this.component().find(".coral-textbox-default"),m=parseInt(l.css("padding-left"))+parseInt(l.css("padding-right")),n=this.uiArrow().outerWidth()+2*parseInt(this.uiArrow().css("right")),o=k.find("span").outerWidth()+m+n;this.resize(o),k.remove(),h.width="item"}this._super(a,b,!1)},_getOnlyValues:function(){var a=this.getData(),b=this.options,c=[],d=0;if(!this.currentValues||!this.currentValues[0]&&1===this.currentValues.length)return c;for(;d<this.currentValues.length;d++){var e=this.currentValues[d],f=0,g=b.valueField,h=b.textField,i=null;if("value-text"===b.postMode&&c.push(e.split(b.valueTextSeparator)[0]),"value"===b.postMode&&c.push(e),"text"===b.postMode)for(;a&&f<a.length;f++)if(i=a[f],i[h]==e){c.push(i[g]);break}}return c},_getOnlyTexts:function(){return this.currentTexts||[]},getData:function(){return this.grid().grid("option","data")||[]},_selectPrev:function(){var a=this,b=null,c=0,d=a.grid().grid("getDataIDs");if(selarrrow=a.grid().grid("option","selarrrow").concat(),valueFirst=a.getValues()[0],a.selectedRow=a.selectedRow||valueFirst,a.options.multiple){if(a.selectedRow){for(var e=d.length;e>=0;e--)if(a.selectedRow==d[e]){c=0==e?d.length-1:e-1,a.selectedRow=d[c];break}$.inArray(a.selectedRow,selarrrow)==-1&&a.grid().grid("setSelection",a.selectedRow)}else a.selectedRow=d.length,a.grid().grid("setSelection",d.length);a._scrollTo(a.selectedRow)}else b=a.grid().grid("option","selrow"),b?(c=a.grid().grid("getInd",b),c>=0&&a.grid().grid("setSelection",d[c-2])):a.grid().grid("setSelection",d.length);a._scrollTo(b)},_selectNext:function(){var a=this,b=null,c=0,d=a.grid().grid("getDataIDs");if(selarrrow=a.grid().grid("option","selarrrow").concat(),valueFirst=a.getValues()[0],a.selectedRow=a.selectedRow||valueFirst,a.options.multiple){if(a.selectedRow){for(var e=0;e<d.length;e++)if(a.selectedRow==d[e]){c=e==d.length-1?0:e+1,a.selectedRow=d[c];break}$.inArray(a.selectedRow,selarrrow)==-1&&a.grid().grid("setSelection",a.selectedRow)}else a.selectedRow=d[0],a.grid().grid("setSelection",d[0]);a._scrollTo(a.selectedRow)}else b=a.grid().grid("option","selrow"),b?(c=a.grid().grid("getInd",b),c<d.length&&a.grid().grid("setSelection",d[c])):a.grid().grid("setSelection",d[0]),a._scrollTo(b)},_doEnter:function(){this.uiCombo.panel.is(":visible")&&(this.options.multiple?this.grid().grid("setSelection",this.selectedRow):this.hidePanel())},_scrollTo:function(a){var b=this.panel(),c=b.find('.coral-row-ltr[id="'+a+'"]');if(c.length)if(c.position().top<=0){var d=b.find(".coral-grid-rows-view").scrollTop()+c.position().top-c.outerHeight();b.find(".coral-grid-rows-view").scrollTop(d)}else if(c.position().top+c.outerHeight()>b.find(".coral-grid-rows-view").height()){var d=b.find(".coral-grid-rows-view").scrollTop()+c.position().top+c.outerHeight()-b.find(".coral-grid-rows-view").height();b.find(".coral-grid-rows-view").scrollTop(d)}}});