CREATE TABLE student
(
    id    SERIAL PRIMARY KEY,
    name VARCHAR,
    age NUMERIC CHECK (age > 0),
    faculty_id      INTEGER REFERENCES faculty (id)
);

CREATE TABLE faculty
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR,
    color       VARCHAR

);
CREATE TABLE avatar
(
    id        SERIAL PRIMARY KEY,
    filePath  VARCHAR,
    fileSize  VARCHAR,
    mediaType VARCHAR,
    data VARCHAR

);