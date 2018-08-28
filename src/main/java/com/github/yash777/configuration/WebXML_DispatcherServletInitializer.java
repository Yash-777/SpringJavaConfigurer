package com.github.yash777.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * WebApplicationContext for namespace 'dispatcher-servlet' :
 * AnnotationConfigWebApplicationContext [initWebApplicationContext, loadBeanDefinitions, initControllerAdviceCache]
 * 
 * @author yashwanth.m
 *
 */
public class WebXML_DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	/*
	Since 3.2
	AbstractAnnotationConfigDispatcherServletInitializer extends
	AbstractDispatcherServletInitializer extends
	AbstractContextLoaderInitializer implements WebApplicationInitializer
	*/
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { MvcServletXMLConfigurer.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
