package com.application.alpha_task.query.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlGifResponse {
    private String currencyCode;
    private String relativeCurrencyCode;
    private double yesterdayRate;
    private double todayRate;
    private double difference;
    private String status;
    private String name;
    private String url;
}
