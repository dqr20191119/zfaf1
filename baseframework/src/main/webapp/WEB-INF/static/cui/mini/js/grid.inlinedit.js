/*! cui 2017-11-03 */
"use strict";$.coral.grid.inlineEdit=$.coral.grid.inlineEdit||{},$.component("coral.grid",$.coral.grid,{editButtonsPos:function(a,b){var c,d,e,f,g=this;c=$(g.element).grid("getDataIDs"),d=$(g.element).grid("getInd",a,!0),f=g.element.find(".coral-grid-rows-view").outerHeight(),e=g.element.find(".coral-grid-btable").outerHeight(),e+$(d).outerHeight()>f&&a==c[c.length-1]?b.position({my:"left-"+b.outerWidth()/2+" top-2",at:"right-"+$(d).outerWidth()/2+" top-"+$(b).outerHeight(),collision:"fit",of:$(d)}):b.position({my:"left-"+b.outerWidth()/2+" top-2",at:"right-"+$(d).outerWidth()/2+" top+"+$(d).outerHeight(),collision:"fit",of:$(d)})},editRow:function(a,b,c,d,e,f,g,h,i){var j={},k=$.makeArray(arguments).slice(1);"object"===$.type(k[0])?j=k[0]:("undefined"!=typeof b&&(j.keys=b),$.isFunction(c)&&(j.oneditfunc=c),$.isFunction(d)&&(j.successfunc=d),"undefined"!=typeof e&&(j.url=e),"undefined"!=typeof f&&(j.extraparam=f),$.isFunction(g)&&(j.aftersavefunc=g),$.isFunction(h)&&(j.errorfunc=h),$.isFunction(i)&&(j.afterrestorefunc=i)),j=$.extend(!0,{keys:!0,oneditfunc:null,successfunc:null,url:"clientArray",extraparam:{},aftersavefunc:null,errorfunc:null,afterrestorefunc:null,restoreAfterError:!0,mtype:"POST"},$.coral.grid.inlineEdit,j);var l,m,n,o,p,q=this,r=0,s=null,t={};if(q.grid&&(o=$(q.element).grid("getInd",a,!0),q.options.editrow=a,q.editRowIndex=a,o!==!1&&(n=$(o).attr("editable")||"0",0!=q._trigger("beforeInlineEditRow",null,[{rowId:a,options:j}])&&"0"==n&&!$(o).hasClass("not-editable-row")))){if(this.options.rowEditButtons){var u=$("<div class='row-editable coral-grid-rows'><div class='row-editable-btns coral-state-highlight'><div class='grid_edit_toolbar'></div></div></div>"),v=[],w={type:"button",id:"update",label:$.grid.edit.bUpdate,name:"update",onClick:function(){q.saveRow(o.id,null,"clientArray")}},x={type:"button",id:"cancel",label:$.grid.edit.bCancel,name:"cancel",onClick:function(){q.restoreRow(o.id),u.remove(),q._trigger("afterInlineCancelRow",null,[{rowId:o.id,options:j,isUpdated:!$(o.id).hasClass("new-row")}])}};0===this.options.rowEditButtons.length&&(this.options.rowEditButtons=["update","cancel"]);for(var y=0;y<this.options.rowEditButtons.length;y++)"update"==this.options.rowEditButtons[y]?v.push(w):"cancel"==this.options.rowEditButtons[y]?v.push(x):v.push(this.options.rowEditButtons[y]);this.rowEditButtons=u,q.element.find(".row-editable").remove(),q.element.find(".coral-grid-view").prepend(u[0]),$(".grid_edit_toolbar",q.element).toolbar({data:v}),this._delay(function(){this.editButtonsPos(a,u)})}p=q.options.colModel,$('td[role="gridcell"]',o).each(function(b){l=p[b].name;var c=q.options.treeGrid===!0&&l==q.options.expandColumn;if(c)m=$("span:first",this).html();else try{m=$.unformat.call(q,this,{rowId:a,colModel:p[b]},b)}catch(d){m=p[b].edittype&&"textarea"==p[b].edittype?$(this).text():$(this).html()}if("cb"!=l&&"subgrid"!=l&&"rn"!=l&&(q.options.autoencode&&(m=$.grid.htmlDecode(m)),t[l]=m,p[b].editable===!0)){null===s&&(s=b),c?$("span:first",this).html(""):$(this).html("");var e=$.extend({},p[b].editoptions||{},{id:a+"_"+l,name:l});p[b].edittype||(p[b].edittype="text"),("&nbsp;"==m||"&#160;"==m||1==m.length&&160==m.charCodeAt(0))&&(m="");var f=$.grid.createEl.call(q,p[b].edittype,e,m,!1,$.extend({},$.grid.ajaxOptions,q.options.ajaxSelectOptions||{}));$(f).addClass("editable"),c?$("span:first",this).append(f):$(this).append(f);var g=p[b].editoptions||{},h=g.postMode||"value";$.extend(g,{onValidError:function(a,b){$(f).parents("td").addClass("coral-gridcell-error"),a.stopPropagation()},onValidSuccess:function(a,b){$(f).parents("td").removeClass("coral-gridcell-error"),a.stopPropagation()},onKeyDown:function(a,b){},onClick:function(a,b){a.stopPropagation()},dataCustom:{rowId:a,gridId:q.options.id}});var i=p[b].edittype;switch(i){case"autocomplete":var j=$(f).val(),k="string"==typeof g.source,n=g.onSelect;g=$.extend({},g,{onSelect:function(a,b){$(f).closest("td").attr("data-org",k?b.value:b.text),n&&n.apply(f,[a,b])}}),k?(g.postMode="text",$(f).autocomplete(g),$(f).autocomplete("setValue",j)):($(f).autocomplete(g),"value"==h?$(f).autocomplete("setValue",j):($(f).autocomplete("setValue",j),$(f).autocomplete("setText",j)));break;case"checkbox":$(f).checkbox(g);var o=["Yes","No"];g&&g.value&&(o=g.value.split(":")),$(f).val()==o[0]?$(f).checkbox("check"):$(f).checkbox("uncheck");break;case"combogrid":case"combobox":case"combotree":$(f)[i](g),$(f)[i]("setValues",$(f).val().split(","));break;case"text":case"textarea":$(f).textbox(g);break;default:$(f)[i](g)}r++}}),r>0&&(t.id=a,q.options.savedRow.push(t),$(o).attr("editable","1"),$("td:eq("+s+") input",o).focus(),j.keys===!0&&$(o).bind("keydown",function(b){if(27===b.keyCode){if($(q.element).grid("restoreRow",a,j.afterrestorefunc),q.options._inlinenav)try{$(q.element).grid("showAddEditButtons")}catch(c){}return!1}if(13===b.keyCode){var d=b.target;if("TEXTAREA"==d.tagName)return!0;if($(q.element).grid("saveRow",a,j)&&q.options._inlinenav)try{$(q.element).grid("showAddEditButtons")}catch(e){}return!1}}),q._trigger("onInlineEditRow",null,[{rowId:a,options:j}]))}},saveRow:function(a,b,c,d,e,f,g){var h=$.makeArray(arguments).slice(1),i={};"object"===$.type(h[0])?i=h[0]:($.isFunction(b)&&(i.successfunc=b),"undefined"!=typeof c&&(i.url=c),"undefined"!=typeof d&&(i.extraparam=d),$.isFunction(e)&&(i.aftersavefunc=e),$.isFunction(f)&&(i.errorfunc=f),$.isFunction(g)&&(i.afterrestorefunc=g)),i=$.extend(!0,{successfunc:null,url:null,extraparam:{},aftersavefunc:null,errorfunc:null,afterrestorefunc:null,restoreAfterError:!0,mtype:"POST"},$.grid.inlineEdit,i);var j,k,l,m,n,o=!1,p=this,q={},r={},s={};if(!p.grid)return o;if(n=$(p.element).grid("getInd",a,!0),n===!1)return o;if(this.options.autoValid){var t=this.valid(n.id);if(!this.options.allowSaveOnError&&!t)return $.message("请确认是否输入正确！"),t}if(k=$(n).attr("editable"),i.url=i.url?i.url:p.options.editurl,i.url=i.url?i.url:"clientArray","1"===k){var u;if($('td[role="gridcell"]',n).each(function(a){if(u=p.options.colModel[a],j=u.name,"cb"!=j&&"subgrid"!=j&&u.editable===!0&&"rn"!=j&&!$(this).hasClass("not-editable-cell")){var b=u.edittype;switch(b){case"checkbox":var c=["Yes","No"];u.editoptions&&(c=u.editoptions.value.split(":")),q[j]=$("input",this).is(":checked")?c[0]:c[1];break;case"datepicker":q[j]=$("input[type='hidden']",this).val();break;case"autocomplete":"string"==typeof u.editoptions.source?q[j]=$(".ctrl-init-"+b,this)[b]("getText"):q[j]=$(".ctrl-init-"+b,this)[b]("getValue");break;case"text":case"password":case"textarea":case"button":q[j]=$("input, textarea",this).val();break;case"combogrid":case"combobox":case"combotree":q[j]=$(".ctrl-init-"+b,this)[b]("getValues").toString(),r[j]=$(".ctrl-init-"+b,this)[b]("getText"),u.formatter&&u.formatter==b&&(r={});break;case"custom":try{if(!u.editoptions||!$.isFunction(u.editoptions.custom_value))throw"e1";if(q[j]=u.editoptions.custom_value.call(p,$(".customelement",this),"get"),void 0===q[j])throw"e2"}catch(d){"e1"==d&&$.grid.info_dialog($.grid.errors.errcap,"function 'custom_value' "+$.grid.edit.msg.nodefined,$.grid.edit.bClose),"e2"==d?$.grid.info_dialog($.grid.errors.errcap,"function 'custom_value' "+$.grid.edit.msg.novalue,$.grid.edit.bClose):$.grid.info_dialog($.grid.errors.errcap,d.message,$.grid.edit.bClose)}}if(m=$.grid.checkValues(q[j],a,p),m[0]===!1)return m[1]=q[j]+" "+m[1],!1;p.options.autoencode&&(q[j]=$.grid.htmlEncode(q[j])),"clientArray"!==i.url&&u.editoptions&&""===q[j]&&(s[j]="null")}}),m[0]===!1){try{var v=$.grid.findPos($("#"+$.grid.coralID(a),p.grid.bDiv)[0]);$.grid.info_dialog($.grid.errors.errcap,m[1],$.grid.edit.bClose,{left:v[0],top:v[1]})}catch(w){alert(m[1])}return o}var x,y,z;if(y=p.options.prmNames,z=y.oper,x=p.options.keyName===!1?y.id:p.options.keyName,q&&(q[z]=y.editoper,q[x]=a,"undefined"==typeof p.options.inlineData&&(p.options.inlineData={}),q=$.extend({},q,p.options.inlineData,i.extraparam)),"clientArray"==i.url){p.options.autoencode&&$.each(q,function(a,b){q[a]=$.grid.htmlDecode(b)});var A=$(p.element).grid("setRowData",a,q);$(n).attr("editable","0");for(var B=0;B<p.options.savedRow.length;B++)if(p.options.savedRow[B].id==a){l=B;break}l>=0&&p.options.savedRow.splice(l,1),o=A,o&&(p.options.editrow=null),p.options.rowEditButtons&&p.rowEditButtons.hide(),p._trigger("afterInlineSaveRow",null,[{rowId:a,options:i,status:A}]),$.isFunction(i.aftersavefunc)&&i.aftersavefunc.call(p,a,A),$(n).unbind("keydown")}else $("#lui_"+$.grid.coralID(p.options.id)).show(),s=$.extend({},q,s),s[x]=$.grid.stripPref(p.options.idPrefix,s[x]),$.ajax($.extend({url:i.url,data:$.isFunction(p.options.serializeRowData)?p.options.serializeRowData.call(p,s):s,type:i.mtype,async:!1,complete:function(b,c){if($("#lui_"+$.grid.coralID(p.options.id)).hide(),"success"===c){var d,e=!0;if(d=p._trigger("inlineSuccessSaveRow",null,[{res:b,rowId:a,options:i}]),$.isArray(d)||(d=[!0,q]),d[0]&&$.isFunction(i.successfunc)&&(d=i.successfunc.call(p,b)),$.isArray(d)?(e=d[0],q=d[1]?d[1]:q):e=d,e===!0){p.options.autoencode&&$.each(q,function(a,b){q[a]=$.grid.htmlDecode(b)}),q=$.extend({},q,r),$(p.element).grid("setRowData",a,q),$(n).attr("editable","0");for(var f=0;f<p.options.savedRow.length;f++)if(p.options.savedRow[f].id==a){l=f;break}l>=0&&p.options.savedRow.splice(l,1),$(p).triggerHandler("gridInlineAfterSaveRow",[a,b,q,i]),$.isFunction(i.aftersavefunc)&&i.aftersavefunc.call(p,a,b),o=!0,p.options.editrow=null,p.rowEditButtons&&p.rowEditButtons.hide(),p._trigger("afterInlineSaveRow",null,[{rowId:a,options:i,status:A}]),$(n).unbind("keydown")}else $(p).triggerHandler("gridInlineErrorSaveRow",[a,b,c,null,i]),$.isFunction(i.errorfunc)&&i.errorfunc.call(p,a,b,c,null),i.restoreAfterError===!0&&$(p.element).grid("restoreRow",a,i.afterrestorefunc)}},error:function(b,c,d){if($("#lui_"+$.grid.coralID(p.options.id)).hide(),$(p).triggerHandler("gridInlineErrorSaveRow",[a,b,c,d,i]),$.isFunction(i.errorfunc))i.errorfunc.call(p,a,b,c,d);else try{$.grid.info_dialog($.grid.errors.errcap,'<div class="coral-state-error">'+b.responseText+"</div>",$.grid.edit.bClose,{buttonalign:"right"})}catch(e){alert(b.responseText)}i.restoreAfterError===!0&&$(p.element).grid("restoreRow",a,i.afterrestorefunc)}},$.grid.ajaxOptions,p.options.ajaxRowOptions||{}))}return o&&$(n).removeClass("new-row"),o},restoreRow:function(a,b){var c=$.makeArray(arguments).slice(1),d={};"object"===$.type(c[0])?d=c[0]:$.isFunction(b)&&(d.afterrestorefunc=b),d=$.extend(!0,$.grid.inlineEdit,d);var e,f,g=this,h={};if(g.grid&&(f=$(g.element).grid("getInd",a,!0),g.options.editrow&&a==g.options.editrow&&(g.options.editrow=null,g.clearErrors(a)),f!==!1)){for(var i=0;i<g.options.savedRow.length;i++)if(g.options.savedRow[i].id==a){e=i;break}if(e>=0){if($.isFunction($.fn.datepicker))try{$("input.hasDatepicker","#"+$.grid.coralID(f.id)).datepicker("hide")}catch(j){}$.each(g.options.colModel,function(){this.editable===!0&&this.name in g.options.savedRow[e]&&(h[this.name]=g.options.savedRow[e][this.name])}),$(g.element).grid("setRowData",a,h),$(f).attr("editable","0").unbind("keydown"),g.options.savedRow.splice(e,1),$("#"+$.grid.coralID(a),"#"+$.grid.coralID(g.options.id)).hasClass("grid-new-row")&&setTimeout(function(){$(g.element).grid("delRowData",a)},0)}$(g).triggerHandler("gridInlineAfterRestoreRow",[a]),$.isFunction(d.afterrestorefunc)&&d.afterrestorefunc.call(g,a),g.options.rowEditButtons&&g.rowEditButtons.hide()}},addRow:function(a){if(a=$.extend(!0,{rowID:"new_row",initdata:{},position:"first",useDefValues:!0,useFormatter:!1,addRowParams:{extraparam:{}}},a||{}),this.grid){var b=this;if(a.useDefValues===!0&&$(b.options.colModel).each(function(){if(this.editoptions&&this.editoptions.defaultValue){var c=this.editoptions.defaultValue,d=$.isFunction(c)?c.call(b):c;a.initdata[this.name]=d}}),$(b.element).grid("addRowData",a.rowID,a.initdata,a.position),$("#"+$.grid.coralID(a.rowID),"#"+$.grid.coralID(b.options.id)).addClass("grid-new-row"),a.useFormatter)$("#"+$.grid.coralID(a.rowID)+" .coral-inline-edit","#"+$.grid.coralID(b.options.id)).click();else{var c=b.options.prmNames,d=c.oper;a.addRowParams.extraparam[d]=c.addoper,$(b.element).grid("editRow",a.rowID,a.addRowParams),$(b.element).grid("setSelection",a.rowID)}}},clearEdited:function(a){var b=$(this.getInd(a,!0));b.removeClass("edited"),b.children("td").removeClass("dirty-cell")},inlineNav:function(a,b){return b=$.extend({edit:!0,editicon:"coral-icon-pencil",add:!0,addicon:"coral-icon-plus",save:!0,saveicon:"coral-icon-disk",cancel:!0,cancelicon:"coral-icon-cancel",addParams:{useFormatter:!1,rowID:"new_row"},editParams:{},restoreAfterSelect:!0},$.grid.nav,b||{}),this.each(function(){if(this.grid){var c,d=this,e=$.grid.coralID(d.options.id);if(d.options._inlinenav=!0,b.addParams.useFormatter===!0){var f,g=d.options.colModel;for(f=0;f<g.length;f++)if(g[f].formatter&&"actions"===g[f].formatter){if(g[f].formatoptions){var h={keys:!1,onEdit:null,onSuccess:null,afterSave:null,onError:null,afterRestore:null,extraparam:{},url:null},i=$.extend(h,g[f].formatoptions);b.addParams.addRowParams={keys:i.keys,oneditfunc:i.onEdit,successfunc:i.onSuccess,url:i.url,extraparam:i.extraparam,aftersavefunc:i.afterSavef,errorfunc:i.onError,afterrestorefunc:i.afterRestore}}break}}b.add&&$(d.element).grid("navButtonAdd",a,{caption:b.addtext,title:b.addtitle,buttonicon:b.addicon,id:d.options.id+"_iladd",onClickButton:function(){$(d.element).grid("addRow",b.addParams),b.addParams.useFormatter||($("#"+e+"_ilsave").removeClass("coral-state-disabled"),$("#"+e+"_ilcancel").removeClass("coral-state-disabled"),$("#"+e+"_iladd").addClass("coral-state-disabled"),$("#"+e+"_iledit").addClass("coral-state-disabled"))}}),b.edit&&$(d.element).grid("navButtonAdd",a,{caption:b.edittext,title:b.edittitle,buttonicon:b.editicon,id:d.options.id+"_iledit",onClickButton:function(){var a=$(d.element).grid("getGridParam","selrow");a?($(d.element).grid("editRow",a,b.editParams),$("#"+e+"_ilsave").removeClass("coral-state-disabled"),$("#"+e+"_ilcancel").removeClass("coral-state-disabled"),$("#"+e+"_iladd").addClass("coral-state-disabled"),$("#"+e+"_iledit").addClass("coral-state-disabled")):($.grid.viewModal("#alertmod",{gbox:"#gbox_"+e,jqm:!0}),$("#jqg_alrt").focus())}}),b.save&&($(d.element).grid("navButtonAdd",a,{caption:b.savetext||"",title:b.savetitle||"Save row",buttonicon:b.saveicon,id:d.options.id+"_ilsave",onClickButton:function(){var a=d.options.savedRow[0].id;if(a){var c=d.options.prmNames,f=c.oper;b.editParams.extraparam||(b.editParams.extraparam={}),$("#"+$.grid.coralID(a),"#"+e).hasClass("grid-new-row")?b.editParams.extraparam[f]=c.addoper:b.editParams.extraparam[f]=c.editoper,$(d.element).grid("saveRow",a,b.editParams)&&$(d.element).grid("showAddEditButtons")}else $.grid.viewModal("#alertmod",{gbox:"#gbox_"+e,jqm:!0}),$("#jqg_alrt").focus()}}),$("#"+e+"_ilsave").addClass("coral-state-disabled")),b.cancel&&($(d.element).grid("navButtonAdd",a,{caption:b.canceltext||"",title:b.canceltitle||"Cancel row editing",buttonicon:b.cancelicon,id:d.options.id+"_ilcancel",onClickButton:function(){var a=d.options.savedRow[0].id;a?($(d.element).grid("restoreRow",a,b.editParams),$(d.element).grid("showAddEditButtons")):($.grid.viewModal("#alertmod",{gbox:"#gbox_"+e,jqm:!0}),$("#jqg_alrt").focus())}}),$("#"+e+"_ilcancel").addClass("coral-state-disabled")),b.restoreAfterSelect===!0&&(c=!!$.isFunction(d.options.beforeSelectRow)&&d.options.beforeSelectRow,d.options.beforeSelectRow=function(a,e){var f=!0;return d.options.savedRow.length>0&&d.options._inlinenav===!0&&a!==d.options.selrow&&null!==d.options.selrow&&(d.options.selrow==b.addParams.rowID?$(d.element).grid("delRowData",d.options.selrow):$(d.element).grid("restoreRow",d.options.selrow,b.editParams),$(d.element).grid("showAddEditButtons")),c&&(f=c.call(d,a,e)),f})}})},showAddEditButtons:function(){return this.each(function(){if(this.grid){var a=$.grid.coralID(this.options.id);$("#"+a+"_ilsave").addClass("coral-state-disabled"),$("#"+a+"_ilcancel").addClass("coral-state-disabled"),$("#"+a+"_iladd").removeClass("coral-state-disabled"),$("#"+a+"_iledit").removeClass("coral-state-disabled")}})}});