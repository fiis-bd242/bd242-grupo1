package com.example.yapeback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.yapeback.utils.FileUploader;

@SpringBootApplication
public class CampanaApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(CampanaApplication.class, args);
        FileUploader.createUploadsDirectory();
        
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permitir CORS para todas las rutas
        registry.addMapping("/**") // O puedes ser más específico, por ejemplo "/api/**"
                .allowedOrigins("http://localhost:3000") // Permitir solicitudes desde el frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                .allowedHeaders("*"); // Permitir todos los headers
             
    }
    
}
