package org.cnam.sample.dto.Request;

import java.math.BigDecimal;
import java.util.UUID;

public class NewTransactionDto {
    public UUID idEmetteur;
    public UUID idRecepteur;
    public BigDecimal amount;
    public String type;
    public UUID idType;

    public NewTransactionDto(UUID idEmetteur, UUID idRecepteur, BigDecimal amount, String type, UUID idType) {
        this.idEmetteur = idEmetteur;
        this.idRecepteur = idRecepteur;
        this.amount = amount;
        this.type = type;
        this.idType = idType;
    }
}
