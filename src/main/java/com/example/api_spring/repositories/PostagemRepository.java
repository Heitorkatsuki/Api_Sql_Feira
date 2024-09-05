package com.example.api_spring.repositories;

import com.example.api_spring.models.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem,Integer> {
}
