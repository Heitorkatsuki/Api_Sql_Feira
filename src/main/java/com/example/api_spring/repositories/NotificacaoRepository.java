package com.example.api_spring.repositories;

import com.example.api_spring.models.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao,Integer> {
}
