package com.portfolio.site;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Seguranca {

    @Bean
    SecurityFilterChain configurar(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(acesso -> acesso
                .requestMatchers("/admin.html", "/api/admin/**").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(login -> login.defaultSuccessUrl("/admin.html").permitAll())
            .logout(logout -> logout.logoutSuccessUrl("/"))
            .csrf(csrf -> csrf.ignoringRequestMatchers("/api/contato"))
            .build();
    }
}
