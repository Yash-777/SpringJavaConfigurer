package com.github.yash777.standalone.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnnotationConfiguration {
	public static void main(String[] args) {
		// https://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/beans.html#beans-java-instantiating-container-scan
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.scan("com.github.yash777.standalone.annotation");
		ctx.register(AppConfig.class);
		
		ctx.registerShutdownHook();
		
		ctx.refresh();
		
		MyService myService = ctx.getBean("S1", MyService.class);
		myService.displayID();
		
		MyService myService2 = ctx.getBean("S2", MyService.class);
		myService2.displayID();
		
		System.out.println("AnnotationConfigApplicationContext : "+ ctx.getBeanDefinitionCount());
		//ctx.destroy();
	}
}
