package com.skillsalad.webapp.repository;

import com.skillsalad.webapp.entity.StudentProfile;
import com.skillsalad.webapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile,Long> {

    Optional<StudentProfile>findByUserId(Long userId);
}
