package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Vendedor;
import com.example.api_spring.api.services.VendedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendedor")
public class VendedorController {
    private final VendedorService vendedorService;

    public VendedorController(VendedorService vendedorService) {
        this.vendedorService = vendedorService;
    }

    @Operation(summary = "Adicionar vendedor", description = "Cadastra um novo vendedor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vendedor cadastrado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseAthleta.class))),
            @ApiResponse(responseCode = "409", description = "Erro de integridade de dados",
                    content = @Content(mediaType = "application/json"))
    })
    @PostMapping("/adicionar")
    public ResponseEntity<ApiResponseAthleta> adicionarVendedor(@Valid @RequestBody Vendedor vendedor) {
        try {
            ApiResponseAthleta novoVendedor = vendedorService.cadastrarVendedor(vendedor);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoVendedor);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @Operation(summary = "Verificar existência de vendedor", description = "Verifica se um vendedor existe pelo id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vendedor encontrado",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Vendedor não encontrado",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/existe/{id}")
    public Boolean checarVendedorExiste(@PathVariable Long id) {
        boolean exists = vendedorService.checarVendedor(id);
        return exists;
    }

    @Operation(summary = "Listar telefone de vendedor por ID", description = "Retorna o telefone de um vendedor pelo ID de usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Telefone do vendedor retornado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Vendedor.class))),
            @ApiResponse(responseCode = "404", description = "ID de vendedor não encontrado",
                    content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/listar/telefone/{id}")
    public ResponseEntity<?> listarTelefonePorId(@PathVariable Long id){
        try{
            Vendedor telefone = vendedorService.findVendedorByIdUsuario(id);
            return ResponseEntity.status(HttpStatus.OK).body(telefone);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id não está no banco");
        }
    }
}
