CREATE TABLE lines (
    id SERIAL PRIMARY KEY,
    name VARCHAR(10) NOT NULL
);

INSERT INTO lines (name)
VALUES ('Cronos'),
       ('Ares');

SELECT * FROM public.lines
ORDER BY id ASC;

CREATE TABLE categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    line_id INT NOT NULL,
    FOREIGN KEY (line_id) REFERENCES lines(id)
);

INSERT INTO categories (name, line_id)
VALUES ('Cronos Old', (SELECT id FROM lines WHERE name = 'Cronos')),
       ('Cronos L', (SELECT id FROM lines WHERE name = 'Cronos')),
       ('Cronos-NG', (SELECT id FROM lines WHERE name = 'Cronos')),
       ('Ares TB', (SELECT id FROM lines WHERE name = 'Ares')),
       ('Ares THS', (SELECT id FROM lines WHERE name = 'Ares'));

SELECT * FROM public.categories
ORDER BY id ASC;

CREATE TABLE models (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    subcategory_id INT NOT NULL,
    FOREIGN KEY (subcategory_id) REFERENCES categories(id)
);

INSERT INTO models (name, subcategory_id)
VALUES ('Cronos 6001-A', (SELECT id FROM categories WHERE name = 'Cronos Old')),
       ('Cronos 6003', (SELECT id FROM categories WHERE name = 'Cronos Old')),
       ('Cronos 7023', (SELECT id FROM categories WHERE name = 'Cronos Old'));

INSERT INTO models (name, subcategory_id)
VALUES ('Cronos 6021L', (SELECT id FROM categories WHERE name = 'Cronos L')),
       ('Cronos 7023L', (SELECT id FROM categories WHERE name = 'Cronos L'));

INSERT INTO models (name, subcategory_id)
VALUES ('Cronos 6001-NG', (SELECT id FROM categories WHERE name = 'Cronos-NG')),
       ('Cronos 6003-NG', (SELECT id FROM categories WHERE name = 'Cronos-NG')),
       ('Cronos 6021-NG', (SELECT id FROM categories WHERE name = 'Cronos-NG')),
       ('Cronos 6031-NG', (SELECT id FROM categories WHERE name = 'Cronos-NG')),
       ('Cronos 7021-NG', (SELECT id FROM categories WHERE name = 'Cronos-NG')),
       ('Cronos 7023-NG', (SELECT id FROM categories WHERE name = 'Cronos-NG'));

INSERT INTO models (name, subcategory_id)
VALUES ('Ares 7021', (SELECT id FROM categories WHERE name = 'Ares TB')),
       ('Ares 7031', (SELECT id FROM categories WHERE name = 'Ares TB')),
       ('Ares 7023', (SELECT id FROM categories WHERE name = 'Ares TB'));

INSERT INTO models (name, subcategory_id)
VALUES ('Ares 8023 15', (SELECT id FROM categories WHERE name = 'Ares THS')),
       ('Ares 8023 200', (SELECT id FROM categories WHERE name = 'Ares THS')),
       ('Ares 8023 2,5', (SELECT id FROM categories WHERE name = 'Ares THS'));

SELECT * FROM public.models
ORDER BY id ASC;

SELECT * FROM public.categories
ORDER BY id ASC;

ALTER TABLE categories ALTER COLUMN id TYPE BIGINT;
ALTER TABLE lines ALTER COLUMN id TYPE BIGINT;
ALTER TABLE models ALTER COLUMN id TYPE BIGINT;

SELECT column_name, data_type 
FROM information_schema.columns 
WHERE table_name = 'categories' AND column_name = 'line_id';

ALTER TABLE models
ALTER COLUMN subcategory_id TYPE BIGINT;