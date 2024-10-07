package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.ContagemSeguidores;
import com.example.api_spring.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContagemSeguidoresRepository extends JpaRepository<ContagemSeguidores,Long> {
    ContagemSeguidores findContagemSeguidoresByIdUsuario(Long idUsuario);
}
