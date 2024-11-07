package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.services.EsporteService;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/esporte")
public class EsporteController {

    private final EsporteService esporteService;

    public EsporteController(EsporteService esporteService) {
        this.esporteService = esporteService;
    }

    @Operation(summary = "Lista todos os esportes disponíveis",
            description = "Este endpoint retorna uma lista de todos os esportes disponíveis no sistema. Se não houver esportes, será retornado um status 404.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Esportes listados com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseAthleta.class))),
                    @ApiResponse(responseCode = "404", description = "Nenhum esporte encontrado",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                            content = @Content)
            })
    @GetMapping("/listar")
    public ResponseEntity<ApiResponseAthleta> listarEsportes(){
        try{
            ApiResponseAthleta response = esporteService.listarEsportes();
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @Operation(summary = "Lista um esporte pelo ID",
            description = "Este endpoint retorna um esporte específico com base no ID fornecido. Se o esporte não for encontrado, será retornado um status 404.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Esporte encontrado com sucesso",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseAthleta.class))),
                    @ApiResponse(responseCode = "404", description = "Esporte não encontrado",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor",
                            content = @Content)
            })
    @GetMapping("/listar/{id}")
    public ResponseEntity<ApiResponseAthleta> listarEsportePorId(@PathVariable Long id){
        try {
            ApiResponseAthleta response = esporteService.listarEsportePorId(id);
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }
}
