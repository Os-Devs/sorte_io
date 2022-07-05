package br.edu.ifpb.pweb2.sorte_io.model.builder;

import java.util.Date;

import br.edu.ifpb.pweb2.sorte_io.model.User;

public interface BuilderUsuario {
    BuilderUsuario setId(Integer id);
    BuilderUsuario setNome(String nome);
    BuilderUsuario setCpf(String cpf);
    BuilderUsuario setDtNascimento(Date dtNascimento);
    BuilderUsuario setUser(User user);
}
