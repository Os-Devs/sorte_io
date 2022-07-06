package br.edu.ifpb.pweb2.sorte_io.model.builder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import br.edu.ifpb.pweb2.sorte_io.model.Aposta;
import br.edu.ifpb.pweb2.sorte_io.model.Controlador;

public interface BuilderSorteio {
    BuilderSorteio setId(Integer id);
    BuilderSorteio setDtRealizacao(Date dtRealizacao);
    BuilderSorteio setNumSorteados(Set<String> numSorteados);
    BuilderSorteio setValPremiacao(BigDecimal valPremiacao);
    BuilderSorteio setCriadoPor(Controlador criadoPor);
    BuilderSorteio setApostas(List<Aposta> apostas);
    BuilderSorteio setRealizado(Boolean realizado);
}
