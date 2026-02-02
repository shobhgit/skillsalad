package com.skillsalad.webapp.controller;

import com.skillsalad.webapp.dto.EnrollmentRequestDto;
import com.skillsalad.webapp.entity.Enrollment;
import com.skillsalad.webapp.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "*")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    //Enroll Student in course
    @PostMapping
    public Enrollment enroll(@RequestBody EnrollmentRequestDto request){
        return enrollmentService.enrollStudent(
                request.getStudentId(),
                request.getCourseId()
        );
    }

    //Get all enrollments of a student
    @GetMapping("/student/{studentId}")
    public List<Enrollment> getStuentEnrollments(@PathVariable Long studentId){
        return enrollmentService.getEnrollmentsByStudent(studentId);
    }
}
