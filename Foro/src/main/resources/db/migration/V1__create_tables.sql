-- Crear tabla curso
CREATE TABLE curso (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       nombre VARCHAR(255) NOT NULL
);

-- Crear tabla usuario
CREATE TABLE usuario (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(255) NOT NULL
);

-- Crear tabla topico
CREATE TABLE topico (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        titulo VARCHAR(255) NOT NULL,
                        mensaje TEXT NOT NULL,
                        autor_id BIGINT NOT NULL,
                        curso_id BIGINT NOT NULL,
                        FOREIGN KEY (autor_id) REFERENCES usuario(id),
                        FOREIGN KEY (curso_id) REFERENCES curso(id)
);

-- Crear tabla respuesta
CREATE TABLE respuesta (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           mensaje TEXT NOT NULL,
                           topico_id BIGINT NOT NULL,
                           autor_id BIGINT NOT NULL,
                           FOREIGN KEY (topico_id) REFERENCES topico(id),
                           FOREIGN KEY (autor_id) REFERENCES usuario(id)
);
