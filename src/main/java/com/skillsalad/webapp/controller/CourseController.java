package com.skillsalad.webapp.controller;

import com.skillsalad.webapp.entity.Course;
import com.skillsalad.webapp.service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin(origins = "*")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    //TODO : Restrict to ADMIN/MENTOR after auth roles
    public Course addCourse(@RequestBody Course course){
        return courseService.addCourse(course);
    }

    @GetMapping
    public List<Course>getAllCourses(){
        return courseService.getAllCourses();
    }

    @PostMapping("/{id}")
    public Course getCourseById(@PathVariable Long id){
        return courseService.getCourseById(id);
    }
}
