package com.application.alpha_task.query.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
public class GifExchangeRateRequest {
    @NotBlank
    @Size(min = 3, max = 3)
    private String currencyCode;
}
