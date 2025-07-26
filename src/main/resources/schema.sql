-- schema.sql
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS kudos;

CREATE TABLE employee (
                          employee_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(255),
                          nik VARCHAR(255) UNIQUE,
                          reporting_manager BIGINT,
                          job_title VARCHAR(255),
                          job_level VARCHAR(255),
                          department_name VARCHAR(255),
                          division_name VARCHAR(255),
                          active BOOLEAN,
                          mvp_of_the_year BOOLEAN
);

CREATE TABLE kudos (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       from_employee_id BIGINT NOT NULL,
                       to_employee_id BIGINT NOT NULL,
                       message TEXT NOT NULL,
                       category VARCHAR(50) NOT NULL,
                       timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       visibility VARCHAR(10)
);

