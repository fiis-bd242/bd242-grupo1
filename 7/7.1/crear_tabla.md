CREATE TABLE seller (
	cod_seller SERIAL NOT NULL, 
	nombre_seller VARCHAR (50),
	rubro VARCHAR (50),
	correo VARCHAR (50),
	telefono numeric (9) NOT NULL,
	ruc numeric (11) NOT null,
	PRIMARY KEY (cod_seller)
);

CREATE TABLE Feedback (
                          id_feedback SERIAL NOT NULL,
                          descripcion TEXT NOT NULL,
                          fecha DATE NOT NULL,
                          PRIMARY KEY (id_feedback)
);

CREATE TABLE Estado_prototipo (
                               
	cod_est_prot serial not null,
	estado_prot text,
    PRIMARY KEY (cod_est_prot)
);

CREATE TABLE Tipo_producto (
                              cod_tipo_producto SERIAL NOT NULL,
                              nombre_tipo_producto VARCHAR(255) NOT NULL,
                              PRIMARY KEY (cod_tipo_producto)
);

CREATE TABLE Producto (
                          cod_producto SERIAL NOT NULL,
                          nombre_producto VARCHAR(255) NOT NULL,
                          empresa VARCHAR(255) NOT NULL,
                          cod_tipo_producto INTEGER NOT NULL,
                          PRIMARY KEY (cod_producto),
                          FOREIGN KEY (cod_tipo_producto) REFERENCES Tipo_producto(cod_tipo_producto)
);

CREATE TABLE productoxseller (
                                 cod_productoxseller SERIAL NOT NULL,
                                 cod_producto INTEGER NOT NULL,
                                 cod_seller INTEGER NOT NULL,
                                 PRIMARY KEY (cod_productoxseller),
                                 FOREIGN KEY (cod_producto) REFERENCES Producto(cod_producto),
                                 FOREIGN KEY (cod_seller) REFERENCES Seller(cod_seller)
);
CREATE TABLE Departamento (
                              id_departamento SERIAL NOT NULL,
                              descripcion VARCHAR(100) NOT NULL,
                              PRIMARY KEY (id_departamento)
);

CREATE TABLE Puesto (
                        id_puesto SERIAL NOT NULL,
                        descripcion VARCHAR(100) NOT NULL,
                        paga FLOAT NOT NULL,
                        nombre VARCHAR(50) NOT NULL,
                        id_departamento INTEGER NOT NULL,
						FOREIGN KEY(id_departamento) REFERENCES Departamento(id_departamento),
                        PRIMARY KEY (id_puesto)
);

CREATE TABLE Empleado (
			  ID_empleado SERIAL NOT NULL,
                          Nombre VARCHAR(50) NOT NULL,
                          Apellido VARCHAR(50) NOT NULL,     
                          fecha_nacimiento DATE NOT NULL,
                          fecha_ingreso DATE NOT NULL,
                          Estado VARCHAR(50) NOT NULL,
                          Documento_identidad VARCHAR(50) NOT NULL,
                          Telefono VARCHAR(20) NOT NULL,
                          id_puesto INTEGER NOT NULL,
                          PRIMARY KEY (ID_empleado),
                          FOREIGN KEY (id_puesto) REFERENCES Puesto(id_puesto)
);

CREATE TABLE Ticket_incidente (
                                  cod_ticket_inc SERIAL NOT NULL,
                                  categoria VARCHAR(10) NOT NULL,
                                  prioridad VARCHAR(10) NOT NULL,
                                  estado VARCHAR(10) NOT NULL,
                                  fecha_ticket_inc DATE NOT NULL,
                                  ID_empleado INTEGER NOT NULL,
                                  PRIMARY KEY (cod_ticket_inc),
                                  FOREIGN KEY (ID_empleado) REFERENCES Empleado(ID_empleado)
);

CREATE TABLE incidente (
                           cod_incidente SERIAL NOT NULL,
                           descripcion_incidente VARCHAR(250) NOT NULL,
                           cod_ticket_inc INTEGER NOT NULL,
                           PRIMARY KEY (cod_incidente),
                           FOREIGN KEY (cod_ticket_inc) REFERENCES Ticket_incidente(cod_ticket_inc)
);

CREATE TABLE diagnostico (
                             cod_diag SERIAL NOT NULL,
                             comentario VARCHAR(250) NOT NULL,
                             fecha_realizacion DATE NOT NULL,
                             proveedor VARCHAR(15) NOT NULL,
                             cod_incidente INTEGER NOT NULL,
                             ID_empleado INTEGER NOT NULL,
                             PRIMARY KEY (cod_diag),
                             FOREIGN KEY (cod_incidente) REFERENCES incidente(cod_incidente),
                             FOREIGN KEY (ID_empleado) REFERENCES Empleado(ID_empleado),
                             UNIQUE (proveedor)
);

