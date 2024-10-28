package com.example.api_spring.api.services;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Role;
import com.example.api_spring.api.models.Usuario;
import com.example.api_spring.api.repositories.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        try{
            usuario.setNome(usuario.getNome().strip());
            usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
            usuario.setUserRole(2L);
            if(maisQueIdadeMinima(usuario.getDtNasc())){
                Usuario usuarioResponse =  usuarioRepository.save(usuario);
                List<Object> usuariosList = new ArrayList<>();
                usuariosList.add(usuarioResponse);
                return new ApiResponseAthleta(true, "Usuário inserido com sucesso", usuariosList, null);
            }
            else {
                return new ApiResponseAthleta(false, "Usuário não possui a idade mínima de 13 anos", null, "Idade");
            }
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível inserir o anuncio", null, null);
        }
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

    public Usuario findByEmail(String email){
        Usuario usuario = usuarioRepository.findByEmail(email);
        Usuario username = new Usuario();
        username.setUsername(usuario.getUsername());
        return username;
    }

    public ApiResponseAthleta atualizarUsuario(Usuario usuarioNovo, Long id){
        try{
            Usuario usuario = usuarioRepository.findUsuarioByIdUsuario(id);
            usuario.setNome(usuarioNovo.getNome());
            usuario.setEmail(usuarioNovo.getEmail());
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

    public boolean maisQueIdadeMinima(Date dataNasc){
        LocalDate dataNascLocal = dataNasc.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dataAtual = LocalDate.now();
        return Period.between(dataNascLocal,dataAtual).getYears() >= 13;
    }
}
