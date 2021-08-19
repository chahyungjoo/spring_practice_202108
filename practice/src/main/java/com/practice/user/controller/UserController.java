package com.practice.user.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.practice.member.service.MemberService;
import com.practice.member.vo.MemberVo;
import com.practice.user.service.UserService;
import com.practice.user.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/*@GetMapping("/hello")
	public String hello(Model model) {
		
		model.addAttribute("data", "hello");
		
		return "hello";
	}*/
	
	@PostMapping("/join")
	public String insertMember(Model model, UserVo memberVo) {
		
		memberVo.setId("aa");
		memberVo.setEmail("aaa@gmial.com");
		
		userService.insertUser(memberVo);
		
		return "hello";
	}
	
	@PostMapping("/selectUserList")
	public String selectUserList(Model model, UserVo memberVo) {
		
		//model.addAttribute("data", "hello");
		
		List<UserVo> userList = userService.selectUserList(memberVo);
		
		model.addAttribute("userList", userList);
		
		return "hello";
	}
	
	@PostMapping("/selectUserById")
	public String selectUserById(Model model) {
		
		model.addAttribute("data", "hello");
		
		return "hello";
	}
	
	
	
}
