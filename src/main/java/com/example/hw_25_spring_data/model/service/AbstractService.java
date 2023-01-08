package com.example.hw_25_spring_data.model.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
abstract class AbstractService {

    protected final ObjectMapper objectMapper = new ObjectMapper();

    protected String getMessage(String msg) {
        log.info(msg);
        return msg;
    }
}

