package br.com.nicolasdimer.projetodeufome.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data

@Entity
@Table(name = "restaurante")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome do restaurante é obrigatório!")
    @Size(min = 3, message = "O nome do restaurante deve ter no mínimo 3 caracteres!")
    @Column(length = 200, nullable = false , unique = true)
    private String nome;

    @NotBlank(message = "O telefone é obrigatório!")
    @Column(length = 15, nullable = true, unique = true)
    private String telefone;

    @NotBlank(message = "O endereço é obrigatório!")
    @Column(length = 150 , nullable = false)
    private Endereco endereco;
}
