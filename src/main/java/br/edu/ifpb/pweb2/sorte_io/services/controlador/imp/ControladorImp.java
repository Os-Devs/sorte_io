package br.edu.ifpb.pweb2.sorte_io.services.controlador.imp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sorte_io.model.Authority;
import br.edu.ifpb.pweb2.sorte_io.model.Controlador;
import br.edu.ifpb.pweb2.sorte_io.model.User;
import br.edu.ifpb.pweb2.sorte_io.repository.AuthorityRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.ControladoresRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.UserRepository;
import br.edu.ifpb.pweb2.sorte_io.services.command.CreateUser;
import br.edu.ifpb.pweb2.sorte_io.services.command.CreateUserHandler;
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
        this.createAuthority(controlador, username, senha);

        controlador.setUser(user);

        controladoresRepository.save(controlador);
    }

    private User createControlador(Controlador controlador, String username, String senha) {
        CreateUserHandler handler = new CreateUserHandler(controlador, username, senha);
        CreateUser create = new CreateUser();

        return userRepository.save(create.createUser(handler));
    }

    private Authority createAuthority(Controlador controlador, String username, String senha) {
        CreateUserHandler handler = new CreateUserHandler(controlador, username, senha);
        CreateUser create = new CreateUser();

        return authorityRepository.save(create.createAuthority(handler));
    }
    
}
