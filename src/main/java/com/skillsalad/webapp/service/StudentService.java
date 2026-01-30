package com.skillsalad.webapp.service;

import com.skillsalad.webapp.entity.Student;
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
        //hashed password using Bcrypt
        String hashedpassword = passwordEncoder.encode(student.getPassword());
        student.setPassword(hashedpassword);

        return studentRepository.save(student);

    }
}