package com.example.api_spring.repositories;

import com.example.api_spring.models.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum,Integer> {
}
