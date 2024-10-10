package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.CentroEsportivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CentroEsportivoRepository extends JpaRepository<CentroEsportivo,Long> {
    List<CentroEsportivo> findTop5By();
}
