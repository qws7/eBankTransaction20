package org.cnam.sample.domain;

import org.cnam.sample.model.TransactionModel;

import java.math.BigDecimal;

public class Transaction {

    private Long id;
    private String idDebtor;
    private String idCredit;
    private BigDecimal amount;
    private String type;
    private String idType;

    public Transaction(TransactionModel  transactionModel) {
        this.id = transactionModel.getId();
        this.idDebtor = transactionModel.getIdEmetteur();
        this.idCredit = transactionModel.getIdRecepteur();
        this.amount = transactionModel.getAmount();
        this.type = transactionModel.getType();
        this.idType = transactionModel.getIdType();
    }

    public String getIdDebtor() {
        return idDebtor;
    }

    public void setIdDebtor(String idDebtor) {
        this.idDebtor = idDebtor;
    }

    public String getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(String idCredit) {
        this.idCredit = idCredit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public Long getId() {
        return id;
    }
}
