package com.matej.RealTry2;

import com.matej.RealTry2.UserService.UserRepositoryUserDetailsService;
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
//        auth.inMemoryAuthentication()
//                .withUser("user1").password(passwordEncoder().encode("user1Pass")).roles("USER")
//                .and()
//                .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
//                .and()
//                .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");
//        auth.jdbcAuthentication().dataSource(dataSource);
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
//                .logoutUrl("/perform_logout")
                .logoutSuccessUrl("/admin3000")
                .deleteCookies("JSESSIONID")
                .and()
                .rememberMe().tokenValiditySeconds(100000).key("secretKey")
                .and()
                .httpBasic().disable();

//        http.httpBasic().disable();
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//        .formLogin()
//                .loginPage("/admin3000")
//                .permitAll()
//                .and()
//        .logout()
//                .permitAll();
    }


}
