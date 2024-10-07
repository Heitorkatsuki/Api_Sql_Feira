package com.example.api_spring.api.services;

import com.example.api_spring.api.models.Anuncio;
import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.repositories.AnuncioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnuncioService {

    private final AnuncioRepository anuncioRepository;

    public AnuncioService(AnuncioRepository anuncioRepository) {
        this.anuncioRepository = anuncioRepository;
    }

    public ApiResponseAthleta listarAnuncios(){
        try {
            List<Anuncio> anuncios = anuncioRepository.findAll();
            List<Object> listaObjetos = anuncios.stream()
                    .map(notificacao -> (Object) notificacao)
                    .toList();
            return new ApiResponseAthleta(true, "Anuncios retornados com sucesso!", listaObjetos, null);
        }
        catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao retornar notificações", null, null);
        }
    }
}
