package br.com.nicolasdimer.projetodeufome.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.nicolasdimer.projetodeufome.model.Endereco;
import br.com.nicolasdimer.projetodeufome.repository.IEndereco;
import br.com.nicolasdimer.projetodeufome.service.EnderecoService;

@RestController
@RequestMapping("api/endereco")
public class EnderecoController {
    
    private EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }


    @GetMapping("/endereco")
    public ResponseEntity<List<Endereco>> listaEndereco () {
        return ResponseEntity.status(200).body(enderecoService.listarEndereco());
    }

    @PreAuthorize("hasRole('DEV')")
    public ResponseEntity<String> criarEndereco(@RequestBody Endereco endereco) {
        enderecoService.criarEndereco(endereco);
        return ResponseEntity.ok("Endereço criado com sucesso");
    }

    @PreAuthorize("hasRole('DEV')")
    public ResponseEntity<String> editarEndereco(@RequestBody Endereco endereco) {
        enderecoService.editarEndereco(endereco);
        return ResponseEntity.ok("Endereço criado com sucesso");
    }

@DeleteMapping("/{id}")
    public ResponseEntity<?> excluirCliente (@PathVariable Integer id) {
        enderecoService.excluirEndereco(id);
        return ResponseEntity.status(204).build();
    }

}
