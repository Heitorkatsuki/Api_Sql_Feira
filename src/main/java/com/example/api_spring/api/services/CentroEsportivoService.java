package com.example.api_spring.api.services;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.CentroEsportivo;
import com.example.api_spring.api.repositories.CentroEsportivoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentroEsportivoService {

    private final CentroEsportivoRepository centroEsportivoRepository;

    public CentroEsportivoService(CentroEsportivoRepository centroEsportivoRepository) {
        this.centroEsportivoRepository = centroEsportivoRepository;
    }

    public ApiResponseAthleta listar(){
        try{
            List<CentroEsportivo> centroEsportivoList = centroEsportivoRepository.findAll();
            if (!centroEsportivoList.isEmpty()){
                List<Object> listaCentros = centroEsportivoList.stream()
                        .map(centroEsportivo -> (Object) centroEsportivo)
                        .toList();
                return new ApiResponseAthleta(true, "Centros esportivos retornados com sucesso!", listaCentros, null);
            }
            return new ApiResponseAthleta(false, "Não há centros esportivos no banco", null, "Vazio");
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao retornar centros esportivos", null, null);
        }
    }

    public ApiResponseAthleta listarTop5(){
        try{
            List<CentroEsportivo> centroEsportivoList = centroEsportivoRepository.findTop5By();
            if (!centroEsportivoList.isEmpty()){
                List<Object> listaCentros = centroEsportivoList.stream()
                        .map(centroEsportivo -> (Object) centroEsportivo)
                        .toList();
                return new ApiResponseAthleta(true, "Centros esportivos retornados com sucesso!", listaCentros, null);
            }
            return new ApiResponseAthleta(false, "Não há centros esportivos no banco", null, "Vazio");
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao retornar centros esportivos", null, null);
        }
    }
}
