package com.example.api_spring.api.services;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Estado;
import com.example.api_spring.api.repositories.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public ApiResponseAthleta listarEstados(){
        try{
        List<Estado> estados = estadoRepository.findAll();
            if (!estados.isEmpty()){
                List<Object> listaObjetos = estados.stream()
                        .map(estado -> (Object) estado)
                        .toList();
                return new ApiResponseAthleta(true, "Estados retornados com sucesso!", listaObjetos, null);
            }
            return new ApiResponseAthleta(false, "Não há estados no banco", null, "Vazio");
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao retornar os estados", null, null);
        }
    }
}
