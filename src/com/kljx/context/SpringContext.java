package com.kljx.context;

import java.io.Writer;
import java.util.Collection;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy(false)
public class SpringContext implements ApplicationContextAware
{
	private static ApplicationContext applicationContext;

	public void setApplicationContext(ApplicationContext ac)
	{
		applicationContext = ac;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}

	public static <T> T getBean(Class<T> _class) throws BeansException {
		return applicationContext.getBean(_class);
	}

	public static <T> T getBeansOfType(Class<T> _class) throws BeansException {
		Map map = applicationContext.getBeansOfType(_class);
		Collection collection = map.values();
		for (Object __obj : collection)
		{
			if (__obj.getClass().equals(_class))
				return (T) __obj;
		}
		return null;
	}
	
}