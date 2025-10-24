package org.igesta.security;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    CustomAccessDeniedHandler customAcessDeniedHandler;

    public SecurityConfig(CustomAccessDeniedHandler customAcessDeniedHandler) {
        this.customAcessDeniedHandler = customAcessDeniedHandler;
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager (PasswordEncoder enconder){
        Dotenv dotenv = Dotenv.load();
        String adminUsername = dotenv.get("API_ADMIN_USERNAME");
        String adminPassword = dotenv.get("API_ADMIN_PASSWORD");
        UserDetails admin = User.withUsername(adminUsername)
                .password(enconder.encode(adminPassword))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/igesta/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAcessDeniedHandler))
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }


}
