package com.practice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.mapper.UserMapper;
import com.practice.service.UserService;
import com.practice.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	
	
	@Override
	public void insertUser(UserVo userVo) {
		userMapper.insertUser(userVo);
	}

	@Override
	public UserVo selectUserById(UserVo userVo) {
		return userMapper.selectUserById(userVo);
	}

	@Override
	public List<UserVo> selectUserList(UserVo userVo) {
		return userMapper.selectUserList(userVo);
	}
	
	
	
	
	
	
	
}
