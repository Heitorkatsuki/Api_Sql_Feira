package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Anuncio;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnuncioRepository extends JpaRepository<Anuncio,Long> {

    Anuncio findAnuncioByIdAnuncio(Long id);
    @Modifying
    @Transactional
    @Query("DELETE FROM Anuncio a WHERE a.idAnuncio = :id")
    void deleteAnuncioByIdAnuncio(@Param("id") Long id);
}