CREATE TABLE Solucion (
                          ID_Solucion SERIAL NOT NULL,
                          estado_solucion VARCHAR(10) NOT NULL,
                          test VARCHAR(20) NOT NULL,
                          cod_diag INTEGER NOT NULL,
                          PRIMARY KEY (ID_Solucion),
                          FOREIGN KEY (cod_diag) REFERENCES diagnostico(cod_diag)
);

CREATE TABLE promocion (
	cod_promocion SERIAL  NOT NULL, 
	fecha_inicio DATE,
	fecha_fin DATE,
	dscto numeric (5, 2), 
	estado_promo BOOLEAN default FALSE,
	dscrip_promo VARCHAR (250),
	ID_empleado INTEGER,
	PRIMARY KEY(cod_promocion),
	FOREIGN KEY(ID_empleado) REFERENCES Empleado(ID_empleado)
);

CREATE TABLE promocionxproducto (
                                    cod_promocionxproducto SERIAL NOT NULL,
                                    cod_promocion INTEGER NOT NULL,
                                    cod_producto INTEGER NOT NULL,
                                    PRIMARY KEY (cod_promocionxproducto),
                                    FOREIGN KEY (cod_promocion) REFERENCES Promocion(cod_promocion),
                                    FOREIGN KEY (cod_producto) REFERENCES Producto(cod_producto)
);

CREATE TABLE Prototipo (
                           cod_prototipo SERIAL NOT NULL,
                           Objetivos TEXT NOT NULL,
                           Prop_presupuesto NUMERIC NOT NULL,
                           Prop_audiencia TEXT NOT NULL,
                           Prop_canal VARCHAR(100) NOT NULL,
                           ID_empleado INTEGER NOT NULL,
                           cod_est_prot INTEGER NOT NULL,
                           PRIMARY KEY (cod_prototipo),
                           FOREIGN KEY (ID_empleado) REFERENCES Empleado(ID_empleado),
                           FOREIGN KEY (cod_est_prot) REFERENCES Estado_prototipo(cod_est_prot)
);

CREATE TABLE Campana_Publicitaria (
                                      cod_campana SERIAL NOT NULL,
                                      Resultado TEXT NOT NULL,
                                      Presupuesto NUMERIC NOT NULL,
                                      Nombre_campana VARCHAR(255) NOT NULL,
                                      Audiencia TEXT NOT NULL,
                                      Canal VARCHAR(100) NOT NULL,
                                      cod_promocion INTEGER NOT NULL,
                                      cod_prototipo INTEGER NOT NULL,
                                      PRIMARY KEY (cod_campana),
                                      FOREIGN KEY (cod_promocion) REFERENCES Promocion(cod_promocion),
                                      FOREIGN KEY (cod_prototipo) REFERENCES Prototipo(cod_prototipo)
);

CREATE TABLE Pre_test (
                          AudienciaA TEXT NOT NULL,
                          AudienciaB TEXT NOT NULL,
                          id_pretest SERIAL NOT NULL,
                          Fecha DATE NOT NULL,
                          Resultado TEXT NOT NULL,
                          Comentarios TEXT NOT NULL,
                          cod_prototipo INTEGER NOT NULL,
                          PRIMARY KEY (id_pretest),
                          FOREIGN KEY (cod_prototipo) REFERENCES Prototipo(cod_prototipo)
);

CREATE TABLE Vacante (
                         id_vacante SERIAL NOT NULL,
                         estado VARCHAR(50) NOT NULL,
                         fecha_fin DATE NOT NULL,
                         fecha_inicio DATE NOT NULL,
                         descripcion VARCHAR(100) NOT NULL,
                         id_puesto INTEGER NOT NULL,
                         PRIMARY KEY (id_vacante),
                         FOREIGN KEY (id_puesto) REFERENCES Puesto(id_puesto)
);
CREATE TABLE Postulante (
    id_postulante SERIAL NOT NULL,
    carrera VARCHAR(100) NOT NULL,
    cv TEXT NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    oferta_laboral TEXT NOT NULL,
    ol_firmada BOOLEAN NOT NULL,
    id_vacante INTEGER NOT NULL,
    PRIMARY KEY (id_postulante),
    FOREIGN KEY (id_vacante) REFERENCES Vacante(id_vacante)
);

