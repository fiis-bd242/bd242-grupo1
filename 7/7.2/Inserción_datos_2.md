-- Insert data into Categoria_Observacion
INSERT INTO Categoria_Observacion (nombre) VALUES
                                               ('Customer Service'),
                                               ('Technical Skills'),
                                               ('Communication'),
                                               ('Teamwork'),
                                               ('Problem Solving'),
                                               ('Leadership'),
                                               ('Time Management'),
                                               ('Adaptability'),
                                               ('Creativity'),
                                               ('Work Ethic'),
                                               ('Attention to Detail'),
                                               ('Interpersonal Skills'),
                                               ('Project Management'),
                                               ('Conflict Resolution'),
                                               ('Decision Making'),
                                               ('Critical Thinking'),
                                               ('Organizational Skills'),
                                               ('Analytical Skills'),
                                               ('Negotiation'),
                                               ('Stress Management');

-- Insert data into Feedback for interviews with estado 'Hecha'
INSERT INTO Feedback (fecha) VALUES
                                 ('2023-01-02'), ('2023-01-05'), ('2023-01-08'), ('2023-01-11'), ('2023-01-14'),
                                 ('2023-01-17'), ('2023-01-20'), ('2023-01-23'), ('2023-01-26'), ('2023-01-29'),
                                 ('2023-02-01'), ('2023-02-04'), ('2023-02-07'), ('2023-02-10'), ('2023-02-13'),
                                 ('2023-02-16'), ('2023-02-19'), ('2023-02-22'), ('2023-02-25'), ('2023-02-28'),
                                 ('2023-03-03'), ('2023-03-06'), ('2023-03-09'), ('2023-03-12'), ('2023-03-15'),
                                 ('2023-03-18'), ('2023-03-21'), ('2023-03-24'), ('2023-03-27'), ('2023-03-30'),
                                 ('2023-04-02'), ('2023-04-05'), ('2023-04-08'), ('2023-04-11'), ('2023-04-14'),
                                 ('2023-04-17'), ('2023-04-20'), ('2023-04-23'), ('2023-04-26'), ('2023-04-29'),
                                 ('2023-05-02'), ('2023-05-05'), ('2023-05-08'), ('2023-05-11'), ('2023-05-14'),
                                 ('2023-05-17'), ('2023-05-20'), ('2023-05-23'), ('2023-05-26'), ('2023-05-29'),
                                 ('2023-06-01'), ('2023-06-04'), ('2023-06-07'), ('2023-06-10'), ('2023-06-13'),
                                 ('2023-06-16'), ('2023-06-19'), ('2023-06-22'), ('2023-06-25'), ('2023-06-28'),
                                 ('2023-07-01'), ('2023-07-04'), ('2023-07-07'), ('2023-07-10'), ('2023-07-13'),
                                 ('2023-07-16'), ('2023-07-19'), ('2023-07-22'), ('2023-07-25'), ('2023-07-28'),
                                 ('2023-08-01'), ('2023-08-04'), ('2023-08-07'), ('2023-08-10'), ('2023-08-13'),
                                 ('2023-08-16'), ('2023-08-19'), ('2023-08-22'), ('2023-08-25'), ('2023-08-28'),
                                 ('2023-09-01'), ('2023-09-04'), ('2023-09-07'), ('2023-09-10'), ('2023-09-13'),
                                 ('2023-09-16'), ('2023-09-19'), ('2023-09-22'), ('2023-09-25'), ('2023-09-28'),
                                 ('2023-10-01'), ('2023-10-04'), ('2023-10-07'), ('2023-10-10'), ('2023-10-13'),
                                 ('2023-10-16'), ('2023-10-19'), ('2023-10-22'), ('2023-10-25'), ('2023-10-28');

-- Insert data into Observacion
INSERT INTO Observacion (id_feedback, id_categoria, descripcion) VALUES
                                                                     (1, 1, 'Customer Service feedback for interview 1'),
                                                                     (2, 2, 'Technical Skills feedback for interview 2'),
                                                                     (3, 3, 'Communication feedback for interview 3'),
                                                                     (4, 4, 'Teamwork feedback for interview 4'),
                                                                     (5, 5, 'Problem Solving feedback for interview 5'),
                                                                     (6, 6, 'Leadership feedback for interview 6'),
                                                                     (7, 7, 'Time Management feedback for interview 7'),
                                                                     (8, 8, 'Adaptability feedback for interview 8'),
                                                                     (9, 9, 'Creativity feedback for interview 9'),
                                                                     (10, 10, 'Work Ethic feedback for interview 10'),
                                                                     (11, 11, 'Attention to Detail feedback for interview 11'),
                                                                     (12, 12, 'Interpersonal Skills feedback for interview 12'),
                                                                     (13, 13, 'Project Management feedback for interview 13'),
                                                                     (14, 14, 'Conflict Resolution feedback for interview 14'),
                                                                     (15, 15, 'Decision Making feedback for interview 15'),
                                                                     (16, 16, 'Critical Thinking feedback for interview 16'),
                                                                     (17, 17, 'Organizational Skills feedback for interview 17'),
                                                                     (18, 18, 'Analytical Skills feedback for interview 18'),
                                                                     (19, 19, 'Negotiation feedback for interview 19'),
                                                                     (20, 20, 'Stress Management feedback for interview 20'),
                                                                     (21, 1, 'Customer Service feedback for interview 21'),
                                                                     (22, 2, 'Technical Skills feedback for interview 22'),
                                                                     (23, 3, 'Communication feedback for interview 23'),
                                                                     (24, 4, 'Teamwork feedback for interview 24'),
                                                                     (25, 5, 'Problem Solving feedback for interview 25'),
                                                                     (26, 6, 'Leadership feedback for interview 26'),
                                                                     (27, 7, 'Time Management feedback for interview 27'),
                                                                     (28, 8, 'Adaptability feedback for interview 28'),
                                                                     (29, 9, 'Creativity feedback for interview 29'),
                                                                     (30, 10, 'Work Ethic feedback for interview 30'),
                                                                     (31, 11, 'Attention to Detail feedback for interview 31'),
                                                                     (32, 12, 'Interpersonal Skills feedback for interview 32'),
                                                                     (33, 13, 'Project Management feedback for interview 33'),
                                                                     (34, 14, 'Conflict Resolution feedback for interview 34'),
                                                                     (35, 15, 'Decision Making feedback for interview 35'),
                                                                     (36, 16, 'Critical Thinking feedback for interview 36'),
                                                                     (37, 17, 'Organizational Skills feedback for interview 37'),
                                                                     (38, 18, 'Analytical Skills feedback for interview 38'),
                                                                     (39, 19, 'Negotiation feedback for interview 39'),
                                                                     (40, 20, 'Stress Management feedback for interview 40'),
                                                                     (41, 1, 'Customer Service feedback for interview 41'),
                                                                     (42, 2, 'Technical Skills feedback for interview 42'),
                                                                     (43, 3, 'Communication feedback for interview 43'),
                                                                     (44, 4, 'Teamwork feedback for interview 44'),
                                                                     (45, 5, 'Problem Solving feedback for interview 45'),
                                                                     (46, 6, 'Leadership feedback for interview 46'),
                                                                     (47, 7, 'Time Management feedback for interview 47'),
                                                                     (48, 8, 'Adaptability feedback for interview 48'),
                                                                     (49, 9, 'Creativity feedback for interview 49'),
                                                                     (50, 10, 'Work Ethic feedback for interview 50'),
                                                                     (51, 11, 'Attention to Detail feedback for interview 51'),
                                                                     (52, 12, 'Interpersonal Skills feedback for interview 52'),
                                                                     (53, 13, 'Project Management feedback for interview 53'),
                                                                     (54, 14, 'Conflict Resolution feedback for interview 54'),
                                                                     (55, 15, 'Decision Making feedback for interview 55'),
                                                                     (56, 16, 'Critical Thinking feedback for interview 56'),
                                                                     (57, 17, 'Organizational Skills feedback for interview 57'),
                                                                     (58, 18, 'Analytical Skills feedback for interview 58'),
                                                                     (59, 19, 'Negotiation feedback for interview 59'),
                                                                     (60, 20, 'Stress Management feedback for interview 60'),
                                                                     (61, 1, 'Customer Service feedback for interview 61'),
                                                                     (62, 2, 'Technical Skills feedback for interview 62'),
                                                                     (63, 3, 'Communication feedback for interview 63'),
                                                                     (64, 4, 'Teamwork feedback for interview 64'),
                                                                     (65, 5, 'Problem Solving feedback for interview 65'),
                                                                     (66, 6, 'Leadership feedback for interview 66'),
                                                                     (67, 7, 'Time Management feedback for interview 67'),
                                                                     (68, 8, 'Adaptability feedback for interview 68'),
                                                                     (69, 9, 'Creativity feedback for interview 69'),
                                                                     (70, 10, 'Work Ethic feedback for interview 70'),
                                                                     (71, 11, 'Attention to Detail feedback for interview 71'),
                                                                     (72, 12, 'Interpersonal Skills feedback for interview 72'),
                                                                     (73, 13, 'Project Management feedback for interview 73'),
                                                                     (74, 14, 'Conflict Resolution feedback for interview 74'),
                                                                     (75, 15, 'Decision Making feedback for interview 75'),
                                                                     (76, 16, 'Critical Thinking feedback for interview 76'),
                                                                     (77, 17, 'Organizational Skills feedback for interview 77'),
                                                                     (78, 18, 'Analytical Skills feedback for interview 78'),
                                                                     (79, 19, 'Negotiation feedback for interview 79'),
                                                                     (80, 20, 'Stress Management feedback for interview 80'),
                                                                     (81, 1, 'Customer Service feedback for interview 81'),
                                                                     (82, 2, 'Technical Skills feedback for interview 82'),
                                                                     (83, 3, 'Communication feedback for interview 83'),
                                                                     (84, 4, 'Teamwork feedback for interview 84'),
                                                                     (85, 5, 'Problem Solving feedback for interview 85'),
                                                                     (86, 6, 'Leadership feedback for interview 86'),
                                                                     (87, 7, 'Time Management feedback for interview 87'),
                                                                     (88, 8, 'Adaptability feedback for interview 88'),
                                                                     (89, 9, 'Creativity feedback for interview 89'),
                                                                     (90, 10, 'Work Ethic feedback for interview 90'),
                                                                     (91, 11, 'Attention to Detail feedback for interview 91'),
                                                                     (92, 12, 'Interpersonal Skills feedback for interview 92'),
                                                                     (93, 13, 'Project Management feedback for interview 93'),
                                                                     (94, 14, 'Conflict Resolution feedback for interview 94'),
                                                                     (95, 15, 'Decision Making feedback for interview 95'),
                                                                     (96, 16, 'Critical Thinking feedback for interview 96'),
                                                                     (97, 17, 'Organizational Skills feedback for interview 97'),
                                                                     (98, 18, 'Analytical Skills feedback for interview 98'),
                                                                     (99, 19, 'Negotiation feedback for interview 99'),
                                                                     (100, 20, 'Stress Management feedback for interview 100');

-- Insert data into Departamento
INSERT INTO Departamento (descripcion, id_departamento_padre) VALUES
                                                                  ('CEO', NULL), ('Área de Marketing', 1), ('Gerencia de planeamiento', 1), ('Área de People and Culture', 1), ('Departamento de publicidad', 2), ('Departamento de comunicación', 2), ('Área comercial', 3), ('Departamento de Contratación', 4), ('Departamento de gestión', 4), ('Tribu tecnológica', 7), ('Área producto', 7), ('Tribu de Experiencia', 7), ('Área de Soporte', 7), ('Área de Data & Analitycs', 7);


-- Insert data into Puesto
INSERT INTO Puesto (nombre, paga, id_departamento) VALUES
                                                       ('CEO', 10000, 1), -- Only one position for the first department

                                                       -- Positions for the second department
                                                       ('Marketing Manager', 6000, 2),
                                                       ('Marketing Specialist', 4000, 2),
                                                       ('Marketing Coordinator', 3500, 2),
                                                       ('Marketing Analyst', 3000, 2),
                                                       ('Marketing Assistant', 2500, 2),

                                                       -- Positions for the third department
                                                       ('Planning Manager', 6500, 3),
                                                       ('Planning Specialist', 4500, 3),
                                                       ('Planning Coordinator', 4000, 3),
                                                       ('Planning Analyst', 3500, 3),
                                                       ('Planning Assistant', 3000, 3),

                                                       -- Positions for the fourth department
                                                       ('People and Culture Manager', 7000, 4),
                                                       ('People and Culture Specialist', 5000, 4),
                                                       ('People and Culture Coordinator', 4500, 4),
                                                       ('People and Culture Analyst', 4000, 4),
                                                       ('People and Culture Assistant', 3500, 4),

                                                       -- Positions for the fifth department
                                                       ('Advertising Manager', 6000, 5),
                                                       ('Advertising Specialist', 4000, 5),
                                                       ('Advertising Coordinator', 3500, 5),
                                                       ('Advertising Analyst', 3000, 5),
                                                       ('Advertising Assistant', 2500, 5),

                                                       -- Positions for the sixth department
                                                       ('Communication Manager', 6000, 6),
                                                       ('Communication Specialist', 4000, 6),
                                                       ('Communication Coordinator', 3500, 6),
                                                       ('Communication Analyst', 3000, 6),
                                                       ('Communication Assistant', 2500, 6),

                                                       -- Positions for the seventh department
                                                       ('Commercial Manager', 6500, 7),
                                                       ('Commercial Specialist', 4500, 7),
                                                       ('Commercial Coordinator', 4000, 7),
                                                       ('Commercial Analyst', 3500, 7),
                                                       ('Commercial Assistant', 3000, 7),

                                                       -- Positions for the eighth department
                                                       ('Hiring Manager', 6000, 8),
                                                       ('Hiring Specialist', 4000, 8),
                                                       ('Hiring Coordinator', 3500, 8),
                                                       ('Hiring Analyst', 3000, 8),
                                                       ('Hiring Assistant', 2500, 8),

                                                       -- Positions for the ninth department
                                                       ('Management Manager', 6500, 9),
                                                       ('Management Specialist', 4500, 9),
                                                       ('Management Coordinator', 4000, 9),
                                                       ('Management Analyst', 3500, 9),
                                                       ('Management Assistant', 3000, 9),

                                                       -- Positions for the tenth department
                                                       ('Tech Tribe Leader', 7000, 10),
                                                       ('Tech Tribe Specialist', 5000, 10),
                                                       ('Tech Tribe Coordinator', 4500, 10),
                                                       ('Tech Tribe Analyst', 4000, 10),
                                                       ('Tech Tribe Assistant', 3500, 10),

                                                       -- Positions for the eleventh department
                                                       ('Product Manager', 7000, 11),
                                                       ('Product Specialist', 5000, 11),
                                                       ('Product Coordinator', 4500, 11),
                                                       ('Product Analyst', 4000, 11),
                                                       ('Product Assistant', 3500, 11),

                                                       -- Positions for the twelfth department
                                                       ('Experience Manager', 7000, 12),
                                                       ('Experience Specialist', 5000, 12),
                                                       ('Experience Coordinator', 4500, 12),
                                                       ('Experience Analyst', 4000, 12),
                                                       ('Experience Assistant', 3500, 12),

                                                       -- Positions for the thirteenth department
                                                       ('Support Manager', 6000, 13),
                                                       ('Support Specialist', 4000, 13),
                                                       ('Support Coordinator', 3500, 13),
                                                       ('Support Analyst', 3000, 13),
                                                       ('Support Assistant', 2500, 13),

                                                       -- Positions for the fourteenth department
                                                       ('Data & Analytics Manager', 7500, 14),
                                                       ('Data & Analytics Specialist', 5500, 14),
                                                       ('Data & Analytics Coordinator', 5000, 14),
                                                       ('Data & Analytics Analyst', 4500, 14),
                                                       ('Data & Analytics Assistant', 4000, 14);

