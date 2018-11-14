package org.cnam.sample.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class Transaction {

    private String message;
    private UUID idDebtor;
    private UUID idCredit;
    private BigDecimal amount;
    private String type;
    private UUID idType;

    public Transaction() {
    }

    public Transaction(UUID idDebtor, UUID idCredit, BigDecimal amount, String type, UUID idType) {
        this.idDebtor = idDebtor;
        this.idCredit = idCredit;
        this.amount = amount;
        this.type = type;
        this.idType = idType;
    }

    public Transaction(String message, UUID idDebtor, UUID idCredit, BigDecimal amount, String type, UUID idType) {
        this.message = message;
        this.idDebtor = idDebtor;
        this.idCredit = idCredit;
        this.amount = amount;
        this.type = type;
        this.idType = idType;
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
}
