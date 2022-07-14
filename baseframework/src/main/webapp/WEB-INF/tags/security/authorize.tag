<%@ attribute name="url" %><%@include file="TagSecurity.jsp" %><%
if(tagSecurity.getAuthorized(url)){%>
<jsp:doBody />
<%}%>