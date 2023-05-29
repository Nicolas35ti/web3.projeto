package br.com.nicolasdimer.projetodeufome.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.stereotype.Service;

import br.com.nicolasdimer.projetodeufome.model.Restaurante;
import br.com.nicolasdimer.projetodeufome.repository.IRestaurante;

@Service
public class RestauranteService {
    private IRestaurante repository;

    public RestauranteService(IRestaurante repository){
        this.repository = repository;
    }

    public List<Restaurante> listarRestaurante() {
        List<Restaurante>lista = repository.findAll();
        return lista;
    }

    public Restaurante criarRestaurante(Restaurante restaurante) {
        if(!validarTelefone(restaurante)) {
            throw new IllegalArgumentException("Número de celular inválido! Por favor insira um número com os padrões do Brasil.");
        }

        if(!validarNome(restaurante)) {
            throw new IllegalArgumentException("Nome do restaurante inválido! O nome do seu restaurante pode conter apenas letras ou números");
        }
        Restaurante restauranteNovo = repository.save(restaurante);
        return restauranteNovo;
    }

    public Restaurante editarRestaurante(Restaurante restaurante) {
        if(!validarTelefone(restaurante)) {
            throw new IllegalArgumentException("Número de celular inválido! Por favor insira um número com os padrões do Brasil");
        }

        if(!validarNome(restaurante)) {
            throw new IllegalArgumentException("Nome do restaurante inválido! O nome do seu restaurante pode conter apenas letras ou números");
        }

        Restaurante restauranteNovo = repository.save(restaurante);
        return restauranteNovo;
    }

    public Boolean excluirRestaurante(Integer id) {
        repository.deleteById(id);
        return true;
    }

    public boolean validarTelefone(Restaurante restaurante) {
        String telefone =restaurante.getTelefone();
        String regex = "^(\\+55)?\\s?(\\(?\\d{2}\\)?)?\\s?\\d{4,5}-?\\d{4}$";
        return telefone.matches(regex);
    }
        public static boolean validarNome(Restaurante restaurante) {
            String nome = restaurante.getNome();
            String regex = "[a-zA-Z0-9]+";
            return nome.matches(regex);
        }
    }
