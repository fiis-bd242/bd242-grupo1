package com.example.yapeback.service;

import com.example.yapeback.model.Permiso;
import com.example.yapeback.model.EvidenciaPermiso;
import com.example.yapeback.interfaces.PermisoRepository;
import com.example.yapeback.interfaces.EvidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PermisoService {
	private static final Logger log = LoggerFactory.getLogger(PermisoService.class);

    @Autowired
    private PermisoRepository permisoRepository;
    
    @Autowired
    private EvidenciaRepository evidenciaRepository;

    public Permiso guardarPermiso(Permiso permiso) {
        return permisoRepository.save(permiso);
    }
    private String guardarArchivoLocalmente(Long idPermiso, MultipartFile archivo) throws IOException {
        // Ruta base configurada desde application.properties
        String directorioBase = System.getProperty("user.dir") + "/uploads/evidencias";

        // Ruta específica del permiso
        String directorioPermiso = directorioBase + "/" + idPermiso;

        // Crear el directorio si no existe
        File directorio = new File(directorioPermiso);
        if (!directorio.exists() && !directorio.mkdirs()) {
            throw new IOException("No se pudo crear el directorio: " + directorioPermiso);
        }

        // Ruta completa del archivo
        String rutaArchivo = directorioPermiso + "/" + archivo.getOriginalFilename();

        // Guardar el archivo físicamente
        File archivoDestino = new File(rutaArchivo);
        archivo.transferTo(archivoDestino);

        return rutaArchivo; // Retorna la ruta donde se guardó el archivo
    }

 //   private String guardarArchivoLocalmente(Long idPermiso, MultipartFile archivo) throws IOException {
        // Ruta base para almacenar los archivos
 //       String directorioBase = "src/main/resources/static/uploads/evidencias";

        // Ruta específica del permiso
   //     String directorioPermiso = directorioBase + "/" + idPermiso;

        // Crear el directorio si no existe
     //   File directorio = new File(directorioPermiso);
       // if (!directorio.exists()) {
         //   boolean creado = directorio.mkdirs(); // Crea todos los directorios necesarios
           // if (!creado) {
             //   throw new IOException("No se pudo crear el directorio: " + directorioPermiso);
        //    }
      //  }

        // Ruta completa del archivo
      //  String rutaArchivo = directorioPermiso + "/" + archivo.getOriginalFilename();

        // Guardar el archivo físicamente
     //   File archivoDestino = new File(rutaArchivo);
       // archivo.transferTo(archivoDestino);

        // Retornar la ruta relativa accesible desde el navegador
      //  return "/uploads/evidencias/" + idPermiso + "/" + archivo.getOriginalFilename();
    //}

    public void guardarEvidencias(Long idPermiso, MultipartFile[] evidencias) throws IOException {
        if (evidencias != null && evidencias.length > 0) {
            for (MultipartFile archivo : evidencias) {
                String rutaArchivo = guardarArchivoLocalmente(idPermiso, archivo);

                // Crear y guardar la evidencia
                EvidenciaPermiso evidencia = new EvidenciaPermiso();
                evidencia.setNombreArchivo(archivo.getOriginalFilename());
                evidencia.setRutaArchivo(rutaArchivo);
                evidencia.setFechaSubida(LocalDateTime.now());
                evidencia.setIdPermiso(idPermiso);

                evidenciaRepository.guardarEvidencia(evidencia);
            }
        }
    }
    public List<Map<String, Object>> obtenerEvidenciasPorPermiso(Long idPermiso) {
        return evidenciaRepository.obtenerEvidenciasPorPermiso(idPermiso);
    }

   // public List<EvidenciaPermiso> obtenerEvidenciasPorPermiso(Long idPermiso) {
     ///   return evidenciaRepository.obtenerEvidenciasPorPermiso(idPermiso);
  //  }

    public List<Map<String, Object>> obtenerPermisosEmpleado(Long idEmpleado) {
        return permisoRepository.findByEmpleado(idEmpleado);
    }

    public List<Map<String, Object>> obtenerPermisosConFiltros(String estado, String tipoPermiso) {
        return permisoRepository.findAllWithFilters(estado, tipoPermiso);
    }

    public void actualizarEstadoPermiso(Long idPermiso, String nuevoEstado, String comentarioAdmin) {
        log.info("Actualizando estado del permiso con ID: {}, nuevoEstado: {}, comentarioAdmin: {}", idPermiso, nuevoEstado, comentarioAdmin);
        permisoRepository.updateEstado(idPermiso, nuevoEstado, comentarioAdmin);
    }
    
    public Map<String, Object> obtenerDetallePermiso(Long idPermiso) {
        return permisoRepository.findDetallePermiso(idPermiso);
    }
}
