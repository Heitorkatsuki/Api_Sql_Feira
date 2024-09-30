package com.example.api_spring.repositories;

import com.example.api_spring.models.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalRepository extends JpaRepository<Local,Long> {
}
