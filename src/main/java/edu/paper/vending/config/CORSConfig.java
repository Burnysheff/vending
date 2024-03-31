package edu.paper.vending.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //.allowedOrigins("https://master--superb-moonbeam-048cf3.netlify.app")
                .allowedMethods("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH");
    }

}