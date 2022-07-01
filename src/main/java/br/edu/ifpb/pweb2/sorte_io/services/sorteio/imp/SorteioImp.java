package br.edu.ifpb.pweb2.sorte_io.services.sorteio.imp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sorte_io.model.Controlador;
import br.edu.ifpb.pweb2.sorte_io.model.Sorteio;
import br.edu.ifpb.pweb2.sorte_io.repository.ControladoresRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.SorteiosRepository;
import br.edu.ifpb.pweb2.sorte_io.services.sorteio.SorteioService;

@Service
public class SorteioImp implements SorteioService {

    @Autowired
    SorteiosRepository sorteiosRepository;

    @Autowired
    ControladoresRepository controladoresRepository;

    @Override
    @Transactional
    public boolean createSorteio(String value, Sorteio sorteio, String username) {
        if(this.validNumeros(value)) {
            Controlador criador = this.controladoresRepository.findByUser(username).get();
            Set<String> numSorteados = new HashSet<>(Arrays.asList(value.split(",")));

            sorteio.setNumSorteados(numSorteados);
            sorteio.setCriadoPor(criador);
            criador.add(sorteio);

            sorteiosRepository.save(sorteio);
            controladoresRepository.save(criador);

            return true;
        }
        else {
            return false;
        }
    }

    private boolean validNumeros(String value) {
        if(value != null) {
            Set<String> values = new HashSet<>(Arrays.asList(value.split(",")));

            if(values.size() == 6) {
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

    @Override
    public List<Sorteio> sorteiosAbertos() {
        return this.sorteiosRepository.findBySorteiosNaoRealizados().get();
    }

    @Override
    public List<Sorteio> sorteiosFechados() {
        return this.sorteiosRepository.findBySorteiosRealizados().get();
    }

    @Override
    public List<Sorteio> sorteiosForUser(String username) {
        return this.sorteiosRepository.findBySorteiosForUser(username).get();
    }
    
}
