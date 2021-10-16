package com.application.alpha_task.client;

import com.application.alpha_task.query.response.ExchangeRateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "currencyRateClient", url = "${exchange.rate.url}")
public interface CurrencyRateClient {

    @GetMapping("/latest.json")
    ExchangeRateResponse getExchangeRates(
            @RequestParam String app_id,
            @RequestParam String base,
            @RequestParam String symbols

    );

    @GetMapping("/historical/{date}.json")
    ExchangeRateResponse getYesterdayExchangeRate(
            @PathVariable String date,
            @RequestParam String app_id,
            @RequestParam String base,
            @RequestParam String symbols
    );
}
