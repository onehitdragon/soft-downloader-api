package com.example.softdownloaderapi.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public DataBaseConfig getDataBaseConfig(){
        return new DataBaseConfig();
    }
}
