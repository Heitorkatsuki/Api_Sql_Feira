package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoRepository extends JpaRepository<Estado,Long> {
}
