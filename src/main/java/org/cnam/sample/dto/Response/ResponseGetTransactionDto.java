package org.cnam.sample.dto.Response;

import org.cnam.sample.domain.Transaction;

import java.util.List;
import java.util.UUID;

public class ResponseGetTransactionDto {
    private UUID idCompte;
    private List<Transaction> allTransac;
}
