package com.javatechie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthConfig {

	private static final String[] WHITE_LIST_URL = {
	        "/auth/register",		"/auth/token",		"/auth/validate",
	        "/v2/api-docs",			"/v3/api-docs",		"/v3/api-docs/**",
	        "/swagger-resources",	"/swagger-resources/**",
	        "/configuration/ui",	"/configuration/security",
	        "/swagger-ui/**",		"/webjars/**",		"/swagger-ui.html",
	        //"/api/auth/**",			"/api/test/**",		"/authenticate"
	        "/auth/register", 		"/auth/token", 		"/auth/validate"
	    };
	
    // Returns your custom user service (fetches users from DB or hardcoded).
    @Bean
    UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
    
    // his replaces the old WebSecurityConfigurerAdapter
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(WHITE_LIST_URL).permitAll()
                    .anyRequest().authenticated()
                )
                .build();
    }

    // Encodes passwords using BCrypt — good for security:
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Configures how authentication works: uses your custom user service and password encoder.
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    // Spring’s main component for handling login logic. You inject it into your service to authenticate users manually if needed (like in a login controller).
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    
}




// his replaces the old WebSecurityConfigurerAdapter
/*@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.csrf().disable()
            .authorizeHttpRequests()
            // .requestMatchers("/auth/register", "/auth/token", "/auth/validate").permitAll()
            .requestMatchers(WHITE_LIST_URL).permitAll()  // <-- allow these endpoints
            .anyRequest().authenticated()                 // <-- secure everything else
            .and()
            .build();
} */