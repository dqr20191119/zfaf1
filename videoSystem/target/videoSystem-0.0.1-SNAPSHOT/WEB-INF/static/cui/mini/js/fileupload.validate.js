/*! cui 2017-11-03 */
!function(){"use strict";$.blueimp.fileupload.prototype.options.processQueue.push({action:"validate",always:!0,acceptFileTypes:"@",maxFileSize:"@",minFileSize:"@",maxNumberOfFiles:"@",disabled:"@disableValidation"}),$.component("blueimp.fileupload",$.blueimp.fileupload,{options:{getNumberOfFiles:$.noop,messages:{maxNumberOfFiles:"Maximum number of files exceeded",acceptFileTypes:"The file is not an accepted file type.",maxFileSize:"The File is too large.",minFileSize:"File is too small"}},processActions:{validate:function(a,b){if(b.disabled)return a;var c,d=$.Deferred(),e=this.options,f=a.files[a.index];return(b.minFileSize||b.maxFileSize)&&(c=f.size),"number"===$.type(b.maxNumberOfFiles)&&(e.getNumberOfFiles()||0)+a.files.length>b.maxNumberOfFiles?f.error=e.i18n("maxNumberOfFiles"):!b.acceptFileTypes||b.acceptFileTypes.test(f.type)||b.acceptFileTypes.test(f.name)?c>b.maxFileSize?f.error=e.i18n("maxFileSize"):"number"===$.type(c)&&c<b.minFileSize?f.error=e.i18n("minFileSize"):delete f.error:f.error=e.i18n("acceptFileTypes"),f.error||a.files.error?(a.files.error=!0,d.rejectWith(this,[a])):d.resolveWith(this,[a]),d.promise()}}})}();