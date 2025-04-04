package com.investscreener.investscreener.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;


import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private UserDetailsService userDetailsService ; // verify username and pswd

    @Autowired
    private JwtFilter jwtFilter;

    // Configure and return the object of security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                .requestMatchers("login", "register").permitAll()
                .anyRequest().authenticated())
                //.authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }




    // Implement userDetailsService : fetch from db
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        //Converts the password received into a hash and the verifies it
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    // Gte hold of authentication manager - Implement jwt
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}


// Stateless applications are generally more scalable, as each request is independent
// and can be handled by any available server using load balancing. Stateful
// applications have tightly coupled instances making it more difficult to scale.

// https://www.redhat.com/en/topics/cloud-native-apps/stateful-vs-stateless#:~:text=Stateless%20applications%20can%20be%20more,or%20clustering%2C%20are%20in%20place.


// Authentication provider
//@Bean
//public UserDetailsService userDetailsService() {
//    UserDetails user1 = User
//            .withDefaultPasswordEncoder()
//            .username("maby")
//            .password("maby")
//            .roles("USER")
//            .build();
//    return new InMemoryUserDetailsManager(user1); // Class that implements UserDetailsService
//}