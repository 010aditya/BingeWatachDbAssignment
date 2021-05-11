package com.dbAissgnment.BingeWatachDbAssignment.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Value("${netflix.http.auth.tokenName}")
    private String authHeaderName;

    //TODO: retrieve this token value from data source
    @Value("${netflix.http.auth.tokenValue}")
    private String authHeaderValue;


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        PreAuthTokenHeaderFilter filter = new PreAuthTokenHeaderFilter(authHeaderName);

        filter.setAuthenticationManager(authentication -> {
            String principal = (String) authentication.getPrincipal();

            if (!authHeaderValue.equals(principal)) {
                throw new BadCredentialsException("The API key was not found "
                        + "or not the expected value.");
            }
            authentication.setAuthenticated(true);
            return authentication;
        });

        httpSecurity.
                antMatcher("/netflix/getShows")
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(filter)
                .addFilterBefore(new ExceptionTranslationFilter(
                                new Http401UnAutherizedEntryPoint()),
                        filter.getClass()
                )
                .authorizeRequests()
                .anyRequest()
                .authenticated();
    }

}

