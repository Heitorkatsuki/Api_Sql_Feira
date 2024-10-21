package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
