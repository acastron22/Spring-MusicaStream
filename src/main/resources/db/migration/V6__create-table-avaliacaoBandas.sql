CREATE TABLE bandas_avaliacao
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nota INT NOT NULL,
    banda_id BIGINT NOT NULL,
    FOREIGN KEY (banda_id) REFERENCES bandas(id)
);
