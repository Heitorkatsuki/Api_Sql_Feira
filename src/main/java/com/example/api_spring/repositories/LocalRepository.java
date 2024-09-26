package com.example.api_spring.postgresql.repositories;

import com.example.api_spring.postgresql.models.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalRepository extends JpaRepository<Local,Long> {
}
