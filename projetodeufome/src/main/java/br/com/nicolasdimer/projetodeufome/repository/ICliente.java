package br.com.nicolasdimer.projetodeufome.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nicolasdimer.projetodeufome.model.Cliente;

public interface ICliente extends JpaRepository<Cliente , Integer>{

    public Cliente findBynomeOrEmail(String nome, String email);
    
}
