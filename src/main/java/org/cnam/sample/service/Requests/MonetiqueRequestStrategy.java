package org.cnam.sample.service.Requests;

import org.cnam.sample.dto.Request.RequestDto;
import org.cnam.sample.dto.Request.RequestMonetiqueDto;
import org.cnam.sample.dto.Response.ResponseDto;
import org.cnam.sample.dto.Response.ResponseMonetiqueDto;
import org.cnam.sample.service.Requests.RequestStrategy;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class MonetiqueRequestStrategy implements RequestStrategy {
    private ResponseMonetiqueDto responseMonetiqueDto;
    private RequestMonetiqueDto requestMonetiqueDto;
    private String url_monetique;
    private String url_monetique_check;
    private String url_monetique_service;
    private boolean status = true;

    public MonetiqueRequestStrategy(String url_monetique, String url_monetique_check,String url_monetique_service) {
        this.responseMonetiqueDto = new ResponseMonetiqueDto();
        this.url_monetique = url_monetique;
        this.url_monetique_check = url_monetique_check;
        this.url_monetique_service = url_monetique_service;
    }

    @Override
    public ResponseDto callRemote(List<String> logs, RequestDto requestDto) {
        this.requestMonetiqueDto = (RequestMonetiqueDto) requestDto;
        final RestTemplate restTemplate = new RestTemplate();

        try{
            logs.add("try call :"+this.url_monetique+this.url_monetique_check+this.requestMonetiqueDto.getid()+this.url_monetique_service);
            this.responseMonetiqueDto = restTemplate.getForObject(url_monetique+this.url_monetique_check+this.requestMonetiqueDto.getid()+this.url_monetique_service, ResponseMonetiqueDto.class);
        }catch(Exception e){
            this.status = false;
            logs.add("err :" +e.toString());
        }

        return this.responseMonetiqueDto;
    }

    @Override
    public Boolean status(List<String> logs) {
        if(!this.status){
            return false;
        }

        logs.add("secu_label : Res monetique");
        logs.add("is card blocked : "+ (responseMonetiqueDto.isBlocked() ? "true" : "false" ));

        return !responseMonetiqueDto.isBlocked();
    }
}

