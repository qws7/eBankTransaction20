package org.cnam.sample.service.Requests;

import org.cnam.sample.dto.Response.ResponseDto;
import org.cnam.sample.dto.Response.ResponseNewFactureDto;

import java.util.HashMap;
import java.util.Map;

public class FactureRequestStrategy implements RequestStrategy{

    private ResponseNewFactureDto responseNewFactureDto ;

    public FactureRequestStrategy(ResponseDto responseDto) {
        this.responseNewFactureDto = (ResponseNewFactureDto) responseDto;
    }

    @Override
    public Boolean status(Map<String, String> logs) {
        logs.put("facture_label","Facturation : ");
        logs.put("facture_val",responseNewFactureDto.getMessage());

        return responseNewFactureDto.isSuccess();
    }
}
