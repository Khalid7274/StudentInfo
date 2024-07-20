package com.studentDirectory.studentInfo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    //Add support for JDBC no more hardcode
    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET, "/api/student/all").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET, "/api/student/**").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST, "/api/student/create").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PUT, "/api/student/update/**").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.DELETE, "/api/student/delete/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                );
        http.httpBasic(Customizer.withDefaults());

        // Disable Cross Site Request Forgery (CSRF)
        // In general, not required for stateless Rest APIs that use GET, POST, PUT and DELETE
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails wakil = User.builder()
                .username("wakil")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails haroon = User.builder()
                .username("haroon")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, wakil, haroon);
    }

     */

}
