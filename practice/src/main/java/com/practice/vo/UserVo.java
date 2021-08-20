package com.practice.vo;

import java.util.Date;

import lombok.Data;

@Data
public class UserVo {

	private Long userSeq;
	private String userId;
	private String password;
	private String name;
	private String email;
	private Long phone;
	private Date creDt;
	private Date updDt;
	
}
