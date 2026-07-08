INSERT INTO categorias (name, type) VALUES ('Comida', 'EXPENSE');
INSERT INTO categorias (name, type)VALUES ('Transporte', 'EXPENSE');
INSERT INTO categorias (name, type) VALUES ('Ocio', 'EXPENSE');
INSERT INTO categorias (name, type) VALUES ('Nomina', 'INCOME');

INSERT INTO categorizacion (key_word, categoria_id)
VALUES ('pizza', (SELECT id FROM categorias WHERE name = 'Comida'));

INSERT INTO categorizacion (key_word, categoria_id)
VALUES ('uber', (SELECT id FROM categorias WHERE name = 'Transporte'));

INSERT INTO categorizacion (key_word, categoria_id)
VALUES ('netflix', (SELECT id FROM categorias WHERE name = 'Ocio'));