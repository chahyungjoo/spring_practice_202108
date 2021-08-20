package com.practice.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.practice.vo.TokenVo;

@Mapper
@Repository
public interface TokenMapper {
	
	public TokenVo getRefreshToken(String id);

	public int updateRefreshToken(TokenVo tokenVo);
	
}
