package com.billing.setu.model;

public class PaymentUpdateResponse {
    String status;
    PaymentResponse data;

    public PaymentUpdateResponse(String status, PaymentResponse data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PaymentResponse getData() {
        return data;
    }

    public void setData(PaymentResponse data) {
        this.data = data;
    }
}
