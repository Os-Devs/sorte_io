package br.edu.ifpb.pweb2.sorte_io.services.apostador.imp;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sorte_io.model.Apostador;
import br.edu.ifpb.pweb2.sorte_io.model.Authority;
import br.edu.ifpb.pweb2.sorte_io.model.EnumRole;
import br.edu.ifpb.pweb2.sorte_io.model.User;
import br.edu.ifpb.pweb2.sorte_io.model.Authority.AuthorityId;
import br.edu.ifpb.pweb2.sorte_io.model.strategy.FactoryPagamento;
import br.edu.ifpb.pweb2.sorte_io.model.strategy.StrategyPagamento;
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

        user.setUsername(username)
                .setPassword(encoder.encode(senha))
                    .setEnabled(true);

        return userRepository.save(user);
    }

    private Authority createAuthority(User user) {
        AuthorityId authorityId = new AuthorityId();
        Authority authority = new Authority();

        authorityId.setUsername(user.getUsername())
                        .setAuthority(EnumRole.APOSTADOR.getValue());

        authority.setId(authorityId)
                    .setUsername(user)
                        .setAuthority(EnumRole.APOSTADOR.getValue());

        return authorityRepository.save(authority);
    }

    @Override
    public Boolean attSaldo(String username, BigDecimal saldo, String radio) {
        if(saldo.signum() < 0) {
            return false;
        }

        Apostador apostador = apostadoresRepository.findByUser(username).get();
        FactoryPagamento factory = new FactoryPagamento();

        StrategyPagamento strategyPagamento = factory.strategyPagamento(radio);

        BigDecimal saldoBonificado = strategyPagamento.adicionar(saldo);

        apostador.setSaldo(
            apostador.getSaldo().add(saldoBonificado)
        );
        
        apostadoresRepository.save(apostador);
        
        return true;
    }
    
}
