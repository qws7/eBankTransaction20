package org.cnam.sample.dto.Response;

import java.math.BigDecimal;
import java.util.UUID;

public class ResponseNewTransactionDto {
    public String message;
    public UUID idTransac;
    public UUID idEmetteur;
    public UUID idRecepteur;
    public BigDecimal amount;
    public String type;
    public  UUID idType;

    public ResponseNewTransactionDto(String message, UUID idTransac, UUID idEmetteur, UUID idRecepteur, BigDecimal amount, String type, UUID idType) {
        this.message = message;
        this.idTransac = idTransac;
        this.idEmetteur = idEmetteur;
        this.idRecepteur = idRecepteur;
        this.amount = amount;
        this.type = type;
        this.idType = idType;
    }
}
