package org.cnam.sample.service.Requests;

import org.cnam.sample.dto.Request.NewFactureDto;
import org.cnam.sample.dto.Request.RequestDto;
import org.cnam.sample.dto.Response.ResponseDto;
import org.cnam.sample.dto.Response.ResponseNewFactureDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

public class FactureRequestStrategy implements RequestStrategy{

    private ResponseNewFactureDto responseNewFactureDto ;
    private NewFactureDto newFactureDto;
    private String url_facture;
    private String url_facture_create;
    private boolean status = true;

    public FactureRequestStrategy(String url_facture, String url_facture_create) {
        this.responseNewFactureDto = new ResponseNewFactureDto();
        this.url_facture = url_facture;
        this.url_facture_create = url_facture_create;
    }

    @Override
    public ResponseDto callRemote(List<String> logs,RequestDto requestDto) {
        this.newFactureDto = (NewFactureDto) requestDto;
        final RestTemplate restTemplate = new RestTemplate();

        try {
            logs.add("try call :"+url_facture+url_facture_create);
            this.responseNewFactureDto = restTemplate.postForObject(url_facture+url_facture_create, newFactureDto, ResponseNewFactureDto.class);
        }catch(Exception e){
            this.status = false;
            logs.add("err :" + e.toString());
        }

        return this.responseNewFactureDto;
     }

    @Override
    public Boolean status(List<String> logs) {
        if(!this.status){
            return false;
        }

        logs.add("facture_label :" + "Facturation : ");
        logs.add("facture_val :" + responseNewFactureDto.getMessage());

        return responseNewFactureDto.isSuccess();
    }
}
