```sql
CREATE TYPE estado_enum AS ENUM ('Pendiente', 'Resuelto', 'No Resuelto','En Progreso', 'Reabierto');
CREATE TYPE aprobacion_enum AS ENUM ('aprobado', 'rechazado');
CREATE TYPE estado_conv_enum AS ENUM ('abierta', 'cerrada');

CREATE TYPE estado_capacitacion AS ENUM ('pendiente', 'en_proceso', 'finalizado');
CREATE TYPE estado_empleado_capacitacion AS ENUM ('inscrito', 'aprobado', 'reprobado', 'pendiente');
CREATE TYPE estado_modulo AS ENUM ('activo', 'inactivo');
CREATE TYPE estado_test AS ENUM ('inactivo', 'cerrado', 'pendiente', 'realizado');
CREATE TYPE tipo_recurso_modulo AS ENUM ('video', 'documento');
CREATE TYPE tipo_pregunta AS ENUM ('opcion_multiple', 'verdadero_falso');
CREATE TYPE valor AS ENUM ('correcto', 'incorrecto');
CREATE TYPE estado_solicitud_capacitacion AS ENUM ('pendiente', 'aprobada', 'rechazada');

CREATE TYPE estado_objetivo_enum AS ENUM ('Pendiente', 'Cumplido', 'No cumplido', 'Cancelado');
CREATE TYPE estado_prioridad_enum AS ENUM ('Alta', 'Media', 'Baja');
CREATE TYPE estado_evaluacion_enum AS ENUM ('Pendiente', 'Finalizada');
CREATE TYPE estado_eval_objetivo_enum AS ENUM ('Cumplido', 'No cumplido', 'En progreso', 'Cancelado');
CREATE TYPE estado_asistencia_enum AS ENUM ('Presente', 'Ausente', 'Tarde');
CREATE TYPE tipo_permiso_enum AS ENUM ('Falta justificada', 'Vacaciones', 'Permiso', 'Tardanza justificada');
CREATE TYPE estado_permiso_enum AS ENUM ('Pendiente', 'Aprobado', 'Rechazado');


CREATE TABLE seller (
                        cod_seller SERIAL NOT NULL,
                        nombre_seller VARCHAR (50),
                        rubro VARCHAR (50),
                        correo VARCHAR (50),
                        telefono numeric (9) NOT NULL,
                        ruc numeric (11) NOT null,
                        PRIMARY KEY (cod_seller)
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

CREATE TABLE Ticket_general (
                                cod_ticket SERIAL NOT NULL,
                                fecha_creacion DATE NOT NULL,
                                categoria VARCHAR(7) NOT NULL,
                                ID_empleado INTEGER not null,
                                PRIMARY KEY (cod_ticket),
                                FOREIGN KEY (ID_empleado) REFERENCES Empleado(ID_empleado)
);


create TABLE Ticket_incidente (
                                  cod_ticket_inc SERIAL NOT NULL,
                                  categoria VARCHAR(10) NOT NULL,
                                  prioridad VARCHAR(10) NOT NULL,
                                  estado VARCHAR(10) NOT NULL,
                                  fecha_ticket_inc DATE NOT NULL,
                                  PRIMARY KEY (cod_ticket_inc),
                                  FOREIGN KEY (cod_ticket_inc) REFERENCES Ticket_general(cod_ticket)
);


CREATE TABLE Ticket_postmortem (
    cod_ticket_post serial not NULL,
    fecha_creacion_post DATE not NULL,
    Estado_ticket_post VARCHAR(50) not NULL,
    cod_ticket_inc INTEGER not NULL,
    primary key (cod_ticket_post),
    FOREIGN KEY (cod_ticket_inc) REFERENCES Ticket_incidente(cod_ticket_inc)
);

-- Tabla Postmortem_detalles
CREATE TABLE Postmortem_detalles (
    cod_postmortem SERIAL not NULL,
    resumen_post VARCHAR(20) not NULL,
    Impacto VARCHAR(50) not NULL,
    Deteccion VARCHAR(50) not NULL,
    Recuperacion VARCHAR(50) not NULL,
    Severidad VARCHAR(50) not NULL,
    cod_ticket_post INTEGER not NULL,
    primary key (cod_postmortem),
    FOREIGN KEY (cod_ticket_post) REFERENCES Ticket_postmortem(cod_ticket_post)
);

-- Tabla Analisis_post
CREATE TABLE Analisis_post (
    cod_analisis_post SERIAL not NULL,
    Aprobacion_analisis VARCHAR(50) not NULL,
    fecha_inicio_post DATE not NULL,
    fecha_fin_post DATE not NULL,
    cod_postmortem INTEGER not NULL,
    primary key (cod_analisis_post),
    FOREIGN KEY (cod_postmortem) REFERENCES Postmortem_detalles(cod_postmortem)
);

-- Tabla Leccion
CREATE TABLE Leccion (
    cod_leccion SERIAL not NULL,
    Leccion VARCHAR(255) not NULL,
    cod_analisis_post INTEGER not NULL,
    primary key (cod_leccion),
    FOREIGN KEY (cod_analisis_post) REFERENCES Analisis_post(cod_analisis_post)
);

-- Tabla Categoria_Accion
CREATE TABLE Categoria_Accion (
    cod_cat_accion SERIAL not NULL,
    cat_accion VARCHAR(15) not NULL,
    cod_accion INTEGER not NULL,
    primary key (cod_cat_accion)
    
);

-- Tabla Accion
CREATE TABLE Accion (
    cod_accion SERIAL not NULL,
    accion VARCHAR(255) not NULL,
    cod_analisis_post INTEGER not NULL,
    cod_cat_accion INTEGER not null,
    primary key (cod_accion),
    FOREIGN KEY (cod_analisis_post) REFERENCES Analisis_post(cod_analisis_post),
    foreign key (cod_cat_accion) references Categoria_Accion(cod_cat_accion)
);


-- Tabla Incidente
CREATE TABLE Incidente (
    cod_incidente SERIAL not NULL,
    mensaje_incidente VARCHAR(100) not NULL,
    categoria VARCHAR(20) not NULL,
    cod_ticket_inc INTEGER not NULL,
    PRIMARY key (cod_incidente),
    FOREIGN KEY (cod_ticket_inc) REFERENCES Ticket_incidente(cod_ticket_inc)
);
-- Tabla Proveedor
CREATE TABLE Proveedor (
    cod_proveedor SERIAL not NULL,
    Nombre_proveedor VARCHAR(20) not NULL,
    SLA VARCHAR(50) not NULL,
    cod_diag INTEGER not NULL,
    PRIMARY key (cod_proveedor)
);

-- Tabla Causa
CREATE TABLE Causa (
    cod_causa SERIAL not NULL,
    Causa VARCHAR(255) not NULL,
    cod_diag INTEGER not null,
    primary key (cod_causa)
);

-- Tabla Diagnostico
CREATE TABLE Diagnostico (
    cod_diag SERIAL not NULL,
    comentario VARCHAR(200) not NULL,
    fecha_realizacion DATE not NULL,
    cod_incidente INTEGER not NULL,
    ID_empleado INTEGER not NULL,
    cod_proveedor INTEGER not null,
    cod_causa INTEGER not null,
    primary key (cod_diag),
    FOREIGN KEY (cod_incidente) REFERENCES Incidente(cod_incidente),
    FOREIGN key (ID_empleado) REFERENCES Empleado(ID_empleado),
    foreign key (cod_proveedor) references Proveedor(cod_proveedor),
    foreign key (cod_causa) references Causa(cod_causa)
);


-- Tabla Solucion
CREATE TABLE Solucion (
  					    cod_solucion SERIAL not NULL,
    					estado_solucion VARCHAR(50) not NULL,
					    test VARCHAR(50) NOT NULL,
    					cod_diag INTEGER not NULL,
    					primary key (cod_solucion),
					    FOREIGN KEY (cod_diag) REFERENCES Diagnostico(cod_diag)
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



CREATE TABLE Audiencia (
	id_audiencia varchar (20) primary key, 
    Edad_rango varchar (20) NOT NULL,
    Genero VARCHAR(2) NOT NULL,
    Ubicacion VARCHAR(50) NOT NULL
);

CREATE TABLE Prototipo (
	cod_prototipo SERIAL NOT NULL,
	nombre_prot VARCHAR(255) not null,
	Descripcion Varchar(255) not null,
	Prop_presupuesto NUMERIC NOT NULL,
	Fecha_creacion timestamp not null,
	Fecha_estado timestamp,
	Prop_audiencia varchar (255) not null,
	ID_empleado INTEGER NOT NULL,
	cod_est_prot INTEGER,
	PRIMARY KEY (cod_prototipo),
	FOREIGN KEY (ID_empleado) REFERENCES Empleado(ID_empleado),
	FOREIGN KEY (cod_est_prot) REFERENCES Estado_prototipo(cod_est_prot),
	FOREIGN KEY (Prop_audiencia) REFERENCES Audiencia(id_audiencia)

);

CREATE TABLE Canal (
    cod_canal SERIAL PRIMARY KEY,
    nombre_canal VARCHAR(255) NOT NULL,
    descripcion varchar(255) not null
);

create table prototipoxcanal (
	cod_prototipo INTEGER not null,
	cod_canal INTEGER not null,
	impresiones numeric default null,
    clics numeric default null,
    conversiones numeric default null, 
	FOREIGN KEY (cod_prototipo) REFERENCES Prototipo(cod_prototipo),
	FOREIGN KEY (cod_canal) REFERENCES Canal(cod_canal)
); 

CREATE TABLE Objetivos (
    Id_objetivo SERIAL PRIMARY KEY,
    Descripcion VARCHAR (255),
    cod_prototipo INT,
    FOREIGN KEY (cod_prototipo) REFERENCES Prototipo(cod_prototipo)
);


CREATE TABLE Tipo_recurso (
    id_tipo_recurso SERIAL PRIMARY KEY,
    nombre_tipo VARCHAR(50) NOT null
);


CREATE TABLE Recursos (
    id_recurso_prot SERIAL PRIMARY KEY,
    nombre_recurso VARCHAR(255) default null,
    url_recurso VARCHAR(255) default null,
    id_tipo_recurso INTEGER default null,
    cod_prototipo INTEGER not null,
    FOREIGN KEY (id_tipo_recurso) REFERENCES Tipo_recurso(id_tipo_recurso),
    FOREIGN KEY (cod_prototipo) REFERENCES Prototipo(cod_prototipo)
);

CREATE TABLE Campana_Publicitaria (
	cod_campana SERIAL NOT NULL,
    Resultado TEXT,
   	Presupuesto NUMERIC NOT NULL,
	Nombre_campana VARCHAR(255) NOT NULL,
	Audiencia Varchar(50) NOT NULL,
	fecha_publicacion timestamp,
	fecha_finalizacion timestamp,
	cod_promocion INTEGER NOT NULL,
	cod_prototipo INTEGER NOT NULL,
	PRIMARY KEY (cod_campana),
	FOREIGN KEY (cod_promocion) REFERENCES Promocion(cod_promocion),
	FOREIGN KEY (cod_prototipo) REFERENCES Prototipo(cod_prototipo)
);


CREATE TABLE Pre_test (
	id_pretest SERIAL NOT null ,
	AudienciaA varchar(255) NOT NULL,
	AudienciaB varchar(255),
	Fecha_inicio timestamp not null,
	Fecha_fin timestamp,
	Resultado varchar (255),
	cod_prototipo INTEGER NOT NULL,
	id_empleado Integer not null,
	PRIMARY KEY (id_pretest),
	FOREIGN KEY (cod_prototipo) REFERENCES Prototipo(cod_prototipo),
	foreign key (AudienciaB)references audiencia(id_audiencia),
	foreign key (Resultado) references audiencia(id_audiencia),
	foreign key (id_empleado) references Empleado(id_empleado)
);

CREATE TABLE Estado_ticket_asig (
    id_estado SERIAL PRIMARY KEY,
    nombre_estado estado_enum NOT NULL 
);

CREATE TABLE Ticket_asig_tip (
                                 cod_ticket_asig SERIAL NOT NULL,
                                 id_conv INTEGER NOT NULL,
                                 problema_ident VARCHAR(250) NOT NULL,
                                 fecha_asig TIMESTAMP NOT NULL,
                                 comentario VARCHAR(250) NOT NULL,
                                 id_estado INTEGER NOT NULL,
                                 PRIMARY KEY (cod_ticket_asig),
                                 FOREIGN KEY (id_estado) REFERENCES Estado_ticket_asig(id_estado),
                                 FOREIGN KEY (cod_ticket_asig) REFERENCES Ticket_general(cod_ticket)
);


CREATE TABLE Notificacion (
                              id_noti SERIAL NOT NULL,
                              fecha_envio TIMESTAMP NOT NULL,
                              asunto VARCHAR(100) NOT NULL,
                              mensaje VARCHAR(250) NOT NULL,
                              aprobacion aprobacion_enum NOT NULL,
                              cod_ticket_asig INTEGER NOT NULL,
                              PRIMARY KEY (id_noti),
                              FOREIGN KEY (cod_ticket_asig) REFERENCES Ticket_asig_tip(cod_ticket_asig)
);

CREATE TABLE tipificacion (
                              cod_etiqueta SERIAL NOT NULL,
                              funcionalidad VARCHAR(200),
                              tipologia VARCHAR(100),
                              fecha_creacion TIMESTAMP NOT NULL,
                              categoria CHAR(10),
                              descripción VARCHAR(500) NOT NULL,
                              id_departamento INTEGER NOT NULL,
                              cod_ticket_asig INTEGER NOT NULL,
                              PRIMARY KEY (cod_etiqueta),
                              FOREIGN KEY (cod_ticket_asig) REFERENCES Ticket_asig_tip(cod_ticket_asig),
                              FOREIGN KEY (id_departamento) REFERENCES Departamento(id_departamento)
);

CREATE table asignacion (
	id_asig SERIAL not null,
	fecha_asig TIMESTAMP not null,
	id_noti INTEGER not null,
	cod_etiqueta INTEGER not null,
	ID_empleado INTEGER not null,
	primary key(id_asig),
	FOREIGN KEY (id_noti) REFERENCES Notificacion(id_noti),
	FOREIGN KEY (cod_etiqueta) REFERENCES tipificacion(cod_etiqueta),
    FOREIGN KEY (ID_empleado) REFERENCES Empleado(ID_empleado)
	
);
create table cliente_externo (
	id_cliente SERIAL not null,
	direccion VARCHAR(100) not null,
	nombre_cli VARCHAR(50) not null,
	fecha_registro TIMESTAMP not null,
	apellido_cli VARCHAR(50) not null,
	empresa_cli VARCHAR(50),
	correo_cli VARCHAR(20) not null,
	primary key (id_cliente)
);
create table telefono (
	id_telefono_cli SERIAL not null,
	telefono CHAR(9) not null,
	id_cliente INTEGER not null,
	primary key(id_telefono_cli), 
	foreign key (id_cliente) references cliente_externo (id_cliente)
);
create table conversacion (
	id_conv SERIAL not null,
	estado_conv estado_conv_enum not null,
	tema VARCHAR(100) not null,
	fecha_inicio TIMESTAMP not null,
	fecha_fin TIMESTAMP not null,
	id_cliente INTEGER not null,
	ID_empleado INTEGER not NULL,
	cod_ticket_asig INTEGER not null,
	primary key(id_conv),
	foreign key (ID_empleado) references Empleado (ID_empleado),
	foreign key (id_cliente) references cliente_externo (id_cliente),
	foreign key (cod_ticket_asig) references Ticket_asig_tip (cod_ticket_asig)
);

CREATE TABLE mensaje (
    id_mensaje SERIAL NOT NULL,
    contenido TEXT NOT NULL,  
    fecha_envio TIMESTAMP NOT NULL,  
    id_conv INTEGER NOT NULL,  
    remitente INTEGER NOT NULL,
    tipo_remitente VARCHAR(10) NOT NULL CHECK (tipo_remitente IN ('Cliente', 'Asesor')),
    PRIMARY KEY (id_mensaje),
    FOREIGN KEY (id_conv) REFERENCES conversacion(id_conv)
);

CREATE TABLE Capacitacion
(
    ID_capacitacion SERIAL NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_final DATE NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    estado estado_capacitacion NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    ID_instructor INTEGER NOT NULL,
    PRIMARY KEY (ID_capacitacion),
    FOREIGN KEY (ID_instructor) REFERENCES Empleado(ID_empleado)
);

CREATE TABLE empleadoxcapacitacion
(
    id_empleadoxcapacitacion SERIAL NOT NULL,
    estado estado_empleado_capacitacion NOT NULL,
    resultado INTEGER,
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
    estado estado_modulo NOT NULL,
    ID_capacitacion INTEGER NOT NULL,
    PRIMARY KEY (ID_modulo),
    FOREIGN KEY (ID_capacitacion) REFERENCES Capacitacion(ID_capacitacion)
);

CREATE TABLE Test
(
    ID_test SERIAL NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    fecha DATE NOT NULL,
    estado estado_test NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    ID_modulo INTEGER NOT NULL,
    PRIMARY KEY (ID_test),
    FOREIGN KEY (ID_modulo) REFERENCES Modulo(ID_modulo)
);

CREATE TABLE Recurso
(
    ID_recurso SERIAL NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    contenido VARCHAR(200) NOT NULL,
    tipo tipo_recurso_modulo NOT NULL,-- Usando ENUM para tipo de recurso
    orden INTEGER NOT NULL,
    estado estado_modulo NOT NULL,
    ID_modulo INTEGER NOT NULL,
    PRIMARY KEY (ID_recurso),
    FOREIGN KEY (ID_modulo) REFERENCES Modulo(ID_modulo)
);

CREATE TABLE Pregunta
(
    ID_pregunta SERIAL NOT NULL,
    titulo VARCHAR(200) NOT NULL,
    tipo tipo_pregunta NOT NULL,
    ID_test INTEGER NOT NULL,
    PRIMARY KEY (ID_pregunta),
    FOREIGN KEY (ID_test) REFERENCES Test(ID_test)
);

CREATE TABLE Resultado_Test
(
    ID_resultadotest SERIAL NOT NULL,
    puntaje INTEGER NOT NULL, -- Resultado del test (por ejemplo, puntaje o porcentaje)
    comentario VARCHAR(200),  -- Comentario opcional sobre el desempeño del empleado
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
    contenido VARCHAR(200) NOT NULL,
    es_correcta valor NOT NULL,
    ID_pregunta INTEGER NOT NULL,
    PRIMARY KEY (ID_alternativa),
    FOREIGN KEY (ID_pregunta) REFERENCES Pregunta(ID_pregunta)
);

CREATE TABLE SolicitudCapacitacion
(
    ID_solicitud_cap SERIAL NOT NULL,
    fecha_solicitud DATE NOT NULL,
    estado estado_solicitud_capacitacion NOT NULL,
    motivo VARCHAR(50) NOT NULL,
    comentario VARCHAR(200) NOT NULL,
    fecha_aprobacion DATE,
    ID_empleado INTEGER NOT NULL,
    PRIMARY KEY (ID_solicitud_cap),
    FOREIGN KEY (ID_empleado) REFERENCES Empleado(ID_empleado)
);


CREATE TABLE seller (
	cod_seller SERIAL NOT NULL, 
	nombre_seller VARCHAR (50),
	rubro VARCHAR (50),
	correo VARCHAR (50),
	telefono NUMERIC (9),
	ruc NUMERIC (11),
	PRIMARY KEY (cod_seller)
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
    FOREIGN KEY (cod_tipo_producto) REFERENCES Tipo_poducto(cod_tipo_producto)
);


CREATE TABLE promocion (
	cod_promocion SERIAL  NOT NULL, 
	fecha_inicio DATE,
	fecha_fin DATE,
	dscto NUMERIC (5,2),
	estado_promo BOOLEAN default FALSE,
	dscrip_promo VARCHAR (250),
	ID_empleado INTEGER,
	PRIMARY KEY(cod_promocion),
	FOREIGN KEY(ID_empleado) REFERENCES Empleado(ID_empleado) 
);


CREATE TABLE productoxseller (
    cod_productoxseller SERIAL NOT NULL,
    cod_producto INTEGER NOT NULL,
    cod_seller INTEGER NOT NULL,
    PRIMARY KEY (cod_productoxseller),
    FOREIGN KEY (cod_producto) REFERENCES Producto(cod_producto),
    FOREIGN KEY (cod_seller) REFERENCES Seller(cod_seller)
);

CREATE TABLE promocionxproducto (
    cod_promocionxproducto SERIAL NOT NULL,
	cod_promocion INTEGER NOT NULL,
    cod_producto INTEGER NOT NULL,
    PRIMARY KEY (cod_promocionxproducto),
    FOREIGN KEY (cod_promocion) REFERENCES Promocion(cod_promocion),
    FOREIGN KEY (cod_producto) REFERENCES Producto(cod_producto)
);
CREATE TABLE Prioridad_objetivo (
    ID_Prioridad_objetivo SERIAL PRIMARY KEY,
    nombre estado_prioridad_enum NOT NULL,
    peso INT NOT NULL
);

CREATE TABLE Objetivo (
    ID_objetivo SERIAL PRIMARY KEY,
    descripcion TEXT NOT NULL,
    estado estado_objetivo_enum NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    ID_empleado INT REFERENCES Empleado(ID_empleado),
    ID_Prioridad_objetivo INT REFERENCES Prioridad_objetivo(ID_Prioridad_objetivo)
);

CREATE TABLE Evaluacion (
    ID_evaluacion SERIAL PRIMARY KEY,
    estado estado_evaluacion_enum NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    puntaje INT NOT NULL,
    retroalimentacion TEXT,
    ID_empleado INT REFERENCES Empleado(ID_empleado)
);

CREATE TABLE Evaluacion_objetivo (
    ID_evaluacion_objetivo SERIAL PRIMARY KEY,
    estado estado_eval_objetivo_enum NOT NULL,
    ID_objetivo INT REFERENCES Objetivo(ID_objetivo),
    ID_evaluacion INT REFERENCES Evaluacion(ID_evaluacion)
);

CREATE TABLE Asistencia (
    ID_Asistencia SERIAL PRIMARY KEY,
    estado estado_asistencia_enum NOT NULL DEFAULT 'Presente',
    fecha DATE NOT NULL,
    hora_entrada TIME,
    hora_salida TIME,
    ID_empleado INT REFERENCES Empleado(ID_empleado)
);

CREATE TABLE Tipo_permiso (
    ID_tipo_permiso SERIAL PRIMARY KEY,
    nombre tipo_permiso_enum NOT NULL
);

CREATE TABLE Permiso (
    ID_permiso SERIAL PRIMARY KEY,
    fecha_inicio DATE NOT NULL,
    fecha_final DATE NOT NULL,
    estado estado_permiso_enum NOT NULL DEFAULT 'Pendiente',
    comentario TEXT, -- Justificación del empleado
    comentario_admin TEXT, -- Comentario del administrador en caso de rechazo
    ID_empleado INT REFERENCES Empleado(ID_empleado),
    ID_tipo_permiso INT REFERENCES Tipo_permiso(ID_tipo_permiso),
    fecha_cambio_estado TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Fecha de cambio de estado
);
CREATE TABLE EvidenciaPermiso (
    ID_evidencia SERIAL PRIMARY KEY,
    nombre_archivo VARCHAR(255) NOT NULL,
    ruta_archivo TEXT NOT NULL, -- Ruta o URL donde se almacena el archivo
    fecha_subida TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ID_permiso INT REFERENCES Permiso(ID_permiso)
);

CREATE TABLE Departamento (
                              id_departamento SERIAL NOT NULL,
                              descripcion VARCHAR(100) NOT NULL,
                              id_departamento_padre INTEGER,
                              PRIMARY KEY (id_departamento),
                              FOREIGN KEY (id_departamento_padre) REFERENCES Departamento(id_departamento)
);

CREATE TABLE Funcion (
                         id_funcion SERIAL PRIMARY KEY,
                         nombre VARCHAR(50) NOT NULL,
                         descripcion VARCHAR(250) NOT NULL
);

CREATE TABLE Puesto (
                        id_puesto SERIAL PRIMARY KEY,
                        nombre VARCHAR(50) NOT NULL,
                        paga FLOAT NOT NULL,
                        id_departamento INTEGER NOT NULL,
                        FOREIGN KEY (id_departamento) REFERENCES Departamento(id_departamento)
);

CREATE TABLE Puesto_Funcion (
                                id_puesto INT REFERENCES Puesto(id_puesto),
                                id_funcion INT REFERENCES Funcion(id_funcion),
                                PRIMARY KEY (id_puesto, id_funcion)
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

CREATE TABLE Vacante (
                         id_vacante SERIAL NOT NULL,
                         estado VARCHAR(50) CHECK (estado IN ('Abierta', 'Cerrada', 'Vencida')) NOT NULL,
                         fecha_fin DATE NOT NULL,
                         fecha_inicio DATE NOT NULL,
                         comentario VARCHAR(100) NOT NULL,
                         id_puesto INTEGER NOT NULL,
                         cantidad INTEGER NOT NULL,
                         PRIMARY KEY (id_vacante),
                         FOREIGN KEY (id_puesto) REFERENCES Puesto(id_puesto)
);

CREATE TABLE Convocatoria (
                              id_convocatoria SERIAL PRIMARY KEY,
                              id_vacante INT NOT NULL,
                              medio_publicacion VARCHAR(25) NOT NULL,
                              fecha_inicio DATE NOT NULL,
                              fecha_fin DATE NOT NULL,
                              estado VARCHAR(50) CHECK (estado IN ('Abierta', 'Cerrada', 'En proceso')),
                              FOREIGN KEY (id_vacante) REFERENCES Vacante(id_vacante)
);

CREATE TABLE Postulante (
                            id_postulante SERIAL NOT NULL,
                            nombre VARCHAR(255) NOT NULL,
                            telefono BIGINT NOT NULL,
                            correo VARCHAR(255) NOT NULL,
                            id_vacante INTEGER NOT NULL,
                            puntaje INTEGER NOT NULL,
                            PRIMARY KEY (id_postulante),
                            FOREIGN KEY (id_vacante) REFERENCES Vacante(id_vacante)
);

CREATE TABLE Educacion (
                           id_educacion SERIAL PRIMARY KEY,
                           id_postulante INT REFERENCES Postulante(id_postulante),
                           institucion VARCHAR(100),
                           titulo VARCHAR(100),
                           fecha_inicio DATE,
                           fecha_fin DATE,
                           en_curso BOOLEAN
);

CREATE TABLE Experiencia_Laboral (
                                     id_experiencia SERIAL PRIMARY KEY,
                                     id_postulante INT REFERENCES Postulante(id_postulante),
                                     empresa VARCHAR(100),
                                     puesto VARCHAR(100),
                                     fecha_inicio DATE,
                                     fecha_fin DATE
);

CREATE TABLE Habilidad_Experiencia (
                                       id_habilidad_experiencia SERIAL PRIMARY KEY,
                                       nombre VARCHAR(50),
                                       id_experiencia INT REFERENCES Experiencia_Laboral(id_experiencia),
                                       nivel VARCHAR(20) CHECK (nivel IN ('Básico', 'Intermedio', 'Avanzado'))
);

CREATE TABLE Idioma (
                        id_idioma SERIAL PRIMARY KEY,
                        nombre VARCHAR(50) NOT NULL
);

CREATE TABLE Idioma_Postulante (
                                   id_postulante INT REFERENCES Postulante(id_postulante),
                                   id_idioma INT REFERENCES Idioma(id_idioma),
                                   nivel VARCHAR(20) CHECK (nivel IN ('Básico', 'Intermedio', 'Avanzado')),
                                   PRIMARY KEY (id_postulante, id_idioma)
);

CREATE TABLE Habilidad_Postulante (
                                      id_habilidad_postulante SERIAL PRIMARY KEY,
                                      id_postulante INT REFERENCES Postulante(id_postulante),
                                      nombre VARCHAR(50),
                                      nivel VARCHAR(20) CHECK (nivel IN ('Básico', 'Intermedio', 'Avanzado'))
);

CREATE TABLE Beneficio (
                           id_beneficio SERIAL PRIMARY KEY,
                           descripcion TEXT NOT NULL
);

CREATE TABLE Oferta_Laboral (
                                id_oferta SERIAL PRIMARY KEY,
                                id_postulante INT REFERENCES Postulante(id_postulante),
                                id_vacante INT REFERENCES Vacante(id_vacante),
                                fecha_oferta DATE,
                                fecha_inicio_propuesta DATE,
                                link_documento_legal_sin_firma TEXT,
                                link_documento_legal_con_firma TEXT,
                                estado VARCHAR(20) CHECK (estado IN ('Pendiente', 'Aceptada', 'Rechazada'))
);

CREATE TABLE Oferta_Laboral_Beneficio (
                                          id_oferta INT REFERENCES Oferta_Laboral(id_oferta),
                                          id_beneficio INT REFERENCES Beneficio(id_beneficio),
                                          PRIMARY KEY (id_oferta, id_beneficio)
);

CREATE TABLE Entrevista (
                            id_entrevista SERIAL NOT NULL,
                            estado VARCHAR(50) NOT NULL CHECK (estado IN ('Pendiente', 'Hecha', 'Rechazada')),
                            fecha DATE NOT NULL,
                            puntaje_general INTEGER NOT NULL,
                            id_postulante INTEGER NOT NULL,
                            ID_empleado INTEGER NOT NULL,
                            tipo_entrevista VARCHAR(50) NOT NULL,
                            PRIMARY KEY (id_entrevista),
                            FOREIGN KEY (id_postulante) REFERENCES Postulante(id_postulante),
                            FOREIGN KEY (ID_empleado) REFERENCES Empleado(ID_empleado)
);

CREATE TABLE Observacion (
                             id_observacion SERIAL PRIMARY KEY,
                             id_entrevista INT REFERENCES Entrevista(id_entrevista),
                             nombre VARCHAR(50) NOT NULL,
                             descripcion TEXT NOT NULL
);
```sql
