package org.cnam.sample.service.Requests;

import org.cnam.sample.dto.Request.RequestDto;
import org.cnam.sample.dto.Request.RequestSecurityRightDto;
import org.cnam.sample.dto.Response.ResponseDto;
import org.cnam.sample.dto.Response.ResponseSecurityRightDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

public class SecurityRequestStrategy implements RequestStrategy {
    private ResponseSecurityRightDto responseSecurityRightDto;
    private RequestSecurityRightDto requestSecurityRightDto;
    private boolean status;

    @Value("${application.securite.url}")
    private String url_securite;
    @Value("${application.securite.feature.service}")
    private String url_securite_service;
    @Value("${application.securite.feature.check}")
    private String url_securite_check ;

    public SecurityRequestStrategy(RequestDto requestDto) {
        this.requestSecurityRightDto = (RequestSecurityRightDto) requestDto;
        this.responseSecurityRightDto = new ResponseSecurityRightDto();
    }

    @Override
    public ResponseDto callRemote(List<String> logs) {
        final RestTemplate restTemplate = new RestTemplate();
        try{
            logs.add("try call :"+url_securite+url_securite_check+this.requestSecurityRightDto.getlogin()+url_securite_service);
            this.responseSecurityRightDto = restTemplate.getForObject(url_securite+url_securite_check+this.requestSecurityRightDto.getlogin()+url_securite_service, ResponseSecurityRightDto.class);
        }catch(Exception e){
            this.status = false;
            logs.add("err :" +e.toString());
        }

        return this.responseSecurityRightDto;
    }

    @Override
    public Boolean status(List<String> logs) {
        if(!this.status){
            return false;
        }

        logs.add("secu_label : Res sécurité");
        logs.add("secu_val : "+ (responseSecurityRightDto.isAllowed() ? "true" : "false" ));

        return responseSecurityRightDto.isAllowed();
    }
}
