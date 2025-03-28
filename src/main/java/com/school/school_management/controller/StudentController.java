package com.school.school_management.controller;

import com.school.school_management.config.services.AuthenticationService;
import com.school.school_management.model.Student;
import com.school.school_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final AuthenticationService authenticationService;

    @Autowired
    public StudentController(AuthenticationService authenticationService, StudentService studentService) {
        this.authenticationService = authenticationService;
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("Missing or invalid Authorization header");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authHeader.substring(7); // Extract token

        boolean isAuthenticated = authenticationService.authenticate(token);
        System.out.println("Token: " + token);
        System.out.println("Authentication Result: " + isAuthenticated);

        if (!isAuthenticated) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@RequestHeader(value="Authorization") String authHeader, @PathVariable Long id){
        if (!isValidToken(authHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestHeader(value = "Authorization") String authHeader,
                                                 @RequestBody Student student) {
        if (!isValidToken(authHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@RequestHeader(value = "Authorization") String authHeader,
                                                @PathVariable Long id) {
        if (!isValidToken(authHeader)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }

    // Utility method for token validation
    private boolean isValidToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return false;
        }
        String token = authHeader.substring(7); // Extract token after "Bearer "
        return authenticationService.authenticate(token);
    }
}
