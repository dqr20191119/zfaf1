<%
final class TagUtil {
    
    private StringBuilder sb = null;
    public TagUtil() {
        sb = new StringBuilder();
    }
    public String getClientId( String id ){
    	if ( null == id ) {
    		//id = "c_"+String.valueOf(System.currentTimeMillis());
    		id = "c_"+java.util.UUID.randomUUID();
    		
		}
    	return id;
    }
    public Object formatDate(Object value, String dateFormat){
       	if (value != null && value instanceof java.util.Date) {
       		value = new java.text.SimpleDateFormat(dateFormat).format(value);
       	}
       	return value;
    }
    
    public TagUtil add(String key, Object value, String type) {
    	if (null == value) return this;
        sb.append(",");
        /* if (type != null && type.equals("options")) {//如果是json则需要加括号
        	sb.append(key).append(":{").append(value).append("}");
        } else  */
		if (value instanceof Boolean || value instanceof Integer) {
        	sb.append(key).append(":").append(value);
        } else {
            String str = value.toString();
        	if (type != null && type.equals("options")){
        		if ( value.toString().startsWith("{")){
        			sb.append(key).append(":").append(str);
        		} else {
	        		str = "{" + str + "}"; 
	        		sb.append(key).append(":").append(str);
        		}
        	} else {
	            str = str.contains("\"") ? str.replace("\"", "\\&quot;") : str;
	            str = str.contains("\'") ? str.replace("\'", "\\&quot;") : str;
        		sb.append(key).append(":&quot;").append(str).append("&quot;");
        	}
        }
        return this;
    }
    
    public TagUtil add(String key, Object value) {
    	
    	return add(key, value, null);
    }
    
    public String toString() {
        return sb.length() > 0 ? sb.deleteCharAt(0).toString() : "";
    }
    
    public TagUtil clear() {    
    	sb.delete(0,sb.length());
    	return this;
    }
}
TagUtil tagUtil = new TagUtil();
%>
