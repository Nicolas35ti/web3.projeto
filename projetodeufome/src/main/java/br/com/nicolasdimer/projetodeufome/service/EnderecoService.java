package br.com.nicolasdimer.projetodeufome.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.nicolasdimer.projetodeufome.model.Endereco;
import br.com.nicolasdimer.projetodeufome.repository.IEndereco;

@Service
public class EnderecoService {
    private IEndereco repository;

    public EnderecoService(IEndereco repository){
        this.repository = repository;
    }

    public List<Endereco> listarEndereco() {
        List<Endereco>lista = repository.findAll();
        return lista;
    }

    public Endereco criarEndereco(Endereco endereco){
        if(endereco.getNumero() == null) {
            throw new IllegalArgumentException("Número inválido! Por favor insira um número valido.");
        }

        if(endereco.getRua() == null) {
            throw new IllegalArgumentException("Rua inválida! Por favor insira um endereço de rua válido.");
        }

        if(endereco.getBairro() == null) {
            throw new IllegalArgumentException("Bairro inválido! Por favor insira um endereço de bairro válido.");
        }

        if(endereco.getCidade() == null) {
            throw new IllegalArgumentException("Cidade inválida! Por favor insire um endereço de cidade válido.");
        }

        if(endereco.getEstado() == null) {
            throw new IllegalArgumentException("Estado inválido! Por favor insira um endereço de estado válido");
        }

        Endereco enderecoNovo = repository.save(endereco);
        return enderecoNovo;
    }

    public Endereco editarEndereco(Endereco endereco){
        if(endereco.getNumero() == null) {
            throw new IllegalArgumentException("Número inválido! Por favor insira um número valido.");
        }

        if(endereco.getRua() == null) {
            throw new IllegalArgumentException("Rua inválida! Por favor insira um endereço de rua válido.");
        }

        if(endereco.getBairro() == null) {
            throw new IllegalArgumentException("Bairro inválido! Por favor insira um endereço de bairro válido.");
        }

        if(endereco.getCidade() == null) {
            throw new IllegalArgumentException("Cidade inválida! Por favor insire um endereço de cidade válido.");
        }

        if(endereco.getEstado() == null) {
            throw new IllegalArgumentException("Estado inválido! Por favor insira um endereço de estado válido");
        }

        Endereco enderecoNovo = repository.save(endereco);
        return enderecoNovo;
    }

    public Boolean excluirEndereco (Integer id) {
        repository.deleteById(id);
        return true;
    }
}
