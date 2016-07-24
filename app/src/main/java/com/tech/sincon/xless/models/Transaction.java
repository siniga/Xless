package com.tech.sincon.xless.models;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by getcore03 on 7/24/2016.
 */
public class Transaction {
    Double amount;
    String type;
    private List<String> debitParty;
    private List<String> creditParty;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public List<String> getCreditParty() {
        return creditParty;
    }

    public void setCreditParty(List<String> creditParty) {
        this.creditParty = creditParty;
    }

    public List<String> getDebitParty() {
        return debitParty;
    }

    public void setDebitParty(List<String> debitParty) {
        this.debitParty = debitParty;
    }

}
