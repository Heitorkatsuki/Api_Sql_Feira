package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalRepository extends JpaRepository<Local,Long> {
    List<Local> findTop4By();
}
