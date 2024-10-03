package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento,Long> {
}
