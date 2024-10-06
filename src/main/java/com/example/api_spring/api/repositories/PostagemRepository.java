package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem,Long> {
}
