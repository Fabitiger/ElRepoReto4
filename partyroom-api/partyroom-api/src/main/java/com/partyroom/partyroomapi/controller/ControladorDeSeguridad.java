package com.partyroom.partyroomapi.controller;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControladorDeSeguridad extends WebSecurityConfigurerAdapter{
@Override
    protected void configure(HttpSecurity http) throws Exception {
//     http.authorizeRequests(a -> a.antMatchers("/", "/url/**", "/error", 
//                "/webjars/**", "/Reservation/**","/Client/**","/Category/**", 
//                "/Partyroom/**", "/Admin/**","/Score/**","/Message/**").permitAll()
//                .anyRequest().authenticated()
//        ).exceptionHandling(e -> e
//                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
//        ).oauth2Login();
//     
     http.cors().and().csrf().disable();
    }
    
}