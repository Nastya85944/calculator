DROP TABLE IF EXISTS calculator;

CREATE TABLE calculator (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    parameter1 VARCHAR(250) NOT NULL,
    parameter2 VARCHAR(250) NOT NULL,
    result VARCHAR(250) NOT NULL,
    operation VARCHAR(250) NOT NULL
);

CREATE SEQUENCE calculator_seq START WITH 1 INCREMENT BY 1;
