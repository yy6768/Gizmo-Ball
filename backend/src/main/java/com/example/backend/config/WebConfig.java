package com.example.backend.config;

import com.example.backend.physics.WorldManager;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
@ComponentScan(basePackages = "com.example.backend")
@EnableScheduling
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public WorldManager getWorldManager(){
        return new WorldManager();
    }
}
