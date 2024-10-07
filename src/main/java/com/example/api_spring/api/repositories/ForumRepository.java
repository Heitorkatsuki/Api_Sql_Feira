package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum,Long> {
}
