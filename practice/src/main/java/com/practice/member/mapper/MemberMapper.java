package com.practice.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.practice.member.vo.MemberVo;

@Mapper
@Repository
public interface MemberMapper {
	
	public void insertMember(MemberVo memberVo);
	
	public List<MemberVo> selectMemberList(MemberVo memberVo);
	
	public MemberVo selectMemberById(Long id);
	
	public Integer updateMember(MemberVo memberVo);
	
	public Integer deleteMember(Long id);
	
}
