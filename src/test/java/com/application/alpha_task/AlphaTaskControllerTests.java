package com.application.alpha_task;

import com.application.alpha_task.query.request.GifExchangeRateRequest;
import com.application.alpha_task.query.response.UrlGifResponse;
import com.application.alpha_task.service.AlphaTaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AlphaTaskControllerTests {
    @MockBean
    private AlphaTaskService alphaTaskService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void correctWorkControllerTest() throws Exception {
        UrlGifResponse urlGifResponse = new UrlGifResponse(
                "RUB",
                "USD",
                71.2,
                71.5,
                71.2 - 71.5,
                "broke",
                "gifName",
                "gifUrl"
        );
        Mockito.when(alphaTaskService.getExchangeRateGif("RUB")).thenReturn(urlGifResponse);
        mockMvc.perform(
                get("/exchange_rate/gif/url")
                        .param("currencyCode", "RUB")
                        .flashAttr("gifExchangeRateRequest", new GifExchangeRateRequest()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("url").value("gifUrl"))
                .andExpect(jsonPath("name").value("gifName")
                );
    }

    @Test
    void emptyCurrencyCodeControllerTest() throws Exception {
        mockMvc.perform(
                get("/exchange_rate/gif/url")
                        .param("currencyCode", "")
                        .flashAttr("gifExchangeRateRequest", new GifExchangeRateRequest()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void incorrectFormatCurrencyCodeControllerTest() throws Exception {
        mockMvc.perform(
                get("/exchange_rate/gif/url")
                        .param("currencyCode", "RUB2")
                        .flashAttr("gifExchangeRateRequest", new GifExchangeRateRequest()))
                .andExpect(status().isBadRequest());
    }


}