-- Insert data into Funcion
INSERT INTO Funcion (nombre, descripcion) VALUES
                                              ('Develop Marketing Strategies', 'Develop and implement marketing strategies to increase brand awareness and sales.'),
                                              ('Manage Marketing Campaigns', 'Oversee the creation and execution of marketing campaigns.'),
                                              ('Analyze Market Trends', 'Analyze market trends to identify opportunities for growth.'),
                                              ('Coordinate Marketing Activities', 'Coordinate marketing activities and ensure alignment with business goals.'),
                                              ('Conduct Market Research', 'Conduct market research to understand customer needs and preferences.'),
                                              ('Plan Marketing Events', 'Plan and execute marketing events to promote products and services.'),
                                              ('Manage Marketing Budget', 'Manage the marketing budget and ensure cost-effective use of resources.'),
                                              ('Develop Marketing Content', 'Create and manage marketing content for various channels.'),
                                              ('Monitor Marketing Performance', 'Monitor and report on the performance of marketing campaigns.'),
                                              ('Develop HR Policies', 'Develop and implement HR policies and procedures.'),
                                              ('Manage Recruitment Process', 'Oversee the recruitment process and ensure the hiring of qualified candidates.'),
                                              ('Conduct Employee Training', 'Conduct training programs to enhance employee skills and knowledge.'),
                                              ('Manage Employee Relations', 'Manage employee relations and address any issues or concerns.'),
                                              ('Develop Compensation Plans', 'Develop and manage compensation and benefits plans.'),
                                              ('Ensure Compliance', 'Ensure compliance with labor laws and regulations.'),
                                              ('Plan Advertising Campaigns', 'Plan and execute advertising campaigns to promote products and services.'),
                                              ('Create Advertising Content', 'Create and manage advertising content for various channels.'),
                                              ('Analyze Advertising Performance', 'Analyze the performance of advertising campaigns and make recommendations for improvement.'),
                                              ('Manage Communication Channels', 'Manage communication channels and ensure effective internal and external communication.'),
                                              ('Develop Communication Strategies', 'Develop and implement communication strategies to support business goals.'),
                                              ('Coordinate Public Relations', 'Coordinate public relations activities and manage media relations.'),
                                              ('Analyze Communication Effectiveness', 'Analyze the effectiveness of communication strategies and make recommendations for improvement.'),
                                              ('Develop Sales Strategies', 'Develop and implement sales strategies to achieve business goals.'),
                                              ('Manage Sales Team', 'Manage the sales team and ensure the achievement of sales targets.'),
                                              ('Analyze Sales Performance', 'Analyze sales performance and make recommendations for improvement.'),
                                              ('Plan Sales Activities', 'Plan and coordinate sales activities to drive business growth.'),
                                              ('Develop Product Roadmap', 'Develop and manage the product roadmap to ensure alignment with business goals.'),
                                              ('Conduct Market Analysis', 'Conduct market analysis to identify opportunities for product development.'),
                                              ('Manage Product Lifecycle', 'Manage the product lifecycle from concept to launch.'),
                                              ('Coordinate Product Development', 'Coordinate product development activities and ensure timely delivery.'),
                                              ('Analyze Product Performance', 'Analyze product performance and make recommendations for improvement.'),
                                              ('Develop Customer Experience Strategies', 'Develop and implement strategies to enhance customer experience.'),
                                              ('Manage Customer Feedback', 'Manage customer feedback and address any issues or concerns.'),
                                              ('Analyze Customer Experience', 'Analyze customer experience data and make recommendations for improvement.'),
                                              ('Plan Customer Experience Activities', 'Plan and coordinate activities to enhance customer experience.'),
                                              ('Provide Customer Support', 'Provide support to customers and address any issues or concerns.'),
                                              ('Manage Support Team', 'Manage the support team and ensure the delivery of high-quality support services.'),
                                              ('Analyze Support Performance', 'Analyze the performance of support services and make recommendations for improvement.'),
                                              ('Develop Support Processes', 'Develop and implement support processes to enhance efficiency and effectiveness.'),
                                              ('Coordinate Support Activities', 'Coordinate support activities and ensure alignment with business goals.'),
                                              ('Develop Data Strategies', 'Develop and implement data strategies to support business goals.'),
                                              ('Analyze Data Trends', 'Analyze data trends to identify opportunities for improvement.'),
                                              ('Manage Data Projects', 'Manage data projects and ensure timely delivery.'),
                                              ('Develop Data Reports', 'Develop and manage data reports to support decision-making.'),
                                              ('Ensure Data Quality', 'Ensure the quality and accuracy of data.'),
                                              ('Coordinate Data Activities', 'Coordinate data activities and ensure alignment with business goals.');

-- Insert data into Puesto_Funcion
INSERT INTO Puesto_Funcion (id_puesto, id_funcion) VALUES
                                                       (1, 1), (1, 2), (1, 3),
                                                       (2, 4), (2, 5), (2, 6),
                                                       (3, 7), (3, 8), (3, 9),
                                                       (4, 10), (4, 11), (4, 12),
                                                       (5, 13), (5, 14), (5, 15),
                                                       (6, 16), (6, 17), (6, 18),
                                                       (7, 19), (7, 20), (7, 21),
                                                       (8, 22), (8, 23), (8, 24),
                                                       (9, 25), (9, 26), (9, 27),
                                                       (10, 28), (10, 29), (10, 30),
                                                       (11, 31), (11, 32), (11, 33),
                                                       (12, 34), (12, 35), (12, 36),
                                                       (13, 37), (13, 38), (13, 39),
                                                       (14, 40), (14, 41), (14, 42);
-- Insert data into Empleado
INSERT INTO Empleado (Nombre, Apellido, fecha_nacimiento, fecha_ingreso, Estado, Documento_identidad, Telefono, id_puesto) VALUES
                                                                                                                               ('John', 'Doe', '1985-01-15', '2010-06-01', 'Activo', '123456789', '555-1234', 1),
                                                                                                                               ('Jane', 'Smith', '1990-02-20', '2012-07-15', 'Activo', '234567890', '555-2345', 2),
                                                                                                                               ('Michael', 'Johnson', '1988-03-25', '2011-08-20', 'Activo', '345678901', '555-3456', 3),
                                                                                                                               ('Emily', 'Davis', '1992-04-30', '2013-09-25', 'Activo', '456789012', '555-4567', 4),
                                                                                                                               ('David', 'Martinez', '1987-05-05', '2010-10-30', 'Activo', '567890123', '555-5678', 5),
                                                                                                                               ('Sarah', 'Lopez', '1991-06-10', '2014-11-05', 'Activo', '678901234', '555-6789', 6),
                                                                                                                               ('James', 'Brown', '1986-07-15', '2009-12-10', 'Activo', '789012345', '555-7890', 7),
                                                                                                                               ('Linda', 'Garcia', '1989-08-20', '2011-01-15', 'Activo', '890123456', '555-8901', 8),
                                                                                                                               ('Robert', 'Wilson', '1985-09-25', '2010-02-20', 'Activo', '901234567', '555-9012', 9),
                                                                                                                               ('Patricia', 'Anderson', '1990-10-30', '2012-03-25', 'Activo', '012345678', '555-0123', 10),
                                                                                                                               ('Charles', 'Thomas', '1987-11-05', '2010-04-30', 'Activo', '123456789', '555-1234', 11),
                                                                                                                               ('Barbara', 'Taylor', '1991-12-10', '2014-05-05', 'Activo', '234567890', '555-2345', 12),
                                                                                                                               ('Joseph', 'Moore', '1988-01-15', '2011-06-10', 'Activo', '345678901', '555-3456', 13),
                                                                                                                               ('Jennifer', 'Jackson', '1992-02-20', '2013-07-15', 'Activo', '456789012', '555-4567', 14),
                                                                                                                               ('Thomas', 'White', '1985-03-25', '2010-08-20', 'Activo', '567890123', '555-5678', 15),
                                                                                                                               ('Susan', 'Harris', '1990-04-30', '2012-09-25', 'Activo', '678901234', '555-6789', 16),
                                                                                                                               ('Christopher', 'Martin', '1987-05-05', '2010-10-30', 'Activo', '789012345', '555-7890', 17),
                                                                                                                               ('Jessica', 'Thompson', '1991-06-10', '2014-11-05', 'Activo', '890123456', '555-8901', 18),
                                                                                                                               ('Daniel', 'Martinez', '1988-07-15', '2011-12-10', 'Activo', '901234567', '555-9012', 19),
                                                                                                                               ('Karen', 'Clark', '1992-08-20', '2013-01-15', 'Activo', '012345678', '555-0123', 20),
                                                                                                                               ('Matthew', 'Rodriguez', '1985-09-25', '2010-02-20', 'Activo', '123456789', '555-1234', 21),
                                                                                                                               ('Nancy', 'Lewis', '1990-10-30', '2012-03-25', 'Activo', '234567890', '555-2345', 22),
                                                                                                                               ('Donald', 'Lee', '1987-11-05', '2010-04-30', 'Activo', '345678901', '555-3456', 23),
                                                                                                                               ('Betty', 'Walker', '1991-12-10', '2014-05-05', 'Activo', '456789012', '555-4567', 24),
                                                                                                                               ('Paul', 'Hall', '1988-01-15', '2011-06-10', 'Activo', '567890123', '555-5678', 25),
                                                                                                                               ('Lisa', 'Allen', '1992-02-20', '2013-07-15', 'Activo', '678901234', '555-6789', 26),
                                                                                                                               ('Mark', 'Young', '1985-03-25', '2010-08-20', 'Activo', '789012345', '555-7890', 27),
                                                                                                                               ('Sandra', 'Hernandez', '1990-04-30', '2012-09-25', 'Activo', '890123456', '555-8901', 28),
                                                                                                                               ('George', 'King', '1987-05-05', '2010-10-30', 'Activo', '901234567', '555-9012', 29),
                                                                                                                               ('Ashley', 'Wright', '1991-06-10', '2014-11-05', 'Activo', '012345678', '555-0123', 30),
                                                                                                                               ('Steven', 'Lopez', '1988-07-15', '2011-12-10', 'Activo', '123456789', '555-1234', 31),
                                                                                                                               ('Kimberly', 'Hill', '1992-08-20', '2013-01-15', 'Activo', '234567890', '555-2345', 32),
                                                                                                                               ('Edward', 'Scott', '1985-09-25', '2010-02-20', 'Activo', '345678901', '555-3456', 33),
                                                                                                                               ('Donna', 'Green', '1990-10-30', '2012-03-25', 'Activo', '456789012', '555-4567', 34),
                                                                                                                               ('Brian', 'Adams', '1987-11-05', '2010-04-30', 'Activo', '567890123', '555-5678', 35),
                                                                                                                               ('Carol', 'Baker', '1991-12-10', '2014-05-05', 'Activo', '678901234', '555-6789', 36),
                                                                                                                               ('Ronald', 'Gonzalez', '1988-01-15', '2011-06-10', 'Activo', '789012345', '555-7890', 37),
                                                                                                                               ('Michelle', 'Nelson', '1992-02-20', '2013-07-15', 'Activo', '890123456', '555-8901', 38),
                                                                                                                               ('Kevin', 'Carter', '1985-03-25', '2010-08-20', 'Activo', '901234567', '555-9012', 39),
                                                                                                                               ('Dorothy', 'Mitchell', '1990-04-30', '2012-09-25', 'Activo', '012345678', '555-0123', 40),
                                                                                                                               ('Jason', 'Perez', '1987-05-05', '2010-10-30', 'Activo', '123456789', '555-1234', 41),
                                                                                                                               ('Helen', 'Roberts', '1991-06-10', '2014-11-05', 'Activo', '234567890', '555-2345', 42),
                                                                                                                               ('Jeffrey', 'Turner', '1988-07-15', '2011-12-10', 'Activo', '345678901', '555-3456', 43),
                                                                                                                               ('Deborah', 'Phillips', '1992-08-20', '2013-01-15', 'Activo', '456789012', '555-4567', 44),
                                                                                                                               ('Ryan', 'Campbell', '1985-09-25', '2010-02-20', 'Activo', '567890123', '555-5678', 45),
                                                                                                                               ('Laura', 'Parker', '1990-10-30', '2012-03-25', 'Activo', '678901234', '555-6789', 46),
                                                                                                                               ('Theresa', 'Griffin', '1993-02-20', '2015-07-25', 'Activo', '890123475', '555-9010', 2),
                                                                                                                               ('Samuel', 'Henderson', '1985-03-15', '2010-08-01', 'Activo', '901234586', '555-0121', 2),
                                                                                                                               ('Laura', 'Parker', '1990-04-20', '2012-09-15', 'Activo', '012345697', '555-1232', 3),
                                                                                                                               ('John', 'Doe', '1985-01-15', '2010-06-01', 'Activo', '123456789', '555-1234', 4),
                                                                                                                               ('Jane', 'Smith', '1990-02-20', '2012-07-15', 'Activo', '234567890', '555-2345', 5),
                                                                                                                               ('Michael', 'Johnson', '1988-03-25', '2011-08-20', 'Activo', '345678901', '555-3456', 6),
                                                                                                                               ('Emily', 'Davis', '1992-04-30', '2013-09-25', 'Activo', '456789012', '555-4567', 7),
                                                                                                                               ('David', 'Martinez', '1987-05-05', '2010-10-30', 'Activo', '567890123', '555-5678', 8),
                                                                                                                               ('Sarah', 'Lopez', '1991-06-10', '2014-11-05', 'Activo', '678901234', '555-6789', 9),
                                                                                                                               ('James', 'Brown', '1986-07-15', '2009-12-10', 'Activo', '789012345', '555-7890', 10),
                                                                                                                               ('Linda', 'Garcia', '1989-08-20', '2011-01-15', 'Activo', '890123456', '555-8901', 11),
                                                                                                                               ('Robert', 'Wilson', '1985-09-25', '2010-02-20', 'Activo', '901234567', '555-9012', 12),
                                                                                                                               ('Patricia', 'Anderson', '1990-10-30', '2012-03-25', 'Activo', '012345678', '555-0123', 13),
                                                                                                                               ('Charles', 'Thomas', '1987-11-05', '2010-04-30', 'Activo', '123456789', '555-1234', 14),
                                                                                                                               ('Barbara', 'Taylor', '1991-12-10', '2014-05-05', 'Activo', '234567890', '555-2345', 15),
                                                                                                                               ('Joseph', 'Moore', '1988-01-15', '2011-06-10', 'Activo', '345678901', '555-3456', 16),
                                                                                                                               ('Jennifer', 'Jackson', '1992-02-20', '2013-07-15', 'Activo', '456789012', '555-4567', 17),
                                                                                                                               ('Thomas', 'White', '1985-03-25', '2010-08-20', 'Activo', '567890123', '555-5678', 18),
                                                                                                                               ('Susan', 'Harris', '1990-04-30', '2012-09-25', 'Activo', '678901234', '555-6789', 19),
                                                                                                                               ('Christopher', 'Martin', '1987-05-05', '2010-10-30', 'Activo', '789012345', '555-7890', 20),
                                                                                                                               ('Jessica', 'Thompson', '1991-06-10', '2014-11-05', 'Activo', '890123456', '555-8901', 21),
                                                                                                                               ('Daniel', 'Martinez', '1988-07-15', '2011-12-10', 'Activo', '901234567', '555-9012', 22),
                                                                                                                               ('Karen', 'Clark', '1992-08-20', '2013-01-15', 'Activo', '012345678', '555-0123', 23),
                                                                                                                               ('Matthew', 'Rodriguez', '1985-09-25', '2010-02-20', 'Activo', '123456789', '555-1234', 24),
                                                                                                                               ('Nancy', 'Lewis', '1990-10-30', '2012-03-25', 'Activo', '234567890', '555-2345', 25),
                                                                                                                               ('Donald', 'Lee', '1987-11-05', '2010-04-30', 'Activo', '345678901', '555-3456', 26),
                                                                                                                               ('Betty', 'Walker', '1991-12-10', '2014-05-05', 'Activo', '456789012', '555-4567', 27),
                                                                                                                               ('Paul', 'Hall', '1988-01-15', '2011-06-10', 'Activo', '567890123', '555-5678', 28),
                                                                                                                               ('Lisa', 'Allen', '1992-02-20', '2013-07-15', 'Activo', '678901234', '555-6789', 29),
                                                                                                                               ('Mark', 'Young', '1985-03-25', '2010-08-20', 'Activo', '789012345', '555-7890', 30),
                                                                                                                               ('Sandra', 'Hernandez', '1990-04-30', '2012-09-25', 'Activo', '890123456', '555-8901', 31),
                                                                                                                               ('George', 'King', '1987-05-05', '2010-10-30', 'Activo', '901234567', '555-9012', 32),
                                                                                                                               ('Ashley', 'Wright', '1991-06-10', '2014-11-05', 'Activo', '012345678', '555-0123', 33),
                                                                                                                               ('Steven', 'Lopez', '1988-07-15', '2011-12-10', 'Activo', '123456789', '555-1234', 34),
                                                                                                                               ('Kimberly', 'Hill', '1992-08-20', '2013-01-15', 'Activo', '234567890', '555-2345', 35),
                                                                                                                               ('Edward', 'Scott', '1985-09-25', '2010-02-20', 'Activo', '345678901', '555-3456', 36),
                                                                                                                               ('Donna', 'Green', '1990-10-30', '2012-03-25', 'Activo', '456789012', '555-4567', 37),
                                                                                                                               ('Brian', 'Adams', '1987-11-05', '2010-04-30', 'Activo', '567890123', '555-5678', 38),
                                                                                                                               ('Carol', 'Baker', '1991-12-10', '2014-05-05', 'Activo', '678901234', '555-6789', 39),
                                                                                                                               ('Ronald', 'Gonzalez', '1988-01-15', '2011-06-10', 'Activo', '789012345', '555-7890', 40),
                                                                                                                               ('Michelle', 'Nelson', '1992-02-20', '2013-07-15', 'Activo', '890123456', '555-8901', 41),
                                                                                                                               ('Kevin', 'Carter', '1985-03-25', '2010-08-20', 'Activo', '901234567', '555-9012', 42),
                                                                                                                               ('Dorothy', 'Mitchell', '1990-04-30', '2012-09-25', 'Activo', '012345678', '555-0123', 43),
                                                                                                                               ('Jason', 'Perez', '1987-05-05', '2010-10-30', 'Activo', '123456789', '555-1234', 44),
                                                                                                                               ('Helen', 'Roberts', '1991-06-10', '2014-11-05', 'Activo', '234567890', '555-2345', 45),
                                                                                                                               ('Jeffrey', 'Turner', '1988-07-15', '2011-12-10', 'Activo', '345678901', '555-3456', 46),
                                                                                                                               ('Deborah', 'Phillips', '1992-08-20', '2013-01-15', 'Activo', '456789012', '555-4567', 2),
                                                                                                                               ('Ryan', 'Campbell', '1985-09-25', '2010-02-20', 'Activo', '567890123', '555-5678', 3),
                                                                                                                               ('Laura', 'Parker', '1990-10-30', '2012-03-25', 'Activo', '678901234', '555-6789', 4),
                                                                                                                               ('Theresa', 'Griffin', '1993-02-20', '2015-07-25', 'Activo', '890123475', '555-9010', 5),
                                                                                                                               ('Samuel', 'Henderson', '1985-03-15', '2010-08-01', 'Activo', '901234586', '555-0121', 6),
                                                                                                                               ('Laura', 'Parker', '1990-04-20', '2012-09-15', 'Activo', '012345697', '555-1232', 7),
                                                                                                                               ('John', 'Doe', '1985-01-15', '2010-06-01', 'Activo', '123456789', '555-1234', 8),
                                                                                                                               ('Jane', 'Smith', '1990-02-20', '2012-07-15', 'Activo', '234567890', '555-2345', 9),
                                                                                                                               ('Michael', 'Johnson', '1988-03-25', '2011-08-20', 'Activo', '345678901', '555-3456', 10),
                                                                                                                               ('Emily', 'Davis', '1992-04-30', '2013-09-25', 'Activo', '456789012', '555-4567', 11),
                                                                                                                               ('David', 'Martinez', '1987-05-05', '2010-10-30', 'Activo', '567890123', '555-5678', 12);

