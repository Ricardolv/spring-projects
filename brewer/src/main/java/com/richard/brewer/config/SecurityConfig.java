package com.richard.brewer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.richard.brewer.security.AppUserDetailsService;

@EnableWebSecurity
@ComponentScan(basePackageClasses = AppUserDetailsService.class)
@EnableGlobalMethodSecurity(prePostEnabled = true) // anotacao que habilita anotacao @PreAuthorize para especificar autorizacao de acesso por metodo
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String LOGIN_RUL = "/login";
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
		.antMatchers("/layout/**")
		.antMatchers("/images/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/users/**").hasRole("REGISTER_USER")
			.antMatchers("/citys/**").hasRole("REGISTER_CITY")
			.antMatchers("/beers/**").hasRole("REGISTER_BEER")
			.antMatchers("/clients/**").hasRole("REGISTER_CLIENT")
			.antMatchers("/styles/**").hasRole("REGISTER_STYLE")
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage(LOGIN_RUL)
			.permitAll()
			.and()
		.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.and()
		.sessionManagement() 
			.invalidSessionUrl(LOGIN_RUL);
//			.maximumSessions(1) // limitar sessao de usuario logado 
//			.expiredUrl("/login");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String tmp = passwordEncoder.encode("123");
		System.out.println("encode: " + tmp);
		if (tmp.equalsIgnoreCase("$2a$10$ANHMsxlsAxuEkWAer7VtUOlQMYbc7yHIaxj5mbC2cHagYulPJz4KW")) {
			System.out.println("OPA");
		}
		
	}

}
