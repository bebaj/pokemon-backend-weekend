package com.mindtech.pokemon_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/h2-console/**").permitAll()  // H2 Console engedélyezése
                                .requestMatchers("/users/register").permitAll() // Regisztráció engedélyezése
                                .anyRequest().authenticated()                    // Minden más kérés autentikációt igényel
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")           // CSRF védelem kikapcsolása a H2 Console esetében
                )
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions.sameOrigin())  // Frame-Options engedélyezése a H2 Console számára
                )
                .formLogin(formLogin ->
                        formLogin.permitAll()                                // Alapértelmezett bejelentkezési űrlap használata
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
