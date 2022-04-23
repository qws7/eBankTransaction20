package org.cnam.sample.dto.Response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseNewFactureDto  implements ResponseDto{
    private boolean success;

    @JsonCreator
    public ResponseNewFactureDto(@JsonProperty("success") boolean success,
                                 @JsonProperty("message") String message) {
        this.success = success;
        this.message = message;
    }

    public ResponseNewFactureDto() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;



    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
