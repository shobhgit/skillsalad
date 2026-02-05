package com.skillsalad.webapp.entity;
import jakarta.persistence.*;


@Entity
@Table(name = "student_profiles")
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false,unique = true)
    private User user;

    private String college;
    private Integer year;

    protected StudentProfile(){}
    public StudentProfile(User user){
        this.user=user;
    }
//Getters
    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getCollege() {
        return college;
    }

    public Integer getYear() {
        return year;
    }

//Setters (only for profile data)


    public void setCollege(String college) {
        this.college = college;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}