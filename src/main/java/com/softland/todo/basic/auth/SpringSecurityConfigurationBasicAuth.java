package com.softland.todo.basic.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfigurationBasicAuth extends WebSecurityConfigurerAdapter{


	//copiamos y sobreescribimos este metodo que se encuentra en WebSecurityConfigurerAdapter
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()   //necesitamos desabilitar CSRF token
			.authorizeRequests()
			.antMatchers(HttpMethod.OPTIONS, "/**").permitAll() //allow preflight request to all URL's. The pre-flight request is an options request
				.anyRequest().authenticated()
				.and()
			//.formLogin().and()   //este no es necesario
			.httpBasic();
	}
	
}
