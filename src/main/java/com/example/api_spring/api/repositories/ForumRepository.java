package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Forum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForumRepository extends JpaRepository<Forum,Long> {

    void deleteForumByIdForum(Long idForum);
    List<Forum> findAllByNomeIgnoreCase(String nome);
    Page<Forum> findAll(Pageable pageable);
}
