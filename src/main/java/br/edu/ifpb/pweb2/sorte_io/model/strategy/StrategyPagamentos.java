package br.edu.ifpb.pweb2.sorte_io.model.strategy;

import java.math.BigDecimal;

/**
 * Common interface for all strategies.
 */

public interface  StrategyPagamentos {
    boolean pagar(BigDecimal pagamento);
    void detalhes();
}
