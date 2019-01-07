package org.cnam.sample.dto.Response;

public class ResponseSecurityRightDto  implements ResponseDto{

    private String name;

    private boolean isAllowed;

    public ResponseSecurityRightDto() {
    }

    public ResponseSecurityRightDto(String name, boolean isAllowed) {
        this.name = name;
        this.isAllowed = isAllowed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }
}