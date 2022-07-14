<%@ attribute name="id" %><%@ attribute name="name" %><%@ attribute name="prependFiles" type="java.lang.Boolean" %><%@ attribute name="downloadTmp" %><%@ attribute name="style" %><%@ attribute name="uploadBtn"%><%@ attribute name="url" %><%@ attribute name="maxFileSize" %><%@ attribute name="cls" %><%@ attribute name="componentCls" %><%@ attribute name="rendered" type="java.lang.Boolean" %><%@ attribute name="authorized" %><%@ attribute name="filesUrl" %><%@ attribute name="acceptFileTypes" %><%@ attribute name="autoUpload" type="java.lang.Boolean" %><%@ attribute name="required" type="java.lang.Boolean" %><%@ attribute name="multiple" type="java.lang.Boolean" %><%@ attribute name="formData" %><%@ attribute name="filesLimt" %><%@ attribute name="fileObjName" %><%@ attribute name="uploadBtnOptions" %><%@ attribute name="onSelect" %><%@ attribute name="queueID" %><%@ attribute name="queueMode" %><%@ attribute name="onFail" %><%@ attribute name="onRemove" %><%@ attribute name="onComplete" %><%@ attribute name="onCreate" %><%@ attribute name="onStart" %><%@ attribute name="onSend" %><%@ attribute name="onProgress" %><%@ attribute name="onSuccess" %><%@ include file="TagUtil.jsp" %><% String dataOption = tagUtil.add("name", name)
.add("id", id)
.add("cls", cls)
.add("downloadTmp", downloadTmp)
.add("prependFiles", prependFiles)
.add("style", style)
.add("uploadBtn", uploadBtn)
.add("url", url)
.add("componentCls", componentCls)
.add("rendered", rendered).add("authorized", authorized)
.add("maxFileSize", maxFileSize)
.add("filesUrl", filesUrl)
.add("acceptFileTypes", acceptFileTypes)
.add("autoUpload", autoUpload)
.add("required", required)
.add("multiple", multiple)
.add("formData", formData)
.add("filesLimt", filesLimt)
.add("fileObjName", fileObjName)
.add("queueMode", queueMode)
.add("queueID", queueID)
.add("uploadBtnOptions", uploadBtnOptions)
.add("controllerName", request.getAttribute("controllerName"))
.add("onCreate", onCreate)
.add("onSelect", onSelect)
.add("onStart", onStart)
.add("onClick", onSend)


.add("onProgress", onProgress)
.add("onComplete", onComplete)
.add("onFail", onFail)
.add("onRemove", onRemove)
.add("onSuccess", onSuccess).toString();
String clsStr = "",
idStr = "",
styleStr = "";
idStr = id==null?"":"id="+id;
clsStr = cls == null ? "class='coralui-fileuploader'" : "class='coralui-fileuploader "+cls+"'";
styleStr = style == null ? "" : "style='"+style+"'";
%><span <%=idStr %> <%=clsStr %> <%=styleStr %> data-options="<%=dataOption %>"><jsp:doBody /></span >