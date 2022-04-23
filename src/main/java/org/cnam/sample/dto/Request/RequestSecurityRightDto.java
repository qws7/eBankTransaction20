package org.cnam.sample.dto.Request;

public class RequestSecurityRightDto  implements RequestDto{

    private String login;

    private String serviceName;

    public RequestSecurityRightDto() {
    }

    public RequestSecurityRightDto(String login, String serviceName ) {
        this.login = login;
        this.serviceName = serviceName;
    }

    public String getlogin() {
        return login;
    }

    public void setlogin(String login) {
        this.login = login;
    }

    public String serviceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        serviceName = serviceName;
    }
}