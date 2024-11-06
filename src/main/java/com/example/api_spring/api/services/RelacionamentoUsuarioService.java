package com.example.api_spring.api.services;

import com.example.api_spring.api.entities.SeguidoresResponse;
import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.RelacionamentoUsuario;
import com.example.api_spring.api.repositories.RelacionamentoUsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RelacionamentoUsuarioService {

    private final RelacionamentoUsuarioRepository relacionamentoUsuarioRepository;

    public RelacionamentoUsuarioService(RelacionamentoUsuarioRepository relacionamentoUsuarioRepository) {
        this.relacionamentoUsuarioRepository = relacionamentoUsuarioRepository;
    }

    public ApiResponseAthleta seguirUsuario(RelacionamentoUsuario relacionamentoUsuario){
        try{
            RelacionamentoUsuario response = relacionamentoUsuarioRepository.save(relacionamentoUsuario);
            if(response != null){
                List<Object> relacionamentoList = new ArrayList<>();
                relacionamentoList.add(response);
                return new ApiResponseAthleta(true, "Relacionamento feito com sucesso", relacionamentoList, null);
            }
            else {
                return new ApiResponseAthleta(false, "Usuario não existe no banco", null, null);
            }
        }catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível inserir o relacionamento", null, null);
        }
    }

    public ApiResponseAthleta pararDeSeguir(Long id) {
        try{
            relacionamentoUsuarioRepository.deleteById(id);
            return new ApiResponseAthleta(true, "Exclusão feito com sucesso", null, null);
        }catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível inserir o relacionamento", null, null);
        }
    }

    public Long seguindo(Long id){
        try {
            return relacionamentoUsuarioRepository.countByIdUsuarioSeguidor(id);
        }
        catch (Exception e){
            return -1L;
        }
    }

    public Long seguidores(Long id) {
        try {
            return relacionamentoUsuarioRepository.countByIdUsuarioSeguido(id);
        }
        catch (Exception e){
            return -1L;
        }
    }

    public SeguidoresResponse relacionamento(RelacionamentoUsuario relacionamentoUsuario){
        try{
            RelacionamentoUsuario response = relacionamentoUsuarioRepository.findByIdUsuarioSeguidoAndIdUsuarioSeguidor(
                    relacionamentoUsuario.getIdUsuarioSeguido(),
                    relacionamentoUsuario.getIdUsuarioSeguidor()
            );
            if (response.getIdUsuarioSeguido() == relacionamentoUsuario.getIdUsuarioSeguido()){
                return new SeguidoresResponse(true);
            }
            else return new SeguidoresResponse(false);
        }catch (Exception e){
            return new SeguidoresResponse(false);
        }
    }
}
