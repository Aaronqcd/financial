package com.kljx.context;

import com.opensymphony.xwork2.ActionContext;
import javax.servlet.http.HttpServletResponse;

public class WebUtils
{
	public static final long ONE_YEAR_SECONDS = 31536000L;

	public static void setExpiresHeader(HttpServletResponse response, long expiresSeconds) {
		response.setDateHeader("Expires", System.currentTimeMillis() + expiresSeconds * 1000L);

		response.setHeader("Cache-Control", "private, max-age=" + expiresSeconds);
	}

	public static void setNoCacheHeader(HttpServletResponse response) {
		response.setDateHeader("Expires", 0L);
		response.addHeader("Pragma", "no-cache");

		response.setHeader("Cache-Control", "no-cache");
	}

	public static void setLastModifiedHeader(HttpServletResponse response, long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	public static UserContext getUserContext() {
		return (UserContext)ActionContext.getContext().getSession().get("userContext");
	}

}