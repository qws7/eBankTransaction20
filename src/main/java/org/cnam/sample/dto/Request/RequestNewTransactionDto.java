package org.cnam.sample.dto.Request;

import org.cnam.sample.domain.Transaction;
import org.cnam.sample.model.TransactionModel;

import java.math.BigDecimal;
import java.util.UUID;

public class RequestNewTransactionDto {
    private UUID idEmetteur;
    private UUID idRecepteur;
    private BigDecimal amount;
    private String type;

    public UUID getIdType() {
        return idType;
    }

    public void setIdType(UUID idType) {
        this.idType = idType;
    }

    private UUID idType;

    public  RequestNewTransactionDto(){

    }

    public UUID getIdEmetteur() {
        return idEmetteur;
    }

    public void setIdEmetteur(UUID idEmetteur) {
        this.idEmetteur = idEmetteur;
    }

    public UUID getIdRecepteur() {
        return idRecepteur;
    }

    public void setIdRecepteur(UUID idRecepteur) {
        this.idRecepteur = idRecepteur;
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
}
