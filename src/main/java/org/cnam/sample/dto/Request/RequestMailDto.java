package org.cnam.sample.dto.Request;
import org.cnam.sample.domain.Email;

import java.util.Map;

public class RequestMailDto implements RequestDto{
    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    // Voir ci-dessous
    private Email email;

    // Nom de votre service
    private String service;

    public RequestMailDto(Email email,String service){
        this.email = email;
        this.service = service;
    }

    public RequestMailDto() {
    }
}

