package com.billing.setu.model;

public class PaymentUpdateRequest {
    String refID;
    Transaction transaction;

    public PaymentUpdateRequest(String refID, Transaction transaction) {
        this.refID = refID;
        this.transaction = transaction;
    }

    public String getRefID() {
        return refID;
    }

    public void setRefID(String refID) {
        this.refID = refID;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
