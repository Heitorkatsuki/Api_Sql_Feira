package com.example.api_spring.api.services;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Esporte;
import com.example.api_spring.api.repositories.EsporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsporteService {

    private final EsporteRepository esporteRepository;

    public EsporteService(EsporteRepository esporteRepository) {
        this.esporteRepository = esporteRepository;
    }

    public ApiResponseAthleta listarEsportes(){
        try{
            List<Esporte> esporteList = esporteRepository.findAll();
            if (!esporteList.isEmpty()){
                List<Object> listaObjetos = esporteList.stream()
                        .map(esporte -> (Object) esporte)
                        .toList();
                return new ApiResponseAthleta(true, "Esportes retornados com sucesso!", listaObjetos, null);
            }
            return new ApiResponseAthleta(false, "Não há esportes no banco", null, "Vazio");
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao retornar esportes", null, null);
        }
    }
}
