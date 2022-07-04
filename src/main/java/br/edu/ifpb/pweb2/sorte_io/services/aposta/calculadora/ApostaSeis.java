package br.edu.ifpb.pweb2.sorte_io.services.aposta.calculadora;

import java.math.BigDecimal;
import java.util.Set;

public class ApostaSeis extends Valores {


    public ApostaSeis() {
        super(null);
    }

    @Override
    public BigDecimal efetuarCalc(Set<String> aposta) {
        return BigDecimal.valueOf(3);
    }

    @Override
    public Boolean testaValores(Set<String> aposta) {
        return aposta.size() == 6;
    }
}