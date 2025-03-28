//package com.school.school_management.controller;
//
//
//
//import com.school.school_management.config.services.AuthenticationService;
//import com.school.school_management.model.Student;
//import com.school.school_management.service.StudentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/students")
//public class StudentController_1 {
//
//
//    private StudentService studentService;
//
//
//    private AuthenticationService authenticationService;
//
//    @Autowired
//    public StudentController_1(AuthenticationService authenticationService, StudentService studentService) {
//        this.authenticationService = authenticationService;
//        this.studentService = studentService;
//    }
//
//
//    @GetMapping
//    public List<Student> getAllStudents() {
//        return studentService.getAllStudents();
//    }
//
//    @GetMapping("/{id}")
//    public Student getStudentById(@PathVariable Long id) {
//        return studentService.getStudentById(id);
//    }
//
//    @PostMapping
//    public Student createStudent(@RequestBody Student student) {
//        return studentService.saveStudent(student);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteStudent(@PathVariable Long id) {
//        studentService.deleteStudent(id);
//    }
//
//
//    //    @GetMapping("/{id}")
////    public Product getProductbyId(
////            @PathVariable("id") Long id) throws ProductNotFoundException, AccessDeniedException {
////        if(!authenticationService.authenticate(token)) {
////            throw new AccessDeniedException("You are not authorised");
////        }
//
////        Product p = productService.getProductById(id);
////        //p.setId(2L);
////        return p;
////    }
//
////
////@GetMapping("/{id}")
////public Student getStudentById(@RequestHeader("authenticationService") String token,
////                              @PathVariable Long id) {
////    if(!authenticationService.authenticate(token))
////    {
////        System.out.println("you are not authotised ");
////    }
////    return studentService.getStudentById(id);
////}
//
//}
