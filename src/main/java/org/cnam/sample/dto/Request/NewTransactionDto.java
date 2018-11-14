package org.cnam.sample.dto.Request;

import org.cnam.sample.model.TransactionModel;

import java.math.BigDecimal;
import java.util.UUID;

public class NewTransactionDto {
    public UUID idEmetteur;
    public UUID idRecepteur;
    public BigDecimal amount;
    public String type;
    public UUID idType;

    public NewTransactionDto(TransactionModel transaction) {
        this.idEmetteur = transaction.getIdEmetteur();
        this.idRecepteur = transaction.getIdRecepteur();
        this.amount = transaction.getAmount();
        this.type = transaction.getType();
        this.idType = transaction.getIdType();
    }
}
