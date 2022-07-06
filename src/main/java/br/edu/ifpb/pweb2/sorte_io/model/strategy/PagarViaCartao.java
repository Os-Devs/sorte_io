package br.edu.ifpb.pweb2.sorte_io.model.strategy;

import java.math.BigDecimal;

public class PagarViaCartao implements StrategyPagamento {

    @Override
    public BigDecimal pagar(BigDecimal valor) {
        return valor.multiply(new BigDecimal(0.05));
    }
}
