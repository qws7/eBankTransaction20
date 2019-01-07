package org.cnam.sample.service.Requests;

import org.cnam.sample.dto.Response.ResponseDto;
import org.cnam.sample.dto.Response.ResponseSecurityRightDto;

import java.util.HashMap;
import java.util.Map;

public class SecurityRequestStrategy implements RequestStrategy {
    private ResponseSecurityRightDto responseSecurityRightDto;

    public SecurityRequestStrategy(ResponseDto responseDto) {
        ResponseSecurityRightDto responseSecurityRightDto = (ResponseSecurityRightDto) responseDto;
    }

    @Override
    public Boolean status(Map<String, String> logs) {

        logs.put("secu_label","Res sécurité");
        logs.put("secu_val",responseSecurityRightDto.isAllowed() ? "true" : "false");

        return responseSecurityRightDto.isAllowed();
    }
}
