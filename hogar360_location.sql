DROP DATABASE IF EXISTS hogar360_location;
CREATE DATABASE hogar360_location;
USE hogar360_location;

CREATE TABLE IF NOT EXISTS locations (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  city VARCHAR(100) NOT NULL,
  country VARCHAR(100) NOT NULL,
  department VARCHAR(100) NOT NULL
);

INSERT INTO locations (name, city, country, department)
  VALUES
    ('La Floresta', 'Medellín', 'Colombia', 'Antioquia'),
    ('Chapinero', 'Bogotá', 'Colombia', 'Cundinamarca');
