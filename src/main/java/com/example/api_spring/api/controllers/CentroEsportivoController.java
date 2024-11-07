package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.services.CentroEsportivoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/centro")
public class CentroEsportivoController {

    private final CentroEsportivoService centroEsportivoService;

    public CentroEsportivoController(CentroEsportivoService centroEsportivoService) {
        this.centroEsportivoService = centroEsportivoService;
    }

    @Operation(summary = "Lista todos os centros esportivos disponíveis",
            description = "Este endpoint retorna uma lista de todos os centros esportivos disponíveis no sistema. Se não houver centros, será retornado um status 404.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Centros esportivos listados com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseAthleta.class))),
                    @ApiResponse(responseCode = "404", description = "Nenhum centro esportivo encontrado",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                            content = @Content)
            })
    @GetMapping("/listar")
    public ResponseEntity<ApiResponseAthleta> listarCentroEsportivo(){
        try{
            ApiResponseAthleta response = centroEsportivoService.listar();
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @Operation(summary = "Lista os primeiros 5 centros esportivos",
            description = "Este endpoint retorna os primeiros 5 centros esportivos. Se não houver centros, será retornado um status 404.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Top 5 centros esportivos listados com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseAthleta.class))),
                    @ApiResponse(responseCode = "404", description = "Nenhum centro esportivo encontrado",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                            content = @Content)
            })
    @GetMapping("/listarprimeiros")
    public ResponseEntity<ApiResponseAthleta> listarTop5CentrosEsportivos(){
        try{
            ApiResponseAthleta response = centroEsportivoService.listarTop5();
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }
}
