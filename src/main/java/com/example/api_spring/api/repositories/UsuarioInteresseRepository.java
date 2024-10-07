package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Usuario;
import com.example.api_spring.api.models.UsuarioInteresse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioInteresseRepository extends JpaRepository<UsuarioInteresse,Long> {
    UsuarioInteresse findByIdUsuario(Long usuario);
}
