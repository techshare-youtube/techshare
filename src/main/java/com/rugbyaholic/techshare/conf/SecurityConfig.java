package com.rugbyaholic.techshare.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.ForwardAuthenticationFailureHandler;

import com.rugbyaholic.techshare.auth.DatabaseUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DatabaseUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// アクセス制御
		http.authorizeRequests()
			.antMatchers("/css/**", "/js/**", "/img/**", "/UserRegistration.do").permitAll()
			.anyRequest().authenticated()
		// ログイン
		.and()
			.formLogin().loginPage("/Login.html")
			.loginProcessingUrl("/Login.do")
			.failureHandler(new ForwardAuthenticationFailureHandler("/Login.err"))
			.usernameParameter("email").passwordParameter("password").permitAll()
		// ログアウト
		.and()
			.logout()
			.logoutUrl("/Logout.do")
			.logoutSuccessUrl("/Login.html")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}