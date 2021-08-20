package com.practice.vo;

import lombok.Data;

@Data
public class TokenVo {
	private String accessToken;
	private String refreshToken;
	private String token;
	private Integer userId;
	private String id;
	private UserVo user;
}
