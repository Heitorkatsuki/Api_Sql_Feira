package com.example.api_spring.postgresql.repositories;

import com.example.api_spring.postgresql.models.Esporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EsporteRepository extends JpaRepository<Esporte,Long> {
}
