package com.practice.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.practice.member.vo.MemberVo;
import com.practice.user.vo.UserVo;

@Mapper
@Repository
public interface UserMapper {
	
	public void insertUser(UserVo userVo);
	
	public List<UserVo> selectUserList(UserVo userVo);
	
	public UserVo selectUserById(Long id);
	
	public Integer updateUser(UserVo userVo);
	
	public Integer deleteMember(Long id);
	
}
