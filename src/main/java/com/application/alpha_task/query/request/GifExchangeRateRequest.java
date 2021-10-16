package com.application.alpha_task.query.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class GifExchangeRateRequest {
    @NotBlank
    @Size(min = 3, max = 3)
    private String currencyCode;
}
