package org.cnam.sample.dto.Request;

public class RequestMonetiqueDto implements RequestDto{
    private String id;

    public RequestMonetiqueDto(String id) {
        this.id = id;
    }

    public RequestMonetiqueDto() {
    }

    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }
}
