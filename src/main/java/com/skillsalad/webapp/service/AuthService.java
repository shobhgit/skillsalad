package com.skillsalad.webapp.service;

import com.skillsalad.webapp.dto.LoginRequestDto;
import com.skillsalad.webapp.dto.LoginResponseDto;
import com.skillsalad.webapp.dto.RegisterStudentRequestDto;
import com.skillsalad.webapp.entity.Role;
import com.skillsalad.webapp.entity.StudentProfile;
import com.skillsalad.webapp.entity.User;
import com.skillsalad.webapp.exception.EmailAlreadyExistsException;
import com.skillsalad.webapp.exception.InvalidCredentialsException;
import com.skillsalad.webapp.repository.RoleRepo;
import com.skillsalad.webapp.repository.StudentProfileRepository;
import com.skillsalad.webapp.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final StudentProfileRepository studentProfileRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepo userRepo, RoleRepo roleRepo, StudentProfileRepository studentProfileRepository, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.studentProfileRepository = studentProfileRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //---------------------REGISTER STUDENT---------------------------

    @Transactional
    public User registerStudent(RegisterStudentRequestDto request){
        if (userRepo.findByEmail(request.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email already registered");
        }
        User user = new User(
                request.getName(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );
        Role studentRole =roleRepo.findByName("ROLE_STUDENT")
                .orElseThrow(() -> new RuntimeException("ROLE_STUDENT not found"));

        user.addRole(studentRole);
        userRepo.save(user);

        //create student profile
        StudentProfile profile = new StudentProfile(user);
        studentProfileRepository.save(profile);
        return user;
    }

    //-------------------LOGIN-------------------------

    public LoginResponseDto login(LoginRequestDto request){
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(()->new InvalidCredentialsException("Invalid Email or Password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw  new InvalidCredentialsException("Invalid Email Or Password");
        }

        return new LoginResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                "Login Successful"
        );
    }
}

