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
import br.edu.ifpb.pweb2.sorte_io.model.strategy.TipoPagamento;
import br.edu.ifpb.pweb2.sorte_io.model.strategy.StrategyPagamento;
import br.edu.ifpb.pweb2.sorte_io.repository.ApostadoresRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.AuthorityRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.UserRepository;
import br.edu.ifpb.pweb2.sorte_io.services.apostador.ApostadorService;
import br.edu.ifpb.pweb2.sorte_io.services.apostador.imp.command.CreateUser;
import br.edu.ifpb.pweb2.sorte_io.services.apostador.imp.command.CreateUserHandler;

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
        this.createAuthority(apostador, username, senha);

        apostador.setUser(user);

        apostadoresRepository.save(apostador);
    }

    private User createUser(Apostador apostador, String username, String senha) {
        CreateUserHandler handler = new CreateUserHandler(apostador, username, senha);
        CreateUser create = new CreateUser();

        return userRepository.save(create.createUser(handler));
    }

    private Authority createAuthority(Apostador apostador, String username, String senha) {
        CreateUserHandler handler = new CreateUserHandler(apostador, username, senha);
        CreateUser create = new CreateUser();

        return authorityRepository.save(create.createAuthority(handler));
    }

    @Override
    public Boolean attSaldo(String username, BigDecimal saldo, String radio) {
        if(saldo.signum() < 0) {
            return false;
        }

        Apostador apostador = apostadoresRepository.findByUser(username).get();
        TipoPagamento tipoPagamento = new TipoPagamento();

        StrategyPagamento strategyPagamento = tipoPagamento.strategyPagamento(radio);

        BigDecimal saldoBonificado = strategyPagamento.adicionar(saldo);

        apostador.setSaldo(
            apostador.getSaldo().add(saldoBonificado)
        );
        
        apostadoresRepository.save(apostador);
        
        return true;
    }
    
}
