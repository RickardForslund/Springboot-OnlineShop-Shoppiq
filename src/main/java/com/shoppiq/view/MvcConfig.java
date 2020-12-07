package com.shoppiq.view;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("/html/home");
        registry.addViewController("/").setViewName("/html/home");
        registry.addViewController("/logout").setViewName("/html/home");
        registry.addViewController("/application").setViewName("/html/application");
        registry.addViewController("/admin").setViewName("/html/admin");
        registry.addViewController("/login").setViewName("/html/login");
        registry.addViewController("/error").setViewName("/html/error");
    }
}
