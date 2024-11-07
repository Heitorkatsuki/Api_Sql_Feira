package com.example.api_spring.api.controllers;

import com.example.api_spring.api.entities.SeguidoresResponse;
import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.RelacionamentoUsuario;
import com.example.api_spring.api.services.RelacionamentoUsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/api/relacionamento")
public class RelacionamentoUsuarioController {

    private final RelacionamentoUsuarioService relacionamentoUsuarioService;

    public RelacionamentoUsuarioController(RelacionamentoUsuarioService relacionamentoUsuarioService) {
        this.relacionamentoUsuarioService = relacionamentoUsuarioService;
    }

    @Operation(
            summary = "Seguir um usuário",
            description = "Este endpoint permite que um usuário siga outro usuário.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Relacionamento de usuários", required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RelacionamentoUsuario.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuário seguido com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseAthleta.class))),
                    @ApiResponse(responseCode = "409", description = "Erro de conflito ao tentar seguir o usuário", content = @Content)
            }
    )
    @PostMapping("/seguir")
    public ResponseEntity<ApiResponseAthleta> seguir(@RequestBody RelacionamentoUsuario relacionamentoUsuario){
        try {
            ApiResponseAthleta response = relacionamentoUsuarioService.seguirUsuario(relacionamentoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @Operation(
            summary = "Parar de seguir um usuário",
            description = "Este endpoint permite que um usuário pare de seguir outro usuário.",
            parameters = {
                    @Parameter(name = "id", description = "ID do usuário a ser deixado de seguir", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário deixou de seguir com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SeguidoresResponse.class))),
                    @ApiResponse(responseCode = "409", description = "Erro de conflito ao tentar deixar de seguir o usuário", content = @Content)
            }
    )
    @DeleteMapping("/seguir/{id}")
    public ResponseEntity<SeguidoresResponse> pararDeSeguir(@PathVariable String id){
        try {
            SeguidoresResponse response = relacionamentoUsuarioService.pararDeSeguir(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new SeguidoresResponse(true));
        }
    }

    @Operation(
            summary = "Obter o número de usuários que o usuário está seguindo",
            description = "Este endpoint retorna o número de usuários que o usuário está seguindo.",
            parameters = {
                    @Parameter(name = "id", description = "ID do usuário", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Número de usuários seguidos retornado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))),
                    @ApiResponse(responseCode = "409", description = "Erro de conflito ao buscar número de usuários seguidos", content = @Content)
            }
    )
    @GetMapping("/seguindo/{id}")
    public ResponseEntity<Long> seguindo(@PathVariable String id){
        try {
            Long response = relacionamentoUsuarioService.seguindo(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(-1L);
        }
    }

    @Operation(
            summary = "Obter o número de seguidores de um usuário",
            description = "Este endpoint retorna o número de seguidores de um usuário.",
            parameters = {
                    @Parameter(name = "id", description = "ID do usuário", required = true)
            },
            responses = {
                    @ApiResponse(responseCode = "200", description = "Número de seguidores retornado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))),
                    @ApiResponse(responseCode = "409", description = "Erro de conflito ao buscar número de seguidores", content = @Content)
            }
    )
    @GetMapping("/seguidores/{id}")
    public ResponseEntity<Long> seguidores(@PathVariable String id){
        try {
            Long response = relacionamentoUsuarioService.seguidores(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(-1L);
        }
    }

    @Operation(
            summary = "Verificar se há relacionamento entre dois usuários",
            description = "Este endpoint verifica se um usuário segue outro usuário.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Relacionamento de usuários", required = true,
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = RelacionamentoUsuario.class))),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Resultado do relacionamento retornado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SeguidoresResponse.class))),
                    @ApiResponse(responseCode = "409", description = "Erro de conflito ao verificar relacionamento", content = @Content)
            }
    )
    @PostMapping("/existe")
    public ResponseEntity<SeguidoresResponse> existe(@RequestBody RelacionamentoUsuario relacionamentoUsuario){
        try {
            SeguidoresResponse response = relacionamentoUsuarioService.relacionamento(relacionamentoUsuario);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new SeguidoresResponse(false));
        }
    }
}
