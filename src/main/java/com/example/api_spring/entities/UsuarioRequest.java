package com.example.api_spring.postgresql.entities;

import com.example.api_spring.postgresql.models.Usuario;
import com.example.api_spring.postgresql.models.UsuarioInteresse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequest {
    Usuario usuario;
    UsuarioInteresse usuarioInteresse;
}
