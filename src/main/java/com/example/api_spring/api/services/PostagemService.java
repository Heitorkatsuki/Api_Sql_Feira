package com.example.api_spring.api.services;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Postagem;
import com.example.api_spring.api.repositories.PostagemRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PostagemService{

    private final PostagemRepository postagemRepository;

    public PostagemService(PostagemRepository postagemRepository) {
        this.postagemRepository = postagemRepository;
    }

    public ApiResponseAthleta listarPostagensRecentes(){
        try{
            List<Postagem> postagemList = postagemRepository.findAll(Sort.by(Sort.Direction.DESC, "dtPostagem"));
            if (!postagemList.isEmpty()){
                List<Object> listaObjetos = postagemList.stream()
                        .map(postagem -> (Object) postagem)
                        .toList();
                return new ApiResponseAthleta(true, "Postagens retornadas com sucesso!", listaObjetos, null);
            }
            return new ApiResponseAthleta(false, "Não há postagem no banco", null, "Vazio");
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao retornar postagens", null, null);
        }
    }

    public ApiResponseAthleta listarPostagensRecentesPorId(Long idUsuario){
        try{
            List<Postagem> postagemList = postagemRepository.findAllByIdUsuario( idUsuario, Sort.by(Sort.Direction.DESC, "dtPostagem"));
            if (!postagemList.isEmpty()){
                List<Object> listaObjetos = postagemList.stream()
                        .map(postagem -> (Object) postagem)
                        .toList();
                return new ApiResponseAthleta(true, "Postagens retornadas com sucesso!", listaObjetos, null);
            }
            return new ApiResponseAthleta(false, "Não há postagens referente a este usuário no banco", null, "Vazio");
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao retornar postagens", null, null);
        }
    }

    public ApiResponseAthleta inserirPostagem(Postagem postagem){
        try{
            postagem.setDtPostagem(new Date());
            Postagem response = postagemRepository.save(postagem);
            List<Object> postagemList = new ArrayList<>();
            postagemList.add(response);
            return new ApiResponseAthleta(true, "Postagem inserida com sucesso", postagemList, null);
        }catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível inserir a postagem", null, null);
        }
    }

    public ApiResponseAthleta editarPostagem(Long idPostagem, String texto){
        try {
            Postagem postagem = postagemRepository.findPostagemByIdPostagem(idPostagem);
            postagem.setTexto(texto);
            Postagem response = postagemRepository.save(postagem);
            List<Object> postagemList = new ArrayList<>();
            postagemList.add(response);
            return new ApiResponseAthleta(true, "Postagem atualizada com sucesso", postagemList, null);
        }catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível inserir a postagem", null, null);
        }
    }

    public ApiResponseAthleta excluirPostagem(Long idPostagem){
        try{
            postagemRepository.deletePostagemByIdPostagem(idPostagem);
            return new ApiResponseAthleta(true,"Postagem excluida com sucesso",null,null);
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível excluir a postagem", null, null);
        }
    }
}
