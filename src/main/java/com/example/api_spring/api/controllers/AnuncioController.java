package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.services.AnuncioService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/anuncio")
public class AnuncioController {

    private final AnuncioService anuncioService;

    public AnuncioController(AnuncioService anuncioService) {
        this.anuncioService = anuncioService;
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResponseAthleta> listarAnuncios(){
        try{
            ApiResponseAthleta response = anuncioService.listarAnuncios();
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ApiResponseAthleta(false, "Error", null, null)
            );
        }
    }
}
