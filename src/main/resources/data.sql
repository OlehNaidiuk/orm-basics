INSERT INTO teacher (teacher_id, teacher_name, teacher_surname)
VALUES (DEFAULT, 'Severus', 'Snape'),
       (DEFAULT, 'Remus', 'Lupin'),
       (DEFAULT, 'Rubeus', 'Hagrid');

INSERT INTO grp (group_id, group_name, teacher_id)
VALUES (DEFAULT, 'Academic', 1),
       (DEFAULT, 'Cultural', 1),
       (DEFAULT, 'Creative', 2),
       (DEFAULT, 'Politics', 2),
       (DEFAULT, 'Health', 3),
       (DEFAULT, 'Hobbies', 3);

INSERT INTO student (student_id, student_name, student_surname, group_id)
VALUES (DEFAULT, 'Ana', 'Varela', 1),
       (DEFAULT, 'Charles', 'Monaghan', 1),
       (DEFAULT, 'Patricia', 'King', 2),
       (DEFAULT, 'John', 'Botts', 2),
       (DEFAULT, 'Fred', 'Sculer', 3),
       (DEFAULT, 'Frank', 'Vickers', 3),
       (DEFAULT, 'Martha', 'Sargent', 4),
       (DEFAULT, 'Matthew', 'Martin', 4),
       (DEFAULT, 'Ricky', 'Kwon', 5),
       (DEFAULT, 'Simone', 'Williams', 5),
       (DEFAULT, 'Michael', 'Naidu', 6),
       (DEFAULT, 'Bill', 'Waits', 6);