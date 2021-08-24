package com.practice.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.practice.vo.RoleVo;
import com.practice.vo.UserVo;

@Mapper
@Repository
public interface RoleMapper {

	public List<RoleVo> selectRoles();

	public List<String> selectRolesByUserUserSeq(Long userSeq);
	
	public void insertRolesByUserSeq(UserVo userVO);

	public void deleteRolesByUserSeq(UserVo userVO);

}
