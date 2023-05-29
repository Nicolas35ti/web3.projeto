package br.com.nicolasdimer.projetodeufome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nicolasdimer.projetodeufome.model.Endereco;
import br.com.nicolasdimer.projetodeufome.model.Restaurante;

public interface IRestaurante extends JpaRepository<String , Integer>{

    public Restaurante findByNameOrEndereco(String nome, Endereco endereco);
}
