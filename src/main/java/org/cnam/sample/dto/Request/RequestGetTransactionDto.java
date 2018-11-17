package org.cnam.sample.dto.Request;

import java.util.UUID;

public class RequestGetTransactionDto {
    private UUID idCompte;

    public RequestGetTransactionDto(UUID idCompte) {

        this.idCompte = idCompte;
    }

    public UUID getIdCompte() {
        return idCompte;
    }
}
