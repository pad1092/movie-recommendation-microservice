package com.movierec.movieservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests(authorize -> {
            try {
                authorize
                        .antMatchers("/api/admin/**").authenticated()
                        .anyRequest().permitAll()
                        .and()
                        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
