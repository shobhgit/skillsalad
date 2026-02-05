package com.skillsalad.webapp.config;

import com.skillsalad.webapp.entity.Role;
import com.skillsalad.webapp.repository.RoleRepo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner seedRoles(RoleRepo roleRepo){
        return args -> {
            if (roleRepo.findByName("ROLE_STUDENT").isEmpty()){
                roleRepo.save(new Role("ROLE_STUDENT"));
            }
            if (roleRepo.findByName("ROLE_MENTOR").isEmpty()){
                roleRepo.save(new Role("ROLE_MENTOR"));
            }
            if (roleRepo.findByName("ROLE_ADMIN").isEmpty()){
                roleRepo.save(new Role("ROLE_ADMIN"));
            }
        };
    }
}
