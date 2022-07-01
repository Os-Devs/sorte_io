package br.edu.ifpb.pweb2.sorte_io.services.aposta;

import java.util.List;

import br.edu.ifpb.pweb2.sorte_io.model.Aposta;

public interface ApostaService {
    boolean createAposta(String value, Aposta aposta, String username);
    List<Aposta> findByUser(String username);
    List<Aposta> findFavoritos(String username);
}
