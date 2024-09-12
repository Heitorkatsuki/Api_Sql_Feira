package com.example.api_spring.controllers;

import com.example.api_spring.models.ApiResponse;
import com.example.api_spring.models.Usuario;
import com.example.api_spring.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final Validator validator;

    public UsuarioController(UsuarioService usuarioService, Validator validator){
        this.usuarioService = usuarioService;
        this.validator = validator;
    }

//    @PostMapping("/adicionar")
//    public ResponseEntity<ApiResponse>adicionarUsuario(@Valid @RequestBody Usuario usuario, BindingResult bindingResult){
//        ApiResponse response = usuarioService.cadastrarUsuario(usuario);
//        if (response.isResponseSucessfull()){
//            return ResponseEntity.ok(response);
//        }else{
//            return ResponseEntity.badRequest().body(response);
//        }
//    }

    @PostMapping("/adicionar")
    public ResponseEntity<ApiResponse> adicionarUsuario(@Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
        ApiResponse response = new ApiResponse();
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder("Erros de validação: ");
            bindingResult.getFieldErrors().forEach(error -> {
                errorMessage.append(error.getField())
                        .append(" ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            });
            response.setDescription(errorMessage.toString());
            response.setResponseSucessfull(false);
            return ResponseEntity.badRequest().body(response);
        }
        response = usuarioService.cadastrarUsuario(usuario);
        if (response.isResponseSucessfull()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PatchMapping("/mudarSenha/{email}/{novaSenha}")
    public ResponseEntity<ApiResponse> mudarSenha(@PathVariable String email, @PathVariable String novaSenha){
        ApiResponse response = usuarioService.mudarSenha(email, novaSenha);
        if (response.isResponseSucessfull()){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.badRequest().body(response);
        }
    }

    public static boolean isNotIdFine(int id){
        return id <= 0;
    }

}