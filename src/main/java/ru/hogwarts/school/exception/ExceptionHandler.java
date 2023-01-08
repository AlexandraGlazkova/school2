package ru.hogwarts.school.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<String> handleStudentNotFoundException(StudentNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Student with id = %d not found.", e.getId()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(FileIsTooBigException.class)
    public ResponseEntity<String> handleFileIsTooBigException(FileIsTooBigException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(String.format("File is too big (max size = %d Kb).", e.getSizeLimit()));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AvatarNotFoundException.class)
    public ResponseEntity<String> handleAvatarNotFoundException(AvatarNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(String.format("Avatar not found for student with id = %d.", e.getStudentId()));
    }
}
