package com.skillsalad.webapp.service;

import com.skillsalad.webapp.entity.Course;
import com.skillsalad.webapp.repository.CourseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepo courseRepo;

    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public Course addCourse(Course course){
        return courseRepo.save(course);
    }
    public List<Course> getAllCourses(){
        return courseRepo.findAll();
    }
    public Course getCourseById(Long id){
        return courseRepo.findById(id).orElse(null);
    }
}
