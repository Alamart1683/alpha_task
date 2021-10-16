package com.application.alpha_task.query.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;

@Data
@AllArgsConstructor
public class ExchangeRateResponse {
    private String disclaimer;
    private String license;
    private Integer timestamp;
    private String base;
    private HashMap<String, Double> rates;
}
