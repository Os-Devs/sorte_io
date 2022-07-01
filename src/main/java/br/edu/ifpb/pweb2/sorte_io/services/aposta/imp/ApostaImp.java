package br.edu.ifpb.pweb2.sorte_io.services.aposta.imp;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

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

                aposta.setNumSelecionados(values);
                aposta.setApostador(apostador);
                aposta.setSorteio(sorteio);

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

            aposta.setApostador(apostador);
            aposta.setSorteio(sorteio);

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

    @Transactional
    private void attApostador(Apostador apostador, Set<String> values) {
        apostador.setGastos(apostador.getGastos().add(this.calcValores(values)));

        this.apostadoresRepository.save(apostador);
    }

    @Transactional
    private void attSorteio(Sorteio sorteio,Aposta aposta, Set<String> values) {
        sorteio.setValPremiacao(sorteio.getValPremiacao().add(this.calcValores(values)));
        sorteio.add(aposta);

        this.sorteiosRepository.save(sorteio);
    }

    private BigDecimal calcValores(Set<String> aposta) {
        // TODO: STRATEGY ???

        switch (aposta.size()) {
            case 7:
                return BigDecimal.valueOf(15);
            case 8:
                return BigDecimal.valueOf(90);
            case 9:
                return BigDecimal.valueOf(300);
            case 10:
                return BigDecimal.valueOf(1200);
            default:
                return BigDecimal.valueOf(3);
        }
    }
    
}
