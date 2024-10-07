package com.example.api_spring.api.services;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Roles;
import com.example.api_spring.api.models.Usuario;
import com.example.api_spring.api.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    public UsuarioService(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    // TODO: ESQUECI A SENHA/, RETORNAR LOGIN, GETUSER

    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    public ApiResponseAthleta cadastrarUsuario(Usuario usuario){
        usuario.setNome(usuario.getNome().strip().toUpperCase());
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        Usuario usuarioResponse =  usuarioRepository.save(usuario);
        List<Object> usuariosList = new ArrayList<>();
        usuariosList.add(usuarioResponse);
        return new ApiResponseAthleta(true, "Usuário inserido com sucesso", usuariosList, null);
    }

//    public ApiResponse cadastrarUsuarioProcedure(Usuario usuario, UsuarioInteresse usuarioInteresse){
//        try {
//            Usuario usuarioResponse = usuarioRepository.inserir_usuario_e_interesse(usuario, usuarioInteresse);
//            List<Object> usuariosList = new ArrayList<>();
//            usuariosList.add(usuarioResponse);
//            return new ApiResponse(true, "Usuário inserido com sucesso", usuariosList, null);
//        }catch (Exception e){
//            return new ApiResponse(false, "Usuário ja existe no banco", null, null);
//        }
//    }

    public ApiResponseAthleta mudarSenha(String email, String senha){
        try {
            Usuario usuario = usuarioRepository.findByEmail(email);
            if (usuario != null){
                usuario.setSenha(senha);
                Usuario usuarioModificado = usuarioRepository.save(usuario);
                List<Object> usuarioList = new ArrayList<>();
                usuarioList.add(usuarioModificado);
                return new ApiResponseAthleta(true, "Senha do usuario de email " +email+"alterada com sucesso", usuarioList, null);
            }else{
                return new ApiResponseAthleta(false, "Usuário não encontrado no banco", null, null);
            }
        }catch (Exception exception){
            return new ApiResponseAthleta(false, "Falha ao buscar usuário", null, null);
        }
    }

    public Usuario findByUsername(String username){
        return usuarioRepository.findByUsername(username);
    }

    // TODO: PERGUNATAR SE A BUSCA DE USUARIO NO APP SERÁ FEITA NO BANCO

    public Usuario findByRoleId(Set<Roles> roles) {
        // Aqui assumo que você quer buscar pelo primeiro role na lista
        if (roles != null && !roles.isEmpty()) {
            Roles role = roles.iterator().next(); // Pega o primeiro role da lista
            return usuarioRepository.findByRoleId(role.getId());
        }
        return null; // Ou lançar uma exceção ou tratar de outra forma
    }

}
