package org.cnam.sample.dto.Request;

public class RequestClientDto implements RequestDto{
    private String id;

    public RequestClientDto(String id) {
        this.id = id;
    }

    public RequestClientDto() {
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }
}
