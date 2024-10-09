package com.example.api_spring.api.services;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Forum;
import com.example.api_spring.api.repositories.ForumRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ForumService {

    private final ForumRepository forumRepository;

    public ForumService(ForumRepository forumRepository) {
        this.forumRepository = forumRepository;
    }

    public ApiResponseAthleta listarForuns(){
        try {
            List<Forum> forumList = forumRepository.findAll();
            if (!forumList.isEmpty()){
                List<Object> listaObjetos = forumList.stream()
                        .map(forum -> (Object) forum)
                        .toList();
                return new ApiResponseAthleta(true, "Foruns retornados com sucesso!", listaObjetos, null);
            }
            return new ApiResponseAthleta(false, "Não há foruns no banco", null, "Vazio");
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao retornar foruns", null, null);
        }
    }

    public ApiResponseAthleta listarForunsPorNome(String nome){
        try {
            List<Forum> forumList = forumRepository.findAllByNome(nome);
            if (!forumList.isEmpty()){
                List<Object> listaObjetos = forumList.stream()
                        .map(forum -> (Object) forum)
                        .toList();
                return new ApiResponseAthleta(true, "Foruns retornados com sucesso!", listaObjetos, null);
            }
            return new ApiResponseAthleta(false, "Não há foruns no banco", null, "Vazio");
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao retornar foruns", null, null);
        }
    }

    public ApiResponseAthleta inserir(Forum forum){
        try{
            Forum forumResponse = forumRepository.save(forum);
            List<Object> forumList = new ArrayList<>();
            forumList.add(forumResponse);
            return new ApiResponseAthleta(true, "Forum inserido com sucesso", forumList, null);
        }catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível inserir o forum", null, null);
        }
    }

    public ApiResponseAthleta excluir(Long id){
        try{
            forumRepository.deleteForumByIdForum(id);
            return new ApiResponseAthleta(true, "Forum excluido com sucesso", null, null);
        }catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível forum o evento", null, null);
        }
    }
}
