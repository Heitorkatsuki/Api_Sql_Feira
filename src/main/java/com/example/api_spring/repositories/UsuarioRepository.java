package com.example.api_spring.repositories;

import com.example.api_spring.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
    Usuario findByEmail(String email);
}
