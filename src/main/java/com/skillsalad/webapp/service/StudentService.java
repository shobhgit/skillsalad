package com.skillsalad.webapp.service;

import com.skillsalad.webapp.entity.Student;
import com.skillsalad.webapp.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService{

    private final StudentRepository studentRepository;


    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student registerStudent(Student student){
        return studentRepository.save(student);
    }
}