-- Insert data into Vacante
INSERT INTO Vacante (estado, fecha_fin, fecha_inicio, comentario, id_puesto, cantidad) VALUES
                                                                                           -- Vacancies with 'Abierta' state
                                                                                           ('Abierta', '2025-12-31', '2024-12-02', 'Comentario Abierta 1', 1, 1),
                                                                                           ('Abierta', '2025-12-31', '2024-12-02', 'Comentario Abierta 2', 2, 1),
                                                                                           ('Abierta', '2025-12-31', '2024-12-02', 'Comentario Abierta 3', 3, 1),
                                                                                           ('Abierta', '2025-12-31', '2024-12-02', 'Comentario Abierta 4', 4, 1),
                                                                                           ('Abierta', '2025-12-31', '2024-12-02', 'Comentario Abierta 5', 5, 1),

                                                                                           -- Vacancies with 'Cerrada' state
                                                                                           ('Cerrada', '2024-11-01', '2024-01-01', 'Comentario Cerrada 1', 6, 0),
                                                                                           ('Cerrada', '2024-11-01', '2024-02-01', 'Comentario Cerrada 2', 7, 0),
                                                                                           ('Cerrada', '2024-11-01', '2024-03-01', 'Comentario Cerrada 3', 8, 0),
                                                                                           ('Cerrada', '2024-11-01', '2024-04-01', 'Comentario Cerrada 4', 9, 0),
                                                                                           ('Cerrada', '2024-11-01', '2024-05-01', 'Comentario Cerrada 5', 10, 0),

                                                                                           -- Vacancies with 'Vencida' state
                                                                                           ('Vencida', '2024-10-01', '2024-01-01', 'Comentario Vencida 1', 11, 0),
                                                                                           ('Vencida', '2024-10-01', '2024-02-01', 'Comentario Vencida 2', 12, 0),
                                                                                           ('Vencida', '2024-10-01', '2024-03-01', 'Comentario Vencida 3', 13, 0),
                                                                                           ('Vencida', '2024-10-01', '2024-04-01', 'Comentario Vencida 4', 14, 0),
                                                                                           ('Vencida', '2024-10-01', '2024-05-01', 'Comentario Vencida 5', 15, 0);

-- Insert data into Convocatoria
INSERT INTO Convocatoria (id_vacante, medio_publicacion, fecha_inicio, fecha_fin, estado) VALUES
                                                                                              -- Convocatorias for Vacante 1
                                                                                              (1, 'Instagram', '2024-12-02', '2025-12-31', 'Abierta'),
                                                                                              (1, 'Facebook', '2024-12-02', '2025-12-31', 'Abierta'),

                                                                                              -- Convocatorias for Vacante 2
                                                                                              (2, 'Instagram', '2024-12-02', '2025-12-31', 'Abierta'),
                                                                                              (2, 'Facebook', '2024-12-02', '2025-12-31', 'Abierta'),

                                                                                              -- Convocatorias for Vacante 3
                                                                                              (3, 'Instagram', '2024-12-02', '2025-12-31', 'Abierta'),
                                                                                              (3, 'Facebook', '2024-12-02', '2025-12-31', 'Abierta'),

                                                                                              -- Convocatorias for Vacante 4
                                                                                              (4, 'Instagram', '2024-12-02', '2025-12-31', 'Abierta'),
                                                                                              (4, 'Facebook', '2024-12-02', '2025-12-31', 'Abierta'),

                                                                                              -- Convocatorias for Vacante 5
                                                                                              (5, 'Instagram', '2024-12-02', '2025-12-31', 'Abierta'),
                                                                                              (5, 'Facebook', '2024-12-02', '2025-12-31', 'Abierta'),

                                                                                              -- Convocatorias for Vacante 6
                                                                                              (6, 'LinkedIn', '2024-01-01', '2024-11-01', 'Cerrada'),
                                                                                              (6, 'Twitter', '2024-01-01', '2024-11-01', 'Cerrada'),

                                                                                              -- Convocatorias for Vacante 7
                                                                                              (7, 'LinkedIn', '2024-02-01', '2024-11-01', 'Cerrada'),
                                                                                              (7, 'Twitter', '2024-02-01', '2024-11-01', 'Cerrada'),

                                                                                              -- Convocatorias for Vacante 8
                                                                                              (8, 'LinkedIn', '2024-03-01', '2024-11-01', 'Cerrada'),
                                                                                              (8, 'Twitter', '2024-03-01', '2024-11-01', 'Cerrada'),

                                                                                              -- Convocatorias for Vacante 9
                                                                                              (9, 'LinkedIn', '2024-04-01', '2024-11-01', 'Cerrada'),
                                                                                              (9, 'Twitter', '2024-04-01', '2024-11-01', 'Cerrada'),

                                                                                              -- Convocatorias for Vacante 10
                                                                                              (10, 'LinkedIn', '2024-05-01', '2024-11-01', 'Cerrada'),
                                                                                              (10, 'Twitter', '2024-05-01', '2024-11-01', 'Cerrada'),

                                                                                              -- Convocatorias for Vacante 11
                                                                                              (11, 'LinkedIn', '2024-01-01', '2024-10-01', 'Cerrada'),
                                                                                              (11, 'Twitter', '2024-01-01', '2024-10-01', 'Cerrada'),

                                                                                              -- Convocatorias for Vacante 12
                                                                                              (12, 'LinkedIn', '2024-02-01', '2024-10-01', 'Cerrada'),
                                                                                              (12, 'Twitter', '2024-02-01', '2024-10-01', 'Cerrada'),

                                                                                              -- Convocatorias for Vacante 13
                                                                                              (13, 'LinkedIn', '2024-03-01', '2024-10-01', 'Cerrada'),
                                                                                              (13, 'Twitter', '2024-03-01', '2024-10-01', 'Cerrada'),

                                                                                              -- Convocatorias for Vacante 14
                                                                                              (14, 'LinkedIn', '2024-04-01', '2024-10-01', 'Cerrada'),
                                                                                              (14, 'Twitter', '2024-04-01', '2024-10-01', 'Cerrada'),

                                                                                              -- Convocatorias for Vacante 15
                                                                                              (15, 'LinkedIn', '2024-05-01', '2024-10-01', 'Cerrada'),
                                                                                              (15, 'Twitter', '2024-05-01', '2024-10-01', 'Cerrada');
-- Insert data into Postulante
INSERT INTO Postulante (nombre, telefono, id_vacante, correo, puntaje_general) VALUES
                                                                                   ('John Doe', 120, 1, 'john.doe@example.com', 0),
                                                                                   ('Jane Smith', 2341, 2, 'jane.smith@example.com', 0),
                                                                                   ('Michael Johnson', 3412, 3, 'michael.johnson@example.com', 0),
                                                                                   ('Emily Davis', 453, 4, 'emily.davis@example.com', 0),
                                                                                   ('David Brown', 5634, 5, 'david.brown@example.com', 0),
                                                                                   ('Sarah Wilson', 45, 6, 'sarah.wilson@example.com', 0),
                                                                                   ('James Taylor', 7856, 7, 'james.taylor@example.com', 0),
                                                                                   ('Linda Martinez', 897, 8, 'linda.martinez@example.com', 0),
                                                                                   ('Robert Anderson', 978, 9, 'robert.anderson@example.com', 0),
                                                                                   ('Patricia Thomas', 191, 10, 'patricia.thomas@example.com', 0),
                                                                                   ('Charles Jackson', 2302, 11, 'charles.jackson@example.com', 0),
                                                                                   ('Barbara White', 313, 12, 'barbara.white@example.com', 0),
                                                                                   ('Christopher Harris', 424, 13, 'christopher.harris@example.com', 0),
                                                                                   ('Jessica Martin', 535, 14, 'jessica.martin@example.com', 0),
                                                                                   ('Daniel Thompson', 46, 15, 'daniel.thompson@example.com', 0),
                                                                                   ('Nancy Garcia', 789, 1, 'nancy.garcia@example.com', 0),
                                                                                   ('Matthew Martinez', 89, 2, 'matthew.martinez@example.com', 0),
                                                                                   ('Karen Robinson', 90, 3, 'karen.robinson@example.com', 0),
                                                                                   ('Joshua Clark', 123, 4, 'joshua.clark@example.com', 0),
                                                                                   ('Betty Rodriguez', 2301, 5, 'betty.rodriguez@example.com', 0),
                                                                                   ('Steven Lewis', 345, 6, 'steven.lewis@example.com', 0),
                                                                                   ('Donna Lee', 456, 7, 'donna.lee@example.com', 0),
                                                                                   ('Paul Walker', 5678, 8, 'paul.walker@example.com', 0),
                                                                                   ('Carol Hall', 67, 9, 'carol.hall@example.com', 0),
                                                                                   ('Mark Allen', 789, 10, 'mark.allen@example.com', 0),
                                                                                   ('Sandra Young', 890, 11, 'sandra.young@example.com', 0),
                                                                                   ('George King', 90, 12, 'george.king@example.com', 0),
                                                                                   ('Ashley Wright', 123, 13, 'ashley.wright@example.com', 0),
                                                                                   ('Kenneth Scott', 234, 14, 'kenneth.scott@example.com', 0),
                                                                                   ('Kimberly Green', 34, 15, 'kimberly.green@example.com', 0),
                                                                                   ('Brian Adams', 46, 1, 'brian.adams@example.com', 0),
                                                                                   ('Deborah Baker', 537, 2, 'deborah.baker@example.com', 0),
                                                                                   ('Kevin Gonzalez', 674, 3, 'kevin.gonzalez@example.com', 0),
                                                                                   ('Sharon Nelson', 789, 4, 'sharon.nelson@example.com', 0),
                                                                                   ('Edward Carter', 450, 5, 'edward.carter@example.com', 0),
                                                                                   ('Michelle Mitchell', 91, 6, 'michelle.mitchell@example.com', 0),
                                                                                   ('Ronald Perez', 14, 7, 'ronald.perez@example.com', 0),
                                                                                   ('Laura Roberts', 2345, 8, 'laura.roberts@example.com', 0),
                                                                                   ('Anthony Turner', 3416, 9, 'anthony.turner@example.com', 0),
                                                                                   ('Sarah Phillips', 4527, 10, 'sarah.phillips@example.com', 0),
                                                                                   ('Jason Campbell', 538, 11, 'jason.campbell@example.com', 0),
                                                                                   ('Dorothy Parker', 649, 12, 'dorothy.parker@example.com', 0),
                                                                                   ('Jeffrey Evans', 460, 13, 'jeffrey.evans@example.com', 0),
                                                                                   ('Amy Edwards', 871, 14, 'amy.edwards@example.com', 0),
                                                                                   ('Gary Collins', 90, 15, 'gary.collins@example.com', 0),
                                                                                   ('Angela Stewart', 125, 1, 'angela.stewart@example.com', 0),
                                                                                   ('Larry Sanchez', 2306, 2, 'larry.sanchez@example.com', 0),
                                                                                   ('Helen Morris', 317, 3, 'helen.morris@example.com', 0),
                                                                                   ('Scott Rogers', 458, 4, 'scott.rogers@example.com', 0),
                                                                                   ('Ruth Reed', 567, 5, 'ruth.reed@example.com', 0),
                                                                                   ('Frank Cook', 678, 6, 'frank.cook@example.com', 0),
                                                                                   ('Brenda Morgan', 783, 7, 'brenda.morgan@example.com', 0),
                                                                                   ('Eric Bell', 890, 8, 'eric.bell@example.com', 0),
                                                                                   ('Pamela Murphy', 973, 9, 'pamela.murphy@example.com', 0),
                                                                                   ('Stephen Bailey', 126, 10, 'stephen.bailey@example.com', 0),
                                                                                   ('Katherine Rivera', 230, 11, 'katherine.rivera@example.com', 0),
                                                                                   ('Andrew Cooper', 348, 12, 'andrew.cooper@example.com', 0),
                                                                                   ('Rebecca Richardson', 429, 13, 'rebecca.richardson@example.com', 0),
                                                                                   ('Joshua Cox', 50, 14, 'joshua.cox@example.com', 0),
                                                                                   ('Stephanie Howard', 651, 15, 'stephanie.howard@example.com', 0),
                                                                                   ('Raymond Ward', 782, 1, 'raymond.ward@example.com', 0),
                                                                                   ('Shirley Torres', 853, 2, 'shirley.torres@example.com', 0),
                                                                                   ('Jerry Peterson', 964, 3, 'jerry.peterson@example.com', 0),
                                                                                   ('Anna Gray', 123, 4, 'anna.gray@example.com', 0),
                                                                                   ('Dennis Ramirez', 208, 5, 'dennis.ramirez@example.com', 0),
                                                                                   ('Martha James', 319, 6, 'martha.james@example.com', 0),
                                                                                   ('Patrick Watson', 4560, 7, 'patrick.watson@example.com', 0),
                                                                                   ('Diane Brooks', 5671, 8, 'diane.brooks@example.com', 0),
                                                                                   ('Walter Kelly', 678, 9, 'walter.kelly@example.com', 0),
                                                                                   ('Janet Sanders', 763, 10, 'janet.sanders@example.com', 0),
                                                                                   ('Peter Price', 8901, 11, 'peter.price@example.com', 0),
                                                                                   ('Carol Bennett', 905, 12, 'carol.bennett@example.com', 0),
                                                                                   ('Harold Wood', 12, 13, 'harold.wood@example.com', 0),
                                                                                   ('Frances Barnes', 23, 14, 'frances.barnes@example.com', 0),
                                                                                   ('Douglas Ross', 34, 15, 'douglas.ross@example.com', 0),
                                                                                   ('Joyce Henderson', 456, 1, 'joyce.henderson@example.com', 0),
                                                                                   ('Henry Coleman', 567, 2, 'henry.coleman@example.com', 0),
                                                                                   ('Gloria Jenkins', 678, 3, 'gloria.jenkins@example.com', 0),
                                                                                   ('Carl Perry', 789, 4, 'carl.perry@example.com', 0),
                                                                                   ('Evelyn Powell', 8905, 5, 'evelyn.powell@example.com', 0),
                                                                                   ('Arthur Long', 9126, 6, 'arthur.long@example.com', 0),
                                                                                   ('Christine Patterson', 459, 7, 'christine.patterson@example.com', 0),
                                                                                   ('Ryan Hughes', 2310, 8, 'ryan.hughes@example.com', 0),
                                                                                   ('Kathleen Flores', 3451, 9, 'kathleen.flores@example.com', 0),
                                                                                   ('Albert Washington', 45, 10, 'albert.washington@example.com', 0),
                                                                                   ('Megan Butler', 5243, 11, 'megan.butler@example.com', 0),
                                                                                   ('Bruce Simmons', 67354, 12, 'bruce.simmons@example.com', 0),
                                                                                   ('Theresa Foster', 78912, 13, 'theresa.foster@example.com', 0),
                                                                                   ('Lawrence Gonzales', 8901, 14, 'lawrence.gonzales@example.com', 0),
                                                                                   ('Denise Bryant', 9123, 15, 'denise.bryant@example.com', 0),
                                                                                      ('Ralph Alexander', 1234, 1, 'ralph.alexander@example.com', 0),
                                                                                      ('Judith Russell', 2345, 2, 'judith.russell@example.com', 0),
                                                                                      ('Roy Griffin', 3456, 3, 'roy.griffin@example.com', 0),
                                                                                      ('Theresa Diaz', 4567, 4, 'theresa.diaz@example.com', 0),
                                                                                      ('Eugene Hayes', 5678, 5, 'eugene.hayes@example.com', 0),
                                                                                      ('Rose Long', 6789, 6, 'rose.long@example.com', 0),
                                                                                      ('Julie Myers', 7890, 7, 'julie.myers@example.com', 0),
                                                                                      ('Vincent Ford', 8901, 8, 'vicent.ford@example.com', 0),
                                                                                      ('Bonnie Hamilton', 9012, 9, 'bonnie.hamilton@example.com', 0),
                                                                                      ('Billy Graham', 1234, 10, 'billy.graham@example.com', 0),
                                                                                      ('Mildred Wallace', 2345, 11, 'mildred.wallace@exampl.com', 0),
                                                                                      ('Willie Sullivan', 3456, 12, 'willie.sullivan@example.com', 0);

