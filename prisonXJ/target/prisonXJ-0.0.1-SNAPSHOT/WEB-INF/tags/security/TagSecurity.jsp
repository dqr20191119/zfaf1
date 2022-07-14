<%
final class TagSecurity {
    public TagSecurity() {
    }
    public boolean getAuthorized( String url ){
    	if(null != url){
    		return com.ces.security.Authorize.urlCheck(url);
		}
    	return false;
    }
}
TagSecurity tagSecurity = new TagSecurity();
%>
