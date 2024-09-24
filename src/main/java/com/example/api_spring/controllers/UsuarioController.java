package com.example.api_spring.controllers;

import com.example.api_spring.entities.LoginRequest;
import com.example.api_spring.entities.UsuarioRequest;
import com.example.api_spring.models.ApiResponse;
import com.example.api_spring.models.Usuario;
import com.example.api_spring.services.UsuarioService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Password;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final Validator validator;

//    private final SecretKey secretKey;

    public UsuarioController(UsuarioService usuarioService, Validator validator) {
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
//    public Map<String, String> login(@RequestBody LoginRequest loginRequest){
//        Usuario usuario = usuarioService.findByUsername(loginRequest.getUsername());
//        if (usuario != null && passwordEncoder.matches(loginRequest.getPassword(), usuario.getSenha())){
//            try {
//                String token = Jwts.builder()
//                        .setSubject(loginRequest.getUsername())
//                        .claim("roles", usuario.getRoles().stream().map(Roles::getRoleName).toArray())
//                        .setExpiration(new Date(System.currentTimeMillis() + 86_400_000))
//                        .signWith(secretKey, SignatureAlgorithm.HS512)
//                        .compact();
//
//                logger.info("Generated token: {}", token);
//                return Map.of("token","Bearer "+ token);
//            }catch (Exception e){
//                logger.error("Error on generate JWS token : ", e);
//                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao gerar o token JWT",e);
//            }
//        }
//        else {
//            logger.error("Invalid credentials for username: {}", loginRequest.getUsername());
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
//        }
//    }

//    @PostMapping("/adicionar")
//    public ResponseEntity<Usuario> adicionarUsuario(@Valid @RequestBody Usuario usuario) {
//        //try {
//            Usuario savedUserRole = usuarioService.cadastrarUsuario(usuario);
//            return ResponseEntity.status(HttpStatus.CREATED).body(savedUserRole);
////        } catch (DataIntegrityViolationException dive) {
////            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
////        }
//    }

    @PostMapping("/adicionar")
    public ResponseEntity<?> adicionarUsuario(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario savedUserRole = usuarioService.cadastrarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUserRole);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar usuário: " + e.getMessage());
        }
    }


//    @PostMapping("/adicionarProcedure")
//    public ResponseEntity<ApiResponse> adicionarUsuarioProcedure(@Valid @RequestBody UsuarioRequest usuarioRequest, BindingResult bindingResult) {
//        ApiResponse response = new ApiResponse();
//        if (bindingResult.hasErrors()) {
//            StringBuilder errorMessage = new StringBuilder("Erros de validação: ");
//            bindingResult.getFieldErrors().forEach(error -> {
//                errorMessage.append(error.getField())
//                        .append(" ")
//                        .append(error.getDefaultMessage())
//                        .append("; ");
//            });
//            response.setDescription(errorMessage.toString());
//            response.setResponseSucessfull(false);
//            return ResponseEntity.badRequest().body(response);
//        }
//        response = usuarioService.cadastrarUsuarioProcedure(usuarioRequest.getUsuario(), usuarioRequest.getUsuarioInteresse());
//        if (response.isResponseSucessfull()) {
//            return ResponseEntity.ok(response);
//        } else {
//            return ResponseEntity.badRequest().body(response);
//        }
//    }



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