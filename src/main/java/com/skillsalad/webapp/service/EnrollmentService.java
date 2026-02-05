package com.skillsalad.webapp.service;

import com.skillsalad.webapp.dto.EnrollmentResponseDto;
import com.skillsalad.webapp.entity.Course;
import com.skillsalad.webapp.entity.Enrollment;
import com.skillsalad.webapp.entity.User;
import com.skillsalad.webapp.exception.DuplicateEnrollmentException;
import com.skillsalad.webapp.repository.CourseRepo;
import com.skillsalad.webapp.repository.EnrollementRepo;
import com.skillsalad.webapp.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.DuplicateFormatFlagsException;
import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollementRepo enrollmentRepo;
    private final UserRepo userRepo;
    private final CourseRepo courseRepo;

    public EnrollmentService(EnrollementRepo enrollmentRepo, UserRepo userRepo, CourseRepo courseRepo) {
        this.enrollmentRepo = enrollmentRepo;
        this.userRepo = userRepo;
        this.courseRepo = courseRepo;
    }
    @Transactional
    public EnrollmentResponseDto enrollStudent(Long userId, Long courseId){
        
       User user = userRepo.findById(userId)
               .orElseThrow(()->new RuntimeException("User not found"));

       boolean isStudent = user.getRoles().stream()
               .anyMatch(r->r.getName().equals("ROLE_STUDENT"));


       if(!isStudent){
           throw new RuntimeException("Role Check failed"+user.getRoles());
       }

       Course course = courseRepo.findById(courseId)
               .orElseThrow(()-> new RuntimeException("Course not found"));

       enrollmentRepo.findByUser_IdAndCourse_CourseId(  userId ,  courseId)
               .ifPresent(e -> {
                   throw new DuplicateEnrollmentException("Student already enrolled in this course");
               });
       Enrollment saved = enrollmentRepo.save(new Enrollment(user,course));
       return mapToDto(saved);
    }
    public List<EnrollmentResponseDto>getEnrollmentsByUser(Long userId){
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        return enrollmentRepo.findByUser_Id(userId )
                .stream()
                .map(this::mapToDto)
                .toList();
    }
    private EnrollmentResponseDto mapToDto(Enrollment enrollment){
        return new EnrollmentResponseDto(
                enrollment.getId(),
                enrollment.getCourse().getCourseId(),
                enrollment.getCourse().getTitle(),
                enrollment.getStatus(),
                enrollment.getEnrolledAt()
        );
    }

}