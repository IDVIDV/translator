CREATE TABLE language
(
    id   SERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

CREATE TABLE translate_request
(
    id                SERIAL PRIMARY KEY,
    ip                TEXT,
    input             TEXT NOT NULL,
    input_language_id INTEGER,
    CONSTRAINT input_language_id_fk
        FOREIGN KEY (input_language_id)
            REFERENCES language (id)
            ON DELETE SET NULL
);

CREATE TABLE translate_result
(
    id                   SERIAL PRIMARY KEY,
    translate_request_id INTEGER,
    output               TEXT,
    output_language_id   INTEGER,
    CONSTRAINT translate_request_id_fk
        FOREIGN KEY (translate_request_id)
            REFERENCES translate_request (id)
            ON DELETE SET NULL,
    CONSTRAINT output_language_id_fk
        FOREIGN KEY (output_language_id)
            REFERENCES language (id)
            ON DELETE SET NULL
)