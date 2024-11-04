package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Anuncio;
import com.example.api_spring.api.models.Esporte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EsporteRepository extends JpaRepository<Esporte,Long> {
    Esporte findEsporteByIdEsporte(Long id);
}
