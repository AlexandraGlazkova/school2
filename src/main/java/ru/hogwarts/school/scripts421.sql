-- -Возраст студента не может быть меньше 16 лет.
CREATE TABLE student (age INTEGER CHECK (age>16));
ALTER TABLE student;
    ADD CONSTRAINT age_constraint CHECK(age>=16);
-- - Имена студентов должны быть уникальными и не равны нулю.
CREATE TABLE student(name TEXT NOT NULL);
ALTER TABLE student ALTER COLUMN name SET NOT NULL;
CREATE TABLE student (name TEXT UNIQUE);
ALTER TABLE student ADD CONSTRAINT nickname_unique UNIQUE (name);
-- - Пара “значение названия” - “цвет факультета” должна быть уникальной.
CREATE TABLE faculty (color TEXT UNIQUE);
ALTER TABLE faculty ADD CONSTRAINT name_color_unique UNIQUE (name,color);
-- - При создании студента без возраста ему автоматически должно присваиваться 20 лет.
CREATE TABLE student (age INTEGER DEFAULT 20);
ALTER TABLE student ALTER COLUMN age SET DEFAULT 20;