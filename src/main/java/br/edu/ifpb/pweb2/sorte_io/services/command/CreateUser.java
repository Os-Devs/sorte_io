package br.edu.ifpb.pweb2.sorte_io.services.apostador.imp.command;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.edu.ifpb.pweb2.sorte_io.model.Apostador;
import br.edu.ifpb.pweb2.sorte_io.model.Authority;
import br.edu.ifpb.pweb2.sorte_io.model.EnumRole;
import br.edu.ifpb.pweb2.sorte_io.model.User;
import br.edu.ifpb.pweb2.sorte_io.model.Authority.AuthorityId;

public class CreateUser {

    public User createUser(CreateUserHandler handler) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();

        user.setUsername(handler.getUsername())
                .setPassword(encoder.encode(handler.getPassword()))
                    .setEnabled(true);

        return user;
    }

    public Authority createAuthority(CreateUserHandler handler) {
        AuthorityId authorityId = new AuthorityId();
        Authority authority = new Authority();

        authorityId.setUsername(handler.getUsername())
                        .setAuthority(handler.getUsuario() instanceof Apostador ? EnumRole.APOSTADOR.getValue() : EnumRole.CONTROLADOR.getValue());

        authority.setId(authorityId)
                    .setUsername(this.createUser(handler))
                        .setAuthority(handler.getUsuario() instanceof Apostador ? EnumRole.APOSTADOR.getValue() : EnumRole.CONTROLADOR.getValue());

        return authority;
    }
}
