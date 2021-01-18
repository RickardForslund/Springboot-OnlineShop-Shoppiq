package com.shoppiq.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

import javax.servlet.http.Cookie;

import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private ShoppiqUserDetailsService userDetailsService;

    public SecurityConfig(ShoppiqUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        provider.setAuthoritiesMapper(authoritiesMapper());
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home", "/login", "api/v1/user/create").permitAll() //TODO remove /api/v1/item/search** from permitAll
                .antMatchers("/api/v1/item/view/**", "/api/v1/item/item/**", "/api/v1/item/search").hasRole("USER")
                .antMatchers("/admin", "/api/v1/item/list", "/api/v1/user/list").hasRole("ADMIN")
//                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring().antMatchers("/h2-console/**");
    }

    @Bean
    public GrantedAuthoritiesMapper authoritiesMapper() {
        SimpleAuthorityMapper authorityMapper = new SimpleAuthorityMapper();
        authorityMapper.setConvertToUpperCase(true);
        authorityMapper.setDefaultAuthority("USER");
        return authorityMapper;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


//
//    private static final ClearSiteDataHeaderWriter.Directive[] SOURCE =
//            {CACHE, COOKIES, STORAGE, EXECUTION_CONTEXTS};
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder
//                .inMemoryAuthentication()
//                .withUser("admin")
//                .password(passwordEncoder().encode("adminpass"))
//                .roles("ADMIN")
//                .and()
//                .withUser("user")
//                .password(passwordEncoder().encode("userpass"))
//                .roles("USER");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable();
//        http
//                .httpBasic()
//                .and()
//                .authorizeRequests()
////                .antMatchers("/admin/**")
////                .hasRole("ADMIN")
////                .antMatchers("api/v1/user/**", "/login/**")
////                .hasRole("USER")
//                .anyRequest()
//                .permitAll();
//        http
//                .logout(logout -> logout
//                        .logoutUrl("/logout/v1")
//                        .addLogoutHandler(((request, response, authentication) -> {
//                            for (Cookie cookie : request.getCookies()) {
//                                String cookieName = cookie.getName();
//                                Cookie cookieToDelete = new Cookie(cookieName, null);
//                                cookieToDelete.setMaxAge(0);
//                                response.addCookie(cookieToDelete);
//                            }
//                        }))
//                        .logoutSuccessUrl("/home")
//                );
//        http
//                .logout(logout -> logout
//                        .logoutUrl("logout/v2")
//                        .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(SOURCE)))
//                );
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers("/h2-console/**");
//    }
//
//
//}
