package com.example.api_spring.api.controllers;


import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Usuario;
import com.example.api_spring.api.models.Vendedor;
import com.example.api_spring.api.services.UsuarioService;
import com.example.api_spring.api.services.VendedorService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
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

    @PostMapping("/adicionar")
    public ResponseEntity<ApiResponseAthleta> adicionarVendedor(@Valid @RequestBody Vendedor vendedor) {
        try {
            ApiResponseAthleta novoVendedor = vendedorService.cadastrarVendedor(vendedor);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoVendedor);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @GetMapping("/existe/{id}")
    public Boolean checarVendedorExiste(@PathVariable Long id) {
        boolean exists = vendedorService.checarVendedor(id);
        return exists;
    }
}

