package com.practice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.practice.jwt.TokenProvider;
import com.practice.service.LoginService;
import com.practice.service.UserService;
import com.practice.vo.UserVo;


@Controller
@RequestMapping("/login")
public class LoginController {
	
	//@Autowired
	//private AuthenticationManager authenticationManager;
	
	private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
 
    public LoginController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }
	
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
	public void login(HttpServletResponse response, Model model, UserVo user) throws Exception {
		
		// user정보 리턴
		UserVo userVo = userService.selectUserById(user.getUserId());
		
		// 해당 ID가 없을 때 or 비밀번호가 틀렸을 때
		if (userVo == null || !passwordEncoder.matches(user.getPassword(), userVo.getPassword())) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		} else {
			response.setStatus(HttpServletResponse.SC_OK);
			
			/*try {
				// 비밀번호 인증
				Authentication authentication = authenticationManager
						.authenticate(new UsernamePasswordAuthenticationToken(
								user.getUsername(), user.getPassword(), userVo.getAuthorities()));
				SecurityContextHolder.getContext().setAuthentication(authentication);
	        } catch (DisabledException e) {
	        	logger.error("USER_DISABLED" + e.getMessage());
				throw new Exception("USER_DISABLED", e);
			} catch (BadCredentialsException e) {
				throw new Exception("INVALID_CREDENTIALS", e);
			}*/
			
			UsernamePasswordAuthenticationToken authenticationToken =
	                new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword());
	 
	        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	 
	        String jwt = tokenProvider.createToken(authentication);
		}
		
	}
	
	/*@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserVO user) throws Exception {
		// user정보 리턴
		UserVO userVo = userService.getUserById(user.getUsername());
		if (userVo == null) {
			Map<String, Object> res = new HashMap<>();
			String message = "Id is not found";
			res.put("message", message);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
		}
		try {
			// 비밀번호 인증
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					user.getUsername(), user.getPassword(), userVo.getAuthorities()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (DisabledException e) {
        	logger.error("USER_DISABLED" + e.getMessage());
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		
		// token 생성
		String accessToken = jwtUtil.generateToken(userVo);
		String refreshToken = jwtUtil.generateRefreshToken(userVo);
		
		// refreshToken 저장
		TokenVO _refreshToken = new TokenVO();
		_refreshToken.setRefreshToken(refreshToken);
		_refreshToken.setUserId(userVo.getUserId());
		authService.updateRefreshToken(_refreshToken);

		Map<String, Object> res = new HashMap<>();
		res.put("token", accessToken);
		res.put("user", userVo);
		return ResponseEntity.ok(res);
	}*/
	
	@PostMapping("/logout")
	public String logout(Model model) {
		
		return "home";
	}
	
}
