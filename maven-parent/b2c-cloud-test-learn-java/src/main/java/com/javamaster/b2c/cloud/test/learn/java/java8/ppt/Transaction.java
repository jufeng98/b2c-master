package com.javamaster.b2c.cloud.test.learn.java.java8.ppt;

/**
 * Created on 2018/12/19.<br/>
 *
 * @author yudong
 */
public class Transaction {
    private Trader trader;
    private int year;
    private int month;
    private int value;
    private String currency;

    public Transaction() {
    }

    public Transaction(Trader trader, int year, int month, int value, String currency) {
        this.trader = trader;
        this.year = year;
        this.month = month;
        this.value = value;
        this.currency = currency;
    }

    public Trader getTrader() {
        return trader;
    }

    public void setTrader(Trader trader) {
        this.trader = trader;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", month=" + month +
                ", value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }
}
