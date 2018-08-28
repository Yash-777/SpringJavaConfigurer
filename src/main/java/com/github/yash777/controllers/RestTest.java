package com.github.yash777.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value={"/rest", "/r"})
public class RestTest {

	@RequestMapping(value = {"/message", "/m" })
	@ResponseBody
	public String message (Model model) {
		model.addAttribute("msg", "a spring-boot example");
		return "myPage";
	}
}
