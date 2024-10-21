package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;


public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Procedure
    int inserir_usuario_e_interesse();
    Usuario findByEmail(String email);
    Usuario findByNome(String nome);

    Usuario findByUsername(String username);

    Usuario findUsuarioByIdUsuario(Long idUsuario);



//    @Procedure
//    Usuario inserir_usua       nrio_e_interesse(Usuario usuario, UsuarioInteresse usuarioInteresse);
}
