package com.example.api_spring.api.repositories;

import com.example.api_spring.api.models.Notificacao;
import com.example.api_spring.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacaoRepository extends JpaRepository<Notificacao,Long> {
    List<Notificacao> findNotificacaoByUsuarioId(Usuario usuario);
}
