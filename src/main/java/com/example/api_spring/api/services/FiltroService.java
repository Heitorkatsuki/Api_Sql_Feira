package com.example.api_spring.api.services;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Filtro;
import com.example.api_spring.api.repositories.FiltroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiltroService {

    private final FiltroRepository filtrosRepository;

    public FiltroService(FiltroRepository filtrosRepository) {
        this.filtrosRepository = filtrosRepository;
    }

    public ApiResponseAthleta listar(){
        try{
            List<Filtro> filtrosList = filtrosRepository.findAll();
            if (!filtrosList.isEmpty()){
                List<Object> listaObjetos = filtrosList.stream()
                        .map(filtro -> (Object) filtro)
                        .toList();
                return new ApiResponseAthleta(true, "Filtros retornados com sucesso!", listaObjetos, null);
            }
            return new ApiResponseAthleta(false, "Não há filtros no banco", null, "Vazio");
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao retornar filtros", null, null);
        }
    }
}
