package org.mirafi.sample.skeleton.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Created by mi on 12/22/17.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    private UserDetailsService authCredentialDetailsService;

    @Autowired
    @Qualifier("authCredentialDetailsService")
    public void setAuthCredentialDetailsService(UserDetailsService authCredentialDetailsService) {
        this.authCredentialDetailsService = authCredentialDetailsService;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ROLE_pos')")
                .and()
                    .userDetailsService(this.authCredentialDetailsService)
                    .formLogin()
                        .usernameParameter("email").passwordParameter("password")

                .and()
                    .logout().logoutSuccessUrl("/login?logout")
                .and()
                    .exceptionHandling().accessDeniedPage("/403")
                .and()
                    .csrf().disable();


    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}