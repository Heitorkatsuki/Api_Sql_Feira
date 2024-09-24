package com.example.api_spring.entities;

import com.example.api_spring.models.Usuario;
import com.example.api_spring.models.UsuarioInteresse;
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