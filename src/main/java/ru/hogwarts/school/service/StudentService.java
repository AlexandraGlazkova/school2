package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repositories.ListOfStudents;
import ru.hogwarts.school.repositories.StudentRepository;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private int threadSyncCounter = 0;
    public StudentService(StudentRepository studentRepository) {
        logger.debug("Calling constructor StudentService");
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.debug("Calling method createStudent");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.debug("Calling method findStudent (studentId = {})", id);
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            throw new StudentNotFoundException(id);
        }
        return student;
    }

    public Student editStudent(Student student) {
        logger.debug("Calling method editStudent (studentId = {})", student.getId());
        if (studentRepository.findById(student.getId()).orElse(null) == null) {
            return null;
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.debug("Calling method deleteStudent (studentId = {})", id);
        studentRepository.deleteById(id);
    }

    public Collection<Student> getStudentsByAge(int age) {
        logger.debug("Calling method getStudentsByAge (age = {})", age);
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.debug("Calling method findByAgeBetween (minAge = {}, maxAge = {})", min, max);
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty getStudentFaculty(long id) {
        logger.debug("Calling method getStudentFaculty (studentId = {})", id);
        Student student = findStudent(id);
        if (student == null) {
            return null;
        }
        return student.getFaculty();
    }

    public int getAllStudentsNumber() {
        logger.debug("Calling method getAllStudentsNumber");
        return studentRepository.getAllStudentsNumber();
    }

    public double getAverageAge() {
        logger.debug("Calling method getAverageAge");
        return studentRepository.getAverageAge();
    }

    public List<ListOfStudents> getLastStudentsById(int limit) {
        logger.debug("Calling method getLastStudentsById (limit = {})", limit);
        return studentRepository.getLastStudentsById(limit);
    }
    public List<String> getStudentsByNameStartsWith(String letter) {
        return studentRepository.findAll().stream()
                .map(user -> user.getName())
                .filter(s -> s.startsWith(letter))
                .sorted((s1, s2) -> s1.compareTo(s2))
                .map(s -> s.toUpperCase())
                .collect(Collectors.toList());
    }

    public Double getAverageAgeWithStream() {
        return studentRepository.findAll().stream()
                .mapToDouble(user -> user.getAge())
                .average()
                .orElse(Double.NaN);
    }
    public void echoAllStudentNames() {
        List<String> names = studentRepository.findAll().stream()
                .map(user -> user.getName())
                .collect(Collectors.toList());

        printToConsole(names.get(0));
        printToConsole(names.get(1));

        Thread thread1 = new Thread(() -> {
            printToConsole(names.get(2));
            printToConsole(names.get(3));
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            printToConsole(names.get(4));
            printToConsole(names.get(5));
        });
        thread2.start();
    }

    public void echoAllStudentNamesSync() {
        List<String> names = studentRepository.findAll().stream()
                .map(user -> user.getName())
                .collect(Collectors.toList());

        printToConsoleSync(names);
        printToConsoleSync(names);

        Thread thread1 = new Thread(() -> {
            printToConsoleSync(names);
            printToConsoleSync(names);
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            printToConsoleSync(names);
            printToConsoleSync(names);
        });
        thread2.start();
    }

    private void printToConsole(String str) {
        System.out.println(str);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private synchronized void printToConsoleSync(List<String> names) {
        System.out.println(names.get(threadSyncCounter));
        threadSyncCounter++;
    }

    private void joinThread(Thread thread) {
        if (thread.isAlive()) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private synchronized void printToConsoleSync(String str) {
        System.out.println(str);
    }
}
