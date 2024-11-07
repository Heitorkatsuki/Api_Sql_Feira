package com.example.api_spring.api.controllers;

import com.example.api_spring.api.entities.LoginEmailRequest;
import com.example.api_spring.api.services.UsuarioService;
import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Usuario;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Cadastrar um novo usuário", description = "Cadastra um novo usuário no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "409", description = "Erro de integridade de dados",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/adicionar")
    public ResponseEntity<ApiResponseAthleta> adicionarUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            ApiResponseAthleta savedUserRole = usuarioService.cadastrarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUserRole);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista de todos os usuários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários retornados com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "400", description = "Lista de usuários vazia ou não encontrada",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Erro de consulta mais demorada do que o esperado",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/listar")
    public ResponseEntity<ApiResponseAthleta> listarUsuarios() {
        try {
            ApiResponseAthleta response = usuarioService.listarUsuarios();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @Operation(summary = "Listar usuário por username", description = "Retorna o usuário com o username fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Erro de consulta mais demorada do que o esperado",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/listar/{username}")
    public ResponseEntity<ApiResponseAthleta> listarUsuarioPorUsername(@PathVariable String username) {
        try {
            ApiResponseAthleta response = usuarioService.findByUsernameResponse(username);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @Operation(summary = "Listar usuário por ID", description = "Retorna o usuário com o ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "400", description = "Usuário não encontrado",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Erro de consulta mais demorada do que o esperado",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("listarporid/{id}")
    public ResponseEntity<ApiResponseAthleta> listarUsuarioPorId(@PathVariable String id) {
        try {
            ApiResponseAthleta response = usuarioService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @Operation(summary = "Listar username por email", description = "Retorna o username do usuário dado o email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Username encontrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "400", description = "Email não encontrado",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "409", description = "Erro de consulta mais demorada do que o esperado",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/listar/username/{email}")
    public ResponseEntity<?> listarUsernamePorEmail(@PathVariable String email) {
        try {
            Usuario username = usuarioService.findByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(username);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Email não está no banco");
        }
    }

    @Operation(summary = "Alterar senha do usuário", description = "Permite alterar a senha de um usuário com base no email fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha alterada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao alterar a senha",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/mudarSenha")
    public ResponseEntity<ApiResponseAthleta> mudarSenha(@RequestBody LoginEmailRequest login) {
        ApiResponseAthleta response = usuarioService.mudarSenha(login.getEmail(), login.getSenha());
        if (response.isResponseSucessfull()) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponseAthleta(false, "Não foi possível alterar a senha", null, null));
        }
    }

    @Operation(summary = "Atualizar dados do usuário", description = "Atualiza as informações de um usuário baseado no ID fornecido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "409", description = "Erro de integridade de dados",
                    content = @Content(mediaType = "application/json"))
    })
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ApiResponseAthleta> atualizarUsuario(@RequestBody Usuario usuario, @PathVariable String id) {
        try {
            ApiResponseAthleta response = usuarioService.atualizarUsuario(usuario, Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Error", null, null));
        }
    }
}
