```sql
-- Tabla Departamento (sin cambios)
ALTER SEQUENCE Departamento_id_departamento_seq RESTART WITH 1;
INSERT INTO Departamento (descripcion, id_departamento_padre)
VALUES
    ('Recursos Humanos', NULL),
    ('Desarrollo de Software', NULL),
    ('Soporte Técnico', 1),
    ('Marketing', NULL),
    ('Comercial', NULL);


-- Tabla Funcion (sin cambios)
INSERT INTO Funcion (nombre, descripcion)
VALUES
    ('Gestión de personal', 'Encargado de la administración de recursos humanos.'),
    ('Desarrollo', 'Encargado de desarrollar software y aplicaciones.'),
    ('Soporte', 'Proveer asistencia técnica a usuarios.');


-- Tabla Puesto (sin cambios)

ALTER SEQUENCE Puesto_id_puesto_seq RESTART WITH 1;
INSERT INTO Puesto (nombre, paga, id_departamento)
VALUES
    ('Gerente de RRHH', 4500.00, 1),
    ('Desarrollador Senior', 3500.00, 2),
    ('Técnico de Soporte', 2500.00, 3),
    ('Analista de Marketing', 3000.00, 4),
    ('Gerente de Marketing', 5000.00, 4),
    ('Gerente Comercial', 5500.00, 5),
    ('Empleado de Marketing', 2500.00, 4);

-- Tabla Puesto_Funcion (sin cambios)
INSERT INTO Puesto_Funcion (id_puesto, id_funcion)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);

-- Tabla Empleado (sin cambios)
INSERT INTO Empleado (Nombre, Apellido, fecha_nacimiento, fecha_ingreso, Estado, Documento_identidad, Telefono, id_puesto)
VALUES
    ('María', 'González', '1980-04-12', '2022-01-01', 'Activo', '12345678', '987654321', 1),
    ('Pedro', 'Martínez', '1990-06-22', '2020-05-15', 'Activo', '23456789', '987654322', 2),
    ('Ana', 'López', '1995-11-30', '2021-10-10', 'Activo', '34567890', '987654323', 3),
    ('Piter', 'Hitler', '1995-11-30', '2021-10-10', 'Activo', '43657890', '984586212', 3),
    ('Sofía', 'Ramírez', '1992-07-15', '2023-03-01', 'Activo', '45678901', '987654324', 4),
    ('Carlos', 'Sánchez', '1985-03-22', '2019-08-01', 'Activo', '56789012', '987654325', 5),
    ('Luis', 'Pérez', '1988-09-10', '2018-07-01', 'Activo', '67890123', '987654326', 6),
    ('Valeria', 'Torres', '1998-02-28', '2022-11-15', 'Activo', '78901234', '987654327', 7);


-- Tabla ticket general
INSERT INTO Ticket_general (cod_ticket, fecha_creacion, categoria, ID_empleado)
VALUES (1, '2024-10-10', 'Interno', 1),
	   (2, '2024-10-11', 'Interno', 2),
	   (3, '2024-10-12', 'Interno', 3),
	   (4, '2024-10-13', 'Externo', 4),
	   (5, '2024-10-14', 'Externo', 5),
       (6, '2024-10-15', 'Externo', 6);

      

-- Tabla ticket incidente
INSERT INTO Ticket_incidente (cod_ticket_inc,categoria, prioridad, estado, fecha_ticket_inc) 
VALUES (1, 'hardware', 'alta', 'abierto', '2024-09-01'),
       (2, 'software', 'media', 'progreso', '2024-09-02'),
       (3,'infraest', 'baja', 'cerrado', '2024-09-03');

-- Tabla Ticket postmortem
INSERT INTO Ticket_postmortem (cod_ticket_post, fecha_creacion_post, Estado_ticket_post, cod_ticket_inc)
VALUES 
		(1, '2024-01-20', 'Cerrado', 1),
		(2, '2024-02-10', 'Cerrado', 2),
		(3, '2024-03-15', 'Abierto', 3);

-- Tablas postmortem detalles
INSERT INTO Postmortem_detalles (cod_postmortem, resumen_post, Impacto, Deteccion, Recuperacion, Severidad, cod_ticket_post) 
VALUES (1,'Fallo crítico', 'Alto', 'Manual', '2 horas', 'Alta', 1),
       (2,'Interrupción menor', 'Medio', 'Automática', '1 hora', 'Media', 2),
       (3,'Error no crítico', 'Bajo', 'Automática', '30 minutos', 'Baja', 3);

-- Tabla analisis post
INSERT INTO Analisis_post (cod_analisis_post, Aprobacion_analisis, fecha_inicio_post, fecha_fin_post, cod_postmortem)
VALUES 
		(1, 'Aprobado', '2024-01-21', '2024-01-22', 1),
		(2, 'Aprobado', '2024-02-11', '2024-02-12', 2),
		(3, 'Rechazado', '2024-03-16', '2024-03-17', 3),
		(4, 'Aprobado', '2024-05-01', '2024-05-02', 3);

-- Tabla leccion
INSERT INTO Leccion (cod_leccion, Leccion, cod_analisis_post)
VALUES 
		(1, 'No repetir fallos de red', 1),
		(2, 'Mejorar la monitorización', 2),
		(3, 'Capacitación de personal', 3),
		(4, 'Prevenir sobrecargas', 4);

-- Tabla categoria accion
INSERT INTO Categoria_Accion (cod_cat_accion, cat_accion, cod_accion)
VALUES 
		(1, 'Preventiva', 1),
		(2, 'Correctiva', 2),
		(3, 'Capacitación', 3),
		(4, 'Revisión', 4);

-- tabla accion
INSERT INTO Accion (cod_accion, accion, cod_analisis_post, cod_cat_accion)
VALUES 
		(1, 'Actualizar cortafuegos', 1, 1),
		(2, 'Reconfigurar red', 2, 2),
		(3, 'Entrenamiento de empleados', 3, 3),
		(4, 'Auditoría de servidores', 4, 4);

-- tabla incidente
	
INSERT INTO Incidente (cod_incidente, mensaje_incidente, categoria, cod_ticket_inc)
VALUES 
		(1, 'Falla en la red local', 'Red', 1),
		(2, 'Falla en servidor', 'Hardware', 2),
		(3, 'Problema en aplicación', 'Software', 3);

-- tabla proveedor
INSERT INTO Proveedor (cod_proveedor, Nombre_proveedor, SLA, cod_diag)
VALUES 
		(1, 'Proveedor1', '99.9%', 1),
		(2, 'Proveedor2', '99.5%', 2),
		(3, 'Proveedor3', '99.0%', 3);

-- tabla causa
INSERT INTO Causa (cod_causa, Causa, cod_diag)
VALUES 
		(1, 'Problema de configuración', 1),
		(2, 'Mal uso del sistema', 2),
		(3, 'Sobrecarga de datos', 3);

-- tabla diagnostico
INSERT INTO Diagnostico (cod_diag, comentario, fecha_realizacion, cod_incidente, ID_empleado, cod_proveedor, cod_causa)
VALUES 
		(1, 'Diagnóstico realizado correctamente', '2024-01-18', 1, 1, 1, 1),
		(2, 'Se encontraron fallos menores', '2024-02-05', 2, 2, 2, 2),
		(3, 'Diagnóstico pendiente', '2024-03-12', 3, 3, 3, 3);

-- tabla solucion
INSERT INTO Solucion (cod_solucion, estado_solucion, test, cod_diag)
VALUES 
		(1, 'Implementada', 'Exitoso', 1),
		(2, 'En Proceso', 'Pendiente', 2),
		(3, 'Fallida', 'Fallido', 3);

	
-- Tabla Feedback (adaptada)
INSERT INTO Feedback (fecha)
VALUES
    ('2024-10-06'),
    ('2024-10-08');

-- Tabla Estado_prototipo (sin cambios)
INSERT INTO Estado_prototipo (estado_prot)
VALUES
    ('Aprobado'),
    ('Rechazado'),
    ('Pendiente');

-- Tabla Promocion (adaptada)
INSERT INTO Promocion (fecha_inicio, fecha_fin, dscto, precio final, estado_promo, dscrip_promo, vigencia, ID_empleado)
VALUES
    ('2024-10-01', '2024-10-15', 15.00, 40.50, TRUE, 'Promoción de inicio de temporada', 30, 5),
    ('2024-11-01', '2024-11-30', 20.00, 29.90, TRUE, 'Promoción de Black Friday',7, 6),
    ('2024-12-01', '2024-12-25', 25.00, 15.40, FALSE, 'Promoción de Navidad',15, 7);

INSERT INTO Audiencia (id_audiencia, Edad_rango, Genero, Ubicacion)
VALUES
('1830ML', '[18,30]', 'M', 'Lima'),
('3145MFA', '[31,45]', 'MF', 'Arequipa'),
('4660MC', '[46,60]', 'M', 'Cusco'),
('1830FT', '[18,30]', 'F', 'Trujillo'),
('3145FL', '[31,45]', 'F', 'Lima'),
('4660ML', '[46,60]', 'M', 'Lima'),
('1830MF', '[18,30]', 'MF', 'Cusco'),
('3145MT', '[31,45]', 'M', 'Trujillo');

-- Prototipos
INSERT INTO Prototipo (nombre_prot,Descripcion,Prop_presupuesto,fecha_creacion,fecha_estado, Prop_audiencia, ID_empleado, cod_est_prot)
VALUES
('Prototipo 1','A',5000.00,'2024-02-14 09:23:45','2024-03-15 10:00:00','1830ML', 5, 1),
('Prototipo 2','B',3000.00,'2024-07-22 14:10:30','2024-09-23 16:30:45','3145MFA', 6, 2),
('Prototipo 3','C',4000.00,'2024-10-05 18:55:15','2024-11-06 20:05:00','4660MC', 7, 3),
('Prototipo 4','D',6000.00,'2024-08-29 06:45:00','2024-09-30 08:15:30','1830FT', 8, 1);

-- Canales
ALTER SEQUENCE canal_cod_canal_seq RESTART WITH 1;
INSERT INTO Canal (nombre_canal, descripcion)
VALUES
('Redes Sociales', 'Campaña dirigida a jóvenes en redes sociales'),
('Televisión', 'Publicidad en canales de TV de alcance nacional'),
('Radio', 'Publicidad en estaciones de radio regionales'),
('Medios Impresos', 'Anuncios en periódicos y revistas locales'),
('Publicidad Digital', 'Anuncios en plataformas de internet');

-- Relación Prototipo-Canal (prototipoxcanal)
INSERT INTO prototipoxcanal (cod_prototipo, cod_canal, impresiones, clics, conversiones)
VALUES
(1, 1, 10000, 1500, 300),  -- Prototipo 1 usa Redes Sociales
(1, 5, 12000, 1800, 350),  -- Prototipo 1 también usa Publicidad Digital
(2, 2, null, null,null),  -- Prototipo 2 usa Televisión
(3, 3, null, null, null),  -- Prototipo 3 usa Radio
(3, 4, null, null,null),  -- Prototipo 3 también usa Medios Impresos
(4, 5, 14000, 2100, 450);  -- Prototipo 4 usa Publicidad Digital



-- Objetivos

INSERT INTO Objetivos (Descripcion, cod_prototipo)
VALUES
('Incrementar las ventas mensuales en un 10%', 1),
('Mejorar la percepción de la marca', 2),
('Fomentar la adopción del producto', 3),
('Fortalecer la relación con los clientes actuales', 4),
('Expandir la presencia en mercados regionales', 1),
('Aumentar el tráfico en el sitio web en un 20%', 2),
('Reducir el costo de adquisición de clientes en un 15%', 3),
('Generar más referencias de clientes actuales', 4);

-- Campaña Publicitaria

INSERT INTO Campana_Publicitaria (Resultado, Presupuesto, Nombre_campana, Audiencia,fecha_publicacion,fecha_finalizacion,cod_promocion, cod_prototipo)
VALUES
('Incremento de ventas en un 15%', 5000.00, 'Campaña de Verano', '1830ML','2024-06-01 10:00:00','2024-07-01 18:00:00', 1, 1),
('Fidelización alcanzada', 6000.00, 'Campaña de Otoño', '1830FT','2024-12-01 09:00:00','2025-01-05 23:59:59', 3, 4);

-- Pre-test

INSERT INTO Pre_test (AudienciaA, AudienciaB, Fecha_inicio, Fecha_fin, Resultado, cod_prototipo, id_empleado)
VALUES
('1830ML', '3145FL', '2024-10-01 10:00:00', '2024-10-05 18:00:00', '1830ML', 1, 5),
('3145MFA', '4660ML', '2024-10-06 09:00:00', '2024-10-10 17:00:00', '3145MFA', 2, 6),
('4660MC', '1830MF', '2024-10-11 08:30:00', '2024-10-15 16:00:00', '4660MC', 3, 7),
('1830FT', '3145MT', '2024-10-16 08:00:00', '2024-10-20 19:00:00', '1830FT', 4, 8);

-- Inserción de datos en la tabla Tipo_recurso
INSERT INTO Tipo_recurso (nombre_tipo)
VALUES 
    ('Foto'),
    ('Video'),
    ('Guion'),
    ('Documento');


INSERT INTO Recursos (nombre_recurso, url_recurso, id_tipo_recurso, cod_prototipo)
VALUES 
    ('Video introductorio', 'http://example.com/video_intro.mp4', 2,2),
    ('Guion principal', 'http://example.com/guion_principal.pdf', 3,3);   
INSERT INTO Recursos (nombre_recurso, url_recurso, id_tipo_recurso, cod_prototipo)
VALUES 
    ('Video 4', 'http://example.com/video_intro.mp4', 2,4),
    ('Guion principal 4', 'http://example.com/guion_principal.pdf', 3,4);

INSERT INTO Recursos (nombre_recurso, url_recurso, id_tipo_recurso, cod_prototipo)
VALUES 
    ('Video publicidad 1', 'http://example.com/foto_inicial.jpg', 2,1),
    ('Guion 1', 'http://example.com/video_intro.mp4', 3,1),
   ('Analisis prot1', 'http://example.com/docum4_principal.pdf',4,1),
   ('Analisis prot4', 'http://example.com/docum4_principal.pdf',4,4);


-- Tabla Vacante (sin cambios)
INSERT INTO Vacante (estado, fecha_fin, fecha_inicio, comentario, id_puesto)
VALUES
    ('Abierta', '2024-12-31', '2024-10-01', 'Vacante para Gerente de RRHH', 1),
    ('Abierta', '2024-11-30', '2024-09-15', 'Vacante para Desarrollador Senior', 2),
    ('Cerrada', '2024-08-30', '2024-07-01', 'Vacante para Técnico de Soporte', 3);

-- Tabla Convocatoria (sin cambios)
INSERT INTO Convocatoria (id_vacante, medio_publicacion, fecha_inicio, fecha_fin, estado)
VALUES
    (1, 'LinkedIn', '2024-10-01', '2024-11-30', 'En proceso'),
    (2, 'Indeed', '2024-09-15', '2024-10-30', 'Abierta');

-- Tabla Postulante (sin cambios)
INSERT INTO Postulante (nombre, telefono, id_vacante, correo)
VALUES
    ('Carlos Ramírez', 1351351, 1, 'Correo1'),
    ('Lucía Flores', 3135165, 2, 'Correo2'),
    ('Juan Pérez', 384311353, 3, 'Correo3');

-- Tabla Educacion (sin cambios)
INSERT INTO Educacion (id_postulante, institucion, titulo, fecha_inicio, fecha_fin, en_curso)
VALUES
    (1, 'Universidad Nacional', 'Administración', '2015-03-01', '2020-12-15', FALSE),
    (2, 'Instituto Tecnológico', 'Ingeniería de Sistemas', '2016-03-01', '2021-12-15', FALSE);

-- Tabla Experiencia_Laboral (sin cambios)
INSERT INTO Experiencia_Laboral (id_postulante, empresa, puesto, fecha_inicio, fecha_fin)
VALUES
    (1, 'TechCorp', 'Gerente de RRHH', '2021-01-01', '2024-05-31'),
    (2, 'Compañía Y', 'Desarrollador Junior', '2022-01-01', '2024-07-31');

-- Tabla Habilidad_Postulante (sin cambios)
INSERT INTO Habilidad_Postulante (id_postulante, nombre, nivel)
VALUES
    (1, 'Liderazgo', 'Avanzado'),
    (2, 'JavaScript', 'Intermedio');

-- Tabla Habilidad_Experiencia (sin cambios)
INSERT INTO Habilidad_Experiencia (id_experiencia, nombre, nivel)
VALUES
    (1, 'Trabajo en equipo', 'Avanzado'),
    (2, 'Liderazgo', 'Intermedio');

-- Tabla Idioma (sin cambios)
INSERT INTO Idioma (nombre)
VALUES
    ('Inglés'),
    ('Francés');

-- Tabla Idioma_Postulante (sin cambios)
INSERT INTO Idioma_Postulante (id_postulante, id_idioma, nivel)
VALUES
    (1, 1, 'Intermedio'),
    (1, 2, 'Básico'),
    (2, 1, 'Básico'),
    (2, 2, 'Intermedio');

-- Tabla Categoria_Observacion (nueva)
INSERT INTO Categoria_Observacion (nombre)
VALUES
    ('Habilidades técnicas'),
    ('Habilidades blandas'),
    ('Experiencia laboral'),
    ('Formación académica'),
    ('Actitud');

-- Tabla Observacion (nueva)
INSERT INTO Observacion (id_feedback, id_categoria, descripcion)
VALUES
    (1, 1, 'Excelente conocimiento en lenguajes de programación'),
    (1, 2, 'Buenas habilidades de comunicación y trabajo en equipo'),
    (1, 3, 'Experiencia relevante en proyectos similares'),
    (2, 4, 'Formación académica sólida y actualizada'),
    (2, 5, 'Actitud positiva y proactiva durante la entrevista');

-- Tabla Tipo_entrevista (nueva)
INSERT INTO Tipo_entrevista (nombre)
VALUES
    ('Entrevista inicial'),
    ('Entrevista técnica'),
    ('Entrevista con RRHH'),
    ('Entrevista final');

-- Tabla Indicador (nueva)
INSERT INTO Indicador (nombre)
VALUES
    ('Conocimientos técnicos'),
    ('Habilidades de comunicación'),
    ('Trabajo en equipo'),
    ('Resolución de problemas'),
    ('Adaptabilidad'),
    ('Liderazgo');

-- Tabla Tipo_entrevista_Indicador (nueva)
INSERT INTO Tipo_entrevista_Indicador (id_tipo_entrevista, id_indicador)
VALUES
    (1, 2), -- Entrevista inicial - Habilidades de comunicación
    (1, 5), -- Entrevista inicial - Adaptabilidad
    (2, 1), -- Entrevista técnica - Conocimientos técnicos
    (2, 4), -- Entrevista técnica - Resolución de problemas
    (3, 2), -- Entrevista con RRHH - Habilidades de comunicación
    (3, 3), -- Entrevista con RRHH - Trabajo en equipo
    (4, 1), -- Entrevista final - Conocimientos técnicos
    (4, 6); -- Entrevista final - Liderazgo

INSERT INTO Entrevista (estado, fecha, puntaje_general, id_postulante, ID_empleado, id_feedback, id_tipo_entrevista)
VALUES
    ('Aceptada', '2024-10-05', 85, 1, 1, 1, 1), -- Entrevista inicial para Carlos Ramírez
    ('Pendiente', '2024-10-10', 0, 2, 2, 2, 2); -- Entrevista técnica pendiente para Lucía Flores

-- Tabla Puntaje_Indicador (nueva)
INSERT INTO Puntaje_Indicador (id_entrevista, id_indicador, puntaje)
VALUES
    (1, 2, 85), -- Puntaje para habilidades de comunicación en la primera entrevista
    (1, 5, 90), -- Puntaje para adaptabilidad en la primera entrevista
    (2, 1, 75), -- Puntaje para conocimientos técnicos en la segunda entrevista
    (2, 4, 80); -- Puntaje para resolución de problemas en la segunda entrevista

-- Tabla Entrevista (adaptada)
INSERT INTO Entrevista (estado, fecha, puntaje_general, id_postulante, ID_empleado, id_feedback, id_tipo_entrevista)
VALUES
    ('Aceptada', '2024-10-05', 85, 1, 1, 1, 1), -- Entrevista inicial para Carlos Ramírez
    ('Pendiente', '2024-10-10', 0, 2, 2, 2, 2); -- Entrevista técnica pendiente para Lucía Flores

-- Tabla Beneficio
INSERT INTO Beneficio (descripcion)
VALUES
    ('Seguro médico'),
    ('Vacaciones pagadas'),
    ('Bonos de desempeño'),
    ('Capacitación continua');

INSERT INTO Oferta_Laboral (id_postulante, id_vacante, fecha_oferta, fecha_inicio_propuesta, link_documento_legal_sin_firma, estado)
VALUES
    (1, 1, '2024-10-01', '2024-10-10', 'link_documento_1.pdf', 'Pendiente'),
    (2, 2, '2024-10-02', '2024-11-01', 'link_documento_2.pdf', 'Aceptada'),
    (3, 3, '2024-10-03', '2024-12-01', 'link_documento_3.pdf', 'Rechazada');

-- Tabla Oferta_Laboral_Beneficio
INSERT INTO Oferta_Laboral_Beneficio (id_oferta, id_beneficio)
VALUES
    (1, 1), -- Oferta laboral 1 incluye Seguro médico
    (1, 2), -- Oferta laboral 1 incluye Vacaciones pagadas
    (2, 3), -- Oferta laboral 2 incluye Bonos de desempeño
    (2, 4); -- Oferta laboral 2 incluye Capacitación continua
    
INSERT INTO Capacitacion (fecha_inicio, fecha_final, nombre, estado, descripcion, ID_instructor)
VALUES
    ('2024-10-19', '2025-02-04', 'AI Integration', 'en_proceso', 'Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.', 12),
    ('2024-11-03', '2025-01-29', 'Blockchain Development', 'en_proceso', 'Vestibulum quam sapien, varius ut, blandit non, interdum in, ante. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae', 13),
    ('2024-10-01', '2024-10-15', 'Capacitación en Ventas', 'pendiente', 'Capacitación sobre técnicas de ventas.', 1),
    ('2024-10-05', '2024-10-20', 'Capacitación en Marketing', 'en_proceso', 'Capacitación sobre estrategias de marketing digital.', 2),
    ('2024-10-10', '2024-10-25', 'Capacitación en Liderazgo', 'finalizado', 'Capacitación sobre habilidades de liderazgo.', 3);

-- Inserciones para la tabla empleadoxcapacitacion
INSERT INTO empleadoxcapacitacion (estado, resultado, ID_empleado, ID_capacitacion)
VALUES
    ('inscrito', NULL, 1, 3),
    ('aprobado', 85, 2, 4),
    ('reprobado', 45, 3, 5),
    ('pendiente', NULL, 4, 3),
    ('inscrito', NULL, 5, 4);

-- Inserciones para la tabla Modulo
INSERT INTO Modulo (nombre, orden, estado, ID_capacitacion)
VALUES
    ('Introduction to AI', 1, 'activo', 1),
    ('Basics of AI', 2, 'activo', 1),
    ('AI Algorithms', 3, 'activo', 1),
    ('Módulo 1: Introducción', 1, 'activo', 3),
    ('Módulo 2: Estrategias Avanzadas', 2, 'activo', 4),
    ('Módulo 3: Habilidades de Comunicación', 1, 'inactivo', 5);

-- Inserciones para la tabla Test
INSERT INTO Test (nombre, fecha, estado, descripcion, ID_modulo)
VALUES
    ('Introduction to AI Exam', '2024-11-02', 'cerrado', 'Introduction to AI Exam. Covering the first module and introduction to AI concepts.', 1),
    ('Test de Ventas', '2024-10-10', 'pendiente', 'Evaluación sobre las técnicas de ventas aprendidas.', 4),
    ('Test de Marketing', '2024-10-15', 'realizado', 'Evaluación sobre estrategias de marketing digital.', 5);

-- Inserciones para la tabla Recurso
INSERT INTO Recurso (nombre, contenido, tipo, orden, estado, ID_modulo)
VALUES
    ('Video de Ventas', 'Contenido sobre técnicas de ventas.', 'video', 1, 'activo', 4),
    ('Documento de Marketing', 'Guía de estrategias de marketing.', 'documento', 2, 'activo', 5);

-- Inserciones para la tabla Pregunta
INSERT INTO Pregunta (titulo, tipo, ID_test)
VALUES
    ('¿Qué es la persuasión en ventas?', 'opcion_multiple', 1),
    ('¿Las redes sociales son efectivas para el marketing?', 'verdadero_falso', 2);

-- Inserciones para la tabla Resultado_Test
INSERT INTO Resultado_Test (puntaje, comentario, ID_empleado, ID_test)
VALUES
    (90, 'Buen desempeño en el test de ventas.', 2, 2),
    (70, 'Desempeño aceptable en el test de marketing.', 3, 3);

-- Inserciones para la tabla Retroalimen_capacitacion
INSERT INTO Retroalimen_capacitacion (comentario, ID_capacitacion)
VALUES
    ('La capacitación fue muy útil.', 1),
    ('Se podría mejorar la duración del curso.', 2);

-- Inserciones para la tabla Alternativa
INSERT INTO Alternativa (contenido, es_correcta, ID_pregunta)
VALUES
    ('La persuasión es clave en ventas.', 'correcto', 1),
    ('No, las redes sociales no son efectivas para el marketing.', 'incorrecto', 2);

-- Inserciones para la tabla SolicitudCapacitacion
INSERT INTO SolicitudCapacitacion (fecha_solicitud, estado, motivo, comentario, fecha_aprobacion, ID_empleado)
VALUES
    ('2024-09-15', 'pendiente', 'Evaluar la capacitación en ventas para el equipo.', 'Solicitud de capacitación para mejorar habilidades de ventas.', NULL, 1),
    ('2024-09-20', 'aprobada', 'Evaluar la capacitación en marketing digital.', 'Solicitud de capacitación para el equipo de marketing.', '2024-09-25', 2);
    


INSERT INTO Seller (cod_seller,nombre_seller, rubro, correo, telefono, ruc) 
VALUES
(1,'Bembos', 'Comida', 'ventas@bembos.com.pe', 987654321, 20567890123),
(2,'Norkys', 'Comida', 'info@norkys.com.pe', 923456789, 20345678912),
(3,'Coney Park', 'Entretenimiento', 'contacto@coneypark.com.pe', 912345678, 20987654321),
(4,'KFC', 'Comida', 'ventas@kfc.com.pe', 998765432, 20123456789),
(5,'Pizza Hut', 'Comida', 'info@pizzahut.com.pe', 911234567, 20876543210),
(6,'Starbucks', 'Bebida', 'contacto@starbucks.com.pe', 921234567, 20654321098),
(7,'La Lucha', 'Comida ', 'ventas@lalucha.com.pe', 914567123, 20765432109);

INSERT INTO Tipo_producto (cod_tipo_producto,nombre_tipo_producto) 
VALUES
(1,'Comida'),
(2,'Bebidas'),
(3,'Entretenimiento'),
(4,'Postres'),
(5,'Snacks'),
(6,'Desayunos'),
(7,'Alimentos Saludables');

INSERT INTO Producto (cod_producto,nombre_producto, empresa, cod_tipo_producto) 
VALUES
(1,'Hamburguesa', 'Bembos', 1),
(2,'Gaseosa', 'InkaKola', 2),
(3,'Entrada', 'Coney Park', 3),
(4,'Pollo a la Parrilla', 'Norkys', 1),
(5,'Papas Fritas', 'KFC', 1),
(6,'Pollo crispy', 'KFC', 1),
(7,'Hamburguesa Doble', 'La Lucha', 1);


INSERT INTO Promocion (cod_promocion,fecha_inicio, fecha_fin, dscto, estado_promo, dscrip_promo, ID_empleado) 
VALUES
(1,'2024-01-01', '2024-01-31', 15.00, FALSE, 'Promoción de Año Nuevo: Disfruta un 15% de descuento en hamburguesas y gaseosas de Bembos para celebrar el inicio de año.', 1),
(2,'2024-02-01', '2024-02-15', 10.00, FALSE, 'Descuento por San Valentín: Sorprende a tu pareja con un 10% de descuento en combos especiales de Norkys durante esta fecha romántica.', 2),
(3,'2024-03-01', '2024-03-31', 20.00, FALSE, 'Ofertas de Primavera: Ven y prueba nuestro nuevo menú de primavera con un 20% de descuento en entradas de Coney Park.', 3),
(4,'2024-04-01', '2024-04-15', 5.00, FALSE, 'Semana Santa: Disfruta de un 5% de descuento en platos de pollo a la parrilla en Norkys.', 4),
(5,'2024-09-01', '2024-10-31', 25.00, TRUE, 'Día de la Madre: Celebra a mamá con un 25% de descuento en el menú de KFC, incluyendo combos especiales.', 5),
(6,'2024-09-01', '2024-10-15', 30.00, TRUE, 'Ofertas de Mitad de Año: Aprovecha un 30% de descuento en bebidas de Starbucks y obtén un regalo por cada compra.', 6),
(7,'2024-10-01', '2024-11-30', 50.00, TRUE, 'Ofertas de Fiestas Patrias: Disfruta de un increíble 50% de descuento en hamburguesas dobles de La Lucha y celebra con nosotros.', 7);

INSERT INTO productoxseller (cod_productoxseller,cod_producto, cod_seller) 
VALUES
(1, 1, 1),  
(2, 2, 1),  
(3, 3, 3), 
(4, 4, 2),  
(5, 5, 4),  
(6, 6, 4),  
(7, 7, 5);  
 
INSERT INTO promocionxproducto (cod_promocionxproducto, cod_promocion, cod_producto) 
VALUES
(1, 1, 1),  
(2, 1, 2),  
(3, 2, 4),  
(4, 3, 3),  
(5, 4, 4),  
(6, 5, 5),  
(7, 6, 2),  
(8, 7, 7); 

INSERT INTO Prioridad_objetivo (nombre, peso)
VALUES
    ('Alta', 1),
    ('Media', 2),
    ('Baja', 3);

INSERT INTO Objetivo (descripcion, estado, fecha_inicio, fecha_fin, ID_empleado, ID_Prioridad_objetivo)
VALUES
    ('Mejorar el rendimiento del equipo de soporte técnico', 'Finalizado', '2024-01-01', '2024-03-31', 3, 1),
    ('Desarrollar la nueva funcionalidad para la plataforma', 'Pendiente', '2024-01-01', '2024-03-31', 2, 1),
    ('Implementar estrategias de marketing digital', 'Finalizado', '2024-01-01', '2024-03-31', 5, 2),
    ('Aumentar las ventas en un 10%', 'Pendiente', '2024-01-01', '2024-03-31', 6, 2),
    ('Capacitar a nuevos empleados en el área de soporte', 'Pendiente', '2024-01-01', '2024-03-31', 3, 2);

INSERT INTO Evaluacion (estado, fecha_inicio, fecha_fin, puntaje, retroalimentacion, ID_empleado)
VALUES
    ('Finalizada', '2024-04-01', '2024-04-15', 90, 'Objetivo cumplido con éxito.', 3),
    ('Pendiente', '2024-04-01', '2024-06-30', 0, 'Pendiente de evaluación', 2),
    ('Finalizada', '2024-04-01', '2024-04-15', 85, 'Objetivo cumplido con éxito.', 5),
    ('Pendiente', '2024-04-01', '2024-06-30', 0, 'Pendiente de evaluación', 6),
    ('Pendiente', '2024-04-01', '2024-06-30', 0, 'Pendiente de evaluación', 3);

INSERT INTO Evaluacion_objetivo (estado, ID_objetivo, ID_evaluacion)
VALUES
    ('Finalizado', 1, 1),
    ('En progreso', 2, 2), 
    ('Finalizado', 3, 3), 
    ('En progreso', 4, 4), 
    ('En progreso', 5, 5); 

INSERT INTO Tipo_permiso (nombre)
VALUES
    ('Falta justificada'),
    ('Vacaciones'),
    ('Permiso'),
    ('Tardanza justificada');

INSERT INTO Permiso (fecha_inicio, fecha_final, estado, comentario, ID_empleado, ID_tipo_permiso)
VALUES
    ('2024-02-01', '2024-02-03', 'Aprobado', 'Vacaciones programadas', 1, 2),
    ('2024-02-15', '2024-02-15', 'Aprobado', 'Permiso por asuntos personales', 3, 3),
    ('2024-03-01', '2024-03-02', 'Pendiente', 'Solicitado', 5, 3),
    ('2024-04-01', '2024-04-05', 'Aprobado', 'Vacaciones anuales', 6, 2),
    ('2024-05-01', '2024-05-01', 'Pendiente', 'Permiso para cita médica', 4, 3);

INSERT INTO Asistencia (fecha, estado, hora_entrada, hora_salida, ID_empleado)
VALUES
    ('2024-02-01', 'Presente', '09:00:00', '18:00:00', 1),
    ('2024-02-02', 'Ausente', NULL, NULL, 2),
    ('2024-02-01', 'Tarde', '09:30:00', '18:00:00', 3),
    ('2024-02-01', 'Presente', '08:55:00', '17:55:00', 4),
    ('2024-02-01', 'Presente', '09:00:00', '18:00:00', 5);
```
