package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.Anuncio;
import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.services.AnuncioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/anuncio")
public class AnuncioController {

    private final AnuncioService anuncioService;

    public AnuncioController(AnuncioService anuncioService) {
        this.anuncioService = anuncioService;
    }

    @Operation(summary = "Lista todos os anúncios disponíveis")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anúncios listados com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))}),
            @ApiResponse(responseCode = "409", description = "Erro de conflito ao listar anúncios",
                    content = @Content)
    })
    @GetMapping("/listar")
    public ResponseEntity<ApiResponseAthleta> listarAnuncios(){
        try{
            ApiResponseAthleta response = anuncioService.listarAnuncios();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ApiResponseAthleta(false, "Error", null, null)
            );
        }
    }

    @Operation(summary = "Busca um anúncio por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anúncio encontrado",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))}),
            @ApiResponse(responseCode = "409", description = "Erro de conflito ao buscar anúncio",
                    content = @Content)
    })
    @GetMapping("/litar/{id}")
    public ResponseEntity<ApiResponseAthleta> listarAnuncioPorId(@PathVariable String id){
        try {
            ApiResponseAthleta response = anuncioService.listarAnuncioPorId(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @Operation(summary = "Insere um novo anúncio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Anúncio inserido com sucesso",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))}),
            @ApiResponse(responseCode = "409", description = "Erro de conflito ao inserir anúncio",
                    content = @Content)
    })
    @PostMapping("/inserir")
    public ResponseEntity<ApiResponseAthleta> inserirAnuncio(@RequestBody Anuncio anuncio){
        try {
            ApiResponseAthleta anuncioSalvo = anuncioService.inserirAnuncio(anuncio);
            return ResponseEntity.status(HttpStatus.CREATED).body(anuncioSalvo);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Error", null, null));
        }
    }
}
