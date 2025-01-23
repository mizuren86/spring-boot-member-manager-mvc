package com.springboot.thymeleafdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // add support for JDBC ... no more hardcoded users
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select username, password, enabled from users where username=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select username, authority from authorities where username=?");
        return jdbcUserDetailsManager;
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configuer ->
                        configuer
                                // Common access for all authenticated users
                                .requestMatchers("/").hasRole("MEMBER")
                                .requestMatchers("/members/list").hasRole("MEMBER")

                                // MANAGER access
                                .requestMatchers("/members/leaders/**").hasRole("MANAGER")
                                .requestMatchers("/members/showFormForAdd").hasRole("MANAGER")
                                .requestMatchers("/members/showFormForUpdate").hasRole("MANAGER")
                                .requestMatchers("/members/save").hasRole("MANAGER")

                                // ADMIN access
                                .requestMatchers("/members/systems/**").hasRole("ADMIN")
                                .requestMatchers("/members/delete").hasRole("ADMIN")

                                // Any other requests require authentication
                                .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPage")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout -> logout.permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );

        return http.build();
    }

    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails soyo = User.builder()
                .username("soyo")
                .password("{noop}test123")
                .roles("MEMBER")
                .build();

        UserDetails rikki = User.builder()
                .username("rikki")
                .password("{noop}test123")
                .roles("MEMBER", "MANAGER")
                .build();

        UserDetails sakiko = User.builder()
                .username("sakiko")
                .password("{noop}fun123")
                .roles("MEMBER", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(soyo, rikki, sakiko);

    }
    */

}

