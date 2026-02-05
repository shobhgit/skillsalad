package com.skillsalad.webapp.dto;



import java.time.LocalDateTime;

public class EnrollmentResponseDto {
    private Long enrollmentId;
    private Long courseId;
    private String courseTitle;
    private String status;
    private LocalDateTime enrolledAt;

    public EnrollmentResponseDto(Long enrollmentId, Long courseId, String courseTitle, String status, LocalDateTime enrolledAt) {
        this.enrollmentId = enrollmentId;
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.status = status;
        this.enrolledAt = enrolledAt;
    }

    //Geters only

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public String getCourseTitle() {
        return courseTitle;
    }
}
