package com.example.softdownloaderapi.configure;
import java.io.File;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig {

    @Bean
    public SessionFactory getSessionFactory(){
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration()
            .configure(new File("hibernate.cfg.xml"));
        return cfg.buildSessionFactory();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }
}
