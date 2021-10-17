package com.application.alpha_task;

import com.application.alpha_task.client.CurrencyRateClient;
import com.application.alpha_task.client.GifClient;
import com.application.alpha_task.exception.EqualExchangeRateException;
import com.application.alpha_task.query.response.*;
import com.application.alpha_task.service.AlphaTaskService;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;

@SpringBootTest
class AlphaTaskApplicationTests {
    @MockBean
    private CurrencyRateClient currencyRateClient;
    @MockBean
    private GifClient gifClient;
    @Autowired
    private AlphaTaskService alphaTaskService;

    @BeforeEach
    void initTests() { // Инициализируем общие для всех тестов свойства приложения
        ReflectionTestUtils.setField(alphaTaskService, "hoursAgo", 24);
        ReflectionTestUtils.setField(alphaTaskService, "relativeWhichCurrency", "USD");
        ReflectionTestUtils.setField(alphaTaskService, "gifAppId", "gifAppId");
    }

    @Test
    void richServiceTest() {
        ReflectionTestUtils.setField(alphaTaskService, "exchangeAppId", "exchangeAppIdRich");
        // Тестовый случай rich
        Mockito.when(currencyRateClient.getExchangeRates("exchangeAppIdRich", "USD", "RUB")).thenReturn(
                new ExchangeRateResponse(
                        "disclaimer",
                        "license",
                        1,
                        "USD",
                        new HashMap<String, Double>() {{
                            put("RUB", 73.2);
                        }}
                )
        );
        Mockito.when(currencyRateClient.getYesterdayExchangeRate(
                ReflectionTestUtils.invokeMethod(alphaTaskService, "getYesterdayDate"), "exchangeAppIdRich", "USD", "RUB")).thenReturn(
                new ExchangeRateResponse(
                        "disclaimer",
                        "license",
                        1,
                        "USD",
                        new HashMap<String, Double>() {{
                            put("RUB", 72.67);
                        }}
                )
        );
        // Симулируем получение данных о gif с сервиса giphy для ситуации rich
        GiphyResponseData giphyResponseData = new GiphyResponseData();
        giphyResponseData.setImage_original_url("urlRich");
        giphyResponseData.setTitle("gifRichName");
        GiphyResponse giphyResponseRich = new GiphyResponse(giphyResponseData, new GiphyResponseMetaData());
        Mockito.when(gifClient.getRandomGif("gifAppId", "rich", "r")).thenReturn(
                giphyResponseRich
        );
        UrlGifResponse urlGifResponse = alphaTaskService.getExchangeRateGif("RUB");
        Assertions.assertEquals("urlRich", urlGifResponse.getUrl());
        Assertions.assertEquals("gifRichName", urlGifResponse.getName());
    }

    @Test
    void brokeServiceTest() {
        // Тестовый случай broke
        ReflectionTestUtils.setField(alphaTaskService, "exchangeAppId", "exchangeAppIdBroke");
        Mockito.when(currencyRateClient.getExchangeRates("exchangeAppIdBroke", "USD", "RUB"))
                .thenReturn(new ExchangeRateResponse(
                                "disclaimer",
                                "license",
                                1,
                                "USD",
                                new HashMap<String, Double>() {{
                                    put("RUB", 72.3);
                                }}
                        )
                );
        Mockito.when(currencyRateClient.getYesterdayExchangeRate(
                ReflectionTestUtils.invokeMethod(alphaTaskService, "getYesterdayDate"),
                "exchangeAppIdBroke", "USD", "RUB")).thenReturn(new ExchangeRateResponse(
                        "disclaimer",
                        "license",
                        1,
                        "USD",
                        new HashMap<String, Double>() {{
                            put("RUB", 74.2);
                        }}
                )
        );
        GiphyResponseData giphyResponseData = new GiphyResponseData();
        giphyResponseData.setImage_original_url("urlBroke");
        giphyResponseData.setTitle("gifBrokeName");
        GiphyResponse giphyResponseBroke = new GiphyResponse(giphyResponseData, new GiphyResponseMetaData());
        Mockito.when(gifClient.getRandomGif("gifAppId", "broke", "r")).thenReturn(
                giphyResponseBroke
        );
        UrlGifResponse urlGifResponse = alphaTaskService.getExchangeRateGif("RUB");
        Assertions.assertEquals("urlBroke", urlGifResponse.getUrl());
        Assertions.assertEquals("gifBrokeName", urlGifResponse.getName());
    }

    @Test
    void equalServiceTest() {
        // Тестовый случай equal
        ReflectionTestUtils.setField(alphaTaskService, "exchangeAppId", "exchangeAppIdEqual");
        Mockito.when(currencyRateClient.getExchangeRates("exchangeAppIdEqual", "USD", "RUB"))
                .thenReturn(new ExchangeRateResponse(
                                "disclaimer",
                                "license",
                                1,
                                "USD",
                                new HashMap<String, Double>() {{
                                    put("RUB", 71.8);
                                }}
                        )
                );
        Mockito.when(currencyRateClient.getYesterdayExchangeRate(
                ReflectionTestUtils.invokeMethod(alphaTaskService, "getYesterdayDate"),
                "exchangeAppIdEqual", "USD", "RUB")).thenReturn(new ExchangeRateResponse(
                        "disclaimer",
                        "license",
                        1,
                        "USD",
                        new HashMap<String, Double>() {{
                            put("RUB", 71.8);
                        }}
                )
        );
        Assertions.assertThrows(
                EqualExchangeRateException.class, () -> alphaTaskService.getExchangeRateGif("RUB")
        );
    }

    @Test
    void incorrectCurrencyCodeServiceTest() {
        // Тестовый случай broke
        ReflectionTestUtils.setField(alphaTaskService, "exchangeAppId", "exchangeAppIdBroke");
        Mockito.when(currencyRateClient.getExchangeRates("exchangeAppIdBroke", "USD", "123"))
                .thenReturn(new ExchangeRateResponse(
                                "disclaimer",
                                "license",
                                1,
                                "USD",
                                new HashMap<>()
                        )
                );
        Mockito.when(currencyRateClient.getYesterdayExchangeRate(
                ReflectionTestUtils.invokeMethod(alphaTaskService, "getYesterdayDate"),
                "exchangeAppIdBroke", "USD", "123")).thenReturn(new ExchangeRateResponse(
                        "disclaimer",
                        "license",
                        1,
                        "USD",
                        new HashMap<>()
                )
        );
        Assertions.assertThrows(
                NullPointerException.class, () -> alphaTaskService.getExchangeRateGif("123")
        );
    }
}
