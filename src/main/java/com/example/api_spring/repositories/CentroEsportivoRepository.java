package com.example.api_spring.postgresql.repositories;

import com.example.api_spring.postgresql.models.CentroEsportivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CentroEsportivoRepository extends JpaRepository<CentroEsportivo,Long> {
}
