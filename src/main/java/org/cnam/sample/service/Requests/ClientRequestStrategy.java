package org.cnam.sample.service.Requests;

import org.cnam.sample.dto.Request.RequestClientDto;
import org.cnam.sample.dto.Request.RequestDto;
import org.cnam.sample.dto.Request.RequestSecurityRightDto;
import org.cnam.sample.dto.Response.ResponseClientDto;
import org.cnam.sample.dto.Response.ResponseDto;
import org.cnam.sample.dto.Response.ResponseSecurityRightDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

public class ClientRequestStrategy implements RequestStrategy {
    private ResponseClientDto responseClientDto;
    private  RequestClientDto  requestClientDto;
    private String url_client;
    private String url_client_getLogin;
    private boolean status = true;

    public ClientRequestStrategy(String url_client,String url_client_getLogin) {
        this.url_client_getLogin = url_client_getLogin;
        this.url_client = url_client;
        this.responseClientDto = new ResponseClientDto();
    }

    @Override
    public ResponseDto callRemote(List<String> logs, RequestDto requestDto) {
        this.requestClientDto = (RequestClientDto) requestDto;
        final RestTemplate restTemplate = new RestTemplate();
        try{
            logs.add("try call :"+url_client+url_client_getLogin+this.requestClientDto.getid());
            this.responseClientDto = restTemplate.getForObject(url_client+url_client_getLogin+this.requestClientDto.getid(), ResponseClientDto.class);
        }catch(Exception e){
            this.status = false;
            logs.add("err : " + e.toString());
        }

        this.status = true;
        return this.responseClientDto;
    }

    @Override
    public Boolean status(List<String> logs) {
        return status;
    }
}
