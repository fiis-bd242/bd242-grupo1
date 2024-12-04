CREATE TABLE Fact_Tickets (
    cod_ticket BIGINT,
    id_cliente BIGINT NOT NULL,
    id_empleado BIGINT NOT NULL,
    id_etiqueta BIGINT NOT NULL,
    tiempo_resolucion DOUBLE,  -- en horas
    fecha_inicio DATETIME NOT NULL,
    fecha_fin DATETIME,
    volumen_ticket INT
) ENGINE=OLAP
DISTRIBUTED BY HASH(cod_ticket) BUCKETS 10
PROPERTIES (
    "replication_num" = "1"
);

CREATE TABLE Dim_Cliente (
    id_cliente BIGINT,
    nombre_cliente VARCHAR(50) NOT NULL,
    empresa_cliente VARCHAR(50),
    fecha_registro DATETIME NOT NULL
) ENGINE=OLAP
DISTRIBUTED BY HASH(id_cliente) BUCKETS 10
PROPERTIES (
    "replication_num" = "1"
);

CREATE TABLE Dim_Empleado (
    id_empleado BIGINT,
    nombre_empleado VARCHAR(50) NOT NULL,
    puesto_empleado VARCHAR(50),
    fecha_ingreso DATETIME,
    estado_empleado VARCHAR(50)
) ENGINE=OLAP
DISTRIBUTED BY HASH(id_empleado) BUCKETS 10
PROPERTIES (
    "replication_num" = "1"
);

CREATE TABLE Dim_Etiqueta (
    cod_etiqueta BIGINT,
    categoria VARCHAR(50),
    funcionalidad VARCHAR(100),
    motivo VARCHAR(250),
    descripcion VARCHAR(500)
) ENGINE=OLAP
DISTRIBUTED BY HASH(cod_etiqueta) BUCKETS 10
PROPERTIES (
    "replication_num" = "1"
);

CREATE TABLE Dim_Tiempo (
    id_tiempo BIGINT,
    fecha DATE NOT NULL,
    mes INT,
    trimestre INT,
    semestre INT,
    anio INT
) ENGINE=OLAP
DISTRIBUTED BY HASH(id_tiempo) BUCKETS 10
PROPERTIES (
    "replication_num" = "1"
);

SELECT * from Fact_Tickets ft ;
INSERT INTO Fact_Tickets (cod_ticket, id_cliente, id_empleado, id_etiqueta, tiempo_resolucion, fecha_inicio, fecha_fin, volumen_ticket) VALUES
(1, 1, 7, 1, 0.5, '2023-02-01 10:00:00', '2023-02-01 10:30:00', 5),
(2, 2, 3, 2, 0.33, '2023-03-05 11:00:00', '2023-03-05 11:20:00', 4),
(3, 3, 6, 3, 0.75, '2023-04-10 12:00:00', '2023-04-10 12:45:00', 3),
(4, 4, 11, 4, 0.5, '2023-05-15 14:00:00', '2023-05-15 14:30:00', 2),
(5, 5, 10, 5, 0.5, '2023-06-20 15:00:00', '2023-06-20 15:30:00', 6),
(6, 6, 9, 6, 0.5, '2023-07-25 16:00:00', '2023-07-25 16:30:00', 7),
(7, 7, 8, 7, 0.75, '2023-08-30 17:00:00', '2023-08-30 17:45:00', 8),
(8, 8, 5, 8, 0.33, '2023-09-05 18:00:00', '2023-09-05 18:20:00', 9),
(9, 9, 4, 9, 0.5, '2023-10-10 19:00:00', '2023-10-10 19:30:00', 6),
(10, 10, 2, 10, 0.75, '2023-11-15 20:00:00', '2023-11-15 20:30:00', 5);

INSERT INTO Dim_Cliente (id_cliente, nombre_cliente, empresa_cliente, fecha_registro) VALUES
(1, 'Mario', 'InnovaTech', '2023-02-15'),
(2, 'Laura', 'FinTech SA', '2023-03-20'),
(3, 'Diego', 'BancaMia', '2023-04-25'),
(4, 'Karla', 'Seguros Plus', '2023-05-30'),
(5, 'Fernando', 'MercadoLibre', '2023-06-15'),
(6, 'Monica', 'TechSmart', '2023-07-10'),
(7, 'Luis', 'BBVA', '2023-08-05'),
(8, 'Sandra', 'Grupo RPP', '2023-09-12'),
(9, 'Oscar', 'Yape', '2023-10-20'),
(10, 'Paola', 'Credicorp', '2023-11-25');

