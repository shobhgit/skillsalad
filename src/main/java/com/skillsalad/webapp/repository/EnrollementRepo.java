package com.skillsalad.webapp.repository;

import com.skillsalad.webapp.entity.Course;
import com.skillsalad.webapp.entity.Enrollment;
import com.skillsalad.webapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EnrollementRepo extends JpaRepository<Enrollment, Long> {

    Optional<Enrollment> findByUser_IdAndCourse_CourseId(Long userId, Long courseId);

    List<Enrollment> findByUser_Id(Long userId);
}
