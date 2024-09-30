package com.example.api_spring.repositories;

import com.example.api_spring.models.Esporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EsporteRepository extends JpaRepository<Esporte,Long> {
}
