package com.practice.login.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practice.login.service.LoginService;
import com.practice.user.service.UserService;


@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	
	@GetMapping("/login")
	public String getLoginPage(Model model) {
		
		return "loginForm";
	}
	
	@PostMapping("/login")
	public String login(Model model) {
		
		return "home";
	}
	
	@PostMapping("/logout")
	public String logout(Model model) {
		
		return "home";
	}
	
}
