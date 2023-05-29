package br.com.nicolasdimer.projetodeufome.service;

import java.util.List;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.nicolasdimer.projetodeufome.model.Cliente;
import br.com.nicolasdimer.projetodeufome.repository.ICliente;

@Service
public class ClienteService {
    private ICliente repository;
    private PasswordEncoder passwordEncoder;

    public ClienteService(ICliente repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<Cliente> listarCliente() {
        List<Cliente> lista = repository.findAll();
        return lista;
    }

    public Cliente criarCliente(Cliente cliente) {
        String encoder = this.passwordEncoder.encode(cliente.getSenha());
        cliente.setSenha(encoder);
        
        if(!validarTelefone(cliente)) {
            throw new IllegalArgumentException("Número de celular inválido! Por favor insira um número com os padrões do Brasil.");
        }

        if(!validarNome(cliente)) {
            throw new IllegalArgumentException("Nome de usuário inválido! Por favor insira seu nome corretamente.");
        }
        Cliente clienteNovo = repository.save(cliente);
        return clienteNovo;
    }

    public Cliente editarCliente(Cliente cliente) {
        String encoder = this.passwordEncoder.encode(cliente.getSenha());
        cliente.setSenha(encoder);
        
        if(!validarTelefone(cliente)) {
            throw new IllegalArgumentException("Número de celular inválido! Por favor insira um número com os padrões do Brasil.");
        }

        if(!validarNome(cliente)) {
            throw new IllegalArgumentException("Nome de usuário inválido! Por favor insira seu nome corretamente.");
        }
        Cliente clienteNovo = repository.save(cliente);
        return clienteNovo;
        
    }

    public Boolean excluirCliente(Integer id) {
        repository.deleteById(id);
        return true;
    }

    public Boolean validarSenha(Cliente cliente) {
        String senha = repository.getById(cliente.getId()).getSenha();
        Boolean valid = passwordEncoder.matches(cliente.getSenha(), senha);
        return valid;
    }

        public boolean validarTelefone(Cliente cliente) {
            String telefone = cliente.getTelefone();
            String regex = "^(\\+55)?\\s?(\\(?\\d{2}\\)?)?\\s?\\d{4,5}-?\\d{4}$";
            return telefone.matches(regex);
        }

    public static boolean validarNome(Cliente cliente) {
        String nome = cliente.getNome();
        String regex = "[a-zA-Z]+";
        return nome.matches(regex);
    }

    private String getLogado() {
        Authentication userLogado = SecurityContextHolder.getContext().getAuthentication();
        if(!(userLogado instanceof AnonymousAuthenticationToken)) {
            return userLogado.getName();
        }
        return "Null";
    }
}
