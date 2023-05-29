package br.com.nicolasdimer.projetodeufome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nicolasdimer.projetodeufome.model.Endereco;

public interface IEndereco extends JpaRepository<Endereco , Integer>{

    public Endereco findByeEndereco(String numero, String rua , String bairro ,String cidade, String estado);
    
}