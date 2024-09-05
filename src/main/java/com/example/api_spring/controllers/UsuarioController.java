package com.example.api_spring.controllers;

import com.example.api_spring.models.Usuario;
import com.example.api_spring.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final Validator validator;

    public UsuarioController(UsuarioService usuarioService, Validator validator){
        this.usuarioService = usuarioService;
        this.validator = validator;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<String>adicionarUsuario(@Valid @RequestBody Usuario usuario, BindingResult bindingResult){
        try{
            Usuario usuar = usuarioService.cadastrarUsuario(usuario);
            if(isNotIdFine(usuar.getIdUsuario())) throw new RuntimeException();
            return ResponseEntity.ok("Produto inserido com sucesso");
        }catch (ClassCastException cce){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
        }catch (RuntimeException rte){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro na requisição.");
        }
    }

    public static boolean isNotIdFine(int id){
        return id <= 0;
    }

}