<%@ tag language="java" pageEncoding="UTF-8"%><%@attribute name="width"%><%@attribute name="name"%><%@attribute name="header"%><%@attribute name="property"%><%@attribute name="dataType"%>
<% String option = "";
option += width==null?"":"width:'"+width+"',";
option += name==null?"":"name:'"+name+"',";
option += header==null?"":"header:'"+header+"',";
option += property==null?"":"property:'"+property+"',";
option += dataType==null?"":"dataType:'"+dataType+"',";
if(option.length()>0)
	option = option.substring(0, option.length()-1);
%>
<div data-options="<%=option %>"><jsp:doBody /></div> 
