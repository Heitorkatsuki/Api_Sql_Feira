package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Evento;
import com.example.api_spring.api.services.EventoService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/evento")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @Operation(summary = "Listar eventos", description = "Retorna uma lista de eventos paginados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eventos retornados com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao listar eventos, dados inválidos",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Erro de conflito ao listar eventos",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/listar")
    public ResponseEntity<ApiResponseAthleta> listarEventos(@RequestParam(defaultValue = "0") int pagina,
                                                            @RequestParam(defaultValue = "10") int tamanho){
        try{
            ApiResponseAthleta response = eventoService.listarEventos(pagina,tamanho);
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @Operation(summary = "Listar eventos por nome", description = "Retorna eventos baseados no nome fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eventos retornados com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao listar eventos, dados inválidos",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Erro de conflito ao listar eventos",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/listar/{nome}")
    public ResponseEntity<ApiResponseAthleta> listarEventosPorNome(@PathVariable String nome){
        try{
            ApiResponseAthleta response = eventoService.listarEventoPorNome(nome);
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @Operation(summary = "Listar eventos por organizador", description = "Retorna eventos baseados no ID do organizador.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Eventos retornados com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao listar eventos, dados inválidos",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Erro de conflito ao listar eventos",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/listar/organizador/{id}")
    public ResponseEntity<ApiResponseAthleta> listarEventosPorOrganizador(@PathVariable int id){
        try{
            ApiResponseAthleta response = eventoService.listarEventosPorOrganizador(id);
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @Operation(summary = "Inserir evento", description = "Permite inserir um novo evento no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Evento inserido com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "409", description = "Erro de conflito ao inserir evento",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/inserir")
    public ResponseEntity<ApiResponseAthleta> inserirEvento(@Valid @RequestBody Evento evento){
        try {
            ApiResponseAthleta response = eventoService.inserirEvento(evento);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @Operation(summary = "Excluir evento", description = "Permite excluir um evento com base no ID fornecido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento excluído com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "409", description = "Erro de conflito ao excluir evento",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<ApiResponseAthleta> excluirEvento(@PathVariable String id){
        try{
            ApiResponseAthleta response = eventoService.excluirEvento(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException dive){
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Error", null, null));
        }
    }
}
