package com.bansarishah.QuoteVerse.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.AntPathMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean // This bean is used for encoding passwords
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean // This bean defines the security rules for HTTP requests
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> auth
                // Allow access to the homepage, registration page, static assets, and H2 console for everyone
                .requestMatchers("/", "/register", "/css/**", "/js/**", "/h2-console/**").permitAll()
                // All other requests require the user to be authenticated
                .anyRequest().authenticated()
        )
                .formLogin(form -> form
                        .loginPage("/login")// Use a custom login page
                        .permitAll()// Everyone can access the login page
                )
        // Configure logout
                .logout(logout -> logout.permitAll());

        // --- THIS IS THE MODERN AND CORRECT WAY TO CONFIGURE CSRF ---
        // We use a lambda to configure the csrf customizer.
        httpSecurity.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"));

        // --- THIS IS THE MODERN AND CORRECT WAY TO CONFIGURE FRAME OPTIONS ---
        // This is also a cleaner way to write the frame options configuration.
        httpSecurity.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)); // Use sameOrigin for better security than disable

        return httpSecurity.build();

    }
}
