package com.therap.supply.chain.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

@Configuration
public class ProjectSecurityConfig {


    /**
     * From Spring Security 5.7, the WebSecurityConfigurerAdapter is deprecated to encourage users
     * to move towards a component-based security configuration. It is recommended to create a bean
     * of type SecurityFilterChain for security related configurations.
     *
     * @param http
     * @return SecurityFilterChain
     * @throws Exception
     */

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
//                .cors().disable()
                .authorizeHttpRequests((auth) -> auth
                        .antMatchers("/authority/add", "/authority/all", "/authority/get",
                                "/authority/edit", "/authority/image").hasAnyRole("MD")
                        .antMatchers("/dealer/approver/**").hasAnyRole("DA")
                        .antMatchers("/authority/login").permitAll()
                        .antMatchers("/notices", "/contact").permitAll()
                )
                .formLogin()
                .loginPage("/auth/login")
//                .loginProcessingUrl("authority/all")
//                .defaultSuccessUrl("")
        ;
        return http.build();

    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


//@Configuration
//public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    /**
//     * /myAccount - Secured
//     * /myBalance - Secured
//     * /myLoans - Secured
//     * /myCards - Secured
//     * /notices - Not Secured
//     * /contact - Not Secured
//     */
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        // custom configuration our requirement
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/authority/all").authenticated()
//                .antMatchers("/myCards").authenticated()
//                .antMatchers("/notices")
//                .permitAll().antMatchers("/contact").permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/authority/login");
////                .and()
////                .httpBasic();
//
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//}