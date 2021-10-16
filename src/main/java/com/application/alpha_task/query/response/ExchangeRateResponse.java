package com.application.alpha_task.query.response;

import lombok.Data;

import java.util.HashMap;

@Data
public class ExchangeRateResponse {
    private String disclaimer;
    private String license;
    private Integer timestamp;
    private String base;
    private HashMap<String, Double> rates;
}
