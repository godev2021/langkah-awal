-- schema.sql
DROP TABLE IF EXISTS employee_attendance;
DROP TABLE IF EXISTS three_sixty_review;
DROP TABLE IF EXISTS employee_score;
DROP TABLE IF EXISTS kudos;
DROP TABLE IF EXISTS employee;

CREATE TABLE employee (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
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
                       score DOUBLE,
                       visibility VARCHAR(10)
);

CREATE TABLE employee_score (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                employee_id BIGINT,
                                name VARCHAR(255),
                                kpi_score DOUBLE,
                                review_score DOUBLE,
                                kudos_score DOUBLE,
                                absence_score DOUBLE,
                                timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                summarized_review TEXT
);

CREATE TABLE three_sixty_review (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    employee_id BIGINT,
                                    employee_review_id BIGINT,
                                    review_score DOUBLE,
                                    review_contribution TEXT,
                                    review_strength TEXT,
                                    review_development TEXT,
                                    type VARCHAR(255)
);

CREATE TABLE employee_attendance (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    employee_id BIGINT,
                                    total_absence INT DEFAULT 0,
                                    total_sick INT DEFAULT 0,
                                    total_wfh INT DEFAULT 0,
                                    total_late_days INT DEFAULT 0
);