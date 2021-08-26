package com.practice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.jwt.JwtFilter;
import com.practice.jwt.TokenProvider;
import com.practice.service.LoginService;
import com.practice.service.UserService;
import com.practice.vo.UserVo;


@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenProvider tokenProvider;
	
	/*private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
 
    public LoginController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }*/
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	
	@GetMapping("/loginForm")
	public String getLoginForm(Model model) {
		
		return "login/loginForm";
	}
	
	@PostMapping("/loginForm")
	public ResponseEntity<?> login(Model model, UserVo user) throws Exception {
		
		// user정보 리턴
		UserVo userVo = userService.selectUserById(user.getUserId());
		
		// 해당 ID가 없을 때 or 비밀번호가 틀렸을 때
		if (userVo == null || !passwordEncoder.matches(user.getPassword(), userVo.getPassword())) {
			// response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			//return ResponseEntity.ok(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
			Map<String, Object> res = new HashMap<>();
			String message = "아이디 혹은 비밀번호가 잘못되었습니다.";
			res.put("message", message);
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
			
		} else {
			// response.setStatus(HttpServletResponse.SC_OK);
			
			try {
				// 비밀번호 인증
				Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(
								user.getUserId(), user.getPassword(), userVo.getAuthorities()));
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
				String accessToken = tokenProvider.createToken(authentication);
				
				Map<String, Object> res = new HashMap<>();
				res.put("token", accessToken);
				res.put("user", userVo);
				
				HttpHeaders httpHeaders = new HttpHeaders();
		        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + accessToken);
				
				// return ResponseEntity.ok(res);
		        
		        return new ResponseEntity<>(res, httpHeaders, HttpStatus.OK);
				
	        } catch (DisabledException e) {
	        	logger.error("USER_DISABLED" + e.getMessage());
				throw new Exception("USER_DISABLED", e);
			} catch (BadCredentialsException e) {
				throw new Exception("INVALID_CREDENTIALS", e);
			}
			
			/*UsernamePasswordAuthenticationToken authenticationToken =
	                new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword(), userVo.getAuthorities());
	 
	        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	 
	        String jwt = tokenProvider.createToken(authentication);*/
		}
		
	}
	
	@PostMapping("/logout")
	public String logout(Model model) {
		
		return "home";
	}
	
}
