package com.billing.setu.model;

public class BillingResponseEntity {
    String status;
    BillResponse data;

    public BillingResponseEntity(String status, BillResponse data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BillResponse getData() {
        return data;
    }

    public void setData(BillResponse data) {
        this.data = data;
    }
}
