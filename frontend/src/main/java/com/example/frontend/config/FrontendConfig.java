package com.example.frontend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class FrontendConfig {
    @Value("${api.endpoint.rate}")
    private String rateEndPoint;
}
