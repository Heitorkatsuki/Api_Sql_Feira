package com.example.api_spring.services;

import com.example.api_spring.models.ApiResponse;
import com.example.api_spring.models.Usuario;
import com.example.api_spring.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    // TODO: ESQUECI A SENHA/, RETORNAR LOGIN, GETUSER

    public ApiResponse cadastrarUsuario(Usuario usuario){
        try {
            Usuario usuarioResponse = usuarioRepository.save(usuario);
            List<Object> usuariosList = new ArrayList<>();
            usuariosList.add(usuarioResponse);
            return new ApiResponse(true, "Usuário inserido com sucesso", usuariosList, null);
        }catch (Exception e){
            return new ApiResponse(false, "Usuário ja existe no banco", null, null);
        }
    }

    public ApiResponse mudarSenha(String email, String senha){
        try {
            Usuario usuario = usuarioRepository.findByEmail(email);
            if (usuario != null){
                usuario.setSenha(senha);
                Usuario usuarioModificado = usuarioRepository.save(usuario);
                List<Object> usuarioList = new ArrayList<>();
                usuarioList.add(usuarioModificado);
                return new ApiResponse(true, "Senha do usuario de email " +email+"alterada com sucesso", usuarioList, null);
            }else{
                return new ApiResponse(false, "Usuário não encontrado no banco", null, null);
            }
        }catch (Exception exception){
            return new ApiResponse(false, "Falha ao buscar usuário", null, null);
        }
    }

    // TODO: PERGUNATAR SE A BUSCA DE USUARIO NO APP SERÁ FEITA NO BANCO

}
