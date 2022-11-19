package com.example.softdownloaderapi.configure;
import java.io.File;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public SessionFactory getSessionFactory(){
        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration()
            .configure(new File("hibernate.cfg.xml"));
        return cfg.buildSessionFactory();
    }
}
