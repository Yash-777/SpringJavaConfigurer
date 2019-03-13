package com.github.yash777.standalone.annotation;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean(name="S1")
	public MyService Service1() {
		MyService s1 = new MyService();
		s1.setId(10);
		return s1;
	}
	
	@Bean(name="S2")
	public MyService Service2() {
		MyService s2 = new MyService();
		s2.setId(777);
		return s2;
	}
}

class MyService implements DisposableBean {
	//@Autowired - @Bean it self creates object so no need of @Autowired @Qualifier
	int id;
	
	public void displayID() {
		System.out.println("My Service id : "+ this.id);
	}
	
	// for setter injection one no parameter constructor is required
	public MyService() {
		System.out.println("Hello_ScopeTest() constructor called ");
	}
	// one setter method to receive String value
	public void setId(int id) {
		this.id = id;
		System.out.println("setId() method called with value : " + id);
	}
	
	// two life-cycle methods
	public void init() {
		System.out.println("« « « « « init() method");
	}
	public void destroy() {
		System.out.println("destroy() method");
	}
}
