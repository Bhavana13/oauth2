/*package com.bhavana.oauth2.config;

*//*
*
* The SecurityConfig class you provided is still needed if you want to customize
* the security configuration for your application, such as setting up OAuth2 login,
* defining URL authorization rules, and other security-related settings.
*
* If you still want to define security rules explicitly
* (e.g., allow public access to /login and protect other endpoints),
* you should use ServerHttpSecurity instead of HttpSecurity.
*
* *//*

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange()
                .pathMatchers("/login", "/oauth2/**").permitAll()  // Public access to login and OAuth2 routes
                .anyExchange().authenticated()  // Require authentication for all other routes
                .and()
                .oauth2Login();  // Enable OAuth2 login

        return http.build();
    }
}*/
