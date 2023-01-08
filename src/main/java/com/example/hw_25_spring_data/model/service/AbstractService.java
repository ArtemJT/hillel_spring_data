package com.example.hw_25_spring_data.model.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
abstract class AbstractService {

    protected final ObjectMapper objectMapper = new ObjectMapper();
}
