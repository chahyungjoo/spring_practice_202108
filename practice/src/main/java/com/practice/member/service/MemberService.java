package com.practice.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practice.member.vo.MemberVo;

public interface MemberService {
	
	public void insertMember(MemberVo param);
	
	public MemberVo selectMemberById(Long id);
	
	public List<MemberVo> selectMemberList(MemberVo memberVo);
	
	
}
