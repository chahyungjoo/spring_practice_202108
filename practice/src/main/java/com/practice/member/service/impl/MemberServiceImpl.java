package com.practice.member.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.member.mapper.MemberMapper;
import com.practice.member.service.MemberService;
import com.practice.member.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberMapper memberMapper;
	
	
	@Override
	public void insertMember(MemberVo memberVo) {
		memberMapper.insertMember(memberVo);
	}

	@Override
	public MemberVo selectMemberById(Long id) {
		return memberMapper.selectMemberById(id);
	}

	@Override
	public List<MemberVo> selectMemberList(MemberVo memberVo) {
		return memberMapper.selectMemberList(memberVo);
	}
	
	
	
	
	
	
	
}
