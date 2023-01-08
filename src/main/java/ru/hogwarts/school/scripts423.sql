-- получить информацию обо всех студентах
-- (достаточно получить только имя и возраст студента) школы Хогвартс вместе с названиями факультетов.
SELECT s.name, s.age, f.color FROM student as s,faculty as f
WHERE s.faculty_id = f.id;
-- получить только тех студентов, у которых есть аватарки
SELECT s.name, s.age FROM avatar as a, student  as s
WHERE s.id = a.student_id;