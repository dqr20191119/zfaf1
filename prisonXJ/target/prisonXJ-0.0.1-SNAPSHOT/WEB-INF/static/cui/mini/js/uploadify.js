/*! cui 2017-11-03 */
!function(){var a={init:function(a,c){return this.each(function(){var d=$(this),e=d.clone(),f=$.extend({id:d.attr("id"),swf:$.coral.scriptPath+"external/swfupload.swf",uploader:"swfupload.php",auto:!0,buttonClass:"",buttonCursor:"hand",buttonImage:null,buttonText:"SELECT FILES",checkExisting:!1,debug:!1,fileObjName:"Filedata",fileSizeLimit:0,fileTypeDesc:"All Files",fileTypeExts:"*.*",height:30,itemTemplate:!1,method:"post",multi:!0,formData:{},preventCaching:!0,progressData:"percentage",queueID:!1,queueSizeLimit:999,removeCompleted:!0,removeTimeout:3,requeueErrors:!1,successTimeout:30,uploadLimit:0,width:120,overrideEvents:[]},a),g={assume_success_timeout:f.successTimeout,button_placeholder_id:f.id,button_width:f.width,button_height:f.height,button_text:null,button_text_style:null,button_text_top_padding:0,button_text_left_padding:0,button_action:f.multi?SWFUpload.BUTTON_ACTION.SELECT_FILES:SWFUpload.BUTTON_ACTION.SELECT_FILE,button_disabled:!1,button_cursor:"arrow"==f.buttonCursor?SWFUpload.CURSOR.ARROW:SWFUpload.CURSOR.HAND,button_window_mode:SWFUpload.WINDOW_MODE.TRANSPARENT,debug:f.debug,requeue_on_error:f.requeueErrors,file_post_name:f.fileObjName,file_size_limit:f.fileSizeLimit,file_types:f.fileTypeExts,file_types_description:f.fileTypeDesc,file_queue_limit:f.queueSizeLimit,file_upload_limit:f.uploadLimit,flash_url:f.swf,prevent_swf_caching:f.preventCaching,post_params:f.formData,upload_url:f.uploader,use_query_string:"get"==f.method,file_dialog_complete_handler:b.onDialogClose,file_dialog_start_handler:b.onDialogOpen,file_queued_handler:b.onSelect,file_queue_error_handler:b.onSelectError,swfupload_loaded_handler:f.onSWFReady,upload_complete_handler:b.onUploadComplete,upload_error_handler:b.onUploadError,upload_progress_handler:b.onUploadProgress,upload_start_handler:b.onUploadStart,upload_success_handler:b.onUploadSuccess};c&&(g=$.extend(g,c)),g=$.extend(g,f);var h=swfobject.getFlashPlayerVersion(),i=h.major>=9;if(i){window["uploadify_"+f.id]=new SWFUpload(g);var j=window["uploadify_"+f.id];d.data("uploadify",j);var k=$("<div />",{id:f.id,"class":"uploadify",css:{height:f.height+"px",width:f.width+"px"}});$("#"+j.movieName).wrap(k),k=$("#"+f.id),k.data("uploadify",j);var l=$("<div />",{id:f.id+"-button","class":"uploadify-button "+f.buttonClass});if(f.buttonImage&&l.css({"background-image":"url('"+f.buttonImage+"')","text-indent":"-9999px"}),l.html('<span class="uploadify-button-text">'+f.buttonText+"</span>").css({height:f.height+"px","line-height":f.height+"px",width:f.width+"px"}),k.append(l),$("#"+j.movieName).css({position:"absolute","z-index":1}),!f.queueID){var m=$("<div />",{id:f.id+"-queue","class":"uploadify-queue"});k.after(m),j.settings.queueID=f.id+"-queue",j.settings.defaultQueue=!0}j.queueData={files:{},filesSelected:0,filesQueued:0,filesReplaced:0,filesCancelled:0,filesErrored:0,uploadsSuccessful:0,uploadsErrored:0,averageSpeed:0,queueLength:0,queueSize:0,uploadSize:0,queueBytesUploaded:0,uploadQueue:[],errorMsg:"Some files were not added to the queue:"},j.original=e,j.wrapper=k,j.button=l,j.queue=m,f.onInit&&f.onInit.call(d,j)}else{f.onFallback&&f.onFallback.call(d);var n=f.onNoflash.call(d),o=$("<div class='coral-uploader-button' style='height: 40px;'></div>").appendTo(n),p=$("<input type='button'/>").appendTo(o);p.button({id:f.id+"-button",label:f.buttonText,onClick:function(){alert("没有安装flash或flash版本过低")}})}})},cancel:function(a,b){var c=arguments;this.each(function(){var b=$(this),d=b.data("uploadify"),e=d.settings,f=-1;if(c[0])if("*"==c[0]){var g=d.queueData.queueLength;$("#"+e.queueID).find(".uploadify-queue-item").each(function(){f++,c[1]===!0?d.cancelUpload($(this).attr("id"),!1):d.cancelUpload($(this).attr("id")),$(this).find(".data").removeClass("data"),$(this).find(".uploadify-progress-bar").remove(),$(this).delay(1e3+100*f).fadeOut(500,function(){$(this).remove()}),delete d.queueData.files[a]}),d.queueData.queueSize=0,d.queueData.queueLength=0,e.onClearQueue&&e.onClearQueue.call(b,g)}else{for(var h=0;h<c.length;h++)d.cancelUpload(c[h]),$("#"+c[h]).find(".data").removeClass("data"),$("#"+c[h]).find(".uploadify-progress-bar").remove(),$("#"+c[h]).delay(1e3+100*h).fadeOut(500,function(){$(this).remove()}),delete d.queueData.files[a];e.onClearQueue&&e.onClearQueue.call(b,g)}else{var i=$("#"+e.queueID).find(".uploadify-queue-item").get(0);$item=$(i),d.cancelUpload($item.attr("id")),$item.find(".data").removeClass("data"),$item.find(".uploadify-progress-bar").remove(),$item.delay(1e3).fadeOut(500,function(){$(this).remove()}),delete d.queueData.files[$item.attr("id")]}})},destroy:function(){this.each(function(){var a=$(this),b=a.data("uploadify"),c=b.settings;b.destroy(),c.defaultQueue&&$("#"+c.queueID).remove(),$("#"+c.id).replaceWith(b.original),c.onDestroy&&c.onDestroy.call(this),delete b})},disable:function(a){this.each(function(){var b=$(this),c=b.data("uploadify"),d=c.settings;a?(c.button.addClass("disabled"),d.onDisable&&d.onDisable.call(this)):(c.button.removeClass("disabled"),d.onEnable&&d.onEnable.call(this)),c.setButtonDisabled(a)})},settings:function(a,b,c){var d=arguments,e=b;if(this.each(function(){var f=$(this),g=f.data("uploadify"),h=g.settings;if("object"==typeof d[0])for(var i in b)setData(i,b[i]);if(1===d.length)e=h[a];else{switch(a){case"uploader":g.setUploadURL(b);break;case"formData":c||(b=$.extend(h.formData,b)),g.setPostParams(h.formData);break;case"method":"get"==b?g.setUseQueryString(!0):g.setUseQueryString(!1);break;case"fileObjName":g.setFilePostName(b);break;case"fileTypeExts":g.setFileTypes(b,h.fileTypeDesc);break;case"fileTypeDesc":g.setFileTypes(h.fileTypeExts,b);break;case"fileSizeLimit":g.setFileSizeLimit(b);break;case"uploadLimit":g.setFileUploadLimit(b);break;case"queueSizeLimit":g.setFileQueueLimit(b);break;case"buttonImage":g.button.css("background-image",settingValue);break;case"buttonCursor":"arrow"==b?g.setButtonCursor(SWFUpload.CURSOR.ARROW):g.setButtonCursor(SWFUpload.CURSOR.HAND);break;case"buttonText":$("#"+h.id+"-button").find(".uploadify-button-text").html(b);break;case"width":g.setButtonDimensions(b,h.height);break;case"height":g.setButtonDimensions(h.width,b);break;case"multi":b?g.setButtonAction(SWFUpload.BUTTON_ACTION.SELECT_FILES):g.setButtonAction(SWFUpload.BUTTON_ACTION.SELECT_FILE)}h[a]=b}}),1===d.length)return e},stop:function(){this.each(function(){var a=$(this),b=a.data("uploadify");b.queueData.averageSpeed=0,b.queueData.uploadSize=0,b.queueData.bytesUploaded=0,b.queueData.uploadQueue=[],b.stopUpload()})},upload:function(){var a=arguments;this.each(function(){var b=$(this),c=b.data("uploadify");if(c.queueData.averageSpeed=0,c.queueData.uploadSize=0,c.queueData.bytesUploaded=0,c.queueData.uploadQueue=[],a[0])if("*"==a[0])c.queueData.uploadSize=c.queueData.queueSize,c.queueData.uploadQueue.push("*"),c.startUpload();else{for(var d=0;d<a.length;d++)c.queueData.uploadSize+=c.queueData.files[a[d]].size,c.queueData.uploadQueue.push(a[d]);c.startUpload(c.queueData.uploadQueue.shift())}else c.startUpload()})}},b={onDialogOpen:function(){var a=this.settings;this.queueData.errorMsg="Some files were not added to the queue:",this.queueData.filesReplaced=0,this.queueData.filesCancelled=0,a.onDialogOpen&&a.onDialogOpen.call(this)},onDialogClose:function(a,b,c){var d=this.settings;this.queueData.filesErrored=a-b,this.queueData.filesSelected=a,this.queueData.filesQueued=b-this.queueData.filesCancelled,this.queueData.queueLength=c,$.inArray("onDialogClose",d.overrideEvents)<0&&this.queueData.filesErrored>0,d.onDialogClose&&d.onDialogClose.call(this,this.queueData),d.auto&&$("#"+d.id).uploadify("upload","*")},onSelect:function(a){var b=this.settings,c={};for(var d in this.queueData.files)if(c=this.queueData.files[d],1!=c.uploaded&&c.name==a.name){var e=confirm('The file named "'+a.name+'" is already in the queue.\nDo you want to replace the existing item in the queue?');if(!e)return this.cancelUpload(a.id),this.queueData.filesCancelled++,!1;$("#"+c.id).remove(),delete this.queueData.files[d],this.cancelUpload(c.id),this.queueData.filesReplaced++}var f=Math.round(a.size/1024),g="KB";f>1e3&&(f=Math.round(f/1e3),g="MB");var h=f.toString().split(".");f=h[0],h.length>1&&(f+="."+h[1].substr(0,2)),f+=g;var i=a.name;if(i.length>25&&(i=i.substr(0,25)+"..."),itemData={fileID:a.id,instanceID:b.id,fileName:i,fileSize:f},0==b.itemTemplate&&(b.itemTemplate='<div id="${fileID}" class="uploadify-queue-item">\t\t\t\t\t<div class="cancel">\t\t\t\t\t\t<a href="javascript:$(\'#${instanceID}\').uploadify(\'cancel\', \'${fileID}\')">X</a>\t\t\t\t\t</div>\t\t\t\t\t<span class="fileName">${fileName} (${fileSize})</span><span class="data"></span>\t\t\t\t\t<div class="uploadify-progress">\t\t\t\t\t\t<div class="uploadify-progress-bar"><!--Progress Bar--></div>\t\t\t\t\t</div>\t\t\t\t</div>'),$.inArray("onSelect",b.overrideEvents)<0){itemHTML=b.itemTemplate;for(var j in itemData)itemHTML=itemHTML.replace(new RegExp("\\$\\{"+j+"\\}","g"),itemData[j]);$("#"+b.queueID).append(itemHTML)}this.queueData.queueSize+=a.size,this.queueData.files[a.id]=a,b.onSelect&&b.onSelect.apply(this,arguments)},onSelectError:function(a,b,c){var d=this.settings;if($.inArray("onSelectError",d.overrideEvents)<0)switch(b){case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:d.queueSizeLimit>c?this.queueData.errorMsg+="\nThe number of files selected exceeds the remaining upload limit ("+c+").":this.queueData.errorMsg+="\nThe number of files selected exceeds the queue size limit ("+d.queueSizeLimit+").";break;case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:this.queueData.errorMsg+='\nThe file "'+a.name+'" exceeds the size limit ('+d.fileSizeLimit+").";break;case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:this.queueData.errorMsg+='\nThe file "'+a.name+'" is empty.';break;case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:this.queueData.errorMsg+='\nThe file "'+a.name+'" is not an accepted file type ('+d.fileTypeDesc+")."}b!=SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED&&delete this.queueData.files[a.id],d.onSelectError&&d.onSelectError.apply(this,arguments)},onQueueComplete:function(){this.settings.onQueueComplete&&this.settings.onQueueComplete.call(this,this.settings.queueData)},onUploadComplete:function(a){var b=this.settings,c=this,d=this.getStats();if(this.queueData.queueLength=d.files_queued,"*"==this.queueData.uploadQueue[0]?this.queueData.queueLength>0?this.startUpload():(this.queueData.uploadQueue=[],b.onQueueComplete&&b.onQueueComplete.call(this,this.queueData)):this.queueData.uploadQueue.length>0?this.startUpload(this.queueData.uploadQueue.shift()):(this.queueData.uploadQueue=[],b.onQueueComplete&&b.onQueueComplete.call(this,this.queueData)),$.inArray("onUploadComplete",b.overrideEvents)<0)if(b.removeCompleted)switch(a.filestatus){case SWFUpload.FILE_STATUS.COMPLETE:setTimeout(function(){$("#"+a.id)&&(c.queueData.queueSize-=a.size,c.queueData.queueLength-=1,delete c.queueData.files[a.id],$("#"+a.id).fadeOut(500,function(){$(this).remove()}))},1e3*b.removeTimeout);break;case SWFUpload.FILE_STATUS.ERROR:b.requeueErrors||setTimeout(function(){$("#"+a.id)&&(c.queueData.queueSize-=a.size,c.queueData.queueLength-=1,delete c.queueData.files[a.id],$("#"+a.id).fadeOut(500,function(){$(this).remove()}))},1e3*b.removeTimeout)}else a.uploaded=!0;b.onUploadComplete&&b.onUploadComplete.call(this,a)},onUploadError:function(a,b,c){var d=this.settings,e="Error";switch(b){case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:e="HTTP Error ("+c+")";break;case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:e="Missing Upload URL";break;case SWFUpload.UPLOAD_ERROR.IO_ERROR:e="IO Error";break;case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:e="Security Error";break;case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:e="Exceeds Upload Limit";break;case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:e="Failed";break;case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:break;case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:e="Validation Error";break;case SWFUpload.UPLOAD_ERROR.FILE_CANCELLED:e="Cancelled",this.queueData.queueSize-=a.size,this.queueData.queueLength-=1,(a.status==SWFUpload.FILE_STATUS.IN_PROGRESS||$.inArray(a.id,this.queueData.uploadQueue)>=0)&&(this.queueData.uploadSize-=a.size),d.onCancel&&d.onCancel.call(this,a),delete this.queueData.files[a.id];break;case SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED:e="Stopped"}$.inArray("onUploadError",d.overrideEvents)<0&&(b!=SWFUpload.UPLOAD_ERROR.FILE_CANCELLED&&b!=SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED&&$("#"+a.id).addClass("uploadify-error"),$("#"+a.id).find(".uploadify-progress-bar").css("width","1px"),b!=SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND&&a.status!=SWFUpload.FILE_STATUS.COMPLETE&&$("#"+a.id).find(".data").html(" - "+e));var f=this.getStats();this.queueData.uploadsErrored=f.upload_errors,d.onUploadError&&d.onUploadError.call(this,a,b,c,e)},onUploadProgress:function(a,b,c){var d=this.settings,e=new Date,f=e.getTime(),g=f-this.timer;g>500&&(this.timer=f);var h=b-this.bytesLoaded;this.bytesLoaded=b;var i=this.queueData.queueBytesUploaded+b,j=Math.round(b/c*100),k="KB/s",l=0,m=h/1024/(g/1e3);m=Math.floor(10*m)/10,this.queueData.averageSpeed>0?this.queueData.averageSpeed=Math.floor((this.queueData.averageSpeed+m)/2):this.queueData.averageSpeed=Math.floor(m),m>1e3&&(l=.001*m,this.queueData.averageSpeed=Math.floor(l),k="MB/s"),$.inArray("onUploadProgress",d.overrideEvents)<0&&("percentage"==d.progressData?$("#"+a.id).find(".data").html(" - "+j+"%"):"speed"==d.progressData&&g>500&&$("#"+a.id).find(".data").html(" - "+this.queueData.averageSpeed+k),$("#"+a.id).find(".uploadify-progress-bar").css("width",j+"%")),d.onUploadProgress&&d.onUploadProgress.call(this,a,b,c,i,this.queueData.uploadSize)},onUploadStart:function(a){var b=this.settings,c=new Date;this.timer=c.getTime(),this.bytesLoaded=0,0==this.queueData.uploadQueue.length&&(this.queueData.uploadSize=a.size),b.checkExisting&&$.ajax({type:"POST",async:!1,url:b.checkExisting,data:{filename:a.name},success:function(b){if(1==b){var c=confirm('A file with the name "'+a.name+'" already exists on the server.\nWould you like to replace the existing file?');c||(this.cancelUpload(a.id),$("#"+a.id).remove(),this.queueData.uploadQueue.length>0&&this.queueData.queueLength>0&&("*"==this.queueData.uploadQueue[0]?this.startUpload():this.startUpload(this.queueData.uploadQueue.shift())))}}}),b.onUploadStart&&b.onUploadStart.call(this,a)},onUploadSuccess:function(a,b,c){var d=this.settings,e=this.getStats();this.queueData.uploadsSuccessful=e.successful_uploads,this.queueData.queueBytesUploaded+=a.size,$.inArray("onUploadSuccess",d.overrideEvents)<0&&$("#"+a.id).find(".data").html(" - 上传完成"),d.onUploadSuccess&&d.onUploadSuccess.call(this,a,b,c)}};$.fn.uploadify=function(b){return a[b]?a[b].apply(this,Array.prototype.slice.call(arguments,1)):"object"!=typeof b&&b?void $.error("The method "+b+" does not exist in $.uploadify"):a.init.apply(this,arguments)}}();