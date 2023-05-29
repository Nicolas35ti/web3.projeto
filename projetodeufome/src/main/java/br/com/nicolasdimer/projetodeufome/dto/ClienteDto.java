package br.com.nicolasdimer.projetodeufome.dto;

import br.com.nicolasdimer.projetodeufome.service.ClienteService;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {


    private String nome;
    private String email;
    private String senha;
    public ClienteDto(String nome, String email, String senha) {
        super();
        this.nome = "developer";
        this.email = "devguy@email.com";
        this.senha = "dev123";
    }
}
