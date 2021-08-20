package com.practice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.practice.vo.UserVo;

@Mapper
@Repository
public interface UserMapper {
	
	public void insertUser(UserVo userVo);
	
	public List<UserVo> selectUserList(UserVo userVo);
	
	public UserVo selectUserById(Long id);
	
	public Integer updateUser(UserVo userVo);
	
	public Integer deleteMember(Long id);
	
}
