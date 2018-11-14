package org.cnam.sample.dto.Response;

import org.cnam.sample.domain.Transaction;

import java.util.List;
import java.util.UUID;

public class ResponseGetTransactionDto {
    public UUID idCompte;
    public List<Transaction> allTransac;

    public ResponseGetTransactionDto(UUID idCompte, List<Transaction> allTransac) {
        this.idCompte = idCompte;
        this.allTransac = allTransac;
    }
}
