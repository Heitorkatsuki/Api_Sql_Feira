package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.services.CentroEsportivoService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/centro")
public class CentroEsportivoController {

    private final CentroEsportivoService centroEsportivoService;

    public CentroEsportivoController(CentroEsportivoService centroEsportivoService) {
        this.centroEsportivoService = centroEsportivoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResponseAthleta> listarCentroEsportivo(){
        try{
            ApiResponseAthleta response = centroEsportivoService.listar();
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @GetMapping("/listarprimeiros")
    public ResponseEntity<ApiResponseAthleta> listarTop5CentrosEsportivos(){
        try{
            ApiResponseAthleta response = centroEsportivoService.listarTop5();
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }
}
