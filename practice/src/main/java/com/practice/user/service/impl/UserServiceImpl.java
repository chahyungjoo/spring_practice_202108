package com.practice.user.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.member.mapper.MemberMapper;
import com.practice.member.service.MemberService;
import com.practice.member.vo.MemberVo;
import com.practice.user.mapper.UserMapper;
import com.practice.user.service.UserService;
import com.practice.user.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	
	
	@Override
	public void insertUser(UserVo userVo) {
		userMapper.insertUser(userVo);
	}

	@Override
	public UserVo selectUserById(Long id) {
		return userMapper.selectUserById(id);
	}

	@Override
	public List<UserVo> selectUserList(UserVo userVo) {
		return userMapper.selectUserList(userVo);
	}
	
	
	
	
	
	
	
}
