<%@attribute name="url"%><%@attribute name="isopen" type="java.lang.Boolean"%><%@attribute name="isactive" type="java.lang.Boolean"%><%@attribute name="iconclass"%><%@attribute name="id"%><%@attribute name="name"%><%@attribute name="disabled"%><%@attribute name="target"%>
<li>
<%
	disabled=disabled==null?"false":disabled;
%>
<a <%=url==null?"":"data-url='"+url+"'"%> <%=isactive==null?"":"data-isactive='"+isactive+"'"%> <%=isopen==null?"":"data-isopen='"+isopen+"'"%> <%=name==null?"":"data-name='"+name+"'"%> <%=id==null?"":"data-id='"+id+"'"%> <%=iconclass==null?"":"data-icon='"+iconclass+"'"%> data-disabled='<%=disabled%>' <%=target==null?"":"data-target='"+target+"'"%>><%=name==null?"":name%></a><jsp:doBody /></li>