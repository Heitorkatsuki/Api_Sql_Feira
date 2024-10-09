package com.example.api_spring.api.services;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Local;
import com.example.api_spring.api.repositories.LocalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalService {

    private final LocalRepository localRepository;

    public LocalService(LocalRepository localRepository) {
        this.localRepository = localRepository;
    }

    public ApiResponseAthleta listarLocais(){
        try{
            List<Local> localList = localRepository.findTop4By();
            if (!localList.isEmpty()){
                List<Object> listaObjetos = localList.stream()
                        .map(local -> (Object) local)
                        .toList();
                return new ApiResponseAthleta(true, "Locais retornados com sucesso!", listaObjetos, null);
            }
            return new ApiResponseAthleta(false, "Não há locais no banco", null, "Vazio");
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao retornar locais", null, null);
        }
    }

}
