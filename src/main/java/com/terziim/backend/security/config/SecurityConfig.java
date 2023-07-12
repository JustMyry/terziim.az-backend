package com.terziim.backend.security.config;


import com.terziim.backend.auth.entrypoint.JwtAuthenticationEntryPoint;
import com.terziim.backend.security.jwt.JwtAccessDeniedHandler;
import com.terziim.backend.security.jwt.JwtFilter;
import com.terziim.backend.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private JwtFilter jwtAuthorizationFilter;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDetailsServiceImpl userDetailsService;
    public SecurityConfig(JwtAccessDeniedHandler jwtAccessDeniedHandler, JwtFilter jwtAuthorizationFilter,
                          JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, UserDetailsServiceImpl userDetailsService,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


//    // Re Code it
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/api/**");
//    }




    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Create a Log for here.
        // Log must keep all data which authenticate someone.
        http.cors();
        http.csrf().disable();
        http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/signin").permitAll();
                    auth.requestMatchers("/signup/istifadeci").permitAll();
                    auth.requestMatchers("/@/*").permitAll();
                    auth.requestMatchers("/axtar/**").permitAll();
                    auth.requestMatchers("/test/**").permitAll();
                    auth.requestMatchers("/serh/**").permitAll();
                    auth.requestMatchers("/category/**").permitAll();
                    auth.requestMatchers("/mehsul/aciq/**").permitAll();
                    auth.requestMatchers("/mehsul/istifadeci/**").hasAnyAuthority("USER");
                    auth.requestMatchers("/mehsul/satici/**").hasAnyAuthority("SELLER");
                    auth.requestMatchers("/@/*/**").hasAnyAuthority("USER");
                    auth.requestMatchers("/test/user/**").hasAnyAuthority("USER");
                    auth.requestMatchers("/report/**").hasAnyAuthority("USER");
                    auth.requestMatchers("/sifarisler/alici/**").hasAnyAuthority("USER");
                    auth.requestMatchers("/sifarisler/satici/**").hasAnyAuthority("SELLER");
                    auth.requestMatchers("/anlasilmazliq/alici/**").hasAnyAuthority("USER");
                    auth.requestMatchers("/anlasilmazliq/satici/**").hasAnyAuthority("SELLER");
                    auth.requestMatchers("/follow/**").hasAnyAuthority("USER");
                    auth.requestMatchers("/dm/**").hasAnyAuthority("USER");
                    auth.requestMatchers("/sual/**").hasAnyAuthority("USER");
                    auth.requestMatchers("/teklif/**").hasAnyAuthority("USER");
                    auth.requestMatchers("/bildirimler/**").hasAnyAuthority("USER");
                    auth.requestMatchers("/mehsul/**").hasAnyAuthority("USER");
                    auth.requestMatchers("/ulduz/**").hasAnyAuthority("USER");
                    auth.requestMatchers("/teleb/**").hasAnyAuthority("USER");
                    auth.requestMatchers("/topluluq/**").hasAnyAuthority("USER");
                    auth.requestMatchers("/signup/admin").hasAnyAuthority("ADMIN");
                    auth.requestMatchers("/administration/**").hasAnyAuthority("ADMIN"); // Bunu MUTLEQ DUZELT
                    auth.requestMatchers("/test/admin/**").hasAnyAuthority("ADMIN");
                    auth.requestMatchers("/authority/**").hasAnyAuthority("ADMIN");
                    auth.anyRequest().authenticated();
                })
                .exceptionHandling().accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        return authenticationProvider;
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}

