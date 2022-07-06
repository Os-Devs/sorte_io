package br.edu.ifpb.pweb2.sorte_io.model.strategy;


public class FactoryPagamento {
    StrategyPagamento strategy;

    public StrategyPagamento strategyPagamento(String tipoPagamento) {

        if(tipoPagamento == "Paypal") {
            strategy = new PagarViaPaypal();
        }
        else if(tipoPagamento == "Cartao") {
            strategy = new PagarViaCartao();
        }
        else {
            strategy = new PagarViaPix();
        }

        return strategy;
    }

}
