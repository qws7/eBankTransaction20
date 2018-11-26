package org.cnam.sample.dto.Request;

import java.util.UUID;

public class RequestGetTransactionDto {
    private String idCompte;

    public RequestGetTransactionDto(UUID idCompte) {

        this.idCompte = idCompte.toString();
    }

    public UUID getIdCompte() {
        return  UUID.fromString(idCompte);
    }
}
