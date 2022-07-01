package br.edu.ifpb.pweb2.sorte_io.services.sorteio;

import java.util.List;

import br.edu.ifpb.pweb2.sorte_io.model.Sorteio;

public interface SorteioService {
    boolean createSorteio(String value, Sorteio sorteio, String username);
    List<Sorteio> sorteiosAbertos();
    List<Sorteio> sorteiosFechados();
    List<Sorteio> sorteiosForUser(String username);
}
