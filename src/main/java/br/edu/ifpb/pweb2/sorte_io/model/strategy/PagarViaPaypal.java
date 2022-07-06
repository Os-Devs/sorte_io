package br.edu.ifpb.pweb2.sorte_io.model.strategy;

import java.math.BigDecimal;

public class PagarViaPaypal implements StrategyPagamento {

    @Override
    public BigDecimal adicionar(BigDecimal valor) {
        return valor.add(valor.multiply(new BigDecimal(0.1)));
    }

}
