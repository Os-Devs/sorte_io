package br.edu.ifpb.pweb2.sorte_io.model.strategy;


public class TipoPagamento {
    StrategyPagamento strategy;

    public StrategyPagamento strategyPagamento(String tipoPagamento) {

        if(tipoPagamento.equals("paypal")) {
            strategy = new PagarViaPaypal();
        }
        else if(tipoPagamento.equals("cartao")) {
            strategy = new PagarViaCartao();
        }
        else {
            strategy = new PagarViaPix();
        }

        return strategy;
    }

}