INSERT INTO Dim_Empleado (id_empleado, nombre_empleado, puesto_empleado, fecha_ingreso, estado_empleado) VALUES
(1, 'Juan Perez', 'Asesor', '2022-01-10', 'activo'),
(2, 'Ana Torres', 'Business', '2021-05-22', 'activo'),
(3, 'Carlos Gomez', 'Asesor', '2023-03-01', 'activo'),
(4, 'Luis Alvarez', 'Business', '2020-08-15', 'activo'),
(5, 'Sofia Rojas', 'Analista', '2019-10-05', 'inactivo'),
(6, 'Ricardo Herrera', 'Asesor', '2022-12-01', 'activo'),
(7, 'Maria Lopez', 'Analista', '2021-06-10', 'activo'),
(8, 'Pedro Ruiz', 'Analista', '2023-04-12', 'activo'),
(9, 'Jorge Fernandez', 'Asesor', '2020-09-30', 'activo'),
(10, 'Elena Gutierrez', 'Asesor', '2023-05-20', 'activo');

INSERT INTO Dim_Etiqueta (cod_etiqueta, categoria, funcionalidad, motivo, descripcion) VALUES
(1, 'Consulta', 'Autenticación', 'activación biometría digital', 'Consulta sobre activación de biometría digital.'),
(2, 'Queja', 'Yape POS', 'actualización de información', 'Queja sobre actualización de datos.'),
(3, 'Consulta', 'Ampliación de límite', 'ampliación 2k', 'Consulta para ampliar el límite de yapeo.'),
(4, 'Queja', 'Dinero Más Seguro', 'reducción de límite yapeo', 'Queja por reducción de límite de yapeo.'),
(5, 'Consulta', 'Yape Promos', 'activación NFC', 'Consulta para activar NFC.'),
(6, 'Consulta', 'Creditos Monocuotas', 'ampliación 950', 'Consulta sobre ampliación a 950.'),
(7, 'Queja', 'Autenticación', 'problema ampliación límite yapeo', 'Problema en ampliación de límite de yapeo.');

INSERT INTO Dim_Tiempo (id_tiempo, fecha, mes, trimestre, semestre, anio) VALUES
(1, '2023-02-01', 2, 1, 1, 2023),
(2, '2023-03-05', 3, 1, 1, 2023),
(3, '2023-04-10', 4, 2, 1, 2023),
(4, '2023-05-15', 5, 2, 1, 2023),
(5, '2023-06-20', 6, 2, 1, 2023),
(6, '2023-07-25', 7, 3, 1, 2023),
(7, '2023-08-30', 8, 3, 2, 2023),
(8, '2023-09-05', 9, 3, 2, 2023),
(9, '2023-10-10', 10, 4, 2, 2023),
(10, '2023-11-15', 11, 4, 2, 2023);


-- tiempo de resolución por etiqueta

SELECT 
    d.cod_etiqueta, 
    AVG(f.tiempo_resolucion) AS tiempo_resolucion_promedio, 
    d.categoria,
    d.funcionalidad,
    d.motivo
FROM 
    Fact_Tickets f
JOIN 
    Dim_Etiqueta d ON f.id_etiqueta = d.cod_etiqueta
WHERE 
    f.fecha_inicio BETWEEN 
        CASE 
            WHEN 'anual' = 'anual' THEN STR_TO_DATE(CONCAT(2023, '-01-01'), '%Y-%m-%d')
            WHEN 'semestral' = 'semestral' AND 1 = 1 THEN STR_TO_DATE(CONCAT(2023, '-01-01'), '%Y-%m-%d')
            WHEN 'trimestral' = 'trimestral' AND 2 = 2 THEN STR_TO_DATE(CONCAT(2023, '-04-01'), '%Y-%m-%d')
            WHEN 'mensual' = 'mensual' THEN STR_TO_DATE(CONCAT(2023, '-02-01'), '%Y-%m-%d')
            ELSE NULL 
        END 
    AND 
    f.fecha_inicio <= '2023-12-31'
