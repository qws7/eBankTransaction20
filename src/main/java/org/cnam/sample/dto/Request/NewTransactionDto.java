package org.cnam.sample.dto.Request;

import java.math.BigDecimal;
import java.util.UUID;

public class NewTransactionDto {
    private UUID idEmetteur;
    private UUID idRecepteur;
    private BigDecimal amount;
    private String type;
    private UUID idType;
}
