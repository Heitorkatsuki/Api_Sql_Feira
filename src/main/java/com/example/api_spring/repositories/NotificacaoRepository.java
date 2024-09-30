package com.example.api_spring.repositories;

import com.example.api_spring.models.Notificacao;
import com.example.api_spring.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificacaoRepository extends JpaRepository<Notificacao,Long> {
    List<Notificacao> findNotificacaoByUsuarioId(Usuario usuario);
}
