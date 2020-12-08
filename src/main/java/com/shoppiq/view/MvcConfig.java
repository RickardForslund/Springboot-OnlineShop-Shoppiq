package com.shoppiq.view;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("/home");
        registry.addViewController("/").setViewName("/home");
        registry.addViewController("/logout").setViewName("/home");
        registry.addViewController("/application").setViewName("/application");
        registry.addViewController("/admin").setViewName("/admin");
        registry.addViewController("/login").setViewName("/login");
        registry.addViewController("/error").setViewName("/error");
        registry.addViewController("/create_item").setViewName("/create_item");
    }
}