package org.cnam.sample.dto.Response;

import java.util.UUID;

public class ResponseGetClientIdFromAccountDto implements ResponseDto {

    private String idCustomer;

    public ResponseGetClientIdFromAccountDto(){
    }
    public ResponseGetClientIdFromAccountDto(String id){
        this.idCustomer = id;
    }
    
    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }
}
