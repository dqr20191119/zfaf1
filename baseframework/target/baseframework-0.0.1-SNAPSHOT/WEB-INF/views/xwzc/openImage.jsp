<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cui" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="cut" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<body>
 <div style="text-align: center;">
 <!-- src="data:image/jpeg;base64,图片字符串" -->
   <img src="" id="image1" width="400" height="400" >
 </div>
</body>

<script type="text/javascript">
   
	var id = "${id}";
    $.parseDone(function () {
          $.ajax({
				type : 'post',
				url : '${ctx}/xwzc/dkImage?id='+id,
				dataType : 'json',
				success : function(data) {
					console.log(data);
					$("#image1").attr("src","data:image/jpeg;base64,"+data.phono1+"");
					
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
				}
			});
       
       

    });
</script>
</html>