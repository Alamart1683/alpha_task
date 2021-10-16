package com.application.alpha_task.service;

import com.application.alpha_task.client.CurrencyRateClient;
import com.application.alpha_task.query.response.ExchangeRateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class AlphaTaskService {
    @Value("${relative.which.currency}")
    // Изменять валюту относительно курса можно только
    private String relativeWhichCurrency;
    @Value("${app.id}")
    private String appId;
    private final CurrencyRateClient currencyRateClient;

    public void getExchangeRateGif(String currencyCode) {
        System.out.println(currencyCode);
        // Получим курс за сегодня
        ExchangeRateResponse today = currencyRateClient.getExchangeRates(
                appId,
                relativeWhichCurrency,
                currencyCode
        );
        // Получим курс за вчера
        ExchangeRateResponse yesterday = currencyRateClient.getYesterdayExchangeRate(
                getYesterdayDate(),
                appId,
                relativeWhichCurrency,
                currencyCode
        );

    }

    private String getYesterdayDate() {
        ZonedDateTime yesterday = ZonedDateTime.now().minusHours(24);
        return  yesterday.getYear() + "-" + yesterday.getMonthValue() + "-" + yesterday.getDayOfMonth();
    }
}
