package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventoRepository extends JpaRepository<Evento,Long> {
    void deleteEventoByIdEvento(Long idEvento);

    List<Evento> findAllByNome(String nome);

    List<Evento> findAllByOrganizador(Long organizador);

    Page<Evento> findAll(Pageable pageable);

    @Procedure(procedureName = "notificar_evento_novo")
    void notificarEventoNovo(@Param("id_evento") Long idEvento, @Param("organizador") Long organizador);
}
