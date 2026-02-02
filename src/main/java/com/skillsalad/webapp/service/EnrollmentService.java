package com.skillsalad.webapp.service;

import com.skillsalad.webapp.entity.Enrollment;
import com.skillsalad.webapp.exception.DuplicateEnrollmentException;
import com.skillsalad.webapp.repository.EnrollementRepo;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.List;

@Service
public class EnrollmentService {
    private final EnrollementRepo enrollmentRepo;

    public EnrollmentService(EnrollementRepo enrollmentRepo) {
        this.enrollmentRepo = enrollmentRepo;
    }

    public Enrollment enrollStudent(Long studentId, Long courseId){
        enrollmentRepo
                .findByStudentIdAndCourseId(studentId, courseId)
                .ifPresent(e ->{
                    throw new DuplicateEnrollmentException("Student already enrolled in this course");
                } );

    return enrollmentRepo.save(
            new Enrollment(studentId,courseId)
    );
    }
    public List<Enrollment>getEnrollmentsByStudent(Long studentId){
        return enrollmentRepo.findByStudentId(studentId);
    }

}
