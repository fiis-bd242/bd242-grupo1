package com.example.backend_capacitaciones_yape.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permite CORS en todas las rutas de la API
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")  // La URL de tu frontend de React (cambia si es diferente)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowedHeaders("*"); // Todos los headers permitidos
    }
}
