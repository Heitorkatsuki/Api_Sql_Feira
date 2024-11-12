package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.RelacionamentoForum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelacionamentoForumRepository extends JpaRepository<RelacionamentoForum, Long> {
    Long countByIdForum(Long id);

    RelacionamentoForum findByIdForumAndIdUsuario(Long idForum, Long idUsuario);
}
