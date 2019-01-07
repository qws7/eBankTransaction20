package org.cnam.sample.service.Requests;

import org.cnam.sample.dto.Response.ResponseDto;

import java.util.HashMap;
import java.util.Map;

public interface RequestStrategy {
    Boolean status(Map<String,String> logs);
}
