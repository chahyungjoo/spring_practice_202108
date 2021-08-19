package com.practice.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class JwtFilter extends GenericFilterBean {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtFilter.class);
	public static final String AUTHORIZATION_HEADER = "Authorization";
	private TokenProvider tokenProvider;

	public JwtFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}

	// 토큰의 인증 정보를 SecurityContext에 저장하는 역할
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		String jwt = resolveToken(httpServletRequest);	// request header에서 토큰 정보를 꺼냄
		String requestURI = httpServletRequest.getRequestURI();

		if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {	// 토큰 유효성 검사
			
			// 토큰에서 Authentication 객체를 받아와서 SecurityContext에 셋팅
			Authentication authentication = tokenProvider.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
		} else {
			logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	// request header에서 토큰 정보를 꺼냄
	private String resolveToken(HttpServletRequest request) {
	      String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
	      
	      if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
	         return bearerToken.substring(7);
	      }
	      
	      return null;
	}
}
