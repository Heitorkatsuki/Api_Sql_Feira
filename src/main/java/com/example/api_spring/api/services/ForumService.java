package com.example.api_spring.api.services;

import com.example.api_spring.api.models.Anuncio;
import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Forum;
import com.example.api_spring.api.repositories.ForumRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ForumService {

    private final ForumRepository forumRepository;

    public ForumService(ForumRepository forumRepository) {
        this.forumRepository = forumRepository;
    }

    public ApiResponseAthleta listarForuns(int pagina, int tamanho){
        try {
            Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(Sort.Direction.DESC, "seguidores"));
            Page<Forum> pageableList = forumRepository.findAll(pageable);
            List<Forum> forumList = pageableList.getContent();
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
            System.out.println("Buscando fóruns com o nome: " + nome);
            List<Forum> forumList = forumRepository.findAllByNomeIgnoreCase(nome);
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

    public ApiResponseAthleta listarForumPorId(Long id){
        try {
            Forum response = forumRepository.findForumByIdForum(id);
            if(response != null){
                List<Object> anuncioList = new ArrayList<>();
                anuncioList.add(response);
                return new ApiResponseAthleta(true, "Anuncio pego com sucesso", anuncioList, null);
            }
            return new ApiResponseAthleta(false, "Anuncio não existe no banco", null, "Vazio");
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível pegar o anuncio", null, null);
        }
    }
}
