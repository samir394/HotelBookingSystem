package com.hmsapp.config;

import com.hmsapp.service.JWTservice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {

    private final JWTservice jwtService;
    private final JwtFilter jwtFilter;

    // Proper dependency injection for JwtFilter
    public SecurityConfig(JWTservice jwtService, JwtFilter jwtFilter) {
        this.jwtService = jwtService;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        // Disable CSRF and CORS protection

        // Add JWT filter before AuthorizationFilter
        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtFilter, AuthorizationFilter.class);
        http.authorizeHttpRequests().anyRequest().permitAll();

        // Define authorization rules
      //  http.authorizeHttpRequests(auth -> auth
              // .requestMatchers("/api/auths/sign-up", "/api/auths/login").permitAll()
             //   .requestMatchers("/api/v1/property/addProperty").hasRole("PROPERTY_OWNER")
             //   .anyRequest().authenticated()
                http.authorizeHttpRequests()
                        //.requestMatchers("/api/auths/sign-up","/api/auths/login","/api/auths/property/sign-up")
                        //.permitAll()
                        //.requestMatchers("api/v1/property/addProperty")
                        //.hasRole("OWNER")
                        //.requestMatchers("/api/v1/property/deleteProperty")
                      //  .hasAnyRole("OWNER","ADMIN")
                        //.requestMatchers("api/auths/blog/sign-up")
                        //.hasRole("ADMIN")
                        //.anyRequest().authenticated();
        ;

        return http.build();
    }
}
