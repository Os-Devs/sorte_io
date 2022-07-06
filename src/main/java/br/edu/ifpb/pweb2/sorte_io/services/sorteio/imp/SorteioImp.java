package br.edu.ifpb.pweb2.sorte_io.services.sorteio.imp;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.pweb2.sorte_io.model.Controlador;
import br.edu.ifpb.pweb2.sorte_io.model.Sorteio;
import br.edu.ifpb.pweb2.sorte_io.repository.ApostadoresRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.ControladoresRepository;
import br.edu.ifpb.pweb2.sorte_io.repository.SorteiosRepository;
import br.edu.ifpb.pweb2.sorte_io.services.sorteio.SorteioService;

@Service
public class SorteioImp implements SorteioService {

    @Autowired
    SorteiosRepository sorteiosRepository;

    @Autowired
    ApostadoresRepository apostadoresRepository;

    @Autowired
    ControladoresRepository controladoresRepository;

    @Override
    @Transactional
    public boolean createSorteio(Sorteio sorteio, String username) {
        if(validSorteio(sorteio, username)) {
            Controlador criador = this.controladoresRepository.findByUser(username).get();

            sorteio.setCriadoPor(criador)
                    .setRealizado(false);
                   
            criador.add(sorteio);

            sorteiosRepository.save(sorteio);
            controladoresRepository.save(criador);

            return true;
        }
        else {
            return false;
        }

    }

    private boolean validSorteio(Sorteio sorteio, String username) {
        List<Sorteio> sorteiosAbertos = this.sorteiosRepository.findBySorteioAbertoForUser(username).get();

        if(sorteiosAbertos.isEmpty()) {
            boolean teste = true;

            List<Sorteio> sorteios = this.sorteiosForUser(username);

            for (Sorteio sort : sorteios) {
                Duration duracao = Duration.between(sort.getDtRealizacao().toInstant(), Instant.now());

                if(duracao.toDays() < 7) {
                    teste = false;
                    break;
                }
            }

            return teste;
        }

        return false;
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
        return this.sorteiosRepository.findBySorteioAbertoForUser(username).get();
    }

    @Override
    public boolean realizarSorteio(Integer id, String value) {
        Sorteio sorteio = this.sorteiosRepository.getById(id);

        if(this.validNumeros(value)) {
            Set<String> values = new HashSet<>(Arrays.asList(value.split(",")));

            if(values.size() == 6) {
                sorteio.setRealizado(true)
                        .setNumSorteados(values);

                sorteio.vencedores();
                this.sorteiosRepository.save(sorteio);

                this.apostadoresRepository.saveAll(sorteio.distribuirPremiacao());
            }
            else {
                sorteio.setRealizado(true);
                sorteio.sortear();

                sorteio.vencedores();
                this.sorteiosRepository.save(sorteio);

                this.apostadoresRepository.saveAll(sorteio.distribuirPremiacao());
            }
        }
        else {
            sorteio.setRealizado(true);
            sorteio.sortear();

            sorteio.vencedores();
            this.sorteiosRepository.save(sorteio);

            this.apostadoresRepository.saveAll(sorteio.distribuirPremiacao());
        }

        return true;
    }

    @Override
    public Sorteio getSorteioById(Integer id) {
        return this.sorteiosRepository.getById(id);
    }
    
}