ALTER TABLE Postulante
    ALTER COLUMN telefono TYPE BIGINT;

-- Insert data into Educacion
-- Insert data into Educacion
INSERT INTO Educacion (id_postulante, institucion, titulo, fecha_inicio, fecha_fin, en_curso) VALUES
                                                                                                  (1, 'Harvard University', 'Bachelor of Science in Computer Science', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (2, 'Stanford University', 'Master of Business Administration', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (3, 'Massachusetts Institute of Technology', 'Bachelor of Science in Electrical Engineering', '2009-09-01', '2013-06-01', FALSE),
                                                                                                  (4, 'University of California, Berkeley', 'Bachelor of Arts in Economics', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (5, 'University of Oxford', 'Master of Science in Data Science', '2013-09-01', '2015-06-01', FALSE),
                                                                                                  (6, 'University of Cambridge', 'Bachelor of Arts in History', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (7, 'California Institute of Technology', 'Bachelor of Science in Physics', '2008-09-01', '2012-06-01', FALSE),
                                                                                                  (8, 'Princeton University', 'Bachelor of Science in Mathematics', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (9, 'Yale University', 'Bachelor of Arts in Political Science', '2009-09-01', '2013-06-01', FALSE),
                                                                                                  (10, 'Columbia University', 'Master of Science in Computer Engineering', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (11, 'University of Chicago', 'Bachelor of Arts in Sociology', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (12, 'University of Pennsylvania', 'Bachelor of Science in Nursing', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (13, 'University of Michigan', 'Bachelor of Science in Mechanical Engineering', '2009-09-01', '2013-06-01', FALSE),
                                                                                                  (14, 'Duke University', 'Bachelor of Arts in Psychology', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (15, 'Northwestern University', 'Master of Science in Finance', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (16, 'New York University', 'Bachelor of Arts in Journalism', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (17, 'University of Southern California', 'Bachelor of Science in Civil Engineering', '2009-09-01', '2013-06-01', FALSE),
                                                                                                  (18, 'University of Toronto', 'Bachelor of Science in Biology', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (19, 'McGill University', 'Bachelor of Arts in Philosophy', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (20, 'University of British Columbia', 'Master of Science in Environmental Science', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (21, 'University of Edinburgh', 'Bachelor of Science in Chemistry', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (22, 'University of Melbourne', 'Bachelor of Arts in English Literature', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (23, 'University of Sydney', 'Master of Science in Physics', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (24, 'University of Queensland', 'Bachelor of Science in Environmental Science', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (25, 'University of New South Wales', 'Bachelor of Science in Computer Science', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (26, 'University of Auckland', 'Bachelor of Arts in History', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (27, 'University of Otago', 'Bachelor of Science in Biology', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (28, 'University of Western Australia', 'Bachelor of Science in Mathematics', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (29, 'University of Adelaide', 'Bachelor of Arts in Political Science', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (30, 'University of Toronto', 'Master of Science in Computer Engineering', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (31, 'University of British Columbia', 'Bachelor of Arts in Sociology', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (32, 'McGill University', 'Bachelor of Science in Nursing', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (33, 'University of Alberta', 'Bachelor of Science in Mechanical Engineering', '2009-09-01', '2013-06-01', FALSE),
                                                                                                  (34, 'University of Montreal', 'Bachelor of Arts in Psychology', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (35, 'University of Waterloo', 'Master of Science in Finance', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (36, 'University of Calgary', 'Bachelor of Arts in Journalism', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (37, 'University of Western Ontario', 'Bachelor of Science in Civil Engineering', '2009-09-01', '2013-06-01', FALSE),
                                                                                                  (38, 'University of Victoria', 'Bachelor of Science in Biology', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (39, 'University of Ottawa', 'Bachelor of Arts in Philosophy', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (40, 'University of Manitoba', 'Master of Science in Environmental Science', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (41, 'University of Saskatchewan', 'Bachelor of Science in Chemistry', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (42, 'University of Guelph', 'Bachelor of Arts in English Literature', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (43, 'University of New Brunswick', 'Master of Science in Physics', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (44, 'University of Prince Edward Island', 'Bachelor of Science in Environmental Science', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (45, 'University of Regina', 'Bachelor of Science in Computer Science', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (46, 'University of Lethbridge', 'Bachelor of Arts in History', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (47, 'University of Windsor', 'Bachelor of Science in Biology', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (48, 'University of Northern British Columbia', 'Bachelor of Science in Mathematics', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (49, 'University of Winnipeg', 'Bachelor of Arts in Political Science', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (50, 'University of Brandon', 'Master of Science in Computer Engineering', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (51, 'University of Moncton', 'Bachelor of Arts in Sociology', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (52, 'University of St. Thomas', 'Bachelor of Science in Nursing', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (53, 'University of Mount Allison', 'Bachelor of Science in Mechanical Engineering', '2009-09-01', '2013-06-01', FALSE),
                                                                                                  (54, 'University of Acadia', 'Bachelor of Arts in Psychology', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (55, 'University of Dalhousie', 'Master of Science in Finance', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (56, 'University of Cape Breton', 'Bachelor of Arts in Journalism', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (57, 'University of King s College', 'Bachelor of Science in Civil Engineering', '2009-09-01', '2013-06-01', FALSE),
                                                                                                  (58, 'University of Saint Mary s', 'Bachelor of Science in Biology', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (59, 'University of Memorial', 'Bachelor of Arts in Philosophy', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (60, 'University of Laurentian', 'Master of Science in Environmental Science', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (61, 'University of Nipissing', 'Bachelor of Science in Chemistry', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (62, 'University of Lakehead', 'Bachelor of Arts in English Literature', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (63, 'University of Trent', 'Master of Science in Physics', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (64, 'University of Brock', 'Bachelor of Science in Environmental Science', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (65, 'University of Carleton', 'Bachelor of Science in Computer Science', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (66, 'University of Ryerson', 'Bachelor of Arts in History', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (67, 'University of York', 'Bachelor of Science in Biology', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (68, 'University of Queens', 'Bachelor of Science in Mathematics', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (69, 'University of Western', 'Bachelor of Arts in Political Science', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (70, 'University of McMaster', 'Master of Science in Computer Engineering', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (71, 'University of Windsor', 'Bachelor of Arts in Sociology', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (72, 'University of Guelph', 'Bachelor of Science in Nursing', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (73, 'University of Ottawa', 'Bachelor of Science in Mechanical Engineering', '2009-09-01', '2013-06-01', FALSE),
                                                                                                  (74, 'University of Calgary', 'Bachelor of Arts in Psychology', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (75, 'University of Alberta', 'Master of Science in Finance', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (76, 'University of British Columbia', 'Bachelor of Arts in Journalism', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (77, 'University of Toronto', 'Bachelor of Science in Civil Engineering', '2009-09-01', '2013-06-01', FALSE),
                                                                                                  (78, 'University of McGill', 'Bachelor of Science in Biology', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (79, 'University of Western Ontario', 'Bachelor of Arts in Philosophy', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (80, 'University of Waterloo', 'Master of Science in Environmental Science', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (81, 'University of Victoria', 'Bachelor of Science in Chemistry', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (82, 'University of Ottawa', 'Bachelor of Arts in English Literature', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (83, 'University of Montreal', 'Master of Science in Physics', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (84, 'University of Alberta', 'Bachelor of Science in Environmental Science', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (85, 'University of Calgary', 'Bachelor of Science in Computer Science', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (86, 'University of British Columbia', 'Bachelor of Arts in History', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (87, 'University of Toronto', 'Bachelor of Science in Biology', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (88, 'University of McGill', 'Bachelor of Science in Mathematics', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (89, 'University of Western Ontario', 'Bachelor of Arts in Political Science', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (90, 'University of Waterloo', 'Master of Science in Computer Engineering', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (91, 'University of Victoria', 'Bachelor of Arts in Sociology', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (92, 'University of Ottawa', 'Bachelor of Science in Nursing', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (93, 'University of Montreal', 'Bachelor of Science in Mechanical Engineering', '2009-09-01', '2013-06-01', FALSE),
                                                                                                  (94, 'University of Alberta', 'Bachelor of Arts in Psychology', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (95, 'University of Calgary', 'Master of Science in Finance', '2012-09-01', '2014-06-01', FALSE),
                                                                                                  (96, 'University of British Columbia', 'Bachelor of Arts in Journalism', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (97, 'University of Toronto', 'Bachelor of Science in Civil Engineering', '2009-09-01', '2013-06-01', FALSE),
                                                                                                  (98, 'University of McGill', 'Bachelor of Science in Biology', '2010-09-01', '2014-06-01', FALSE),
                                                                                                  (99, 'University of Western Ontario', 'Bachelor of Arts in Philosophy', '2011-09-01', '2015-06-01', FALSE),
                                                                                                  (100, 'University of Waterloo', 'Master of Science in Environmental Science', '2012-09-01', '2014-06-01', FALSE);
-- Insert data into Experiencia_Laboral
INSERT INTO Experiencia_Laboral (id_postulante, empresa, puesto, fecha_inicio, fecha_fin) VALUES
                                                                                              (1, 'Google', 'Software Engineer', '2015-01-01', '2019-12-31'),
                                                                                              (2, 'Microsoft', 'Project Manager', '2016-02-01', '2020-01-31'),
                                                                                              (3, 'Amazon', 'Data Analyst', '2014-03-01', '2018-02-28'),
                                                                                              (4, 'Facebook', 'Marketing Specialist', '2017-04-01', '2021-03-31'),
                                                                                              (5, 'Apple', 'Product Manager', '2013-05-01', '2017-04-30'),
                                                                                              (6, 'IBM', 'System Administrator', '2012-06-01', '2016-05-31'),
                                                                                              (7, 'Intel', 'Hardware Engineer', '2011-07-01', '2015-06-30'),
                                                                                              (8, 'Cisco', 'Network Engineer', '2010-08-01', '2014-07-31'),
                                                                                              (9, 'Oracle', 'Database Administrator', '2009-09-01', '2013-08-31'),
                                                                                              (10, 'Salesforce', 'Sales Manager', '2018-10-01', '2022-09-30'),
                                                                                              (11, 'Adobe', 'Graphic Designer', '2015-11-01', '2019-10-31'),
                                                                                              (12, 'HP', 'IT Support Specialist', '2016-12-01', '2020-11-30'),
                                                                                              (13, 'Dell', 'Mechanical Engineer', '2014-01-01', '2018-12-31'),
                                                                                              (14, 'NVIDIA', 'AI Researcher', '2017-02-01', '2021-01-31'),
                                                                                              (15, 'Tesla', 'Electrical Engineer', '2013-03-01', '2017-02-28'),
                                                                                              (16, 'SpaceX', 'Aerospace Engineer', '2012-04-01', '2016-03-31'),
                                                                                              (17, 'Uber', 'Operations Manager', '2011-05-01', '2015-04-30'),
                                                                                              (18, 'Airbnb', 'Customer Service Representative', '2010-06-01', '2014-05-31'),
                                                                                              (19, 'Netflix', 'Content Manager', '2009-07-01', '2013-06-30'),
                                                                                              (20, 'Spotify', 'Music Curator', '2018-08-01', '2022-07-31'),
                                                                                              (21, 'Snapchat', 'Social Media Manager', '2015-09-01', '2019-08-31'),
                                                                                              (22, 'Pinterest', 'Community Manager', '2016-10-01', '2020-09-30'),
                                                                                              (23, 'LinkedIn', 'Business Analyst', '2014-11-01', '2018-10-31'),
                                                                                              (24, 'Twitter', 'Content Strategist', '2017-12-01', '2021-11-30'),
                                                                                              (25, 'Reddit', 'Community Moderator', '2013-01-01', '2017-12-31'),
                                                                                              (26, 'Dropbox', 'Cloud Engineer', '2012-02-01', '2016-01-31'),
                                                                                              (27, 'Slack', 'Product Designer', '2011-03-01', '2015-02-28'),
                                                                                              (28, 'Zoom', 'Video Engineer', '2010-04-01', '2014-03-31'),
                                                                                              (29, 'PayPal', 'Financial Analyst', '2009-05-01', '2013-04-30'),
                                                                                              (30, 'Square', 'Payment Specialist', '2018-06-01', '2022-05-31'),
                                                                                              (31, 'Stripe', 'Risk Analyst', '2015-07-01', '2019-06-30'),
                                                                                              (32, 'Shopify', 'E-commerce Specialist', '2016-08-01', '2020-07-31'),
                                                                                              (33, 'Etsy', 'Marketplace Manager', '2014-09-01', '2018-08-31'),
                                                                                              (34, 'Alibaba', 'Supply Chain Manager', '2017-10-01', '2021-09-30'),
                                                                                              (35, 'Tencent', 'Game Developer', '2013-11-01', '2017-10-31'),
                                                                                              (36, 'Baidu', 'Search Engine Specialist', '2012-12-01', '2016-11-30'),
                                                                                              (37, 'JD.com', 'Logistics Coordinator', '2011-01-01', '2015-12-31'),
                                                                                              (38, 'Huawei', 'Telecom Engineer', '2010-02-01', '2014-01-31'),
                                                                                              (39, 'Xiaomi', 'Product Tester', '2009-03-01', '2013-02-28'),
                                                                                              (40, 'ByteDance', 'Content Creator', '2018-04-01', '2022-03-31'),
                                                                                              (41, 'TikTok', 'Influencer Manager', '2015-05-01', '2019-04-30'),
                                                                                              (42, 'WeChat', 'App Developer', '2016-06-01', '2020-05-31'),
                                                                                              (43, 'Didi', 'Ride-Hailing Specialist', '2014-07-01', '2018-06-30'),
                                                                                              (44, 'Meituan', 'Delivery Coordinator', '2017-08-01', '2021-07-31'),
                                                                                              (45, 'Pinduoduo', 'E-commerce Analyst', '2013-09-01', '2017-08-31'),
                                                                                              (46, 'Kuaishou', 'Video Content Manager', '2012-10-01', '2016-09-30'),
                                                                                              (47, 'Bilibili', 'Animation Specialist', '2011-11-01', '2015-10-31'),
                                                                                              (48, 'ZTE', 'Network Technician', '2010-12-01', '2014-11-30'),
                                                                                              (49, 'Lenovo', 'Hardware Technician', '2009-01-01', '2013-12-31'),
                                                                                              (50, 'Sony', 'Audio Engineer', '2018-02-01', '2022-01-31'),
                                                                                              (51, 'Samsung', 'Mobile Developer', '2015-03-01', '2019-02-28'),
                                                                                              (52, 'LG', 'Display Engineer', '2016-04-01', '2020-03-31'),
                                                                                              (53, 'Panasonic', 'Battery Specialist', '2014-05-01', '2018-04-30'),
                                                                                              (54, 'Toshiba', 'Semiconductor Engineer', '2017-06-01', '2021-05-31'),
                                                                                              (55, 'Hitachi', 'Automation Engineer', '2013-07-01', '2017-06-30'),
                                                                                              (56, 'Fujitsu', 'IT Consultant', '2012-08-01', '2016-07-31'),
                                                                                              (57, 'NEC', 'Systems Engineer', '2011-09-01', '2015-08-31'),
                                                                                              (58, 'Mitsubishi', 'Mechanical Technician', '2010-10-01', '2014-09-30'),
                                                                                              (59, 'Nissan', 'Automotive Engineer', '2009-11-01', '2013-10-31'),
                                                                                              (60, 'Toyota', 'Production Manager', '2018-12-01', '2022-11-30'),
                                                                                              (61, 'Honda', 'Quality Control Specialist', '2015-01-01', '2019-12-31'),
                                                                                              (62, 'Mazda', 'Design Engineer', '2016-02-01', '2020-01-31'),
                                                                                              (63, 'Subaru', 'Safety Engineer', '2014-03-01', '2018-02-28'),
                                                                                              (64, 'Mitsubishi', 'Logistics Manager', '2017-04-01', '2021-03-31'),
                                                                                              (65, 'Suzuki', 'Manufacturing Engineer', '2013-05-01', '2017-04-30'),
                                                                                              (66, 'Isuzu', 'Field Service Engineer', '2012-06-01', '2016-05-31'),
                                                                                              (67, 'Daihatsu', 'Test Engineer', '2011-07-01', '2015-06-30'),
                                                                                              (68, 'Yamaha', 'R&D Engineer', '2010-08-01', '2014-07-31'),
                                                                                              (69, 'Kawasaki', 'Production Engineer', '2009-09-01', '2013-08-31'),
                                                                                              (70, 'Mitsubishi', 'Project Engineer', '2018-10-01', '2022-09-30'),
                                                                                              (71, 'Sony', 'Software Developer', '2015-11-01', '2019-10-31'),
                                                                                              (72, 'Panasonic', 'Hardware Developer', '2016-12-01', '2020-11-30'),
                                                                                              (73, 'Toshiba', 'Firmware Engineer', '2014-01-01', '2018-12-31'),
                                                                                              (74, 'Hitachi', 'Systems Analyst', '2017-02-01', '2021-01-31'),
                                                                                              (75, 'Fujitsu', 'Network Administrator', '2013-03-01', '2017-02-28'),
                                                                                              (76, 'NEC', 'Database Administrator', '2012-04-01', '2016-03-31'),
                                                                                              (77, 'Mitsubishi', 'Security Analyst', '2011-05-01', '2015-04-30'),
                                                                                              (78, 'Nissan', 'Cloud Engineer', '2010-06-01', '2014-05-31'),
                                                                                              (79, 'Toyota', 'DevOps Engineer', '2009-07-01', '2013-06-30'),
                                                                                              (80, 'Honda', 'AI Engineer', '2018-08-01', '2022-07-31'),
                                                                                              (81, 'Mazda', 'Machine Learning Engineer', '2015-09-01', '2019-08-31'),
                                                                                              (82, 'Subaru', 'Data Scientist', '2016-10-01', '2020-09-30'),
                                                                                              (83, 'Suzuki', 'Blockchain Developer', '2014-11-01', '2018-10-31'),
                                                                                              (84, 'Isuzu', 'IoT Developer', '2017-12-01', '2021-11-30'),
                                                                                              (85, 'Daihatsu', 'Robotics Engineer', '2013-01-01', '2017-12-31'),
                                                                                              (86, 'Yamaha', 'Automation Specialist', '2012-02-01', '2016-01-31'),
                                                                                              (87, 'Kawasaki', 'Embedded Systems Engineer', '2011-03-01', '2015-02-28'),
                                                                                              (88, 'Mitsubishi', 'Control Systems Engineer', '2010-04-01', '2014-03-31'),
                                                                                              (89, 'Sony', 'Power Systems Engineer', '2009-05-01', '2013-04-30'),
                                                                                              (90, 'Panasonic', 'Energy Systems Engineer', '2018-06-01', '2022-05-31'),
                                                                                              (91, 'Toshiba', 'Renewable Energy Engineer', '2015-07-01', '2019-06-30'),
                                                                                              (92, 'Hitachi', 'Sustainability Specialist', '2016-08-01', '2020-07-31'),
                                                                                              (93, 'Fujitsu', 'Environmental Engineer', '2014-09-01', '2018-08-31'),
                                                                                              (94, 'NEC', 'Climate Change Analyst', '2017-10-01', '2021-09-30'),
                                                                                              (95, 'Mitsubishi', 'Geospatial Analyst', '2013-11-01', '2017-10-31'),
                                                                                              (96, 'Nissan', 'Urban Planner', '2012-12-01', '2016-11-30'),
                                                                                              (97, 'Toyota', 'Civil Engineer', '2011-01-01', '2015-12-31'),
                                                                                              (98, 'Honda', 'Mechanical Engineer', '2010-02-01', '2014-01-31'),
                                                                                              (99, 'Mazda', 'Electrical Engineer', '2009-03-01', '2013-02-28'),
                                                                                              (100, 'Subaru', 'Biomedical Engineer', '2018-04-01', '2022-03-31');
-- Insert data into Habilidad_Experiencia
INSERT INTO Habilidad_Experiencia (nombre, id_experiencia, nivel) VALUES
                                                                      ('Java Programming', 1, 'Avanzado'),
                                                                      ('System Design', 1, 'Intermedio'),
                                                                      ('Project Management', 2, 'Avanzado'),
                                                                      ('Data Analysis', 3, 'Intermedio'),
                                                                      ('SQL', 3, 'Avanzado'),
                                                                      ('Digital Marketing', 4, 'Intermedio'),
                                                                      ('Product Management', 5, 'Avanzado'),
                                                                      ('Agile Methodologies', 5, 'Intermedio'),
                                                                      ('System Administration', 6, 'Avanzado'),
                                                                      ('Hardware Design', 7, 'Intermedio'),
                                                                      ('Embedded Systems', 7, 'Avanzado'),
                                                                      ('Network Security', 8, 'Intermedio'),
                                                                      ('Cloud Computing', 9, 'Avanzado'),
                                                                      ('DevOps', 9, 'Intermedio'),
                                                                      ('Machine Learning', 10, 'Avanzado'),
                                                                      ('Cybersecurity', 11, 'Intermedio'),
                                                                      ('Data Analysis', 12, 'Avanzado'),
                                                                      ('JavaScript', 13, 'Intermedio'),
                                                                      ('Python', 14, 'Avanzado'),
                                                                      ('C++', 15, 'Intermedio'),
                                                                      ('Ruby on Rails', 16, 'Avanzado'),
                                                                      ('HTML/CSS', 17, 'Intermedio'),
                                                                      ('React', 18, 'Avanzado'),
                                                                      ('Angular', 19, 'Intermedio'),
                                                                      ('Vue.js', 20, 'Avanzado'),
                                                                      ('Node.js', 21, 'Intermedio'),
                                                                      ('Express.js', 22, 'Avanzado'),
                                                                      ('Django', 23, 'Intermedio'),
                                                                      ('Flask', 24, 'Avanzado'),
                                                                      ('Spring Boot', 25, 'Intermedio'),
                                                                      ('Kotlin', 26, 'Avanzado'),
                                                                      ('Swift', 27, 'Intermedio'),
                                                                      ('Objective-C', 28, 'Avanzado'),
                                                                      ('Go', 29, 'Intermedio'),
                                                                      ('Rust', 30, 'Avanzado'),
                                                                      ('Scala', 31, 'Intermedio'),
                                                                      ('Elixir', 32, 'Avanzado'),
                                                                      ('Haskell', 33, 'Intermedio'),
                                                                      ('Perl', 34, 'Avanzado'),
                                                                      ('PHP', 35, 'Intermedio'),
                                                                      ('Laravel', 36, 'Avanzado'),
                                                                      ('Symfony', 37, 'Intermedio'),
                                                                      ('CodeIgniter', 38, 'Avanzado'),
                                                                      ('CakePHP', 39, 'Intermedio'),
                                                                      ('Zend Framework', 40, 'Avanzado'),
                                                                      ('ASP.NET', 41, 'Intermedio'),
                                                                      ('C#', 42, 'Avanzado'),
                                                                      ('VB.NET', 43, 'Intermedio'),
                                                                      ('F#', 44, 'Avanzado'),
                                                                      ('SQL Server', 45, 'Intermedio'),
                                                                      ('MySQL', 46, 'Avanzado'),
                                                                      ('PostgreSQL', 47, 'Intermedio'),
                                                                      ('MongoDB', 48, 'Avanzado'),
                                                                      ('Redis', 49, 'Intermedio'),
                                                                      ('Cassandra', 50, 'Avanzado'),
                                                                      ('Elasticsearch', 51, 'Intermedio'),
                                                                      ('Solr', 52, 'Avanzado'),
                                                                      ('Hadoop', 53, 'Intermedio'),
                                                                      ('Spark', 54, 'Avanzado'),
                                                                      ('Kafka', 55, 'Intermedio'),
                                                                      ('RabbitMQ', 56, 'Avanzado'),
                                                                      ('ActiveMQ', 57, 'Intermedio'),
                                                                      ('ZeroMQ', 58, 'Avanzado'),
                                                                      ('Docker', 59, 'Intermedio'),
                                                                      ('Kubernetes', 60, 'Avanzado'),
                                                                      ('OpenShift', 61, 'Intermedio'),
                                                                      ('Rancher', 62, 'Avanzado'),
                                                                      ('Terraform', 63, 'Intermedio'),
                                                                      ('Ansible', 64, 'Avanzado'),
                                                                      ('Puppet', 65, 'Intermedio'),
                                                                      ('Chef', 66, 'Avanzado'),
                                                                      ('SaltStack', 67, 'Intermedio'),
                                                                      ('Vagrant', 68, 'Avanzado'),
                                                                      ('Jenkins', 69, 'Intermedio'),
                                                                      ('Travis CI', 70, 'Avanzado'),
                                                                      ('CircleCI', 71, 'Intermedio'),
                                                                      ('GitLab CI', 72, 'Avanzado'),
                                                                      ('Bamboo', 73, 'Intermedio'),
                                                                      ('TeamCity', 74, 'Avanzado'),
                                                                      ('Nagios', 75, 'Intermedio'),
                                                                      ('Zabbix', 76, 'Avanzado'),
                                                                      ('Prometheus', 77, 'Intermedio'),
                                                                      ('Grafana', 78, 'Avanzado'),
                                                                      ('Splunk', 79, 'Intermedio'),
                                                                      ('ELK Stack', 80, 'Avanzado'),
                                                                      ('New Relic', 81, 'Intermedio'),
                                                                      ('AppDynamics', 82, 'Avanzado'),
                                                                      ('Dynatrace', 83, 'Intermedio'),
                                                                      ('Datadog', 84, 'Avanzado'),
                                                                      ('Sentry', 85, 'Intermedio'),
                                                                      ('Rollbar', 86, 'Avanzado'),
                                                                      ('Raygun', 87, 'Intermedio'),
                                                                      ('Honeybadger', 88, 'Avanzado'),
                                                                      ('Bugsnag', 89, 'Intermedio'),
                                                                      ('Airbrake', 90, 'Avanzado'),
                                                                      ('Loggly', 91, 'Intermedio'),
                                                                      ('Papertrail', 92, 'Avanzado'),
                                                                      ('Sumo Logic', 93, 'Intermedio'),
                                                                      ('Logz.io', 94, 'Avanzado'),
                                                                      ('Graylog', 95, 'Intermedio'),
                                                                      ('Fluentd', 96, 'Avanzado'),
                                                                      ('Logstash', 97, 'Intermedio'),
                                                                      ('Filebeat', 98, 'Avanzado'),
                                                                      ('Metricbeat', 99, 'Intermedio'),
                                                                      ('Heartbeat', 100, 'Avanzado');
-- Insert data into Idioma
INSERT INTO Idioma (nombre) VALUES
                                ('English'),
                                ('Spanish'),
                                ('French'),
                                ('German'),
                                ('Chinese');

-- Insert data into Idioma_Postulante
INSERT INTO Idioma_Postulante (id_postulante, id_idioma, nivel) VALUES
                                                                    -- Postulante 1 with 2 languages
                                                                    (1, 1, 'Básico'),
                                                                    (1, 2, 'Intermedio'),

                                                                    -- Postulante 2 with 1 language
                                                                    (2, 3, 'Avanzado'),

                                                                    -- Postulante 3 with 2 languages
                                                                    (3, 4, 'Básico'),
                                                                    (3, 5, 'Intermedio'),

                                                                    -- Postulante 4 with 1 language
                                                                    (4, 1, 'Avanzado'),

                                                                    -- Postulante 5 with 1 language
                                                                    (5, 2, 'Básico'),

                                                                    -- Postulante 6 with 2 languages
                                                                    (6, 3, 'Intermedio'),
                                                                    (6, 4, 'Avanzado'),

                                                                    -- Postulante 7 with 1 language
                                                                    (7, 5, 'Básico'),

                                                                    -- Postulante 8 with 2 languages
                                                                    (8, 1, 'Intermedio'),
                                                                    (8, 2, 'Avanzado'),

                                                                    -- Postulante 9 with 1 language
                                                                    (9, 3, 'Básico'),

                                                                    -- Postulante 10 with 1 language
                                                                    (10, 4, 'Intermedio'),

                                                                    -- Postulante 11 with 2 languages
                                                                    (11, 5, 'Avanzado'),
                                                                    (11, 1, 'Básico'),

                                                                    -- Postulante 12 with 1 language
                                                                    (12, 2, 'Intermedio'),

                                                                    -- Postulante 13 with 2 languages
                                                                    (13, 3, 'Avanzado'),
                                                                    (13, 4, 'Básico'),

                                                                    -- Postulante 14 with 1 language
                                                                    (14, 5, 'Intermedio'),

                                                                    -- Postulante 15 with 1 language
                                                                    (15, 1, 'Avanzado'),

                                                                    -- Postulante 16 with 2 languages
                                                                    (16, 2, 'Básico'),
                                                                    (16, 3, 'Intermedio'),

                                                                    -- Postulante 17 with 1 language
                                                                    (17, 4, 'Avanzado'),

                                                                    -- Postulante 18 with 2 languages
                                                                    (18, 5, 'Básico'),
                                                                    (18, 1, 'Intermedio'),

                                                                    -- Postulante 19 with 1 language
                                                                    (19, 2, 'Avanzado'),

                                                                    -- Postulante 20 with 1 language
                                                                    (20, 3, 'Básico'),

                                                                    -- Postulante 21 with 2 languages
                                                                    (21, 4, 'Intermedio'),
                                                                    (21, 5, 'Avanzado'),

                                                                    -- Postulante 22 with 1 language
                                                                    (22, 1, 'Básico'),

                                                                    -- Postulante 23 with 2 languages
                                                                    (23, 2, 'Intermedio'),
                                                                    (23, 3, 'Avanzado'),

                                                                    -- Postulante 24 with 1 language
                                                                    (24, 4, 'Básico'),

                                                                    -- Postulante 25 with 1 language
                                                                    (25, 5, 'Intermedio'),

                                                                    -- Postulante 26 with 2 languages
                                                                    (26, 1, 'Avanzado'),
                                                                    (26, 2, 'Básico'),

                                                                    -- Postulante 27 with 1 language
                                                                    (27, 3, 'Intermedio'),

                                                                    -- Postulante 28 with 2 languages
                                                                    (28, 4, 'Avanzado'),
                                                                    (28, 5, 'Básico'),

                                                                    -- Postulante 29 with 1 language
                                                                    (29, 1, 'Intermedio'),

                                                                    -- Postulante 30 with 1 language
                                                                    (30, 2, 'Avanzado'),

                                                                    -- Postulante 31 with 2 languages
                                                                    (31, 3, 'Básico'),
                                                                    (31, 4, 'Intermedio'),

                                                                    -- Postulante 32 with 1 language
                                                                    (32, 5, 'Avanzado'),

                                                                    -- Postulante 33 with 2 languages
                                                                    (33, 1, 'Básico'),
                                                                    (33, 2, 'Intermedio'),

                                                                    -- Postulante 34 with 1 language
                                                                    (34, 3, 'Avanzado'),

                                                                    -- Postulante 35 with 1 language
                                                                    (35, 4, 'Básico'),

                                                                    -- Postulante 36 with 2 languages
                                                                    (36, 5, 'Intermedio'),
                                                                    (36, 1, 'Avanzado'),

                                                                    -- Postulante 37 with 1 language
                                                                    (37, 2, 'Básico'),

                                                                    -- Postulante 38 with 2 languages
                                                                    (38, 3, 'Intermedio'),
                                                                    (38, 4, 'Avanzado'),

                                                                    -- Postulante 39 with 1 language
                                                                    (39, 5, 'Básico'),

                                                                    -- Postulante 40 with 1 language
                                                                    (40, 1, 'Intermedio'),

                                                                    -- Postulante 41 with 2 languages
                                                                    (41, 2, 'Avanzado'),
                                                                    (41, 3, 'Básico'),

                                                                    -- Postulante 42 with 1 language
                                                                    (42, 4, 'Intermedio'),

                                                                    -- Postulante 43 with 2 languages
                                                                    (43, 5, 'Avanzado'),
                                                                    (43, 1, 'Básico'),

                                                                    -- Postulante 44 with 1 language
                                                                    (44, 2, 'Intermedio'),

                                                                    -- Postulante 45 with 1 language
                                                                    (45, 3, 'Avanzado'),

                                                                    -- Postulante 46 with 2 languages
                                                                    (46, 4, 'Básico'),
                                                                    (46, 5, 'Intermedio'),

                                                                    -- Postulante 47 with 1 language
                                                                    (47, 1, 'Avanzado'),

                                                                    -- Postulante 48 with 2 languages
                                                                    (48, 2, 'Básico'),
                                                                    (48, 3, 'Intermedio'),

                                                                    -- Postulante 49 with 1 language
                                                                    (49, 4, 'Avanzado'),

                                                                    -- Postulante 50 with 1 language
                                                                    (50, 5, 'Básico'),

                                                                    -- Postulante 51 with 2 languages
                                                                    (51, 1, 'Intermedio'),
                                                                    (51, 2, 'Avanzado'),

                                                                    -- Postulante 52 with 1 language
                                                                    (52, 3, 'Básico'),

                                                                    -- Postulante 53 with 2 languages
                                                                    (53, 4, 'Intermedio'),
                                                                    (53, 5, 'Avanzado'),

                                                                    -- Postulante 54 with 1 language
                                                                    (54, 1, 'Básico'),

                                                                    -- Postulante 55 with 1 language
                                                                    (55, 2, 'Intermedio'),

                                                                    -- Postulante 56 with 2 languages
                                                                    (56, 3, 'Avanzado'),
                                                                    (56, 4, 'Básico'),

                                                                    -- Postulante 57 with 1 language
                                                                    (57, 5, 'Intermedio'),

                                                                    -- Postulante 58 with 1 language
                                                                    (58, 1, 'Avanzado'),

                                                                    -- Postulante 59 with 2 languages
                                                                    (59, 2, 'Básico'),
                                                                    (59, 3, 'Intermedio'),

                                                                    -- Postulante 60 with 1 language
                                                                    (60, 4, 'Avanzado'),

                                                                    -- Postulante 61 with 2 languages
                                                                    (61, 5, 'Básico'),
                                                                    (61, 1, 'Intermedio'),

                                                                    -- Postulante 62 with 1 language
                                                                    (62, 2, 'Avanzado'),

                                                                    -- Postulante 63 with 1 language
                                                                    (63, 3, 'Básico'),

                                                                    -- Postulante 64 with 2 languages
                                                                    (64, 4, 'Intermedio'),
                                                                    (64, 5, 'Avanzado'),

                                                                    -- Postulante 65 with 1 language
                                                                    (65, 1, 'Básico'),

                                                                    -- Postulante 66 with 2 languages
                                                                    (66, 2, 'Intermedio'),
                                                                    (66, 3, 'Avanzado'),

                                                                    -- Postulante 67 with 1 language
                                                                    (67, 4, 'Básico'),

                                                                    -- Postulante 68 with 1 language
                                                                    (68, 5, 'Intermedio'),

                                                                    -- Postulante 69 with 2 languages
                                                                    (69, 1, 'Avanzado'),
                                                                    (69, 2, 'Básico'),

                                                                    -- Postulante 70 with 1 language
                                                                    (70, 3, 'Intermedio'),

                                                                    -- Postulante 71 with 2 languages
                                                                    (71, 4, 'Avanzado'),
                                                                    (71, 5, 'Básico'),

                                                                    -- Postulante 72 with 1 language
                                                                    (72, 1, 'Intermedio'),

                                                                    -- Postulante 73 with 1 language
                                                                    (73, 2, 'Avanzado'),

                                                                    -- Postulante 74 with 2 languages
                                                                    (74, 3, 'Básico'),
                                                                    (74, 4, 'Intermedio'),

                                                                    -- Postulante 75 with 1 language
                                                                    (75, 5, 'Avanzado'),

                                                                    -- Postulante 76 with 2 languages
                                                                    (76, 1, 'Básico'),
                                                                    (76, 2, 'Intermedio'),

                                                                    -- Postulante 77 with 1 language
                                                                    (77, 3, 'Avanzado'),

                                                                    -- Postulante 78 with 2 languages
                                                                    (78, 4, 'Básico'),
                                                                    (78, 5, 'Intermedio'),

                                                                    -- Postulante 79 with 1 language
                                                                    (79, 1, 'Avanzado'),

                                                                    -- Postulante 80 with 1 language
                                                                    (80, 2, 'Básico'),

                                                                    -- Postulante 81 with 2 languages
                                                                    (81, 3, 'Intermedio'),
                                                                    (81, 4, 'Avanzado'),

                                                                    -- Postulante 82 with 1 language
                                                                    (82, 5, 'Básico'),

                                                                    -- Postulante 83 with 2 languages
                                                                    (83, 1, 'Intermedio'),
                                                                    (83, 2, 'Avanzado'),

                                                                    -- Postulante 84 with 1 language
                                                                    (84, 3, 'Básico'),

                                                                    -- Postulante 85 with 1 language
                                                                    (85, 4, 'Intermedio'),

                                                                    -- Postulante 86 with 2 languages
                                                                    (86, 5, 'Avanzado'),
                                                                    (86, 1, 'Básico'),

                                                                    -- Postulante 87 with 1 language
                                                                    (87, 2, 'Intermedio'),

                                                                    -- Postulante 88 with 2 languages
                                                                    (88, 3, 'Avanzado'),
                                                                    (88, 4, 'Básico'),

                                                                    -- Postulante 89 with 1 language
                                                                    (89, 5, 'Intermedio'),

                                                                    -- Postulante 90 with 1 language
                                                                    (90, 1, 'Avanzado'),

                                                                    -- Postulante 91 with 2 languages
                                                                    (91, 2, 'Básico'),
                                                                    (91, 3, 'Intermedio'),

                                                                    -- Postulante 92 with 1 language
                                                                    (92, 4, 'Avanzado'),

                                                                    -- Postulante 93 with 2 languages
                                                                    (93, 5, 'Básico'),
                                                                    (93, 1, 'Intermedio'),

                                                                    -- Postulante 94 with 1 language
                                                                    (94, 2, 'Avanzado'),

                                                                    -- Postulante 95 with 1 language
                                                                    (95, 3, 'Básico'),

                                                                    -- Postulante 96 with 2 languages
                                                                    (96, 4, 'Intermedio'),
                                                                    (96, 5, 'Avanzado'),

                                                                    -- Postulante 97 with 1 language
                                                                    (97, 1, 'Básico'),

                                                                    -- Postulante 98 with 2 languages
                                                                    (98, 2, 'Intermedio'),
                                                                    (98, 3, 'Avanzado'),

                                                                    -- Postulante 99 with 1 language
                                                                    (99, 4, 'Básico'),

                                                                    -- Postulante 100 with 1 language
                                                                    (100, 5, 'Intermedio');

-- Insert data into Habilidad_Postulante
INSERT INTO Habilidad_Postulante (id_postulante, nombre, nivel) VALUES
                                                                    -- Postulante 1 with 2 skills
                                                                    (1, 'Project Management', 'Básico'),
                                                                    (1, 'Data Analysis', 'Intermedio'),

                                                                    -- Postulante 2 with 1 skill
                                                                    (2, 'Software Development', 'Avanzado'),

                                                                    -- Postulante 3 with 3 skills
                                                                    (3, 'Graphic Design', 'Básico'),
                                                                    (3, 'SEO Optimization', 'Intermedio'),
                                                                    (3, 'Content Writing', 'Avanzado'),

                                                                    -- Postulante 4 with 1 skill
                                                                    (4, 'Customer Service', 'Básico'),

                                                                    -- Postulante 5 with 2 skills
                                                                    (5, 'Digital Marketing', 'Intermedio'),
                                                                    (5, 'Social Media Management', 'Avanzado'),

                                                                    -- Postulante 6 with 1 skill
                                                                    (6, 'Network Security', 'Básico'),

                                                                    -- Postulante 7 with 3 skills
                                                                    (7, 'Database Management', 'Intermedio'),
                                                                    (7, 'Cloud Computing', 'Avanzado'),
                                                                    (7, 'Technical Support', 'Básico'),

                                                                    -- Postulante 8 with 1 skill
                                                                    (8, 'Business Analysis', 'Intermedio'),

                                                                    -- Postulante 9 with 2 skills
                                                                    (9, 'Web Development', 'Avanzado'),
                                                                    (9, 'UI/UX Design', 'Básico'),

                                                                    -- Postulante 10 with 1 skill
                                                                    (10, 'Cybersecurity', 'Intermedio'),

                                                                    -- Postulante 11 with 3 skills
                                                                    (11, 'Machine Learning', 'Avanzado'),
                                                                    (11, 'Artificial Intelligence', 'Básico'),
                                                                    (11, 'Data Science', 'Intermedio'),

                                                                    -- Postulante 12 with 1 skill
                                                                    (12, 'IT Support', 'Avanzado'),

                                                                    -- Postulante 13 with 2 skills
                                                                    (13, 'Mobile App Development', 'Básico'),
                                                                    (13, 'Game Development', 'Intermedio'),

                                                                    -- Postulante 14 with 1 skill
                                                                    (14, 'Network Administration', 'Avanzado'),

                                                                    -- Postulante 15 with 3 skills
                                                                    (15, 'System Administration', 'Básico'),
                                                                    (15, 'DevOps', 'Intermedio'),
                                                                    (15, 'Agile Methodologies', 'Avanzado'),

                                                                    -- Postulante 16 with 1 skill
                                                                    (16, 'Technical Writing', 'Básico'),

                                                                    -- Postulante 17 with 2 skills
                                                                    (17, 'Quality Assurance', 'Intermedio'),
                                                                    (17, 'Software Testing', 'Avanzado'),

                                                                    -- Postulante 18 with 1 skill
                                                                    (18, 'IT Consulting', 'Básico'),

                                                                    -- Postulante 19 with 3 skills
                                                                    (19, 'Blockchain', 'Intermedio'),
                                                                    (19, 'Cryptography', 'Avanzado'),
                                                                    (19, 'Smart Contracts', 'Básico'),

                                                                    -- Postulante 20 with 1 skill
                                                                    (20, 'Robotics', 'Intermedio'),

                                                                    -- Postulante 21 with 2 skills
                                                                    (21, 'Embedded Systems', 'Avanzado'),
                                                                    (21, 'IoT', 'Básico'),

                                                                    -- Postulante 22 with 1 skill
                                                                    (22, 'Big Data', 'Intermedio'),

                                                                    -- Postulante 23 with 3 skills
                                                                    (23, 'Virtual Reality', 'Avanzado'),
                                                                    (23, 'Augmented Reality', 'Básico'),
                                                                    (23, '3D Modeling', 'Intermedio'),

                                                                    -- Postulante 24 with 1 skill
                                                                    (24, 'E-commerce', 'Avanzado'),

                                                                    -- Postulante 25 with 2 skills
                                                                    (25, 'Supply Chain Management', 'Básico'),
                                                                    (25, 'Logistics', 'Intermedio'),

                                                                    -- Postulante 26 with 1 skill
                                                                    (26, 'Human Resources', 'Avanzado'),

                                                                    -- Postulante 27 with 3 skills
                                                                    (27, 'Recruitment', 'Básico'),
                                                                    (27, 'Employee Relations', 'Intermedio'),
                                                                    (27, 'Training and Development', 'Avanzado'),

                                                                    -- Postulante 28 with 1 skill
                                                                    (28, 'Performance Management', 'Básico'),

                                                                    -- Postulante 29 with 2 skills
                                                                    (29, 'Compensation and Benefits', 'Intermedio'),
                                                                    (29, 'Organizational Development', 'Avanzado'),

                                                                    -- Postulante 30 with 1 skill
                                                                    (30, 'Change Management', 'Básico'),

                                                                    -- Postulante 31 with 3 skills
                                                                    (31, 'Leadership', 'Intermedio'),
                                                                    (31, 'Team Building', 'Avanzado'),
                                                                    (31, 'Conflict Resolution', 'Básico'),

                                                                    -- Postulante 32 with 1 skill
                                                                    (32, 'Strategic Planning', 'Intermedio'),

                                                                    -- Postulante 33 with 2 skills
                                                                    (33, 'Financial Analysis', 'Avanzado'),
                                                                    (33, 'Budgeting', 'Básico'),

                                                                    -- Postulante 34 with 1 skill
                                                                    (34, 'Accounting', 'Intermedio'),

                                                                    -- Postulante 35 with 3 skills
                                                                    (35, 'Taxation', 'Avanzado'),
                                                                    (35, 'Auditing', 'Básico'),
                                                                    (35, 'Financial Reporting', 'Intermedio'),

                                                                    -- Postulante 36 with 1 skill
                                                                    (36, 'Investment Management', 'Avanzado'),

                                                                    -- Postulante 37 with 2 skills
                                                                    (37, 'Risk Management', 'Básico'),
                                                                    (37, 'Insurance', 'Intermedio'),

                                                                    -- Postulante 38 with 1 skill
                                                                    (38, 'Corporate Finance', 'Avanzado'),

                                                                    -- Postulante 39 with 3 skills
                                                                    (39, 'Mergers and Acquisitions', 'Básico'),
                                                                    (39, 'Venture Capital', 'Intermedio'),
                                                                    (39, 'Private Equity', 'Avanzado'),

                                                                    -- Postulante 40 with 1 skill
                                                                    (40, 'Real Estate', 'Básico'),

                                                                    -- Postulante 41 with 2 skills
                                                                    (41, 'Wealth Management', 'Intermedio'),
                                                                    (41, 'Estate Planning', 'Avanzado'),

                                                                    -- Postulante 42 with 1 skill
                                                                    (42, 'Financial Planning', 'Básico'),

                                                                    -- Postulante 43 with 3 skills
                                                                    (43, 'Credit Analysis', 'Intermedio'),
                                                                    (43, 'Loan Processing', 'Avanzado'),
                                                                    (43, 'Mortgage Lending', 'Básico'),

                                                                    -- Postulante 44 with 1 skill
                                                                    (44, 'Banking Operations', 'Intermedio'),

                                                                    -- Postulante 45 with 2 skills
                                                                    (45, 'Customer Relationship Management', 'Avanzado'),
                                                                    (45, 'Sales Management', 'Básico'),

                                                                    -- Postulante 46 with 1 skill
                                                                    (46, 'Retail Management', 'Intermedio'),

                                                                    -- Postulante 47 with 3 skills
                                                                    (47, 'Merchandising', 'Avanzado'),
                                                                    (47, 'Inventory Management', 'Básico'),
                                                                    (47, 'Store Operations', 'Intermedio'),

                                                                    -- Postulante 48 with 1 skill
                                                                    (48, 'Visual Merchandising', 'Avanzado'),

                                                                    -- Postulante 49 with 2 skills
                                                                    (49, 'Product Management', 'Básico'),
                                                                    (49, 'Brand Management', 'Intermedio'),

                                                                    -- Postulante 50 with 1 skill
                                                                    (50, 'Market Research', 'Avanzado'),

                                                                    -- Postulante 51 with 3 skills
                                                                    (51, 'Advertising', 'Básico'),
                                                                    (51, 'Public Relations', 'Intermedio'),
                                                                    (51, 'Event Planning', 'Avanzado'),

                                                                    -- Postulante 52 with 1 skill
                                                                    (52, 'Copywriting', 'Básico'),

                                                                    -- Postulante 53 with 2 skills
                                                                    (53, 'Media Buying', 'Intermedio'),
                                                                    (53, 'Campaign Management', 'Avanzado'),

                                                                    -- Postulante 54 with 1 skill
                                                                    (54, 'Creative Direction', 'Básico'),

                                                                    -- Postulante 55 with 3 skills
                                                                    (55, 'Art Direction', 'Intermedio'),
                                                                    (55, 'Photography', 'Avanzado'),
                                                                    (55, 'Video Production', 'Básico'),

                                                                    -- Postulante 56 with 1 skill
                                                                    (56, 'Animation', 'Intermedio'),

                                                                    -- Postulante 57 with 2 skills
                                                                    (57, 'Illustration', 'Avanzado'),
                                                                    (57, 'Typography', 'Básico'),

                                                                    -- Postulante 58 with 1 skill
                                                                    (58, 'Web Design', 'Intermedio'),

                                                                    -- Postulante 59 with 3 skills
                                                                    (59, 'User Research', 'Avanzado'),
                                                                    (59, 'Prototyping', 'Básico'),
                                                                    (59, 'Wireframing', 'Intermedio'),

                                                                    -- Postulante 60 with 1 skill
                                                                    (60, 'Interaction Design', 'Avanzado'),

                                                                    -- Postulante 61 with 2 skills
                                                                    (61, 'Usability Testing', 'Básico'),
                                                                    (61, 'Information Architecture', 'Intermedio'),

                                                                    -- Postulante 62 with 1 skill
                                                                    (62, 'Front-end Development', 'Avanzado'),

                                                                    -- Postulante 63 with 3 skills
                                                                    (63, 'Back-end Development', 'Básico'),
                                                                    (63, 'Full-stack Development', 'Intermedio'),
                                                                    (63, 'API Development', 'Avanzado'),

                                                                    -- Postulante 64 with 1 skill
                                                                    (64, 'DevOps Engineering', 'Básico'),

                                                                    -- Postulante 65 with 2 skills
                                                                    (65, 'Continuous Integration', 'Intermedio'),
                                                                    (65, 'Continuous Deployment', 'Avanzado'),

                                                                    -- Postulante 66 with 1 skill
                                                                    (66, 'Containerization', 'Básico'),

                                                                    -- Postulante 67 with 3 skills
                                                                    (67, 'Microservices', 'Intermedio'),
                                                                    (67, 'Serverless Architecture', 'Avanzado'),
                                                                    (67, 'Infrastructure as Code', 'Básico'),

                                                                    -- Postulante 68 with 1 skill
                                                                    (68, 'Network Engineering', 'Intermedio'),

                                                                    -- Postulante 69 with 2 skills
                                                                    (69, 'System Architecture', 'Avanzado'),
                                                                    (69, 'IT Infrastructure', 'Básico'),

                                                                    -- Postulante 70 with 1 skill
                                                                    (70, 'Cloud Architecture', 'Intermedio'),

                                                                    -- Postulante 71 with 3 skills
                                                                    (71, 'Data Engineering', 'Avanzado'),
                                                                    (71, 'Data Warehousing', 'Básico'),
                                                                    (71, 'ETL Development', 'Intermedio'),

                                                                    -- Postulante 72 with 1 skill
                                                                    (72, 'Data Visualization', 'Avanzado'),

                                                                    -- Postulante 73 with 2 skills
                                                                    (73, 'Business Intelligence', 'Básico'),
                                                                    (73, 'Data Mining', 'Intermedio'),

                                                                    -- Postulante 74 with 1 skill
                                                                    (74, 'Predictive Analytics', 'Avanzado'),

                                                                    -- Postulante 75 with 3 skills
                                                                    (75, 'Statistical Analysis', 'Básico'),
                                                                    (75, 'Machine Learning', 'Intermedio'),
                                                                    (75, 'Deep Learning', 'Avanzado'),

                                                                    -- Postulante 76 with 1 skill
                                                                    (76, 'Natural Language Processing', 'Básico'),

                                                                    -- Postulante 77 with 2 skills
                                                                    (77, 'Computer Vision', 'Intermedio'),
                                                                    (77, 'Reinforcement Learning', 'Avanzado'),

                                                                    -- Postulante 78 with 1 skill
                                                                    (78, 'Robotic Process Automation', 'Básico'),

                                                                    -- Postulante 79 with 3 skills
                                                                    (79, 'Quantum Computing', 'Intermedio'),
                                                                    (79, 'Edge Computing', 'Avanzado'),
                                                                    (79, 'Fog Computing', 'Básico'),

                                                                    -- Postulante 80 with 1 skill
                                                                    (80, '5G Technology', 'Intermedio'),

                                                                    -- Postulante 81 with 2 skills
                                                                    (81, 'Autonomous Vehicles', 'Avanzado'),
                                                                    (81, 'Drones', 'Básico'),

                                                                    -- Postulante 82 with 1 skill
                                                                    (82, 'Wearable Technology', 'Intermedio'),

                                                                    -- Postulante 83 with 3 skills
                                                                    (83, 'Smart Home Technology', 'Avanzado'),
                                                                    (83, 'Smart Cities', 'Básico'),
                                                                    (83, 'Smart Grid', 'Intermedio'),

                                                                    -- Postulante 84 with 1 skill
                                                                    (84, 'Renewable Energy', 'Avanzado'),

                                                                    -- Postulante 85 with 2 skills
                                                                    (85, 'Energy Storage', 'Básico'),
                                                                    (85, 'Energy Management', 'Intermedio'),

                                                                    -- Postulante 86 with 1 skill
                                                                    (86, 'Sustainable Development', 'Avanzado'),

                                                                    -- Postulante 87 with 3 skills
                                                                    (87, 'Environmental Science', 'Básico'),
                                                                    (87, 'Climate Change', 'Intermedio'),
                                                                    (87, 'Ecology', 'Avanzado'),

                                                                    -- Postulante 88 with 1 skill
                                                                    (88, 'Geospatial Analysis', 'Básico'),

                                                                    -- Postulante 89 with 2 skills
                                                                    (89, 'Urban Planning', 'Intermedio'),
                                                                    (89, 'Architecture', 'Avanzado'),

                                                                    -- Postulante 90 with 1 skill
                                                                    (90, 'Civil Engineering', 'Básico'),

                                                                    -- Postulante 91 with 3 skills
                                                                    (91, 'Mechanical Engineering', 'Intermedio'),
                                                                    (91, 'Electrical Engineering', 'Avanzado'),
                                                                    (91, 'Chemical Engineering', 'Básico'),

                                                                    -- Postulante 92 with 1 skill
                                                                    (92, 'Biomedical Engineering', 'Intermedio'),

                                                                    -- Postulante 93 with 2 skills
                                                                    (93, 'Aerospace Engineering', 'Avanzado'),
                                                                    (93, 'Marine Engineering', 'Básico'),

                                                                    -- Postulante 94 with 1 skill
                                                                    (94, 'Nuclear Engineering', 'Intermedio'),

                                                                    -- Postulante 95 with 3 skills
                                                                    (95, 'Materials Science', 'Avanzado'),
                                                                    (95, 'Nanotechnology', 'Básico'),
                                                                    (95, 'Biotechnology', 'Intermedio'),

                                                                    -- Postulante 96 with 1 skill
                                                                    (96, 'Pharmaceuticals', 'Avanzado'),

                                                                    -- Postulante 97 with 2 skills
                                                                    (97, 'Medical Devices', 'Básico'),
                                                                    (97, 'Healthcare Management', 'Intermedio'),

                                                                    -- Postulante 98 with 1 skill
                                                                    (98, 'Public Health', 'Avanzado'),

                                                                    -- Postulante 99 with 3 skills
                                                                    (99, 'Epidemiology', 'Básico'),
                                                                    (99, 'Health Informatics', 'Intermedio'),
                                                                    (99, 'Telemedicine', 'Avanzado'),

                                                                    -- Postulante 100 with 1 skill
                                                                    (100, 'Genomics', 'Básico');

-- Insert data into Oferta_Laboral
INSERT INTO Oferta_Laboral (id_postulante, id_vacante, fecha_oferta, fecha_inicio_propuesta, link_documento_legal_sin_firma, link_documento_legal_con_firma, estado) VALUES
                                                                                                                                                                         -- Offers for Vacante 1
                                                                                                                                                                         (1, 1, '2023-01-01', '2023-02-01', 'link1_sin_firma', 'link1_con_firma', 'Pendiente'),
                                                                                                                                                                         (2, 1, '2023-01-02', '2023-02-02', 'link2_sin_firma', 'link2_con_firma', 'Aceptada'),

                                                                                                                                                                         -- Offers for Vacante 2
                                                                                                                                                                         (3, 2, '2023-02-01', '2023-03-01', 'link3_sin_firma', 'link3_con_firma', 'Rechazada'),
                                                                                                                                                                         (4, 2, '2023-02-02', '2023-03-02', 'link4_sin_firma', 'link4_con_firma', 'Pendiente'),

                                                                                                                                                                         -- Offers for Vacante 3
                                                                                                                                                                         (5, 3, '2023-03-01', '2023-04-01', 'link5_sin_firma', 'link5_con_firma', 'Aceptada'),
                                                                                                                                                                         (6, 3, '2023-03-02', '2023-04-02', 'link6_sin_firma', 'link6_con_firma', 'Rechazada'),

                                                                                                                                                                         -- Offers for Vacante 4
                                                                                                                                                                         (7, 4, '2023-04-01', '2023-05-01', 'link7_sin_firma', 'link7_con_firma', 'Pendiente'),
                                                                                                                                                                         (8, 4, '2023-04-02', '2023-05-02', 'link8_sin_firma', 'link8_con_firma', 'Aceptada'),

                                                                                                                                                                         -- Offers for Vacante 5
                                                                                                                                                                         (9, 5, '2023-05-01', '2023-06-01', 'link9_sin_firma', 'link9_con_firma', 'Rechazada'),
                                                                                                                                                                         (10, 5, '2023-05-02', '2023-06-02', 'link10_sin_firma', 'link10_con_firma', 'Pendiente'),

                                                                                                                                                                         -- Continue for all vacancies up to the required number
                                                                                                                                                                         (11, 6, '2023-06-01', '2023-07-01', 'link11_sin_firma', 'link11_con_firma', 'Aceptada'),
                                                                                                                                                                         (12, 6, '2023-06-02', '2023-07-02', 'link12_sin_firma', 'link12_con_firma', 'Rechazada');
-- Insert data into Beneficio
INSERT INTO Beneficio (descripcion) VALUES
                                        ('Seguro de salud'),
                                        ('Bono de desempeño'),
                                        ('Vacaciones pagadas'),
                                        ('Plan de pensiones'),
                                        ('Capacitación y desarrollo'),
                                        ('Horario flexible'),
                                        ('Trabajo remoto'),
                                        ('Descuentos en productos'),
                                        ('Gimnasio en la empresa'),
                                        ('Comedor subsidiado');

-- Insert data into Oferta_Laboral_Beneficio
INSERT INTO Oferta_Laboral_Beneficio (id_oferta, id_beneficio) VALUES
                                                                   -- Assigning multiple benefits to some offers
                                                                   (1, 1), (1, 2), (1, 3),
                                                                   (2, 4), (2, 5),
                                                                   (3, 6), (3, 7), (3, 8),
                                                                   (4, 9), (4, 10),
                                                                   (5, 1), (5, 2),
                                                                   (6, 3), (6, 4), (6, 5),
                                                                   (7, 6), (7, 7),
                                                                   (8, 8), (8, 9), (8, 10),
                                                                   (9, 1), (9, 2), (9, 3),
                                                                   (10, 4), (10, 5);

-- Insert data into Tipo_entrevista
INSERT INTO Tipo_entrevista (nombre) VALUES
                                         ('Technical Interview'),
                                         ('HR Interview'),
                                         ('Behavioral Interview'),
                                         ('Case Study Interview'),
                                         ('Panel Interview'),
                                         ('Group Interview'),
                                         ('Phone Interview'),
                                         ('Video Interview'),
                                         ('On-site Interview'),
                                         ('Final Interview');

-- Insert data into Entrevista
INSERT INTO Entrevista (estado, fecha, puntaje_general, id_postulante, ID_empleado, id_feedback, id_tipo_entrevista) VALUES
                                                                                                                         -- Interviews for all 100 applicants
                                                                                                                         ('Pendiente', '2023-01-01', 80, 1, 1, 1, 1),
                                                                                                                         ('Hecha', '2023-01-02', 85, 1, 2, 2, 2),
                                                                                                                         ('Rechazada', '2023-02-01', 90, 2, 3, 3, 3),
                                                                                                                         ('Pendiente', '2023-02-02', 75, 2, 4, 4, 4),
                                                                                                                         ('Hecha', '2023-03-01', 95, 3, 5, 5, 5),
                                                                                                                         ('Pendiente', '2023-03-02', 80, 3, 6, 6, 6),
                                                                                                                         ('Rechazada', '2023-04-01', 85, 4, 7, 7, 7),
                                                                                                                         ('Pendiente', '2023-04-02', 90, 4, 8, 8, 8),
                                                                                                                         ('Hecha', '2023-05-01', 75, 5, 9, 9, 9),
                                                                                                                         ('Pendiente', '2023-05-02', 95, 5, 10, 10, 10),
                                                                                                                         ('Rechazada', '2023-06-01', 80, 6, 11, 11, 1),
                                                                                                                         ('Pendiente', '2023-06-02', 85, 6, 12, 12, 2),
                                                                                                                         ('Hecha', '2023-07-01', 90, 7, 13, 13, 3),
                                                                                                                         ('Pendiente', '2023-07-02', 75, 7, 14, 14, 4),
                                                                                                                         ('Rechazada', '2023-08-01', 95, 8, 15, 15, 5),
                                                                                                                         ('Pendiente', '2023-08-02', 80, 8, 16, 16, 6),
                                                                                                                         ('Hecha', '2023-09-01', 85, 9, 17, 17, 7),
                                                                                                                         ('Pendiente', '2023-09-02', 90, 9, 18, 18, 8),
                                                                                                                         ('Rechazada', '2023-10-01', 75, 10, 19, 19, 9),
                                                                                                                         ('Pendiente', '2023-10-02', 95, 10, 20, 20, 10),
                                                                                                                         ('Hecha', '2023-11-01', 80, 11, 21, 21, 1),
                                                                                                                         ('Pendiente', '2023-11-02', 85, 11, 22, 22, 2),
                                                                                                                         ('Rechazada', '2023-12-01', 90, 12, 23, 23, 3),
                                                                                                                         ('Pendiente', '2023-12-02', 75, 12, 24, 24, 4),
                                                                                                                         ('Hecha', '2024-01-01', 95, 13, 25, 25, 5),
                                                                                                                         ('Pendiente', '2024-01-02', 80, 13, 26, 26, 6),
                                                                                                                         ('Rechazada', '2024-02-01', 85, 14, 27, 27, 7),
                                                                                                                         ('Pendiente', '2024-02-02', 90, 14, 28, 28, 8),
                                                                                                                         ('Hecha', '2024-03-01', 75, 15, 29, 29, 9),
                                                                                                                         ('Pendiente', '2024-03-02', 95, 15, 30, 30, 10),
                                                                                                                         ('Rechazada', '2024-04-01', 80, 16, 31, 31, 1),
                                                                                                                         ('Pendiente', '2024-04-02', 85, 16, 32, 32, 2),
                                                                                                                         ('Hecha', '2024-05-01', 90, 17, 33, 33, 3),
                                                                                                                         ('Pendiente', '2024-05-02', 75, 17, 34, 34, 4),
                                                                                                                         ('Rechazada', '2024-06-01', 95, 18, 35, 35, 5),
                                                                                                                         ('Pendiente', '2024-06-02', 80, 18, 36, 36, 6),
                                                                                                                         ('Hecha', '2024-07-01', 85, 19, 37, 37, 7),
                                                                                                                         ('Pendiente', '2024-07-02', 90, 19, 38, 38, 8),
                                                                                                                         ('Rechazada', '2024-08-01', 75, 20, 39, 39, 9),
                                                                                                                         ('Pendiente', '2024-08-02', 95, 20, 40, 40, 10),
                                                                                                                         ('Hecha', '2024-09-01', 80, 21, 41, 41, 1),
                                                                                                                         ('Pendiente', '2024-09-02', 85, 21, 42, 42, 2),
                                                                                                                         ('Rechazada', '2024-10-01', 90, 22, 43, 43, 3),
                                                                                                                         ('Pendiente', '2024-10-02', 75, 22, 44, 44, 4),
                                                                                                                         ('Hecha', '2024-11-01', 95, 23, 45, 45, 5),
                                                                                                                         ('Pendiente', '2024-11-02', 80, 23, 46, 46, 6),
                                                                                                                         ('Rechazada', '2024-12-01', 85, 24, 47, 47, 7),
                                                                                                                         ('Pendiente', '2024-12-02', 90, 24, 48, 48, 8),
                                                                                                                         ('Hecha', '2025-01-01', 75, 25, 49, 49, 9),
                                                                                                                         ('Pendiente', '2025-01-02', 95, 25, 50, 50, 10),
                                                                                                                         ('Rechazada', '2025-02-01', 80, 26, 51, 51, 1),
                                                                                                                         ('Pendiente', '2025-02-02', 85, 26, 52, 52, 2),
                                                                                                                         ('Hecha', '2025-03-01', 90, 27, 53, 53, 3),
                                                                                                                         ('Pendiente', '2025-03-02', 75, 27, 54, 54, 4),
                                                                                                                         ('Rechazada', '2025-04-01', 95, 28, 55, 55, 5),
                                                                                                                         ('Pendiente', '2025-04-02', 80, 28, 56, 56, 6),
                                                                                                                         ('Hecha', '2025-05-01', 85, 29, 57, 57, 7),
                                                                                                                         ('Pendiente', '2025-05-02', 90, 29, 58, 58, 8),
                                                                                                                         ('Rechazada', '2025-06-01', 75, 30, 59, 59, 9),
                                                                                                                         ('Pendiente', '2025-06-02', 95, 30, 60, 60, 10),
                                                                                                                         ('Hecha', '2025-07-01', 80, 31, 61, 61, 1),
                                                                                                                         ('Pendiente', '2025-07-02', 85, 31, 62, 62, 2),
                                                                                                                         ('Rechazada', '2025-08-01', 90, 32, 63, 63, 3),
                                                                                                                         ('Pendiente', '2025-08-02', 75, 32, 64, 64, 4),
                                                                                                                         ('Hecha', '2025-09-01', 95, 33, 65, 65, 5),
                                                                                                                         ('Pendiente', '2025-09-02', 80, 33, 66, 66, 6),
                                                                                                                         ('Rechazada', '2025-10-01', 85, 34, 67, 67, 7),
                                                                                                                         ('Pendiente', '2025-10-02', 90, 34, 68, 68, 8),
                                                                                                                         ('Hecha', '2025-11-01', 75, 35, 69, 69, 9),
                                                                                                                         ('Pendiente', '2025-11-02', 95, 35, 70, 70, 10),
                                                                                                                         ('Rechazada', '2025-12-01', 80, 36, 71, 71, 1),
                                                                                                                         ('Pendiente', '2025-12-02', 85, 36, 72, 72, 2),
                                                                                                                         ('Hecha', '2026-01-01', 90, 37, 73, 73, 3),
                                                                                                                         ('Pendiente', '2026-01-02', 75, 37, 74, 74, 4),
                                                                                                                         ('Rechazada', '2026-02-01', 95, 38, 75, 75, 5),
                                                                                                                         ('Pendiente', '2026-02-02', 80, 38, 76, 76, 6),
                                                                                                                         ('Hecha', '2026-03-01', 85, 39, 77, 77, 7),
                                                                                                                         ('Pendiente', '2026-03-02', 90, 39, 78, 78, 8),
                                                                                                                         ('Rechazada', '2026-04-01', 75, 40, 79, 79, 9),
                                                                                                                         ('Pendiente', '2026-04-02', 95, 40, 80, 80, 10),
                                                                                                                         ('Hecha', '2026-05-01', 80, 41, 81, 81, 1),
                                                                                                                         ('Pendiente', '2026-05-02', 85, 41, 82, 82, 2),
                                                                                                                         ('Rechazada', '2026-06-01', 90, 42, 83, 83, 3),
                                                                                                                         ('Pendiente', '2026-06-02', 75, 42, 84, 84, 4),
                                                                                                                         ('Hecha', '2026-07-01', 95, 43, 85, 85, 5),
                                                                                                                         ('Pendiente', '2026-07-02', 80, 43, 86, 86, 6),
                                                                                                                         ('Rechazada', '2026-08-01', 85, 44, 87, 87, 7),
                                                                                                                         ('Pendiente', '2026-08-02', 90, 44, 88, 88, 8),
                                                                                                                         ('Hecha', '2026-09-01', 75, 45, 89, 89, 9),
                                                                                                                         ('Pendiente', '2026-09-02', 95, 45, 90, 90, 10),
                                                                                                                         ('Rechazada', '2026-10-01', 80, 46, 91, 91, 1),
                                                                                                                         ('Pendiente', '2026-10-02', 85, 46, 92, 92, 2),
                                                                                                                         ('Hecha', '2026-11-01', 90, 47, 93, 93, 3),
                                                                                                                         ('Pendiente', '2026-11-02', 75, 47, 94, 94, 4),
                                                                                                                         ('Rechazada', '2026-12-01', 95, 48, 95, 95, 5),
                                                                                                                         ('Pendiente', '2026-12-02', 80, 48, 96, 96, 6),
                                                                                                                         ('Hecha', '2027-01-01', 85, 49, 97, 97, 7),
                                                                                                                         ('Pendiente', '2027-01-02', 90, 49, 98, 98, 8),
                                                                                                                         ('Rechazada', '2027-02-01', 75, 50, 99, 99, 9),
                                                                                                                         ('Pendiente', '2027-02-02', 95, 50, 100, 100, 10);
-- Insert data into Indicador
INSERT INTO Indicador (nombre) VALUES
                                   ('Technical Knowledge'),
                                   ('Problem Solving'),
                                   ('Communication Skills'),
                                   ('Cultural Fit'),
                                   ('Leadership'),
                                   ('Teamwork'),
                                   ('Creativity'),
                                   ('Analytical Thinking'),
                                   ('Time Management'),
                                   ('Adaptability'),
                                   ('Conflict Resolution'),
                                   ('Decision Making'),
                                   ('Attention to Detail'),
                                   ('Customer Focus'),
                                   ('Negotiation Skills');

-- Insert data into Tipo_entrevista_Indicador
INSERT INTO Tipo_entrevista_Indicador (id_tipo_entrevista, id_indicador) VALUES
                                                                             -- Indicators for Technical Interview
                                                                             (1, 1), (1, 2), (1, 3),

                                                                             -- Indicators for HR Interview
                                                                             (2, 4), (2, 5), (2, 6),

                                                                             -- Indicators for Behavioral Interview
                                                                             (3, 7), (3, 8), (3, 9),

                                                                             -- Indicators for Case Study Interview
                                                                             (4, 10), (4, 11), (4, 12),

                                                                             -- Indicators for Panel Interview
                                                                             (5, 13), (5, 14), (5, 15),

                                                                             -- Indicators for Group Interview
                                                                             (6, 1), (6, 4), (6, 7),

                                                                             -- Indicators for Phone Interview
                                                                             (7, 2), (7, 5), (7, 8),

                                                                             -- Indicators for Video Interview
                                                                             (8, 3), (8, 6), (8, 9),

                                                                             -- Indicators for On-site Interview
                                                                             (9, 10), (9, 13), (9, 14),

                                                                             -- Indicators for Final Interview
                                                                             (10, 11), (10, 12), (10, 15);

-- Insert data into Puntaje_Indicador for interviews with estado 'Pendiente' or 'Hecha'
INSERT INTO Puntaje_Indicador (id_entrevista, id_indicador, puntaje) VALUES
                                                                         -- Example for interview 1 (Pendiente)
                                                                         (1, 1, 80), (1, 2, 85), (1, 3, 90),
                                                                         -- Example for interview 2 (Hecha)
                                                                         (2, 4, 75), (2, 5, 80), (2, 6, 85),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 4 (Pendiente)
                                                                         (4, 10, 85), (4, 11, 90), (4, 12, 95),
                                                                         -- Example for interview 5 (Hecha)
                                                                         (5, 13, 80), (5, 14, 85), (5, 15, 90),
                                                                         -- Example for interview 6 (Pendiente)
                                                                         (6, 1, 75), (6, 4, 80), (6, 7, 85),
                                                                         -- Example for interview 7 (Hecha)
                                                                         (7, 2, 70), (7, 5, 75), (7, 8, 80),
                                                                         -- Example for interview 8 (Pendiente)
                                                                         (8, 3, 85), (8, 6, 90), (8, 9, 95),
                                                                         -- Example for interview 9 (Hecha)
                                                                         (9, 10, 80), (9, 13, 85), (9, 14, 90),
                                                                         -- Example for interview 10 (Pendiente)
                                                                         (10, 11, 75), (10, 12, 80), (10, 15, 85),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 11 (Hecha)
                                                                         (11, 1, 80), (11, 2, 85), (11, 3, 90),
                                                                         -- Example for interview 12 (Pendiente)
                                                                         (12, 4, 75), (12, 5, 80), (12, 6, 85),
                                                                         -- Example for interview 13 (Hecha)
                                                                         (13, 7, 70), (13, 8, 75), (13, 9, 80),
                                                                         -- Example for interview 14 (Pendiente)
                                                                         (14, 10, 85), (14, 11, 90), (14, 12, 95),
                                                                         -- Example for interview 15 (Hecha)
                                                                         (15, 13, 80), (15, 14, 85), (15, 15, 90),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 16 (Pendiente)
                                                                         (16, 1, 75), (16, 4, 80), (16, 7, 85),
                                                                         -- Example for interview 17 (Hecha)
                                                                         (17, 2, 70), (17, 5, 75), (17, 8, 80),
                                                                         -- Example for interview 18 (Pendiente)
                                                                         (18, 3, 85), (18, 6, 90), (18, 9, 95),
                                                                         -- Example for interview 19 (Hecha)
                                                                         (19, 10, 80), (19, 13, 85), (19, 14, 90),
                                                                         -- Example for interview 20 (Pendiente)
                                                                         (20, 11, 75), (20, 12, 80), (20, 15, 85),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 21 (Hecha)
                                                                         (21, 1, 80), (21, 2, 85), (21, 3, 90),
                                                                         -- Example for interview 22 (Pendiente)
                                                                         (22, 4, 75), (22, 5, 80), (22, 6, 85),
                                                                         -- Example for interview 23 (Hecha)
                                                                         (23, 7, 70), (23, 8, 75), (23, 9, 80),
                                                                         -- Example for interview 24 (Pendiente)
                                                                         (24, 10, 85), (24, 11, 90), (24, 12, 95),
                                                                         -- Example for interview 25 (Hecha)
                                                                         (25, 13, 80), (25, 14, 85), (25, 15, 90),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 26 (Pendiente)
                                                                         (26, 1, 75), (26, 4, 80), (26, 7, 85),
                                                                         -- Example for interview 27 (Hecha)
                                                                         (27, 2, 70), (27, 5, 75), (27, 8, 80),
                                                                         -- Example for interview 28 (Pendiente)
                                                                         (28, 3, 85), (28, 6, 90), (28, 9, 95),
                                                                         -- Example for interview 29 (Hecha)
                                                                         (29, 10, 80), (29, 13, 85), (29, 14, 90),
                                                                         -- Example for interview 30 (Pendiente)
                                                                         (30, 11, 75), (30, 12, 80), (30, 15, 85),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 31 (Hecha)
                                                                         (31, 1, 80), (31, 2, 85), (31, 3, 90),
                                                                         -- Example for interview 32 (Pendiente)
                                                                         (32, 4, 75), (32, 5, 80), (32, 6, 85),
                                                                         -- Example for interview 33 (Hecha)
                                                                         (33, 7, 70), (33, 8, 75), (33, 9, 80),
                                                                         -- Example for interview 34 (Pendiente)
                                                                         (34, 10, 85), (34, 11, 90), (34, 12, 95),
                                                                         -- Example for interview 35 (Hecha)
                                                                         (35, 13, 80), (35, 14, 85), (35, 15, 90),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 36 (Pendiente)
                                                                         (36, 1, 75), (36, 4, 80), (36, 7, 85),
                                                                         -- Example for interview 37 (Hecha)
                                                                         (37, 2, 70), (37, 5, 75), (37, 8, 80),
                                                                         -- Example for interview 38 (Pendiente)
                                                                         (38, 3, 85), (38, 6, 90), (38, 9, 95),
                                                                         -- Example for interview 39 (Hecha)
                                                                         (39, 10, 80), (39, 13, 85), (39, 14, 90),
                                                                         -- Example for interview 40 (Pendiente)
                                                                         (40, 11, 75), (40, 12, 80), (40, 15, 85),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 41 (Hecha)
                                                                         (41, 1, 80), (41, 2, 85), (41, 3, 90),
                                                                         -- Example for interview 42 (Pendiente)
                                                                         (42, 4, 75), (42, 5, 80), (42, 6, 85),
                                                                         -- Example for interview 43 (Hecha)
                                                                         (43, 7, 70), (43, 8, 75), (43, 9, 80),
                                                                         -- Example for interview 44 (Pendiente)
                                                                         (44, 10, 85), (44, 11, 90), (44, 12, 95),
                                                                         -- Example for interview 45 (Hecha)
                                                                         (45, 13, 80), (45, 14, 85), (45, 15, 90),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 46 (Pendiente)
                                                                         (46, 1, 75), (46, 4, 80), (46, 7, 85),
                                                                         -- Example for interview 47 (Hecha)
                                                                         (47, 2, 70), (47, 5, 75), (47, 8, 80),
                                                                         -- Example for interview 48 (Pendiente)
                                                                         (48, 3, 85), (48, 6, 90), (48, 9, 95),
                                                                         -- Example for interview 49 (Hecha)
                                                                         (49, 10, 80), (49, 13, 85), (49, 14, 90),
                                                                         -- Example for interview 50 (Pendiente)
                                                                         (50, 11, 75), (50, 12, 80), (50, 15, 85),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 51 (Hecha)
                                                                         (51, 1, 80), (51, 2, 85), (51, 3, 90),
                                                                         -- Example for interview 52 (Pendiente)
                                                                         (52, 4, 75), (52, 5, 80), (52, 6, 85),
                                                                         -- Example for interview 53 (Hecha)
                                                                         (53, 7, 70), (53, 8, 75), (53, 9, 80),
                                                                         -- Example for interview 54 (Pendiente)
                                                                         (54, 10, 85), (54, 11, 90), (54, 12, 95),
                                                                         -- Example for interview 55 (Hecha)
                                                                         (55, 13, 80), (55, 14, 85), (55, 15, 90),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 56 (Pendiente)
                                                                         (56, 1, 75), (56, 4, 80), (56, 7, 85),
                                                                         -- Example for interview 57 (Hecha)
                                                                         (57, 2, 70), (57, 5, 75), (57, 8, 80),
                                                                         -- Example for interview 58 (Pendiente)
                                                                         (58, 3, 85), (58, 6, 90), (58, 9, 95),
                                                                         -- Example for interview 59 (Hecha)
                                                                         (59, 10, 80), (59, 13, 85), (59, 14, 90),
                                                                         -- Example for interview 60 (Pendiente)
                                                                         (60, 11, 75), (60, 12, 80), (60, 15, 85),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 61 (Hecha)
                                                                         (61, 1, 80), (61, 2, 85), (61, 3, 90),
                                                                         -- Example for interview 62 (Pendiente)
                                                                         (62, 4, 75), (62, 5, 80), (62, 6, 85),
                                                                         -- Example for interview 63 (Hecha)
                                                                         (63, 7, 70), (63, 8, 75), (63, 9, 80),
                                                                         -- Example for interview 64 (Pendiente)
                                                                         (64, 10, 85), (64, 11, 90), (64, 12, 95),
                                                                         -- Example for interview 65 (Hecha)
                                                                         (65, 13, 80), (65, 14, 85), (65, 15, 90),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 66 (Pendiente)
                                                                         (66, 1, 75), (66, 4, 80), (66, 7, 85),
                                                                         -- Example for interview 67 (Hecha)
                                                                         (67, 2, 70), (67, 5, 75), (67, 8, 80),
                                                                         -- Example for interview 68 (Pendiente)
                                                                         (68, 3, 85), (68, 6, 90), (68, 9, 95),
                                                                         -- Example for interview 69 (Hecha)
                                                                         (69, 10, 80), (69, 13, 85), (69, 14, 90),
                                                                         -- Example for interview 70 (Pendiente)
                                                                         (70, 11, 75), (70, 12, 80), (70, 15, 85),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 71 (Hecha)
                                                                         (71, 1, 80), (71, 2, 85), (71, 3, 90),
                                                                         -- Example for interview 72 (Pendiente)
                                                                         (72, 4, 75), (72, 5, 80), (72, 6, 85),
                                                                         -- Example for interview 73 (Hecha)
                                                                         (73, 7, 70), (73, 8, 75), (73, 9, 80),
                                                                         -- Example for interview 74 (Pendiente)
                                                                         (74, 10, 85), (74, 11, 90), (74, 12, 95),
                                                                         -- Example for interview 75 (Hecha)
                                                                         (75, 13, 80), (75, 14, 85), (75, 15, 90),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 76 (Pendiente)
                                                                         (76, 1, 75), (76, 4, 80), (76, 7, 85),
                                                                         -- Example for interview 77 (Hecha)
                                                                         (77, 2, 70), (77, 5, 75), (77, 8, 80),
                                                                         -- Example for interview 78 (Pendiente)
                                                                         (78, 3, 85), (78, 6, 90), (78, 9, 95),
                                                                         -- Example for interview 79 (Hecha)
                                                                         (79, 10, 80), (79, 13, 85), (79, 14, 90),
                                                                         -- Example for interview 80 (Pendiente)
                                                                         (80, 11, 75), (80, 12, 80), (80, 15, 85),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 81 (Hecha)
                                                                         (81, 1, 80), (81, 2, 85), (81, 3, 90),
                                                                         -- Example for interview 82 (Pendiente)
                                                                         (82, 4, 75), (82, 5, 80), (82, 6, 85),
                                                                         -- Example for interview 83 (Hecha)
                                                                         (83, 7, 70), (83, 8, 75), (83, 9, 80),
                                                                         -- Example for interview 84 (Pendiente)
                                                                         (84, 10, 85), (84, 11, 90), (84, 12, 95),
                                                                         -- Example for interview 85 (Hecha)
                                                                         (85, 13, 80), (85, 14, 85), (85, 15, 90),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 86 (Pendiente)
                                                                         (86, 1, 75), (86, 4, 80), (86, 7, 85),
                                                                         -- Example for interview 87 (Hecha)
                                                                         (87, 2, 70), (87, 5, 75), (87, 8, 80),
                                                                         -- Example for interview 88 (Pendiente)
                                                                         (88, 3, 85), (88, 6, 90), (88, 9, 95),
                                                                         -- Example for interview 89 (Hecha)
                                                                         (89, 10, 80), (89, 13, 85), (89, 14, 90),
                                                                         -- Example for interview 90 (Pendiente)
                                                                         (90, 11, 75), (90, 12, 80), (90, 15, 85),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 91 (Hecha)
                                                                         (91, 1, 80), (91, 2, 85), (91, 3, 90),
                                                                         -- Example for interview 92 (Pendiente)
                                                                         (92, 4, 75), (92, 5, 80), (92, 6, 85),
                                                                         -- Example for interview 93 (Hecha)
                                                                         (93, 7, 70), (93, 8, 75), (93, 9, 80),
                                                                         -- Example for interview 94 (Pendiente)
                                                                         (94, 10, 85), (94, 11, 90), (94, 12, 95),
                                                                         -- Example for interview 95 (Hecha)
                                                                         (95, 13, 80), (95, 14, 85), (95, 15, 90),
                                                                         -- Continue for all interviews up to 100
                                                                         -- Example for interview 96 (Pendiente)
                                                                         (96, 1, 75), (96, 4, 80), (96, 7, 85),
                                                                         -- Example for interview 97 (Hecha)
                                                                         (97, 2, 70), (97, 5, 75), (97, 8, 80),
                                                                         -- Example for interview 98 (Pendiente)
                                                                         (98, 3, 85), (98, 6, 90), (98, 9, 95),
                                                                         -- Example for interview 99 (Hecha)
                                                                         (99, 10, 80), (99, 13, 85), (99, 14, 90),
                                                                         -- Example for interview 100 (Pendiente)
                                                                         (100, 11, 75), (100, 12, 80), (100, 15, 85);
