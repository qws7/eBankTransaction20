package org.cnam.sample.service.Requests;

import org.cnam.sample.dto.Request.RequestDto;
import org.cnam.sample.dto.Response.ResponseDto;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public interface RequestStrategy {
    ResponseDto callRemote(List<String>logs);
    Boolean status(List<String> logs);
}
