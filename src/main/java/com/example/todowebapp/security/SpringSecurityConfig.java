package com.example.todowebapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecurityConfig {
    //typically will use LDAP or Database
    //For now we use InMemoryUserDetailsManager
   // InMemoryUserDetailsManager(UserDetails user)
    @Bean
    public InMemoryUserDetailsManager userDetails()
    {

        UserDetails userDetails1 = getUserDetails("dmtuser", "password");
        UserDetails userDetails2 = getUserDetails("ricoh", "password");
        return new InMemoryUserDetailsManager(userDetails1,userDetails2);
    }

    private static UserDetails getUserDetails(String dmtuser, String password) {
        return User.withDefaultPasswordEncoder()
                .username(dmtuser)
                .password(password)
                .roles("USER","ADMIN")
                .build();

    }

    //All URLs are protected
    //A login form is shown for unauthorized requests
    //CSRF disable
    //Frames are not allowed to be rendered.So we need to allow them.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());
        http.formLogin(withDefaults());

        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions(frameOptionsConfig-> frameOptionsConfig.disable()));

        return http.build();
    }

}
