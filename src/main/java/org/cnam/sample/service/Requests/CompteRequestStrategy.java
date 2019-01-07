package org.cnam.sample.service.Requests;

import org.cnam.sample.dto.Response.ResponseDto;

import java.util.HashMap;
import java.util.Map;

public class CompteRequestStrategy implements RequestStrategy {
    public CompteRequestStrategy(ResponseDto responseDto) {
        this.responseDto = responseDto;
    }

    private ResponseDto responseDto;


    @Override
    public Boolean status(Map<String, String> logs) {
        return null;
    }
}
