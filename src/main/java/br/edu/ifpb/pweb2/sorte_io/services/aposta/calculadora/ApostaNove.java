package br.edu.ifpb.pweb2.sorte_io.services.aposta.calculadora;

import java.math.BigDecimal;
import java.util.Set;

public class ApostaNove extends Valores {

    public ApostaNove(Valores proximo) {
        super(proximo);
    }

    @Override
    public BigDecimal efetuarCalc(Set<String> aposta) {
        return BigDecimal.valueOf(300);
    }

    @Override
    public Boolean testaValores(Set<String> aposta) {
        return aposta.size() == 9;
    }
}
