package com.tmp.BTS.security.config;


import com.tmp.BTS.security.JwtAuthenticationFilter;
import com.tmp.BTS.security.JwtAuthenticationProvider;
import com.tmp.BTS.security.JwtAuthorizationFilter;
import com.tmp.BTS.security.JwtLoginSuccessHandler;
import com.tmp.BTS.security.utils.TokenUtils;
import com.tmp.BTS.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(POST, "/users/sign-up").permitAll()
                .antMatchers(GET, "/users/**").hasROLE("USER")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin()
                .disable()
                .addFilterBefore(JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(JwtAuthorizationFilter(), BasicAuthenticationFilter.class);

    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(customAuthenticationProvider());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter JwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        jwtAuthenticationFilter.setFilterProcessesUrl("user/sign-in");
        jwtAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
        jwtAuthenticationFilter.afterPropertiesSet();
        return jwtAuthenticationFilter;
    }

    @Bean
    public JwtAuthorizationFilter JwtAuthorizationFilter() throws Exception {
        JwtAuthorizationFilter jwtAuthorizationFilter = new JwtAuthorizationFilter(authenticationManager(), tokenUtils, userRepository);
        return jwtAuthorizationFilter;
    }

    @Bean
    public JwtLoginSuccessHandler customLoginSuccessHandler() {
        return new JwtLoginSuccessHandler();
    }

    @Bean
    public JwtAuthenticationProvider customAuthenticationProvider() {
        return new JwtAuthenticationProvider(bCryptPasswordEncoder());
    }
}
