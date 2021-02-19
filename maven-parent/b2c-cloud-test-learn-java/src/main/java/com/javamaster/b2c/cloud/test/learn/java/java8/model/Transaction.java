package com.javamaster.b2c.cloud.test.learn.java.java8.model;

/**
 * Created on 2018/12/19.<br/>
 *
 * @author yudong
 */
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;
    private final String currency;

    public Transaction(Trader trader, int year, int value, String currency) {
        this.trader = trader;
        this.year = year;
        this.value = value;
        this.currency = currency;
    }

    public PriceLevel priceLevel() {
        if (value <= 400) {
            return PriceLevel.CHEAP;
        } else if (value <= 700) {
            return
                    PriceLevel.EXPENCE;
        } else {
            return PriceLevel.NORMAL;
        }
    }

    public Trader getTrader() {
        return this.trader;
    }

    public int getYear() {
        return this.year;
    }

    public int getValue() {
        return this.value;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }
}
