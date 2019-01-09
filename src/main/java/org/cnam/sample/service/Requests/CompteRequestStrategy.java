package org.cnam.sample.service.Requests;

import org.cnam.sample.dto.Request.RequestClientDto;
import org.cnam.sample.dto.Request.RequestDto;
import org.cnam.sample.dto.Request.RequestWithdrawCompteDto;
import org.cnam.sample.dto.Response.ResponseDto;
import org.cnam.sample.dto.Response.ResponseWithdrawCompteDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

public class CompteRequestStrategy implements RequestStrategy {
    private ResponseWithdrawCompteDto responseWithdrawCompteDto;
    private RequestWithdrawCompteDto requestWithdrawCompteDto;
    private String url_compte;
    private String url_compte_withdraw;
    private boolean status = true;

    public CompteRequestStrategy(String url_compte, String url_compte_withdraw) {
        this.status=false;
        this.responseWithdrawCompteDto = new ResponseWithdrawCompteDto();
        this.url_compte = url_compte;
        this.url_compte_withdraw= url_compte_withdraw;
    }

    @Override
    public ResponseDto callRemote(List<String> logs,RequestDto requestDto) {
        this.requestWithdrawCompteDto = (RequestWithdrawCompteDto) requestDto;
        final RestTemplate restTemplate = new RestTemplate();

        try{
            logs.add("try call :"+url_compte+url_compte_withdraw);
            this.responseWithdrawCompteDto = restTemplate.postForObject(url_compte+url_compte_withdraw, requestWithdrawCompteDto, ResponseWithdrawCompteDto.class);
        }catch(Exception e){
            this.status = false;
            logs.add("err :"+e.toString());
        }

        return this.responseWithdrawCompteDto;
    }

    @Override
    public Boolean status(List<String> logs) {
        if(!this.status){
            return false;
        }

        logs.add("wd_label : Result of Request");
        logs.add("wd_res_1: " + responseWithdrawCompteDto.getMessage());

        return responseWithdrawCompteDto.getRequestSuceed();
    }
}
