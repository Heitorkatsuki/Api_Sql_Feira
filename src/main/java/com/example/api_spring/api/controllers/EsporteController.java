package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.repositories.EsporteRepository;
import com.example.api_spring.api.services.EsporteService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/esporte")
public class EsporteController {

    private final EsporteService esporteService;

    public EsporteController(EsporteService esporteService) {
        this.esporteService = esporteService;
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResponseAthleta> listarEsportes(){
        try{
            ApiResponseAthleta response = esporteService.listarEsportes();
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<ApiResponseAthleta> listarEsportePorId(@PathVariable Long id){
        try {
            ApiResponseAthleta response = esporteService.listarEsportePorId(id);
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }
}
