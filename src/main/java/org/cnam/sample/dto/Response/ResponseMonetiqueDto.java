package org.cnam.sample.dto.Response;

public class ResponseMonetiqueDto implements ResponseDto{
    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isBlocked;
    public String message;

    public ResponseMonetiqueDto(boolean isBlocked, String message) {
        this.isBlocked = isBlocked;
        this.message = message;
    }

    public ResponseMonetiqueDto() {
    }
}
