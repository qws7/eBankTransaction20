package org.cnam.sample.dto.Response;

import java.math.BigDecimal;
import java.util.UUID;

public class ResponseNewTransactionDto {
    private String message;
    private UUID idTransac;
    private UUID idEmetteur;
    private UUID idRecepteur;
    private BigDecimal amount;
    private String type;
    private  UUID idType;
}
