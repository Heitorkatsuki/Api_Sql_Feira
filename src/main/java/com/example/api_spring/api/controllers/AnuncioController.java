package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.Anuncio;
import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.services.AnuncioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
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

    @Operation(summary = "Lista todos os anúncios disponíveis",
            description = "Este endpoint retorna uma lista de todos os anúncios disponíveis no sistema. Se não houver anúncios, será retornado um status 404.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Anúncios listados com sucesso",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseAthleta.class))}),
                    @ApiResponse(responseCode = "204", description = "Nenhum anúncio encontrado",
                            content = @Content),
                    @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                            content = @Content)
            })
    @GetMapping("/listar")
    public ResponseEntity<ApiResponseAthleta> listarAnuncios(){
        try {
            ApiResponseAthleta response = anuncioService.listarAnuncios();
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @Operation(summary = "Busca um anúncio por ID",
            description = "Este endpoint permite buscar um anúncio específico pelo seu ID. Se o anúncio não for encontrado, retornará um status 404.",
            parameters = {
                    @Parameter(name = "id", description = "ID do anúncio a ser buscado", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Anúncio encontrado com sucesso",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseAthleta.class))}),
                    @ApiResponse(responseCode = "404", description = "Anúncio não encontrado",
                            content = @Content),
                    @ApiResponse(responseCode = "409", description = "Erro de conflito ao buscar anúncio",
                            content = @Content)
            })
    @GetMapping("/listar/{id}")
    public ResponseEntity<ApiResponseAthleta> listarAnuncioPorId(@PathVariable String id){
        try {
            ApiResponseAthleta response = anuncioService.listarAnuncioPorId(Long.parseLong(id));
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @Operation(summary = "Insere um novo anúncio",
            description = "Este endpoint permite inserir um novo anúncio. Um status 201 é retornado em caso de sucesso, ou um status 409 em caso de conflito.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Objeto Anuncio a ser inserido", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Anuncio.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Anúncio inserido com sucesso",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApiResponseAthleta.class))}),
                    @ApiResponse(responseCode = "409", description = "Erro de conflito ao inserir anúncio",
                            content = @Content)
            })
    @PostMapping("/inserir")
    public ResponseEntity<ApiResponseAthleta> inserirAnuncio(@Valid @RequestBody Anuncio anuncio){
        try {
            ApiResponseAthleta response = anuncioService.inserirAnuncio(anuncio);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @Operation(summary = "Excluir um anúncio",
            description = "Exclui um anúncio pelo ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Anúncio excluído com sucesso"),
            @ApiResponse(responseCode = "409", description = "Conflito ao tentar excluir o anúncio"),
            @ApiResponse(responseCode = "404", description = "Anúncio não encontrado")
    })
    @DeleteMapping ("/excluir/{id}")
    public ResponseEntity<ApiResponseAthleta> excluirAnuncio(
            @Parameter(description = "ID do anúncio a ser excluído", required = true)
            @PathVariable("id") String idAnuncio){

        try{
            ApiResponseAthleta response = anuncioService.excluirPorId(Long.parseLong(idAnuncio));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (DataIntegrityViolationException dive){
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Error", null, null));
        }
    }
}
