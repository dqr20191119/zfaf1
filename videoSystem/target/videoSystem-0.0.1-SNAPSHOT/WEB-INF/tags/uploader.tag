<%@ tag language="java" pageEncoding="UTF-8" %>
<!-- add options  -->
<%@ attribute name="id" type="java.lang.String" required="true" description="uploder id" %>
<%@ attribute name="displayStyle" description="original,custom" %>
<%@ attribute name="emptyText" %>
<%@ attribute name="delay" %>
<%@ attribute name="style" %>
<%@ attribute name="buttons" %>
<%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %>
<%@ attribute name="componentCls" %>
<%@ attribute name="name" type="java.lang.String" description="uploader name" %>
<%@ attribute name="auto" type="java.lang.Boolean" description="Automatically upload files when added to the queue" %>
<%@ attribute name="buttonClass" type="java.lang.String" description="A class name to add to the browse button DOM object" %>
<%@ attribute name="buttonCursor" type="java.lang.String" description="The cursor to use with the browse button" %>
<%@ attribute name="buttonImage" type="java.lang.String" description="(String or null) The path to an image to use for the Flash browse button if not using CSS to style the button" %>
<%@ attribute name="buttonText" type="java.lang.String" description="The text to use for the browse button" %>
<%@ attribute name="checkExisting" type="java.lang.Boolean" description="The path to a server-side script that checks for existing files on the server" %>
<%@ attribute name="debug" type="java.lang.Boolean" description="Turn on swfUpload debugging mode" %>
<%@ attribute name="fileObjName" type="java.lang.String" description="The name of the file object to use in your server-side script" %>
<%@ attribute name="fileSizeLimit" description="The maximum size of an uploadable file in KB (Accepts units B KB MB GB if string, 0 for no limit)" %>
<%@ attribute name="fileTypeDesc" type="java.lang.String" description="The description for file types in the browse dialog" %>
<%@ attribute name="fileTypeExts" type="java.lang.String" description="Allowed extensions in the browse dialog (server-side validation should also be used)" %>
<%@ attribute name="formData" description="An object with additional data to send to the server-side upload script with every file upload" %>
<%@ attribute name="height" type="java.lang.Integer" description="The height of the browse button" %>
<%@ attribute name="itemTemplate" type="java.lang.String" description="The template for the file item in the queue" %>
<%@ attribute name="method" type="java.lang.String" description="The method to use when sending files to the server-side upload script" %>
<%@ attribute name="multi" type="java.lang.Boolean" description="Allow multiple file selection in the browse dialog" %>
<%@ attribute name="overrideEvents" description="(Array) A list of default event handlers to skip" %>
<%@ attribute name="preventCaching" type="java.lang.Boolean" description="Adds a random value to the Flash URL to prevent caching of it (conflicts with existing parameters)" %>
<%@ attribute name="progressData" type="java.lang.String" description="('percentage' or 'speed') Data to show in the queue item during a file upload" %>
<%@ attribute name="queueID" description="The ID of the DOM object to use as a file queue (without the #)" %>
<%@ attribute name="queueSizeLimit" type="java.lang.Integer" description="The maximum number of files that can be in the queue at one time" %>
<%@ attribute name="removeCompleted" type="java.lang.Boolean" description="Remove queue items from the queue when they are done uploading" %>
<%@ attribute name="removeTimeout" type="java.lang.Integer" description="The delay in seconds before removing a queue item if removeCompleted is set to true" %>
<%@ attribute name="requeueErrors" type="java.lang.Boolean" description="Keep errored files in the queue and keep trying to upload them" %>
<%@ attribute name="successTimeout" type="java.lang.Integer" description="The number of seconds to wait for Flash to detect the server's response after the file has finished uploading" %>
<%@ attribute name="swf" type="java.lang.String" description="The path to the uploadify SWF file" %>
<%@ attribute name="uploader" type="java.lang.String" required="true" description="The path to the server-side upload script" %>
<%@ attribute name="uploadLimit" type="java.lang.Integer" description="The maximum number of files you can upload" %>
<%@ attribute name="width" type="java.lang.Integer" description="The width of the browse button" %>
<%@ attribute name="cls" type="java.lang.String" description="class added on this Dom Object" %>
<!-- add events -->
<%@ attribute name="onCancel" description="Triggered when a file is cancelled from the queue" %>
<%@ attribute name="onClearQueue" description="Triggered during the 'clear queue' method" %>
<%@ attribute name="onDestroy" description="Triggered when the uploadify object is destroyed" %>
<%@ attribute name="onDialogClose" description="Triggered when the browse dialog is closed" %>
<%@ attribute name="onDialogOpen" description="Triggered when the browse dialog is opened" %>
<%@ attribute name="onDisable" description="Triggered when the browse button gets disabled" %>
<%@ attribute name="onEnable" description="Triggered when the browse button gets enabled" %>
<%@ attribute name="onFallback" description="Triggered is Flash is not detected" %>
<%@ attribute name="onInit" description="Triggered when Uploadify is initialized" %>
<%@ attribute name="onQueueComplete" description="Triggered when all files in the queue have been uploaded" %>
<%@ attribute name="onSelectError" description="Triggered when an error occurs while selecting a file (file size, queue size limit, etc.)" %>
<%@ attribute name="onSelect" description="Triggered for each file that is selected" %>
<%@ attribute name="onNoFlash" description="Triggered for computer is Flash" %>
<%@ attribute name="onSWFReady" description="Triggered when the SWF button is loaded" %>
<%@ attribute name="onUploadComplete" description="Triggered when a file upload completes (success or error)" %>
<%@ attribute name="onUploadError" description="Triggered when a file upload returns an error" %>
<%@ attribute name="onUploadSuccess" description="Triggered when a file is uploaded successfully" %>
<%@ attribute name="onUploadProgress" description="Triggered every time a file progress is updated" %>
<%@ attribute name="onUploadStart" description="Triggered immediately before a file upload starts" %>
<!-- handle dataOption --><%@ include file="TagUtil.jsp" %><% 	
String dataOption = tagUtil.add("id", id)
.add("emptyText", emptyText)
.add("componentCls", componentCls)
.add("delay", delay)
.add("style", style)
.add("displayStyle", displayStyle)
.add("buttons", buttons)
.add("rendered", rendered).add("authorized", authorized)
.add("name", name)
.add("auto", auto)						
.add("buttonClass", buttonClass)
.add("buttonCursor", buttonCursor)
.add("buttonImage", buttonImage)
.add("buttonText", buttonText)
.add("checkExisting", checkExisting)
.add("debug", debug)
.add("fileObjName", fileObjName)
.add("fileSizeLimit", fileSizeLimit)
.add("fileTypeDesc", fileTypeDesc)
.add("fileTypeExts", fileTypeExts)			
.add("formData", formData)
.add("height", height)
.add("itemTemplate", itemTemplate)
.add("method", method)
.add("multi", multi)
.add("overrideEvents", overrideEvents)
.add("preventCaching", preventCaching)
.add("progressData", progressData)
.add("queueID", queueID)
.add("queueSizeLimit", queueSizeLimit)
.add("removeCompleted", removeCompleted)
.add("removeTimeout", removeTimeout)
.add("requeueErrors", requeueErrors)
.add("successTimeout", successTimeout)
.add("swf", swf)
.add("uploader", uploader)
.add("uploadLimit", uploadLimit)
.add("width", width)
.add("cls", cls)

.add("onCancel", onCancel)
.add("onClearQueue", onClearQueue)
.add("onDestroy", onDestroy)
.add("onDialogClose", onDialogClose)
.add("onDialogOpen", onDialogOpen)
.add("onDisable", onDisable)
.add("onEnable", onEnable)
.add("onFallback", onFallback)
.add("onInit", onInit)
.add("onQueueComplete", onQueueComplete)
.add("onSelectError", onSelectError)
.add("onSelect", onSelect)
.add("onNoFlash", onNoFlash)
.add("onSWFReady", onSWFReady)
.add("onUploadComplete", onUploadComplete)
.add("onUploadError", onUploadError)
.add("onUploadSuccess", onUploadSuccess)
.add("onUploadProgress", onUploadProgress)
.add("controllerName", request.getAttribute("controllerName"))
.add("onUploadStart", onUploadStart).toString();
String	clsStr = "",
		idStr = "",
		styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-uploader'" : "class='coralui-uploader "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><input <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>" type="file"/>
