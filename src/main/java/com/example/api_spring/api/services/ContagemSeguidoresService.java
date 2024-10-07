package com.example.api_spring.api.services;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.ContagemSeguidores;
import com.example.api_spring.api.repositories.ContagemSeguidoresRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContagemSeguidoresService {

    private final ContagemSeguidoresRepository contagemSeguidoresRepository;

    public ContagemSeguidoresService(ContagemSeguidoresRepository contagemSeguidoresRepository) {
        this.contagemSeguidoresRepository = contagemSeguidoresRepository;
    }

    public ApiResponseAthleta numeroSeguidoresPorId(Long idUsuario){
        try{
            ContagemSeguidores contagemSeguidores = contagemSeguidoresRepository.findContagemSeguidoresByIdUsuario(idUsuario);
            List<Object> contagemSequidoresList = new ArrayList<>();
            contagemSequidoresList.add(contagemSeguidores);
            return new ApiResponseAthleta(true, "Notificações retornadas com sucesso", contagemSequidoresList, null);
        }catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao retornar notificações", null, null);
        }
    }

    public ApiResponseAthleta seguirUsuario(Long idUsuario){
        try{
            ContagemSeguidores usuario = contagemSeguidoresRepository.findContagemSeguidoresByIdUsuario(idUsuario);
            ContagemSeguidores contagemSeguidores = contagemSeguidoresRepository.save(usuario);
            List<Object> contagemSequidoresList = new ArrayList<>();
            contagemSequidoresList.add(contagemSeguidores);
            return new ApiResponseAthleta(true, "Seguidores aumentados com sucesso", contagemSequidoresList, null);
        }catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao aumentar seguidores", null, null);
        }
    }
    public ApiResponseAthleta deixarDeSeguirUsuario(Long idUsuario){
        try{
            ContagemSeguidores usuario = contagemSeguidoresRepository.findContagemSeguidoresByIdUsuario(idUsuario);
            contagemSeguidoresRepository.delete(usuario);
            return new ApiResponseAthleta(true, "Seguidores diminuidos com sucesso", null, null);
        }catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao diminuir seguidores", null, null);
        }
    }

}
