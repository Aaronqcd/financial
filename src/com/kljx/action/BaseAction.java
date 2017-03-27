package com.kljx.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

public abstract class BaseAction extends ActionSupport implements ServletContextAware, ServletRequestAware, ServletResponseAware, RequestAware, SessionAware, ParameterAware {
	private static final long serialVersionUID = 1L;
	private ServletContext servletContext;
	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected HttpServletResponse servletResponse;
	protected HttpServletRequest servletRequest;
	protected Map<String, String[]> parameters;

	public ServletContext getServletContext()
	{
		return this.servletContext;
	}

	public void addAlertMessage(String msg)
	{
		addActionMessage(msg);
	}

	public void addFieldError(String errmsg) {
		if ((errmsg != null) && (!errmsg.trim().equals("")))
		{
			addFieldError("errmsg", errmsg);
		}
	}

	public void setServletContext(ServletContext context) {
		this.servletContext = context;
	}

	public ServletContext setServletContext()
	{
		return this.servletContext;
	}

	public HttpServletResponse getServletResponse() {
		return this.servletResponse;
	}

	public HttpServletRequest getServletRequest()
	{
		return this.servletRequest;
	}

	public String getUploadExcelPath()
	{
		return getServletContext().getRealPath("/upload");
	}

	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

	public void setServletRequest(HttpServletRequest servletRequest)
	{
		this.servletRequest = servletRequest;
	}

	public void setSession(Map<String, Object> session)
	{
		this.session = session;
	}

	public void setRequest(Map<String, Object> request)
	{
		this.request = request;
	}

	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}

}