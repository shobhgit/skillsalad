package com.skillsalad.webapp.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "enrollments",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "course_id"})
        }
)
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDateTime enrolledAt;

    @Column(nullable = false)
    private String status; //Enrolled,Completed, Canceled

    public Enrollment(User user, Course course){
        this.user=user;
        this.course=course;
        this.enrolledAt = LocalDateTime.now();
        this.status="ENROLLED";
    }
    protected Enrollment(){}
    @PrePersist
    protected void onEnroll(){
        enrolledAt=LocalDateTime.now();
        status = "ENROLLED";
    }
//GETTERS AND SETTERS


    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
