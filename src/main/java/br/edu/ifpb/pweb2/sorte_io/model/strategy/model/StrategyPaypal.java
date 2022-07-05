package br.edu.ifpb.pweb2.sorte_io.model.strategy.model;

/**
 * Dummy credit card class.
 */
public class StrategyCartao {
    private int amount;
    private String number;
    private String date;
    private String cvv;

    StrategyCartao(String number, String date, String cvv) {
        this.amount = 100_000;
        this.number = number;
        this.date = date;
        this.cvv = cvv;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}