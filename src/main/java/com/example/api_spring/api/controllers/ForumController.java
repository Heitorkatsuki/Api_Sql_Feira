package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Forum;
import com.example.api_spring.api.services.ForumService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forum")
public class ForumController {

    private final ForumService forumService;

    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResponseAthleta> listarForuns(){
        try{
            ApiResponseAthleta response = forumService.listarForuns();
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException dive){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @GetMapping("/listar/{nome}")
    public ResponseEntity<ApiResponseAthleta> listarForunsPorNome(@PathVariable String nome){
        try {
            ApiResponseAthleta response = forumService.listarForunsPorNome(nome);
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException dive){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @PostMapping("/inserir")
    public ResponseEntity<ApiResponseAthleta> inserirForum(@Valid @RequestBody Forum forum){
        try {
            ApiResponseAthleta response = forumService.inserir(forum);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<ApiResponseAthleta> excluirForum(String id){
        try {
            ApiResponseAthleta response = forumService.excluir(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException dive){
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Error", null, null));
        }
    }
}
