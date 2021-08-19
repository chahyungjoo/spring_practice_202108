package com.practice.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practice.member.vo.MemberVo;
import com.practice.user.vo.UserVo;

public interface UserService {
	
	public void insertUser(UserVo userVo);
	
	public UserVo selectUserById(Long id);
	
	public List<UserVo> selectUserList(UserVo userVo);
	
	
}
