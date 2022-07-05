package br.edu.ifpb.pweb2.sorte_io.services.aposta.calculadora;

import java.math.BigDecimal;
import java.util.Set;

public class ApostaSete extends Valores {

    public ApostaSete(Valores proximo) {
        super(proximo);
    }

    public BigDecimal efetuarCalc(Set<String> aposta) {
        return BigDecimal.valueOf(15);
    }

    @Override
    public Boolean testaValores(Set<String> aposta) {
        return aposta.size() == 7;
    }
}



