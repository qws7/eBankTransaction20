package org.cnam.sample.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "ebt_transaction")
public class TransactionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private UUID id;

    @Column(name="ideme")
    private UUID idEmetteur;

    @Column(name="idrec")
    private UUID idRecepteur;

    @Column(name="amount")
    private BigDecimal amount;

    @Column(name="type")
    private String type;

    @Column(name="idtype")
    private UUID idType;

    public TransactionModel(){

    }
    public TransactionModel(UUID idEmetteur, UUID idRecepteur, BigDecimal amount, String type, UUID idType) {
        this.idEmetteur = idEmetteur;
        this.idRecepteur = idRecepteur;
        this.amount = amount;
        this.type = type;
        this.idType = idType;
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
}
