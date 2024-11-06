package com.example.api_spring.api.controllers;

import com.example.api_spring.api.models.ApiResponseAthleta;
import com.example.api_spring.api.models.Evento;
import com.example.api_spring.api.services.EventoService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/evento")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<ApiResponseAthleta> listarEventos(@RequestParam(defaultValue = "0") int pagina,
                                                            @RequestParam(defaultValue = "10") int tamanho){
        try{
            ApiResponseAthleta response = eventoService.listarEventos(pagina,tamanho);
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @GetMapping("/listar/{nome}")
    public ResponseEntity<ApiResponseAthleta> listarEventosPorNome(@PathVariable String nome){
        try{
            ApiResponseAthleta response = eventoService.listarEventoPorNome(nome);
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @GetMapping("/listar/organizador/{id}")
    public ResponseEntity<ApiResponseAthleta> listarEventosPorOrganizador(@PathVariable int id){
        try{
            ApiResponseAthleta response = eventoService.listarEventosPorOrganizador(id);
            if(!response.isResponseSucessfull() && response.getAditionalInformation().equals("Vazio")){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (QueryTimeoutException qte){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponseAthleta(false, "Consulta mais demorada do que o esperado", null, null));
        }
    }

    @PostMapping("/inserir")
    public ResponseEntity<ApiResponseAthleta> inserirEvento(@Valid @RequestBody Evento evento){
        try {
            ApiResponseAthleta response = eventoService.inserirEvento(evento);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Error", null, null));
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<ApiResponseAthleta> excluirEvento(@PathVariable String id){
        try{
            ApiResponseAthleta response = eventoService.excluirEvento(Long.parseLong(id));
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (DataIntegrityViolationException dive){
            return ResponseEntity.status(HttpStatus.CONFLICT).body( new ApiResponseAthleta(false, "Error", null, null));
        }
    }
}
