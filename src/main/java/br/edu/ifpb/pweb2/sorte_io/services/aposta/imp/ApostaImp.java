package br.edu.ifpb.pweb2.sorte_io.services.aposta.imp;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import br.edu.ifpb.pweb2.sorte_io.services.aposta.calculadora.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sorte_io.model.Aposta;
import br.edu.ifpb.pweb2.sorte_io.model.Apostador;
import br.edu.ifpb.pweb2.sorte_io.model.Sorteio;
import br.edu.ifpb.pweb2.sorte_io.repository.ApostadoresRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.ApostasRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.SorteiosRepository;
import br.edu.ifpb.pweb2.sorte_io.services.aposta.ApostaService;

@Service
public class ApostaImp implements ApostaService {

    @Autowired
    ApostadoresRepository apostadoresRepository;

    @Autowired
    SorteiosRepository sorteiosRepository;

    @Autowired
    ApostasRepository apostasRepository;

    @Override
    @Transactional
    public boolean createAposta(String value, Aposta aposta, String username) {
        if(aposta.getNumSelecionados().isEmpty() || aposta.getNumSelecionados() == null) {
            if(this.validNumeros(value)) {
                Set<String> values = new HashSet<>(Arrays.asList(value.split(",")));
                Apostador apostador = this.apostadoresRepository.findByUser(username).get();
                Sorteio sorteio = this.sorteiosRepository.getById(aposta.getNumSorteio());

                if(apostador.getSaldo().subtract(this.calcValores(values)).signum() < 0) {
                    return false;
                }

                aposta.setNumSelecionados(values)
                      .setApostador(apostador)
                      .setSorteio(sorteio);

                this.apostasRepository.save(aposta);

                this.attApostador(apostador, values);
                this.attSorteio(sorteio, aposta, values);

                return true;
            }
            else {
                return false;
            }
        }
        else {
            Set<String> values = new HashSet<>(aposta.getNumSelecionados());
            Apostador apostador = this.apostadoresRepository.findByUser(username).get();
            Sorteio sorteio = this.sorteiosRepository.getById(aposta.getNumSorteio());

            if(apostador.getSaldo().subtract(this.calcValores(values)).signum() < 0) {
                return false;
            }

            aposta.setApostador(apostador)
                    .setSorteio(sorteio);

            this.apostasRepository.save(aposta);

            this.attApostador(apostador, values);
            this.attSorteio(sorteio, aposta, values);

            return true;
        }
    }

    private boolean validNumeros(String value) {
        if(value != null) {
            Set<String> values = new HashSet<>(Arrays.asList(value.split(",")));

            if(values.size() >= 6  && values.size() <= 10) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    private void attApostador(Apostador apostador, Set<String> values) {
        apostador.setSaldo(
            apostador.getSaldo().subtract(
                this.calcValores(values)
            )
        );

        this.apostadoresRepository.save(apostador);
    }

    private void attSorteio(Sorteio sorteio,Aposta aposta, Set<String> values) {
        sorteio.setValPremiacao(
            sorteio.getValPremiacao().add(
                this.calcValores(values)
            )
        );
        
        sorteio.add(aposta);

        this.sorteiosRepository.save(sorteio);
    }

    private BigDecimal calcValores(Set<String> aposta) {
        // CHAIN + TEMPLATE

        Valores valor = new ApostaDez(
            new ApostaNove(
                new ApostaOito(
                    new ApostaSete(
                        new ApostaSeis()
                    )
                )
            )
        );

        return valor.calValores(aposta);
    }

    @Override
    public List<Aposta> findByUser(String username) {
        return apostasRepository.findByForUser(username).get();
    }

    @Override
    public List<Aposta> findFavoritos(String username) {
        return apostasRepository.findByFavoritoTrueForUser(username).get();
    }
    
}
