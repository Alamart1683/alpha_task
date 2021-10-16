package com.application.alpha_task.service;

import com.application.alpha_task.client.CurrencyRateClient;
import com.application.alpha_task.client.GifClient;
import com.application.alpha_task.exception.EqualExchangeRateException;
import com.application.alpha_task.query.response.ExchangeRateResponse;
import com.application.alpha_task.query.response.GiphyResponse;
import com.application.alpha_task.query.response.UrlGifResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class AlphaTaskService {
    @Value("${relative.which.currency}")
    private String relativeWhichCurrency;
    @Value("${exchange.app.id}")
    private String exchangeAppId;
    @Value("${gif.app.id}")
    private String gifAppId;

    private final CurrencyRateClient currencyRateClient;
    private final GifClient gifClient;

    public UrlGifResponse getExchangeRateGif(String currencyCode) {
        GiphyResponse giphyResponse;
        String status;

        // Получим курс за сегодня
        ExchangeRateResponse today = currencyRateClient.getExchangeRates(
                exchangeAppId,
                relativeWhichCurrency,
                currencyCode
        );
        // Получим курс за вчера
        ExchangeRateResponse yesterday = currencyRateClient.getYesterdayExchangeRate(
                getYesterdayDate(),
                exchangeAppId,
                relativeWhichCurrency,
                currencyCode
        );
        if (today.getRates().size() == 0 || today.getRates().size() == 0) {
            throw new NullPointerException("Не были получены данные о разнице курсов для кода валюты: " + currencyCode);
        }
        double difference = today.getRates().get(currencyCode) - yesterday.getRates().get(currencyCode);
        if (difference > 0) {
            status = "rich";
            giphyResponse = gifClient.getRandomGif(gifAppId, status, "r");
            return new UrlGifResponse(
                    currencyCode,
                    relativeWhichCurrency,
                    today.getRates().get(currencyCode),
                    yesterday.getRates().get(currencyCode),
                    difference,
                    status,
                    giphyResponse.getData().getTitle(),
                    giphyResponse.getData().getImage_original_url()
            );
        }
        if (difference < 0) {
            status = "broke";
            giphyResponse = gifClient.getRandomGif(gifAppId, status, "r");
            return new UrlGifResponse(
                    currencyCode,
                    relativeWhichCurrency,
                    today.getRates().get(currencyCode),
                    yesterday.getRates().get(currencyCode),
                    difference,
                    status,
                    giphyResponse.getData().getTitle(),
                    giphyResponse.getData().getImage_original_url()
            );
        }
        throw new EqualExchangeRateException("Так как курсы валют совпадают, невозможно получить gif.");
    }

    private String getYesterdayDate() {
        // Так как текущий курс определяется за последние полные сутки
        ZonedDateTime yesterday = ZonedDateTime.now().minusHours(48);
        return  yesterday.getYear() + "-" + yesterday.getMonthValue() + "-" + yesterday.getDayOfMonth();
    }
}
