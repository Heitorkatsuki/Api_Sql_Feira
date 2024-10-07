package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioRepository extends JpaRepository<Anuncio,Long> {
}
