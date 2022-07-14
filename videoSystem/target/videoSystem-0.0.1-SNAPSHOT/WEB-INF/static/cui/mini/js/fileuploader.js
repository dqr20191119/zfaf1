/*! cui 2017-11-03 */
!function(){"use strict";$.blueimp.fileupload.prototype._specialOptions.push("filesContainer","uploadTemplateId","downloadTemplateId"),$.component("blueimp.fileupload",$.blueimp.fileupload,{getUnUploadValues:function(){for(var a=[],b=0;b<this.options.unUploadedFileList.length;b++)a.push(this.options.unUploadedFileList[b][this.options.prmNames.fileId]);return a},remove:function(a){function b(a){var b=$("#"+a).data("data");$("#"+a).hasClass("template-download")?c._trigger("remove",null,$.extend({context:$("#"+a),id:a,type:"DELETE"},$("#"+a).data())):$("#"+a).hasClass("template-upload")&&(b.abort?b.abort():(b.errorThrown="abort",c._trigger("fail",null,$.extend({context:$("#"+a),type:"DELETE"},b))))}var c=this;if(void 0===a)this.options.filesContainer.children().each(function(){b(this.id)});else if($.isArray(a))for(var d=0;d<a.length;d++)b(a[d]);else b(a);this._trigger("onDelete",null)},destroy:function(){this.element.find(".fileupload-buttonbar").find(".fileinput-button").each(function(){var a=$(this).find("input:file").detach();$(this).button("destroy").append(a)}),this.element.find(".ctrl-init-button").remove(),this.options.filesContainer.remove(),this._super()},upload:function(a){if($.isArray(a))for(var b=0;b<a.length;b++){var c=$("#"+a[b]).data("data");c&&c.submit&&c.submit()}else{var c=$("#"+a).data("data");c&&c.submit&&c.submit()}}}),$.component("coral.fileuploader",{castProperties:["triggers","uploadBtnOptions","formData","downloadTmp"],checkIE:function(){for(var a=3,b=document.createElement("div"),c=b.getElementsByTagName("i");b.innerHTML="<!--[if gt IE "+ ++a+"]><i></i><![endif]-->",c[0];);return a>4&&a},options:{prmNames:{fileName:"name",fileSize:"size",fileURL:"url",fileError:"error",fileId:"id",filetype:"type",fileDate:"date",thumbnailUrl:"thumbnailUrl"},focus:null,clearError:null,triggers:null,postData:{},autoUpload:!1,removeCompleted:!1,maxFileSize:"5012kb",minFileSize:"0kb",separator:",",filesUrl:null,filesLimt:9999,multiple:"multiple",uploadBtnOptions:{icons:"",label:"+"}},_getConfig:function(){var a,b=this,c=this.checkIE(),d=this.options;d.queueID?(d.filesContainer=$("#"+d.queueID),d.filesContainer.addClass("files")):(d.queueID=this.element[0].id+"_queueID",d.filesContainer=$("<ul/>",{"class":"files",id:d.queueID}),this.element.before(d.filesContainer)),"list"==d.queueMode&&d.filesContainer.addClass("list-files"),"card"==d.queueMode&&d.filesContainer.addClass("card-files"),"card"!==d.queueMode&&"list"!==d.queueMode&&d.filesContainer.addClass(d.queueMode),d.templatesContainer=this.document[0].createElement(d.filesContainer.prop("nodeName"));var e={getObjectURL:function(a){var b=null;return void 0!=window.createObjectURL?b=window.createObjectURL(a):void 0!=window.URL?b=window.URL.createObjectURL(a):void 0!=window.webkitURL&&(b=window.webkitURL.createObjectURL(a)),b},uploadTmp:function(a){var b,c,d="";b=a.fileError?'<div class="fileError"><span class="error"></span></div>':"";var e=/png|jpg|bmp|gif|jpeg/i;return d=1==e.test(a.fileType)&&a.fileThumbnailUrl?'<span class="fileThumb"> <img src= "{{fileThumbnailUrl}}"></span>':'<span class="fileThumb">{{fileType}}</span>',c='<li id="{{fileId}}" class="fileItem"><div class="fileContent">'+d+'<div class="progress"><div class="progressbar-value"></div></div><span class="fileName" title="{{fileName}}">{{fileName}}</span><span class="fileSize">({{fileSize}})k</span><span class="fileAction"><span class="progressbar-text"></span><span class="upload cui-icon-plus-circle2"></span><span class="remove cui-icon-minus-circle2"></span></span>'+b+"</div></li>"},uploadTemplate:function(a){var b="fileId_"+(new Date).getTime(),c=a.options.prmNames;a.options.unUploadedFileList=a.options.unUploadedFileList||[];var d,e,f=a.options.autoUpload,g={fileName:a.files[0][c.fileName],fileSize:a.files[0][c.fileSize],fileId:a.files[0][c.fileId]||b,fileError:a.files[0][c.fileError],fileType:a.files[0][c.filetype],fileDate:a.files[0][c.fileDate]};a.files[0].id=g.fileId,g.fileType=g.fileName.substring(g.fileName.lastIndexOf(".")+1),g.fileThumbnailUrl=a.options.getObjectURL(a.files[0]),a.options.unUploadedFileList.push(g),f||(d="disabled"),e=a.options.uploadTmp(g);for(var h in g)e=e.replace(new RegExp("\\{\\{"+h+"\\}\\}","g"),g[h]);return e},downloadTmp:function(a){var b,c,d="";return b=a.fileError?'<div class="fileError"><span class="error"></span>{{fileError}}</div>':"",d=a.thumbnailUrl?'<span class="fileThumb"> <img src= "{{thumbnailUrl}}"></span>':'<span class="fileThumb">{{fileType}}</span>',c='<li id="{{fileId}}" data-url="{{fileUrl}}" class="fileItem"><div class="fileContent">'+d+'<div class="progress"><div class="progressbar-value"></div></div><span class="fileName"><a href="javascript:void(0)" title="{{fileName}}" download="{{fileName}}">{{fileName}}</a></span><span class="fileSize">({{fileSize}})k</span><span class="remove cui-icon-minus-circle2 fileAction"></span></span>'+b+"</div></li>"},downloadTemplate:function(a){var b=a.options.prmNames;a.options.uploadedFileList=a.options.uploadedFileList||[];var c,d="fileId_"+(new Date).getTime(),e=(a.options.autoUpload,{fileName:a.files[0][b.fileName],fileSize:a.files[0][b.fileSize],fileUrl:a.files[0][b.fileURL],fileError:a.files[0][b.fileError],fileId:a.files[0][b.fileId]||d,fileType:a.files[0][b.filetype],fileDate:a.files[0][b.fileDate],thumbnailUrl:a.files[0][b.thumbnailUrl]});a.options.uploadedFileList.push(e),c=a.options.downloadTmp(e);for(var f in e)c=c.replace(new RegExp("\\{\\{"+f+"\\}\\}","g"),e[f]);return c}};if(this.options=$.extend(!0,{},e,this.options),this.useFlash=c!==!1&&c<10,this.options.uploadBtn){var f="fileinput-button",g=$.coral.toFunction(this.options.uploadBtnOptions);g=g.cls?$.extend({},g,{cls:f+" "+g.cls}):$.extend({},g,{cls:f}),$(this.options.uploadBtn).button(g);var h={type:"file",name:"uploadFile"};this.options.multiple&&(h.multiple="multiple"),this.options.fileObjName&&(h.name=this.options.fileObjName),$("<input/>",h).appendTo($(this.options.uploadBtn)),this.uploadFile=$(this.options.uploadBtn).find("input[type=file]"),this.uploaderBtn=$(this.options.uploadBtn).uniqueId()}else this.uploadFile=this.element.find("input[type=file]"),this.uploaderBtn=this.element.find(".fileinput-button").uniqueId();this.useFlash?(this.uploadFile.hide(),a={swf:$.coral.scriptPath+"external/swfupload.swf",uploader:this.options.url,auto:this.options.autoUpload,messages:{uploadedBytes:"Uploaded bytes exceed file size",maxNumberOfFiles:"上传的文件数量超出限制",acceptFileTypes:"选择的文件类型不符合",maxFileSize:"上传文件的大小超出最大限制",minFileSize:"上传文件的大小低于最小限制"},separator:this.options.separator,checkExisting:!1,debug:this.options.debug,fileObjName:"uploadFile",height:130,uploadTemplate:this.options.uploadTemplate,downloadTemplate:this.options.downloadTemplate,method:"post",multi:this.options.multi,formData:this.options.formData,preventCaching:!0,progressData:"percentage",queueID:this.options.queueID,queueSizeLimit:999,removeCompleted:this.options.removeCompleted,removeTimeout:this.options.removeTimeout,requeueErrors:!1,successTimeout:30,uploadLimit:this.options.filesLimt,width:820,maxFileSize:this.options.maxFileSize,minFileSize:this.options.minFileSize,fileTypeExts:this.options.acceptFileTypes,prependFiles:this.options.prependFiles,onSWFReady:function(){b.options.filesUrl&&$.ajax({type:"POST",async:!1,datatype:"json",url:b.options.filesUrl,data:b.options.postData,success:function(a){for(var c=0;c<a.length;c++){var d=b._renderDownload([a[c]]).appendTo(b.options.filesContainer);b._transition(d),b.uploaderBtn.swfuploader("addUploadCount"),b.setValue(b.options.uploadedFileList)}},error:function(a){}})},overrideEvents:[]},$.extend(!0,a,this.options),this.uploaderBtn.swfuploader(a)):(a={disabled:!1,autoUpload:!1,url:this.options.url,maxFileSize:this.options.maxFileSize,minFileSize:this.options.minFileSize,maxNumberOfFiles:this.options.maxNumberOfFiles,acceptFileTypes:this.options.acceptFileTypes,uploadTemplate:this.options.uploadTemplate,downloadTemplate:this.options.downloadTemplate,templatesContainer:this.options.templatesContainer,filesContainer:this.options.filesContainer,prependFiles:!1,formData:this.options.formData,dataType:"json",separator:",",messages:{unknownError:"Unknown error",uploadedBytes:"Uploaded bytes exceed file size",maxNumberOfFiles:"上传的文件数量超出限制",acceptFileTypes:"选择的文件类型不符合",maxFileSize:"上传文件的大小超出最大限制",minFileSize:"上传文件的大小低于最小限制"},processdone:function(a,b){b.context.find(".upload").button("enable")},required:!0,getNumberOfFiles:function(){return this.filesContainer.children().not(".processing").length},getFilesFromResponse:function(a){return"string"==typeof a.result&&(a.result=$.parseJSON(a.result)),a.result&&$.isArray(a.result.files)?a.result.files:[]},add:function(a,c){if(a.isDefaultPrevented())return!1;var d=$(this),e=d.data("blueimp-fileupload"),f=e.options;b.validateFile(f,c)&&(d.fileupload("process",c),c.context=b._renderUpload(c.files).data("data",c).addClass("processing"),f.filesContainer[f.prependFiles?"prepend":"append"](c.context),b._forceReflow(c.context),b._transition(c.context),c.process(function(){return d.fileupload("process",c)}).always(function(){c.context.each(function(a){$(this).find(".size").text(b._formatFileSize(c.files[a].size))}).removeClass("processing")}).done(function(){c.context.find(".upload").prop("disabled",!1),b._trigger("onSelect",a,[{file:c.files[0]}])!==!1&&(f.autoUpload||c.autoUpload)&&c.autoUpload!==!1&&c.submit()}).fail(function(){c.files.error&&c.context.each(function(a){var b=c.files[a].error;b&&$(this).find(".error").text(b)})}))},done:function(a,c){if(a.isDefaultPrevented())return!1;var d,e,f=$(this).data("blueimp-fileupload"),g=c.getFilesFromResponse||f.options.getFilesFromResponse,h=g(c);c.context?c.context.each(function(f){var g=h[f]||{error:"Empty file upload result"};e=b._addFinishedDeferreds(),b._transition($(this)).done(function(){var f=$(this);d=b._renderDownload([g]).data("data",c).replaceAll(f),b._forceReflow(d),b._transition(d).done(function(){c.context=$(this),b._trigger("onSuccess",a,[{file:g}]),b._trigger("onComplete",a,[{file:g}]),e.resolve()})})}):(d=b._renderDownload(h)[f.options.prependFiles?"prependTo":"appendTo"](f.options.filesContainer),b._forceReflow(d),e=b._addFinishedDeferreds(),b._transition(d).done(function(){c.context=$(this),b._trigger("onSuccess",a,[{file:file}]),b._trigger("onComplete",a,[{file:file}]),e.resolve()}));var i=b.options.unUploadedFileList;b.clearFileList(i,c)},stop:function(a){if(a.isDefaultPrevented())return!1;var c=($(this).data("blueimp-fileupload"),b._addFinishedDeferreds());$.when.apply($,b._getFinishedDeferreds()).done(function(){b._trigger("onStop",a)}),b._transition($(this).find(".fileupload-progress")).done(function(){$(this).find(".progress").attr("aria-valuenow","0").children().first().css("width","0%"),$(this).find(".progress-extended").html("&nbsp;"),c.resolve()})},processstart:function(a){return!a.isDefaultPrevented()&&void $(this).addClass("fileupload-processing")},processstop:function(a){return!a.isDefaultPrevented()&&void $(this).removeClass("fileupload-processing")},fail:function(a,c){if(a.isDefaultPrevented())return!1;var d,e,f=$(this).data("blueimp-fileupload");c.context?c.context.each(function(f){if("abort"!==c.errorThrown){var g=c.files[f];g.error=g.error||c.errorThrown||c.i18n("unknownError"),e=b._addFinishedDeferreds(),b._transition($(this)).done(function(){var f=$(this);d=b._renderDownload([g]).replaceAll(f),b._forceReflow(d),b._transition(d).done(function(){c.context=$(this),b._trigger("onFail",a,[{file:g,error:g.error}]),b._trigger("onComplete",a,[{file:g}]),e.resolve()})})}else e=b._addFinishedDeferreds(),b._transition($(this)).done(function(){$(this).remove(),b._trigger("onRemove",a),e.resolve()})}):"abort"!==c.errorThrown?(c.context=b._renderUpload(c.files)[f.options.prependFiles?"prependTo":"appendTo"](f.options.filesContainer).data("data",c),b._forceReflow(c.context),e=b._addFinishedDeferreds(),b._transition(c.context).done(function(){c.context=$(this),b._trigger("onFail",a,[{file:c.files[0],error:c.files[0].error}]),b._trigger("onComplete",a,[{file:c.files[0]}]),e.resolve()})):(b._trigger("onFail",a,[{file:c.files[0],error:c.files[0].error}]),b._trigger("onComplete",a,[{file:c.files[0]}]),b._addFinishedDeferreds().resolve());var g=b.options.uploadedFileList;b.clearFileList(g,c);var h=b.options.unUploadedFileList;b.clearFileList(h,c)},progress:function(a,c){if(a.isDefaultPrevented())return!1;var d=Math.floor(c.loaded/c.total*100);c.context&&c.context.each(function(){$(this).find(".progressbar-value").show().css("width",d+"%"),$(this).find(".progressbar-text").text(d+"%")}),b._trigger("onProgress",a,[{file:c.files[0]}])},progressall:function(a,c){if(a.isDefaultPrevented())return!1;var d=$(this),e=(Math.floor(c.loaded/c.total*100),d.find(".fileupload-progress")),f=e.find(".progress-extended");f.length&&f.html(b._renderExtendedProgress(c))},start:function(a){if(a.isDefaultPrevented())return!1;$(this).data("blueimp-fileupload");b._resetFinishedDeferreds(),b._transition($(this).find(".fileupload-progress")).done(function(){b._trigger("onStart",a)})},send:function(a,c){return!a.isDefaultPrevented()&&(c.context&&c.dataType&&"iframe"===c.dataType.substr(0,6)&&c.context.find(".progress").addClass(!$.support.transition&&"progress-animated").attr("aria-valuenow",100).children().first().css("width","100%"),b._trigger("onSend",a,[{file:c.files[0]}]))},remove:function(a,c){if(a.isDefaultPrevented())return!1;var d,e=$(this).data("blueimp-fileupload"),f=function(){b._transition(c.context).done(function(){$(this).remove(),b._trigger("onRemove",a,{fileId:c.id})})};if(c.url&&"undefined"!=c.url){var g={};c.data=[];for(var h=0;h<b.options.uploadedFileList.length;h++)c.id==b.options.uploadedFileList[h].fileId&&(g.data=b.options.uploadedFileList[h]);g.dataType=c.dataType||e.options.dataType,g.url=c.url,$.ajax(g).done(f).fail(function(){b._trigger("onRemoveFailed",a)})}else f();var d=b.options.uploadedFileList;b.clearFileList(d,c)}},$.extend(!0,a,this.options),this.uploaderBtn.fileupload(a),this.options.filesUrl&&$.ajax({type:"POST",async:!1,datatype:"json",url:this.options.filesUrl,data:this.options.postData,success:function(a){for(var c=0;c<a.length;c++){var d=b._renderDownload([a[c]]).appendTo(b.options.filesContainer);b._transition(d)}},error:function(a){}}))},upload:function(a){this.checkIE();this.useFlash?this.uploaderBtn.swfuploader("upload",a):(void 0===a&&(a=this.getUnUploadValues()),this.uploaderBtn.fileupload("upload",a))},_destroy:function(){this.checkIE();this.useFlash?this.uploaderBtn.swfuploader("destroy"):this.uploaderBtn.fileupload("destroy")},remove:function(a){this.checkIE();this.useFlash?this.uploaderBtn.swfuploader("cancel",a):this.uploaderBtn.fileupload("remove",a)},_resetFinishedDeferreds:function(){this._finishedUploads=[]},_addFinishedDeferreds:function(a){return a||(a=$.Deferred()),this._finishedUploads.push(a),a},_getFinishedDeferreds:function(){return this._finishedUploads},_enableDragToDesktop:function(){var a=$(this),b=a.prop("href"),c=a.prop("download"),d="application/octet-stream";a.bind("dragstart",function(a){try{a.originalEvent.dataTransfer.setData("DownloadURL",[d,c,b].join(":"))}catch(e){}})},_formatFileSize:function(a){return"number"!=typeof a?"":a>=1e9?(a/1e9).toFixed(2)+" GB":a>=1e6?(a/1e6).toFixed(2)+" MB":(a/1e3).toFixed(2)+" KB"},_formatBitrate:function(a){return"number"!=typeof a?"":a>=1e9?(a/1e9).toFixed(2)+" Gbit/s":a>=1e6?(a/1e6).toFixed(2)+" Mbit/s":a>=1e3?(a/1e3).toFixed(2)+" kbit/s":a.toFixed(2)+" bit/s"},_formatTime:function(a){var b=new Date(1e3*a),c=Math.floor(a/86400);return c=c?c+"d ":"",c+("0"+b.getUTCHours()).slice(-2)+":"+("0"+b.getUTCMinutes()).slice(-2)+":"+("0"+b.getUTCSeconds()).slice(-2)},_formatPercentage:function(a){return(100*a).toFixed(2)+" %"},_renderExtendedProgress:function(a){return this._formatBitrate(a.bitrate)+" | "+this._formatTime(8*(a.total-a.loaded)/a.bitrate)+" | "+this._formatPercentage(a.loaded/a.total)+" | "+this._formatFileSize(a.loaded)+" / "+this._formatFileSize(a.total)},_renderTemplate:function(a,b){if(!a)return $();var c=a({files:b,formatFileSize:this._formatFileSize,options:this.options});return c instanceof $?c:$(this.options.templatesContainer).html(c).children()},_renderPreviews:function(a){a.context.find(".preview").each(function(b,c){$(c).append(a.files[b].preview)})},_renderUpload:function(a,b){var c=this._renderTemplate(this.options.uploadTemplate,a);return c.addClass("template-upload fade"),c.hasClass("fade")&&c.hide(),this._trigger("onRenderUploadTmp",null,{item:c}),c},_renderDownload:function(a){var b=this._renderTemplate(this.options.downloadTemplate,a).find("a[download]").each(this._enableDragToDesktop).end();return b.addClass("template-download fade"),b.hasClass("fade")&&b.hide(),this._trigger("onRenderDownloadTmp",null,{item:b}),b},_forceReflow:function(a){return $.support.transition&&a.length&&a[0].offsetWidth},_transition:function(a){var b=$.Deferred();return a.hasClass("fade")?a.fadeToggle(this.options.transitionDuration,this.options.transitionEasing,function(){b.resolveWith(a)}):b.resolveWith(a),b},_destroyButtonBarEventHandlers:function(){this._off(this.element.find(".fileupload-buttonbar").find(".upload, .remove"),"click"),this._off(this.element.find(".fileupload-buttonbar .toggle"),"change.")},_destroyEventHandlers:function(){this._destroyButtonBarEventHandlers(),this._off(this.options.filesContainer,"click"),this._super()},_enableFileInputButton:function(){this.element.find(".fileinput-button input").prop("disabled",!1).parent().removeClass("disabled")},_disableFileInputButton:function(){this.element.find(".fileinput-button input").prop("disabled",!0).parent().addClass("disabled")},_create:function(){var a=this.options;this.options.id=this.element.uniqueId().attr("id"),this._super(),this._resetFinishedDeferreds(),$.support.fileInput||this._disableFileInputButton(),this.element.find(".fileupload-buttonbar").find(".fileinput-button").each(function(){}),a.uploadedFileList=[],a.unUploadedFileList=[],this._getConfig(),a.beforeCreate?a.beforeCreate.apply(this):this.beforeCreate()},beforeCreate:function(){var a=this;this._on(this.options.filesContainer,{"click .upload":function(b){b.preventDefault();var c=$(b.currentTarget).closest(".template-upload");$("#"+a.element[0].id).fileuploader("upload",c[0].id)},"click .remove":function(b){b.preventDefault();var c=$(b.currentTarget).closest(".template-upload,.template-download");$("#"+a.element[0].id).fileuploader("remove",c[0].id)},"click .stop":function(b){b.preventDefault();var c=$(b.currentTarget).closest(".template-upload");$("#"+a.element[0].id).fileuploader("stop",c[0].id)}})},_setOption:function(a,b){this._super(a,b),this.useFlash&&this.uploaderBtn.swfuploader("option",a,b),"formData"===a&&(this.useFlash?this.uploaderBtn.swfuploader("option","formData",b):this.uploaderBtn.fileupload("option","formData",b))},clearFileList:function(a,b){var c,d;a===this.options.uploadedFileList?c=this.getValues():a===this.options.unUploadedFileList&&(c=this.getUnUploadValues()),d=$.inArray(b.context[0][this.options.prmNames.fileId],c),d!==-1?a.splice(d,1):a.splice(d,0)},validateFile:function(a,b){var c=b.files[0].name.substring(b.files[0].name.lastIndexOf(".")+1);c=$.trim(c);var d=$.trim(a.acceptFileTypes),e=this.getQueueData(),f=this.formatMaxSize(a.maxFileSize);return e.length>=a.filesLimt?($.messageQueue({message:a.messages.maxNumberOfFiles}),!1):b.files[0].size>=f?($.messageQueue({message:a.messages.maxFileSize}),!1):"*.*"==d||(d.indexOf(c.toLowerCase())!=-1||($.messageQueue({message:a.messages.acceptFileTypes}),!1))},formatMinSize:function(a){var b,c,d=new RegExp("/^s*|s*$/"),e=1024;a=a.toLowerCase(),a=a.replace(d,"");var f=a.match(/^\d+/);return null!==f&&f.length>0&&(c=parseInt(f[0])),(isNaN(c)||c<0)&&(c=0),b=c*e},formatMaxSize:function(a){var b,c,d=new RegExp("/^s*|s*$/"),e=1024;a=a.toLowerCase(),a=a.replace(d,"");var f=a.match(/^\d+/);return null!==f&&f.length>0&&(c=parseInt(f[0])),(isNaN(c)||c<0)&&(c=0),b=c*e},getQueueData:function(){for(var a=[],b=[],c=0;c<this.options.uploadedFileList.length;c++)a.push(this.options.uploadedFileList[c].fileId);for(var c=0;c<this.options.unUploadedFileList.length;c++)b.push(this.options.unUploadedFileList[c].fileId);return $.merge(a,b)},getValues:function(){for(var a=[],b=0;b<this.options.uploadedFileList.length;b++)a.push(this.options.uploadedFileList[b].fileId);return a},getValidateValue:function(){this.checkIE();return this.useFlash?this.uploaderBtn.swfuploader("getValidateValue"):this.getValue()},setValue:function(a){this.checkIE();this.useFlash&&this.uploaderBtn.swfuploader("setValues",a)},getValue:function(){this.checkIE();return this.useFlash?this.uploaderBtn.swfuploader("getValue"):this.getValues().join(this.options.separator)},clearError:function(){},focus:function(){},getUnUploadValues:function(){for(var a=[],b=0;b<this.options.unUploadedFileList.length;b++)a.push(this.options.unUploadedFileList[b].fileId);return a},enable:function(){this.checkIE();if(this.useFlash)this.uploaderBtn.swfuploader("disable",!1);else{var a=!1;this.options.disabled&&(a=!0),this._super(),a&&(this.element.find("input, button").prop("disabled",!1),this._enableFileInputButton(),this.options.filesContainer.prop("disabled",!1).removeClass("coral-state-disabled")),this.options.disabled=!1,this._trigger("onEnable",null,[])}},disable:function(){this.checkIE();this.useFlash?this.uploaderBtn.swfuploader("disable",!0):(this.options.disabled||(this.element.find("input, button").prop("disabled",!0),this._disableFileInputButton(),this.options.filesContainer.prop("disabled",!0).addClass("coral-state-disabled")),this._super(),this.options.disabled=!0,this._trigger("onDisable",null,[]))}})}();