package com.example.api_spring.repositories;

import com.example.api_spring.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Usuario findByEmail(String email);
    Usuario findByNome(String nome);

//    @Procedure
//    Usuario inserir_usuario_e_interesse(Usuario usuario, UsuarioInteresse usuarioInteresse);
}
