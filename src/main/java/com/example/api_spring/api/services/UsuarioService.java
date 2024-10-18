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

    public ApiResponseAthleta listarUsuarios() {
        try {
            List<Usuario> response = usuarioRepository.findAll();
            List<Object> listaObjetos = response.stream()
                    .map(usuario -> (Object) usuario)
                    .toList();
            return new ApiResponseAthleta(true, "Anuncios retornados com sucesso!", listaObjetos, null);
        } catch (Exception e) {
            return new ApiResponseAthleta(false, "Falha ao retornar notificações", null, null);
        }
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

    public ApiResponseAthleta findByUsernameResponse(String username){
        try{
            Usuario response = usuarioRepository.findByUsername(username);
            if(response != null){
                List<Object> anuncioList = new ArrayList<>();
                anuncioList.add(response);
                return new ApiResponseAthleta(true, "Anuncio pego com sucesso", anuncioList, null);
            }
            return new ApiResponseAthleta(false, "Anuncio não existe no banco", null, null);
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível pegar o anuncio", null, null);
        }
    }

    public ApiResponseAthleta findByEmail(String email){
        try{
            Usuario response = usuarioRepository.findByEmail(email);
            if(response != null){
                List<Object> usuarioList = new ArrayList<>();
                usuarioList.add(response);
                return new ApiResponseAthleta(true, "Anuncio pego com sucesso", usuarioList, null);
            }
            return new ApiResponseAthleta(false, "Anuncio não existe no banco", null, null);
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível pegar o anuncio", null, null);
        }
    }

    public Usuario findByRoleId(Set<Roles> roles) {
        if (roles != null && !roles.isEmpty()) {
            Roles role = roles.iterator().next();
            return usuarioRepository.findByRoleId(role.getId());
        }
        return null;
    }

    public ApiResponseAthleta atualizarUsuario(Usuario usuarioNovo, Long id){
        try{
            Usuario usuario = usuarioRepository.findUsuarioByIdUsuario(id);
            usuario.setNome(usuarioNovo.getNome());
            usuario.setEmail(usuarioNovo.getEmail());
            usuario.setRoles(usuarioNovo.getRoles());
            usuario.setUsername(usuarioNovo.getUsername());
            usuario.setDtNasc(usuarioNovo.getDtNasc());
            usuario.setFotoPerfil(usuarioNovo.getFotoPerfil());
            Usuario response = usuarioRepository.save(usuario);
            List<Object> postagemList = new ArrayList<>();
            postagemList.add(response);
            return new ApiResponseAthleta(true, "Usuario atualizado com sucesso", postagemList, null);
        }catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível atualizar o usuario", null, null);
        }
    }

}
