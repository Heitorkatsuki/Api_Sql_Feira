package com.example.api_spring.services;

import com.example.api_spring.models.ApiResponse;
import com.example.api_spring.models.Notificacao;
import com.example.api_spring.models.Usuario;
import com.example.api_spring.repositories.NotificacaoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificacaoService {
    private final NotificacaoRepository notificacaoRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    public ApiResponse listarNotificacoesPorUsuario(String userid){
        try{
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(Long.parseLong(userid));
            List<Notificacao> notificacoes = notificacaoRepository.findNotificacaoByUsuarioId(usuario);
            List<Object> listaObjetos = notificacoes.stream()
                    .map(notificacao -> (Object) notificacao)
                    .toList();
            return new ApiResponse(true, "Notificações retornadas com sucesso", listaObjetos, null);
        }catch (Exception e){
            return new ApiResponse(false, "Falha ao retornar notificações", null, null);
        }
    }

    public ApiResponse inserirNotificacao(Notificacao notificacao){
        try{
            Notificacao notificacaoResponse = notificacaoRepository.save(notificacao);
            List<Object> notificacaoList = new ArrayList<>();
            notificacaoList.add(notificacaoResponse);
            return new ApiResponse(true, "Notificação inserida com sucesso", notificacaoList, null);
        }catch (Exception e){
            return new ApiResponse(false, "Não foi possível inserir a notificação", null, null);
        }
    }
}
