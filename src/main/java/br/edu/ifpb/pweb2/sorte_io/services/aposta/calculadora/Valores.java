package br.edu.ifpb.pweb2.sorte_io.services.aposta.calculadora;

import java.math.BigDecimal;
import java.util.Set;

public abstract class Valores {

    protected Valores proximo;

    public Valores(Valores proximo){
        this.proximo = proximo;
    }

    public BigDecimal calValores(Set<String> aposta){
        if(testaValores(aposta)){
            return efetuarCalc(aposta);
        }
        return proximo.calValores(aposta);
    }

    protected abstract BigDecimal efetuarCalc(Set<String> aposta);
    protected abstract Boolean testaValores(Set<String> aposta);
}
