package com.example.api_spring.api.services;

import com.example.api_spring.api.models.Anuncio;
import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.repositories.AnuncioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            if (!anuncios.isEmpty()){
                List<Object> listaObjetos = anuncios.stream()
                        .map(anuncio -> (Object) anuncio)
                        .toList();
                return new ApiResponseAthleta(true, "Anuncios retornados com sucesso!", listaObjetos, null);
            }
            return new ApiResponseAthleta(false, "Não há anuncios no banco", null, "Vazio");
        } catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao retornar anuncios", null, null);
        }
    }

    public ApiResponseAthleta listarAnuncioPorId(Long id){
        try {
            Anuncio response = anuncioRepository.findAnuncioByIdAnuncio(id);
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

    public ApiResponseAthleta inserirAnuncio(Anuncio anuncio){
        try{
            Anuncio anuncioResponse = anuncioRepository.save(anuncio);
            List<Object> anuncioList = new ArrayList<>();
            anuncioList.add(anuncioResponse);
            return new ApiResponseAthleta(true, "Anuncio inserido com sucesso", anuncioList, null);
        }catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível inserir o anuncio", null, null);
        }
    }

    public ApiResponseAthleta excluirPorId(Long idAnuncio){
        try{
            anuncioRepository.deleteAnuncioByIdAnuncio(idAnuncio);
            return new ApiResponseAthleta(true, "Anuncio excluido com sucesso", null, null);
        }catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível excluir o anuncio", null, null);
        }
    }
}
