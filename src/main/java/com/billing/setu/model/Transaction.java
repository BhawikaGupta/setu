package com.billing.setu.model;

import java.math.BigInteger;

public class Transaction {

    private String amountPaid;

    private String date;

    private String id;

    public Transaction(String amountPaid, String date, String id) {
        this.amountPaid = amountPaid;
        this.date = date;
        this.id = id;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
