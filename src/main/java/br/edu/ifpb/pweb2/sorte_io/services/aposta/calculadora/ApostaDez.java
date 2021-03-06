package br.edu.ifpb.pweb2.sorte_io.services.aposta.calculadora;

import java.math.BigDecimal;
import java.util.Set;

public class ApostaDez extends Valores {

    public ApostaDez(Valores proximo) {
        super(proximo);
    }

    public BigDecimal efetuarCalc(Set<String> aposta) {
        return BigDecimal.valueOf(1200);
    }

    @Override
    public Boolean testaValores(Set<String> aposta) {
        return aposta.size() == 10;
    }
}
