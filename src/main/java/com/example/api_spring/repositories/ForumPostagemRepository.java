package com.example.api_spring.repositories;

import com.example.api_spring.models.ForumPostagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumPostagemRepository extends JpaRepository<ForumPostagem,Integer> {
}
