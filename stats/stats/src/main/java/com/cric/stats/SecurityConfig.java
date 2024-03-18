package com.cric.stats;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig  {

	@Bean
    public UserDetailsService userDetailsService() {
        UserDetails userDetails = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
            http.authorizeHttpRequests().requestMatchers("/api/**")
            .hasRole("ADMIN") // Restrict /api/** to users with ADMIN role
                    .anyRequest().fullyAuthenticated().and().httpBasic(); // Require authentication for any other request
           
             // Use HTTP Basic authentication
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authentication -> authentication;
    }
}

