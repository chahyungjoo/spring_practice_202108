package com.practice.member.controller;

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

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	/*@GetMapping("/hello")
	public String hello(Model model) {
		
		model.addAttribute("data", "hello");
		
		return "hello";
	}*/
	
	@PostMapping("/join")
	public String insertMember(Model model, MemberVo memberVo) {
		
		memberVo.setMemberId("aa");
		memberVo.setEmail("aaa@gmial.com");
		
		memberService.insertMember(memberVo);
		
		return "hello";
	}
	
	@PostMapping("/selectMemberList")
	public String selectMemberList(Model model, MemberVo memberVo) {
		
		//model.addAttribute("data", "hello");
		
		List<MemberVo> memberList = memberService.selectMemberList(memberVo);
		
		model.addAttribute("memberList", memberList);
		
		return "hello";
	}
	
	@PostMapping("/selectMemberById")
	public String selectMemberById(Model model) {
		
		model.addAttribute("data", "hello");
		
		return "hello";
	}
	
	
	
}
