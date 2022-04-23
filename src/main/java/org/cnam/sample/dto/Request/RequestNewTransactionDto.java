package org.cnam.sample.dto.Request;

import org.cnam.sample.domain.Transaction;
import org.cnam.sample.model.TransactionModel;

import java.math.BigDecimal;
import java.util.UUID;

public class RequestNewTransactionDto  implements RequestDto{
    private UUID idEmetteur;
    private UUID idRecepteur;
    private BigDecimal amount;
    private String type;
    private UUID idType;

    public String getIdType() {
        return idType.toString();
    }

    public void setIdType(UUID idType) {
        this.idType = idType;
    }


    public  RequestNewTransactionDto(){

    }

    public String getIdEmetteur() {
        return idEmetteur.toString();
    }

    public void setIdEmetteur(UUID idEmetteur) {
        this.idEmetteur = idEmetteur;
    }

    public String getIdRecepteur() {
        return  idRecepteur.toString();
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
