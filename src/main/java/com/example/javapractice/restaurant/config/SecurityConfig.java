package com.example.javapractice.restaurant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String USER_AUTHORITY = "USER";
    public static final String ADMIN_AUTHORITY = "ADMIN";

    @Bean // (1)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt way of encoding
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder()
                .username("user1") //
                    .password("$2a$12$dYGXTXzAT0DeJMQJTD7EmOV459yiwUV23K.qZasZwJRzgBLD.ISTy") // useronepas
                .authorities(USER_AUTHORITY)
                .build();

        UserDetails admin = User.builder()
                .username("admin") //
                .password("$2a$12$0ny4A3Q2B/WpeY3DPW.4Ye4nfbE7BhqHyrcIrV6R66fqDO444ncyW") // secret
                .authorities(USER_AUTHORITY, ADMIN_AUTHORITY)
                .build();

        UserDetails admin2 = User.builder()
                .username("foxxy") //
                .password("$2a$12$0ny4A3Q2B/WpeY3DPW.4Ye4nfbE7BhqHyrcIrV6R66fqDO444ncyW") // secret
                .authorities(USER_AUTHORITY, ADMIN_AUTHORITY)
                .build();
        // This is not ready for Production class. it is just for simple test
        // In real case there should be a service class, in service layer that load data from storage (e.g. SQL and appropriate table)
        return new InMemoryUserDetailsManager(user1, admin, admin2);
    }

    // Add a component that will identify user by http provide security data
    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder); // it is mandatory to set
        return new ProviderManager(authenticationProvider);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService
    ) throws Exception {
        http
                .securityMatcher("/api/**")
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET).hasAuthority(USER_AUTHORITY) // user with authority USER should execute GET request
                        .requestMatchers(HttpMethod.POST).hasAuthority(ADMIN_AUTHORITY) // user with authority ADMIN should execute POST request
                        .requestMatchers(HttpMethod.PUT).hasAuthority(ADMIN_AUTHORITY) // user with authority ADMIN should execute PUT request
                        .requestMatchers(HttpMethod.DELETE).hasAuthority(ADMIN_AUTHORITY) // user with authority ADMIN should execute DELETE request
                        .anyRequest().authenticated() // config that every request should be authenticated

                )
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults()) // user Basic Auth
                .authenticationManager(authenticationManager) // Setup a way to identification who call http request?
                .userDetailsService(userDetailsService)
        ;
        DefaultSecurityFilterChain build = http.build();
        return build;
    }
}
