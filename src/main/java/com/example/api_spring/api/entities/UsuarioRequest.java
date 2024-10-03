package com.example.api_spring.api.entities;

import com.example.api_spring.api.models.UsuarioInteresse;
import com.example.api_spring.api.models.Usuario;
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
