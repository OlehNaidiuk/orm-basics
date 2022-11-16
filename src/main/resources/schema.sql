CREATE TABLE teacher
(
    teacher_id      BIGINT NOT NULL AUTO_INCREMENT,
    teacher_name    VARCHAR(255) DEFAULT NULL,
    teacher_surname VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (teacher_id)
) ENGINE = INNODB;

CREATE TABLE grp
(
    group_id   BIGINT NOT NULL AUTO_INCREMENT,
    group_name VARCHAR(255) DEFAULT NULL,
    teacher_id BIGINT       DEFAULT NULL,
    PRIMARY KEY (group_id),
    FOREIGN KEY (teacher_id)
        REFERENCES teacher (teacher_id)
) ENGINE = INNODB;

CREATE TABLE student
(
    student_id      BIGINT NOT NULL AUTO_INCREMENT,
    student_name    VARCHAR(255) DEFAULT NULL,
    student_surname VARCHAR(255) DEFAULT NULL,
    group_id        BIGINT       DEFAULT NULL,
    PRIMARY KEY (student_id),
    FOREIGN KEY (group_id)
        REFERENCES grp (group_id)
) ENGINE = INNODB;