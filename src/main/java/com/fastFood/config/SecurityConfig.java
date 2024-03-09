package com.fastFood.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String[] GET_PUBLIC_REQUEST = {
            "/clients",
            "/menu",
            "/menu/{id}",
            "/transaction"
    };
    public static final String[] POST_REQUEST_PUBLIC = {
            "/clients",
            "/transaction",
            "/auth/login"
    };
    public static final String[] GET_REQUEST_PRIVATE = {
            "/clients/getAll",
            "/clients/{id}",
            "/transaction/getAll"
    };
    public static final String[] POST_REQUEST_PRIVATE = {
            "/menu/food"
    };

    @Autowired
    public SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, GET_PUBLIC_REQUEST).permitAll()
                        .requestMatchers(HttpMethod.POST, POST_REQUEST_PUBLIC).permitAll()
                        .requestMatchers(HttpMethod.GET, GET_REQUEST_PRIVATE).hasRole("COMPANY")
                        .requestMatchers(HttpMethod.POST, POST_REQUEST_PRIVATE).hasRole("COMPANY")
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}