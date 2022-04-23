package org.cnam.sample.service.Requests;

import org.cnam.sample.dto.Request.RequestDto;
import org.cnam.sample.dto.Request.RequestMailDto;
import org.cnam.sample.dto.Response.ResponseDto;
import org.cnam.sample.dto.Response.ResponseGetClientIdFromAccountDto;
import org.cnam.sample.dto.Request.RequestGetClientId;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class CompteGetCliIdFromAccountStrategy implements RequestStrategy {
    private ResponseGetClientIdFromAccountDto responseGetClientIdFromAccountDto;
    private RequestGetClientId requestGetClientId;
    private String url_compte;
    private String url_compte_getId;
    private boolean status = true;

    public CompteGetCliIdFromAccountStrategy(String url_compte, String url_compte_getId) {
        this.status = false;
        this.responseGetClientIdFromAccountDto = new ResponseGetClientIdFromAccountDto();
        this.url_compte = url_compte;
        this.url_compte_getId = url_compte_getId;
    }

    @Override
    public ResponseDto callRemote(List<String> logs, RequestDto requestDto) {
        this.requestGetClientId = (RequestGetClientId) requestDto;
        final RestTemplate restTemplate = new RestTemplate();

        try {
            logs.add("try call :" + url_compte + url_compte_getId);
            this.responseGetClientIdFromAccountDto =  restTemplate.postForObject(url_compte + url_compte_getId,requestGetClientId, ResponseGetClientIdFromAccountDto.class);

        } catch (Exception e) {
            this.status = false;
            logs.add("err :" + e.toString());
        }

        return this.responseGetClientIdFromAccountDto;
    }

    @Override
    public Boolean status(List<String> logs) {
        if (!this.status) {
            return false;
        }

        logs.add("wd_label : Result of Request");
        logs.add("wd_res_1: " + true);

        return true;
    }
}
