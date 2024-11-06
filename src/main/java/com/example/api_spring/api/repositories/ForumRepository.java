package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Anuncio;
import com.example.api_spring.api.models.Forum;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ForumRepository extends JpaRepository<Forum,Long> {

    List<Forum> findAllByNomeIgnoreCase(String nome);
    Page<Forum> findAll(Pageable pageable);
    Forum findForumByIdForum(Long id);
    @Modifying
    @Transactional
    @Query("DELETE FROM Forum f WHERE f.idForum = :id")
    void deleteForumByIdForum(@Param("id") Long idForum);
}
