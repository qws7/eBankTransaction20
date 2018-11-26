package org.cnam.sample.model;

import org.cnam.sample.domain.Transaction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "EBT_TRANSACTION")
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String idEmetteur;

    private String idRecepteur;

    private BigDecimal amount;

    private String type;

    private String idType;

    public TransactionModel(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdEmetteur() {
        return idEmetteur;
    }

    public String getIdRecepteur() {
        return idRecepteur;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdEmetteur(String idEmetteur) {
        this.idEmetteur = idEmetteur;
    }

    public void setIdRecepteur(String idRecepteur) {
        this.idRecepteur = idRecepteur;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }
}
