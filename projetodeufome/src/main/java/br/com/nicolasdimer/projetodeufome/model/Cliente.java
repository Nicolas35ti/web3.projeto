package br.com.nicolasdimer.projetodeufome.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.List;

import lombok.Data;

@Data

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O nome é obrigatório!")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres!")
    @Column(length = 200, nullable = false , unique = true)
    private String nome;

    @NotBlank(message = "A senha é obrigatória!")
    @Column(columnDefinition = "TEXT", nullable = true)
    private String senha;

    @NotBlank(message = "O telefone é obrigatório!")
    @Column(length = 15, nullable = true, unique = true)
    private String telefone;

    @Email(message = "Insira um email válido!")
    @NotBlank(message = "O email é obrigatório!")
    @Column(length = 80 , nullable = false , unique = true)
    private String email;

    @NotBlank(message = "O endereço é obrigatório!")
    @Column(length = 150 , nullable = false)
    private Endereco endereco;

    @NotBlank(message = "Não pode ficar em branco!")
    @Column(length = 40 , nullable = false)
    private List<String> roles;
}
