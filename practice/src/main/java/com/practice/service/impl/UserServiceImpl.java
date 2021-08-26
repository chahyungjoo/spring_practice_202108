package com.practice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.mapper.RoleMapper;
import com.practice.mapper.UserMapper;
import com.practice.service.UserService;
import com.practice.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	RoleMapper roleMapper;
	
	
	@Transactional
	@Override
	public void insertUser(UserVo userVo) {
		userMapper.insertUser(userVo);
		roleMapper.insertRolesByUserSeq(userVo);
	}

	@Override
	public UserVo selectUserById(String userId) {
		return userMapper.selectUserById(userId);
	}

	@Override
	public List<UserVo> selectUserList(UserVo userVo) {
		return userMapper.selectUserList(userVo);
	}
	
	
	
	
	
	
	
}
