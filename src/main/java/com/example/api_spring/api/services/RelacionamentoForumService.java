package com.example.api_spring.api.services;

import com.example.api_spring.api.entities.SeguidoresResponse;
import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.RelacionamentoForum;
import com.example.api_spring.api.repositories.RelacionamentoForumRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelacionamentoForumService {

    private final RelacionamentoForumRepository relacionamentoForumRepository;

    public RelacionamentoForumService(RelacionamentoForumRepository relacionamentoForumRepository) {
        this.relacionamentoForumRepository = relacionamentoForumRepository;
    }

    public ApiResponseAthleta seguirForum(RelacionamentoForum relacionamentoForum) {
        try {
            RelacionamentoForum response = relacionamentoForumRepository.save(relacionamentoForum);
            if (response != null) {
                List<Object> relacionamentoList = new ArrayList<>();
                relacionamentoList.add(response);
                return new ApiResponseAthleta(true, "Relacionamento feito com sucesso", relacionamentoList, null);
            } else {
                return new ApiResponseAthleta(false, "Usuario não existe no banco", null, null);
            }
        } catch (Exception e) {
            return new ApiResponseAthleta(false, "Não foi possível inserir o relacionamento", null, null);
        }
    }

    public SeguidoresResponse deixarDeSeguir(Long id){
        try{
            relacionamentoForumRepository.deleteById(id);
            return new SeguidoresResponse(false);
        }catch (Exception e){
            return new SeguidoresResponse(true);
        }
    }

    public Long seguidores(Long id){
        try{
            return relacionamentoForumRepository.countByIdForum(id);
        }catch (Exception e){
            return -1L;
        }
    }

    public SeguidoresResponse relacionamento(RelacionamentoForum relacionamentoForum){
        try{
            RelacionamentoForum response = relacionamentoForumRepository.findByIdForumAndIdUsuario(
                    relacionamentoForum.getIdForum(),
                    relacionamentoForum.getIdUsuario()
            );
            if(response.getIdForum() == relacionamentoForum.getIdForum()){
                return new SeguidoresResponse(true);
            }
            else return new SeguidoresResponse(false);
        }catch (Exception e){
            return new SeguidoresResponse(false);
        }
    }
}
