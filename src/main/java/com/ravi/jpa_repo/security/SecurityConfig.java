package com.ravi.jpa_repo.security;

import com.ravi.jpa_repo.security.custom_filter.CustomSecurityFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //In build auth provider , enable it if not using default
   /* @Bean
    UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        UserDetails userDetails = User.withUsername("Alice")
                .password(passwordEncoder().encode("Alice"))
                .authorities("read").build();
        inMemoryUserDetailsManager.createUser(userDetails);
        return inMemoryUserDetailsManager;
    }*/

    //B
    @Bean
    SecurityFilterChain filterRequestChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.formLogin(Customizer.withDefaults());
        httpSecurity.authorizeHttpRequests(authorize -> {
            System.out.println("<<<<<<"+authorize.requestMatchers("/departments").toString().toString());
            //allowing any request
            //authorize.anyRequest()
            //or
            //deny request other than departments
            //authorize.requestMatchers("/departments").authenticated().anyRequest().denyAll();

            //permit
            authorize.requestMatchers("/students").authenticated().anyRequest().permitAll();

        });
        httpSecurity.addFilterBefore(new CustomSecurityFilter(), BasicAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
