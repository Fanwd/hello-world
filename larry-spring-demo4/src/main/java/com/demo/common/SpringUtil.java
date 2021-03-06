package com.demo.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SpringUtil{
	
	private static Logger LOG = LoggerFactory.getLogger(SpringUtil.class);
	
	private static ApplicationContext applicationContext;
	
	public static void setApplicationContext(ApplicationContext applicationContext){
		SpringUtil.applicationContext = applicationContext;
	}
	
	/**
	 * 根据beanID从容器获取bean
	 * @param beanID
	 * @return
	 */
	public static Object getBean(String beanID){
		return applicationContext.getBean(beanID);
	}
	
	/**
	 * 通过RequestContextHolder获取request(或者通过注入获取)
	 * @return
	 */
	public static HttpServletRequest getRequest(){
		HttpServletRequest request = null;
		try{
			request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		}catch(Exception ex){
			LOG.error("Exception", ex);
		}
		return request;
	}
	
}