package com.example.api_spring.api.services;

import com.example.api_spring.api.models.Usuario;
import com.example.api_spring.api.models.UsuarioInteresse;
import com.example.api_spring.api.repositories.UsuarioInteresseRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioInteresseService {

    private final UsuarioInteresseRepository usuarioInteresseRepository;

    public UsuarioInteresseService(UsuarioInteresseRepository usuarioInteresseRepository) {
        this.usuarioInteresseRepository = usuarioInteresseRepository;
    }

    public UsuarioInteresse findbyUsuarioId(Usuario usuario){
        return usuarioInteresseRepository.findByIdUsuario(usuario);
    }
}
