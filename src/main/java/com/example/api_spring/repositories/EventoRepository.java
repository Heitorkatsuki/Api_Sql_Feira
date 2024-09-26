package com.example.api_spring.postgresql.repositories;

import com.example.api_spring.postgresql.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento,Long> {
}
