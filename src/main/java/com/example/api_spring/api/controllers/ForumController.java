package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Forum;
import com.example.api_spring.api.services.ForumService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
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
    public ResponseEntity<ApiResponseAthleta> listarForuns(@RequestParam(defaultValue = "0") int pagina,
                                                           @RequestParam(defaultValue = "10") int tamanho){
        try{
            ApiResponseAthleta response = forumService.listarForuns(pagina,tamanho);
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @GetMapping("/listar/{nome}")
    public ResponseEntity<ApiResponseAthleta> listarForunsPorNome(@PathVariable String nome){
        try {
            ApiResponseAthleta response = forumService.listarForunsPorNome(nome);
            if(!response.isResponseSucessfull()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
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

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<ApiResponseAthleta> excluirForum(@PathVariable Long id){
        try {
            ApiResponseAthleta response = forumService.excluir(id);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException dive){
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<ApiResponseAthleta> listarForumPorId(@PathVariable Long id){
        try {
            ApiResponseAthleta response = forumService.listarForumPorId(id);
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }
}
