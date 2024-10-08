package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.services.EstadoService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @Operation(summary = "Listar estados", description = "Retorna todos os estados disponíveis.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estados retornados com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum estado encontrado",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Erro de conflito ao listar estados",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/listar")
    public ResponseEntity<ApiResponseAthleta> listarEstados(){
        try {
            ApiResponseAthleta response = estadoService.listarEstados();
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Error", null, null));
        }
    }

}
