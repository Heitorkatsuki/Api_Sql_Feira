package com.example.api_spring.api.services;
import com.example.api_spring.api.repositories.NotificacaoRepository;
import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Notificacao;
import com.example.api_spring.api.models.Usuario;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificacaoService {
    private final NotificacaoRepository notificacaoRepository;

    public NotificacaoService(NotificacaoRepository notificacaoRepository) {
        this.notificacaoRepository = notificacaoRepository;
    }

    public ApiResponseAthleta listarNotificacoesPorUsuario(String userid){
        try{
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(Long.parseLong(userid));
            List<Notificacao> notificacoes = notificacaoRepository.findNotificacaoByIdUsuario(usuario.getIdUsuario());
            List<Object> listaObjetos = notificacoes.stream()
                    .map(notificacao -> (Object) notificacao)
                    .toList();
            return new ApiResponseAthleta(true, "Notificações retornadas com sucesso", listaObjetos, null);
        }catch (Exception e){
            return new ApiResponseAthleta(false, "Falha ao retornar notificações", null, null);
        }
    }

    public ApiResponseAthleta inserirNotificacao(Notificacao notificacao){
        try{
            Notificacao notificacaoResponse = notificacaoRepository.save(notificacao);
            List<Object> notificacaoList = new ArrayList<>();
            notificacaoList.add(notificacaoResponse);
            return new ApiResponseAthleta(true, "Notificação inserida com sucesso", notificacaoList, null);
        }catch (Exception e){
            return new ApiResponseAthleta(false, "Não foi possível inserir a notificação", null, null);
        }
    }
}
