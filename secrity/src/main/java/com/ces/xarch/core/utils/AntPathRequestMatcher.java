/**
 * <p>Copyright:Copyright(c) 2014</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.ces.xarch.core.utils</p>
 * <p>文件名:AntPathMatcher.java</p>
 * <p>类更新历史信息</p>
 * @todo <a href="mailto:yangmujiang@sohu.com">Reamy(杨木江)</a> 创建于 2014-07-03 17:01:20
 */
package com.ces.xarch.core.utils;

import org.apache.log4j.Logger;
import org.springframework.util.AntPathMatcher;

/**
 * 路径匹配.
 * <p>描述:使用Ant方式进行路径匹配</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * @author <a href="mailto:yangmujiang@sohu.com">Reamy(杨木江)</a>
 * @date 2014-07-03  17:01:20
 * @version 1.0.2014.0703
 */
public class AntPathRequestMatcher {
	/** 日志记录对象. */
	private Logger logger = Logger.getLogger(getClass());
	private final org.springframework.security.web.util.AntPathRequestMatcher springMatcher;
	private final String pattern;
	private static final String MATCH_ALL = "/**";
	private final Matcher matcher;
	
	/**
	 * 构造函数.
	 * @param pattern ant表达式
	 * @author <a href="mailto:yangmujiang@sohu.com">Reamy(杨木江)</a>
	 * @date 2014-07-03  17:06:06
	 */
	public AntPathRequestMatcher(String pattern) {
        this.pattern = pattern;
        springMatcher = new org.springframework.security.web.util.AntPathRequestMatcher(pattern);
        
        if (pattern.equals(MATCH_ALL) || pattern.equals("**")) {
            pattern = MATCH_ALL;
            matcher = null;
        } else {
            pattern = pattern.toLowerCase();

            // If the pattern ends with {@code /**} and has no other wildcards, then optimize to a sub-path match
            if (pattern.endsWith(MATCH_ALL) && pattern.indexOf('?') == -1 &&
                    pattern.indexOf("*") == pattern.length() - 2) {
                matcher = new SubpathMatcher(pattern.substring(0, pattern.length() - 3));
            } else {
                matcher = new SpringAntMatcher(pattern);
            }
        }
    }
	
	/**
	 * 进行匹配.
	 * @param object 待验证对象（允许String及FilterInvocation对象）
	 * @return 匹配结果
	 * @author <a href="mailto:yangmujiang@sohu.com">Reamy(杨木江)</a>
	 * @date 2014-07-03  17:06:20
	 */
	public boolean matches(Object object) {
		/*if (object instanceof FilterInvocation) {
			return springMatcher.matches(((FilterInvocation) object).getRequest());
		} else*/ if (object instanceof String) {
			String url = (String)object;
			
			if (pattern.equals(MATCH_ALL)) {
	            if (logger.isDebugEnabled()) {
	                logger.debug("Request '" + url + "' matched by universal pattern '/**'");
	            }

	            return true;
	        }

	        if (logger.isDebugEnabled()) {
	            logger.debug("Checking match of request : '" + url + "'; against '" + pattern + "'");
	        }

	        return matcher.matches(url);
		}
		
		return false;
	}
	
	private static interface Matcher {
        boolean matches(String path);
    }

    private static class SpringAntMatcher implements Matcher {
        private static final AntPathMatcher antMatcher = new AntPathMatcher();

        private final String pattern;

        private SpringAntMatcher(String pattern) {
            this.pattern = pattern;
        }

        public boolean matches(String path) {
            return antMatcher.match(pattern, path);
        }
    }

    /**
     * Optimized matcher for trailing wildcards
     */
    private static class SubpathMatcher implements Matcher {
        private final String subpath;
        private final int length;

        private SubpathMatcher(String subpath) {
            assert !subpath.contains("*");
            this.subpath = subpath;
            this.length = subpath.length();
        }

        public boolean matches(String path) {
            return path.startsWith(subpath) && (path.length() == length || path.charAt(length) == '/');
        }
    }
}
