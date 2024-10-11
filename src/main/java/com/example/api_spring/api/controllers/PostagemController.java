package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Postagem;
import com.example.api_spring.api.services.PostagemService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/postagem")
public class PostagemController {

    private final PostagemService postagemService;

    public PostagemController(PostagemService postagemService) {
        this.postagemService = postagemService;
    }

    @Operation(summary = "Listar postagens recentes", description = "Retorna todas as postagens recentes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Postagens retornadas com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma postagem encontrada",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/listardata")
    public ResponseEntity<ApiResponseAthleta> listarPostagensRecentes(){
        try {
            ApiResponseAthleta response = postagemService.listarPostagensRecentes();
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @Operation(summary = "Listar postagens recentes por ID de usuário", description = "Retorna todas as postagens recentes de um determinado usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Postagens retornadas com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma postagem encontrada",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/listardata/{idUsuario}")
    public ResponseEntity<ApiResponseAthleta> listarPostagensRecentesPorId(@PathVariable String idUsuario){
        try {
            ApiResponseAthleta response = postagemService.listarPostagensRecentesPorId(Long.parseLong(idUsuario));
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @Operation(summary = "Inserir nova postagem", description = "Insere uma nova postagem no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Postagem inserida com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "409", description = "Erro de conflito ao inserir postagem",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/inserir")
    public ResponseEntity<ApiResponseAthleta> inserirPostagem(@Valid @RequestBody Postagem postagem){
        try{
            ApiResponseAthleta response = postagemService.inserirPostagem(postagem);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "O id não deve ser igual a uma postagem existente", null, null));
        }
    }

    @Operation(summary = "Editar uma postagem", description = "Edita o texto de uma postagem existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Postagem editada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "409", description = "Erro de conflito ao editar postagem",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/editar/{idPostagem}/{texto}")
    public ResponseEntity<ApiResponseAthleta> editarPostagem(
            @PathVariable String idPostagem,
            @PathVariable String texto){
        try{
            ApiResponseAthleta response = postagemService.editarPostagem(Long.parseLong(idPostagem),texto);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @Operation(summary = "Excluir uma postagem", description = "Exclui uma postagem existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Postagem excluída com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "409", description = "Erro de conflito ao excluir postagem",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/excluir/{idPostagem}")
    public ResponseEntity<ApiResponseAthleta> excluirPostagem(@PathVariable String idPostagem){
        try{
            ApiResponseAthleta response = postagemService.excluirPostagem(Long.parseLong(idPostagem));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "O id deve ser igual a uma postagem existente", null, null));
        }
    }
}
