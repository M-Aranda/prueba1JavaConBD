CREATE DATABASE matriculasCursosDeBaile;

USE matriculasCursosDeBaile;



CREATE TABLE matricula(
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(128), 
valor_objeto BLOB 
);


SELECT * FROM matricula;


TRUNCATE matricula;

DROP DATABASE matriculasCursosDeBaile;


