package com.kljx.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class WebContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		String contextPath = servletContextEvent.getServletContext().getContextPath();
		String rootpath = servletContextEvent.getServletContext().getRealPath("/");
		System.setProperty("webapp.rootpath", rootpath);
		System.setProperty("webapp.contextPath", contextPath);
	}

}