package com.example.api_spring.api.controllers;

import com.example.api_spring.api.entities.SeguidoresResponse;
import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.RelacionamentoForum;
import com.example.api_spring.api.services.RelacionamentoForumService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forumrelacionamento")
public class RelacionamentoForumController {

    private final RelacionamentoForumService relacionamentoForumService;

    public RelacionamentoForumController(RelacionamentoForumService relacionamentoForumService) {
        this.relacionamentoForumService = relacionamentoForumService;
    }

    @PostMapping("/seguir")
    public ResponseEntity<ApiResponseAthleta> seguir(@RequestBody RelacionamentoForum relacionamentoForum){
        try {
            ApiResponseAthleta response = relacionamentoForumService.seguirForum(relacionamentoForum);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @DeleteMapping("/seguir/{id}")
    public ResponseEntity<SeguidoresResponse> pararDeSeguir(@PathVariable String id){
        try {
            SeguidoresResponse response = relacionamentoForumService.deixarDeSeguir(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new SeguidoresResponse(true));
        }
    }

    @GetMapping("/seguidores/{id}")
    public ResponseEntity<Long> seguidores(@PathVariable String id){
        try {
            Long response = relacionamentoForumService.seguidores(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(-1L);
        }
    }

    @PostMapping("/existe")
    public ResponseEntity<SeguidoresResponse> existe(@RequestBody RelacionamentoForum relacionamentoForum){
        try {
            SeguidoresResponse response = relacionamentoForumService.relacionamento(relacionamentoForum);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new SeguidoresResponse(false));
        }
    }

}
