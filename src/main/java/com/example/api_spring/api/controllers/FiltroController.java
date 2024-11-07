package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.services.FiltroService;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/filtro")
public class FiltroController {

    private final FiltroService filtroService;

    public FiltroController(FiltroService filtroService) {
        this.filtroService = filtroService;
    }

    @Operation(summary = "Listar filtros", description = "Retorna uma lista de filtros.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filtros retornados com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "404", description = "Filtros não encontrados",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Erro de consulta mais demorada do que o esperado",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/listar")
    public ResponseEntity<ApiResponseAthleta> listar(){
        try{
            ApiResponseAthleta response = filtroService.listar();
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }
}
