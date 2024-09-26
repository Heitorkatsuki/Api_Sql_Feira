package com.example.api_spring.postgresql.repositories;

import com.example.api_spring.postgresql.models.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem,Long> {
}
