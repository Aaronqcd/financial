package com.kljx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.kljx.context.UserContext;

public class PreSessionFilter implements Filter {

	public FilterConfig config;

	public void destroy() {
		this.config = null;
	}

	public static boolean isContains(String container, String[] regx) {
		boolean result = false;

		for (int i = 0; i < regx.length; i++) {
			if (regx[i].indexOf("*") >= 0) {
				if (isRegexpMatch(container, regx[i])) return true;
			}
			else if (container.indexOf(regx[i]) != -1) {
				return true;
			}
		}
		return result;
	}

	public static boolean isRegexpMatch(String container, String regexp) {
		regexp = regexp.replace("*", "[\\S\\s]{1,}");
		return container.matches(regexp);
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest)request;
		HttpServletResponse hresponse = (HttpServletResponse)response;
		
//		session过期\没有登录访问 到session过期页面
		/**
		UserContext user = (UserContext)hrequest.getSession().getAttribute("userContext");
		if (user == null) {
		//	String redirectPath = this.config.getInitParameter("redirectPath");
		//	hresponse.sendRedirect(hrequest.getContextPath() + "/" + StringUtils.strip(redirectPath, "/"));
			hresponse.sendError(500);
			return;
		}*/

		String logonStrings = this.config.getInitParameter("logonStrings");

		String disabletestfilter = this.config.getInitParameter("disabletestfilter");
		
		if (StringUtils.isEmpty(disabletestfilter)) disabletestfilter = "N";

		if (disabletestfilter.toUpperCase().equals("Y")) {
			chain.doFilter(request, response);
			return;
		}
		
		String[] logonList = logonStrings.split(";");

		if ((StringUtils.isEmpty(StringUtils.strip(hrequest.getRequestURI(), "/"))) || (isContains(hrequest.getRequestURI(), logonList))) {
			chain.doFilter(request, response);
			return;
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.config = filterConfig;
	}

}