package com.skillsalad.webapp.controller;

import com.skillsalad.webapp.dto.LoginRequestDto;
import com.skillsalad.webapp.dto.LoginResponseDto;
import com.skillsalad.webapp.dto.RegisterStudentRequestDto;
import com.skillsalad.webapp.dto.StudentResponseDto;
import com.skillsalad.webapp.entity.Student;
import com.skillsalad.webapp.service.StudentService;
import jakarta.validation.Valid;
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
    public StudentResponseDto register(@Valid @RequestBody RegisterStudentRequestDto request) {

        Student savedStudent = studentService.registerStudent(request);

        return new StudentResponseDto(
            savedStudent.getId(),
            savedStudent.getName(),
            savedStudent.getEmail()
        );

    }
    @PostMapping("/login")
    public LoginResponseDto login (@Valid @RequestBody LoginRequestDto request){
        return studentService.login(request);
    }
}
