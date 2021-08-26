package com.practice.config;

import com.practice.jwt.JwtAccessDeniedHandler;
import com.practice.jwt.JwtAuthenticationEntryPoint;
import com.practice.jwt.JwtSecurityConfig;
import com.practice.jwt.TokenProvider;
import com.practice.service.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// @EnableGlobalMethodSecurity : @PreAuthorize 검증 어노테이션을 메소드 단위로 사용하기 위해 추가
	
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
 
    public SecurityConfig(
            TokenProvider tokenProvider,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler
    ) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials
        // Use BCryptPasswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
 
    @Override
    public void configure(WebSecurity web) {
    	// 정적 resouces 경로는 security 적용 안함 (/resources/static/**)
    	web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    	//web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }
 
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()	// 토큰방식을 사용하므로 csrf는 disable
 
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                
                // H2-console을 위한 설정
                //.and()
                //.headers()
                //.frameOptions()
                //.sameOrigin()
                
                // session을 사용하지 않을때 설정을 STATELESS로 함
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                
                // 정적 resouces 경로는 security 적용 안함 (/resources/static/**)
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/images/**").permitAll()
                
                .antMatchers("/login/loginForm").permitAll()
                .antMatchers("/user/joinForm").permitAll()
                //.antMatchers("/api/hello").permitAll()
                //.antMatchers("/api/authenticate").permitAll()	// 토큰을 받기위한 login api
                //.antMatchers("/api/signup").permitAll()	// 회원가입을 위한 api
                .anyRequest().authenticated()
 
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));
    }
 
}