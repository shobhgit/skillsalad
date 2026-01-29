package com.skillsalad.webapp.controller;

import com.skillsalad.webapp.entity.Student;
import com.skillsalad.webapp.service.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    //controller cannot exist without studentService

    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping("/register")
    public Student register(@RequestBody Student student){
        return studentService.registerStudent(student);
    }
}
