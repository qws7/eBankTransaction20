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
    private boolean status;

    @Value("${application.facture.url}")
    private String url_facture ;
    @Value("${application.facture.feature.create}")
    private String url_facture_create ;

    public FactureRequestStrategy(RequestDto requestDto) {
        this.newFactureDto = (NewFactureDto) requestDto;
        this.responseNewFactureDto = new ResponseNewFactureDto();
    }

    @Override
    public ResponseDto callRemote(List<String> logs) {
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
