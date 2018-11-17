package org.cnam.sample.domain;

import org.cnam.sample.model.TransactionModel;

import java.math.BigDecimal;
import java.util.UUID;

public class Transaction {

    private UUID id;
    private String message;
    private UUID idDebtor;
    private UUID idCredit;
    private BigDecimal amount;
    private String type;
    private UUID idType;

    public Transaction(TransactionModel  transactionModel) {
        this.idDebtor = transactionModel.getIdEmetteur();
        this.idCredit = transactionModel.getIdRecepteur();
        this.amount = transactionModel.getAmount();
        this.type = transactionModel.getType();
        this.idType = transactionModel.getIdType();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UUID getIdDebtor() {
        return idDebtor;
    }

    public void setIdDebtor(UUID idDebtor) {
        this.idDebtor = idDebtor;
    }

    public UUID getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(UUID idCredit) {
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

    public UUID getIdType() {
        return idType;
    }

    public void setIdType(UUID idType) {
        this.idType = idType;
    }

    public UUID getId() {
        return id;
    }
}