GROUP BY 
    d.cod_etiqueta, d.categoria, d.funcionalidad, d.motivo;

   
 -- Volumen tipificacion general
SELECT
    COUNT(f.cod_ticket) AS volumen_tickets,
    CASE 
        WHEN 'mes' = 'mes' THEN dt.mes
        WHEN 'trimestre' = 'trimestre' THEN dt.trimestre
        WHEN 'semestre' = 'semestre' THEN dt.semestre
        ELSE dt.anio
    END AS periodo
FROM
    Fact_Tickets f
JOIN
    Dim_Tiempo dt ON f.fecha_inicio = dt.fecha
GROUP BY
    periodo;
SELECT * FROM Fact_Tickets f ;
SELECT * FROM Dim_Tiempo dt ;
SELECT
    COUNT(f.cod_ticket) AS volumen_tickets,
    dt.anio AS periodo
FROM Fact_Tickets f
JOIN Dim_Tiempo dt ON f.fecha_inicio = dt.fecha
WHERE f.fecha_inicio BETWEEN '2023-01-01' AND '2023-12-31'
GROUP BY dt.anio


SELECT COUNT(f.cod_ticket) AS volumen_tickets,
       dt.anio AS periodo
FROM Fact_Tickets f
JOIN Dim_Tiempo dt ON DATE(f.fecha_inicio) = dt.fecha
WHERE f.fecha_inicio BETWEEN'2023-01-01' AND '2023-12-31'
GROUP BY dt.anio





-- Ticket detallados

SELECT
    d.categoria,
    d.funcionalidad,
    d.motivo,
    COUNT(f.cod_ticket) AS volumen_tickets,
    CASE 
        WHEN 'mes' = 'mes' THEN dt.mes
        WHEN 'trimestre' = 'trimestre' THEN dt.trimestre
        WHEN 'semestre' = 'semestre' THEN dt.semestre
        ELSE dt.anio
    END AS periodo
FROM
    Fact_Tickets f
JOIN
    Dim_Etiqueta d ON f.id_etiqueta = d.cod_etiqueta
JOIN
    Dim_Tiempo dt ON f.fecha_inicio = dt.fecha
GROUP BY
    d.categoria, d.funcionalidad, d.motivo, periodo;


 -- Ticket atendidos 
SELECT
    c.empresa_cliente,
    COUNT(DISTINCT f.id_cliente) AS clientes_activos,
    CASE 
        WHEN 'mes' = 'mes' THEN dt.mes
        WHEN 'trimestre' = 'trimestre' THEN dt.trimestre
        WHEN 'semestre' = 'semestre' THEN dt.semestre
        ELSE dt.anio
    END AS periodo
FROM
    Fact_Tickets f
JOIN
    Dim_Cliente c ON f.id_cliente = c.id_cliente
JOIN
    Dim_Tiempo dt ON f.fecha_inicio = dt.fecha
GROUP BY
    c.empresa_cliente, periodo;



-- Cliente mas activos por empresa
SELECT
    c.empresa_cliente,
    COUNT(DISTINCT f.id_cliente) AS clientes_activos,
    CASE 
        WHEN 'mes' = 'mes' THEN dt.mes
        WHEN 'trimestre' = 'trimestre' THEN dt.trimestre
        WHEN 'semestre' = 'semestre' THEN dt.semestre
        ELSE dt.anio
    END AS periodo
FROM
    Fact_Tickets f
JOIN
    Dim_Cliente c ON f.id_cliente = c.id_cliente
JOIN
    Dim_Tiempo dt ON f.fecha_inicio = dt.fecha
GROUP BY
    c.empresa_cliente, periodo;


-- Ticket gestion por empleado

SELECT
    e.nombre_empleado,
    COUNT(f.cod_ticket) AS volumen_tickets_gestionados,
    CASE 
        WHEN 'mes' = 'mes' THEN dt.mes
        WHEN 'trimestre' = 'trimestre' THEN dt.trimestre
        WHEN 'semestre' = 'semestre' THEN dt.semestre
        ELSE dt.anio
    END AS periodo
FROM
    Fact_Tickets f
JOIN
    Dim_Empleado e ON f.id_empleado = e.id_empleado
JOIN
    Dim_Tiempo dt ON f.fecha_inicio = dt.fecha
GROUP BY
    e.nombre_empleado, periodo;













   
   