package org.cnam.sample.dto.Request;

import java.util.UUID;

public class GetTransactionDto {
    public UUID idCompte;

    public GetTransactionDto(UUID idCompte) {
        this.idCompte = idCompte;
    }
}
