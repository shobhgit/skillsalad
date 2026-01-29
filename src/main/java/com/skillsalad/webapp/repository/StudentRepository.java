package com.skillsalad.webapp.repository;

import com.skillsalad.webapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
