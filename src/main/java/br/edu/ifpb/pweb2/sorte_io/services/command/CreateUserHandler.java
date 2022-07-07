package br.edu.ifpb.pweb2.sorte_io.services.command;

import br.edu.ifpb.pweb2.sorte_io.model.Usuario;
import lombok.Getter;

@Getter
public class CreateUserHandler {
    private Usuario usuario;
    private String username;
    private String password;

    public CreateUserHandler(Usuario usuario, String username, String password) {
        this.usuario = usuario;
        this.username = username;
        this.password = password;
    }
}
