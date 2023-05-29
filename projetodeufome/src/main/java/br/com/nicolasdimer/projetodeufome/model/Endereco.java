package br.com.nicolasdimer.projetodeufome.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter

@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O número é obrigatório")
    @Size(min= 3 , max = 4 , message = "O número deve ter no mínimo 3 caracteres e no máximo 4!")
    @Column(length = 4 , nullable = false)
    private String numero;

    public void setNumero(String numero) {
        if(metodoAtual == 0){
        this.numero = numero;
        metodoAtual = 1;
        } else {
            throw new IllegalStateException("O número deve ser o primeiro a ser inserido!");
        }
    }

    @NotBlank(message = "O nome da rua não pode ficar em branco!")
    @Size(min = 5 , message = "O nome da rua deve ter no mínimo 5 caracteres!")
    @Column(length = 100 , nullable = false)
    private String rua;

    public void setRua(String rua) {
        if(metodoAtual == 1) {
        this.rua = rua;
        metodoAtual = 2;
        }else {
            throw new IllegalStateException("A rua deve ser inserida depois do número!");
        }
    }

    @NotBlank(message = "O nome do bairro não pode ficar em branco!")
    @Size(min = 5 , message = "O nome do bairro deve ter no mínimo 5 caracteres!")
    @Column(length = 100 , nullable = false)
    private String bairro;

    public void setBairro(String bairro) {
        if(metodoAtual == 2) {
        this.bairro = bairro;
        metodoAtual = 3;
        } else {
            throw new IllegalStateException("O bairro deve ser inserido depois da rua!");
        }
    }

    @NotBlank(message = "O nome da cidade não pode ficar em branco!")
    @Size(min = 5 , message = "O nomdeda cidade deve ter no mínimo 5 caracteres!")
    @Column(length = 100 , nullable = false)
    private String cidade;

    public void setCidade(String cidade) {
        if(metodoAtual == 3) {
        this.cidade = cidade;
        metodoAtual = 4;
        } else {
            throw new IllegalStateException("A cidade deve ser inserida depois do bairro!");
        }
    }

    @NotBlank(message = "O nome do estado não pode ficar em branco!")
    @Size(min = 5 , message = "O nome do estado deve ter no mínimo 5 caracteres!")
    @Column(length = 50 , nullable = false)
    private String estado;

    public void setEstado(String estado) {
        if(metodoAtual == 4) {
        this.estado = estado;
        metodoAtual = 5;
        } else {
            throw new IllegalStateException("O estado deve ser inserido por último, depois da cidade");
        }
    }

    private int metodoAtual = 0;
}
