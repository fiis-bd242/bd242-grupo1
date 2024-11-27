package com.example.yapeback.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/uploads")
public class FileController {

	@Value("${upload.dir}") // Ruta base configurada en application.properties
    private String uploadDir;

    @GetMapping("/evidencias/{idPermiso}/{filename}")
    public ResponseEntity<Resource> getEvidencia(@PathVariable Long idPermiso, @PathVariable String filename) throws Exception {
        try {
            // Construir la ruta completa del archivo
            Path filePath = Paths.get(uploadDir, "evidencias", String.valueOf(idPermiso), filename).toAbsolutePath();
            Resource fileResource = new UrlResource(filePath.toUri());

            // Verificar si el archivo existe y es legible
            if (!fileResource.exists() || !fileResource.isReadable()) {
                throw new RuntimeException("Archivo no encontrado o no es legible: " + filename);
            }

            // Configurar los encabezados de la respuesta
            String contentDisposition = "attachment; filename=\"" + filename + "\"";
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                    .body(fileResource);

        } catch (Exception e) {
            throw new RuntimeException("Error al acceder al archivo: " + filename, e);
        }
    }
}

