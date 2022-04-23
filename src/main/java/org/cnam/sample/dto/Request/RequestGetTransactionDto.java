package org.cnam.sample.dto.Request;

import java.util.UUID;

public class RequestGetTransactionDto implements RequestDto {
    private String idCompte;

    public RequestGetTransactionDto(UUID idCompte) {

        this.idCompte = idCompte.toString();
    }

    public UUID getIdCompte() {
        return  UUID.fromString(idCompte);
    }
}
