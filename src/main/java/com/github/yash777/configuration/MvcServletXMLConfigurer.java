package com.github.yash777.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.PathResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(value = {"com.github.yash777.controllers"})
public class MvcServletXMLConfigurer extends WebMvcConfigurerAdapter implements WebMvcConfigurer {

	/*
	 * @EnableWebMvc « <mvc:annotation-driven />
	 *  « <mvc:resources mapping="/js/**" location="/js/"/>
	 * 
	 * @ComponentScan(value = {"com.github.yash777.controllers"}) «
	 * <context:component-scan base-package="com.github.yash777" />
	 */
	
	/**
	 * <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" 
	 * p:prefix="/WEB-INF/jsp/" p:suffix=".jsp" />
	 * 
	 * @return InternalResourceViewResolver as a bean configuration.
	 */
	@Bean
	public InternalResourceViewResolver getInternalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	// https://stackoverflow.com/a/51762966/5081877
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("WebMvcConfigurer - addResourceHandlers() function get loaded...");
		
		// https://www.baeldung.com/spring-mvc-static-resources
		// <mvc:resources mapping="/styles/**" location="/css/" />
		registry
			.addResourceHandler("/styles/**") 
			.addResourceLocations("/css/") // webapp/css/
			.setCachePeriod(3600)
			.resourceChain(true) // 4.1
			.addResolver(new GzipResourceResolver()) // 4.1
			.addResolver(new PathResourceResolver()); //4.1
		
		// <mvc:resources mapping="/static/**" location="/static/" />
		registry.addResourceHandler("/static/**")
				.addResourceLocations("/static/", "classpath:/static/") // src/main/resources/static/
				.setCachePeriod(3600)
				.resourceChain(true)
				.addResolver(new PathResourceResolver());
	}
}
