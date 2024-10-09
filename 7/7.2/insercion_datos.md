```sql
INSERT INTO Departamento (id_departamento, descripcion)
VALUES
    (1, 'Departamento de Marketing'),
    (2, 'Departamento Comercial'),
    (3, 'Departamento de Recursos Humanos'),
    (4, 'Departamento de Finanzas'),
    (5, 'Departamento de Operaciones'),
    (6, 'Departamento de Atención al Cliente'),
    (7, 'Departamento de TI'),
    (8, 'Ventas'),
    (9, 'Tecnología');


INSERT INTO Puesto (id_puesto, descripcion, paga, nombre, id_departamento)
VALUES
    (1, 'Responsable de diseñar y ejecutar estrategias de marketing y publicidad.', 3000.00, 'Gerente de Marketing', 1),
    (2, 'Encargado de supervisar las actividades comerciales y la gestión del equipo de ventas.', 3200.00, 'Gerente Comercial', 2),
    (3, 'Asiste en la implementación de campañas de marketing y gestiona redes sociales.', 1800.00, 'Empleado de Marketing', 1),
    (4, 'Apoya en las ventas y atención al cliente en el área comercial.', 1500.00, 'Empleado de Comercial', 2),
    (5, 'Gestión de personal', 5000.00, 'Gerente de RRHH', 3),
    (6, 'Ventas y negociación', 4000.00, 'Representante de Ventas', 8),
    (7, 'Desarrollo de software', 5500.00, 'Desarrollador Senior', 9),
    (8, 'Análisis financiero', 4500.00, 'Analista Financiero', 4),
    (9, 'Estrategias de marketing', 4200.00, 'Especialista en Marketing', 1);


INSERT INTO Empleado (id_empleado, Nombre, Apellido, fecha_nacimiento, fecha_ingreso, Estado, Documento_identidad, Telefono, id_puesto)
VALUES
    (1, 'Carlos', 'González', '1985-03-21', '2020-01-10', 'Activo', 'DNI12345678', '987654321', 1),
    (2, 'María', 'Pérez', '1990-05-14', '2019-11-25', 'Activo', 'DNI87654321', '923456789', 2),
    (3, 'Luis', 'Ramírez', '1982-07-11', '2021-02-01', 'Activo', 'DNI45678901', '912345678', 3),
    (4, 'Ana', 'Lopez', '1995-08-02', '2022-03-05', 'Activo', 'DNI76543219', '998765432', 1),
    (5, 'Jorge', 'Mendoza', '1979-12-10', '2018-06-10', 'Activo', 'DNI23456789', '911234567', 2),
    (6, 'Sofía', 'Herrera', '1993-04-18', '2020-09-09', 'Activo', 'DNI56789012', '921234567', 3),
    (7, 'Gabriel', 'Torres', '1988-10-22', '2021-12-15', 'Activo', 'DNI12398765', '914567123', 1),
    (8, 'Juan', 'Pérez', '1985-05-15', '2020-01-10', 'Activo', 'DNI12345679', '987654322', 5),
    (9, 'María', 'González', '1990-08-22', '2019-03-05', 'Activo', 'DNI23456780', '987654323', 6),
    (10, 'Carlos', 'Rodríguez', '1988-11-30', '2021-06-15', 'Activo', 'DNI34567891', '987654324', 7),
    (11, 'Ana', 'Martínez', '1992-02-18', '2018-09-20', 'Activo', 'DNI45678902', '987654325', 8),
    (12, 'Luis', 'Sánchez', '1987-07-07', '2022-02-01', 'Activo', 'DNI56789013', '987654326', 9);


INSERT INTO Seller (cod_seller, nombre_seller, rubro, correo, telefono, ruc)
VALUES
    (1, 'Bembos', 'Comida', 'ventas@bembos.com.pe', 987654321, 20567890123),
    (2, 'Norkys', 'Comida', 'info@norkys.com.pe', 923456789, 20345678912),
    (3, 'Coney Park', 'Entretenimiento', 'contacto@coneypark.com.pe', 912345678, 20987654321),
    (4, 'KFC', 'Comida', 'ventas@kfc.com.pe', 998765432, 20123456789),
    (5, 'Pizza Hut', 'Comida', 'info@pizzahut.com.pe', 911234567, 20876543210),
    (6, 'Starbucks', 'Bebida', 'contacto@starbucks.com.pe', 921234567, 20654321098),
    (7, 'La Lucha', 'Comida ', 'ventas@lalucha.com.pe', 914567123, 20765432109);


INSERT INTO Tipo_producto (cod_tipo_producto, nombre_tipo_producto)
VALUES
    (1, 'Comida'),
    (2, 'Bebidas'),
    (3, 'Entretenimiento'),
    (4, 'Postres'),
    (5, 'Snacks'),
    (6, 'Desayunos'),
    (7, 'Alimentos Saludables');


INSERT INTO Producto (cod_producto, nombre_producto, empresa, cod_tipo_producto)
VALUES
    (1, 'Hamburguesa', 'Bembos', 1),
    (2, 'Gaseosa', 'InkaKola', 2),
    (3, 'Entrada', 'Coney Park', 3),
    (4, 'Pollo a la Parrilla', 'Norkys', 1),
    (5, 'Papas Fritas', 'KFC', 1),
    (6, 'Pollo crispy', 'KFC', 1),
    (7, 'Hamburguesa Doble', 'La Lucha', 1);


INSERT INTO Promocion (cod_promocion, fecha_inicio, fecha_fin, dscto, estado_promo, dscrip_promo, ID_empleado)
VALUES
    (1, '2024-01-01', '2024-01-31', 15.00, FALSE, 'Promoción de Año Nuevo: Disfruta un 15% de descuento en hamburguesas y gaseosas de Bembos para celebrar el inicio de año.', 1),
    (2, '2024-02-01', '2024-02-15', 10.00, FALSE, 'Descuento por San Valentín: Sorprende a tu pareja con un 10% de descuento en combos especiales de Norkys durante esta fecha romántica.', 2),
    (3, '2024-03-01', '2024-03-31', 20.00, FALSE, 'Ofertas de Primavera: Ven y prueba nuestro nuevo menú de primavera con un 20% de descuento en entradas de Coney Park.', 3),
    (4, '2024-04-01', '2024-04-15', 5.00, FALSE, 'Semana Santa: Disfruta de un 5% de descuento en platos de pollo a la parrilla en Norkys.', 4),
    (5, '2024-09-01', '2024-10-31', 25.00, TRUE, 'Día de la Madre: Celebra a mamá con un 25% de descuento en el menú de KFC, incluyendo combos especiales.', 5),
    (6, '2024-09-01', '2024-10-15', 30.00, TRUE, 'Ofertas de Mitad de Año: Aprovecha un 30% de descuento en bebidas de Starbucks y obtén un regalo por cada compra.', 6),
    (7, '2024-10-01', '2024-11-30', 50.00, TRUE, 'Ofertas de Fiestas Patrias: Disfruta de un increíble 50% de descuento en hamburguesas dobles de La Lucha y celebra con nosotros.', 7);


INSERT INTO productoxseller (cod_productoxseller, cod_producto, cod_seller)
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


ALTER SEQUENCE estado_prototipo_cod_est_prot_seq RESTART WITH 1;
INSERT INTO Estado_prototipo (estado_prot)
VALUES
    ('Aprobado'),
    ('Rechazado'),
    ('Pendiente');


ALTER SEQUENCE prototipo_cod_prototipo_seq RESTART WITH 1;
INSERT INTO Prototipo (Objetivos, Prop_presupuesto, Prop_audiencia, Prop_canal, cod_est_prot, ID_empleado)
VALUES
    ('Aumentar ventas en un 15%', 4500.00, '2030MLimaNorte', 'Redes sociales', 1, 1),
    ('Incrementar interacción en redes', 3500.00, '4050MFLimaCentro', 'Publicidad digital', 2, 2),
    ('Posicionar nuevo producto', 6000.00, '1030MSJL', 'Anuncios en TV y radio', 1, 3),
    ('Aumentar las ventas en un 10%', 5000.00, '1850MFLimaCentro', 'Redes sociales', 1, 4),
    ('Incrementar la visibilidad de la marca', 3000.00, '3040MCallao', 'Publicidad digital', 1, 2),
    ('Realizar una campaña de fidelización', 4000.00, '2040MFLimaCentro', 'Email marketing', 3, 2),
    ('Lanzar un nuevo producto en el mercado', 7000.00, '2050MFLimaNorte', 'Televisión', 3, 1);


ALTER SEQUENCE campana_publicitaria_cod_campana_seq RESTART WITH 1;
INSERT INTO Campana_Publicitaria (Resultado, Presupuesto, Nombre_campana, Audiencia, Canal, cod_promocion, cod_prototipo)
VALUES
    ('Aumento de ventas en un 20%', 6000.00, 'Campaña de Verano', '1850MFLimaCentro', 'Redes sociales', 1, 1),
    ('Incremento de tráfico web en un 30%', 4500.00, 'Campaña de Fin de Año', '4050MFLimaCentro', 'Publicidad digital', 3, 2),
    ('Fidelización de clientes existentes', 3000.00, 'Campaña de lealtad', '1030MSJL', 'Anuncion en TV y radio', 2, 3),
    ('Aumento de las ventas en un 10%', 5000.00, 'CampañaN1', '1850MFLimaCentro', 'Redes sociales', 1, 4);


ALTER SEQUENCE pre_test_id_pretest_seq RESTART WITH 1;
INSERT INTO pre_test (fecha, audienciaa, audienciab, resultado, comentarios, cod_prototipo)
VALUES
    ('2024-10-01', '1850MFLimaCentro', '2030MLimaNorte', '1850MFLimaCentro', 'El prototipo es bien recibido por el público objetivo.', 1),
    ('2024-10-02', '4050MFLimaCentro', '3040MCallao', '4050MFLimaCentro', 'Interés moderado, se sugiere ajustar la propuesta de valor.', 2),
    ('2024-10-03', '1030MSJL', '3040MCallao', '1030MSJL', 'Los objetivos estan bien pero pueden ser mejores.', 3),
    ('2024-10-04', '1850MFLimaCentro', '1850MFLimaNorte', '1850MFLimaCentro', 'Excelentes reacciones, se recomienda avanzar al siguiente paso.', 4),
    ('2024-10-04', '3040MCallao', '1030MSJL', '3040MCallao', 'Excelentes reacciones, se recomienda avanzar al siguiente paso.', 5);


INSERT INTO Vacante (estado, fecha_fin, fecha_inicio, descripcion, id_puesto) VALUES
                                                                                  ('Abierta', '2024-12-31', '2024-10-15', 'Se busca desarrollador Java Senior', 7),
                                                                                  ('Abierta', '2024-11-30', '2024-10-01', 'Vacante para Analista de Marketing Digital', 9),
                                                                                  ('Cerrada', '2024-09-30', '2024-08-01', 'Posición de Asistente de RRHH', 5);


INSERT INTO Postulante (carrera, cv, nombre, oferta_laboral, ol_firmada, id_vacante) VALUES
                                                                                         ('Ingeniería Informática', 'CV_JoseSilva.pdf', 'José Silva', 'Oferta_Dev_Java.pdf', FALSE, 1),
                                                                                         ('Marketing', 'CV_LauraRios.pdf', 'Laura Ríos', 'Oferta_Marketing.pdf', FALSE, 2),
                                                                                         ('Administración de Empresas', 'CV_PedroGomez.pdf', 'Pedro Gómez', 'Oferta_RRHH.pdf', TRUE, 3);


INSERT INTO Feedback (descripcion, fecha) VALUES
                                              ('Excelente conocimiento técnico y habilidades de comunicación', '2024-10-20'),
                                              ('Buena experiencia en marketing digital, necesita mejorar en análisis de datos', '2024-10-10'),
                                              ('Buen candidato, pero con poca experiencia en el área de RRHH', '2024-09-15');


INSERT INTO Entrevista (estado, fecha, puntaje, tipo, id_postulante, ID_empleado, id_feedback) VALUES
                                                                                                   ('Completada', '2024-10-20', 95, 'Técnica', 1, 10, 1),
                                                                                                   ('Completada', '2024-10-10', 80, 'Presencial', 2, 12, 2),
                                                                                                   ('Completada', '2024-09-15', 75, 'Virtual', 3, 8, 3);


INSERT INTO Capacitacion (id_capacitacion,fecha_inicio, fecha_final, nombre, estado, descripcion, ID_instructor) 
VALUES
(1,'2024-01-01', '2024-01-10', 'Capacitación en SQL', 'Pendiente', 'Aprender SQL básico', 9),
(2,'2024-02-01', '2024-02-05', 'Capacitación en Python', 'Pendiente', 'Introducción a Python', 8);


INSERT INTO empleadoxcapacitacion (estado, resultado, ID_empleado, ID_capacitacion)
 VALUES
('Completado', 90, 3, 1),
('Pendiente', 0, 4, 1),
('Completado', 85, 5, 2);


INSERT INTO Modulo (nombre, orden, estado, ID_capacitacion) 
VALUES
('Introducción a SQL', 1, 'Activo', 1),
('Bases de datos', 2, 'Activo', 1),
('Introducción a Python', 1, 'Activo', 2);


INSERT INTO Test (nombre, fecha, estado, descripcion, ID_modulo) 
VALUES
('Examen SQL 1', '2024-01-09', 'Pendiente', 'Examen 1 de SQL', 1),
('Examen Python 1', '2024-02-04', 'Pendiente', 'Examen 1 de Python', 3);


INSERT INTO Recurso (nombre, contenido, tipo, orden, estado, ID_modulo)
 VALUES
('Video introductorio', 'https://www.youtube.com/watch?v=Atpj2UsF65M', 'Video', 1, 'Activo', 1),
('PPT 2 - Bases de datos', 'http://www.yape.com/recurso-2', 'Documento', 2, 'Activo', 2);


INSERT INTO Pregunta (titulo, tipo, ID_test) 
VALUES
('¿Qué es SQL?', 'Claves', 1),
('¿Para qué se usa Python?', 'Claves', 2);


INSERT INTO Resultado_Test (comentario, descripcion, ID_empleado, ID_test) 
VALUES
('Buena respuesta', 'Entendimiento completo de SQL', 3, 1),
('Necesita mejorar', 'Conceptos básicos de Python', 4, 2);


INSERT INTO Retroalimen_capacitacion (comentario, ID_capacitacion)
 VALUES
('Buena capacitación', 1),
('Mejorar contenido', 2);


INSERT INTO Alternativa (contenido, es_correcta, ID_pregunta) 
VALUES
('Structured Query Language', 'S', 1),
('Para análisis de datos', 'S', 2);


INSERT INTO SolicitudCapacitacion (fecha_solicitud, estado, motivo, comentario, fecha_aprobacion, ID_empleado) 
VALUES
('2024-10-01', 'Aprobado', 'Mejorar habilidades', 'Solicita capacitación en SQL', '2024-10-05', 1),
('2024-10-02', 'Pendiente', 'Nuevo puesto', 'Solicita capacitación en Python', NULL, 2);

INSERT INTO Asistencia (Estado, Fecha, hora_entrada, hora_salida, ID_empleado) 
VALUES
('Presente', '2024-10-01', '09:00:00', '17:00:00', 1),
('Ausente', '2024-10-01', NULL, NULL, 2),
('Presente', '2024-10-02', '09:30:00', '17:00:00', 2),
('Tarde', '2024-10-02', '10:15:00', '17:00:00', 3),
('Presente', '2024-10-02', '09:00:00', '17:00:00', 1),
('Ausente', '2024-10-04', NULL, NULL, 2),
('Presente', '2024-10-03', '09:00:00', '17:00:00', 1),
('Ausente', '2024-10-03', NULL, NULL, 5),
('Presente', '2024-10-04', '09:00:00', '17:00:00', 6);

INSERT INTO Permiso (Fecha_inicio, Fecha_final, estado, Tipo, comentario, ID_empleado)
VALUES
('2024-10-06', '2024-10-08', 'PENDIENTE', 'Vacaciones', 'Solicito 5 días de vacaciones para asuntos personales.', 1),
('2024-10-10', '2024-10-11', 'APROBADO', 'Día Libre', 'Solicito un día libre por motivos médicos.', 2),
('2024-10-15', '2024-10-17', 'PENDIENTE', 'Licencia', 'Necesito 3 días de descanso por enfermedad.', 3),
('2024-10-20', '2024-10-20', 'APROBADO', 'Otros', 'Solicito medio día para realizar trámites.', 4);

INSERT INTO Evaluacion (estado, fecha_inicio, fecha_fin, puntaje, retroalimentacion, ID_empleado) 
VALUES
('Finalizado', '2024-01-15', '2024-03-15', 66.6, 'Buen desempeño general, pero puede mejorar en gestión de tiempos.', 1),
('FINALIZADO', '2024-02-10', '2024-04-15', 100.0, 'Buen trabajo en equipo, pero necesita mejorar en la atención al cliente.', 2),
('EN PROCESO', '2024-09-01', '2024-12-07', NULL, NULL, 3),
('EN PROCESO', '2024-09-05', '2024-12-10', NULL, NULL, 4),
('EN PROCESO', '2024-09-12', '2024-12-17', NULL, NULL, 5),
('Finalizado', '2024-01-15', '2024-03-15', 50.0, 'Cumplimiento amedias de meta, pero falta más proactividad.', 6);

INSERT INTO Objetivo (descripcion, Cumplido, ID_Evaluacion) 
values
('Realizar 50 reportes mensuales de ventas.', TRUE, 1),
('Reducir el tiempo de entrega de proyectos en un 10%.', FALSE, 1),
('Capacitar a 5 nuevos empleados en el sistema CRM.', TRUE, 1),
('Capacitar al equipo en metodologías ágiles.', TRUE, 2),
('Desarrollar mas de 5 campañas publicitaria en redes sociales.', TRUE, 2),
('Aumentar las ventas en un 20% en el segundo semestre.', TRUE, 2),
('Mejorar la comunicación interna entre departamentos.', TRUE, 2),
('Mejorar la retención de clientes en un 15%.', FALSE, 6),
('Optimizar el proceso de reclutamiento y selección.', TRUE, 6);


```