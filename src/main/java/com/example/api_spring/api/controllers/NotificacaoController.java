package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponse;
import com.example.api_spring.api.services.NotificacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notificacao")
public class NotificacaoController {
    private final NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }


    @GetMapping("/listar/{userId}")
    public ResponseEntity<ApiResponse> listarNotificacoesPorUsuario(@PathVariable String userId){
        ApiResponse response = notificacaoService.listarNotificacoesPorUsuario(userId);
        if (response.isResponseSucessfull()){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.badRequest().body(response);
        }
    }
}
