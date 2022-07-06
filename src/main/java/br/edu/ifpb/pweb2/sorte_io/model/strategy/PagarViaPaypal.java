package br.edu.ifpb.pweb2.sorte_io.model.strategy;

import java.math.BigDecimal;

public class PagarViaPaypal implements StrategyPagamento {

    @Override
    public BigDecimal pagar(BigDecimal valor) {
        return valor.multiply(new BigDecimal(0.1));
    }

}
