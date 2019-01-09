package org.cnam.sample.dto.Response;

import java.util.UUID;

public class ResponseClientDto implements ResponseDto{

    private UUID id;

    private String lastName;

    private String firstName;

    private String mail;

    private String birthString;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBirthString() {
        return birthString;
    }

    public void setBirthString(String birthString) {
        this.birthString = birthString;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ResponseClientDto() {
    }

    public ResponseClientDto(UUID id, String lastName, String firstName, String mail, String birthString) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.mail = mail;
        this.birthString = birthString;
    }
}