CREATE TABLE Entrevista (
    id_entrevista SERIAL NOT NULL,
    estado VARCHAR(50) NOT NULL,
    fecha DATE NOT NULL,
    puntaje INTEGER NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    id_postulante INTEGER NOT NULL,
    ID_empleado INTEGER NOT NULL,
    id_feedback INTEGER NOT NULL,
    PRIMARY KEY (id_entrevista),
    FOREIGN KEY (id_postulante) REFERENCES Postulante(id_postulante),
    FOREIGN KEY (ID_empleado) REFERENCES Empleado(ID_empleado),
    FOREIGN KEY (id_feedback) REFERENCES Feedback(id_feedback)
);

CREATE TABLE Ticket_asig_tip (
    cod_ticket_asig SERIAL NOT NULL,
    id_conv INTEGER NOT NULL,
    problema_ident VARCHAR(250) NOT NULL,
    fecha_asig DATE NOT NULL,
    estado VARCHAR(5) NOT NULL,
    comentario VARCHAR(250) NOT NULL,
    ID_empleado INTEGER NOT NULL,
    PRIMARY KEY (cod_ticket_asig),
    FOREIGN KEY (ID_empleado) REFERENCES Empleado(ID_empleado)
);

CREATE TABLE Ticket_general (
    cod_ticket SERIAL NOT NULL,
    fecha_creacion DATE NOT NULL,
    categoria VARCHAR(7) NOT NULL,
    cod_ticket_asig INTEGER NOT NULL,
    cod_ticket_inc INTEGER NOT NULL,
    PRIMARY KEY (cod_ticket),
    FOREIGN KEY (cod_ticket_inc) REFERENCES Ticket_incidente(cod_ticket_inc),
    FOREIGN KEY (cod_ticket_asig) REFERENCES Ticket_asig_tip(cod_ticket_asig)
);

CREATE TABLE Notificacion (
    id_noti SERIAL NOT NULL,
    fecha_envio DATE NOT NULL,
    asunto VARCHAR(100) NOT NULL,
    mensaje VARCHAR(250) NOT NULL,
    aprobacion VARCHAR(10) NOT NULL,
    cod_ticket_asig INTEGER NOT NULL,
    PRIMARY KEY (id_noti),
    FOREIGN KEY (cod_ticket_asig) REFERENCES Ticket_asig_tip(cod_ticket_asig)
);

CREATE TABLE tipificacion (
    cod_etiqueta SERIAL NOT NULL,
    funcionalidad VARCHAR(200) NOT NULL,
    fecha_creacion DATE NOT NULL,
    id_departamento INTEGER NOT NULL,
    comentario VARCHAR(250) NOT NULL,
    motivo VARCHAR(250) NOT NULL,
    cod_ticket_asig INTEGER NOT NULL,
    PRIMARY KEY (cod_etiqueta),
    FOREIGN KEY (cod_ticket_asig) REFERENCES Ticket_asig_tip(cod_ticket_asig),
    FOREIGN KEY (id_departamento) REFERENCES Departamento(id_departamento)
);

CREATE TABLE Asistencia (
                            ID_Asistencia SERIAL NOT NULL,
                            Estado VARCHAR(50) NOT NULL,
                            Fecha DATE NOT NULL,
                            hora_entrada TIME,
                            hora_salida TIME,
                            ID_empleado INTEGER NOT NULL,
                            PRIMARY KEY (ID_Asistencia),
                            FOREIGN KEY (ID_empleado) REFERENCES Empleado(ID_empleado)
);

CREATE TABLE Permiso (
    Id_Permiso SERIAL NOT NULL,
    Fecha_inicio DATE NOT NULL,
    Fecha_final DATE NOT NULL,
    estado VARCHAR(50) NOT NULL,
    Tipo VARCHAR(50) NOT NULL,
    comentario VARCHAR(300) NOT NULL,
    ID_empleado INTEGER NOT NULL,
    PRIMARY KEY (Id_Permiso),
    FOREIGN KEY (ID_empleado) REFERENCES Empleado(ID_empleado)
);

CREATE TABLE Evaluacion (
    ID_Evaluacion SERIAL NOT NULL,
    estado VARCHAR(20) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    puntaje NUMERIC,
    retroalimentacion VARCHAR(200),
    ID_empleado INTEGER NOT NULL,
    PRIMARY KEY (ID_Evaluacion),
    FOREIGN KEY (ID_empleado) REFERENCES Empleado(ID_empleado)
);

CREATE TABLE Objetivo (
    Id_objetivo SERIAL NOT NULL,
    descripcion TEXT NOT NULL,
    Cumplido BOOLEAN NOT NULL,
    ID_Evaluacion INTEGER NOT NULL,
    PRIMARY KEY (Id_objetivo),
    FOREIGN KEY (ID_Evaluacion) REFERENCES Evaluacion(ID_Evaluacion)
);

