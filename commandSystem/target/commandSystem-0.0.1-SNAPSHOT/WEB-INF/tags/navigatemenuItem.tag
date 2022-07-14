<%@attribute name="menu_id"%><%@attribute name="url"%><%@attribute name="iconclass"%><%@attribute name="checked"%><%@attribute name="name"%><%@attribute name="pitem_name"%><%@attribute name="disabled"%><%@attribute name="checkable"%><%@attribute name="target"%><%@variable name-given="item_name" scope="NESTED"%>
<%
jspContext.setAttribute("item_name",name);

String chk="";
String dis="true".equals(disabled)?"disabled=true":"";
String chd="true".equals(checked)?"checked":"";
if("false".equals(checkable)){
    chk="<input type='radio' "+dis+" name='"+pitem_name+"' "+chd+" data-id='"+menu_id+"_"+name+"'/>";
}else if("true".equals(checkable)){
    chk="<input type='checkbox' "+dis+" "+chd+" data-id='"+menu_id+"_"+name+"'/>";
}
%>
<li>
<a <%=url==null?"":"href='"+url+"'"%> <%=iconclass==null && checkable!=null?"":"data-icon='"+iconclass+"'"%>  <%=name==null?"":"data-name='"+name+"'"%> data-disabled='<%=disabled%>' <%=target==null?"":"target='"+target+"'"%>>
<%=name==null?chk:chk+name%>
</a>
<jsp:doBody />
</li>