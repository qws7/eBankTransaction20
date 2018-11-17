package org.cnam.sample.model;

import org.cnam.sample.domain.Transaction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "ebt_transaction")
public class TransactionModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( columnDefinition = "uuid")
    private UUID id;

    private UUID idEmetteur;

    private UUID idRecepteur;

    private BigDecimal amount;

    private String type;

    private UUID idType;

    public TransactionModel(){

    }

    public TransactionModel(UUID id, UUID idEmetteur, UUID idRecepteur, BigDecimal amount, String type, UUID idType) {
        this.id = id;
        this.idEmetteur = idEmetteur;
        this.idRecepteur = idRecepteur;
        this.amount = amount;
        this.type = type;
        this.idType = idType;
    }

    public TransactionModel(Transaction transaction) {
        this.idEmetteur = transaction.getIdDebtor();
        this.idRecepteur = transaction.getIdCredit();
        this.amount = transaction.getAmount();
        this.type = transaction.getType();
        this.idType = transaction.getIdType();
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public UUID getId() {
        return id;
    }

    public UUID getIdEmetteur() {
        return idEmetteur;
    }

    public UUID getIdRecepteur() {
        return idRecepteur;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public UUID getIdType() {
        return idType;
    }

    public void setIdEmetteur(UUID idEmetteur) {
        this.idEmetteur = idEmetteur;
    }

    public void setIdRecepteur(UUID idRecepteur) {
        this.idRecepteur = idRecepteur;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIdType(UUID idType) {
        this.idType = idType;
    }
}
