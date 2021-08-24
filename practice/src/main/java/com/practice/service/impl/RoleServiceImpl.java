package com.practice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.mapper.RoleMapper;
import com.practice.service.RoleService;
import com.practice.vo.RoleVo;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleMapper roleMapper;

	@Override
	public List<RoleVo> selectRoles() {
		return roleMapper.selectRoles();
	}	
	
}
