package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Notificacao;
import com.example.api_spring.api.services.NotificacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/notificacao")
public class NotificacaoController {
    private final NotificacaoService notificacaoService;

    public NotificacaoController(NotificacaoService notificacaoService) {
        this.notificacaoService = notificacaoService;
    }

    @Operation(summary = "Listar notificações de um usuário",
            description = "Retorna uma lista de notificações associadas ao usuário pelo ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notificações retornadas com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida ou usuário não encontrado",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/listar/{userId}")
    public ResponseEntity<ApiResponseAthleta> listarNotificacoesPorUsuario(
            @Parameter(description = "ID do usuário cujas notificações devem ser retornadas", required = true, example = "1")
            @PathVariable String userId) {
        try {
            ApiResponseAthleta response = notificacaoService.listarNotificacoesPorUsuario(userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @PostMapping("/adicionar")
    @Operation(summary = "Adicionar Notificação",
            description = "Insere uma nova notificação no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notificação criada com sucesso."),
            @ApiResponse(responseCode = "409", description = "Conflito ao tentar adicionar a notificação, possivelmente devido a dados duplicados."),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor.")
    })
    public ResponseEntity<ApiResponseAthleta> adicionarNotificacao(
            @Parameter(description = "Dados da notificação a ser adicionada.", required = true)
            @RequestBody Notificacao notificacao) {
        try {
            ApiResponseAthleta notificacaoSalva = notificacaoService.inserirNotificacao(notificacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(notificacaoSalva);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Error", null, null));
        }
    }

}
