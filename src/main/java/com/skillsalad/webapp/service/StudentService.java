package com.skillsalad.webapp.service;

import com.skillsalad.webapp.entity.Student;
import com.skillsalad.webapp.exception.EmailAlreadyExistsException;
import com.skillsalad.webapp.repository.StudentRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService{

    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
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
}