/*! cui 2017-11-03 */
$.component("coral.grid",$.coral.grid,{editCell:function(a,b,c){var d,e,f,g,h=this;if(h.grid&&h.options.cellEdit===!0){if(b=parseInt(b,10),h.options.knv||$(h.element).grid("GridNav"),h.options.savedRow.length>0){if(c===!0&&a==h.options.iRow&&b==h.options.iCol)return;if(h.rows[h.options.savedRow[0].id]&&!$(h.element).grid("saveCell",h.options.savedRow[0].id,h.options.savedRow[0].ic))return}else window.setTimeout(function(){$("#"+$.grid.coralID(h.options.knv)).attr("tabindex","-1").focus()},0);if(g=h.options.colModel[b],d=g.name,"subgrid"!=d&&"cb"!=d&&"rn"!=d){if(f=$("td:eq("+b+")",h.rows[a]),g.editable!==!0||c!==!0||f.hasClass("not-editable-cell"))parseInt(h.options.iCol,10)>=0&&parseInt(h.options.iRow,10)>=0&&($("td:eq("+h.options.iCol+")",h.rows[h.options.iRow]).removeClass("edit-cell"),$(h.rows[h.options.iRow]).removeClass("selected-row coral-state-hover")),f.addClass("edit-cell"),$(h.rows[a]).addClass("selected-row coral-state-hover"),e=f.html().replace(/\&#160\;/gi,""),$(h.element).triggerHandler("jqGridSelectCell",[h.rows[a].id,d,e,a,b]),$.isFunction(h.options.onSelectCell)&&h.options.onSelectCell.call(h,h.rows[a].id,d,e,a,b);else{parseInt(h.options.iCol,10)>=0&&parseInt(h.options.iRow,10)>=0&&($("td:eq("+h.options.iCol+")",h.rows[h.options.iRow]).removeClass("edit-cell"),$(h.rows[h.options.iRow]).removeClass("selected-row coral-state-hover")),$(f).addClass("edit-cell"),$(h.rows[a]).addClass("selected-row coral-state-hover");try{e=$.unformat.call(h,f,{rowId:h.rows[a].id,colModel:g},b)}catch(i){e=g.edittype&&"textarea"==g.edittype?$(f).text():$(f).html()}if(h.options.autoencode&&(e=$.grid.htmlDecode(e)),!h._trigger("beforeEditCell",null,[{rowId:h.rows[a].id,name:d,cellValue:e,rowIndex:a,cellIndex:b}]))return;if(h.options.savedRow.push({id:a,ic:b,name:d,value:e}),("&nbsp;"===e||"&#160;"===e||1===e.length&&160===e.charCodeAt(0))&&(e=""),$.isFunction(h.options.formatCell)){var j=h.options.formatCell.call(h,h.rows[a].id,d,e,a,b);void 0!==j&&(e=j)}var k=g.editoptions,l={id:a+"_"+d,name:d};k=$.extend({},k||{},l);var m=$.grid.createEl.call(h,g.edittype,k,e,!0,$.extend({},$.grid.ajaxOptions,h.options.ajaxSelectOptions||{}));$(f).html("").append(m).attr("tabindex","0");var n=function(c){if(27===c.keyCode&&($("input.hasDatepicker",f).length>0?$(".coral-datepicker").is(":hidden")?$(h.element).grid("restoreCell",a,b):$("input.hasDatepicker",f).datepicker("hide"):$(h.element).grid("restoreCell",a,b)),13===c.keyCode)return $(h.element).grid("saveCell",a,b),!1;if(9===c.keyCode){if(h.grid.columnsView.loading)return!1;c.shiftKey?$(h.element).grid("prevCell",a,b):$(h.element).grid("nextCell",a,b)}c.stopPropagation()},o={onKeyDown:n,value:$(m).val()},p=g.edittype,q=$(h.element).grid("getRowData",h.rows[a].id);switch(g.edittype){case"text":case"textarea":if(k=$.extend({},k,o),g.cellEditoptions)var r=h.getCellEditOptions(g,$(m).val(),k,q,l,o);p=r&&r.type?r.type:g.edittype,k=r&&r.cOpts?r.cOpts:k,p="text"==p||"textarea"==p?"textbox":p,$(m)[p](k),$(m)[p]("setValues",$(m).val().split(","));break;case"datepicker":k=$.extend({},k,o),$(m).datepicker(k);break;case"radio":k=$.extend({},k,o),$(m).radio(k),"Yes"==$(m).val()?$(m).radio("check"):$(m).radio("uncheck");break;case"checkbox":k=$.extend({},k,o),$(m).checkbox(k),"Yes"==$(m).val()?$(m).checkbox("check"):$(m).checkbox("uncheck");break;case"autocomplete":var s=$(m).val(),t="string"==typeof k.source;k=$.extend({},k,o);var u=k.postMode||"value";k.postMode=u,k=$.extend({},k,{onKeyDown:n}),$(m).autocomplete(k),t?(k.postMode="text",$(m).autocomplete(k),$(m).autocomplete("setValue",s)):"value"==u?$(m).autocomplete("setValue",s):($(m).autocomplete("setValue",s),$(m).autocomplete("setText",s));break;case"combobox":case"combotree":case"combogrid":if(k=$.extend({},k,o),g.cellEditoptions)var r=h.getCellEditOptions(g,$(m).val(),k,q,l,o);p=r&&r.type?r.type:g.edittype,k=r&&r.cOpts?r.cOpts:k,p="text"==p?"textbox":p,$(m)[p](k),$(m)[p]("setValues",$(m).val().split(","))}window.setTimeout(function(){$(m).focus()},0),h._trigger("afterEditCell",null,[{rowId:h.rows[a].id,name:d,cellValue:e,rowIndex:a,celIndex:b}])}h.options.iCol=b,h.options.iRow=a}}},getCellEditOptions:function(a,b,c,d,e,f){var g=$.coral.toFunction(a.cellEditoptions).call(this,b,c,d),h=g&&g.type?g.type:a.edittype;return g=g&&g.cellEditoptions?$.extend({},g.cellEditoptions,e,f):c,c=g||c,{cOpts:g,type:h}},GridNav:function(){function a(a,b,d){if("v"==d.substr(0,1)){var e=$(c.grid.rowsView)[0].clientHeight,f=$(c.grid.rowsView)[0].scrollTop,g=c.rows[a].offsetTop+c.rows[a].clientHeight,h=c.rows[a].offsetTop;"vd"==d&&g>=e&&($(c.grid.rowsView)[0].scrollTop=$(c.grid.rowsView)[0].scrollTop+c.rows[a].clientHeight),"vu"==d&&h<f&&($(c.grid.rowsView)[0].scrollTop=$(c.grid.rowsView)[0].scrollTop-c.rows[a].clientHeight)}if("h"==d){var i=$(c.grid.rowsView)[0].clientWidth,j=$(c.grid.rowsView)[0].scrollLeft,k=c.rows[a].cells[b].offsetLeft+c.rows[a].cells[b].clientWidth,l=c.rows[a].cells[b].offsetLeft;k>=i+parseInt(j,10)?$(c.grid.rowsView)[0].scrollLeft=$(c.grid.rowsView)[0].scrollLeft+c.rows[a].cells[b].clientWidth:l<j&&($(c.grid.rowsView)[0].scrollLeft=$(c.grid.rowsView)[0].scrollLeft-c.rows[a].cells[b].clientWidth)}}function b(a,b){var d,e;if("lft"==b)for(d=a+1,e=a;e>=0;e--)if(c.options.colModel[e].hidden!==!0){d=e;break}if("rgt"==b)for(d=a-1,e=a;e<c.options.colModel.length;e++)if(c.options.colModel[e].hidden!==!0){d=e;break}return d}var c=this;if(c.grid&&c.options.cellEdit===!0){c.options.knv=c.options.id+"_kn";var d,e,f=$("<div style='position:fixed;top:0px;width:1px;height:1px;' tabindex='0'><span tabindex='-1' style='width:0px;height:0px;background-color:grey' id='"+c.options.knv+"'></span></div>");$(f).insertBefore(c.grid.columnsView),$("#"+c.options.knv).focus().keydown(function(f){switch(e=f.keyCode,"rtl"==c.options.direction&&(37===e?e=39:39===e&&(e=37)),e){case 38:c.options.iRow-1>0&&(a(c.options.iRow-1,c.options.iCol,"vu"),$(c.element).grid("editCell",c.options.iRow-1,c.options.iCol,!1));break;case 40:c.options.iRow+1<=c.rows.length-1&&(a(c.options.iRow+1,c.options.iCol,"vd"),$(c.element).grid("editCell",c.options.iRow+1,c.options.iCol,!1));break;case 37:c.options.iCol-1>=0&&(d=b(c.options.iCol-1,"lft"),a(c.options.iRow,d,"h"),$(c.element).grid("editCell",c.options.iRow,d,!1));break;case 39:c.options.iCol+1<=c.options.colModel.length-1&&(d=b(c.options.iCol+1,"rgt"),a(c.options.iRow,d,"h"),$(c.element).grid("editCell",c.options.iRow,d,!1));break;case 13:parseInt(c.options.iCol,10)>=0&&parseInt(c.options.iRow,10)>=0&&$(c.element).grid("editCell",c.options.iRow,c.options.iCol,!0);break;default:return!0}return!1})}},saveCell:function(a,b){var c,d=this;if(d.grid&&d.options.cellEdit===!0){if(c=d.options.savedRow.length>=1?0:null,null!==c){var e,f,g=$("td:eq("+b+")",d.rows[a]),h=d.options.colModel[b],i=h.name,j=$.grid.coralID(i);if(this.options.autoValid){var k=this.valid(d.rows[a].id,h.name);if(!this.options.allowSaveOnError&&!k)return $.message("请确认是否输入正确！"),k}var l=h.edittype;if(h.cellEditoptions){var m=$(d.element).grid("getRowData",d.rows[a].id),n=$("#"+a+"_"+j,d.rows[a]),o=$.coral.toFunction(h.cellEditoptions).call(this,$(n)[$(n).attr("component-role")]("getValue"),h.editoptions,m);l=o&&o.type?o.type:l}switch(l){case"select":if(h.editoptions.multiple){var n=$("#"+a+"_"+j,d.rows[a]),p=[];e=$(n).val(),e?e.join(","):e="",$("option:selected",n).each(function(a,b){p[a]=$(b).text()}),f=p.join(",")}else e=$("#"+a+"_"+j+" option:selected",d.rows[a]).val(),f=$("#"+a+"_"+j+" option:selected",d.rows[a]).text();h.formatter&&(f=e);break;case"checkbox":var q=["Yes","No"];h.editoptions&&(q=h.editoptions.value.split(":")),e=$("#"+a+"_"+j,d.rows[a]).is(":checked")?q[0]:q[1],f=e;break;case"password":case"text":case"textarea":e=$("#"+a+"_"+j,d.rows[a]).val(),f=e;break;case"button":e=$("#"+a+"_"+j,d.rows[a]).val(),f=e;break;case"custom":try{if(!h.editoptions||!$.isFunction(h.editoptions.custom_value))throw"e1";if(e=h.editoptions.custom_value.call(d,$(".customelement",g),"get"),void 0===e)throw"e2";f=e}catch(r){"e1"==r&&$.grid.info_dialog(jQuery.jgrid.errors.errcap,"function 'custom_value' "+$.grid.edit.msg.nodefined,jQuery.jgrid.edit.bClose),"e2"==r?$.grid.info_dialog(jQuery.jgrid.errors.errcap,"function 'custom_value' "+$.grid.edit.msg.novalue,jQuery.jgrid.edit.bClose):$.grid.info_dialog(jQuery.jgrid.errors.errcap,r.message,jQuery.jgrid.edit.bClose)}break;case"datepicker":e=$("#"+a+"_"+j,d.rows[a]).val(),f=e;$("#"+a+"_"+j,d.rows[a]);break;case"combobox":case"combogrid":case"combotree":e=$("#"+a+"_"+j,d.rows[a])[l]("getValues").toString(),f=$("#"+a+"_"+j,d.rows[a])[l]("getText"),h.formatter&&(f=e);break;case"autocomplete":f=$("#"+a+"_"+j,d.rows[a])[l]("getText"),e="string"==typeof h.editoptions.source?f:$("#"+a+"_"+j,d.rows[a])[l]("getValue"),h.formatter&&"value"==h.postMode&&(f=e)}if(f!==d.options.savedRow[c].value){var s=$(d).triggerHandler("jqGridBeforeSaveCell",[d.rows[a].id,i,e,a,b]);if(s&&(e=s,f=s),$.isFunction(d.options.beforeSaveCell)){var t=d.options.beforeSaveCell.call(d,d.rows[a].id,i,e,a,b);t&&(e=t,f=t)}var u=$.grid.checkValues(e,b,d);if(u[0]===!0){var v=$(d).triggerHandler("jqGridBeforeSubmitCell",[d.rows[a].id,i,e,a,b])||{};if($.isFunction(d.options.beforeSubmitCell)&&(v=d.options.beforeSubmitCell.call(d,d.rows[a].id,i,e,a,b),v||(v={})),$("input.hasDatepicker",g).length>0&&$("input.hasDatepicker",g).datepicker("hide"),"remote"==d.options.cellsubmit)if(d.options.cellurl){var w={};d.options.autoencode&&(e=$.grid.htmlEncode(e)),w[i]=e;var x,y,z;z=d.options.prmNames,x=z.id,y=z.oper,w[x]=$.grid.stripPref(d.options.idPrefix,d.rows[a].id),w[y]=z.editoper,w=$.extend(v,w),$("#lui_"+$.grid.coralID(d.options.id)).show(),d.grid.columnsView.loading=!0,$.ajax($.extend({url:d.options.cellurl,data:$.isFunction(d.options.serializeCellData)?d.options.serializeCellData.call(d,w):w,type:"POST",complete:function(c,h){if($("#lui_"+d.options.id).hide(),d.grid.columnsView.loading=!1,"success"==h){var j=$(d).triggerHandler("jqGridAfterSubmitCell",[d,c,w.id,i,e,a,b])||[!0,""];j[0]===!0&&$.isFunction(d.options.afterSubmitCell)&&(j=d.options.afterSubmitCell.call(d,c,w.id,i,e,a,b)),j[0]===!0?($(g).empty(),$(d.element).grid("setCell",d.rows[a].id,b,f,!1,!1,!0),$(g).addClass("dirty-cell"),$(d.rows[a]).addClass("edited"),$(d).triggerHandler("jqGridAfterSaveCell",[d.rows[a].id,i,e,a,b]),$.isFunction(d.options.afterSaveCell)&&d.options.afterSaveCell.call(d,d.rows[a].id,i,e,a,b),d.options.savedRow.splice(0,1)):($.grid.info_dialog($.grid.errors.errcap,j[1],$.grid.edit.bClose),$(d.element).grid("restoreCell",a,b))}},error:function(c,e,f){$("#lui_"+$.grid.coralID(d.options.id)).hide(),d.grid.columnsView.loading=!1,$(d).triggerHandler("jqGridErrorCell",[c,e,f]),$.isFunction(d.options.errorCell)?(d.options.errorCell.call(d,c,e,f),$(d.element).grid("restoreCell",a,b)):($.grid.info_dialog($.grid.errors.errcap,c.status+" : "+c.statusText+"<br/>"+e,$.grid.edit.bClose),$(d.element).grid("restoreCell",a,b))}},$.grid.ajaxOptions,d.options.ajaxCellOptions||{}))}else try{$.grid.info_dialog($.grid.errors.errcap,$.grid.errors.nourl,$.grid.edit.bClose),$(d.element).grid("restoreCell",a,b)}catch(r){}"clientArray"==d.options.cellsubmit&&($(g).empty(),$(d.element).grid("setCell",d.rows[a].id,b,f,!1,!1,!0),$(g).addClass("dirty-cell"),$(d.rows[a]).addClass("edited"),d._trigger("afterSaveCell",null,[{rowId:d.rows[a].id,name:i,cellValue:e,rowIndex:a,celIndex:b}]),d.options.savedRow.splice(0,1))}else try{window.setTimeout(function(){$.grid.info_dialog($.grid.errors.errcap,e+" "+u[1],$.grid.edit.bClose)},100),$(d.element).grid("restoreCell",a,b)}catch(r){}}else $(d.element).grid("restoreCell",a,b)}window.setTimeout(function(){$("#"+$.grid.coralID(d.options.knv)).attr("tabindex","-1").focus()},0)}},restoreCell:function(a,b){var c,d=this;if(d.grid&&d.options.cellEdit===!0){if(c=d.options.savedRow.length>=1?0:null,null!==c){var e=$("td:eq("+b+")",d.rows[a]);$(e).empty().attr("tabindex","-1"),$(d.element).grid("setCell",d.rows[a].id,b,d.options.savedRow[c].value,!1,!1,!0),$(d).triggerHandler("jqGridAfterRestoreCell",[d.rows[a].id,d.options.savedRow[c].value,a,b]),$.isFunction(d.options.afterRestoreCell)&&d.options.afterRestoreCell.call(d,d.rows[a].id,d.options.savedRow[c].value,a,b),d.options.savedRow.splice(0,1)}window.setTimeout(function(){$("#"+d.options.knv).attr("tabindex","-1").focus()},0)}},nextCell:function(a,b){var c=this,d=!1;if(c.grid&&c.options.cellEdit===!0){for(var e=b+1;e<c.options.colModel.length;e++)if(c.options.colModel[e].editable===!0){d=e;break}d!==!1?$(c.element).grid("editCell",a,d,!0):c.options.savedRow.length>0&&$(c.element).grid("saveCell",a,b)}},prevCell:function(a,b){var c=this,d=!1;if(c.grid&&c.options.cellEdit===!0){for(var e=b-1;e>=0;e--)if(c.options.colModel[e].editable===!0){d=e;break}d!==!1?$(c.element).grid("editCell",a,d,!0):c.options.savedRow.length>0&&$(c.element).grid("saveCell",a,b)}},getChangedCells:function(a){var b=[];a||(a="all");var c,d=this,e=!0;if(d.grid&&d.options.cellEdit===!0)return $(d.rows).each(function(f){var g={};if($(this).hasClass("edited")){if($("td",this).each(function(b){if(c=d.options.colModel[b].name,"cb"!==c&&"subgrid"!==c){if($(this).hasClass("coral-gridcell-error"))return e=!1,!1;if("dirty"==a){if($(this).hasClass("dirty-cell"))try{g[c]=$.unformat.call(d,this,{rowId:d.rows[f].id,colModel:d.options.colModel[b]},b)}catch(h){g[c]=$.jgrid.htmlDecode($(this).html())}}else try{g[c]=$.unformat.call(d,this,{rowId:d.rows[f].id,colModel:d.options.colModel[b]},b)}catch(h){g[c]=$.jgrid.htmlDecode($(this).html())}}}),!e)return!1;g.id=this.id,b.push(g)}}),e||(b=[]),b}});