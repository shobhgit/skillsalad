package com.skillsalad.webapp.service;

import com.skillsalad.webapp.entity.StudentProfile;
import com.skillsalad.webapp.repository.StudentProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentProfileRepository studentProfileRepository;

    public StudentService(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    public StudentProfile getProfileByUserId(Long userId){
        return studentProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Student Profile not found"));
        }

    public StudentProfile updateProfile(Long userId, String college, Integer year){
        StudentProfile profile=getProfileByUserId(userId);
        profile.setCollege(college);
        profile.setYear(year);
        return studentProfileRepository.save(profile);
    }
}
