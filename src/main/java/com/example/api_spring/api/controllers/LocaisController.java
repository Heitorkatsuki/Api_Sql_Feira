package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.services.LocalService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/local")
public class LocaisController {

    private final LocalService localService;

    public LocaisController(LocalService localService) {
        this.localService = localService;
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResponseAthleta> listarLocais(){
        try{
            ApiResponseAthleta response = localService.listarLocais();
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException dive){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Error", null, null));
        }
    }

}
