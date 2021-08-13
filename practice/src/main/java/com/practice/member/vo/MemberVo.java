package com.practice.member.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVo {

	private Long memberSeq;
	private String memberId;
	private String password;
	private String name;
	private String email;
	private Long phone;
	private Date creDt;
	private Date updDt;
	
}
