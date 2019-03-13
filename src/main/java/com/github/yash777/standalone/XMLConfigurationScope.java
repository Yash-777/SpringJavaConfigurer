package com.github.yash777.standalone;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * One of the popular implementation of BeanFactory interface is XMLBeanFactory while one of the popular
 * implementation of ApplicationContext interface is ClassPathXmlApplicationContext.
 * On Java web application we use WebApplicationContext  which extends ApplicationContext interface and
 * adds getServletContext method.

 * @author yashwanth.m
 *
 */
@SuppressWarnings({ "unused", "deprecation" })
public class XMLConfigurationScope {
	public static void main(String[] args) {
		System.out.println("===== BeanFactory =====");
		BeanFactory factory = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
		System.out.println("SingleTon Object test: constructor-arg");
		getBeanFactory(factory, "h1");
		
		System.out.println("Prototype Object test: setter-property");
		getBeanFactory(factory, "h2");
		
		System.out.println("Prototype Object test: constructor-arg");
		getBeanFactory(factory, "h3");
		
		System.out.println("===== ApplicationContext =====");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		System.out.println("SingleTon Object test: constructor-arg");
		getBeanContext(context, "h1");
		
		System.out.println("Prototype Object test: setter-property");
		getBeanContext(context, "h2");
		
		shutdownHook( context );
		
		System.out.println("Prototype Object test: constructor-arg");
		getBeanContext(context, "h3");
	}
	
	/**
	 * All the destroy methods are called when the context is closed or refreshed.
	 * <p> If you run a stand-alone application,
	 * in order to make sure that destroy methods are called before the application ends,
	 * you need to register a shutdown hook. [its execution is guaranteed by the JVM] </P>
	 * 
	 * <p> If you using web based application over servlet containers (tomcat, in this case),
	 * you'll need to rely on the servlet context lifecycle, NOT on shutdown hooks 
	 * (which would only be fired on tomcat shutdown, not on webapp redeployment).
	 * Spring provides a utility class called ContextLoaderListener for this purpose,
	 * which you need to configure as &lt;listener&gt; in your web.xml. </p>
	 * 
	 * https://stackoverflow.com/a/17497507/5081877
	 * https://docs.spring.io/spring/docs/3.2.x/spring-framework-reference/html/beans.html#context-introduction-ctx-vs-beanfactory
	 * @param context
	 */
	public static void shutdownHook( ApplicationContext context ) {
		ConfigurableApplicationContext configurableContext = (ConfigurableApplicationContext) context;
		configurableContext.registerShutdownHook(); // AbstractApplicationContext
	}
	
	public static void getBeanContext(ApplicationContext context, String beanID ) {
		Hello_ScopeTest h1 = context.getBean(beanID, Hello_ScopeTest.class);
		h1.sayHello();
		System.out.println("		Obj:"+ System.identityHashCode(h1) );
		Hello_ScopeTest h1_singleton = context.getBean(beanID, Hello_ScopeTest.class);
		h1_singleton.sayHello();
		System.out.println("		Obj:"+ System.identityHashCode(h1_singleton) );
	}
	public static void getBeanFactory(BeanFactory factory, String beanID ) {
		Hello_ScopeTest h1 = factory.getBean(beanID, Hello_ScopeTest.class);
		h1.sayHello();
		System.out.println("		Obj:"+ System.identityHashCode(h1) );
		Hello_ScopeTest h1_singleton = factory.getBean(beanID, Hello_ScopeTest.class);
		h1_singleton.sayHello();
		System.out.println("		Obj:"+ System.identityHashCode(h1_singleton) );
	}
}
