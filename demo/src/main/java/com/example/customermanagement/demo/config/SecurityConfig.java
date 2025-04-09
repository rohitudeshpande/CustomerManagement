package com.example.customermanagement.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Configuration
public class SecurityConfig {

    @Bean
    UserDetailsService userDetailsService( @Value("${authentication.admin-user}") String adminUser,  @Value("${authentication.readonly-user}") String readOnlyUser) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        addUser(adminUser,"ADMIN",manager);
        addUser(readOnlyUser,"READONLY",manager);
        return manager;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(
                    auth -> auth.requestMatchers(HttpMethod.POST,"/addCustomer").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET,"/getCustomer/**").hasAnyRole("ADMIN","READONLY")
                            .requestMatchers("/h2-console/**").permitAll()
                            .anyRequest().authenticated())
                .httpBasic(httpBasic ->{})
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        return http.build();

    }

    private void addUser(String encodedUser,String role, InMemoryUserDetailsManager manager){
        if(!encodedUser.startsWith("Basic ")){
            return;
        }
        String base64 = encodedUser.substring(6);
        String decodedUser = new String(Base64.getDecoder().decode(base64),StandardCharsets.UTF_8);
        String[] splitUsers = decodedUser.split(":");
        if(splitUsers.length == 2){
            String username = splitUsers[0];
            String password = splitUsers[1];
            manager.createUser(User
                    .withUsername(username)
                    .password(passwordEncoder().encode(password))
                    .roles(role)
                    .build());
        }
    }
}
