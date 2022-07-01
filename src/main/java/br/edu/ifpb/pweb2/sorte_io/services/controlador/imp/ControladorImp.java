package br.edu.ifpb.pweb2.sorte_io.services.controlador.imp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sorte_io.model.Authority;
import br.edu.ifpb.pweb2.sorte_io.model.Controlador;
import br.edu.ifpb.pweb2.sorte_io.model.EnumRole;
import br.edu.ifpb.pweb2.sorte_io.model.User;
import br.edu.ifpb.pweb2.sorte_io.model.Authority.AuthorityId;
import br.edu.ifpb.pweb2.sorte_io.repository.AuthorityRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.ControladoresRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.UserRepository;
import br.edu.ifpb.pweb2.sorte_io.services.controlador.ControladorService;

@Service
public class ControladorImp implements ControladorService {

    @Autowired
    ControladoresRepository controladoresRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public Controlador getControladorForUser(String username) {
        return controladoresRepository.findByUser(username).get();
    }

    @Override
    @Transactional
    public void saveControlador(Controlador controlador, String username, String senha) {
        User user = this.createControlador(controlador, username, senha);
        this.createAuthority(user);

        controlador.setUser(user);

        controladoresRepository.save(controlador);
    }

    private User createControlador(Controlador controlador, String username, String senha) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User user = new User();

        user.setUsername(username);
        user.setPassword(encoder.encode(senha));
        user.setEnabled(true);

        return userRepository.save(user);
    }

    private Authority createAuthority(User user) {
        AuthorityId authorityId = new AuthorityId();
        Authority authority = new Authority();

        authorityId.setUsername(user.getUsername());
        authorityId.setAuthority(EnumRole.CONTROLADOR.getValue());

        authority.setId(authorityId);
        authority.setUsername(user);
        authority.setAuthority(EnumRole.CONTROLADOR.getValue());

        return authorityRepository.save(authority);
    }
    
}
