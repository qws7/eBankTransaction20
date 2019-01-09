package org.cnam.sample.service.Requests;

import org.cnam.sample.dto.Request.RequestDto;
import org.cnam.sample.dto.Request.RequestWithdrawCompteDto;
import org.cnam.sample.dto.Response.ResponseDto;
import org.cnam.sample.dto.Response.ResponseWithdrawCompteDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class CompteRequestStrategy implements RequestStrategy {
    private ResponseWithdrawCompteDto responseWithdrawCompteDto;
    private RequestWithdrawCompteDto requestWithdrawCompteDto;
    private boolean status;

    @Value("${application.compte.url}")
    private String url_compte ;
    @Value("${application.compte.feature.withdraw}")
    private String url_compte_withdraw ;

    public CompteRequestStrategy(RequestDto requestDto) {
        this.status=false;
        this.requestWithdrawCompteDto= (RequestWithdrawCompteDto)requestDto;
        this.responseWithdrawCompteDto = new ResponseWithdrawCompteDto();
    }

    @Override
    public ResponseDto callRemote(List<String> logs) {
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
