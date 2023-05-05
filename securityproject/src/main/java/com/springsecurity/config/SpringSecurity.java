package com.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {
    @Bean
    protected static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    //This will configure our SecurityFilterChain
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
    	http.csrf().disable()
    		.authorizeRequests()
    		.antMatchers("/register/**").permitAll()
    		.antMatchers("/index").permitAll()
    		.antMatchers("/users").hasRole("ADMIN")
    		.and()
    		.formLogin(
    			form -> form
    					.loginPage("/login")
    					.loginProcessingUrl("/login")
    					.defaultSuccessUrl("/users")
    					.permitAll()
    		).logout(
    			logout -> logout
    					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
    					.permitAll()
    		);
    	return http.build();
    }
}
