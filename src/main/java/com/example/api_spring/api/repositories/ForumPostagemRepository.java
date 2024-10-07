package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.ForumPostagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumPostagemRepository extends JpaRepository<ForumPostagem,Long> {
}
