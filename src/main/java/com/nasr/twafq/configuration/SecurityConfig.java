package com.nasr.twafq.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

import com.nasr.twafq.jwt.JwtFilter;
import com.nasr.twafq.user.service.UserDetailsServiceImpl;

/*
 * the explaination of 
 *the crsf and how it work is :
 * we have a public endpoint /hello that we want to ignore the csrf token for it (we can add more endpoints to ignore the csrf token for it)
 * we have a filter that will add the csrf token to the response header
 * and the csrf token will be added to the cookie and the response header
 * and every request will be checked for the csrf token and if it is not there it will return 403
 * we add the csrf token to the request header in the form of X-XSRF-TOKEN = csrf token
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final CorsFilter corsFilter;

    public SecurityConfig(JwtFilter jwtFilter, CorsFilter corsFilter) {
        this.jwtFilter = jwtFilter;
        this.corsFilter = corsFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // CsrfTokenRequestAttributeHandler requestHandler = new
        // CsrfTokenRequestAttributeHandler();

        http

                .addFilterBefore(corsFilter, CorsFilter.class)
                //
                // // i put the csrf config in the csrfconfig class and i will call it here
                // .csrf((csrf) -> csrf.getClass().equals(CsrfConfig.class));

                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/", "/error", "/webjars/**", "/index.html", "/signup",
                                "/api/signin", "/api/refreshtoken", "/api/**", "/api/", "/api/addnewuser",
                                "/api/authenticate",
                                "/api/validateToken", "/api/verifyemail",
                                "/api/refreshtoken", "/api/validatetoken",
                                "/auth/**",
                                "/v2/api-docs",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources",
                                "/swagger-resources/**",
                                "/configuration/ui",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/swagger-ui.html",
                                "/pay.html",
                                "/api/verifyemail",
                                "/api/forgotpassword",
                                "/api/resetpassword",
                                "/api/colors",
                                "/api/users/filter",
                                "/api/blogs",
                                "/api/blog/")
                        .permitAll()
                        .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(withDefaults())
                // .sessionManagement(session ->
                // session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
