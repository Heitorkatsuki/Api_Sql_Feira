package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento,Long> {
    void deleteEventoByIdEvento(Long idEvento);

    List<Evento> findAllByNome(String nome);

    Page<Evento> findAll(Pageable pageable);
}
