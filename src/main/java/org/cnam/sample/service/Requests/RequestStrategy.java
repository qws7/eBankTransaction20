package org.cnam.sample.service.Requests;

import org.cnam.sample.dto.Request.RequestDto;
import org.cnam.sample.dto.Response.ResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public interface RequestStrategy {
    ResponseDto callRemote(List<String>logs,RequestDto requestDto);
    Boolean status(List<String> logs);
}
