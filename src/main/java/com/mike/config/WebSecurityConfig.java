package com.mike.config;

import com.mike.service.UserDetailsServiceImpl;
import com.mike.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOG = LogManager.getLogger(WebSecurityConfig.class);
    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AccessDeniedHandlerImpl accessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

            .authorizeRequests()
            .antMatchers("/resources/static/images/favicon.ico").permitAll()
            .antMatchers("/registration").permitAll()
            .antMatchers("/captcha").permitAll()
            .antMatchers("/api/**").permitAll()
            .antMatchers("/user/**").hasAuthority("user")
            .antMatchers("/admin/**").hasAuthority("admin")
            .antMatchers("/home/**").hasAnyAuthority("admin", "user")
            .anyRequest().authenticated().and().csrf().disable()
            .formLogin().loginPage("/login").permitAll()
            .and().exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler)
            .and()
            .logout()
            .permitAll();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}