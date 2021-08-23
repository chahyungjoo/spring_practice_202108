package com.practice.service;

import java.util.List;

import com.practice.vo.UserVo;

public interface UserService {
	
	public void insertUser(UserVo userVo);
	
	public UserVo selectUserById(UserVo userVo);
	
	public List<UserVo> selectUserList(UserVo userVo);
	
	
}
