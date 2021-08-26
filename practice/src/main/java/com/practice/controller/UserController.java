package com.practice.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practice.service.UserService;
import com.practice.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@GetMapping("/joinForm")
	public String getJoinForm(Model model) {
		
		return "user/joinForm";
	}
	
	@PostMapping("/joinForm")
	public String insertUser(UserVo userVo) {
		
		// 권한 설정
		List<String> role = new ArrayList<String>();
		role.add("ROLE_USER");
		userVo.setRole(role);
		
		// password encoding
		userVo.setPassword(passwordEncoder.encode(userVo.getPassword()));
		
		
		
		userService.insertUser(userVo);
		
		return "redirect:/login/loginForm";
	}
	
	@PostMapping("/selectUserList")
	public String selectUserList(Model model, UserVo userVo) {
		
		//model.addAttribute("data", "hello");
		
		List<UserVo> userList = userService.selectUserList(userVo);
		
		model.addAttribute("userList", userList);
		
		return "hello";
	}
	
	@PostMapping("/selectUserById")
	public String selectUserById(Model model) {
		
		model.addAttribute("data", "hello");
		
		return "hello";
	}
	
	
	
}
