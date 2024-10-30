package com.example.employee_service.config;


import com.example.employee_service.security.JwtTokenFilter;
import jakarta.ws.rs.HttpMethod;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
    public class SpringSecurityConfig  {
        JwtTokenFilter jwtTokenFilter;

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
        //Security Filter chain after many filters authorize user for end Point Permission
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            httpSecurity.csrf().disable().authorizeHttpRequests(authorize -> {
                        //authorize.requestMatchers(HttpMethod.POST, "/api/employee").permitAll();
                        authorize.requestMatchers(HttpMethod.GET,"/api/employee").permitAll();
                        authorize.requestMatchers(HttpMethod.POST,"/api/employee/save").hasRole("ADMIN");
                        authorize.requestMatchers(HttpMethod.POST,"/api/auth/**").permitAll();
                        authorize.anyRequest().authenticated();
                    }).sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);//.httpBasic(Customizer.withDefaults());
            httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
            return httpSecurity.build();
        }
}


