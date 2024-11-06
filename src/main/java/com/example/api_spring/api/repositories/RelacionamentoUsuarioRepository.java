package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.RelacionamentoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelacionamentoUsuarioRepository extends JpaRepository<RelacionamentoUsuario,Long> {

    Long countByIdUsuarioSeguidor(Long id);
    Long countByIdUsuarioSeguido(Long id);
    RelacionamentoUsuario findByIdUsuarioSeguidoAndIdUsuarioSeguidor(Long idUsuarioSeguido, Long idUsuarioSeguidor);

}
