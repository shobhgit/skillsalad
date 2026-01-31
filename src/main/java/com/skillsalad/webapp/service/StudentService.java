package com.skillsalad.webapp.service;

import com.skillsalad.webapp.dto.LoginRequestDto;
import com.skillsalad.webapp.dto.LoginResponseDto;
import com.skillsalad.webapp.entity.Student;
import com.skillsalad.webapp.exception.EmailAlreadyExistsException;
import com.skillsalad.webapp.exception.InvalidCredentialsException;
import com.skillsalad.webapp.repository.StudentRepository;
import jakarta.transaction.Transactional;
import com.skillsalad.webapp.config.SecurityConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService{

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository,PasswordEncoder passwordEncoder){
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Student registerStudent(Student student){
        //Check Email Exits or not
        if(studentRepository.findByEmail(student.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email already registered!");
        }

        //hashed password using Bcrypt
        student.setPassword(passwordEncoder.encode(student.getPassword()));

        return studentRepository.save(student);

    }
    @Transactional
    public LoginResponseDto login(LoginRequestDto request){
        Student student= studentRepository.findByEmail(request.getEmail()).orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if(!passwordEncoder.matches(request.getPassword(), student.getPassword())){
            throw new InvalidCredentialsException("Invalid Email or Password");
        }

        return new LoginResponseDto(student.getId(),student.getName(),student.getEmail(),"Login Successful");
}
}