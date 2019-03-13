package com.github.yash777.standalone;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;

public class Hello_ScopeTest implements DisposableBean {
	/* dependent object, we want to pass string through constructor/setter method */
	@Autowired(required=false)
	// @Qualifier("main")
	String name;

	// for constructor injection
	@Autowired(required=false)
	public Hello_ScopeTest(String name) {
		this.name=name;
		System.out.println("	Hello_ScopeTest(name) constructor called ");
	}

	// for setter injection one no parameter constructor is required
	public Hello_ScopeTest() {
		System.out.println("	Hello_ScopeTest() constructor called ");
	}
	// one setter method to receive String value
	public void setName(String n) {
		name = n;
		System.out.println("	setName() method called ");
	}

	// two life-cycle methods
	public void init() {
		System.out.println("	« « « « « init() method");
	}
	public void destroy() {
		System.out.println("	destroy() method");
	}

	// one business logic method
	public void sayHello() {
		System.out.println("	Hello "+name);
	}
}
