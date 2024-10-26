package com.polar_moviechart.movieservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/**")
                        .allowedOrigins("http://localhost:3000") // Next.js의 localhost 포트를 허용
                        .allowedMethods("GET", "POST", "PUT")
                        .allowedHeaders("*")
                        .allowCredentials(true);

                registry.addMapping("/v3/api-docs")
                        .allowedOrigins("http://localhost:3000") // Next.js의 localhost 포트를 허용
                        .allowedMethods("GET")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}
