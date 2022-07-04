package br.edu.ifpb.pweb2.sorte_io.services.apostador;

import br.edu.ifpb.pweb2.sorte_io.model.Apostador;

public interface ApostadorService {
    void saveApostador(Apostador apostador, String username, String senha);
    Apostador findByUser(String username);
}
