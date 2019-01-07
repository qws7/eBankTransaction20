package org.cnam.sample.dto.Response;

public class ResponseWithdrawCompteDto  implements ResponseDto{

    private Boolean requestSuccess;
    private String message;

    public ResponseWithdrawCompteDto(){}
    public ResponseWithdrawCompteDto(boolean success, String message)
    {
        this.requestSuccess = success;
        this.message = message;
    }

    public void setRequestSucceed(boolean requestSucceed)
    {
        this.requestSuccess = requestSucceed;
    }

    public boolean getRequestSuceed()
    {
        return requestSuccess;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
