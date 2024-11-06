package com.example.api_spring.api.controllers;

import com.example.api_spring.api.entities.SeguidoresResponse;
import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.RelacionamentoUsuario;
import com.example.api_spring.api.services.RelacionamentoUsuarioService;
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

    @PostMapping("/seguir")
    public ResponseEntity<ApiResponseAthleta> seguir(@RequestBody RelacionamentoUsuario relacionamentoUsuario){
        try {
            ApiResponseAthleta response = relacionamentoUsuarioService.seguirUsuario(relacionamentoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @DeleteMapping("/seguir/{id}")
    public ResponseEntity<ApiResponseAthleta> pararDeSeguir(@PathVariable String id){
        try {
            ApiResponseAthleta response = relacionamentoUsuarioService.pararDeSeguir(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @GetMapping("/seguindo/{id}")
    public ResponseEntity<Long> seguindo(@PathVariable String id){
        try {
            Long response = relacionamentoUsuarioService.seguindo(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (QueryTimeoutException qte) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( -1L);
        }
    }

    @GetMapping("/seguidores/{id}")
    public ResponseEntity<Long> seguidores(@PathVariable String id){
        try {
            Long response = relacionamentoUsuarioService.seguidores(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (QueryTimeoutException qte) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( -1L);
        }
    }

    @GetMapping("/existe")
    public ResponseEntity<SeguidoresResponse> seguidores(@RequestBody RelacionamentoUsuario relacionamentoUsuario){
        try {
            SeguidoresResponse response = relacionamentoUsuarioService.relacionamento(relacionamentoUsuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (QueryTimeoutException qte) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new SeguidoresResponse(false));
        }
    }
}
