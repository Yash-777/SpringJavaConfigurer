package com.github.yash777.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = { "/controller", "/c" })
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = {"/message", "/m"}, method = RequestMethod.GET )
	public void message(HttpServletRequest request, HttpServletResponse response ) throws IOException {
		System.out.println("@Controller Get method called.");
	}
	
	@RequestMapping(value = "/getView", method = RequestMethod.GET )
	public ModelAndView setViewName( Model model ) {
		System.out.println("GET... /getView");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("test");
		return mav;
	}
}
