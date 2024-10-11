package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.services.ContagemSeguidoresService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seguidores")
public class ContagemSeguidoresController {

    private final ContagemSeguidoresService contagemSeguidoresService;

    public ContagemSeguidoresController(ContagemSeguidoresService contagemSeguidoresService) {
        this.contagemSeguidoresService = contagemSeguidoresService;
    }

    @Operation(summary = "Obter contagem de seguidores de um usuário",
            description = "Retorna a contagem de seguidores para o usuário especificado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contagem de seguidores retornada com sucesso."),
            @ApiResponse(responseCode = "409", description = "Conflito de dados ao tentar acessar a contagem.")
    })
    @GetMapping("/contagem/{idUsuario}")
    public ResponseEntity<ApiResponseAthleta> contagemSeguidores(
            @Parameter(description = "ID do usuário para o qual obter a contagem de seguidores")
            @PathVariable String idUsuario) {
        try {
            ApiResponseAthleta response = contagemSeguidoresService.numeroSeguidoresPorId(Long.parseLong(idUsuario));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @Operation(summary = "Seguir um usuário",
            description = "Permite que o usuário atual siga o usuário especificado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário seguido com sucesso."),
            @ApiResponse(responseCode = "409", description = "Conflito de dados ao tentar seguir o usuário.")
    })
    @PostMapping("/seguir/{idUsuario}")
    public ResponseEntity<ApiResponseAthleta> seguirUsuario(
            @Parameter(description = "ID do usuário a ser seguido")
            @PathVariable String idUsuario) {
        try {
            ApiResponseAthleta response = contagemSeguidoresService.seguirUsuario(Long.parseLong(idUsuario));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @Operation(summary = "Deixar de seguir um usuário",
            description = "Permite que o usuário atual deixe de seguir o usuário especificado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário deixado de seguir com sucesso."),
            @ApiResponse(responseCode = "409", description = "Conflito de dados ao tentar deixar de seguir o usuário.")
    })
    @PostMapping("/deixardeseguir/{idUsuario}")
    public ResponseEntity<ApiResponseAthleta> deixarDeSeguirUsuario(
            @Parameter(description = "ID do usuário a ser deixado de seguir")
            @PathVariable String idUsuario) {
        try {
            ApiResponseAthleta response = contagemSeguidoresService.deixarDeSeguirUsuario(Long.parseLong(idUsuario));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Error", null, null));
        }
    }
}
