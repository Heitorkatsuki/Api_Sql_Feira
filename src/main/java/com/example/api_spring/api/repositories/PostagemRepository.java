package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Postagem;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem,Long> {
    List<Postagem> findAllByIdUsuario(Long idUsuario, Sort sort);

    Postagem findPostagemByIdPostagem(Long idPostagem);

    @Modifying
    @Transactional
    @Query("DELETE FROM Postagem p WHERE p.idPostagem = :id")
    void deletePostagemByIdPostagem( @Param("id") Long id);
}
