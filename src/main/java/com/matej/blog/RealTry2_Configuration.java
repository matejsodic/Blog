package com.matej.blog;

import com.matej.blog.UserService.UserRepositoryUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class RealTry2_Configuration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Qualifier("userRepositoryUserDetailsService")
    @Autowired
    private UserRepositoryUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/*.css").permitAll()
                .antMatchers("/administration/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/anonymous*").anonymous()
                .antMatchers("/admin3000*").permitAll()
                .antMatchers("/register*").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/post/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/admin3000")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/administration", true)
                .and()
                .logout()
                .logoutSuccessUrl("/admin3000")
                .deleteCookies("JSESSIONID")
                .and()
                .rememberMe().key("secretKey")
                .and()
                .httpBasic().disable();
    }


}
