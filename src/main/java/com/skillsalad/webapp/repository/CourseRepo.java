package com.skillsalad.webapp.repository;

import com.skillsalad.webapp.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepo extends JpaRepository<Course, Long> {
}
