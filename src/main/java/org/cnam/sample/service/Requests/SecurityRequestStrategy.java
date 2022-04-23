package org.cnam.sample.service.Requests;

import org.cnam.sample.dto.Request.RequestClientDto;
import org.cnam.sample.dto.Request.RequestDto;
import org.cnam.sample.dto.Request.RequestSecurityRightDto;
import org.cnam.sample.dto.Response.ResponseDto;
import org.cnam.sample.dto.Response.ResponseSecurityRightDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

public class SecurityRequestStrategy implements RequestStrategy {
    private ResponseSecurityRightDto responseSecurityRightDto;
    private RequestSecurityRightDto requestSecurityRightDto;
    private String url_securite;
    private String url_securite_check;
    private String url_securite_service;
    private boolean status = true;

    public SecurityRequestStrategy(String url_securite, String url_securite_check,String url_securite_service) {
        this.responseSecurityRightDto = new ResponseSecurityRightDto();
        this.url_securite = url_securite;
        this.url_securite_check = url_securite_check;
        this.url_securite_service = url_securite_service;
    }

    @Override
    public ResponseDto callRemote(List<String> logs,RequestDto requestDto) {
        this.requestSecurityRightDto = (RequestSecurityRightDto) requestDto;
        final RestTemplate restTemplate = new RestTemplate();

        try{
            logs.add("try call :"+this.url_securite+this.url_securite_check+this.requestSecurityRightDto.getlogin()+this.url_securite_service);
            this.responseSecurityRightDto = restTemplate.getForObject(url_securite+this.url_securite_check+this.requestSecurityRightDto.getlogin()+this.url_securite_service, ResponseSecurityRightDto.class);
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
