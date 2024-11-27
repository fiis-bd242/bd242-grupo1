package com.example.yapeback.interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.yapeback.model.Recursos;

public interface RecursosServiceInterface {

	void guardarRecurso(Recursos recurso);

	void actualizarUrl_recurso(String url_recurso, Integer cod_prototipo, Integer id_tipo_recurso);

	String guardarArchivo(MultipartFile file);

	Recursos encontrarPorPrototipoYTipo(Integer cod_prototipo, Integer id_tipo_recurso);

	List<Recursos> obtenerRecursosPorPrototipo(Integer codPrototipo);


    
}

