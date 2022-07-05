package br.edu.ifpb.pweb2.sorte_io.model.builder;

import java.util.Set;

import br.edu.ifpb.pweb2.sorte_io.model.Apostador;
import br.edu.ifpb.pweb2.sorte_io.model.Sorteio;

public interface BuilderAposta {
    BuilderAposta setId(Integer id);
    BuilderAposta setNumSelecionados(Set<String> numSelecionados);
    BuilderAposta setFavorito(Boolean favorito);
    BuilderAposta setApostador(Apostador apostador);
    BuilderAposta setSorteio(Sorteio sorteio);
    BuilderAposta setNumSorteio(Integer numSorteio);
}
