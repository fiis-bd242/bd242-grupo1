package com.example.yapeback.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.textract.AmazonTextract;
import com.amazonaws.services.textract.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.ByteBuffer;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    @Autowired
    private AmazonS3 amazonS3;

    @Autowired
    private AmazonTextract amazonTextract;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        ByteBuffer imageBytes = ByteBuffer.wrap(file.getBytes());
        
        DetectDocumentTextRequest request = new DetectDocumentTextRequest()
                .withDocument(new Document().withBytes(imageBytes));
        
        DetectDocumentTextResult result = amazonTextract.detectDocumentText(request);
        
        // Extraer y procesar el texto
        StringBuilder fullText = new StringBuilder();
        for (Block block : result.getBlocks()) {
            if (block.getBlockType().equals("LINE")) {
                fullText.append(block.getText()).append("\n");
            }
        }
        
        // Crear el mapa de respuesta
        Map<String, Object> extractedData = new HashMap<>();
        
        // Imprimir el texto completo para debugging
        System.out.println("Texto extraído del PDF:");
        System.out.println(fullText.toString());
        
        // Buscar los campos específicos usando expresiones regulares
        Pattern nombrePattern = Pattern.compile("(?i)nombre:?\\s*(.+)");
        Pattern correoPattern = Pattern.compile("(?i)correo:?\\s*(.+)");
        Pattern telefonoPattern = Pattern.compile("(?i)tel[eéf]+ono:?\\s*([\\d]+)");
        
        String[] lines = fullText.toString().split("\n");
        for (String line : lines) {
            // Buscar nombre
            Matcher nombreMatcher = nombrePattern.matcher(line);
            if (nombreMatcher.find()) {
                extractedData.put("nombre", nombreMatcher.group(1).trim());
                System.out.println("Nombre encontrado: " + nombreMatcher.group(1).trim());
            }
            
            // Buscar correo
            Matcher correoMatcher = correoPattern.matcher(line);
            if (correoMatcher.find()) {
                extractedData.put("correo", correoMatcher.group(1).trim());
                System.out.println("Correo encontrado: " + correoMatcher.group(1).trim());
            }
            
            // Buscar teléfono
            Matcher telefonoMatcher = telefonoPattern.matcher(line);
            if (telefonoMatcher.find()) {
                extractedData.put("telefono", telefonoMatcher.group(1).trim());
                System.out.println("Teléfono encontrado: " + telefonoMatcher.group(1).trim());
            }
        }
        
        // Agregar listas vacías para mantener la estructura de datos esperada
        extractedData.putIfAbsent("idiomas", new ArrayList<>());
        extractedData.putIfAbsent("educaciones", new ArrayList<>());
        extractedData.putIfAbsent("habilidades", new ArrayList<>());
        extractedData.putIfAbsent("experienciasLaborales", new ArrayList<>());
        
        return ResponseEntity.ok(extractedData);
    }
}