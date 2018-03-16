package org.springframework.hello.api.v1;

import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Order(100)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                .antMatcher("/hello/api/**")
                .requiresChannel().anyRequest().requiresSecure()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/hello/api/login").permitAll()
                .antMatchers("/hello/api/v1/**").authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter("/hello/api/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler((req, res, ex) -> res.sendError(SC_UNAUTHORIZED))
                .authenticationEntryPoint((req, res, ex) -> res.sendError(SC_UNAUTHORIZED));
        }
}
