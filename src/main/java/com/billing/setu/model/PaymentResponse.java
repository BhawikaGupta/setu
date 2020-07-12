package com.billing.setu.model;

public class PaymentResponse {
    String ackID;

    public PaymentResponse(String ackID) {
        this.ackID = ackID;
    }

    public String getAckID() {
        return ackID;
    }

    public void setAckID(String ackID) {
        this.ackID = ackID;
    }
}
