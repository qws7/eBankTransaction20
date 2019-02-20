package org.cnam.sample.service.Requests;

import org.cnam.sample.domain.Email;
import org.cnam.sample.dto.Request.RequestDto;
import org.cnam.sample.dto.Request.RequestMailDto;
import org.cnam.sample.dto.Request.RequestMonetiqueDto;
import org.cnam.sample.dto.Response.ResponseDto;
import org.cnam.sample.dto.Response.ResponseMailDto;
import org.cnam.sample.dto.Response.ResponseMonetiqueDto;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class EmailRequestStrategy implements RequestStrategy{
    private String url_mail ;
    private String url_mail_send;
    private RequestMailDto requestMailDto;
    private ResponseMailDto responseMailDto;
    private boolean status = true;

    public EmailRequestStrategy(String url_mail ,String url_mail_send) {
        this.url_mail = url_mail;
        this.url_mail_send = url_mail_send;
    }

    @Override
    public ResponseDto callRemote(List<String> logs, RequestDto requestMailDto) {
        this.requestMailDto = (RequestMailDto) requestMailDto;
        final RestTemplate restTemplate = new RestTemplate();

        try{
            this.responseMailDto = restTemplate.postForObject( url_mail + url_mail_send ,requestMailDto, ResponseMailDto.class);
        }catch(Exception e){
            this.status = false;
            logs.add("err :" +e.toString());
        }

        return this.responseMailDto;
    }

    @Override
    public Boolean status(List<String> logs) {
        logs.add(this.status ? "Success Mail" : "Fail Mail");
        return this.status;
    }
}
