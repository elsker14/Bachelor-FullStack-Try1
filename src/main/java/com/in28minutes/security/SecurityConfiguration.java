package com.in28minutes.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("Checu").password("dummy")
				.roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/login").permitAll()
				.antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
				.formLogin();
	}
}


//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        // authentication manager (see below)
//    	 auth.inMemoryAuthentication()
//         .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
//         .and()
//         .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
//         .and()
//         .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
//    }
//    
//    @Bean 
//    public PasswordEncoder passwordEncoder() { 
//        return new BCryptPasswordEncoder(); 
//    }
//
//    @Override
//    protected void configure(final HttpSecurity http) throws Exception {
//        // http builder configurations for authorize requests and form login (see below)
//    	http
//        .csrf().disable()
//        .authorizeRequests()
//        .antMatchers("/admin/**").hasRole("ADMIN")
//        .antMatchers("/anonymous*").anonymous()
//        .antMatchers("/login*").permitAll()
//        .anyRequest().authenticated()
//        .and()
//        .formLogin()
//        .loginPage("/login.html")
//        .loginProcessingUrl("/perform_login")
//        .defaultSuccessUrl("/homepage.html", true)
//        .failureUrl("/login.html?error=true")
//        .failureHandler(authenticationFailureHandler())
//        .and()
//        .logout()
//        .logoutUrl("/perform_logout")
//        .deleteCookies("JSESSIONID")
//        .logoutSuccessHandler(logoutSuccessHandler());
//        // ...
//    }
//}


