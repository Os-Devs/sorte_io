package br.edu.ifpb.pweb2.sorte_io.services.apostador.imp;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sorte_io.model.Apostador;
import br.edu.ifpb.pweb2.sorte_io.model.Authority;
import br.edu.ifpb.pweb2.sorte_io.model.EnumRole;
import br.edu.ifpb.pweb2.sorte_io.model.User;
import br.edu.ifpb.pweb2.sorte_io.model.Authority.AuthorityId;
import br.edu.ifpb.pweb2.sorte_io.repository.ApostadoresRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.AuthorityRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.UserRepository;
import br.edu.ifpb.pweb2.sorte_io.services.apostador.ApostadorService;

@Service
public class ApostadorImpl implements ApostadorService {

    @Autowired
    ApostadoresRepository apostadoresRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public Apostador findByUser(String username) {
        return apostadoresRepository.findByUser(username).get();
    }

    @Override
    @Transactional
    public void saveApostador(Apostador apostador, String username, String senha) {
        User user = this.createUser(apostador, username, senha);
        this.createAuthority(user);

        apostador.setUser(user);

        apostadoresRepository.save(apostador);
    }

    private User createUser(Apostador apostador, String username, String senha) {
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
        authorityId.setAuthority(EnumRole.APOSTADOR.getValue());

        authority.setId(authorityId);
        authority.setUsername(user);
        authority.setAuthority(EnumRole.APOSTADOR.getValue());

        return authorityRepository.save(authority);
    }
    
}