CREATE TABLE Capacitacion
(
  ID_capacitacion SERIAL NOT NULL,
  fecha_inicio DATE NOT NULL,
  fecha_final DATE NOT NULL,
  nombre VARCHAR(255) NOT NULL,
  estado VARCHAR(20) NOT NULL,
  descripcion VARCHAR(200) NOT NULL,
  ID_instructor INTEGER NOT NULL,
  PRIMARY KEY (ID_capacitacion),
  FOREIGN KEY (ID_instructor) REFERENCES Empleado(ID_empleado)
);

CREATE TABLE empleadoxcapacitacion
(
  id_empleadoxcapacitacion SERIAL NOT NULL,
  estado VARCHAR(20) NOT NULL,
  resultado INTEGER NOT NULL,
  ID_empleado INTEGER NOT NULL,
  ID_capacitacion INTEGER NOT NULL,
  PRIMARY KEY (id_empleadoxcapacitacion),
  FOREIGN KEY (ID_empleado) REFERENCES Empleado(ID_empleado),
  FOREIGN KEY (ID_capacitacion) REFERENCES Capacitacion(ID_capacitacion),
  UNIQUE (ID_empleado, ID_capacitacion)
);

CREATE TABLE Modulo
(
  ID_modulo SERIAL NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  orden INTEGER NOT NULL,
  estado VARCHAR(20) NOT NULL,
  ID_capacitacion INTEGER NOT NULL,
  PRIMARY KEY (ID_modulo),
  FOREIGN KEY (ID_capacitacion) REFERENCES Capacitacion(ID_capacitacion)
);

CREATE TABLE Test
(
  ID_test SERIAL NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  fecha DATE NOT NULL,
  estado VARCHAR(50) NOT NULL,
  descripcion VARCHAR(50) NOT NULL,
  ID_modulo INTEGER NOT NULL,
  PRIMARY KEY (ID_test),
  FOREIGN KEY (ID_modulo) REFERENCES Modulo(ID_modulo)
);

CREATE TABLE Recurso
(
  ID_recurso SERIAL NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  contenido VARCHAR(200) NOT NULL,
  tipo VARCHAR(50) NOT NULL,
  orden INTEGER NOT NULL,
  estado VARCHAR(20) NOT NULL,
  ID_modulo INTEGER NOT NULL,
  PRIMARY KEY (ID_recurso),
  FOREIGN KEY (ID_modulo) REFERENCES Modulo(ID_modulo)
);

CREATE TABLE Pregunta
(
  ID_pregunta SERIAL NOT NULL,
  titulo VARCHAR(50) NOT NULL,
  tipo VARCHAR(50) NOT NULL,
  ID_test INTEGER NOT NULL,
  PRIMARY KEY (ID_pregunta),
  FOREIGN KEY (ID_test) REFERENCES Test(ID_test)
);

CREATE TABLE Resultado_Test
(
  ID_resultadotest SERIAL NOT NULL,
  comentario VARCHAR(200) NOT NULL,
  descripcion VARCHAR(200) NOT NULL,
  ID_empleado INTEGER NOT NULL,
  ID_test INTEGER NOT NULL,
  PRIMARY KEY (ID_resultadotest),
  FOREIGN KEY (ID_empleado) REFERENCES Empleado(ID_empleado),
  FOREIGN KEY (ID_test) REFERENCES Test(ID_test)
);

CREATE TABLE Retroalimen_capacitacion
(
  ID_retroalimen_cap SERIAL NOT NULL,
  comentario VARCHAR(50) NOT NULL,
  ID_capacitacion INTEGER NOT NULL,
  PRIMARY KEY (ID_retroalimen_cap),
  FOREIGN KEY (ID_capacitacion) REFERENCES Capacitacion(ID_capacitacion)
);

CREATE TABLE Alternativa
(
  ID_alternativa SERIAL NOT NULL,
  contenido VARCHAR(50) NOT NULL,
  es_correcta CHAR(1) NOT NULL,
  ID_pregunta INTEGER NOT NULL,
  PRIMARY KEY (ID_alternativa),
  FOREIGN KEY (ID_pregunta) REFERENCES Pregunta(ID_pregunta)
);

CREATE TABLE SolicitudCapacitacion
(
  ID_solicitud_cap SERIAL NOT NULL,
  fecha_solicitud DATE NOT NULL,
  estado VARCHAR(20) NOT NULL,
  motivo VARCHAR(50) NOT NULL,
  comentario VARCHAR(200) NOT NULL,
  fecha_aprobacion DATE,
  ID_empleado INTEGER NOT NULL,
  PRIMARY KEY (ID_solicitud_cap),
  FOREIGN KEY (ID_empleado) REFERENCES Empleado(ID_empleado)
);
