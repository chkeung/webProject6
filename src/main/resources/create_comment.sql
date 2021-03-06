CREATE TABLE comment (
    id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    name VARCHAR(50),
    message VARCHAR(200),
    date TIMESTAMP,
    customer VARCHAR(200),
    PRIMARY KEY (id)
);