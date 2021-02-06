package com.lyl.config;


/*
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
*/

public class SecurityConfig /*extends WebSecurityConfigurerAdapter*/ {

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/page/**").permitAll().
                antMatchers("/SSF-login").permitAll().
                antMatchers("/login").permitAll().
                antMatchers("login.html").permitAll().
                antMatchers("index.html").permitAll();*/
    //}


   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("123123").password("123123").roles("管理员");
    }*/
}
