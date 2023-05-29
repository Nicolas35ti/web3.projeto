package br.com.nicolasdimer.projetodeufome.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ErrorHandler;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.nicolasdimer.projetodeufome.model.Restaurante;
import br.com.nicolasdimer.projetodeufome.service.RestauranteService;

@RestController
@RequestMapping("api/restaurantes")
public class RestauranteController {
    
    private RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService){
        this.restauranteService = restauranteService;
    }

    @GetMapping("api/restaurantes")
    public ResponseEntity<List<Restaurante>> listarRestaurante() {
        return ResponseEntity.status(200).body(restauranteService.listarRestaurante());
    }

    @PostMapping
    public ResponseEntity<Restaurante> criarRestaurante(@Valid @RequestBody Restaurante restaurante) {
        return ResponseEntity.status(201).body(restauranteService.criarRestaurante(restaurante));
    }

    @PutMapping
    public ResponseEntity<Restaurante> editarRestaurante(@Valid @RequestBody Restaurante restaurante) {
        return ResponseEntity.status(200).body(restauranteService.editarRestaurante(restaurante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirCliente(@PathVariable Integer id) {
        restauranteService.excluirRestaurante(id);
        return ResponseEntity.status(204).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String , String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName , errorMessage);
        });;

        return errors;
    }
}
