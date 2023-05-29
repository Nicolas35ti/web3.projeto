package br.com.nicolasdimer.projetodeufome.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.ErrorManager;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import antlr.Token;
import br.com.nicolasdimer.projetodeufome.repository.ICliente;
import br.com.nicolasdimer.projetodeufome.service.ClienteService;
import br.com.nicolasdimer.projetodeufome.dto.ClienteDto;
import br.com.nicolasdimer.projetodeufome.model.Cliente;


@RestController
@RequestMapping("api/cliente")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/cliente")
    public ResponseEntity<List<Cliente>> listaClientes () {
        return ResponseEntity.status(200).body(clienteService.listarCliente());
    }

    @PostMapping
    public ResponseEntity<Cliente> criarCliente (@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.status(201).body(clienteService.criarCliente(cliente));
    }

    @PutMapping
    public ResponseEntity<Cliente> editarCliente (@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.status(200).body(clienteService.editarCliente(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirCliente (@PathVariable Integer id) {
        clienteService.excluirCliente(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Token> logar (@Valid @RequestBody ClienteDto cliente) {
        Boolean valid = clienteService.logar(cliente);
        if(!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.status(200).build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

}

