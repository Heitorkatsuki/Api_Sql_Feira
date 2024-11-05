package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor,Long> {
    boolean existsById(Long id);

    Vendedor findVendedorByIdUsuario(Long idUsuario);
}
