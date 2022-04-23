package org.cnam.sample.dto.Request;

import org.cnam.sample.dto.Response.ResponseDto;

import java.util.UUID;

public class RequestGetClientId implements RequestDto {

    private String idAccount;

    public RequestGetClientId(){
    }
    public RequestGetClientId(String _idAccount){
        idAccount = _idAccount;
    }

    public UUID getIdAccount() {
        return UUID.fromString(idAccount);
    }

    public void setIdAccount(UUID idAccount) {
        this.idAccount = idAccount.toString();
    }
}
