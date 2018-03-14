package org.springframework.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.hello.admin.AdminFormSecurityConfig;
import org.springframework.hello.api.v1.ApiSecurityConfig;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
@EnableWebSecurity
@Import({ApiSecurityConfig.class, AdminFormSecurityConfig.class})
public class WebSecurityConfig {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> configurer = auth.inMemoryAuthentication();
		// TODO provide encoder in UserDetailsService
		configurer.passwordEncoder(NoOpPasswordEncoder.getInstance()); 
		configurer.withUser("user").password("password").roles("USER");
		configurer.withUser("ana").password("password").roles("ADMIN");
	}
}
