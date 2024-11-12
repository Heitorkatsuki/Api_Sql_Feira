package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Forum;
import com.example.api_spring.api.services.ForumService;
import io.swagger.v3.oas.annotations.media.Content;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/api/forum")
public class ForumController {

    private final ForumService forumService;

    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    @Operation(summary = "Listar fóruns", description = "Retorna uma lista de fóruns com paginação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fóruns retornados com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "400", description = "Fóruns não encontrados ou lista vazia",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Erro de consulta mais demorada do que o esperado",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/listar")
    public ResponseEntity<ApiResponseAthleta> listarForuns(@RequestParam(defaultValue = "0") int pagina,
                                                           @RequestParam(defaultValue = "10") int tamanho){
        try{
            ApiResponseAthleta response = forumService.listarForuns(pagina,tamanho);
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @Operation(summary = "Listar fóruns por nome", description = "Retorna fóruns que correspondem ao nome fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fóruns encontrados com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "400", description = "Fóruns não encontrados com o nome fornecido",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Erro de consulta mais demorada do que o esperado",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/listar/{nome}")
    public ResponseEntity<ApiResponseAthleta> listarForunsPorNome(@PathVariable String nome){
        try {
            ApiResponseAthleta response = forumService.listarForunsPorNome(nome);
            if(!response.isResponseSucessfull()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @Operation(summary = "Inserir fórum", description = "Cria um novo fórum no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Fórum criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "409", description = "Erro ao inserir fórum devido a violação de integridade de dados",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/inserir")
    public ResponseEntity<ApiResponseAthleta> inserirForum(@Valid @RequestBody Forum forum){
        try {
            ApiResponseAthleta response = forumService.inserir(forum);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Erro ao inserir fórum", null, null));
        }
    }

    @Operation(summary = "Excluir fórum", description = "Exclui um fórum pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fórum excluído com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "409", description = "Erro ao excluir fórum devido a violação de integridade de dados",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<ApiResponseAthleta> excluirForum(@PathVariable Long id){
        try {
            ApiResponseAthleta response = forumService.excluir(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException dive){
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Erro ao excluir fórum", null, null));
        }
    }

    @Operation(summary = "Listar fórum por ID", description = "Retorna o fórum com o ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fórum encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "404", description = "Fórum não encontrado com o ID fornecido",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Erro de consulta mais demorada do que o esperado",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/listar/id/{id}")
    public ResponseEntity<ApiResponseAthleta> listarForumPorId(@PathVariable Long id){
        try {
            ApiResponseAthleta response = forumService.listarForumPorId(id);
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @GetMapping("/listar/organizador/{organizador}")
    public ResponseEntity<ApiResponseAthleta> listarForumPorOrganizador(@PathVariable String organizador){
        try {
            ApiResponseAthleta response = forumService.listarForumPorUsuarioResp(Long.parseLong(organizador));
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

}
