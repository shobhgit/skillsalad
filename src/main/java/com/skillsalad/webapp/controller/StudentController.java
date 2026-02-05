package com.skillsalad.webapp.controller;

import com.skillsalad.webapp.dto.LoginRequestDto;
import com.skillsalad.webapp.dto.LoginResponseDto;
import com.skillsalad.webapp.dto.RegisterStudentRequestDto;
import com.skillsalad.webapp.dto.StudentResponseDto;
import com.skillsalad.webapp.entity.StudentProfile;
import com.skillsalad.webapp.entity.User;
import com.skillsalad.webapp.service.AuthService;
import com.skillsalad.webapp.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {
    //controller cannot exist without studentService
    private final AuthService authService;
    private final StudentService studentService;

    public StudentController(AuthService authService, StudentService studentService){
        this.authService = authService;
        this.studentService = studentService;
    }
    //-----------------------REGISTER-------------------------
    @PostMapping("/register")
    public StudentResponseDto register(@Valid @RequestBody RegisterStudentRequestDto request) {

        User user  =authService.registerStudent(request);
        return new StudentResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );

    }

    //-----------------------LOGIN----------------------------
    @PostMapping("/login")
    public LoginResponseDto login (@Valid @RequestBody LoginRequestDto request){
        return authService.login(request);
    }

    //------------------GET STUDENT PROFILE------------------------
    @GetMapping("/profile/{userId}")
    public StudentProfile getProfile(@PathVariable Long userId){
        return studentService.getProfileByUserId(userId);
    }
}
